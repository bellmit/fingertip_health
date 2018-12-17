package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.FamilyEconomyCheckProjectCheckStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckProjectCheckStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * 家庭经济状况核对-核对项目类统计
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = FamilyEconomyCheckProjectCheckStatisticsFragmentModule.class)
public interface FamilyEconomyCheckProjectCheckStatisticsFragmentComponent {
    void inject(FamilyEconomyCheckProjectCheckStatisticsFragment fragment);
}
