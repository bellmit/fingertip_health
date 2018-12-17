package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HistoryDetailContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class HistoryDetailModule {

    private HistoryDetailContract.View view;

    public HistoryDetailModule(HistoryDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HistoryDetailContract.View providerView() {
        return view;
    }

}
