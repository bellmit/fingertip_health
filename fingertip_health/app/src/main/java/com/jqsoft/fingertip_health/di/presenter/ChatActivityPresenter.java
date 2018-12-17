//package com.jqsoft.grassroots_civil_administration_platform.di.presenter;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
//import com.jqsoft.fingertip_health.bean.response.ChatListWrapperBean;
//import com.jqsoft.fingertip_health.di.contract.ChatActivityContract;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
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
//public class ChatActivityPresenter implements ChatActivityContract.presenter {
//
//    private final ChatActivityContract.View view;
//    private final SharedPreferences sharedPreferences;
//    private final GCAService signedDoctorClientService;
//
//
//    @Inject
//    public ChatActivityPresenter(ChatActivityContract.View view,
//                                 @Named("default") SharedPreferences sharedPreferences,
//                                 GCAService signedDoctorClientService) {
//        this.view = view;
//        this.sharedPreferences = sharedPreferences;
//        this.signedDoctorClientService = signedDoctorClientService;
//    }
//
//    public void main(Map<String, String> map, final boolean isLoadMore) {
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
//            return;
//        }
//
//        signedDoctorClientService.getChatData(1)
//                .compose(abstractActivity.<HttpResultBaseBean<ChatListWrapperBean>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<ChatListWrapperBean>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.onLoadListFailure(e.getMessage(), isLoadMore);
//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<ChatListWrapperBean> bean) {
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
//
//    public void sendMessage(Map<String, String> map){
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onSendMessageFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        signedDoctorClientService.sendMessage(map)
//                .compose(abstractActivity.<HttpResultBaseBean<HttpResultEmptyBean>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<HttpResultEmptyBean>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.onSendMessageFailure(e.getMessage());
//                        Util.hideGifProgressDialog(context);
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
//                        if (isSuccessful){
//                            view.onSendMessageSuccess(bean);
//                        } else if (isResponseTokenTimeout) {
//                            Util.gotoLoginActivity(context, true, true);
//                        }   else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onSendMessageFailure(msg);
//                        }
//
//                    }
//                });
//    }
//}