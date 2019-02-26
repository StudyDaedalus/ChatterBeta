package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from/to Dec,2nd,2018

 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blessingsoftware.chatterbeta.Models.Beans.MomentInfo;
import com.blessingsoftware.chatterbeta.Models.DB.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MomentTableDao {

    private DBHelper dbHelper;

    public MomentTableDao(DBHelper helper){
        dbHelper=helper;
    }

    public List<MomentInfo>getAllMomentInfo(){
        List<MomentInfo>momentInfoList=new ArrayList<>();

        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String Sql="SELECT * FROM moment";
        Cursor cursor=db.rawQuery(Sql,null);

        while (cursor.moveToNext()){
            MomentInfo momentInfo=new MomentInfo();
            momentInfo.setFriendName(cursor.getString(cursor.getColumnIndex(MomentTable.FriendName)));
            momentInfo.setFriendID(cursor.getString(cursor.getColumnIndex(MomentTable.FriendID)));
            momentInfo.setHeadPhoto(cursor.getString(cursor.getColumnIndex(MomentTable.HeadPhoto)));
            momentInfo.setPublishTime(cursor.getString(cursor.getColumnIndex(MomentTable.PublishTime)));
            momentInfo.setPublishImg(cursor.getString(cursor.getColumnIndex(MomentTable.PublishImg)));
            momentInfo.setPublishText(cursor.getString(cursor.getColumnIndex(MomentTable.PublishText)));
            momentInfo.setPublishTime(cursor.getString(cursor.getColumnIndex(MomentTable.PublishTime)));
            momentInfoList.add(momentInfo);
        }
        db.close();
        return momentInfoList;

    }
}
