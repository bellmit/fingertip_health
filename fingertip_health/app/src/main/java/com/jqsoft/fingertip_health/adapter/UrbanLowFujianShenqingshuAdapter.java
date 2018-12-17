package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianFragment;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;


//政策新闻适配器
public class UrbanLowFujianShenqingshuAdapter extends BaseQuickAdapterEx<UrbanLowFujianBean.Jiuzhuxiang, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;
    private String mybatchNo;

    private UrbanFuJianFragment urbanFuJianFragment;

    private  BaseQuickAdapter<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang, BaseViewHolder> myDetailAdapter;

    //private UrbanLowFujianShenqingDetailAdapter myDetailAdapter;


    DeleteListener mDeleteListener;

    public interface DeleteListener {
        public void onDeleteClick(String s);
    }

    public void setOnDeleteClickListener (DeleteListener  DeleteListener) {
        this.mDeleteListener = DeleteListener;
    }



    public String itemid;

    PaizhaoListener mPaizhaoListener;

    public UrbanLowFujianShenqingDetailAdapter myAdapter2;
    public  FullyLinearLayoutManager fullyLinearLayoutManager1;
    private UrbanLowFamilyFragmentPresenter mPresenter;;

    public interface PaizhaoListener {
        public void onPaizhaoClick(String s,String itemid);
    }

    public void setOnPaizhaoClickListener (PaizhaoListener  paizhaoListener) {
        this.mPaizhaoListener = paizhaoListener;
    }

    public UrbanLowFujianShenqingshuAdapter(List<UrbanLowFujianBean.Jiuzhuxiang> data, int type,Context context,
             FullyLinearLayoutManager fullyLinearLayoutManager1, UrbanLowFamilyFragmentPresenter mPresenter,String mybatchNo,UrbanFuJianFragment urbanFuJianFragment ) {
        super(R.layout.item_urbanlow_fujian_shenqingxiang, data);
        this.type = type;
        this.context=context;


        this.fullyLinearLayoutManager1=fullyLinearLayoutManager1;
        this.mPresenter=mPresenter;
        this.mybatchNo=mybatchNo;
        this.urbanFuJianFragment=urbanFuJianFragment;

    }


    @Override
    protected void convert(final BaseViewHolder helper, final UrbanLowFujianBean.Jiuzhuxiang item) {

        helper.setText(R.id.tv_name, Util.trimString(item.getFileCodeName()));


        final RecyclerView rv_shengqingdetail = helper.getView(R.id.rv_shengqingdetail);

       myDetailAdapter = new UrbanLowFujianShenqingDetailAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang>(), TYPE_MULTIPLE_LINE,context,mPresenter,item,mybatchNo, urbanFuJianFragment);

        myDetailAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //       mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
       /* FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(context);
        fullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        fullyLinearLayoutManager.setAutoMeasureEnabled(true);*/
        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(context);
        fullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        fullyLinearLayoutManager.setAutoMeasureEnabled(true);
        rv_shengqingdetail.setLayoutManager(fullyLinearLayoutManager);
    //    rv_shengqingdetail.setHasFixedSize(true);
     //   rv_shengqingdetail.setNestedScrollingEnabled(false);

    //    rv_shengqingdetail.setLayoutManager(new FullyLinearLayoutManager(context));
        Util.addDividerToRecyclerView(context, rv_shengqingdetail, false);
        rv_shengqingdetail.setAdapter(myDetailAdapter);
        myDetailAdapter.setNewData(item.getDetails());
        myDetailAdapter.notifyDataSetChanged();




        final ImageView iv_photo = helper.getView(R.id.iv_photo);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPaizhaoListener.onPaizhaoClick(Util.trimString(item.getFileCode()),itemid);
            }
        });
    }

    public void saveItemid(String itemId){
        this.itemid=itemId;
    }

    public  void dianji(){
      /*  myDetailAdapter.setOnDeleteNewClickListener(new UrbanLowFujianShenqingDetailAdapter.DeleteNewListener() {
            @Override
            public void onDeleteNewClick(String s) {
                String s2 =s;
                Toast.makeText(getActivity(),s2+"啦",Toast.LENGTH_SHORT).show();
            }


        });

        myDetailAdapter.setOn*/
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
