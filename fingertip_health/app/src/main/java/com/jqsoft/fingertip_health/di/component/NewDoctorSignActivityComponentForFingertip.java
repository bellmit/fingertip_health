package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.NewDoctorSignActivityModuleForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.NewDoctorSignActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = NewDoctorSignActivityModuleForFingertip.class)
public interface NewDoctorSignActivityComponentForFingertip {
    void inject(NewDoctorSignActivity activity);
//    void inject(LoginActivity loginActivity);
}
