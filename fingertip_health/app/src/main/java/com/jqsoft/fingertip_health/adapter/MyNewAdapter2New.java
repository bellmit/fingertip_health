package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianFragment;

import java.util.List;

public class MyNewAdapter2New extends BaseAdapter {

	private Context context;
	public String itemid;


	private LayoutInflater inflater;
	private List<UrbanLowFujianBean.Jiuzhuxiang> data;
	private UrbanFuJianFragment urbanFuJianBianjiNewFragment;
	private UrbanLowFamilyFragmentPresenter mPresenter;


	PaizhaoListener mPaizhaoListener;


	public interface PaizhaoListener {
		public void onPaizhaoClick(String s, String itemid);
	}

	public void setOnPaizhaoClickListener (PaizhaoListener paizhaoListener) {
		this.mPaizhaoListener = paizhaoListener;
	}


	public MyNewAdapter2New(Context context, List<UrbanLowFujianBean.Jiuzhuxiang> data, UrbanFuJianFragment urbanFuJianBianjiNewFragment, UrbanLowFamilyFragmentPresenter mPresenter, String itemid) {
		super();
		this.context = context;
		this.data = data;
		this.urbanFuJianBianjiNewFragment=urbanFuJianBianjiNewFragment;
		this.mPresenter=mPresenter;
		this.itemid=itemid;

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
			convertView = inflater.inflate(R.layout.item_urbanlow_fujian_shenqingxiang_new, null);
			viewHolder = new ViewHolder();


			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.ls_shenqingxiang = (ListView) convertView.findViewById(R.id.ls_shenqingxiang);
			viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
			viewHolder.iv_bitian = (ImageView) convertView.findViewById(R.id.iv_bitian);
				convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String fileState = data.get(position).getFileState();
		if( fileState==null || TextUtils.isEmpty(fileState) || fileState.equals("null")){
			viewHolder.iv_bitian.setVisibility(View.GONE);
		}else {
			if(data.get(position).getFileState().equals("0")){
				viewHolder.iv_bitian.setVisibility(View.VISIBLE);
			}else {
				viewHolder.iv_bitian.setVisibility(View.GONE);
			}
		}


		viewHolder.tv_name.setText(data.get(position).getFileCodeName());

		List<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> data1=data.get(position).getDetails();
		MyNewAdapter3New myNewAdapter3= new MyNewAdapter3New(context, data1,urbanFuJianBianjiNewFragment,mPresenter,data.get(position));
		viewHolder.ls_shenqingxiang.setAdapter(myNewAdapter3);
		myNewAdapter3.notifyDataSetChanged();

		setListViewHeightBasedOnChildren(viewHolder.ls_shenqingxiang);

		viewHolder.iv_photo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				urbanFuJianBianjiNewFragment.myPaizhao(data.get(position).getFileCode(),itemid);
			//	onAddPicClick(0,0);
			//	mPaizhaoListener.onPaizhaoClick(Util.trimString(data.get(position).getFileCode()),itemid);
			}
		});


		return convertView;
	}

	static class ViewHolder {
		public TextView tv_name;
		public ListView ls_shenqingxiang;
		public ImageView iv_photo;
		public ImageView iv_bitian;


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
