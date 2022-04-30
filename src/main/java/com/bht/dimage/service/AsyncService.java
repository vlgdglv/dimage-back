package com.bht.dimage.service;

import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface AsyncService {
    Integer updatePurchase(List<PurchaseTransaction> ptxList);
}
