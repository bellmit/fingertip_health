package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.response.SignedResidentDirectoryResultBean;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.AppUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class SignedResidentDirectoryAdapter extends BaseQuickAdapterEx<SignedResidentDirectoryResultBean, BaseViewHolder> {
    private Context context;

    public SignedResidentDirectoryAdapter(List<SignedResidentDirectoryResultBean> data) {
        super(R.layout.item_signed_resident_directory_layout, data);
//        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignedResidentDirectoryResultBean item) {
        helper.setText(R.id.tv_name, Util.trimString(item.getUserName()));
        helper.setText(R.id.tv_address,  Util.trimString(item.getAddress()));
        final String phoneNumber = Util.trimString(item.getPhone());
        helper.setText(R.id.tv_phone_number, phoneNumber);
        final View dialView = helper.getView(R.id.iv_dial);
        dialView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                if (!StringUtils.isBlank(phoneNumber)){
                    AppUtils.actionDial(dialView.getContext(), phoneNumber);
                } else {
                    Util.showToast(dialView.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
                }

            }
        });

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
