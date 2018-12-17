//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignadapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.di.ui.activity.SignClientDoctorSeverPakes;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by jerry on 2017/7/1.
// */
//
//public class PopWindowListAdapter extends BaseAdapter {
//    private Context context;
//    private ArrayList<SignSeverPakesBeanList> reslist = new ArrayList<>();
//
//    public PopWindowListAdapter(Context context, ArrayList<SignSeverPakesBeanList> reslist) {
//        this.context = context;
//        this.reslist = reslist;
//    }
//
//    @Override
//    public int getCount() {
//        return reslist.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return reslist.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.item_test_pop, null);//
//            holder = new ViewHolder();
//            holder.pakename = (TextView) convertView.findViewById(R.id.pakename);
//            holder.paketype = (TextView) convertView.findViewById(R.id.paketype);
//            holder.pakecount = (TextView) convertView.findViewById(R.id.pakecount);
//            holder.detpake = (Button) convertView.findViewById(R.id.pakedet);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        if (reslist.get(position).getNhcompensateProjName().equals("1")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#F4D249"));
//            //holder.paketype.setBackgroundResource(R.drawable.basic_actionbar_tv_nor);
//            holder.paketype.setText("基础");
//        } else if (reslist.get(position).getNhcompensateProjName().equals("2")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#6ED1E0"));
//            //  holder.paketype.setBackgroundResource(R.drawable.primary_actionbar_tv_nor);
//            holder.paketype.setText("初级");
//        } else if (reslist.get(position).getNhcompensateProjName().equals("3")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#7DD0FF"));
//            // holder.paketype.setBackgroundResource(R.drawable.mid_actionbar_tv_nor);
//            holder.paketype.setText("中级");
//        } else if (reslist.get(position).getNhcompensateProjName().equals("4")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
//            //  holder.paketype.setBackgroundResource(R.drawable.high_actionbar_tv_nor);
//            holder.paketype.setText("高级");
//        } else {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
//            //holder.paketype.setBackgroundResource(R.drawable.actionbar_tv_nor);
//            holder.paketype.setText("其他");
//        }
//        holder.pakename.setText(reslist.get(position).getFwmc());
//        holder.pakecount.setText(reslist.get(position).getSjzfje());
//        holder.detpake.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String deleteKey = Util.trimString(reslist.get(position).getKey());
//                List<String> listKey = new ArrayList<String>();
//                listKey.add(deleteKey);
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE,
//                        listKey);
//
//                SignClientDoctorSeverPakes dettv = (SignClientDoctorSeverPakes) context;
//                dettv.detItem();
//                dettv.detItemcount(reslist, position);
//                reslist.remove(position);
//                notifyDataSetChanged();
//                if (reslist.size() == 0) {
//                    dettv.setbottom();
//                }
////                if (uncheckStatus != null) {
////                    uncheckStatus.checkStatus(reslist.get(position).getKey());
////
////                }
//
//
//            }
//        });
//        return convertView;
//    }
//
//    public interface uncheckStatusListener {
//        void checkStatus(String key);
//    }
//
//    public uncheckStatusListener uncheckStatus;
//
//    public void setUncheckStatus(uncheckStatusListener listener) {
//        uncheckStatus = listener;
//    }
//
//    static class ViewHolder {
//        public TextView pakename, paketype, pakecount;
//        public Button detpake;
//
//    }
//}
