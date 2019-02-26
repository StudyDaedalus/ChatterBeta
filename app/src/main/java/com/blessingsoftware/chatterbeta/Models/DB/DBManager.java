package com.blessingsoftware.chatterbeta.Models.DB;

 /*

    Sth wrong,but I cant found it.
    written by CAT from/to Dec,2nd,2018

 */

import android.content.Context;

import com.blessingsoftware.chatterbeta.Models.DAO.ChatTableDao;
import com.blessingsoftware.chatterbeta.Models.DAO.FriendTableDao;
import com.blessingsoftware.chatterbeta.Models.DAO.InvitationTableDao;
import com.blessingsoftware.chatterbeta.Models.DAO.MomentTableDao;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTableDao;

public class DBManager {

    private final DBHelper dbHelper;

    public DBManager(Context context,String name){//创建数据库，不知为何报错 -12.2
        dbHelper=new DBHelper(context,name);
    }

    public MomentTableDao getMomentTableDao(){
        return new MomentTableDao(dbHelper);
    }

    public ChatTableDao getChatTableDao(){
        return new ChatTableDao(dbHelper);
    }

    public FriendTableDao getFriendTableDao(){
        return new FriendTableDao(dbHelper);
    }

    public InvitationTableDao getInvitataionTableDao(){
        return new InvitationTableDao(dbHelper);
    }

    public UserTableDao getUserTableDao(){
        return new UserTableDao(dbHelper);
    }

    public void close(){
        dbHelper.close();
    }
}
