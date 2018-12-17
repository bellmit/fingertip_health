package com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.utils.LogUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class GCARetrofit {
    public static String BASE_URL = Constants.EMPTY_STRING;
    public static String BASE_URL_NEW = Constants.EMPTY_STRING;
    public static Retrofit retrofit;
//    private static Retrofit.Builder builder;

//    private CompositeSubscription mBaseUrlSubscription;

    public GCARetrofit(OkHttpClient okHttpClient) {
//        builder = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(FastJsonConverterFactory.create());
//        retrofit = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        LogUtil.i("GCARetrofit create new retrofit, BASE_URL:"+BASE_URL);

//        if (mBaseUrlSubscription == null) {
//            registerBaseUrlSubscription();
//        }

    }


    public Retrofit getRetrofit() {
//        BASE_URL= Util.trimString(BASE_URL);
//        BASE_URL_NEW = Util.trimString(BASE_URL_NEW);
//        LogUtil.i("GCARetrofit BASE_URL/BASE_URL_NEW:"+ BASE_URL + Constants.SPACE_STRING + BASE_URL_NEW);
//        if (!BASE_URL_NEW.equals(BASE_URL)){
//            BASE_URL=BASE_URL_NEW;
//            builder.baseUrl(BASE_URL);
//            retrofit=builder.build();
//        }
        return retrofit;
    }

//    private void registerBaseUrlSubscription() {
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_RETROFIT_BASE_URL_CHANGE, String.class).subscribe(new Action1<String>() {
//            @Override
//            public void call(String newUrl) {
//            }
//        });
//        if (mBaseUrlSubscription == null) {
//            mBaseUrlSubscription = new CompositeSubscription();
//        }
//        mBaseUrlSubscription.add(subscription);
//    }
//
//    private void unregisterSubscription(){
//        if (mBaseUrlSubscription != null && mBaseUrlSubscription.hasSubscriptions()) {
//            mBaseUrlSubscription.unsubscribe();
//        }
//
//    }

}
