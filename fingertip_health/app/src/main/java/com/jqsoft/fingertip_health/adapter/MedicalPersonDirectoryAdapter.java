package com.jqsoft.fingertip_health.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.response.MedicalPersonDirectoryResultBean;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.AppUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;



public class MedicalPersonDirectoryAdapter extends BaseQuickAdapterEx<MedicalPersonDirectoryResultBean, BaseViewHolder> {
//    private Context context;

    public MedicalPersonDirectoryAdapter(List<MedicalPersonDirectoryResultBean> data) {
        super(R.layout.item_medical_person_directory_layout, data);
//        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MedicalPersonDirectoryResultBean item) {
//        helper.setText(R.id.tv_person_name, Util.trimString(item.getDocName()+" "+item.getDocPhone()));
        helper.setText(R.id.tv_person_name, Util.trimString(item.getDocName()));
        helper.setText(R.id.tv_org_name,  Util.trimString(item.getOrgName()));
        helper.setText(R.id.tv_person_phone,  Util.trimString(item.getDocPhone()));
    //    helper.setText(R.id.tv_phone_number, Util.trimString(item.getPhoneNumber()));

        final String phoneNumber = Util.trimString(item.getDocPhone());
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
    }


}
