package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class ImageAndTextVerticalAdapterNew extends BaseQuickAdapterEx<ImageAndTextBean, BaseViewHolder> {
    public ImageAndTextVerticalAdapterNew(List data) {
        super(R.layout.layout_image_and_text_item_new, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ImageAndTextBean item) {
        ImageAndTextBean bean = (ImageAndTextBean) item;
        helper.setImageResource(R.id.iv_image, bean.getImageId());
        helper.setText(R.id.tv_title, bean.getTitle());

    }
}
