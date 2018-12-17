package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.TempDisasterAssistancePercentageStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistancePercentageStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = TempDisasterAssistancePercentageStatisticsFragmentModule.class)
public interface TempDisasterAssistancePercentageStatisticsFragmentComponent {
    void inject(TempDisasterAssistancePercentageStatisticsFragment fragment);
}
