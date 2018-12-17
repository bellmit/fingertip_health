package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignManagementReadDialogContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignManagementReadListDialogModuleForFingertip {

    private SignManagementReadDialogContractForFingertip.View view;

    public SignManagementReadListDialogModuleForFingertip(SignManagementReadDialogContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignManagementReadDialogContractForFingertip.View providerView(){
        return view;
    }

}