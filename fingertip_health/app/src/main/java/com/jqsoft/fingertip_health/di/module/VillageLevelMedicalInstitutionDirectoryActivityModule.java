package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.VillageLevelMedicalInstitutionDirectoryActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class VillageLevelMedicalInstitutionDirectoryActivityModule {

    private VillageLevelMedicalInstitutionDirectoryActivityContract.View view;

    public VillageLevelMedicalInstitutionDirectoryActivityModule(VillageLevelMedicalInstitutionDirectoryActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public VillageLevelMedicalInstitutionDirectoryActivityContract.View providerView(){
        return view;
    }

}
