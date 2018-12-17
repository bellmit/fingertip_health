package com.jqsoft.fingertip_health.di.ui.onlinesignadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.PersonInfoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YLL on 2017-7-6.
 */

public class NHListAdapter extends RecyclerView.Adapter<NHListAdapter.ViewHolder> {
    private ArrayList<PersonInfoList> xhlistdata = new ArrayList<>();
    public RecyclerView recyclerView;
    public List<Boolean> isClicks = new ArrayList<>();
    private HashMap<Integer,Boolean> positionMap;
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    // 删除计算接口
    public interface DeletOnItemClickListener {
        void onItemDelete(ArrayList<PersonInfoList> xhlistdata, int position);
    }

    private DeletOnItemClickListener listener;

    public void setDeteOnItemClickListener(DeletOnItemClickListener listener) {
        this.listener = listener;
    }

    private LayoutInflater mInflater;
    private Context context;
    private int mSelectedPos = 0;//实现单选  变量保存当前选中的position
    public NHListAdapter(Context context, ArrayList<PersonInfoList> xhlistdata, RecyclerView view) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.xhlistdata = xhlistdata;
        this.recyclerView =view;
        positionMap = new HashMap<>();
        for(int i=0;i<xhlistdata.size();i++){
            positionMap.put(i,false);
            if (xhlistdata.get(i).ischecked) {
                mSelectedPos = i;
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        android.widget.CheckBox select_btn;
        TextView username, sexidimg, icdcard, memberNO, familySysno, select_btns;
    }

    @Override
    public int getItemCount() {
        return xhlistdata.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = mInflater.inflate(R.layout.dialog_item_nh,
                viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.select_btn = (android.widget.CheckBox) view.findViewById(R.id.select_btn);
        viewHolder.username = (TextView) view.findViewById(R.id.nx_name);
        viewHolder.icdcard = (TextView) view.findViewById(R.id.nh_idcard);
        viewHolder.memberNO = (TextView) view.findViewById(R.id.chengyuanbianma);
        viewHolder.familySysno = (TextView) view.findViewById(R.id.jiatingbianma);
        viewHolder.sexidimg = (TextView) view.findViewById(R.id.sexidimg);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.username.setText(xhlistdata.get(i).getName());
        viewHolder.icdcard.setText(xhlistdata.get(i).getIdcardNo());
        viewHolder.memberNO.setText(xhlistdata.get(i).getMemberNO());
        viewHolder.familySysno.setText(xhlistdata.get(i).getFamilySysno());
        if (xhlistdata.get(i).getSexId().equals("1")) {
            viewHolder.sexidimg.setBackgroundResource(R.mipmap.ic_boy);
        } else {
            viewHolder.sexidimg.setBackgroundResource(R.mipmap.ic_girl);
        }
//如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    for(int i=0;i<xhlistdata.size();i++){
//                        positionMap.put(i,false);
//                        viewHolder.select_btn.setChecked(false);
//                        notifyDataSetChanged();
//                    }
//                    viewHolder.select_btn.setChecked(true);
//                    positionMap.put(i,true);
//                    notifyItemChanged(i);
//                    notifyDataSetChanged();//一定要刷新，否则会出现多选现
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                    ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForLayoutPosition(mSelectedPos);
                    if (holder != null) {
                        holder.select_btn.setChecked(false);
                    } else {
                        notifyItemChanged(mSelectedPos);
                    }
                    xhlistdata.get(mSelectedPos).setIschecked(false);//上次选中的条目，设置为false；
                    //更新默认选中的position；
                    mSelectedPos = i;
                    //最后设置要选中的那项；
                    xhlistdata.get(mSelectedPos).setIschecked(true);
                    viewHolder.select_btn.setChecked(true);

//  341723200803097247



                }
            });

        }

    }

}
