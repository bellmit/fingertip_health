package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.di.contract.VillageLevelMedicalInstitutionDirectoryActivityContract;
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

public class VillageLevelMedicalInstitutionDirectoryActivityPresenter implements VillageLevelMedicalInstitutionDirectoryActivityContract.presenter {

    private final VillageLevelMedicalInstitutionDirectoryActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public VillageLevelMedicalInstitutionDirectoryActivityPresenter(VillageLevelMedicalInstitutionDirectoryActivityContract.View view,
                                                                    @Named("default") SharedPreferences sharedPreferences,
                                                                    GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(RequestBody body, final boolean isLoadMore) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, false);
            return;
        }

        GCAService.getVillageLevelMedicalInstitutionDirectoryData(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>>>() {
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
                        view.onLoadListFailure(e.getMessage(),false);
                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        if (isSuccessful){
                            view.onLoadListSuccess(bean);
                        } else {
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg,false);
                        }
                    }

                });

    }


}