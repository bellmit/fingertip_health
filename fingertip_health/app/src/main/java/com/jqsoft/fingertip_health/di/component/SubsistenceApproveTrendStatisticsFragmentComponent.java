package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceApproveTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceApproveTrendStatisticsFragmentModule.class)
public interface SubsistenceApproveTrendStatisticsFragmentComponent {
    void inject(SubsistenceApproveTrendStatisticsFragment subsistenceApproveRankingStatisticsFragment);
}
