package com.jqsoft.fingertip_health.di.ui.activity.fingertip;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignManagementAdapterForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.SignManagementItemBeanForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.SignDoctorListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SignPeopleManagementActivityForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 签约管理
 * Created by Administrator on 2018-09-13.
 */

public class SignManagementActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    SignManagementAdapterForFingertip mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_management_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, "");

        final BaseQuickAdapter<SignManagementItemBeanForFingertip, BaseViewHolder> mAdapter = new SignManagementAdapterForFingertip(
                new ArrayList<SignManagementItemBeanForFingertip>());
        this.mAdapter = (SignManagementAdapterForFingertip) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Activity activity = Util.scanForActivity(this);
        Util.addDividerToRecyclerView(activity, recyclerView, true);
        recyclerView.setAdapter(mAdapter);

        setData();

        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                switch (position){
                    case 0:

                        Util.gotoActivity(SignManagementActivity.this, SignDoctorListActivity.class);

//                        Util.gotoActivity(SignManagementActivity.this, SignPeopleManagementActivityForFingertip.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    private void setData(){
        List<SignManagementItemBeanForFingertip> list = new ArrayList<>();
        list.add(new SignManagementItemBeanForFingertip(R.mipmap.money_red, "签约人群管理"));
        list.add(new SignManagementItemBeanForFingertip(R.mipmap.money_red, "健康评估管理"));
        list.add(new SignManagementItemBeanForFingertip(R.mipmap.money_red, "方案制定管理"));
        list.add(new SignManagementItemBeanForFingertip(R.mipmap.money_red, "年度效果评估"));

        mAdapter.setNewData(list);
    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
