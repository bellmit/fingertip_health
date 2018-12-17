package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HouseHoldFamilyModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldFaimilyFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HouseHoldFamilyModule.class)
public interface HouseHoldFamilyComponent {
    void inject(HouseHoldFaimilyFragment houseHoldFaimilyFragment);
}
