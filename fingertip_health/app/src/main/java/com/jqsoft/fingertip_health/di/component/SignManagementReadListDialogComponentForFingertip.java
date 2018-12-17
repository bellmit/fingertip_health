package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignManagementReadListDialogModuleForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;
import com.jqsoft.fingertip_health.dialog.fingertip.SignManagementReadDialog;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = SignManagementReadListDialogModuleForFingertip.class)
public interface SignManagementReadListDialogComponentForFingertip {
    void inject(SignManagementReadDialog dialog);
//    void inject(LoginActivity loginActivity);
}
