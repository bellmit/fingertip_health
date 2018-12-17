package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DiabetesMellitusListActivityModule;
import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.DiabetesMellitusListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = DiabetesMellitusListActivityModule.class)
public interface DiabetesMellitusListActivityComponent {
    void inject(DiabetesMellitusListActivity diabetesMellitusListActivity);
}
