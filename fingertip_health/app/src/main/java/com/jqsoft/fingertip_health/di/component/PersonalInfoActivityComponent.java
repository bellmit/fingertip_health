package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PersonalInfoActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.PersonalInfoActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = PersonalInfoActivityModule.class)
public interface PersonalInfoActivityComponent {
    void inject(PersonalInfoActivity polityActivity);
}
