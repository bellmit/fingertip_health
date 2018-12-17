package com.jqsoft.fingertip_health.di.ui.activity;
;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.PolityAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.GuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolityBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.PolityActivityContract;
import com.jqsoft.fingertip_health.di.module.PolityActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PolityActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;

public class PoliticsActivity extends AbstractActivity implements
        PolityActivityContract.View, SwipeRefreshLayout.OnRefreshListener
        {


    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.bannerview)
    RelativeLayout bannerview;
    @BindView(R.id.bannerTitle)
    TextView bannerTitle;
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
    @BindView(R.id.list_size)
    TextView list_size;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private  String code,titlename;
    TextView tvFailureView;

    @Inject
    PolityActivityPresenter mPresenter;

    private boolean isRefresh = false;
    private GuideBean guideBean;
    private int type;

    private PolityAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    @BindView(R.id.policy_title)
    TextView online_consultation_title;


    @Override
    protected void loadData() {
        onRefresh();
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_politics;
    }

    @Override
    protected void initData() {
        guideBean=(GuideBean) getDeliveredSerializableByKey(Constants.GUIDE_DETAIL_ACTIVITY_KEY);

    }
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addPolityActivity(new PolityActivityModule(this))
                .inject(this);
    }


    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        bannerTitle.setText(guideBean.getName());

        if (guideBean==null){
            online_consultation_title.setText("办事指南");
        } else {
            online_consultation_title.setText(guideBean.getName());
        }
        switch (guideBean.getName()){
            case "民政":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_politics));
                break;
            case "残联":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_cdpf));
                break;
            case "卫计":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_hygiene));
                break;
            case "教育":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_edu));
                break;
            case "教体局":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_edu));
                break;
            case "工会":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_union));
                break;
            case "总工会":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_union));
                break;
            case "人社":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_agency));
                break;
            case "住建":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_build));
                break;
            case "住建局":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_build));
                break;
            case "工商联":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_business));
                break;
            case "团区委":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_committee));
                break;
            case "司法":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_judicial));
                break;
            case "综治办":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_government));
                break;
            case "妇联":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_federation));
                break;
            case "信访":
                bannerview.setBackground(getResources().getDrawable(R.mipmap.banner_xinfang));
                break;





        }



        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<PolityBean, BaseViewHolder> mAdapter = new PolityAdapter(new ArrayList<PolityBean>(), TYPE_MULTIPLE_LINE);
        this.mAdapter = (PolityAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                PolityBean pb = mAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RELIEF_ITEM_ACTIVITY_KEY,   pb.getId());

                Util.gotoActivityWithBundle(PoliticsActivity.this, ReliefItemActivity.class, bundle);
            }
        });

    }

    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);

//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);
        srl.setRefreshing(false);
        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);
    }


    public Map<String, String> getRequestMap() {

        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());


        Map<String, String> map = ParametersFactory.getGCAPolitics(this,name,
                guideBean.getCode(),
                Util.getAreaCode(),
                Constants.METHOD_NAME_RELIEF_ITEM);

        return map;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
//    @Override
//    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
////        LogUtil.i("PolicyActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
//    }

    public List<PolityBean> getListFromResult(GCAHttpResultBaseBean<List<PolityBean>> beanList) {
        if (beanList != null) {
            List<PolityBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<PolityBean>> beanList) {
        if (beanList != null) {
            List<PolityBean> list = beanList.getData();
            if (list != null) {
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
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

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<PolityBean>> beanList) {


        int listSize = getListSizeFromResult(beanList);
        List<PolityBean> list = getListFromResult(beanList);
        mAdapter.setNewData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

        list_size.setText("共有"+listSize+"个服务项");
        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PolityBean>> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);

        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<PolityBean> list = getListFromResult(beanList);
//        replaceXmlTag(list);
        mAdapter.addData(list);
        list_size.setText("共有"+listSize+"个服务项");
        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        if (isLoadMore){
            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

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
