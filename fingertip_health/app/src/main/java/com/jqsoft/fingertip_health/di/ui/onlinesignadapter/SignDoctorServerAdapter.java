//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignadapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
///**
// * Created by Jerry on 2017/6/29.
// */
//
//public class SignDoctorServerAdapter extends RecyclerView.Adapter<SignDoctorServerAdapter.ViewHolder> implements PopWindowListAdapter.uncheckStatusListener {
//    private Context context;
//    private int temp = 1;
//    //public String[] data;
//    private List<Boolean> isClicks = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> reslist = new ArrayList<>();
//    private static SignDoctorServerAdapter instance = null;
//
//    private CompositeSubscription mDeleteMessageSubscription;
//
//    private void registerDeleteMessageSubscription() {
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE, List.class).subscribe(new Action1<List>() {
//            @Override
//            public void call(List reslist) {
//                List<String> listKey = (List<String>) reslist;
//                //   List<SignSeverPakesBeanList> list = (List<SignSeverPakesBeanList>)reslist;
//                if (listKey.size() == 1) {
//                    String s = Util.trimString(listKey.get(0));
//                    checkStatus(s);
//                } else {
//                    detalllist(listKey);
//                }
//            }
//        });
////        Subscription initilizeOnlineSignSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_ONLINE_SIGN_AND_INITIALIZE, IndexAndOnlineSignInitialData.class)
////                .subscribe(new Action1<IndexAndOnlineSignInitialData>() {
////                    @Override
////                    public void call(IndexAndOnlineSignInitialData indexAndOnlineSignInitialData) {
////
////                    }
////                });
//        if (mDeleteMessageSubscription == null) {
//            mDeleteMessageSubscription = new CompositeSubscription();
//        }
//        mDeleteMessageSubscription.add(subscription);
//    }
//
//    public SignDoctorServerAdapter() {
//
//    }
//
////    public static SignDoctorServerAdapter signDoctorServerAdapter() {
////        if (instance == null) {
////            instance = new SignDoctorServerAdapter();
////        }
////        return instance;
////    }
//
//    public void checkStatus(String key) {
//        for (int i = 0; i < datalist.size(); i++) {
//            if (key.equals(datalist.get(i).getKey())) {
//                isClicks.set(i, false);
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    public interface OnItemClickListener {
//        void OnItemClick(View view, int position);
//
//        void OnItemLongClick(View view, int position);
//    }
//
//    private SignDoctorServersAdapter.OnItemClickListener mOnItemClickListener;
//
//    public void setOnItemClickListener(SignDoctorServersAdapter.OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//
//
//    // 1 定义了所有activity必须实现的接口方法
//    private FragmentInteraction listterner;
//
//    public interface FragmentInteraction {
//        void process(ArrayList<SignSeverPakesBeanList> itemlist);
//
//        void uncheck(String str);
//    }
//
//    public void setFragmentInteraction(FragmentInteraction listterner) {
//        this.listterner = listterner;
//    }
//
//    public void unregisterDeleteAction() {
//        if (mDeleteMessageSubscription != null && mDeleteMessageSubscription.hasSubscriptions()) {
//            mDeleteMessageSubscription.unsubscribe();
//        }
//
//    }
//
//    public SignDoctorServerAdapter(Context context, ArrayList<SignSeverPakesBeanList> datalist) {
//        this.context = context;
//        this.datalist = datalist;
//        instance = this;
//
//        if (mDeleteMessageSubscription == null) {
//            registerDeleteMessageSubscription();
//        }
//
//
//        for (int i = 0; i < datalist.size(); i++) {
//            isClicks.add(false);
//        }
//    }
//
//    public void setNewData(List<SignSeverPakesBeanList> newList) {
//        isClicks.clear();
//        if (!ListUtils.isEmpty(newList)) {
//            for (int i = 0; i < newList.size(); ++i) {
//                isClicks.add(false);
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_client_pack, null);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        if (mOnItemClickListener != null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.OnItemClick(holder.itemView, position);
//
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mOnItemClickListener.OnItemLongClick(holder.itemView, position);
//                    return false;
//                }
//            });
//        }
//
////        nhcompensateProjName （1:基础包 2:初级包 3: 中级包 4:高级包 5 其他）
////        fwmc服务包名称
////        fwnr服务内容
////        sydx适用对象
////        hjnysfje总收入（总金额）
////        xnhbcje新农合应予补偿金额
////        qtjmje其他减免金额
////        sjzfje实际自付金额
////        cdje基本公共卫生服务经费承担金额
////        serviceCententFee实收金额
////         key服务包主键
//        String fwmc = datalist.get(position).getFwmc();
//        String fwnr = datalist.get(position).getFwnr();
//        String hjnysfje = datalist.get(position).getHjnysfje();
//        String qtjmje = datalist.get(position).getQtjmje();
//        String xnhbcje = datalist.get(position).getXnhbcje();
//        String sjzfje = datalist.get(position).getSjzfje();
//        holder.tvItem.setText(fwmc);
//        holder.tv_projnr.setText(fwnr);
//        holder.tv_money.setText("总金额:" + hjnysfje + "元; 减免金额:" + qtjmje + "元; 医保补偿金额:" + xnhbcje + "元; 自付金额:" + sjzfje + "元");
//        if (datalist.get(position).getNhcompensateProjName().equals("1")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#F4D249"));
//            holder.paketype.setText("基础");
//        } else if (datalist.get(position).getNhcompensateProjName().equals("2")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#6ED1E0"));
//            holder.paketype.setText("初级");
//        } else if (datalist.get(position).getNhcompensateProjName().equals("3")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#7DD0FF"));
//            holder.paketype.setText("中级");
//        } else if (datalist.get(position).getNhcompensateProjName().equals("4")) {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
//            holder.paketype.setText("高级");
//        } else {
//            holder.paketype.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
//            holder.paketype.setText("其他");
//        }
//        if (listterner != null) {
//          /*  holder.select_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (holder.select_btn.isChecked()) {
//                        reslist.clear();
//                        reslist.add(datalist.get(position));
//                        listterner.process(reslist);
//                        isClicks.set(position, true);
//                    } else {
//                        listterner.uncheck(datalist.get(position).getKey());
//                        isClicks.set(position, false);
//                    }
//                }
//            });*/
//        }
////        isClicks.get(position);
//      //  holder.select_btn.setChecked(isClicks.get(position));
////        holder.select_btn.setOnCheckedChangeListener(null);
//
//        //       holder.select_btn.setChecked(isClicks.get(i));
//
//
////            holder.select_btn.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    if(holder.select_btn.isChecked()){
////                        reslist.clear();
////                        reslist.add(datalist.get(position));
////                        listterner.process(reslist);
////                    }else{
////                        listterner.uncheck(datalist.get(position).getKey());
////
////                    }
////
////                }
////            });
//
////        holder.select_btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if( holder.select_btn.isChecked()){
////                    datalist.get(position);
////                   // holder.itemView.setBackgroundColor(Color.parseColor("#66CDAA"));
//////                    holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"));
//////                    holder.paketype.setTextColor(Color.parseColor("#FFFFFF"));
//////                    holder.tv_projnr.setTextColor(Color.parseColor("#FFFFFF"));
//////                    holder.tv_money.setTextColor(Color.parseColor("#FFFFFF"));
////                }else{
////                 //   holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//////                    holder.tvItem.setTextColor(Color.parseColor("#000000"));
//////                    holder.paketype.setTextColor(Color.parseColor("#9ba4a3"));
//////                    holder.tv_projnr.setTextColor(Color.parseColor("#9ba4a3"));
//////                    holder.tv_money.setTextColor(Color.parseColor("#66CDAA"));
////                }
////
////
////            }
////
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return datalist.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView tvItem, tv_projnr, tv_money, paketype;
//     //   public android.widget.CheckBox select_btn;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvItem = (TextView) itemView.findViewById(R.id.servertitle);
//            paketype = (TextView) itemView.findViewById(R.id.paketype);
//            tv_projnr = (TextView) itemView.findViewById(R.id.tv_projName);
//            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
//       //     select_btn = (android.widget.CheckBox) itemView.findViewById(R.id.select_btn);
//        }
//    }
//
//    public void detalllist(List<String> listKey) {
//        if (listKey.size() > 0) {
//            for (int j = 0; j < datalist.size(); j++) {
//                for (int i = 0; i < listKey.size(); i++) {
//                    if (listKey.get(i).equals(datalist.get(j).getKey())) {
//                        isClicks.set(j, false);
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//    }
//}
