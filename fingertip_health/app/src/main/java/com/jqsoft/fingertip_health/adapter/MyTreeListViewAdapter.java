package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MyNodeBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.Node;

import java.util.List;


public class MyTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

	private String itemid;
	private List<MyNodeBean> mDatas;
	boolean flag =true;

	public MyTreeListViewAdapter(ListView mTree, Context context,
								 List<T> datas, int defaultExpandLevel,boolean isHide,String ItemId,List<MyNodeBean>mDatas)
			throws IllegalArgumentException, IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel,isHide);

		this.itemid=ItemId;
		this.mDatas=mDatas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getConvertView(Node node, int position, View convertView,
							   ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			/*viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);*/
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_name);
			viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.id_treeNode_check);

			convertView.setTag(viewHolder);

		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1)
		{
			//viewHolder.icon.setVisibility(View.INVISIBLE);
		} else
		{
			//viewHolder.icon.setVisibility(View.VISIBLE);
			//viewHolder.icon.setImageResource(node.getIcon());
		}




		if(TextUtils.isEmpty(itemid)){

		}else {
			String[] split = itemid.split(",");

			int ssss=position;
			int dr=node.getId();
			//int s45=node.getChildrenNodes().get();
			for(int i=0;i<mDatas.size();i++){
				for(int j=0;j<split.length;j++){
					if(split[j].equals(mDatas.get(i).getDesc())){
						for(int k=0;k<node.getChildrenNodes().size();k++){
							if(node.getChildrenNodes().get(k).getId()==mDatas.get(i).getId()){
								node.getChildrenNodes().get(k).setChecked(true);

							}
						}

					}
				}
			}

		}




		if(node.isHideChecked()){
			viewHolder.checkBox.setVisibility(View.GONE);
		}else{
			viewHolder.checkBox.setVisibility(View.VISIBLE);
			setCheckBoxBg(viewHolder.checkBox,node.isChecked());
		}






		if(position<8){
			viewHolder.label.setText(node.getName()+"啦");
			//	setCheckBoxBg(viewHolder.checkBox,true);
		}else {
			viewHolder.label.setText(node.getName());
		}


		return convertView;
	}
	private final class ViewHolder
	{
		//ImageView icon;
		TextView label;
		CheckBox checkBox;
	}

	/**
	 * checkbox是否显示
	 * @param cb
	 * @param isChecked
	 */
	private void setCheckBoxBg(CheckBox cb,boolean isChecked){
		if(isChecked){

			cb.setBackgroundResource(R.drawable.check_box_bg_check);
		}else{
			cb.setBackgroundResource(R.drawable.check_box_bg);

		}
	}
}
