package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MedicalAssistantMoneyConstitutionStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantMoneyConstitutionStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = MedicalAssistantMoneyConstitutionStatisticsFragmentModule.class)
public interface MedicalAssistantMoneyConstitutionStatisticsFragmentComponent {
    void inject(MedicalAssistantMoneyConstitutionStatisticsFragment fragment);
}
