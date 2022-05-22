package com.bht.dimage.service.impl;

import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.AsyncService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Resource
    PurchaseDao purchaseDao;

    @Async("publicExecutor")
    @Override
    public Integer updatePurchase(List<PurchaseTransaction> ptxList) {
        int successCount = 0;
        for(PurchaseTransaction ptx: ptxList) {
            successCount += purchaseDao.updateByTxID(ptx);
        }
        return successCount;
    }
}
