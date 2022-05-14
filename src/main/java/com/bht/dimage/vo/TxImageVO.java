package com.bht.dimage.vo;

import com.bht.dimage.entity.Image;
import com.bht.dimage.entity.PurchaseTransaction;

public class TxImageVO {
    Image image;
    PurchaseTransaction ptx;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public PurchaseTransaction getPtx() {
        return ptx;
    }

    public void setPtx(PurchaseTransaction ptx) {
        this.ptx = ptx;
    }
}
