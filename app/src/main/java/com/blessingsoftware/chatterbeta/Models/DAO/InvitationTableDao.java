package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from/to Dec,2nd,2018

 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blessingsoftware.chatterbeta.Models.Beans.InvitationInfo;
import com.blessingsoftware.chatterbeta.Models.DB.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationTableDao {

    private DBHelper dbHelper;

    public InvitationTableDao(DBHelper helper){
        dbHelper=helper;
    }

    public void addInvitation(InvitationInfo invitationInfo){

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String Sql="INSERT INTO invitation(account,reason,status) values('"
                +invitationInfo.getAccount()+"','"
                +invitationInfo.getReason()+"',1;";
        db.execSQL(Sql);
        db.close();
    }

    public List<Map<String,Object>>getInvitationInfo(){

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String Sql="SELECT * FROM invitation WHERE status=1 ORDER BY _id DESC";
        Cursor cursor=db.rawQuery(Sql,null);

        List<Map<String,Object>> newFriendList=new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
        while (cursor.moveToNext()){
            map=new HashMap<String, Object>();
            map.put(InvitationTable.ACCOUNT,cursor.getString(cursor.getColumnIndex(InvitationTable.ACCOUNT)));
            map.put(InvitationTable.REASON,cursor.getString(cursor.getColumnIndex(InvitationTable.REASON)));
            newFriendList.add(map);
        }
        return newFriendList;
    }

    public  void setAdded(String Account){

        SQLiteDatabase db=dbHelper.getWritableDatabase();//创建数据库 -12.2
        String Sql="update invitation set status=0 where account='"+Account+"'";//更新语句 -12.2
        db.execSQL(Sql);//更新 -12.2
        db.close();//关闭 -12.2

    }
}
