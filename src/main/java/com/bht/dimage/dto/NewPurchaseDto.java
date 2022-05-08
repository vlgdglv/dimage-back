package com.bht.dimage.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class NewPurchaseDto {
    private String contractAddress;
    private String purchaser;
    private String imageOwner;
    private String imageAuthor;

    private String ownerShare;

    private String authorShare;
    private long imageID;
    private String sha3;
    private String offer;
    private long launchTime;
    private long duration;

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getImageOwner() {
        return imageOwner;
    }

    public void setImageOwner(String imageOwner) {
        this.imageOwner = imageOwner;
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(String imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getOwnerShare() {
        return ownerShare;
    }

    public void setOwnerShare(String ownerShare) {
        this.ownerShare = ownerShare;
    }

    public String getAuthorShare() {
        return authorShare;
    }

    public void setAuthorShare(String authorShare) {
        this.authorShare = authorShare;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public String getSha3() { return sha3; }

    public void setSha3(String sha3) { this.sha3 = sha3; }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public long getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(long launchTime) {
        this.launchTime = launchTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
