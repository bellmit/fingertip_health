package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MyMessageDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.MyMessageDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MyMessageDetailActivityModule.class)
public interface MyMessageDetailActivityComponent {
    void inject(MyMessageDetailActivity myMessageDetailActivity);
}
