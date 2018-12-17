package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultItemBean;
import com.jqsoft.fingertip_health.content.ProjectExecutionItemContent;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.view.DonutProgressView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by quantan.liu on 2017/3/27.
 */

public class ExecutionProjectsAdapter extends BaseQuickAdapterEx<ExecutionProjectsResultBean, BaseViewHolder> {
    private Context context;

    public ExecutionProjectsAdapter(List<ExecutionProjectsResultBean> data) {
        super(R.layout.item_execution_projects, data);
//        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ExecutionProjectsResultBean item) {
//        if (helper.getPosition() % 2 == 0) {
//            DensityUtil.setViewMargin(helper.itemView, false, 0, 0, 0, 40);
//        } else {
//            DensityUtil.setViewMargin(helper.itemView, false, 5, 0, 0, 40);
//        }
        final View dialView = helper.getView(R.id.iv_dial);
//        dialView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                String phoneNumber = Util.trimString(item.getPhone());
//                if (!StringUtils.isBlank(phoneNumber)){
//                    AppUtils.actionDial(dialView.getContext(), phoneNumber);
//                } else {
//                    Util.showToast(dialView.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
//                }
//            }
//        });
        RxView.clicks(dialView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        Util.dial(dialView.getContext(), Util.trimString(item.getPhone()));
//                        Util.showToast(dialView.getContext(), "dial click");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        item.setHeadUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        String photoUrl = Util.trimString(item.getPhotoUrl());
        String imageUrl = Version.FILE_URL_BASE+photoUrl;
        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_name, Util.trimString(item.getUserName()));
        helper.setText(R.id.tv_age, Util.trimString(item.getAge())+Constants.YEARS_OLD);
        helper.setText(R.id.tv_package_name, Util.trimString(item.getServerPackageName()));
        String packageLevel = Util.trimString(item.getNhcompensateProjName());
        String packageLevelRepresentation = Util.getPackageLevelStringFromPackageLevel(packageLevel);
        helper.setText(R.id.tv_package_level, packageLevelRepresentation);
        if (StringUtils.isBlank(packageLevelRepresentation)){
            helper.setVisible(R.id.tv_package_level, false);
        } else {
            helper.setVisible(R.id.tv_package_level, true);
        }
        int packageLevelBackgroundId = Util.getBackgroundResourceIdFromPackageLevel(packageLevel);
        TextView packageLevelTextView = helper.getView(R.id.tv_package_level);
        packageLevelTextView.setBackgroundResource(packageLevelBackgroundId);

//        helper.setText(R.id.tv_package_level, "中级");
//        double ratio = Util.getDoubleFromString(Util.trimString(item.getRatio()));
        String hadExecCount = Util.trimString(item.getHadExecCount());
        String shouldExecCount = Util.trimString(item.getShouldExecCount());
        float progress = Util.getProgressFromTwoString(hadExecCount, shouldExecCount);
        DonutProgressView dpv = helper.getView(R.id.dpv_progress);
        dpv.setPercentageValue(progress);

        List<ExecutionProjectsResultItemBean> list = item.getList();
        if (!ListUtils.isEmpty(list)) {
            LinearLayout llProjects = helper.getView(R.id.ll_projects);
            llProjects.removeAllViews();
            for (int i = 0; i < list.size(); ++i){
                ExecutionProjectsResultItemBean projectItem = list.get(i);
                projectItem.setParentBean(item);
                ProjectExecutionItemContent content = new ProjectExecutionItemContent(dpv.getContext());
                content.initView(projectItem);
                llProjects.addView(content.getView());
            }
        }


//        List<String> nextDateList = item.getNxetdate();
//        if (nextDateList!=null && nextDateList.size()>0) {
//            helper.setText(R.id.tv_round_visit_next_execution_date, Constants.HINT_NEXT_EXECUTION_DATE+Util.trimString(nextDateList.get(0)));
//        }
//        View homeRoundVisitButton = helper.getView(R.id.btn_round_visit_execute);
//        homeRoundVisitButton.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
//                        .title(R.string.hint_suggestion)
//                        .content(R.string.hint_confirm_to_execute)
//                        .positiveText(R.string.confirm)
//                        .negativeText(R.string.cancel)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                            }
//                        }).build();
//                dialog.show();
//
//            }
//        });
//        if (nextDateList!=null && nextDateList.size()>1) {
//            helper.setText(R.id.tv_blood_pressure_next_execution_date, Constants.HINT_NEXT_EXECUTION_DATE+Util.trimString(nextDateList.get(1)));
//        }
//        View bloodPressureButton = helper.getView(R.id.btn_blood_pressure_execute);
//        bloodPressureButton.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
//                        .title(R.string.hint_suggestion)
//                        .content(R.string.hint_confirm_to_execute)
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
