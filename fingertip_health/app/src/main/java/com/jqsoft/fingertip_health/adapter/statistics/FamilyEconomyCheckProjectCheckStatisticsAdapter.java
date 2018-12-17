package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 家庭经济情况核对-核对项目类统计-适配器
 * Created by Administrator on 2018-01-04.
 */

//public class FamilyEconomyCheckProjectCheckStatisticsAdapter extends BaseQuickAdapter<NameValueBean, BaseViewHolder> {
public class FamilyEconomyCheckProjectCheckStatisticsAdapter extends BaseQuickAdapterEx<NameValueBean, BaseViewHolder> {
    public FamilyEconomyCheckProjectCheckStatisticsAdapter(List<NameValueBean> data) {
        super(R.layout.item_family_economy_check_project_check_2_column_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NameValueBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_value, item.getValue());
    }



}
