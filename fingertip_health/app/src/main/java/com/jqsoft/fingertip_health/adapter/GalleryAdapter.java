package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;

/**
 * Created by YLL on 2017-7-5.
 */

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{



    /**
     * ItemClick的回调接口
     * @author zhy
     *
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



    private LayoutInflater mInflater;
    private List<String> mDatas;

    public GalleryAdapter(Context context, List<String> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        TextView mTxt;
        RelativeLayout rl_image;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.item_gallery_pack,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.rl_image = (RelativeLayout) view.findViewById(R.id.rl_image);

        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.tv_pack);


        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
      //  viewHolder.mTxt.setImageResource(mDatas.get(i));
        viewHolder.mTxt.setText(mDatas.get(i));

        String servercontent = Util.trimString(mDatas.get(i));
        if(!TextUtils.isEmpty(servercontent)){
            if(servercontent.indexOf("高级")!=-1){

            }else if(servercontent.indexOf("中级")!=-1){

            }else if(servercontent.indexOf("初级")!=-1){

            }else{

            }
        }else{

        }



        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                }
            });

        }


    }

}
