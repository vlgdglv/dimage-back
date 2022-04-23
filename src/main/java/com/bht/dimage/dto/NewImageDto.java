package com.bht.dimage.dto;

public class NewImageDto {
    private String author;
    private String hash;
    private String sha3;
    private String signature;
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
}
