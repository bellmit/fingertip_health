package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignServiceIncomeFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignServiceIncomeFragmentModule {

    private SignServiceIncomeFragmentContract.View view;

    public SignServiceIncomeFragmentModule(SignServiceIncomeFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SignServiceIncomeFragmentContract.View providerView(){
        return view;
    }

}
