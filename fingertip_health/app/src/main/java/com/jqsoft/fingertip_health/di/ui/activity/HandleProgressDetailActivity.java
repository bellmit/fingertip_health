package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HandleProgressDetailAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.ProgressDetailbean;
import com.jqsoft.fingertip_health.bean.ResultOpinion;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HandleProgressDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.HandleProgressDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.HandleProgressDetailPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/9.
 */

public class HandleProgressDetailActivity extends AbstractActivity implements HandleProgressDetailActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String itemCode, identifier, idcard, name;
    @BindView(R.id.identifier)
    TextView tv_identifier;
    @BindView(R.id.idcard)
    TextView tv_idcard;
    @BindView(R.id.yibaotype)
    TextView tv_yibaotype;
    @BindView(R.id.username)
    TextView tv_username;

    private String itemName;
    @Inject
    HandleProgressDetailPresenter mPresenter;
    private HandleProgressDetailAdapter mAdapter;
    private ArrayList<ResultOpinion> detaillist = new ArrayList<>();
    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handleprogressdetail;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {
        Map<String, String> map = getRequestMap();
        mPresenter.getSocialDetails(map, false);
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("详情");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final BaseQuickAdapter<ResultOpinion, BaseViewHolder> mAdapter = new HandleProgressDetailAdapter(new ArrayList<ResultOpinion>());
        this.mAdapter = (HandleProgressDetailAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //   mAdapter.setOnLoadMoreListener(this, recyclerview);
        recyclerview.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });
    }

    @Override
    protected void loadData() {
        itemCode = getIntent().getStringExtra("itemCode");
        identifier = getIntent().getStringExtra("identifier");
        idcard = getIntent().getStringExtra("idcard");
        name = getIntent().getStringExtra("name");
        itemName = getIntent().getStringExtra("itemName");
        tv_yibaotype.setText(itemName);
        tv_identifier.setText(identifier);
        tv_idcard.setText(idcard);
        tv_username.setText(name);
        onRefresh();
    }

    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getProgressDetail(this, itemCode, identifier);
        return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addprogressDetail(new HandleProgressDetailActivityModule(this))
                .inject(this);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<ProgressDetailbean> bean) {
        if (bean.getData() ==null) {
            showRecyclerViewOrFailureView(true, true);
        }else{
            if (bean.getData().getResultOpinion().size() > 0) {
                detaillist.addAll(bean.getData().getResultOpinion());
                mAdapter.setNewData(detaillist);
                tv_yibaotype.setText(bean.getData().getItemNames());
            } else {
                showRecyclerViewOrFailureView(true, true);
            }
        }

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<ProgressDetailbean> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                //   srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                //   srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            //  srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_load_xqi_empty_please_click_to_reloadx);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_xq_error_please_click_to_reload);
    }

}
