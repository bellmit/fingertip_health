package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolityBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//民政指南适配器
public class PolityAdapter extends BaseQuickAdapterEx<PolityBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public PolityAdapter(List<PolityBean> data, int type) {
        super(R.layout.item_polity_single_line, data);
        this.type = type;
//        this.context=context;
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
    protected void convert(final BaseViewHolder helper, final PolityBean item) {
        helper.setText(R.id.tv_content, Util.trimString(item.getName()));//getname
        item.getCode();

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
