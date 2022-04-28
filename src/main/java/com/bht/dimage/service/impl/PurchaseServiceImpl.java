package com.bht.dimage.service.impl;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    PurchaseDao purchaseDao;

    @Override
    public RestResult createPurchase(PurchaseTransaction ptx) {
        List<PurchaseTransaction> ptxList = null;
        ptxList = purchaseDao.selectByPurchaserAndImage(ptx.getPurchaser(), ptx.getImageID());
        if (ptxList.size() != 0 ) {
            return RestResult.Fail().message("You already made an offer for this!");
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
    public RestResult fetchTxByPurchaser(String purchaser) {
        List<PurchaseTransaction> ptxList = purchaseDao.selectByPurchaser(purchaser);
        if (ptxList == null) {
            return RestResult.Fail().message("database error!");
        }
        return RestResult.Success().data(ptxList);
    }

    @Override
    public RestResult fetchTxByOwner(String owner) {
        List<PurchaseTransaction> ptxList = purchaseDao.selectByImageOwner(owner);
        if (ptxList == null) {
            return RestResult.Fail().message("database error!");
        }
        return RestResult.Success().data(ptxList);
    }
}
