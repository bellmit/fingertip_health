///*
//package com.jqsoft.fingertip_health.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
//import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
//import com.jqsoft.fingertip_health.bean.ReservationBeanList;
//import com.jqsoft.fingertip_health.di.ui.activity.ExecuProjectActivity;
//import com.jqsoft.fingertip_health.di.ui.activity.ReservationServiceActivity;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//
//*/
///**
// * Created by quantan.liu on 2017/3/27.
// *//*
//
//
//public class ReservationApplyingAdapter extends BaseQuickAdapterEx<ReservationBeanList, BaseViewHolder> {
//    private Context context;
//    String address_shangmen="",address_cunsi="",address_xiangz="",address_other="";
//
//    public ReservationApplyingAdapter(Context context, List<ReservationBeanList> data) {
//        super(R.layout.item_reservation_apply, data);
//        this.context=context;
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final ReservationBeanList item) {
//       // helper.setText(R.id.tv_applyname, Util.trimString(item.getUserName()));
//
//
//        final SmoothRadioGroup rg_adress1= helper.getView(R.id.rg_adress1);
//        final SmoothRadioGroup rg_adress2= helper.getView(R.id.rg_adress2);
//
//        SmoothRadioButton rb_adress1= helper.getView(R.id.rb_adress1);
//        SmoothRadioButton rb_adress2= helper.getView(R.id.rb_adress2);
//        SmoothRadioButton rb_adress3= helper.getView(R.id.rb_adress3);
//        SmoothRadioButton rb_adress4= helper.getView(R.id.rb_adress4);
//
//
//
//
//        TextView tv_applyname =  helper.getView(R.id.tv_applyname);
//        ImageView iv_applysex =  helper.getView(R.id.iv_applysex);
//        TextView tv_applyage = helper.getView(R.id.tv_applyage);
//        TextView tv_applycard =  helper.getView(R.id.tv_applycard);
//        TextView tv_filename =  helper.getView(R.id.tv_filename);
//        TextView tv_applyadrress =  helper.getView(R.id.tv_applyadrress);
//        TextView tv_applypackname =  helper.getView(R.id.tv_applypackname);
//        TextView tv_applyservername =  helper.getView(R.id.tv_applyservername);
//        TextView tv_applydate =  helper.getView(R.id.tv_applydate);
//
//        ImageView iv_tang =helper.getView(R.id.iv_tang);
//        ImageView iv_gao =  helper.getView(R.id.iv_gao);
//        ImageView iv_lao = helper.getView(R.id.iv_lao);
//        ImageView iv_jing =  helper.getView(R.id.iv_jing);
//        ImageView iv_mian =  helper.getView(R.id.iv_mian);
//        ImageView iv_pin =  helper.getView(R.id.iv_pin);
//        ImageView iv_tong =helper.getView(R.id.iv_tong);
//        ImageView iv_tuo =  helper.getView(R.id.iv_tuo);
//        ImageView iv_yun =  helper.getView(R.id.iv_yun);
//        ImageView iv_applyphone = helper.getView(R.id.iv_applyphone);
//        TextView tv_execuserver = helper.getView(R.id.tv_execuserver);
//        final EditText et_execu_addressother =helper.getView(R.id.et_execu_addressother);
//
//        String headUrl = Util.trimString(item.getPhotoUrl());
//        String  imageUrl= Version.FILE_URL_BASE+headUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.img_head));
//
//
//
//        rb_adress1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rg_adress2.clearCheck();
//                address_shangmen="1";
//                address_cunsi="";
//                address_xiangz="";
//                address_other="";
//                et_execu_addressother.setVisibility(View.GONE);
//             //   Toast.makeText(context,"上门"+address_shangmen,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        rb_adress2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rg_adress2.clearCheck();
//                address_shangmen="";
//                address_cunsi="2";
//                address_xiangz="";
//                address_other="";
//                et_execu_addressother.setVisibility(View.GONE);
//             //   Toast.makeText(context,"村室"+address_cunsi,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        rb_adress3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rg_adress2.clearCheck();
//                address_shangmen="";
//                address_cunsi="";
//                address_xiangz="3";
//                address_other="";
//                et_execu_addressother.setVisibility(View.GONE);
//             //   Toast.makeText(context,"乡镇"+address_xiangz,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        rb_adress4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rg_adress1.clearCheck();
//                address_shangmen="";
//                address_cunsi="";
//                address_xiangz="";
//                address_other="4";
//                et_execu_addressother.setVisibility(View.GONE);
//               // Toast.makeText(context,"其他"+address_other,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//
//       tv_applyname.setText(item.getUserName());
//        String sSex =item.getSexCode();
//        if(sSex.equals("1")){
//            iv_applysex.setImageResource(R.mipmap.i_male);
//
//        }else{
//           iv_applysex.setImageResource(R.mipmap.i_female);
//        }
//       tv_applyage.setText(item.getAge()+"岁");
//        tv_applycard.setText(item.getCardNo());
//        tv_filename.setText(item.getNo());
//       tv_applyadrress.setText(item.getAddress());
//        tv_applypackname.setText(item.getPakageName());
//        tv_applyservername.setText(item.getServerContent());
//
//        String CreateDate = item.getReservationTime();
//        if(TextUtils.isEmpty(CreateDate) || CreateDate.equals("null") || CreateDate==null){
//            tv_applydate.setText("");
//        }else{
//            if(CreateDate.length()<10){
//                tv_applydate.setText(item.getReservationTime());
//            }else{
//
//                tv_applydate.setText(item.getReservationTime().substring(0,10));
//            }
//        }
//
//
//
//
//        String spersonMold =item.getPersonMold();
//
//        ArrayList<String> spersonMoldList = new ArrayList<>();
//
//        spersonMoldList.clear();
//        if(spersonMold.substring(0,1).equals("1")){
//            spersonMoldList.add("高");
//        }
//        if(spersonMold.substring(1,2).equals("1")){
//            spersonMoldList.add("糖");
//        }
//        if(spersonMold.substring(2,3).equals("1")){
//            spersonMoldList.add("精");
//        }
//        if(spersonMold.substring(3,4).equals("1")){
//            spersonMoldList.add("老");
//        }
//        if(spersonMold.substring(4,5).equals("1")){
//            spersonMoldList.add("孕");
//        }
//        if(spersonMold.substring(5,6).equals("1")){
//            spersonMoldList.add("童");
//        }
//        if(spersonMold.substring(6,7).equals("1")){
//            spersonMoldList.add("贫");
//        }
//        if(spersonMold.substring(6,7).equals("2")){
//            spersonMoldList.add("脱");
//        }
//        if(spersonMold.substring(6,7).equals("3")){
//            spersonMoldList.add("免");
//        }
//
//        String s="";
//
//        LinearLayout ll_label= helper.getView(R.id.ll_label);
//
//        for(int i=0;i<spersonMoldList.size();i++){
//            s=s+spersonMoldList.get(i);
//        }
//
//        if(spersonMoldList.size()==0){
//            ll_label.setVisibility(View.GONE);
//        }else{
//            ll_label.setVisibility(View.VISIBLE);
//            if(s.indexOf("糖")!=-1){
//
//                iv_tang.setVisibility(View.VISIBLE);
//            }
//
//            if(s.indexOf("高")!=-1) {
//                iv_gao.setImageResource(R.mipmap.ic_gao);
//                iv_gao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("老")!=-1) {
//
//                iv_lao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("精")!=-1) {
//
//                iv_jing.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("免")!=-1) {
//                iv_mian.setImageResource(R.mipmap.ic_mian);
//                iv_mian.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("贫")!=-1) {
//                iv_pin.setImageResource(R.mipmap.ic_pin);
//                iv_pin.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("童")!=-1) {
//                iv_tong.setImageResource(R.mipmap.ic_tong);
//                iv_tong.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("脱")!=-1) {
//                iv_tuo.setImageResource(R.mipmap.ic_tuo);
//                iv_tuo.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("孕")!=-1) {
//                iv_yun.setImageResource(R.mipmap.ic_yun);
//                iv_yun.setVisibility(View.VISIBLE);
//            }
//        }
//
//
//
//       iv_applyphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!StringUtils.isBlank(item.getPhone())){
//                    AppUtils.actionDial(context,
//                           item.getPhone());
//                } else {
//                    Util.showToast(context,
//                            Constants.HINT_PHONE_NUMBER_EMPTY);
//                }
//                // Toast.makeText(getActivity(),mpeopleBasebean.getPhone(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//       tv_execuserver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PeopleBaseInfoBean mpeopleBasebean;
//                PendExecuBeanList mPendExecuBeanList;
//                mpeopleBasebean=item.getSignUserInfo();
//                mPendExecuBeanList=item.getSignPromExec();
//
//                String sEtaddressother =et_execu_addressother.getText().toString();
//                Intent intent = new Intent(context,ExecuProjectActivity.class);
//                intent.putExtra("address_shangmen", address_shangmen);
//                intent.putExtra("address_cunsi", address_cunsi);
//                intent.putExtra("address_xiangz", address_xiangz);
//                intent.putExtra("address_other", address_other);
//                intent.putExtra("sEtaddressother", sEtaddressother);
//                intent.putExtra("flag", "2");
//                intent.putExtra("orderServiceId", item.getServicePlanID());
//
//                intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
//                intent.putExtra("PendExecuBeanList", (Serializable)mPendExecuBeanList);
//                context.startActivity(intent);
//            }
//        });
//
//        TextView tv_delete =  helper.getView(R.id.tv_delete);
//
//        tv_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MaterialDialog dialog = new MaterialDialog.Builder(context)
//                        .title(R.string.hint_suggestion)
//                        .content("确定要取消申请吗?")
//                        .negativeText(R.string.cancel)
//                        .positiveText(R.string.confirm)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull
//                                                        MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                                ReservationServiceActivity myActivity = (ReservationServiceActivity) context;
//                                myActivity.deleteExecuInfo(item);
//
//
//                            }
//                        }).build();
//                dialog.show();
//
//
//
//
//            }
//        });
//
//
//
//    }
//
////    OnItemClickListener onItemClickListener;
////
////    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
////        this.onItemClickListener = onItemClickListener;
////    }
////
////    public interface OnItemClickListener {
////        void onItemClickListener(String id, String imgUrl, View view);}
//
//}
//*/
