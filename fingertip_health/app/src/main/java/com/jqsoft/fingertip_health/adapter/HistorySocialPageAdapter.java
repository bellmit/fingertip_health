package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.SocialHisDetail;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;


public class HistorySocialPageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<SocialHisDetail> HelpResult;


    public HistorySocialPageAdapter(Context context, ArrayList<SocialHisDetail> HelpResult) {
        super();
        this.context = context;
        this.HelpResult = HelpResult;

    }

    @Override
    public int getCount() {

        return HelpResult.size();
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
        SocailDetailHisAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.contentview_socialhis, null);
            viewHolder = new SocailDetailHisAdapter.ViewHolder();

            viewHolder.socailTpye = (TextView) convertView.findViewById(R.id.socialtype);
            viewHolder.socailMoney = (TextView) convertView.findViewById(R.id.ffje);
            viewHolder.socailtime = (TextView) convertView.findViewById(R.id.ffdate);
            viewHolder.socailssu = (TextView) convertView.findViewById(R.id.ffjg);
            viewHolder.sjlx = (TextView) convertView.findViewById(R.id.sjlx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SocailDetailHisAdapter.ViewHolder) convertView.getTag();
        }
        if (TextUtils.isEmpty(HelpResult.get(position).getItemName())) {
            viewHolder.socailTpye.setText("无");
        } else {
            viewHolder.socailTpye.setText(HelpResult.get(position).getItemName());
        }

        viewHolder.socailMoney.setText(HelpResult.get(position).getMoneyGrant() + "元");
        viewHolder.socailtime.setText(HelpResult.get(position).getMoneyDate());
        viewHolder.socailssu.setText(HelpResult.get(position).getIssuingAgency());
        viewHolder.sjlx.setText(Util.testString(HelpResult.get(position).getDataType()));
        return convertView;
    }

    static class ViewHolder {
        public TextView socailTpye;
        public TextView socailMoney;
        public TextView socailtime;
        public TextView socailssu;
        public TextView sjlx;
    }
}
