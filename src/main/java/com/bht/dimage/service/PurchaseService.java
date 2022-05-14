package com.bht.dimage.service;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.UpdatePurchaseDto;
import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface PurchaseService {
    RestResult createPurchase(PurchaseTransaction ptx);
    List<PurchaseTransaction> fetchTxByOwner(String owner, int currentPage, int pageCount, int state);
    List<PurchaseTransaction> fetchTxByPurchaser(String purchaser, int currentPage, int pageCount, int state);
    RestResult updateTx(UpdatePurchaseDto updatePurchaseDto);
    RestResult fetchTxByID(long txID);
}
