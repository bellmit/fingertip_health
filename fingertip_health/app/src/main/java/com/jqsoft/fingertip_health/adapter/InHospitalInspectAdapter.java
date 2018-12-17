//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.view.DonutProgressView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by quantan.liu on 2017/3/27.
// */
//
//public class InHospitalInspectAdapter extends BaseQuickAdapterEx<InHospitalInspectBeanList,BaseViewHolder> {
//    private Context context;
//    public InHospitalInspectAdapter(Context context,List<InHospitalInspectBeanList> data) {
//        super(R.layout.item_in_hospital_inspect,data);
//        this.context=context;
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final InHospitalInspectBeanList item) {
////        if (helper.getPosition() % 2 == 0) {
////            DensityUtil.setViewMargin(helper.itemView, false, 0, 0, 0, 40);
////        } else {
////            DensityUtil.setViewMargin(helper.itemView, false, 5, 0, 0, 40);
////        }
////        helper.setOnClickListener(R.id.iv_item_lock, new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                MaterialDialog dialog = new MaterialDialog.Builder(context)
////                        .title(R.string.hint_suggestion)
////                        .content(R.string.hint_whether_lock_hospital)
////                        .positiveText(R.string.confirm)
////                        .negativeText(R.string.cancel)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                dialog.dismiss();
////                            }
////                        }).build();
////                dialog.show();
////            }
////        });
//        helper.setText(R.id.tv_name, Util.trimString(item.getUserName()));
//        helper.setText(R.id.tv_server_date, Util.trimString("签约年份:"+item.getYear()+"年"));
//
//        TextView tv_base_name= helper.getView(R.id.tv_base_name);
//        String servercontent =Util.trimString(item.getServerContent());
//        if(!TextUtils.isEmpty(servercontent)){
//        if(servercontent.indexOf("高级")!=-1){
//            helper.setText(R.id.tv_base_name, "高级");
//            tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
//        }else if(servercontent.indexOf("中级")!=-1){
//            helper.setText(R.id.tv_base_name, "中级");
//            tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#7DD0FF"));
//        }else if(servercontent.indexOf("初级")!=-1){
//            helper.setText(R.id.tv_base_name, "初级");
//            tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#6ED1E0"));
//        }else{
//            helper.setText(R.id.tv_base_name, "基础");
//            tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#F4D249"));
//        }
//        }
//
//        String sSex = Util.trimString(item.getSexCode());
//        if(sSex.equals("1")){
//            helper.setImageResource(R.id.iv_sex,R.mipmap.i_male);
//        }else{
//            helper.setImageResource(R.id.iv_sex,R.mipmap.i_female);
//        }
//        helper.setText(R.id.tv_age, Util.trimString(item.getAge()+"岁"));
//
//        List<String> mExecuServerPackList = new ArrayList<>();
//        mExecuServerPackList.clear();
//        String sServerPack = item.getServerPackageName();
//
//        if(TextUtils.isEmpty(sServerPack) || sServerPack==null){
//            helper.setText(R.id.tv_packname,"");
//        }else{
//            String [] temp = null;
//            temp = sServerPack.split(" ");
//            String s1 =temp[0];
//            int d=temp.length;
//            mExecuServerPackList.clear();
//            for(int i=0;i<temp.length;i++){
//                mExecuServerPackList.add(temp[i]);
//            }
//
//            helper.setText(R.id.tv_packname, Util.trimString(mExecuServerPackList.get(mExecuServerPackList.size()-1)));
//        }
//
//
//
//        String sSumExecCount,sExecuedCount;
//        float proportion;
//
//
//
//        sSumExecCount= Util.trimString(item.getShouldExecCount());
//        sExecuedCount= Util.trimString(item.getHadExecCount());
//        DonutProgressView circularProgressBar= helper.getView(R.id.circular_progress_bar);
//        if(TextUtils.isEmpty(sSumExecCount) || Integer.parseInt(sSumExecCount)==0){
//         /*   circularProgressBar.setMax(100);
//            circularProgressBar.setProgress(0);*/
//            circularProgressBar.setPercentageValue(0);
//        }else{
//
//         //   proportion =(float)Integer.parseInt(sExecuedCount)/Integer.parseInt(sSumExecCount)*100;
//           // helper.setText(R.id.circular_progress_bar, Util.trimString(item.getServerPackageName()));
//
//         /*   circularProgressBar.setMax(100);
//            circularProgressBar.setProgress((int)proportion);
//            circularProgressBar.setCircleWidth(13);*/
//
//            float ratio = Util.getProgressFromTwoString(item.getHadExecCount(), item.getShouldExecCount());
//            circularProgressBar.setPercentageValue(ratio);
//
//        }
//
//
//        String headUrl = Util.trimString(item.getPhotoUrl());
//        String  imageUrl= Version.FILE_URL_BASE+headUrl;
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.img_head));
//
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
//        String spersonMold =item.getPersonMold();
//
//        if(TextUtils.isEmpty(spersonMold) || spersonMold.equals("null") || spersonMold==null){
//
//        }else{
//            ArrayList<String> spersonMoldList = new ArrayList<>();
//
//            spersonMoldList.clear();
//            if(spersonMold.substring(0,1).equals("1")){
//                spersonMoldList.add("高");
//            }
//            if(spersonMold.substring(1,2).equals("1")){
//                spersonMoldList.add("糖");
//            }
//            if(spersonMold.substring(2,3).equals("1")){
//                spersonMoldList.add("精");
//            }
//            if(spersonMold.substring(3,4).equals("1")){
//                spersonMoldList.add("老");
//            }
//            if(spersonMold.substring(4,5).equals("1")){
//                spersonMoldList.add("孕");
//            }
//            if(spersonMold.substring(5,6).equals("1")){
//                spersonMoldList.add("童");
//            }
//            if(spersonMold.substring(6,7).equals("1")){
//                spersonMoldList.add("贫");
//            }
//            if(spersonMold.substring(6,7).equals("2")){
//                spersonMoldList.add("脱");
//            }
//            if(spersonMold.substring(6,7).equals("3")){
//                spersonMoldList.add("免");
//            }
//
//            iv_tang.setVisibility(View.GONE);
//            iv_gao.setVisibility(View.GONE);
//            iv_lao.setVisibility(View.GONE);
//            iv_jing.setVisibility(View.GONE);
//            iv_mian.setVisibility(View.GONE);
//            iv_pin.setVisibility(View.GONE);
//            iv_tong.setVisibility(View.GONE);
//            iv_tuo.setVisibility(View.GONE);
//            iv_yun.setVisibility(View.GONE);
//
//            String s="";
//            for(int i=0;i<spersonMoldList.size();i++){
//                s=s+spersonMoldList.get(i);
//            }
//            if(s.indexOf("糖")!=-1){
//                iv_tang.setImageResource(R.mipmap.ic_tang);
//                iv_tang.setVisibility(View.VISIBLE);
//            }
//
//            if(s.indexOf("高")!=-1) {
//                iv_gao.setImageResource(R.mipmap.ic_gao);
//                iv_gao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("老")!=-1) {
//                iv_lao.setImageResource(R.mipmap.ic_lao);
//                iv_lao.setVisibility(View.VISIBLE);
//            }
//            if(s.indexOf("精")!=-1) {
//                iv_jing.setImageResource(R.mipmap.ic_jing);
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
//     //   helper.setMax(R.id.circular_progress_bar,100);
//    //    helper.setProgress(R.id.circular_progress_bar,Integer.parseInt(sExecuedCount));
//
//        //   helper.setText(R.id.iv_sex, Util.trimString(item.getUserName()));
//      //  helper.setText(R.id.tv_fee, Util.trimString(item.getFee()));
//      //  helper.setText(R.id.tv_disease, Util.trimString(item.getDisease()));
//      //  helper.setText(R.id.tv_hospital_name, Util.trimString(item.getHospitalName()));
////        GlideUtils.loadImage(3,item.getImgsrc(), (ImageView) helper.getView(R.id.iv_item_top_news));
////        helper.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
////            }
////        });
//
//    }
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
