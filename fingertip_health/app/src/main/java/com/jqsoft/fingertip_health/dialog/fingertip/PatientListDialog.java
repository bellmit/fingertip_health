package com.jqsoft.fingertip_health.dialog.fingertip;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.PatientListAdapterForFingertip;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientWrapperBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.PatientListDialogContractForFingertip;
import com.jqsoft.fingertip_health.di.module.PatientListDialogModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.PatientListDialogPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.dialog.base.BaseDialog;
import com.jqsoft.fingertip_health.feature.PatientSelectListener;
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

public class PatientListDialog extends BaseDialog implements
        PatientListDialogContractForFingertip.View, SwipeRefreshLayout.OnRefreshListener {

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


    @BindView(R.id.lay_patient_list_load_failure)
    View failureView;
    TextView tvFailureView;

    private PatientListAdapterForFingertip mAdapter;

    private boolean isRefresh = false;

    PatientSelectListener listener;

    @Inject
    PatientListDialogPresenter mPresenter;


    public PatientListDialog(@NonNull Context context, PatientSelectListener listener) {
        super(context, R.style.white_background_dialog, R.layout.dialog_patient_list_layout);
        this.listener = listener;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getContext())
                .getAppComponent()
                .addPatientListDialogForFingertip(new PatientListDialogModuleForFingertip(this))
                .inject(this);

    }

    @Override
    public void initData() {
        etKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String keyword = getKeyword();
                    if (StringUtils.isBlank(keyword)) {
                        Util.showToast(getContext(), "请输入农合证号或身份证号再试");
                    } else {
                        onRefresh();
                    }

                }
                return false;
            }
        });
    }

    private void showSoft() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) etKeyword.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(etKeyword, 0);
            }
        }, 0);
    }

    @Override
    public void initView() {
        //  showSoft();
        showSoftInputFromWindow(etKeyword);
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
                    Util.showToast(getContext(), "请输入农合证号或身份证号再试");
                } else {
                    onRefresh();
                }
            }
        });

        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

        srl.setColorSchemeColors(getContext().getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<OutpatientBeanForFingertip, BaseViewHolder> mAdapter = new PatientListAdapterForFingertip(
                listener
                , new ArrayList<OutpatientBeanForFingertip>());
        this.mAdapter = (PatientListAdapterForFingertip) mAdapter;
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
                OutpatientBeanForFingertip pb = mAdapter.getItem(position);
                didSelectOnePatient(pb);
            }
        });
    }

    private void didSelectOnePatient(OutpatientBeanForFingertip bean) {
        Activity activity = Util.scanForActivity(getContext());
        if (activity instanceof OutpatientChargesActivity) {
            OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
            oca.handlePatientSelect(bean);
        }
        dismiss();
    }

    private String getKeyword() {
        String s = Util.trimString(etKeyword.getText().toString());
        return s;
    }

    @Override
    public void onRefresh() {
        Util.showGifProgressDialog(getContext().getApplicationContext());
        String keyword = getKeyword();
        String year = Util.getCurrentYearString();
        Map<String, String> map = ParametersFactory.getPatientListMapForFingertip(getContext(), year, keyword);//5202031501010023
        mPresenter.main(map);

    }

    @Override
    public void onLoadPatientListInfoSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        if (bean != null) {
            try {
                String resultString = bean.getResult();
                OutpatientWrapperBeanForFingertip result = JSON.parseObject(resultString, OutpatientWrapperBeanForFingertip.class);

                List<OutpatientBeanForFingertip> list = result.getPersons();
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
    public void onLoadPatientListInfoFailure(String message) {
        showRecyclerViewOrFailureView(false, true);

        srl.setEnabled(true);
        srl.setRefreshing(false);
        isRefresh = false;

        Util.showToast(getContext(), message);

    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
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
    public EditText getEtKeyword(){
        return etKeyword;
    }
    private String getListEmptyHint() {
        return "暂无人员信息，点我刷新";
    }

    private String getFailureHint() {
        return "加载人员信息失败，点我重试";
    }

    public void showSoftInputFromWindow(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            InputMethodManager inputManager =
                    (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }

    }

}
