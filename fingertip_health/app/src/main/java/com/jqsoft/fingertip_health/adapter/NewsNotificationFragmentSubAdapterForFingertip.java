package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.TodoSubItemBean;

import java.util.List;


public class NewsNotificationFragmentSubAdapterForFingertip extends BaseQuickAdapterEx<TodoSubItemBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;

    public NewsNotificationFragmentSubAdapterForFingertip(List<TodoSubItemBean> data) {
        super(R.layout.item_find_sub_layout, data);
        this.type = type;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final TodoSubItemBean item) {
    }


}
