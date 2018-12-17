package com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform;


import com.jqsoft.fingertip_health.bean.AdviceBean;
import com.jqsoft.fingertip_health.bean.AdviceDetailBean;
import com.jqsoft.fingertip_health.bean.AdviceHuifuBean;
import com.jqsoft.fingertip_health.bean.CanheDetailBean;
import com.jqsoft.fingertip_health.bean.CanheListBean;
import com.jqsoft.fingertip_health.bean.ClientPersonSignApply;
import com.jqsoft.fingertip_health.bean.CompensateDetailBean;
import com.jqsoft.fingertip_health.bean.CompensateFeeListBean;
import com.jqsoft.fingertip_health.bean.CompensateListBean;
import com.jqsoft.fingertip_health.bean.CoreIndexBeanList;
import com.jqsoft.fingertip_health.bean.DemoCraticBaseBean;
import com.jqsoft.fingertip_health.bean.DemocraticAppraisalBean;
import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.EvaluationInfos;
import com.jqsoft.fingertip_health.bean.FamilyDetailbeans;
import com.jqsoft.fingertip_health.bean.FamilyMemberListBean;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.HouseFileBean;
import com.jqsoft.fingertip_health.bean.HouseHoldBasebean;
import com.jqsoft.fingertip_health.bean.HouseHoldSeveryBean;
import com.jqsoft.fingertip_health.bean.HouseHoldSurveyBean;
import com.jqsoft.fingertip_health.bean.HouseHoldeBackBean;
import com.jqsoft.fingertip_health.bean.HttpResultTestBean;
import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.MedicalInstitutionDetailBean;
import com.jqsoft.fingertip_health.bean.MedicalInstitutionListBean;
import com.jqsoft.fingertip_health.bean.MineBean;
import com.jqsoft.fingertip_health.bean.ModifyExecuedBean;
import com.jqsoft.fingertip_health.bean.MyFindResultBean;
import com.jqsoft.fingertip_health.bean.MyMessageBean;
import com.jqsoft.fingertip_health.bean.MyMessageDetailBean;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.PcodeDataBean;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.PeopleSignInfoBean;
import com.jqsoft.fingertip_health.bean.PersonDoctorMessageInfo;
import com.jqsoft.fingertip_health.bean.PersonInfoList;
import com.jqsoft.fingertip_health.bean.PersonMessage;
import com.jqsoft.fingertip_health.bean.PersonSignAgreement;
import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.ProgressBean;
import com.jqsoft.fingertip_health.bean.ProgressDetailbean;
import com.jqsoft.fingertip_health.bean.QuestionBean;
import com.jqsoft.fingertip_health.bean.ReceptionDetailNewListBean;
import com.jqsoft.fingertip_health.bean.ReservationBeanList;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.bean.SignTeamBean;
import com.jqsoft.fingertip_health.bean.SocailHistoryDetailsBean;
import com.jqsoft.fingertip_health.bean.SocialAssistanceObjectBean;
import com.jqsoft.fingertip_health.bean.SocialDetailBean;
import com.jqsoft.fingertip_health.bean.SocialListHistoryBean;
import com.jqsoft.fingertip_health.bean.SubmitMapLocationResultBean;
import com.jqsoft.fingertip_health.bean.TownLevelMedicalInstitutionBeanList;
import com.jqsoft.fingertip_health.bean.Uploadpic;
import com.jqsoft.fingertip_health.bean.UrbanbaseInfoSaveBean;
import com.jqsoft.fingertip_health.bean.VideoBackBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.AboutInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.CollectionUrlTypeBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.FamilyEconomyCheckProjectCheckBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.GuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HeatmapBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuidePostBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionCharacterNameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MedicalAssistantMoneyConstitutionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValuePercentBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NotificationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonCollectionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonalInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolityBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.QuestionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReliefItemBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApprovePovertyReasonBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApproveRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApproveTrendStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceTrendStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.TempDisasterAssistancePercentageBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilybianjiBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianSaveBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowInsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanbaseInfobianjiBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultMapBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonStreetOrAboveLocationBean;
import com.jqsoft.fingertip_health.bean.resident.FamilyMemberBean;
import com.jqsoft.fingertip_health.bean.resident.RemindAndMessageBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginBean;
import com.jqsoft.fingertip_health.bean.response.ChatListWrapperBean;
import com.jqsoft.fingertip_health.bean.response.FriendResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.LoginResultBean;
import com.jqsoft.fingertip_health.bean.response.MedicalPersonDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.MyInfoBean;
import com.jqsoft.fingertip_health.bean.response.SignAndHonourAgreementResultBean;
import com.jqsoft.fingertip_health.bean.response.SignedResidentDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.TreatmentListResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response_new.AppointmentRegistrationResultBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultBean;
import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
import com.jqsoft.fingertip_health.bean.response_new.IntelligentHonourAgreementOverviewResultBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.bean.response_new.OnlineConsultationResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignClientServiceAssessResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceIncomeResultBean;

import java.util.List;
import java.util.Map;

import dagger.Module;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by quantan.liu on 2017/3/27.
 */

@Module
public interface GCAService {
//    String API_SIGNED_DOCTOR_CLIENT_DATA = "http://c.m.163.com/nc/article/";

//    @FormUrlEncoded
//    @POST("apis/getUserInfo")
//    @Headers("Content-Type: application/json")
//    Observable<HttpResultBaseBean<LoginResultBean2>> login(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>>> getSubsistenceVarianceTrendStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getSubsistenceVarianceRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceApproveRankingStatisticsBean>>> getSubsistenceApproveRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceApproveTrendStatisticsBean>>> getSubsistenceApproveTrendStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceApprovePovertyReasonBean>>> getSubsistenceApprovePovertyReasonStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getSubsistenceArchiveRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getInstitutionRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>>> getSubsistenceArchiveTrendStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>>> getSubsistenceAccountTrendStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>>> getSubsistenceAccountIncreaseRatioStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NameValueBean>>> getSubsistenceArchiveHealthClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>>> getInstitutionCharacterClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NameValuePercentBean>>> getInstitutionServerClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NameValuePercentBean>>> getInstitutionLegalPersonClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NameValueBean>>> getSubsistenceArchiveAbilityClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NameValueBean>>> getSubsistenceArchiveAgeClassificationStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getSubsistenceAccountRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getSubsistenceStandardRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getSubsistenceStandardAverageRankingStatisticsList(@FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<MedicalAssistantMoneyConstitutionBean>>> getMedicalAssistantMoneyConstitutionStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getMedicalAssistantDirectOutcomeStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getMedicalAssistantFinanceAssuranceStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getTempDisasterAssistantStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<TempDisasterAssistancePercentageBean>>> getTempDisasterAssistancePercentageStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getFamilyEconomyCheckRankingStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getFamilyEconomyCheckTrendStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>>> getFamilyEconomyCheckShareIndexStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<FamilyEconomyCheckProjectCheckBean>>> getFamilyEconomyCheckProjectCheckStatisticsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>>> getAmbientPersonList(@FieldMap Map<String, String> map);
//    Observable<GCAHttpResultBaseBean<List<PersonLocationBean>>> getAmbientPersonList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<HeatmapBean>> getHeatmapBean(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<UrbanLowFamilyBean>>> getUrbanLowFamilyList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<UrbanLowFujianBean>>> getUrbanLowfujianList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<UrbanLowFujianSaveBean>> getUrbanLowfujianTakeList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<HttpResultEmptyBean>> getUrbanLowfamilydeleteList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<HttpResultEmptyBean>> getUrbanLowfujiandeleteList(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<HttpResultEmptyBean>> getUrbanLowfujianbianjiList(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<UrbanLowInsBean>>> getUrbanLowInsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> DeleteUrbanSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<NotificationBean>>> getNotificationList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<HouseHoldBasebean>>> gethouseHoldBasedata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<FamilyMemberListBean>>> gethouseFamilydata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<HouseFileBean>>> gethouseFliedata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<HouseHoldSeveryBean>>> gethouseserverydata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<DemocraticAppraisalBean>>> getdemocraticdata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<PolicyBean>>> getPolicyList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<PolityBean>>> getPolityList(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<ReceptionDetailNewListBean>> getReceptionDetailNewList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<PersonalInfoBean>> getPersonalInfo(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<AboutInfoBean>> getAboutlInfo(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<ReceptionBean>>> getReception(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<PersonCollectionBean>>> getPersonCollection(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<AdviceBean>>> getAdvice(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<ReceptionListBean>>> getReceptionList(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<QuestionBean>>> getQuestionList(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<MyMessageBean>>> getMyMessage(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<GuideBean>>> getGuideList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<MyMessageDetailBean>> getMyMessageDetail(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<QuestionDetailBean>> getQuestionDetail(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<AdviceDetailBean>> getAdviceDetail(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<AdviceHuifuBean>> getAdviceNewDetail(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<SaveWanttoAdvicebean>> removeAdvice(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<ReliefItemBean>> getReliefItem(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<ReceptionDetailBean>> getReceptionDetail(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<CollectionUrlTypeBean>> getCollectionUrlType(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<IgGuideBean>>> getIgGuide(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<ReliefItemBean>>> getReliefItemBeanlist(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<ReceptionDetailBean>>> getReceptionDetailBeanlist(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<IgGuidePostBean>>> getIgGuidePost(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<MyFindResultBean>> getMyFindData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HouseHoldSurveyBean>> gethouseholdData(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<SaveWanttoAdvicebean>> saveAdvice(@FieldMap Map<String, String> map);
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<MyFindResultBean>>> getSignServiceAssessData(@Body RequestBody body);


    @POST("person/savePersonFamilyMember")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> addFamilyMember(@Body RequestBody body);

    @POST("person/getPersonFamilyMember")
    Observable<HttpResultBaseBean<List<FamilyMemberBean>>> getFamilyMemberData(@Body RequestBody body);

    @POST("person/getSignFile")
    Observable<HttpResultBaseBean<List<com.jqsoft.fingertip_health.bean.resident.PolicyBean>>> getPolicyData(@Body RequestBody body);

    @POST("person/getPersonMessage")
    Observable<HttpResultBaseBean<List<PersonMessage>>> getPersonMessage(@Body RequestBody body);

    @POST("person/getPersonDoctorMessage")
    Observable<HttpResultBaseBean<List<PersonDoctorMessageInfo>>> getPersonDoctorMessage(@Body RequestBody body);

    @POST("person/getRemindInfo")
    Observable<HttpResultBaseBean<RemindAndMessageBean>> getRemindData(@Body RequestBody body);

    @POST("person/savePersonSignApply")
    Observable<HttpResultBaseBean<LoginResultBean2>> savePersonSignApply(@Body RequestBody body);

    @POST("person/getPersonSignAgreement")
    Observable<HttpResultBaseBean<PersonSignAgreement>> getPersonSignAgreement(@Body RequestBody body);

    @POST("person/savePersonEvaluationServer")
    Observable<HttpResultBaseBean<LoginResultBean2>> savePersonEvaluationServer(@Body RequestBody body);

    @POST("person/saveOnlineConsultation")
    Observable<HttpResultBaseBean<LoginResultBean2>> saveClientOnlineConsultation(@Body RequestBody body);

    @POST("person/updateOnlineConsultation")
    Observable<HttpResultBaseBean<LoginResultBean2>> updateOnlineChat(@Body RequestBody body);

    @POST("person/getEvaluationInfo")
    Observable<HttpResultBaseBean<EvaluationInfos>> getEvaluationInfo(@Body RequestBody body);

    @POST("person/getIsExistLoginName")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> isUserWithIdCardNumberExist(@Body RequestBody body);

    @POST("person/getBackPassword")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> retrievePassword(@Body RequestBody body);

    @POST("person/registerPersonInfo")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> register(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> loginForFingertip(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> RigesterFingertip(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> FormalSettlementFingertip(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryPatientList(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> querySignManagementReadList(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> submitNewDoctorSign(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> prescribleForFingertip(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> budgetingForFingertip(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryNotification(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryList(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> postiabetesMeli(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> postHighBlood(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryOutpatientStatistics(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryRecipe(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryTreat(@Body RequestBody body);
    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> queryharbel(@Body RequestBody body);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<SRCLoginBean>> loginSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> AddFindSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultTestBean> getDisplayInfo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<DetailFindBeans>> DetailFindSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> HandleNewFindSRC(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> DeleteFindSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<UrbanbaseInfoSaveBean>> UrbanBaseInfoSRC(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<UrbanbaseInfobianjiBean>> UrbanBaseInfobianjiSRC(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> UrbanAddFamilySRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<UrbanLowFamilybianjiBean>> UrbanbianjiFamilySRC(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<SRCLoginAreaBean>>> loginAreaSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<SRCLoginDataDictionaryBean>>> loginDictionarySRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<SRCLoginSalvationBean>>> loginSalvationSRC(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<OrganizationBean>>> longinOrganization(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBean<List<PcodeDataBean>>> longinpCodeData(@FieldMap Map<String, String> map);

    @POST("person/getDoctorTeamInfo")
    Observable<HttpResultBaseBean<List<DoctorTeamInfo>>> getDoctorTeamInfo(@Body RequestBody body);

    @POST("person/getPersonSignApply")
    Observable<HttpResultBaseBean<List<ClientPersonSignApply>>> getPersonSignApply(@Body RequestBody body);


    @POST("person/getPersonEvaluationItems")
    Observable<HttpResultBaseBean<List<SignClientServiceAssessResultBean>>> getPersonEvaluationItems(@Body RequestBody body);

    @POST("apis/getUserInfo")
    Observable<HttpResultBaseBean<LoginResultBean2>> login(@Body RequestBody body);

    @POST("apis/saveOnlineConsultation")
    Observable<HttpResultBaseBean<LoginResultBean2>> saveOnlineConsultation(@Body RequestBody body);

    @POST("apis/getSignTeamInfo")
    Observable<HttpResultBaseBean<SignTeamBean>> SignTeamInfo(@Body RequestBody body);

    @POST("apis/saveFamilyDoctorSign")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> submitSignInfo(@Body RequestBody body);

    @POST("apis/getAgriculturaInfo")
    Observable<HttpResultBaseBean<List<PersonInfoList>>> getPersonInfo(@Body RequestBody body);

    @POST("apis/getPersonnelInfo")
    Observable<HttpResultBaseBean<PersonnelInfoData>> getPersonnelInfo(@Body RequestBody body);

    @POST("apis/getSignPersonInfo")
    Observable<HttpResultBaseBean<PersonnelInfoData>> getAccessFile(@Body RequestBody body);

    @POST("apis/upSignUserInfo")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> getupdatepeople(@Body RequestBody body);

    @POST("apis/getSignDoctorServerList")
    Observable<HttpResultBaseBean<List<SignSeverPakesBeanList>>> getSignDoctorServer(@Body RequestBody body);

    @POST("apis/getSignDoctorItemhxzb")
    Observable<HttpResultBaseBean<List<CoreIndexBeanList>>> coreindex(@Body RequestBody body);

    @POST("apis/executeServiceItems")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> saveexecuserveritem(@Body RequestBody body);

    @POST("person/savePersonOrderServer")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> savePersonOrderServer(@Body RequestBody body);


    @POST("apis/getSignExecProDetail")
    Observable<HttpResultBaseBean<ModifyExecuedBean>> modifyexecuserveritem(@Body RequestBody body);

    @POST("apis/attachmentUpload")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> postimg(@Body RequestBody body);

    @POST("apis/getSignDoctorDetailServerList")
    Observable<HttpResultBaseBean<List<ServicePackDetailBeanList>>> servicepackdetail(@Body RequestBody body);

    @POST("apis/getSignPromExec")
    Observable<HttpResultBaseBean<List<PendExecuBeanList>>> getpendexecudetail(@Body RequestBody body);

    @POST("apis/getPersonOrderSignServer")
    Observable<HttpResultBaseBean<List<ReservationBeanList>>> getreservation(@Body RequestBody body);

    @POST("apis/cancelOrderService")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> deletereservation(@Body RequestBody body);

    //    v=1.0&password=admin&timestamp=1496308256475&username=admin&token=&appkey=android&sig=69c076dde06984660d14d4e9aa18aa282aa2d951
    @GET("user/login")
    Observable<HttpResultBaseBean<LoginResultBean>> login(@Query("v") String v, @Query("timestamp") String timestamp,
                                                          @Query("token") String token, @Query("appkey") String appkey,
                                                          @Query("sig") String sig, @Query("username") String username,
                                                          @Query("password") String password);

    @GET("catalog/icds")
    Observable<HttpResultBaseBean<TreatmentListResultWrapperBean>> getTreatmentData(@Query("v") String v, @Query("timestamp") String timestamp, @Query("token") String token, @Query("appkey") String appkey, @Query("sig") String sig, @Query("keyword") String keyword, @Query("area") String area, @Query("page") int page, @Query("size") int size);

    @GET("catalog/icds")
    Observable<HttpResultBaseBean<TreatmentListResultWrapperBean>> getTreatmentData(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("icd/lock")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> lockTreatmentData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("icd/unlock")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> unlockTreatmentData(@FieldMap Map<String, String> map);

    @POST("person/getNearExecutionItems")
    Observable<HttpResultBaseBean<List<ExecutionProjectsResultBean>>> getExecutionProjectsData(@Body RequestBody body);

    @POST("apis/getOnlineConsultation")
    Observable<HttpResultBaseBean<List<OnlineConsultationResultBean>>> getOnlineConsultationData(@Body RequestBody body);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SocialAssistanceObjectBean>>> getSocialObjectData(@FieldMap Map<String, String> map);



    @POST("interface/entry")
     Observable<HttpResultBaseBeanForFingertip<String>> getHighBloodListdata(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> getSelectchargeListdata(@Body RequestBody body);

    @POST("interface/entry")
    Observable<HttpResultBaseBeanForFingertip<String>> getPersonListdata(@Body RequestBody body);


      @FormUrlEncoded
    @POST("app/appService.do")
    Observable<HttpResultBaseBeanForFingertip<List<HighBloodListActivityBean>>> getHighBloodList(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SocialListHistoryBean>>> getSociaHistoryList(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<HouseHoldeBackBean>>> getaddSeverybasedata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<FamilyDetailbeans>>> getfaimilydata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<DemoCraticBaseBean>> getDemocraticBase(@FieldMap Map<String, String> map);

//    @FormUrlEncoded
//    @POST("app/appService.do")
//    Observable<GCAHttpResultBaseBean<VideoBackBean>> saveVideo(@FieldMap Map<String, String> map);
    @Multipart
    @POST
    Observable<GCAHttpResultBaseBean<VideoBackBean>> saveVideo(
            @Url String url,
//            @Part("identity") RequestBody identity,
//            @Part("key1") String description
//            @Part MultipartBody.Part file
            @PartMap Map<String, RequestBody> params
    );

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<Uploadpic>>> saveImageview(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<ProgressBean>>> getProgressData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<List<SocailHistoryDetailsBean>>> gethisdata(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<SocialDetailBean>> getSocialDetailData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<SubmitMapLocationResultBean> submitMapLocationData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app/appService.do")
    Observable<GCAHttpResultBaseBean<ProgressDetailbean>> getprogressDetailData(@FieldMap Map<String, String> map);


    @POST("apis/getAppointmentRegistrationData")
    Observable<HttpResultBaseBean<List<AppointmentRegistrationResultBean>>> getAppointmentRegistrationData(@Body RequestBody body);

    @POST("apis/denizenSignApply")
    Observable<HttpResultBaseBean<List<IndexAndOnlineSignInitialData>>> getSignApplicationData(@Body RequestBody body);

    @POST("apis/cancelSignApply")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> cancelSignApplication(@Body RequestBody body);


    @POST("apis/updateEvaluateState")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> readSignServiceAssessItem(@Body RequestBody body);

    @POST("apis/getSignServicePakageSum")
    Observable<HttpResultBaseBean<List<SignServiceIncomeResultBean>>> getSignServiceIncomeData(@Body RequestBody body);

    @POST("apis/getSignDenizenAddressList")
    Observable<HttpResultBaseBean<List<SignedResidentDirectoryResultBean>>> getSignedResidentDirectoryData(@Body RequestBody body);

    @POST("apis/getAddressMedical")
    Observable<HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>>> getTownLevelMedicalInstitutionDirectoryData(@Body RequestBody body);

    @POST("apis/getSignUserInfo")
    Observable<HttpResultBaseBean<PeopleBaseInfoBean>> getPeopleBaseInfoData(@Body RequestBody body);

    @POST("apis/getSignPromExec")
    Observable<HttpResultBaseBean<PeopleSignInfoBean>> getPeopleSignInfoData(@Body RequestBody body);


    @POST("apis/getAddressMedical")
    Observable<HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>>> getVillageLevelMedicalInstitutionDirectoryData(@Body RequestBody body);

    @POST("apis/getAddressMedical")
    Observable<HttpResultBaseBean<List<MedicalPersonDirectoryResultBean>>> getMedicalPersonDirectoryData(@Body RequestBody body);

    @FormUrlEncoded
    @POST("im/send")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> sendMessage(@FieldMap Map<String, String> map);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<ChatListWrapperBean>> getChatData(@Path("id") int id);

    @FormUrlEncoded
    @POST("im/friends")
    Observable<HttpResultBaseBean<FriendResultWrapperBean>> getHeyibanData();

    @GET("im/friends")
    Observable<HttpResultBaseBean<FriendResultWrapperBean>> getHeyibanData(@Query("v") String v, @Query("timestamp") String timestamp, @Query("token") String token, @Query("appkey") String appkey, @Query("sig") String sig);


    @GET("index/getSignInfo")
    Observable<HttpResultBaseBean<SignAndHonourAgreementResultBean>> getSignAndHonourAgreementData(@QueryMap Map<String, String> map);

    @POST("apis/getSignDoctorSituation")
    Observable<HttpResultBaseBean<List<SignInfoOverviewResultBean>>> getSignInfoOverviewData(@Body RequestBody body);

    @POST("apis/getSignDoctorRemindInfo")
    Observable<HttpResultBaseBean<IntelligentHonourAgreementOverviewResultBean>> getIntelligentHonourAgreementOverview(@Body RequestBody body);

    @GET("index/getMyInfo")
    Observable<HttpResultBaseBean<MyInfoBean>> getMyInfo(@QueryMap Map<String, String> map);

    @POST("apis/updateDoctorPhone")
    Observable<HttpResultBaseBean<HttpResultEmptyBean>> updatePhone(@Body RequestBody body);


    @POST("person/getPersonSignInfoList")
    Observable<HttpResultBaseBean<List<InHospitalInspectBeanList>>> getInHospitalInspectData(@Body RequestBody body);
//    @POST("person/getEvaluationInfo")
//    Observable<HttpResultBaseBean<List<InHospitalInspectBeanList>>> getInHospitalInspectData(@Body  RequestBody body);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<MineBean>> getMineData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<MedicalInstitutionListBean>> getMedicalInstitutionData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<MedicalInstitutionDetailBean>> getMedicalInstitutionDetailData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<CanheListBean>> getCanheData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<CanheDetailBean>> getCanheDetailData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<CompensateListBean>> getCompensateData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<CompensateDetailBean>> getCompensateDetailData(@Path("id") int id);

    @GET("headline/T1348647909107/{id}-20.html")
    Observable<HttpResultBaseBean<CompensateFeeListBean>> getCompensateFeeData(@Path("id") int id);


//    //   http://c.m.163.com/nc/article/list/T1348649145984/0-20.html
//    @GET("list/T1348649145984/{id}-20.html")
//    Observable<NBAListBean> getNBA(@Path("id") int id);
//
//    //    http://c.m.163.com/nc/article/CHTLBV3C0005877U/full.html
//    @GET("{postId}/full.html")
//    Observable<NBADetailBean> getNewDetail(
//            @Path("postId") String postId);

}
