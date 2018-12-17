package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.FamilyEconomyCheckShareIndexBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 家庭经济情况核对-信息共享指标统计-适配器
 * Created by Administrator on 2018-01-04.
 */

public class FamilyEconomyCheckShareIndexStatisticsAdapter extends BaseQuickAdapterEx<FamilyEconomyCheckShareIndexBean, BaseViewHolder> {
    public FamilyEconomyCheckShareIndexStatisticsAdapter(List<FamilyEconomyCheckShareIndexBean> data) {
        super(R.layout.item_family_economy_check_share_index_2_column_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyEconomyCheckShareIndexBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_first, item.getFirstValue());
    }



}
