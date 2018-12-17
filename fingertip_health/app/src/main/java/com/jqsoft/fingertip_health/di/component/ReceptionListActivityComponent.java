package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ReceptionListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ReceptionListActivityModule.class)
public interface ReceptionListActivityComponent {
    void inject(ReceptionListActivity ReceptionListActivity);
}
