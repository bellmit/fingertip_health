package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ProgressDetailbean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HandleProgressDetailActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.BaseSubscriber;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class HandleProgressDetailPresenter implements HandleProgressDetailActivityContract.presenter {

    private final HandleProgressDetailActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService signedResidentClientService;


    @Inject
    public HandleProgressDetailPresenter(HandleProgressDetailActivityContract.View view,
                                         @Named("default") SharedPreferences sharedPreferences,
                                         GCAService signedResidentClientService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.signedResidentClientService = signedResidentClientService;
    }

    public void getSocialDetails(Map<String, String> map, final boolean isLoadMore) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }
//        Map<String, String> ultimateMap = new HashMap<>();
//        String jsonString = JSON.toJSONString(map);
//        ultimateMap.put(Constants.REQUEST_KEY, jsonString);

        signedResidentClientService.getprogressDetailData(map)
                .compose(abstractActivity.<GCAHttpResultBaseBean<ProgressDetailbean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GCAHttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new BaseSubscriber<GCAHttpResultBaseBean<ProgressDetailbean>>(context) {
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
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<ProgressDetailbean> bean) {
                        //  Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isSuccessfulempty = Util.isGCAResponseEmpty(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful || isSuccessfulempty) {
                            if (isLoadMore) {
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg, isLoadMore);
                        }

                    }


                });

    }


}