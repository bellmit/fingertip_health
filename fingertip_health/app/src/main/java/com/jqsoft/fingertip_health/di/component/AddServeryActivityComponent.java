package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddServeryActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AddServeryActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = AddServeryActivityModule.class)
public interface AddServeryActivityComponent {
    void inject(AddServeryActivity addServeryActivity);
}
