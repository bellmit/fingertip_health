//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jqsoft.fingertip_health.helper.chart;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.Iterator;
import java.util.List;

public class BarDataEx extends BarData {
//public class BarDataEx extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth = 0.85F;

    public BarDataEx() {
    }

    public BarDataEx(IBarDataSet... dataSets) {
        super(dataSets);
    }

    public BarDataEx(List<IBarDataSet> dataSets) {
        super(dataSets);
    }

    public void setBarWidth(float mBarWidth) {
        this.mBarWidth = mBarWidth;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public void groupBars(float fromX, float groupSpace, float barSpace) {
        int setCount = this.mDataSets.size();
//        if(setCount <= 1) {
//            throw new RuntimeException("BarDataEx needs to hold at least 2 BarDataSets to allow grouping.");
//        } else {
            IBarDataSet max = (IBarDataSet)this.getMaxEntryCountSet();
            int maxEntryCount = max.getEntryCount();
            float groupSpaceWidthHalf = groupSpace / 2.0F;
            float barSpaceHalf = barSpace / 2.0F;
            float barWidthHalf = this.mBarWidth / 2.0F;
            float interval = this.getGroupWidth(groupSpace, barSpace);

            for(int i = 0; i < maxEntryCount; ++i) {
                float start = fromX;
                fromX += groupSpaceWidthHalf;

                for(Iterator var13 = this.mDataSets.iterator(); var13.hasNext(); fromX += barSpaceHalf) {
                    IBarDataSet set = (IBarDataSet)var13.next();
                    fromX += barSpaceHalf;
                    fromX += barWidthHalf;
                    if(i < set.getEntryCount()) {
                        BarEntry entry = (BarEntry)set.getEntryForIndex(i);
                        if(entry != null) {
                            entry.setX(fromX);
                        }
                    }

                    fromX += barWidthHalf;
                }

                fromX += groupSpaceWidthHalf;
                float innerInterval = fromX - start;
                float diff = interval - innerInterval;
                if(diff > 0.0F || diff < 0.0F) {
                    fromX += diff;
                }
            }

            this.notifyDataChanged();
//        }
    }

    public float getGroupWidth(float groupSpace, float barSpace) {
        return (float)this.mDataSets.size() * (this.mBarWidth + barSpace) + groupSpace;
    }
}
