package com.jqsoft.fingertip_health.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.AppUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */

public class VillageLevelMedicalInstitutionDirectoryAdapter extends BaseQuickAdapterEx<VillageLevelMedicalInstitutionDirectoryResultBean, BaseViewHolder> {
    public VillageLevelMedicalInstitutionDirectoryAdapter(List<VillageLevelMedicalInstitutionDirectoryResultBean> data) {
        super(R.layout.item_village_level_medical_institution_directory_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VillageLevelMedicalInstitutionDirectoryResultBean item) {
        helper.setText(R.id.tv_village_name, Util.trimString(item.getOrgName()));
        helper.setText(R.id.tv_village_number, Util.trimString(item.getCount()+"名医疗人员"));

        final String phoneNumber = Util.trimString(item.getDocPhone());
        if (TextUtils.isEmpty(phoneNumber)){
            helper.setVisible(R.id.tv_village_phone, false);
        } else {
            helper.setVisible(R.id.tv_village_phone, true);
            helper.setText(R.id.tv_village_phone, item.getDocPhone());
        }


        final View dialView = helper.getView(R.id.iv_phone);
        dialView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                if (!StringUtils.isBlank(phoneNumber)){
                    AppUtils.actionDial(dialView.getContext(),
                            phoneNumber);
                } else {
                    Util.showToast(dialView.getContext(),
                            Constants.HINT_PHONE_NUMBER_EMPTY);
                }

            }
        });

     //   helper.setText(R.id.tv_code, Util.trimString(item.getCode()));
     //   TextView tvNumber = helper.getView(R.id.tv_number);
     //   String numberString = String.format(tvNumber.getContext().getResources().getString(R.string.village_level_medical_institution_administration_number), Util.trimString(item.getNumberOfMedicalPersonnel()));
   //     tvNumber.setText(numberString);
    }
}
