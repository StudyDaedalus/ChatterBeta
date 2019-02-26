package com.blessingsoftware.chatterbeta.Models.Beans;

/*

    written by CAT from/to Dec,2nd,2018

 */
public class MomentInfo {
    private String FriendName;
    private String FriendID;
    private String HeadPhoto;
    private String PublishTime;
    private String PublishText;
    private String PublishImg;

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }

    public String getFriendID() {
        return FriendID;
    }

    public void setFriendID(String friendID) {
        FriendID = friendID;
    }

    public String getHeadPhoto() {
        return HeadPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        HeadPhoto = headPhoto;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public String getPublishText() {
        return PublishText;
    }

    public void setPublishText(String publishText) {
        PublishText = publishText;
    }

    public String getPublishImg() {
        return PublishImg;
    }

    public void setPublishImg(String publishImg) {
        PublishImg = publishImg;
    }
}
