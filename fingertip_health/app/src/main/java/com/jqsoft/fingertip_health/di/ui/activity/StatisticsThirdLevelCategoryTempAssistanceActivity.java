package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.ImageTextHorizontalLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 统计三级分类-临时救助
 * Created by Administrator on 2018-01-23.
 */

public class StatisticsThirdLevelCategoryTempAssistanceActivity extends AbstractActivity {
    //社会救助-城乡低保
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_RANKING = 1;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_TREND = 2;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_RANKING = 3;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_TREND = 4;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_POVERTY_REASON = 5;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_CANCEL_REASON = 6;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_RANKING = 7;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_TREND = 8;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_HEALTH_CLASSIFY = 9;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_ABILITY_CLASSIFY = 10;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_AGE_CLASSIFY = 11;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_RANKING = 12;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_TREND = 13;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_INCREASE_RATIO = 14;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_RANKING = 15;
    public static final int STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_AVERAGE_RANKING = 16;
    //社会救助-特困人员供养
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING = 1;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND = 2;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING = 3;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_TREND = 4;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_POVERTY_REASON = 5;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING = 6;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND = 7;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY = 8;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY = 9;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY = 10;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_RANKING = 11;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_TREND = 12;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_INCREASE_RATIO = 13;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_RANKING = 14;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_AVERAGE_RANKING = 15;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_RANKING = 16;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_CHARACTER = 17;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_SERVER = 18;
    public static final int STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_LEGAL_PERSON = 19;

    //社会救助-低收入家庭
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING = 1;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND = 2;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING = 3;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_TREND = 4;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_POVERTY_REASON = 5;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_CANCEL_REASON = 6;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING = 7;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND = 8;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY = 9;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY = 10;
    public static final int STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY = 11;

    //社会救助-医疗救助
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_CONSTITUTION = 1;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_METHOD = 2;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_TYPE = 3;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_RANKING = 4;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_TREND = 5;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_CATEGORY = 6;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_INCREASE_RATIO = 7;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_LEVEL = 8;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_RANKING = 9;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_TREND = 10;
    public static final int STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_INCREASE_RATIO = 11;

    //社会救助-临时救助
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND = 1;
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING = 2;
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING = 3;
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND = 4;
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY = 5;
    public static final int STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_NUMBER= 6;

    //社会救助-受灾救助
    public static final int STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND = 1;
    public static final int STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING = 2;
    public static final int STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING = 3;
    public static final int STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND = 4;

    //社会救助-家庭经济情况核对
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_RANKING = 1;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_TREND = 2;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_PROJECT_CATEGORY = 3;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_SHARE_INDEX = 4;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_RANKING = 5;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_TREND = 6;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_RANKING = 7;
    public static final int STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_TREND = 8;

    //社会救助-城乡低保
    private int[] subsistenceIdArray = new int[]{STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_RANKING,
            STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_VARIANCE_TREND,
            STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_RANKING,
            STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_APPROVE_TREND,
            STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_POVERTY_REASON,
            STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_CANCEL_REASON
    , STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_RANKING
            ,   STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_TREND
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_HEALTH_CLASSIFY
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_ABILITY_CLASSIFY
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ARCHIVE_AGE_CLASSIFY
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_RANKING
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_TREND
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_ACCOUNT_INCREASE_RATIO
//            , STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_RANKING
//            ,STATISTICS_URBAN_RURAL_SUBSISTENCE_THIRD_LEVEL_MODULE_ID_SUBSISTENCE_STANDARD_AVERAGE_RANKING
    };

    private int[] subsistenceImageIdArray = new int[]{
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis

    };

    private String[] subsistenceTitleArray = new String[]{
            "排名统计",
            "趋势分析",
            "排名统计",
            "趋势分析",
            "致贫原因分类统计",
            "退保原因分类统计",
            "排名统计",
            "趋势分析",
            "健康情况分类统计",
            "自理能力分类统计",
            "年龄分类统计",
            "排名统计",
            "趋势分析",
            "救助资金总支出增长率",
            "低保标准排名统计",
            "平均补助水平排名统计"
    };

    //社会救助-特困人员供养
    private int[] veryPoorSupportIdArray = new int[]{STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING,
            STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND,
            STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING,
            STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_APPROVE_TREND,
            STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_POVERTY_REASON
            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING
            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_RANKING
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_TREND
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_ACCOUNT_INCREASE_RATIO
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_RANKING
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_STANDARD_AVERAGE_RANKING
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_RANKING
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_CHARACTER
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_SERVER
//            ,STATISTICS_VERY_POOR_SUPPORT_THIRD_LEVEL_MODULE_ID_INSTITUTION_LEGAL_PERSON
    };

    private int[] veryPoorSupportImageIdArray = new int[]{
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics
    };

    private String[] veryPoorSupportTitleArray = new String[]{
            "排名统计",
            "趋势分析",
            "排名统计",
            "趋势分析",
            "致贫原因分类统计",
            "排名统计",
            "趋势分析",
            "健康情况分类统计",
            "自理能力分类统计",
            "年龄分类统计",
            "排名统计",
            "趋势分析",
            "救助资金总支出增长率",
            "特困标准排名统计",
            "平均补助水平排名统计",
            "排名统计",
            "性质分类统计",
            "管理服务人员分类统计",
            "法人登记分类统计"
    };

    //社会救助-低收入家庭
    private int[] lowSalaryFamilyIdArray = new int[]{STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_RANKING,
            STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_VARIANCE_TREND,
            STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_RANKING,
            STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_APPROVE_TREND,
            STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_POVERTY_REASON,
            STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_CANCEL_REASON
            ,STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_RANKING
            ,STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_TREND
//            ,STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_HEALTH_CLASSIFY
//            ,STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_ABILITY_CLASSIFY
//            ,STATISTICS_LOW_SALARY_FAMILY_THIRD_LEVEL_MODULE_ID_ARCHIVE_AGE_CLASSIFY
    };

    private int[] lowSalaryFamilyImageIdArray = new int[]{
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
    };

    private String[] lowSalaryFamilyTitleArray = new String[]{
            "排名统计",
            "趋势分析",
            "排名统计",
            "趋势分析",
            "致贫原因分类统计",
            "退保原因分类统计",
            "排名统计",
            "趋势分析",
            "健康情况分类统计",
            "自理能力分类统计",
            "年龄分类统计"
    } ;

    //社会救助-医疗救助
    private int[] medicalAssistanceIdArray = new int[]{STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_CONSTITUTION,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_METHOD,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY_COMPENSATION_TYPE,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_RANKING,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_TREND,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_CATEGORY,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_INCREASE_RATIO,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_DIRECT_OUTCOME_LEVEL,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_RANKING,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_TREND,
            STATISTICS_MEDICAL_ASSISTANCE_THIRD_LEVEL_MODULE_ID_SUPPORT_OUTCOME_INCREASE_RATIO    };

    private int[] medicalAssistanceImageIdArray = new int[]{
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics
    };

    private String[] medicalAssistanceTitleArray = new String[]{
            "资金构成",
            "资金补偿方式构成",
            "资金补偿类型构成",
            "排名统计",
            "趋势分析",
            "民政救助类别统计",
            "直接医疗救助增长率",
            "医疗救助水平分析",
            "排名统计",
            "趋势分析",
            "资助参保增长率"
    } ;

    //社会救助-临时救助
    private int[] tempAssistanceIdArray = new int[]{ STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND,
            STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING,
            STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING,
            STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND,
            STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_MONEY ,
            STATISTICS_TEMP_ASSISTANCE_THIRD_LEVEL_MODULE_ID_NUMBER        };

    private int[] tempAssistanceImageIdArray = new int[]{
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics
    };

    private String[] tempAssistanceTitleArray = new String[]{
            "趋势分析",
            "排名统计",
            "次均救助金额排名统计",
            "次均救助金额趋势分析",
            "金额支出占比统计",
            "救助人次占比统计"
    } ;

    //社会救助-受灾救助
    private int[] disasterAssistanceIdArray = new int[]{STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_TREND,
            STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_RANKING,
            STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_RANKING,
            STATISTICS_DISASTER_ASSISTANCE_THIRD_LEVEL_MODULE_ID_AVERAGE_TREND       };

    private int[] disasterAssistanceImageIdArray = new int[]{
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis
    };

    private String[] disasterAssistanceTitleArray = new String[]{
            "趋势分析",
            "排名统计",
            "次均救助金额排名统计",
            "次均救助金额趋势分析"
    } ;

    //社会救助-家庭经济状况核对
    private int[] familyEconomyCheckIdArray = new int[]{STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_RANKING,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_ACCEPTANCE_TREND,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_PROJECT_CATEGORY,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_SHARE_INDEX,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_RANKING,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REPORT_TREND,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_RANKING,
            STATISTICS_FAMILY_ECONOMY_CHECK_THIRD_LEVEL_MODULE_ID_REVIEW_TREND      };

    private int[] familyEconomyCheckImageIdArray = new int[]{
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis,
            R.mipmap.g_ranking_statistics,
            R.mipmap.g_trend_analysis
    };

    private String[] familyEconomyCheckTitleArray = new String[]{
            "排名统计",
            "趋势分析",
            "核对项目类统计",
            "信息共享指标统计",
            "排名统计",
            "趋势分析",
            "排名统计",
            "趋势分析"
    } ;



    @BindView(R.id.statistics_third_level_category_title)
    TextView tvTitle;
    @BindView(R.id.ithl_trend)
    ImageTextHorizontalLayout ithlTrend;
    @BindView(R.id.ithl_ranking)
    ImageTextHorizontalLayout ithlRanking;
    @BindView(R.id.ithl_average_ranking)
    ImageTextHorizontalLayout ithlAverageRanking;
    @BindView(R.id.ithl_average_trend)
    ImageTextHorizontalLayout ithlAverageTrend;
    @BindView(R.id.ithl_money_percentage)
    ImageTextHorizontalLayout ithlMoneyPercentage;
    @BindView(R.id.ithl_number_percentage)
    ImageTextHorizontalLayout ithlNumberPercentage;

    private int statisticsFirstLevelId;
    private int statisticsSecondLevelId;

    private String toolbarTitle;

    private int[] selectedIdArray;
    private int[] selectedImageIdArray;
    private String[] selectedTitleArray;

    private List<ImageAndTextBean> list;


    private void populateToolbarTitle(){
        switch (statisticsSecondLevelId) {
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_URBAN_RURAL_SUBSISTENCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_RAISE_POOR_PEOPLE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_LOW_SALARY_FAMILY;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_MEDICAL_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_TEMPORARY_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_DISASTER_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_CHECK_FAMILY_ECONOMY;
                break;
            default:
                break;
        }

    }

    private void populateSelectedSecondLevelCorrespondingInfo(){
        switch (statisticsSecondLevelId){
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE:
                selectedIdArray = subsistenceIdArray;
                selectedImageIdArray = subsistenceImageIdArray;
                selectedTitleArray = subsistenceTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE:
                selectedIdArray = veryPoorSupportIdArray;
                selectedImageIdArray = veryPoorSupportImageIdArray;
                selectedTitleArray = veryPoorSupportTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY:
                selectedIdArray = lowSalaryFamilyIdArray;
                selectedImageIdArray = lowSalaryFamilyImageIdArray;
                selectedTitleArray = lowSalaryFamilyTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE:
                selectedIdArray = medicalAssistanceIdArray;
                selectedImageIdArray = medicalAssistanceImageIdArray;
                selectedTitleArray = medicalAssistanceTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE:
                selectedIdArray = tempAssistanceIdArray;
                selectedImageIdArray = tempAssistanceImageIdArray;
                selectedTitleArray = tempAssistanceTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE:
                selectedIdArray = disasterAssistanceIdArray;
                selectedImageIdArray = disasterAssistanceImageIdArray;
                selectedTitleArray = disasterAssistanceTitleArray;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY:
                selectedIdArray = familyEconomyCheckIdArray;
                selectedImageIdArray = familyEconomyCheckImageIdArray;
                selectedTitleArray = familyEconomyCheckTitleArray;
                break;
            default:
                break;
        }
    }

    private void initModelList(){
        list = new ArrayList<>();
        if (selectedIdArray!=null) {
            for (int i = 0; i < selectedIdArray.length; ++i){
                ImageAndTextBean bean = new ImageAndTextBean(selectedIdArray[i], selectedImageIdArray[i], selectedTitleArray[i]);
                list.add(bean);
            }
        }

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics_third_level_category_temp_assistance_layout;
    }

    @Override
    protected void initData() {
        statisticsFirstLevelId= getDeliveredIntByKey(Constants.STATISTICS_FIRST_LEVEL_ID_KEY);
        statisticsSecondLevelId = getDeliveredIntByKey(Constants.STATISTICS_SECOND_LEVEL_ID_KEY);
        populateToolbarTitle();
        populateSelectedSecondLevelCorrespondingInfo();
        initModelList();

    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        tvTitle.setText(toolbarTitle);

//        List<ImageAndTextBean> moduleList = list;
//        LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
//        ImageAndTextHorizontalAdapter adapter = new ImageAndTextHorizontalAdapter(moduleList);
////        Util.addDividerToRecyclerView(context, recyclerView, true);
//        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//                ImageAndTextBean item = (ImageAndTextBean) adapter.getItem(position);
//                LogUtil.i("has selected item title:" + item.getTitle());
////                Util.showAlert(StatisticsSecondLevelCategoryActivity.this, "提示", Integer.toString(item.getId()));
//                gotoModule(item);
////                RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_MODULE, item);
//            }
//        });
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);

        initItemClickListener();
    }

    private void initItemClickListener(){
        ithlTrend.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(0));
            }
        });
        ithlRanking.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(1));
            }
        });
        ithlAverageRanking.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(2));
            }
        });
        ithlAverageTrend.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(3));
            }
        });
        ithlMoneyPercentage.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(4));
            }
        });
        ithlNumberPercentage.setClickListener(new ImageTextHorizontalLayout.ClickListener() {
            @Override
            public void layoutDidClick() {
                gotoModule(list.get(5));
            }
        });
    }

    private void gotoModule(ImageAndTextBean bean) {
        if (bean==null){
            return;
        }
        int id = bean.getId();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, statisticsFirstLevelId);
        bundle.putInt(Constants.STATISTICS_SECOND_LEVEL_ID_KEY, statisticsSecondLevelId);
        bundle.putInt(Constants.STATISTICS_THIRD_LEVEL_ID_KEY, id);
        bundle.putString(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY, bean.getTitle());
        Util.gotoActivityWithBundle(this, StatisticsSpecificActivity.class, bundle);
//        Util.gotoActivityWithBundle(this, StatisticsMultipageContainerActivity.class, bundle);

    }



    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
