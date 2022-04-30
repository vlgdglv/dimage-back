package com.bht.dimage.service;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.UpdatePurchaseDto;
import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface PurchaseService {
    RestResult createPurchase(PurchaseTransaction ptx);
    RestResult fetchTxByPurchaser(String purchaser);
    RestResult fetchTxByOwner(String owner);
    RestResult updateTx(UpdatePurchaseDto updatePurchaseDto);
}
