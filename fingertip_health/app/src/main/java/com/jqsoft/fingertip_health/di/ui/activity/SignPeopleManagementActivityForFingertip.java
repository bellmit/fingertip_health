package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignPeopleManagementAdapterForFingertip;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.SignPeopleManagementItemBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.SignPeopleManagementActivityContractForFingertip;
import com.jqsoft.fingertip_health.di.module.SignPeopleManagementActivityModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.SignPeopleManagementActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.NewDoctorSignActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

//签约人群管理
public class SignPeopleManagementActivityForFingertip extends AbstractActivity implements
        SignPeopleManagementActivityContractForFingertip.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.iv_sign_people_management_add_new)
    ImageView ivAddNew;

    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_sign_people_management_load_failure)
    View failureView;
    TextView tvFailureView;
    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.query_btn)
    ImageView query_btn;
    @BindView(R.id.bt_username_clear)
    Button bt_username_clear;
    @Inject
    SignPeopleManagementActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private SignPeopleManagementAdapterForFingertip mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private int qcurrentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int qpageSize = Constants.DEFAULT_PAGE_SIZE;

    @Override
    protected void loadData() {
//        onRefresh();

    }




    public String getKeywordString() {
        String s = et_search.getText().toString();
        return Util.trimString(s);
    }


    public Map<String, String> getRequestMap() {
        String keyword = getKeywordString();
        Map<String, String> map = ParametersFactory.getSignPeopleManagementMapForFingertipnew(this, keyword, "");

        return map;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_people_management_layout;
    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        LogUtil.i("PolicyActivity onCreateOptionsMenu called");
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
        LogUtil.i("PolicyActivity onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_search:
                //code here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void test(){
        LeakThread leakThread = new LeakThread();
        leakThread.start();

    }



    @Override
    protected void initView() {
//        test();

        LogUtil.i("PolicyActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        Util.setViewListener(ivAddNew, new Runnable() {
            @Override
            public void run() {
                Util.gotoActivity(SignPeopleManagementActivityForFingertip.this, NewDoctorSignActivity.class);
            }
        });

        et_search.setHint("输入姓名或身份证号进行检索");
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        final String username= IdentityManager.getLoginSuccessUsername(this);
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  tempString = et_search.getText().toString();

                if (!TextUtils.isEmpty(tempString)) {
                    qcurrentPage = Constants.DEFAULT_INITIAL_PAGE;
                    isRefresh = true;
                    mAdapter.setEnableLoadMore(false);

                    onRefresh();
                } else {
                    Util.showToast(SignPeopleManagementActivityForFingertip.this, Constants.CHECKPOC_DATA);
                }
            }
        });
        bt_username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
//        initSearchView();

//        String failureString = Util.getPolicyActivityHintTitle(this, type);
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


        final BaseQuickAdapter<SignPeopleManagementItemBeanForFingertip, BaseViewHolder> mAdapter = new SignPeopleManagementAdapterForFingertip(this,new ArrayList<SignPeopleManagementItemBeanForFingertip>());
        this.mAdapter = (SignPeopleManagementAdapterForFingertip) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.bindToRecyclerView(recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();

        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
////                Util.showToast(PolicyActivity.this, position+" is clicked");
//                SignPeopleManagementItemBeanForFingertip pb = mAdapter.getItem(position);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.POLICY_DETAIL_ACTIVITY_KEY, pb);
//                Util.gotoActivityWithBundle(SignPeopleManagementActivityForFingertip.this, PolicyDetailActivity.class, bundle);
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
                .addSignPeopleManagementActivity(new SignPeopleManagementActivityModuleForFingertip(this))
                .inject(this);
    }


//    @Override
//    public void refreshView(TreatmentListBean data) {
////        LogUtils.e("aaaacurrentIndex" + currentIndex);
//        LogUtil.i("PolicyActivity refreshView");
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
        LogUtil.i("PolicyActivity simulateData");
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
    }



    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        LogUtil.i("PolicyActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);

        Map<String, String> map = getRequestMap();
        mPresenter.queryList(map);
    }


    @Override
    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//        LogUtil.i("PolicyActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
////        srl.setEnabled(false);
    }

    public List<SignPeopleManagementItemBeanForFingertip> getListFromResult(List<SignPeopleManagementItemBeanForFingertip> beanList) {
        if (beanList != null) {
            List<SignPeopleManagementItemBeanForFingertip> list = beanList;
            return list;
        } else {
            return null;
        }
    }

//    public int getPageFromResult(HttpResultBaseBean<List<SignPeopleManagementItemBeanForFingertip>> beanList) {
//        if (beanList!=null){
//            List<SignPeopleManagementItemBeanForFingertip> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getPage();
//            } else {
//                return Constants.DEFAULT_INITIAL_PAGE;
//            }
//        } else {
//            return Constants.DEFAULT_INITIAL_PAGE;
//        }
//    }

//    public int getPageSizeFromResult(HttpResultBaseBean<List<SignPeopleManagementItemBeanForFingertip>> beanList){
//        if (beanList!=null){
//            PolicyResultWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
////                List<SignPeopleManagementItemBeanForFingertip> list = wrapperBean.getList();
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

    public int getListSizeFromResult(List<SignPeopleManagementItemBeanForFingertip> beanList) {
        if (beanList != null) {
            List<SignPeopleManagementItemBeanForFingertip> list = beanList;
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
          mAdapter.setEnableLoadMore(false);

//        if (isSuccessful) {
//            if (listSize < pageSize) {
////                mAdapter.setEnableLoadMore(false);
//                mAdapter.loadMoreEnd(true);
//            } else {
////                new Handler().postDelayed(new Runnable() {
////                    public void run() {
//                        mAdapter.setEnableLoadMore(true);
//                        mAdapter.loadMoreComplete();
////                    }
////                }, 1000);
//            }
//        } else {
//            mAdapter.setEnableLoadMore(true);
//            mAdapter.loadMoreFail();
//        }
    }

//    private void replaceXmlTag(List<SignPeopleManagementItemBeanForFingertip> list){
//        if (!ListUtils.isEmpty(list)){
//            for (int i = 0; i < list.size(); ++i){
//                SignPeopleManagementItemBeanForFingertip pb = list.get(i);
//                pb.setTitle(Util.getReplacedXmlTagString(pb.getTitle()));
//                pb.setMessage(Util.getReplacedXmlTagString(pb.getMessage()));
//            }
//        }
//    }



    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> beanList) {

        int listSize = 0;
        try {
            String resultString = beanList.getResult();
            List<SignPeopleManagementItemBeanForFingertip> list = JSON.parseObject(resultString, new TypeReference<List<SignPeopleManagementItemBeanForFingertip>>(){});

//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);

            listSize = getListSizeFromResult(list);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

//        replaceXmlTag(list);
            mAdapter.setNewData(list);

            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//        showRecyclerViewOrFailureView(true, true);
//        showRecyclerViewOrFailureView(false, true);
        } catch (Exception e) {
            e.printStackTrace();
            onLoadListFailure("", false);
        }

        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;


    }

//    @Override
//    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<SignPeopleManagementItemBeanForFingertip>> beanList) {
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("PolicyActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<SignPeopleManagementItemBeanForFingertip> list = getListFromResult(beanList);
////        replaceXmlTag(list);
//        mAdapter.addData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//
//
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(this.pageSize, listSize, true);
//
//    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
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
        return "暂无签约人群信息,点我刷新";
    }

    private String getFailureHint(){
        return "加载签约人群信息失败,点我重试";
    }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }


//    private void simulate() {
//        List<SignPeopleManagementItemBeanForFingertip> list = SimulateData.getPolicyBeanList();
//        HttpResultBaseBean<List<SignPeopleManagementItemBeanForFingertip>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//    }

}
