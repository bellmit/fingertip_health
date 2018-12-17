package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.FamilyMemberActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class FamilyMemberActivityModule {

    private FamilyMemberActivityContract.View view;

    public FamilyMemberActivityModule(FamilyMemberActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public FamilyMemberActivityContract.View providerView(){
        return view;
    }

}
