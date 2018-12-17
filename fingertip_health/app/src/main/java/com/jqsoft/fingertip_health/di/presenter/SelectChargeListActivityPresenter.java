package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.SeletChargeListActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SelectChargeListActivityPresenter implements SeletChargeListActivityContract.presenter {

    private final SeletChargeListActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SelectChargeListActivityPresenter(SeletChargeListActivityContract.View view,
                                             @Named("default") SharedPreferences sharedPreferences,
                                             GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }
    public void getlist(Map<String, String> map, final boolean isLoadMore){
        final Context context = (Context)view;
        Util.showGifProgressDialog(context);
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
//            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        RequestBody body = Util.getBodyFromMap(map);
        GCAService.getSelectchargeListdata(body)
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

                        view.onSeletChargeListFailure(e.getMessage(),isLoadMore);
                    }

                    @Override
//                    public void onNext(HttpResultBaseBeanForFingertip<LoginResultForFingertip> bean) {
                    public void onNext(HttpResultBaseBeanForFingertip<String> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessfulForFingertip(bean);
                        boolean isTimeout = Util.isResponseTokenTimeoutForFingertip(bean);
                        if (isSuccessful){
                            if (isLoadMore){
                                view.onSeletChargeMoreListSuccess(bean);
                            } else {
                                view.onSeletChargeListSuccess(bean);
                            }
                        } else if (isTimeout){
                            Util.gotoLoginActivity(context, true, true);
                        }
                        else {
                            String msg = Util.getMessageFromHttpResponseForFingertip(bean);
                            view.onSeletChargeListFailure(msg, isLoadMore);
                        }


                    }

                });

    }



}