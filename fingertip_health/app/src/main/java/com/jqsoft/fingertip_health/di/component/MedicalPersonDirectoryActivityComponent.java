package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MedicalPersonDirectoryActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.MedicalPersonDirectoryActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = MedicalPersonDirectoryActivityModule.class)
public interface MedicalPersonDirectoryActivityComponent {
    void inject(MedicalPersonDirectoryActivity directoryActivity);
}
