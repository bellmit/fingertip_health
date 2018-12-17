package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceAccountTrendNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 资金台账 趋势分析适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceAccountTrendStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceAccountTrendNaturalBean, BaseViewHolder> {
    public SubsistenceAccountTrendStatisticsAdapter(List<SubsistenceAccountTrendNaturalBean> data) {
        super(R.layout.item_subsistence_account_trend_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceAccountTrendNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_month_name, item.getMonthName());
        helper.setText(R.id.tv_money, item.getMoney());

    }

    private void setEmptyString(BaseViewHolder helper){
        helper.setText(R.id.tv_month_name, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_household_number, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_person_number, Constants.EMPTY_STRING);
    }

}
