package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianFragment;

import java.util.List;

public class MyNewAdapterNew extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;
	private List<UrbanLowFujianBean> data;
	private UrbanFuJianFragment urbanFuJianBianjiNewFragment;
	private UrbanLowFamilyFragmentPresenter mPresenter;

	public MyNewAdapter2 myNewAdapter2;


	public MyNewAdapterNew(Context context, List<UrbanLowFujianBean> data, UrbanFuJianFragment urbanFuJianBianjiNewFragment, UrbanLowFamilyFragmentPresenter mPresenter ) {
		super();
		this.context = context;
		this.data = data;
		this.urbanFuJianBianjiNewFragment=urbanFuJianBianjiNewFragment;
		this.mPresenter=mPresenter;


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
			convertView = inflater.inflate(R.layout.item_urbanlow_fujian_jiuzhuxiang_new, null);
			viewHolder = new ViewHolder();


			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.ls_shenqingxiang = (ListView) convertView.findViewById(R.id.ls_shenqingxiang);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}


		viewHolder.tv_name.setText(data.get(position).getItemName());

	//	List<UrbanLowFujianBean.Jiuzhuxiang> list =data.get(position);
		List<UrbanLowFujianBean.Jiuzhuxiang> data1=data.get(position).getFiles();
		MyNewAdapter2New myNewAdapter2= new MyNewAdapter2New(context, data1,urbanFuJianBianjiNewFragment,mPresenter,data.get(position).getItemId());
		viewHolder.ls_shenqingxiang.setAdapter(myNewAdapter2);
		myNewAdapter2.notifyDataSetChanged();

		setListViewHeightBasedOnChildren(viewHolder.ls_shenqingxiang);



		return convertView;
	}

	static class ViewHolder {
		public TextView tv_name;
		public ListView ls_shenqingxiang;

	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if(listView == null) return;

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
