package com.jqsoft.fingertip_health.di.ui.fragment.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/21.
 */

//public abstract class AbstractFragment extends Fragment {
public abstract class AbstractFragment extends RxFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterEventReceiver();
        DaggerApplication.getRefWatcher().watch(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initInject();
        initData();
        initView();
        loadData();

//        registerEventReceiver();
    }

//    public void registerEventReceiver(){
//        JMessageClient.registerEventReceiver(this);
//    }
//
//    public void unregisterEventReceiver(){
//        JMessageClient.unRegisterEventReceiver(this);
//    }

    public int getDeliveredInt(String key){
        Bundle bundle = getArguments();
        if (bundle!=null){
            int result = bundle.getInt(key, Integer.MAX_VALUE);
            return result;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public String getDeliveredString(String key){
        Bundle bundle = getArguments();
        if (bundle!=null){
            String result = bundle.getString(key, Constants.EMPTY_STRING);
            if (result==null){
                result = Constants.EMPTY_STRING;
            }
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public Serializable getDeliveredSerializable(String key){
        Bundle bundle = getArguments();
        if (bundle!=null){
            Serializable serializable = bundle.getSerializable(key);
            return serializable;
        } else {
            return null;
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void loadData();
    protected void initInject(){}
}
