package com.jqsoft.fingertip_health.di.ui.onlinesignadapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class SignSeverPakesAdapter extends BaseQuickAdapterEx<SignSeverPakesBeanList, BaseViewHolder> {
    private Context context;

    public SignSeverPakesAdapter(List<SignSeverPakesBeanList> data) {
        super(R.layout.item_select_serverpake, data);
    //    this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignSeverPakesBeanList item) {
//        helper.setText(R.id.servertitle, Util.trimString(item.getName()));
//        helper.setText(R.id.servertitle, Util.trimString(item.getName()));

    }


}
