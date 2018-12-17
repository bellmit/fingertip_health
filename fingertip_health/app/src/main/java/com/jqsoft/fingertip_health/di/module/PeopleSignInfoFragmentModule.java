package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PeopleSignInfoFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class PeopleSignInfoFragmentModule {

    private PeopleSignInfoFragmentContract.View view;

    public PeopleSignInfoFragmentModule(PeopleSignInfoFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public PeopleSignInfoFragmentContract.View providerView(){
        return view;
    }

}
