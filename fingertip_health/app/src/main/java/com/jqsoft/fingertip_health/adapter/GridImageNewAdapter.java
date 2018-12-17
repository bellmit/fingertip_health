package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * author：luck
 * project：LeTuGolf
 * package：com.tongyu.luck.paradisegolf.adapter
 * email：893855882@qq.com
 * data：16/7/27
 */
public class GridImageNewAdapter extends
        RecyclerView.Adapter<GridImageNewAdapter.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private LayoutInflater mInflater;
    private List<LocalMedia> list = new ArrayList<>();
    private int selectMax = 9;
    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick(int type, int position);
    }

    public GridImageNewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

    }

    public GridImageNewAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;


    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setList(List<LocalMedia> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImg;

        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.fiv);

        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < selectMax) {
            return list.size() ;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.gv_filter_image_new,
                viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        //itemView 的点击事件
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), v);
                }
            });
        }
        return viewHolder;
    }

    private boolean isShowAddItem(int position) {
        int size = list.size() == 0 ? 0 : list.size();
        return position == size;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //少于8张，显示继续添加的图标

        if (getItemViewType(position) == TYPE_CAMERA) {
            if(list.size()==0){
                viewHolder.mImg.setVisibility(View.GONE);
            }else {
                viewHolder.mImg.setVisibility(View.VISIBLE);
            }
        } else {

            if(list.size()==0){
                viewHolder.mImg.setVisibility(View.GONE);
            }else {
                viewHolder.mImg.setVisibility(View.VISIBLE);
            }


            LocalMedia media = list.get(position);
            int type = media.getType();
            String path = "";
            if (media.isCompressed()) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            String url = media.getUrl();

            switch (type) {
                case 1:
                    // 图片



                    if (media.isCompressed()) {
                        Log.i("compress image result", new File(media.getCompressPath()).length() / 1024 + "k");
                        Log.i("原图地址::", media.getPath());
                        Log.i("压缩地址::", media.getCompressPath());
                    }

                    if(list.size()==0){
                        viewHolder.mImg.setVisibility(View.GONE);
                    }else {
                        if(media.getPath().equals("test")){

                            if(TextUtils.isEmpty(url) || url==null || url.equals("null")){
                                viewHolder.mImg.setVisibility(View.GONE);
                            }else {
                                viewHolder.mImg.setVisibility(View.VISIBLE);
                           //     String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";
                               /* String FIND_FILE_URL_BASE = Version.FILE_URL_BASE;
                                String imageUrl =FIND_FILE_URL_BASE+url;
                                media.setUrl(imageUrl);*/
                                GlideUtils.loadImageNew(url, (ImageView) viewHolder.mImg);
                            }



                        }else {
                           /* Glide.with(viewHolder.itemView.getContext())
                                    .load(path)
                                    .centerCrop()
                                    .placeholder(R.color.color_f6)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(viewHolder.mImg);*/

                        }

                    }



                    break;
                case 2:
                    // 视频
                    Log.i("时长:", media.getDuration() + "");
                    Glide.with(viewHolder.itemView.getContext()).load(path).thumbnail(0.5f).into(viewHolder.mImg);
                    break;
                default:

                    break;
            }

        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
