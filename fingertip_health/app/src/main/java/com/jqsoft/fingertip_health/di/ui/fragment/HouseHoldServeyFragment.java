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
import com.jqsoft.fingertip_health.adapter.HouseholdeServeryAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HouseHoldSeveryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldServeryContract;
import com.jqsoft.fingertip_health.di.module.HouseHoldserveryModule;
import com.jqsoft.fingertip_health.di.presenter.HouseHoldServeryFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AddImgVideoServeryActivity;
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
 * 入户调查
 */

public class HouseHoldServeyFragment extends AbstractFragment implements HouseHoldServeryContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @Inject
    HouseHoldServeryFragmentPresenter mPresenter;
    private String gfamliyId;
    private HouseholdeServeryAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    //    @BindView(R.id.addhousehold_btn)
//    LinearLayout addHouseservery_btn;
    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;

    private List<HouseHoldSeveryBean> list;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;

    @Override
    protected int getLayoutId() {
        return R.layout.severyanddemocratic;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {
        Map<String, String> map = getRequestMap();
        mPresenter.getServerypresent(map);
    }

    private String cardno;
    private CompositeSubscription mIndexSelectSubscription;

    private void registerIndexSelectSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SOUND_SABESUCUSS, Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Map<String, String> map = getRequestMap();
                mPresenter.getServerypresent(map);
            }
        });

        Subscription subscription2 = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_IDCARD, GCAHttpResultBaseBean.class).subscribe(new Action1<GCAHttpResultBaseBean>() {
            @Override
            public void call(GCAHttpResultBaseBean integer) {
                cardno = integer.getMsg();
            }
        });


        if (mIndexSelectSubscription == null) {
            mIndexSelectSubscription = new CompositeSubscription();
        }
        mIndexSelectSubscription.add(subscription);
        mIndexSelectSubscription.add(subscription2);
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
        final BaseQuickAdapter<HouseHoldSeveryBean, BaseViewHolder> mAdapter = new HouseholdeServeryAdapter(new ArrayList<HouseHoldSeveryBean>(), getActivity());
        this.mAdapter = (HouseholdeServeryAdapter) mAdapter;
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
                bundle.putString("cardno",cardno);
                intent.putExtras(bundle);
                intent.setClass(getActivity(), AddImgVideoServeryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("gfamliyId", gfamliyId);
                bundle.putString("gId", mAdapter.getData().get(position).getGID());
                bundle.putString("ftype", "2");
                intent.putExtras(bundle);
                ///  intent.setClass(getActivity(), AddServeryActivity.class);
                intent.setClass(getActivity(), AddImgVideoServeryActivity.class);
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
    protected void loadData() {
        // gfamliyId = "7FC05D69-FE82-41AF-AB5B-B3DA19DBDA49";
        onRefresh();

    }

    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getServeryFragmentData(getActivity(), gfamliyId);
        return map;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addserveryFragment(new HouseHoldserveryModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseHoldSeveryBean>> bean) {

        if (bean.getData().size() > 0) {
            list = getListFromResult(bean);
            mAdapter.setNewData(list);
            failureView.setVisibility(View.GONE);
        } else {
            showRecyclerViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseHoldSeveryBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
    }

    public List<HouseHoldSeveryBean> getListFromResult(GCAHttpResultBaseBean<List<HouseHoldSeveryBean>> beanList) {
        if (beanList != null) {
            List<HouseHoldSeveryBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public void setId(String id) {
        gfamliyId = id;

    }


    @Override
    public void onLoadMoreRequested() {

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
        return getResources().getString(R.string.hint_load_diaocha_empty_please_click_to_reload);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_diaocha_error_please_click_to_reload);
    }
}
