package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.FamilyDetailsActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.FamilyDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = FamilyDetailsActivityModule.class)
public interface FamilyDetailActivityComponent {
    void inject(FamilyDetailActivity familyDetailActivity);
}
