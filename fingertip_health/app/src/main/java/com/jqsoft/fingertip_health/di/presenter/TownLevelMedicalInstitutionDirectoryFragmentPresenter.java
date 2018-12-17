package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.TownLevelMedicalInstitutionBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.TownLevelMedicalInstitutionDirectoryFragmentContract;
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

public class TownLevelMedicalInstitutionDirectoryFragmentPresenter implements TownLevelMedicalInstitutionDirectoryFragmentContract.presenter {

    private final TownLevelMedicalInstitutionDirectoryFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public TownLevelMedicalInstitutionDirectoryFragmentPresenter(TownLevelMedicalInstitutionDirectoryFragmentContract.View view,
                                                                 @Named("default") SharedPreferences sharedPreferences,
                                                                 GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(RequestBody body, final boolean isLoadMore) {
        Fragment fragment = (Fragment) view;
        AbstractFragment abstractFragment = (AbstractFragment) fragment;
        final Context context = (Context) fragment.getActivity();
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, false);
            return;
        }

        GCAService.getTownLevelMedicalInstitutionDirectoryData(body)
                .compose(abstractFragment.<HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                .subscribe(new Subscriber<HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>>>() {
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
                    public void onNext(HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>> bean) {
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