package com.bht.dimage.service.impl;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.dto.UpdatePurchaseDto;
import com.bht.dimage.entity.Image;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.AsyncService;
import com.bht.dimage.service.PrevOwnerService;
import com.bht.dimage.service.PurchaseService;
import com.bht.dimage.vo.TxImageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    PurchaseDao purchaseDao;
    @Resource
    ImageDao imageDao;
    @Resource
    AsyncService asyncService;
    @Resource
    PrevOwnerService prevOwnerService;

    @Override
    public RestResult createPurchase(PurchaseTransaction ptx) {
        List<PurchaseTransaction> ptxList = null;
        ptxList = purchaseDao.selectByPurchaserAndImage(ptx.getPurchaser(), ptx.getImageID());
        if (ptxList.size() != 0 ) {
//            return RestResult.Fail().message("You already made an offer for this!");
        }
        ptxList = purchaseDao.selectByContractAddress(ptx.getContractAddress());
        if (ptxList.size() != 0 ) {
            return RestResult.Fail().message("This contract already exits!");
        }
        ptx.setIsClosed(0);
        ptx.setState(1);
        ptx.setPrevOwnerShareRatio(-1);
        if (purchaseDao.insertPurchase(ptx) == 1) {
            return RestResult.Success().message("Purchase inserted");
        }else {
            return RestResult.Fail().message("Purchase fail to insert!");
        }
    }

    @Override
    public List<PurchaseTransaction> fetchTxByOwner(String owner, int currentPage, int pageCount, int state) {
        purchaseDao.updateExpiredByOwner(owner);
        int begin = (currentPage - 1 ) * pageCount;
        List<PurchaseTransaction> ptxList;
        if (state == -3) {
            ptxList = purchaseDao.selectExpiredByOwner(owner,begin, pageCount);
        }else if (state == 0 ) {
            ptxList = purchaseDao.selectByImageOwner(owner, begin, pageCount, 0);
            ptxList.addAll(purchaseDao.selectByImageOwner(owner, begin, pageCount, 2));
            //TODO:get previous owner share ratio
            ptxList = prevOwnerService.calPrevOwnerShare(ptxList);
        }else {
            ptxList = purchaseDao.selectByImageOwner(owner, begin, pageCount, state);
        }
        if (ptxList == null) {
            return null;
        }
        return ptxList;
    }

    @Override
    public List<PurchaseTransaction> fetchTxByPurchaser(String purchaser, int currentPage, int pageCount, int state) {
//        purchaseDao.updateExpiredByPurchaser(purchaser);
        int begin = (currentPage - 1 ) * pageCount;
        List<PurchaseTransaction> ptxList ;

        ptxList = purchaseDao.selectByPurchaser(purchaser, begin, pageCount, state);

        if (ptxList == null) {
            return null;
        }
        return ptxList;
    }

    @Override
    public RestResult updateTx(UpdatePurchaseDto updatePurchaseDto) {
        List<PurchaseTransaction> pl = purchaseDao.selectByContractAddress(updatePurchaseDto.getContractAddress());
        if (pl.size() == 0) {
            return RestResult.Fail().message("Contract not found");
        }
        PurchaseTransaction ptx = pl.get(0);
        String from = updatePurchaseDto.getFrom();
        int newState = updatePurchaseDto.getNewState();
        int oldStateOrigin = ptx.getState();
        int oldStateProvided = updatePurchaseDto.getOldState();
//        System.out.println(oldStateProvided);
//        System.out.println(oldStateOrigin);

        if (oldStateProvided != oldStateOrigin) {
            return RestResult.Fail().message("Given state is incorrect");
        }
        if (oldStateProvided <= 0) {
            return RestResult.Fail().message("This transaction is dead");
        }

        if (newState == -3) {
            ptx.setIsClosed(1);
        }
        //Change owner
        if (oldStateOrigin==1) {
            if (newState == 2){
                if (!from.equalsIgnoreCase(ptx.getImageOwner())) {
                    return RestResult.Fail().message("No permission 1-2");
                }else {
                    Image image = imageDao.selectImageByID(ptx.getImageID()).get(0);
                    image.setOwner(ptx.getPurchaser());
                    System.out.println(ptx.getImageID() + " Changing owner:" + ptx.getPurchaser());
                    image.setTxCount(image.getTxCount()+1);
                    if (imageDao.updateImage(image) != 1){
                        return RestResult.Fail().message("Databases error!");
                    }
                    String sha3 = image.getSha3();
                    prevOwnerService.addPrevOwner(sha3,ptx.getImageOwner());
                    //TODO:decline other
                }
            }
            if (newState == -1 && !from.equalsIgnoreCase(ptx.getImageOwner())) {
                return RestResult.Fail().message("No permission 1--1");
            }
            if (newState == -2 && !from.equalsIgnoreCase(ptx.getPurchaser())) {
                return RestResult.Fail().message("No permission 1--2");
            }
        }
        if (oldStateOrigin==2 && newState==0){
            System.out.println("From:" + from);
            System.out.println("Purc:" + ptx.getPurchaser());
            if (!from.equalsIgnoreCase(ptx.getPurchaser())){
                return RestResult.Fail().message("No permission 2-0");
            }else {
                String sign = updatePurchaseDto.getSignature();
                if (sign == null || sign.equals("")) {  return RestResult.Fail().message("No signature!"); }
                Image image = imageDao.selectImageByID(ptx.getImageID()).get(0);
                image.setSignature(sign);
                if (imageDao.updateImage(image) != 1){
                    return RestResult.Fail().message("Databases error!");
                }
            }
        }
        ptx.setState(newState);

        if (purchaseDao.updateByTxID(ptx) == 1) {
            return RestResult.Success().data(ptx);
        }else{
            return RestResult.Fail().message("Databases error!");
        }
    }

    @Override
    public RestResult fetchTxByID(long txID) {
        PurchaseTransaction ptx = purchaseDao.selectTxByID(txID);
        if (ptx == null) {
            return RestResult.Fail().message("No such transaction");
        }
        Image image = imageDao.selectImageByID(ptx.getImageID()).get(0);
        if (image == null) {
            return RestResult.Fail().message("No image");
        }
        TxImageVO tivo = new TxImageVO();
        tivo.setImage(image);
        tivo.setPtx(ptx);
        return RestResult.Success().data(tivo);
    }


}
