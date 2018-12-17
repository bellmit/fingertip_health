package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignedResidentDirectoryFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.SignedResidentDirectoryFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SignedResidentDirectoryFragmentModule.class)
public interface SignedResidentDirectoryFragmentComponent {
    void inject(SignedResidentDirectoryFragment signedResidentDirectoryFragment);
}
