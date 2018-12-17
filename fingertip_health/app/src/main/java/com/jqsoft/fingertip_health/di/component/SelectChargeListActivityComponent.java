package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.module.SelectChargeListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SelectChargesActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SelectOutChargeListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SelectChargeListActivityModule.class)
public interface SelectChargeListActivityComponent {
    void inject(SelectOutChargeListActivity selectOutChargeListActivity);
}
