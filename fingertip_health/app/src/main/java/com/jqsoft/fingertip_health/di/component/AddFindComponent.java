package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddFindModule;
import com.jqsoft.fingertip_health.di.ui.activity.AddFindActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AddFindModule.class)
public interface AddFindComponent {
    void inject(AddFindActivity addFindActivity);
//    void inject(LoginActivity loginActivity);
}
