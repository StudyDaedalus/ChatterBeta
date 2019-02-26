package com.blessingsoftware.chatterbeta.Logs;

/*

    wriitten by CAT from/to Dec,3rd,2018 -并不确定这一部分的作用能否体现

 */

import android.util.Log;

/*

    FNINISH
    written by CAT from/to Dec,3rd,2018

 */


public class Logs {

    private static boolean ISDEBUG =true;

    public static void e(String TAG,String MSG){
        if(ISDEBUG){
            Log.e(TAG, MSG);
        }
    }

    public static void i(String TAG,String MSG){
        if(ISDEBUG){
            Log.i(TAG, MSG);
        }
    }

    public static void w(String TAG,String MSG){
        if(ISDEBUG){
            Log.w(TAG, MSG);
        }
    }

    public static void d(String TAG,String MSG){
        if(ISDEBUG){
            Log.d(TAG, MSG);
        }
    }
}
