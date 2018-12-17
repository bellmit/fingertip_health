package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.QuestionActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;



@Module
public class QuestionActivityModule {

    private QuestionActivityContract.View view;

    public QuestionActivityModule(QuestionActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public QuestionActivityContract.View providerView(){
        return view;
    }

}
