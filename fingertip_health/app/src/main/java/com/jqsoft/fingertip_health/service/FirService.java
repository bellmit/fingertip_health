package com.jqsoft.fingertip_health.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class FirService extends Service {

    public final static String TAG = "com.example.servicedemo.FirService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("FirService onStartCommand");
        thread.start();
        return START_STICKY;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    LogUtil.i("FirService Run: "+System.currentTimeMillis());
                    boolean b = Util.isServiceWorked(FirService.this, "com.jqsoft.fingertip_health.service.SecService");
                    if(!b) {
                        Intent service = new Intent(FirService.this, SecService.class);
                        startService(service);
                        LogUtil.i("Start SecService");
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