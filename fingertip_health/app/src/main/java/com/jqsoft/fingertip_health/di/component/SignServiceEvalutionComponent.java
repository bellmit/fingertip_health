package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignServiceEvaluteActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceEvalution;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SignServiceEvaluteActivityModule.class)
public interface SignServiceEvalutionComponent {
    void inject(SignServiceEvalution signServiceEvalution);
}
