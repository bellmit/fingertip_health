package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceAccountRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceAccountRankingStatisticsFragmentModule.class)
public interface SubsistenceAccountRankingStatisticsFragmentComponent {
    void inject(SubsistenceAccountRankingStatisticsFragment fragment);
}
