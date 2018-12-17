package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceStandardRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceStandardRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceStandardRankingStatisticsFragmentModule.class)
public interface SubsistenceStandardRankingStatisticsFragmentComponent {
    void inject(SubsistenceStandardRankingStatisticsFragment fragment);
}
