package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HouseFamilyAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.FamilyMemberListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldFaimilyMemberContract;
import com.jqsoft.fingertip_health.di.module.HouseHoldFamilyModule;
import com.jqsoft.fingertip_health.di.presenter.HouseHoldFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.FamilyDetailActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HouseHoldFaimilyFragment extends AbstractFragment implements HouseHoldFaimilyMemberContract.View,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener  {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String userID;
//    @BindView(R.id.lay_content)
//    SwipeRefreshLayout srl;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;
    @Inject
    HouseHoldFamilyFragmentPresenter mPresenter;
    private HouseFamilyAdapter mAdapter;
    private boolean isRefresh = false;
    @Override
    protected int getLayoutId() {
        return R.layout.serveryfaimilymenview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        final BaseQuickAdapter<FamilyMemberListBean, BaseViewHolder> mAdapter = new HouseFamilyAdapter(new ArrayList<FamilyMemberListBean>(), getActivity());
        this.mAdapter = (HouseFamilyAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("gId", mAdapter.getData().get(position).getGID());
                intent.putExtras(bundle);
                intent.setClass(getActivity(), FamilyDetailActivity.class);
                getActivity().startActivity(intent);
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

     //   srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
      //  srl.setOnRefreshListener(this);

    }

    @Override
    protected void loadData() {
        // userID = "5C5AAD6E-5EBC-4B5F-B0D7-3147C8D5D17A";
        onRefresh();

    }
    @Override
    public void onRefresh() {
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        Map<String, String> map = getRequestMap();
        mPresenter.getfamilypresent(map);
    }

    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getFamiltFragmentData(getActivity(), userID);
        return map;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addfamilyFragment(new HouseHoldFamilyModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<FamilyMemberListBean>> bean) {
        if (bean.getData().size() > 0) {
            List<FamilyMemberListBean> list = getListFromResult(bean);
            mAdapter.setNewData(list);
        }else{
            showRecyclerViewOrFailureView(true,true);
        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<FamilyMemberListBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0, 0, false);
    }

    public List<FamilyMemberListBean> getListFromResult(GCAHttpResultBaseBean<List<FamilyMemberListBean>> beanList) {
        if (beanList != null) {
            List<FamilyMemberListBean> list = beanList.getData();
            return list;
        } else {

            return null;
        }
    }

    public void setId(String id) {
        userID = id;

    }


    @Override
    public void onLoadMoreRequested() {

    }
    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
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

    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_dibao_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_dibao_info_error_please_click_to_reload);
    }
    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {
//                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreFail();
        }
    }
}
