package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.DoctorBeanForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YLL on 2017-7-6.
 */

public class DoctorListAdapter extends BaseAdapter {
    public List<DoctorBeanForFingertip> userInfoList = new ArrayList<>();
    private Context context;

    public DoctorListAdapter(Context context, List<DoctorBeanForFingertip> userInfoList  ) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    @Override
    public int getCount() {
        return userInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return userInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DoctorListAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_dialoglayout, null);//
            holder = new DoctorListAdapter.ViewHolder();
            holder.username = (TextView) convertView.findViewById(R.id.username);
            convertView.setTag(holder);
        } else {
            holder = (DoctorListAdapter.ViewHolder) convertView.getTag();
        }

        holder.username.setText(userInfoList.get(position).getRealName());


        return convertView;
    }



    static class ViewHolder {
        public TextView username;

    }
}
