package com.jqsoft.fingertip_health.util;

import android.text.TextUtils;

import java.text.DecimalFormat;

import static java.util.regex.Pattern.matches;

//import com.amap.api.navi.model.AMapNaviPath;
//import com.amap.api.navi.model.AMapNaviStep;

public class Utils {
    private static DecimalFormat fnum = new DecimalFormat("##0.0");
    public static final int AVOID_CONGESTION = 4;  // 躲避拥堵
    public static final int AVOID_COST = 5;  // 避免收费
    public static final int AVOID_HIGHSPEED = 6; //不走高速
    public static final int PRIORITY_HIGHSPEED = 7; //高速优先

    public static final int START_ACTIVITY_REQUEST_CODE = 1;
    public static final int ACTIVITY_RESULT_CODE = 2;

    public static final String INTENT_NAME_AVOID_CONGESTION = "AVOID_CONGESTION";
    public static final String INTENT_NAME_AVOID_COST = "AVOID_COST";
    public static final String INTENT_NAME_AVOID_HIGHSPEED = "AVOID_HIGHSPEED";
    public static final String INTENT_NAME_PRIORITY_HIGHSPEED = "PRIORITY_HIGHSPEED";


    /**
     * 判断手机格式是否正确
     * @param mobiles
     * @return
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}" ;
        if (TextUtils.isEmpty(mobiles))
            return false ;
        else
            return mobiles.matches( telRegex ) ;
    }

    /*** 验证电话号码
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsTelephone(String str) {
        String regex = "^0(10|2[0-5789]-|\\d{3})-?\\d{7,8}$";
        //  Logger.e("TAG", "phoneNum4:" + matches(regex, str));
        return matches(regex, str);
    }


    public static String getFriendlyTime(int s) {
        String timeDes = "";
        int h = s / 3600;
        if (h > 0) {
            timeDes += h + "小时";
        }
        int min = (int) (s % 3600) / 60;
        if (min > 0) {
            timeDes += min + "分";
        }
        return timeDes;
    }

    public static String getFriendlyDistance(int m) {
        if (m < 1000) {
            return m + "米";
        }
        float dis = m / 1000f;
        String disDes = fnum.format(dis) + "公里";
        return disDes;
    }

//    public static Spanned getRouteOverView(AMapNaviPath path) {
//        String routeOverView = "";
//        if (path == null) {
//            Html.fromHtml(routeOverView);
//        }
//
//        int cost = path.getTollCost();
//        if (cost > 0) {
//            routeOverView += "过路费约<font color=\"red\" >" + cost + "</font>元";
//        }
//        int trafficLightNumber = getTrafficNumber(path);
//        if (trafficLightNumber > 0) {
//            routeOverView += "红绿灯" + trafficLightNumber + "个";
//        }
//        return Html.fromHtml(routeOverView);
//    }
//
//    public static int getTrafficNumber(AMapNaviPath path) {
//        int trafficLightNumber = 0;
//        if (path == null) {
//            return trafficLightNumber;
//        }
//        List<AMapNaviStep> steps = path.getSteps();
//        for (AMapNaviStep step : steps) {
//            trafficLightNumber += step.getTrafficLightNumber();
//        }
//        return trafficLightNumber;
//    }
}