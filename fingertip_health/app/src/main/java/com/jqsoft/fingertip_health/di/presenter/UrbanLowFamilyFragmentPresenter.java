package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianSaveBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.UrbanLowFamilyFragmentContract;

import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
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

public class UrbanLowFamilyFragmentPresenter implements UrbanLowFamilyFragmentContract.presenter {

    private final UrbanLowFamilyFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public UrbanLowFamilyFragmentPresenter(UrbanLowFamilyFragmentContract.View view,
                                           @Named("default") SharedPreferences sharedPreferences,
                                           GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowFamilyList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<List<UrbanLowFamilyBean>>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                 .subscribe(new Subscriber<GCAHttpResultBaseBean<List<UrbanLowFamilyBean>>>() {
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
                    public void onNext(GCAHttpResultBaseBean<List<UrbanLowFamilyBean>> bean) {
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

    public void mainfujian(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowfujianList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<List<UrbanLowFujianBean>>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<GCAHttpResultBaseBean<List<UrbanLowFujianBean>>>() {
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
                        view.onLoadFujianListFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadFujianSuccess(bean);

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }



    public void mainfujiantake(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadFujianTakeListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowfujianTakeList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<UrbanLowFujianSaveBean>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<GCAHttpResultBaseBean<UrbanLowFujianSaveBean>>() {
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
                        view.onLoadFujianTakeListFailure(e.getMessage());
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<UrbanLowFujianSaveBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadFujianTakeSuccess(bean);

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadFujianTakeListFailure(msg);
                        }

                    }


                });

    }



    public void mainfamilydelete(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadFamilydeleteListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowfamilydeleteList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<GCAHttpResultBaseBean<HttpResultEmptyBean>>() {
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
                        view.onLoadFamilydeleteListFailure(e.getMessage());

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadFamilydeleteSuccess(bean);

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadFamilydeleteListFailure(msg);
                        }

                    }


                });

    }



    public void mainfujiandelete(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadFujiandeleteListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowfujiandeleteList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<GCAHttpResultBaseBean<HttpResultEmptyBean>>() {
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
                        view.onLoadFujiandeleteListFailure(e.getMessage());

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadFujiandeleteSuccess(bean);

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadFujiandeleteListFailure(msg);
                        }

                    }


                });

    }



    public void mainfujianbianji(Map<String, String> map) {


        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();

       /* final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity)context;*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadFujianBIanjiListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getUrbanLowfujianbianjiList(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<GCAHttpResultBaseBean<HttpResultEmptyBean>>() {
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
                        view.onLoadFujianBIanjiListFailure(e.getMessage());

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){

                            view.onLoadFujianBIanjiSuccess(bean);

                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadFujianBIanjiListFailure(msg);
                        }

                    }


                });

    }


}