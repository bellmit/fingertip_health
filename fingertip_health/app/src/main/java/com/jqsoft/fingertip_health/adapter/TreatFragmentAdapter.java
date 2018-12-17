package com.jqsoft.fingertip_health.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class TreatFragmentAdapter extends RecyclerView.Adapter<TreatFragmentAdapter.ViewHolder> {
    private int sum = 1;
    private Context context;
    private ArrayList<TreatdirectoryBean> datalist = new ArrayList<>();


    public TreatFragmentAdapter(ArrayList<TreatdirectoryBean> datalist, Context context) {
        this.context = context;
        this.datalist = datalist;
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treat_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TreatdirectoryBean treatdirectoryBean = datalist.get(position);
        holder.tv_treatname.setText(treatdirectoryBean.getName());
        holder.feestandard.setText(treatdirectoryBean.getFeeStandard());


        holder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                didSelectOnePatient(datalist.get(position));
            }
        });
        holder.add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = datalist.get(position).getChargeFrequency();
                sum++;
                holder.pro_num.setText(sum+"");
                datalist.get(position).setChargeFrequency(sum);
            }
        });
        holder.jian_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sum>1){
                    sum = datalist.get(position).getChargeFrequency();
                    sum--;
                    holder.pro_num.setText(sum+"");
                    datalist.get(position).setChargeFrequency(sum);
                }else{
                    Toast.makeText(context,"最小值1",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_treatname;
        public TextView feestandard;
        public TextView pro_num;
        public TextView add_btn;
        public TextView jian_tv, add_tv;

        public ViewHolder(View view) {
            super(view);
            tv_treatname = (TextView) view.findViewById(R.id.tv_treatname);
            feestandard = (TextView) view.findViewById(R.id.feestandard);
            pro_num = (TextView) view.findViewById(R.id.pro_num);
            add_btn = (TextView) view.findViewById(R.id.add_btn);
            jian_tv = (TextView) view.findViewById(R.id.jian_tv);
            add_tv = (TextView) view.findViewById(R.id.add_tv);
        }
    }

    private void didSelectOnePatient(TreatdirectoryBean bean) {
        Activity activity = Util.scanForActivity(context);
        if (activity instanceof ElectronicPrescriptionActivity) {
            ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
            oca.handleTreatProInfo(bean);
        }
    }
}
