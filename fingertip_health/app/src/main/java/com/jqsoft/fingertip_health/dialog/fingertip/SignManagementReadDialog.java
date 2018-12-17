package com.jqsoft.fingertip_health.dialog.fingertip;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignManagementReadAdapterForFingertip;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.SignManagementReadBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.SignManagementReadDialogContractForFingertip;
import com.jqsoft.fingertip_health.di.module.SignManagementReadListDialogModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.SignManagementReadListDialogPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.NewDoctorSignActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.dialog.base.BaseDialog;
import com.jqsoft.fingertip_health.feature.SignManagementReadSelectListener;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-06.
 */

public class SignManagementReadDialog extends BaseDialog  implements
        SignManagementReadDialogContractForFingertip.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.bt_search)
    Button btSearch;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;


    @BindView(R.id.lay_sign_management_read_list_load_failure)
    View failureView;
    TextView tvFailureView;

    private SignManagementReadAdapterForFingertip mAdapter;

    private boolean isRefresh = false;

    SignManagementReadSelectListener listener;

    @Inject
    SignManagementReadListDialogPresenter mPresenter;


    public SignManagementReadDialog(@NonNull Context context, SignManagementReadSelectListener listener) {
        super(context, R.style.white_background_dialog, R.layout.dialog_sign_management_read_list_layout);
        this.listener = listener;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getContext())
                .getAppComponent()
                .addSignManagementReadListDialogForFingertip(new SignManagementReadListDialogModuleForFingertip(this))
                .inject(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        Util.setViewListener(ivClose, new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        });

        Util.setViewListener(btSearch, new Runnable() {
            @Override
            public void run() {
                String keyword = getKeyword();
                if (StringUtils.isBlank(keyword)) {
                    Util.showToast(getContext(), "请输入姓名或身份证号再试");
                } else {
                    onRefresh();
                }
            }
        });

        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

        srl.setColorSchemeColors(getContext().getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<SignManagementReadBeanForFingertip, BaseViewHolder> mAdapter = new SignManagementReadAdapterForFingertip(
                listener
                , new ArrayList<SignManagementReadBeanForFingertip>());
        this.mAdapter = (SignManagementReadAdapterForFingertip) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        Activity activity = Util.scanForActivity(getContext());
        Util.addDividerToRecyclerView(activity, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.bindToRecyclerView(recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();

        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(PolicyActivity.this, position+" is clicked");
                SignManagementReadBeanForFingertip pb = mAdapter.getItem(position);
                didSelectOnePatient(pb);
            }
        });
    }

    private void didSelectOnePatient(SignManagementReadBeanForFingertip bean){
        Activity activity = Util.scanForActivity(getContext());
        if (activity instanceof NewDoctorSignActivity){
            NewDoctorSignActivity oca = (NewDoctorSignActivity)activity;
            oca.handlePersonSelect(bean);
        }
        dismiss();
    }

    private String getKeyword(){
        String s = Util.trimString(etKeyword.getText().toString());
        return s;
    }
    public EditText getEtKeyword(){
        return etKeyword;
    }
    @Override
    public void onRefresh() {
        Util.showGifProgressDialog(getContext().getApplicationContext());
        String keyword = getKeyword();
//        String year = Util.getCurrentYearString();
        Map<String, String> map = ParametersFactory.getSignManagementReadListMapForFingertip(getContext(), keyword, "");//5202031501010023
        mPresenter.main(map);

    }

    @Override
    public void onLoadListInfoSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        if (bean!=null) {
            try {
                String resultString = bean.getResult();
                List<SignManagementReadBeanForFingertip> result = JSON.parseObject(resultString,
                        new TypeReference<List<SignManagementReadBeanForFingertip>>(){});

                List<SignManagementReadBeanForFingertip> list = result;
                mAdapter.setNewData(list);

                showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//        showRecyclerViewOrFailureView(true, true);
//        showRecyclerViewOrFailureView(false, true);

            } catch (Exception e) {
                e.printStackTrace();
                showRecyclerViewOrFailureView(false, true);

                Util.showToast(getContext(), "解析数据失败");
            }
        } else {
            showRecyclerViewOrFailureView(true, true);
            Util.showToast(getContext(), "获取数据为空");
        }

        srl.setEnabled(true);
        srl.setRefreshing(false);
        isRefresh = false;

    }

    @Override
    public void onLoadListInfoFailure(String message) {
        showRecyclerViewOrFailureView(false, true);

        srl.setEnabled(true);
        srl.setRefreshing(false);
        isRefresh = false;

        Util.showToast(getContext(), message);

    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint(){
        return "暂无调阅人员信息，点我刷新";
    }

    private String getFailureHint(){
        return "加载调阅人员信息失败，点我重试";
    }

}
