package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2017/7/1.
 */

public class PopWindowListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DrugInfo> reslist = new ArrayList<>();

    public PopWindowListAdapter(Context context, ArrayList<DrugInfo> reslist) {
        this.context = context;
        this.reslist = reslist;
    }

    @Override
    public int getCount() {
        return reslist.size();
    }

    @Override
    public Object getItem(int position) {
        return reslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_test_pop, null);//
            holder = new ViewHolder();
            holder.pakename = (TextView) convertView.findViewById(R.id.pakename);
            holder.paketype = (TextView) convertView.findViewById(R.id.paketype);
            holder.pakecount = (TextView) convertView.findViewById(R.id.pakecount);
            holder.detpake = (Button) convertView.findViewById(R.id.pakedet);
            holder.text_num = (TextView)convertView.findViewById(R.id.text_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.pakename.setText(reslist.get(position).getName());
        holder.pakecount.setText(reslist.get(position).getPriceSale()+"元/"+reslist.get(position).getUnitYkName());
        holder.text_num.setText(reslist.get(position).getChargeFrequency()+"");
        holder.detpake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String deleteKey = Util.trimString(reslist.get(position).getKey());
//                List<String> listKey = new ArrayList<String>();
//                listKey.add(deleteKey);
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE,
//                        listKey);

                ElectronicPrescriptionActivity dettv = (ElectronicPrescriptionActivity) context;
//                dettv.detItem();
               // dettv.detItemcount(reslist, position);
                reslist.remove(position);
                notifyDataSetChanged();
//                if (reslist.size() == 0) {
//                    dettv.setbottom();
//                }
//                if (uncheckStatus != null) {
//                    uncheckStatus.checkStatus(reslist.get(position).getKey());
//
//                }


            }
        });
        return convertView;
    }

    public interface uncheckStatusListener {
        void checkStatus(String key);
    }

    public uncheckStatusListener uncheckStatus;

    public void setUncheckStatus(uncheckStatusListener listener) {
        uncheckStatus = listener;
    }

    static class ViewHolder {
        public TextView pakename, paketype, pakecount,text_num;
        public Button detpake;

    }
}
