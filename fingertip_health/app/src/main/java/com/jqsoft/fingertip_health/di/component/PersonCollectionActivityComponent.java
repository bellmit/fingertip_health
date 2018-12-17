package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PersonCollectionActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.PersonCollectionActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = PersonCollectionActivityModule.class)
public interface PersonCollectionActivityComponent {
    void inject(PersonCollectionActivity PersonCollectionActivity);
}
