package com.jqsoft.fingertip_health.helper;

import android.content.Context;

/**
 * Created by Administrator on 2017-11-02.
 */

public class FullyGridLayoutManagerSmoothScroll extends FullyGridLayoutManager {
    public FullyGridLayoutManagerSmoothScroll(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FullyGridLayoutManagerSmoothScroll(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
