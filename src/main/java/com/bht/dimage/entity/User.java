package com.bht.dimage.entity;

import java.util.Date;

public class User {
    //合约地址
    private String address;
    //用户名
    private String userName;
    //密码
    private String password;
    //是否白名单
    private int isWL;
    //创建时间
    private Date createTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsWL() {
        return isWL;
    }

    public void setIsWL(int isWL) {
        this.isWL = isWL;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
