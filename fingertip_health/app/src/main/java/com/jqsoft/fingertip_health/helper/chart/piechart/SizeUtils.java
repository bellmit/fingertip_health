package com.jqsoft.fingertip_health.helper.chart.piechart;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2018-05-17.
 */

public class SizeUtils {
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

}
