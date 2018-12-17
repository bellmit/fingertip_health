package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SubsistenceApprovePovertyReasonStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApprovePovertyReasonStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = SubsistenceApprovePovertyReasonStatisticsFragmentModule.class)
public interface SubsistenceApprovePovertyReasonStatisticsFragmentComponent {
    void inject(SubsistenceApprovePovertyReasonStatisticsFragment fragment);
}
