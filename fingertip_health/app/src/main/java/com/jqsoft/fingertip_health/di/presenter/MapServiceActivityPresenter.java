package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HeatmapBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultMapBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonStreetOrAboveLocationBean;
import com.jqsoft.fingertip_health.di.contract.MapServiceActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
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

public class MapServiceActivityPresenter implements MapServiceActivityContract.presenter {

    private final MapServiceActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public MapServiceActivityPresenter(MapServiceActivityContract.View view,
                                       @Named("default") SharedPreferences sharedPreferences,
                                       GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map, final boolean isLoadMore) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getAmbientPersonList(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>>>() {
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
                        view.onLoadListFailure(e.getMessage(), isLoadMore);

                    }

                    @Override
                    public void onNext(GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        boolean isEmpty = Util.isGCAResponseEmpty(bean);
                        if (isEmpty){
                            isSuccessful=true;
                            if (bean!=null){
                                bean.setData(new ArrayList<PersonStreetOrAboveLocationBean>());
                            }
                        }
                        if (isSuccessful) {
                            if (isLoadMore) {
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg, isLoadMore);
                        }

                    }


                });

    }
//    public void main(Map<String, String> map, final boolean isLoadMore) {
//        final Context context = (Context) view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)) {
//            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
//            return;
//        }
////        RequestBody body = Util.getBodyFromMap(map);
//        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);
//
//        GCAService
//                .getAmbientPersonList(ultimateMap)
//                .compose(abstractActivity.<GCAHttpResultBaseBean<List<PersonLocationBean>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GCAHttpResultBaseBean<List<PersonLocationBean>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoadListFailure(e.getMessage(), isLoadMore);
//
//                    }
//
//                    @Override
//                    public void onNext(GCAHttpResultBaseBean<List<PersonLocationBean>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
//                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
//                        boolean isEmpty = Util.isGCAResponseEmpty(bean);
//                        if (isEmpty){
//                            isSuccessful=true;
//                            if (bean!=null){
//                                bean.setData(new ArrayList<PersonLocationBean>());
//                            }
//                        }
//                        if (isSuccessful) {
//                            if (isLoadMore) {
//                                view.onLoadMoreListSuccess(bean);
//                            } else {
//                                view.onLoadListSuccess(bean);
//                            }
//                        } else if (isResponseTokenTimeout) {
//                            Util.gotoLoginActivity(context, true, true);
//                        } else {
//                            String msg = Util.getGCAMessageFromHttpResponse(bean);
//                            view.onLoadListFailure(msg, isLoadMore);
//                        }
//
//                    }
//
//
//                });
//
//    }

    public void getHeatmapBean(Map<String, String> map) {
        final Context context = (Context) view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)) {
            view.onLoadHeatmapFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
//        RequestBody body = Util.getBodyFromMap(map);
        Map<String, String> ultimateMap = Util.getMapForHttpRequestFromMap(map);

        GCAService
                .getHeatmapBean(ultimateMap)
                .compose(abstractActivity.<GCAHttpResultBaseBean<HeatmapBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GCAHttpResultBaseBean<HeatmapBean>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Util.showGifProgressDialog(context);
                    }

                    @Override
                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.hideGifProgressDialog(context);
                        view.onLoadHeatmapFailure(e.getMessage());

                    }

                    @Override
                    public void onNext(GCAHttpResultBaseBean<HeatmapBean> bean) {
//                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isGCAResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isGCAResponseTokenTimeout(bean);
                        boolean isEmpty = Util.isGCAResponseEmpty(bean);
                        if (isEmpty){
                            isSuccessful=true;
                            if (bean!=null){
                                bean.setData(new HeatmapBean());
                            }
                        }
                        if (isSuccessful) {
                            view.onLoadHeatmapSuccess(bean);
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else {
                            String msg = Util.getGCAMessageFromHttpResponse(bean);
                            view.onLoadHeatmapFailure(msg);
                        }

                    }


                });

    }


}