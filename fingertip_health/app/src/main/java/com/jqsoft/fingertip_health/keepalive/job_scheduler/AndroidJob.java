package com.jqsoft.fingertip_health.keepalive.job_scheduler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.service.LongTermService;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017-09-13.
 */

public class AndroidJob extends Job {

    public static final String TAG = "signed_resident_client_android_job_tag";

    public AndroidJob() {
        super();
    }

    private Handler handler = new Handler(DaggerApplication.getInstance().getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case Constants.HANDLER_ANDROID_JOB_WHAT_ID:
                    startLongTermService();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        // run your job here
        LogUtil.i("AndroidJob onRunJob");
        Message message = Message.obtain();
        message.what = Constants.HANDLER_ANDROID_JOB_WHAT_ID;
        handler.sendMessage(message);
        return Result.SUCCESS;
    }

    private void startLongTermService(){

        Context context = getContext();
        Intent intent = new Intent(context, LongTermService.class);
        context.startService(intent);
    }



    public static void scheduleJob() {
        new JobRequest.Builder(AndroidJob.TAG)
//                .setExecutionWindow(3_000L, 8_000L)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
//                .setPeriodic(TimeUnit.SECONDS.toMillis(60), TimeUnit.SECONDS.toMillis(30))
                .setUpdateCurrent(true)
                .setPersisted(true)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .build()
                .schedule();
    }

}
