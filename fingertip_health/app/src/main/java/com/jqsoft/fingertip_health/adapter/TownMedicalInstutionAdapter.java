package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.TownLevelMedicalInstitutionBeanList;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingqiang on 2017/1/10.
 */

public class TownMedicalInstutionAdapter extends RecyclerView.Adapter<TownMedicalInstutionAdapter.ViewHolder> {
    // public ArrayList<String> datas = null;

    // public PeopleListBean datas;
    private Context mContext;
    private List<TownLevelMedicalInstitutionBeanList> data;


    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public TownMedicalInstutionAdapter(Context mContext, List<TownLevelMedicalInstitutionBeanList> data) {
        this.data = data;
        this.mContext = mContext;
    }

    //    public static final int TYPE_HEADER = 0;
    public static final int HEADER_ONE = 1;
    public static final int HEADER_TWO = 2;

    public static final int TYPE_NORMAL = 0;

    private ArrayList<View> mHeaderViews = new ArrayList<>(); // 所有的头布局
    private List<Integer> sHeaderTypes = new ArrayList<>();//每个header必须有不同的type,不然滚动的时候顺序会变化


    public boolean isHeader(int position) {
        if (position < mHeaderViews.size())
            return true;
        return false;
    }

    public void setmHeaderViews(ArrayList<View> mHeaderViews) {
        this.mHeaderViews = mHeaderViews;
    }

    public void setsHeaderTypes(List<Integer> sHeaderTypes) {
        this.sHeaderTypes = sHeaderTypes;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderViews == null || mHeaderViews.size() <= 0)
            return TYPE_NORMAL;
        if (isHeader(position))
            return sHeaderTypes.get(position);
        return TYPE_NORMAL;
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (mHeaderViews != null && sHeaderTypes.contains(viewType))
            return new ViewHolder(getHeaderViewByType(viewType));
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_town_level_medical_institution_directory_layout, viewGroup, false);


        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (isHeader(position)) {
        } else {

            viewHolder.tv_medical_name.setText(data.get(position).orgName);
            viewHolder.tv_medical_number.setText("管辖" + data.get(position).count + "个村室医疗机构");

            if (mOnItemClickLitener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                    }
                });

            }

            final String phoneNumber = Util.trimString(data.get(position).getDocPhone());
            viewHolder.tv_medical_phone.setText(phoneNumber);
            if (TextUtils.isEmpty(phoneNumber)){
                viewHolder.tv_medical_phone.setVisibility(View.GONE);
            } else {
                viewHolder.tv_medical_phone.setVisibility(View.VISIBLE);
            }

            viewHolder.iv_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.dial(mContext, phoneNumber);
                   /* if (!StringUtils.isBlank(data.get(position).)){
                        AppUtils.actionDial(dialView.getContext(),
                                phoneNumber);
                    } else {
                        Util.showToast(dialView.getContext(),
                                Constants.HINT_PHONE_NUMBER_EMPTY);
                    }*/
                }
            });


        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mHeaderViews == null ? data.size() : data.size() + getHeaderCount();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_medical_name;
        public TextView tv_medical_phone;
        public TextView tv_medical_number;
        public ImageView iv_phone;


        public ViewHolder(View view) {
            super(view);
            tv_medical_name = (TextView) view.findViewById(R.id.tv_medical_name);
            tv_medical_phone = (TextView) view.findViewById(R.id.tv_medical_phone);
            tv_medical_number = (TextView) view.findViewById(R.id.tv_medical_number);
            iv_phone = (ImageView) view.findViewById(R.id.iv_phone);


        }
    }

    private boolean isHeaderType(int type) {
        return mHeaderViews.size() > 0 && sHeaderTypes.contains(type);
    }

    private View getHeaderViewByType(int itemType) {
        if (!isHeaderType(itemType))
            return null;
        return mHeaderViews.get(itemType - TYPE_NORMAL - 1);
    }
}