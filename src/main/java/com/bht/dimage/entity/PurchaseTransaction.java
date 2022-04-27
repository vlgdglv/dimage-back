package com.bht.dimage.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PurchaseTransaction {
    @ApiModelProperty(value = "交易合约地址", required = true)
    private String contractAddress;
    @ApiModelProperty(value = "结束时间", required = true)
    private String purchaser;
    @ApiModelProperty(value = "结束时间", required = true)
    private String imageOwner;
    @ApiModelProperty(value = "结束时间", required = true)
    private String imageAuthor;
    @ApiModelProperty(value = "结束时间", required = true)
    private long imageID;
    @ApiModelProperty(value = "结束时间", required = true)
    private double offer;
    @ApiModelProperty(value = "结束时间", required = true)
    private long launchTime;
    @ApiModelProperty(value = "结束时间", required = true)
    private long duration;
    @ApiModelProperty(value = "结束时间", required = false)
    private long endTime;
    @ApiModelProperty(value = "是否超过截止时间", required = false)
    private int isClosed;
    /*
     * records purchase transaction's state
     *  value  |                        meaning
     *   -2    |  purchase cancelled by purchaser
     *   -1    |  purchase declined by owner
     *    0    |  transaction done
     *    1    |  transaction launched, pending for owner's confirmation
     *    2    |  owner confirmed, pending for purchaser sign the image
     * */
    @ApiModelProperty(value = "交易状态", required = false)
    private int state;

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

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
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

    public long getEndTime() { return endTime; }

    public void setEndTime(long endTime) { this.endTime = endTime; }

    public int getIsClosed() { return isClosed; }

    public void setIsClosed(int isClosed) { this.isClosed = isClosed; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }
}
