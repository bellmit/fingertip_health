package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceAccountIncreaseRatioStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountIncreaseRatioStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceAccountIncreaseRatioStatisticsFragmentModule.class)
public interface SubsistenceAccountIncreaseRatioStatisticsFragmentComponent {
    void inject(SubsistenceAccountIncreaseRatioStatisticsFragment fragment);
}
