package com.jqsoft.fingertip_health.util;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * Created by YLL on 2018-3-27.
 */

public class killSelfService extends Service {
    /**关闭应用后多久重新启动*/
    private static  long stopDelayed=1000;
    private Handler handler;
    private String PackageName;
    public killSelfService() {
        handler=new Handler();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        stopDelayed=intent.getLongExtra("Delayed",1000);
        PackageName=intent.getStringExtra("PackageName");
      /*  handler.postDelayed(()->{
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
            startActivity(LaunchIntent);
            killSelfService.this.stopSelf();
        },stopDelayed);*/

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(getApplication().getPackageName());
                LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(LaunchIntent);
               /* Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                startActivity(LaunchIntent);*/
                killSelfService.this.stopSelf();
            }
        },stopDelayed);

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

