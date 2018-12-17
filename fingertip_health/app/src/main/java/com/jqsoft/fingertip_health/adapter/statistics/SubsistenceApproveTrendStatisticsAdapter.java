package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApproveTrendNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 低保审批情况趋势分析适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceApproveTrendStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceApproveTrendNaturalBean, BaseViewHolder> {
//    private List<String> titleList;
    public SubsistenceApproveTrendStatisticsAdapter(List<SubsistenceApproveTrendNaturalBean> data) {
        super(R.layout.item_subsistence_approve_trend_statistics_layout, data);
//        this.titleList = titleList;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceApproveTrendNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_month_name, item.getMonthName());
        helper.setText(R.id.tv_through_ratio, item.getThroughRatio());

//        List<SubsistenceApproveTrendNaturalBean> dataList = getData();
//        int dataListLength = ListUtils.getSize(dataList);
//        for (int i = 0; i < 3; ++i) {
//            if (dataListLength>i){
//                SubsistenceApproveTrendNaturalBean scb = dataList.get(i);
//                List<Integer> integerList = scb.getList();
//                if (ListUtils.getSize(integerList)>index){
//                    Integer integer = integerList.get(index);
//                    String integerString = String.valueOf(integer);
//                    if (i==0){
//                        helper.setText(R.id.tv_new, integerString);
//                    } else if (i==1){
//                        helper.setText(R.id.tv_review, integerString);
//                    } else if (i==2){
//                        helper.setText(R.id.tv_delete, integerString);
//                    } else {
//                        setEmptyString(helper);
//                    }
//                } else {
//                    setEmptyString(helper);
//                }
//            } else {
//                setEmptyString(helper);
//            }
//        }
    }

    private void setEmptyString(BaseViewHolder helper){
        helper.setText(R.id.tv_new, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_review, Constants.EMPTY_STRING);
        helper.setText(R.id.tv_delete, Constants.EMPTY_STRING);
    }

//    public List<String> getTitleList() {
//        return titleList;
//    }
//
//    public void setTitleList(List<String> titleList) {
//        this.titleList = titleList;
//    }
}
