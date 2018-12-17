package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ReceptionDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ReceptionDetailActivityModule.class)
public interface ReceptionDetailActivityComponent {
    void inject(ReceptionDetailActivity ReceptionDetailActivity);
}
