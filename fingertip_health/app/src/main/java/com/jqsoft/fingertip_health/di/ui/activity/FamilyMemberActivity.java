package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.FamilyMemberAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.FamilyMemberBean;
import com.jqsoft.fingertip_health.di.contract.FamilyMemberActivityContract;
import com.jqsoft.fingertip_health.di.module.FamilyMemberActivityModule;
import com.jqsoft.fingertip_health.di.presenter.FamilyMemberActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.simulate.SimulateData;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


//家庭成员列表
public class FamilyMemberActivity extends AbstractActivity implements
        FamilyMemberActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.fl_family_member_add)
    FrameLayout flFamilyMemberAdd;

    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_family_member_load_failure)
    View failureView;

    TextView tvFailureView;

    @Inject
    FamilyMemberActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private FamilyMemberAdapter mAdapter;


    private String keywordString;

    private CompositeSubscription mFamilyMemberRefreshSubscription;


    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    private void registerFamilyMemberRefreshSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_ADD_FAMILY_MEMBER_SUCCESS, Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                onRefresh();
            }
        });
        if (mFamilyMemberRefreshSubscription == null) {
            mFamilyMemberRefreshSubscription = new CompositeSubscription();
        }
        mFamilyMemberRefreshSubscription.add(subscription);
    }

    private void unregisterFamilyMemberRefreshSubscription(){
        if (mFamilyMemberRefreshSubscription != null && mFamilyMemberRefreshSubscription.hasSubscriptions()) {
            mFamilyMemberRefreshSubscription.unsubscribe();
        }

    }

    @Override
    protected void loadData() {
        onRefresh();

    }




//    public String getKeywordString() {
//        return Util.trimString(keywordString);
//    }


    public Map<String, String> getRequestMap() {
        String year = Util.getCurrentYearString();
        String id = IdentityManager.getId(this);
        Map<String, String> map = ParametersFactory.getFamilyMemberDataMap(this, id, currentPage, pageSize);
        return map;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_member_layout;
    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        LogUtil.i("FamilyMemberActivity onCreateOptionsMenu called");
////        reassignToolbar();
//        menu.clear();
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i("FamilyMemberActivity onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_search:
                //code here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void initView() {
        LogUtil.i("FamilyMemberActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        if (mFamilyMemberRefreshSubscription == null) {
            registerFamilyMemberRefreshSubscription();
        }


        RxView.clicks(flFamilyMemberAdd)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        Util.gotoActivity(FamilyMemberActivity.this, AddFamilyMemberActivity.class);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        initSearchView();

//        String failureString = Util.getFamilyMemberActivityHintTitle(this, type);
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


        final BaseQuickAdapter<FamilyMemberBean, BaseViewHolder> mAdapter = new FamilyMemberAdapter(new ArrayList<FamilyMemberBean>());
        this.mAdapter = (FamilyMemberAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(FamilyMemberActivity.this, position+" is clicked");
                FamilyMemberBean pb = mAdapter.getItem(position);
                String cardNo = Util.trimString(pb.getMemberCardNo());
                String personID = Util.trimString(pb.getPersonInfoKey());
                Bundle bundle = new Bundle();
                bundle.putString(Constants.CARD_NO_KEY, cardNo);
                bundle.putString(Constants.PERSON_ID_KEY, personID);
                Util.gotoActivityWithBundle(FamilyMemberActivity.this, SmartAlertActivity.class, bundle);
            }
        });
    }

//    public void initSearchView() {
//        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
//        searchView.setVoiceSearch(false);
//        searchView.setHint(getResources().getString(R.string.search_hint));
////        searchView.setCursorDrawable(R.drawable.color_cursor_white);
////        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//
//        searchView.setOnQueryTextListener(new MaterialSearchViewNew.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
////                        .show();
////                loadData();
//                keywordString=Util.trimString(query);
//                onRefresh();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do some magic
//                keywordString = Util.trimString(newText);
//                return false;
//            }
//        });
//
//        searchView.setOnSearchViewListener(new MaterialSearchViewNew.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                //Do some magic
////                ToastUtil.show(getActivity(), "searchview show");
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                //Do some magic
//            }
//        });
//    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addFamilyMemberActivity(new FamilyMemberActivityModule(this))
                .inject(this);
    }


//    @Override
//    public void refreshView(TreatmentListBean data) {
////        LogUtils.e("aaaacurrentIndex" + currentIndex);
//        LogUtil.i("FamilyMemberActivity refreshView");
//        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
//
//        treatmentList = bean.getTreatmentList();
////        mAdapter.addData(treatmentList);
////        index += 1;
////        currentIndex = mAdapter.getData().size() - 2 * index;
////        mAdapter.loadMoreComplete();
////
//        if (isRefresh) {
//            srl.setRefreshing(false);
//            mAdapter.setEnableLoadMore(true);
//            isRefresh = false;
//            mAdapter.setNewData(treatmentList);
//        } else {
//            srl.setEnabled(true);
//            index += 20;
//            mAdapter.addData(treatmentList);
//            mAdapter.loadMoreComplete();
//        }
//
//
//    }

    public void simulateData() {
        LogUtil.i("FamilyMemberActivity simulateData");
//        setState(AppConstants.STATE_SUCCESS);

//        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
//        treatmentList = bean.getTreatmentList();
//        mAdapter.getData().clear();
//        mAdapter.addData(treatmentList);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.loadMoreComplete();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterFamilyMemberRefreshSubscription();
    }



    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        LogUtil.i("FamilyMemberActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);

        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);
    }


    @Override
    public void onLoadMoreRequested() {
        ++currentPage;
        Map<String, String> map = getRequestMap();
        mPresenter.main(map, true);
        LogUtil.i("FamilyMemberActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
    }

    public List<FamilyMemberBean> getListFromResult(HttpResultBaseBean<List<FamilyMemberBean>> beanList) {
        if (beanList != null) {
            List<FamilyMemberBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

//    public int getPageFromResult(HttpResultBaseBean<List<FamilyMemberListBean>> beanList) {
//        if (beanList!=null){
//            List<FamilyMemberListBean> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getPage();
//            } else {
//                return Constants.DEFAULT_INITIAL_PAGE;
//            }
//        } else {
//            return Constants.DEFAULT_INITIAL_PAGE;
//        }
//    }

//    public int getPageSizeFromResult(HttpResultBaseBean<List<FamilyMemberListBean>> beanList){
//        if (beanList!=null){
//            FamilyMemberResultWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
////                List<FamilyMemberListBean> list = wrapperBean.getList();
////                int size = ListUtils.getSize(list);
////                return size;
//                return wrapperBean.getSize();
//            } else {
//                return Constants.DEFAULT_PAGE_SIZE;
//            }
//        } else {
//            return Constants.DEFAULT_PAGE_SIZE;
//        }
//    }

    public int getListSizeFromResult(HttpResultBaseBean<List<FamilyMemberBean>> beanList) {
        if (beanList != null) {
            List<FamilyMemberBean> list = beanList.getData();
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
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.setEnableLoadMore(false);
            mAdapter.loadMoreFail();
        }
//        if (isSuccessful) {
//            if (listSize < pageSize) {
////                mAdapter.setEnableLoadMore(false);
//                mAdapter.loadMoreEnd(true);
//            } else {
//                mAdapter.setEnableLoadMore(true);
//                mAdapter.loadMoreComplete();
//            }
//        } else {
//            mAdapter.setEnableLoadMore(true);
//            mAdapter.loadMoreFail();
//        }
    }




    @Override
    public void onLoadListSuccess(HttpResultBaseBean<List<FamilyMemberBean>> beanList) {

//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("FamilyMemberActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

        List<FamilyMemberBean> list = getListFromResult(beanList);
        mAdapter.setNewData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//        showRecyclerViewOrFailureView(true, true);
//        showRecyclerViewOrFailureView(false, true);

        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;


    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<FamilyMemberBean>> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("FamilyMemberActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<FamilyMemberBean> list = getListFromResult(beanList);
        mAdapter.addData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
//        simulate();

        showRecyclerViewOrFailureView(false, true);


        if (isLoadMore){
            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
        Util.showToast(this, message);
//        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);


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
        return getResources().getString(R.string.hint_no_family_member_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_family_member_info_error_please_click_to_reload);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }


    private void simulate() {
        List<FamilyMemberBean> list = SimulateData.getSimulatedFamilyMemberList();
        HttpResultBaseBean<List<FamilyMemberBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
        onLoadMoreListSuccess(wrapper);

    }

}
