package com.jqsoft.fingertip_health.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

/**
 * Created by Administrator on 2018-01-22.
 */

public class InfoWindowForSelectPointAdapter implements AMap.InfoWindowAdapter {
    private Context context;
    private View infoWindow = null;

    public InfoWindowForSelectPointAdapter(Context context) {
        this.context=context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (infoWindow==null){
            infoWindow= LayoutInflater.from(context).inflate(R.layout.layout_info_window_for_select_point, null, false);
        }
        render(marker, infoWindow);
        return infoWindow;
//        return null;
    }

    public void render(final Marker marker, View view){

        TextView tvLongitude = (TextView)view.findViewById(R.id.tv_longitude);
        TextView tvLatitude = (TextView)view.findViewById(R.id.tv_latitude);
        TextView tvAddress = (TextView)view.findViewById(R.id.tv_address);
        final PersonLocationBean plb = (PersonLocationBean)marker.getObject();
        String longitudeString = "经度:"+plb.getLng();
        String processedLongitudeString = Util.getWidthLessMultipleLineStringFromString(longitudeString);
        tvLongitude.setText(processedLongitudeString);

        String latitudeString = "纬度:"+plb.getLat();
        String processedLatitudeString = Util.getWidthLessMultipleLineStringFromString(latitudeString);
        tvLatitude.setText(processedLatitudeString);

        String addressString = plb.getAddress();
        String processedAddressString = Util.getWidthLessMultipleLineStringFromString(addressString);
        tvAddress.setText(processedAddressString);
//        Util.setViewWidth(view, 400);
//        Util.setTextViewMaxWidthFill(tvAddress);

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

    @Override
    public View getInfoContents(Marker marker) {
//        if (infoWindow==null){
//            infoWindow= LayoutInflater.from(context).inflate(R.layout.layout_info_window_for_select_point, null, false);
//        }
//        render(marker, infoWindow);
//        return infoWindow;
        return null;
    }
}

