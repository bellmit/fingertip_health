package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilyBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.IDCard;

import java.util.List;


//政策新闻适配器
public class UrbanLowFamilyAdapter extends BaseQuickAdapterEx<UrbanLowFamilyBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;
    private String status;


    DeleteListener mDeleteListener;

    public interface DeleteListener {
        public void onDeleteClick(String s,int flag,String relation);
    }

    public void setOnDeleteClickListener (DeleteListener  DeleteListener) {
        this.mDeleteListener = DeleteListener;
    }

    public UrbanLowFamilyAdapter(List<UrbanLowFamilyBean> data, int type,Context context,String status) {
        super(R.layout.item_urbanlow_family, data);
        this.type = type;
        this.context=context;
        this.status=status;
    }



    @Override
    protected void convert(final BaseViewHolder helper, final UrbanLowFamilyBean item) {
        final String sarealeavel= IdentityManager.getAreaLevel(context);
        helper.setText(R.id.tv_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_idcard, Util.trimString(item.getCardNo()));

        final TextView tv_sex = helper.getView(R.id.tv_sex);
        tv_sex.setText(IDCard.getSex(Util.trimString(item.getCardNo())));

        final TextView tv_birth = helper.getView(R.id.tv_birth);
        tv_birth.setText(IDCard.getbirthdayNew(Util.trimString(item.getCardNo())));



        final Button btn_bianji = helper.getView(R.id.btn_bianji);
        final Button btn_del = helper.getView(R.id.btn_del);

        final TextView tv_relation = helper.getView(R.id.tv_relation);
        tv_relation.setText(item.getRelationName());

        if(sarealeavel.equals("area_4") || sarealeavel.equals("area_5")){

            if(status.equals("0") || status.equals("10")){

                btn_bianji.setVisibility(View.VISIBLE);
                btn_del.setText("删除");



            }else {
                btn_bianji.setVisibility(View.GONE);
                btn_del.setText("查看");
            }
        }else {
            btn_bianji.setVisibility(View.GONE);
            btn_del.setText("查看");
        }






        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sarealeavel.equals("area_4") || sarealeavel.equals("area_5")){

                    mDeleteListener.onDeleteClick(Util.trimString(item.getId()),0,item.getRelationName());
                }else {
                    Toast.makeText(context,"您没有权限编辑信息!",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   if(btn_del.getText().equals("查看")){
                       mDeleteListener.onDeleteClick(Util.trimString(item.getId()),3,item.getRelationName());
                   }else {
                       if(status.equals("0") || status.equals("10")){
                           if(sarealeavel.equals("area_4") || sarealeavel.equals("area_5")){

                               if(item.getRelationName().equals("本人")){
                                   Toast.makeText(context,"本人不能删除",Toast.LENGTH_SHORT).show();
                               }else {
                                   mDeleteListener.onDeleteClick(Util.trimString(item.getId()),1,item.getRelationName());
                               }

                           }else {
                               Toast.makeText(context,"您没有权限删除信息!",Toast.LENGTH_SHORT).show();
                           }
                       }else {

                           mDeleteListener.onDeleteClick(Util.trimString(item.getId()),1,item.getRelationName());



                       }

                   }







            }
        });



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
