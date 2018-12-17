package com.jqsoft.fingertip_health.di.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HouseFileAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HouseFileBean;
import com.jqsoft.fingertip_health.bean.HouseHoldFileList;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldFlieContract;
import com.jqsoft.fingertip_health.di.module.HouseHoldFileModule;
import com.jqsoft.fingertip_health.di.presenter.HouseHoldFileFragmentPresenter;
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

public class FileFragment extends AbstractFragment implements HouseHoldFlieContract.View,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @Inject
    HouseHoldFileFragmentPresenter mPresenter;
    private String userID;
    private List<HouseHoldFileList> fileList = new ArrayList<>();
    private HouseFileAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;
    @Override
    protected int getLayoutId() {
        return R.layout.serveryfaimilymenview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        BaseQuickAdapter<HouseHoldFileList, BaseViewHolder> mAdapter = new HouseFileAdapter(new ArrayList<HouseHoldFileList>(), getActivity());
        this.mAdapter = (HouseFileAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
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
       // userID = "5C5AAD6E-5EBC-4B5F-B0D7-3147C8D5D17A";
        onRefresh();

    }
    @Override
    public void onRefresh() {
        Map<String, String> map = getRequestMap();
        mPresenter.getfilepresent(map);
    }
    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getFileFragmentData(getActivity(), userID);
        return map;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addfileFragment(new HouseHoldFileModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseFileBean>> bean) {
        Log.d("",bean.getData().toString());
        if (bean.getData().get(0).getFileList().size() > 0) {
            mAdapter.setNewData(bean.getData().get(0).getFileList());
            mAdapter.setGolbIp(bean.getData().get(0).getGoalIP());
        }else{
            showRecyclerViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseFileBean>> bean) {
//        if (bean.getData().size() > 0) {
//            mAdapter.setNewData(bean.getData().get(0).getFileList());
//        }else{
//            showRecyclerViewOrFailureView(false, true);
//        }

    }

    @Override
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
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
        return getResources().getString(R.string.hint_no_fujian_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_file_info_error_please_click_to_reload);
    }
}
