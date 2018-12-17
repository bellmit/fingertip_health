package com.jqsoft.fingertip_health.adapter;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.di.ui.activity.SocialDetailActivity;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.concurrent.TimeUnit;

import rx.Observer;

/**
 * Created by Administrator on 2018-01-22.
 */

public class InfoWindowAdapter implements AMap.InfoWindowAdapter {
    private Context context;
    private View infoWindow = null;

    public InfoWindowAdapter(Context context) {
        this.context=context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        Object o = marker.getObject();
        if (o instanceof PersonLocationBean) {
            PersonLocationBean plb = (PersonLocationBean) o;
            String type = plb.getType();
            if (Constants.MAP_LOCATION_TYPE_TOTAL.equals(type)) {
                return null;
            } else {
                if (infoWindow==null){
                    infoWindow= LayoutInflater.from(context).inflate(R.layout.layout_info_window, null, false);
                }
                render(marker, infoWindow);
                return infoWindow;
            }
        } else {
            return null;
        }
//        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
//        if (infoWindow==null){
//            infoWindow= LayoutInflater.from(context).inflate(R.layout.layout_info_window, null, false);
//        }
//        render(marker, infoWindow);
//        return infoWindow;
        return null;
    }


    public void render(final Marker marker, View view){
        TextView tvName = (TextView)view.findViewById(R.id.tv_name);
        ImageView ivClose = (ImageView)view.findViewById(R.id.iv_close);
        TextView tvAddress = (TextView)view.findViewById(R.id.tv_address);
        final TextView tvPhone = (TextView)view.findViewById(R.id.tv_phone);
        TextView tvFamilyType = (TextView)view.findViewById(R.id.tv_family_type);
        LinearLayout llDetail = (LinearLayout) view.findViewById(R.id.ll_detail);
        LinearLayout llNavi = (LinearLayout) view.findViewById(R.id.ll_navigate);
        final PersonLocationBean plb = (PersonLocationBean)marker.getObject();
        String name = plb.getName();
        String displayName = name;
//        String processedName = Util.getWidthLessMultipleLineStringFromString(displayName);
        tvName.setText(displayName);
//        tvName.setText(name);
        showOrHideView(displayName, tvName);

        RxView.clicks(ivClose)
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
                        marker.hideInfoWindow();
                    }
                });

        String address = plb.getAddress();
//        String processedAddress = Util.getWidthLessMultipleLineStringFromString(address);
        tvAddress.setText(address);
        showOrHideView(address, tvAddress);

        final String phone = plb.getPhone();
        Spanned spanned = Html.fromHtml(Util.getUnderlinedString(phone));
        tvPhone.setText(spanned);
        RxView.clicks(tvPhone)
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
                        if (StringUtils.isBlank(phone) || "无".equals(phone)) {
                            Util.showToast(context, "电话号码为空");
                        } else {
                            Util.dial(tvPhone.getContext(), phone);
                        }
                    }
                });
//        showOrHideView(phone, tvPhone);

//        tvFamilyType.setText(plb.getFamilyType());
        String readableFamilyType = plb.getFamilyTypeReadable();
//        String processedFamilyType = Util.getWidthLessMultipleLineStringFromString(readableFamilyType);
        tvFamilyType.setText(Constants.LEFT_PARENTHESIS+readableFamilyType+Constants.RIGHT_PARENTHESIS);
//        showOrHideView(processedFamilyType, tvFamilyType);

        RxView.clicks(llDetail)
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
                        gotoRescueDetail(plb);
//                        Util.showToast(context, "详情被点击");
                    }
                });

        RxView.clicks(llNavi)
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
                        startAMapNavi(marker);
                    }
                });
//        ibNavi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startAMapNavi(marker);
//            }
//        });
    }

    private void gotoRescueDetail(PersonLocationBean plb){
        if (plb==null){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("batchNo", plb.getBatchNo());
        bundle.putString("userSex", Constants.EMPTY_STRING);
        bundle.putString("cardNo", Constants.EMPTY_STRING);
        bundle.putString("userName", plb.getName());
        bundle.putString("filePath", Constants.EMPTY_STRING);
        Util.gotoActivityWithBundle(context, SocialDetailActivity.class, bundle);
    }

    private void showOrHideView(String s, View v){
        if (StringUtils.isBlank(s)){
            v.setVisibility(View.GONE);
        } else {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 点击一键导航按钮跳转到导航页面
     *
     * @param marker
     */
    private void startAMapNavi(Marker marker) {
        MapServiceActivity activity = null;
        if (context instanceof MapServiceActivity){
            activity=(MapServiceActivity) context;
            activity.startNavi(marker);
        }
    }

}

