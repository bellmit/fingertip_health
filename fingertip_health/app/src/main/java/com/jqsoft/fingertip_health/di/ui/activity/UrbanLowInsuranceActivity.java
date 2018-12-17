package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.UrbanLowInsAdapter;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowInsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.urbanLowInsActivityContract;
import com.jqsoft.fingertip_health.di.module.UrbanLowInsActivityModule;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowInsuranceActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.UrbanLowInsAdapter.TYPE_MULTIPLE_LINE;

//政策解读列表
public class UrbanLowInsuranceActivity extends AbstractActivity implements
        urbanLowInsActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

    @BindView(R.id.policy_title)
    TextView policy_title;


    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;

    @Inject
    UrbanLowInsuranceActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private UrbanLowInsAdapter mAdapter;


    private String keywordString;

    private int currentPage = 1;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    private String myItemId,mytitles;
    String arearLevel="";
    private String itemName="";


    @Override
    protected void loadData() {
        onRefresh();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
                String areaid= Identity.srcInfo.getAreaId().trim();

                List<SRCLoginAreaBean>  user1 = DataSupport.where("areaCode=? and state=?",areaid ,"0").find(SRCLoginAreaBean.class);

                if (user1.size()==0){

                }else {
                    arearLevel= user1.get(0).getAreaLevel();
                }

                if( arearLevel.equals("area_5") || arearLevel.equals("area_4")){
                    Intent intent = new Intent();
                    intent.putExtra("titils",mytitles);
                    intent.setClass(getApplicationContext(), AddUrbanLowActivity.class);
                    //startActivity(intent);
                    startActivityForResult(intent,10);
                }else {
                    Toast.makeText(getApplicationContext(),"您没有权限进行新增申请!",Toast.LENGTH_SHORT).show();
                }


                //   finish();
            }
        });


        mAdapter.setOnDeleteClickListener (new UrbanLowInsAdapter.DeleteListener(){

            @Override
            public void onDeleteClick(final String batchNo,int flag,String crad,String name,String itemName,String status){
                //0是删除1，1是查看基本信息，2是查看流程
                if(flag==0){

                    if(status.equals("0") || status.equals("10")){
                        MaterialDialog dialog = new MaterialDialog.Builder(UrbanLowInsuranceActivity.this)
                                .title(R.string.hint_suggestion)
                                .content("确定要删除吗?")
                                .negativeText(R.string.cancel)
                                .positiveText(R.string.confirm)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();

                                        Map<String, String> map1 = ParametersFactory.getDeleteUrban(UrbanLowInsuranceActivity.this,batchNo);
                                        mPresenter.maindelete(map1);
                                /*SignServiceAssessActivity activity = (SignServiceAssessActivity) UrbanLowInsuranceActivity.this;
                                activity.onRefresh();*/


                                    }
                                }).build();
                        dialog.show();
                    }else {

                    }


                }else if(flag==1){

                    Intent intent = new Intent();
                    intent.putExtra("batchNo",batchNo);
                    intent.putExtra("titils",mytitles);
                    intent.putExtra("status",status);
                    intent.setClass(getApplicationContext(), AddUrbanLowBianjiActivity.class);
                    startActivityForResult(intent,10);

                }else if(flag==2){
                    Intent intent = new Intent();
                    if(itemName.equals("农村低保")){
                        intent.putExtra("itemCode","ITEM_DIBAO_NC");
                        intent.putExtra("itemName","农村低保");
                    }else if(itemName.equals("城市低保") || itemName.equals("城镇低保")){
                        intent.putExtra("itemCode","ITEM_DIBAO_CZ");
                        intent.putExtra("itemName","城市低保");
                    }else {
                        intent.putExtra("itemCode",myItemId);
                        intent.putExtra("itemName",itemName);
                    }



                    intent.putExtra("identifier",batchNo);
                    intent.putExtra("idcard",crad);
                    intent.putExtra("name",name);
                    intent.setClass(getApplicationContext(), HandleProgressDetailActivity.class);
                    startActivity(intent);

                }




            }




        });

    }




//    public String getKeywordString() {
//        return Util.trimString(keywordString);
//    }


    public Map<String, String> getRequestMap() {
//        String year = Util.getCurrentYearString();
//        Map<String, String> map = ParametersFactory.getPolicyDataMap(this, year, currentPage, pageSize);

       String areaid= Identity.srcInfo.getAreaId().trim();

        List<SRCLoginAreaBean>  user1 = DataSupport.where("areaCode=? and state=?",areaid ,"0").find(SRCLoginAreaBean.class);

        if (user1.size()==0){

        }else {
             arearLevel= user1.get(0).getAreaLevel();
        }


        Map<String, String> map = ParametersFactory.getGCAUrbanLowListMap(this, Constants.CODE_POLICY, String.valueOf(currentPage), String.valueOf(pageSize),
                Constants.METHOD_NAME_NOTIFICATION_OR_POLICY,areaid,myItemId,arearLevel);
        return map;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_urban_lowinsurance_layout;
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
        myItemId=getDeliveredStringByKey("ItemId");
        mytitles=getDeliveredStringByKey("titils");
        policy_title.setText(mytitles);
        LogUtil.i("PolicyActivity initView enter");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

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


        final BaseQuickAdapterEx<UrbanLowInsBean, BaseViewHolder> mAdapter = new UrbanLowInsAdapter(new ArrayList<UrbanLowInsBean>(), TYPE_MULTIPLE_LINE,UrbanLowInsuranceActivity.this);
        this.mAdapter = (UrbanLowInsAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(PolicyActivity.this, position+" is clicked");
               /* UrbanLowInsBean pb = mAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.POLICY_DETAIL_ACTIVITY_KEY, pb);
                Util.gotoActivityWithBundle(UrbanLowInsuranceActivity.this, PolicyDetailActivity.class, bundle);*/
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
                .addUrbanLowInsActivity(new UrbanLowInsActivityModule(this))
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


        currentPage = 1;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        LogUtil.i("PolicyActivity onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);
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
        LogUtil.i("PolicyActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
    }

    public List<UrbanLowInsBean> getListFromResult(GCAHttpResultBaseBean<List<UrbanLowInsBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowInsBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

//    public int getPageFromResult(HttpResultBaseBean<List<PolicyBean>> beanList) {
//        if (beanList!=null){
//            List<PolicyBean> wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getPage();
//            } else {
//                return Constants.DEFAULT_INITIAL_PAGE;
//            }
//        } else {
//            return Constants.DEFAULT_INITIAL_PAGE;
//        }
//    }

//    public int getPageSizeFromResult(HttpResultBaseBean<List<PolicyBean>> beanList){
//        if (beanList!=null){
//            PolicyResultWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
////                List<PolicyBean> list = wrapperBean.getList();
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

    public int getListSizeFromResult(GCAHttpResultBaseBean<List<UrbanLowInsBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowInsBean> list = beanList.getData();
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

//    private void replaceXmlTag(List<PolicyBean> list){
//        if (!ListUtils.isEmpty(list)){
//            for (int i = 0; i < list.size(); ++i){
//                PolicyBean pb = list.get(i);
//                pb.setTitle(Util.getReplacedXmlTagString(pb.getTitle()));
//                pb.setMessage(Util.getReplacedXmlTagString(pb.getMessage()));
//            }
//        }
//    }


/*
    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<PolicyBean>> beanList) {


        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

        List<PolicyBean> list = getListFromResult(beanList);
//        replaceXmlTag(list);
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
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PolicyBean>> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
        int listSize = getListSizeFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<PolicyBean> list = getListFromResult(beanList);
//        replaceXmlTag(list);
        mAdapter.addData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);

    }*/

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<UrbanLowInsBean>> bean) {

        int listSize = getListSizeFromResult(bean);

        List<UrbanLowInsBean> list = getListFromResult(bean);
//        replaceXmlTag(list);
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
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<UrbanLowInsBean>> bean) {
        int listSize = getListSizeFromResult(bean);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);

        List<UrbanLowInsBean> list = getListFromResult(bean);
//        replaceXmlTag(list);
        mAdapter.addData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));


        srl.setEnabled(true);
        srl.setRefreshing(false);
        setLoadMoreStatus(this.pageSize, listSize, true);
    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
       // showRecyclerViewOrFailureView(false, true);
        if (isLoadMore){
            mAdapter.loadMoreFail();


          /*  if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }*/

        } else {
            showRecyclerViewOrFailureView(false, true);
        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    @Override
    public void onDeleteUrbanSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        onRefresh();

    }

    @Override
    public void onDeleteUrbanFailure(String message) {
        Util.showToast(this, message);
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


        if(mytitles.equals("城市低保")){
            return getResources().getString(R.string.hint_no_dibao_info_please_click_to_reload);
        }else if(mytitles.equals("特困人员供养")){
            return getResources().getString(R.string.hint_no_tekun_info_please_click_to_reload);
        }else if(mytitles.equals("农村低保")){
            return getResources().getString(R.string.hint_no_yiliao_info_please_click_to_reload);
        }else if(mytitles.equals("其他救助")){
            return getResources().getString(R.string.hint_no_linshi_info_please_click_to_reload);
        }else {
            return "";
        }



    }

    private String getFailureHint(){

        if(mytitles.equals("城市低保")){
            return getResources().getString(R.string.hint_load_dibao_info_error_please_click_to_reload);
        }else if(mytitles.equals("特困人员供养")){
            return getResources().getString(R.string.hint_load_tekun_info_error_please_click_to_reload);
        }else if(mytitles.equals("农村低保")){
            return getResources().getString(R.string.hint_load_yiliao_info_error_please_click_to_reload);
        }else if(mytitles.equals("其他救助")){
            return getResources().getString(R.string.hint_load_linshi_info_error_please_click_to_reload);
        }else {
            return "";
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

     //   onRefresh();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10&&resultCode==10){

            onRefresh();
        }
    }


//    private void simulate() {
//        List<PolicyBean> list = SimulateData.getPolicyBeanList();
//        HttpResultBaseBean<List<PolicyBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//    }

}
