package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.QuestionDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.QuestionDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = QuestionDetailActivityModule.class)
public interface QuestionDetailActivityComponent {
    void inject(QuestionDetailActivity questionDetailActivity);
}
