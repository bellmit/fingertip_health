package com.jqsoft.fingertip_health.di.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.OnlineChatAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.PersonDoctorMessageInfo;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.OnLineChatintFragmentContract;
import com.jqsoft.fingertip_health.di.module.OnlineChatingFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.OnLineChatingFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.ChatDetailActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jerry on 2017/8/31.
 */

public class OnlineChatingFragment extends AbstractFragment implements OnLineChatintFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.sign_service_assess_title)
    TextView sign_service_assess_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.lay_content_remind)
    View layContentRemind;
    SwipeRefreshLayout srlRemind;
    RecyclerView rvRemind;
    @BindView(R.id.lay_remind_load_failure)
    View remindFailureView;
    TextView tvRemindFailureView;
    List<PersonDoctorMessageInfo> remindList;
    OnlineChatAdapter onlineChatAdapter;
    @Inject
    OnLineChatingFragmentPresenter mPresenter;
    private String startIndex, endIndex, flag;

    private CompositeSubscription mCompositeSubscription;
    private void registerIndexSelectSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_REFRESH_CHAT_PERSON_LIST, Boolean.class).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean b) {
                loadRemindList();
            }
        });
        Subscription subscription2 = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SURFADAPTER, String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                for (int i = 0; i < remindList.size(); i++) {
                    if (str.equals(remindList.get(i).getDocUserID())) {
                        remindList.get(i).setTotal("0");
                        onlineChatAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
        mCompositeSubscription.add(subscription2);
    }

    private void unregisterSubscription(){
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterSubscription();
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this.getActivity())
                .getAppComponent()
                .addchatFragment(new OnlineChatingFragmentModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onlinechat;
    }

    @Override
    protected void initData() {
        remindList = new ArrayList<>();
    }


    @Override
    protected void initView() {
        if (mCompositeSubscription == null) {
            registerIndexSelectSubscription();
        }

        sign_service_assess_title.setText("医生列表");
        ll_back.setVisibility(View.GONE);
        srlRemind = (SwipeRefreshLayout) layContentRemind;
        rvRemind = (RecyclerView) layContentRemind.findViewById(R.id.recyclerview);
        srlRemind.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srlRemind.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onlineChatAdapter.setEnableLoadMore(false);
                loadRemindList();

            }
        });

        tvRemindFailureView = (TextView) remindFailureView.findViewById(R.id.tv_load_failure_hint);
        RxView.clicks(tvRemindFailureView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onlineChatAdapter.setEnableLoadMore(false);
                        loadRemindList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        onlineChatAdapter = new OnlineChatAdapter(getActivity().getApplicationContext(), remindList);
//        easyLoadMoreView = new EasyLoadMoreView();
//        remindAdapter.setLoadMoreView(easyLoadMoreView);
        onlineChatAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        onlineChatAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rvRemind);
        onlineChatAdapter.setEnableLoadMore(false);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvRemind.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        Util.addDividerToRecyclerView(getActivity(), rvRemind, true);
        rvRemind.setAdapter(onlineChatAdapter);
        onlineChatAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                PersonDoctorMessageInfo bean = onlineChatAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ChatDetailActivity.class);
                intent.putExtra("docUserID",bean.getDocUserID());
                intent.putExtra("total",bean.getTotal());
                startActivity(intent);

            }
        });
        loadRemindList();

    }

    private void loadRemindList() {
        Map<String, String> map = getRemindListRequestMap();
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.getPersonDoctorMessageInfo(body);

    }

    private Map<String, String> getRemindListRequestMap() {
        Map<String, String> map = ParametersFactory.getPersonDoctorMessage(getActivity(), IdentityManager.getPersonID(getActivity()));//"341222_110804018490"
        return map;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onLoadPolicyDataSuccess(HttpResultBaseBean<List<PersonDoctorMessageInfo>> bean) {
        srlRemind.setRefreshing(false);
        if (bean != null) {
            if (bean.getData().size() > 0) {
                remindList.clear();
                remindList.addAll(bean.getData());
                onlineChatAdapter.notifyDataSetChanged();
                showRemindRecyclerViewOrFailureView(true, false);
            }
        } else {
            showRemindRecyclerViewOrFailureView(true, true);
        }

    }

    @Override
    public void onLoadRemindDataFailure(String message) {
        Util.showToast(getActivity().getApplicationContext(),message);

    }

    private void showRemindRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                layContentRemind.setVisibility(View.GONE);
                remindFailureView.setVisibility(View.VISIBLE);
                tvRemindFailureView.setText(getRemindListEmptyHint());
            } else {
                layContentRemind.setVisibility(View.VISIBLE);
                remindFailureView.setVisibility(View.GONE);
            }
        } else {
            layContentRemind.setVisibility(View.GONE);
            remindFailureView.setVisibility(View.VISIBLE);
            tvRemindFailureView.setText(getRemindFailureHint());

        }
    }

    private String getRemindListEmptyHint() {
        return getResources().getString(R.string.hint_no_remind_info_please_click_to_reload);
    }

    private String getRemindFailureHint() {
        return getResources().getString(R.string.hint_load_remind_info_error_please_click_to_reload);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
