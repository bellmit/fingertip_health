package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ServicePackDetailContract;
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

public class ServicePackDetailPresenter implements ServicePackDetailContract.presenter {

    private final ServicePackDetailContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public ServicePackDetailPresenter(ServicePackDetailContract.View view,
                                      @Named("default") SharedPreferences sharedPreferences,
                                      GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(RequestBody body){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onServicePackDetailFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.servicepackdetail(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<ServicePackDetailBeanList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<ServicePackDetailBeanList>>>() {
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
                        view.onServicePackDetailFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<ServicePackDetailBeanList>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onServicePackDetailSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onServicePackDetailFailure(msg);
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
//                .subscribe(new Subscriber<HttpResultBaseBean<LoginResultBean2>>() {
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
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<LoginResultBean2> bean) {
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
