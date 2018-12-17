package com.jqsoft.fingertip_health.keepalive.job_scheduler;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Administrator on 2017-09-13.
 */

public class AndroidJobCreator implements JobCreator {
    public AndroidJobCreator() {
        super();
    }

    @Override
    public Job create(String tag) {
        switch (tag) {
            case AndroidJob.TAG:
                return new AndroidJob();
            default:
                return null;
        }
    }

}
