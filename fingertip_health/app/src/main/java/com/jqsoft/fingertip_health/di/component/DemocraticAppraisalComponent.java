package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DemocraticAppraisalModule;
import com.jqsoft.fingertip_health.di.ui.fragment.DemocraticAppraisalfragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = DemocraticAppraisalModule.class)
public interface DemocraticAppraisalComponent {
    void inject(DemocraticAppraisalfragment democraticAppraisalfragment);
}
