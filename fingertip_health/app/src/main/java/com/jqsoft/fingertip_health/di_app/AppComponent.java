package com.jqsoft.fingertip_health.di_app;

import com.jqsoft.fingertip_health.di.component.*;
import com.jqsoft.fingertip_health.di.module.*;
import com.jqsoft.fingertip_health.di_http.http.OkhttpModule;
import com.jqsoft.fingertip_health.di_http.http.RetrofitModule;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAServiceModule;

import javax.inject.Singleton;

import dagger.Component;

//import com.jqsoft.fingertip_health.di.component.ChatActivityComponent;
//import com.jqsoft.fingertip_health.di.module.ChatActivityModule;

//import com.jqsoft.fingertip_health.di.component.BpartFragmentComponent;

//import com.jqsoft.fingertip_health.di.component.MedicalInstitutionDetailActivityComponent;

/**
 * <b>类名称：</b> AppComponent <br/>
 * <b>类描述：</b> 类似与Spring的Context上下文，提供依赖注入的对象<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:22<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Singleton
@Component(modules = {AppModule.class,
        OkhttpModule.class,
        RetrofitModule.class,
        GCAServiceModule.class
})
public interface AppComponent {

    AddFamilyMemberActivityComponent addAddingFamilyMemberActivity(AddFamilyMemberActivityModule module);

    FamilyMemberActivityComponent addFamilyMemberActivity(FamilyMemberActivityModule module);

    ModuleListFragmentComponent addModuleListFragment(ModuleListFragmentModule module);

    RetrievePasswordComponent addRetrievePassword(RetrievePasswordModule retrievePasswordModule);

    RegisterComponent addRegister(RegisterModule registerModule);

    LoginComponentForFingertip addLoginForFingertip(LoginModuleForFingertip module);
    OutpatientComponentForFingertip  addOutPatient(OutpatientModuleForFingertip module);
    FormalSettlementComponentForFingertip addFormal(FormalsettlementModuleForFingertip moduleForFingertip);
    PatientListDialogComponentForFingertip addPatientListDialogForFingertip(PatientListDialogModuleForFingertip module);
    SignManagementReadListDialogComponentForFingertip addSignManagementReadListDialogForFingertip(SignManagementReadListDialogModuleForFingertip module);
    NewDoctorSignActivityComponentForFingertip addNewDoctorSignActivityForFingertip(NewDoctorSignActivityModuleForFingertip module);

    SRCLoginComponent addLogin(SRCLoginModule loginModule);
//    LoginComponent addLogin(LoginModule loginModule);

    SubsistenceVarianceRankingStatisticsFragmentComponent addSubsistenceVarianceRankingStatisticsFragment(SubsistenceVarianceRankingStatisticsFragmentModule module);

    SubsistenceApproveRankingStatisticsFragmentComponent addSubsistenceApproveRankingStatisticsFragment(SubsistenceApproveRankingStatisticsFragmentModule module);

    SubsistenceApproveTrendStatisticsFragmentComponent addSubsistenceApproveTrendStatisticsFragment(SubsistenceApproveTrendStatisticsFragmentModule module);

    SubsistenceVarianceTrendStatisticsFragmentComponent addSubsistenceVarianceTrendStatisticsFragment(SubsistenceVarianceTrendStatisticsFragmentModule module);

    SubsistenceApprovePovertyReasonStatisticsFragmentComponent addPovertyReasonStatisticsFragment(SubsistenceApprovePovertyReasonStatisticsFragmentModule module);

    SubsistenceArchiveRankingStatisticsFragmentComponent addSubsistenceArchiveRankingStatisticsFragment(SubsistenceArchiveRankingStatisticsFragmentModule module);

    InstitutionRankingStatisticsFragmentComponent addInstitutionRankingStatisticsFragment(InstitutionRankingStatisticsFragmentModule module);

    SubsistenceArchiveTrendStatisticsFragmentComponent addSubsistenceArchiveTrendStatisticsFragment(SubsistenceArchiveTrendStatisticsFragmentModule module);

    SubsistenceArchiveHealthClassificationStatisticsFragmentComponent addSubsistenceArchiveHealthClassificationStatisticsFragment
            (SubsistenceArchiveHealthClassificationStatisticsFragmentModule module);

    InstitutionCharacterClassificationStatisticsFragmentComponent addInstitutionCharacterClassificationStatisticsFragment
            ( InstitutionCharacterClassificationStatisticsFragmentModule module);

    InstitutionServerClassificationStatisticsFragmentComponent addInstitutionServerClassificationStatisticsFragment
            ( InstitutionServerClassificationStatisticsFragmentModule module);

    InstitutionLegalPersonClassificationStatisticsFragmentComponent addInstitutionLegalPersonClassificationStatisticsFragment
            ( InstitutionLegalPersonClassificationStatisticsFragmentModule module);

    SubsistenceArchiveAbilityClassificationStatisticsFragmentComponent addSubsistenceArchiveAbilityClassificationStatisticsFragment
            (SubsistenceArchiveAbilityClassificationStatisticsFragmentModule module);

    SubsistenceArchiveAgeClassificationStatisticsFragmentComponent addSubsistenceArchiveAgeClassificationStatisticsFragment
            (SubsistenceArchiveAgeClassificationStatisticsFragmentModule module);

    SubsistenceAccountRankingStatisticsFragmentComponent addSubsistenceAccountRankingStatisticsFragment(SubsistenceAccountRankingStatisticsFragmentModule module);

    SubsistenceStandardRankingStatisticsFragmentComponent addSubsistenceStandardRankingStatisticsFragment(SubsistenceStandardRankingStatisticsFragmentModule module);

    SubsistenceStandardAverageRankingStatisticsFragmentComponent addSubsistenceStandardAverageRankingStatisticsFragment(SubsistenceStandardAverageRankingStatisticsFragmentModule module);

    SubsistenceAccountTrendStatisticsFragmentComponent addSubsistenceAccountTrendStatisticsFragment(SubsistenceAccountTrendStatisticsFragmentModule module);

    SubsistenceAccountIncreaseRatioStatisticsFragmentComponent addSubsistenceAccountIncreaseRatioStatisticsFragment(SubsistenceAccountIncreaseRatioStatisticsFragmentModule module);


    MedicalAssistantMoneyConstitutionStatisticsFragmentComponent addMedicalAssistantMoneyConstitutionStatisticsFragment
            (MedicalAssistantMoneyConstitutionStatisticsFragmentModule module);

    MedicalAssistantDirectOutcomeStatisticsFragmentComponent addMedicalAssistantDirectOutcomeStatisticsFragment(
            MedicalAssistantDirectOutcomeStatisticsFragmentModule module);

    MedicalAssistantFinanceAssuranceStatisticsFragmentComponent addMedicalAssistantFinanceAssuranceStatisticsFragment(
            MedicalAssistantFinanceAssuranceStatisticsFragmentModule module);

    TempDisasterAssistantStatisticsFragmentComponent addTempDisasterAssistantStatisticsFragment(
            TempDisasterAssistantStatisticsFragmentModule module
    );

    TempDisasterAssistancePercentageStatisticsFragmentComponent addTempDisasterAssistancePercentageStatisticsFragment
            (TempDisasterAssistancePercentageStatisticsFragmentModule module);

    FamilyEconomyCheckRankingStatisticsFragmentComponent addFamilyEconomyCheckRankingStatisticsFragment
            (FamilyEconomyCheckRankingStatisticsFragmentModule module);

    FamilyEconomyCheckTrendStatisticsFragmentComponent addFamilyEconomyCheckTrendStatisticsFragment
            (FamilyEconomyCheckTrendStatisticsFragmentModule module);

    FamilyEconomyCheckShareIndexStatisticsFragmentComponent addFamilyEconomyCheckShareIndexStatisticsFragment
            (FamilyEconomyCheckShareIndexStatisticsFragmentModule module);

    FamilyEconomyCheckProjectCheckStatisticsFragmentComponent addProjectCheckFragment
            (FamilyEconomyCheckProjectCheckStatisticsFragmentModule module);

    PolicyActivityComponent addPolicyActivity(PolicyActivityModule policyActivityModule);
    SignPeopleManagementActivityComponentForFingertip addSignPeopleManagementActivity(SignPeopleManagementActivityModuleForFingertip moduleForFingertip);

    MapServiceActivityComponent addMapServiceActivity(MapServiceActivityModule module);

    NotificationActivityComponent addNotificationActivity(NotificationActivityModule notificationActivityModule);
//    ClientChatComponent  addClientChat(CLientChatModule cLientChatModule);

    PolityActivityComponent addPolityActivity(PolityActivityModule module);
    ReceptionDetailNewListActivityComponent addReceptionDetailNewListActivity(ReceptionDetailNewListActivityModule module);

    ReceptionActivityComponent addReceptionActivity(ReceptionActivityModule module);

    PersonCollectionActivityComponent addPersonCollectionActivity(PersonCollectionActivityModule module);

    AdviceActivityComponent addAdviceActivity(AdviceActivityModule module);

    UseCollectionFragmentComponent addUseCollectionFragment(UseCollectionFragmentModule module);

    ReceptionListActivityComponent addReceptionListActivity(ReceptionListActivityModule module);

    MyMessageActivityComponent addMyMessageActivity(MyMessageActivityModule module);

//    QuestionActivityComponent addQuestionActivity(QuestionActivityModule module);

    GuideActivityComponent addGuideActivity(GuideActivityModule module);

    ChangePasswordActivityComponent addChangePasswordActivity(ChangePasswordActivityModule module);

    ReliefItemActivityComponent addReliefItemActivity(ReliefItemActivityModule module);


    MyMessageDetailActivityComponent addMyMessageDetailActivity(MyMessageDetailActivityModule module);

    QuestionDetailActivityComponent addQuestionDetailActivity(QuestionDetailActivityModule module);
    AdviceDetailActivityComponent addAdviceDetailActivity(AdviceDetailActivityModule module);

    AdviceDetailHadActivityComponent addAdviceHadDetailActivity(AdviceDetailActivityModule module);


    ReceptionDetailActivityComponent addReceptionDetailActivity(ReceptionDetailActivityModule module);

    IgGuideActivityComponent addIgGuideActivity(IgGuideActivityModule module);

    CoreIndexComponent addcoreinddex(CoreIndexActivityModule coreIndexActivityModule);


    ReserverComponent addreserver(ReserverActivityModule coreIndexActivityModule);

    ModifyCoreIndexComponent addmodifycoreinddex(CoreIndexActivityModule coreIndexActivityModule);

    ServicePackDetailComponent addservicepackdetail(ServicePackDetailActivityModule servicepackdetailActivityModule);

    /*SearchDetailComponent addsearchdetail(SearchDetailActivityModule searchdetailActivityModule);*/

    PendExecuComponent addpendexecu(PendExecuActivityModule pendexecuActivityModule);

  //  ReservationComponent addreservation(ReservationActivityModule reservationActivityModule);

   // SignSeverPakesComponent addserverpakes(SignSeverPakesActivityModule severPakesActivityModule);

    //SignSeverPakesComponent2 addserverpakes2(SignSeverPakesActivityModule severPakesActivityModule);

   // SignClientSeverPakesComponent addclientServerpakes(SignClientSeverPakesActivityModule signClientSeverPakesActivityModule);

    IndexFragmentComponent addIndexFragment(IndexFragmentModule indexFragmentModule);
    TreatFragmentComponent addTreatFragment(TreatFragmentModule treatFragmentModule);
    RecipeFragmentComponent addRecipeFragment(RecipeFragmentModule recipeFragmentModule);
    HarbleFragmentComponent addharbelFragment(HarbelFragmentModule harbelFragmentModule);
    OnlineChatFragmentComponent addchatFragment(OnlineChatingFragmentModule onlineChatingFragmentModule);

    OnlineChatActivityComponent addchatActivity(OnlineChatingActivityModule onlineChatingActivityModule);

    SmartAlertActivityComponent addSmartAlertActivity(SmartAlertActivityModule smartAlertActivityModule);

    //InHospitalInspectFragmentComponent addInHospitalInspectFragment(InHospitalInspectFragmentModule inHospitalInspectFragmentModule);

    //BpartFragmentComponent addBapartFragment(BFragmentModule submitSignFragmentModule);

 //   SignTeamFragmentComponent addsignteamFragment(SignTeamFragmentModule signTeamFragmentModule);

    //SaveFamilyDoctorSignFragmentComponent addCApartFragment(SaveFamilyDoctorSignFragmentModule saveFamilyDoctorSignFragmentModule);


//    ChatActivityComponent addChatActivity(ChatActivityModule chatActivityModule);


    MedicalInstitutionActivityComponent addMedicalInstitutionActivity(MedicalInstitutionActivityModule medicalInstitutionActivityModule);

//    MedicalInstitutionDetailActivityComponent addMedicalInstitutionDetailActivity(MedicalInstitutionDetailActivityModule medicalInstitutionDetailActivityModule);


    ExecutionProjectsActivityComponent addExecutionProjectsActivity(ExecutionProjectsActivityModule executionProjectsActivityModule);
    //SignSeverPakesComponent addSignServerPakes(SignSeverPakesActivityModule signSeverPakesActivityModule );
    DiabetesMellitusListActivityComponent addDiabetesMellitusListActivity(DiabetesMellitusListActivityModule module);

    //OnlineConsultationActivityComponent addOnlineConsultationActivity(OnlineConsultationActivityModule module);
    SignDoctorListActivityComponent addSignDoctorListActivity(SignDoctorListActivityModule module);

    DetailPeopleInfoActivityComponent addDetailPeopleInfo(DetailPeopleInfoActivityModule module);

    HighBloodListActivityComponent addHighBloodListActivity(HighBloodListActivityModule module);
    SelectChargeListActivityComponent addselectchargeActivity(SelectChargeListActivityModule module);
    SocialAssistanceObjectActivityComponent addSocialAssistanceObjectActivity(SocialAssistanceObjectActivityModule module);
    SocialHistoryActivityComponent addSocialHistorActivity(SocialHistoryActivityModule module);

    AddServeryActivityComponent addServeryactivity(AddServeryActivityModule module);

    FamilyDetailActivityComponent addfaimilydetailactivity(FamilyDetailsActivityModule module);

    DispalyBaseInfoComponent adddisplay(DispalyInfoActivityModule module);

    AddDemoCraticComponent addDemocratic(AddDemocraticModule module);

    DisPlayDemocraticComponent addDisplayDemoCratic(DisPlayDemocraticModule module);

    HandleProgressComponent addhandleprogress(HandleProgressModule module);
    HistoryDetailComponent addhisdetailPage(HistoryDetailModule module);
    SocialDetailActivityComponent addSocailDetail(SocialDetailActivityModule socialDetailActivityModule);

    //    AppointmentRegistrationActivityComponent addAppointmentRegistrationActivity(AppointmentRegistrationActivityModule module);
    HandleProgressDetailActivityComponent addprogressDetail(HandleProgressDetailActivityModule module);

    MyInfoComponent addMyInfoActivity(MyInfoModule myInfoModule);

    PersonalInfoActivityComponent addPersonalInfoActivity(PersonalInfoActivityModule myInfoModule);
    AboutInfoActivityComponent addAboutInfoActivity(AboutInfoActivityModule myInfoModule);
    QuestionActivityComponent addQuestionActivity(QuestionActivityModule questionActivityModule);

    SignedResidentDirectoryFragmentComponent addSignedResidentDirectoryFragment(SignedResidentDirectoryFragmentModule module);

    TownLevelMedicalInstitutionDirectoryFragmentComponent addTownLevelMedicalInstitutionDirectoryFragment(TownLevelMedicalInstitutionDirectoryFragmentModule module);

    VillageLevelMedicalInstitutionDirectoryActivityComponent addVillageLevelMedicalInstitutionDirectoryActivity(VillageLevelMedicalInstitutionDirectoryActivityModule module);

    MedicalPersonDirectoryActivityComponent addMedicalPersonDirectoryActivity(MedicalPersonDirectoryActivityModule module);

    MySignInfoActivityComponent addMySignInfoActivity(MySignInfoActivityModule module);

    //IntelligentHonourAgreementRemindActivityComponent addIntelligentHonourAgreementRemindActivity(IntelligentHonourAgreementRemindActivityModule module);

    SignServiceAssessFragmentComponent addSignServiceAssessFragment(SignServiceAssessFragmentModule module);

    SignServiceAssessActivityComponent addSignServiceAssessActivity(SignServiceAssessActivityModule module);

    HouseHoldServeyActivityComponent addHouseholdActivity(HouseHoldServeyActivityModule module);
    WanttoAdviceComponent  addWanttoadvice(WanttoAdviceModule module);
    HouseHoldServeyBaseComponent addhouseholdbaseFragment(HouseHoldServeyBaseModule module);

    HouseHoldFamilyComponent addfamilyFragment(HouseHoldFamilyModule module);

    HouseHoldFileComponent addfileFragment(HouseHoldFileModule module);

    HouseHoldServeryComponent addserveryFragment(HouseHoldserveryModule module);

    DemocraticAppraisalComponent adddemocraticFragment(DemocraticAppraisalModule module);

    SignClientServiceAssessActivityComponent addSignClientActity(SignClientServiceAssessActivityModule module);

    SignServiceIncomeFragmentComponent addSignServiceIncomeFragment(SignServiceIncomeFragmentModule module);

    SignServiceIncomeActivityComponent addSignServiceIncomeActivity(SignServiceIncomeActivityModule module);

    //SignApplicationActivityComponent addSignApplicationActivity(SignApplicationActivityModule module);

    SignServiceEvalutionComponent addSignServiceEvalutionActivity(SignServiceEvaluteActivityModule module);

    //OnlineSignApplicationComponent addOnlineSignApplicationActity(OnlineSignApplicationActivityModule module);

    SignAgreementComponent addSignAgreenmentActivity(SignAgreementActivityModule signAgreementActivityModule);

    //PeopleBaseInfoFragmentComponent addPeopleBaseInfoFragment(PeopleBaseInfoFragmentModule module);
    DMFragmentComponent addDMFragment(DMFragmentModule module);

    HighBloodFragmentComponent addHighBloodFragment(HighBloodFragmentModule module);

    PersonFragment3Component addpersonFragment3(PersonFragment3Module module);

    PeopleSignInfoFragmentComponent addPeopleSignInfoFragment(PeopleSignInfoFragmentModule module);

    AccessFileComponent addAccessFile(AccessFileModule module);

   /* UpdatePeopleComponent addupdatepeople(UpdatePeopleModule module);*/

    //PeopleBaseCallComponent addPeopleBaseCallFragment(PeopleBaseInfoFragmentModule module);

    AddFindComponent addFind(AddFindModule addFindModule);

    DetailFindComponent detailFind(AddFindModule addFindModule);

    DetailFindStatusComponent detaildaibanFind(AddFindModule addFindModule);

    DeleteFindFragmentComponent addDeleteFindFragment(DeleteFindFragmentModule module);

    UrbanBaseInfoFragmentComponent addUrbanBaseInfoFragment(UrbanBaseInfoFragmentModule module);

    UrbanBaseInfoOtherFragmentComponent addUrbanBaseInfootherFragment(UrbanBaseInfoFragmentModule module);


    DetailFindDiscoverComponent detaildiscoverFind(AddFindModule addFindModule);

    HandleFindComponent handlenewFind(HandleFindModule handleFindModule);

    UrbanLowInsActivityComponent addUrbanLowInsActivity(UrbanLowInsActivityModule urbanLowInsActivityModule);

    UrbanLowAddFamilyActivityComponent addUrbanLowaddFamilyActivity(UrbanLowAddFamilyActivityModule urbanLowInsActivityModule);

    UrbanLowFamilyFragmentComponent addUrbanLowFamilyFragment(UrbanLowFamilyFragmentModule urbanLowInsActivityModule);

    UrbanLowFujianFragmentComponent addUrbanLowFUjianFragment(UrbanLowFamilyFragmentModule urbanLowInsActivityModule);

    UrbanLowAddFamilyBianjiActivityComponent addUrbanLowaddFamilybianjiActivity(UrbanLowAddFamilyActivityModule urbanLowInsActivityModule);

    UrbanBaseInfoBianjiFragmentComponent addUrbanBaseInfobianjiFragment(UrbanBaseInfoFragmentModule module);

    UrbanBaseInfoBianjiOtherFragmentComponent addUrbanBaseInfobianjiotherFragment(UrbanBaseInfoFragmentModule module);

    UrbanLowFamilyBianjiFragmentComponent addUrbanLowFamilybianjiFragment(UrbanLowFamilyFragmentModule urbanLowInsActivityModule);

    UrbanLowFujianBianjiFragmentComponent addUrbanLowFUjianbianjiFragment(UrbanLowFamilyFragmentModule urbanLowInsActivityModule);

    UrbanLowFujianBianjiNewFragmentComponent addUrbanLowFUjianbianjiNewFragment(UrbanLowFamilyFragmentModule urbanLowInsActivityModule);

    UrbanBaseInfoBianjiStatusFragmentComponent addUrbanBaseInfobianjistatusFragment(UrbanBaseInfoFragmentModule module);

    UrbanLowAddFamilyBianjiStatusActivityComponent addUrbanLowaddFamilybianjistatusActivity(UrbanLowAddFamilyActivityModule urbanLowInsActivityModule);

    IntroductionComponent addArea(SRCLoginModule loginModule);

    IntroductionNewComponent addNewArea(SRCLoginModule loginModule);

    PersonJiandangListActivityComponent addPersonJiandangListActivity(PersonJiandangListActivityModule module);

    PersonJiandangActivityComponent addPersonJiandangActivity(PersonJiandangListActivityModule module);

    KucunfenxiListActivityComponent addKuncunfenxiListActivity(PersonJiandangListActivityModule module);
}
