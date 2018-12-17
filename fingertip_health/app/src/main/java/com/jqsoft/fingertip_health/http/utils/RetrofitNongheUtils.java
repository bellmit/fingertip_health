//package com.jqsoft.grassroots_civil_administration_platform.http.utils;
//
//
//import com.jqsoft.fingertip_health.bean.ChartDataBean;
//import com.jqsoft.fingertip_health.bean.ChatListWrapperBean;
//import com.jqsoft.fingertip_health.bean.HeyibanListBean;
//import com.jqsoft.fingertip_health.bean.InHospitalInspectListBean;
//import com.jqsoft.fingertip_health.bean.MineBean;
//import com.jqsoft.fingertip_health.bean.TreatmentListBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.http.base.HttpUtils;
//import com.jqsoft.fingertip_health.di_http.http.fingertip_health.GrassrootsCivilAdministrationService;
//
//import rx.Observable;
//
///**
// * Created by quantan.liu on 2017/3/27.
// */
//
//public class RetrofitSignedDoctorClientUtils extends HttpUtils {
//
//    private GrassrootsCivilAdministrationService mSignedDoctorClientService;
//
//    public RetrofitSignedDoctorClientUtils(GrassrootsCivilAdministrationService mSignedDoctorClientService) {
//        this.mSignedDoctorClientService = mSignedDoctorClientService;
//    }
//
//    public Observable<HttpResultBaseBean<ChartDataBean>> fetchChartData(int id) {
//        return mSignedDoctorClientService.getSignAndHonourAgreementData(id);
//    }
//    public Observable<HttpResultBaseBean<ChatListWrapperBean>> fetchChatData(int id) {
//        return mSignedDoctorClientService.getChatData(id);
//    }
//    public Observable<HttpResultBaseBean<HeyibanListBean>> fetchHeyibanData(int id) {
//        return mSignedDoctorClientService.getHeyibanData(id);
//    }
//    public Observable<HttpResultBaseBean<InHospitalInspectListBean>> fetchInHospitalInspectData(int id) {
//        return mSignedDoctorClientService.getInHospitalInspectData(id);
//    }
//    public Observable<HttpResultBaseBean<MineBean>> fetchMineData(int id) {
//        return mSignedDoctorClientService.getMineData(id);
//    }
//    public Observable<HttpResultBaseBean<TreatmentListBean>> fetchTreatmentData(int id) {
//        return mSignedDoctorClientService.getTreatmentData(id);
//    }
//
////    public Observable<NBAListBean> fetchNBAList(int id) {
////        return mSignedDoctorClientService.getNBA(id);
////    }
////
////    public Observable<NBADetailBean> fetchNBADetail(String id) {
////        return mSignedDoctorClientService.getNewDetail(id);
////    }
//}
