//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.OnlineConsultationAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.AreaBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.response_new.OnlineConsultationResultBean;
//import com.jqsoft.fingertip_health.di.contract.OnlineConsultationActivityContract;
//import com.jqsoft.fingertip_health.di.module.OnlineConsultationActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.OnlineConsultationActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.simulate.SimulateData;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//import com.miguelcatalan.materialsearchview.MaterialSearchView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import rx.subscriptions.CompositeSubscription;
//
////在线咨询
//public class OnlineConsultationActivity extends AbstractActivity implements
//        OnlineConsultationActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
//        BaseQuickAdapter.RequestLoadMoreListener {
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @BindView(R.id.online_consultation_title)
//    TextView tvTitle;
//
//    @BindView(R.id.view_search)
//    MaterialSearchView searchView;
//
//    //    @BindView(R.id.srl)
////    SwipeRefreshLayout srl;
////    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.lay_content)
//    SwipeRefreshLayout srl;
//    @BindView(R.id.lay_online_consultation_load_failure)
//    View failureView;
//
//    TextView tvFailureView;
//
//
//
//    @Inject
//    OnlineConsultationActivityPresenter mPresenter;
//
//    private boolean isRefresh = false;
//
//    private int type;
//
//    private OnlineConsultationAdapter mAdapter;
////    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
////    private EasyLoadMoreView easyLoadMoreView;
//
//    private CompositeSubscription compositeSubscription;
//
//    private String keywordString;
//
//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
//
//    @Override
//    protected void loadData() {
//        onRefresh();
//
////        currentPage = Constants.DEFAULT_INITIAL_PAGE;//为了以后写下拉刷新 不然可以直接用这个方法。
////        TreatmentListRequestBean bean = getRequestBean();
////        mPresenter.main(bean, false);
//    }
//
////    public void registerProjectExecutionClickEvent() {
////        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM, OnlineConsultationResultItemBean.class)
////                .subscribe(new Action1<OnlineConsultationResultItemBean>() {
////                    @Override
////                    public void call(OnlineConsultationResultItemBean bean) {
////                        handleExecutionButtonClick(bean);
//////                        Util.showAlert(OnlineConsultationActivity.this, "提示", "您点击了执行" + bean.getServiceContentID());
////                    }
////                });
////        if (this.compositeSubscription == null) {
////            compositeSubscription = new CompositeSubscription();
////        }
////        compositeSubscription.add(mSubscription);
////    }
////
////    public void unregisterProjectExecutionClickEvent() {
////        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
////            compositeSubscription.unsubscribe();
////        }
////    }
////
////    public void handleExecutionButtonClick(OnlineConsultationResultItemBean item) {
////        MaterialDialog dialog = new MaterialDialog.Builder(this)
////                .title(R.string.hint_suggestion)
////                .content(R.string.hint_confirm_to_execute)
////                .positiveText(R.string.confirm)
////                .negativeText(R.string.cancel)
////                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                    @Override
////                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                        dialog.dismiss();
////                    }
////                }).build();
////        dialog.show();
////
////    }
//
//
//    public String getKeywordString() {
//        return Util.trimString(keywordString);
//    }
//
//    public String getArea() {
//        if (!ListUtils.isEmpty(Identity.area)) {
//            AreaBean ab = Identity.area.get(0);
//            return ab.getCode();
//        } else {
//            return Constants.EMPTY_STRING;
//        }
//
//    }
//
////    public TreatmentListRequestBean getRequestBean(){
////        String keyword = getKeywordString();
////        String area = getArea();
////         TreatmentListRequestBean bean = ParametersFactory.getTreatmentListRequestBean(keyword, area, currentPage, pageSize);
////        return bean;
////    }
//
//    public Map<String, String> getRequestMap() {
//        String docUserId = Identity.getUserId();
//        String year = Util.getCurrentYearString();
//        Map<String, String> map = ParametersFactory.getOnlineConsultationListMap(this, docUserId, year, currentPage, pageSize);
//        return map;
//    }
//
////    @Override
////    public int getContentLayoutId() {
////        return R.layout.layout_recyclerview;
////    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_online_consultation;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
////    @Override
////    public int setFrameLayoutId() {
////        return R.id.framelayout;
////    }
//
////    @Override
////    protected void initUI() {
////        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
////        setToolBar(toolbar, "");
////    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        LogUtil.i("OnlineConsultationActivity onCreateOptionsMenu called");
////        reassignToolbar();
//        menu.clear();
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu_search, menu);
////        MenuItem item = menu.findItem(R.id.action_search);
////        searchView.setMenuItem(item);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        LogUtil.i("OnlineConsultationActivity onOptionsItemSelected");
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                //code here
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    protected void initView() {
//        LogUtil.i("OnlineConsultationActivity initView enter");
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setToolBar(toolbar, Constants.EMPTY_STRING);
//
////        initSearchView();
//
////        srl=(SwipeRefreshLayout)contentView.findViewById(srl);
//        recyclerView=(RecyclerView)srl.findViewById(R.id.recyclerview);
//        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onRefresh();
//            }
//        });
//
//
////        failureView.setOnClickListener(new NoDoubleClickListener() {
////            @Override
////            public void onNoDoubleClick(View v) {
////                super.onNoDoubleClick(v);
////                onRefresh();
////            }
////        });
//
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//
//
//        BaseQuickAdapter<OnlineConsultationResultBean, BaseViewHolder> mAdapter = new OnlineConsultationAdapter(new ArrayList<OnlineConsultationResultBean>());
//        this.mAdapter = (OnlineConsultationAdapter) mAdapter;
////        easyLoadMoreView = new EasyLoadMoreView();
////        mAdapter.setLoadMoreView(easyLoadMoreView);
////        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
////        mAdapter.disableLoadMoreIfNotFullPage();
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
////        Util.addDividerToRecyclerView(this, recyclerView, true);
//        recyclerView.setAdapter(mAdapter);
////        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
////            @Override
////            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
////                super.onNoDoubleItemClick(adapter, view, position);
////            }
////        });
////        ((OnlineConsultationAdapter) mAdapter).setOnItemClickListener(new OnlineConsultationAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                MaterialDialog dialog = new MaterialDialog.Builder(OnlineConsultationActivity.this)
////                        .title(R.string.hint_suggestion)
////                        .content(R.string.hint_whether_lock_hospital)
////                        .positiveText(R.string.confirm)
////                        .negativeText(R.string.cancel)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                dialog.dismiss();
////                            }
////                        }).build();
////                dialog.show();
////
////            }
////
//////            @Override
//////            public void onItemClickListener(String id, String imgUrl, View view) {
//////                startZhiHuDetailActivity(id, imgUrl, view);
//////            }
////        });
//
////        LogUtil.i("OnlineConsultationActivity initView before simulateData");
////        simulateData();
//
////        if (compositeSubscription == null) {
////            registerProjectExecutionClickEvent();
////        }
//    }
//
//    public void initSearchView() {
//        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
//        searchView.setVoiceSearch(false);
//        searchView.setHint(getResources().getString(R.string.search_hint));
////        searchView.setCursorDrawable(R.drawable.color_cursor_white);
////        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
////                        .show();
//                loadData();
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
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
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
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addOnlineConsultationActivity(new OnlineConsultationActivityModule(this))
//                .inject(this);
////        DaggerTopNewsComponent.builder()
////                .topNewsHttpModule(new TopNewsHttpModule())
////                .topNewsModule(new TopNewsModule())
////                .build().injectTopNews(this);
//    }
//
//
////    @Override
////    public void refreshView(TreatmentListBean data) {
//////        LogUtils.e("aaaacurrentIndex" + currentIndex);
////        LogUtil.i("OnlineConsultationActivity refreshView");
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////
////        treatmentList = bean.getTreatmentList();
//////        mAdapter.addData(treatmentList);
//////        index += 1;
//////        currentIndex = mAdapter.getData().size() - 2 * index;
//////        mAdapter.loadMoreComplete();
//////
////        if (isRefresh) {
////            srl.setRefreshing(false);
////            mAdapter.setEnableLoadMore(true);
////            isRefresh = false;
////            mAdapter.setNewData(treatmentList);
////        } else {
////            srl.setEnabled(true);
////            index += 20;
////            mAdapter.addData(treatmentList);
////            mAdapter.loadMoreComplete();
////        }
////
////
////    }
//
////    public void simulateData() {
////        LogUtil.i("OnlineConsultationActivity simulateData");
//////        setState(AppConstants.STATE_SUCCESS);
////
////        List<OnlineConsultationResultBean> list = SimulateData.getSimulatedOnlineConsultationList();
////        mAdapter.getData().clear();
////        mAdapter.addData(list);
////        mAdapter.notifyDataSetChanged();
////        mAdapter.loadMoreComplete();
////
////    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        unregisterProjectExecutionClickEvent();
//    }
//
//    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
////        Intent intent = new Intent();
////        intent.setClass(getActivity(), TopNewsActivity.class);
////        intent.putExtra("id", id);
////        intent.putExtra("url", imgUrl);
////        /**
////         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
////         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
////         *     <android.support.design.widget.AppBarLayout
////         android:transitionName="zhihu_detail_title"
////         android:fitsSystemWindows="true">
////         */
////        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
////                view, getActivity().getResources().getString(R.string.zhihu_detail_title));
////        getActivity().startActivity(intent, options.toBundle());
//    }
//
//
//    @Override
//    public void onRefresh() {
//        currentPage = Constants.DEFAULT_INITIAL_PAGE;
//        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
//        LogUtil.i("OnlineConsultationActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
////        TreatmentListRequestBean bean = getRequestBean();
////        mPresenter.main(bean, false);
//
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, false);
//    }
//
//
//    @Override
//    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
//        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
////        // mQuickAdapter.setAutoLoadMoreSize(int);
////        if (currentIndex >= 60) {
////            mAdapter.loadMoreEnd();
////            srl.setEnabled(true);
////        } else {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//        LogUtil.i("OnlineConsultationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
////        srl.setEnabled(false);
////        }
//    }
//
//    public List<OnlineConsultationResultBean> getListFromResult(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList) {
//        if (beanList != null) {
//            List<OnlineConsultationResultBean> list = beanList.getData();
//            return list;
//        } else {
//            return null;
//        }
//    }
//
////    public int getPageFromResult(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList) {
////        if (beanList!=null){
////            List<OnlineConsultationResultBean> wrapperBean = beanList.getData();
////            if (wrapperBean!=null){
////                return wrapperBean.getPage();
////            } else {
////                return Constants.DEFAULT_INITIAL_PAGE;
////            }
////        } else {
////            return Constants.DEFAULT_INITIAL_PAGE;
////        }
////    }
//
////    public int getPageSizeFromResult(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList){
////        if (beanList!=null){
////            OnlineConsultationResultWrapperBean wrapperBean = beanList.getData();
////            if (wrapperBean!=null){
//////                List<OnlineConsultationResultBean> list = wrapperBean.getList();
//////                int size = ListUtils.getSize(list);
//////                return size;
////                return wrapperBean.getSize();
////            } else {
////                return Constants.DEFAULT_PAGE_SIZE;
////            }
////        } else {
////            return Constants.DEFAULT_PAGE_SIZE;
////        }
////    }
//
//    public int getListSizeFromResult(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList) {
//        if (beanList != null) {
//            List<OnlineConsultationResultBean> list = beanList.getData();
//            if (list != null) {
//                int size = ListUtils.getSize(list);
//                return size;
//            } else {
//                return 0;
//            }
//        } else {
//            return 0;
//        }
//    }
//
//    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
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
//    }
//
//    private void showListOrFailureView(boolean success){
//        if (success){
//            srl.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);
//        } else {
//            srl.setVisibility(View.GONE);
//            failureView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList) {
////        List<OnlineConsultationResultBean> simulatedList = SimulateData.getSimulatedTreatmentResultList();
////        OnlineConsultationResultWrapperBean wrapperBean = new OnlineConsultationResultWrapperBean(1, 20, simulatedList);
////        beanList.setData(wrapperBean);
//
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("OnlineConsultationActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);
//
//        List<OnlineConsultationResultBean> list = getListFromResult(beanList);
//        mAdapter.setNewData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(list));
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
////        mAdapter.setEnableLoadMore(true);
//        isRefresh = false;
//
//
////        mAdapter.disableLoadMoreIfNotFullPage();
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<OnlineConsultationResultBean>> beanList) {
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////
////        treatmentList = bean.getTreatmentList();
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("OnlineConsultationActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<OnlineConsultationResultBean> list = getListFromResult(beanList);
//        mAdapter.addData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//
////        mAdapter.disableLoadMoreIfNotFullPage();
//
////        mAdapter.loadMoreComplete();
////        mAdapter.disableLoadMoreIfNotFullPage();
//
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(this.pageSize, listSize, true);
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
////        LogUtil.i("OnlineConsultationActivity onLoadListFailure,message:"+message);
////        simulate();
//
//        showRecyclerViewOrFailureView(false, true);
//
//
//        if (isLoadMore){
//            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
//                --currentPage;
//            }
//
//        } else {
//
//        }
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0, 0, false);
//        showListOrFailureView(false);
//////        Util.showToast(this, message);
//        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//    }
//
//    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
//        if (success){
//            if (isListEmpty){
//                srl.setVisibility(View.GONE);
//                failureView.setVisibility(View.VISIBLE);
//                tvFailureView.setText(getListEmptyHint());
//            } else {
//                srl.setVisibility(View.VISIBLE);
//                failureView.setVisibility(View.GONE);
//            }
//        } else {
//            srl.setVisibility(View.GONE);
//            failureView.setVisibility(View.VISIBLE);
//            tvFailureView.setText(getFailureHint());
//
//        }
//    }
//
//    private String getListEmptyHint(){
//        return getResources().getString(R.string.hint_no_consultation);
//    }
//
//    private String getFailureHint(){
//        return getResources().getString(R.string.hint_load_failure);
//    }
//
//
//    private void simulate() {
//        List<OnlineConsultationResultBean> list = SimulateData.getSimulatedOnlineConsultationList();
//        HttpResultBaseBean<List<OnlineConsultationResultBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//    }
//
//}
