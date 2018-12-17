package com.jqsoft.fingertip_health.di.ui.onlinesignadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.util.Util;

import net.qiujuer.genius.ui.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingqiang on 2017/1/10.
 */

public class SignDoctorServersAdapter extends RecyclerView.Adapter<SignDoctorServersAdapter.ViewHolder> {
    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
    private Context mContext;
  //  private List<String> data;
    private List<Boolean> isClicks;
    private String tabtype;

    public SignDoctorServersAdapter(Context mContext, ArrayList<SignSeverPakesBeanList> datalist) {
     //   this.data = data;
        this.datalist=datalist;
        this.mContext = mContext;
        isClicks = new ArrayList<>();
        for(int i = 0;i<datalist.size();i++){
            isClicks.add(false);
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
        void OnItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;


    }
//    public static final int TYPE_HEADER = 0;
    public static final int HEADER_ONE = 1;
    public static final int HEADER_TWO = 2;

    public static final int TYPE_NORMAL = 0;

    private ArrayList<View> mHeaderViews = new ArrayList<>(); // 所有的头布局
    private List<Integer> sHeaderTypes = new ArrayList<>();//每个header必须有不同的type,不然滚动的时候顺序会变化


    public boolean isHeader(int position){
        if(position<mHeaderViews.size())
            return true;
        return false;
    }

    public void setmHeaderViews(ArrayList<View> mHeaderViews) {
        this.mHeaderViews = mHeaderViews;
    }

    public  void setsHeaderTypes(List<Integer> sHeaderTypes) {
        this.sHeaderTypes = sHeaderTypes;
    }




    @Override
    public int getItemViewType(int position) {
        if(mHeaderViews==null || mHeaderViews.size() <= 0)
            return TYPE_NORMAL;
        if(isHeader(position))
            return sHeaderTypes.get(position);
        return TYPE_NORMAL;
    }

    public int getHeaderCount(){
        return mHeaderViews.size();
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(mHeaderViews!=null && sHeaderTypes.contains(viewType))
            return new ViewHolder(getHeaderViewByType(viewType));
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_serverpake,viewGroup,false);


        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if(isHeader(position)){
            // TODO
        }else {
            //position要重新校验
            final int adjPosition = position - getHeaderCount();

            if(mOnItemClickListener!=null){
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.OnItemClick(viewHolder.itemView,position);

                    }
                });
                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.OnItemLongClick(viewHolder.itemView,position);
                        return false;
                    }
                });
            }

//        nhcompensateProjName （1:基础包 2:初级包 3: 中级包 4:高级包 5 其他）
//        fwmc服务包名称
//        fwnr服务内容
//        sydx适用对象
//        hjnysfje总收入（总金额）
//        xnhbcje新农合应予补偿金额
//        qtjmje其他减免金额
//        sjzfje实际自付金额
//        cdje基本公共卫生服务经费承担金额
//        serviceCententFee实收金额
//         key服务包主键
            String nhcompensateProjName = Util.getDecodedBase64String(datalist.get(position).getNhcompensateProjName());
            String fwmc = Util.getDecodedBase64String(datalist.get(position).getFwmc());
            String fwnr = Util.getDecodedBase64String(datalist.get(position).getFwnr());
            String hjnysfje = Util.getDecodedBase64String(datalist.get(position).getHjnysfje());
            String qtjmje = Util.getDecodedBase64String(datalist.get(position).getQtjmje());
            String xnhbcje = Util.getDecodedBase64String(datalist.get(position).getXnhbcje());
            String sjzfje = Util.getDecodedBase64String(datalist.get(position).getSjzfje());

                viewHolder.tvItem.setText(fwmc);
                viewHolder.tv_projnr.setText(fwnr);
                viewHolder.tv_money.setText("总金额:"+hjnysfje+"元; 减免金额:"+qtjmje+"元; 医保补偿金额:"+xnhbcje+"元; 自付金额:"+sjzfje+"元");

            viewHolder.select_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( viewHolder.select_btn.isChecked()){
                        datalist.get(position);
                        viewHolder.itemView.setBackgroundColor(Color.parseColor("#66CDAA"));
                        viewHolder.tvItem.setTextColor(Color.parseColor("#FFFFFF"));
                        viewHolder.tv_projnr.setTextColor(Color.parseColor("#FFFFFF"));
                        viewHolder.tv_money.setTextColor(Color.parseColor("#FFFFFF"));
                    }else{
                        viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        viewHolder.tvItem.setTextColor(Color.parseColor("#000000"));
                        viewHolder.tv_projnr.setTextColor(Color.parseColor("#9ba4a3"));
                        viewHolder.tv_money.setTextColor(Color.parseColor("#66CDAA"));
                    }


                }

            });

        }
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mHeaderViews == null ? datalist.size():datalist.size()+getHeaderCount();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem, paketype,tv_projnr, tv_money;
        public CheckBox select_btn;

        public ViewHolder(View view){
            super(view);
            tvItem = (TextView) view.findViewById(R.id.servertitle);
            paketype =(TextView)view.findViewById(R.id.paketype);
            tv_projnr = (TextView) view.findViewById(R.id.tv_projName);
            tv_money = (TextView) view.findViewById(R.id.tv_money);
            select_btn = (CheckBox)view.findViewById(R.id.select_btn);

        }
    }

    private boolean isHeaderType(int type){
        return mHeaderViews.size() > 0 && sHeaderTypes.contains(type);
    }

    private View getHeaderViewByType(int itemType){
        if(!isHeaderType(itemType))
            return null;
        return mHeaderViews.get(itemType - TYPE_NORMAL - 1);
    }
}