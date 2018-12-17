package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValuePercentBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 管理服务人员分类统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class InstitutionLegalPersonClassificationStatisticsAdapter extends BaseQuickAdapterEx<NameValuePercentBean, BaseViewHolder> {
    public InstitutionLegalPersonClassificationStatisticsAdapter(List<NameValuePercentBean> data) {
        super(R.layout.item_institution_legal_person_classification_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NameValuePercentBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_legal_person_type, item.getName());
        helper.setText(R.id.tv_institution_number, item.getValue());
        helper.setText(R.id.tv_percentage, item.getPercent());
    }


}
