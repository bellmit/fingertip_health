package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.PolicyActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
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

public class PolicyActivityPresenter implements PolicyActivityContract.presenter {

    private final PolicyActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public PolicyActivityPresenter(PolicyActivityContract.View view,
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
                .getPolicyList(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<List<PolicyBean>>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                 .subscribe(new Subscriber<GCAHttpResultBaseBean<List<PolicyBean>>>() {
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
                    public void onNext(GCAHttpResultBaseBean<List<PolicyBean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        boolean isEmpty = Util.isGCAResponseEmpty(bean);
                        if (isEmpty){
                            isSuccessful=true;
                            if (bean!=null){
                                bean.setData(new ArrayList<PolicyBean>());
                            }
                        }
                        if (isSuccessful){
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



}