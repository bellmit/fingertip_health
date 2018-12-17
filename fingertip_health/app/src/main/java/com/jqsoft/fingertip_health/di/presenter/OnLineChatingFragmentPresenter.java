package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PersonDoctorMessageInfo;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.OnLineChatintFragmentContract;
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

public class OnLineChatingFragmentPresenter implements OnLineChatintFragmentContract.presenter {

    private final OnLineChatintFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public OnLineChatingFragmentPresenter(OnLineChatintFragmentContract.View view,
                                          @Named("default") SharedPreferences sharedPreferences,
                                          GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }


    public void getPersonDoctorMessageInfo(RequestBody body) {
        Fragment fragment = (Fragment) view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context) fragment.getActivity();
        if (!Util.isNetworkConnected(context)) {
            view.onLoadRemindDataFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getPersonDoctorMessage(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<PersonDoctorMessageInfo>>>bindToLifecycle())
//                .delay(8000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<PersonDoctorMessageInfo>>>() {
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
                        view.onLoadRemindDataFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<PersonDoctorMessageInfo>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful) {
                            view.onLoadPolicyDataSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadRemindDataFailure(msg);
                        }

                    }


                });

    }


}