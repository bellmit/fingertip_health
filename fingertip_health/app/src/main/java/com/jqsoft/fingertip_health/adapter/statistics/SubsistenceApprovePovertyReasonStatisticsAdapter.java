package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApprovePovertyReasonNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 低保审批情况/特困人员供养/低收入家庭 致贫原因/退保原因适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceApprovePovertyReasonStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceApprovePovertyReasonNaturalBean, BaseViewHolder> {
    public SubsistenceApprovePovertyReasonStatisticsAdapter(List<SubsistenceApprovePovertyReasonNaturalBean> data) {
        super(R.layout.item_subsistence_approve_poverty_reason_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceApprovePovertyReasonNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_poverty_reason, item.getTitle());
        helper.setText(R.id.tv_household_number, item.getHouseholdNumber());
        helper.setText(R.id.tv_percentage, String.valueOf(item.getPercent()));
    }


}
