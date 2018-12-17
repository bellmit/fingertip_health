package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DetailHelpResult;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class SocailDetailHisAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<DetailHelpResult> HelpResult;


    public SocailDetailHisAdapter(Context context, ArrayList<DetailHelpResult> HelpResult) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.contentview_socialhis, null);
            viewHolder = new ViewHolder();

            viewHolder.socailTpye = (TextView) convertView.findViewById(R.id.socialtype);
            viewHolder.socailMoney = (TextView) convertView.findViewById(R.id.ffje);
            viewHolder.socailtime = (TextView) convertView.findViewById(R.id.ffdate);
            viewHolder.socailssu = (TextView) convertView.findViewById(R.id.ffjg);
            viewHolder.sjlx = (TextView)convertView.findViewById(R.id.sjlx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.socailTpye.setText(Util.testString(HelpResult.get(position).getItemName()));

        viewHolder.socailtime.setText(Util.testString(HelpResult.get(position).getMoneyDate()));
        viewHolder.socailssu.setText(Util.testString(HelpResult.get(position).getIssuingAgency()));
        viewHolder.sjlx.setText(Util.testString(HelpResult.get(position).getDataType()));

        if(Util.testString(HelpResult.get(position).getMoneyGrant()).equals("无")){
            viewHolder.socailMoney.setText("无");
        }else {
            viewHolder.socailMoney.setText(Util.testString(HelpResult.get(position).getMoneyGrant())+"元");
        }


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
