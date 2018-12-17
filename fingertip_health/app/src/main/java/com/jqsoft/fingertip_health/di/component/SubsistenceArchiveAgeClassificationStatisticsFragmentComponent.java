package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveAgeClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveAgeClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceArchiveAgeClassificationStatisticsFragmentModule.class)
public interface SubsistenceArchiveAgeClassificationStatisticsFragmentComponent {
    void inject(SubsistenceArchiveAgeClassificationStatisticsFragment fragment);
}
