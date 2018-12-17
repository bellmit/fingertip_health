package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignAgreementActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SignedAgreement;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SignAgreementActivityModule.class)
public interface SignAgreementComponent {
    void inject(SignedAgreement signedAgreement);
}
