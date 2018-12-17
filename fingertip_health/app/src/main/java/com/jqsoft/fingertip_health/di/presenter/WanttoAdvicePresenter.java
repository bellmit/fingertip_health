package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.WanttoAdviceContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class WanttoAdvicePresenter implements WanttoAdviceContract.presenter {

    private final WanttoAdviceContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public WanttoAdvicePresenter(WanttoAdviceContract.View view,
                                            @Named("default") SharedPreferences sharedPreferences,
                                            GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map, final boolean isLoadMore) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        //  RequestBody body = Util.getBodyFromMap(map);

        GCAService.saveAdvice(map)
                .compose(abstractActivity.<HttpResultBaseBean<SaveWanttoAdvicebean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<SaveWanttoAdvicebean>>() {
                    //                .subscribe(new BaseSubscriber<HttpResultBaseBean<MyFindResultBean>>(context) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Util.showGifProgressDialog(((Context)view));
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
                    public void onNext(HttpResultBaseBean<SaveWanttoAdvicebean> bean) {
                        //  Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful) {
                            if (isLoadMore) {
                               // view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }


}