package com.jqsoft.fingertip_health.listener;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.utils.LogUtil;

public abstract class NoDoubleItemClickListener implements BaseQuickAdapter.OnItemClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 3000;
    private long lastClickTime = 0;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        long currentTime = System.currentTimeMillis();
//        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            LogUtil.i("last/current click time:"+lastClickTime+"/"+currentTime);
            lastClickTime = currentTime;
            onNoDoubleItemClick(adapter, view, position);
        }
    }

    public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}