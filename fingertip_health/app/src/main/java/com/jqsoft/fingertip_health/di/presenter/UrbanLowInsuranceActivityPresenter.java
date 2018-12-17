package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowInsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import com.jqsoft.fingertip_health.di.contract.urbanLowInsActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class UrbanLowInsuranceActivityPresenter implements urbanLowInsActivityContract.presenter {

    private final urbanLowInsActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public UrbanLowInsuranceActivityPresenter(urbanLowInsActivityContract.View view,
                                              @Named("default") SharedPreferences sharedPreferences,
                                              GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map, final boolean isLoadMore) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowInsList(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<List<UrbanLowInsBean>>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                 .subscribe(new Subscriber<GCAHttpResultBaseBean<List<UrbanLowInsBean>>>() {
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
                        view.onLoadListFailure(e.getMessage(), isLoadMore);
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<List<UrbanLowInsBean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isSuccessempty = Util.isGCAResponseEmpty(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful || isSuccessempty){
                            if (isLoadMore){
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg, isLoadMore);
                        }

                    }


                });

    }


    public void maindelete(Map<String, String> map){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;

        if (!Util.isNetworkConnected(context)){
            view.onDeleteUrbanFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.DeleteFindSRC(map)
                .compose(abstractActivity.<HttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<HttpResultEmptyBean>>() {
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
                        view.onDeleteUrbanFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onDeleteUrbanSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onDeleteUrbanFailure(msg);
                        }
                    }

                });

    }


}