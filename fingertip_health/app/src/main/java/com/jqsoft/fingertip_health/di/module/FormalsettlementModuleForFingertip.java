package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.FormalsettlementContractForFingertip;
import com.jqsoft.fingertip_health.di.contract.OpoutpatientContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class FormalsettlementModuleForFingertip {

    private FormalsettlementContractForFingertip.View view;

    public FormalsettlementModuleForFingertip(FormalsettlementContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public FormalsettlementContractForFingertip.View providerView(){
        return view;
    }

}