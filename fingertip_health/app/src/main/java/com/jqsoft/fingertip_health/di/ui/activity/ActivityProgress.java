package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.HandleProgressAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.ProgressBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ActivityProgress extends AbstractActivity {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<ProgressBean> progresslist = new ArrayList<>();
    private HandleProgressAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        online_consultation_title.setText("办理进度");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final BaseQuickAdapter<ProgressBean, BaseViewHolder> mAdapter = new HandleProgressAdapter(new ArrayList<ProgressBean>());
        this.mAdapter = (HandleProgressAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //   mAdapter.setOnLoadMoreListener(this, recyclerview);
        recyclerview.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ActivityProgress.this, HandleProgressDetailActivity.class);
                intent.putExtra("itemCode", mAdapter.getData().get(position).getItemCode());
                intent.putExtra("identifier", mAdapter.getData().get(position).getIdentifier());
                intent.putExtra("idcard",mAdapter.getData().get(position).getCardNo());
                intent.putExtra("name",mAdapter.getData().get(position).getName());
                startActivity(intent);
                // Util.gotoActivityWithBundle(getApplicationContext(), HandleProgressDetailActivity.class, bundle);
            }
        });

    }

    @Override
    protected void loadData() {
        progresslist = (ArrayList<ProgressBean>) getIntent().getSerializableExtra("progeressdata");
        mAdapter.setNewData(progresslist);
    }


}
