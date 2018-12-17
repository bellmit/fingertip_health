package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceArchiveTrendStatisticsFragmentModule.class)
public interface SubsistenceArchiveTrendStatisticsFragmentComponent {
    void inject(SubsistenceArchiveTrendStatisticsFragment fragment);
}
