package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HistoryDetailModule;
import com.jqsoft.fingertip_health.di.ui.activity.SocialHistoryPageActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = HistoryDetailModule.class)
public interface HistoryDetailComponent {
    void inject(SocialHistoryPageActivity socialHistoryPageActivity);
}
