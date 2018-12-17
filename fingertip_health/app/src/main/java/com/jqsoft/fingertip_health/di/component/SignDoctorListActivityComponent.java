package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.module.SignDoctorListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SignDoctorListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SignDoctorListActivityModule.class)
public interface SignDoctorListActivityComponent {
    void inject(SignDoctorListActivity signDoctorListActivity);
}
