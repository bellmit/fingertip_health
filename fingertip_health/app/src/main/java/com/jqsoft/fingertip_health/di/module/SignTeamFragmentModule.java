package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignTeamContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignTeamFragmentModule {

    private SignTeamContract.View view;

    public SignTeamFragmentModule(SignTeamContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SignTeamContract.View providerView(){
        return view;
    }

}