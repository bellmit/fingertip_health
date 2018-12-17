package com.jqsoft.fingertip_health.adapter.statistics;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.TreatmentListBean;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class TreatListAdapter extends RecyclerView.Adapter<TreatListAdapter.ViewHolder> {

    Context context;
    int sum=1;
    private ArrayList<TreatdirectoryBean> opadiagnoseList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_proname,btn_delete;
        public TextView proprice,pro_num,jia_btn,jian_btn;

        public ViewHolder(View view) {
            super(view);
            tv_proname = (TextView) view.findViewById(R.id.tv_proname);
            proprice = (TextView) view.findViewById(R.id.proprice);
            pro_num= (TextView) view.findViewById(R.id.pro_num);
            jia_btn= (TextView) view.findViewById(R.id.jia_btn);
            jian_btn= (TextView) view.findViewById(R.id.jian_btn);
            btn_delete = (TextView)view.findViewById(R.id.btn_delete);

        }
    }

    public TreatListAdapter(ArrayList<TreatdirectoryBean> opadiagnoseList,Context context) {
        this.opadiagnoseList = opadiagnoseList;
        this.context= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treatlist_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final  int position) {
        TreatdirectoryBean treatdirectoryBean = opadiagnoseList.get(position);
        holder.tv_proname.setText(treatdirectoryBean.getName());
        holder.proprice.setText(treatdirectoryBean.getFeeStandard());
        holder.pro_num.setText(treatdirectoryBean.getChargeFrequency()+"");

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = Util.scanForActivity(context);
                if (activity instanceof OutpatientChargesActivity) {
                    OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                    oca.item_treatcaculate_detel(position,holder.proprice.getText().toString(),holder.pro_num.getText().toString());
                }
            }
        });
        holder.jia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = opadiagnoseList.get(position).getChargeFrequency();
                sum++;
                holder.pro_num.setText(sum+"");
                opadiagnoseList.get(position).setChargeFrequency(sum);

                Activity activity = Util.scanForActivity(context);
                if (activity instanceof OutpatientChargesActivity) {
                    OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                    oca.itemtreat_caculate_add( holder.proprice.getText().toString());
                }
            }
        });
        holder.jian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = opadiagnoseList.get(position).getChargeFrequency();
                if(sum>1){
                    sum--;
                    holder.pro_num.setText(sum+"");
                    opadiagnoseList.get(position).setChargeFrequency(sum);
                    Activity activity = Util.scanForActivity(context);
                    if (activity instanceof OutpatientChargesActivity) {
                        OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                        oca.itemtreat_caculate_jian( holder.proprice.getText().toString());
                    }
                }else{
                    Toast.makeText(context,"最小值1",Toast.LENGTH_SHORT).show();
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
