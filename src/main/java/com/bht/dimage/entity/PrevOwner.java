package com.bht.dimage.entity;

public class PrevOwner {
    private long id;
    private String sha3;
    private String address;
    private int distance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSha3() {
        return sha3;
    }

    public void setSha3(String sha3) {
        this.sha3 = sha3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
