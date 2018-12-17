package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.VillageLevelMedicalInstitutionDirectoryAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.AreaBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultBean;
import com.jqsoft.fingertip_health.bean.response.VillageLevelMedicalInstitutionDirectoryResultWrapperBean;
import com.jqsoft.fingertip_health.di.contract.VillageLevelMedicalInstitutionDirectoryActivityContract;
import com.jqsoft.fingertip_health.di.module.VillageLevelMedicalInstitutionDirectoryActivityModule;
import com.jqsoft.fingertip_health.di.presenter.VillageLevelMedicalInstitutionDirectoryActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

//村级医疗机构通讯录
public class VillageLevelMedicalInstitutionDirectoryActivity extends AbstractActivity implements
        VillageLevelMedicalInstitutionDirectoryActivityContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.village_level_medical_institution_directory_title)
    TextView tvTitle;

    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    @BindView(R.id.lay_village_level_directory_load_failure)
    View failureView;

    TextView tvFailureView;


    @Inject
    VillageLevelMedicalInstitutionDirectoryActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private String title,orgcode;

    private VillageLevelMedicalInstitutionDirectoryAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;

    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    @Override
    protected void loadData() {
        onRefresh();

//        currentPage = Constants.DEFAULT_INITIAL_PAGE;//为了以后写下拉刷新 不然可以直接用这个方法。
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);
    }

    public String getKeywordString(){
        return Util.trimString(keywordString);
    }

    public String getArea(){
        if (!ListUtils.isEmpty(Identity.area)){
            AreaBean ab = Identity.area.get(0);
            return ab.getCode();
        } else {
            return Constants.EMPTY_STRING;
        }

    }


    public Map<String, String> getRequestMap(){
        String keyword = getKeywordString();
        String area = getArea();
        Map<String, String> map = ParametersFactory.getVillageLevelMedicalInstitutionDirectoryListMap(keyword, area, currentPage, pageSize);
        return map;
    }

//    @Override
//    public int getContentLayoutId() {
//        return R.layout.layout_recyclerview;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_village_level_medical_institution_directory;
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
        LogUtil.i("VillageLevelMedicalInstitutionDirectoryActivity onCreateOptionsMenu called");
//        reassignToolbar();
        menu.clear();
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i("VillageLevelMedicalInstitutionDirectoryActivity onOptionsItemSelected");
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
        LogUtil.i("VillageLevelMedicalInstitutionDirectoryActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        title=getDeliveredStringByKey(Constants.VILLAGE_LEVEL_MEDICAL_INSTITUTION_TITLE_KEY);


        tvTitle.setText(title);

        initSearchView();

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

        mAdapter=new VillageLevelMedicalInstitutionDirectoryAdapter(new ArrayList<VillageLevelMedicalInstitutionDirectoryResultBean>());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);


    //    mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
      //  Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);

                Bundle bundle = new Bundle();
            //    bundle.putString("year", mAdapter.getData().get(position).getOrgName());
                bundle.putString(Constants.MEDICAL_PERSON_TITLE_KEY, mAdapter.getData()!=null ? mAdapter.getData().get(position).getOrgName() : Constants.EMPTY_STRING);
                bundle.putString("orgcode", mAdapter.getData()!=null ? mAdapter.getData().get(position).getKey() : Constants.EMPTY_STRING);
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.MEDICAL_PERSON_DIRECTORY_ACTIVITY_NAME, bundle);
                Util.gotoActivityWithBundle(VillageLevelMedicalInstitutionDirectoryActivity.this, MedicalPersonDirectoryActivity.class, bundle);
            }
        });


//        ((VillageLevelMedicalInstitutionDirectoryAdapter) mAdapter).setOnItemClickListener(new VillageLevelMedicalInstitutionDirectoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MaterialDialog dialog = new MaterialDialog.Builder(VillageLevelMedicalInstitutionDirectoryActivity.this)
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

//        LogUtil.i("VillageLevelMedicalInstitutionDirectoryActivity initView before simulateData");
//        simulateData();
    }

    public void gotoMedicalPersonDirectoryActivity(VillageLevelMedicalInstitutionDirectoryResultBean bean){
      //  Bundle bundle = new Bundle();
     //   bundle.putString(Constants.MEDICAL_PERSON_TITLE_KEY, bean!=null ? bean.getName() : Constants.EMPTY_STRING);
      //  ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.MEDICAL_PERSON_DIRECTORY_ACTIVITY_NAME, bundle);
    }

    public void initSearchView(){
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
                loadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                keywordString=Util.trimString(newText);
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
                .addVillageLevelMedicalInstitutionDirectoryActivity(new VillageLevelMedicalInstitutionDirectoryActivityModule(this))
                .inject(this);
//        DaggerTopNewsComponent.builder()
//                .topNewsHttpModule(new TopNewsHttpModule())
//                .topNewsModule(new TopNewsModule())
//                .build().injectTopNews(this);
    }


    public void simulateData() {
        LogUtil.i("VillageLevelMedicalInstitutionDirectory simulateData");
//        setState(AppConstants.STATE_SUCCESS);

//        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
//        treatmentList = bean.getTreatmentList();
//        mAdapter.getData().clear();
//        mAdapter.addData(treatmentList);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.loadMoreComplete();

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
    //    mAdapter.setEnableLoadMore(false);
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);


      /*  Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);*/

        orgcode=getDeliveredStringByKey("orgcode");
        Map<String, String> map = ParametersFactory.getVillageMedicalList(this, orgcode);
        RequestBody body = Util.getBodyFromMap1(map);

        mPresenter.main(body, false);
    }


    public List<VillageLevelMedicalInstitutionDirectoryResultBean> getListFromResult(HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>> beanList){
        if (beanList != null) {
            List<VillageLevelMedicalInstitutionDirectoryResultBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public int getPageFromResult(HttpResultBaseBean<VillageLevelMedicalInstitutionDirectoryResultWrapperBean> beanList) {
        if (beanList!=null){
            VillageLevelMedicalInstitutionDirectoryResultWrapperBean wrapperBean = beanList.getData();
            if (wrapperBean!=null){
                return wrapperBean.getPage();
            } else {
                return Constants.DEFAULT_INITIAL_PAGE;
            }
        } else {
            return Constants.DEFAULT_INITIAL_PAGE;
        }
    }

    public int getPageSizeFromResult(HttpResultBaseBean<VillageLevelMedicalInstitutionDirectoryResultWrapperBean> beanList){
        if (beanList!=null){
            VillageLevelMedicalInstitutionDirectoryResultWrapperBean wrapperBean = beanList.getData();
            if (wrapperBean!=null){
//                List<VillageLevelMedicalInstitutionDirectoryResultBean> list = wrapperBean.getList();
//                int size = ListUtils.getSize(list);
//                return size;
                return wrapperBean.getSize();
            } else {
                return Constants.DEFAULT_PAGE_SIZE;
            }
        } else {
            return Constants.DEFAULT_PAGE_SIZE;
        }
    }

    public int getListSizeFromResult(HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>> beanList){
        if (beanList != null) {
            List<VillageLevelMedicalInstitutionDirectoryResultBean> list = beanList.getData();
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

    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful){
        if (isSuccessful) {
            if (listSize<pageSize){
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

    private String getListEmptyHint(){
        String s = getResources().getString(R.string.hint_no_village_level_directory_please_click_to_reload);
        return s;
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
    public void onLoadListSuccess(HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>> bean) {
        Util.hideGifProgressDialog(this);
        srl.setRefreshing(false);
        isRefresh = false;

        if(bean!=null){

            int listSize = getListSizeFromResult(bean);
            List<VillageLevelMedicalInstitutionDirectoryResultBean> list = getListFromResult(bean);
            mAdapter.setNewData(list);


            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//            showRecyclerViewOrFailureView(true, true);

            srl.setRefreshing(false);
       //     setLoadMoreStatus(pageSize, listSize, true);
            isRefresh = false;


        } else {
            showRecyclerViewOrFailureView(true, true);
        }
    }



    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<VillageLevelMedicalInstitutionDirectoryResultBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        srl.setRefreshing(false);
        isRefresh = false;
        Util.hideGifProgressDialog(this);

        showRecyclerViewOrFailureView(false, true);

    }


}
