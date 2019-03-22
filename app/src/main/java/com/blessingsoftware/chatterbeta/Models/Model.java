package com.blessingsoftware.chatterbeta.Models;

/*

    written by CAT from Dec,2nd,2018

 */

import android.content.Context;
import android.view.Display;

import com.blessingsoftware.chatterbeta.Models.DB.DBManager;

import java.util.EventListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    private Context context;
    private static Model model = new Model();
    private DBManager dbManager;
    private ExecutorService executorService = Executors.newCachedThreadPool(); //创建全局线程池

    // 获取单例对象
    public static Model getInstance(){
        return model;
    }

    //初始化
    public void init(Context c){
        context = c;
        //设置全局监听
        EventListener eventListener; //= new EventListener(context);
    }

    //获取全局线程池
    public ExecutorService getGlobalTheadPool(){
        return executorService;
    }

    //获取DB管理类
    public DBManager getDbManager(){
        //待续
        dbManager = new DBManager(context,"blchat");
        return dbManager;
    }
}
