package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.NewDoctorSignContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class NewDoctorSignActivityModuleForFingertip {

    private NewDoctorSignContractForFingertip.View view;

    public NewDoctorSignActivityModuleForFingertip(NewDoctorSignContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public NewDoctorSignContractForFingertip.View providerView(){
        return view;
    }

}