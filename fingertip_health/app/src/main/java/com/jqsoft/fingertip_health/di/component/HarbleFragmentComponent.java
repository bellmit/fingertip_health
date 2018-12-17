package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HarbelFragmentModule;
import com.jqsoft.fingertip_health.di.module.TreatFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HarbelFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.TreatProjectFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = HarbelFragmentModule.class)
public interface HarbleFragmentComponent {
    void inject(HarbelFragment harbelFragment);
}
