package com.blessingsoftware.chatterbeta.Models.Beans;

import java.util.List;
import java.util.Map;

/*

    written by CAT from/to Dec,2nd,2018
    有关对象（请注意，此处有极大问题，需要修改） -12.2

 */
public class ChatInfo {

    //基础属性 -12.2
    private int userID,friendID;
    private String userName,userHead,friendName,friendHead,friendExpandRelation;

    private List<Map<String,Object>> chatMsgData; //聊天记录数据 -12.2
    private List<Map<String,Object>> recentChatData; //聊天对象 -12.2

    public ChatInfo(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
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

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }

    public String getFriendExpandRelation() {
        return friendExpandRelation;
    }

    public void setFriendExpandRelation(String friendExpandRRelation) {
        this.friendExpandRelation = friendExpandRRelation;
    }

    public List<Map<String, Object>> getChatMsgData() {
        return chatMsgData;
    }

    public void setChatMsgData(List<Map<String, Object>> chatMsgData) {
        this.chatMsgData = chatMsgData;
    }

    public List<Map<String, Object>> getRecentChatData() {
        return recentChatData;
    }

    public void setRecentChatData(List<Map<String, Object>> recentChatData) {
        this.recentChatData = recentChatData;
    }
}
