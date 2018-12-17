package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MedicalInstitutionDetailActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MedicalInstitutionDetailActivityModule {

    private MedicalInstitutionDetailActivityContract.View view;

    public MedicalInstitutionDetailActivityModule(MedicalInstitutionDetailActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MedicalInstitutionDetailActivityContract.View providerView(){
        return view;
    }

}
