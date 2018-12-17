package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.InstitutionLegalPersonClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionLegalPersonClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = InstitutionLegalPersonClassificationStatisticsFragmentModule.class)
public interface InstitutionLegalPersonClassificationStatisticsFragmentComponent {
    void inject(InstitutionLegalPersonClassificationStatisticsFragment fragment);
}
