package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddFamilyMemberActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AddFamilyMemberActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = AddFamilyMemberActivityModule.class)
public interface AddFamilyMemberActivityComponent {
    void inject(AddFamilyMemberActivity addFamilyMemberActivity);
}
