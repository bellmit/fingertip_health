package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceVarianceTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceVarianceTrendStatisticsFragmentModule.class)
public interface SubsistenceVarianceTrendStatisticsFragmentComponent {
    void inject(SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceRankingStatisticsFragment);
}
