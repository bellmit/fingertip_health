package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.AreaBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class AreaAdapter extends BaseQuickAdapterEx<AreaBean, BaseViewHolder> {
    public AreaAdapter(List data) {
        super(R.layout.layout_area_item, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
        AreaBean areaBean = (AreaBean) item;
        helper.setText(R.id.area_name, areaBean.getName());

    }
}
