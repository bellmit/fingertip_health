package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddFindModule;
import com.jqsoft.fingertip_health.di.ui.activity.DetailFindDaibanActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AddFindModule.class)
public interface DetailFindStatusComponent {
    void inject(DetailFindDaibanActivity addFindActivity);
//    void inject(LoginActivity loginActivity);
}
