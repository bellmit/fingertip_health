package com.jqsoft.fingertip_health.di_http.http;

import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCARetrofit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * <b>类名称：</b> RetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:52<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class RetrofitModule {


    @Singleton
    @Provides
    public GCARetrofit providerSignedDoctorClientRetrofit(@Named("fingertip_health") OkHttpClient okHttpClient){
        return new GCARetrofit(okHttpClient);
    }
}
