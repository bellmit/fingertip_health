package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DiscoverManageBean;
import com.jqsoft.fingertip_health.util.Util;


import java.util.List;

public class DetailFindDiscoverAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<DiscoverManageBean> HelpResult;


    public DetailFindDiscoverAdapter(Context context, List<DiscoverManageBean> HelpResult) {
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
            convertView = inflater.inflate(R.layout.contentview_find_manageinfo, null);
            viewHolder = new ViewHolder();

            viewHolder.tv_managepeople = (TextView) convertView.findViewById(R.id.tv_managepeople);
            viewHolder.tv_manage_mode = (TextView) convertView.findViewById(R.id.tv_manage_mode);
            viewHolder.tv_manage_remark = (TextView) convertView.findViewById(R.id.tv_manage_remark);
            viewHolder.tv_manage_time = (TextView) convertView.findViewById(R.id.tv_manage_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_managepeople.setText(HelpResult.get(position).getEditor());

        String act=HelpResult.get(position).getAct();
        if(act.equals("2")){
            viewHolder.tv_manage_mode.setText("处理中");
        }else  if(act.equals("3")){
            viewHolder.tv_manage_mode.setText("已处理");
        }


        viewHolder.tv_manage_remark.setText(Util.isMyEmpty( HelpResult.get(position).getRemarks()) );
        viewHolder.tv_manage_time.setText(Util.trimString(HelpResult.get(position).getEditDate()).substring(0,10));

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_managepeople;
        public TextView tv_manage_mode;
        public TextView tv_manage_remark;
        public TextView tv_manage_time;

    }
}
