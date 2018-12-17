package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceVarianceRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceVarianceRankingStatisticsFragmentModule.class)
public interface SubsistenceVarianceRankingStatisticsFragmentComponent {
    void inject(SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment);
}
