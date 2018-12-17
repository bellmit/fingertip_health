package com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform;


import com.jqsoft.fingertip_health.utils.LogUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> IPLocaltionServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年06月22日 下午4:37<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

@Module
public class GCAServiceModule {

    @Singleton
    @Provides
    public GCAService proidverSignedDoctorClientServiceModule(GCARetrofit grassrootsCivilAdministration) {
        LogUtil.i("GCARetrofit GCAServiceModule proidverSignedDoctorClientServiceModule");
        return grassrootsCivilAdministration.getRetrofit().create(GCAService.class);
    }
}
