package com.jqsoft.fingertip_health.adapter;

/**
 * Created by YLL on 2018-2-1.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 适配adapter
 */
public class MyAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    private List<Map<String, String>> parentList = new ArrayList<Map<String, String>>();
    private ExpandableListView exListView;
    private List<String> myItemName=new ArrayList<>();
    private String sHujileixing;

    public MyAdapter(Context context, List<List<Map<String, String>>> childData, List<Map<String, String>> parentList, ExpandableListView exListView,List<String> myItemName,String sHujileixing) {
        this.context=context;
        this.childData=childData;
        this.parentList=parentList;
        this.exListView=exListView;
        this.myItemName=myItemName;
        this.sHujileixing=sHujileixing;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        // TODO Auto-generated method stub
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {

        ViewHolder holder = null;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listview_item,
                    null);
            holder.childText = (TextView) convertView
                    .findViewById(R.id.id_text);
            holder.childBox = (CheckBox) convertView
                    .findViewById(R.id.id_checkbox);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }
/*
        String s =childData.get(groupPosition)
                .get(childPosition).get("childItem");

        if(sHujileixing.equals("农业")){
           sHujileixing="城市低保";
          }else if(sHujileixing.equals("非农业")){
             sHujileixing="农村低保";
        }

        sHujileixing="学前教育贫困幼儿资助";

        if(sHujileixing.equals(s)){
            holder.childBox.setEnabled(false);
        }else {
            holder.childBox.setEnabled(true);
        }*/



        holder.childText.setText(childData.get(groupPosition)
                .get(childPosition).get("childItem"));
        String isChecked = childData.get(groupPosition).get(childPosition)
                .get("isChecked");

        if(myItemName.size()==0){

        }else {
            for(int i=0;i<myItemName.size();i++){
                if(myItemName.get(i).equals(childData.get(groupPosition)
                        .get(childPosition).get("childItem"))){
                    isChecked="Yes";
                }
            }
        }

        if(sHujileixing.equals("农业")){
            sHujileixing="城市低保";
        }else if(sHujileixing.equals("非农业")){
            sHujileixing="农村低保";
        }

        if(sHujileixing.equals(holder.childText.getText().toString())){
         //   holder.childBox.setEnabled(false);
            holder.childBox.setVisibility(View.GONE);
        }else {
            holder.childBox.setVisibility(View.VISIBLE);
        }


        if ("No".equals(isChecked))
        {
            holder.childBox.setChecked(false);
         /*   myItemName.remove(childData.get(groupPosition)
                    .get(childPosition).get("childItem"));*/

        } else
        {
            holder.childBox.setChecked(true);
          /*  myItemName.add(childData.get(groupPosition)
                    .get(childPosition).get("childItem"));*/
        }





        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        // TODO Auto-generated method stub
        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {

        return parentList.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        // TODO Auto-generated method stub
        return parentList.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition,
                             final boolean isExpanded, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.group_item, null);
            holder.groupText = (TextView) convertView
                    .findViewById(R.id.id_group_text);
            holder.groupBox = (CheckBox) convertView
                    .findViewById(R.id.id_group_checkbox);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.groupText.setText(parentList.get(groupPosition).get(
                "groupText"));
        final String isGroupCheckd = parentList.get(groupPosition).get(
                "isGroupCheckd");

        if ("No".equals(isGroupCheckd))
        {
            holder.groupBox.setChecked(false);
        } else
        {
            holder.groupBox.setChecked(true);
        }

			/*
			 * groupListView的点击事件
			 */
        holder.groupBox.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                CheckBox groupBox = (CheckBox) v
                        .findViewById(R.id.id_group_checkbox);
                if (!isExpanded)
                {
                    //展开某个group view
                    exListView.expandGroup(groupPosition);
                } else
                {
                    //关闭某个group view
                    exListView.collapseGroup(groupPosition);
                }

                if ("No".equals(isGroupCheckd))
                {
                    exListView.expandGroup(groupPosition);
                    groupBox.setChecked(true);
                    parentList.get(groupPosition).put("isGroupCheckd",
                            "Yes");
                    List<Map<String, String>> list = childData
                            .get(groupPosition);
                    for (Map<String, String> map : list)
                    {
                        map.put("isChecked", "Yes");
                    }
                } else
                {
                    groupBox.setChecked(false);
                    parentList.get(groupPosition)
                            .put("isGroupCheckd", "No");
                    List<Map<String, String>> list = childData
                            .get(groupPosition);
                    for (Map<String, String> map : list)
                    {
                        map.put("isChecked", "No");
                    }
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }


    private class ViewHolder {
        TextView groupText, childText;
        CheckBox groupBox, childBox;
    }

}



