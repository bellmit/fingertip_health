package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.DemocraticAppraisalAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DemocraticAppraisalBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.DemocraticAppraisalContract;
import com.jqsoft.fingertip_health.di.module.DemocraticAppraisalModule;
import com.jqsoft.fingertip_health.di.presenter.DemocraticAppraisalFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AddDemocraticActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/1/19.
 */

public class DemocraticAppraisalfragment extends AbstractFragment implements DemocraticAppraisalContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    //    @BindView(R.id.addhousehold_btn)
//    LinearLayout addhousehold_btn;
//    @BindView(R.id.add_text)
//    TextView add_text;
    private String gfamliyId;
    private DemocraticAppraisalAdapter mAdapter;
    @Inject
    DemocraticAppraisalFragmentPresenter mPresenter;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;
    TextView tvFailureView;
    private String idcrad;
    @Override

    protected int getLayoutId() {
        return R.layout.severyanddemocratic;
    }

    @Override
    protected void initData() {

    }

    private CompositeSubscription mIndexSelectSubscription;

    private void registerIndexSelectSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SOUND_SAVEPY, Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Map<String, String> map = getRequestMap();
                mPresenter.getDemocraticpresent(map);
            }
        });

        if (mIndexSelectSubscription == null) {
            mIndexSelectSubscription = new CompositeSubscription();
        }
        mIndexSelectSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIndexSelectSubscription != null && mIndexSelectSubscription.hasSubscriptions()) {
            mIndexSelectSubscription.unsubscribe();
        }
    }

    @Override
    protected void initView() {
//        add_text.setText("新增评议");
        final BaseQuickAdapter<DemocraticAppraisalBean, BaseViewHolder> mAdapter = new DemocraticAppraisalAdapter(new ArrayList<DemocraticAppraisalBean>(), getActivity());
        this.mAdapter = (DemocraticAppraisalAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(mAdapter);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("gfamliyId", gfamliyId);
                bundle.putString("ftype", "1");
                intent.putExtras(bundle);
                intent.setClass(getActivity(), AddDemocraticActivity.class);
                getActivity().startActivity(intent);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("ftype", "2");
                bundle.putString("gfamliyId", gfamliyId);
                bundle.putString("gId", mAdapter.getData().get(position).getGID());
                intent.putExtras(bundle);
                // intent.setClass(getActivity(), DisPlayDemocraticActivity.class);
                intent.setClass(getActivity(), AddDemocraticActivity.class);
                getActivity().startActivity(intent);
            }
        });
        if (mIndexSelectSubscription == null) {
            registerIndexSelectSubscription();
        }

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
    public void onRefresh() {
        Map<String, String> map = getRequestMap();
        mPresenter.getDemocraticpresent(map);
    }

    @Override
    protected void loadData() {
        //  gfamliyId = "5C5AAD6E-5EBC-4B5F-B0D7-3147C8D5D17A";
        onRefresh();

    }

    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getDemocraticData(getActivity(), gfamliyId);
        return map;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .adddemocraticFragment(new DemocraticAppraisalModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<DemocraticAppraisalBean>> bean) {
        if (bean.getData().size() > 0) {
            List<DemocraticAppraisalBean> list = getListFromResult(bean);
            mAdapter.setNewData(list);
            failureView.setVisibility(View.GONE);
        } else {
            showRecyclerViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<DemocraticAppraisalBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
    }

    public List<DemocraticAppraisalBean> getListFromResult(GCAHttpResultBaseBean<List<DemocraticAppraisalBean>> beanList) {
        if (beanList != null) {
            List<DemocraticAppraisalBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public void setId(String id) {
        gfamliyId = id;

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
        return getResources().getString(R.string.hint_load_pingyi_empty_please_click_to_reload);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_pingyi_error_please_click_to_reload);
    }


    @Override
    public void onLoadMoreRequested() {

    }
}
