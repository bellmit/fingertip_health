package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.di.ui.activity.ReserrverServerActivity;

import java.io.Serializable;
import java.util.List;

public class PendExecuAdapter extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;
	private List<PendExecuBeanList> data;
	private PeopleBaseInfoBean mpeopleBasebean;

	public PendExecuAdapter(Context context, List<PendExecuBeanList> data,PeopleBaseInfoBean mpeopleBasebean) {
		super();
		this.context = context;
		this.data = data;
		this.mpeopleBasebean=mpeopleBasebean;
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
		ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.item_pendexecu, null);
			viewHolder = new ViewHolder();
			viewHolder.btn_execu = (Button) convertView.findViewById(R.id.btn_execu);
			viewHolder.tv_pend_name = (TextView) convertView.findViewById(R.id.tv_pend_name);
			viewHolder.tv_server_item = (TextView) convertView.findViewById(R.id.tv_server_item);
			viewHolder.tv_execu_unit = (TextView) convertView.findViewById(R.id.tv_execu_unit);
			viewHolder.tv_execued = (TextView) convertView.findViewById(R.id.tv_execued);
			viewHolder.tv_pend_execu = (TextView) convertView.findViewById(R.id.tv_pend_execu);
			viewHolder.tv_pend_type = (TextView) convertView.findViewById(R.id.tv_pend_type);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/*
		String titleStr = list.get(position).get("title").toString();
		
	
		viewHolder.title.setText(titleStr);*/
		viewHolder.btn_execu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Bundle bundle = new Bundle();
				bundle.putString("ServiceContentItemsKey", data.get(position).getServiceContentItemsKey());

				ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
						"com.jqsoft.fingertip_health.di.ui.activity.ExecuProjectActivity",bundle);*/

				Intent intent = new Intent(context,ReserrverServerActivity.class);
				intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
				intent.putExtra("PendExecuBeanList", (Serializable)data.get(position));
				context.startActivity(intent);


			}
		});

		viewHolder.tv_pend_name.setText(data.get(position).getFwmc());
		viewHolder.tv_server_item.setText("服务项目:"+data.get(position).getServiceItemsName());

		String sort =data.get(position).getServiceClassSort();
		if(sort.equals("1")){
			viewHolder.tv_pend_type.setText("基础");
			viewHolder.tv_pend_type.setBackgroundColor(android.graphics.Color.parseColor("#F4D249"));
		}else if(sort.equals("2")){
			viewHolder.tv_pend_type.setText("初级");
			viewHolder.tv_pend_type.setBackgroundColor(android.graphics.Color.parseColor("#6ED1E0"));
		}else if(sort.equals("3")){
			viewHolder.tv_pend_type.setText("中级");
			viewHolder.tv_pend_type.setBackgroundColor(android.graphics.Color.parseColor("#7DD0FF"));
		}else {
			viewHolder.tv_pend_type.setText("高级");
			viewHolder.tv_pend_type.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
		}



		if(data.get(position).getExecOfficer().equals("3")){
			viewHolder.tv_execu_unit.setText("执行单位:乡镇");
		}else{
			viewHolder.tv_execu_unit.setText("执行单位:村级");
		}


		viewHolder.tv_execued.setText("已执行:"+data.get(position).getHadExecTimes());
		viewHolder.tv_pend_execu.setText("应执行:"+data.get(position).getShouldExecTimes());


		return convertView;
	}

	static class ViewHolder {
		public TextView tv_pend_name;
		public TextView tv_server_item;
		public TextView tv_execu_unit;
		public TextView tv_execued;
		public TextView tv_pend_execu;
		public Button btn_execu;
		public TextView tv_pend_type;
	}
}
