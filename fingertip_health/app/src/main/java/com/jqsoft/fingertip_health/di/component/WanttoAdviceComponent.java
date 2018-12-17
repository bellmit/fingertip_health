package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.WanttoAdviceModule;
import com.jqsoft.fingertip_health.di.ui.activity.WanttoAdvice;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = WanttoAdviceModule.class)
public interface WanttoAdviceComponent {
    void inject(WanttoAdvice wanttoAdvice);
}
