package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.CenterListBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//我的收藏适配器
public class CenterListAdapter extends BaseQuickAdapterEx<CenterListBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public CenterListAdapter(List<CenterListBean> data, int type) {
        super(R.layout.item_polity_single_line, data);
        this.type = type;
//        this.context=context;
    }



    @Override
    protected void convert(final BaseViewHolder helper, final CenterListBean item) {

        helper.setText(R.id.tv_content, Util.trimString(item.getName()));






    }



}
