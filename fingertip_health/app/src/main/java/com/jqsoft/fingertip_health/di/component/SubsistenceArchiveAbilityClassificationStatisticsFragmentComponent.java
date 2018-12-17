package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveAbilityClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveAbilityClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceArchiveAbilityClassificationStatisticsFragmentModule.class)
public interface SubsistenceArchiveAbilityClassificationStatisticsFragmentComponent {
    void inject(SubsistenceArchiveAbilityClassificationStatisticsFragment fragment);
}
