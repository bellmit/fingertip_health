package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;

import java.util.List;

public class ExecuedProjectAdapter extends BaseAdapter  {

	private Context context;

	private LayoutInflater inflater;
	private List<PendExecuBeanList> data;
	private int myposition;



	public ExecuedProjectAdapter(Context context, List<PendExecuBeanList> data) {
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
			convertView = inflater.inflate(R.layout.item_execuedproject, null);
			viewHolder = new ViewHolder();

			viewHolder.tv_pend_name = (TextView) convertView.findViewById(R.id.tv_pend_name);
			viewHolder.tv_server_item = (TextView) convertView.findViewById(R.id.tv_server_item);
			viewHolder.tv_execu_unit = (TextView) convertView.findViewById(R.id.tv_execu_unit);
			viewHolder.tv_execued = (TextView) convertView.findViewById(R.id.tv_execued);
			viewHolder.tv_pend_type = (TextView) convertView.findViewById(R.id.tv_pend_type);
	//		viewHolder.btn_execu = (Button) convertView.findViewById(R.id.btn_execu);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/*
		String titleStr = list.get(position).get("title").toString();
		
	
		viewHolder.title.setText(titleStr);*/


		viewHolder.tv_pend_name.setText(data.get(position).getFwmc());
		viewHolder.tv_server_item.setText("服务项目:"+data.get(position).getServiceItemsName());


			viewHolder.tv_execu_unit.setText("执行情况:已执行");

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


		if(!TextUtils.isEmpty(data.get(position).getExecuteDT())){
			String  ss =data.get(position).getExecuteDT().substring(0,10);
			viewHolder.tv_execued.setText("执行日期:"+data.get(position).getExecuteDT().substring(0,10));
		}

	/*	if(data.get(position).getEvaluationState()==null){

			viewHolder.btn_execu.setText("待评价");
		}else{
			viewHolder.btn_execu.setText("已评价");
		}*/

/*
		viewHolder.btn_execu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SignClientServiceAssessResultBean data1 = new SignClientServiceAssessResultBean();


				Intent intent = new Intent(context, SignServiceEvalution.class);
				intent.putExtra("SignClientServiceAssessResultBean",  data.get(position));
				context.startActivity(intent);

			}
		});*/


		return convertView;
	}





	static class ViewHolder {
		public TextView tv_pend_name;
		public TextView tv_server_item;
		public TextView tv_execu_unit;
		public TextView tv_execued;
		public TextView tv_pend_execu;
		public TextView tv_pend_type;
		//public Button btn_execu;


	}



}
