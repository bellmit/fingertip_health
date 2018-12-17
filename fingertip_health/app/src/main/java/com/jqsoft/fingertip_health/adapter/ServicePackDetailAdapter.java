package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;

import java.util.ArrayList;
import java.util.List;

public class ServicePackDetailAdapter extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;
	//private List<String> data;

	private List<ServicePackDetailBeanList> data = new ArrayList<>();


	public ServicePackDetailAdapter(Context context, List<ServicePackDetailBeanList> data) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.item_servicepack_detail, null);
			viewHolder = new ViewHolder();

			viewHolder.tv_pack_name = (TextView) convertView.findViewById(R.id.tv_pack_name);
			viewHolder.tv_numers = (TextView) convertView.findViewById(R.id.tv_numers);
			viewHolder.tv_instruction = (TextView) convertView.findViewById(R.id.tv_instruction);
			viewHolder.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/*
		String titleStr = list.get(position).get("title").toString();
		
	
		viewHolder.title.setText(titleStr);*/
		/*viewHolder.btn_execu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
						"com.jqsoft.fingertip_health.di.ui.activity.ExecuProjectActivity");
                Util.gotoActivity(viewHolder.btn_execu.getContext(), ExecuProjectActivity.class);
				*//*Intent intent = new Intent(context, ExecuProjectActivity.class);
				context.startActivity(intent);*//*

			}
		});*/
		viewHolder.tv_pack_name.setText(data.get(position).xmmc);
		viewHolder.tv_numers.setText(data.get(position).cs+"次");
		viewHolder.tv_instruction.setText(data.get(position).xmnr);
		viewHolder.tv_unit.setText(data.get(position).zxdw);


		return convertView;
	}

	static class ViewHolder {
		public TextView tv_pack_name;
		public TextView tv_numers;
		public TextView tv_instruction;
		public TextView tv_unit;

	}
}
