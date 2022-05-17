package com.bht.dimage.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@Api("图片实体类")
public class Image {
    @ApiModelProperty(value = "图片ID", required = true)
    private long imageID;
    @ApiModelProperty(value = "作者", required = true)
    private String author;
    @ApiModelProperty(value = "所有者", required = true)
    private String owner;
//    @ApiModelProperty(value = "IPFS地址", required = true)
//    private String ipfsHash;
    @ApiModelProperty(value = "图片SHA3(keccak256)", required = true)
    private String sha3;
    @ApiModelProperty(value = "数字签名", required = true)
    private String signature;
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    @ApiModelProperty(value = "缩略图地址", required = true)
    private String thumbnailPath;
    @ApiModelProperty(value = "发布时间", required = false)
    private Date releaseTime;
    @ApiModelProperty(value = "持续时间", required = false)
    private int txCount;


    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public String getSha3() { return sha3; }

    public void setSha3(String sha3) { this.sha3 = sha3; }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }
}
