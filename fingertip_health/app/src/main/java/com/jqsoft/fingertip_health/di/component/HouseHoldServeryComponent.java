package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HouseHoldserveryModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldServeyFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HouseHoldserveryModule.class)
public interface HouseHoldServeryComponent {
    void inject(HouseHoldServeyFragment houseHoldServeyFragment);
}
