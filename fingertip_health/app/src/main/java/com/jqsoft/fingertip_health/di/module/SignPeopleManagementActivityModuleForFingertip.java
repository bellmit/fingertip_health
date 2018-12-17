package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignPeopleManagementActivityContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignPeopleManagementActivityModuleForFingertip {

    private SignPeopleManagementActivityContractForFingertip.View view;

    public SignPeopleManagementActivityModuleForFingertip(SignPeopleManagementActivityContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignPeopleManagementActivityContractForFingertip.View providerView(){
        return view;
    }

}
