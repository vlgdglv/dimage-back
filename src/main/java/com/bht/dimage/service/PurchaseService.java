package com.bht.dimage.service;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.entity.PurchaseTransaction;

public interface PurchaseService {
    RestResult createPurchase(PurchaseTransaction ptx);
    RestResult fetchTxByPurchaser(String purchaser);
    RestResult fetchTxByOwner(String owner);
}
