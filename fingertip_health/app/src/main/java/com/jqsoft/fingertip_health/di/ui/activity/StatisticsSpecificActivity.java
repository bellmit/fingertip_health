package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.StatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckProjectCheckStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckShareIndexStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.FamilyEconomyCheckTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionCharacterClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionLegalPersonClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionServerClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeIncreaseRatioStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeRescueCategoryStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantDirectOutcomeTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceIncreaseRatioFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantFinanceAssuranceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.MedicalAssistantMoneyConstitutionStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountIncreaseRatioStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceAccountTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApprovePovertyReasonStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceApproveTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveAbilityClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveAgeClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveHealthClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceArchiveTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceStandardAverageRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceStandardRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceRankingStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.SubsistenceVarianceTrendStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistancePercentageStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantStatisticsFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.TempDisasterAssistantTrendStatisticsFragment;
import com.jqsoft.fingertip_health.feature.IUpLevel;
import com.jqsoft.fingertip_health.util.Util;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-01-23.
 */

public class StatisticsSpecificActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.statistics_specific_title)
    TextView tvTitle;
    @BindView(R.id.fl_statistics_specific_title_menu)
    FrameLayout flStatisticsSpecificTitleMenu;
    @BindView(R.id.fl_main)
    FrameLayout flMain;

    private int firstLevelId, secondLevelId, thirdLevelId;
    private String title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics_specific_layout;
    }

    @Override
    protected void initData() {
        firstLevelId = getDeliveredIntByKey(Constants.STATISTICS_FIRST_LEVEL_ID_KEY);
        secondLevelId = getDeliveredIntByKey(Constants.STATISTICS_SECOND_LEVEL_ID_KEY);
        thirdLevelId = getDeliveredIntByKey(Constants.STATISTICS_THIRD_LEVEL_ID_KEY);
        title = getDeliveredStringByKey(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY);

    }

    public void showUpLevelButton(boolean show) {
        int value = show ? View.VISIBLE : View.GONE;
        flStatisticsSpecificTitleMenu.setVisibility(value);
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        tvTitle.setText(title);

        Util.setViewListener(flStatisticsSpecificTitleMenu, new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_main);
                if (fragment != null && fragment instanceof IUpLevel){
                    IUpLevel ul = (IUpLevel)fragment;
                    ul.upLevelDidClick();
                }
            }
        });

        initFragment();
    }

    private void initFragment() {
        int id = R.id.fl_main;
        switch (firstLevelId) {
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RESCUE: {
                //城乡低保
                if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE) {
//                    titleArray = new String[]{"低保变化情况排名统计",
//                            "低保变化情况趋势分析",
//                            "低保审批情况排名统计",
//                            "低保审批情况趋势分析",
//                            "致贫原因分类统计",
//                            "退保原因分类统计",
//                              "低保档案排名统计"};
//                    fragmentList = new ArrayList<>();

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_RANKING) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_VARIANCE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceRankingStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_TREND){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_VARIANCE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceTrendStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_RANKING){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_APPROVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_TREND){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_APPROVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_POVERTY_REASON){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_POVERTY_REASON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_CANCEL_REASON){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_CANCEL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_CANCEL_SUPPORT_REASON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_RANKING) {
                            SubsistenceArchiveRankingStatisticsFragment fragment = new SubsistenceArchiveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ARCHIVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_TREND) {
                            SubsistenceArchiveTrendStatisticsFragment fragment = new SubsistenceArchiveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ARCHIVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_HEALTH_CLASSIFY) {
                            SubsistenceArchiveHealthClassificationStatisticsFragment fragment = new SubsistenceArchiveHealthClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ARCHIVE_HEALTH_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_ABILITY_CLASSIFY) {
                            SubsistenceArchiveAbilityClassificationStatisticsFragment fragment = new SubsistenceArchiveAbilityClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ARCHIVE_ABILITY_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_AGE_CLASSIFY) {
                            SubsistenceArchiveAgeClassificationStatisticsFragment fragment = new SubsistenceArchiveAgeClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ARCHIVE_AGE_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_RANKING) {
                            SubsistenceAccountRankingStatisticsFragment fragment = new SubsistenceAccountRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ACCOUNT_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_TREND) {
                            SubsistenceAccountTrendStatisticsFragment fragment = new SubsistenceAccountTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ACCOUNT_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_INCREASE_RATIO) {
                            SubsistenceAccountIncreaseRatioStatisticsFragment fragment = new SubsistenceAccountIncreaseRatioStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_ACCOUNT_INCREASE_RATIO_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_RANKING) {
                            SubsistenceStandardRankingStatisticsFragment fragment = new SubsistenceStandardRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_STANDARD_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_AVERAGE_RANKING) {
                            SubsistenceStandardAverageRankingStatisticsFragment fragment = new SubsistenceStandardAverageRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_SUBSISTENCE);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.SUBSISTENCE_TYPE_VALUE_ALL);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.EMPTY_STRING);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_SUBSISTENCE_STANDARD_AVERAGE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                    else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //特困人员供养
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE) {
//                    titleArray = new String[]{"特困人员变化情况排名统计",
//                            "特困人员变化情况趋势分析",
//                            "特困人员审批情况排名统计",
//                            "特困人员审批情况趋势分析",
//                            "致贫原因分类统计"
////                            ,
////                            "退保原因分类统计"
//                    };

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_VARIANCE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceRankingStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_VARIANCE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceTrendStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_APPROVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_TREND){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_APPROVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_POVERTY_REASON){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_POVERTY_REASON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING){
                            SubsistenceArchiveRankingStatisticsFragment fragment = new SubsistenceArchiveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ARCHIVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND){
                            SubsistenceArchiveTrendStatisticsFragment fragment = new SubsistenceArchiveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ARCHIVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY){
                            SubsistenceArchiveHealthClassificationStatisticsFragment fragment = new SubsistenceArchiveHealthClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ARCHIVE_HEALTH_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY){
                            SubsistenceArchiveAbilityClassificationStatisticsFragment fragment = new SubsistenceArchiveAbilityClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ARCHIVE_ABILITY_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY){
                            SubsistenceArchiveAgeClassificationStatisticsFragment fragment = new SubsistenceArchiveAgeClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ARCHIVE_AGE_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_RANKING){
                            SubsistenceAccountRankingStatisticsFragment fragment = new SubsistenceAccountRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ACCOUNT_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_TREND){
                            SubsistenceAccountTrendStatisticsFragment fragment = new SubsistenceAccountTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ACCOUNT_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_INCREASE_RATIO){
                            SubsistenceAccountIncreaseRatioStatisticsFragment fragment = new SubsistenceAccountIncreaseRatioStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_ACCOUNT_INCREASE_RATIO_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_RANKING){
                            SubsistenceStandardRankingStatisticsFragment fragment = new SubsistenceStandardRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_STANDARD_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_AVERAGE_RANKING){
                            SubsistenceStandardAverageRankingStatisticsFragment fragment = new SubsistenceStandardAverageRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_VERY_POOR);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_VERY_POOR);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_STANDARD_AVERAGE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_RANKING){
                            InstitutionRankingStatisticsFragment fragment = new InstitutionRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_INSTITUTION_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_CHARACTER){
                            InstitutionCharacterClassificationStatisticsFragment fragment = new  InstitutionCharacterClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_INSTITUTION_CHARACTER_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_SERVER){
                            InstitutionServerClassificationStatisticsFragment fragment = new  InstitutionServerClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_INSTITUTION_SERVER_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_LEGAL_PERSON){
                            InstitutionLegalPersonClassificationStatisticsFragment fragment = new  InstitutionLegalPersonClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_VERY_POOR_INSTITUTION_LEGAL_PERSON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //低收入家庭
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY) {
//                    titleArray = new String[]{"低收入家庭变化情况排名统计",
//                            "低收入家庭变化情况趋势分析",
//                            "低收入家庭审批情况排名统计",
//                            "低收入家庭审批情况趋势分析",
//                            "致贫原因分类统计",
//                            "退保原因分类统计"};

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING) {
                            SubsistenceVarianceRankingStatisticsFragment subsistenceVarianceRankingStatisticsFragment = new SubsistenceVarianceRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceRankingStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceRankingStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND){
                            SubsistenceVarianceTrendStatisticsFragment subsistenceVarianceTrendStatisticsFragment = new SubsistenceVarianceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            subsistenceVarianceTrendStatisticsFragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, subsistenceVarianceTrendStatisticsFragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING){
                            SubsistenceApproveRankingStatisticsFragment fragment = new SubsistenceApproveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_TREND){
                            SubsistenceApproveTrendStatisticsFragment fragment = new SubsistenceApproveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_POVERTY_REASON){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_POVERTY);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_POVERTY_REASON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_CANCEL_REASON){
                            SubsistenceApprovePovertyReasonStatisticsFragment fragment = new SubsistenceApprovePovertyReasonStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.STATISTICS_POVERTY_REASON_KEY, Constants.POVERTY_REASON_CANCEL);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_CANCEL_SUPPORT_REASON_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING){
                            SubsistenceArchiveRankingStatisticsFragment fragment = new SubsistenceArchiveRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.SUB_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_RANKING_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND){
                            SubsistenceArchiveTrendStatisticsFragment fragment = new SubsistenceArchiveTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.SUB_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_TREND_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY){
                            SubsistenceArchiveHealthClassificationStatisticsFragment fragment = new SubsistenceArchiveHealthClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.SUB_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_HEALTH_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY){
                            SubsistenceArchiveAbilityClassificationStatisticsFragment fragment = new SubsistenceArchiveAbilityClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.SUB_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_ABILITY_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY){
                            SubsistenceArchiveAgeClassificationStatisticsFragment fragment = new SubsistenceArchiveAgeClassificationStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.ITEM_TYPE_VALUE_LOW_SALARY_FAMILY);
                            bundle.putString(Constants.SUB_TYPE_KEY, Constants.SUB_TYPE_ALL_VALUE);
                            bundle.putString(Constants.METHOD_NAME_KEY, Constants.METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_AGE_CLASSIFY_STATISTICS);
                            bundle.putString(Constants.IS_AREA_OWNER_KEY, Constants.IS_NOT_AREA_OWNER);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //医疗救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE) {
//                    titleArray = new String[]{"医疗救助资金构成",
//                            "医疗救助资金补偿方式构成",
//                            "医疗救助资金补偿类型构成",
//                            "直接医疗救助支出情况排名统计",
//                            "直接医疗救助支出情况趋势分析",
//                            "直接支出情况民政救助类别统计",
//                            "直接支出情况直接医疗救助增长率",
//                            "直接支出情况医疗救助水平分析",
//                            "资助参保支出情况排名统计",
//                            "资助参保支出情况趋势分析",
//                            "资助参保支出情况资助参保增长率"};

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_CONSTITUTION) {
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_MONEY_CONSTITUTIONI_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_METHOD){
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_COMPENSATE_METHOD_CONSTITUTION_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_TYPE){
                            MedicalAssistantMoneyConstitutionStatisticsFragment fragment=new MedicalAssistantMoneyConstitutionStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_COMPENSATE_TYPE_CONSTITUTION_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_RANKING){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_RANKING_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_TREND){
                            MedicalAssistantDirectOutcomeTrendStatisticsFragment fragment = new MedicalAssistantDirectOutcomeTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_TREND_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_CATEGORY){
                            MedicalAssistantDirectOutcomeRescueCategoryStatisticsFragment fragment = new MedicalAssistantDirectOutcomeRescueCategoryStatisticsFragment();
//                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_CATEGORY_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_INCREASE_RATIO){
                            MedicalAssistantDirectOutcomeIncreaseRatioStatisticsFragment fragment = new MedicalAssistantDirectOutcomeIncreaseRatioStatisticsFragment();
//                            MedicalAssistantDirectOutcomeTrendStatisticsFragment fragment = new MedicalAssistantDirectOutcomeTrendStatisticsFragment();
//                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_LEVEL){
                            MedicalAssistantDirectOutcomeStatisticsFragment fragment = new MedicalAssistantDirectOutcomeStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_LEVEL_ANALYSIS_STATISTICS);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_RANKING){
                            MedicalAssistantFinanceAssuranceStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_RANKING_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_TREND){
                            MedicalAssistantFinanceAssuranceTrendStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_TREND_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_INCREASE_RATIO){
                            MedicalAssistantFinanceAssuranceIncreaseRatioFragment fragment = new MedicalAssistantFinanceAssuranceIncreaseRatioFragment();
//                            MedicalAssistantFinanceAssuranceTrendStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceTrendStatisticsFragment();
//                            MedicalAssistantFinanceAssuranceStatisticsFragment fragment = new MedicalAssistantFinanceAssuranceStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.MEDICAL_ASSISTANT_FINANCE_ASSURANCE_INCREASE_RATIO_VALUE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //临时救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE) {
//                    titleArray = new String[]{"趋势分析",
//                            "排名统计",
//                            "次均救助金额排名统计",
//                            "次均救助金额趋势分析",
//                            "金额支出占比统计",
//                            "救助人次占比统计"};

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_AVERAGE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_AVERAGE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY){
                            TempDisasterAssistancePercentageStatisticsFragment fragment = new TempDisasterAssistancePercentageStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_MONEY_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_MONEY_PERCENTAGE_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_NUMBER){
                            TempDisasterAssistancePercentageStatisticsFragment fragment = new TempDisasterAssistancePercentageStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_NUMBER_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_TEMP_ASSISTANT_NUMBER_PERCENTAGE_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }

                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //受灾救助
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE) {
//                    titleArray = new String[]{"趋势分析",
//                            "排名统计",
//                            "次均救助金额排名统计",
//                            "次均救助金额趋势分析"};

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING){
                            TempDisasterAssistantStatisticsFragment fragment = new TempDisasterAssistantStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_RANKING_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND){
                            TempDisasterAssistantTrendStatisticsFragment fragment = new TempDisasterAssistantTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.ITEM_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER);
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_TREND_TYPE);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }

                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

                        }

                }
                //家庭经济状况核对
                else if (secondLevelId == StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY) {
//                    titleArray = new String[]{"业务受理情况排名统计",
//                            "业务受理情况趋势分析",
//                            "核对项目类统计",
//                            "信息共享指标统计",
//                            "核对报告排名统计",
//                            "核对报告趋势分析",
//                            "核对报告复议排名统计",
//                            "核对报告复议趋势分析"};

                        if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_RANKING){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_TREND){
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_PROJECT_CATEGORY){
                            FamilyEconomyCheckProjectCheckStatisticsFragment fragment = new FamilyEconomyCheckProjectCheckStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_PROJECT_CATEGORY_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_SHARE_INDEX){
                            FamilyEconomyCheckShareIndexStatisticsFragment fragment = new FamilyEconomyCheckShareIndexStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_INFO_SHARE_INDEX_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_RANKING){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REPORT_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_TREND){
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REPORT_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_RANKING){
                            FamilyEconomyCheckRankingStatisticsFragment fragment = new FamilyEconomyCheckRankingStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REVIEW_RANKING);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_RANKING_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);

                        } else if (thirdLevelId == StatisticsThirdLevelCategoryActivity.STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_TREND) {
                            FamilyEconomyCheckTrendStatisticsFragment fragment = new FamilyEconomyCheckTrendStatisticsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.STATISTICS_TYPE_KEY, Constants.FAMILY_ECONOMY_CHECK_REVIEW_TREND);
                            bundle.putString(Constants.METHOD_KEY, Constants.METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_TREND_STATISTICS);
                            fragment.setArguments(bundle);
                            Util.replaceIdWithFragment(this, id, fragment);
                        }
                        else {
                            SimpleCardFragment scf = SimpleCardFragment.getInstance("title " + (1));

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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
