package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.UrbanBaseInfoFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanBaseInfoOtherFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = UrbanBaseInfoFragmentModule.class)
public interface UrbanBaseInfoOtherFragmentComponent {
    void inject(UrbanBaseInfoOtherFragment myFindFragment);
}
