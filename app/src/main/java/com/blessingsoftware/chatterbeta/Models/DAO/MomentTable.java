package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from/to Dec,2nd,2018

 */

public class MomentTable {
    public static final String TableName="moment";
    public static final String FriendName="friendname";
    public static final String FriendID="friendid";
    public static final String HeadPhoto="headphoto";
    public static final String PublishTime="publishtime";
    public static final String PublishText="publishtext";
    public static final String PublishImg="publishimg";

    public static final String CreateTable="create table "
            +TableName+" ("
            +"_id integer autoinc primary key,"
            +FriendName+" varchar(32),"
            +FriendID+" varchar(32),"
            +HeadPhoto+" varchar(108),"
            +PublishTime+" varchar(32),"
            +PublishImg+" varchar(108),"
            +PublishText+" text);";
}
