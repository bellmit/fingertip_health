package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.UrbanbaseInfoSaveBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanbaseInfobianjiBean;
import com.jqsoft.fingertip_health.di.contract.UrbanBaseInfoContract;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
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

public class UrbanBaseInfoPresenter implements UrbanBaseInfoContract.presenter {

    private final UrbanBaseInfoContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public UrbanBaseInfoPresenter(UrbanBaseInfoContract.View view,
                                  @Named("default") SharedPreferences sharedPreferences,
                                  GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map){
      /*  final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;*/
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

        if (!Util.isNetworkConnected(context)){
            view.onUrbanBaseInfoSaveFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.UrbanBaseInfoSRC(map)
                .compose(abstractFragment.<HttpResultBaseBean<UrbanbaseInfoSaveBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<UrbanbaseInfoSaveBean>>() {
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
                        view.onUrbanBaseInfoSaveFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<UrbanbaseInfoSaveBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onUrbanBaseInfoSaveSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onUrbanBaseInfoSaveFailure(msg);
                        }
                    }

                });

    }



    public void mainbianji(Map<String, String> map){
      /*  final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;*/
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

        if (!Util.isNetworkConnected(context)){
            view.onUrbanBaseInfobianjiFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.UrbanBaseInfobianjiSRC(map)
                .compose(abstractFragment.<HttpResultBaseBean<UrbanbaseInfobianjiBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<UrbanbaseInfobianjiBean>>() {
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
                        view.onUrbanBaseInfobianjiFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<UrbanbaseInfobianjiBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onUrbanBaseInfobianjiSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onUrbanBaseInfobianjiFailure(msg);
                        }
                    }

                });

    }

}
