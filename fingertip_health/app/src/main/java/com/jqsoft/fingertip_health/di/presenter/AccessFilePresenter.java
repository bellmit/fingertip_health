package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.AccessFileContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
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

public class AccessFilePresenter implements AccessFileContract.presenter {

    private final AccessFileContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public AccessFilePresenter(AccessFileContract.View view,
                               @Named("default") SharedPreferences sharedPreferences,
                               GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(RequestBody body, final boolean isLoadMore) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
         /* Fragment fragment = (Fragment) view;
        final Context context = (Context) fragment.getActivity();*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadAccessFileFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        GCAService.getAccessFile(body)
                .compose(abstractActivity.<HttpResultBaseBean<PersonnelInfoData>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
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
                        view.onLoadAccessFileFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<PersonnelInfoData> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoadAccessFileSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadAccessFileFailure(msg);
                        }
                    }

                });

    }

    public void mainupdate(RequestBody body) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
         /* Fragment fragment = (Fragment) view;
        final Context context = (Context) fragment.getActivity();*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadAccessFileFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }

        GCAService.getupdatepeople(body)
                .compose(abstractActivity.<HttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
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
                        view.onLoadUpdatePeopleFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<HttpResultEmptyBean> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoadUpdatePeopleSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadUpdatePeopleFailure(msg);
                        }
                    }

                });

    }


}