package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.ResultOpinion;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class HandleProgressDetailAdapter extends BaseQuickAdapterEx<ResultOpinion, BaseViewHolder> {

    public HandleProgressDetailAdapter(List<ResultOpinion> data) {
        super(R.layout.item_socialprogressdetail, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ResultOpinion item) {
//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;

        //   GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
        String data = Util.trimString(item.getFinishTime());
        // String nydata = data.substring(0, 11);
        String[] time = new String[0];
        try {
            time = data.split(" ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        helper.setText(R.id.hm_data, time[1]);
        helper.setText(R.id.nydata, time[0]);
        helper.setText(R.id.proname, "任务名称:" + Util.trimString(item.getDisplayName()));
        helper.setText(R.id.proadv, "处理意见:" + Util.trimString(item.getVariable()));
        helper.setText(R.id.handlemag, "任务处理人:" + Util.trimString(item.getOperator()));
        String state = item.getTaskName();
        if (state.equals("88")) {
            helper.setText(R.id.state, "已");
        } else {
            helper.setText(R.id.state, "未");
        }
//        ImageView iv_sex = helper.getView(R.id.objectsex);
//        String sex = Util.trimString(item.getSex());
//        if (sex.equals("sex_1")) {
//            iv_sex.setImageResource(R.mipmap.i_male);
//        } else {
//            iv_sex.setImageResource(R.mipmap.i_female);
//        }


//        helper.setText(R.id.tv_message, Util.trimString(item.getPostMessage()));
//        String time = Util.trimString(item.getSetTime());
//        String canonicalTime = Util.getYearMonthDayFromFullString(time);
//        helper.setText(R.id.tv_time, canonicalTime);
//        ImageView iv_tang =helper.getView(R.id.iv_tang);


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


