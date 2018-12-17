package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.StatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckProjectCheckStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckShareIndexStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantMoneyConstitutionStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApprovePovertyReasonStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistancePercentageStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantTrendStatisticsFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 多页统计页面
 * Created by Administrator on 2017-12-29.
 */

public class StatisticsMultipageContainerActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.statistics_multipage_container_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private int firstLevelId, secondLevelId;
    private String title;

    private ArrayList<AbstractFragment> fragmentList = new ArrayList<>();
    private String[] titleArray;

    private MyPagerAdapter adapter;

    private void displayFragments() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        slidingTabLayout.setViewPager(viewPager, titleArray);
        viewPager.setOffscreenPageLimit(Constants.OFFSCREEN_PAGE_LIMIT);

    }

//    public void setFragmentList(ArrayList<AbstractFragment> fragmentList) {
//        this.fragmentList = fragmentList;
//    }
//
//    public void setTitleArray(String[] titleArray) {
//        this.titleArray = titleArray;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics_multipage_container_layout;
    }

    @Override
    protected void initData() {
        firstLevelId = getDeliveredIntByKey(Constants.STATISTICS_FIRST_LEVEL_ID_KEY);
        secondLevelId = getDeliveredIntByKey(Constants.STATISTICS_SECOND_LEVEL_ID_KEY);
        title = getDeliveredStringByKey(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY);
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        tvTitle.setText(title);

        initFragments();

        displayFragments();
    }

    private void initFragments() {
        switch (firstLevelId) {
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RESCUE: {
                //城乡低保
                if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE) {
                    titleArray = new String[]{"排名统计",
                            "趋势分析",
                            "排名统计",
                            "趋势分析",
                            "致贫原因分类统计",
                            "退保原因分类统计"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_VARIANCE_RANKING_STATISTICS);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceRankingStatisticsFragment);
                        } else if (1 == i){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_VARIANCE_TREND_STATISTICS);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceTrendStatisticsFragment);
                        } else if (2 == i){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_APPROVE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_APPROVE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (4 == i){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_POVERTY_REASON_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        } else if (5 == i){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_SUBSISTENCE);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_CANCEL);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_SUBSISTENCE_CANCEL_SUPPORT_REASON_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }
                    }

                }
                //特困人员供养
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE) {
                    titleArray = new String[]{"排名统计",
                            "趋势分析",
                            "排名统计",
                            "趋势分析",
                            "致贫原因分类统计"
//                            ,
//                            "退保原因分类统计"
                    };
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_VERY_POOR_VARIANCE_RANKING_STATISTICS);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceRankingStatisticsFragment);
                        } else if (1 == i){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_VERY_POOR_VARIANCE_TREND_STATISTICS);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceTrendStatisticsFragment);
                        } else if (2 == i){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_VERY_POOR_APPROVE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_VERY_POOR_APPROVE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (4 == i){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_VERY_POOR_POVERTY_REASON_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }
                    }

                }
                //低收入家庭
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY) {
                    titleArray = new String[]{"排名统计",
                            "趋势分析",
                            "排名统计",
                            "趋势分析",
                            "致贫原因分类统计",
                            "退保原因分类统计"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_RANKING_STATISTICS);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceRankingStatisticsFragment);
                        } else if (1 == i){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_TREND_STATISTICS);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            fragmentList.add(subsistenceVarianceTrendStatisticsFragment);
                        } else if (2 == i){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (4 == i){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_POVERTY_REASON_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        } else if (5 == i){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_RESCUE_TYPE_KEY, Constants.RESCUE_TYPE_VALUE_LOW_SALARY);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_CANCEL);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_CANCEL_SUPPORT_REASON_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }
                    }

                }
                //医疗救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE) {
                    titleArray = new String[]{"资金构成",
                            "资金补偿方式构成",
                            "资金补偿类型构成",
                            "排名统计",
                            "趋势分析",
                            "民政救助类别统计",
                    "直接医疗救助增长率",
                    "医疗救助水平分析",
                    "排名统计",
                    "趋势分析",
                    "资助参保增长率"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i) {
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_MONEY_CONSTITUTIONI_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (1 == i){
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_COMPENSATE_METHOD_CONSTITUTION_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (2 == i){
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_COMPENSATE_TYPE_CONSTITUTION_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_RANKING_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (4 == i){
                            MedicalAssistantDirectOutcomeTrendStatisticsFragment fragment = new MedicalAssistantDirectOutcomeTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_TREND_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (5 == i){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_CATEGORY_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (6 == i){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (7 == i){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_LEVEL_ANALYSIS_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (8 == i){
                            MedicalAssistantFinanceAssuranceStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_RANKING_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (9 == i){
                            MedicalAssistantFinanceAssuranceTrendStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_TREND_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (10 == i){
                            MedicalAssistantFinanceAssuranceStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_INCREASE_RATIO_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }
                    }

                }
                //临时救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE) {
                    titleArray = new String[]{"趋势分析",
                            "排名统计",
                            "次均救助金额排名统计",
                            "次均救助金额趋势分析",
                            "金额支出占比统计",
                            "救助人次占比统计"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (1 == i){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (2 == i){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_AVERAGE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_AVERAGE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (4 == i){
                            TempDisasterAssistancePercentageStatisticsFragment fragment = new TempDisasterAssistancePercentageStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_MONEY_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_MONEY_PERCENTAGE_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (5 == i){
                            TempDisasterAssistancePercentageStatisticsFragment fragment = new TempDisasterAssistancePercentageStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_NUMBER_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_NUMBER_PERCENTAGE_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }

                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }

                    }
                }
                //受灾救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE) {
                    titleArray = new String[]{"趋势分析",
                            "排名统计",
                            "次均救助金额排名统计",
                            "次均救助金额趋势分析"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (1 == i){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (2 == i){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (3 == i){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }

                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }

                    }
                }
                //家庭经济状况核对
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY) {
                    titleArray = new String[]{"排名统计",
                            "趋势分析",
                            "核对项目类统计",
                            "信息共享指标统计",
                    "排名统计",
                    "趋势分析",
                    "复议排名统计",
                    "复议趋势分析"};
                    fragmentList = new ArrayList<>();

                    for (int i = 0; i < titleArray.length; ++i) {
                        if (0 == i){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (1 == i){
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        } else if (2 == i){
                            FamilyEconomyCheckProjectCheckStatisticsFragment fragment = new FamilyEconomyCheckProjectCheckStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_PROJECT_CATEGORY_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }
                        else if (3 == i){
                            FamilyEconomyCheckShareIndexStatisticsFragment fragment = new FamilyEconomyCheckShareIndexStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_INFO_SHARE_INDEX_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }
                        else if (4 == i){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REPORT_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        } else if (5 == i){
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REPORT_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }
                        else if (6 == i){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REVIEW_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);

                        } else if (7 == i){
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REVIEW_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            fragmentList.add(fragment);
                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (i + 1));
                            fragmentList.add(scf);

                        }
                    }
                }

            }
            break;
            default:
                break;
        }
    }

    @Override
    protected void loadData() {


    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleArray[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
