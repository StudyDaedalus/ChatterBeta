package com.blessingsoftware.chatterbeta.Models.Beans;

public class UserInfo {

    private int userID;

    private String userName,userHead,userSex,userAccount,userLocation,userSign;

    public UserInfo(){}

    public UserInfo(int userID,String userName,String userHead,String userSex,String userAccount,String userLocation,String userSign){
        this.userID=userID;
        this.userName=userName;
        this.userHead=userHead;
        this.userSex=userSex;
        this.userAccount=userAccount;
        this.userLocation=userLocation;
        this.userSign=userSign;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
