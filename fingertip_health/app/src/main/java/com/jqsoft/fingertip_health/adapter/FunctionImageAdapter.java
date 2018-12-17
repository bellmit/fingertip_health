package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.FunctionImageBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class FunctionImageAdapter extends BaseQuickAdapterEx<FunctionImageBean, BaseViewHolder> {
    public FunctionImageAdapter(List data) {
        super(R.layout.layout_function_image_item, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, FunctionImageBean item) {
        FunctionImageBean functionImageBean = (FunctionImageBean)item;
        helper.setImageResource(R.id.imageView, functionImageBean.getImageResourceId());
    }
}
