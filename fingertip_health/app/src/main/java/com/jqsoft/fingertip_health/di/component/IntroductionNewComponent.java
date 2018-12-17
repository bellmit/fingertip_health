package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SRCLoginModule;
import com.jqsoft.fingertip_health.di.ui.activity.IntroductionNewActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = SRCLoginModule.class)
public interface IntroductionNewComponent {
    void inject(IntroductionNewActivity loginActivity);
//    void inject(LoginActivity loginActivity);
}
