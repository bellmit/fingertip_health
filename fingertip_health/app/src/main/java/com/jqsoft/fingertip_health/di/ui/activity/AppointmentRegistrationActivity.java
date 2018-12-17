//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.AppointmentRegistrationAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.AreaBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.response_new.AppointmentRegistrationResultBean;
//import com.jqsoft.fingertip_health.di.contract.AppointmentRegistrationActivityContract;
//import com.jqsoft.fingertip_health.di.module.AppointmentRegistrationActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.AppointmentRegistrationActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
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
//
////预约挂号
//public class AppointmentRegistrationActivity extends AbstractActivity implements
//        AppointmentRegistrationActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
//        BaseQuickAdapter.RequestLoadMoreListener, AMapLocationListener {
//
//    @BindView(R.id.appointment_registration_title)
//    TextView tvTitle;
//
//    @BindView(R.id.view_search)
//    MaterialSearchView searchView;
//
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @Inject
//    AppointmentRegistrationActivityPresenter mPresenter;
//
//    private boolean isRefresh = false;
//
//    private int type;
//
//    private AppointmentRegistrationAdapter mAdapter;
////    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
////    private EasyLoadMoreView easyLoadMoreView;
//
//    public boolean hasGotCurrentLocation = false;
//    public boolean hasGotHttpResult = false;
//
//    private AMapLocationClient mLocationClient;
//    private AMapLocationClientOption mLocationOption;
//    public AMapLocation mCurrentLocation;
//
//
////    private CompositeSubscription compositeSubscription;
//
//    private String keywordString;
//
//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
//
//    public void showDistance() {
//        if (hasGotCurrentLocation && hasGotHttpResult && mCurrentLocation!=null) {
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    private void destroyLocation() {
//        if (mLocationClient != null) {
//            mLocationClient.unRegisterLocationListener(this);
//            mLocationClient.onDestroy();
//        }
//    }
//
//    private void initLocation() {
//        mLocationOption = new AMapLocationClientOption();
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setOnceLocation(true);
//        mLocationClient = new AMapLocationClient(this.getApplicationContext());
//        mLocationClient.setLocationListener(this);
//        mLocationClient.startLocation();
//    }
//
//    @Override
//    public void onLocationChanged(AMapLocation aMapLocation) {
//        LogUtil.i("location error code:" + aMapLocation.getErrorCode());
//        if (aMapLocation == null || aMapLocation.getErrorCode() != AMapLocation.LOCATION_SUCCESS) {
//            return;
//        }
//        mCurrentLocation = aMapLocation;
//        hasGotCurrentLocation = true;
//        showDistance();
//        mLocationClient.stopLocation();
//    }
//
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
////        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM, AppointmentRegistrationResultItemBean.class)
////                .subscribe(new Action1<AppointmentRegistrationResultItemBean>() {
////                    @Override
////                    public void call(AppointmentRegistrationResultItemBean bean) {
////                        handleExecutionButtonClick(bean);
//////                        Util.showAlert(AppointmentRegistrationActivity.this, "提示", "您点击了执行" + bean.getServiceContentID());
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
//
////    public void handleExecutionButtonClick(AppointmentRegistrationResultItemBean item) {
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
////        TreatmentListRequestBean bean = ParametersFactory.getTreatmentListRequestBean(keyword, area, currentPage, pageSize);
////        return bean;
////    }
//
//    public Map<String, String> getRequestMap() {
//        String organizationKey = Identity.getOrganizationKey();
//        String userId = Identity.getUserId();
//        String keyword = getKeywordString();
//        Map<String, String> map = ParametersFactory.getAppointmentRegistrationListMap(organizationKey, userId, keyword, currentPage, pageSize);
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
//        return R.layout.activity_appointment_registration;
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
//        LogUtil.i("AppointmentRegistrationActivity onCreateOptionsMenu called");
////        reassignToolbar();
//        menu.clear();
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        LogUtil.i("AppointmentRegistrationActivity onOptionsItemSelected");
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
//        LogUtil.i("AppointmentRegistrationActivity initView enter");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setToolBar(toolbar, "");
//
//        initLocation();
//
//
//        initSearchView();
//
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//
//
//        BaseQuickAdapter<AppointmentRegistrationResultBean, BaseViewHolder> mAdapter = new AppointmentRegistrationAdapter(this, new ArrayList<AppointmentRegistrationResultBean>());
//        this.mAdapter = (AppointmentRegistrationAdapter) mAdapter;
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
////        ((AppointmentRegistrationAdapter) mAdapter).setOnItemClickListener(new AppointmentRegistrationAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                MaterialDialog dialog = new MaterialDialog.Builder(AppointmentRegistrationActivity.this)
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
////        LogUtil.i("AppointmentRegistrationActivity initView before simulateData");
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
//                .addAppointmentRegistrationActivity(new AppointmentRegistrationActivityModule(this))
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
////        LogUtil.i("AppointmentRegistrationActivity refreshView");
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
//        destroyLocation();
//
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
//        LogUtil.i("AppointmentRegistrationActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
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
//        LogUtil.i("AppointmentRegistrationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
////        }
//    }
//
//    public List<AppointmentRegistrationResultBean> getListFromResult(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList) {
//        if (beanList != null) {
//            List<AppointmentRegistrationResultBean> list = beanList.getData();
//            return list;
//        } else {
//            return null;
//        }
//    }
//
////    public int getPageFromResult(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList) {
////        if (beanList!=null){
////            List<AppointmentRegistrationResultBean> wrapperBean = beanList.getData();
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
////    public int getPageSizeFromResult(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList){
////        if (beanList!=null){
////            AppointmentRegistrationResultWrapperBean wrapperBean = beanList.getData();
////            if (wrapperBean!=null){
//////                List<AppointmentRegistrationResultBean> list = wrapperBean.getList();
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
//    public int getListSizeFromResult(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList) {
//        if (beanList != null) {
//            List<AppointmentRegistrationResultBean> list = beanList.getData();
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
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList) {
////        List<AppointmentRegistrationResultBean> simulatedList = SimulateData.getSimulatedTreatmentResultList();
////        AppointmentRegistrationResultWrapperBean wrapperBean = new AppointmentRegistrationResultWrapperBean(1, 20, simulatedList);
////        beanList.setData(wrapperBean);
//
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("AppointmentRegistrationActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);
//
//        List<AppointmentRegistrationResultBean> list = getListFromResult(beanList);
//        mAdapter.setNewData(list);
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
////        mAdapter.setEnableLoadMore(true);
//        isRefresh = false;
//
//        hasGotHttpResult = true;
//        showDistance();
//
//
////        mAdapter.disableLoadMoreIfNotFullPage();
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<AppointmentRegistrationResultBean>> beanList) {
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////
////        treatmentList = bean.getTreatmentList();
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("AppointmentRegistrationActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<AppointmentRegistrationResultBean> list = getListFromResult(beanList);
//        mAdapter.addData(list);
////        mAdapter.disableLoadMoreIfNotFullPage();
//
////        mAdapter.loadMoreComplete();
////        mAdapter.disableLoadMoreIfNotFullPage();
//
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(this.pageSize, listSize, true);
//
//        hasGotHttpResult = true;
//        showDistance();
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
////        LogUtil.i("AppointmentRegistrationActivity onLoadListFailure,message:"+message);
//        simulate();
//
////        if (isLoadMore){
////            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
////                --currentPage;
////            }
////
////        } else {
////
////        }
////        srl.setRefreshing(false);
////        setLoadMoreStatus(0, 0, false);
////////        Util.showToast(this, message);
////        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//    }
//
//    private void simulate() {
//        List<AppointmentRegistrationResultBean> list = SimulateData.getAppointmentRegistrationResultBeanList();
//        HttpResultBaseBean<List<AppointmentRegistrationResultBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//        hasGotHttpResult = true;
//        showDistance();
//
//    }
//
//}
