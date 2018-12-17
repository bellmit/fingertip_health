package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignServiceAssessFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.SignServiceAssessFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SignServiceAssessFragmentModule.class)
public interface SignServiceAssessFragmentComponent {
    void inject(SignServiceAssessFragment signServiceAssessFragment);
}
