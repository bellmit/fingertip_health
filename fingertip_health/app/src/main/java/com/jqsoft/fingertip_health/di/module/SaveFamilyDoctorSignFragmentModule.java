package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SaveFamilyDoctorSignContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SaveFamilyDoctorSignFragmentModule {

    private SaveFamilyDoctorSignContract.View view;

    public SaveFamilyDoctorSignFragmentModule(SaveFamilyDoctorSignContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SaveFamilyDoctorSignContract.View providerView() {
        return view;
    }

}
