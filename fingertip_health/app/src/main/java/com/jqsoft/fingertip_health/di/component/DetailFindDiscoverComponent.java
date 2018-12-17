package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddFindModule;
import com.jqsoft.fingertip_health.di.ui.activity.DetailFindDiscoverActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AddFindModule.class)
public interface DetailFindDiscoverComponent {
    void inject(DetailFindDiscoverActivity addFindActivity);
//    void inject(LoginActivity loginActivity);
}
