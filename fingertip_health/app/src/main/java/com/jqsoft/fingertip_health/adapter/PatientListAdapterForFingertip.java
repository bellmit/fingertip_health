package com.jqsoft.fingertip_health.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.feature.PatientSelectListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


public class PatientListAdapterForFingertip extends BaseQuickAdapterEx<OutpatientBeanForFingertip, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE = 1;
    public static final int TYPE_MULTIPLE_LINE = 2;

    private int type = TYPE_MULTIPLE_LINE;
    private PatientSelectListener listener;

    public PatientListAdapterForFingertip(PatientSelectListener listener, List<OutpatientBeanForFingertip> data) {
        super(R.layout.item_patient_layout, data);
        this.type = type;
        this.listener = listener;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final OutpatientBeanForFingertip item) {
        helper.setText(R.id.tv_person_name, Util.trimString(item.getMemberName()));
        helper.setText(R.id.tv_person_gender, Util.trimString(item.getMemberSex()));
        helper.setText(R.id.tv_person_age, Util.trimString(item.getMemberAge()));
        helper.setText(R.id.tv_person_birthdate, Util.trimString("("+item.getBirthday()+")"));
        helper.setText(R.id.tv_person_status, Util.trimString("正常"));
        String sex = item.getMemberSex();
        if (sex.equals("1")) {
            helper.setText(R.id.tv_person_gender, "男");
        } else if (sex.equals("2")) {
            helper.setText(R.id.tv_person_gender, "女");
        }

        helper.setText(R.id.tv_card1, Util.trimString(item.getFamilyId()));
        helper.setText(R.id.tv_card2, Util.trimString(item.getIdCard()));
        helper.setText(R.id.tv_card3, Util.trimString(item.getMemberId()));

        String itemRelation = item.getRelation();
        if(itemRelation.equals("0")){
            helper.setText(R.id.tv_person_level, Util.trimString("本人或户主"));
        }else if(itemRelation.equals("1")){
            helper.setText(R.id.tv_person_level, Util.trimString("配偶"));
        }else if(itemRelation.equals("2")){
            helper.setText(R.id.tv_person_level, Util.trimString("子"));
        }else if(itemRelation.equals("3")){
            helper.setText(R.id.tv_person_level, Util.trimString("女"));
        }else if(itemRelation.equals("4")){
            helper.setText(R.id.tv_person_level, Util.trimString("孙子、孙女"));
        }else if(itemRelation.equals("5")){
            helper.setText(R.id.tv_person_level, Util.trimString("父母"));
        }else if(itemRelation.equals("6")){
            helper.setText(R.id.tv_person_level, Util.trimString("祖父母"));
        }else if(itemRelation.equals("7")){
            helper.setText(R.id.tv_person_level, Util.trimString("兄、弟、姐、妹"));
        }else if(itemRelation.equals("8")){
            helper.setText(R.id.tv_person_level, Util.trimString("媳妇"));
        }else if(itemRelation.equals("9")){
            helper.setText(R.id.tv_person_level, Util.trimString("其他"));
        }

        helper.setText(R.id.tv_person_attribute, Util.trimString(item.getMemberPro()));
        helper.setText(R.id.tv_individual_accumulated_compensate, Util.trimString("￥" + item.getOutpCompensateCost()));

        View selectView = helper.getView(R.id.tv_select);
        Util.setViewListener(selectView, new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.patientDidSelect(item);
                }
            }
        });
    }


}
