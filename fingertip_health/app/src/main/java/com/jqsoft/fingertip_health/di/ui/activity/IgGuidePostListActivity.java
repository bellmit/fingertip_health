package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.IgGuidePostListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuidePostBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.presenter.PolityActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;

;

public class IgGuidePostListActivity extends AbstractActivity
       {


    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.list_size)
    TextView list_size;

    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private  String code,titlename;
    TextView tvFailureView;

    @Inject
    PolityActivityPresenter mPresenter;

    private boolean isRefresh = false;
  ArrayList<IgGuidePostBean> IgGuidePostBeanlist;
    private int type;

    private IgGuidePostListAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    @BindView(R.id.policy_title)
    TextView online_consultation_title;


    @Override
    protected void loadData() {

    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_igguidepostlist;
    }

    @Override
    protected void initData() {
        IgGuidePostBeanlist=(ArrayList<IgGuidePostBean>) getDeliveredSerializableByKey(Constants.IGGUIDE_ITEM_ACTIVITY_KEY);

    }

    @Override
    protected void initView() {

//        IgGuideActivity.instance.finish();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        online_consultation_title.setText("智能引导");

        final BaseQuickAdapter<IgGuidePostBean, BaseViewHolder> mAdapter = new IgGuidePostListAdapter(new ArrayList<IgGuidePostBean>(), TYPE_MULTIPLE_LINE);
        this.mAdapter = (IgGuidePostListAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);

        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(IgGuidePostBeanlist);
        list_size.setText("共有"+IgGuidePostBeanlist.size()+"个可能适合您的救助，请点击查看！");
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                IgGuidePostBean pb = mAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RELIEF_ITEM_ACTIVITY_KEY,   pb.getItemId());
                Util.setFromCollection("0");
                Util.gotoActivityWithBundle(IgGuidePostListActivity.this, ReliefItemActivity.class, bundle);
            }
        });

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public List<IgGuidePostBean> getListFromResult(GCAHttpResultBaseBean<ArrayList<IgGuidePostBean>> beanList) {
        if (beanList != null) {
            List<IgGuidePostBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_policy_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_policy_info_error_please_click_to_reload);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }


       }
