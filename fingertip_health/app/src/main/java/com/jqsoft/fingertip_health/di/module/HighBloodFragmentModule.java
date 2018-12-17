package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodFragmentContract;
import com.jqsoft.fingertip_health.di.contract.PeopleSignInfoFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class HighBloodFragmentModule {

    private HighBloodFragmentContract.View view;

    public HighBloodFragmentModule(HighBloodFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public HighBloodFragmentContract.View providerView(){
        return view;
    }

}
