package com.bht.dimage.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class PurchaseTransaction {

    @ApiModelProperty(value = "交易ID", required = false)
    private long txID;
    @ApiModelProperty(value = "交易合约地址", required = true)
    private String contractAddress;
    @ApiModelProperty(value = "购买者", required = true)
    private String purchaser;
    @ApiModelProperty(value = "拥有者", required = true)
    private String imageOwner;
    @ApiModelProperty(value = "作者", required = true)
    private String imageAuthor;
    @ApiModelProperty(value = "图片ID", required = true)
    private long imageID;
    @ApiModelProperty(value = "出价", required = true)
    private String offer;
    @ApiModelProperty(value = "发起时间", required = true)
    private Timestamp launchTime;
    @ApiModelProperty(value = "持续时间", required = true)
    private long duration;
    @ApiModelProperty(value = "结束时间", required = false)
    private Timestamp endTime;
    @ApiModelProperty(value = "是否超过截止时间", required = false)
    private int isClosed;
    /*
     * records purchase transaction's state
     *  value  |                        meaning
     *   -3    |  transaction has expired
     *   -2    |  purchase cancelled by purchaser
     *   -1    |  purchase declined by owner
     *    0    |  transaction done
     *    1    |  transaction launched, pending for owner's confirmation
     *    2    |  owner confirmed, pending for purchaser sign the image
     * */
    @ApiModelProperty(value = "交易状态", required = false)
    private int state;

    public long getTxID() {
        return txID;
    }

    public void setTxID(long txID) {
        this.txID = txID;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getPurchaser() { return purchaser; }

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

    public void setImageAuthor(String imageAuthor) { this.imageAuthor = imageAuthor; }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) { this.imageID = imageID; }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Timestamp getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Timestamp launchTime) {
        this.launchTime = launchTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Timestamp getEndTime() { return endTime; }

    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }

    public int getIsClosed() { return isClosed; }

    public void setIsClosed(int isClosed) { this.isClosed = isClosed; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }
}
