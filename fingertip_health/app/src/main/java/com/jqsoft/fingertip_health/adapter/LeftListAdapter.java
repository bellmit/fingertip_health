package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;


/**
 * 基本功能：左侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class LeftListAdapter extends BaseAdapter {
    private String[] leftStr;
    boolean[] flagArray;
    private Context context;

    public LeftListAdapter(Context context, String[] leftStr, boolean[] flagArray) {
        this.leftStr = leftStr;
        this.context = context;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return leftStr.length;
    }

    @Override
    public Object getItem(int arg0) {
        return leftStr[arg0];
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder = null;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(context).inflate(R.layout.left_list_item, null);
            holder.left_list_item = (TextView) arg1.findViewById(R.id.left_list_item);
            holder.vw_shuxian = (View) arg1.findViewById(R.id.vw_shuxian);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }
        holder.updataView(arg0);
        return arg1;
    }

    private class Holder {
        private TextView left_list_item;
        private View vw_shuxian;

        public void updataView(final int position) {
            left_list_item.setText(leftStr[position]);
            if (flagArray[position]) {
                left_list_item.setBackgroundColor(Color.rgb(255, 255, 255));
                vw_shuxian.setVisibility(View.VISIBLE);
                TextPaint paint = left_list_item.getPaint();
                paint.setFakeBoldText(true);
                left_list_item.setTextColor(Color.rgb(39,39,39));

            } else {
               left_list_item.setBackgroundColor(Color.TRANSPARENT);
                vw_shuxian.setVisibility(View.INVISIBLE);
                TextPaint paint = left_list_item.getPaint();
                paint.setFakeBoldText(false);
                left_list_item.setTextColor(Color.rgb(133,133,133));

            }
        }

    }
}
