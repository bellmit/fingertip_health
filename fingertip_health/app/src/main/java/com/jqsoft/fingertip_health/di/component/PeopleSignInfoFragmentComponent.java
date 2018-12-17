package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PeopleSignInfoFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.PeopleSignFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = PeopleSignInfoFragmentModule.class)
public interface PeopleSignInfoFragmentComponent {
    void inject(PeopleSignFragment peopleSignInfoFragment);
}
