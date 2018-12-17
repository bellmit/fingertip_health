package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.OnlineChatingFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.OnlineChatingFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = OnlineChatingFragmentModule.class)
public interface OnlineChatFragmentComponent {
    void inject(OnlineChatingFragment indexFragment);
}
