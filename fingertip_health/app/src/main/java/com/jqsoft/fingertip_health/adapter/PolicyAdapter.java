package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;

import java.util.List;


//政策新闻适配器
public class PolicyAdapter extends BaseQuickAdapterEx<PolicyBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public PolicyAdapter(Context context,List<PolicyBean> data, int type) {
        super(type==TYPE_MULTIPLE_LINE?R.layout.item_policy_multiple_line:R.layout.item_policy_single_line, data);
        this.type = type;
        this.context=context;
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
    protected void convert(final BaseViewHolder helper, final PolicyBean item) {
//        String imageUrl = item.getMessageFirstImgSrc();
        String pictureUrl = item.getPicture();
//        pictureUrl= Util.getUrlPathWithoutLastSlashCharacter(Version.HTTP_URL)+pictureUrl;
        ImageView imageView = helper.getView(R.id.iv_image);
        imageView.setTag(R.id.imageId, pictureUrl);
        String url = (String) imageView.getTag(R.id.imageId);
        if (url!=null && url.equals(pictureUrl)) {
            GlideUtils.loadImageWithPlaceholderAndError(imageView, pictureUrl, R.mipmap.icon_wutu, R.mipmap.icon_wutu);
        }
        String arealevel=item.getAreaLevel();

        if(TextUtils.isEmpty(arealevel)){
            helper.setVisible(R.id.arealevel, false);
        }else {
            helper.setVisible(R.id.arealevel, true);
            switch (arealevel){
                case "area_1":
                    helper.setText(R.id.arealevel,"省级");
                    helper.setBackgroundRes(R.id.ll_arealevel,R.drawable.border_red);
                    helper.setTextColor(R.id.arealevel,context.getResources().getColor(R.color.holo_red_light));
                    break;
                case "area_2":
                    helper.setText(R.id.arealevel,"市级");
                    helper.setBackgroundRes(R.id.ll_arealevel,R.drawable.border_green);
                    helper.setTextColor(R.id.arealevel,context.getResources().getColor(R.color.holo_green_light));
                    break;
                case "area_3":
                    helper.setText(R.id.arealevel,"县级");
                    helper.setBackgroundRes(R.id.ll_arealevel,R.drawable.border_blue);
                    helper.setTextColor(R.id.arealevel,context.getResources().getColor(R.color.holo_blue_light));
                    break;
                case "area_4":
                    helper.setText(R.id.arealevel,"乡级");
                    helper.setBackgroundRes(R.id.ll_arealevel,R.drawable.border_blue);
                    helper.setTextColor(R.id.arealevel,context.getResources().getColor(R.color.holo_blue_light));
                    break;
                case "area_5":
                    helper.setText(R.id.arealevel,"村级");
                    helper.setBackgroundRes(R.id.ll_arealevel,R.drawable.border_blue);
                    helper.setTextColor(R.id.arealevel,context.getResources().getColor(R.color.holo_blue_light));
                    break;
            }
        }
        helper.setText(R.id.tv_content, Util.trimString(item.getTitle()));
        String createDate = Util.trimString(item.getReleaseTime());
        String processedCreateDate = Util.getProcessedDateTimeString(createDate);
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
        if(TextUtils.isEmpty(processedCreateDate) || processedCreateDate==null || processedCreateDate.equals("null") || (processedCreateDate.length()<11)){

        }else {
            processedCreateDate=processedCreateDate.substring(0,10);
        }
            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
        helper.setText(R.id.tv_date,  processedCreateDate);

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
