package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.TempDisasterAssistancePercentageBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 *
 * Created by Administrator on 2018-01-04.
 */

public class TempDisasterAssistancePercentageStatisticsAdapter extends BaseQuickAdapterEx<TempDisasterAssistancePercentageBean, BaseViewHolder> {
    public TempDisasterAssistancePercentageStatisticsAdapter(List<TempDisasterAssistancePercentageBean> data) {
        super(R.layout.item_temp_disaster_assistance_percentage_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TempDisasterAssistancePercentageBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_value, item.getValue());
    }


}
