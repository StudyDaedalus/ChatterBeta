package com.blessingsoftware.chatterbeta.ChatterBeta;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;

import com.blessingsoftware.chatterbeta.Logs.Logs;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

public class ChatterBetaApplication extends Application {
    public void onCreate() {
        super.onCreate();
        initChatterBeta();
    }

    public void initChatterBeta(){
        EMOptions options=new EMOptions();

        options.setAcceptInvitationAlways(false);//这样加好友就需要验证了
        int pid=android.os.Process.myPid();
        String processAppName=getAppName(pid);
        if(processAppName==null||!processAppName.equalsIgnoreCase(this.getPackageName())){
            Logs.e("APP","enter the service process.");
            return;
        }
        EMClient.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);
        Model.getInstance().init(this);
    }

    private String getAppName(int pID){
        String processName=null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()){
            ActivityManager.RunningAppProcessInfo info=(ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if(info.pid==pID){
                    processName=info.processName;
                    return processName;
                }
            }catch (Exception e){
                //Logs.d("Process","Error>> :"+e.toString());
            }
        }
        return processName;
    }
}
