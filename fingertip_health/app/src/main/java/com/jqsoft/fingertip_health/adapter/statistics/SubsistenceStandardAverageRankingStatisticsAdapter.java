package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceStandardAverageRankingNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 平均补助水平排名统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceStandardAverageRankingStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceStandardAverageRankingNaturalBean, BaseViewHolder> {
    public SubsistenceStandardAverageRankingStatisticsAdapter(List<SubsistenceStandardAverageRankingNaturalBean> data) {
        super(R.layout.item_subsistence_standard_average_ranking_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceStandardAverageRankingNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_district_name, item.getDistrictName());
        helper.setText(R.id.tv_average_money_per_month_person, item.getAverageMoneyPerMonthPerson());
    }


}
