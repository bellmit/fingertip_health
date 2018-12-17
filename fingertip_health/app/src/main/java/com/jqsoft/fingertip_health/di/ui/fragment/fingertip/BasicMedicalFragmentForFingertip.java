package com.jqsoft.fingertip_health.di.ui.fragment.fingertip;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.TodoFragmentSubAdapterForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.TodoSubItemBean;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-07.
 */

public class BasicMedicalFragmentForFingertip extends AbstractFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;


    @BindView(R.id.lay_todo_list_load_failure)
    View failureView;
    TextView tvFailureView;

    TodoFragmentSubAdapterForFingertip mAdapter;

    public BasicMedicalFragmentForFingertip() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo_sub_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);

        final BaseQuickAdapter<TodoSubItemBean, BaseViewHolder> mAdapter = new TodoFragmentSubAdapterForFingertip(
                new ArrayList<TodoSubItemBean>());
        this.mAdapter = (TodoFragmentSubAdapterForFingertip) mAdapter;
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

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });

        simulate();

    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    private void simulate(){
        List<TodoSubItemBean> list = new ArrayList<>();
        for (int i = 0; i < 1; ++i){
            TodoSubItemBean bean = new TodoSubItemBean();
            list.add(bean);
        }
        mAdapter.setNewData(list);
    }

}
