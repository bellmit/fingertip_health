package com.jqsoft.fingertip_health.adapter.statistics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class DrugListAdapter extends RecyclerView.Adapter<DrugListAdapter.ViewHolder> {
    Context context;
     int a = 0;

    private ArrayList<DrugInfo> opadiagnoseList = new ArrayList<>();
    private int sum = 1;

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_drugname, spec_tv, tv_mpu1, tv_mpu2, tv_price, btn_delete;
        public TextView address, jian_btn, jia_btn, pro_num;

        public ViewHolder(View view) {
            super(view);
            tv_drugname = (TextView) view.findViewById(R.id.tv_drugname);
            spec_tv = (TextView) view.findViewById(R.id.spec_tv);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            address = (TextView) view.findViewById(R.id.address);
            jian_btn = (TextView) view.findViewById(R.id.jian_btn);
            jia_btn = (TextView) view.findViewById(R.id.jia_btn);
            pro_num = (TextView) view.findViewById(R.id.pro_num);
            tv_mpu1 = (TextView) view.findViewById(R.id.tv_mpu1);
            tv_mpu2 = (TextView) view.findViewById(R.id.tv_mpu2);
            btn_delete = (TextView) view.findViewById(R.id.btn_delete);
        }
    }

    public DrugListAdapter(ArrayList<DrugInfo> opadiagnoseList, Context context) {
        this.opadiagnoseList = opadiagnoseList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DrugInfo drugInfo = opadiagnoseList.get(position);
        holder.tv_drugname.setText(drugInfo.getName());
        holder.spec_tv.setText(drugInfo.getSpec());
        holder.address.setText(drugInfo.getSupplier());
        holder.pro_num.setText(drugInfo.getChargeFrequency() + "");
        if (opadiagnoseList.get(position).getMpu().equals("1")) {
            opadiagnoseList.get(position).setTempUnit(opadiagnoseList.get(position).getUnitYkName());
            holder.tv_mpu1.setText(drugInfo.getTempUnit());
            opadiagnoseList.get(position).setTempPrice(drugInfo.getPriceSale());
            holder.tv_price.setText(drugInfo.getTempPrice());

        } else if (opadiagnoseList.get(position).getMpu().equals("2")) {
            if(TextUtils.isEmpty(opadiagnoseList.get(position).getTempMpu())){
                holder.tv_mpu1.setVisibility(View.GONE);
                holder.tv_mpu2.setVisibility(View.VISIBLE);
                opadiagnoseList.get(position).setTempUnit(opadiagnoseList.get(position).getUnit());
                holder.tv_mpu2.setText(drugInfo.getTempUnit());
                opadiagnoseList.get(position).setTempPrice(drugInfo.getPrice());
                holder.tv_price.setText(drugInfo.getTempPrice());
            }else{
                holder.tv_mpu1.setVisibility(View.GONE);
                holder.tv_mpu2.setVisibility(View.VISIBLE);
                holder.tv_mpu2.setText(drugInfo.getTempUnit());
                holder.tv_price.setText(drugInfo.getTempPrice());
            }


        }
          if(a==1){
            holder.tv_mpu1.setVisibility(View.GONE);
            holder.tv_mpu2.setVisibility(View.VISIBLE);
            holder.tv_mpu2.setText(drugInfo.getTempUnit());
            holder.tv_price.setText(drugInfo.getTempPrice());
        }
//        if(holder.tv_mpu.equals("3")){
//            holder.tv_price.setText(drugInfo.getPrice());
//            holder.tv_mpu.setText(drugInfo.getUnit());
//
//        }else{
//            holder.tv_price.setText(drugInfo.getPriceSale());
//            holder.tv_mpu.setText(drugInfo.getUnitYkName());
//        }
        holder.tv_mpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{drugInfo.getUnitYkName(), drugInfo.getUnit()};//大单位  小单位
                AlertDialog dialog = new AlertDialog.Builder(context).setTitle("请选择类型")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String Str = items[which];
                                holder.tv_mpu2.setText(Str);
                                if (which == 0) {
                                    a=1;
                                    opadiagnoseList.get(position).setTempPrice(drugInfo.getPriceSale());
                                    opadiagnoseList.get(position).setTempUnit(drugInfo.getUnitYkName());
                                     opadiagnoseList.get(position).setTempMpu("8");
//                                    opadiagnoseList.get(position).setMpu("3");
                                    holder.tv_price.setText(drugInfo.getTempPrice());

                                    Activity activity = Util.scanForActivity(context);
                                    if (activity instanceof OutpatientChargesActivity) {
                                        OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
//                                        oca.item_TotolPrice(position, Double.parseDouble(drugInfo.getPriceSale()), Double.parseDouble(holder.pro_num.getText().toString()));
                                    oca.caculateNum();

                                    }
                                } else if (which == 1) {
                                    a=1;
                                    opadiagnoseList.get(position).setTempPrice(drugInfo.getPrice());
                                    opadiagnoseList.get(position).setTempUnit(drugInfo.getUnit());
                                    opadiagnoseList.get(position).setTempMpu("8");
                                    holder.tv_price.setText(drugInfo.getTempPrice());
                                    Activity activity = Util.scanForActivity(context);
                                    if (activity instanceof OutpatientChargesActivity) {
                                        OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
//                                        oca.item_TotolPrice(position, Double.parseDouble(drugInfo.getPriceSale()), Double.parseDouble(holder.pro_num.getText().toString()));
                                        oca.caculateNum();

                                    }
                                }

                                dialog.dismiss();

                            }
                        }).create();
                dialog.show();


            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = Util.scanForActivity(context);
                if (activity instanceof OutpatientChargesActivity) {
                    OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                    oca.item_drugcaculate_detel(position, holder.tv_price.getText().toString(), holder.pro_num.getText().toString());
                }
            }
        });
        holder.jia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = opadiagnoseList.get(position).getChargeFrequency();
                sum++;
                holder.pro_num.setText(sum + "");
                opadiagnoseList.get(position).setChargeFrequency(sum);

                Activity activity = Util.scanForActivity(context);
                if (activity instanceof OutpatientChargesActivity) {
                    OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                    oca.itemdrug_caculate_add(holder.tv_price.getText().toString());
                }
            }
        });
        holder.jian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = opadiagnoseList.get(position).getChargeFrequency();
                if (sum > 1) {
//                    sum = opadiagnoseList.get(position).getChargeFrequency();
                    sum--;
                    holder.pro_num.setText(sum + "");
                    opadiagnoseList.get(position).setChargeFrequency(sum);
                    Activity activity = Util.scanForActivity(context);
                    if (activity instanceof OutpatientChargesActivity) {
                        OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
                        oca.itemdrug_caculate_jian(holder.tv_price.getText().toString());
                    }
                } else {
                    Toast.makeText(context, "最小值1", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return opadiagnoseList.size();
    }

//    private void didSelectOnePatient(DrugInfo bean) {
//        Activity activity = Util.scanForActivity(context);
//        if (activity instanceof ElectronicPrescriptionActivity) {
//            ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
//            oca.handleDrugInfo(bean);
//        }
//    }
}
