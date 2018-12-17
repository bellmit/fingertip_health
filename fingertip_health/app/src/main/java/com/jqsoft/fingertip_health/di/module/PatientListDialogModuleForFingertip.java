package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PatientListDialogContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class PatientListDialogModuleForFingertip {

    private PatientListDialogContractForFingertip.View view;

    public PatientListDialogModuleForFingertip(PatientListDialogContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PatientListDialogContractForFingertip.View providerView(){
        return view;
    }

}