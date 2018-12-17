package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultBean;
import com.jqsoft.fingertip_health.di.contract.MySignInfoActivityContract;
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
 * Created by Administrator on 2017/5/21.
 */

public class MySignInfoActivityPresenter implements MySignInfoActivityContract.presenter {

    private final MySignInfoActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public MySignInfoActivityPresenter(MySignInfoActivityContract.View view,
                                       @Named("default") SharedPreferences sharedPreferences,
                                       GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getSignInfoOverview(RequestBody body){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadSignOverviewDataFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        GCAService.getSignInfoOverviewData(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<SignInfoOverviewResultBean>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<SignInfoOverviewResultBean>>>() {
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
                        view.onLoadSignOverviewDataFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<SignInfoOverviewResultBean>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful){
                            view.onLoadSignOverviewDataSuccess(bean);
                        }  else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadSignOverviewDataFailure(msg);
                        }

                    }



                });

    }
}