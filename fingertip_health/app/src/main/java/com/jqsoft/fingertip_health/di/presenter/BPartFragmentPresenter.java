package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PersonInfoList;
import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.BpartFragmentContract;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
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

public class BPartFragmentPresenter implements BpartFragmentContract.presenter {

    private final BpartFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public BPartFragmentPresenter(BpartFragmentContract.View view,
                                  @Named("default") SharedPreferences sharedPreferences,
                                  GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getPersonInfo(RequestBody body) {
        Fragment fragment = (Fragment) view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context) fragment.getActivity();
        if (!Util.isNetworkConnected(context)) {
            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getPersonInfo(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<PersonInfoList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<PersonInfoList>>>() {
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
                        view.onLoginFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<PersonInfoList>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onLoadListSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoginFailure(msg);
                        }
                    }

                });
    }

    public void getPersonnlInfo(RequestBody body) {
        Fragment fragment = (Fragment) view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context) fragment.getActivity();
        if (!Util.isNetworkConnected(context)) {
            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getPersonnelInfo(body)
                .compose(abstractFragment.<HttpResultBaseBean<PersonnelInfoData>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<PersonnelInfoData>>() {
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
                        view.onLoginFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<PersonnelInfoData> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onPersonnelInfo(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);

                        }
                    }

                });
    }
}