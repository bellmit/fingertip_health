package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DetailPeopleInfoActivityModule;
import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = DetailPeopleInfoActivityModule.class)
public interface DetailPeopleInfoActivityComponent {
    void inject(DetailPeopleInfoActivity detailPeopleInfoActivity);
}
