package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.InstitutionRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = InstitutionRankingStatisticsFragmentModule.class)
public interface InstitutionRankingStatisticsFragmentComponent {
    void inject(InstitutionRankingStatisticsFragment fragment);
}
