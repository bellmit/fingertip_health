package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.propMap;
import com.jqsoft.fingertip_health.di.ui.activity.ReserrverServerActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HighBloodListItemAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater inflater;
    private List<propMap> data;


    public HighBloodListItemAdapter(Context context, List<propMap> data) {
        super();
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HighBloodListItemAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_highboold_item, null);
            viewHolder = new HighBloodListItemAdapter.ViewHolder();
//            viewHolder.line = (LinearLayout) convertView.findViewById(R.id.line);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.im_content);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HighBloodListItemAdapter.ViewHolder) convertView.getTag();
        }

        switch (data.get(position).getSname()){
            case "高":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_g);
                break;
            case "糖":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_t);
                break;
            case "精":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_j);
                break;
            case "老":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_lao);
                break;
            case "孕":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_yun);
                break;
            case "童":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_er);
                break;
            case "贫":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_p);
                break;
            case "五保":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_wb);
                break;
            case "低保":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_db);
                break;
            case "优抚":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_yf);
                break;
            case "残":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_cjr);
                break;
            case "否计生特扶":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_jstf);
                break;
            case "计生家庭":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_jsjt);
                break;
            case "肺":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_jsjt);
                break;
            case "瘤":
                viewHolder.imageView.setImageResource(R.mipmap.zgllx_jsjt);
                break;

        }








//
//    GradientDrawable mGroupDrawable= (GradientDrawable)viewHolder.tv_content .getBackground();
//
//     /*设置边框颜色和宽度*/
//


        return convertView;
    }

    static class ViewHolder {


        public  ImageView imageView;
    }

//private Context context;





}
