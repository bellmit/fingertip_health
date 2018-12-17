package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.LoginContractForFingertip;
import com.jqsoft.fingertip_health.di.contract.OpoutpatientContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class OutpatientModuleForFingertip {

    private OpoutpatientContractForFingertip.View view;

    public OutpatientModuleForFingertip(OpoutpatientContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public OpoutpatientContractForFingertip.View providerView(){
        return view;
    }

}