package com.jqsoft.fingertip_health.helper;

import android.content.Context;

/**
 * Created by Administrator on 2018-03-19.
 */

public class NoScrollFullyLinearLayoutManager extends FullyLinearLayoutManager {
    private boolean isScrollEnabled = true;

    public NoScrollFullyLinearLayoutManager(Context context) {
        super(context);
    }

    public NoScrollFullyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
