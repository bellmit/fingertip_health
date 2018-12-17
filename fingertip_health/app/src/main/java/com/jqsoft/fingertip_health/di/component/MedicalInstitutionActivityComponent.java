package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MedicalInstitutionActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.MedicalInstitutionActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MedicalInstitutionActivityModule.class)
public interface MedicalInstitutionActivityComponent {
    void inject(MedicalInstitutionActivity medicalInstitutionActivity);
}
