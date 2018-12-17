package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.SaveFamilyDoctorSignContract;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class SaveFamilyDoctorSignFragmentPresenter implements SaveFamilyDoctorSignContract.presenter {

    private final SaveFamilyDoctorSignContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SaveFamilyDoctorSignFragmentPresenter(SaveFamilyDoctorSignContract.View view,
                                                 @Named("default") SharedPreferences sharedPreferences,
                                                 GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getSingData(RequestBody body){
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
        if (!Util.isNetworkConnected(context)){
            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.submitSignInfo(body)
                .compose(abstractFragment.<HttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
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
                        Log.i("wwq","sdadeafwer");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.hideGifProgressDialog(context);
                        view.onLoginFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<HttpResultEmptyBean> bean) {
                        Log.i("wwq","sdadeafwer");
                        Util.decodeBase64Bean(bean);
                      //  view.onLoginSuccess(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoginSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoginFailure(msg);
                        }
                    }

                });


    }



}