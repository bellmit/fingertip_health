package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PersonJiandangListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.KucunfenxiListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = PersonJiandangListActivityModule.class)
public interface KucunfenxiListActivityComponent {
    void inject(KucunfenxiListActivity personJiandangListActivity);
}
