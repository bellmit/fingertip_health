package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DeleteFindFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.MyFindFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = DeleteFindFragmentModule.class)
public interface DeleteFindFragmentComponent {
    void inject(MyFindFragment myFindFragment);
}
