package com.jqsoft.fingertip_health.di.component;


import com.jqsoft.fingertip_health.di.module.ReserverActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReserrverServerActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ReserverActivityModule.class)
public interface ReserverComponent {
    void inject(ReserrverServerActivity coreIndexActivity);
}
