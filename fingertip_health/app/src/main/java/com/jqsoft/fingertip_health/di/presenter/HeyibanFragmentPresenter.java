//package com.jqsoft.grassroots_civil_administration_platform.di.presenter;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.request.FriendListRequestBean;
//import com.jqsoft.fingertip_health.bean.response.FriendResultWrapperBean;
//import com.jqsoft.fingertip_health.di.contract.HeyibanFragmentContract;
//import com.jqsoft.fingertip_health.di.ui.fragment.HeyibanFragment;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di_http.http.fingertip_health.GCAService;
//import com.jqsoft.fingertip_health.util.Util;
//
//import java.util.Map;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Created by Administrator on 2017/5/21.
// */
//
//public class HeyibanFragmentPresenter implements HeyibanFragmentContract.presenter {
//
//    private final HeyibanFragmentContract.View view;
//    private final SharedPreferences sharedPreferences;
//    private final GCAService GCAService;
//
//
//    @Inject
//    public HeyibanFragmentPresenter(HeyibanFragmentContract.View view,
//                                    @Named("default") SharedPreferences sharedPreferences,
//                                    GCAService GCAService) {
//        this.view = view;
//        this.sharedPreferences = sharedPreferences;
//        this.GCAService = GCAService;
//    }
//
//    public void main(Map<String, String> map, final boolean isLoadMore) {
//        HeyibanFragment fragment = (HeyibanFragment)view;
//        AbstractFragment abstractFragment = fragment;
//        final Context context = fragment.getActivity();
//        if (!Util.isNetworkConnected(context)){
//            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
//            return;
//        }
//
//        GCAService.getHeyibanData()
//                .compose(abstractFragment.<HttpResultBaseBean<FriendResultWrapperBean>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<FriendResultWrapperBean>>() {
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
//                        view.onLoadListFailure(e.getMessage(), isLoadMore);
////                        if (isLoadMore){
////                            view.onLoadMoreListSuccess(null);
////                        } else {
////                            view.onLoadListSuccess(null);
////                        }
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<FriendResultWrapperBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
//                        if (isSuccessful){
//                            if (isLoadMore){
//                                view.onLoadMoreListSuccess(bean);
//                            } else {
//                                view.onLoadListSuccess(bean);
//                            }
//                        } else if (isResponseTokenTimeout) {
//                            Util.gotoLoginActivity(context, true, true);
//                        }  else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoadListFailure(msg, isLoadMore);
//                        }
//
//                    }
//
//
//                });
//
//    }
//    public void main(FriendListRequestBean bean, final boolean isLoadMore) {
//        HeyibanFragment fragment = (HeyibanFragment)view;
//        AbstractFragment abstractFragment = fragment;
//        final Context context = fragment.getActivity();
//        if (!Util.isNetworkConnected(context)){
//            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
//            return;
//        }
//
//        GCAService.getHeyibanData(bean.getV(), bean.getTimestamp(), bean.getToken(), bean.getAppkey(), bean.getSig())
//                .compose(abstractFragment.<HttpResultBaseBean<FriendResultWrapperBean>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<FriendResultWrapperBean>>() {
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
//                        view.onLoadListFailure(e.getMessage(), isLoadMore);
////                        if (isLoadMore){
////                            view.onLoadMoreListSuccess(null);
////                        } else {
////                            view.onLoadListSuccess(null);
////                        }
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<FriendResultWrapperBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
//                        if (isSuccessful){
//                            if (isLoadMore){
//                                view.onLoadMoreListSuccess(bean);
//                            } else {
//                                view.onLoadListSuccess(bean);
//                            }
//                        } else if (isResponseTokenTimeout) {
//                            Util.gotoLoginActivity(context, true, true);
//                        }  else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoadListFailure(msg, isLoadMore);
//                        }
//
//                    }
//
//
//                });
//
//    }
//}