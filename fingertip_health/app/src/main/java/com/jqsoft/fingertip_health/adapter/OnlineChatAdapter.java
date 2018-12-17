package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.PersonDoctorMessageInfo;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class OnlineChatAdapter extends BaseQuickAdapterEx<PersonDoctorMessageInfo, BaseViewHolder> {
    private Context context;

    public OnlineChatAdapter(Context context, List<PersonDoctorMessageInfo> data) {
        super(R.layout.item_doctorlist, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PersonDoctorMessageInfo item) {
//        id                      在线咨询主键
//        postMessage              发送内容
//        recipient                 接收者（）
//        recipientName               接收者名称
//        sender                     发送者
//        senderName                    发送者名称
//        setTime                    发送时间
//        status                         1 未读  2已读
//        LinearLayout leftlayout = helper.getView(R.id.leftlayout);
//        LinearLayout rightlayout = helper.getView(R.id.rightlayout);

        TextView tv_number = helper.getView(R.id.tv_number);
//        ImageView img_head = helper.getView(R.id.img_head);
//        String headUrl = Util.trimString(item.getPhotoUrl());
//        String imageUrl = Version.FILE_URL_BASE + headUrl;
//        GlideUtils.loadImage(imageUrl, img_head);
        int num = Integer.parseInt(Util.trimString(item.getTotal()));
        if (num > 0) {
            tv_number.setVisibility(View.VISIBLE);
            tv_number.setText(num + "");
        } else {
            tv_number.setVisibility(View.GONE);
            tv_number.setText(num + "");
        }
        helper.setText(R.id.tv_username, Util.trimString(item.getDoctorName()));
        helper.setText(R.id.tv_lastmessage, Util.trimString(item.getMessage()));
//         helper.setText(R.id.tv_age, Util.trimString(item.getAge())+"岁");
//        if (Util.trimString(item.getSexName()).equals("男")) {
//            helper.setImageResource(R.id.iv_sex,R.mipmap.i_male);
//        }else{
//            helper.setImageResource(R.id.iv_sex,R.mipmap.i_female);
//        }
        try {
            helper.setText(R.id.recaptiontime, Util.trimString(item.getSendTime().substring(0, 19)));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.id_form_msg_date, Util.trimString(item.getSetTime()));
//        if (item.getSender().equals(Identity.getPersonID())) {
//            helper.setText(R.id.id_from_msg_info, Util.trimString(item.getPostMessage()));
//            helper.setText(R.id.msgname, Util.trimString(item.getSenderName()));
//        } else {
//            leftlayout.setVisibility(View.GONE);
//            rightlayout.setVisibility(View.VISIBLE);
//            helper.setText(R.id.id_from_msg_info2, Util.trimString(item.getPostMessage()));
//            helper.setText(R.id.msgname2, Util.trimString(item.getSenderName()));
//        }


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
