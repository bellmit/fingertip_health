package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HouseHoldServeyBaseModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldBaseInfoFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HouseHoldServeyBaseModule.class)
public interface HouseHoldServeyBaseComponent {
    void inject(HouseHoldBaseInfoFragment houseHoldBaseInfoFragment);
}
