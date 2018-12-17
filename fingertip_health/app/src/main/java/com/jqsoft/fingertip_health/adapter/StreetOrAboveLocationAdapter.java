package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonStreetOrAboveLocationBean;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;


public class StreetOrAboveLocationAdapter extends BaseQuickAdapterEx<PersonStreetOrAboveLocationBean, BaseViewHolder> {
    private Context context;
    public StreetOrAboveLocationAdapter(Context context, List<PersonStreetOrAboveLocationBean> data) {
        super(R.layout.item_street_or_above_location, data);
        this.context = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final PersonStreetOrAboveLocationBean item) {
        helper.setText(R.id.tv_area_name, item.getAreaName());
        String areaCode = item.getAreaCode();
        helper.setText(R.id.tv_area_code, areaCode);
        if (StringUtils.isBlank(areaCode)){
            helper.setVisible(R.id.tv_area_code, false);
        } else {
            helper.setVisible(R.id.tv_area_code, true);
        }
        String number = Util.getNumberStringFromString(item.getTotal());
        helper.setText(R.id.tv_number, "数量:"+number);

        ImageView naviImageView = helper.getView(R.id.iv_navi);
        RxView.clicks(naviImageView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        startAMapNavi(item);
                    }
                });
    }

    private void startAMapNavi(PersonStreetOrAboveLocationBean plb) {
        MapServiceActivity activity = null;
        if (context instanceof MapServiceActivity){
            activity=(MapServiceActivity) context;
            activity.startNavi(plb);
        }
    }

}
