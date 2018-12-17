package com.jqsoft.fingertip_health.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.SignManagementReadBeanForFingertip;
import com.jqsoft.fingertip_health.feature.SignManagementReadSelectListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


public class SignManagementReadAdapterForFingertip extends BaseQuickAdapterEx<SignManagementReadBeanForFingertip, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private SignManagementReadSelectListener listener;

    public SignManagementReadAdapterForFingertip(SignManagementReadSelectListener listener, List<SignManagementReadBeanForFingertip> data) {
        super(R.layout.item_sign_management_read_layout, data);
        this.type = type;
        this.listener=listener;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final SignManagementReadBeanForFingertip item) {
        helper.setText(R.id.tv_person_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_person_gender, Util.trimString(item.getSexName()));
        helper.setText(R.id.tv_person_age, Util.trimString(item.getAge()));
        helper.setText(R.id.tv_person_birthdate, "("+Util.trimString(item.getBirthday())+")");
        helper.setText(R.id.tv_habit_residence, Util.trimString(item.getResidentType()));
        helper.setText(R.id.tv_household_type, Util.trimString(item.getHujiType()));

        helper.setText(R.id.tv_id_card_number, Util.trimString(item.getIdNo()));
        helper.setText(R.id.tv_phone_number, Util.trimString(item.getPhone()));
        helper.setText(R.id.tv_address, Util.trimString(item.getAreaFulladdress()));


        View selectView = helper.getView(R.id.tv_select);
        Util.setViewListener(selectView, new Runnable() {
            @Override
            public void run() {
                if (listener!=null){
                    listener.patientDidSelect(item);
                }
            }
        });
    }


}
