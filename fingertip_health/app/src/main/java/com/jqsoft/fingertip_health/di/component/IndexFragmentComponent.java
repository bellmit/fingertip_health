package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.IndexFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.IndexFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = IndexFragmentModule.class)
public interface IndexFragmentComponent {
    void inject(IndexFragment indexFragment);
}
