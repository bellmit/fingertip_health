package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.PersonMessage;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class OnlineChatDetailAdapter extends BaseQuickAdapterEx<PersonMessage, BaseViewHolder> {
    private Context context;

    public OnlineChatDetailAdapter(Context context, List<PersonMessage> data) {
        super(R.layout.item_from_msg, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PersonMessage item) {
//        id                      在线咨询主键
//        postMessage              发送内容
//        recipient                 接收者（）
//        recipientName               接收者名称
//        sender                     发送者
//        senderName                    发送者名称
//        setTime                    发送时间
//        status                         1 未读  2已读
        LinearLayout leftlayout = helper.getView(R.id.leftlayout);
        LinearLayout rightlayout = helper.getView(R.id.rightlayout);
        try {
            helper.setText(R.id.id_form_msg_date, Util.trimString(item.getSetTime()).substring(0, 19));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String asa = IdentityManager.getPersonID(context);
        if (item.getSender().equals(IdentityManager.getPersonID(context))) {//341222_110804018490
            helper.setText(R.id.id_from_msg_info2, Util.trimString(item.getPostMessage()));
            helper.setText(R.id.msgname2, Util.trimString(item.getSenderName()));
            rightlayout.setVisibility(View.VISIBLE);
            leftlayout.setVisibility(View.GONE);
            ImageView img_head = helper.getView(R.id.msgtou2);
            String headUrl = Util.trimString(item.getPhotoUrl());
            String f =Version.FILE_URL_BASE;
            String imageUrl = Version.FILE_URL_BASE + headUrl;
            GlideUtils.loadImage(imageUrl, img_head);
            PreferencesUtils.putString(context,"imageUrl",imageUrl);
        } else {
            leftlayout.setVisibility(View.VISIBLE);
            rightlayout.setVisibility(View.GONE);
            helper.setText(R.id.id_from_msg_info, Util.trimString(item.getPostMessage()));
            helper.setText(R.id.msgname, Util.trimString(item.getSenderName()));

        }


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
