package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.LoginModuleForFingertip;
import com.jqsoft.fingertip_health.di.module.OutpatientModuleForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = OutpatientModuleForFingertip.class)
public interface OutpatientComponentForFingertip {
    void inject(OutpatientChargesActivity outpatientChargesActivity);
}
