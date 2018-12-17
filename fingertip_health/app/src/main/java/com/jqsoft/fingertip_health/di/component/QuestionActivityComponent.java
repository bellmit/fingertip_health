package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.QuestionActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.QuestionActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;



@ActivityScope
@Subcomponent(modules = QuestionActivityModule.class)
public interface QuestionActivityComponent {
    void inject(QuestionActivity questionActivity);
}
