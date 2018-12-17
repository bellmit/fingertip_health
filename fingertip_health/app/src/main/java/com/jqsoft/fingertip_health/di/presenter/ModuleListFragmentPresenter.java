package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ModuleListFragmentContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.BaseSubscriber;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ModuleListFragmentPresenter implements ModuleListFragmentContract.presenter {
    private final ModuleListFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;

    @Inject
    public ModuleListFragmentPresenter(ModuleListFragmentContract.View view,
                                       @Named("default") SharedPreferences sharedPreferences,
                                       GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void getPeopleSignInfoList(Map<String, String> map, final boolean isLoadMore) {

        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
        //   final Context context = (Context)view;
        if (!Util.isNetworkConnected(context)){
            view.onLoadPeopleSignInfoListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }

        RequestBody body = Util.getBodyFromMap(map);


        GCAService.getInHospitalInspectData(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<InHospitalInspectBeanList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResultBaseBean<List<InHospitalInspectBeanList>>>(context) {
                    @Override
                    public void onStart() {
                        super.onStart();
//                        Util.showGifProgressDialog(((Context)view));
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
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
                        super.onError(e);
                        view.onLoadPeopleSignInfoListFailure(e.getMessage(), isLoadMore);

                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful){
                            if (isLoadMore){
                                view.onLoadPeopleSignInfoMoreListSuccess(bean);
                            } else {
                                view.onLoadPeopleSignInfoListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadPeopleSignInfoListFailure(msg, isLoadMore);
                        }

                    }


                });

    }


    public void reservationSign(RequestBody body, final boolean isLoadMore) {
        Fragment fragment = (Fragment)view;
        final Context context = (Context)fragment.getActivity();
        AbstractActivity abstractActivity = (AbstractActivity) context;
         /* Fragment fragment = (Fragment) view;
        final Context context = (Context) fragment.getActivity();*/
        if (!Util.isNetworkConnected(context)){
            view.onLoadSignUserInfoFailure(Constants.HINT_LOADING_DATA_FAILURE, false);
            return;
        }

        GCAService.getPeopleBaseInfoData(body)
                .compose(abstractActivity.<HttpResultBaseBean<PeopleBaseInfoBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<HttpResultBaseBean<PeopleBaseInfoBean>>() {
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
                        view.onLoadSignUserInfoFailure(e.getMessage(),false);
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<PeopleBaseInfoBean> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoadSignUserInfoSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadSignUserInfoFailure(msg,false);
                        }
                    }

                });

    }


    public void main(RequestBody body) {
        Fragment fragment = (Fragment)view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context)fragment.getActivity();
        if (!Util.isNetworkConnected(context)) {
            view.onLoadDoctorListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        GCAService.getDoctorTeamInfo(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<DoctorTeamInfo>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<DoctorTeamInfo>>>() {
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
                        view.onLoadDoctorListFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful) {
                            view.onLoadDoctorListSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadDoctorListFailure(msg);
                        }
                    }

                });

    }


}
