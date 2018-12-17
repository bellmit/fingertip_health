package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MapServiceActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = MapServiceActivityModule.class)
public interface MapServiceActivityComponent {
    void inject(MapServiceActivity activity);
}
