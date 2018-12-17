package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.OnlineChatingActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ChatDetailActivity;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = OnlineChatingActivityModule.class)
public interface OnlineChatActivityComponent {
    void inject(ChatDetailActivity chatDetailActivity);
}
