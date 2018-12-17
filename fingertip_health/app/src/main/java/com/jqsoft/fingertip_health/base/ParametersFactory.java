package com.jqsoft.fingertip_health.base;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.utils.EncryptUtils;
import com.jqsoft.fingertip_health.bean.DeletehxzbInVoListBeans;
import com.jqsoft.fingertip_health.bean.DiagnosisInVo;
import com.jqsoft.fingertip_health.bean.FeeDetailInVos;
import com.jqsoft.fingertip_health.bean.FeeInVos;
import com.jqsoft.fingertip_health.bean.ImageListData;
import com.jqsoft.fingertip_health.bean.OutpatientInVo;
import com.jqsoft.fingertip_health.bean.SaveItemTargetsoListBeans;
import com.jqsoft.fingertip_health.bean.SaveSignServiceContentItemListBeans;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.bean.TcmInVo;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPastHistoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPostBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonallergyHistoriesBean;
import com.jqsoft.fingertip_health.bean.parameter.ExecutionProjectsListParameters;
import com.jqsoft.fingertip_health.bean.parameter.FriendListParameters;
import com.jqsoft.fingertip_health.bean.parameter.MedicalPersonDirectoryListParameters;
import com.jqsoft.fingertip_health.bean.parameter.SendMessageParameters;
import com.jqsoft.fingertip_health.bean.parameter.TreatmentListParameters;
import com.jqsoft.fingertip_health.bean.parameter.TreatmentLockUnlockParameters;
import com.jqsoft.fingertip_health.bean.parameter.VillageLevelMedicalInstitutionDirectoryListParameters;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;
import com.jqsoft.fingertip_health.bean.request.FriendListRequestBean;
import com.jqsoft.fingertip_health.bean.request.TreatmentListRequestBean;
import com.jqsoft.fingertip_health.util.HttpParameterAnnotationProcessor;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Administrator on 2017-06-02.
 */

public class ParametersFactory {
    public static String getProcessedString(String s) {
//        String processedString = Util.getBase64String(s);
        String processedString = s;
        return processedString;
    }

    public static String getProcessedStringForFingertip(String s) {
        String processedString = Util.getBase64String(s);
//        String processedString = s;
        return processedString;
    }

    public static Map<String, String> getBaseRequestMap(Context context) {
        Map<String, String> map = new HashMap<>();
        String timestamp = Util.getTimestampLeading10CharactersString();
        map.put(Constants.API_KEY, getProcessedString(Constants.API_KEY_VALUE));
        map.put(Constants.TIME_STAMP_KEY, getProcessedString(timestamp));
        map.put(Constants.TOKEN_KEY, getProcessedString(Util.getEncryptedDigest(timestamp)));

//        String time =Util.getCurrentTime();
//        String smd5=time+"api_key_android"+"34@fr*53jg2!";
//        String token=Util.getmd5(smd5);
//        map.put(Constants.APIKEY, "api_key_android");
//        map.put(Constants.TIMESTAMP, time);
//        map.put(Constants.TOKEN, token);

//        String loginName = IdentityManager.getLoginSuccessUsername(context);
//        String encodedLoginName = Util.getBase64String(loginName);
//        String encodedSign = Util.getBase64String(Version.SIGN);
//        map.put(Constants.SIGN_KEY, encodedSign);
//        map.put(Constants.LOGIN_USERNAME_KEY, encodedLoginName);
        return map;
    }

    public static Map<String, String> getBaseRequestMapLoginMethodForFingertip(Context context, String timestamp) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.TIMESTAMP_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(timestamp));
        map.put(Constants.DEVICE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(Util.getLoginMethodDeviceString()));

        return map;
    }

    public static Map<String, String> getBaseRequestMapNonloginMethodForFingertip(Context context, String timestamp) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.TIMESTAMP_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(timestamp));
        map.put(Constants.DEVICE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(Util.getNonloginMethodDeviceString(context)));
//        map.put(Constants.TOKEN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(Util.getNonloginMethodTokenString(context)));

        return map;
    }

    public static Map<String, String> getSubsistenceVarianceRankingStatisticsListMap(Context context, String subsistenceType, String resultType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.RESULT_TYPE_KEY, getProcessedString(resultType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceArchiveRankingStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getInstitutionRankingStatisticsListMap(Context context, String institutionType, String legalPersonType, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.GOV_TYPE_KEY, getProcessedString(institutionType));
        result.put(Constants.LAWER_TYPE_KEY, getProcessedString(legalPersonType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceAccountRankingStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceStandardRankingStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceStandardAverageRankingStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }


    public static Map<String, String> getSubsistenceApproveRankingStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceApproveTrendStatisticsListMap(Context context, String subsistenceType, String supportType, String year, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATISTICS_YEAR_KEY, getProcessedString(year));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceVarianceTrendStatisticsListMap(Context context, String subsistenceType, String resultType, String supportType, String year, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.RESULT_TYPE_KEY, getProcessedString(resultType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATISTICS_YEAR_KEY, getProcessedString(year));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceArchiveTrendStatisticsListMap(Context context, String subsistenceType, String supportType, String year, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATISTICS_YEAR_KEY, getProcessedString(year));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceAccountTrendStatisticsListMap(Context context, String subsistenceType, String supportType, String year, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATISTICS_YEAR_KEY, getProcessedString(year));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceAccountIncreaseRatioStatisticsListMap(Context context, String subsistenceType, String supportType, String year, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATISTICS_YEAR_KEY, getProcessedString(year));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceApprovePovertyReasonStatisticsListMap(Context context, String subsistenceType, String supportType, String statsType, String yearMonth, String isAreaOwner, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.STATS_TYPE_KEY, getProcessedString(statsType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.IS_AREA_OWNER_KEY, getProcessedString(isAreaOwner));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }

    public static Map<String, String> getSubsistenceArchiveHealthClassificationStatisticsListMap(Context context, String subsistenceType, String yearMonth, String supportType, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }

    public static Map<String, String> getInstitutionCharacterClassificationStatisticsListMap(Context context, String institutionType, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.GOV_TYPE_KEY, getProcessedString(institutionType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }

    public static Map<String, String> getInstitutionServerClassificationStatisticsListMap(Context context, String institutionType,
                                                                                          String personType, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.GOV_TYPE_KEY, getProcessedString(institutionType));
        result.put(Constants.PERSON_TYPE_KEY, getProcessedString(personType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getInstitutionLegalPersonClassificationStatisticsListMap(Context context, String lawerType,
                                                                                               String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.LAWER_TYPE_KEY, getProcessedString(lawerType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getSubsistenceArchiveAbilityClassificationStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }

    public static Map<String, String> getSubsistenceArchiveAgeClassificationStatisticsListMap(Context context, String subsistenceType, String supportType, String yearMonth, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(subsistenceType));
        result.put(Constants.SUB_TYPE_KEY, getProcessedString(supportType));
        result.put(Constants.YEAR_MONTH_KEY, getProcessedString(yearMonth));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }

    public static Map<String, String> getMedicalAssistantMoneyConstitutionStatisticsListMap(Context context, String type, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.STATISTICS_TYPE_KEY, getProcessedString(type));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getTempDisasterAssistancePercentageStatisticsListMap(Context context, String itemType, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(itemType));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getMedicalAssistantDirectOutcomeStatisticsListMap(Context context, String type, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.STATISTICS_TYPE_KEY, getProcessedString(type));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getMedicalAssistantFinanceAssuranceStatisticsListMap(Context context, String type, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.STATISTICS_TYPE_KEY, getProcessedString(type));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getTempDisasterAssistantStatisticsListMap(Context context, String itemType, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(itemType));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getFamilyEconomyCheckRankingStatisticsListMap(Context context, String itemType, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(itemType));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getFamilyEconomyCheckTrendStatisticsListMap(Context context, String itemType, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ITEM_TYPE_KEY, getProcessedString(itemType));
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getFamilyEconomyCheckShareIndexStatisticsListMap(Context context, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getProjectCheckStatisticsListMap(Context context, String startDate, String endDate, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.START_DATE_KEY, getProcessedString(startDate));
        result.put(Constants.END_DATE_KEY, getProcessedString(endDate));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }


    public static Map<String, String> getNotificationListMap(Context context, String code, String name, String pageIndex, String pageSize, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.CODE_KEY, getProcessedString(code));
        result.put(Constants.POLICY_NOTIFICATION_USER_NAME_KEY, getProcessedString(name));
        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAPolicyListMap(Context context, String code, String name, String pageIndex, String pageSize, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.CODE_KEY, getProcessedString(code));
        result.put(Constants.POLICY_NOTIFICATION_USER_NAME_KEY, getProcessedString(name));
        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getAmbientPersonListMap(Context context, String dataType, String centerLong, String centerLat,
                                                              String radius, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.LONGITUDE_KEY, getProcessedString(centerLong));
        result.put(Constants.LATITUDE_KEY, getProcessedString(centerLat));
        result.put(Constants.RADIUS_KEY, getProcessedString(radius));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getDrawToSearchPersonListMap(Context context, String dataType, String centerLong, String centerLat,
                                                                   String radius, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.LONGITUDE_KEY, getProcessedString(centerLong));
        result.put(Constants.LATITUDE_KEY, getProcessedString(centerLat));
        result.put(Constants.RADIUS_KEY, getProcessedString(radius));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getRegionPersonListMap(Context context, String dataType,
                                                             String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getCategorySearchPersonListMap(Context context, String dataType,
                                                                     String familyType, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.FAMILY_TYPE_KEY, getProcessedString(familyType));
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getKeywordSearchPersonListMap(Context context, String dataType,
                                                                    String keyword, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.KEYWORD_KEY_NAME, getProcessedString(keyword));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getHeatmapBeanMap(Context context, String dataType,
                                                        String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.DATA_TYPE_KEY, getProcessedString(dataType));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    //  public static Map<String, String> getGCAPolityListMap(Context context,String username, String unitCode, String method){

    public static Map<String, String> getGCAUrbanLowListMap(Context context, String code, String pageIndex, String pageSize, String method, String areaid, String myItemId, String arearLevel) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.METHOD_KEY, "appBatch.queryApplyDraftListSri");
        result.put("itemCode", myItemId);
        result.put("state", "");
        result.put("areaCode", areaid);
        result.put("areaLevel", arearLevel);
        return result;
    }


    public static Map<String, String> getUrbanLowFamilyMap(Context context, String batchNo) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appFamilyMember.queryFamilyMemberListSri");
        //  result.put("batchNo", batchNo);
        result.put("batchNo", batchNo);

        return result;
    }

    public static Map<String, String> getGCAPolityListMap(Context context, String username, String unitCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAPolitics(Context context, String username, String unitCode, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.AREACOD_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCARection(Context context, String username, String areacode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.AREACOD_KEY, getProcessedString(areacode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAReceptionDetailNewListMap(Context context, String newsId, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.NEWSID_KEY, getProcessedString(newsId));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAPersonCollectionMap(Context context, String username, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAPersonInfoMap(Context context, String username, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCASavePersonInfoMap(Context context, String username, String realname, String mobiephone, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.REAL_NAME_KEY, getProcessedString(realname));
        result.put(Constants.MOBIE_PHONE_KEY, getProcessedString(mobiephone));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAReceptionMap(Context context, String areaCode, String jgType, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.AREACOD_KEY, getProcessedString(areaCode));
        result.put(Constants.jgType_KEY, getProcessedString(jgType));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAQuestionMap1(Context context, String pageIndex, String pageSize, String method) {
        Map<String, String> result = getBaseRequestMap(context);
//        result.put(Constants.QUSTYPE_KEY, getProcessedString(type));
        result.put("pageSize", getProcessedString(pageSize));
//        result.put("ids", getProcessedString(ids));
        result.put("pageIndex", getProcessedString(pageIndex));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAQuestionMap(Context context, String ids, String pageIndex, String pageSize, String method) {
        Map<String, String> result = getBaseRequestMap(context);
//        result.put(Constants.QUSTYPE_KEY, getProcessedString(type));
        result.put("pageSize", getProcessedString(pageSize));
        result.put("ids", getProcessedString(ids));
        result.put("pageIndex", getProcessedString(pageIndex));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAQuestiontypeMap(Context context, String ids, String pageIndex, String pageSize, String type, String method) {
        Map<String, String> result = getBaseRequestMap(context);
//        result.put(Constants.QUSTYPE_KEY, getProcessedString(type));
        result.put("type", getProcessedString(type));
        result.put("ids", getProcessedString(ids));
        result.put("pageIndex", getProcessedString(pageIndex));
        result.put("pageSize", getProcessedString(pageSize));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAQuestiontypeMap1(Context context, String pageIndex, String pageSize, String type, String method) {
        Map<String, String> result = getBaseRequestMap(context);
//        result.put(Constants.QUSTYPE_KEY, getProcessedString(type));
        result.put("type", getProcessedString(type));
//        result.put("ids", getProcessedString(ids));
        result.put("pageIndex", getProcessedString(pageIndex));
        result.put("pageSize", getProcessedString(pageSize));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAqueryNewsOrPoliceListMap(Context context, String code,
                                                                     String pageSize, String userName, String pageIndex, String title, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.CODE_KEY, getProcessedString(code));
        result.put("title", getProcessedString(title));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCASeachQuestionMap(Context context, String keyWords, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.keyWords_KEY, getProcessedString(keyWords));
//        result.put(Constants.QUSTYPE_KEY, getProcessedString(type));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAQuaAdviceMap(Context context, String keyWords, String conNo, String username, String isreply, String method, String pageIndex, String pageSize) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("keyWords", getProcessedString(keyWords));
        result.put("conNo", getProcessedString(conNo));
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.ISREPLY_KEY, getProcessedString(isreply));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAAdviceMap(Context context, String username, String isreply, String method, String pageIndex, String pageSize) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.PAGE_SIZE_KEY, getProcessedString(pageSize));
        result.put(Constants.PAGE_INDEX_KEY, getProcessedString(pageIndex));
        result.put(Constants.ISREPLY_KEY, getProcessedString(isreply));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAMyMessageMap(Context context, String username, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }


    public static Map<String, String> getGCAAboutInfoMap(Context context, String method) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAChangePasswordMap(Context context, String userName, String oldPassword
            , String newPassword, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.NEW_PASSWORD_KEY, getProcessedString(newPassword));
        result.put(Constants.OLD_PASSWORD_KEY, getProcessedString(oldPassword));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }


    public static Map<String, String> getGCAReceptionListMap(Context context, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.AREACOD_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAGuideListMap(Context context, String username, String areaCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.AREACODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAReliefInstListMap(Context context, String username, String areaCode, String itemId, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.ITEMID_KEY, getProcessedString(itemId));
        result.put(Constants.AREACODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAReliefInstListMapFormCollection(Context context, String username, String areaCode, String Collection, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(username));
        result.put(Constants.COLLECTION_KEY, getProcessedString(Collection));
        result.put(Constants.AREACODE_KEY, getProcessedString(areaCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAMyMessageDetail(Context context, String mailId, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.MAILID_KEY, getProcessedString(mailId));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAQuestionDetail(Context context, String id, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.ID_KEY, getProcessedString(id));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAAdviceDetail(Context context, String conNo, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.CONNO_KEY, getProcessedString(conNo));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAAdviceRemoe(Context context, String turnUnit,
                                                        String unitCode, String conNo,
                                                        String userName, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("turnUnit", getProcessedString(turnUnit));
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.CONNO_KEY, getProcessedString(conNo));

        return result;
    }

    public static Map<String, String> getGCAAdvicereply(Context context, String conNo, String content,
                                                        String userName, String unitCode, String method, String openId) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("openId", getProcessedString(openId));
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.content_KEY, getProcessedString(content));
        result.put(Constants.CONNO_KEY, getProcessedString(conNo));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAAdviceconsultAgain(Context context, String pconNo, String content,
                                                               String userName, String unitCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.content_KEY, getProcessedString(content));
        result.put("pconNo", getProcessedString(pconNo));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAAdviceturnConsult(Context context, String conNo, String turnUnit,
                                                              String userName, String unitCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.UNITCOD_KEY, getProcessedString(unitCode));
        result.put(Constants.USER_NAME_KEY, getProcessedString(userName));
        result.put(Constants.CONNO_KEY, getProcessedString(conNo));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put("turnUnit", getProcessedString(turnUnit));

        return result;
    }

    public static Map<String, String> getGCAAdviceremove(Context context, String conNo, String content,
                                                         String userName, String unitCode, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.CONNO_KEY, getProcessedString(conNo));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAReliefcollectionMap(Context context, String usrname, String collectionUrl, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, getProcessedString(usrname));
        result.put(Constants.COLLECTION_KEY, getProcessedString(collectionUrl));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAReliefremovecollectionMap(Context context, String collectioId, String method) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.COLLECTIONID_KEY, getProcessedString(collectioId));
        result.put(Constants.METHOD_KEY, getProcessedString(method));


        return result;
    }

    public static Map<String, String> getGCAReceptionDetailMap(Context context, String username, String method, String recepId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, username);
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put(Constants.RECEPID_KEY, getProcessedString(recepId));


        return result;
    }

    public static Map<String, String> getGCAReceptionDetailMapforconcetion(Context context, String username, String method, String collectionUrl) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, username);
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put(Constants.COLLECTION_KEY, getProcessedString(collectionUrl));


        return result;
    }

    public static Map<String, String> getGCAReceptionDetailCollectionMap(Context context, String username, String method, String collectionUrl) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.USER_NAME_KEY, username);
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put(Constants.COLLECTION_KEY, getProcessedString(collectionUrl));

        return result;
    }

    public static Map<String, String> getGCAIgGuideMap(Context context, String areacode, String method) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put(Constants.AREA_CODE_KEY, getProcessedString(areacode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        return result;
    }

    public static Map<String, String> getGCAIgPostMap(Context context, String areacode, String method, String qaIds) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put(Constants.AREA_CODE_KEY, getProcessedString(areacode));
        result.put(Constants.METHOD_KEY, getProcessedString(method));
        result.put(Constants.QAIDS_KEY, getProcessedString(qaIds));


        return result;
    }


    public static Map<String, String> getGCAReliefcollectMap(Context context, String method, String result1) {
        Map<String, String> result = getBaseRequestMap(context);
        String bodyJson = JSON.toJSONString(result1);
        result.put(Constants.COLLECTION_KEY, getProcessedString(bodyJson));
        result.put(Constants.METHOD_KEY, getProcessedString(method));

        return result;
    }


    public static Map<String, String> getDoctorTeamDataMap(Context context, String cardNo) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedCardNo = Util.getBase64String(cardNo);
        result.put(Constants.CARD_NO_KEY, encodedCardNo);
        return result;
    }

    public static Map<String, String> getRemindDataMapForFamilyMember(Context context, String cardNo, String personID) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedCardNo = Util.getBase64String(cardNo);
        String encodedPersonID = Util.getBase64String(personID);
        result.put(Constants.CARD_NO_KEY, encodedCardNo);
        result.put(Constants.PERSON_ID_KEY, encodedPersonID);
        return result;
    }

    public static Map<String, String> getRemindDataMap(Context context, String cardNo, String personID) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedCardNo = Util.getBase64String(cardNo);
//        String personID = Identity.getPersonID();
        String encodedPersonID = Util.getBase64String(personID);
        result.put(Constants.CARD_NO_KEY, encodedCardNo);
        result.put(Constants.PERSON_ID_KEY, encodedPersonID);
        return result;
    }

    public static Map<String, String> getPersonMessage(Context context, int page, int size, String flag, String docUserID, String personID) {
        Map<String, String> result = getBaseRequestMap(context);
//        String personID = Identity.getPersonID();
        String encodedPersonID = Util.getBase64String(personID);//"341222_110804018490"
        String encodedstartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedendIndex = Util.getBase64String(String.valueOf(page * size));
        String encodedflag = Util.getBase64String(flag);
        String encodedocUserID = Util.getBase64String(docUserID);
        result.put("personID", encodedPersonID);
        result.put("startIndex", encodedstartIndex);
        result.put("endIndex", encodedendIndex);
        result.put("flag", encodedflag);
        result.put("docUserID", encodedocUserID);
        return result;
    }

    public static Map<String, String> getPersonDoctorMessage(Context context, String personID) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedpersonID = Util.getBase64String(personID);
        result.put("personID", encodedpersonID);
        return result;
    }

    public static Map<String, String> postMessage(Context context, String postMessage, String sender, String recipient, String residientCardNo,
                                                  String senderName) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedpostMessage = Util.getBase64String(postMessage);
        String encodedsender = Util.getBase64String(sender);
        String encodedrecipient = Util.getBase64String(recipient);
        String encodedResidentCardNo = Util.getBase64String(residientCardNo);
        String encodedSenderName = Util.getBase64String(senderName);
        result.put("postMessage", encodedpostMessage);
        result.put("sender", encodedsender);
        result.put("recipient", encodedrecipient);

        result.put(Constants.CARD_NO_KEY, encodedResidentCardNo);
        result.put("senderName", encodedSenderName);
        return result;
    }

    public static Map<String, String> updateOnlineConsultation(Context context, String sender, String recipient) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedsender = Util.getBase64String(sender);
        String encodedrecipient = Util.getBase64String(recipient);
        result.put("recipient", encodedrecipient);
        result.put("sender", encodedsender);
        return result;
    }

    public static Map<String, String> savePersonSignApply(Context context,
                                                          String cardNo,
                                                          String personID,
                                                          String sexName,
                                                          String no,
                                                          String personName,
                                                          String phone,
                                                          String serverPackageName,
                                                          String packID,
                                                          String address,
                                                          String areaCode,
                                                          String applyDoctor,
                                                          String applyDoctorName,
                                                          String age,
                                                          String personMold) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedCardNo = Util.getBase64String(cardNo);
        String encodedpersonID = Util.getBase64String(personID);
        String encodesexName = Util.getBase64String(sexName);
        String encodedno = Util.getBase64String(no);
        String encodedpersonName = Util.getBase64String(personName);
        String encodedphone = Util.getBase64String(phone);
        String encodedserverPackageName = Util.getBase64String(serverPackageName);
        String encodedpackID = Util.getBase64String(packID);
        String encodedaddress = Util.getBase64String(address);
        String encodedareaCode = Util.getBase64String(areaCode);
        String encodedapplyDoctor = Util.getBase64String(applyDoctor);
        String encodedapplyDoctorName = Util.getBase64String(applyDoctorName);
        String encodedage = Util.getBase64String(age);
        String encodedpersonMold = Util.getBase64String(personMold);
        result.put("cardNo", encodedCardNo);
        result.put("personID", encodedpersonID);
        result.put("sexName", encodesexName);
        result.put("no", encodedno);
        result.put("personName", encodedpersonName);
        result.put("phone", encodedphone);
        result.put("serverPackageName", encodedserverPackageName);
        result.put("packID", encodedpackID);
        result.put("address", encodedaddress);
        result.put("areaCode", encodedareaCode);
        result.put("applyDoctor", encodedapplyDoctor);
        result.put("applyDoctorName", encodedapplyDoctorName);
        result.put("age", encodedage);
        result.put("personMold", encodedpersonMold);
        return result;
    }

    public static Map<String, String> getFamilyMemberDataMap(Context context, String id, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
//        String id = Identity.getId();
        String encodedId = Util.getBase64String(id);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put(Constants.USER_ID_KEY, encodedId);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        return result;
    }

    public static Map<String, String> getPolicyDataMap(Context context, String year, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedYear = Util.getBase64String(year);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put(Constants.YEAR_KEY_NAME, encodedYear);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        return result;
    }


    public static Map<String, String> getAddFamilyMemberMapFromParameters(Context context, String userId, String memberCardNo,
                                                                          String memberName, String relationship) {
        Map<String, String> result = getBaseRequestMap(context);
//        String userId = Identity.getId();
        String encodedUserId = Util.getBase64String(userId);
        String encodedMemberCardNumber = Util.getBase64String(memberCardNo);
        String encodedMemberName = Util.getBase64String(memberName);
        String encodedRelationship = Util.getBase64String(relationship);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.MEMBER_CARD_NUMBER_KEY, encodedMemberCardNumber);
        result.put(Constants.MEMBER_NAME_KEY, encodedMemberName);
        result.put(Constants.RELATIONSHIP_KEY, encodedRelationship);
        return result;
    }

    public static Map<String, String> getRetrievePasswordMapFromParameters(Context context, String idCardNumber,
                                                                           String phone, String newPassword) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedIdCardNumber = Util.getBase64String(idCardNumber);
        String encodedPhone = Util.getBase64String(phone);
        String encodedNewPassword = Util.getBase64String(newPassword);
        result.put(Constants.LOGIN_USERNAME_KEY, encodedIdCardNumber);
        result.put(Constants.PHONE_KEY, encodedPhone);
        result.put(Constants.PASSWORD_KEY, encodedNewPassword);
        return result;
    }

    public static Map<String, String> getCheckUserExistMapFromParameters(Context context, String idCardNumber,
                                                                         String phone) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedIdCardNumber = Util.getBase64String(idCardNumber);
        String encodedPhone = Util.getBase64String(phone);
        result.put(Constants.LOGIN_USERNAME_KEY, encodedIdCardNumber);
        result.put(Constants.PHONE_KEY, encodedPhone);
        return result;
    }

    public static Map<String, String> getRegisterMapFromParameters(Context context, String encodedHeadImageString, String alias, String idCardNumber,
                                                                   String phone, String password) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedHeadString = encodedHeadImageString;
        String encodedAlias = Util.getBase64String(alias);
        String encodedIdCardNumber = Util.getBase64String(idCardNumber);
        String encodedPhone = Util.getBase64String(phone);
        String encodedPassword = Util.getBase64String(password);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.LOGIN_USERNAME_KEY, encodedIdCardNumber);
        result.put(Constants.LOGIN_PASSWORD_KEY, encodedPassword);
        result.put(Constants.SIGN_KEY, encodedSign);
        result.put(Constants.ALIAS_KEY, encodedAlias);
        result.put(Constants.PHONE_KEY, encodedPhone);
        result.put(Constants.PHOTO_KEY, encodedHeadString);
        result.put(Constants.CARD_NO_KEY, encodedIdCardNumber);
        return result;
    }

    public static Map<String, String> getLoginMapFromUsernameAndPassword(Context context, String username, String password) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "protal.login");
        result.put("userName", username);
        result.put("password", password);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLoginMapFromUsernameAndPasswordForFingertip(Context context, String username, String password) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getLoginBodyStringForFingertip(username, password);
        String sign = Util.getLoginMethodSignString(body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("A01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String postDMStringForFingertip(
            String taskId,
            String id,
            String no,
            String flwDate,
            String flwType,
            String followDownReason,
            String symptom,
            String otherSymptom,
            String sbp,
            String dbp,
            String heartRate,
            String height,
            String weight,
            String weightTarget,
            String bmi,
            String bmiTarget,
            String otherSign,
            String dorsalisPedisL,
            String dorsalisPedisR,
            String dailySmoke,
            String dailySmokeTarget,
            String dailyDrink,
            String dailyDrinkTarget,
            String exercise,
            String exerciseTimes,
            String nextExercise,
            String nextExerciseTimes,
            String staple,
            String stapleAim,
            String psychic,
            String patientCompliance,
            String fpg,
            String pbg,
            String accessoryExaminationHba1c,
            String accessoryExaminationDate,
            String drugCompliance,
            String adverseReaction,
            String lowSugarReaction,
            String sort,
            String insulinType,
            String nextFlwDate,
            String doctorCode,
            String name,
            String frequency,
            String singleDose,
            String unit,
            String isReferral,
            String referralReason,
            String referralHosp,
            String otherAdverseReaction,
            List<UseDrugInfo> useDrugInfoList,
            List<UseDrugInfo> ydsDrugInfoList,
            String personid, String doctor, String idno


    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("name", name);
        map.put("id", "");
        map.put("no", no);
        map.put("flwDate", flwDate);
        if (TextUtils.isEmpty(flwType)){
            map.put("flwType",flwType);
        }else {
            map.put("flwType",  Integer.valueOf(flwType));
        }


        map.put("followDownReason", followDownReason);
        map.put("symptom", symptom);
        map.put("otherSymptom", otherSymptom);
        if (TextUtils.isEmpty(sbp)){
            map.put("sbp",sbp);
        }else {
            map.put("sbp",  Integer.valueOf(sbp));
        }
        if (TextUtils.isEmpty(dbp)){
            map.put("dbp",dbp);
        }else {
            map.put("dbp",  Integer.valueOf(dbp));
        }

        if (TextUtils.isEmpty(heartRate)){
            map.put("heartRate",heartRate);
        }else {
            map.put("heartRate",  Integer.valueOf(heartRate));
        }

        if (TextUtils.isEmpty(height)){
            map.put("height",height);
        }else {
            map.put("height",  Double.valueOf(height));
        }
        if (TextUtils.isEmpty(weight)){
            map.put("weight",weight);
        }else {
            map.put("weight",  Double.valueOf(weight));
        }
        if (TextUtils.isEmpty(weightTarget)){
            map.put("weightTarget",weightTarget);
        }else {
            map.put("weightTarget",  Double.valueOf(weightTarget));
        }
        if (TextUtils.isEmpty(bmi)){
            map.put("bmi",bmi);
        }else {
            map.put("bmi",  Double.valueOf(bmi));
        }
        if (TextUtils.isEmpty(bmiTarget)){
            map.put("bmiTarget",bmiTarget);
        }else {
            map.put("bmiTarget",  Double.valueOf(bmiTarget));
        }
        map.put("otherSign", otherSign);

        if (TextUtils.isEmpty(dorsalisPedisL)){
            map.put("dorsalisPedisL",dorsalisPedisL);
        }else {
            map.put("dorsalisPedisL",  Double.valueOf(dorsalisPedisL));
        }
        if (TextUtils.isEmpty(dorsalisPedisR)){
            map.put("dorsalisPedisR",dorsalisPedisR);
        }else {
            map.put("dorsalisPedisR",  Double.valueOf(dorsalisPedisR));
        }
        if (TextUtils.isEmpty(dailySmoke)){
            map.put("dailySmoke",dailySmoke);
        }else {
            map.put("dailySmoke",  Integer.valueOf(dailySmoke));
        }
        if (TextUtils.isEmpty(dailySmokeTarget)){
            map.put("dailySmokeTarget",dailySmokeTarget);
        }else {
            map.put("dailySmokeTarget",  Integer.valueOf(dailySmokeTarget));
        }
        if (TextUtils.isEmpty(dailyDrink)){
            map.put("dailyDrink",dailyDrink);
        }else {
            map.put("dailyDrink",  Double.valueOf(dailyDrink));
        }
        if (TextUtils.isEmpty(dailyDrinkTarget)){
            map.put("dailyDrinkTarget",dailyDrinkTarget);
        }else {
            map.put("dailyDrinkTarget",  Double.valueOf(dailyDrinkTarget));
        }
        if (TextUtils.isEmpty(exercise)){
            map.put("exercise",exercise);
        }else {
            map.put("exercise",  Double.valueOf(exercise));
        }
        if (TextUtils.isEmpty(exerciseTimes)){
            map.put("exerciseTimes",exerciseTimes);
        }else {
            map.put("exerciseTimes",  Integer.valueOf(exerciseTimes));
        }
        if (TextUtils.isEmpty(nextExercise)){
            map.put("nextExercise",nextExercise);
        }else {
            map.put("nextExercise",  Double.valueOf(nextExercise));
        }
        if (TextUtils.isEmpty(nextExerciseTimes)){
            map.put("nextExerciseTimes",nextExerciseTimes);
        }else {
            map.put("nextExerciseTimes",  Integer.valueOf(nextExerciseTimes));
        }

        if (TextUtils.isEmpty(staple)){
            map.put("staple",staple);
        }else {
            map.put("staple",  Double.valueOf(staple));
        }
        if (TextUtils.isEmpty(stapleAim)){
            map.put("stapleAim",stapleAim);
        }else {
            map.put("stapleAim",  Double.valueOf(stapleAim));
        }
        if (TextUtils.isEmpty(psychic)){
            map.put("psychic",psychic);
        }else {
            map.put("psychic",  Double.valueOf(psychic));
        }

        if (TextUtils.isEmpty(patientCompliance)){
            map.put("patientCompliance",patientCompliance);
        }else {
            map.put("patientCompliance",  Double.valueOf(patientCompliance));
        }
        if (TextUtils.isEmpty(fpg)){
            map.put("fpg",fpg);
        }else {
            map.put("fpg",  Double.valueOf(fpg));
        }

        if (TextUtils.isEmpty(pbg)){
            map.put("pbg",pbg);
        }else {
            map.put("pbg",  Double.valueOf(pbg));
        }

        if (TextUtils.isEmpty(accessoryExaminationHba1c)){
            map.put("accessoryExaminationHba1c",accessoryExaminationHba1c);
        }else {
            map.put("accessoryExaminationHba1c",  Double.valueOf(accessoryExaminationHba1c));
        }

        map.put("accessoryExaminationDate", accessoryExaminationDate);

        if (TextUtils.isEmpty(drugCompliance)){
            map.put("drugCompliance",drugCompliance);
        }else {
            map.put("drugCompliance",  Integer.valueOf(drugCompliance));
        }
        if (TextUtils.isEmpty(drugCompliance)){
            map.put("adverseReaction",adverseReaction);
        }else {
            map.put("adverseReaction",  Integer.valueOf(adverseReaction));
        }
        if (TextUtils.isEmpty(lowSugarReaction)){
            map.put("lowSugarReaction",lowSugarReaction);
        }else {
            map.put("lowSugarReaction",  Integer.valueOf(lowSugarReaction));
        }
        if (TextUtils.isEmpty(sort)){
            map.put("sort",sort);
        }else {
            map.put("sort",  Integer.valueOf(sort));
        }

        map.put("insulinType", insulinType);
        map.put("nextFlwDate", nextFlwDate);
        map.put("doctorCode", doctorCode);
        map.put("name", name);
        map.put("frequency", frequency);
        map.put("singleDose", singleDose);
        map.put("unit", unit);
        if (TextUtils.isEmpty(isReferral)){
            map.put("isReferral",isReferral);
        }else {
            map.put("isReferral",  Integer.valueOf(isReferral));
        }

        map.put("referralReason", referralReason);
        map.put("referralHosp", referralHosp);
        map.put("otherAdverseReaction", otherAdverseReaction);
        map.put("personId", personid);
        map.put("doctor", doctor);
        map.put("idNo", idno);

//0:普通药剂 1：胰岛素药剂
        List<Map<String, Object>> PhMedicine = new ArrayList<>();
        for (int i = 0; i < useDrugInfoList.size(); i++) {
            PhMedicine.add(getDmDrugStringForFingertip(useDrugInfoList.get(i), 0));
        }

        for (int i = 0; i < ydsDrugInfoList.size(); i++) {
            PhMedicine.add(getDmDrugStringForFingertip(ydsDrugInfoList.get(i), 1));
        }
        map.put("PhMedicine", PhMedicine);

        String result = JSON.toJSONString(map);
        return result;
    }

    public static String postHighBodyStringForFingertip(
            String taskId,
            String name,
            String id,
            String no,
            String flwDate,
            String flwType,
            String followDownReason,
            String symptom,
            String otherSymptom,
            String sbp,
            String dbp,
            String heartRate,
            String height,
            String weight,
            String weightTarget,
            String bmi,
            String bmiTarget,
            String otherSign,
            String dailySmoke,
            String psychic,
            String patientCompliance,
            String accessoryExamination,
            String drugCompliance,
            String adverseReaction,
            String otherAdverseReaction,
            String sort,
            String nextFlwDate,
            String doctorCode,
            List<UseDrugInfo> useDrugInfoList,
            String exercise, String exerciseTimes, String nextExercise, String nextExerciseTimes,
            String isReferral, String referralReason, String referralHosp
            , String dailySmokeTarget, String dailyDrink, String dailyDrinkTarget, String saltUptake, String saltUptakeTarget, String pp, String personid, String doctor, String idnum


    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("name", name);
        map.put("id", "");
        map.put("no", no);
        map.put("flwDate", flwDate);
        map.put("flwType", flwType);
        map.put("followDownReason", followDownReason);
        map.put("symptom", symptom);
        map.put("otherSymptom", otherSymptom);
        if (TextUtils.isEmpty(sbp)){
            map.put("sbp",sbp);
        }else {
            map.put("sbp", Double.valueOf(sbp));
        }
        if (TextUtils.isEmpty(dbp)){
            map.put("dbp", dbp);
        }else {
            map.put("dbp", Double.valueOf(dbp));
        }
        if (TextUtils.isEmpty(heartRate)){
            map.put("heartRate", heartRate);
        }else {
            map.put("heartRate", Double.valueOf(heartRate));
        }
        if (TextUtils.isEmpty(height)){
            map.put("height", height);
        }else {
            map.put("height", Double.valueOf(height));
        }
        if (TextUtils.isEmpty(weight)){
            map.put("weight", weight);
        }else {
            map.put("weight", Double.valueOf(weight));
        }
        if (TextUtils.isEmpty(weightTarget)){
            map.put("weightTarget", weightTarget);
        }else {
            map.put("weightTarget", Double.valueOf(weightTarget));
        }
        if (TextUtils.isEmpty(bmi)){
            map.put("bmi", bmi);
        }else {
            map.put("bmi", Double.valueOf(bmi));
        }
        if (TextUtils.isEmpty(bmiTarget)){
            map.put("bmiTarget", bmiTarget);
        }else {
            map.put("bmiTarget", Double.valueOf(bmiTarget));
        }
        map.put("otherSign", otherSign);

        if (TextUtils.isEmpty(dailySmoke)){
            map.put("dailySmoke", dailySmoke);
        }else {
            map.put("dailySmoke", Double.valueOf(dailySmoke));
        }

        if (TextUtils.isEmpty(psychic)){
            map.put("psychic", psychic);
        }else {
            map.put("psychic", Integer.valueOf(psychic));
        }
        map.put("accessoryExamination", accessoryExamination);

        if (TextUtils.isEmpty(drugCompliance)){
            map.put("drugCompliance", drugCompliance);
        }else {
            map.put("drugCompliance", Integer.valueOf(drugCompliance));
        }
        if (TextUtils.isEmpty(adverseReaction)){
            map.put("adverseReaction", adverseReaction);
        }else {
            map.put("adverseReaction", Integer.valueOf(adverseReaction));
        }
        map.put("otherAdverseReaction", otherAdverseReaction);

        if (TextUtils.isEmpty(sort)){
            map.put("sort", sort);
        }else {
            map.put("sort", Integer.valueOf(sort));
        }
        map.put("nextFlwDate", nextFlwDate);
        map.put("doctorCode", doctorCode);
        map.put("exercise", exercise);
        map.put("exerciseTimes", exerciseTimes);
        map.put("nextExercise", nextExercise);
        map.put("nextExerciseTimes", nextExerciseTimes);
        map.put("isReferral", isReferral);
        map.put("referralReason", referralReason);
        map.put("referralHosp", referralHosp);


        if (TextUtils.isEmpty(dailySmokeTarget)){
            map.put("dailySmokeTarget", dailySmokeTarget);
        }else {
            map.put("dailySmokeTarget", Integer.valueOf(dailySmokeTarget));
        }
        if (TextUtils.isEmpty(dailyDrink)){
            map.put("dailyDrink", dailyDrink);
        }else {
            map.put("dailyDrink", Double.valueOf(dailyDrink));
        }

        if (TextUtils.isEmpty(dailyDrinkTarget)){
            map.put("dailyDrinkTarget", dailyDrinkTarget);
        }else {
            map.put("dailyDrinkTarget", Double.valueOf(dailyDrinkTarget));
        }

        if (TextUtils.isEmpty(saltUptake)){
            map.put("saltUptake", saltUptake);
        }else {
            map.put("saltUptake", Integer.valueOf(saltUptake));
        }
        if (TextUtils.isEmpty(saltUptakeTarget)){
            map.put("saltUptakeTarget", saltUptakeTarget);
        }else {
            map.put("saltUptakeTarget", Integer.valueOf(saltUptake));
        }

        if (TextUtils.isEmpty(pp)){
            map.put("patientCompliance", pp);
        }else {
            map.put("patientCompliance", Integer.valueOf(pp));
        }

        map.put("personId", personid);
        map.put("doctor", doctor);
        map.put("idNo", idnum);
        List<Map<String, Object>> PhMedicine = new ArrayList<>();
        for (int i = 0; i < useDrugInfoList.size(); i++) {
            PhMedicine.add(getUesDrugStringForFingertip(useDrugInfoList.get(i)));
        }
        map.put("PhMedicine", PhMedicine);

        String result = JSON.toJSONString(map);
        return result;
    }


    //0:普通药剂 1：胰岛素药剂
    public static Map<String, Object> getDmDrugStringForFingertip(UseDrugInfo useDrugInfo, int type) {
        Map<String, Object> map = new HashMap<>();

        map.put("name", useDrugInfo.getName());
        map.put("frequency", useDrugInfo.getFrequency());
        if (TextUtils.isEmpty(useDrugInfo.getSingleDose())) {
            map.put("singleDose", 0);
        } else {
            map.put("singleDose", Float.valueOf(useDrugInfo.getSingleDose()));
        }

        map.put("unit", useDrugInfo.getUnit());
        map.put("type", type);
        map.put("drugUsage", useDrugInfo.getDrugUsage());


//        String result = JSON.toJSONString(map);
        return map;
    }

    public static Map<String, Object> getUesDrugStringForFingertip(UseDrugInfo useDrugInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", useDrugInfo.getName());
        map.put("frequency", useDrugInfo.getFrequency());
        if (TextUtils.isEmpty(useDrugInfo.getSingleDose())) {
            map.put("singleDose", 0);
        } else {
            map.put("singleDose", Float.valueOf(useDrugInfo.getSingleDose()));
        }

        map.put("unit", useDrugInfo.getUnit());
//        String result = JSON.toJSONString(map);
        return map;
    }

    public static String getSignDoctorBodyStringForFingertip(String params, String username, int policyYear, int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("params", params);
        map.put("signDoctorName", username);
        map.put("isContainsLower", "1");
        map.put("policyYear", policyYear);
        map.put(Constants.LIMIT_KEY_FOR_FINGERTIP, pageSize);
        map.put(Constants.PAGE_KEY_FOR_FINGERTIP, pageIndex);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static String getDetailPeopleInfoForFingertip(int policyYear, String out, String personId, String signState) {
        Map<String, Object> map = new HashMap<>();
        map.put("policyYear", policyYear);
        map.put("out", out);
        map.put("personId", personId);
        map.put("signState", signState);

        String result = JSON.toJSONString(map);
        return result;
    }

    public static String getHighBodyStringForFingertip(String no, String username, String idNo, int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchText", no);
//        map.put("name", username);
//        map.put("idNo", idNo);
        map.put(Constants.LIMIT_KEY_FOR_FINGERTIP, pageSize);
        map.put(Constants.PAGE_KEY_FOR_FINGERTIP, pageIndex);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static String getSelectChargeStringForFingertip(String no, String isCharged, int pageIndex, int pageSize, String visitTimeStart, String visitTimeEnd) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchText", no);
        map.put("isCharged", isCharged);
        map.put("limit", pageSize);
        map.put("start", pageIndex);
        map.put("visitTimeStart", visitTimeStart);
        map.put("visitTimeEnd", visitTimeEnd);
        String result = JSON.toJSONString(map);
        return result;

//        searchText	查询文本	String
//        isCharged	是否已收费	int
//        start	页码	int
//        limit	条数	int
//        visitTimeStart	就诊日期起	String
//        visitTimeEnd	就诊日期止	String


    }

    public static String getLoginBodyStringForFingertip(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> RegisterForFingertip(Context context, OutpatientInVo outpatientInVo, ArrayList<DiagnosisInVo> diagnosisInVoList) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = RegisterBodyStringForFingertip(outpatientInVo, diagnosisInVoList);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C021"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String RegisterBodyStringForFingertip(OutpatientInVo outpatientInVo, ArrayList<DiagnosisInVo> diagnosisInVoList) {
        Map<String, Object> map = new HashMap<>();
        map.put("chiefComplaint", "");
        map.put("medicalHistory", "");
        map.put("pastHistory", "");
        map.put("allergyHistory", "");
        map.put("t", "");
        map.put("p", "");
        map.put("r", "");
        map.put("sbp", "");
        map.put("dbp", "");
        map.put("glu", "");
        map.put("gluType", "");
        map.put("otherInspect", "");
        map.put("symptomCode", "");
        map.put("symptomName", "");
        map.put("outpatientInVo", outpatientInVo);
        map.put("diagnosisInVoList", diagnosisInVoList);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> PrescribeForFingertip(Context context, String visitNumber, ArrayList<FeeInVos> feeInVos, ArrayList<FeeDetailInVos> feeDetailInVos, TcmInVo tcmInVo) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = PrescribeBodyStringForFingertip(visitNumber, feeInVos, feeDetailInVos, tcmInVo);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C022"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String PrescribeBodyStringForFingertip(String visitNumber, ArrayList<FeeInVos> feeInVos, ArrayList<FeeDetailInVos> feeDetailInVos, TcmInVo tcmInVo) {
        Map<String, Object> map = new HashMap<>();
        if (feeInVos.size() > 0) {
            if (TextUtils.isEmpty(feeInVos.get(0).getChargeItemsCode())) {
                feeInVos = null;
            }
        }
        if (feeDetailInVos.size() > 0) {
            if (TextUtils.isEmpty(feeDetailInVos.get(0).getDrugId())) {
                feeDetailInVos = null;
            }
        }
        if (tcmInVo != null) {
            if (tcmInVo.getFeeDetailsInVo().size() > 0) {
                if (TextUtils.isEmpty(tcmInVo.getFeeDetailsInVo().get(0).getDrugId())) {
                    tcmInVo = null;
                }
            } else {
                tcmInVo = null;
            }

        }
        map.put("visitNumber", visitNumber);
        map.put("prescriptionId", "");
        map.put("feeInVos", feeInVos);
        map.put("feeDetailInVos", feeDetailInVos);
        map.put("tcmInVo", tcmInVo);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> TreatForFingertip(Context context, String param) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = TreatBodyStringForFingertip(param);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("B041"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static Map<String, String> SettleForFingertip(Context context, String feelTotalSS, List<String> prescriptionId, String visitNumber, String compType) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = SettleStringForFingertip(feelTotalSS, prescriptionId, visitNumber, compType);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C024"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static Map<String, String> budgetingForFingertip(Context context, List<String> prescriptionId, String visitNumber, String compType) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = budgetingStringForFingertip(prescriptionId, visitNumber, compType);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C023"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String SettleStringForFingertip(String feelTotalSS, List<String> prescriptionId, String visitNumber, String compType) {
        Map<String, Object> map = new HashMap<>();
        map.put("feelTotalSS", feelTotalSS);
        map.put("prescriptionId", prescriptionId);
        map.put("visitNumber", visitNumber);
        map.put("compType", compType);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static String budgetingStringForFingertip(List<String> prescriptionId, String visitNumber, String compType) {
        Map<String, Object> map = new HashMap<>();
        map.put("prescriptionId", prescriptionId);
        map.put("visitNumber", visitNumber);
        map.put("compType", compType);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static String TreatBodyStringForFingertip(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "1");
        map.put("out", "1");
        map.put("rownum", "");
        map.put("param", param);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> RecipeForFingertip(Context context, String param) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = RecipeBodyStringForFingertip(param);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C031"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String RecipeBodyStringForFingertip(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("params", param);
        map.put("drugId", "");
        map.put("type", "");
        map.put("drug", "0");//chufang
        map.put("out", "1");
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> HarbleForFingertip(Context context, String param) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = harbelBodyStringForFingertip(param);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C031"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String harbelBodyStringForFingertip(String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("params", param);
        map.put("drugId", "");
        map.put("type", "");
        map.put("drug", "1");//caoyao
        map.put("out", "1");
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getNotificationMapForFingertip(Context context, String num) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getNotificationBodyStringForFingertip(num);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("A02"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String getNotificationBodyStringForFingertip(String num) {
        Map<String, String> map = new HashMap<>();
        map.put("num", num);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getSignPeopleManagementMapForFingertip(Context context, String nameOridNo, String areaFulladdress) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getSignPeopleManagementBodyStringForFingertip(nameOridNo, areaFulladdress);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static Map<String, String> getSignPeopleManagementMapForFingertipnew(Context context, String nameOridNo, String areaFulladdress) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getSignPeopleManagementBodyStringForFingertipnew(nameOridNo, areaFulladdress);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String getSignPeopleManagementBodyStringForFingertipnew(String nameOridNo, String areaFulladdress) {
        Map<String, String> map = new HashMap<>();
        map.put("isContainsLower", nameOridNo);
        map.put("beginTimeSign", areaFulladdress);
        map.put("overTimeSign", areaFulladdress);
        map.put("policyYear", areaFulladdress);
        map.put("areaFulladdress", areaFulladdress);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static String getSignPeopleManagementBodyStringForFingertip(String nameOridNo, String areaFulladdress) {
        Map<String, String> map = new HashMap<>();
        map.put("nameOridNo", nameOridNo);
        map.put("areaFulladdress", areaFulladdress);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getPatientListMapForFingertip(Context context, String year, String keyword) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getPatientListBodyStringForFingertip(year, keyword);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C02"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String getPatientListBodyStringForFingertip(String year, String keyword) {
        Map<String, String> map = new HashMap<>();
        map.put("year", year);
        map.put("medicalNo", keyword);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getSignManagementReadListMapForFingertip(Context context, String params, String areaFullAddress) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getSignManagementReadListBodyStringForFingertip(params, areaFullAddress);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E012"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String getSignManagementReadListBodyStringForFingertip(String params, String areaFullAddress) {
        Map<String, String> map = new HashMap<>();
        map.put("params", params);
        map.put("areaFulladdress", areaFullAddress);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getNewDoctorSignMapForFingertip(Context context, String personId,
                                                                      String signDoctorPhone,
                                                                      String areaFulladdress,
                                                                      String nationCode,
                                                                      String nationName,
                                                                      String birthday,
                                                                      String name,
                                                                      String sexCode,
                                                                      String sexName,
                                                                      String areaHouseno,
                                                                      String idNo,
                                                                      String phone,
                                                                      String residentType,
                                                                      String hujiType,
                                                                      String areavillageCode,

                                                                      String personTypes, String ssAreazuCode) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getNewDoctorSignBodyStringForFingertip(personId, signDoctorPhone, areaFulladdress, nationCode, nationName,
                birthday, name, sexCode, sexName, areaHouseno, idNo, phone, residentType, hujiType, areavillageCode, personTypes, ssAreazuCode);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E011"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }

    public static String getNewDoctorSignBodyStringForFingertip(String personId,
                                                                String signDoctorPhone,
                                                                String areaFulladdress,
                                                                String nationCode,
                                                                String nationName,
                                                                String birthday,
                                                                String name,
                                                                String sexCode,
                                                                String sexName,
                                                                String areaHouseno,
                                                                String idNo,
                                                                String phone,
                                                                String residentType,
                                                                String hujiType,
                                                                String areavillageCode,
                                                                String personTypes, String ssAreazuCode
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("personId", personId);
        map.put("signDoctorPhone", signDoctorPhone);
        map.put("areaFulladdress", areaFulladdress);
        map.put("nationCode", nationCode);
        map.put("nationName", nationName);
        map.put("birthday", birthday);
        map.put("name", name);
        map.put("sexCode", sexCode);
        map.put("sexName", sexName);
        map.put("areaHouseno", areaHouseno);
        map.put("idNo", idNo);
        map.put("phone", phone);
        map.put("residentType", residentType);
        map.put("hujiType", hujiType);
        map.put("areavillageCode", areavillageCode);
        map.put("personTypes", personTypes);
        map.put("zuCode", ssAreazuCode);
        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, String> getOutpatientStatisticsMapForFingertip(Context context) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = "";
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        String bodyJson = JSON.toJSONString(result);

        return result;
    }


    public static Map<String, String> getLoginMapFrompCode(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadConfig");

        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLoginMapArea(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadAreas");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLoginMapDictionary(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadDicts");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLoginOrganization(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadUnits");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLoginMapSalvation(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadItems");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getUpdatePhoneNumberFromUsernameAndPassword(Context context, String userId, String phoneNumber) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedUserId = Util.getBase64String(userId);
        String encodedPhoneNumber = Util.getBase64String(phoneNumber);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.PHONE_KEY, encodedPhoneNumber);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;
    }

    public static Map<String, String> getSignTeamInfo(Context context, String orgId, String docUserId) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedSign = Util.getBase64String(Version.SIGN);
        String encodedorgId = Util.getBase64String(orgId);
        String encodeddocUserId = Util.getBase64String(docUserId);
        result.put("orgId", encodedorgId);
        result.put("docUserId", encodeddocUserId);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;

    }

    public static Map<String, String> getPersonInfo(Context context, String bookno, String operator) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodebookno = Util.getBase64String(bookno);
        String encodeoperator = Util.getBase64String(operator);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put("bookno", encodebookno);
        result.put("operator", encodeoperator);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;
    }

    public static Map<String, String> getPersonnelInfo(Context context, String bookno) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodebookno = Util.getBase64String(bookno);
        result.put("personId", encodebookno);

        return result;
    }

    public static Map<String, String> getPersonnelInfo2(Context context, String bookno) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodebookno = Util.getBase64String(bookno);
        result.put("cardNo", encodebookno);

        return result;
    }

    public static Map<String, String> getupdatePeople(Context context, String year, String personId, String phone, String contactName,
                                                      String contactPhone, String photo, String cardNo, String flag) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("year", Util.getBase64String(year));
        result.put("personId", Util.getBase64String(personId));
        result.put("phone", Util.getBase64String(phone));
        result.put("contactName", Util.getBase64String(contactName));
        result.put("contactPhone", Util.getBase64String(contactPhone));
        result.put("photo", photo);
        result.put("cardNo", Util.getBase64String(cardNo));
        result.put("flag", Util.getBase64String(flag));

        return result;
    }


    public static Map<String, String> getSignDoctorServerList(Context context) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;
    }

    public static Map<String, String> getCoreIndexList(Context context, String ServiceContentItemsKey) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedServiceContentItemsKey = Util.getBase64String(ServiceContentItemsKey);
        //    String encodedUsername = encodedServiceContentItemsKey;
        result.put("serviceContentItemsID", encodedServiceContentItemsKey);
        //    result.put("serviceContentItemsID", Util.getBase64String("CE8234FD-C79C-46C6-A40A-691E49A32797"));
        return result;
    }

    public static Map<String, String> getSignExecProDetail(Context context, String getServicePlanID, String year) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedgetServicePlanID = Util.getBase64String(getServicePlanID);
        String encodedyear = Util.getBase64String(year);

        result.put("servicePlanID", encodedgetServicePlanID);
        result.put("signPageYear", encodedyear);

        return result;
    }


    public static Map<String, String> getServicePackDetailList(Context context, String key) {
        Map<String, String> result = getBaseRequestMap(context);
//        String id = "3E0EBE4B-B9EB-4405-965C-08FD8CB86E6D";
//        id = Util.getBase64String(id);
        String encodeKey = Util.getBase64String(key);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.SERVICECONTENTID_KEY, encodeKey);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;
    }

    public static Map<String, String> getTownLevelMedicalList(Context context) {
        Map<String, String> result = getBaseRequestMap(context);

        String level = "NA==";

        result.put("level", level);
        result.put("orgId", "");
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;
    }

    public static Map<String, String> getVillageMedicalList(Context context, String orgcode) {
        Map<String, String> result = getBaseRequestMap(context);

        String level = "NQ==";

        result.put("level", level);
        result.put("orgId", Util.getBase64String(orgcode));
        return result;
    }


    public static Map<String, String> getMedicalPersonList(Context context, String orgcode) {
        Map<String, String> result = getBaseRequestMap(context);

        String level = "Ng==";

        result.put("level", level);
        result.put("orgId", Util.getBase64String(orgcode));

        return result;
    }


    public static Map<String, String> getPeopleBaseInfo(Context context, String sYear, String sSignKey, String sPersonModel, String sPersonId) {
        Map<String, String> result = getBaseRequestMap(context);

        String encodedsYear = Util.getBase64String(sYear);
        String encodedsSignKey = Util.getBase64String(sSignKey);
        String encodedsPersonModel = Util.getBase64String(sPersonModel);
        String encodedsPersonId = Util.getBase64String(sPersonId);

        result.put("year", encodedsYear);
        result.put("signKey", encodedsSignKey);
        result.put("personModel", encodedsPersonModel);
        result.put("personId", encodedsPersonId);


        return result;
    }

    public static Map<String, String> getPeopleSignInfo(Context context, String sYear, String sSignKey) {

        Map<String, String> result = getBaseRequestMap(context);

        String encodedsYear = Util.getBase64String(sYear);
        String encodedsSignKey = Util.getBase64String(sSignKey);

        result.put("year", encodedsYear);
        result.put("signKey", encodedsSignKey);
        /*String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.SIGN_KEY, encodedSign);*/
        return result;
    }

    public static Map<String, String> getReservationInfo(Context context, String sYear, String orgId, String docUserId) {

        Map<String, String> result = getBaseRequestMap(context);

        String encodedorgId = Util.getBase64String(orgId);
        String encodeddocUserId = Util.getBase64String(docUserId);
        String encodeddocyear = Util.getBase64String(sYear);

        result.put("orgId", encodedorgId);
        result.put("docUserId", encodeddocUserId);
        result.put("year", encodeddocyear);
        return result;
    }

    public static Map<String, String> getEvaluationInfo(Context context, String servicePlanID, String year) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodeservicePlanID = Util.getBase64String(servicePlanID);
        String encodeyear = Util.getBase64String(year);
        result.put("servicePlanID", encodeservicePlanID);
        result.put("year", encodeyear);
        return result;
    }

    public static Map<String, String> savePersonEvaluationServer(Context context, String servicePlanID, String year, String evaluation, String evaluationNote,
                                                                 String userName, String docUserID) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodeservicePlanID = Util.getBase64String(servicePlanID);
        String encodeyear = Util.getBase64String(year);
        String encodeevaluation = Util.getBase64String(evaluation);
        String encodeevaluationNote = Util.getBase64String(evaluationNote);
        String encodedUserName = Util.getBase64String(userName);
        String encodedDocUserID = Util.getBase64String(docUserID);
        result.put("servicePlanID", encodeservicePlanID);
        result.put("year", encodeyear);
        result.put("evaluation", encodeevaluation);
        result.put("evaluationNote", encodeevaluationNote);

        result.put("userName", encodedUserName);
        result.put("docUserID", encodedDocUserID);
        return result;
    }

    public static Map<String, String> getSignInfoOverviewMap(Context context, String organizationKey, String userId) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedOrganization = Util.getBase64String(organizationKey);
        String encodedUserId = Util.getBase64String(userId);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.ORGANIZATION_KEY, encodedOrganization);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;

    }

    public static Map<String, String> getIntelligentHonourAgreementOverviewMap(Context context, String organizationKey, String userId, String category) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedOrganization = Util.getBase64String(organizationKey);
        String encodedUserId = Util.getBase64String(userId);
        String encodedCategory = Util.getBase64String(category);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.ORGANIZATION_KEY, encodedOrganization);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.CATEGORY_KEY, encodedCategory);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;

    }

    public static Map<String, String> getLatest7DaysExecutionProjectsListMap(Context context, String cardNumber) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedCardNumber = Util.getBase64String(cardNumber);
        result.put(Constants.CARD_NO_KEY, encodedCardNumber);
        return result;

    }

    public static Map<String, String> getExecutionProjectsListMap(Context context, String organizationKey, String userId, String item,
                                                                  String type, String userName, String cardNumber, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedOrganization = Util.getBase64String(organizationKey);
        String encodedUserId = Util.getBase64String(userId);
        String encodedItem = Util.getBase64String(item);
        String encodedType = Util.getBase64String(type);
        String encodedUserName = Util.getBase64String(userName);
        String encodedCardNumber = Util.getBase64String(cardNumber);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.ORGANIZATION_KEY, encodedOrganization);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.CATEGORY_KEY, encodedItem);
        result.put(Constants.TYPE_KEY, encodedType);
        result.put(Constants.USER_NAME_KEY, encodedUserName);
        result.put(Constants.CARD_NO_KEY, encodedCardNumber);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;

    }

    public static Map<String, String> getAppointmentRegistrationListMap(Context context, String organizationKey, String userId, String keyword, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedOrganization = Util.getBase64String(organizationKey);
        String encodedUserId = Util.getBase64String(userId);
        String encodedKeyword = Util.getBase64String(keyword);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.ORGANIZATION_KEY, encodedOrganization);
        result.put(Constants.USER_ID_KEY, encodedUserId);
        result.put(Constants.KEYWORD_KEY, encodedKeyword);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        result.put(Constants.SIGN_KEY, encodedSign);
        return result;

    }

    public static Map<String, String> getSignResidentDirectoryListMap(Context context, String docUserId, String keyword, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedDocUserId = Util.getBase64String(docUserId);
        String encodedKeyword = Util.getBase64String(keyword);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put(Constants.DOCTOR_USER_ID, encodedDocUserId);
        result.put(Constants.KEYWORD_KEY, encodedKeyword);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        return result;

    }

    //cardNo   loginName   sign
    public static Map<String, String> getSignPromExeclist(Context context, String cardNo) {
        Map<String, String> result = getBaseRequestMap(context);
        //  String encodeyear = Util.getBase64String(year);
        String encodecardNo = Util.getBase64String(cardNo);
        //    String encodeloginName = Util.getBase64String(loginName);
        //  result.put("year", encodeyear);
        result.put("cardNo", encodecardNo);
        //  result.put("loginName", encodeloginName);
        return result;
    }

    public static Map<String, String> getSignServiceAssessListMap(Context context, String sUsername, String sRealName, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appDiscover.discoverList");
        result.put("userName", sUsername);
        result.put("pageIndex", String.valueOf((page) * size));
        result.put("pageSize", String.valueOf(size));
        result.put("nickName", sRealName);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;


       /* Map<String, String> result = getBaseRequestMap(context);
        String encodedDocUserId = Util.getBase64String(docUserId);
        String encodedYear = Util.getBase64String(year);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put(Constants.DOCTOR_USER_ID, encodedDocUserId);
        result.put(Constants.SPECIFIC_YEAR_KEY, encodedYear);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        return result;*/

    }

    public static Map<String, String> getHouseholdData(Context context, String userName, String searchCondition, String queryType, int pageIndex) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryExamineSearchList");
        result.put("userName", userName);
        result.put("pageIndex", String.valueOf(pageIndex));
        result.put("searchCondition", searchCondition);
        result.put("queryType", queryType);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> saveAdviceData(Context context, String userName, String unitCode, String type, String name, String telphone, String email, String title, String content) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "consult.saveConsult");
        result.put("type", type);
        result.put("unitCode", unitCode);
        result.put("name", name);
        result.put("telphone", telphone);
        result.put("email", email);
        result.put("title", title);
        result.put("content", content);
        result.put("userName", userName);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getReadSignServiceAssessItemMap(Context context, String servicePlanId, String year) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedServicePlanId = Util.getBase64String(servicePlanId);
        String encodedYear = Util.getBase64String(year);
//        String encodedFlag = Util.getBase64String(Constants.SIGN_SERVICE_ASSESS_LIST_DISPLAY);
        result.put(Constants.SERVICE_PLAN_ID_KEY, encodedServicePlanId);
        result.put(Constants.SPECIFIC_YEAR_KEY, encodedYear);
//        result.put(Constants.SIGN_SERVICE_ASSESS_FLAG_KEY, encodedFlag);
        return result;

    }

    public static Map<String, String> getOnlineConsultationListMap(Context context, String docUserId, String year, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedDocUserId = Util.getBase64String(docUserId);
        String encodedYear = Util.getBase64String(year);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put(Constants.DOCTOR_USER_ID, encodedDocUserId);
        result.put(Constants.SPECIFIC_YEAR_KEY, encodedYear);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);
        return result;

    }

    public static Map<String, String> getSignServiceIncomeListMap(Context context, String docUserId, String orgId, String year) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedDocUserId = Util.getBase64String(docUserId);
        String encodedYear = Util.getBase64String(year);
        String encodedOrgId = Util.getBase64String(orgId);
        result.put(Constants.DOCTOR_USER_ID, encodedDocUserId);
        result.put(Constants.SPECIFIC_YEAR_KEY, encodedYear);
        result.put(Constants.ORG_ID_KEY, encodedOrgId);
        return result;

    }

    public static Map<String, String> getSignApplicationListMap(Context context,
                                                                int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String userId = Identity.getUserId();
        String year = Util.getCurrentYearString();
        String encodedDoctorId = Util.getBase64String(userId);
        String encodedYear = Util.getBase64String(year);
        result.put(Constants.APPLY_DOCTOR_ID, encodedDoctorId);
        result.put(Constants.SPECIFIC_YEAR_KEY, encodedYear);


        return result;

    }

    public static Map<String, String> getCancelSignApplicationMap(Context context, String applyKey, String cardNo, String applyCancel, String finishDoctor) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedApplyKey = Util.getBase64String(applyKey);
        String encodedCardNo = Util.getBase64String(cardNo);
        String encodedApplyCancel = Util.getBase64String(applyCancel);
        String encodedFinishDoctor = Util.getBase64String(finishDoctor);
        result.put(Constants.APPLY_KEY, encodedApplyKey);
        result.put(Constants.CARD_NO_KEY, encodedCardNo);
        result.put(Constants.APPLY_CANCEL, encodedApplyCancel);
        result.put(Constants.FINISH_DOCTOR, encodedFinishDoctor);
        return result;
    }

    public static Map<String, String> getHospitalInspectListMap(Context context, String cardNo) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("cardNo", Util.getBase64String(cardNo));
        //    result.put("cardNo", "MzQyMTIzMTk1MjA5MTY4NTAy");


        return result;

    }

    public static Map<String, String> getHospitalInspectListCardMap(Context context, String orgId, String docUserId,
                                                                    String card, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put("orgId", orgId);
        result.put("docUserId", docUserId);
        result.put("cardNo", card);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);

        return result;

    }

    public static Map<String, String> getPostImgMap(Context context, String personID, String cardNo,
                                                    String photo, String edit) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("personID", personID);
        result.put("cardNo", cardNo);
        result.put("photo", photo);
        result.put("edit", Util.getBase64String(edit));

        return result;

    }


    public static Map<String, String> getHospitalInspectListNameMap(Context context, String orgId, String docUserId,
                                                                    String name, int page, int size) {
        Map<String, String> result = getBaseRequestMap(context);
        String encodedSign = Util.getBase64String(Version.SIGN);
        String encodedStartIndex = Util.getBase64String(String.valueOf((page - 1) * size + 1));
        String encodedEndIndex = Util.getBase64String(String.valueOf(page * size));
        result.put("orgId", orgId);
        result.put("docUserId", docUserId);
        result.put("userName", name);
        result.put(Constants.START_INDEX_KEY, encodedStartIndex);
        result.put(Constants.END_INDEX_KEY, encodedEndIndex);

        return result;

    }


    public static Map<String, String> getMedicalPersonDirectoryListMap(String keyword, String area, int page, int size) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        MedicalPersonDirectoryListParameters tlp = new MedicalPersonDirectoryListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                keyword, area, page, size);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("medical person directory list map:" + map.toString());
        return map;
    }

    public static Map<String, String> getVillageLevelMedicalInstitutionDirectoryListMap(String keyword, String area, int page, int size) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        VillageLevelMedicalInstitutionDirectoryListParameters tlp = new VillageLevelMedicalInstitutionDirectoryListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                keyword, area, page, size);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("village level medical institution directory list map:" + map.toString());
        return map;
    }

    public static Map<String, String> getExecutionProjectsListMap(String keyword, String area, int page, int size) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        ExecutionProjectsListParameters tlp = new ExecutionProjectsListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                keyword, area, page, size);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("execution projects list map:" + map.toString());
        return map;
    }

    public static FriendListRequestBean getFriendListRequestBean(/*String keyword, String area, int page, int size*/) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
//        String encodedKeyword = Util.getUrlEncodedString(keyword);
        FriendListParameters flp = new FriendListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV());
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(flp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("friend list request map:" + map.toString());

        FriendListRequestBean result = new FriendListRequestBean(flp.getV(), flp.getTimestamp(), flp.getToken(),
                flp.getAppkey(), signature);

        return result;
    }


    public static Map<String, String> getFriendListMap() {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        FriendListParameters flp = new FriendListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV());
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(flp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("friend list map:" + map.toString());
        return map;
    }

    public static Map<String, String> getSendMsgMap(String to, String content) {
        content = Util.trimString(content);
        int contentLength = StringUtils.length(content);
        if (contentLength > Constants.SEND_MSG_MAX_LENGTH) {
            return null;
        }
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        SendMessageParameters smp = new SendMessageParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                to, content);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(smp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("send message map:" + map.toString());
        return map;
    }

    public static Map<String, String> getLockUnlockTreatmentMap(String treatmentId) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        TreatmentLockUnlockParameters tlup = new TreatmentLockUnlockParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                treatmentId);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlup);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("lock unlock treatment map:" + map.toString());
        return map;
    }

    public static Map<String, String> getTreatmentListMap(String keyword, String area, int page, int size) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        TreatmentListParameters tlp = new TreatmentListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                keyword, area, page, size);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("treatment list map:" + map.toString());
        return map;
    }

    public static TreatmentListRequestBean getTreatmentListRequestBean(String keyword, String area, int page, int size) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
//        String encodedKeyword = Util.getUrlEncodedString(keyword);
        TreatmentListParameters tlp = new TreatmentListParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(), keyword, area, page, size);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignature(tlp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("treatment list request map:" + map.toString());

        TreatmentListRequestBean result = new TreatmentListRequestBean(tlp.getV(), tlp.getTimestamp(), tlp.getToken(),
                tlp.getAppkey(), signature, keyword, tlp.getArea(), tlp.getPage(), tlp.getSize());

        return result;
    }

    public static Map<String, String> getParametersKeysAndValuesExcludeSignature(Object object) {
//        String[] keyArray = Util.getFiledNameArray(object);
//        String s = Util.getStringFromStringArray(keyArray);
//        LogUtil.i("keyArray:"+s);
        Map<String, String> result = HttpParameterAnnotationProcessor.getFieldNameAndValueMap(object);
        result.remove(Version.SIG_KEY);
        return result;
    }

    public static Map<String, String> getParametersKeysAndValuesExcludeSignatureAndToken(Object object) {
//        String[] keyArray = Util.getFiledNameArray(object);
//        String s = Util.getStringFromStringArray(keyArray);
//        LogUtil.i("keyArray:"+s);
        Map<String, String> result = HttpParameterAnnotationProcessor.getFieldNameAndValueMap(object);
        result.remove(Version.SIG_KEY);
        result.remove(Version.TOKEN_KEY);
        return result;
    }

    public static String getSignatureForMap(Map<String, String> map) {
        if (map == null) {
            return Constants.EMPTY_STRING;
        } else {
            Set<String> keys = map.keySet();
            List<String> keyList = new ArrayList<>(keys);
            LogUtil.i("keyList except sig:" + ListUtils.join(keyList, Constants.COMMA_STRING));
            Collections.sort(keyList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            LogUtil.i("sorted keyList except sig:" + ListUtils.join(keyList, Constants.COMMA_STRING));
            String combinedString = "";
            for (int i = 0; i < keyList.size(); ++i) {
                String key = keyList.get(i);
                String value = map.get(key);
//                String encodedValue = Util.encodeString(value);
                if (!StringUtils.isBlank(value)) {

                    combinedString += key;
//                    combinedString += encodedValue;
                    combinedString += value;
                }
            }
            combinedString += Version.PRIVATE_KEY;
//            String sha1 = EncryptUtils.encryptSHA1ToString(combinedString);
            String md5 = EncryptUtils.encryptMD5ToString(combinedString);
            LogUtil.i("plain text:" + combinedString);
            LogUtil.i("cypher text:" + md5);
            return md5;
        }
    }

    public static Map<String, Object> saveFamilyDoctorSign(Context context,
                                                           String key,
                                                           String fdSigningDoctorMode,
                                                           String statusCode,
                                                           String serverPackageName,
                                                           String isPersonality,
                                                           String signDT,
                                                           String signMode,
                                                           String signHomeCode,
                                                           String signDeptName,
                                                           String signDeptCode,
                                                           String signDeptPhone,
                                                           String teamName,
                                                           String teamCode,
                                                           String signTeamHeaderName,
                                                           String signTeamHeaderCode,
                                                           String signTeamHeaderPhone,
                                                           String doctorName,
                                                           String doctorCode,
                                                           String doctorPhone,
                                                           String userName,
                                                           String sexCode,
                                                           String cardNo,
                                                           String guardianCardNo,
                                                           String isUseGuardian,
                                                           String phone,
                                                           String agriculturalCardNo,
                                                           String isHouseholder,
                                                           String personID,
                                                           String no,
                                                           String isRelation,
                                                           String filingStatue,
                                                           String recordMode,
                                                           String inputDeptCode,
                                                           String inputDeptName,
                                                           String areaCode,
                                                           String areaName,
                                                           String addUserCode,
                                                           String addUserName,
                                                           String addOrgId,
                                                           String addDT,
                                                           String updateUserCode,
                                                           String updateUserName,
                                                           String updateOrgId,
                                                           String updateDT,
                                                           String isFilingStatue,
                                                           String personMold,
                                                           String docOrganizationKey,
                                                           String docOrganizationName,
                                                           String docLoginName,
                                                           String serviceContent,
                                                           String isExecute,
                                                           String docUserID,
                                                           String isCharge,
                                                           String actualPackageSumFee,
                                                           String packSumFee,
                                                           String newRuralCMSFee,
                                                           String otherReduceFee,
                                                           String shouldSelfFee,
                                                           String jbggwsState,
                                                           String signPageYear,
                                                           String startExecData,
                                                           String endExecData,
                                                           String death,
                                                           String familySysno,
                                                           String memberSysno,
                                                           String interfaceStatus,
                                                           String basicPubilcMoney,
                                                           String isPrintyjj,
                                                           String idType,
                                                           String applyKey,
                                                           String signEdit,
                                                           List<SignSeverPakesBeanList> detailInVoList) {

        Map<String, Object> result = new HashMap<>();
        String loginName = IdentityManager.getLoginSuccessUsername(context);
        String encodedLoginName = Util.getBase64String(loginName);
        result.put(Constants.LOGIN_USERNAME_KEY, encodedLoginName);

        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put(Constants.SIGN_KEY, encodedSign);
        result.put("key", Util.getBase64String(key));
        result.put("fdSigningDoctorMode", Util.getBase64String(fdSigningDoctorMode));//（签约方式： 1：按年度签约  2：随到随签）
        result.put("statusCode", Util.getBase64String(statusCode));//1：起草中、2：已提交（已签约）、3：审核通过、4：审核不通过，5解约
        result.put("serverPackageName", Util.getBase64String(serverPackageName));//服务包名称，多个用分号隔开
        result.put("isPersonality", Util.getBase64String(isPersonality));//是否含个性化服务（1是、0否）
        result.put("signDT", Util.getBase64String(signDT));//签约时间
        result.put("signMode", Util.getBase64String(signMode));//签约形式（1、家庭、2、个人）
        result.put("signHomeCode", Util.getBase64String(signHomeCode));//户编号（一户生成一个GUID）
        result.put("signDeptName", Util.getBase64String(signDeptName));//签约机构名称(甲方))
        result.put("signDeptCode", Util.getBase64String(signDeptCode));//签约机构编码
        result.put("signDeptPhone", Util.getBase64String(signDeptPhone));//机构的联系电话
        result.put("teamName", Util.getBase64String(teamName));//团队名称
        result.put("teamCode", Util.getBase64String(teamCode));//团队编码
        result.put("signTeamHeaderName", Util.getBase64String(signTeamHeaderName));//签约团队负责人姓名
        result.put("signTeamHeaderCode", Util.getBase64String(signTeamHeaderCode));//签约团队负责人编码
        result.put("signTeamHeaderPhone", Util.getBase64String(signTeamHeaderPhone));//签约团队负责人电话
        result.put("doctorName", Util.getBase64String(doctorName));//家庭医生姓名
        result.put("doctorCode", Util.getBase64String(doctorCode));//家庭医生编码
        result.put("doctorPhone", Util.getBase64String(doctorPhone));//家庭医生电话
        result.put("userName", Util.getBase64String(userName));//签约人姓名
        result.put("sexCode", Util.getBase64String(sexCode));//签约人性别编号
        result.put("cardNo", Util.getBase64String(cardNo));//签约人身份证号(isUseGuardian = 0  必填))
        result.put("guardianCardNo", Util.getBase64String(guardianCardNo));//监护人身份证号(isUseGuardian = 1  必填))
        result.put("isUseGuardian", Util.getBase64String(isUseGuardian));//是否启用监护人 0 代表本人身份证号码，1代表的是 监护人身份证号码
        result.put("phone", Util.getBase64String(phone));//签约人联系电话
        result.put("agriculturalCardNo", Util.getBase64String(agriculturalCardNo));//医保类型(0 新农合  1职工医保 3居民医保 4其他))
        result.put("isHouseholder", Util.getBase64String(isHouseholder));//是否是户主
        result.put("personID", Util.getBase64String(personID));//个人健康档案唯一标识符
        result.put("no", Util.getBase64String(no));//健康档案号
        result.put("isRelation", Util.getBase64String(isRelation));//是否关联亲属（0未关联1关联))
        result.put("filingStatue", Util.getBase64String(filingStatue));//包是否完成 0 未完成，1已完成
        result.put("recordMode", Util.getBase64String(recordMode));//录入方式（1手动、2农合卡、3医保卡、4身份证）
        result.put("inputDeptCode", Util.getBase64String(inputDeptCode));//行政机构编码
        result.put("inputDeptName", Util.getBase64String(inputDeptName));//行政机构名称
        result.put("areaCode", Util.getBase64String(areaCode));//地区编码
        result.put("areaName", Util.getBase64String(areaName));//地区名称
        result.put("addUserCode", Util.getBase64String(addUserCode));//创建人编码
        result.put("addUserName", Util.getBase64String(addUserName));//创建人名称
        result.put("addOrgId", Util.getBase64String(addOrgId));//添加机构ID
        result.put("addDT", Util.getBase64String(addDT));//录入时间
        result.put("updateUserCode", Util.getBase64String(updateUserCode));//修改人编码
        result.put("updateUserName", Util.getBase64String(updateUserName));//修改人名称
        result.put("updateOrgId", Util.getBase64String(updateOrgId));//修改机构ID
        result.put("updateDT", Util.getBase64String(updateDT));//修改时间
        result.put("isFilingStatue", Util.getBase64String(isFilingStatue));//是否关联健康档案（0未关联1已关联）
        result.put("personMold", Util.getBase64String(personMold));//人员类型 （详细解释在后面）
        result.put("docOrganizationKey", Util.getBase64String(docOrganizationKey));//家庭医生机构编码 (公卫中的机构）
        result.put("docOrganizationName", Util.getBase64String(docOrganizationName));//家庭医生机构名称
        result.put("docLoginName", Util.getBase64String(docLoginName));//签约医生公卫系统登录帐号
        result.put("serviceContent", Util.getBase64String(serviceContent));//服务内容
        result.put("isExecute", Util.getBase64String(isExecute));//是否执行(0执行1未执行))
        result.put("docUserID", Util.getBase64String(docUserID));//家庭医生UserID  注：为基本公共卫生里面的签约医生ID
        result.put("isCharge", Util.getBase64String(isCharge));//是否已收费（1是，0否）
        result.put("actualPackageSumFee", Util.getBase64String(actualPackageSumFee));//实收金额总计(not null))
        result.put("packSumFee", Util.getBase64String(packSumFee));//费用总计
        result.put("newRuralCMSFee", Util.getBase64String(newRuralCMSFee));//新农合补偿金额总计(not null))
        result.put("otherReduceFee", Util.getBase64String(otherReduceFee));//减免金额总计(not null))
        result.put("shouldSelfFee", Util.getBase64String(shouldSelfFee));//应自付金额总计(not null))
        result.put("jbggwsState", Util.getBase64String(jbggwsState)); //是否是基本公共卫生(0 是  1 否))
        result.put("signPageYear", Util.getBase64String(signPageYear));//签约年份
        result.put("startExecData", Util.getBase64String(startExecData));//开始执行时间
        result.put("endExecData", Util.getBase64String(endExecData));//结束执行时间
        result.put("death", Util.getBase64String(death));//死亡状态
        result.put("familySysno", Util.getBase64String(familySysno));//家庭编码
        result.put("memberSysno", Util.getBase64String(memberSysno));//人员编码
        result.put("interfaceStatus", Util.getBase64String(interfaceStatus));//1 代表关联农合成功 2 代表未关联农合成功
        result.put("basicPubilcMoney", Util.getBase64String(basicPubilcMoney));//公共卫生承担金额
        result.put("isPrintyjj", Util.getBase64String(isPrintyjj));//是否打印签约协议（1是0否）
        result.put("idType", Util.getBase64String(idType));//报补类型（农合和医报）
        result.put("applyKey", Util.getBase64String(applyKey));//签约明细[飓风季节：解决]
        result.put("signEdit", Util.getBase64String(signEdit));//签约明细[飓风季节：解决]
        result.put("detailInVoList", detailInVoList);//签约明细[飓风季节：解决]


        //  Util.decodeBase64Bean(detailInVoList);
        return result;
    }


    public static Map<String, Object> SaveExecuServerItem(Context context,
                                                          String signPageYear,
                                                          String servicePlanId,
                                                          String signDetailKey,
                                                          String serviceContentItemsId,
                                                          String serviceContentId,
                                                          String viewgKey,
                                                          String serviceContentDesc,
                                                          String serverDT,
                                                          String byServiceUserName,
                                                          String feedBackOpinion,
                                                          String feedBackDT,
                                                          String nextServerDT,
                                                          String doctorName,
                                                          String doctorCode,
                                                          String doctorPhone,
                                                          String addUserName,
                                                          String addOrgId,
                                                          String addDT,
                                                          String updateUserName,
                                                          String updateOrgId,
                                                          String updateDT,
                                                          String docUserID,
                                                          String townDeptCode,
                                                          String serverPlaceType,
                                                          String serverPlaceOther,
                                                          String docOrganizationKey,
                                                          String docOrganizationName,
                                                          String isExecute,
                                                          String isRefused,
                                                          String noExecuteRemark,
                                                          String checkDate,
                                                          String doctorSignKey,
                                                          String signFilingStatue,
                                                          String serviceContentItemsGkey,
                                                          String shouldExecTimes,
                                                          String hadExecTimes,
                                                          String execTimes,
                                                          String edit,
                                                          String flag,
                                                          String orderServiceId,
                                                          List<SaveItemTargetsoListBeans> myCoreIndexItemList,
                                                          List<SaveSignServiceContentItemListBeans> myServiceContentItemList
    ) {


        Map<String, Object> result = new HashMap<>();
        String loginName = IdentityManager.getLoginSuccessUsername(context);
        String encodedLoginName = Util.getBase64String(loginName);
        result.put(Constants.LOGIN_USERNAME_KEY, encodedLoginName);

        result.put("addDT", Util.getBase64String(addDT));
        result.put("addOrgId", Util.getBase64String(addOrgId));
        result.put("addUserName", Util.getBase64String(addUserName));
        result.put("byServiceUserName", Util.getBase64String(byServiceUserName));
        result.put("checkDate", Util.getBase64String(checkDate));
        result.put("docOrganizationKey", Util.getBase64String(docOrganizationKey));
        result.put("docOrganizationName", Util.getBase64String(docOrganizationName));
        result.put("docUserID", Util.getBase64String(docUserID));
        result.put("doctorCode", Util.getBase64String(doctorCode));
        result.put("doctorName", Util.getBase64String(doctorName));
        result.put("doctorPhone", Util.getBase64String(doctorPhone));
        result.put("doctorSignKey", Util.getBase64String(doctorSignKey));

        result.put("execTimes", Util.getBase64String(execTimes));
        result.put("feedBackDT", Util.getBase64String(feedBackDT));
        result.put("feedBackOpinion", Util.getBase64String(feedBackOpinion));
        result.put("hadExecTimes", Util.getBase64String(hadExecTimes));
        result.put("isExecute", Util.getBase64String(isExecute));
        result.put("isRefused", Util.getBase64String(isRefused));


        result.put("nextServerDT", Util.getBase64String(nextServerDT));
        result.put("noExecuteRemark", Util.getBase64String(noExecuteRemark));
        result.put("serverDT", Util.getBase64String(serverDT));
        result.put("serverPlaceOther", Util.getBase64String(serverPlaceOther));
        result.put("serverPlaceType", Util.getBase64String(serverPlaceType));
        result.put("serviceContentDesc", Util.getBase64String(serviceContentDesc));
        result.put("serviceContentId", Util.getBase64String(serviceContentId));
        result.put("serviceContentItemsGkey", Util.getBase64String(serviceContentItemsGkey));
        result.put("serviceContentItemsId", Util.getBase64String(serviceContentItemsId));
        result.put("servicePlanId", Util.getBase64String(servicePlanId));
        result.put("shouldExecTimes", Util.getBase64String(shouldExecTimes));
        result.put("signDetailKey", Util.getBase64String(signDetailKey));
        result.put("signFilingStatue", Util.getBase64String(signFilingStatue));
        result.put("signPageYear", Util.getBase64String(signPageYear));
        result.put("townDeptCode", Util.getBase64String(townDeptCode));
        result.put("updateDT", Util.getBase64String(updateDT));
        result.put("updateOrgId", Util.getBase64String(updateOrgId));
        result.put("updateUserName", Util.getBase64String(updateUserName));
        result.put("viewgKey", Util.getBase64String(viewgKey));


        if (edit.equals("2")) {

        } else {
            result.put("orderServiceId", Util.getBase64String(orderServiceId));
        }
        result.put("flag", Util.getBase64String(flag));


        result.put("edit", Util.getBase64String(edit));
        String myloginName = IdentityManager.getLoginSuccessUsername(context);

        result.put(Constants.LOGIN_USERNAME_KEY, Util.getBase64String(myloginName));
        result.put(Constants.LOGIN_USERNAME_KEY, Util.getBase64String(myloginName));
        result.put(Constants.SIGN_KEY, Util.getBase64String(Version.SIGN));


      /*  Gson gson = new Gson();
        String MdRecord = gson.toJson(mydetailInvoList);
        String detail = "[" + MdRecord + "]";

        String MdRecord1 = gson.toJson(myItemTargetsoList);
        String item = "[" + MdRecord1 + "]";

        String MdRecord2 = gson.toJson(mySaveSignServiceContentItemList);
        String signitem = "[" + MdRecord2 + "]";*/

        result.put("itemhxzbInVoList", myCoreIndexItemList);//签约明细[飓风季节：解决]
        result.put("signServiceContentItemsList", myServiceContentItemList);//签约明细[飓风季节：解决]
           /* JSONArray myJsonArray1 = new JSONArray(myCoreIndexItemList);
            JSONArray myJsonArray2 = new JSONArray(myServiceContentItemList);

            result.put("itemhxzbInVoList", myJsonArray1);
            result.put("signServiceContentItemsList", myJsonArray2);*/


        return result;
    }

    public static Map<String, Object> getdeleteSignInfo(Context context, String sYear, String servicePlanId,
                                                        ArrayList<DeletehxzbInVoListBeans> myDeletehxzbInVoListBeans, String serviceContentItemsGkey,
                                                        String signDetailKey, String hadExecTimes) {
        Map<String, Object> result = new HashMap<>();


        String loginName = IdentityManager.getLoginSuccessUsername(context);
        String encodedsYear = Util.getBase64String(sYear);
        //  String encodedsSignKey = Util.getBase64String(sSignKey);
        /*result.put(Constants.LOGIN_USERNAME_KEY, Util.getBase64String(loginName));
        result.put("signPageYear", "MjAxNw==");
        result.put("servicePlanId", "Nzk5NjNFNTUtMUNDMS00NzZBLUIyM0UtOUZBMzI5RDlBNDE4");*/
        String encodedsservicePlanId = Util.getBase64String(servicePlanId);

        result.put(Constants.LOGIN_USERNAME_KEY, Util.getBase64String(loginName));
        result.put("signPageYear", encodedsYear);
        result.put("servicePlanId", encodedsservicePlanId);
        String encodedSign = Util.getBase64String(Version.SIGN);
        result.put("itemhxzbInVoList", myDeletehxzbInVoListBeans);
        result.put(Constants.SIGN_KEY, encodedSign);
        result.put("edit", "Mw==");

        result.put("serviceContentItemsGkey", Util.getBase64String(serviceContentItemsGkey));
        result.put("signDetailKey", Util.getBase64String(signDetailKey));
        result.put("hadExecTimes", Util.getBase64String(hadExecTimes));
        return result;
    }


    public static Map<String, String> getdeleteReservation(Context context, String docUserId, String servicePlanId, String syear) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("docUserId", Util.getBase64String(docUserId));
        result.put("servicePlanId", Util.getBase64String(servicePlanId));
        result.put("year", Util.getBase64String(syear));

        return result;
    }


    public static Map<String, String> saveReserver(Context context, String signDetailID, String serviceContentItemsID,
                                                   String serviceContentID, String userName,
                                                   String doctorCode, String orgID,
                                                   String docUserID, String reservationTime, String serverPlaceType, String serverPlaceOther,
                                                   String signPageyear
    ) {

        //   Map<String, String> result = new HashMap<>();
        Map<String, String> result = getBaseRequestMap(context);
        result.put("signDetailID", Util.getBase64String(signDetailID));
        result.put("serviceContentItemsID", Util.getBase64String(serviceContentItemsID));
        result.put("serviceContentID", Util.getBase64String(serviceContentID));
        result.put("userName", Util.getBase64String(userName));
        result.put("doctorCode", Util.getBase64String(doctorCode));
        result.put("orgID", Util.getBase64String(orgID));
        result.put("docUserID", Util.getBase64String(docUserID));
        result.put("reservationServerPlaceType", Util.getBase64String(serverPlaceType));
        result.put("reservationServerPlaceOther", Util.getBase64String(serverPlaceOther));
        result.put("reservationTime", Util.getBase64String(reservationTime));
        result.put("year", Util.getBase64String(signPageyear));


        return result;
    }

    public static Map<String, String> postDmData(Context context,
                                                 String taskId,
                                                 String id,
                                                 String no,
                                                 String flwDate,
                                                 String flwType,
                                                 String followDownReason,
                                                 String symptom,
                                                 String otherSymptom,
                                                 String sbp,
                                                 String dbp,
                                                 String heartRate,
                                                 String height,
                                                 String weight,
                                                 String weightTarget,
                                                 String bmi,
                                                 String bmiTarget,
                                                 String otherSign,
                                                 String dorsalisPedisL,
                                                 String dorsalisPedisR,
                                                 String dailySmoke,
                                                 String dailySmokeTarget,
                                                 String dailyDrink,
                                                 String dailyDrinkTarget,
                                                 String exercise,
                                                 String exerciseTimes,
                                                 String nextExercise,
                                                 String nextExerciseTimes,
                                                 String staple,
                                                 String stapleAim,
                                                 String psychic,
                                                 String patientCompliance,
                                                 String fpg,
                                                 String pbg,
                                                 String accessoryExaminationHba1c,
                                                 String accessoryExaminationDate,
                                                 String drugCompliance,
                                                 String adverseReaction,
                                                 String lowSugarReaction,
                                                 String sort,
                                                 String insulinType,
                                                 String nextFlwDate,
                                                 String doctorCode,
                                                 String name,
                                                 String frequency,
                                                 String singleDose,
                                                 String unit,
                                                 String isReferral,
                                                 String referralReason,
                                                 String referralHosp,
                                                 String otherAdverseReaction,
                                                 List<UseDrugInfo> useDrugInfoList,
                                                 List<UseDrugInfo> ydsDrugInfoList,
                                                 String personid,
                                                 String doctor,
                                                 String idNo
    ) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = postDMStringForFingertip(
                taskId, id, no, flwDate, flwType, followDownReason, symptom, otherSymptom, sbp, dbp, heartRate, height, weight, weightTarget, bmi, bmiTarget, otherSign,
                dorsalisPedisL, dorsalisPedisR, dailySmoke, dailySmokeTarget, dailyDrink,
                dailyDrinkTarget, exercise, exerciseTimes, nextExercise, nextExerciseTimes,
                staple, stapleAim, psychic, patientCompliance, fpg, pbg,
                accessoryExaminationHba1c, accessoryExaminationDate, drugCompliance,
                adverseReaction, lowSugarReaction, sort, insulinType, nextFlwDate,
                doctorCode, name, frequency, singleDose, unit, isReferral, referralReason,
                referralHosp, otherAdverseReaction, useDrugInfoList, ydsDrugInfoList, personid, doctor, idNo
        );
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D021"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }

    public static Map<String, String> postHighBloodData(Context context,
                                                        String taskId,
                                                        String name,
                                                        String id,
                                                        String no,
                                                        String flwDate,
                                                        String flwType,
                                                        String followDownReason,
                                                        String symptom,
                                                        String otherSymptom,
                                                        String sbp,
                                                        String dbp,
                                                        String heartRate,
                                                        String height,
                                                        String weight,
                                                        String weightTarget,
                                                        String bmi,
                                                        String bmiTarget,
                                                        String otherSign,
                                                        String dailySmoke,
                                                        String psychic,
                                                        String patientCompliance,
                                                        String accessoryExamination,
                                                        String drugCompliance,
                                                        String adverseReaction,
                                                        String otherAdverseReaction,
                                                        String sort,
                                                        String nextFlwDate,
                                                        String doctorCode,
                                                        List<UseDrugInfo> useDrugInfoList,
                                                        String exercise, String exerciseTimes, String nextExercise, String nextExerciseTimes, String isReferral, String referralReason, String referralHosp
            , String dailySmokeTarget, String dailyDrink, String dailyDrinkTarget, String saltUptake, String saltUptakeTarget, String pp, String personid, String doctor, String idnum

    ) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = postHighBodyStringForFingertip(taskId, name, id, no, flwDate, flwType, followDownReason, symptom, otherSymptom, sbp, dbp,
                heartRate, height, weight, weightTarget, bmi, bmiTarget, otherSign, dailySmoke, psychic, patientCompliance,
                accessoryExamination, drugCompliance, adverseReaction, otherAdverseReaction, sort, nextFlwDate,
                doctorCode, useDrugInfoList, exercise, exerciseTimes, nextExercise, nextExerciseTimes, isReferral, referralReason, referralHosp
                , dailySmokeTarget, dailyDrink, dailyDrinkTarget, saltUptake, saltUptakeTarget
                , pp, personid, doctor, idnum);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D011"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }

    public static Map<String, String> getDiabetesDataList(Context context, String no, String name, String idNo, int pageIndex, int pageSize) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getHighBodyStringForFingertip(no, name, idNo, pageIndex, pageSize);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D02"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }


    public static Map<String, String> getSignDoctorList(Context context, String params, String name, int policyYear, int pageIndex, int pageSize) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getSignDoctorBodyStringForFingertip(params, name, policyYear, pageIndex, pageSize);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }


    public static Map<String, String> getDetailPeopleInfo(Context context, int policyYear, String out, String personId, String signState) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
//        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getDetailPeopleInfoForFingertip(policyYear, out, personId, signState);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("E013"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }

    public static Map<String, String> getHighBloodDataList(Context context, String no, String name, String idNo, int pageIndex, int pageSize) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
//        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getHighBodyStringForFingertip(no, name, idNo, pageIndex, pageSize);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D01"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }

    public static Map<String, String> getSelectChargeDataList(Context context, String no, String isCharged, int pageIndex, int pageSize, String visitTimeStart, String visitTimeEnd) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = getSelectChargeStringForFingertip(no, isCharged, pageIndex, pageSize, visitTimeStart, visitTimeEnd);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C025"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }

    public static Map<String, String> getPersonDataList(Context context, String no, String name, String idNo, String signState, int pageIndex, int pageSize, String tempString) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
//        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getPersonStringForFingertip(no, name, idNo, signState, pageIndex, pageSize, tempString);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
//        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D01"));
//         String body = getPersonStringForFingertip(no, name,idNo,signState,pageIndex,pageSize);
//        String sign = Util.getNonloginMethodSignString(context,body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D00"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        result.put(Constants.LIMIT_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageIndex));
//        result.put(Constants.PAGE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageSize));
        return result;
    }


    public static String getPersonStringForFingertip(String no, String username, String idNo, String signState, int pageIndex, int pageSize, String tempString) {
        Map<String, Object> map = new HashMap<>();

        map.put("searchText", tempString);
        map.put(Constants.LIMIT_KEY_FOR_FINGERTIP, pageSize);
        map.put(Constants.PAGE_KEY_FOR_FINGERTIP, pageIndex);
        String result = JSON.toJSONString(map);
        return result;
    }


    public static Map<String, String> getSocialDataList(Context context, String userName, String cardNo, String name, int pageIndex, int pageSize) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("userName", "admin");
        result.put("method", "poorObj.queryPoorObjList");
        result.put("cardNo", cardNo);
        result.put("name", name);
        result.put("pageIndex", String.valueOf(pageIndex));
        result.put("pageSize", String.valueOf(pageSize));
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getSocialHistoryList(Context context, String cardNoOrName, int pageIndex, int pageSize) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "reliefHis.queryReliefHisList");
        result.put("cardNoOrName", cardNoOrName);
        result.put("areaCode", Identity.srcInfo.getAreaId());
        String areaLevel = Identity.srcInfo.getAreaLevel();
        result.put("areaLevel", areaLevel);
        result.put("pageIndex", String.valueOf(pageIndex));
        result.put("pageSize", String.valueOf(pageSize));
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getSocialDetail(Context context, String batchNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("batchNo", batchNo);
        result.put("method", "poorObj.queryPoorObj");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }

    public static Map<String, String> getSubmitMapLocationForSocialDetail(Context context, String batchNo, String address,
                                                                          String lat, String lng, String mapId) {
        Map<String, String> result = getBaseRequestMap(context);
        String uuid = UUID.randomUUID().toString();
        result.put("batchNo", batchNo);
        result.put("address", address);
        result.put("lat", lat);
        result.put("lng", lng);
        result.put("mapId", mapId);
        //todo method值需要修改
        result.put("method", "poorObj.saveMap");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }

    public static Map<String, String> getprogressList(Context context, String batchCard) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("batchCard", batchCard);
        result.put("method", "appSearchService.queryProgressList");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getSocialHisList(Context context, String cardNoOrName) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("cardNoOrName", cardNoOrName);
        result.put("method", "reliefHis.queryReliefHis");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getHistoryDetail(Context context, String cardNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("cardNo", cardNo);
        result.put("method", "reliefHis.queryReliefHis");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }


    public static Map<String, String> getAddFindZancun(Context context, String name, String sCodeshi, String sCodexian,
                                                       String sCodequ, String sCodejiedao, String sphone, String sadress, String sreason, String base64, String sFileId, String sbatchNo, String sId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appDiscover.saveDiscover");
        result.put("userName", name);
        result.put("id", sId);
        result.put("countyCode", sCodexian);
        result.put("cityCode", sCodeshi);
        result.put("officeCode", sCodequ);
        result.put("communityCode", sCodejiedao);
        result.put("familyAddress", sadress);
        result.put("sriReason", sreason);
        result.put("discoverPhone", sphone);
        //0是暂存 1是待办
        result.put("status", "0");
        //2代表手机
        result.put("region", "2");
        result.put("batchNo", sbatchNo);
        result.put("picBase64", base64);
        result.put("fileIds", sFileId);


        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getAddFindSave(Context context, String name, String sCodeshi, String sCodexian,
                                                     String sCodequ, String sCodejiedao, String sphone, String sadress, String sreason, String base64, String sFileId, String sbatchNo, String sId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appDiscover.saveDiscover");
        result.put("userName", name);
        result.put("id", sId);
        result.put("countyCode", sCodexian);
        result.put("cityCode", sCodeshi);
        result.put("officeCode", sCodequ);
        result.put("communityCode", sCodejiedao);
        result.put("familyAddress", sadress);
        result.put("sriReason", sreason);
        result.put("discoverPhone", sphone);
        //0是暂存 1是待办
        result.put("status", "1");
        //2代表手机
        result.put("region", "2");
        result.put("batchNo", sbatchNo);
        result.put("picBase64", base64);
        result.put("fileIds", sFileId);


        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getDetailFind(Context context, String name, String batchNo, String isMine) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appDiscover.discoverView");
        result.put("batchNo", batchNo);
        result.put("isMine", isMine);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getDeleteFind(Context context, String batchNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("batchNo", batchNo);
        result.put("method", "appDiscover.discoverDelete");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }


    public static Map<String, String> getProgressDetail(Context context, String itemCode, String batcnNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("itemCode", itemCode);
        result.put("batcnNo", batcnNo);
        result.put("method", "appSearchService.queryProgressView");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }


    public static Map<String, String> getHandleFind(Context context, String name, String batchNo, String discoverId, String module1, String remark, String date) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appDiscover.discoverHandle");
        result.put("batchNo", batchNo);
        result.put("act", module1);
        result.put("editDate", date);
        result.put("userName", name);
        result.put("remarks", remark);
        result.put("discoverId", discoverId);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getBaseFragmentData(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryExamineViewDetail");
        result.put("id", id);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getFamiltFragmentData(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryExamineViewFamilyList");
        result.put("id", id);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getFileFragmentData(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryExamineViewFileList");
        result.put("id", id);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getServeryFragmentData(Context context, String gfamliyId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryRhdcList");
        result.put("gfamliyId", gfamliyId);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getDemocraticData(Context context, String gfamliyId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryMzpyList");
        result.put("gfamliyId", gfamliyId);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getAddServeryData(Context context, String gId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryRhdcObjectInfo");
        result.put("gId", gId);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getFaimilydata(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryExamineViewFamilyDetail");
        result.put("id", id);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getDemocraticdata(Context context, String gId) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.queryMzpyObjectInfo");
        result.put("gId", gId);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }


    public static Map<String, String> saveVideoData(Context context, String videoName, String byteArray) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.uploadVideo");
        result.put("videoName", videoName);
        result.put("byteArray", byteArray);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> saveImageView(Context context, String fileName, String fileBase64) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.uploadPicture");
        result.put("fileName", fileName);
        result.put("fileBase64", fileBase64);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }


    public static Map<String, String> saveBaseHouseInfo(Context context, String gfamilyid, String gid, String diaochadate, String diaocharenname, String diaochajielun, String fileSize, List<ImageListData> dataArrayList, String longitude, String latitude, String diaochaaddress, String cardno) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.saveRhdcObjectInfo");
        result.put("gfamilyid", gfamilyid);
        result.put("gid", gid);
        result.put("diaochadate", diaochadate);
        result.put("diaocharenname", diaocharenname);
        result.put("diaochajielun", diaochajielun);
        result.put("longitude", longitude);
        result.put("latitude", latitude);
        result.put("diaochaaddress", diaochaaddress);
        result.put("cardno", cardno);
        result.put("fileSize", dataArrayList.size() + "");
        for (int i = 0; i < dataArrayList.size(); i++) {
            result.put("filePath_" + i, dataArrayList.get(i).getFilePath_0());
            result.put("fileName_" + i, dataArrayList.get(i).getFileName_0());
            result.put("addDate_" + i, dataArrayList.get(i).getAddDate_0());
            result.put("fileType_" + i, dataArrayList.get(i).getFileType_0());
//            result.put("fileType_" + i, "0");
            result.put("videoUrl_" + i, dataArrayList.get(i).getVideoUrl_0());
        }
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> saveDemocraticInfo(Context context, String gfamilyid, String gid, String pingyiDate, String pingyiResult, String pingyiRenName, String fileSize, String pingyiMemo, List<ImageListData> dataArrayList) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "dcpyData.saveMzpyObjectInfo");
        result.put("gfamilyid", gfamilyid);
        result.put("gid", gid);
        result.put("pingyiDate", pingyiDate);
        result.put("pingyiResult", pingyiResult);
        result.put("pingyiRenName", pingyiRenName);
        result.put("fileSize", dataArrayList.size() + "");
        result.put("pingyiMemo", pingyiMemo);
        for (int i = 0; i < dataArrayList.size(); i++) {
            result.put("filePath_" + i, dataArrayList.get(i).getFilePath_0());
            result.put("fileName_" + i, dataArrayList.get(i).getFileName_0());
            result.put("addDate_" + i, dataArrayList.get(i).getAddDate_0());
            result.put("fileType_" + i, dataArrayList.get(i).getFileType_0());
//            result.put("fileType_" + i, "1");
            result.put("videoUrl_" + i, dataArrayList.get(i).getVideoUrl_0());
        }
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }


    public static Map<String, Object> saveTest(List<ByteArrayInputStream> temp, String videoName) {
        Map<String, Object> result = new HashMap<>();
        String timestamp = Util.getTimestampLeading10CharactersString();
        result.put(Constants.API_KEY, getProcessedString(Constants.API_KEY_VALUE));
        result.put(Constants.TIME_STAMP_KEY, getProcessedString(timestamp));
        result.put(Constants.TOKEN_KEY, getProcessedString(Util.getEncryptedDigest(timestamp)));
        result.put("method", "dcpyData.uploadVideo");
        result.put("videoName", videoName);
        result.put("byteArray", temp);
        String bodyJson = JSON.toJSONString(result);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getUrbanbaseinfoSave(Context context, String sname, String editor, String sidcard, String sexcode,
                                                           String sbirth, String sphone, String sapplytime, String skaihuren, String sbankaccount,
                                                           String shujiadress, String sfamilyadress, String sapplyreason, String jinancode,
                                                           String sdiaochareason, String sremark, String officeCode, String communityCode,
                                                           String registerType, String registerProperty, String familyType, String bankName,
                                                           String poorReason, String copyBatchNo, String ItemId, String jinanreason, String arearLevel

    ) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("method", "appBaseinfo.saveApplyBaseInfoSri");

        result.put("isPersons", "0");
        result.put("batchNo", copyBatchNo);
        result.put("officeCode", officeCode);
        result.put("communityCode", communityCode);
        result.put("name", sname);
        result.put("editor", editor);
        result.put("cardNo", sidcard);
        result.put("sexCode", sexcode);
        result.put("birthday", sbirth);
        result.put("phone", sphone);
        result.put("editDate", sapplytime);
        result.put("familyNumber", "1");
        result.put("registerType", registerType);
        result.put("registerProperty", registerProperty);
        result.put("familyType", familyType);
        result.put("accountHolder", skaihuren);
        result.put("bankName", bankName);
        result.put("bankCardno", sbankaccount);
        result.put("farmerCode", "");
        result.put("familyPoorType", "family_poor_type_1");
        result.put("registerAddress", shujiadress);
        result.put("familyAddress", sfamilyadress);
        result.put("sriReason", sapplyreason);
        result.put("itemCode", "ITEM_DIBAO_CZ");
        result.put("itemCode", ItemId);
        result.put("poorReason", poorReason);
        result.put("isTrouble", jinancode);

        if (jinancode.equals("0")) {

        } else {
            result.put("troubleReason", jinanreason);
        }

        result.put("investiSituation", sdiaochareason);
        result.put("remarks", sremark);
        result.put("areaLevel", arearLevel);


        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }

    public static Map<String, String> getUrbanaddfamilySave(Context context, String sname, String sidcard, String sexcode,
                                                            String sbirth, String relation, String politicalStatus, String nation, String isDeformity,
                                                            String employmentStatus, String isDisease, String canhecanbao, String incomeStatus,
                                                            String workStatus, String socialSecurityNo, String healthStatus, String maritalStatus, String myBatchNo, String sPic, String myid, String myPic


    ) {
        Map<String, String> result = getBaseRequestMap(context);

        result.put("method", "appFamilyMember.saveFamilyMemberSri");
        result.put("batchNo", myBatchNo);
        result.put("name", sname);
        result.put("cardNo", sidcard);
        result.put("sex", sexcode);
        result.put("birthDate", sbirth);
        result.put("relation", relation);
        result.put("politicalStatus", politicalStatus);
        result.put("nation", nation);
        result.put("isDeformity", isDeformity);
        result.put("employmentStatus", employmentStatus);
        result.put("isDisease", isDisease);
        result.put("canhecanbao", canhecanbao);
        result.put("incomeStatus", incomeStatus);
        result.put("workStatus", workStatus);
        result.put("socialSecurityNo", socialSecurityNo);
        result.put("healthStatus", healthStatus);
        result.put("maritalStatus", maritalStatus);
        result.put("pic", myPic);
        result.put("id", myid);
        result.put("base64", sPic);


        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }

    public static Map<String, String> getDeleteUrban(Context context, String batchNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("batchNo", batchNo);
        result.put("method", "appBatch.delApplyDraftSri");
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;

    }


    public static Map<String, String> getUrbanLowFujianMap(Context context, String batchNo) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appUploadFile.queryFileListSri");
        //   result.put("batchNo", "20180131083618759");
        result.put("batchNo", batchNo);

        return result;
    }

    public static Map<String, String> getfamilybianji(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appFamilyMember.queryFamilyMemberSri");

        result.put("id", id);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }


    public static Map<String, String> getUrbanLowFujianTakeMap(Context context, String myfileCode, String myItemid, String sBase64, String batchNo, String date, int myCoun) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appUploadFile.uploadFileSri");
        result.put("fileName", "申请书" + myCoun + ".PNG");
        result.put("fileSize", "100");
        result.put("createTime", date);
        result.put("fileCode", myfileCode);
        //    result.put("batchNo", "20180131083618759");
        result.put("batchNo", batchNo);
        result.put("itemId", myItemid);
        result.put("base64", sBase64);

        return result;
    }

    public static Map<String, String> getUrbanLowFamilydeleteMap(Context context, String id) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appFamilyMember.delFamilyMemberSri");
        result.put("id", id);

        return result;
    }


    public static Map<String, String> getUrbanLowBaseinfobianji(Context context, String mybatchNo) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "appBatch.editApplyDraftSri");
        result.put("batchNo", mybatchNo);
        //  result.put("batchNo", "20171025020344501" );
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);
        return map1;
    }

    public static Map<String, String> getUrbanLowfujiandeleteMap(Context context, String id, String mybatchNo) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appUploadFile.delFileSri");
        result.put("fileId", id);
        //  result.put("batchNo", "20180131083618759");
        result.put("batchNo", mybatchNo);

        return result;
    }

    public static Map<String, String> getUrbanLowfujianbianjiMap(Context context, String fileId,
                                                                 String fileName, String fileType, String batchId) {
        Map<String, String> result = getBaseRequestMap(context);


        result.put(Constants.METHOD_KEY, "appUploadFile.modifyFileNameSri");
        result.put("fileId", fileId);
        result.put("fileName", fileName);
        result.put("fileType", fileType);
        result.put("fileTypeName", "PNG");
        result.put("batchId", batchId);

        return result;
    }

    public static Map<String, String> getLogininitArea(Context context, String lasttime) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadAreas");
        result.put("lastTime", lasttime);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLogininitSalvation(Context context, String lasttime) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadItems");
        result.put("lastTime", lasttime);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLogininitDictionary(Context context, String lasttime) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadDicts");
        result.put("lastTime", lasttime);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }

    public static Map<String, String> getLogininitOrganization(Context context, String lasttime) {
        Map<String, String> result = getBaseRequestMap(context);
        result.put("method", "initData.loadUnits");
        result.put("lastTime", lasttime);
        String bodyJson = JSON.toJSONString(result);
        Map<String, String> map1 = new HashMap<>();
        map1.put("sRequest", bodyJson);

        return map1;
    }


    public static Map<String, String> postHighBloodData1(Context context, PersonPostBean personPostBean1

    ) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
        String body = postHighBodyStringForFingertip1(personPostBean1
        );
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D001"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));

        return result;
    }


    public static String postHighBodyStringForFingertip1(PersonPostBean personPostBean1) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", Util.isMyEmpty1(personPostBean1.getId()));
        map.put("name", Util.isMyEmpty1(personPostBean1.getName()));
        map.put("no",  Util.isMyEmpty1(personPostBean1.getNo()));
        map.put("idNo", personPostBean1.getIdNo());
        map.put("sexCode", personPostBean1.getSexCode());
        map.put("sexName", personPostBean1.getSexName());
        map.put("birthday", personPostBean1.getBirthday());
        map.put("workUnit", personPostBean1.getWorkUnit());
        map.put("contactPhone", personPostBean1.getContactPhone());
        map.put("phone", personPostBean1.getPhone());
        map.put("contactName", personPostBean1.getContactName());
        map.put("residentType", personPostBean1.getResidentType());
        map.put("nationCode", personPostBean1.getNationCode());
        map.put("nationName", personPostBean1.getNationName());
        map.put("abobloodCode", personPostBean1.getAbobloodCode());
        map.put("abobloodName", personPostBean1.getAbobloodName());
        map.put("rhbloodName", personPostBean1.getRhbloodName());
        map.put("cultureCode", personPostBean1.getCultureCode());
        map.put("cultureName", personPostBean1.getCultureName());
        map.put("occupationCode", personPostBean1.getOccupationCode());
        map.put("occupationName", personPostBean1.getOccupationName());
        map.put("marryCode", personPostBean1.getMarryCode());
        map.put("insType", personPostBean1.getInsType());
        map.put("exposureHistor", personPostBean1.getExposureHistor());
        map.put("fatherDisease", personPostBean1.getFatherDisease());
        map.put("motherDisease", personPostBean1.getMotherDisease());
        map.put("brothersDisease", personPostBean1.getBrothersDisease());
        map.put("childrenDisease", personPostBean1.getChildrenDisease());
        map.put("hereditaryHistory", personPostBean1.getHereditaryHistory());
        map.put("hereditaryName", personPostBean1.getHereditaryName());
        map.put("deformitySituation", personPostBean1.getDeformitySituation());
        map.put("exhaustFacilit", personPostBean1.getExhaustFacilit());
        map.put("fuelType", personPostBean1.getFuelType());
        map.put("drinkingWater", personPostBean1.getDrinkingWater());
        map.put("toilet", personPostBean1.getToilet());
        map.put("poultryEnclosu", personPostBean1.getPoultryEnclosu());
        map.put("registerAddress", personPostBean1.getRegisterAddress());
        map.put("zuCode", personPostBean1.getZuCode());
        map.put("areaFulladdress", "贵州省六盘水市六枝特区龙河镇红旗村民委员会");
        map.put("archivingDoctorDate", personPostBean1.getArchivingDoctorDate());
        map.put("archivingDoctorCode", personPostBean1.getArchivingDoctorCode());
        map.put("archivingDoctorName", personPostBean1.getArchivingDoctorName());
//
        map.put("allergyHistories", personPostBean1.getAllergyHistories());
        map.put("pastHistories", personPostBean1.getPastHistories());

//        List<Map<String, Object>>  PhMedicine=new ArrayList<>();
//        for (int i=0;i<personPostBean1.getAllergyHistories().size();i++){
//            PhMedicine.add(getUesDrugStringForFingertip2(personPostBean1.getAllergyHistories().get(i)));
//        }
//        map.put("allergyHistories",PhMedicine);
//
//
//        List<Map<String, Object>>  PhMedicine2=new ArrayList<>();
//        for (int i=0;i<personPostBean1.getPastHistories().size();i++){
//            PhMedicine2.add(getUesDrugStringForFingertip3(personPostBean1.getPastHistories().get(i)));
//        }
//        map.put("pastHistories",PhMedicine2);


        String result = JSON.toJSONString(map);
        return result;
    }


    public static Map<String, String> getPersonData2(Context context, String id) {

        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
//        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getPersonStringForFingertip2(id);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D002"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        result.put(Constants.LIMIT_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageIndex));
//        result.put(Constants.PAGE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageSize));
        return result;
    }

    public static String getPersonStringForFingertip2(String id) {
        Map<String, Object> map = new HashMap<>();

        map.put("id ", id);

        String result = JSON.toJSONString(map);
        return result;
    }

    public static Map<String, Object> getUesDrugStringForFingertip2(PersonallergyHistoriesBean useDrugInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("allergyHistoryCode", useDrugInfo.getAllergyHistoryCode());


//        String result = JSON.toJSONString(map);
        return map;
    }

    public static Map<String, Object> getUesDrugStringForFingertip3(PersonPastHistoryBean useDrugInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeCode", useDrugInfo.getTypeCode());
        map.put("pastHistoryCode", useDrugInfo.getPastHistoryCode());


//        String result = JSON.toJSONString(map);
        return map;
    }

    public static Map<String, String> getkuncunDataList(Context context, String departmentCode, String drugId, String stockId, String avail, String expire, int pageIndex, int pageSize, String tempString) {
        String timestampString = Util.getTimestampString();
        Map<String, String> result = getBaseRequestMapNonloginMethodForFingertip(context, timestampString);
//        Map<String, String> result = getBaseRequestMapLoginMethodForFingertip(context, timestampString);
        String body = getkuncunStringForFingertip(departmentCode, drugId, stockId, avail, expire, pageIndex, pageSize, tempString);
        String sign = Util.getNonloginMethodSignString(context, body, timestampString);
//        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("D01"));
//         String body = getPersonStringForFingertip(no, name,idNo,signState,pageIndex,pageSize);
//        String sign = Util.getNonloginMethodSignString(context,body, timestampString);
        result.put(Constants.CODE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip("C03"));
        result.put(Constants.BODY_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(body));
        result.put(Constants.SIGN_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(sign));
//        result.put(Constants.LIMIT_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageIndex));
//        result.put(Constants.PAGE_KEY_FOR_FINGERTIP, getProcessedStringForFingertip(pageSize));
        return result;
    }

    public static String getkuncunStringForFingertip(String departmentCode, String drugId, String stockId, String avail, String expire, int pageIndex, int pageSize, String tempString) {
        Map<String, Object> map = new HashMap<>();

        map.put("departmentCode", departmentCode);
        map.put("drugId", drugId);
        map.put("stockId", stockId);
        map.put("avail", avail);
        map.put("expire", expire);
        map.put("searchText", tempString);


        map.put(Constants.LIMIT_KEY_FOR_FINGERTIP, pageSize);
        map.put(Constants.PAGE_KEY_FOR_FINGERTIP, pageIndex);
        String result = JSON.toJSONString(map);
        return result;
    }

}
