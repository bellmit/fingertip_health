package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.UseCollectionFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = UseCollectionFragmentModule.class)
public interface UseCollectionFragmentComponent {
    void inject(SimpleCardFragment simpleCardFragment);
}
