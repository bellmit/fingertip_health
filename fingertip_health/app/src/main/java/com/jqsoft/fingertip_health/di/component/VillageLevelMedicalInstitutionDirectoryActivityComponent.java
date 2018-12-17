package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.VillageLevelMedicalInstitutionDirectoryActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.VillageLevelMedicalInstitutionDirectoryActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = VillageLevelMedicalInstitutionDirectoryActivityModule.class)
public interface VillageLevelMedicalInstitutionDirectoryActivityComponent {
    void inject(VillageLevelMedicalInstitutionDirectoryActivity directoryActivity);
}
