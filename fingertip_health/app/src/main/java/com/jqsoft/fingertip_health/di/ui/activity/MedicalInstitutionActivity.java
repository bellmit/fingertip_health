package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.MedicalInstitutionAdapter;
import com.jqsoft.fingertip_health.bean.HospitalTypeBean;
import com.jqsoft.fingertip_health.bean.MedicalInstitutionListBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.MedicalInstitutionActivityContract;
import com.jqsoft.fingertip_health.di.module.MedicalInstitutionActivityModule;
import com.jqsoft.fingertip_health.di.presenter.MedicalInstitutionActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.HospitalTypeSelectListener;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.simulate.SimulateData;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class MedicalInstitutionActivity extends AbstractActivity implements
        MedicalInstitutionActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, HospitalTypeSelectListener {

    @BindView(R.id.fl_medical_institution_hospital_type_select)
    FrameLayout selectHospitalTypeFrameLayout;
    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    @Inject
    MedicalInstitutionActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private MedicalInstitutionAdapter mAdapter;
    private ArrayList<MedicalInstitutionListBean.MedicalInstitutionBean> medicalInstitutionList;
//    private EasyLoadMoreView easyLoadMoreView;

    private int currentIndex = 0;

    private int index;

    //    @OnClick(R.id.fl_medical_institution_hospital_type_select)
    public void onSelectHospitalType() {
        List<HospitalTypeBean> list = SimulateData.getSimulatedHospitalTypeBeanList();
        Util.showSelectHospitalTypePopupWindow(this, selectHospitalTypeFrameLayout, list, this);
    }

    @Override
    protected void loadData() {
        currentIndex = 0;//为了以后写下拉刷新 不然可以直接用这个方法。
        mPresenter.main(null, false);
    }

//    @Override
//    public int getContentLayoutId() {
//        return R.layout.layout_recyclerview;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medical_institution;
    }

    @Override
    protected void initData() {

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
        LogUtil.i("MedicalInstitutionActivity onCreateOptionsMenu called");
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
        LogUtil.i("MedicalInstitutionActivity onOptionsItemSelected");
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
        LogUtil.i("MedicalInstitutionActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, "");

        selectHospitalTypeFrameLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onSelectHospitalType();
            }
        });

        initSearchView();

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        BaseQuickAdapter<MedicalInstitutionListBean.MedicalInstitutionBean, BaseViewHolder> mAdapter = new MedicalInstitutionAdapter(new ArrayList<MedicalInstitutionListBean.MedicalInstitutionBean>());
        this.mAdapter = (MedicalInstitutionAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.MEDICAL_INSTITUTION_DETAIL_ACTIVITY_NAME);
//                Util.gotoActivity(MedicalInstitutionActivity.this, MedicalInstitutionDetailActivity.class);
            }
        });
//        ((MedicalInstitutionAdapter) mAdapter).setOnItemClickListener(new MedicalInstitutionAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.MEDICAL_INSTITUTION_DETAIL_ACTIVITY_NAME);
////                MaterialDialog dialog = new MaterialDialog.Builder(MedicalInstitutionActivity.this)
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
//
//            }
//
////            @Override
////            public void onItemClickListener(String id, String imgUrl, View view) {
////                startZhiHuDetailActivity(id, imgUrl, view);
////            }
//        });

//        LogUtil.i("MedicalInstitutionActivity initView before simulateData");
//        simulateData();
    }

    public void initSearchView() {
        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setHint(getResources().getString(R.string.search_hint));
//        searchView.setCursorDrawable(R.drawable.color_cursor_white);
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
//                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
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
                .addMedicalInstitutionActivity(new MedicalInstitutionActivityModule(this))
                .inject(this);
//        DaggerTopNewsComponent.builder()
//                .topNewsHttpModule(new TopNewsHttpModule())
//                .topNewsModule(new TopNewsModule())
//                .build().injectTopNews(this);
    }


//    @Override
//    public void refreshView(MedicalInstitutionListBean data) {
////        LogUtils.e("aaaacurrentIndex" + currentIndex);
//        LogUtil.i("MedicalInstitutionFragment refreshView");
//        MedicalInstitutionListBean bean = SimulateData.getSimulatedMedicalInstitutionListBean();
//
//        medicalInstitutionList = bean.getMedicalInstitutionList();
////        mAdapter.addData(medicalInstitutionList);
////        index += 1;
////        currentIndex = mAdapter.getData().size() - 2 * index;
////        mAdapter.loadMoreComplete();
////
//        if (isRefresh) {
//            srl.setRefreshing(false);
//            mAdapter.setEnableLoadMore(true);
//            isRefresh = false;
//            mAdapter.setNewData(medicalInstitutionList);
//        } else {
//            srl.setEnabled(true);
//            index += 20;
//            mAdapter.addData(medicalInstitutionList);
//            mAdapter.loadMoreComplete();
//        }
//
//
//    }

    public void simulateData() {
        LogUtil.i("MedicalInstitutionFragemnt simulateData");
//        setState(AppConstants.STATE_SUCCESS);

        MedicalInstitutionListBean bean = SimulateData.getSimulatedMedicalInstitutionListBean();
        medicalInstitutionList = bean.getList();
        mAdapter.getData().clear();
        mAdapter.addData(medicalInstitutionList);
        mAdapter.notifyDataSetChanged();
        mAdapter.loadMoreComplete();

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
        index = 0;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        LogUtil.i("MedicalInstitutionFragment onRefresh");
        mPresenter.main(null, false);
    }


    @Override
    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//        // mQuickAdapter.setAutoLoadMoreSize(int);
//        if (currentIndex >= 60) {
//            mAdapter.loadMoreEnd();
//            srl.setEnabled(true);
//        } else {
        mPresenter.main(null, true);
//        srl.setEnabled(false);
//        }
    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBean<MedicalInstitutionListBean> beanList) {
        MedicalInstitutionListBean bean = SimulateData.getSimulatedMedicalInstitutionListBean();

        medicalInstitutionList = bean.getList();
//
        srl.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
        isRefresh = false;
        mAdapter.setNewData(medicalInstitutionList);

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<MedicalInstitutionListBean> beanList) {
        MedicalInstitutionListBean bean = SimulateData.getSimulatedMedicalInstitutionListBean();

        medicalInstitutionList = bean.getList();
        srl.setEnabled(true);
        index += 20;
        mAdapter.addData(medicalInstitutionList);
        mAdapter.loadMoreComplete();

    }

    @Override
    public void onLoadListFailure(String message) {

    }

    @Override
    public void didSelectHospitalType(HospitalTypeBean bean) {
        Util.showToast(this, "您选择了" + bean.getFeatureTitle());
    }
}
