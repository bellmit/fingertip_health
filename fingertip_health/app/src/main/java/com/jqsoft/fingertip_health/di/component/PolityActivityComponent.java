package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PolityActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.PoliticsActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = PolityActivityModule.class)
public interface PolityActivityComponent {
    void inject(PoliticsActivity polityActivity);
}
