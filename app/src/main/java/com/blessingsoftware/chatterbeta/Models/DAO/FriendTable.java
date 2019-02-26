package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from/to Nov,30th,2018

 */

public class FriendTable {

    public static final String TABLE_NAME="friend_info";

    public static final String USER_ID="user_id";
    public static final String FRIEND_ID="friend_id";
    public static final String GROUP_NAME="group_name";
    public static final String FRIEND_NAME="friend_name";
    public static final String NICK_NAME="nick_name";
    public static final String FRIEND_EXPAND_RELATION="friend_expand_relation";
    public static final String FRIEND_SEX="friend_sex";
    public static final String FRIEND_ACCOUNT="friend_account";
    public static final String FRIEND_HEAD="friend_head";
    public static final String FRIEND_LOCATION="friend_location";
    public static final String FRIEND_RECENT_PHOTO="friend_rectnt_photo";//备注，此处用于制作朋友圈功能，先定义着，若无法实现则删除。 -11.30
    public static final String NEW_FRIEND_FLAG="new_friend_flag";
    public static final String NEW_FRIEND_REQUEST_MSG="new_friend_request_flag";//请求获得好友发来的信息。 -11.30

    public static final String CREATE_TABLE="create table "
            +TABLE_NAME+"("
            +"_id integer autoinc primary key,"
            +USER_ID+" integer,"
            +FRIEND_ID+" integer,"
            +GROUP_NAME+" varchar(32),"
            +FRIEND_NAME+" varchar(32),"
            +NICK_NAME+" varchar(32),"
            +FRIEND_EXPAND_RELATION+" varchar(16),"
            +FRIEND_SEX+" varchar(4),"
            +FRIEND_ACCOUNT+" varchar(32),"
            +FRIEND_HEAD+" varchar(108),"
            +FRIEND_LOCATION+" varchar(64),"
            +FRIEND_RECENT_PHOTO+" varchar(108),"
            +NEW_FRIEND_FLAG+" integer,"
            +NEW_FRIEND_REQUEST_MSG+" varchar(50));";//当然，构建数据库，而已。
}
/*
    注记：本处已经准备好制作朋友圈功能，但并非一定能够实现，故此留迹，勿忘。
 */