package com.bht.dimage.dao;

import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface PurchaseDao {
    int insertPurchase(PurchaseTransaction ptx);
    List<PurchaseTransaction> selectByPurchaserAndImage(String purchaser, long imageID);
    List<PurchaseTransaction> selectByContractAddress(String address);
    List<PurchaseTransaction> selectByPurchaser(String purchaser);
    List<PurchaseTransaction> selectByImageOwner(String imageOwner);
}
