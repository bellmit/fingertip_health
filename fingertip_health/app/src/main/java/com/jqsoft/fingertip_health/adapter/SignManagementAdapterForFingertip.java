package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.SignManagementItemBeanForFingertip;

import java.util.List;


public class SignManagementAdapterForFingertip extends BaseQuickAdapterEx<SignManagementItemBeanForFingertip, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;

    public SignManagementAdapterForFingertip(List<SignManagementItemBeanForFingertip> data) {
        super(R.layout.item_sign_management_layout, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final SignManagementItemBeanForFingertip item) {
        helper.setImageResource(R.id.iv_icon, item.getIconId());
        helper.setText(R.id.tv_title, item.getTitle());
    }


}
