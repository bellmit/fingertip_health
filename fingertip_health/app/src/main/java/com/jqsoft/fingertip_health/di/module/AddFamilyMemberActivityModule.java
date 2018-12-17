package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AddFamilyMemberActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class AddFamilyMemberActivityModule {

    private AddFamilyMemberActivityContract.View view;

    public AddFamilyMemberActivityModule(AddFamilyMemberActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AddFamilyMemberActivityContract.View providerView(){
        return view;
    }

}