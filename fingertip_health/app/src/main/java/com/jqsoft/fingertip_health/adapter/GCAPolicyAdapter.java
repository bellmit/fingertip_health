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

/**
 * Created by Administrator on 2017-12-27.
 */

public class GCAPolicyAdapter extends BaseQuickAdapterEx<PolicyBean, BaseViewHolder> {
    Context context;
    public GCAPolicyAdapter(Context context,List data) {
        super(R.layout.item_gca_policy_multiple_line, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PolicyBean item) {
//        String imageUrl = item.getMessageFirstImgSrc();
        String pictureUrl = item.getPicture();
//        pictureUrl= Util.getUrlPathWithoutLastSlashCharacter(Version.HTTP_URL)+pictureUrl;
        ImageView imageView = helper.getView(R.id.iv_image);
        imageView.setTag(R.id.imageId, pictureUrl);
        String url = (String) imageView.getTag(R.id.imageId);
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
        if (url!=null && url.equals(pictureUrl)) {
            GlideUtils.loadImageWithPlaceholderAndError(imageView, pictureUrl, R.mipmap.icon_wutu, R.mipmap.icon_wutu);
        }
        helper.setText(R.id.tv_content, item.getTitle());
        String processedDateTime = Util.getProcessedDateTimeString(item.getReleaseTime());

        if(TextUtils.isEmpty(processedDateTime) || processedDateTime==null || processedDateTime.equals("null") || (processedDateTime.length()<11)){

        }else {
            processedDateTime=processedDateTime.substring(0,10);
        }

        processedDateTime= Constants.PUBLISH_TIME +processedDateTime;
        helper.setText(R.id.tv_date, processedDateTime);
    }
}
