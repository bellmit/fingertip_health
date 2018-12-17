package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.AdviceBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//咨询建议适配器
public class ReplyAdapter extends BaseQuickAdapterEx<AdviceBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public ReplyAdapter(List<AdviceBean> data, int type) {
        super(R.layout.item_consult_single_line, data);
            this.type = type;
//        this.context=context;
    }



    @Override
    protected void convert(final BaseViewHolder helper, final AdviceBean item) {

        helper.setText(R.id.tv_title, Util.trimString(item.getTitle()));
        helper.setText(R.id.tv_content, "回复内容: "+Util.trimString(item.getReplyContent()));
        if (!TextUtils.isEmpty(item.getReplyTime())){
            helper.setText(R.id.tv_time,"回复时间: " + Util.trimString(item.getReplyTime().replace('T',' ')));
        }else {
            helper.setVisible(R.id.tv_time,false);
        }



//        String createDate = Util.trimString(item.getReleaseTime());
//        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
//            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
//        helper.setText(R.id.tv_date,  processedCreateDate);

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }



}
