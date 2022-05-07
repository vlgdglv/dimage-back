package com.bht.dimage.dto;

public class UpdatePurchaseDto {
    private String contractAddress;
    private String from;
    private int oldState;
    private int newState;
    private String signature;
    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getOldState() {
        return oldState;
    }

    public void setOldState(int oldState) {
        this.oldState = oldState;
    }

    public int getNewState() {
        return newState;
    }

    public void setNewState(int newState) {
        this.newState = newState;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
