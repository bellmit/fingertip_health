package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.HospitalTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2017-05-19.
 */

public class HospitalTypeSecondLevelAdapter extends BaseQuickAdapterEx<HospitalTypeBean, BaseViewHolder> {
    public HospitalTypeSecondLevelAdapter(List<HospitalTypeBean> data) {
        super(R.layout.item_hospital_type_second_level_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalTypeBean item) {
        if (item!=null){
            helper.setText(R.id.tv_title, item.getFeatureTitle());
        }
    }
}
