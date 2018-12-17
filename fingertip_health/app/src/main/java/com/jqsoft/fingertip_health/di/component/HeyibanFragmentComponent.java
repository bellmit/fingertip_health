package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HeyibanFragmentModule;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = HeyibanFragmentModule.class)
public interface HeyibanFragmentComponent {
    //void inject(HeyibanFragment heyibanFragment);
}
