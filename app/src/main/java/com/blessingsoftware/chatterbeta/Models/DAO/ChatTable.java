package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from/to Nov,30th,2018

 */

public class ChatTable {
    public static final String TABLE_NAME = "chat_msg";//总表名 -11.30

    public static final String USER_ID = "user_id";//当前用户ID -11.30
    public static final String FRIEND_ID = "friend_id";//好友ID -11.30
    public static final String CHAT_MSG_CONTENT = "chat_msg_content";//消息内容 -11.30
    public static final String CHAT_MSG_TIME = "chat_msg_time";//消息时间 -11.30
    public static final String CHAT_MSG_TYPE = "chat_msg_type";//消息类型 -11.30

    public static final String CHAT_MSG_TYPE_RECEIVER = "receive";
    public static final String CHAT_MSG_TYPE_SEND = "send";//消息类型，便于判断是接受还是发送 -11.30

    public static final String TARGET_EXPAND_RELATION_SWEET = "sweet";
    public static final String TARGET_EXPAND_RELATION_WORK = "work";
    public static final String TARGET_EXPAND_RELATION_FAMILY = "family";//此处未决定使用与否，暂且定义着：目标受众 -11.30

    public static final String SHOW_TIME_FLAG = "show_time_flag";//显示时间用 -11.30
    public static final int HIDE_TIME = 0;//消息过期时间 -11.30
    public static final int SHOW_TIME = 1;//消息显示时间 -11.30
    public static final int SHOW_DATE = 2;//消息显示日期 -11.30

    public static final String CREATE_TABLE = "create table"
            + TABLE_NAME + "("
            + "[_id] integer autoic primary key,"
            +USER_ID+"integer,"
            +FRIEND_ID+"integer,"
            +SHOW_TIME_FLAG+"integer,"
            +CHAT_MSG_CONTENT+"text,"
            +CHAT_MSG_TIME+"datetime,"
            +CHAT_MSG_TYPE+"varchar(10))";//建表，并用于Chat框架 -11.30
}