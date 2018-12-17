package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;

import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowInsBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

import static com.jqsoft.fingertip_health.R.id.btn_del;


//政策新闻适配器
public class UrbanLowInsAdapter extends BaseQuickAdapterEx<UrbanLowInsBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public UrbanLowInsAdapter(List<UrbanLowInsBean> data, int type,Context context) {
        super(R.layout.item_urbanlow, data);
        this.type = type;
        this.context=context;
    }


    DeleteListener mDeleteListener;

    public interface DeleteListener {
        public void onDeleteClick(String s,int flag,String card,String name,String itemNames,String status);
    }

    public void setOnDeleteClickListener (DeleteListener  DeleteListener) {
        this.mDeleteListener = DeleteListener;
    }


//    private int getLayoutId(){
//        int result = R.layout.item_policy_single_line;
//        if (type == TYPE_SINGLE_LINE){
//            result=R.layout.item_policy_single_line;
//        } else if (type == TYPE_MULTIPLE_LINE){
//            result=R.layout.item_policy_multiple_line;
//        }
//        return result;
//    }

    @Override
    protected void convert(final BaseViewHolder helper, final UrbanLowInsBean item) {
        final Button btn_del = helper.getView(R.id.btn_del);
        btn_del.setVisibility(View.VISIBLE);
        final TextView tv_status = helper.getView(R.id.tv_status);
        helper.setText(R.id.tv_status, Util.trimString(item.getState()));
        tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
        final String status =item.getState();
        if(status==null || TextUtils.isEmpty(status) || status.equals("null")){
            tv_status.setText("");
        }else {
            if(status.equals("0")){
                btn_del.setVisibility(View.VISIBLE);
                tv_status.setText("状态：草稿");
                tv_status.setTextColor(context.getResources().getColor(R.color.red));
            }else if(status.equals("10")){
                btn_del.setVisibility(View.VISIBLE);
                tv_status.setText("被驳回，社区处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("20")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("街道处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("30")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("社会救助中心办理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("31")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("大厅处理中 ");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("40")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("协办单位处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("88")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("办理完结");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else{
                btn_del.setVisibility(View.GONE);
                tv_status.setText(status);
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }
        }



        helper.setText(R.id.tv_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_idcard, Util.trimString(item.getCardNo()));

        helper.setText(R.id.tv_leibie, Util.trimString(item.getItemNames()));

        helper.setText(R.id.tv_applyriqi, Util.trimString(item.getCreateTime()));

        final TextView tv_applyriqitv_applyriqi = helper.getView(R.id.tv_applyriqi);
        String sapplytime =item.getCreateTime();
        if(sapplytime==null || TextUtils.isEmpty(sapplytime) || sapplytime.equals("null")){
            tv_applyriqitv_applyriqi.setText("");
        }else {
            if(sapplytime.length()<10){
                tv_applyriqitv_applyriqi.setText(sapplytime);
            }else {
                tv_applyriqitv_applyriqi.setText(sapplytime.substring(0,10));
            }

        }



        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDeleteClick(Util.trimString(item.getIdentifier()),0,"","","",status);

            }
        });

        final LinearLayout ll_baseinfo = helper.getView(R.id.ll_baseinfo);
        ll_baseinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDeleteClick(Util.trimString(item.getIdentifier()),1,"","","",item.getState());
            }
        });

        final LinearLayout ll_liucheng = helper.getView(R.id.ll_liucheng);
        ll_liucheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDeleteClick(Util.trimString(item.getIdentifier()),2,item.getCardNo(),item.getName(),item.getItemNames(),"");
            }
        });

        String sarealeavel= IdentityManager.getAreaLevel(context);

        if(sarealeavel.equals("area_4") || sarealeavel.equals("area_5")){
           // btn_del.setVisibility(View.VISIBLE);

            if(status.equals("0")){
                btn_del.setVisibility(View.VISIBLE);
                tv_status.setText("状态：草稿");
                tv_status.setTextColor(context.getResources().getColor(R.color.red));
            }else if(status.equals("10")){
                btn_del.setVisibility(View.VISIBLE);
                tv_status.setText("被驳回，社区处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("20")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("街道处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("30")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("社会救助中心办理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("31")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("大厅处理中 ");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("40")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("协办单位处理中");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else if(status.equals("88")){
                btn_del.setVisibility(View.GONE);
                tv_status.setText("办理完结");
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }else{
                btn_del.setVisibility(View.GONE);
                tv_status.setText(status);
                tv_status.setTextColor(context.getResources().getColor(R.color.subtitle_color));
            }
        }else {
            btn_del.setVisibility(View.GONE);
        }




        /*String imageUrl = item.getMessageFirstImgSrc();
        ImageView imageView = helper.getView(R.id.iv_image);
        imageView.setTag(R.id.imageId, imageUrl);
        String url = (String) imageView.getTag(R.id.imageId);
        if (url!=null && url.equals(imageUrl)) {
            GlideUtils.loadImageWithPlaceholderAndError(imageView, imageUrl, R.mipmap.i_read_id_card, R.mipmap.i_read_id_card);
        }

        helper.setText(R.id.tv_content, Util.trimString(item.getTitle()));
        String createDate = Util.trimString(item.getReleaseTime());
        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
        helper.setText(R.id.tv_date,  processedCreateDate);*/

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }

//    OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClickListener(String id, String imgUrl, View view);}

}
