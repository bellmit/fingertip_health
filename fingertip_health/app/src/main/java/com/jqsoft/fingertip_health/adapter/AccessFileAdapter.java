package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;

import java.util.List;

public class AccessFileAdapter extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;
	private List<String> data;


	public AccessFileAdapter(Context context, List<String> data) {
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
		 ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.item_access_file, null);
		//	convertView = inflater.inflate(R.layout.item_server_situation5, null);
			viewHolder = new ViewHolder();

			viewHolder.tv_access_unit = (TextView) convertView.findViewById(R.id.tv_access_unit);
            viewHolder.tv_access_name = (TextView) convertView.findViewById(R.id.tv_access_name);
            viewHolder.tv_access_date = (TextView) convertView.findViewById(R.id.tv_access_date);


			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String sData =data.get(position);
      //  if(data.get(position).e)
            if(TextUtils.isEmpty(sData) || sData.equals("null") || sData==null ){

            }else {
                String[] temp = null;
                temp = sData.split(",");

				if(temp.length==0){

				}else if(temp.length==1){
					viewHolder.tv_access_name.setText(temp[0]);
					viewHolder.tv_access_date.setText("确诊日期:无");
					viewHolder.tv_access_unit.setText("确诊医院:无");
				}else if(temp.length==2){
					viewHolder.tv_access_name.setText(temp[0]);

					if(TextUtils.isEmpty(temp[1]) || temp[1].equals("null") || temp[1]==null){
						viewHolder.tv_access_date.setText("确诊日期:无");
					}else{
						viewHolder.tv_access_date.setText("确诊日期:"+temp[1]);
					}


					if(temp.length<3){
						viewHolder.tv_access_unit.setText("确诊医院:无");

					}else{
						if(TextUtils.isEmpty(temp[2]) || temp[2].equals("null") || temp[2]==null){
							viewHolder.tv_access_unit.setText("确诊医院:无");
						}else{
							viewHolder.tv_access_unit.setText("确诊医院:"+temp[2]);
						}

					}
				}else if(temp.length==3){
					viewHolder.tv_access_name.setText(temp[0]);

					if(TextUtils.isEmpty(temp[1]) || temp[1].equals("null") || temp[1]==null){
						viewHolder.tv_access_date.setText("确诊日期:无");
					}else{
						viewHolder.tv_access_date.setText("确诊日期:"+temp[1]);
					}


					if(temp.length<3){
						viewHolder.tv_access_unit.setText("确诊医院:无");

					}else{
						if(TextUtils.isEmpty(temp[2]) || temp[2].equals("null") || temp[2]==null){
							viewHolder.tv_access_unit.setText("确诊医院:无");
						}else{
							viewHolder.tv_access_unit.setText("确诊医院:"+temp[2]);
						}

					}
				}

            }


		return convertView;
	}

	static class ViewHolder {
		public  TextView tv_access_unit;
		public TextView tv_access_name;
		public TextView tv_access_date;

	}
}
