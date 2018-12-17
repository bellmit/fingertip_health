package com.jqsoft.fingertip_health.di.component;


import com.jqsoft.fingertip_health.di.module.ServicePackDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.DoctorServerDetails;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ServicePackDetailActivityModule.class)
public interface ServicePackDetailComponent {
    void inject(DoctorServerDetails servicepackdetailActivity);
}
