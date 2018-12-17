package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.response.FriendResultBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class HeyibanAdapter extends BaseQuickAdapterEx<FriendResultBean,BaseViewHolder> {
    private Context context;
    public HeyibanAdapter(List<FriendResultBean> data) {
        super(R.layout.item_heyiban,data);
//        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final FriendResultBean item) {
//        if (helper.getPosition() % 2 == 0) {
//            DensityUtil.setViewMargin(helper.itemView, false, 0, 0, 0, 40);
//        } else {
//            DensityUtil.setViewMargin(helper.itemView, false, 5, 0, 0, 40);
//        }
//        helper.setOnClickListener(R.id.iv_item_lock, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MaterialDialog dialog = new MaterialDialog.Builder(context)
//                        .title(R.string.hint_suggestion)
//                        .content(R.string.hint_whether_lock_hospital)
//                        .positiveText(R.string.confirm)
//                        .negativeText(R.string.cancel)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                            }
//                        }).build();
//                dialog.show();
//            }
//        });
        helper.setImageResource(R.id.iv_doctor, R.mipmap.person);
        helper.setText(R.id.tv_doctor_name, Util.trimString(item.getRealName()));
        helper.setText(R.id.tv_department_name, Util.trimString(item.getArea()));
//        GlideUtils.loadImage(3,item.getImgsrc(), (ImageView) helper.getView(R.id.iv_item_top_news));
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
