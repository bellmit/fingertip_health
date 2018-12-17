package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianBianjiNewFragment;
import com.jqsoft.fingertip_health.util.InputDialog;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.PictureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyNewAdapter3 extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;
	private List<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> data;
	private UrbanFuJianBianjiNewFragment urbanFuJianBianjiNewFragment;
	private UrbanLowFamilyFragmentPresenter mPresenter;

	private UrbanLowFujianBean.Jiuzhuxiang itemJiuzhuxiang;
	private String status;

	public MyNewAdapter3(Context context, List<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> data,UrbanFuJianBianjiNewFragment urbanFuJianBianjiNewFragment,UrbanLowFamilyFragmentPresenter mPresenter,UrbanLowFujianBean.Jiuzhuxiang itemJiuzhuxiang,String status) {
		super();
		this.context = context;
		this.data = data;
		this.urbanFuJianBianjiNewFragment=urbanFuJianBianjiNewFragment;
		this.mPresenter=mPresenter;
		this.itemJiuzhuxiang=itemJiuzhuxiang;
		this.status=status;

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
			convertView = inflater.inflate(R.layout.item_urbanlow_fujian_shenqingdetail_new, null);
			viewHolder = new ViewHolder();


			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.iv_tupian = (ImageView) convertView.findViewById(R.id.iv_tupian);
			viewHolder.btn_del = (LinearLayout) convertView.findViewById(R.id.btn_del);
			viewHolder.btn_bianji = (LinearLayout) convertView.findViewById(R.id.btn_bianji);
			viewHolder.btn_yulan = (LinearLayout) convertView.findViewById(R.id.btn_yulan);
			viewHolder.tv_applydate = (TextView) convertView.findViewById(R.id.tv_applydate);

				convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.btn_del.setVisibility(View.VISIBLE);
		viewHolder.btn_bianji.setVisibility(View.VISIBLE);
		if(status.equals("0") || status.equals("10")){
			String sarealeavel= IdentityManager.getAreaLevel(context);
			if(sarealeavel.equals("area_4") || sarealeavel.equals("area_5")){
				viewHolder.btn_del.setVisibility(View.VISIBLE);
				viewHolder.btn_bianji.setVisibility(View.VISIBLE);
			}else {
				viewHolder.btn_del.setVisibility(View.GONE);
				viewHolder.btn_bianji.setVisibility(View.GONE);
			}


		}else {
			viewHolder.btn_del.setVisibility(View.GONE);
			viewHolder.btn_bianji.setVisibility(View.GONE);
		}

		viewHolder.tv_name.setText(Util.trimString(data.get(position).getFileName()));

		String date=data.get(position).getCreateTime();
		if(date==null || ("").equals(date) || date.equals("null")){
			date="";
		}else {
			if(date.length()<10){

			}else {
				date=date.substring(0,10);
			}
		}

		viewHolder.tv_applydate.setText(Util.trimString(date));

		//String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";
		String FIND_FILE_URL_BASE = Version.FILE_URL_BASE;
		// String FIND_FILE_URL_BASE ="http://192.168.44.51:8080/sri";
		String imageUrl =FIND_FILE_URL_BASE+data.get(position).getFilePath();
		GlideUtils.loadImageNew(imageUrl, (ImageView) viewHolder.iv_tupian);

		viewHolder.btn_yulan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<LocalMedia> selectMedia = new ArrayList<>();
				selectMedia.clear();

				LocalMedia localMedia = new LocalMedia();
				localMedia.setUrl(Version.FILE_URL_BASE+data.get(position).getFilePath());
				localMedia.setPath("test");
				localMedia.setType(1);
				localMedia.setFileId(data.get(position).getFileId());
				selectMedia.add(localMedia);

				PictureConfig.getInstance().externalPicturePreview(	urbanFuJianBianjiNewFragment.getActivity(), 0, selectMedia);
			}
		});

		final String mybatchNoNew = urbanFuJianBianjiNewFragment.getbatchNo();

		viewHolder.btn_del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				MaterialDialog dialog = new MaterialDialog.Builder(context)
						.title(R.string.hint_suggestion)
						.content("确定要删除吗?")
						.negativeText(R.string.cancel)
						.positiveText(R.string.confirm)
						.onPositive(new MaterialDialog.SingleButtonCallback() {
							@Override
							public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
								dialog.dismiss();

								String s1 =data.get(position).getFileId();
								// Toast.makeText(context,s1+"啦",Toast.LENGTH_SHORT).show();
								//  mDeleteNewListener.onDeleteNewClick(Util.trimString(s1));
              /*  Map<String, String> map = getRequestMap();
                mPresenter.mainfujian(map);*/
								Map<String, String> map = ParametersFactory.getUrbanLowfujiandeleteMap(context,
										s1,mybatchNoNew);
								mPresenter.mainfujiandelete(map);

							}
						}).build();
				dialog.show();



			}
		});



		viewHolder.btn_bianji.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				InputDialog inputDialog = new InputDialog(context).builder().setTitle("编辑")
						.setPositiveBtn("确定", new InputDialog.OnPositiveListener() {
							@Override
							public void onPositive(View view, String inputMsg) {
								if(TextUtils.isEmpty(inputMsg)){
									Toast.makeText(context,"编辑内容不能为空!",Toast.LENGTH_SHORT).show();
								}else {
									String fileId =data.get(position).getFileId();
									String fileName =inputMsg;
									String fileType =itemJiuzhuxiang.getFileCode();
									//    String batchId ="20180131083618759";
									String batchId =mybatchNoNew;
									Map<String, String> map = ParametersFactory.getUrbanLowfujianbianjiMap(context,
											fileId,fileName,fileType,batchId);
									mPresenter.mainfujianbianji(map);
								}
							}
						})

						.setNegativeBtn("取消", null)
						.setContentMsg("");

				inputDialog.getContentView().setHint("请输入编辑内容");
				inputDialog.getContentView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
				inputDialog.show();
			}
		});



		return convertView;
	}

	static class ViewHolder {
		public TextView tv_name;
		public ImageView iv_tupian;
//		public Button btn_del;
//		public Button btn_bianji;
//		public Button btn_yulan;
		public TextView tv_applydate;
		public LinearLayout btn_del,btn_bianji,btn_yulan;

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
