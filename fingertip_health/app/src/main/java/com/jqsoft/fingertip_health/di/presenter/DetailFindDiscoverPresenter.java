package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AddFindContract;
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

public class DetailFindDiscoverPresenter implements AddFindContract.presenter {

    private final AddFindContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public DetailFindDiscoverPresenter(AddFindContract.View view,
                                       @Named("default") SharedPreferences sharedPreferences,
                                       GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void maindetail(Map<String, String> map){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onDetailFindFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.DetailFindSRC(map)
                .compose(abstractActivity.<HttpResultBaseBean<DetailFindBeans>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<DetailFindBeans>>() {
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
                        view.onDetailFindFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<DetailFindBeans> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onDetailFindSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onDetailFindFailure(msg);
                        }
                    }

                });

    }

//    public void main(Map<String, String> map){
//        final Context context = (Context)view;
//        if (!Util.isNetworkConnected(context)){
//            return;
//        }
//        GCAService.login(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<SRCLoginResultBean>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<SRCLoginResultBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginFailure(msg);
//                        }
//                    }
//
//                });
//
//    }

//    public void main(LoginRequestBean loginRequestBean){
//        if (loginRequestBean!=null){
//            main(loginRequestBean.getV(), loginRequestBean.getTimestamp(), loginRequestBean.getToken(),
//                    loginRequestBean.getAppkey(), loginRequestBean.getSig(), loginRequestBean.getUsername(),
//                    loginRequestBean.getPassword());
//        }
//    }
//
//    public void main(String v,  String timestamp,
//                      String token,  String appkey,
//                      String sig,  String username,
//                      String password){
//        final Context context = (Context)view;
//        if (!Util.isNetworkConnected(context)){
//            return;
//        }
//        GCAService.login(v, timestamp, token, appkey, sig, username, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<LoginResultBean>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<LoginResultBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
}
