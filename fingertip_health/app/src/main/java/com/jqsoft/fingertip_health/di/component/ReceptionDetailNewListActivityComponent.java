package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ReceptionDetailNewListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionDetailNewListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = ReceptionDetailNewListActivityModule.class)
public interface ReceptionDetailNewListActivityComponent {
    void inject(ReceptionDetailNewListActivity receptionDetailNewListActivity);
}
