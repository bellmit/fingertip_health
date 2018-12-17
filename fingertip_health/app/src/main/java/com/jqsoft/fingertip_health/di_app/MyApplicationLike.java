package com.jqsoft.fingertip_health.di_app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.utils.Utils;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.util.Util;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.im.android.api.JMessageClient;

public class MyApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.MyApplicationLike";

//    public NetChangeObserver netChangeObserver;

    public MyApplicationLike(Application application, int tinkerFlags,
                             boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                             long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Utils.init(this.getApplication());//一个utils库的初始化 https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md

        initBugly();

//        initNetworkStatusListener();

        initStrictMode();

//        JPushInterface.setDebugMode(Version.JPUSH_DEBUG_MODE);
//        JPushInterface.init(this.getApplication());
//
//        JMessageClient.setDebugMode(Version.JMESSAGE_DEBUG_MODE);
//        JMessageClient.init(this.getApplication(), Version.JMESSAGE_ENABLE_ROAMING);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        NetStateReceiver.removeRegisterObserver(netChangeObserver);
//        NetStateReceiver.unRegisterNetworkStateReceiver(getApplication());
    }

    public void initStrictMode(){
        if (Version.ENABLE_STRICT_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

//    public void initNetworkStatusListener(){
//        netChangeObserver = new NetChangeObserver() {
//            @Override
//            public void onNetConnected(NetUtils.NetType type) {
//                LogUtil.i("connection to server success,net type:"+type);
//                Util.showToast(getApplication(), Constants.HINT_CONNECTION_TO_SERVER_SUCCESS);
//            }
//
//            @Override
//            public void onNetDisConnect() {
//                LogUtil.i("connection to server failure");
//                Util.showToast(getApplication(), Constants.HINT_CONNECTION_TO_SERVER_FAILURE);
//            }
//        };
//        NetStateReceiver.registerNetworkStateReceiver(getApplication());
//        NetStateReceiver.registerObserver(netChangeObserver);
//    }

    public void initBugly(){
        Context context = getApplication();
        String packageName = context.getPackageName();
        String processName = Util.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName==null||processName.equals(packageName));
//        CrashReport.initCrashreport(getapplication(), version.bugly_app_id, version.bugly_debug_mode, strategy);

        Bugly.init(getApplication(), Version.BUGLY_APP_ID, Version.BUGLY_DEBUG_MODE, strategy);
    }



    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
//        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}