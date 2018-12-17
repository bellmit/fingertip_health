package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MedicalAssistantDirectOutcomeBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 医疗救助-直接医疗救助站支出情况适配器
 * Created by Administrator on 2018-01-04.
 */

public class MedicalAssistantDirectOutcomeStatisticsAdapter extends BaseQuickAdapterEx<MedicalAssistantDirectOutcomeBean, BaseViewHolder> {
    private String type;
    public MedicalAssistantDirectOutcomeStatisticsAdapter(String type, List<MedicalAssistantDirectOutcomeBean> data) {
        super(R.layout.item_medical_assistant_direct_outcome_5_column_statistics_layout, data);
//        super(Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS.equals(type) ?
//                R.layout.item_medical_assistant_direct_outcome_5_column_statistics_layout :
//                R.layout.item_medical_assistant_direct_outcome_3_column_statistics_layout, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, MedicalAssistantDirectOutcomeBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_title, item.getTitle());
        if (Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_LEVEL_ANALYSIS_STATISTICS.equals(type) ||
                Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS.equals(type)) {
            helper.setText(R.id.tv_first, item.getFirstValue());
        } else {
            helper.setText(R.id.tv_first, item.getFirstValue());
//            helper.setText(R.id.tv_first, Util.getIntStringFromString(item.getFirstValue()));
        }
        helper.setText(R.id.tv_second, item.getSecondValue());
        if (is5Column()){
            helper.setVisible(R.id.tv_third, true);
            helper.setVisible(R.id.tv_fourth, true);
            helper.setText(R.id.tv_third, item.getThirdValue());
            helper.setText(R.id.tv_fourth, item.getFourthValue());
        } else {
            helper.setVisible(R.id.tv_third, false);
            helper.setVisible(R.id.tv_fourth, false);
            helper.setText(R.id.tv_third, Constants.EMPTY_STRING);
            helper.setText(R.id.tv_fourth, Constants.EMPTY_STRING);
        }
    }

    private boolean is5Column(){
        if (Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS.equals(type)){
            return true;
        } else {
            return false;
        }
    }

    private int getLayoutIdFromType(String type){
        int result = R.layout.item_medical_assistant_direct_outcome_3_column_statistics_layout;
        if (Constants.MEDICAL_ASSISTANT_DIRECT_OUTCOME_INCREASE_RATIO_STATISTICS.equals(type)){
            result= R.layout.item_medical_assistant_direct_outcome_5_column_statistics_layout;
        } else {
            result = R.layout.item_medical_assistant_direct_outcome_3_column_statistics_layout;
        }
        return result;
    }

    private void setEmptyString(BaseViewHolder helper){
        helper.setText(R.id.tv_new, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_review, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_delete, Constants.EMPTY_STRING);
    }

}
