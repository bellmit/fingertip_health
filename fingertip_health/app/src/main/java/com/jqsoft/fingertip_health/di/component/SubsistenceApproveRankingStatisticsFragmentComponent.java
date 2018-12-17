package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceApproveRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceApproveRankingStatisticsFragmentModule.class)
public interface SubsistenceApproveRankingStatisticsFragmentComponent {
    void inject(SubsistenceApproveRankingStatisticsFragment subsistenceApproveRankingStatisticsFragment);
}
