package com.jqsoft.fingertip_health.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;

import com.jqsoft.fingertip_health.utils.LogUtil;

/**
 * Created by Administrator on 2017-09-12.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//API需要在21及以上
public class MyJobService extends JobService {

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            LogUtil.i("MyJobService handler");
//            Toast.makeText(MyJobService.this, "MyJobService", Toast.LENGTH_SHORT).show();
            JobParameters param = (JobParameters) msg.obj;
            jobFinished(param, true);

            startLongTermService();

//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            return true;
        }
    });

    private void startLongTermService(){
        Intent intent = new Intent(this, LongTermService.class);
        startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Message m = Message.obtain();
        m.obj = params;
        handler.sendMessage(m);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        handler.removeCallbacksAndMessages(null);
        return false;
    }
}
