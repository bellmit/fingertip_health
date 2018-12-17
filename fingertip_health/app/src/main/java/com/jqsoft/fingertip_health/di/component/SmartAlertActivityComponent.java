package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SmartAlertActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SmartAlertActivity;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SmartAlertActivityModule.class)
public interface SmartAlertActivityComponent {
    void inject(SmartAlertActivity smartAlertActivity);
}
