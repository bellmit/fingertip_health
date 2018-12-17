package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//政策新闻适配器
public class UrbanLowFujianJiuzhuxiangAdapter extends BaseQuickAdapterEx<UrbanLowFujianBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public UrbanLowFujianShenqingshuAdapter myAdapter;
/*
    public  FullyLinearLayoutManager fullyLinearLayoutManager1;
*/

    DeleteListener mDeleteListener;
    private UrbanLowFamilyFragmentPresenter mPresenter;
    private String mybatchNo;
   // BaseQuickAdapter<UrbanLowFujianBean.Jiuzhuxiang, BaseViewHolder> myAdapter = new UrbanLowFujianShenqingshuAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang>(), TYPE_MULTIPLE_LINE,context);

    public UrbanLowFujianShenqingDetailAdapter myAdapter2;
    public  FullyLinearLayoutManager fullyLinearLayoutManager2;


    public interface DeleteListener {
        public void onDeleteClick(String s);
    }

    public void setOnDeleteClickListener (DeleteListener  DeleteListener) {
        this.mDeleteListener = DeleteListener;
    }

    public UrbanLowFujianJiuzhuxiangAdapter(List<UrbanLowFujianBean> data, int type,Context context,UrbanLowFujianShenqingshuAdapter myAdapter,FullyLinearLayoutManager fullyLinearLayoutManager1,
                                            UrbanLowFamilyFragmentPresenter mPresenter ,String mybatchNo) {
        super(R.layout.item_urbanlow_fujian_jiuzhuxiang, data);
        this.type = type;
        this.context=context;
        this.myAdapter=myAdapter;
      //  this.fullyLinearLayoutManager1=fullyLinearLayoutManager1;


        this.mPresenter=mPresenter;
        this.mybatchNo=mybatchNo;
       // this.fullyLinearLayoutManager2=fullyLinearLayoutManager2;


    }

//    private int getLayoutId(){
//        int result = R.layout.item_policy_single_line;
//        if (type == TYPE_SINGLE_LINE){
//            result=R.layout.item_policy_single_line;
//        } else if (type == TYPE_MULTIPLE_LINE){
//            result=R.layout.item_policy_multiple_line;
//        }
//        return result;
//    }

    @Override
    protected void convert(final BaseViewHolder helper, final UrbanLowFujianBean item) {

        helper.setText(R.id.tv_name, Util.trimString(item.getItemName()));

        final RecyclerView ls_shenqingxiang = helper.getView(R.id.ls_shenqingxiang);

        FullyLinearLayoutManager fullyLinearLayoutManager1 = new FullyLinearLayoutManager(context);
        fullyLinearLayoutManager1.setSmoothScrollbarEnabled(true);
        fullyLinearLayoutManager1.setAutoMeasureEnabled(true);



        ls_shenqingxiang.setLayoutManager(fullyLinearLayoutManager1);
        ls_shenqingxiang.setHasFixedSize(true);
        ls_shenqingxiang.setNestedScrollingEnabled(false);

        Util.addDividerToRecyclerView(context, ls_shenqingxiang, false);
        ls_shenqingxiang.setAdapter(myAdapter);
        myAdapter.setNewData(item.getFiles());
     //   myAdapter.setData(i,item.getFiles());
        myAdapter.saveItemid(item.getItemId());
        myAdapter.notifyDataSetChanged();


        //  helper.setText(R.id.tv_idcard, Util.trimString(item.getCardNo()));

       /* final Button btn_bianji = helper.getView(R.id.btn_bianji);

        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDeleteClick(Util.trimString(item.getId()));

            }
        });*/
        /*String imageUrl = item.getMessageFirstImgSrc();
        ImageView imageView = helper.getView(R.id.iv_image);
        imageView.setTag(R.id.imageId, imageUrl);
        String url = (String) imageView.getTag(R.id.imageId);
        if (url!=null && url.equals(imageUrl)) {
            GlideUtils.loadImageWithPlaceholderAndError(imageView, imageUrl, R.mipmap.i_read_id_card, R.mipmap.i_read_id_card);
        }

        helper.setText(R.id.tv_content, Util.trimString(item.getTitle()));
        String createDate = Util.trimString(item.getReleaseTime());
        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
        helper.setText(R.id.tv_date,  processedCreateDate);*/

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
