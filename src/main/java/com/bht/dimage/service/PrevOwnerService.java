package com.bht.dimage.service;

import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface PrevOwnerService {
    int addPrevOwner(String sha3, String address);
    List<PurchaseTransaction> calPrevOwnerShare(List<PurchaseTransaction> ptxList);
}
