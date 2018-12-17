package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AboutInfoActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AboutInfoActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AboutInfoActivityModule.class)
public interface AboutInfoActivityComponent {
    void inject(AboutInfoActivity aboutActivity);
}
