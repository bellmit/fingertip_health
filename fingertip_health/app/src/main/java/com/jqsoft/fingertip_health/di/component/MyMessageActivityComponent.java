package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MyMessageActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.MyMessageActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MyMessageActivityModule.class)
public interface MyMessageActivityComponent {
    void inject(MyMessageActivity myMessageActivity);
}
