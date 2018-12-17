package com.jqsoft.fingertip_health.di.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.SignManagementReadDialogContractForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class SignManagementReadListDialogPresenter implements SignManagementReadDialogContractForFingertip.presenter {

    private final SignManagementReadDialogContractForFingertip.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SignManagementReadListDialogPresenter(SignManagementReadDialogContractForFingertip.View view,
                                                 @Named("default") SharedPreferences sharedPreferences,
                                                 GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map) {
        Dialog dialog = (Dialog) view;
        final Context context = (Context) Util.scanForActivity(dialog.getContext());
        LogUtil.i("context is activity:"+(context instanceof Activity));
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListInfoFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        RequestBody body = Util.getBodyFromMap(map);
//        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .querySignManagementReadList(body)
                .compose(abstractActivity.<HttpResultBaseBeanForFingertip<String>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<HttpResultBaseBeanForFingertip<String>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Util.showGifProgressDialog(context);
                    }

                    @Override
                    public void onCompleted() {
                        Util.hideGifProgressDialog(context);
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
                        Util.hideGifProgressDialog(context);
                        view.onLoadListInfoFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(HttpResultBaseBeanForFingertip<String> bean) {
                        Util.decodeBase64Bean(bean);

                        boolean isSuccessful = Util.isResponseSuccessfulForFingertip(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeoutForFingertip(bean);
                        if (isSuccessful) {
                            view.onLoadListInfoSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY);
                            Util.gotoActivity(context, LoginActivityNew.class);
                        } else {
                            String msg = Util.getMessageFromHttpResponseForFingertip(bean);
                            view.onLoadListInfoFailure(msg);
                        }

                    }


                });

    }


}