package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.UrbanLowFamilyFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFamilyBianjiFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = UrbanLowFamilyFragmentModule.class)
public interface UrbanLowFamilyBianjiFragmentComponent {
    void inject(UrbanFamilyBianjiFragment policyActivity);
}
