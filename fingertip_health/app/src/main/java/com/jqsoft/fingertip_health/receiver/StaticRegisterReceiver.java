package com.jqsoft.fingertip_health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.utils.LogUtil;

/**
 * Created by Administrator on 2017-09-12.
 */

public class StaticRegisterReceiver extends BroadcastReceiver {
    public StaticRegisterReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, LoginActivityNew.class);
            pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(pushIntent);
            if (intent != null) {
                LogUtil.i("StaticRegisterReceiver received event, actioin:" + intent.getAction());
            } else {
                LogUtil.i("StaticRegisterReceiver received intent is null");
            }
        }

    }
}
