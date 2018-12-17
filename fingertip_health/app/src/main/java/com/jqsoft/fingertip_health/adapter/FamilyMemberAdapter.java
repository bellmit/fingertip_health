package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.resident.FamilyMemberBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


public class FamilyMemberAdapter extends BaseQuickAdapterEx<FamilyMemberBean, BaseViewHolder> {
    private Context context;

    public FamilyMemberAdapter(List<FamilyMemberBean> data) {
        super(R.layout.item_family_member_layout, data);
//        this.context=context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final FamilyMemberBean item) {
        helper.setText(R.id.tv_name, Util.trimString(item.getMemberName()));
        String relationshipString = Util.trimString(item.getRelationshipRepresentation());
        helper.setText(R.id.tv_relationship,  relationshipString);
        helper.setText(R.id.tv_id_card_number, Util.trimString(item.getMemberCardNo()));

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
