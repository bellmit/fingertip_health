package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionRankingNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 供养机构排名统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class InstitutionRankingStatisticsAdapter extends BaseQuickAdapterEx<InstitutionRankingNaturalBean, BaseViewHolder> {
    public InstitutionRankingStatisticsAdapter(List<InstitutionRankingNaturalBean> data) {
        super(R.layout.item_institution_ranking_statistics_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InstitutionRankingNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_district_name, item.getDistrictName());
//        helper.setText(R.id.tv_institution_number, item.getInstitutionNumber());
        helper.setText(R.id.tv_bed_number, item.getBedNumber());

    }


}
