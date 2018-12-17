package com.jqsoft.fingertip_health.helper.chart.piechart;

import android.text.TextUtils;

import com.jqsoft.fingertip_health.util.Util;

import java.text.DecimalFormat;

/**
 * Created by ${Karoline} on 2017/5/11.
 */

public class DecimalFUtil {
    public static String formatTo3(double num){
        return new DecimalFormat(",###.00元").format(num);
    }

    public static String formatTo3(String price){
        if(TextUtils.isEmpty(price)){
            return "";
        }
        try {
            return new DecimalFormat(",###.00元").format(Double.valueOf(price));
        }catch (Exception e){
            return price;
        }
    }

    public static String formatToNormal(String price){
        if(TextUtils.isEmpty(price)){
            return "";
        }
        try {
            return new DecimalFormat(",###.00").format(Double.valueOf(price));
        }catch (Exception e){
            return "0";
        }
    }

    public static String formatToPercent(String price){
        if(TextUtils.isEmpty(price)){
            return "";
        }
        try {
            float f = Util.getFloatFromPercentString(price);
            f *= 100;
            return new DecimalFormat(",##0.00").format(f)+"%";
        }catch (Exception e){
            return "0%";
        }
    }


}