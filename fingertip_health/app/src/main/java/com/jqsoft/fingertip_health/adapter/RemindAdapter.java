package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;


public class RemindAdapter extends BaseMultiItemQuickAdapter<RemindBean, BaseViewHolder> {
    private Context context;

    public RemindAdapter(List<RemindBean> data) {
        super(data);
//        this.context=context;
        addItemType(RemindBean.TYPE_REMIND, R.layout.item_remind);
        addItemType(RemindBean.TYPE_MESSAGE, R.layout.item_message_sent_by_doctor_remind);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final RemindBean item) {
        switch (helper.getItemViewType()) {
            case RemindBean.TYPE_REMIND:
                helper.setImageResource(R.id.iv_image, R.mipmap.r_remind_1);
                helper.setText(R.id.tv_content, Util.trimString(item.getCompressedDateCombinedString()));
                break;
            case RemindBean.TYPE_MESSAGE:
                helper.setImageResource(R.id.iv_image, R.mipmap.r_remind_3);
                String contentName = Util.trimString(item.getContentName());//最新的一条消息内容
                if (StringUtils.isBlank(contentName)){
                    contentName= Constants.HINT_NO_MESSAGES_SINCE;
                }
                helper.setText(R.id.tv_content, contentName);
                String messageCount = Util.trimString(item.getPackageName());//消息数量
                String processedMessageCount = Util.getDisplayNumberString(messageCount);
                helper.setText(R.id.tv_message_count, processedMessageCount);
                break;
        }


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
