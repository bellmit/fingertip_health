package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HouseHoldServeyActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HouseholdSurveysActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HouseHoldServeyActivityModule.class)
public interface HouseHoldServeyActivityComponent {
    void inject(HouseholdSurveysActivity householdSurveysActivity);
}
