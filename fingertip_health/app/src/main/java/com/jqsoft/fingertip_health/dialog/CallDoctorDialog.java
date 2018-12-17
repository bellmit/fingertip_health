//package com.jqsoft.grassroots_civil_administration_platform.dialog;
//
//import android.content.Context;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.View;
//
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.google.gson.Gson;
//import com.jqsoft.fingertip_health.adapter.SignDoctorNameAndPhoneAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.callback.MyResultCallback;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//import com.zhy.http.okhttp.OkHttpUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import okhttp3.MediaType;
//
///**
// * Created by Administrator on 2017-08-30.
// * 呼叫医生对话框
// */
//
//public class CallDoctorDialog extends MaterialDialog {
//    public Context context;
//    public Builder builder = null;
//    private List<DoctorTeamInfo> list = new ArrayList<>();
//    BaseQuickAdapter<DoctorTeamInfo, BaseViewHolder> mAdapter;
//
//    public CallDoctorDialog(Builder builder) {
//        super(builder);
//    }
//
//
//    public Builder getCustomBuilder(Context ctx){
//        context=ctx;
//        if (builder==null) {
//            mAdapter = new SignDoctorNameAndPhoneAdapter(list);
//            mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//            builder = new Builder(context)
//                    .title(null)
//                    .negativeText(Constants.CANCEL)
//                    // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
//                    .adapter(mAdapter, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
//                    .autoDismiss(true);
//        }
//        return builder;
//    }
//
//    public void showPhoneDialog(){
//        String url = Version.HTTP_URL+"person/getDoctorTeamInfo";
//        String cardNo = Identity.getCardNo();
//        Map<String, String> params =   ParametersFactory.getDoctorTeamDataMap(cardNo);
//        String json = new Gson().toJson(params);
//
//
//        Util.showGifProgressDialog(context);
//        OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content(json)
//                .build()
//                .execute(new MyResultCallback<HttpResultBaseBean<List<DoctorTeamInfo>>>() {
//
//                    @Override
//                    public void onSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
//                        Util.decodeBase64Bean(bean);
//                        Util.hideGifProgressDialog(context);
//                        if (bean!=null){
//                            final List<DoctorTeamInfo> newList = bean.getData();
////            for (int i = 0; i < 20; ++i){
////                DoctorTeamInfo info = new DoctorTeamInfo("","","医生"+i, "15209999999", "", "0");
////                list.add(info);
////            }
//                            if (!ListUtils.isEmpty(newList)){
//                                final MaterialDialog dialog ;
//                                list.clear();
//                                list.addAll(newList);
//                                mAdapter.setOnItemClickListener(new SignDoctorNameAndPhoneAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                        DoctorTeamInfo dti = list.get(position);
//                                        String phone = Util.trimString(dti.getDoctorPhone());
//                                        Util.dial(context, phone);
//
//                                        dialog.dismiss();
//                                    }
//                                });
//                                try {
////                                    if (((Activity)context).hasWindowFocus()) {
//                                        dialog.show();
////                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                showDoctorPhoneInfoResult(true);
//                            }
//                        } else {
//                            showDoctorPhoneInfoResult(true);
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        Util.hideGifProgressDialog(context);
//                        showDoctorPhoneInfoResult(false);
//                    }
//                });
//
//    }
//
//    private void showDoctorPhoneInfoResult(boolean isEmptyOrFailure){
//        if (isEmptyOrFailure){
//            Util.showToast(context, Constants.HINT_NO_SIGN_DOCTOR_PHONE_INFO);
//        } else {
//            Util.showToast(context, Constants.HINT_LOAD_SIGN_DOCTOR_PHONE_INFO_FAILURE);
//        }
//    }
//
//
//}
