package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceStandardAverageRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceStandardAverageRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceStandardAverageRankingStatisticsFragmentModule.class)
public interface SubsistenceStandardAverageRankingStatisticsFragmentComponent {
    void inject(SubsistenceStandardAverageRankingStatisticsFragment fragment);
}
