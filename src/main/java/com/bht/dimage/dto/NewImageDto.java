package com.bht.dimage.dto;

public class NewImageDto {
    private long imgID;
    private String author;
    private String sha3;
    private String signature;
    private String title;
    private String thumbnailPath;

    public long getImgID() {
        return imgID;
    }

    public void setImgID(long imgID) {
        this.imgID = imgID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSha3() {
        return sha3;
    }

    public void setSha3(String sha3) {
        this.sha3 = sha3;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String sign) {
        this.signature = sign;
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
}