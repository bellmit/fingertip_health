package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AdviceDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceHadDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AdviceDetailActivityModule.class)
public interface AdviceDetailHadActivityComponent {
    void inject(AdviceHadDetailActivity adviceDetailActivity);
}
