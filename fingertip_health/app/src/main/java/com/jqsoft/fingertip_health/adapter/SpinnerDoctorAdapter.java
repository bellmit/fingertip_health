package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;

import java.util.ArrayList;

/**
 * Created by Jerry on 2017/8/28.
 */

public class SpinnerDoctorAdapter extends BaseAdapter {
    private ArrayList<DoctorTeamInfo> doctorList = new ArrayList<>();
    private Context context;


    public SpinnerDoctorAdapter(ArrayList<DoctorTeamInfo> doctorList, Context context) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int i) {
        return doctorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_doctorteam, null);
            holder = new ViewHolder();
            holder.tv_cname = (TextView) view.findViewById(R.id.doctorname);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_cname.setText(doctorList.get(position).getDoctorName()+"("+doctorList.get(position).getDoctorPhone()+")");
        return view;
    }

    static class ViewHolder {
        private TextView tv_cname;
    }
}
