package com.jqsoft.fingertip_health.di.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignedResidentDirectoryAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response.SignedResidentDirectoryResultBean;
import com.jqsoft.fingertip_health.di.contract.SignedResidentDirectoryFragmentContract;
import com.jqsoft.fingertip_health.di.module.SignedResidentDirectoryFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.SignedResidentDirectoryFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

//签约居民通讯录
public class SignedResidentDirectoryFragment extends AbstractFragment implements
        SignedResidentDirectoryFragmentContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
//    @BindView(R.id.view_search_heyiban)
//    MaterialSearchView searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    @BindView(R.id.lay_signed_resident_directory_load_failure)
    View failureView;

    TextView tvFailureView;



    @Inject
    SignedResidentDirectoryFragmentPresenter mPresenter;

    private boolean isRefresh = false;


    private SignedResidentDirectoryAdapter mAdapter;
    private List<SignedResidentDirectoryResultBean> directoryList=new ArrayList<>();
//    private EasyLoadMoreView easyLoadMoreView;

    private String keywordString;


    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signed_resident_directory_layout;
    }

    @Override
    protected void initData() {

    }

//    public void reassignToolbar() {
//        WorkbenchActivity workbenchActivity = (WorkbenchActivity) getActivity();
//        if (workbenchActivity != null) {
//            Toolbar toolbar = (Toolbar) workbenchActivity.findViewById(R.id.toolbar3);
//            //LogUtil.i("SignedResidentDirectoryFragment initView toolbar:" + toolbar);
//            workbenchActivity.setToolBarWithNoBackButtonAndNoTitle(toolbar);
//        }

//    }

    @Override
    protected void initView() {
        //LogUtil.i("inhospitalinspectfragment initView enter");
//        setHasOptionsMenu(true);

//        reassignToolbar();

////        searchView = (MaterialSearchView) findViewById(R.id.search_view);
//        searchView.setVoiceSearch(false);
////        searchView.setCursorDrawable(R.drawable.color_cursor_white);
////        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
////                        .show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do some magic
//                keywordString=Util.trimString(newText);
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

        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        mAdapter = new SignedResidentDirectoryAdapter(new ArrayList<SignedResidentDirectoryResultBean>());
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter.setEnableLoadMore(false);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                SignedResidentDirectoryResultBean bean = mAdapter.getItem(position);
//                if (bean!=null){
//                    String toId = Util.trimString(bean.getUid());
//                    getTargetUserInfoAndGotoChatActivity(toId, new IMProcessSuccessListener() {
//                        @Override
//                        public void onIMProcessSuccess(UserInfo userInfo) {
//                            gotoChatActivity(userInfo);
//                        }
//                    });
////                    gotoChatActivity(toId);
//                } else {
//                    Util.showToast(getActivity(), Constants.HINT_THE_FRIEND_INFO_EMPTY);
//                }
////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.CHAT_ACTIVITY_NAME);

            }
        });
//        ((SignedResidentDirectoryAdapter) mAdapter).setOnItemClickListener(new SignedResidentDirectoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.CHAT_ACTIVITY_NAME);
////                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
////                        .title(R.string.hint_suggestion)
////                        .content(R.string.hint_select_one_item)
////                        .positiveText(R.string.confirm)
////                        .negativeText(R.string.cancel)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                dialog.dismiss();
////                            }
////                        }).build();
////                dialog.show();
//
//            }
//
////            @Override
////            public void onItemClickListener(String id, String imgUrl, View view) {
////                startZhiHuDetailActivity(id, imgUrl, view);
////            }
//        });

//        LogUtil.i("inhospitalinspectfragment initView before simulateData");
//        simulateData();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        //LogUtil.i("SignedResidentDirectoryFragment onCreateOptionsMenu called");
//        menu.clear();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //LogUtil.i("SignedResidentDirectoryFragment onOptionsItemSelected");
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                //code here
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addSignedResidentDirectoryFragment(new SignedResidentDirectoryFragmentModule(this))
                .inject(this);
//        DaggerTopNewsComponent.builder()
//                .topNewsHttpModule(new TopNewsHttpModule())
//                .topNewsModule(new TopNewsModule())
//                .build().injectTopNews(this);
    }

//    @Override
//    public void refreshView(HeyibanListBean data) {
////        LogUtils.e("aaaacurrentIndex" + currentIndex);
//        LogUtil.i("SignedResidentDirectoryFragment refreshView");
//        HeyibanListBean bean = SimulateData.getSimulatedHeyibanListBean();
//
//        directoryList = bean.getList();
////        mAdapter.addData(directoryList);
////        index += 1;
////        currentIndex = mAdapter.getData().size() - 2 * index;
////        mAdapter.loadMoreComplete();
////
//        if (isRefresh) {
//            srl.setRefreshing(false);
//            mAdapter.setEnableLoadMore(true);
//            isRefresh = false;
//            mAdapter.setNewData(directoryList);
//        } else {
//            srl.setEnabled(true);
//            index += 20;
//            mAdapter.addData(directoryList);
//            mAdapter.loadMoreComplete();
//        }
//
//
//    }

//    public void simulateData() {
//        //LogUtil.i("HeyibanFragemnt simulateData");
////        setState(AppConstants.STATE_SUCCESS);
//
//        HeyibanListBean bean = SimulateData.getSimulatedHeyibanListBean();
////        directoryList = bean.getList();
//        mAdapter.getData().clear();
//        mAdapter.addData(directoryList);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.loadMoreComplete();
//
//    }


//    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
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
//    }

    public String getKeywordString(){
        return Util.trimString(keywordString);
    }

    public void setKeywordString(String newKeyword){
        this.keywordString=Util.trimString(newKeyword);
    }

    public Map<String, String> getRequestMap() {
        String userId = Identity.getUserId();
        String keyword = getKeywordString();
        Map<String, String> map = ParametersFactory.getSignResidentDirectoryListMap(getActivity(), userId, keyword, currentPage, pageSize);
        return map;

    }

    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
        LogUtil.i("SignedResidentDirectoryFragment onRefresh");
        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);

    }


    @Override
    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        ++currentPage;

        Map<String, String> map = getRequestMap();
        mPresenter.main(map, true);

//        srl.setEnabled(false);
    }

    public List<SignedResidentDirectoryResultBean> getListFromResult(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList){
        if (beanList!=null){
            List<SignedResidentDirectoryResultBean> list = beanList.getData();
            if (list!=null){
                return list;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

//    public int getPageFromResult(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList) {
//        if (beanList!=null){
//            List<SignedResidentDirectoryResultBean> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getPage();
//            } else {
//                return Constants.DEFAULT_INITIAL_PAGE;
//            }
//        } else {
//            return Constants.DEFAULT_INITIAL_PAGE;
//        }
//    }
//
//    public int getPageSizeFromResult(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList){
//        if (beanList!=null){
//            List<SignedResidentDirectoryResultBean> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
////                List<TreatmentListResultBean> list = wrapperBean.getList();
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

    public int getListSizeFromResult(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList){
        if (beanList!=null){
            List<SignedResidentDirectoryResultBean> list = beanList.getData();
            if (list!=null){
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful){
        if (isSuccessful) {
            if (listSize<pageSize){
//                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
            }
//            mAdapter.loadMoreEnd(true);
        } else {
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreFail();
//            mAdapter.loadMoreEnd(true);
        }
    }

    private String getListEmptyHint(){
        String s = getResources().getString(R.string.hint_no_signed_resident_directory_please_click_to_reload);
        return s;
//        return getResources().getString(R.string.hint_list_empty_please_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_failure);
    }


    public void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
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


    @Override
    public void onLoadListSuccess(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList) {
//        HeyibanListBean bean = SimulateData.getSimulatedHeyibanListBean();

//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
        int listSize = getListSizeFromResult(beanList);
        List<SignedResidentDirectoryResultBean> list = getListFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("SignedResidentDirectoryFragment onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

//        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();

        mAdapter.setNewData(list);
        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//        showRecyclerViewOrFailureView(false, true);


        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> beanList) {
//        HeyibanListBean bean = SimulateData.getSimulatedHeyibanListBean();
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
        int listSize = getListSizeFromResult(beanList);
        List<SignedResidentDirectoryResultBean> list = getListFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("SignedResidentDirectoryFragment onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

//        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();

//        if (list!=null){
//            directoryList=list;
//        } else {
//            directoryList = new ArrayList<>();
//        }

        mAdapter.addData(list);
        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);



    }


    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
//        List<SignedResidentDirectoryResultBean> result = SimulateData.getList<SignedResidentDirectoryResultBean>();
//        HttpResultBaseBean<List<SignedResidentDirectoryResultBean>> wrapper = new HttpResultBaseBean<>("0","success", result);
//        onLoadMoreListSuccess(wrapper);
        showRecyclerViewOrFailureView(false, true);


        if (isLoadMore){
            if (currentPage> Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);

        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);
    }
}
