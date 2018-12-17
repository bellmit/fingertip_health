//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.support.annotation.NonNull;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.TextView;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.SignApplicationAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
//import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
//import com.jqsoft.fingertip_health.di.contract.SignApplicationActivityContract;
//import com.jqsoft.fingertip_health.di.module.SignApplicationActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.SignApplicationActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
////居民签约申请
//public class SignApplicationActivity extends AbstractActivity implements
//        SignApplicationActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
//        BaseQuickAdapter.RequestLoadMoreListener {
//
//    @BindView(R.id.sign_application_title)
//    TextView tvTitle;
//
////    @BindView(R.id.view_search)
////    MaterialSearchView searchView;
//
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.lay_content)
//    SwipeRefreshLayout srl;
//
////    @BindView(R.id.srl)
////    SwipeRefreshLayout srl;
//
//    @BindView(R.id.lay_sign_application_load_failure)
//    View failureView;
//
//    TextView tvFailureView;
//
//
//    @Inject
//    SignApplicationActivityPresenter mPresenter;
//
//    private boolean isRefresh = false;
//
//
//    private SignApplicationAdapter mAdapter;
////    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
////    private EasyLoadMoreView easyLoadMoreView;
//
//    private IndexAndOnlineSignInitialData processingBean;
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
//    public void registerSignOrCancelClickEvent() {
//        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_SIGN_BUTTON, IndexAndOnlineSignInitialData.class)
//                .subscribe(new Action1<IndexAndOnlineSignInitialData>() {
//                    @Override
//                    public void call(IndexAndOnlineSignInitialData bean) {
//                        SignApplicationActivity.this.processingBean = bean;
//                        handleSignButtonClick(bean);
////                        Util.showAlert(SignApplicationActivity.this, "提示", "您点击了执行" + bean.getServiceContentID());
//                    }
//                });
//        Subscription mCancelSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_CANCEL_BUTTON, IndexAndOnlineSignInitialData.class)
//                .subscribe(new Action1<IndexAndOnlineSignInitialData>() {
//                    @Override
//                    public void call(IndexAndOnlineSignInitialData IndexAndOnlineSignInitialData) {
//                        SignApplicationActivity.this.processingBean = IndexAndOnlineSignInitialData;
//                        handleCancelButtonClick(IndexAndOnlineSignInitialData);
//                    }
//                });
//        if (this.compositeSubscription == null) {
//            compositeSubscription = new CompositeSubscription();
//        }
//        compositeSubscription.add(mSubscription);
//        compositeSubscription.add(mCancelSubscription);
//    }
//
//    public void unregisterSignOrCancelClickEvent() {
//        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
//            compositeSubscription.unsubscribe();
//        }
//    }
//
//    public void handleSignButtonClick(final IndexAndOnlineSignInitialData item) {
//        MaterialDialog dialog = new MaterialDialog.Builder(this)
//                .title(R.string.hint_suggestion)
//                .content(R.string.hint_confirm_to_sign)
//                .positiveText(R.string.confirm)
//                .negativeText(R.string.cancel)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        Identity.shouldReadIdCard=false;
//                        RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_TRANSACT);
//                        RxBus.getDefault().post(Constants.EVENT_TYPE_ONLINE_SIGN_PER_FRAGMENT_INITIALIZE, (IndexAndOnlineSignInitialData) item);
//                        dialog.dismiss();
//                    }
//                }).build();
//        dialog.show();
//
//
//    }
//    //IndexAndOnlineSignInitialData
//    public void handleCancelButtonClick(final IndexAndOnlineSignInitialData item) {
//        MaterialDialog dialog = new MaterialDialog.Builder(this)
//                .title(R.string.hint_suggestion)
//                .content(R.string.hint_confirm_to_cancel_application)
//                .positiveText(R.string.confirm)
//                .negativeText(R.string.cancel)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                        Map<String, String> map = getCancelSignApplicationMap(item);
//                        mPresenter.cancelSignApplication(map);
//                    }
//                }).build();
//        dialog.show();
//
//    }
//
//
//    public String getKeywordString() {
//        return Util.trimString(keywordString);
//    }
//
//
////    public TreatmentListRequestBean getRequestBean(){
////        String keyword = getKeywordString();
////        String area = getArea();
////        TreatmentListRequestBean bean = ParametersFactory.getTreatmentListRequestBean(keyword, area, currentPage, pageSize);
////        return bean;
////    }
//
//    public Map<String, String> getRequestMap() {
////        String organizationKey = Identity.getOrganizationKey();
////        String userId = Identity.getUserId();
//        Map<String, String> map = ParametersFactory.getSignApplicationListMap(this, currentPage, pageSize);
//        return map;
//    }
//
//    public Map<String, String> getCancelSignApplicationMap(IndexAndOnlineSignInitialData bean) {
//        if (bean != null) {
//            String applyKey = Util.trimString(bean.getApplyKey());
//            String cardNo = Util.trimString(bean.getCardNo());
//            String applyCancel = Constants.EMPTY_STRING;
//            String finishDoctor = Identity.getUserId();
//            Map<String, String> map = ParametersFactory.getCancelSignApplicationMap(this, applyKey, cardNo, applyCancel, finishDoctor);
//            return map;
//        } else {
//            return new HashMap<>();
//        }
//    }
//
////    @Override
////    public int getContentLayoutId() {
////        return R.layout.layout_recyclerview;
////    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_sign_application;
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
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        LogUtil.i("SignApplicationActivity onCreateOptionsMenu called");
//////        reassignToolbar();
////        menu.clear();
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu_search, menu);
////        MenuItem item = menu.findItem(R.id.action_search);
////        searchView.setMenuItem(item);
////
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        LogUtil.i("SignApplicationActivity onOptionsItemSelected");
////        switch (item.getItemId()) {
////            case R.id.action_search:
////                //code here
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
//
//    @Override
//    protected void initView() {
//        LogUtil.i("SignApplicationActivity initView enter");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setToolBar(toolbar, "");
//
////        initSearchView();
//
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
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//
//
//        BaseQuickAdapter<IndexAndOnlineSignInitialData, BaseViewHolder> mAdapter = new SignApplicationAdapter(new ArrayList<IndexAndOnlineSignInitialData>());
//        this.mAdapter = (SignApplicationAdapter) mAdapter;
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
////        ((SignApplicationAdapter) mAdapter).setOnItemClickListener(new SignApplicationAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                MaterialDialog dialog = new MaterialDialog.Builder(SignApplicationActivity.this)
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
////        LogUtil.i("SignApplicationActivity initView before simulateData");
////        simulateData();
//
//        if (compositeSubscription == null) {
//            registerSignOrCancelClickEvent();
//        }
//    }
//
////    public void initSearchView() {
////        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
////        searchView.setVoiceSearch(false);
////        searchView.setHint(getResources().getString(R.string.search_hint));
//////        searchView.setCursorDrawable(R.drawable.color_cursor_white);
//////        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
////
////        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
//////                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
//////                        .show();
////                loadData();
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                //Do some magic
////                keywordString = Util.trimString(newText);
////                return false;
////            }
////        });
////
////        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
////            @Override
////            public void onSearchViewShown() {
////                //Do some magic
//////                ToastUtil.show(getActivity(), "searchview show");
////            }
////
////            @Override
////            public void onSearchViewClosed() {
////                //Do some magic
////            }
////        });
////    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addSignApplicationActivity(new SignApplicationActivityModule(this))
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
////        LogUtil.i("SignApplicationActivity refreshView");
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
//    public void simulateData() {
//        LogUtil.i("TreatmentFragemnt simulateData");
////        setState(AppConstants.STATE_SUCCESS);
//
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////        treatmentList = bean.getTreatmentList();
////        mAdapter.getData().clear();
////        mAdapter.addData(treatmentList);
////        mAdapter.notifyDataSetChanged();
////        mAdapter.loadMoreComplete();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterSignOrCancelClickEvent();
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
//        LogUtil.i("SignApplicationActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
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
//        LogUtil.i("SignApplicationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
////        srl.setEnabled(false);
////        }
//    }
//
//    public List<IndexAndOnlineSignInitialData> getListFromResult(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList) {
//        if (beanList != null) {
//            List<IndexAndOnlineSignInitialData> list = beanList.getData();
//            return list;
//        } else {
//            return null;
//        }
//    }
//
////    public int getPageFromResult(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList) {
////        if (beanList!=null){
////            List<IndexAndOnlineSignInitialData> wrapperBean = beanList.getData();
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
////    public int getPageSizeFromResult(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList){
////        if (beanList!=null){
////            SignApplicationResultWrapperBean wrapperBean = beanList.getData();
////            if (wrapperBean!=null){
//////                List<IndexAndOnlineSignInitialData> list = wrapperBean.getList();
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
//    public int getListSizeFromResult(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList) {
//        if (beanList != null) {
//            List<IndexAndOnlineSignInitialData> list = beanList.getData();
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
//
//        //一次下载所有数据
//        mAdapter.setEnableLoadMore(false);
//    }
//
//    public void addPositionToList(List<IndexAndOnlineSignInitialData> list) {
//        if (!ListUtils.isEmpty(list)) {
//            for (int i = 0; i < list.size(); ++i) {
//                IndexAndOnlineSignInitialData bean = list.get(i);
//                bean.setPosition(i);
//            }
//        }
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList) {
////        List<IndexAndOnlineSignInitialData> simulatedList = SimulateData.getSimulatedTreatmentResultList();
////        SignApplicationResultWrapperBean wrapperBean = new SignApplicationResultWrapperBean(1, 20, simulatedList);
////        beanList.setData(wrapperBean);
//
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("SignApplicationActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);
//
//        List<IndexAndOnlineSignInitialData> list = getListFromResult(beanList);
//        mAdapter.setNewData(list);
//        addPositionToList(mAdapter.getData());
//
////        showRecyclerViewOrFailureView(true, true);
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//
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
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> beanList) {
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////
////        treatmentList = bean.getTreatmentList();
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("SignApplicationActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<IndexAndOnlineSignInitialData> list = getListFromResult(beanList);
//        mAdapter.addData(list);
//        addPositionToList(mAdapter.getData());
//
////        showRecyclerViewOrFailureView(true, true);
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
////        LogUtil.i("SignApplicationActivity onLoadListFailure,message:"+message);
////        simulate();
//
//        showRecyclerViewOrFailureView(false, true);
//
//        if (isLoadMore) {
//            if (currentPage > Constants.DEFAULT_INITIAL_PAGE) {
//                --currentPage;
//            }
//
//        } else {
//
//        }
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0, 0, false);
//////        Util.showToast(this, message);
//        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//    }
//
//    @Override
//    public void onCancelSignApplicationSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//        removeProcessingPosition();
//        RxBus.getDefault().post(Constants.EVENT_TYPE_REFRESH_INTELLIGENT_HONOUR_AGREEMENT_REMIND, true);
//        Util.showToast(this, Constants.HINT_CANCEL_SIGN_APPLICATION_SUCCESS);
//    }
//
//    @Override
//    public void onCancelSignApplicationFailure(String message) {
////        removeProcessingPosition();
//        Util.showToast(this, Constants.HINT_CANCEL_SIGN_APPLICATION_FAILURE);
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
//        String s = getResources().getString(R.string.hint_no_sign_application_info_please_click_to_reload);
//        return s;
////        return getResources().getString(R.string.hint_list_empty_please_reload);
//    }
//
//    private String getFailureHint(){
//        return getResources().getString(R.string.hint_load_failure);
//    }
//
//    private void removeProcessingPosition() {
//        int position = getProcessingPosition();
//        if (position != -1) {
//            mAdapter.remove(position);
//            addPositionToList(mAdapter.getData());
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    private int getProcessingPosition() {
//        if (processingBean != null) {
//            return processingBean.getPosition();
//        } else {
//            return -1;
//        }
//    }
//
//    private void simulate() {
////        List<IndexAndOnlineSignInitialData> list = SimulateData.getIndexAndOnlineSignInitialDataList();
////        HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> wrapper = new HttpResultBaseBean<>("0", "success", list);
////        onLoadMoreListSuccess(wrapper);
//
//    }
//
//}
