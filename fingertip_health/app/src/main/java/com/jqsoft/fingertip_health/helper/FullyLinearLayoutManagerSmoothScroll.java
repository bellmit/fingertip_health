package com.jqsoft.fingertip_health.helper;

import android.content.Context;

/**
 * Created by Administrator on 2017-12-28.
 */

public class FullyLinearLayoutManagerSmoothScroll extends FullyLinearLayoutManager {
    public FullyLinearLayoutManagerSmoothScroll(Context context) {
        super(context);
    }

    public FullyLinearLayoutManagerSmoothScroll(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
