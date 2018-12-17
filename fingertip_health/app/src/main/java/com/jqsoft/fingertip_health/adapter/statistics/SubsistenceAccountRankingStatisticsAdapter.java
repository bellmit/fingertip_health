package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceAccountRankingNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 资金台账排名统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceAccountRankingStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceAccountRankingNaturalBean, BaseViewHolder> {
    public SubsistenceAccountRankingStatisticsAdapter(List<SubsistenceAccountRankingNaturalBean> data) {
        super(R.layout.item_subsistence_account_ranking_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceAccountRankingNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));

        helper.setText(R.id.tv_district_name, item.getDistrictName());
        helper.setText(R.id.tv_money, item.getMoney());
    }


}
