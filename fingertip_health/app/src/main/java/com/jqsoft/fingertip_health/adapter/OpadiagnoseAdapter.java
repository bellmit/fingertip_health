package com.jqsoft.fingertip_health.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

public class OpadiagnoseAdapter extends RecyclerView.Adapter<OpadiagnoseAdapter.ViewHolder> {


    private ArrayList<GDWS_ICD> opadiagnoseList = new ArrayList<>();
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_delete;
        public TextView tv_zdname;

        public ViewHolder(View view) {
            super(view);
            btn_delete = (TextView) view.findViewById(R.id.btn_delete);
            tv_zdname = (TextView) view.findViewById(R.id.tv_zdname);
        }
    }

    public OpadiagnoseAdapter(ArrayList<GDWS_ICD> opadiagnoseList,Context context) {
        this.opadiagnoseList = opadiagnoseList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnose_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GDWS_ICD gdws_icd = opadiagnoseList.get(position);
        holder.tv_zdname.setText(gdws_icd.getName());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = Util.scanForActivity(context);
                if (activity instanceof OutpatientChargesActivity) {
                    OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                    oca.item_diagnose_detel(position);
                }

            }
        });
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return opadiagnoseList.size();
    }


//	public OpadiagnoseAdapter(Context context, ArrayList<GDWS_ICD> opadiagnoseList) {
//		super();
//		this.context = context;
//		this.opadiagnoseList = opadiagnoseList;
//
//	}
//
//	@Override
//	public int getCount() {
//
//		return opadiagnoseList   .size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		 ViewHolder viewHolder = null;
//		if (convertView == null) {
//			inflater = LayoutInflater.from(parent.getContext());
//			convertView = inflater.inflate(R.layout.item_diagnose_layout, null);
//			viewHolder = new ViewHolder();
//			viewHolder.btn_delete = (TextView) convertView.findViewById(R.id.tv_access_unit);
//            viewHolder.tv_zdname = (TextView) convertView.findViewById(R.id.tv_access_name);
//
//
//			convertView.setTag(viewHolder);
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
//
////		String sData =data.get(position);
//
//
//		return convertView;
//	}
//
//	static class ViewHolder {
//		public  TextView btn_delete;
//		public TextView tv_zdname;
//
//	}
}
