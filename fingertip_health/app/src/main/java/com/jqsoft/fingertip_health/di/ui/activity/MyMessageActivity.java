package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.MyMessageAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.MyMessageBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.MyMessageActivityContract;
import com.jqsoft.fingertip_health.di.module.MyMessageActivityModule;
import com.jqsoft.fingertip_health.di.presenter.MyMessagePresenter;
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

import static com.jqsoft.fingertip_health.adapter.ReceptionListAdapter.TYPE_MULTIPLE_LINE;

//我的消息
public class MyMessageActivity extends AbstractActivity implements
        MyMessageActivityContract.View, SwipeRefreshLayout.OnRefreshListener
     {


    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//    @BindView(R.id.list_size)
//    TextView list_size;
//    @BindView(R.id.bannerTitle)
//    TextView bannerTitle;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private  String code,titlename;
    TextView tvFailureView;

    @Inject
    MyMessagePresenter mPresenter;
         SRCLoginAreaBean receptionBean;
    private boolean isRefresh = false;

    private int type;

    private MyMessageAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String id;

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
        return R.layout.activity_mymessage;
    }

    @Override
    protected void initData() {
//        receptionBean=(gdws_sys_area)getDeliveredSerializableByKey(Constants.RECEPTION_ITEM_ACTIVITY_KEY);

    }
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addMyMessageActivity(new MyMessageActivityModule(this))
                .inject(this);
    }


    @Override
    protected void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        online_consultation_title.setText("我的消息");





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


        final MyMessageAdapter mAdapter = new MyMessageAdapter(new ArrayList<MyMessageBean>(), TYPE_MULTIPLE_LINE,this);
        this.mAdapter = (MyMessageAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                MyMessageBean pb = mAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RELIEF_DETAIL_ACTIVITY_KEY,   pb.getId());

                Util.gotoActivityWithBundle(MyMessageActivity.this, MyMessageDetailActivity.class, bundle);
            }
        });

    }

    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getGCAMyMessageMap(this,name,
                "appMessage.messageList");
        mPresenter.main(map);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public List<MyMessageBean> getListFromResult(GCAHttpResultBaseBean<List<MyMessageBean>> beanList) {
        if (beanList != null) {
            List<MyMessageBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<MyMessageBean>> beanList) {
        if (beanList != null) {
            List<MyMessageBean> list = beanList.getData();
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
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<MyMessageBean>> beanList) {


        int listSize = getListSizeFromResult(beanList);
        List<MyMessageBean> list = getListFromResult(beanList);
        mAdapter.setNewData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<MyMessageBean>> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);

        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<MyMessageBean> list = getListFromResult(beanList);
//        replaceXmlTag(list);
        mAdapter.addData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);



    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        Log.d("onLoadListFailure",message);

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
              /*  tvFailureView.setText("数据加载失败，点我刷新！");*/


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
             return getResources().getString(R.string.hint_no_mymsg_info_please_click_to_reload);
         }

         private String getFailureHint(){
             return getResources().getString(R.string.hint_load_mymsg_info_error_please_click_to_reload);
         }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }



}
