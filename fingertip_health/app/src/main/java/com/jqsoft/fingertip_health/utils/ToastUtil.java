package com.jqsoft.fingertip_health.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ToastUtil {
    public static final void show(final Context context, final String msg) {
        if (context==null){
            return;
        }
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity)context;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                    }
                });
            } else {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
