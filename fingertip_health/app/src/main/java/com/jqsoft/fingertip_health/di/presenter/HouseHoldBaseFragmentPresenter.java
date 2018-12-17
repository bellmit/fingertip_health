package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.HouseHoldBasebean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldServeyBaseContract;
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

public class HouseHoldBaseFragmentPresenter implements HouseHoldServeyBaseContract.presenter {

    private final HouseHoldServeyBaseContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public HouseHoldBaseFragmentPresenter(HouseHoldServeyBaseContract.View view,
                                          @Named("default") SharedPreferences sharedPreferences,
                                          GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getbasepresent(Map<String, String> map){
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
     //   Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService.gethouseHoldBasedata(map)
                .compose(abstractFragment.<GCAHttpResultBaseBean<List<HouseHoldBasebean>>>bindToLifecycle())
//                .delay(8000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<List<HouseHoldBasebean>>>() {
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
                        view.onLoadListFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<List<HouseHoldBasebean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){
                            view.onLoadListSuccess(bean);
                        }  else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }

                    }



                });

    }
    public void getPolicyList(Map<String, String> map){
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);
//        Map<String, String> ultimateMap = new HashMap<>();
//        String jsonString = JSON.toJSONString(map);
//        ultimateMap.put(Constants.REQUEST_KEY, jsonString);
        GCAService.gethouseHoldBasedata(ultimateMap)
                .compose(abstractFragment.<GCAHttpResultBaseBean<List<HouseHoldBasebean>>>bindToLifecycle())
//                .delay(8000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<List<HouseHoldBasebean>>>() {
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
                        view.onLoadListFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<List<HouseHoldBasebean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        if (isSuccessful){
                            view.onLoadListSuccess(bean);
                        }  else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }

                    }



                });

    }

}