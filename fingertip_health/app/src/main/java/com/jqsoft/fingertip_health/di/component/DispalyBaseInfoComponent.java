package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DispalyInfoActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AddImgVideoServeryActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = DispalyInfoActivityModule.class)
public interface DispalyBaseInfoComponent {
    void inject(AddImgVideoServeryActivity addServeryActivity);
}
