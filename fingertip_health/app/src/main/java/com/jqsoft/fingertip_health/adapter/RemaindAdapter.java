package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;

import java.util.ArrayList;

/**
 * Created by YLL on 2017-7-6.
 */

public class RemaindAdapter extends RecyclerView.Adapter<RemaindAdapter.ViewHolder> {
    private ArrayList<RemindBean> datalist = new ArrayList<>();

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    // 删除计算接口
    public interface DeletOnItemClickListener {
        void onItemDelete(ArrayList<RemindBean> datalist, int position);
    }

    private DeletOnItemClickListener listener;

    public void setDeteOnItemClickListener(DeletOnItemClickListener listener) {
        this.listener = listener;
    }

    private LayoutInflater mInflater;
    private Context context;

    public RemaindAdapter(Context context, ArrayList<RemindBean> datalist) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.datalist = datalist;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView rl_image;
        TextView tv_content;
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = mInflater.inflate(R.layout.item_remind,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.rl_image = (ImageView) view.findViewById(R.id.iv_image);
        viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);


        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //  viewHolder.mTxt.setImageResource(mDatas.get(i));
        viewHolder.tv_content.setText(datalist.get(position).getContentName());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                }
            });

        }
        if (listener != null) {
//            viewHolder.detsever_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemDelete(datalist, position);
//                    datalist.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
        }

    }

}
