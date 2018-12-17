package com.jqsoft.fingertip_health.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.jqsoft.fingertip_health.receiver.LongTermReceiver;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import cn.jpush.android.api.JPushInterface;

public class LongTermService extends Service {
    private LongTermReceiver receiver;
    public LongTermService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("LongTermService onStartCommand");
        initReceiver();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
        startNewService();
    }

    private void startNewService(){
        Intent intent = new Intent(this, LongTermService.class);
        startService(intent);
        LogUtil.i("LongTermService destroyed, now restart");
    }

    private void initReceiver(){
        if (receiver==null){
            receiver=new LongTermReceiver();
            IntentFilter filter = new IntentFilter();
//            filter.addAction(Intent.ACTION_SCREEN_OFF);
//            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(JPushInterface.ACTION_NOTIFICATION_RECEIVED);
            filter.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED);
            String category =  Util.getMetaDataFromManifest(this, "JPUSH_PKGNAME");
            if (!StringUtils.isBlank(category)) {
                filter.addCategory(category);
            }
            registerReceiver(receiver, filter);
        }
//        LogUtil.i("initReceiver receiver:"+receiver);
    }

    private void unregisterBroadcastReceiver(){
        if (receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
