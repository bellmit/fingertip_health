package com.jqsoft.fingertip_health.adapter.statistics;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class PopWindowListCaoyaoListAdapter extends RecyclerView.Adapter<PopWindowListCaoyaoListAdapter.ViewHolder> {

    Context context;
    private ArrayList<DrugInfo> reslist = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pakename, paketype, pakecount, text_num;
        public Button detpake, btn_jia,btn_jian;

        public ViewHolder(View view) {
            super(view);
            pakename = (TextView) view.findViewById(R.id.pakename);
            paketype = (TextView) view.findViewById(R.id.paketype);
            pakecount = (TextView) view.findViewById(R.id.pakecount);
            text_num = (TextView) view.findViewById(R.id.text_num);
            detpake = (Button) view.findViewById(R.id.pakedet);
            text_num = (TextView) view.findViewById(R.id.text_num);
            btn_jia = (Button) view.findViewById(R.id.btn_jia);
            btn_jian= (Button) view.findViewById(R.id.btn_jian);

        }
    }

    public PopWindowListCaoyaoListAdapter(ArrayList<DrugInfo> reslist, Context context) {
        this.reslist = reslist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_pop, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      final   DrugInfo drugInfo = reslist.get(position);
        holder.pakename.setText(drugInfo.getName());
        holder.pakecount.setText(drugInfo.getPrice() + "元/" + reslist.get(position).getUnit());
        holder.text_num.setText(drugInfo.getChargeFrequency() + "");
        holder.btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int   sum = reslist.get(position).getChargeFrequency();
                sum++;
                holder.text_num.setText(sum+"");
                reslist.get(position).setChargeFrequency(sum);
                Activity activity = Util.scanForActivity(context);
                if (activity instanceof ElectronicPrescriptionActivity) {
                    ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
                    oca.PopwindhandleCaoyaoDrugInfo(drugInfo);
                }
            }
        });
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = reslist.get(position).getChargeFrequency();
                if(sum>1){
                    sum--;
                    holder.text_num.setText(sum + "");
                    reslist.get(position).setChargeFrequency(sum);
                    Activity activity = Util.scanForActivity(context);
                    if (activity instanceof ElectronicPrescriptionActivity) {
                        ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
                        oca.itemdrug_caculate_jian(drugInfo.getPrice());
                    }
                }else{

                    Activity activity = Util.scanForActivity(context);
                    if (activity instanceof ElectronicPrescriptionActivity) {
                        ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
                        oca.detItemcount( position,3,drugInfo.getPrice());
                    }
                }

            }
        });
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return reslist.size();
    }


}
