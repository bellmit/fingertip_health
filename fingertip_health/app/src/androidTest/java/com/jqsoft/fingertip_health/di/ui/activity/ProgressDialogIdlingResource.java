package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.support.test.espresso.IdlingResource;

import com.jqsoft.fingertip_health.util.LoadingUtil;

/**
 * Created by Administrator on 2017-10-09.
 */

public class ProgressDialogIdlingResource implements IdlingResource {
    private Context context;
    private ResourceCallback resourceCallback;

    public ProgressDialogIdlingResource(Context context) {
        super();
        this.context=context;
    }

    @Override
    public String getName() {
        return ProgressDialogIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean isProgressDialogShowing = LoadingUtil.getInstance(context).isShowing();
        if (isProgressDialogShowing){
            resourceCallback.onTransitionToIdle();
            return false;
        }
        return true;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

}
