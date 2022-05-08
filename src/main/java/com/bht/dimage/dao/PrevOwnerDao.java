package com.bht.dimage.dao;

import com.bht.dimage.entity.PrevOwner;

import java.util.List;

public interface PrevOwnerDao {
    List<PrevOwner> selectBySHA3(String sha3);
    int updatePrevOwner(PrevOwner po);
    int deletePrevOwner(PrevOwner po);
    int insertPrevOwner(PrevOwner po);
    List<PrevOwner> selectBySHA3andAddress(String sha3, String address);
}
