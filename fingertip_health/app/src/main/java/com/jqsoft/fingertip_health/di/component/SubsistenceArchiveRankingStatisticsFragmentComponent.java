package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceArchiveRankingStatisticsFragmentModule.class)
public interface SubsistenceArchiveRankingStatisticsFragmentComponent {
    void inject(SubsistenceArchiveRankingStatisticsFragment fragment);
}
