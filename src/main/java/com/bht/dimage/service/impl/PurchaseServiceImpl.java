package com.bht.dimage.service.impl;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.dto.UpdatePurchaseDto;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.AsyncService;
import com.bht.dimage.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    PurchaseDao purchaseDao;

    @Resource
    AsyncService asyncService;

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
        if (purchaseDao.insertPurchase(ptx) == 1) {
            return RestResult.Success().message("Purchase inserted");
        }else {
            return RestResult.Fail().message("Purchase fail to insert!");
        }
    }

    @Override
    public List<PurchaseTransaction> fetchTxByOwner(String owner, int currentPage, int pageCount, int state) {
        int begin = (currentPage - 1 ) * pageCount;
        List<PurchaseTransaction> ptxList ;
        if (state == -3) {
            ptxList = purchaseDao.selectExpiredByOwner(owner,begin, pageCount);
        }else if (state == 0) {
            ptxList = purchaseDao.selectByImageOwner(owner, begin, pageCount, 0);
            ptxList.addAll(purchaseDao.selectByImageOwner(owner, begin, pageCount, 2));
        }else {
            ptxList = purchaseDao.selectByImageOwner(owner, begin, pageCount, state);
        }

        if (ptxList == null) {
            return null;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<PurchaseTransaction> NeedUpdate = new ArrayList<>();
        for(PurchaseTransaction ptx: ptxList) {
            if (ptx.getIsClosed() == 0 && now.after(ptx.getEndTime())) {
                ptx.setIsClosed(1);
                NeedUpdate.add(ptx);
                ptxList.remove(ptx);
            }
        }
        if (NeedUpdate.size() != 0) {
            asyncService.updatePurchase(ptxList);
        }
        return ptxList;
    }

    @Override
    public List<PurchaseTransaction> fetchTxByPurchaser(String purchaser, int currentPage, int pageCount, int state) {
        int begin = (currentPage - 1 ) * pageCount;
        List<PurchaseTransaction> ptxList ;
        if (state == -3) {
            ptxList = purchaseDao.selectExpiredByPurchaser(purchaser, begin ,pageCount);
        }else {
            ptxList = purchaseDao.selectByPurchaser(purchaser, begin, pageCount, state);
        }
        if (ptxList == null) {
            return null;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<PurchaseTransaction> NeedUpdate = new ArrayList<>();
        for(PurchaseTransaction ptx: ptxList) {
            if (ptx.getIsClosed() == 0 && now.after(ptx.getEndTime())) {
                ptx.setIsClosed(1);
                NeedUpdate.add(ptx);
                ptxList.remove(ptx);
            }
        }
        if (NeedUpdate.size() != 0) {
            asyncService.updatePurchase(ptxList);
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
        System.out.println(oldStateProvided);
        System.out.println(oldStateOrigin);

        if (oldStateProvided != oldStateOrigin) {
            return RestResult.Fail().message("Given state is incorrect");
        }
        if (oldStateProvided <= 0) {
            return RestResult.Fail().message("This transaction is dead");
        }

        if (newState == -3) {
            ptx.setIsClosed(1);
        }
        if (oldStateOrigin==1) {
            if (newState == 2 && !from.equals(ptx.getImageOwner())) {
                return RestResult.Fail().message("No permission 1-2");
            }
            if (newState == -1 && !from.equals(ptx.getImageOwner())) {
                return RestResult.Fail().message("No permission 1--1");
            }
            if (newState == -2 && !from.equals(ptx.getPurchaser())) {
                return RestResult.Fail().message("No permission 1--2");
            }
        }
        if (oldStateOrigin==2 && newState==0){
            if (!from.equals(ptx.getPurchaser())){
                return RestResult.Fail().message("No permission 2-0");
            }
        }
        ptx.setState(newState);

        if (purchaseDao.updateByTxID(ptx) == 1) {
            return RestResult.Success().data(ptx);
        }else{
            return RestResult.Fail().message("Databases error!");
        }
    }
}
