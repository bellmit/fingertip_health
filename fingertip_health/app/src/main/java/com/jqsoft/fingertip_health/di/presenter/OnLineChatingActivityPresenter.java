package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PersonMessage;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.OnLineChatingActivityContract;
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

public class OnLineChatingActivityPresenter implements OnLineChatingActivityContract.presenter {

    private final OnLineChatingActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public OnLineChatingActivityPresenter(OnLineChatingActivityContract.View view,
                                          @Named("default") SharedPreferences sharedPreferences,
                                          GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }


    public void getPersonMessage(RequestBody body, final boolean isLoadMore) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onMessageDataFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }
        GCAService.getPersonMessage(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<PersonMessage>>>bindToLifecycle())
//                .delay(8000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<PersonMessage>>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                      //  Util.showGifProgressDialog(context);

                    }

                    @Override
                    public void onCompleted() {
                        Util.hideGifProgressDialog(context);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.hideGifProgressDialog(context);
                        view.onMessageDataFailure(e.getMessage(), isLoadMore);
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<PersonMessage>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful) {
                            if (isLoadMore) {
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onMessageDataSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onMessageDataFailure(msg,isLoadMore);
                        }

                    }


                });

    }

    public void saveOnlineConsultation(RequestBody body) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onsaveOnlineFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        GCAService.saveClientOnlineConsultation(body)
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
                        view.onsaveOnlineFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<LoginResultBean2> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onsaveOnlineSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);

                        }
                    }

                });

    }

    public void updateOnlineChat(RequestBody body) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onsaveOnlineFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        GCAService.updateOnlineChat(body)
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
                        view.onsaveOnlineFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<LoginResultBean2> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.updateOnlineSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);

                        }
                    }

                });

    }
}