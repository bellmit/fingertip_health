package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.AdviceDetailBean;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AdviceDetailActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class AdviceDetailActivityPresenter implements AdviceDetailActivityContract.presenter {

    private final AdviceDetailActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public AdviceDetailActivityPresenter(AdviceDetailActivityContract.View view,
                                         @Named("default") SharedPreferences sharedPreferences,
                                         GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getAdviceDetail(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<AdviceDetailBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<AdviceDetailBean>>() {
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
                        view.onLoadListFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadListSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);

                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }
    public void turnConsult(Map<String, String> map) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getAdviceDetail(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<AdviceDetailBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<AdviceDetailBean>>() {
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
                        view.onLoadListFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onturnConsultSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);

                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }
    public void Adviceremove(Map<String, String> map) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .removeAdvice(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<SaveWanttoAdvicebean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<SaveWanttoAdvicebean>>() {
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
                        view.onLoadListFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<SaveWanttoAdvicebean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onAdviceremoveSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);

                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }
    //type =0 回复 ,=1 追问
    public void replyConsult(Map<String, String> map, final String type) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getAdviceDetail(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<AdviceDetailBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<AdviceDetailBean>>() {
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
                        if (type.equals("1")){
                            view.onconsultAgainFailure(e.getMessage());
                        }else {
                            view.onreplyConsultFailure(e.getMessage());
                        }
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){
                            if (type.equals("1")){
                                view.onconsultAgainSuccess(bean);
                            }else {
                                view.onreplyConsultSuccess(bean);
                            }

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);

                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }

}