package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DmFragmentContract;
import com.jqsoft.fingertip_health.di.contract.HighBloodFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class DMFragmentModule {

    private DmFragmentContract.View view;

    public DMFragmentModule(DmFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public DmFragmentContract.View providerView(){
        return view;
    }

}
