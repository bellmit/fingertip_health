package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionCharacterNameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.InstitutionCharacterClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
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

public class InstitutionCharacterClassificationStatisticsFragmentPresenter implements InstitutionCharacterClassificationStatisticsFragmentContract.presenter {

    private final InstitutionCharacterClassificationStatisticsFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public InstitutionCharacterClassificationStatisticsFragmentPresenter(InstitutionCharacterClassificationStatisticsFragmentContract.View view,
                                                                         @Named("default") SharedPreferences sharedPreferences,
                                                                         GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map) {
        final AbstractFragment abstractFragment = (AbstractFragment) view;
        final AbstractActivity abstractActivity = (AbstractActivity) abstractFragment.getActivity();
        final Context context = abstractActivity;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getInstitutionCharacterClassificationStatisticsList(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>>>bindToLifecycle())
//                .compose(abstractActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>>>() {
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
                    public void onNext(GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        boolean isEmpty = Util.isGCAResponseEmpty(bean);
                        if (isEmpty){
                            isSuccessful=true;
                            if (bean!=null){
                                bean.setData(new ArrayList<InstitutionCharacterNameValueBean>());
                            }
                        }
                        if (isSuccessful) {
                            view.onLoadListSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg);
                        }

                    }


                });

    }


}