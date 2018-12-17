package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.InstitutionServerClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionServerClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = InstitutionServerClassificationStatisticsFragmentModule.class)
public interface InstitutionServerClassificationStatisticsFragmentComponent {
    void inject(InstitutionServerClassificationStatisticsFragment fragment);
}
