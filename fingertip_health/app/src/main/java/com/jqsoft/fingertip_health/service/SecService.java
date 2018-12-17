package com.jqsoft.fingertip_health.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SecService extends Service {

    public final static String TAG = "com.example.servicedemo.SecService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("SecService onStartCommand");
        thread.start();
        return START_REDELIVER_INTENT;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    LogUtil.i("SecService Run: " + System.currentTimeMillis());
                    boolean b = Util.isServiceWorked(SecService.this, "com.jqsoft.fingertip_health.service.FirService");
                    if(!b) {
                        Intent service = new Intent(SecService.this, FirService.class);
                        startService(service);
                    }
                }
            };
            timer.schedule(task, 0, 1000);
        }
    });

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}