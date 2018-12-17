package com.jqsoft.fingertip_health.di.component;


import com.jqsoft.fingertip_health.di.module.PendExecuActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.PendExecuActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = PendExecuActivityModule.class)
public interface PendExecuComponent {
    void inject(PendExecuActivity pendexecuActivity);
}
