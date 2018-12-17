package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.MyMessageBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//受理中心列表适配器
public class MyMessageAdapter extends BaseQuickAdapterEx<MyMessageBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private String filePath;
    private int type=TYPE_MULTIPLE_LINE;

    private Context context;
    public MyMessageAdapter(List<MyMessageBean> data, int type, Context context) {
        super(R.layout.item_mymessageactivity, data);
        this.type = type;
        this.context=context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final MyMessageBean item) {
        helper.setText(R.id.tv_message, Util.trimString(item.getMessage()));
        helper.setText(R.id.tv_state, Util.trimString(item.getTheme()));

        if(Util.isMyEmpty(item.getSendTime()).equals("无")){

            helper.setText(R.id.tv_time, Util.trimString(""));
        }else{
            String time= item.getSendTime().substring(0,10);
            helper.setText(R.id.tv_time, Util.trimString(time));
        }


//        helper.setText(R.id.tv_officetime, Util.trimString(item.getOfficeHours()));
//        helper.setText(R.id.tv_address, Util.trimString(item.getAddress()));
//        helper.setText(R.id.tv_officephone,Util.trimString(item.getTelephone()));
//        filePath = item.getFilePath();
//        String imageUrl = Version.FIND_FILE_URL_BASE+filePath;
//        GlideUtils.loadImageNew(imageUrl, (ImageView) helper.getView(R.id.iv_title));




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
