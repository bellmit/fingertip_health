package com.jqsoft.fingertip_health.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;


/**
 * 基本功能：右侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class MainSectionedAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private String[][] rightStr;
    private String[][] rightLevel;
    private String[][] rightimg;

    public MainSectionedAdapter(Context context, String[] leftStr, String[][] rightStr, String[][] rightLevel, String[][] rightimg) {
        this.mContext = context;
        this.leftStr = leftStr;
        this.rightStr = rightStr;
        this.rightLevel = rightLevel;
        this.rightimg = rightimg;
    }

    @Override
    public Object getItem(int section, int position) {
        return rightStr[section][position];
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.length;
    }

    @Override
    public int getCountForSection(int section) {
        return rightStr[section].length;
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        TextView tv_name;
        ImageView iv;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) inflator.inflate(R.layout.right_list_item, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        tv_name=((TextView) layout.findViewById(R.id.textItem));
        tv_name.setText(rightStr[section][position]);
       /* if(rightStr[section][position].equals("无老人")){
            tv_name.setTextColor(Color.rgb(230,230,230));
        }else{
            tv_name.setTextColor(Color.rgb(0,0,0));
        }*/



        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {

            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr[section]);
        return layout;
    }

    static class ViewHolder {

        public ImageView imageItem;




    }

}
