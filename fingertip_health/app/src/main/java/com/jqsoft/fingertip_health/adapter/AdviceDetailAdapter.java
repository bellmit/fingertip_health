package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.RepliesBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.RepliesAndConsult;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceDetailActivity;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceHadDetailActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//列表适配器
public class AdviceDetailAdapter extends BaseQuickAdapterEx<RepliesBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private String filePath;
    private int type=TYPE_MULTIPLE_LINE;
    private String qusid="";
    private String username="";
    private Context context;
    private String  replyUnitCode;
    private String isreply;

    public AdviceDetailAdapter(List<RepliesAndConsult> data, int type, Context context, String isreply,String replyUnitCode) {
//        super(R.layout.item_advicedetail_line, data);
        super(isreply.equals("3")?R.layout.item_advicedetail_line:
                isreply.equals("1")?R.layout.item_advicedetail_turn_line:
                        R.layout.item_advicedetail_noreply_line, data);

        this.type = type;
        this.context=context;
        this.isreply=isreply;
        this.replyUnitCode=replyUnitCode;




    }


    @Override
    protected void convert(final BaseViewHolder helper, final RepliesBean item) {


        try {

            helper.setText(R.id.replyTime, Util.trimString( item.getReplyTime().replace('T',' ')));
            helper.setText(R.id.replyUnit, Util.trimString(item.getReplyUnit()));
            helper.setText(R.id.replycontent, Util.trimString(item.getReplyContent()));
            final LinearLayout ll_remove = helper.getView(R.id.ll_remove);
            String area_level= IdentityManager.getAreaLevel(context);
            if(area_level==null){

            }else {

                if(area_level.equals("area_1") || area_level.equals("area_2")){
                    ll_remove.setVisibility(View.GONE);
                }

                else {
                    if (replyUnitCode.equals(item.getReplyUnitCode())){
                        ll_remove.setVisibility(View.VISIBLE);
                    }else {
                        ll_remove.setVisibility(View.GONE);
                    }

                }
            }


            ll_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isreply.equals("3")){
                        AdviceHadDetailActivity adviceHadDetailActivity=(AdviceHadDetailActivity)context;
                        adviceHadDetailActivity.remove( Identity.srcInfo.getUnitCode());
                    }else {
                        AdviceDetailActivity adviceDetailActivity=(AdviceDetailActivity) context;
                        adviceDetailActivity.remove( Identity.srcInfo.getUnitCode());
                    }


                }
            });
//            helper.setOnClickListener(R.id.ll_remove, new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String  a="0";
//
//                }
//            });

        }catch (Exception e1) {
            e1.printStackTrace();
        }

        if (item.getIsTurn().equals("1")){
            helper.setText(R.id.tv_title2, "转办信息: ");
            helper.setText(R.id.tv_danwei, "转办单位: ");
            helper.setText(R.id.tv_content, "转办内容: ");
        }else if (item.getIsTurn().equals("0")){
            helper.setText(R.id.tv_title2, "回复信息: ");
            helper.setText(R.id.tv_danwei, "回复单位: ");
            helper.setText(R.id.tv_content, "回复内容: ");
        }



        if (isreply.equals("3")){
            final LinearLayout ll_remove = helper.getView(R.id.ll_remove);
            AdviceHadDetailActivity adviceHadDetailActivity=( AdviceHadDetailActivity)context;

            if(item.getIsTurn().equals("1")){
                if ( adviceHadDetailActivity.checkisturn().equals("true")){
                    ll_remove.setVisibility(View.GONE);
                }else{
                    ll_remove.setVisibility(View.VISIBLE);
                }
            }


        }else {
//            final LinearLayout ll_remove = helper.getView(R.id.ll_remove);
//            AdviceDetailActivity adviceDetailActivity=(AdviceDetailActivity) context;
//
//            if(item.getIsTurn().equals("1")){
//                if ( adviceDetailActivity.checkisturn().equals("true")){
//                    ll_remove.setVisibility(View.GONE);
//                }else{
//                    ll_remove.setVisibility(View.VISIBLE);
//                }
//            }



        }

//        String createDate = Util.trimString(item.getReleaseTime());
//        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
//            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
//        helper.setText(R.id.tv_date,  processedCreateDate);

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }



}
