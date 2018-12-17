package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.response_new.SignClientServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceEvalution;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


/**
 * 签约服务评价适配器
 */
public class SignClientServiceAssessAdapter extends BaseQuickAdapterEx<SignClientServiceAssessResultBean, BaseViewHolder> {
    private Context context;

    public SignClientServiceAssessAdapter(List<SignClientServiceAssessResultBean> data, Context context) {
        super(R.layout.itemvaluation, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignClientServiceAssessResultBean item) {
        helper.setText(R.id.tv_pend_name, Util.trimString(item.getFwmc()));
        helper.setText(R.id.tv_server_item, "项目名称:" + Util.trimString(item.getServiceItemsName()));
        helper.setText(R.id.tv_execu_unit, "执行单位:" + Util.trimString(item.getExecOfficer()));
        try {
            helper.setText(R.id.tv_pend_execu, Util.trimString(item.getExecuteDT()).substring(0,19));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (item.getEvaluationState().equals("1")) {
            helper.setText(R.id.btn_execu, "已评价");
            //helper.setTextColor(R.id.btn_execu,ffF4D249);
        }else{
            helper.setText(R.id.btn_execu, "待评价");
        }

        String sort = item.getServiceClassSort();
        if (sort.equals("1")) {
            helper.setText(R.id.tv_pend_type, "基础");
            helper.setBackgroundColor(R.id.tv_pend_type, android.graphics.Color.parseColor("#F4D249"));
        } else if (sort.equals("2")) {
            helper.setText(R.id.tv_pend_type, "初级");
            helper.setBackgroundColor(R.id.tv_pend_type, android.graphics.Color.parseColor("#6ED1E0"));
        } else if (sort.equals("3")) {
            helper.setText(R.id.tv_pend_type, "中级");
            helper.setBackgroundColor(R.id.tv_pend_type, android.graphics.Color.parseColor("#7DD0FF"));
        } else {
            helper.setText(R.id.tv_pend_type, "高级");
            helper.setBackgroundColor(R.id.tv_pend_type, android.graphics.Color.parseColor("#FF8C5A"));
        }
        if (item.getExecOfficer().equals("3")) {
            helper.setText(R.id.tv_execu_unit, "执行单位:乡镇");
        } else {
            helper.setText(R.id.tv_execu_unit, "执行单位:村级");
        }


        final Button evalutebtn = helper.getView(R.id.btn_execu);
        evalutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Util.gotoActivity(context, SignServiceEvalution.class);
                Intent intent = new Intent(context, SignServiceEvalution.class);
                intent.putExtra("SignClientServiceAssessResultBean", item);
                context.startActivity(intent);
            }
        });
//        dialView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                String phoneNumber = Util.trimString(Constants.EMPTY_STRING);
//                if (!StringUtils.isBlank(phoneNumber)){
//                    AppUtils.actionDial(dialView.getContext(), phoneNumber);
//                } else {
//                    Util.showToast(dialView.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
//                }
//            }
//        });
////        RxView.clicks(dialView)
////                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
////                .subscribe(new Action1<Void>() {
////                    @Override
////                    public void call(Void aVoid) {
////                        Util.showToast(dialView.getContext(), "dial click");
////                    }
////                });
//
//        String photoUrl = Util.trimString(item.getPhotoUrl());
//        String imageUrl = Version.FILE_URL_BASE+photoUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
//        helper.setText(R.id.tv_name, Util.trimString(item.getName()));
//        helper.setText(R.id.tv_age, Util.trimString(item.getAge()+Constants.YEARS_OLD));
//        helper.setText(R.id.tv_package_name, Util.trimString(item.getFwmc()));
//        String packageLevel = Util.trimString(item.getServiceClassName());
//        String packageLevelRepresentation = Util.getPackageLevelStringFromPackageLevel(packageLevel);
////        String packageLevelRepresentation = Util.trimString(item.getServiceClassName());
//        helper.setText(R.id.tv_package_level, packageLevelRepresentation);
//        if (StringUtils.isBlank(packageLevelRepresentation)){
//            helper.setVisible(R.id.tv_package_level, false);
//        } else {
//            helper.setVisible(R.id.tv_package_level, true);
//        }
//        int packageLevelBackgroundId = Util.getBackgroundResourceIdFromPackageLevel(packageLevel);
//        TextView packageLevelTextView = helper.getView(R.id.tv_package_level);
//        packageLevelTextView.setBackgroundResource(packageLevelBackgroundId);
//        float ratio = Util.getProgressFromTwoString(item.getHadExecCount(), item.getShouldExecCount());
//        DonutProgressView dpv = helper.getView(R.id.dpv_progress);
//        dpv.setPercentageValue(ratio);
//
//        helper.setText(R.id.tv_service_project, Util.trimString(item.getXmmc()));
//        String executionTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getAddDT()));
//        helper.setText(R.id.tv_execution_time, executionTime);
//
//        String level = Util.trimString(item.getEvaluation());
//        helper.setText(R.id.tv_assess_level, Util.getStarLevelStringFromLevel(level));
//        String assessTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getEvaluationTime()));
//        helper.setText(R.id.tv_assess_time, assessTime);
//
//        String content = Util.trimString(item.getEvaluationNote());
////        String content = "形散神聚：”形散“既指题材广泛、写法多样，又指结构自由、不拘一格；“神聚”既指中心集中，又指有贯穿全文的线索。散文写人写事都只是表面现象，从根本上说写的是情感体验。情感体验就是“不散的神”，而人与事则是“散”的可有可无、可多可少的“形”。";
//        helper.setText(R.id.tv_assess_content, content);
//
//        String readStatus = item.getEvaluationState();
//        if (Constants.SIGN_SERVICE_ASSESS_TYPE_NEW.equals(readStatus)){
//            helper.setVisible(R.id.view_dash_line, true);
//            helper.setVisible(R.id.ll_read, true);
//            View readView = helper.getView(R.id.ll_read);
//            readView.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_SERVICE_ASSESS_DID_CLICK_READ_BUTTON, item);
//                }
//            });
//        } else {
//            helper.setVisible(R.id.view_dash_line, false);
//            helper.setVisible(R.id.ll_read, false);
//            View readView = helper.getView(R.id.ll_read);
//            readView.setOnClickListener(null);
//        }
//
//
////        helper.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
////            }
////        });

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
