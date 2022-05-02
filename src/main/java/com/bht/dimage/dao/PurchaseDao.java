package com.bht.dimage.dao;

import com.bht.dimage.entity.PurchaseTransaction;

import java.util.List;

public interface PurchaseDao {
    int insertPurchase(PurchaseTransaction ptx);
    List<PurchaseTransaction> selectByPurchaserAndImage(String purchaser, long imageID);
    List<PurchaseTransaction> selectByContractAddress(String address);
    List<PurchaseTransaction> selectByImageOwner(String imageOwner,int begin, int count, int state);
    List<PurchaseTransaction> selectByPurchaser(String purchaser,int begin, int count, int state);
    List<PurchaseTransaction> selectExpiredByOwner(String imageOwner,int begin, int count);
    List<PurchaseTransaction> selectExpiredByPurchaser(String purchaser,int begin, int count);
    int updateByTxID(PurchaseTransaction ptx);
    int countByOwner(String owner, int state);
    int countByPurchaser(String purchaser, int state);
    int countExpiredByOwner(String owner);
    int countExpiredByPurchaser(String purchaser);
}
