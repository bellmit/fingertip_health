package com.jqsoft.fingertip_health.di.presenter;

import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.di.contract.SignServiceIncomeFragmentContract;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2017/5/21.
 */

public class SignServiceIncomeFragmentPresenter implements SignServiceIncomeFragmentContract.presenter {

    private final SignServiceIncomeFragmentContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public SignServiceIncomeFragmentPresenter(SignServiceIncomeFragmentContract.View view,
                                              @Named("default") SharedPreferences sharedPreferences,
                                              GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

//    public void main(Map<String, String> map) {
//        Fragment fragment = (Fragment)view;
//AbstractFragment abstractFragment = (AbstractFragment) fragment;

//        final Context context = (Context) fragment.getActivity();
////        if (!Util.isNetworkConnected(context)){
////                        view.onLoadDataFailure(Constants.HINT_LOADING_DATA_FAILURE);
//return;
////        }
//        RequestBody body = Util.getBodyFromMap(map);
//
//        GCAService.getSignServiceIncomeData(body)
//    .compose()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
//                .subscribe(new BaseSubscriber<HttpResultBaseBean<SignServiceIncomeResultBean>>(context) {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
////                        Util.showGifProgressDialog(((Context)view));
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        super.onCompleted();
////                        boolean isMainThread = Util.isMainThread();
////                        LogUtil.i("onCompleted runs in main thread:"+isMainThread);
////                        final Activity activity = (Activity) view;
////                        activity.runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                Util.hideGifProgressDialog(activity);
////
////                            }
////                        });
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                        view.onLoadDataFailure(e.getMessage());
////                        Activity activity = (Activity) view;
////                        Util.hideGifProgressDialog(activity);
//
////                        if (isLoadMore){
////                            view.onLoadMoreListSuccess(null);
////                        } else {
////                            view.onLoadListSuccess(null);
////                        }
//
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<SignServiceIncomeResultBean> bean) {
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
//                        if (isSuccessful) {
//                            view.onLoadDataSuccess(bean);
//                        } else if (isResponseTokenTimeout) {
//                            Util.gotoLoginActivity(context, true, true);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoadDataFailure(msg);
//                        }
//
//                    }
//
//
//                });
//
//    }


}