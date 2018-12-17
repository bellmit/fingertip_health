package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.KuncunListActivityBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonListActivityBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


public class KucunfenxiListActivityAdapter extends BaseQuickAdapterEx<KuncunListActivityBean, BaseViewHolder> {
    private Context context;
    private ListAdapter adapter;

    public KucunfenxiListActivityAdapter(List<KuncunListActivityBean> data, Context context) {
        super(R.layout.inventory_item_layout, data);

        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final KuncunListActivityBean item) {
//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;
//        HorizontalListView listView=helper.getView(R.id.listview);
////        recyclerView.setLayoutManager(new LinearLayoutManager(context));
////        recyclerView.addItemDecoration(new DividerItemDecoration(context, RecyclerView.HORIZONTAL));
//        adapter = new HighBloodListItemAdapter(context,item.getPropMap());
//        listView.setAdapter(adapter);
//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;

        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv_disname, Util.trimString(item.getName()));
        }
        helper.setText(R.id.tv_yibao, Util.trimString(item.getDoseName()));
        helper.setText(R.id.productadd, Util.trimString(item.getSupplier()));
        helper.setText(R.id.durgcode, Util.trimString(item.getDrugId()));
        if (!TextUtils.isEmpty(item.getExpireDate())) {
            helper.setText(R.id.time, Util.trimString(item.getExpireDate()));
        }
        helper.setText(R.id.jixing, Util.trimString(item.getApprovalNumber()));

        helper.setText(R.id.tv_pin, Util.trimString(item.getDrugSpecificationUnit()));
        helper.setText(R.id.price, Util.trimString(item.getPriceView()));
        helper.setText(R.id.ku_cun, Util.trimString(  Util.trimString( item.getStockView())));



//        helper.setText(R.id.tv_message, Util.trimString(item.getPostMessage()));
//        String time = Util.trimString(item.getSetTime());
//        String canonicalTime = Util.getYearMonthDayFromFullString(time);
//        helper.setText(R.id.tv_time, canonicalTime);
//        ImageView iv_tang =helper.getView(R.id.iv_tang);


//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }

//    OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClickListener(String id, String imgUrl, View view);}

}
