package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HouseHoldFileModule;
import com.jqsoft.fingertip_health.di.ui.fragment.FileFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HouseHoldFileModule.class)
public interface HouseHoldFileComponent {
    void inject(FileFragment fileFragment);
}
