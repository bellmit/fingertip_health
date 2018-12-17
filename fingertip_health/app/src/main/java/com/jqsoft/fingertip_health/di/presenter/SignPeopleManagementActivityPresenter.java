package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.SignPeopleManagementActivityContractForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

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

public class SignPeopleManagementActivityPresenter implements SignPeopleManagementActivityContractForFingertip.presenter {

    private final SignPeopleManagementActivityContractForFingertip.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SignPeopleManagementActivityPresenter(SignPeopleManagementActivityContractForFingertip.View view,
                                                 @Named("default") SharedPreferences sharedPreferences,
                                                 GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void queryList(Map<String, String> map){
        final Context context = (Context)view;
        Util.showGifProgressDialog(context);
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, false);
            return;
        }
        RequestBody body = Util.getBodyFromMap(map);
        GCAService.queryList(body)
                .compose(abstractActivity.<HttpResultBaseBeanForFingertip<String>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBeanForFingertip<String>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Util.showGifProgressDialog(context);

                    }

                    @Override
                    public void onCompleted() {
                            Util.hideGifProgressDialog(context);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.hideGifProgressDialog(context);
                        view.onLoadListFailure(e.getMessage(), false);
                    }

                    @Override
//                    public void onNext(HttpResultBaseBeanForFingertip<LoginResultForFingertip> bean) {
                    public void onNext(HttpResultBaseBeanForFingertip<String> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessfulForFingertip(bean);
                        boolean isTimeout = Util.isResponseTokenTimeoutForFingertip(bean);
                        if (isSuccessful){
                            view.onLoadListSuccess(bean);
                        } else if (isTimeout){
                            RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY);
                            Util.gotoActivity(context, LoginActivityNew.class);
                        }
                        else {
                            String msg = Util.getMessageFromHttpResponseForFingertip(bean);
                            view.onLoadListFailure(msg, false);
                        }
                    }

                });

    }



}