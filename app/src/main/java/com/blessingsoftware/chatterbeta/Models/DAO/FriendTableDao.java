package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from Nov,30th,2018 to Dec,1st,2018

 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blessingsoftware.chatterbeta.Models.Beans.FriendInfo;
import com.blessingsoftware.chatterbeta.Models.DB.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendTableDao {

    private DBHelper dbHelper;

    public FriendTableDao(DBHelper helper){
        dbHelper=helper;
    }

    //获取用户信息 -11.30
    public FriendInfo getFriendInfoByAccount(String friendAccount){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        //执行查询语句
        String Sql="SELECT * FROM friend_info WHERE friend_account=?";
        Cursor cursor=db.rawQuery(Sql,new String[]{friendAccount});
        //获取数据
        if(cursor.moveToNext()){
            FriendInfo friendInfo=new FriendInfo();
            friendInfo.setFriendID(cursor.getInt(cursor.getColumnIndex(FriendTable.FRIEND_ID)));
            friendInfo.setFriendName(cursor.getColumnName(cursor.getColumnIndex(FriendTable.FRIEND_NAME)));
            friendInfo.setNickName(cursor.getColumnName(cursor.getColumnIndex(FriendTable.NICK_NAME)));
            friendInfo.setFriendAccount(cursor.getColumnName(cursor.getColumnIndex(FriendTable.FRIEND_ACCOUNT)));
            friendInfo.setFriendSex(cursor.getColumnName(cursor.getColumnIndex(FriendTable.FRIEND_SEX)));
            friendInfo.setFriendLocation(cursor.getColumnName(cursor.getColumnIndex(FriendTable.FRIEND_LOCATION)));
            friendInfo.setFriendRecentPhoto(cursor.getColumnName(cursor.getColumnIndex(FriendTable.FRIEND_RECENT_PHOTO)));
            db.close();
            return friendInfo;
        }
        return null;
    }

    //获取分组列表 -11.30
    public List<String> getGroupList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String Sql="SELECT DISTINCT group_name FROM friend_info ORDER BY group_name";
        Cursor cursor=db.rawQuery(Sql,null);
        List<String> groupList =new ArrayList<String>();
        while (cursor.moveToNext()){
            groupList.add(cursor.getString(cursor.getColumnIndex(FriendTable.GROUP_NAME)));
        }
        db.close();
        return groupList;
    }

    //获取分组子列表 -11.30
    public List<List<Map<String,Object>>> getGroupChildList(List<String> parentList){
        List<List<Map<String,Object>>> childList=new ArrayList<List<Map<String,Object>>>();

        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30

        List<Map<String,Object>> child;
        Map<String,Object> map;
        for(int i=0;i<parentList.size();i++){
            String Sql="SELECT friend_id,friend_name,friend_head FROM friend_info WHERE group_name=?";
            Cursor cursor=db.rawQuery(Sql,new String[]{parentList.get(i)});

            child=new ArrayList<Map<String, Object>>();
            while (cursor.moveToNext()){
                map=new HashMap<String, Object>();
                map.put(FriendTable.FRIEND_ID,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_ID)));
                map.put(FriendTable.FRIEND_NAME,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_NAME)));
                map.put(FriendTable.FRIEND_HEAD,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_HEAD)));
                child.add(map);
            }
            childList.add(child);
        }
        db.close();
        return childList;
    }

    //获取新好友列表数据 -11.30
    public List<Map<String,Object>> getNewFriendListByID(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30

        String Sql="SELECT friend_id,friend_head,friend_name,new_friend_request_msg FROM friend_info WHERE new_friend_flag=1";
        Cursor cursor=db.rawQuery(Sql,null);

        //遍历新的好友列表数据 -11.30
        List<Map<String,Object>> newFriendList=new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
        while(cursor.moveToNext()){
            map=new HashMap<String, Object>();
            map.put(FriendTable.FRIEND_ID,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_ID)));
            map.put(FriendTable.FRIEND_HEAD,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_HEAD)));
            map.put(FriendTable.FRIEND_NAME,cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_NAME)));
            map.put(FriendTable.NEW_FRIEND_REQUEST_MSG,cursor.getString(cursor.getColumnIndex(FriendTable.NEW_FRIEND_REQUEST_MSG)));
            newFriendList.add(map);
        }
        return newFriendList;
    }

    //添加好友（未必准确，需要后期修改） -11.30
    public boolean AddFriend(FriendInfo friendInfo){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        String friendSql="INSERT INTO friend_info(user_id,friend_id,group_name,friend_name,nick_name,friend_sex,friend_account,friend_head,friend_location,friend_recent_photo,new_friend_flag,new_friend_request_msg)"
                +" values(1,"
                +friendInfo.getFriendID()
                +", 'friend','"
                +friendInfo.getFriendName()
                +"', 'xxx','"
                +friendInfo.getFriendSex()
                +"', '"
                +friendInfo.getFriendAccount()
                +"', 'R.drawable.head, '"
                +friendInfo.getFriendLocation()
                +"', null, 1, '你好，能加个好友吗？')";
        db.execSQL(friendSql);
        db.close();
        return true;
    }

    public FriendInfo getFriendInfoByID(int friendID){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        String Sql="SELECT * FROM friend_info WHERE friend_id=?";
        Cursor cursor=db.rawQuery(Sql,new String[]{""+friendID});
        if(cursor.moveToNext()){
            FriendInfo friendInfo=new FriendInfo();
            friendInfo.setFriendID(cursor.getInt(cursor.getColumnIndex(FriendTable.FRIEND_ID)));
            friendInfo.setFriendName(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_NAME)));
            friendInfo.setNickName(cursor.getString(cursor.getColumnIndex(FriendTable.NICK_NAME)));
            friendInfo.setFriendAccount(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_ACCOUNT)));
            friendInfo.setFriendSex(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_SEX)));
            friendInfo.setFriendLocation(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_LOCATION)));
            friendInfo.setFriendRecentPhoto(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_RECENT_PHOTO)));
            db.close();
            return friendInfo;
        }
        return null;
    }
}
