package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MySignInfoActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.MySignInfoActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MySignInfoActivityModule.class)
public interface MySignInfoActivityComponent {
    void inject(MySignInfoActivity mySignInfoActivity);
}
