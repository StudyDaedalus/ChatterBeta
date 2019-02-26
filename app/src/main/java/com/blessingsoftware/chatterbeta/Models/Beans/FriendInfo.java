package com.blessingsoftware.chatterbeta.Models.Beans;

/*

    written by CAT from/to Nov,30th,2018

 */
public class FriendInfo {

    private int friendID,newFriendFlag;
    private String groupName,friendName,nickName,friendSex,friendAccount,frientHead,
                    friendLocation,friendRecentPhoto,UserAccount;

    public FriendInfo(){}

    public FriendInfo(int friendID,String groupName,String friendName,String nickName,String friendSex,
                        String friendAccount,String frientHead,String friendLocation,String friendRecentPhoto,
                        int newFriendFlag){
        this.friendID=friendID;
        this.groupName=groupName;
        this.friendName=friendName;
        this.nickName=nickName;
        this.friendSex=friendSex;
        this.friendAccount=friendAccount;
        this.frientHead=frientHead;
        this.friendLocation=friendLocation;
        this.friendRecentPhoto=friendRecentPhoto;
        this.newFriendFlag=newFriendFlag;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getNewFriendFlag() {
        return newFriendFlag;
    }

    public void setNewFriendFlag(int newFriendFlag) {
        this.newFriendFlag = newFriendFlag;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFriendSex() {
        return friendSex;
    }

    public void setFriendSex(String friendSex) {
        this.friendSex = friendSex;
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    public String getFrientHead() {
        return frientHead;
    }

    public void setFrientHead(String frientHead) {
        this.frientHead = frientHead;
    }

    public String getFriendLocation() {
        return friendLocation;
    }

    public void setFriendLocation(String friendLocation) {
        this.friendLocation = friendLocation;
    }

    public String getFriendRecentPhoto() {
        return friendRecentPhoto;
    }

    public void setFriendRecentPhoto(String friendRecentPhoto) {
        this.friendRecentPhoto = friendRecentPhoto;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }
}
