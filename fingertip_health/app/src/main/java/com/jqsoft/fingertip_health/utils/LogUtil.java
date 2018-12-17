package com.jqsoft.fingertip_health.utils;

import android.util.Log;

import com.jqsoft.fingertip_health.base.Version;

/**
 * Created by Administrator on 2017-05-12.
 */

public class LogUtil {
    public static final boolean DEBUG_MODE = Version.DEBUG_MODE;
    public static void i (String msg){
        if (DEBUG_MODE){
            Log.i("chenxu", msg);
        }
    }

    public static void i(String tag, String msg){
        if (DEBUG_MODE){
            Log.i(tag, msg);
        }
    }
}
