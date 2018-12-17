package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.SearchDetailContract;
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

public class SearchDetailPresenter implements SearchDetailContract.presenter {

    private final SearchDetailContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SearchDetailPresenter(SearchDetailContract.View view,
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
            view.onSearchFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getInHospitalInspectData(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<InHospitalInspectBeanList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<InHospitalInspectBeanList>>>() {
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
                        view.onSearchFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onSearchDetailSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onSearchFailure(msg);
                        }
                    }

                });

    }


    public void mainpost(RequestBody body){
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadPostImgFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.postimg(body)
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
                        view.onLoadPostImgFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<HttpResultEmptyBean> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onPostImgSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadPostImgFailure(msg);
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
