package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.module.PersonJiandangListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = PersonJiandangListActivityModule.class)
public interface PersonJiandangListActivityComponent {
    void inject(PersonJiandangListActivity personJiandangListActivity);
}
