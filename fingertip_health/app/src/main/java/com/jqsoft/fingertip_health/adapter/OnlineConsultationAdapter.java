//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.response_new.OnlineConsultationResultBean;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.jqsoft.fingertip_health.view.DonutProgressView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class OnlineConsultationAdapter extends BaseQuickAdapterEx<OnlineConsultationResultBean, BaseViewHolder> {
//
//    public OnlineConsultationAdapter(List<OnlineConsultationResultBean> data) {
//        super(R.layout.item_online_consultation, data);
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final OnlineConsultationResultBean item) {
////        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
//        String photoUrl = Util.trimString(item.getPhotoUrl());
//        String imageUrl = Version.FILE_URL_BASE+photoUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
//        helper.setText(R.id.tv_name, Util.trimString(item.getUserName()));
//        helper.setText(R.id.tv_age, Util.trimString(item.getAge())+Constants.YEARS_OLD);
////        if (item.getIsDiabetes()){
////            helper.setVisible(R.id.iv_diabetes, true);
////        } else {
////            helper.setVisible(R.id.iv_diabetes, false);
////        }
////        if (item.getIsHypertension()){
////            helper.setVisible(R.id.iv_hypertension, true);
////        } else {
////            helper.setVisible(R.id.iv_hypertension, false);
////        }
////        if (item.getIsElderlyPeople()){
////            helper.setVisible(R.id.iv_elderly_people, true);
////        } else {
////            helper.setVisible(R.id.iv_elderly_people, false);
////        }
//
//        helper.setText(R.id.tv_package_name, Util.trimString(item.getServerPackageName()));
//        String packageLevel = Util.trimString(Constants.EMPTY_STRING);
//        String packageLevelRepresentation = Util.getPackageLevelStringFromPackageLevel(packageLevel);
//        helper.setText(R.id.tv_package_level, packageLevelRepresentation);
//        if (StringUtils.isBlank(packageLevelRepresentation)){
//            helper.setVisible(R.id.tv_package_level, false);
//        } else {
//            helper.setVisible(R.id.tv_package_level, true);
//        }
//        int packageLevelBackgroundId = Util.getBackgroundResourceIdFromPackageLevel(packageLevel);
//        TextView packageLevelTextView = helper.getView(R.id.tv_package_level);
//        packageLevelTextView.setBackgroundResource(packageLevelBackgroundId);
//
//        String hadExecCount = Util.trimString(item.getHadExecCount());
//        String shouldExecCount = Util.trimString(item.getShouldExecCount());
//        float progress = Util.getProgressFromTwoString(hadExecCount, shouldExecCount);
//        DonutProgressView dpv = helper.getView(R.id.dpv_progress);
//        dpv.setPercentageValue(progress);
//
//        helper.setText(R.id.tv_message, Util.trimString(item.getPostMessage()));
//        String time = Util.trimString(item.getSetTime());
//        String canonicalTime = Util.getYearMonthDayFromFullString(time);
//        helper.setText(R.id.tv_time, canonicalTime);
//        ImageView iv_tang =helper.getView(R.id.iv_tang);
//        ImageView iv_gao =  helper.getView(R.id.iv_gao);
//        ImageView iv_lao = helper.getView(R.id.iv_lao);
//        ImageView iv_jing =  helper.getView(R.id.iv_jing);
//        ImageView iv_mian =  helper.getView(R.id.iv_mian);
//        ImageView iv_pin =  helper.getView(R.id.iv_pin);
//        ImageView iv_tong =helper.getView(R.id.iv_tong);
//        ImageView iv_tuo =  helper.getView(R.id.iv_tuo);
//        ImageView iv_yun =  helper.getView(R.id.iv_yun);
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
//        for(int i=0;i<spersonMoldList.size();i++){
//            s=s+spersonMoldList.get(i);
//        }
//        if(s.indexOf("糖")!=-1){
//            iv_tang.setImageResource(R.mipmap.ic_tang);
//            iv_tang.setVisibility(View.VISIBLE);
//        }
//
//        if(s.indexOf("高")!=-1) {
//            iv_gao.setImageResource(R.mipmap.ic_gao);
//            iv_gao.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("老")!=-1) {
//            iv_lao.setImageResource(R.mipmap.ic_lao);
//            iv_lao.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("精")!=-1) {
//            iv_jing.setImageResource(R.mipmap.ic_jing);
//            iv_jing.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("免")!=-1) {
//            iv_mian.setImageResource(R.mipmap.ic_mian);
//            iv_mian.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("贫")!=-1) {
//            iv_pin.setImageResource(R.mipmap.ic_pin);
//            iv_pin.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("童")!=-1) {
//            iv_tong.setImageResource(R.mipmap.ic_tong);
//            iv_tong.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("脱")!=-1) {
//            iv_tuo.setImageResource(R.mipmap.ic_tuo);
//            iv_tuo.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("孕")!=-1) {
//            iv_yun.setImageResource(R.mipmap.ic_yun);
//            iv_yun.setVisibility(View.VISIBLE);
//        }
//
////        helper.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
////            }
////        });
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
