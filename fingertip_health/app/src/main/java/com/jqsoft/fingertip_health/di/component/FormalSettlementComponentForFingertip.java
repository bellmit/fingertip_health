package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.FormalsettlementModuleForFingertip;
import com.jqsoft.fingertip_health.di.module.OutpatientModuleForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SettleAccountsActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = FormalsettlementModuleForFingertip.class)
public interface FormalSettlementComponentForFingertip {
    void inject(SettleAccountsActivity settleAccountsActivity);
}
