package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.TownLevelMedicalInstitutionBeanList;
import com.jqsoft.fingertip_health.bean.response.TownLevelMedicalInstitutionDirectoryResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */

public class TownLevelMedicalInstitutionDirectoryAdapter extends BaseQuickAdapterEx<TownLevelMedicalInstitutionDirectoryResultBean, BaseViewHolder> {
    public TownLevelMedicalInstitutionDirectoryAdapter(List<TownLevelMedicalInstitutionBeanList> data) {
        super(R.layout.item_town_level_medical_institution_directory_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TownLevelMedicalInstitutionDirectoryResultBean item) {
       /* helper.setText(R.id.tv_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_code, Util.trimString(item.getCode()));
        TextView tvNumber = helper.getView(R.id.tv_number);
        String numberString = String.format(tvNumber.getContext().getResources().getString(R.string.town_level_medical_institution_administration_number), Util.trimString(item.getNumberOfVillageLevelInstitution()));
        tvNumber.setText(numberString);*/
    }
}
