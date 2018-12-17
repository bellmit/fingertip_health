package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ClientPersonSignApply;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.OnLineSignApplicationContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jerry on 2017/8/22.
 */

public class OnlineApplicationPresenter implements OnLineSignApplicationContract.presenter {
    private final OnLineSignApplicationContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;

    @Inject
    public OnlineApplicationPresenter(OnLineSignApplicationContract.View view,
                                      @Named("default") SharedPreferences sharedPreferences,
                                      GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getDoctorTeamInfo(RequestBody body) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getDoctorTeamInfo(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<DoctorTeamInfo>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<DoctorTeamInfo>>>() {
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
                        view.onLoadListFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onLoadListSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }
                    }

                });

    }

    public void getPersonSignApply(RequestBody body) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getPersonSignApply(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<ClientPersonSignApply>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<ClientPersonSignApply>>>() {
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
                        view.onLoadListFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<ClientPersonSignApply>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onLineApplicationListSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }
                    }

                });

    }

    public void savePersonSignApply(RequestBody body){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.savePersonSignApply(body)
                .compose(abstractActivity.<HttpResultBaseBean<LoginResultBean2>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<LoginResultBean2>>() {
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
                        view.onLoginFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<LoginResultBean2> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoginSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoginFailure(msg);
                        }
                    }

                });

    }



}
