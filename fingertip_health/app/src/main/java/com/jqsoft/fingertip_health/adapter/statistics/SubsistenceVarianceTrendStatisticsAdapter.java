package com.jqsoft.fingertip_health.adapter.statistics;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceTrendNaturalBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * 低保变化情况趋势统计适配器
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceVarianceTrendStatisticsAdapter extends BaseQuickAdapterEx<SubsistenceVarianceTrendNaturalBean, BaseViewHolder> {
//    private List<String> titleList;
    public SubsistenceVarianceTrendStatisticsAdapter(List<SubsistenceVarianceTrendNaturalBean> data) {
        super(R.layout.item_subsistence_variance_trend_statistics_layout, data);
//        this.titleList = titleList;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsistenceVarianceTrendNaturalBean item) {
        int index = helper.getAdapterPosition();
        helper.setText(R.id.tv_serial_number, Util.getStatisticsSerialNumberStringFromInt(index+1));
        helper.setText(R.id.tv_month_name, item.getMonthName());
        helper.setText(R.id.tv_new, item.getNewQuantity());
        helper.setText(R.id.tv_review, item.getReviewQuantity());
        helper.setText(R.id.tv_delete, item.getDeleteQuantity());

//        List<SubsistenceVarianceTrendNaturalBean> dataList = getData();
//        int dataListLength = ListUtils.getSize(dataList);
//        for (int i = 0; i < 3; ++i) {
//            if (dataListLength>i){
//                SubsistenceVarianceTrendNaturalBean scb = dataList.get(i);
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
