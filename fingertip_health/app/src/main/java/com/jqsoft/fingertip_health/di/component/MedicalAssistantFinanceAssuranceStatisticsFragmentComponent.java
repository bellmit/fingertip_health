package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.MedicalAssistantFinanceAssuranceStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceIncreaseRatioFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = MedicalAssistantFinanceAssuranceStatisticsFragmentModule.class)
public interface MedicalAssistantFinanceAssuranceStatisticsFragmentComponent {
    void inject(MedicalAssistantFinanceAssuranceStatisticsFragment fragment);
    void inject(MedicalAssistantFinanceAssuranceTrendStatisticsFragment fragment);
    void inject(MedicalAssistantFinanceAssuranceIncreaseRatioFragment fragment);
}
