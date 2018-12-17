//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignadapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//
//import java.util.ArrayList;
//
///**
// * Created by YLL on 2017-7-6.
// */
//
//public class GallerySeverpakesAdapter extends RecyclerView.Adapter<GallerySeverpakesAdapter.ViewHolder> {
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//
//    public interface OnItemClickLitener {
//        void onItemClick(View view, int position);
//    }
//
//    private OnItemClickLitener mOnItemClickLitener;
//
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }
//
//    // 删除计算接口
//    public interface DeletOnItemClickListener {
//        void onItemDelete(ArrayList<SignSeverPakesBeanList> datalist, int position);
//    }
//
//    private DeletOnItemClickListener listener;
//
//    public void setDeteOnItemClickListener(DeletOnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    private LayoutInflater mInflater;
//    private Context context;
//
//    public GallerySeverpakesAdapter(Context context, ArrayList<SignSeverPakesBeanList> datalist) {
//        this.context = context;
//        this.mInflater = LayoutInflater.from(context);
//        this.datalist = datalist;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(View arg0) {
//            super(arg0);
//        }
//
//        RelativeLayout rl_image;
//        Button detsever_btn;
//        TextView mTxt;
//    }
//
//    @Override
//    public int getItemCount() {
//        return datalist.size();
//    }
//
//    /**
//     * 创建ViewHolder
//     */
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
//        View view = mInflater.inflate(R.layout.item_gallery_server,
//                viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.detsever_btn = (Button) view.findViewById(R.id.detsever_btn);
//        viewHolder.rl_image = (RelativeLayout) view.findViewById(R.id.rl_image);
//        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_pack);
//
//
//        return viewHolder;
//    }
//
//    /**
//     * 设置值
//     */
//    @Override
//    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
//        //  viewHolder.mTxt.setImageResource(mDatas.get(i));
//        viewHolder.mTxt.setText(datalist.get(position).getFwmc());
//        if (datalist.get(position).getNhcompensateProjName().equals("1")) {
//            viewHolder.rl_image.setBackgroundResource(R.mipmap.zhongjisbg);
//        } else if (datalist.get(position).getNhcompensateProjName().equals("2")) {
//            viewHolder.rl_image.setBackgroundResource(R.mipmap.chujisbg3);
//        } else if (datalist.get(position).getNhcompensateProjName().equals("3")) {
//            viewHolder.rl_image.setBackgroundResource(R.mipmap.qianyue_fuwubao);
//        } else if (datalist.get(position).getNhcompensateProjName().equals("4")) {
//            viewHolder.rl_image.setBackgroundResource(R.mipmap.gaojisbg);
//        }
//        //如果设置了回调，则设置点击事件
//        if (mOnItemClickLitener != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
//                }
//            });
//
//        }
//        if (listener != null) {
//            viewHolder.detsever_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemDelete(datalist, position);
//                    datalist.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
//        }
//
//    }
//
//}
