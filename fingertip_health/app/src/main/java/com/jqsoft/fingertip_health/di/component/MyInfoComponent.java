package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MyInfoModule;
import com.jqsoft.fingertip_health.di.ui.activity.MyInfoActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MyInfoModule.class)
public interface MyInfoComponent {
    void inject(MyInfoActivity myInfoActivity);
}
