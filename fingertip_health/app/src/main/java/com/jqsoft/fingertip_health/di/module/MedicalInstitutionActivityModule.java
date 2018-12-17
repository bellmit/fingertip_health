package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MedicalInstitutionActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MedicalInstitutionActivityModule {

    private MedicalInstitutionActivityContract.View view;

    public MedicalInstitutionActivityModule(MedicalInstitutionActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MedicalInstitutionActivityContract.View providerView(){
        return view;
    }

}
