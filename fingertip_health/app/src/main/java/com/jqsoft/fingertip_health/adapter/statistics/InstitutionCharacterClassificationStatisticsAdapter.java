package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionCharacterNameValueBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 机构性质分类统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class InstitutionCharacterClassificationStatisticsAdapter extends BaseQuickAdapterEx<InstitutionCharacterNameValueBean, BaseViewHolder> {
    public InstitutionCharacterClassificationStatisticsAdapter(List<InstitutionCharacterNameValueBean> data) {
        super(R.layout.item_institution_character_classification_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InstitutionCharacterNameValueBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_institution_character, item.getName());
        helper.setText(R.id.tv_institution_number, item.getValue());
        helper.setText(R.id.tv_bed_number, item.getBednum());
        helper.setText(R.id.tv_percentage, item.getPercent());
    }


}
