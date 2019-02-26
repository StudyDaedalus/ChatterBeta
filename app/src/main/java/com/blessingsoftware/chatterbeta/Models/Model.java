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
    private static Model model=new Model();
    private DBManager dbManager;
    private ExecutorService executorService=Executors.newCachedThreadPool();//全局线程 -12.3

    public static Model getInstance(){
        return model;
    }

    public void init(Context c){
        context=c;
        EventListener eventListener= new EventListener(context);
    }

    public ExecutorService getGlobalTheadPool(){
        return executorService;
    }

    public DBManager getDbManager(){
        dbManager=new DBManager(context,"ChatterBeta");
        return dbManager;
    }
}
