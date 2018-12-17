package com.jqsoft.fingertip_health.base;

import android.graphics.Color;
import android.view.ViewGroup;

import com.amap.api.maps.CoordinateConverter;

/**
 * Created by Administrator on 2017/5/13.
 */

public interface Constants {
    String URL_PATH_CALL_DOCTOR_TEAM = "person/getDoctorTeamInfo";


    int RXBINDING_THROTTLE = 1;

    String EMPTY_STRING = "";
    String ZERO_STRING = "0";
    String DOUBLE_ZERO_STRING = "00";
    String ONE_STRING = "1";
    String HYPHEN_STRING = "-";
    String COMMA_STRING = ",";
    String PERIOD_STRING = ".";
    String COLON_STRING = ":";
    String SPACE_STRING = " ";
    String LT_STRING = "<";
    String GT_STRING = ">";
    String LEFT_PARENTHESIS="(";
    String RIGHT_PARENTHESIS=")";
    String PERCENT_STRING = "%";
    String T_SEPARATOR="T";
    String NEW_LINE_SEPARATOR = "\n";
    String SLASH_STRING="/";
    String ELLIPSIS_STRING="..";

    float TINY_NUMBER = 1e-5f;

    String FILLED_STAR = "★";
    String UNFILLED_STAR = "☆";
    int MAX_STAR_NUMBER = 5;

    int BUGLY_APP_ID_REAL_INDEX=1;

    String NONSCIENTIFIC_NUMBER_FORMATTER_STRING = ",##0.00";
    String INT_NUMBER_FORMATTER_STRING="##";


    int MAX_CHARACTER_NUMBER_PER_LINE = 14;

    int MAX_OFFSCREEN_PAGE_LIMIT = 10;
    int VIEW_PAGER_OFF_SCREEN_NUMBER = 3;

    long INTRODUCTION_DISPLAY_DURATION = 100;

    String SRC_LOGIN_RESULT_BEAN_KEY = "srcLoginResultBeanKey";
    String LOGIN_RESULT_FOR_FINGERTIP_BEAN_KEY = "loginResultForFingertipBeanKey";

    int JOB_SERVICE_JOB_ID = 1;

    int LONGITUDE_LATITUDE_FRACTION_DIGITS_NUMBER=6;

    String PACKAGE_LEVEL_BASE = "1";
    String PACKAGE_LEVEL_JUNIOR = "2";
    String PACKAGE_LEVEL_INTERMEDIATE = "3";
    String PACKAGE_LEVEL_ADVANCED = "4";

    String PACKAGE_LEVEL_BASE_REPRESENTATION = "基础包";
    String PACKAGE_LEVEL_JUNIOR_REPRESENTATION = "初级包";
    String PACKAGE_LEVEL_INTERMEDIATE_REPRESENTATION = "中级包";
    String PACKAGE_LEVEL_ADVANCED_REPRESENTATION = "高级包";

    String PACKAGE_LEVEL_BASE_REPRESENTATION_SIMPLIFY = "基础";
    String PACKAGE_LEVEL_JUNIOR_REPRESENTATION_SIMPLIFY = "初级";
    String PACKAGE_LEVEL_INTERMEDIATE_REPRESENTATION_SIMPLIFY = "中级";
    String PACKAGE_LEVEL_ADVANCED_REPRESENTATION_SIMPLIFY = "高级";

    int DEFAULT_INT = Integer.MAX_VALUE;

    int NUMBER_OF_SIGN_INFO_OVERVIEW_PER_YEAR = 4;

    int NUMBER_OF_SIGN_SERVICE_INCOME_CATEGORY = 3;

    int SETTING_PER_CATEGORY_TAG_START = 10;
    int STATISTICS_SOCIAL_RESCUE_PER_CATEGORY_TAG_START = 1000;

    //    透传消息key:
    String PUSH_NOTIFICATION_INTENT = "pushNotificationIntent";
    //    透传消息value:
    String PI_ADD_SIGN_APPLICATION = "0";//添加签约申请
    String PI_CANCEL_SIGN_APPLICATION = "1";//取消签约申请
    String PI_ADD_APPOINTMENT_SIGN = "2";//添加预约签约
    String PI_CANCEL_APPOINTMENT_SIGN = "4";//取消预约签约
    String PI_EXECUTE_APPOINTMENT_SIGN_SUCCESS = "5";//执行预约签约成功
    String PI_EXECUTE_APPOINTMENT_SIGN_FAILURE = "6";//执行预约签约失败
    String PI_ADD_SIGN_SERVICE_ASSESS = "7";//添加签约服务评价
    String PI_NEW_ONLINE_ADVISORY = "8";//新在线咨询信息到来

    String PI_ADD_SIGN_XINWEN = "0";//添加政策新闻
    String PI_ADD_SIGN_TONGZHI = "1";//添加通知公告
    String PI_ADD_SIGN_XIAOXI= "2";//添加我的消息


//    String PACKAGE_NAME = "com.jqsoft.fingertip_health";
//    String LOGIN_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew";
//    String WORKBENCH_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity";
//    String TREATMENT_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.TreatmentActivity";
//    String CHAT_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.ChatActivity";
//    String MESSAGE_LIST_ACTIVITY_NAME = "com.jqsoft.fingertip_health.jmessage.messages.MessageListActivity";
//    String MEDICAL_INSTITUTION_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.MedicalInstitutionActivity";
//    String MEDICAL_INSTITUTION_DETAIL_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.MedicalInstitutionDetailActivity";
//    String CANHE_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.CanheActivity";
//    String CANHE_DETAIL_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.CanheDetailActivity";
//    String COMPENSATE_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.CompensateActivity";
//    String COMPENSATE_DETAIL_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.CompensateDetailActivity";
//    String COMPENSATE_FEE_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.CompensateFeeActivity";
//    String SETTING_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SettingActivity";
//    String EXECUTION_PROJECTS_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.ExecutionProjectsActivity";
//    String MY_INFO_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.MyInfoActivity";
//    String INTELLIGENT_HONOUR_AGREEMENT_REMIND_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.IntelligentHonourAgreementRemindActivity";
//    String DIRECTORY_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.DirectoryActivity";
//    String SETTING_NEW_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SettingActivityNew";
//    String MY_SIGN_INFO_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.MySignInfoActivity";
//    String SIGN_SERVICE_ASSESS_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity";
//    String SIGN_SERVICE_INCOME_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SignServiceIncomeActivity";
//    String SIGN_APPLICATION_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SignApplicationActivity";
//    String APPOINTMENT_REGISTRATION_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.AppointmentRegistrationActivity";
//    String ONLINE_CONSULTATION_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.OnlineConsultationActivity";
//    String VILLAGE_LEVEL_MEDICAL_INSTITUTION_DIRECTORY_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.VillageLevelMedicalInstitutionDirectoryActivity";
//    String MEDICAL_PERSON_DIRECTORY_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.MedicalPersonDirectoryActivity";
//    String SEARCH_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.SearchActivity";
//    String RESERVATION_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.ReservationServiceActivity";
//    String DETAILPEOPLEINFO_ACTIVITY_NAME = "com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity";

    int OKHTTP_CLIENT_CONNECT_TIMEOUT_SECONDS = 20;
    int OKHTTP_CLIENT_READ_TIMEOUT_SECONDS = 300;
    int OKHTTP_CLIENT_WRITE_TIMEOUT_SECONDS = 300;
//    int OKHTTP_CLIENT_READ_TIMEOUT_SECONDS = 60;
//    int OKHTTP_CLIENT_WRITE_TIMEOUT_SECONDS = 60;

    String HTTP_RESPONSE_SUCCESS_CODE = "200";
    String HTTP_RESPONSE_SUCCESS_CODE_2 = "0";
    String HTTP_RESPONSE_EMPTY_CODE = "404";
    String HTTP_RESPONSE_EMPTY_CODE_2 = "444";
    String HTTP_RESPONSE_TOKEN_TIMEOUT_CODE = "100";
    String HTTP_RESPONSE_DEFAULT_CODE = "99999";
    String HTTP_RESPONSE_FAILURE_CODE = "0";

    String HTTP_RESPONSE_SUCCESS_CODE_FOR_FINGERTIP = "1";
    String HTTP_RESPONSE_FAILURE_CODE_FOR_FINGERTIP = "0";
    String HTTP_RESPONSE_TIMEOUT_CODE_FOR_FINGERTIP = "99";

    int RECORD_VIDEO_MAX_DURATION_IN_SECONDS=10;
    String VIDEO_URL_KEY="videoUrlKey";


    int EVENT_TYPE_DID_SELECT_AREA = 10000;
    int EVENT_TYPE_DID_SELECT_FUNCTION_IMAGE = 10001;
    int EVENT_TYPE_DID_SELECT_HOSPITAL_TYPE = 10002;
    int EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX = 10003;
    int EVENT_TYPE_RETROFIT_BASE_URL_CHANGE = 10004;
    int EVENT_TYPE_SOUND_SABESUCUSS = 2018;
    int EVENT_TYPE_SOUND_SAVEPY = 2019;
    int EVENT_TYPE_IDCARD = 100;
    int EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE = 10004;
    int EVENT_TYPE_DID_SELECT_MODULE = 10005;
    int GUIDE_TYPE_DID_SELECT_MODULE = 10099;
    int EVENT_TYPE_DID_CLICK_LEFT_RIGHT_ARROW_IN_SIGN_INFO_OVERVIEW = 10006;//主页
    int EVENT_TYPE_DID_CLICK_LEFT_RIGHT_ARROW_IN_MY_SIGN_INFO_OVERVIEW = 10007;//我的签约信息页面
    int EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM = 10008;
    int EVENT_TYPE_DOWNLOAD_SIGN_SERVICE_INCOME_DATA = 10009;
    int EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_SIGN_BUTTON = 10010;
    int EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_CANCEL_BUTTON = 10011;
    int EVENT_TYPE_FINISH_INTRODUCTION_ACTIVITY = 10012;
    int EVENT_TYPE_FINISH_ACTIVITY = 10013;
    int EVENT_TYPE_REFRESH_INTELLIGENT_HONOUR_AGREEMENT_REMIND = 10014;
    int EVENT_TYPE_SIGN_SERVICE_ASSESS_DID_CLICK_READ_BUTTON = 10030;
    int EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE = 10040;
    int EVENT_TYPE_SIGN_DOCTOR_SERVER_CLIENTSIGN_ASSESS = 10041;
    int EVENT_TYPE_BUGLY_UPGRADE_CODE = 10049;
    int EVENT_TYPE_BUGLY_UPGRADE_CODE_CLEAR_DATA = 10009;
    int EVENT_TYPE_BUGLY_UPGRADE_SUCCESS = 10050;
    int EVENT_TYPE_BUGLY_UPGRADE_FAILURE = 10051;
    int EVENT_TYPE_BUGLY_UPGRADE_UPGRADING = 10052;
    int EVENT_TYPE_BUGLY_UPGRADE_NO_VERSION = 10053;
    int EVENT_TYPE_BUGLY_UPGRADE_DOWNLOAD_COMPLETED = 10054;
    int EVENT_TYPE_ADD_FAMILY_MEMBER_SUCCESS = 10060;
    int EVENT_TYPE_GOTO_APPOINTMENT_SIGN_PAGE = 10070;
    int EVENT_TYPE_REFRESH_CHAT_PERSON_LIST = 10080;
    int EVENT_TYPE_CHATDETAILACTIVTY = 10090;
    int EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_ONLINE_SIGN_AND_INITIALIZE = 10100;
    int EVENT_TYPE_ONLINE_SIGN_PER_FRAGMENT_INITIALIZE = 10101;
    int EVENT_TYPE_INDEX_REFRESH_INTELLIGENT_HONOUR_AGREEMENT = 10110;
    int EVENT_TYPE_MAP_TOUCH_TO_DRAW_CIRCLE_DID_FINISH = 10120;
    int EVENT_TYPE_STATISTICS_DID_SELECT_CONCRETE_MODULE = 10200;
    int EVENT_TYPE_SURFADAPTER = 100086;

    String DEVICE_KEY_FOR_FINGERTIP="device";
    String TOKEN_KEY_FOR_FINGERTIP = "token";
    String SIGN_KEY_FOR_FINGERTIP="sign";
    String TIMESTAMP_KEY_FOR_FINGERTIP="timestamp";
    String CODE_KEY_FOR_FINGERTIP="code";
    String BODY_KEY_FOR_FINGERTIP="body";

    String LIMIT_KEY_FOR_FINGERTIP="limit";
    String PAGE_KEY_FOR_FINGERTIP="page";

    String REQUEST_KEY = "sRequest";

    String CODE_NOTIFICATION = "Article_5";
    String CODE_POLICY = "touch_article_01";
    String CODE_RESCUR = "touch_article_02";

    String METHOD_NAME_NOTIFICATION_OR_POLICY="appNewsService.queryNewsOrPoliceList";
    String METHOD_NAME_SUBSISTENCE_VARIANCE_RANKING_STATISTICS="diBaoStatis.diBaoChangeRanking";
    String METHOD_NAME_SUBSISTENCE_VARIANCE_TREND_STATISTICS="diBaoStatis.diBaoChangeTrend";
    String METHOD_NAME_SUBSISTENCE_APPROVE_RANKING_STATISTICS="diBaoStatis.diBaoApproveRanking";
    String METHOD_NAME_SUBSISTENCE_APPROVE_TREND_STATISTICS="diBaoStatis.diBaoApproveTrend";
    String METHOD_NAME_SUBSISTENCE_POVERTY_REASON_STATISTICS="diBaoStatis.poorReason";
    String METHOD_NAME_SUBSISTENCE_CANCEL_SUPPORT_REASON_STATISTICS="diBaoStatis.backReason";
    String METHOD_NAME_SUBSISTENCE_ARCHIVE_RANKING_STATISTICS="diBaoStatis.dibaoRecordRanking";
    String METHOD_NAME_SUBSISTENCE_ARCHIVE_TREND_STATISTICS="diBaoStatis.dibaoRecordTrend";
    String METHOD_NAME_SUBSISTENCE_ARCHIVE_HEALTH_CLASSIFY_STATISTICS="diBaoStatis.healthClassify";
    String METHOD_NAME_SUBSISTENCE_ARCHIVE_ABILITY_CLASSIFY_STATISTICS="diBaoStatis.abilityClassify";
    String METHOD_NAME_SUBSISTENCE_ARCHIVE_AGE_CLASSIFY_STATISTICS="diBaoStatis.ageClassify";
    String METHOD_NAME_SUBSISTENCE_ACCOUNT_RANKING_STATISTICS="diBaoStatis.moneyRanking";
    String METHOD_NAME_SUBSISTENCE_ACCOUNT_TREND_STATISTICS="diBaoStatis.moneyTrend";
    String METHOD_NAME_SUBSISTENCE_ACCOUNT_INCREASE_RATIO_STATISTICS="diBaoStatis.moneyIncreaseRate";
    String METHOD_NAME_SUBSISTENCE_STANDARD_RANKING_STATISTICS="diBaoStatis.standardRanking";
    String METHOD_NAME_SUBSISTENCE_STANDARD_AVERAGE_RANKING_STATISTICS="diBaoStatis.avgLevelOfReliefRanking";

    String METHOD_NAME_VERY_POOR_VARIANCE_RANKING_STATISTICS="teKunStatis.queryTKChangePMReport";
    String METHOD_NAME_VERY_POOR_VARIANCE_TREND_STATISTICS="teKunStatis.queryTKChangeQSReport";
    String METHOD_NAME_VERY_POOR_APPROVE_RANKING_STATISTICS="teKunStatis.queryTKCheckPMReport";
    String METHOD_NAME_VERY_POOR_APPROVE_TREND_STATISTICS="teKunStatis.queryTKCheckQSReport";
    String METHOD_NAME_VERY_POOR_POVERTY_REASON_STATISTICS="teKunStatis.queryTKCheckPoorReport";
    String METHOD_NAME_VERY_POOR_ARCHIVE_RANKING_STATISTICS="teKunStatis.queryTKAchivePMReport";
    String METHOD_NAME_VERY_POOR_ARCHIVE_TREND_STATISTICS="teKunStatis.queryTKAchiveQSReport";
    String METHOD_NAME_VERY_POOR_ARCHIVE_HEALTH_CLASSIFY_STATISTICS="teKunStatis.queryTKAchiveHealthyReport";
    String METHOD_NAME_VERY_POOR_ARCHIVE_ABILITY_CLASSIFY_STATISTICS="teKunStatis.queryTKAchiveSelfCareReport";
    String METHOD_NAME_VERY_POOR_ARCHIVE_AGE_CLASSIFY_STATISTICS="teKunStatis.queryTKAchiveAgeReport";
    String METHOD_NAME_VERY_POOR_ACCOUNT_RANKING_STATISTICS="teKunStatis.queryTKLedgerPMReport";
    String METHOD_NAME_VERY_POOR_ACCOUNT_TREND_STATISTICS="teKunStatis.queryTKLedgerQSReport";
    String METHOD_NAME_VERY_POOR_ACCOUNT_INCREASE_RATIO_STATISTICS="teKunStatis.queryTKLedgerZZLReport";
    String METHOD_NAME_VERY_POOR_STANDARD_RANKING_STATISTICS="teKunStatis.queryTKStandardReport";
    String METHOD_NAME_VERY_POOR_STANDARD_AVERAGE_RANKING_STATISTICS="teKunStatis.queryTKStandardAvgReport";
    String METHOD_NAME_VERY_POOR_INSTITUTION_RANKING_STATISTICS="teKunStatis.queryGovPMReport";
    String METHOD_NAME_VERY_POOR_INSTITUTION_CHARACTER_STATISTICS="teKunStatis.queryGovXZReport";
    String METHOD_NAME_VERY_POOR_INSTITUTION_SERVER_STATISTICS="teKunStatis.queryGovPersonReport";
    String METHOD_NAME_VERY_POOR_INSTITUTION_LEGAL_PERSON_STATISTICS="teKunStatis.queryGovFRReport";

    String METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_RANKING_STATISTICS="lowIncomeData.lowIncomeFamilySituationRank";
    String METHOD_NAME_LOW_SALARY_FAMILY_VARIANCE_TREND_STATISTICS="lowIncomeData.lowIncomeFamilySituationTrend";
    String METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_RANKING_STATISTICS="lowIncomeData.lowIncomeFamilyAuditingRank";
    String METHOD_NAME_LOW_SALARY_FAMILY_APPROVE_TREND_STATISTICS="lowIncomeData.lowIncomeFamilyAuditingThrend";
    String METHOD_NAME_LOW_SALARY_FAMILY_POVERTY_REASON_STATISTICS="lowIncomeData.lowIncomeFamilyAuditingZpyy";
    String METHOD_NAME_LOW_SALARY_FAMILY_CANCEL_SUPPORT_REASON_STATISTICS="lowIncomeData.lowIncomeFamilyAuditingTbyy";
    String METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_RANKING_STATISTICS="lowIncomeData.lowIncomeFamilyArchivesRank";
    String METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_TREND_STATISTICS="lowIncomeData.lowIncomeFamilyArchivesThrend";
    String METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_HEALTH_CLASSIFY_STATISTICS="lowIncomeData.lowIncomeFamilyArchivesJkqk";
    String METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_ABILITY_CLASSIFY_STATISTICS="lowIncomeData.lowIncomeFamilyArchivesZlnl";
    String METHOD_NAME_LOW_SALARY_FAMILY_ARCHIVE_AGE_CLASSIFY_STATISTICS="lowIncomeData.lowIncomeFamilyArchivesDbnl";

    String METHOD_NAME_MEDICAL_ASSISTANT_MONEY_CONSTITUTION_STATISTICS="yiliao.compositionMoneyYljz";
    String METHOD_NAME_MEDICAL_ASSISTANT_DIRECT_OUTCOME_STATISTICS="yiliao.peoplesDirectYljz";
    String METHOD_NAME_MEDICAL_ASSISTANT_FINANCE_ASSURANCE_STATISTICS="yiliao.insuredYljz";

    String METHOD_NAME_TEMP_ASSISTANT_TREND_STATISTICS="sriStatis.moneyAndPersonTrend";
    String METHOD_NAME_TEMP_ASSISTANT_RANKING_STATISTICS="sriStatis.moneyAndPersonRanking";
    String METHOD_NAME_TEMP_ASSISTANT_AVERAGE_RANKING_STATISTICS="sriStatis.moneyLevelRanking";
    String METHOD_NAME_TEMP_ASSISTANT_AVERAGE_TREND_STATISTICS="sriStatis.moneyLevelTrend";
    String METHOD_NAME_TEMP_ASSISTANT_MONEY_PERCENTAGE_STATISTICS="sriStatis.moneyClassifyByFamilyType";
    String METHOD_NAME_TEMP_ASSISTANT_NUMBER_PERCENTAGE_STATISTICS="sriStatis.personClassifyByFamilyType";
    String METHOD_NAME_DISASTER_ASSISTANT_TREND_STATISTICS="sriStatis.moneyAndPersonTrend";
    String METHOD_NAME_DISASTER_ASSISTANT_RANKING_STATISTICS="sriStatis.moneyAndPersonRanking";
    String METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_RANKING_STATISTICS="sriStatis.moneyLevelRanking";
    String METHOD_NAME_DISASTER_ASSISTANT_AVERAGE_TREND_STATISTICS="sriStatis.moneyLevelTrend";

    String METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_RANKING_STATISTICS="HecvStatisService.hecvCaseRanking";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_BUSINESS_ACCEPTANCE_TREND_STATISTICS="HecvStatisService.hecvCaseTrend";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_PROJECT_CATEGORY_STATISTICS="HecvStatisService.hecvClassify";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_INFO_SHARE_INDEX_STATISTICS="HecvStatisService.hecvShareTarget";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_RANKING_STATISTICS="HecvStatisService.hecvReportRanking";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_TREND_STATISTICS="HecvStatisService.hecvReportTrend";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_RANKING_STATISTICS="HecvStatisService.hecvReconsiderRanking";
    String METHOD_NAME_FAMILY_ECONOMY_CHECK_REPORT_REVIEW_TREND_STATISTICS="HecvStatisService.hecvReconsiderTrend";

    String METHOD_NAME_GET_AMBIENT_PERSON_LIST="mapData.queryPoorInfoByAmbitusMap";
    String METHOD_NAME_GET_DRAW_TO_SEARCH_PERSON_LIST="mapData.queryPoorInfoByCirclecMap";
    String METHOD_NAME_GET_REGION_PERSON_LIST="mapData.queryPoorInfoByAreaMap";
    String METHOD_NAME_GET_CATEGORY_PERSON_LIST="mapData.queryPoorInfoBySortMap";
    String METHOD_NAME_GET_KEYWORD_PERSON_LIST="mapData.queryPoorInfoByKeyMap";
    String METHOD_NAME_GET_HEATMAP_BEAN="mapData.queryHeatMap";

    String METHOD_NAME_RELIEF_ITEM = "itemHelpData.queryReliefItemListConvenience";
    String METHOD_NAME_RELIEF_INST = "itemHelpData.queryReliefInstListConvenience";
    String METHOD_NAME_RELIEF_OR_POLITY = "itemHelpData.queryReliefInstListConvenience";

    int START_PAGE_INDEX = 0;
    int PAGE_SIZE_MINOR = 2;
    int PAGE_SIZE = 10;


    int GRID_LAYOUT_DEFAULT_COL_NUMBER = 3;
    int AREA_SELECTION_VIEW_COL_NUMBER = 3;
    int FUNCTION_VIEW_COL_NUMBER = 4;
    int MODULE_LAYOUT_COL_NUMBER = 3;
    int STATISTICS_GRID_LAYOUT_COL_NUMBER = 2;
    int STATISTICS_GRID_LAYOUT_SECOND_LEVEL_COL_NUMBER = 2;

    int MAX_NUMBER = 999;


    int ADAPTER_AUTO_LOAD_MORE_SIZE = 1;

    int RUNNING_TASK_SIZE = 300;

    int DEFAULT_PAGE_SIZE = PAGE_SIZE;
    int DEFAULT_INITIAL_PAGE = START_PAGE_INDEX;
    int DEFAULT_MESSAGE_PAGE_SIZE = 10;
    int EXECUTION_PROJECTS_PAGE_SIZE = 10;
    int isfresh = 0;
    int SEND_MSG_MAX_LENGTH = 500;
    int HB_INITIAL_PAGE = 1;
    int HB_PAGE_SIZE = 10;

    int NEWEST_MESSAGE_LIST_SIZE = 3;

    String TYPE_SUMMARY = "1";
    String TYPE_DETAIL = "2";

    String TYPE_CURRENT_INSTITUTION = "1";
    String TYPE_CURRENT_DOCTOR = "2";

    String TYPE_INDEX = "1";
    String TYPE_MY_SIGN_INFO = "2";

    int SIGN_YEAR_MIN_VALUE = 2010;
    int SIGN_YEAR_MAX_VALUE = 2030;


    boolean LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON = false;
    boolean LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS = true;

    String LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON_KEY = "loginActivityShouldShowBackButtonKey";
    String LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS_KEY = "loginActivityShouldFinishWhenLoginSuccessKey";

    String EXECUTION_PROJECTS_TYPE_KEY = "executionProjectsTypeKey";
    String VILLAGE_LEVEL_MEDICAL_INSTITUTION_TITLE_KEY = "villageLevelMedicalInstitutionTitleKey";
    String MEDICAL_PERSON_TITLE_KEY = "medicalPersonTitleKey";

    String SIGN_SERVICE_ASSESS_TYPE_KEY = "signServiceAssessTypeKey";
    String SIGN_SERVICE_ASSESS_TYPE_NEW = "1";
    String SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ = "2";
    String SIGN_SERVICE_INCOME_TYPE_KEY = "signServiceIncomeTypeKey";
    String SIGN_SERVICE_INCOME_BEAN_KEY = "signServiceIncomeBeanKey";
    String SIGN_SERVICE_INCOME_TYPE_ALL = "1";
    String SIGN_SERVICE_INCOME_TYPE_JUNIOR = "2";
    String SIGN_SERVICE_INCOME_TYPE_INTERMEDIATE = "3";
    String SIGN_SERVICE_INCOME_TYPE_ADVANCED = "4";

    String SIGN_SERVICE_INCOME_ITEM_TYPE_TOTAL = "1";
    String SIGN_SERVICE_INCOME_ITEM_TYPE_ACTUAL = "2";
    String SIGN_SERVICE_INCOME_ITEM_TYPE_SELF = "3";
    String SIGN_SERVICE_INCOME_ITEM_TYPE_COMPENSATE = "4";
    String SIGN_SERVICE_INCOME_ITEM_TYPE_REDUCTION_AND_EXEMPTION = "5";


    String IMAGE_FILE_PATH_KEY = "imageFilePathKey";
    String MESSAGE_KEY = "messageKey";

    String USERNAME_KEY = "username";
    String PASSWORD_KEY = "password";

    String IS_DESTROYED_BY_SYSTEM_KEY = "isDestroyedBySystemKey";

    String STATISTICS_FIRST_LEVEL_ID_KEY = "statisticsFirstLevelIdKey";
    String STATISTICS_SECOND_LEVEL_ID_KEY = "statisticsSecondLevelIdKey";
    String STATISTICS_THIRD_LEVEL_ID_KEY = "statisticsThirdLevelIdKey";
    String STATISTICS_THIRD_LEVEL_TITLE_KEY = "statisticsThirdLevelTitleKey";

    String STATISTICS_RESCUE_TYPE_KEY = "statisticsRescueTypeKey";
    String STATISTICS_POVERTY_REASON_KEY = "statisticsPovertyReasonKey";

    String API_KEY_VALUE = "api_key_android";//由服务方提供，用于标识身份
    String PRIVATE_KEY_VALUE = "34@fr*53jg2!";//私钥

    String IS_AREA_OWNER="1";
    String IS_NOT_AREA_OWNER="0";

    String AREA_CODE_ALL="0";

    String API_KEY = "apiKey";
    String TIME_STAMP_KEY = "timeStamp";//取系统当前时间，用于链接的时效性验证。（只需要前10位）
    String TOKEN_KEY = "token";//MD5(时间戳 + 身份标识 + 私钥)
    String CODE_KEY = "code";
    String POLICY_NOTIFICATION_USER_NAME_KEY="userName";
    String UNITCOD_KEY = "unitCode";
    String NEWSID_KEY = "newsId";
    String AREACOD_KEY = "areaCode";
    String jgType_KEY= "jgType";
    String PAGE_INDEX_KEY = "pageIndex";//用于分页，为0表示第一次加载数据，非空,用于分页，为0表示第一次加载数据，非空
    String PAGE_SIZE_KEY = "pageSize";//本次加载的条数
    String METHOD_KEY = "method";
    String keyWords_KEY = "keyWords";
    String ISREPLY_KEY = "isReply";
    String QUSTYPE_KEY = "type";
    String QAIDS_KEY = "qaIds";
    String REG_TYPE_KEY = "regType";
    String ITEM_TYPE_KEY = "itemType";
    String RESULT_TYPE_KEY = "resultType";
    String SUP_TYPE_KEY = "supType";
    String SUB_TYPE_KEY="subType";
    String STATS_TYPE_KEY = "statsType";
    String START_DATE_KEY = "startDate";
    String END_DATE_KEY = "endDate";
    String YEAR_MONTH_KEY="yearMonth";
    String STATISTICS_YEAR_KEY="year";
    String AREA_CODE_KEY = "areaCode";
    String STATISTICS_TYPE_KEY = "type";
    String DATA_TYPE_KEY="dateType";
    String LONGITUDE_KEY="centerLng";
    String LATITUDE_KEY="centerLat";
    String RADIUS_KEY="strradius";
    String KEYWORD_KEY_NAME="key";
    String FAMILY_TYPE_KEY="familyType";
    String METHOD_NAME_KEY="methodName";
    String SELECTED_AREA_CODE_KEY="selectedAreaCodeKey";
    String IS_AREA_OWNER_KEY="isAreaOwner";
    String PAGE_TYPE_KEY="pageTypeKey";
    String GOV_TYPE_KEY="govType";
    String LAWER_TYPE_KEY="lawerType";
    String PERSON_TYPE_KEY="personType";

    String DST_LATITIDE_KEY="dstLatitudeKey";
    String DST_LONGITUDE_KEY="dstLongitudeKey";

    String SELECTED_PROVINCE_AREA_CODE_KEY="selectedProvinceAreaCodeKey";
    String SELECTED_CITY_AREA_CODE_KEY="selectedCityAreaCodeKey";
    String SELECTED_COUNTY_AREA_CODE_KEY="selectedCountyAreaCodeKey";
    String SELECTED_STREET_AREA_CODE_KEY="selectedStreetAreaCodeKey";
    String SELECTED_VILLAGE_AREA_CODE_KEY="selectedVillageAreaCodeKey";

    String SELECTED_MAP_LOCATION_KEY="selectedMapLocationKey";

    String SIGN_KEY = "sign";
    String LOGIN_USERNAME_KEY = "loginName";
    String LOGIN_PASSWORD_KEY = "password";
    String ORGANIZATION_KEY = "organizationKey";
    String USER_ID_KEY = "userId";
    String CATEGORY_KEY = "item";
    String TYPE_KEY = "type";
    String ITEMID_KEY = "itemId";
    String RECEPID_KEY = "recepId";
    String USER_NAME_KEY = "userName";
    String content_KEY = "content";

    String AREACODE_KEY = "areaCode";
    String MOBIE_PHONE_KEY = "mobiePhone";
    String REAL_NAME_KEY = "realName";
    String OLD_PASSWORD_KEY = "oldPassword";
    String NEW_PASSWORD_KEY = "newPassword";
    String COLLECTION_KEY = "collectionUrl";
    String MAILID_KEY = "mailId";
    String COLLECTIONID_KEY = "collectionId";
    String MEMBER_NAME_KEY = "memberName";
    String MEMBER_CARD_NUMBER_KEY = "memberCardNo";
    String CARD_NO_KEY = "cardNo";
    String START_INDEX_KEY = "startIndex";
    String END_INDEX_KEY = "endIndex";
    String SERVICECONTENTID_KEY = "serviceContentID";
    String PHONE_KEY = "phone";
    String DOCTOR_USER_ID = "docUserID";
    String APPLY_DOCTOR_ID = "applyDoctorId";
    String SPECIFIC_YEAR_KEY = "year";
    String ORG_ID_KEY = "orgId";
    String APPLY_KEY = "applyKey";
    String APPLY_CANCEL = "applyCancel";
    String FINISH_DOCTOR = "finishDoctor";
    String SERVICE_PLAN_ID_KEY = "servicePlanId";
    String SIGN_SERVICE_ASSESS_FLAG_KEY = "flag";
    String YEAR_KEY_NAME = "year";
    String PERSON_ID_KEY = "personID";
    String RELATIONSHIP_KEY = "relationship";
    String ID_KEY = "id";
    String CONNO_KEY = "conNo";
    String ALIAS_KEY = "alias";
    String PHOTO_KEY = "photo";

    String KEYWORD_KEY = "keyword";
    String RELIEF_ITEM_ACTIVITY_KEY = "ReliefItemActivityKey";
    String Advice_ACTIVITY_KEY = "AdviceDetailctivityKey";
    String Adviceisreply_ACTIVITY_KEY = "AdviceDetailctivityisreplyKey";
    String RELIEF_Question_ACTIVITY_KEY = "QuestionDetailctivityKey";
    String RELIEF_DETAIL_ACTIVITY_KEY = "ReceptionDetailctivityKey";
    String SETTING_SERVER_ACTIVITY_KEY="SettingServerActivity";
    String SETTING_SERVER_POSTION_ACTIVITY_KEY="SettingServerPostionActivity";
    String SETTING_SERVER_ACTIVITY_STYE_KEY="SettingServerStyleActivity";
    String RECEPTION_ITEM_ACTIVITY_KEY = "ReceptionListActivityKey";
    String RECEPTION_TYPE_ACTIVITY_KEY = "ReceptionTypeActivityKey";
    String GUIDE_ITEM_ACTIVITY_KEY = "GuideListActivityKey";
    String FROM_WHERE_ACTIVITY_KEY = "FromWhereActivityKey";

    String IGGUIDE_ITEM_ACTIVITY_KEY = "IgGuideActivityKey";
    String POLICY_DETAIL_ACTIVITY_KEY = "policyDetailActivityKey";
    String RECRPTION_NEWLIST_ACTIVITY_KEY = "ReceptionDetailNewList";
    String GUIDE_DETAIL_ACTIVITY_KEY = "GuideListActivityKey";
    //    String GUIDE_DETAIL_ACTIVITY_NAMEKEY = "GuideListActivityKey";
    String NOTIFICATION_DETAIL_ACTIVITY_KEY = "notificationDetailActivityKey";

    String SIGN_SERVICE_ASSESS_LIST_DISPLAY = "1";
    String SIGN_SERVICE_ASSESS_READ_STATUS_MODIFICATION = "2";


    String CONTAINER_TYPE_KEY = "containerTypeKey";
    String SIGN_INFO_KEY = "signInfoKey";
    String TOTAL_SIGN_INFO_OVERVIEW_NUMBER_KEY = "totalSignInfoOverviewNumberKey";
    String SIGN_INFO_OVERVIEW_INDEX_KEY = "signInfoOverviewIndexKey";
    String CLINIC_NAME_KEY = "clinicNameKey";
    String YEAR_KEY = "yearKey";
    String TOTAL_FEE_SIGN_NUMBER = "totalFeeSignNumber";
    String SIGN_RATIO = "signRatio";
    String SIGN_INFO_HINT = "signInfoHint";
    String SEVER_KEY = "serverpakekey";
    String WHETHER_ENABLE_PUSH_KEY = "whetherEnablePushKey";
    String ALLOWED_PUSH_START_TIME_KEY = "allowedPushStartTimeKey";
    String ALLOWED_PUSH_END_TIME_KEY = "allowedPushEndTimeKey";
    String PUSH_SILENCE_START_TIME_KEY = "pushSilenceStartTimeKey";
    String PUSH_SILENCE_END_TIME_KEY = "pushSilenceEndTimeKey";

    String ALLOWED_PUSH_START_TIME_DEFAULT_VALUE = "00";
    String ALLOWED_PUSH_END_TIME_DEFAULT_VALUE = "23";
    String PUSH_SILENCE_START_TIME_DEFAULT_KEY = "00:00";
    String PUSH_SILENCE_END_TIME_DEFAULT_KEY = "00:00";


    int NOTIFICATION_ID = 10000;

    int ANIMATION_DURATION=400;


    int WORKBENCH_INDEX = 0;
    int WORKBENCH_TRANSACT = 1;
    int WORKBENCH_QUERY = 2;
    int WORKBENCH_STATISTICS = 3;
    int WORKBENCH_MINE = 4;

//    int WORKBENCH_PAGE_INDEX=0;
//    int WORKBENCH_TRANSACT=1;
//    int WORKBENCH_PAGE_ONLINE_CONSULTATION=2;
//    int WORKBENCH_PAGE_ALL=3;

    int MODULE_ID_POLICY_NEWS = 1;
    int MODULE_ID_NOTIFICATION = 2;
    int MODUBLE_ID_CIVIL = 3;
    int MODUBLE_ID_HANDLEPROGRESS = 4;
    int MODUBLE_ID_HANDLEHIS = 5;
    int MODULE_ID_MAP_SERVICE =  6;
    int MODULE_ID_SOCIAILORGNATION=7;
    int MODULE_ID_QUERY=8;
    int MODULE_ID_SOCIALSTATION=9;
    int MODULE_ID_USUALLYTRUBLE=10;
    int MODULE_ID_REJESTION= 12;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE=1001;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE=1101;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_VERY_POOR_BRINGUP=1102;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_LOW_SALARY_FAMILY=1103;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_MEDICAL_ASSISTANCE=1104;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_TEMP_ASSISTANCE=1105;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_DISASTER_ASSISTANCE=1106;
    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_FAMILY_ECONOMY_CHECK=1107;


//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ARCHIVE_RANKING=20000;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ARCHIVE_TREND=20001;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ARCHIVE_HEALTH_CATEGORY=20002;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ARCHIVE_ABILITY_CATEGORY=20003;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ARCHIVE_AGE_CATEGORY=20004;
//
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ACCOUNT_RANKING=20010;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ACCOUNT_TREND=20011;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_ACCOUNT_MONEY_INCREASE_RATIO=20012;
//
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_STANDARD_RANKING=20020;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_STANDARD_AVERAGE_RANKING=20021;
//
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_VARIANCE_RANKING=20030;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_VARIANCE_TREND=20031;
//
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_APPROVE_RANKING=20040;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_APPROVE_TREND=20041;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_APPROVE_POVERTY_REASON=20042;
//    int MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE_APPROVE_QUIT_REASON=20043;


    String MODULE_TITLE_POLICY_NEWS = "政策新闻";
    String MODULE_TITLE_NOTIFICATION = "通知公告";
    String MODULE_TITLE_CIVIL = "救助对象";
    String MODULE_TITLE_HANDLEPROGRESS = "办理进度";
    String MODLE_TITLE_HANDLEHIS = "救助历史";
    String MODULE_TITLE_MAP_SERVICE = "地图服务";
    String MODULE_TILLE_SOCILARGNATION="社会组织";
    String MODULE_TILLE_QUEER="查询";
    String MODULE_TILLE_SOCIALSTATION="救助站";
    String MODULE_TILLE_USUALLYTRABLE="常见问题";
    String MODULE_TILLE_REJESTION="咨询建议";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE="社会救助";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE="城乡低保";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_VERY_POOR_BRINGUP="特困人员供养";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_LOW_SALARY_FAMILY="低收入家庭";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_MEDICAL_ASSISTANCE="医疗救助";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_TEMP_ASSISTANCE="临时救助";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_DISASTER_ASSISTANCE="受灾救助";
    String MODULE_TITLE_STATISTICS_SOCIAL_RESCUE_FAMILY_ECONOMY_CHECK="家庭经济核对";

    int MODULE_ID_ONLINE_SIGN_APPLICATION = 1;
    int MODULE_ID_MY_SIGN_INFO = 2;
    int MODULE_ID_MY_SIGN_AGREEMENT = 3;
    int MODULE_ID_SIGN_POLICY_TRANSLATE = 4;
    int MODULE_ID_SIGN_SERVICE_PACKAGE_TRANSLATE = 5;
    int MODULE_ID_INTELLIGENT_HONOUR_AGREEMENT_REMIND = 6;
    int MODULE_ID_SIGN_SERVICE_ASSESS = 7;
    int MODULE_ID_APPOINTMENT_SIGN_SERVICE = 8;
    int MODULE_ID_CALL_DOCTOR = 9;
    int MODULE_ID_ONLINE_CONSULTATION = 10;
    int MODULE_ID_GUIDE = 11;
    int MODULE_ID_POLITICS = 14;
    int MODULE_ID_Hygiene = 15;
    int MODULE_ID_EDU = 16;
    int MODULE_ID_CDPF = 17;
    int MODULE_ID_Union = 18;
    int MODULE_ID_agency = 19;
    int MODULE_ID_build = 20;
    int MODULE_ID_Business = 21;
    int MODULE_ID_Committee = 22;
    int MODULE_ID_judicial = 23;
    int MODULE_ID_Government = 24;
    int MODULE_ID_Federation = 25;
    int MODULE_ID_IG_GUIDE = 26;
    int MODULE_ID_Reception = 27;

    int MODULE_ID_FIND = 30;
    int MODULE_ID_CHENGXIANG_DIBAO = 31;
    int MODUBLE_ID_TEKUN = 32;
    int MODUBLE_ID_YILIAO_JIUZHU = 33;
    int MODUBLE_ID_LINSHI_JIUZHU = 34;
    int MODULE_ID_SOUZAI_JIUZHU = 35;
    int MODULE_ID_RUHU_DIAOCHA=36;
    int MODULE_ID_MINZHU_PINYI=37;

    String MODULE_TITLE_ZHUDONG_FAXIAN = "主动发现";
    String MODULE_TITLE_CHENGXIANG_DIBAO = "城市低保";
    String MODULE_TITLE_TEKUN_GONGYANG = "特困人员供养";
    String MODULE_TITLE_YILIAO_JIUZHU = "农村低保";
    String MODLE_TITLE_LINSHI_JIUZHU = "其他救助";
    String MODULE_TITLE_SOUZAI_JIUZHU = "受灾救助";
    String MODULE_TILLE_RUHU_DIAOCHA="入户调查";
    String MODULE_TILLE_MINZHU_PINGYI="民主评议";



    String MODULE_TITLE_ONLINE_SIGN_APPLICATION = "民政对象";
    String MODULE_TITLE_MY_SIGN_INFO = "我的签约信息";
    String MODULE_TITLE_MY_SIGN_AGREEMENT = "我的签约协议";
    String MODULE_TITLE_SIGN_POLICY_TRANSLATE = "签约政策解读";
    String MODULE_TITLE_SIGN_SERVICE_PACKAGE_TRANSLATE = "签约服务包解读";
    String MODULE_TITLE_INTELLIGENT_HONOUR_AGREEMENT_REMIND = "智能履约提醒";
    String MODULE_TITLE_SIGN_SERVICE_ASSESS = "签约服务评价";
    String MODULE_TITLE_APPOINTMENT_SIGN_SERVICE = "预约签约服务";
    String MODULE_TITLE_CALL_DOCTOR = "一键呼叫医生";
    String MODULE_TITLE_ONLINE_CONSULTATION = "在线咨询";
    String MODULE_TITLE_SIGN_COMPLAINT = "签约投诉";
    String MODULE_TITLE_GUIDE = "办事指南";
    String MODULE_TITLE_CENTER = "受理中心";
    String MODULE_TITLE_BRAIN = "智能引导";
    String MODULE_TITLE_SETTING = "设置";
    String MODULE_TITLE_POLITICS = "民政";
    String MODULE_TITLE_IG_GUIDE = "智能引导";
    String MODULE_TITLE_Reception = "受理中心";
    String MODULE_TITLE_CDPF = "残联";
    String MODULE_TITLE_Hygiene = "卫计";
    String MODULE_TITLE_EDU = "教育";
    String MODULE_TITLE_Union = "工会";
    String MODULE_TITLE_agency = "人社";
    String MODULE_TITLE_build = "住建";
    String MODULE_TITLE_Business = "工商联";
    String MODULE_TITLE_Committee = "团区委";
    String MODULE_TITLE_judicial = "司法";
    String MODULE_TITLE_Government = "综治办";
    String MODULE_TITLE_Federation = "妇联";



    int FUNCTION_1 = 1;
    int FUNCTION_2 = 2;
    int FUNCTION_3 = 3;
    int FUNCTION_4 = 4;
    int FUNCTION_5 = 5;
    int FUNCTION_6 = 6;
    int FUNCTION_7 = 7;
    int FUNCTION_8 = 8;

    int JPUSH_SET_ALIAS_SUCCESS_CODE = 0;
    int JPUSH_SET_ALIAS_FAILURE_CODE = 6002;

    int JMESSAGE_REGISTER_SUCCESS_CODE = 0;
    int JMESSAGE_REGISTER_USER_EXIST_CODE = 898001;
    int JMESSAGE_LOGIN_SUCCESS_CODE = 0;
    int JMESSAGE_GET_USER_INFO_SUCCESS_CODE = 0;
    int JMESSAGE_SEND_SUCCESS_CODE = 0;
    int JMESSAGE_RECEIVE_SUCCESS_CODE = 0;

    int LOADING_DIALOG_WIDTH = 350;
    int LOADING_DIALOG_HEIGHT = 350;

    int HANDLER_SET_ALIAS_MSG_ID = 1000;
    int HANDLER_ANDROID_JOB_WHAT_ID = 1001;

    int OFFSCREEN_PAGE_LIMIT = 10;

    int AMAP_LOCATE_INTERVAL = 10000;

    int POPUP_WINDOW_WIDTH_FOR_STATISTICS = ViewGroup.LayoutParams.WRAP_CONTENT;
//    int POPUP_WINDOW_WIDTH_FOR_STATISTICS = 400;
    int POPUP_WINDOW_WIDTH_FOR_MAP = 500;
//    int POPUP_WINDOW_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;
    int POPUP_WINDOW_HEIGHT = 700;
    int POPUP_WINDOW_HEIGHT_FOR_MAP = 800;
    int POPUP_WINDOW_X_OFFSET = -80;
    int POPUP_WINDOW_Y_OFFSET = 32;

    int DATE_RANGE_TYPE_MONTH = 0;
    int DATE_RANGE_TYPE_QUARTER = 1;
    int DATE_RANGE_TYPE_YEAR = 2;

    int DATA_TYPE_DIFFICULTY_PEOPLE = 0;
    int DATA_TYPE_YIMENSHI = 1;
    int DATA_TYPE_ALL = 2;

    String DATA_TYPE_VALUE_DIFFICULTY_PEOPLE = "1";
    String DATA_TYPE_VALUE_YIMENSHI = "2";
    String DATA_TYPE_VALUE_ALL = "3";

    int PAGE_TYPE_SUBSISTENCE=0;
    int PAGE_TYPE_VERY_POOR=1;
    int PAGE_TYPE_LOW_SALARY_FAMILY=2;

    int SUBSISTENCE_TYPE_ALL = 0;
    int SUBSISTENCE_TYPE_RURAL = 1;
    int SUBSISTENCE_TYPE_URBAN = 2;

    String SUBSISTENCE_TYPE_VALUE_ALL = "99";
    String SUBSISTENCE_TYPE_VALUE_RURAL = "01";
    String SUBSISTENCE_TYPE_VALUE_URBAN = "02";

    int HOUSEHOLD_TYPE_RURAL = 0;
    int HOUSEHOLD_TYPE_URBAN = 1;



    String ITEM_TYPE_VALUE_VERY_POOR="03";
    String ITEM_TYPE_VALUE_LOW_SALARY_FAMILY="04";
    String SUB_TYPE_ALL_VALUE="99";


    int HOUSEHOLD_TYPE_ALL = 0;
    int HOUSEHOLD_TYPE_CITY = 1;
    int HOUSEHOLD_TYPE_COUNTRYSIDE = 2;

//    String HOUSEHOLD_TYPE_VALUE_ALL = "99";
//    String HOUSEHOLD_TYPE_VALUE_CITY = "02";
//    String HOUSEHOLD_TYPE_VALUE_COUNTRYSIDE = "01";
    String HOUSEHOLD_TYPE_VALUE_ALL = "all";
    String HOUSEHOLD_TYPE_VALUE_CITY = "city";
    String HOUSEHOLD_TYPE_VALUE_COUNTRYSIDE = "countryside";

    String RESCUE_TYPE_VALUE_SUBSISTENCE = "db";
    String RESCUE_TYPE_VALUE_VERY_POOR = "tk";
    String RESCUE_TYPE_VALUE_LOW_SALARY = "dsr";

    String POVERTY_REASON_POVERTY = "1";
    String POVERTY_REASON_CANCEL = "2";

    int RESCUE_RESULT_TYPE_HOUSEHOLD = 0;
    int RESCUE_RESULT_TYPE_PERSON = 1;

    String RESCUE_RESULT_TYPE_VALUE_HOUSEHOLD = "hs";
    String RESCUE_RESULT_TYPE_VALUE_PERSON = "rs";
//    String RESCUE_RESULT_TYPE_VALUE_HOUSEHOLD = "households";
//    String RESCUE_RESULT_TYPE_VALUE_PERSON = "person";

    int VERY_POOR_SUPPORT_TYPE_ALL=0;
    int VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS=1;
    int VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE=2;
    int VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS=3;
    int VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE=4;

    int VERY_POOR_SUPPORT_TYPE_STANDARD_RURAL_FOCUS=0;
    int VERY_POOR_SUPPORT_TYPE_STANDARD_RURAL_DISPERSE=1;
    int VERY_POOR_SUPPORT_TYPE_STANDARD_URBAN_FOCUS=2;
    int VERY_POOR_SUPPORT_TYPE_STANDARD_URBAN_DISPERSE=3;

    String VERY_POOR_SUPPORT_TYPE_ALL_VALUE="99";
    String VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS_VALUE="0301";
    String VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE_VALUE="0302";
    String VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS_VALUE="0303";
    String VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE_VALUE="0304";

    int INSTITUTION_TYPE_ALL=0;
    int INSTITUTION_TYPE_GOVERNMENT=1;
    int INSTITUTION_TYPE_CIVIL=2;
    int INSTITUTION_TYPE_DISABLED=3;

    String INSTITUTION_TYPE_ALL_VALUE="99";
    String INSTITUTION_TYPE_GOVERNMENT_VALUE="1";
    String INSTITUTION_TYPE_CIVIL_VALUE="2";
    String INSTITUTION_TYPE_DISABLED_VALUE="3";

    int LEGAL_PERSON_TYPE_ALL=0;
    int LEGAL_PERSON_TYPE_CIVIL=1;
    int LEGAL_PERSON_TYPE_INDUSTRIAL=2;
    int LEGAL_PERSON_TYPE_BUSINESS=3;
    int LEGAL_PERSON_TYPE_EMPTY=4;

    String LEGAL_PERSON_TYPE_ALL_VALUE="99";
    String LEGAL_PERSON_TYPE_CIVIL_VALUE="1";
    String LEGAL_PERSON_TYPE_INDUSTRIAL_VALUE="2";
    String LEGAL_PERSON_TYPE_BUSINESS_VALUE="3";
    String LEGAL_PERSON_TYPE_EMPTY_VALUE="4";

    int PERSON_TYPE_ALL=0;
    int PERSON_TYPE_MANAGER=1;
    int PERSON_TYPE_SERVER=2;
    int PERSON_TYPE_REGISTERED=3;
    int PERSON_TYPE_TECHNICIAN=4;

    String PERSON_TYPE_ALL_VALUE="99";
    String PERSON_TYPE_MANAGER_VALUE="1";
    String PERSON_TYPE_SERVER_VALUE="2";
    String PERSON_TYPE_REGISTERED_VALUE="3";
    String PERSON_TYPE_TECHNICIAN_VALUE="4";


    int RESCUE_BRINGUP_TYPE_ALL = 0;
    int RESCUE_BRINGUP_TYPE_FOCUS = 1;
    int RESCUE_BRINGUP_TYPE_DISPERSE = 2;


    String RESCUE_BRINGUP_TYPE_VALUE_ALL = "all";
    String RESCUE_BRINGUP_TYPE_VALUE_FOCUS = "focus";
    String RESCUE_BRINGUP_TYPE_VALUE_DISPERSE = "disperse";

    String MEDICAL_ASSISTANT_MONEY_CONSTITUTIONI_VALUE = "0";
    String MEDICAL_ASSISTANT_COMPENSATE_METHOD_CONSTITUTION_VALUE = "1";
    String MEDICAL_ASSISTANT_COMPENSATE_TYPE_CONSTITUTION_VALUE = "2";

    String MEDICAL_ASSISTANT_DIRECT_OUTCOME_RANKING_STATISTICS = "0";
    String MEDICAL_ASSISTANT_DIRECT_OUTCOME_TREND_STATISTICS = "1";
    String MEDICAL_ASSISTANT_DIRECT_OUTCOME_CATEGORY_STATISTICS = "2";
    String MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS = "3";
    String MEDICAL_ASSISTANT_DIRECT_OUTCOME_LEVEL_ANALYSIS_STATISTICS = "4";

    String MEDICAL_ASSISTANT_FINANCE_ASSURANCE_RANKING_VALUE = "0";
    String MEDICAL_ASSISTANT_FINANCE_ASSURANCE_TREND_VALUE = "1";
    String MEDICAL_ASSISTANT_FINANCE_ASSURANCE_INCREASE_RATIO_VALUE = "2";

    String TEMP_DISASTER_ASSISTANT_ITEM_TYPE_ALL = "all";
    String TEMP_DISASTER_ASSISTANT_ITEM_TYPE_TEMP = "linshi";
    String TEMP_DISASTER_ASSISTANT_ITEM_TYPE_DISASTER = "shouzai";

    String FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING = "0";
    String FAMILY_ECONOMY_CHECK_REPORT_RANKING = "1";
    String FAMILY_ECONOMY_CHECK_REVIEW_RANKING = "2";

    String FAMILY_ECONOMY_CHECK_ACCEPTANCE_TREND="0";
    String FAMILY_ECONOMY_CHECK_REPORT_TREND="1";
    String FAMILY_ECONOMY_CHECK_REVIEW_TREND="2";

    int RESCUE_TYPE_ALL=0;
    int RESCUE_TYPE_TEMP=1;
    int RESCUE_TYPE_DISASTER=2;

    String TEMP_DISASTER_ASSISTANT_TEMP_TREND_TYPE = "0";
    String TEMP_DISASTER_ASSISTANT_TEMP_RANKING_TYPE = "1";
    String TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_RANKING_TYPE = "2";
    String TEMP_DISASTER_ASSISTANT_TEMP_AVERAGE_TREND_TYPE = "3";
    String TEMP_DISASTER_ASSISTANT_DISASTER_TREND_TYPE = "4";
    String TEMP_DISASTER_ASSISTANT_DISASTER_RANKING_TYPE = "5";
    String TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_RANKING_TYPE = "6";
    String TEMP_DISASTER_ASSISTANT_DISASTER_AVERAGE_TREND_TYPE = "7";
    String TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_MONEY_TYPE = "8";
    String TEMP_DISASTER_ASSISTANT_TEMP_CATEGORY_NUMBER_TYPE = "9";


    int CHECK_TYPE_ALL = 0;
    int CHECK_TYPE_LOWEST_LIFE_ASSURANCE = 1;
    int CHECK_TYPE_VERY_POOR_SUPPORT = 2;
    int CHECK_TYPE_DISASTER_ASSISTANCE = 3;
    int CHECK_TYPE_MEDICAL_ASSISTANCE = 4;
    int CHECK_TYPE_EDUCATION_ASSISTANT = 5;
    int CHECK_TYPE_HOUSE_ASSISTANCE = 6;
    int CHECK_TYPE_JOB_ASSISTANCE = 7;
    int CHECK_TYPE_TEMP_ASSISTANCE = 8;

    String CHECK_ITEM_TYPE_ALL = EMPTY_STRING;
    String CHECK_ITEM_TYPE_LOWEST_LIFE_ASSURANCE = "ZDSHBZ";
    String CHECK_ITEM_TYPE_VERY_POOR_SUPPORT = "TKRYGY";
    String CHECK_ITEM_TYPE_DISASTER_ASSISTANCE = "SZRYJZ";
    String CHECK_ITEM_TYPE_MEDICAL_ASSISTANCE = "YLJZ";
    String CHECK_ITEM_TYPE_EDUCATION_ASSISTANCE = "JIAOYUJZ";
    String CHECK_ITEM_TYPE_HOUSE_ASSISTANCE = "ZFJZ";
    String CHECK_ITEM_TYPE_JOB_ASSISTANCE = "JIUYEJZ";
    String CHECK_ITEM_TYPE_TEMP_ASSISTANCE = "LSJZ";

    String AREA_LEVEL="areaLevel";
    String AREA_PID="areaPid";

    String AREA_LEVEL_NATION="area_0";
    String AREA_LEVEL_PROVINCE="1";
    String AREA_LEVEL_CITY="2";
    String AREA_LEVEL_COUNTY="3";
    String AREA_LEVEL_STREET="4";
    String AREA_LEVEL_VILLAGE="5";
    String AREA_LEVEL_ZU="6";

    String FAMILY_TYPE_PCODE="family_type";


    String LIST_SUMMARY_TEXT="合计";
    String CHART_NO_DATA_TEXT = "暂无数据";
//    String CHART_LARGE_VALUE_HINT_TEXT=" k:千 m:百万";
    float CHART_DEFAULT_BAR_WIDTH = 1f;
    //    float CHART_GROUP_SPACE_WIDTH=CHART_DEFAULT_BAR_WIDTH/6;
    float CHART_GROUP_SPACE_WIDTH = 0f;
    int CHART_MAX_NO_VALUES_ENTRY_NUMBER = 60;
    int CHART_ANIMATION_DURATION = 1000;
//    int CHART_ANIMATION_DURATION = 1400;
    int CHART_Y_LABEL_COUNT = 5;
    float CHART_Y_SPACE_TOP_PERCENT = 20;
    int NUMBER_PER_DISTRICT = 3;
    int THROUGH_RATIO_NUMBER_PER_DISTRICT = 1;
    int BAR_NUMBER_PER_SCREEN = 24;
    int BAR_NUMBER_PER_SCREEN_SMALL = 12;
    int BAR_NUMBER_PER_SCREEN_TINY = 8;
    int BAR_NUMBER_PER_SCREEN_FOR_ONE_COLUMN = 16;
    int LINE_CHART_ITEM_NUMBER_PER_SCREEN = 5;
    float BAR_CHART_ONE_COLUMN_BAR_WIDTH=0.5f;
    float BAR_CHART_ONE_COLUMN_BAR_SPACE=0.0f;
//    float BAR_CHART_ONE_COLUMN_BAR_WIDTH=0.4f;
//    float BAR_CHART_ONE_COLUMN_BAR_SPACE=0.1f;
    float BAR_CHART_ONE_COLUMN_GROUP_SPACE=0.5f;
    float BAR_CHART_TWO_COLUMN_BAR_WIDTH=0.34f;
    float BAR_CHART_TWO_COLUMN_BAR_SPACE=0.04f;
    float BAR_CHART_TWO_COLUMN_GROUP_SPACE=0.24f;
//    float BAR_CHART_THREE_COLUMN_BAR_WIDTH=0.23f;
//    float BAR_CHART_THREE_COLUMN_BAR_SPACE=0.04f;
//    float BAR_CHART_THREE_COLUMN_GROUP_SPACE=0.19f;
    float BAR_CHART_THREE_COLUMN_BAR_WIDTH=0.22f;
    float BAR_CHART_THREE_COLUMN_BAR_SPACE=0.04f;
    float BAR_CHART_THREE_COLUMN_GROUP_SPACE=0.22f;
    float BAR_CHART_FOUR_COLUMN_BAR_WIDTH=0.16f;
    float BAR_CHART_FOUR_COLUMN_BAR_SPACE=0.04f;
    float BAR_CHART_FOUR_COLUMN_GROUP_SPACE=0.20f;

    float HORIZONTAL_BAR_CHART_START_POINT_X=100000f;
    float HORIZONTAL_BAR_CHART_START_POINT_Y=0f;
    float HORIZONTAL_BAR_CHART_MINIMUM_Y_AXIS_VALUE =50f;

    float BAR_CHART_XAXIS_OFFSET=14f;

//    int CHART_FIRST_COLOR = Color.rgb(192, 255, 140);
//    int CHART_SECOND_COLOR = Color.rgb(147, 224, 255);
//    int CHART_THIRD_COLOR = Color.rgb(255, 208, 140);
//    int CHART_FOURTH_COLOR = Color.rgb(178, 190, 126);

    int CHART_FIRST_COLOR = Color.parseColor("#3DA1FF");
    int CHART_SECOND_COLOR = Color.parseColor("#82CE26");
    int CHART_THIRD_COLOR = Color.parseColor("#2578D4");
    int CHART_FOURTH_COLOR = Color.parseColor("#FFAD2D");
    int CHART_FIFTH_COLOR = Color.parseColor("#FA6868");

    int[] CHART_COLOR_LIST = {CHART_FIRST_COLOR, CHART_SECOND_COLOR, CHART_THIRD_COLOR,
        CHART_FOURTH_COLOR, CHART_FIFTH_COLOR};

    float CHART_VALUE_TEXT_SIZE=13;
    float CHART_RANGE_DEVIATION_VALUE=1;
    float CHART_RANGE_DEVIATION_VALUE_SMALL=1;
    float CHART_RANGE_EXTRA_SPACE_PERCENT = 0.1f;
    float CHART_RANGE_EXTRA_SPACE_PERCENT_FIRST = 0.05f;
    float CHART_RANGE_EXTRA_SPACE_PERCENT_SECOND = 0.2f;
    float CHART_RANGE_EXTRA_SPACE_PERCENT_THIRD = 0.1f;
    float CHART_RANGE_EXTRA_SPACE_PERCENT_FOURTH = 0.2f;
    int CHART_LABEL_STRING_MAX_COUNT = 8;
    float CHART_PERCENT_VERY_SMALL = 0.01f;
    float CHART_VALUE_VERY_SMALL=1.0f;
    int CHART_PERCENT_SHOW_THRESHOLD = 2;

    float CHART_Y_AXIS_VALUE_FACTOR=1.4f;


    int LINE_CHART_HILIGHT_COLOR=Color.rgb(244, 117, 117);
    float LINE_CHART_LINE_WIDTH=2.5f;
    float LINE_CHART_CIRCLE_RADIUS=4.5f;


    int EDIT_INPUT_TYPE_TEXT = 0;
    int EDIT_INPUT_TYPE_DATE_TIME = 1;
    int EDIT_INPUT_TYPE_DATE = 2;
    int EDIT_INPUT_TYPE_TIME = 3;
    int EDIT_INPUT_TYPE_POSITIVE_INTEGER = 4;
    int EDIT_INPUT_TYPE_INTEGER = 5;
    int EDIT_INPUT_TYPE_POSITIVE_DECIMAL = 6;
    int EDIT_INPUT_TYPE_DECIMAL = 7;
    int EDIT_INPUT_TYPE_NUMBER_PASSWORD = 8;
    int EDIT_INPUT_TYPE_PHONE = 9;

    float MIN_LONGITUDE = -180f;
    float MAX_LONGITUDE = 180f;
    float MIN_LATITUDE = -90f;
    float MAX_LATITUDE = 90f;

    int GENDER_MALE = 0;
    int GENDER_FEMALE = 1;

    String GENDER_STRING_MALE = "0";
    String GENDER_STRING_FEMALE = "1";

    int VOICE_MIN_DURATION = 1;
    int VOICE_MAX_DURATION = 60;

    int FILE_TYPE_IMAGE = 0;
    int FILE_TYPE_VIDEO = 1;

    String MIME_ALL = "*/*";
    String MIME_IMAGE = "image/*";
    String MIME_VIDEO = "video/*";

    String ROOT_PATH = "/";
    String UP_LEVEL_NAME = "上一级文件夹";

    String HINT_PAID_SIGN = "有偿签约";
    String HINT_ELDERLY_PEOPLE = "老年人";
    String HINT_HYPERTENSION = "高血压";
    String HINT_DIABETES = "糖尿病";

    String HINT_NO_MESSAGES_SINCE = "暂无消息";

    String RATIO = "率";

    String HINT_SIGN = "签约";

    String PUBLISH_TIME = "发布日期: ";


    String WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY = "onNewIntentInitialIndexKey";
    String WORKBENCH_PUSH_NOTIFICATION_EXIST_KEY = "workbenchPushNotificationExistKey";
    String WORKBENCH_PUSH_NOTIFICATION_INTENT_KEY = "workbenchPushNotificationIntentKey";
    String WORKBENCH_PUSH_NOTIFICATION_USER_UUID_KEY = "workbenchPushNotificationUerUuidKey";
    String ONLINE_SIGN_INITIAL_DATA_KEY = "onlineSignInitialDataKey";


    String WHETHER_REMEMBER_PASSWORD_KEY = "whetherRememberPasswordKey";
    String REMEMBERED_USERNAME_KEY = "rememberedUsernameKey";
    String REMEMBERED_PASSWORD_KEY = "rememberedPasswordKey";

    String VERSION_PREFIX = "v";

    int MAP_ZOOM_LEVEL = 14;
    int MAP_ZOOM_LEVEL_FOR_ONE_POINT = 14;
    int MAP_MARKER_JUMP_DURATION=1000;
    CoordinateConverter.CoordType SERVER_MAP_COORDINATE_TYPE=CoordinateConverter.CoordType.BAIDU;
    String MAP_AMBIENT_SEARCH_INITIAL_RADIUS = "1000";
    int MAP_BOUND_SPACE=300;

    String MAP_LOCATION_TYPE_TOTAL = "total";
    String MAP_LOCATION_TYPE_POINT = "point";

    String INTELLIGENT_HONOUR_AGREEMENT_CATEGORY_OVERVIEW = "1";
    String INTELLIGENT_HONOUR_AGREEMENT_CATEGORY_DETAIL = "2";


    String FAMILY_MEMBER_SELF_VALUE = "0";
    String FAMILY_MEMBER_SPOUSE_VALUE = "1";
    String FAMILY_MEMBER_SON_VALUE = "2";
    String FAMILY_MEMBER_DAUGHTER_VALUE = "3";
    String FAMILY_MEMBER_GRAND_SON_VALUE = "4";
    String FAMILY_MEMBER_GRAND_DAUGHTER_VALUE = "5";
    String FAMILY_MEMBER_PARENT_VALUE = "6";
    String FAMILY_MEMBER_GRAND_PARENT_VALUE = "7";
    String FAMILY_MEMBER_SIBLING_VALUE = "8";
    String FAMILY_MEMBER_OTHER_VALUE = "9";
    String FAMILY_MEMBER_DAUGHTER_IN_LAW_VALUE = "11";

    String FAMILY_MEMBER_SELF = "本人或户主";
    String FAMILY_MEMBER_SPOUSE = "配偶";
    String FAMILY_MEMBER_SON = "子";
    String FAMILY_MEMBER_DAUGHTER = "女";
    String FAMILY_MEMBER_GRAND_SON = "孙子";
    String FAMILY_MEMBER_GRAND_DAUGHTER = "孙女、外孙子、外孙女";
    String FAMILY_MEMBER_PARENT = "父母";
    String FAMILY_MEMBER_GRAND_PARENT = "祖父母、外祖父母";
    String FAMILY_MEMBER_SIBLING = "兄弟姐妹";
    String FAMILY_MEMBER_OTHER = "其他";
    String FAMILY_MEMBER_DAUGHTER_IN_LAW = "儿媳";

    String TITLE_INDEX = "首页";
    String TITLE_INSPECT = "督查";
    String TITLE_MESSAGE = "在线签约";
    String TITLE_ONLINE_SIGN = "在线签约";
    String TITLE_TRANSACT = "办理";
    String TITLE_MINE = "我的";
    String TITLE_QUERY = "查询";
    String TITLE_STATISTICS = "统计";
    String TITLE_ALL = "全部";
    String TITLE_ALL_MODULE = "全部";
    String TITLE_SIGN_RESIDENT_DIRECTORY = "签约居民通讯录";
    String TITLE_MEDICAL_INSTITUTION = "医疗机构";
    String TITLE_NEW_ASSESS = "我的发现";
    String TITLE_REVIEWED = "待办发现";

    String TITLE_BASEINFO = "基本信息";
    String TITLE_FAMILY = "家庭成员";
    String TITLE_FUJIAN = "附件信息";

    String TITLE_APPLICATION = "正在申请";
    String TITLE_CHECKING = "正在复查";
    String TITLE_BASEINFOSER= "基本信息";
    String TITLE_FAMAILYMEN="家庭成员";
    String TITLE_FILE= "附件";
    String TITLE_SEVERYPAGE= "入户调查";
    String TITLE_DEMOCRATIC = "民主评议";

    String HINT_WILL_BE_AVAILABLE_SOON="即将上线,敬请期待!";





    String TITLE_SIGN_SERVICE_INCOME_ALL = "全部";
    String TITLE_SIGN_SERVICE_INCOME_JUNIOR_PACKAGE = "初级包";
    String TITLE_SIGN_SERVICE_INCOME_INTERMEDIATE_PACKAGE = "中级包";
    String TITLE_SIGN_SERVICE_INCOME_ADVANCED_PACKAGE = "高级包";

    String ANNUAL_SIGN_INFO_OVERVIEW = "年度签约信息一览";

    String HINT_NOT_AVAILABLE = "尚未开通";

    String HINT_ALIAS_EMPTY = "别名为空";
    String HINT_ALIAS_INVALID = "别名非法";

    String HINT_CONNECTION_TO_SERVER_SUCCESS = "和服务器连接成功";
    String HINT_CONNECTION_TO_SERVER_FAILURE = "和服务器连接断开";
    String HINT_NET_CONNECTION_SUCCESS = "网络连接成功";
    String HINT_NET_CONNECTION_FAILURE = "网络连接失败";

    String HINT_SIGN_SERVICE_ASSESS_READ_ITEM_SUCCESS = "阅读此签约服务评价项成功";
    String HINT_SIGN_SERVICE_ASSESS_READ_ITEM_FAILURE = "阅读此签约服务评价项失败";

    String HINT_NO_SIGN_DOCTOR_PHONE_INFO = "暂无签约医生电话信息";
    String HINT_LOAD_SIGN_DOCTOR_PHONE_INFO_FAILURE = "加载签约医生电话信息失败";
    String HINT_SELECT_DOCTOR_AND_DIAL = "请选择签约医生并拨打其电话";

    String APP_NAME = "居民健康";
    String HINT_YOU_RECEIVE_NEW_MESSAGE = "您收到新的消息";
    String HINT_CONNECTION_TO_PUSH_SERVER_SUCCESS = "和推送服务器连接成功";
    String HINT_CONNECTION_TO_PUSH_SERVER_FAILURE = "和推送服务器连接断开";
    String HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_NOTIFICATION = "处理推送通知时发生异常";
    String HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_CUSTOM_MESSAGE = "处理推送自定义消息时发生异常";
    String HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_NOTIFICATION_OR_CUSTOM_MESSAGE = "处理推送通知或自定义消息时发生异常";


    String HINT_THE_FRIEND_INFO_EMPTY = "此朋友信息为空";

    String MESSAGE_FROM_USER_ID_KEY = "messageFromUserIdKey";
    String MESSAGE_TO_USER_ID_KEY = "messageToUserIdKey";
    String MESSAGE_CONTENT_KEY = "messageContentKey";

    String SET_ALIAS_SUCCESS_KEY = "setAliasSuccessKey";

    String CONV_TITLE = "convTitle";
    String TARGET_APP_KEY = "targetAppKey";
    String TARGET_ID = "targetId";

    String HINT_JPUSH_SET_ALIAS_SUCCESS = "推送通知设置别名成功";

    String HINT_REGISTER_IM_FAILURE = "注册即时通讯失败";
    String HINT_REGISTER_IM_SUCCESS = "注册即时通讯成功";
    String HINT_LOGIN_IM_FAILURE = "登录即时通讯失败";
    String HINT_LOGIN_IM_SUCCESS = "登录即时通讯成功";
    String HINT_IM_GET_USER_INFO_SUCCESS = "即时通讯获取用户信息成功";
    String HINT_IM_GET_USER_INFO_FAILURE = "即时通讯获取用户信息失败";

    String HINT_IN_LOADING = "加载中...";
    String HINT_LOADING_DATA_SUCCESS = "加载数据成功";
    String HINT_LOADING_DATA_FAILURE = "加载数据失败";

    String HINT_SUBMIT_MAP_LOCATION_SUCCESS="提交坐标位置成功";
    String HINT_SUBMIT_MAP_LOCATION_FAILURE="提交坐标位置失败";

    String HINT_CANCEL_SIGN_APPLICATION_SUCCESS = "取消签约申请成功";
    String HINT_CANCEL_SIGN_APPLICATION_FAILURE = "取消签约申请失败";

    String HINT_LOADING_IMAGE_SUCCESS = "加载图片成功";
    String HINT_LOADING_IMAGE_FAILURE = "加载图片失败";

    String HINT_NETWORK_DISCONNECTED = "网络已断开,请检查网络";

    String HINT_UPDATE_PHONE_NUMBER_SUCCESS = "更新电话号码成功";
    String HINT_UPDATE_PHONE_NUMBER_FAILURE = "更新电话号码失败";

    String HINT_SEND_MESSAGE_SUCCESS = "发送消息成功";
    String HINT_SEND_MESSAGE_FAILURE = "发送消息失败";
    String HINT_MESSAGE_IS_NULL = "消息为空";

    String HINT_LOCK_TREATMENT_FAILURE = "锁定诊断失败";
    String HINT_UNLOCK_TREATMENT_FAILURE = "解锁诊断失败";

    String HINT_GET_UPGRADE_INFO_SUCCESS = "获取更新信息成功";
    String HINT_GET_UPGRADE_INFO_FAILURE = "获取更新信息失败";
    String HINT_GET_UPGRADE_INFO_ALREADY_LATEST = "已经是最新版本";

    String HINT = "提示";
    String HINT_LONGITUDE_INVALID = "经度不合法";
    String HINT_LATITUDE_INVALID = "纬度不合法";
    String HINT_CONFIRM_TO_NAVIGATE = "确定导航到目的地吗?";

    String HINT_UPDATE_PHONE = "更新电话号码";
    String HINT_PLEASE_INPUT_PHONE_NUMBER = "请输入更新后的电话号码";

    String HINT_NEXT_EXECUTION_DATE = "下次执行时间:";

    String TITLE_ACTIVITY_EXECUTION_PROJECTS_LATEST_7_DAYS = "近7天需要执行项目";
    String TITLE_ACTIVITY_EXECUTION_PROJECTS_TIMEOUT = "超时未执行项目";

    String HINT_GET_MY_INFO_FAILURE = "获取我的信息失败";
    String HINT_GET_MY_INFO_EMPTY = "获取我的信息为空";

    String HINT_RESIDENT_SIGN_INFO_LIST_EMPTY = "居民签约信息列表为空";
    String HINT_SIGN_RESIDENT_INFO_EMPTY = "签约居民信息为空";

    String HINT_ADD_FAMILY_MEMBER_SUCCESS = "添加家庭成员成功";

    String YUAN = "元";
    String ZHI = "至";
    String YEARS_OLD = "岁";

    String METER = "米";
    String KILOMETER = "千米";

    String GPS_KEY = "gps";
    String START_KEY = "start";
    String END_KEY = "end";

    String BTN_SEND_TEXT = "发送";
    String HINT_PLEASE_INPUT_MSG_TO_SEND = "请输入文本以便发送";
    String HINT_MSG_LENGTH_CANNOT_EXCEED_ONE_VALUE = "消息长度不可超过" + SEND_MSG_MAX_LENGTH;

    String SCORE_FEN = "分";
    String EVALUTION = "评价";
    String HINT_PHONE_NUMBER_EMPTY = "电话号码为空";

    String HINT_SELECT_AREA = "请选择区域";
    String CONFIRM = "确定";
    String CANCEL = "取消";


    String HINT_PLEASE_INPUT_USERNAME = "请输入用户名";
    String HINT_PLEASE_INPUT_PASSWORD = "请输入密码";
    String HINT_LOGIN_FAILURE = "登录失败";
    String HINT_LOGIN_SUCCESS = "登录成功";
    String HINT_REGISTER_SUCCESS = "注册成功";
    String HINT_REGISTER_FAILURE = "注册失败";
    String HINT_ID_CARD_NUMBER_AND_PHONE_NUMBER_NOT_MATCH = "此用户身份证号和手机号不匹配";
    String HINT_RETRIEVE_PASSWORD_SUCCESS = "取回密码成功";
    String HINT_RETRIEVE_PASSWORD_FAILURE = "取回密码失败";
    String GET_SERVER_SUCCESS = "成功获取服务包数据";
    String HINT_CONFIRM_EXITING = "您确定要退出应用程序吗?";

    String RESPONSE_NULL = "响应为空";

    String PACKAGE_BASE = "基础包";
    String PACKAGE_JUNIOR = "初级包";
    String PACKAGE_INTERMEDIATE = "中级包";
    String PACKAGE_ADVANCED = "高级包";

    String HINT_WELCOME = "欢迎您,";
    String EXCLAMATION = "!";
    String HINT_YEAR_SIGN_OVERVIEW = "年度签约信息一览";
    String PERSON = "人";
    String PERCENTAGE_SIGN = "%";
    String HINT_NUMBER_OF_MY_SIGN = "我的签约人数:";
    String HINT_INTELLIGENT_HONOUR_AGREEMENT_ALERT = "智能履约提醒";
    String HINT_NUMBER_OF_PROJECTS_LATEST_7_DAYS = "近7天需要执行项目数:";
    String PERSON_CI = "人次";
    String HINT_NUMBER_OF_PROJECTS_TIMEOUT = "超时未执行项目数:";
    String YOU_HAVE = "您有";
    String HINT_NEW_SERVICE_ASSESS_INFO = "条新服务评价信息!";
    String HINT_NEW_SIGN_APPLICATION_INFO = "条新签约申请信息!";
    String HINT_APPOINTMENT_SIGN_SERVICE_INFO = "条预约签约服务信息!";

    String HINT_ADDRESS = "地址:";
    String HINT_ACCUMULATED_OUTPATIENT = "累计门诊:";
    String HINT_ACCUMULATED_INHOSPITAL = "累计住院:";
    String HINT_COLLECT_MONEY_TIME = "筹资时间:";

    public static String QQNumber = "1141771375";// 腾讯优图开发者账号（QQ号）
    public static String AppID = "10086430";
    public static String SecretID = "AKIDCXu690wASYUEm8IEzra2Toz2tlbSulgP";
    public static String SecretKey = "jIfqrphOsAGAzb1EJtMelgP0PehYDFCx";
    public static int EXPIRED_SECONDS = 2592000;// 过期时间戳

    String APIKEY = "apiKey";
    String TIMESTAMP = "timeStamp";
    String TOKEN = "token";
    String CHECK_DATA = "请输入身份证号或者姓名查询";
    String CHECKPOC_DATA = "请输入关键字查询";
}
