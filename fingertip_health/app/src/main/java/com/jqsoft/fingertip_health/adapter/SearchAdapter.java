//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity;
//import com.jqsoft.fingertip_health.util.Util;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by jingqiang on 2017/1/10.
// */
//
//public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
//   // public ArrayList<String> datas = null;
//
//   // public PeopleListBean datas;
//    private Context mContext;
//    private List<String> data;
//
//    public SearchAdapter(Context mContext, List<String> data) {
//        this.data = data;
//        this.mContext = mContext;
//    }
////    public static final int TYPE_HEADER = 0;
//    public static final int HEADER_ONE = 1;
//    public static final int HEADER_TWO = 2;
//
//    public static final int TYPE_NORMAL = 0;
//
//    private ArrayList<View> mHeaderViews = new ArrayList<>(); // 所有的头布局
//    private List<Integer> sHeaderTypes = new ArrayList<>();//每个header必须有不同的type,不然滚动的时候顺序会变化
//
//
//    public boolean isHeader(int position){
//        if(position<mHeaderViews.size())
//            return true;
//        return false;
//    }
//
//    public void setmHeaderViews(ArrayList<View> mHeaderViews) {
//        this.mHeaderViews = mHeaderViews;
//    }
//
//    public  void setsHeaderTypes(List<Integer> sHeaderTypes) {
//        this.sHeaderTypes = sHeaderTypes;
//    }
//
//
//
//
//    @Override
//    public int getItemViewType(int position) {
//        if(mHeaderViews==null || mHeaderViews.size() <= 0)
//            return TYPE_NORMAL;
//        if(isHeader(position))
//            return sHeaderTypes.get(position);
//        return TYPE_NORMAL;
//    }
//
//    public int getHeaderCount(){
//        return mHeaderViews.size();
//    }
//
//    //创建新View，被LayoutManager所调用
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        if(mHeaderViews!=null && sHeaderTypes.contains(viewType))
//            return new ViewHolder(getHeaderViewByType(viewType));
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search,viewGroup,false);
//
//
//        return new ViewHolder(view);
//    }
//    //将数据与界面进行绑定的操作
//    @Override
//    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
//        if(isHeader(position)){
//        }else {
//            //position要重新校验
//            final int adjPosition = position - getHeaderCount();
//
//            viewHolder.ll_detail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
////                            "com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity");
//                    Util.gotoActivity(viewHolder.ll_detail.getContext(), DetailPeopleInfoActivity.class);
//                }
//            });
//
//
//        }
//    }
//    //获取数据的数量
//    @Override
//    public int getItemCount() {
//        return mHeaderViews == null ? data.size():data.size()+getHeaderCount();
//    }
//
//    //自定义的ViewHolder，持有每个Item的的所有界面元素
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView tv_name;
//        public ImageView img_head;
//        public TextView tv_minzu;
//        public TextView tv_address;
//        public TextView tv_xingbie;
//        public LinearLayout ll_detail;
//
//        public ViewHolder(View view){
//            super(view);
//            tv_name = (TextView) view.findViewById(R.id.tv_name);
//            ll_detail =(LinearLayout)view.findViewById(R.id.ll_detail);
//
//        }
//    }
//
//    private boolean isHeaderType(int type){
//        return mHeaderViews.size() > 0 && sHeaderTypes.contains(type);
//    }
//
//    private View getHeaderViewByType(int itemType){
//        if(!isHeaderType(itemType))
//            return null;
//        return mHeaderViews.get(itemType - TYPE_NORMAL - 1);
//    }
//}