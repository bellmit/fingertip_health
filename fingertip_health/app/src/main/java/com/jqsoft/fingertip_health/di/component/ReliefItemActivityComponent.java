package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ReliefItemActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReliefItemActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ReliefItemActivityModule.class)
public interface ReliefItemActivityComponent {
    void inject(ReliefItemActivity reliefItemActivity);
}
