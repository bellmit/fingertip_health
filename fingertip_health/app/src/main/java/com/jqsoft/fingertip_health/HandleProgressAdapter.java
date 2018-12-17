package com.jqsoft.fingertip_health;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.ProgressBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


public class HandleProgressAdapter extends BaseQuickAdapterEx<ProgressBean, BaseViewHolder> {

    public HandleProgressAdapter(List<ProgressBean> data) {
        super(R.layout.item_handleprogress, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ProgressBean item) {
//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;

        //   GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.username, Util.trimString(item.getName()));
        helper.setText(R.id.idcard, Util.trimString(item.getCardNo()));
        String data = Util.trimString(item.getFinishTime());
        helper.setText(R.id.shenqingdata, data.substring(0, 11));
        helper.setText(R.id.jiuzhutype, Util.trimString(item.getItemNames()));
        TextView tv_state = helper.getView(R.id.state);
        String state = item.getState();
        if (state.equals("0")) {
            tv_state.setText("等待处理");
        } else if (state.equals("10")) {
            tv_state.setText("被驳回");
        }else if (state.equals("20")) {
            tv_state.setText("乡镇（街道）办理中");
        }else if (state.equals("30")) {
            tv_state.setText("社会救助中心办理中");
        }else if (state.equals("40")) {
            tv_state.setText("协同办理单位办理中");
        }else if (state.equals("88")) {
            tv_state.setText("办理完结");
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
