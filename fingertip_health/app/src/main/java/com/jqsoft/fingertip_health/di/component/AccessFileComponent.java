package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AccessFileModule;
import com.jqsoft.fingertip_health.di.ui.activity.AccessFileActivity;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = AccessFileModule.class)
public interface AccessFileComponent {
    void inject(AccessFileActivity accessFileActivity);
}
