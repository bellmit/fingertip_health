package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.TempDisasterAssistantStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = TempDisasterAssistantStatisticsFragmentModule.class)
public interface TempDisasterAssistantStatisticsFragmentComponent {
    void inject(TempDisasterAssistantStatisticsFragment fragment);
    void inject(TempDisasterAssistantTrendStatisticsFragment fragment);
}
