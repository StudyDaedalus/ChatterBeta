package com.blessingsoftware.chatterbeta.Models.DAO;
/*

    written by CAT from/to Nov,28th,2018

 */
public class UserTable {
    public static final String TABLE_NAME="user_info";//总表名

    public static final String USER_ID="user_id";//用户id，主键，不可重复，自增1，int，max=100
    public static final String USER_NAME="user_name";//用户昵称，varchar(32)
    public static final String USER_HEAD="user_head";//用户头像，varchar(32)
    public static final String USER_SEX="user_sex";//用户性别，varchar(4)
    public static final String USER_SIGN="user_sign";//用户签名，varchar(64)
    public static final String USER_ACCOUNT="user_account";//用户账号，不可重复，varchar(32)，*暂时未定*
    public static final String USER_LOCATION="user_location";//用户定位，varchar(20)，*暂时未定*

    public static final String CREATE_TABLE="create table "
            +TABLE_NAME+ " ("
            +"_id integer autoinc primary key,"
            +USER_ID+" integer,"
            +USER_NAME+" varchar(32),"
            +USER_HEAD+" varchar(32),"
            +USER_SEX+" varchar(4),"
            +USER_SIGN+" varchar(64),"
            +USER_ACCOUNT+" varchar(32),"
            +USER_LOCATION+" varchar(20));";//就是用SQL语言添加一个数据库呗还能怎样233333....
}
