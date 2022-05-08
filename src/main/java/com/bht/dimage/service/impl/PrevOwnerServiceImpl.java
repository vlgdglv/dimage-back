package com.bht.dimage.service.impl;

import com.bht.dimage.dao.PrevOwnerDao;
import com.bht.dimage.entity.PrevOwner;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.PrevOwnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrevOwnerServiceImpl implements PrevOwnerService {
    @Resource
    PrevOwnerDao prevOwnerDao;

    @Override
    public int addPrevOwner(String sha3, String address) {
        List<PrevOwner> poList = prevOwnerDao.selectBySHA3(sha3);
        for(PrevOwner po : poList) {
            po.setDistance(po.getDistance()-1);
            if (po.getDistance() == 0) {
                prevOwnerDao.deletePrevOwner(po);
            }else {
                prevOwnerDao.updatePrevOwner(po);
            }
        }
        PrevOwner newPO = new PrevOwner();
        newPO.setDistance(5);
        newPO.setSha3(sha3);
        newPO.setAddress(address);
        return prevOwnerDao.insertPrevOwner(newPO);
    }

    @Override
    public List<PurchaseTransaction> calPrevOwnerShare(List<PurchaseTransaction> ptxList) {
        for(PurchaseTransaction ptx : ptxList) {
            PrevOwner po = prevOwnerDao.selectBySHA3andAddress(ptx.getSha3(),ptx.getImageOwner()).get(0);
            if (po != null) {
                ptx.setPrevOwnerShareRatio(po.getDistance());
            }
        }
        return ptxList;
    }

}
