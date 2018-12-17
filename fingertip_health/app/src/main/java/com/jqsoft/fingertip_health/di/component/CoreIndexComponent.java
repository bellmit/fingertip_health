package com.jqsoft.fingertip_health.di.component;


import com.jqsoft.fingertip_health.di.module.CoreIndexActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ExecuProjectActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = CoreIndexActivityModule.class)
public interface CoreIndexComponent {
    void inject(ExecuProjectActivity coreIndexActivity);
}
