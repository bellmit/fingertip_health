package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 低保/特困人员供养/低收入家庭 健康情况分类统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceArchiveHealthClassificationStatisticsAdapter extends BaseQuickAdapterEx<NameValueBean, BaseViewHolder> {
    public SubsistenceArchiveHealthClassificationStatisticsAdapter(List<NameValueBean> data) {
        super(R.layout.item_subsistence_archive_health_classification_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NameValueBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_health_condition, item.getName());
        helper.setText(R.id.tv_person_number, item.getValue());
    }


}
