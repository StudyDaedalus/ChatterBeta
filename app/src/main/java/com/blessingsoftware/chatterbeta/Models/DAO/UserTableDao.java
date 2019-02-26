package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    written by CAT from Nov,29th,2018 to

 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blessingsoftware.chatterbeta.Models.Beans.UserInfo;
import com.blessingsoftware.chatterbeta.Models.DB.DBHelper;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTable;

public class UserTableDao {
    private DBHelper dbHelper;

    public UserTableDao(DBHelper helper){
        dbHelper=helper;
    }

    //获取用户信息 -11.29
    public UserInfo getUserInfo(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.29
        String Sql= "SELECT * FROM user_info";
        Cursor cursor=db.rawQuery(Sql,null);
        if(cursor.moveToNext()){
            int userID=cursor.getInt(cursor.getColumnIndex(UserTable.USER_ID));
            String userName=cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME));
            String userHead=cursor.getString(cursor.getColumnIndex(UserTable.USER_HEAD));
            String userSex=cursor.getString(cursor.getColumnIndex(UserTable.USER_SEX));
            String userAccount=cursor.getString(cursor.getColumnIndex(UserTable.USER_ACCOUNT));
            String userLocation=cursor.getString(cursor.getColumnIndex(UserTable.USER_LOCATION));
            String userSign=cursor.getString(cursor.getColumnIndex(UserTable.USER_SIGN));
            UserInfo userInfo=new UserInfo(userID,userName,userHead,userSex,userAccount,userLocation,userSign);
            db.close();
            return userInfo;//获取数据并赋予给UserInfo实例中。 -11.29
        }
        return null;//若未创建成功，返回null -11.29
    }

    //获取用户ID -11.29
    public int getUserID(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.29
        String Sql= "SELECT user_id FROM user_info";//查询语句 -11.29
        Cursor cursor=db.rawQuery(Sql,null);
        if(cursor.moveToNext()){
            int userID=cursor.getInt(cursor.getColumnIndex(UserTable.USER_ID));//明明是将获取到的数据赋予实例，怎么会爆红呢？（下同） -11.29
            db.close();
            return userID;
        }
        return -1;//如果获取失败，返回-1 -11.29
    }

    //将用户添加至数据库（注册） -11.30
    public void addUserAccount(UserInfo userInfo){
        SQLiteDatabase db=dbHelper.getWritableDatabase();//实例化/读取可写的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        ContentValues values=new ContentValues();//执行添加的操作 -11.30
        values.put(UserTable.USER_ID,userInfo.getUserID());
        values.put(UserTable.USER_NAME,userInfo.getUserName());
        values.put(UserTable.USER_HEAD,userInfo.getUserHead());
        values.put(UserTable.USER_SEX,userInfo.getUserSex());
        values.put(UserTable.USER_ACCOUNT,userInfo.getUserAccount());
        values.put(UserTable.USER_LOCATION,userInfo.getUserLocation());
        values.put(UserTable.USER_SIGN,userInfo.getUserSign());
        db.replace(UserTable.TABLE_NAME,null,values);
        db.close();
    }

    //删除用户账号（不是退出登录，是从后台将用户账号完全消除） -11.30
    public void LoginOut(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();//实例化/读取可写的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        String Sql="delete from user_info";//SQL删除语句 -11.30
        db.execSQL(Sql);//执行删除命令 -11.30
        db.close();
    }
}
