//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.ReservationBeanList;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by quantan.liu on 2017/3/27.
// */
//
//public class ReservationHadedAdapter extends BaseQuickAdapterEx<ReservationBeanList, BaseViewHolder> {
//    private Context context;
//    String address_shangmen="",address_cunsi="",address_xiangz="",address_other="";
//
//    public ReservationHadedAdapter(Context context, List<ReservationBeanList> data) {
//        super(R.layout.item_reservation_had, data);
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
//
//        String headUrl = Util.trimString(item.getPhotoUrl());
//        String  imageUrl= Version.FILE_URL_BASE+ headUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.img_head));
//
//
//
//
//
//
//        String serverPlaceType = item.getReservationServrtPlace();
//        if(serverPlaceType.equals("1")){
//            rb_adress1.setEnabled(true);
//            rb_adress1.setChecked(true);
//
//
//            rb_adress2.setEnabled(false);
//            rb_adress3.setEnabled(false);
//            rb_adress4.setEnabled(false);
//
//        }else if(serverPlaceType.equals("2")){
//            rb_adress2.setEnabled(true);
//            rb_adress2.setChecked(true);
//
//            rb_adress1.setEnabled(false);
//            rb_adress3.setEnabled(false);
//            rb_adress4.setEnabled(false);
//
//        }else if(serverPlaceType.equals("3")){
//            rb_adress4.setEnabled(true);
//            rb_adress4.setChecked(true);
//            rb_adress1.setEnabled(false);
//            rb_adress2.setEnabled(false);
//            rb_adress3.setEnabled(false);
//
//
//            et_execu_addressother.setVisibility(View.VISIBLE);
//            et_execu_addressother.setEnabled(false);
//            et_execu_addressother.setText(item.getReservationServrtPlaceOther());
//        }else if(serverPlaceType.equals("4")){
//            rb_adress3.setEnabled(true);
//            rb_adress3.setChecked(true);
//            rb_adress1.setEnabled(false);
//            rb_adress2.setEnabled(false);
//            rb_adress4.setEnabled(false);
//
//        }else{
//            rb_adress1.setEnabled(false);
//            rb_adress2.setEnabled(false);
//            rb_adress3.setEnabled(false);
//            rb_adress4.setEnabled(false);
//        }
//
//
//     /*   rb_adress1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                rg_adress2.clearCheck();
//                address_shangmen="1";
//                address_cunsi="";
//                address_xiangz="";
//                address_other="";
//                et_execu_addressother.setVisibility(View.GONE);
//             //   Toast.makeText(context,"??????"+address_shangmen,Toast.LENGTH_SHORT).show();
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
//             //   Toast.makeText(context,"??????"+address_cunsi,Toast.LENGTH_SHORT).show();
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
//             //   Toast.makeText(context,"??????"+address_xiangz,Toast.LENGTH_SHORT).show();
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
//                et_execu_addressother.setVisibility(View.VISIBLE);
//               // Toast.makeText(context,"??????"+address_other,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//*/
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
//       tv_applyage.setText(item.getAge()+"???");
//        tv_applycard.setText(item.getCardNo());
//        tv_filename.setText(item.getNo());
//       tv_applyadrress.setText(item.getAddress());
//        tv_applypackname.setText(item.getPakageName());
//        tv_applyservername.setText(item.getServerContent());
//
//       // tv_applydate.setText(item.getServerTime());
//
//        String CreateDate = item.getServerTime();
//        if(TextUtils.isEmpty(CreateDate) || CreateDate.equals("null") || CreateDate==null){
//            tv_applydate.setText("");
//        }else{
//            if(CreateDate.length()<10){
//                tv_applydate.setText(item.getServerTime());
//            }else{
//
//                tv_applydate.setText(item.getServerTime().substring(0,10));
//            }
//        }
//
//
//        String spersonMold =item.getPersonMold();
//
//        ArrayList<String> spersonMoldList = new ArrayList<>();
//
//        spersonMoldList.clear();
//        if(spersonMold.substring(0,1).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(1,2).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(2,3).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(3,4).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(4,5).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(5,6).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(6,7).equals("1")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(6,7).equals("2")){
//            spersonMoldList.add("???");
//        }
//        if(spersonMold.substring(6,7).equals("3")){
//            spersonMoldList.add("???");
//        }
//
//        String s="";
//
//
//        for(int i=0;i<spersonMoldList.size();i++){
//            s=s+spersonMoldList.get(i);
//        }
//
//        LinearLayout ll_label= helper.getView(R.id.ll_label);
//        if(spersonMoldList.size()==0){
//            ll_label.setVisibility(View.GONE);
//        }else{
//            ll_label.setVisibility(View.VISIBLE);
//            if(s.indexOf("???")!=-1){
//                iv_tang.setImageResource(R.mipmap.ic_tang);
//                iv_tang.setVisibility(View.VISIBLE);
//            }
//
//            if(s.indexOf("???")!=-1) {
//                iv_gao.setImageResource(R.mipmap.ic_gao);
//                iv_gao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_lao.setImageResource(R.mipmap.ic_lao);
//                iv_lao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_jing.setImageResource(R.mipmap.ic_jing);
//                iv_jing.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_mian.setImageResource(R.mipmap.ic_mian);
//                iv_mian.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_pin.setImageResource(R.mipmap.ic_pin);
//                iv_pin.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_tong.setImageResource(R.mipmap.ic_tong);
//                iv_tong.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
//                iv_tuo.setImageResource(R.mipmap.ic_tuo);
//                iv_tuo.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("???")!=-1) {
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
