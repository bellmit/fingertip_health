package com.jqsoft.fingertip_health.helper;

import android.content.Context;

import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

/**
 * Created by Administrator on 2018-01-31.
 */

public class GridRecyclerViewSeparator extends Y_DividerItemDecoration {
    public GridRecyclerViewSeparator(Context context, float lineWidthDp, int colorRGB) {
        super(context, lineWidthDp, colorRGB);
    }

    public GridRecyclerViewSeparator(Context context, int lineWidthDp, int colorRGB) {
        super(context, lineWidthDp, colorRGB);
    }

    @Override
    public boolean[] getItemSidesIsHaveOffsets(int i) {
        boolean[] isOffset = {false, false, true, true};
        return isOffset;
    }
}
