package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.QuestionDetailActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class QuestionDetailActivityModule {

    private QuestionDetailActivityContract.View view;

    public QuestionDetailActivityModule(QuestionDetailActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public QuestionDetailActivityContract.View providerView(){
        return view;
    }

}
