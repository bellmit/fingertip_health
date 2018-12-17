package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.HouseHoldSeveryBean;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


/**
 * 签约服务评价适配器
 */
public class HouseholdeServeryAdapter extends BaseQuickAdapterEx<HouseHoldSeveryBean, BaseViewHolder> {
    private Context context;

    public HouseholdeServeryAdapter(List<HouseHoldSeveryBean> data, Context context) {
        super(R.layout.item_servery_houselist, data);
        this.context = context;
    }

    DeleteListener mDeleteListener;

    public interface DeleteListener {
        public void onDeleteClick(String s);
    }

    public void setOnDeleteClickListener(DeleteListener DeleteListener) {
        this.mDeleteListener = DeleteListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HouseHoldSeveryBean item) {

//        String photoUrl = Util.trimString(item.getFile());
//        String imageUrl = Version.FIND_FILE_URL_BASE+photoUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_title));
        helper.setText(R.id.responsname, Util.trimString(item.getDIAOCHARENNAME()));
        helper.setText(R.id.householddata, Util.trimString(item.getDIAOCHADATE()));
        helper.setText(R.id.householdresult, Util.trimString(item.getDIAOCHAJIELUN()));
//        helper.setText(R.id.re_sex, Util.trimString(item.getSEX()));
//        helper.setText(R.id.re_bithday, Util.trimString(item.getDDATE()));
//        helper.setText(R.id.tv_riqi, Util.trimString(item.getEditDate()));
//
//        final  TextView tv_state = helper.getView(R.id.tv_state);
//        final Button btn_del = helper.getView(R.id.btn_del);
//        btn_del.setVisibility(View.GONE);
//        String state =Util.trimString(item.getStatus());
//        if(state.equals("0")){
//            tv_state.setText("暂存");
//            tv_state.setBackgroundResource(R.drawable.semi_round_zancun);
//            btn_del.setVisibility(View.VISIBLE);
//        }else if(state.equals("1")){
//            tv_state.setText("待办");
//            tv_state.setBackgroundResource(R.drawable.semi_round_daiban);
//            btn_del.setVisibility(View.GONE);
//        }else if(state.equals("2")){
//            tv_state.setText("处理中");
//            tv_state.setBackgroundResource(R.drawable.semi_round_chulizhong);
//            btn_del.setVisibility(View.GONE);
//        }else if(state.equals("3")){
//            tv_state.setText("已处理");
//            tv_state.setBackgroundResource(R.drawable.semi_round_yichuli);
//            btn_del.setVisibility(View.GONE);
//        }else{
//
//        }
//
//        btn_del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDeleteListener.onDeleteClick(Util.trimString(item.getBatchNo()));
//
//            }
//        });
/*
        helper.setText(R.id.tv_package_name, Util.trimString(item.getFwmc()));
        String packageLevel = Util.trimString(item.getServiceClassName());
        String packageLevelRepresentation = Util.getPackageLevelStringFromPackageLevel(packageLevel);
//        String packageLevelRepresentation = Util.trimString(item.getServiceClassName());
        helper.setText(R.id.tv_package_level, packageLevelRepresentation);
        if (StringUtils.isBlank(packageLevelRepresentation)){
            helper.setVisible(R.id.tv_package_level, false);
        } else {
            helper.setVisible(R.id.tv_package_level, true);
        }
        int packageLevelBackgroundId = Util.getBackgroundResourceIdFromPackageLevel(packageLevel);
        TextView packageLevelTextView = helper.getView(R.id.tv_package_level);
        packageLevelTextView.setBackgroundResource(packageLevelBackgroundId);
        float ratio = Util.getProgressFromTwoString(item.getHadExecCount(), item.getShouldExecCount());
        DonutProgressView dpv = helper.getView(R.id.dpv_progress);
        dpv.setPercentageValue(ratio);

        helper.setText(R.id.tv_service_project, Util.trimString(item.getXmmc()));
        String executionTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getAddDT()));
        helper.setText(R.id.tv_execution_time, executionTime);

        String level = Util.trimString(item.getEvaluation());
        helper.setText(R.id.tv_assess_level, Util.getStarLevelStringFromLevel(level));
        String assessTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getEvaluationTime()));
        helper.setText(R.id.tv_assess_time, assessTime);*/
        /*final View dialView = helper.getView(R.id.iv_dial);
        dialView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
               *//* String phoneNumber = Util.trimString(Constants.EMPTY_STRING);
                if (!StringUtils.isBlank(phoneNumber)){
                    AppUtils.actionDial(dialView.getContext(), phoneNumber);
                } else {
                    Util.showToast(dialView.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
                }*//*
            }
        });*/
//        RxView.clicks(dialView)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        Util.showToast(dialView.getContext(), "dial click");
//                    }
//                });

      /*  String photoUrl = Util.trimString(item.getPhotoUrl());
        String imageUrl = Version.FILE_URL_BASE+photoUrl;

        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_age, Util.trimString(item.getAge()+Constants.YEARS_OLD));
        helper.setText(R.id.tv_package_name, Util.trimString(item.getFwmc()));
        String packageLevel = Util.trimString(item.getServiceClassName());
        String packageLevelRepresentation = Util.getPackageLevelStringFromPackageLevel(packageLevel);
//        String packageLevelRepresentation = Util.trimString(item.getServiceClassName());
        helper.setText(R.id.tv_package_level, packageLevelRepresentation);
        if (StringUtils.isBlank(packageLevelRepresentation)){
            helper.setVisible(R.id.tv_package_level, false);
        } else {
            helper.setVisible(R.id.tv_package_level, true);
        }
        int packageLevelBackgroundId = Util.getBackgroundResourceIdFromPackageLevel(packageLevel);
        TextView packageLevelTextView = helper.getView(R.id.tv_package_level);
        packageLevelTextView.setBackgroundResource(packageLevelBackgroundId);
        float ratio = Util.getProgressFromTwoString(item.getHadExecCount(), item.getShouldExecCount());
        DonutProgressView dpv = helper.getView(R.id.dpv_progress);
        dpv.setPercentageValue(ratio);

        helper.setText(R.id.tv_service_project, Util.trimString(item.getXmmc()));
        String executionTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getAddDT()));
        helper.setText(R.id.tv_execution_time, executionTime);

        String level = Util.trimString(item.getEvaluation());
        helper.setText(R.id.tv_assess_level, Util.getStarLevelStringFromLevel(level));
        String assessTime = Util.getYearMonthDayFromFullString(Util.trimString(item.getEvaluationTime()));
        helper.setText(R.id.tv_assess_time, assessTime);

        String content = Util.trimString(item.getEvaluationNote());
//        String content = "形散神聚：”形散“既指题材广泛、写法多样，又指结构自由、不拘一格；“神聚”既指中心集中，又指有贯穿全文的线索。散文写人写事都只是表面现象，从根本上说写的是情感体验。情感体验就是“不散的神”，而人与事则是“散”的可有可无、可多可少的“形”。";
        helper.setText(R.id.tv_assess_content, content);

        String readStatus = item.getEvaluationState();
        if (Constants.SIGN_SERVICE_ASSESS_TYPE_NEW.equals(readStatus)){
            helper.setVisible(R.id.view_dash_line, true);
            helper.setVisible(R.id.ll_read, true);
            View readView = helper.getView(R.id.ll_read);
            readView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    super.onNoDoubleClick(v);
                    RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_SERVICE_ASSESS_DID_CLICK_READ_BUTTON, item);
                }
            });
        } else {
            helper.setVisible(R.id.view_dash_line, false);
            helper.setVisible(R.id.ll_read, false);
            View readView = helper.getView(R.id.ll_read);
            readView.setOnClickListener(null);
        }*/


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
