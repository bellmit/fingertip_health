package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.IgGuideActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.IgGuideActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = IgGuideActivityModule.class)
public interface IgGuideActivityComponent {
    void inject(IgGuideActivity IgGuideActivity);
}
