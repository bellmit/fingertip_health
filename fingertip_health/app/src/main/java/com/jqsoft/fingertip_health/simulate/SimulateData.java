package com.jqsoft.fingertip_health.simulate;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.AreaBean;
import com.jqsoft.fingertip_health.bean.BarDataBean;
import com.jqsoft.fingertip_health.bean.BarDataItemBean;
import com.jqsoft.fingertip_health.bean.CanheDetailBean;
import com.jqsoft.fingertip_health.bean.CanheListBean;
import com.jqsoft.fingertip_health.bean.CompensateDetailBean;
import com.jqsoft.fingertip_health.bean.CompensateFeeListBean;
import com.jqsoft.fingertip_health.bean.CompensateListBean;
import com.jqsoft.fingertip_health.bean.FunctionImageBean;
import com.jqsoft.fingertip_health.bean.HeyibanListBean;
import com.jqsoft.fingertip_health.bean.HospitalTypeBean;
import com.jqsoft.fingertip_health.bean.InHospitalInspectListBean;
import com.jqsoft.fingertip_health.bean.MedicalInstitutionDetailBean;
import com.jqsoft.fingertip_health.bean.MedicalInstitutionListBean;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.PieDataBean;
import com.jqsoft.fingertip_health.bean.PieDataItemBean;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBean;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.bean.TreatmentListBean;
import com.jqsoft.fingertip_health.bean.resident.FamilyMemberBean;
import com.jqsoft.fingertip_health.bean.resident.PolicyBean;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;
import com.jqsoft.fingertip_health.bean.response.ChatBean;
import com.jqsoft.fingertip_health.bean.response.MedicalPersonDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.MedicalPersonDirectoryResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.MyInfoBean;
import com.jqsoft.fingertip_health.bean.response.SignAndHonourAgreementResultBean;
import com.jqsoft.fingertip_health.bean.response.SignNumberAndRatioBean;
import com.jqsoft.fingertip_health.bean.response.SignServerPakesResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.SignedResidentDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.SignedResidentDirectoryResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.TownLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.TownLevelMedicalInstitutionDirectoryResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response.TreatmentListResultBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response_new.AppointmentRegistrationResultBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultItemBean;
import com.jqsoft.fingertip_health.bean.response_new.IntelligentHonourAgreementOverviewResultBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.bean.response_new.OnlineConsultationResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultWrapperBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceIncomeResultBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017-05-12.
 */

public class SimulateData {
    public static List<FamilyMemberBean> getSimulatedFamilyMemberList(){
        List<FamilyMemberBean> result = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            FamilyMemberBean bean = new FamilyMemberBean("341111196011111234", "??????"+i, "124334234", "2");
            result.add(bean);
        }
        return result;
    }
    public static List<RemindBean> getSimulatedRemindList(){
        List<RemindBean> result = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            RemindBean rb =  new RemindBean(RemindBean.TYPE_REMIND, R.mipmap.r_remind_1,
                    "????????????", "??????????????????", "2017???5???17???");
            result.add(rb);
        }
        RemindBean message = new RemindBean(RemindBean.TYPE_MESSAGE, R.mipmap.r_remind_3,
                "??????????????????????????????,???????????????????????????!", "3", "2017");
        result.add(message);
        return result;
    }
    public static List<PolicyBean> getSimulatedPolicyList(){
        List<PolicyBean> result = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            PolicyBean pb = new PolicyBean(i+"", "??????", "2017-05-01", "??????????????????????????????????????????");
            result.add(pb);
        }
        return result;
    }
    public static List<OnlineConsultationResultBean> getSimulatedOnlineConsultationList(){
        List<OnlineConsultationResultBean> list = new ArrayList<>();
        String photoUrl="http://192.168.88.36:8080/fdss-api/photo/0123456.jpg";
        for (int i = 0; i < 20; ++i){
            OnlineConsultationResultBean bean = new OnlineConsultationResultBean("??????????????????,???????????????????????????????", "2017-07-19","??????",
                    "05518888888", "62", "docUserId", "34103403", "5", "4", photoUrl, "personMold", "??????????????????");
            list.add(bean);
        }
        return list;
    }
    public static List<AppointmentRegistrationResultBean> getAppointmentRegistrationResultBeanList(){
        List<AppointmentRegistrationResultBean> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            String url =  "http://www.runoob.com/wp-content/uploads/2013/10/bs.png";
            String longitude="", latitude="";
            if (i%2==0){
                longitude="108.22";
                latitude="31.55";
            } else {
                longitude="112.22";
                latitude="32.88";
            }
            AppointmentRegistrationResultBean bean = new AppointmentRegistrationResultBean(i+"", url, "???????????????????????????",
                    "????????????", "?????????1???", longitude, latitude);
            list.add(bean);
        }
        return list;
    }
//    public static List<SignApplicationResultBean> getSignApplicationResultBeanList(){
//        List<SignApplicationResultBean> list = new ArrayList<>();
//        for (int i = 0; i < 20; ++i){
//            String headUrl = "http://www.runoob.com/wp-content/uploads/2013/10/bs.png";
////            SignApplicationResultBean bean = new SignApplicationResultBean();
//            SignApplicationResultBean bean = new SignApplicationResultBean(i, "1", "1","1","1","1","1","1","1",
//                    "1","1","1","1","1","1","1","1","1","1","1",i+"","1","1","1","1","1","1","1","1","1","1","1","1","1",
//                    "1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",
//                    "1","1","1","1","1","1","1","1","1", null);
//            list.add(bean);
//        }
//        return list;
//    }
    public static SignServiceIncomeResultBean getSignServiceIncomeResultBean(){
        SignServiceIncomeResultBean result = new SignServiceIncomeResultBean(Constants.SIGN_SERVICE_INCOME_TYPE_ALL,
                "6900","714105.30","714105.30","213979.00","357457.00","-142669.30", "200");
        return result;
    }
    public static List<SignServiceAssessResultBean> getSignServiceAssessList(){
        List<SignServiceAssessResultBean> list = new ArrayList<>();
//        for (int i = 0; i < 20; ++i){
//            SignServiceAssessResultBean bean = new SignServiceAssessResultBean(i+"", "0551-23434", "url", "??????"+i, "68",
//                    "??????????????????", "3", "0.6", "????????????", "2017-07-07", "4", "2017-07-08", "???????????????????????????");
//            list.add(bean);
//        }
        return list;
    }
    public static LoginResultBean2 getLoginResultBean2(){
        LoginResultBean2 result = new LoginResultBean2();
        result.setGuserid("QTNDRTc2MjUtRTcxRC00NTg0LTlGNjEtODM0Mzc2RkM4QzYx");
        result.setShiploginname("MDAwMA==");
        result.setSloginname("YWRtaW4=");
        result.setSmanagementdivisioncode("MzQxMjAy");
        result.setSmanagementdivisionname("6aKN5bee5Yy6");
        result.setSorgInstitutioncode("MDAwMDAwMDAtMA==");
        result.setSorganizationkey("RkJBMzdGN0EtREJFOC00REJGLUJCREMtOTAwMEIyQzdCRUI4");
        result.setSorganizationlevelcode("Mw==");
        result.setSorganizationname("566h55CG5py65p6E");
        result.setSorganizationtypecode("MzA=");
        result.setSphone("");
        result.setSsexname("");
        result.setSusername("566h55CG5ZGY");
        return result;
    }
    public static IntelligentHonourAgreementOverviewResultBean getIntelligentHonourAgreementOverviewResultBean(){
        IntelligentHonourAgreementOverviewResultBean result = new IntelligentHonourAgreementOverviewResultBean("30", "100",
                "30", "0", "0");
        return result;
    }
    public static SignInfoOverviewResultBean getSignInfoOverviewResultBean(){
        SignInfoOverviewResultBean bean = new SignInfoOverviewResultBean("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
        return bean;
    }
    public static SignInfoOverviewResultWrapperBean getSignInfoOverviewResultWrapperBean(){
        SignInfoOverviewResultBean bean = new SignInfoOverviewResultBean("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
        SignInfoOverviewResultBean bean2 = new SignInfoOverviewResultBean("0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5","0.5");
        List<SignInfoOverviewResultBean> list = new ArrayList<>();
        list.add(bean);
        list.add(bean2);
        SignInfoOverviewResultWrapperBean result = new SignInfoOverviewResultWrapperBean(list);
        return result;
    }
    public static MedicalPersonDirectoryResultWrapperBean getMedicalPersonDirectoryResultWrapperBean(){
        List<MedicalPersonDirectoryResultBean> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            MedicalPersonDirectoryResultBean bean = new MedicalPersonDirectoryResultBean(i+"", "??????"+i, "????????????????????????????????????",
                    "18888888888");
            list.add(bean);
        }
        MedicalPersonDirectoryResultWrapperBean result = new MedicalPersonDirectoryResultWrapperBean(1, 20, list);
        return result;
    }
    public static VillageLevelMedicalInstitutionDirectoryResultWrapperBean getVillageLevelMedicalInstitutionDirectoryResultWrapperBean(){
        List<VillageLevelMedicalInstitutionDirectoryResultBean> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            VillageLevelMedicalInstitutionDirectoryResultBean bean = new VillageLevelMedicalInstitutionDirectoryResultBean(i+"", "????????????????????????????????????", "3412210134344", "6");
            list.add(bean);
        }
        VillageLevelMedicalInstitutionDirectoryResultWrapperBean result = new VillageLevelMedicalInstitutionDirectoryResultWrapperBean(1, 20, list);
        return result;
    }
    public static TownLevelMedicalInstitutionDirectoryResultWrapperBean getTownLevelMedicalInstitutionDirectoryResultWrapperBean(){
        List<TownLevelMedicalInstitutionDirectoryResultBean> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            TownLevelMedicalInstitutionDirectoryResultBean bean = new TownLevelMedicalInstitutionDirectoryResultBean(i+"", "???????????????????????????????????????", "48592661X", "6");
            list.add(bean);
        }
        TownLevelMedicalInstitutionDirectoryResultWrapperBean result = new TownLevelMedicalInstitutionDirectoryResultWrapperBean(1, 20, list);
        return result;
    }
    public static SignedResidentDirectoryResultWrapperBean getSignedResidentDirectoryResultWrapperBean(){
        List<SignedResidentDirectoryResultBean> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            SignedResidentDirectoryResultBean bean = new SignedResidentDirectoryResultBean(i+"", "??????"+i, "??????????????????????????????????????????", "18888888888");
            list.add(bean);
        }
        SignedResidentDirectoryResultWrapperBean result = new SignedResidentDirectoryResultWrapperBean(1, 20, list);
        return result;
    }
    public static MyInfoBean getMyInfoBean(){
        MyInfoBean result = new MyInfoBean("22", "??????", "3400001", "AABBCC", "340322198808118888",
                "???????????????????????????", "1888888");
        return result;
    }
    public static List<ExecutionProjectsResultBean> getExecutionProjectsResultBeanList(){
        List<ExecutionProjectsResultBean> list = new ArrayList<>();
        String headUrl = "http://www.runoob.com/images/pulpit.jpg";
        for (int i = 0; i < 20; ++i){
            List<ExecutionProjectsResultItemBean> itemList = new ArrayList<>();
            for (int j = 0; j < 2; ++j){
                ExecutionProjectsResultItemBean itemBean = new ExecutionProjectsResultItemBean("????????????"+j, "2017-07-04",
                        j+"100", j+"1000", j+"10000");
                itemList.add(itemBean);
            }
            PeopleBaseInfoBean pbib = new PeopleBaseInfoBean();
            ExecutionProjectsResultBean bean = new ExecutionProjectsResultBean(headUrl,  i, "??????", "11010000000000000000", "key", "2017", "??????", "34122424424", "??????????????????", "0551888888", "68",
                    Constants.PACKAGE_LEVEL_INTERMEDIATE, "2", "2", pbib, itemList);
            list.add(bean);
        }
        return list;
    }

    public static SignAndHonourAgreementResultBean getSignAndHonourAgreementBean(){
        List<SignNumberAndRatioBean> list = new ArrayList<>();
        SignNumberAndRatioBean first = new SignNumberAndRatioBean("?????????????????????", "500", "?????????", "15.88%");
        SignNumberAndRatioBean second = new SignNumberAndRatioBean("?????????????????????", "100", "?????????", "20.88%");
        SignNumberAndRatioBean third = new SignNumberAndRatioBean("?????????????????????", "100", "?????????", "12.34%");
        SignNumberAndRatioBean fourth = new SignNumberAndRatioBean("?????????????????????", "100", "?????????", "12.34%");
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);
        SignAndHonourAgreementResultBean result = new SignAndHonourAgreementResultBean("??????????????????", "2017",
                list, "50", "30", "100",  "3", "1", "1");
        return result;
    }
    public static List<TreatmentListResultBean> getSimulatedTreatmentResultList(){
        List<TreatmentListResultBean> result = new ArrayList<>();
        for (int i = 0; i < Constants.DEFAULT_PAGE_SIZE; ++i){
            TreatmentListResultBean tlrb = new TreatmentListResultBean(""+i, "200"+i, "name"+i, TreatmentListResultBean.TREATMENT_LOCK);
            result.add(tlrb);
        }
        return result;
    }
    public static SignServerPakesResultWrapperBean getSignServerPakesResultWrapperBean(){
        SignSeverPakesBean result = new SignSeverPakesBean();
        List<SignSeverPakesBeanList> list = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            SignSeverPakesBeanList item = new SignSeverPakesBeanList("??????"+i, "68", "??????????????????", "0.6","??????"+i, "68", "??????????????????", "0.6","","","","","");
            list.add(item);
        }
        SignServerPakesResultWrapperBean results = new SignServerPakesResultWrapperBean(1, 20, list);
        return results;
    }
    public static CompensateFeeListBean getSimulatedCompensateFeeBeanList(){
        CompensateFeeListBean result = new CompensateFeeListBean();
        List<CompensateFeeListBean.CompensateFeeBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            String prefix = (i%2==0)?"?????????":"?????????";
            CompensateFeeListBean.CompensateFeeBean item = result.new CompensateFeeBean(prefix, "1000"+i);
            list.add(item);
        }
        result.setList(list);
        return result;
    }
    public static CompensateDetailBean getSimulatedCompensateDetailBean(){
        CompensateDetailBean result = new CompensateDetailBean("??????", "34032219", 0, R.mipmap.icon,
                "???????????????????????????", "?????????", "2017-01-01", "2017-05-25", "???????????????", "10000", "8000",
                "7500", "6000", "87654", "56700");
        return result;
    }
    public static CompensateListBean getSimulatedCompensateBeanList(){
        CompensateListBean result = new CompensateListBean();
        List<CompensateListBean.CompensateBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            CompensateListBean.CompensateBean item = result.new CompensateBean(i+"", "??????", "12345"+i);
            list.add(item);
        }
        result.setList(list);
        return result;
    }
    public static CanheDetailBean getSimulatedCanheDetailBean() {
        CanheDetailBean result = new CanheDetailBean(R.mipmap.icon, "??????", Constants.GENDER_FEMALE, true,
                "?????????", "10000", "20000", "2017-05-25");
        return result;
    }
    public static CanheListBean getSimulatedCanheBeanList(){
        CanheListBean result = new CanheListBean();
        List<CanheListBean.CanheBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            CanheListBean.CanheBean item = result.new CanheBean(i+"", "??????", i%2, (i%2==0));
            list.add(item);
        }
        result.setList(list);
        return result;
    }
    public static MedicalInstitutionDetailBean getSimulatedMedicalInstitutionDetailBean() {
        String[] array = new String[]{"?????????141", "????????????60", "????????????5", "?????????24", "????????????11", "????????????4"};
        List<String> list = Arrays.asList(array);
        MedicalInstitutionDetailBean result = new MedicalInstitutionDetailBean(117.269309f, 31.847776f, 4.8f, 690, list, "???????????????????????????", "??????????????????", "055122233");
        return result;
    }

    public static MedicalInstitutionListBean getSimulatedMedicalInstitutionListBean() {
        MedicalInstitutionListBean result = new MedicalInstitutionListBean();
        List<MedicalInstitutionListBean.MedicalInstitutionBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            MedicalInstitutionListBean.MedicalInstitutionBean bean = result.new MedicalInstitutionBean("" + i,
                    "????????????????????????", R.mipmap.icon, 5, "170");
            list.add(bean);
        }
        result.setList((ArrayList<MedicalInstitutionListBean.MedicalInstitutionBean>) list);
        return result;
    }

    public static List<ChatBean> getSimulatedChatBeanList() {
        List<ChatBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                ChatBean bean = new ChatBean(ChatBean.OTHER_PERSON, "2017-05-18", "fromId", "fromPerson",
                        "toId", "toPerson", "???????????????????????????");
                list.add(bean);
            } else if (i == 1) {
                ChatBean bean = new ChatBean(ChatBean.SELF, "2017-05-18", "fromId", "fromPerson",
                        "toId", "toPerson", "???????????????????????????");
                list.add(bean);
            } else if (i == 2) {
                ChatBean bean = new ChatBean(ChatBean.OTHER_PERSON, "2017-05-18", "fromId", "fromPerson",
                        "toId", "toPerson", "????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                        "????????????????????????????????????????????????????????????????????????????????????????????????????????????1");
                list.add(bean);
            } else if (i == 3) {
                ChatBean bean = new ChatBean(ChatBean.SELF, "2017-05-18", "fromId", "fromPerson",
                        "toId", "toPerson", "????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                        "????????????????????????????????????????????????????????????????????????????????????????????????????????????2");
                list.add(bean);
            } else {
                ChatBean bean = new ChatBean(ChatBean.OTHER_PERSON, "2017-05-18", "fromId", "fromPerson",
                        "toId", "toPerson", "???????????????????????????");
                list.add(bean);

            }

        }
        return list;
    }

    public static List<HospitalTypeBean> getSimulatedHospitalTypeBeanList() {
        List<HospitalTypeBean> list = new ArrayList<>();
        HospitalTypeBean beanAll = new HospitalTypeBean("0", R.mipmap.icon, "??????", null);
        HospitalTypeBean gongliBean = new HospitalTypeBean("1", R.mipmap.icon, "??????", null);
        HospitalTypeBean firstLevel = new HospitalTypeBean("10", 0, "????????????", null);
        HospitalTypeBean secondLevel = new HospitalTypeBean("11", 0, "????????????", null);
        HospitalTypeBean thirdLevel = new HospitalTypeBean("12", 0, "????????????", null);
        List<HospitalTypeBean> children = new ArrayList<>();
        children.add(firstLevel);
        children.add(secondLevel);
        children.add(thirdLevel);
        gongliBean.setChildren(children);
        HospitalTypeBean siyingBean = new HospitalTypeBean("2", R.mipmap.icon, "??????", null);

        list.add(beanAll);
        list.add(gongliBean);
        list.add(siyingBean);
        return list;
    }

    public static TreatmentListBean getSimulatedTreatmentListBean() {
        TreatmentListBean result = new TreatmentListBean();
        List<TreatmentListBean.TreatmentBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            TreatmentListBean.TreatmentBean item = result.new TreatmentBean("?????????????????????" + i, "H61.104", true);
            list.add(item);
        }
        result.setTreatmentList((ArrayList<TreatmentListBean.TreatmentBean>) list);
        return result;
    }

    public static HeyibanListBean getSimulatedHeyibanListBean() {
        HeyibanListBean result = new HeyibanListBean();
        List<HeyibanListBean.HeyibanBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            HeyibanListBean.HeyibanBean item = result.new HeyibanBean(R.mipmap.icon,
                    "?????????" + i, "??????????????????");
            list.add(item);
        }
        result.setList((ArrayList<HeyibanListBean.HeyibanBean>) list);
        return result;
    }

    public static InHospitalInspectListBean getSimulatedInHospitalInspectListBean() {
        InHospitalInspectListBean result = new InHospitalInspectListBean();
        List<InHospitalInspectListBean.InHospitalInspectBean> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            InHospitalInspectListBean.InHospitalInspectBean item = result.new InHospitalInspectBean(
                    "??????", "??????", "????????????????????????", "1000" + i, false);
            list.add(item);
        }
        result.setInspectList((ArrayList<InHospitalInspectListBean.InHospitalInspectBean>) list);
        return result;
    }

    public static List<AreaBean> getSimulatedAreaList() {
        List<AreaBean> result = new ArrayList<>();
        AreaBean quanshi = new AreaBean("??????", "1", 0);
        AreaBean qixingguan = new AreaBean("?????????", "2", 1);
        AreaBean jinhaihu = new AreaBean("?????????", "3", 2);
        AreaBean qianxi = new AreaBean("??????", "4", 3);
        AreaBean zhijin = new AreaBean("??????", "5", 4);
        AreaBean dafang = new AreaBean("??????", "6", 5);
        AreaBean nayong = new AreaBean("??????", "7", 6);
        AreaBean jinsha = new AreaBean("??????", "8", 7);
        AreaBean weining = new AreaBean("??????", "9", 8);
        result.add(quanshi);
        result.add(qixingguan);
        result.add(jinhaihu);
        result.add(qianxi);
        result.add(zhijin);
        result.add(dafang);
        result.add(nayong);
        result.add(jinsha);
        result.add(weining);
        return result;
    }

    public static List<FunctionImageBean> getSimulatedFunctionImageGroup() {
        List<FunctionImageBean> result = new ArrayList<>();
        FunctionImageBean b1 = new FunctionImageBean("???1", "1", R.mipmap.i_all_appointment_execution);
        FunctionImageBean b2 = new FunctionImageBean("???2", "2", R.mipmap.i_all_appointment_registration);
        FunctionImageBean b3 = new FunctionImageBean("???3", "3", R.mipmap.i_all_basic_common_hygienism);
        FunctionImageBean b4 = new FunctionImageBean("???4", "4", R.mipmap.i_all_hospital_raise_combination);
        FunctionImageBean b5 = new FunctionImageBean("???5", "5", R.mipmap.i_all_intelligent_honour_agreement_remind);
        FunctionImageBean b6 = new FunctionImageBean("???6", "6", R.mipmap.i_all_my_info);
        FunctionImageBean b7 = new FunctionImageBean("???7", "7", R.mipmap.i_all_online_consultation);
        FunctionImageBean b8 = new FunctionImageBean("???8", "8", R.mipmap.i_all_online_sign);
        result.add(b1);
        result.add(b2);
        result.add(b3);
        result.add(b4);
        result.add(b5);
        result.add(b6);
        result.add(b7);
        result.add(b8);
        return result;
    }

    public static BarDataBean getSimulatedBarDataBean() {
        BarDataBean result = new BarDataBean();
        result.setMonth("2017-05");
        result.setTitle("?????????????????????");
        List<BarDataItemBean> list = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            BarDataItemBean item = new BarDataItemBean();
            item.setCount("10" + i);
            list.add(item);
        }
        result.setList(list);
        return result;
    }

    public static PieDataBean getSimulatedPieDataBean() {
        PieDataBean result = new PieDataBean();
        result.setMonth("2017-05");
        result.setTitle("???????????????????????????");
        List<PieDataItemBean> list = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            PieDataItemBean item = new PieDataItemBean();
            item.setCount("10" + i);
            list.add(item);
        }
        result.setList(list);
        return result;
    }
}
