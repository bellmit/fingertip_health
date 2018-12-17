package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceAccountTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceAccountTrendStatisticsFragmentModule.class)
public interface SubsistenceAccountTrendStatisticsFragmentComponent {
    void inject(SubsistenceAccountTrendStatisticsFragment fragment);
}
