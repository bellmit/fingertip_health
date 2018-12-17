//package com.jqsoft.grassroots_civil_administration_platform.di.ui.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.InHospitalInspectAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.IdentityManager;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.HospitalTypeBean;
//import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
//import com.jqsoft.fingertip_health.bean.InHospitalInspectListBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.InHospitalInspectFragmentContract;
//import com.jqsoft.fingertip_health.di.module.InHospitalInspectFragmentModule;
//import com.jqsoft.fingertip_health.di.presenter.InHospitalInspectFragmentPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity;
//import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
//import com.jqsoft.fingertip_health.listener.HospitalTypeSelectListener;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
//import com.jqsoft.fingertip_health.listener.submitOnSuccessListener;
//import com.jqsoft.fingertip_health.util.Util;
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
///**
// * Created by quantan.liu on 2017/3/22.
// * 头条新闻会出现一些问题因为API不稳定。。。
// */
//
//public class InHospitalInspectFragment extends AbstractFragment implements
//        InHospitalInspectFragmentContract.View, SwipeRefreshLayout.OnRefreshListener,
//        BaseQuickAdapter.RequestLoadMoreListener, HospitalTypeSelectListener, submitOnSuccessListener {
//    private static InHospitalInspectFragment instance = null;
//    /* @BindView(R.id.fl_inhospital_inspect_hospital_type_select)
//     FrameLayout flInHospitalInspectHospitalTypeSelect;
//     @BindView(R.id.iv_inhospital_inspect_hospital_type_select)
//     ImageView ivHospitalSelect;*/
//    @BindView(R.id.view_search_in_hospital_inspect)
//    MaterialSearchView searchView;
//
//
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @Inject
//    InHospitalInspectFragmentPresenter mPresenter;
//
//    private boolean isRefresh = false;
//
//
//    private InHospitalInspectAdapter mAdapter;
//    private ArrayList<InHospitalInspectListBean.InHospitalInspectBean> inHospitalInspectList;
////    private EasyLoadMoreView easyLoadMoreView;
////    private CompositeSubscription hospitalTypeSelectSubscription;
//    /*private int currentIndex = 0;
//
//    private int index;*/
//
//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
//
//    @BindView(R.id.lay_inhospital_load_failure)
//    View failureView;
//
//    TextView tvFailureView;
//
////    @OnClick(R.id.fl_inhospital_inspect_hospital_type_select)
//    public void hospitalTypeSelectHandler() {
//       /* List<HospitalTypeBean> list = SimulateData.getSimulatedHospitalTypeBeanList();
//        Util.showSelectHospitalTypePopupWindow(getActivity(), ivHospitalSelect, list, this);*/
//    }
//
//    public void registerHospitalTypeSelectEvent() {
////        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_DID_SELECT_HOSPITAL_TYPE,
////                HospitalTypeBean.class).subscribe(new Action1<HospitalTypeBean>() {
////            @Override
////            public void call(HospitalTypeBean hospitalTypeBean) {
////                Util.showToast(getActivity(), "您选择了" + hospitalTypeBean.getFeatureTitle());
////            }
////        });
////        if (this.hospitalTypeSelectSubscription == null) {
////            hospitalTypeSelectSubscription = new CompositeSubscription();
////        }
////        hospitalTypeSelectSubscription.add(mSubscription);
//    }
//
//    @Override
//    protected void loadData() {
////        if (hospitalTypeSelectSubscription == null) {
////            registerHospitalTypeSelectEvent();
////        }
// //       currentIndex = 0;//为了以后写下拉刷新 不然可以直接用这个方法。
//     //   mPresenter.main(null, false);
//        onRefresh();
//    }
//
//    public static InHospitalInspectFragment getInHospitalInspectFragment() {
//        if (instance == null) {
//            instance = new InHospitalInspectFragment();
//        }
//        return instance;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
////        if (this.hospitalTypeSelectSubscription != null && this.hospitalTypeSelectSubscription.hasSubscriptions()) {
////            this.hospitalTypeSelectSubscription.unsubscribe();
////        }
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_inhospital_inspect;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(true);
//    }
//
//    public void reassignToolbar() {
//        WorkbenchActivity workbenchActivity = (WorkbenchActivity) getActivity();
//        if (workbenchActivity != null) {
//            Toolbar toolbar = (Toolbar) workbenchActivity.findViewById(R.id.toolbar2);
//            //LogUtil.i("InHospitalInspectFragment initView toolbar:" + toolbar);
//            workbenchActivity.setToolBarWithNoBackButtonAndNoTitle(toolbar);
//        }
//
//    }
//
//    @Override
//    protected void initView() {
//        instance = this;
//        //LogUtil.i("inhospitalinspectfragment initView enter");
//        setHasOptionsMenu(true);
//
////        reassignToolbar();
//
//       /* flInHospitalInspectHospitalTypeSelect.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                hospitalTypeSelectHandler();
//            }
//        });*/
//
////        searchView = (MaterialSearchView) findViewById(R.id.search_view);
//        searchView.setVoiceSearch(false);
////        searchView.setCursorDrawable(R.drawable.color_cursor_white);
////        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//
//      /*  searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
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
//                return false;
//            }
//        });*/
//
//     /*   searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                //Do some magic
//                ToastUtil.show(getActivity(), "searchview show");
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                //Do some magic
//            }
//        });*/
//
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////              //  ToastUtil.show(getActivity(), "searchview show");
////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
////                        Constants.SEARCH_ACTIVITY_NAME);
//              /*  Util.gotoActivity(getActivity(), SearchActivity.class);*/
//            }
//        });
//
//
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onRefresh();
//
//
//            }
//        });
//
//        mAdapter=new InHospitalInspectAdapter(getActivity(),new ArrayList<InHospitalInspectBeanList>());
////        easyLoadMoreView = new EasyLoadMoreView();
////        mAdapter.setLoadMoreView(easyLoadMoreView);
////        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//
//                Bundle bundle = new Bundle();
//
//                bundle.putString("year", mAdapter.getData().get(position).getYear());
//                bundle.putString("signKey", mAdapter.getData().get(position).getKey());
//                bundle.putString("personModel", mAdapter.getData().get(position).getPersonMold());
//                bundle.putString("personId", mAdapter.getData().get(position).getPersonId());
//
////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.DETAILPEOPLEINFO_ACTIVITY_NAME,bundle);
//                Util.gotoActivityWithBundle(getActivity(), DetailPeopleInfoActivity.class, bundle);
//            }
//        });
//
////        ((InHospitalInspectAdapter) mAdapter).setOnItemClickListener(new InHospitalInspectAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////            onLockInHospitalInfo();
////            }
////
//////            @Override
//////            public void onItemClickListener(String id, String imgUrl, View view) {
//////                startZhiHuDetailActivity(id, imgUrl, view);
//////            }
////        });
//
////        LogUtil.i("inhospitalinspectfragment initView before simulateData");
////        simulateData();
//    }
//
//    public void onLockInHospitalInfo(){
//      /*  MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
//                .title(R.string.hint_suggestion)
//                .content(R.string.hint_whether_lock_hospital)
//                .positiveText(R.string.confirm)
//                .negativeText(R.string.cancel)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                    }
//                }).build();
//        dialog.show();*/
//
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        //LogUtil.i("InHospitalInspectFragment onCreateOptionsMenu called");
//        menu.clear();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //LogUtil.i("InHospitalInspectFragment onOptionsItemSelected");
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                //code here
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this.getActivity())
//                .getAppComponent()
//                .addInHospitalInspectFragment(new InHospitalInspectFragmentModule(this))
//                .inject(this);
////        DaggerTopNewsComponent.builder()
////                .topNewsHttpModule(new TopNewsHttpModule())
////                .topNewsModule(new TopNewsModule())
////                .build().injectTopNews(this);
//    }
//
////    @Override
////    public void refreshView(InHospitalInspectListBean data) {
//////        LogUtils.e("aaaacurrentIndex" + currentIndex);
////        LogUtil.i("InHospitalInspectFragment refreshView");
////        InHospitalInspectListBean bean = SimulateData.getSimulatedInHospitalInspectListBean();
////
////        inHospitalInspectList = bean.getInspectList();
//////        mAdapter.addData(inHospitalInspectList);
//////        index += 1;
//////        currentIndex = mAdapter.getData().size() - 2 * index;
//////        mAdapter.loadMoreComplete();
//////
////        if (isRefresh) {
////            srl.setRefreshing(false);
////            mAdapter.setEnableLoadMore(true);
////            isRefresh = false;
////            mAdapter.setNewData(inHospitalInspectList);
////        } else {
////            srl.setEnabled(true);
////            index += 20;
////            mAdapter.addData(inHospitalInspectList);
////            mAdapter.loadMoreComplete();
////        }
////
////
////    }
//
//    public void simulateData() {
//        //LogUtil.i("InHospitalInspectFragemnt simulateData");
////        setState(AppConstants.STATE_SUCCESS);
//
//      /*  InHospitalInspectListBean bean = SimulateData.getSimulatedInHospitalInspectListBean();
//        inHospitalInspectList = bean.getInspectList();
//        mAdapter.getData().clear();
//        mAdapter.addData(inHospitalInspectList);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.loadMoreComplete();*/
//
//    }
//
//
////    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
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
////    }
//
//    @Override
//    public void onRefresh() {
//       /* index = 0;
//        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
//        //LogUtil.i("InHospitalInspectFragment onRefresh");
//        mPresenter.main(null, false);*/
//
//        currentPage = Constants.DEFAULT_INITIAL_PAGE;
//        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
//     //   LogUtil.i("ExecutionProjectsActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
////        TreatmentListRequestBean bean = getRequestBean();
////        mPresenter.main(bean, false);
//
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, false);
//
//    }
//
//    public Map<String, String> getRequestMap() {
//       /* String orgId = "NjdCOTZFRjktNEEwOC00QzEyLUE4RUItNDEzRkZDNzNGMzQ5";
//        String docUserId = "MmE4M2Y5NTItNjVjMi00MmY1LTk1MTItODQ3ZDIyNzJiMWMw";
//        String sign ="SlFTT0ZUNjUzNTA4ODA=";*/
//
//        String orgId = Identity.getOrganizationKey();
//     //   String orgId = Identity.info.getSorganizationkey();
//        String docUserId = Identity.getUserId();
//        String encodedorgId = Util.getBase64String(orgId);
//        String encodeddocUserId = Util.getBase64String(docUserId);
//
//        //String sign ="SlFTT0ZUNjUzNTA4ODA=";
//
////        String cardNo=Identity.srcInfo.getCardNo();
////        String cardNo = Identity.getCardNo();
//        String cardNo = IdentityManager.getCardNo(getActivity());
//        Map<String, String> map = ParametersFactory.getHospitalInspectListMap(getActivity(), cardNo);
//        return map;
//    }
//
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
//       /* mPresenter.main(null, true);
//        srl.setEnabled(false);*/
//
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//     //   LogUtil.i("ExecutionProjectsActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
////        srl.setEnabled(false);
//
//
////        }
//    }
//
//  /*  @Override
//    public void onLoadListSuccess(HttpResultBaseBean<InHospitalInspectListBean> beanList) {
//        InHospitalInspectListBean bean = SimulateData.getSimulatedInHospitalInspectListBean();
//
//        inHospitalInspectList = bean.getInspectList();
////        mAdapter.addData(inHospitalInspectList);
////        index += 1;
////        currentIndex = mAdapter.getData().size() - 2 * index;
////        mAdapter.loadMoreComplete();
////
//        srl.setRefreshing(false);
//        mAdapter.setEnableLoadMore(true);
//        isRefresh = false;
//        mAdapter.setNewData(inHospitalInspectList);
//
//    }*/
//
//   /* @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<InHospitalInspectListBean> beanList) {
//        InHospitalInspectListBean bean = SimulateData.getSimulatedInHospitalInspectListBean();
//
//        inHospitalInspectList = bean.getInspectList();
////        mAdapter.addData(inHospitalInspectList);
////        index += 1;
////        currentIndex = mAdapter.getData().size() - 2 * index;
////        mAdapter.loadMoreComplete();
////
//        srl.setEnabled(true);
// //       index += 20;
//        mAdapter.addData(inHospitalInspectList);
//        mAdapter.loadMoreComplete();
//
//    }*/
//
//   /* @Override
//    public void onLoadListFailure(String message) {
//
//    }*/
//
//    @Override
//    public void didSelectHospitalType(HospitalTypeBean bean) {
//        Util.showToast(getActivity(), "已选择了"+bean.getFeatureTitle());
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> beanList) {
//        Util.hideGifProgressDialog(getActivity());
//        int listSize = getListSizeFromResult(beanList);
//        List<InHospitalInspectBeanList> list = getListFromResult(beanList);
//        mAdapter.setNewData(list);
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
//        isRefresh = false;
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> beanList) {
//        Util.hideGifProgressDialog(getActivity());
//        int listSize = getListSizeFromResult(beanList);
//        List<InHospitalInspectBeanList> list  = getListFromResult(beanList);
//        mAdapter.addData(list);
//
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(this.pageSize, listSize, true);
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
////        Util.hideGifProgressDialog(getActivity());
//        if(message!=null){
//            Toast.makeText(getActivity(),message.toString(),Toast.LENGTH_SHORT).show();
//            showRecyclerViewOrFailureView(false, true);
//        }
//
//    }
//
//
//    public int getListSizeFromResult(HttpResultBaseBean<List<InHospitalInspectBeanList>> beanList) {
//        if (beanList != null) {
//            List<InHospitalInspectBeanList> list = beanList.getData();
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
//    public List<InHospitalInspectBeanList> getListFromResult(HttpResultBaseBean<List<InHospitalInspectBeanList>> beanList) {
//        if (beanList != null) {
//            List<InHospitalInspectBeanList> list = beanList.getData();
//            return list;
//        } else {
//            return null;
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
//
//    @Override
//    public void sendSuccessinfo() {
//        onRefresh();
//    }
//
//
//    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
//        if (success){
//            if (isListEmpty){
//                //   srl.setVisibility(View.GONE);
//                failureView.setVisibility(View.VISIBLE);
//                tvFailureView.setText(getListEmptyHint());
//            } else {
//                //   srl.setVisibility(View.VISIBLE);
//                failureView.setVisibility(View.GONE);
//            }
//        } else {
//            //     srl.setVisibility(View.GONE);
//            failureView.setVisibility(View.VISIBLE);
//            tvFailureView.setText(getFailureHint());
//
//        }
//    }
//
//    private String getListEmptyHint(){
//        return getResources().getString(R.string.hint_list_empty_inhospital);
//    }
//
//    private String getFailureHint(){
//        return getResources().getString(R.string.hint_load_failure);
//    }
//
//
//}
