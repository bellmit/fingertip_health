package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.OnLineChatintFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class OnlineChatingFragmentModule {

    private OnLineChatintFragmentContract.View view;

    public OnlineChatingFragmentModule(OnLineChatintFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public OnLineChatintFragmentContract.View providerView(){
        return view;
    }

}