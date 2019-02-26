package com.blessingsoftware.chatterbeta.Models.DB;

/*

    written by CAT from/to Dec,3rd,2018

 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blessingsoftware.chatterbeta.Logs.Logs;
import com.blessingsoftware.chatterbeta.Models.DAO.ChatTable;
import com.blessingsoftware.chatterbeta.Models.DAO.FriendTable;
import com.blessingsoftware.chatterbeta.Models.DAO.InvitationTable;
import com.blessingsoftware.chatterbeta.Models.DAO.MomentTable;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTable;

/*

    written by CAT from Dec,3rd,2018

 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context,String name){
        super(context,name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS moment");
        db.execSQL("DROP TABLE IF EXISTS user_info");
        db.execSQL("DROP TABLE IF EXISTS friend_info");
        db.execSQL("DROP TABLE IF EXISTS chat_msg");
        db.execSQL("DROP TABLE IF EXISTS invitation");

        db.execSQL(MomentTable.CreateTable);
        Logs.d("TABLE",MomentTable.CreateTable);

        db.execSQL(UserTable.CREATE_TABLE);
        Logs.d("TABLE",UserTable.CREATE_TABLE);

        db.execSQL(FriendTable.CREATE_TABLE);
        Logs.d("TABLE",FriendTable.CREATE_TABLE);

        db.execSQL(ChatTable.CREATE_TABLE);
        Logs.d("TABLE",ChatTable.CREATE_TABLE);

        db.execSQL(InvitationTable.CREATE_TABLE);
        Logs.d("TABLE",InvitationTable.CREATE_TABLE);

    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}

}
