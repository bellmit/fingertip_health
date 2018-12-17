package com.jqsoft.fingertip_health.util;

import android.content.Context;
import android.graphics.Point;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-01-26.
 */

public class MapUtil {
    public static LatLng convertGcjCoordinateToBaiduCoordinate(LatLng latLng){
        LatLng result = null;
        if (latLng!=null){
            double lat = latLng.latitude;
            double lng = latLng.longitude;
            double[] baiduCoordinate = CoordinateUtil.gcj02_To_Bd09(lat, lng);
            double latBaidu = baiduCoordinate[0];
            double lngBaidu = baiduCoordinate[1];
            double canonicalizedLat = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(latBaidu)));
            double canonicalizedLng = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(lngBaidu)));
            result = new LatLng(canonicalizedLat, canonicalizedLng);
        }
        return result;
    }

    public static LatLng getGcjLatLngFromLatLngType(Context context, CoordinateConverter.CoordType type, LatLng originalLatLng){
        CoordinateConverter converter  = new CoordinateConverter(context);
        // CoordType.GPS 待转换坐标类型
        converter.from(type);
//        converter.from(CoordinateConverter.CoordType.GPS);
        // sourceLatLng待转换坐标点 LatLng类型
        converter.coord(originalLatLng);
        // 执行转换操作
        LatLng desLatLng = converter.convert();
        return desLatLng;
    }

    public static List<LatLng> getGcjLatLngListFromLatLngType(Context context, CoordinateConverter.CoordType type, List<LatLng> originalList){
        List<LatLng> result = new ArrayList<>();
        if (ListUtils.isEmpty(originalList)){
            return result;
        } else {
            CoordinateConverter converter  = new CoordinateConverter(context);
            // CoordType.GPS 待转换坐标类型
            converter.from(type);
//        converter.from(CoordinateConverter.CoordType.GPS);
            // sourceLatLng待转换坐标点 LatLng类型
            for (int i = 0; i < originalList.size(); ++i){
                LatLng originalLatLng = originalList.get(i);
                converter.coord(originalLatLng);
                // 执行转换操作
                LatLng desLatLng = converter.convert();
                result.add(desLatLng);
            }
            return result;
        }
    }

    public static void setPersonLocationBeanListToGcjLatLng(Context context, CoordinateConverter.CoordType fromType,
                                                            List<PersonLocationBean> originalList){
        if (ListUtils.isEmpty(originalList)){

        } else {
            CoordinateConverter converter  = new CoordinateConverter(context);
            // CoordType.GPS 待转换坐标类型
            converter.from(fromType);
            for (int i = 0; i < originalList.size(); ++i){
                PersonLocationBean bean = originalList.get(i);
                float longitude = Util.getFloatFromString(bean.getLng());
                float latitude = Util.getFloatFromString(bean.getLat());
                LatLng latLng = new LatLng(latitude, longitude);
                converter.coord(latLng);
                LatLng gcjLatLng = converter.convert();
                bean.setLng(String.valueOf(gcjLatLng.longitude));
                bean.setLat(String.valueOf(gcjLatLng.latitude));

            }
        }
    }

    public static LatLng getLatLngFromScreenPoint(AMap aMap, float x, float y){
        Point point = new Point((int)x, (int)y);
        LatLng latLng = null;
        try {
            latLng = aMap.getProjection().fromScreenLocation(point);
        } catch (Exception e) {
            e.printStackTrace();
            latLng = null;
        }
        return latLng;
    }

    public static Point getScreenPointFromLatLng(AMap aMap, double latitude, double longitude){
        Point point = null;
        LatLng latLng = new LatLng(latitude, longitude);
        point = getScreenPointFromLatLng(aMap, latLng);
        return point;
    }

    public static Point getScreenPointFromLatLng(AMap aMap, LatLng latLng) {
        if (aMap==null || latLng==null){
            return null;
        } else {
            Point point = null;
            try {
                point = aMap.getProjection().toScreenLocation(latLng);
            } catch (Exception e) {
                e.printStackTrace();
                point = null;
            }
            return point;
        }
    }

    public static float getDistanceBetweenTwoScreenPoint(AMap amap, float firstX, float firstY, float secondX, float secondY){
        LatLng latLngFirst = null, latLngSecond = null;
        float distance = 0f;
        try {
            latLngFirst = getLatLngFromScreenPoint(amap, firstX, firstY);
            latLngSecond = getLatLngFromScreenPoint(amap, secondX, secondY);
            distance = getDistanceBetweenTwoLatLng(latLngFirst, latLngSecond);
        } catch (Exception e) {
            e.printStackTrace();
            distance = 0f;
        }
        return distance;
    }

    public static float getDistanceBetweenTwoLatLng(LatLng first, LatLng second){
        float distance = 0f;
        try {
            distance = AMapUtils.calculateLineDistance(first, second);
        } catch (Exception e) {
            e.printStackTrace();
            distance = 0f;
        }
        return distance;
    }
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }
}
