package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveHealthClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveHealthClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceArchiveHealthClassificationStatisticsFragmentModule.class)
public interface SubsistenceArchiveHealthClassificationStatisticsFragmentComponent {
    void inject(SubsistenceArchiveHealthClassificationStatisticsFragment fragment);
}
