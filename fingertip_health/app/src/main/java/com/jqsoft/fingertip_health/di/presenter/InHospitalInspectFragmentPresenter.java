package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.InHospitalInspectFragmentContract;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.BaseSubscriber;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class InHospitalInspectFragmentPresenter implements InHospitalInspectFragmentContract.presenter {

    private final InHospitalInspectFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public InHospitalInspectFragmentPresenter(InHospitalInspectFragmentContract.View view,
                                              @Named("default") SharedPreferences sharedPreferences,
                                              GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map, final boolean isLoadMore) {

        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
     //   final Context context = (Context)view;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }

        RequestBody body = Util.getBodyFromMap(map);


        GCAService.getInHospitalInspectData(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<InHospitalInspectBeanList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResultBaseBean<List<InHospitalInspectBeanList>>>(context) {
                    @Override
                    public void onStart() {
                        super.onStart();
//                        Util.showGifProgressDialog(((Context)view));
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
//                        boolean isMainThread = Util.isMainThread();
//                        LogUtil.i("onCompleted runs in main thread:"+isMainThread);
//                        final Activity activity = (Activity) view;
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Util.hideGifProgressDialog(activity);
//
//                            }
//                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.onLoadListFailure(e.getMessage(), isLoadMore);

                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful){
                            if (isLoadMore){
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg, isLoadMore);
                        }

                    }


                });

    }



}