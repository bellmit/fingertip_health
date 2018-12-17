package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DetailPeopleInfoContract;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class DetailPeopleInfoActivityModule {

    private DetailPeopleInfoContract.View view;

    public DetailPeopleInfoActivityModule(DetailPeopleInfoContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public DetailPeopleInfoContract.View providerView(){
        return view;
    }

}
