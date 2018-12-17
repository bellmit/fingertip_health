package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.FamilyEconomyCheckTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * 家庭经济状况核对-业务受理情况趋势分析，核对报告趋势分析，核对报告复议趋势分析
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = FamilyEconomyCheckTrendStatisticsFragmentModule.class)
public interface FamilyEconomyCheckTrendStatisticsFragmentComponent {
    void inject(FamilyEconomyCheckTrendStatisticsFragment fragment);
}
