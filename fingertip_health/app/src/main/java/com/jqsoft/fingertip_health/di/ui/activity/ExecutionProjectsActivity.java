package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ExecutionProjectsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.AreaBean;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultItemBean;
import com.jqsoft.fingertip_health.di.contract.ExecutionProjectsActivityContract;
import com.jqsoft.fingertip_health.di.module.ExecutionProjectsActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ExecutionProjectsActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.simulate.SimulateData;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//近7天需要执行项目  超时未执行项目
public class ExecutionProjectsActivity extends AbstractActivity implements
        ExecutionProjectsActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.execution_projects_title)
    TextView tvTitle;

    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_execution_projects_load_failure)
    View failureView;

    TextView tvFailureView;

    @Inject
    ExecutionProjectsActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private ExecutionProjectsAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;

    private CompositeSubscription compositeSubscription;

    private String keywordString;

    String cardNo;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;

    private int pageSize = Constants.EXECUTION_PROJECTS_PAGE_SIZE;

    @Override
    protected void loadData() {
//        onRefresh();

//        currentPage = Constants.DEFAULT_INITIAL_PAGE;//为了以后写下拉刷新 不然可以直接用这个方法。
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);
    }

    public void registerProjectExecutionClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM, ExecutionProjectsResultItemBean.class)
                .subscribe(new Action1<ExecutionProjectsResultItemBean>() {
                    @Override
                    public void call(ExecutionProjectsResultItemBean bean) {
                        handleExecutionButtonClick(bean);
//                        Util.showAlert(ExecutionProjectsActivity.this, "提示", "您点击了执行" + bean.getServiceContentID());
                    }
                });
        if (this.compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(mSubscription);
    }

    public void unregisterProjectExecutionClickEvent() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    public void handleExecutionButtonClick(final ExecutionProjectsResultItemBean item) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.hint_suggestion)
                .content(R.string.hint_confirm_to_execute)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        item.getParentBean();
                        PeopleBaseInfoBean mpeopleBasebean;
                        PendExecuBeanList mPendExecuBeanList;
                        mpeopleBasebean=item.getParentBean().getSignUserInfo();
                        mPendExecuBeanList=item.getSignPromExec();


                        Intent intent = new Intent(ExecutionProjectsActivity.this,ExecuProjectActivity.class);
                        intent.putExtra("address_shangmen", "");
                        intent.putExtra("address_cunsi", "");
                        intent.putExtra("address_xiangz", "");
                        intent.putExtra("address_other", "");
                        intent.putExtra("sEtaddressother", "");
                        intent.putExtra("flag", "1");
                        intent.putExtra("orderServiceId", "");

                        intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
                        intent.putExtra("PendExecuBeanList", (Serializable)mPendExecuBeanList);
                        startActivity(intent);

                    }
                }).build();
        dialog.show();

    }


    public String getKeywordString() {
        return Util.trimString(keywordString);
    }

    public String getArea() {
        if (!ListUtils.isEmpty(Identity.area)) {
            AreaBean ab = Identity.area.get(0);
            return ab.getCode();
        } else {
            return Constants.EMPTY_STRING;
        }

    }

//    public TreatmentListRequestBean getRequestBean(){
//        String keyword = getKeywordString();
//        String area = getArea();
//        TreatmentListRequestBean bean = ParametersFactory.getTreatmentListRequestBean(keyword, area, currentPage, pageSize);
//        return bean;
//    }

    public Map<String, String> getRequestMap() {
        String organizationKey = Identity.getOrganizationKey();
        String userId = Identity.getUserId();
        String item = Constants.TYPE_DETAIL;
        String type = String.valueOf(this.type);
        String keyword = getKeywordString();
        String ultimateKeyword = Constants.EMPTY_STRING;
        if (StringUtils.isBlank(keyword)){
            ultimateKeyword=cardNo;
//            ultimateKeyword=Identity.getCardNo();
        } else {
            ultimateKeyword=keyword;
        }
        String userName = ultimateKeyword;
        String cardNumber = ultimateKeyword;
//        Map<String, String> map = ParametersFactory.getExecutionProjectsListMap(organizationKey, userId, item, type, userName, cardNumber, currentPage, pageSize);
        Map<String, String> map = ParametersFactory.getLatest7DaysExecutionProjectsListMap(this, cardNumber);
        return map;
    }

//    @Override
//    public int getContentLayoutId() {
//        return R.layout.layout_recyclerview;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_need_execute_projects;
    }

    @Override
    protected void initData() {
        cardNo=getDeliveredStringByKey(Constants.CARD_NO_KEY);
    }

//    @Override
//    public int setFrameLayoutId() {
//        return R.id.framelayout;
//    }

//    @Override
//    protected void initUI() {
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setToolBar(toolbar, "");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.i("ExecutionProjectsActivity onCreateOptionsMenu called");
//        reassignToolbar();
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i("ExecutionProjectsActivity onOptionsItemSelected");
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
        LogUtil.i("ExecutionProjectsActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        type = getDeliveredIntByKey(Constants.EXECUTION_PROJECTS_TYPE_KEY);
        String title = Util.getExecutionProjectsActivityTitle(type);
        tvTitle.setText(title);

        initSearchView();

//        String failureString = Util.getExecutionProjectsActivityHintTitle(this, type);
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


        BaseQuickAdapter<ExecutionProjectsResultBean, BaseViewHolder> mAdapter = new ExecutionProjectsAdapter(new ArrayList<ExecutionProjectsResultBean>());
        this.mAdapter = (ExecutionProjectsAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter.setEnableLoadMore(false);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
//        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//            }
//        });
//        ((ExecutionProjectsAdapter) mAdapter).setOnItemClickListener(new ExecutionProjectsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MaterialDialog dialog = new MaterialDialog.Builder(ExecutionProjectsActivity.this)
//                        .title(R.string.hint_suggestion)
//                        .content(R.string.hint_whether_lock_hospital)
//                        .positiveText(R.string.confirm)
//                        .negativeText(R.string.cancel)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                            }
//                        }).build();
//                dialog.show();
//
//            }
//
////            @Override
////            public void onItemClickListener(String id, String imgUrl, View view) {
////                startZhiHuDetailActivity(id, imgUrl, view);
////            }
//        });

//        LogUtil.i("ExecutionProjectsActivity initView before simulateData");
//        simulateData();

        if (compositeSubscription == null) {
            registerProjectExecutionClickEvent();
        }
    }

    public void initSearchView() {
        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setHint(getResources().getString(R.string.search_hint));
//        searchView.setCursorDrawable(R.drawable.color_cursor_white);
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

        searchView.setOnQueryTextListener(new MaterialSearchViewNew.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
//                        .show();
//                loadData();
                keywordString=Util.trimString(query);
                onRefresh();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                keywordString = Util.trimString(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchViewNew.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
//                ToastUtil.show(getActivity(), "searchview show");
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addExecutionProjectsActivity(new ExecutionProjectsActivityModule(this))
                .inject(this);
//        DaggerTopNewsComponent.builder()
//                .topNewsHttpModule(new TopNewsHttpModule())
//                .topNewsModule(new TopNewsModule())
//                .build().injectTopNews(this);
    }


//    @Override
//    public void refreshView(TreatmentListBean data) {
////        LogUtils.e("aaaacurrentIndex" + currentIndex);
//        LogUtil.i("ExecutionProjectsActivity refreshView");
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
        LogUtil.i("ExecutionProjectsActivity simulateData");
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
        unregisterProjectExecutionClickEvent();
    }

    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
//        Intent intent = new Intent();
//        intent.setClass(getActivity(), TopNewsActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("url", imgUrl);
//        /**
//         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
//         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
//         *     <android.support.design.widget.AppBarLayout
//         android:transitionName="zhihu_detail_title"
//         android:fitsSystemWindows="true">
//         */
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                view, getActivity().getResources().getString(R.string.zhihu_detail_title));
//        getActivity().startActivity(intent, options.toBundle());
    }


    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        LogUtil.i("ExecutionProjectsActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);

        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);
    }


    @Override
    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//        // mQuickAdapter.setAutoLoadMoreSize(int);
//        if (currentIndex >= 60) {
//            mAdapter.loadMoreEnd();
//            srl.setEnabled(true);
//        } else {
        ++currentPage;
        Map<String, String> map = getRequestMap();
        mPresenter.main(map, true);
        LogUtil.i("ExecutionProjectsActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
//        }
    }

    public List<ExecutionProjectsResultBean> getListFromResult(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {
        if (beanList != null) {
            List<ExecutionProjectsResultBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

//    public int getPageFromResult(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {
//        if (beanList!=null){
//            List<ExecutionProjectsResultBean> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getPage();
//            } else {
//                return Constants.DEFAULT_INITIAL_PAGE;
//            }
//        } else {
//            return Constants.DEFAULT_INITIAL_PAGE;
//        }
//    }

//    public int getPageSizeFromResult(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList){
//        if (beanList!=null){
//            ExecutionProjectsResultWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
////                List<ExecutionProjectsResultBean> list = wrapperBean.getList();
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

    public int getListSizeFromResult(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {
        if (beanList != null) {
            List<ExecutionProjectsResultBean> list = beanList.getData();
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

    public int getProjectListSizeFromResult(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {
        if (beanList != null) {
            List<ExecutionProjectsResultBean> list = beanList.getData();
            if (list != null) {
                int sum = 0;
                for (int i = 0; i < list.size(); ++i){
                    ExecutionProjectsResultBean item = list.get(i);
                    if (item!=null){
                        List<ExecutionProjectsResultItemBean> projectList = item.getList();
                        if (!ListUtils.isEmpty(projectList)){
                            sum+=projectList.size();
                        }
                    }
                }
                return sum;
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

    public void addPositionToList(List<ExecutionProjectsResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); ++i) {
                ExecutionProjectsResultBean bean = list.get(i);
                bean.setPosition(i);
            }
        }
    }


    @Override
    public void onLoadListSuccess(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {

//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
        int listSize = getProjectListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("ExecutionProjectsActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

        List<ExecutionProjectsResultBean> list = getListFromResult(beanList);
        mAdapter.setNewData(list);
        addPositionToList(mAdapter.getData());

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;


//        mAdapter.disableLoadMoreIfNotFullPage();



//        simulate();

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<ExecutionProjectsResultBean>> beanList) {
//        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
//
//        treatmentList = bean.getTreatmentList();
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
        int listSize = getProjectListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("ExecutionProjectsActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<ExecutionProjectsResultBean> list = getListFromResult(beanList);
        mAdapter.addData(list);
        addPositionToList(mAdapter.getData());

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

//        mAdapter.disableLoadMoreIfNotFullPage();

//        mAdapter.loadMoreComplete();
//        mAdapter.disableLoadMoreIfNotFullPage();

        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
//        LogUtil.i("ExecutionProjectsActivity onLoadListFailure,message:"+message);
//        simulate();

        showRecyclerViewOrFailureView(false, true);
//        showRecyclerViewOrFailureView(true, false);

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

    @Override
    public void onLoadRemindDataSuccess(HttpResultBaseBean<List<RemindBean>> bean) {

    }

    @Override
    public void onLoadRemindDataFailure(String message) {

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
        String result = Util.getExecutionProjectsActivityHintTitle(this, type);
        return result;

//        return getResources().getString(R.string.hint_list_empty_please_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_failure);
    }

    @Override
    protected void onResume() {
        super.onResume();

        onRefresh();

    }


    private void simulate() {
        List<ExecutionProjectsResultBean> list = SimulateData.getExecutionProjectsResultBeanList();
        HttpResultBaseBean<List<ExecutionProjectsResultBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
        onLoadMoreListSuccess(wrapper);

    }

}
