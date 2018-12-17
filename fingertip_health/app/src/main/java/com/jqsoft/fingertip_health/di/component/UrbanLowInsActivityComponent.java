package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.UrbanLowInsActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.UrbanLowInsuranceActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = UrbanLowInsActivityModule.class)
public interface UrbanLowInsActivityComponent {
    void inject(UrbanLowInsuranceActivity policyActivity);
}
