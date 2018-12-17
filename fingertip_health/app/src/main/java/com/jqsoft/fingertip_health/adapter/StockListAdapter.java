package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.SelectOutPatientChargesBean;
import com.wangshijia.www.panellistviewlibrary.CommonAdapter;
import com.wangshijia.www.panellistviewlibrary.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andjdk on 2016/10/13.
 */
public class StockListAdapter extends CommonAdapter<SelectOutPatientChargesBean> {

    public StockListAdapter(Context mContext, List<SelectOutPatientChargesBean> mDatas, int layoutId) {
        super(mContext, mDatas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, SelectOutPatientChargesBean stockDataInfo, int position, ArrayList<View> movableViewList) {
        holder.setText(R.id.text1, stockDataInfo.getName());
        if (stockDataInfo.getPatientType().equals("自费医疗")) {
            holder.setText(R.id.tv_marketimg, "自费");
            holder.getView(R.id.tv_marketimg).setBackgroundResource(R.color.red);
        } else if (stockDataInfo.getPatientType().equals("新型农村合作医疗")) {
            holder.setText(R.id.tv_marketimg, "农合");
        }

        holder.setText(R.id.text13, stockDataInfo.getAge());
        try {
            holder.setText(R.id.text2, stockDataInfo.getVisitTime().substring(0, 10));
            holder.setText(R.id.text02, stockDataInfo.getVisitTime().substring(10, 19));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.setText(R.id.text3, stockDataInfo.getFeeTotal());
        holder.setText(R.id.text4, stockDataInfo.getFeeCms());
        holder.setText(R.id.text5, stockDataInfo.getFeeYs());
        holder.setText(R.id.text6, stockDataInfo.getFeeRounding());
        if (stockDataInfo.getInvalidSign().equals("0")) {
            holder.setText(R.id.text7, "正常");
        } else if (stockDataInfo.getInvalidSign().equals("1")) {
            holder.setText(R.id.text7, "已退费");
        }

        holder.setText(R.id.text8, stockDataInfo.getDiagnosisName());
        holder.setText(R.id.text9, stockDataInfo.getCardNumber());
        holder.setText(R.id.text10, stockDataInfo.getOccupationName());
        holder.setText(R.id.text11, stockDataInfo.getOutpatientDoctorName());

    }
}
