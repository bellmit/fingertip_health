package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.TitleAndValueBean;

import java.util.List;

/**
 * 选择列表
 * Created by Administrator on 2018-01-01.
 */

public class TitleAndValueListTextAdapter extends BaseQuickAdapterEx<TitleAndValueBean, BaseViewHolder> {
    public TitleAndValueListTextAdapter(List<TitleAndValueBean> list) {
        super(R.layout.item_title_and_value_text_layout, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TitleAndValueBean item) {
        helper.setText(R.id.tv_text, item.getTitle());
    }

}
