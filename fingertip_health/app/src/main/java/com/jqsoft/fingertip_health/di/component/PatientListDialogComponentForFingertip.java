package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PatientListDialogModuleForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;
import com.jqsoft.fingertip_health.dialog.fingertip.PatientListDialog;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = PatientListDialogModuleForFingertip.class)
public interface PatientListDialogComponentForFingertip {
    void inject(PatientListDialog dialog);
//    void inject(LoginActivity loginActivity);
}
