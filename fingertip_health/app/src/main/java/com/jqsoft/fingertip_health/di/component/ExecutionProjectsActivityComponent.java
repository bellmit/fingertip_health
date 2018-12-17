package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ExecutionProjectsActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ExecutionProjectsActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ExecutionProjectsActivityModule.class)
public interface ExecutionProjectsActivityComponent {
    void inject(ExecutionProjectsActivity executionProjectsActivity);
}
