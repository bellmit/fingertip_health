package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.UrbanLowFamilyAdapter;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianSaveBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.UrbanLowFamilyFragmentContract;
import com.jqsoft.fingertip_health.di.module.UrbanLowFamilyFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AddUrbanLowActivity;
import com.jqsoft.fingertip_health.di.ui.activity.UrbanFamilyActivity;
import com.jqsoft.fingertip_health.di.ui.activity.UrbanFamilybianjiActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils.UserEvent;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.UrbanLowInsAdapter.TYPE_MULTIPLE_LINE;

//政策解读列表
public class UrbanFamilyFragment extends AbstractFragment implements
        UrbanLowFamilyFragmentContract.View, SwipeRefreshLayout.OnRefreshListener
        {



    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;


    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;

    @Inject
    UrbanLowFamilyFragmentPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private UrbanLowFamilyAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;
            private List<String> mCardList = new ArrayList<>();

    private int currentPage = 1;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private String mybatchno="",status="";
    public static final int REQUEST_A = 1;

    @Override
    protected void loadData() {
     //   String mybatchNo= ((UrbanLowInsuranceActivity)getActivity()).getFlagBatchno();
      /*  String s2=mybatchno;

        onRefresh();*/

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
             //   intent.putExtra("batchNo","20180201092502878");
                intent.putExtra("batchNo",mybatchno);
                intent.putExtra("mCardList",(Serializable) mCardList);
                intent.setClass(getActivity(), UrbanFamilyActivity.class);
            //    startActivity(intent);
                startActivityForResult(intent, REQUEST_A);
                //   finish();
            }
        });


        mAdapter.setOnDeleteClickListener (new UrbanLowFamilyAdapter.DeleteListener(){

            @Override
            public void onDeleteClick(final String id,int flag,String relation) {

           //     Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();
                if(flag==0){
                    //0是编辑，1是删除
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                //    bundle.putString("batchNo", "20180201092502878");
                    bundle.putString("batchNo", mybatchno);
                    bundle.putString("relation", relation);
                    Intent i = new Intent(getActivity(), UrbanFamilybianjiActivity.class);
                    i.putExtras(bundle);
                    i.putExtra("mCardList",(Serializable) mCardList);
                  //  startActivity(i);
                    startActivityForResult(i, REQUEST_A);
                }else {

                    MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                            .title(R.string.hint_suggestion)
                            .content("确定要删除吗?")
                            .negativeText(R.string.cancel)
                            .positiveText(R.string.confirm)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    Map<String, String> map = ParametersFactory.getUrbanLowFamilydeleteMap(getActivity(),
                                            id );
                                    mPresenter.mainfamilydelete(map);


                                }
                            }).build();
                    dialog.show();



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
        Map<String, String> map = ParametersFactory.getUrbanLowFamilyMap(getActivity(),
                mybatchno);
        return map;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_urban_family_layout;
    }

    @Override
    protected void initData() {


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

            //定义处理接收方法
            @Subscribe
            public void onEventMainThread(UserEvent event) {
                mybatchno =event.getUserName();
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                EventBus.getDefault().unregister(this);
            }

    @Override
    protected void initView() {
        status = getDeliveredString("status");
        //注册订阅者
        EventBus.getDefault().register(this);
//        test();

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


        final BaseQuickAdapter<UrbanLowFamilyBean, BaseViewHolder> mAdapter = new UrbanLowFamilyAdapter(new ArrayList<UrbanLowFamilyBean>(), TYPE_MULTIPLE_LINE,getActivity(),"0");
        this.mAdapter = (UrbanLowFamilyAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
 //       mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
    //    Util.addDividerToRecyclerView(getActivity(), recyclerView, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(PolicyActivity.this, position+" is clicked");

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
       DaggerApplication.get(getActivity())
                .getAppComponent()
                .addUrbanLowFamilyFragment(new UrbanLowFamilyFragmentModule(this))
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
    public void onRefresh() {



        isRefresh = true;
        mAdapter.setEnableLoadMore(false);

        Map<String, String> map = getRequestMap();
        mPresenter.main(map);
    }



    public List<UrbanLowFamilyBean> getListFromResult(GCAHttpResultBaseBean<List<UrbanLowFamilyBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowFamilyBean> list = beanList.getData();
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

    public int getListSizeFromResult(GCAHttpResultBaseBean<List<UrbanLowFamilyBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowFamilyBean> list = beanList.getData();
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
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<UrbanLowFamilyBean>> bean) {
        int listSize = getListSizeFromResult(bean);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("PolicyActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);

        List<UrbanLowFamilyBean> list = getListFromResult(bean);

        mCardList.clear();
        for(int i=0;i<list.size();i++){
            mCardList.add(list.get(i).getCardNo());
        }
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
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
        /*if (isLoadMore){
            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }*/
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);
    }

            @Override
            public void onLoadFujianSuccess(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> bean) {

            }

            @Override
            public void onLoadFujianListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujianTakeSuccess(GCAHttpResultBaseBean<UrbanLowFujianSaveBean> bean) {

            }

            @Override
            public void onLoadFujianTakeListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFamilydeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {

                onRefresh();
            }

            @Override
            public void onLoadFamilydeleteListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujiandeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {

            }

            @Override
            public void onLoadFujiandeleteListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujianBIanjiSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {

            }

            @Override
            public void onLoadFujianBIanjiListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
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
        return getResources().getString(R.string.hint_no_family_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_family_info_error_please_click_to_reload);
    }



            @Override
            public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if (getUserVisibleHint()) {

                 //   onRefresh();

                    String s2=mybatchno;
                    if(TextUtils.isEmpty(s2) || s2.equals("null") || s2==null){
                        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                                .title(R.string.hint_suggestion)
                                .content("请完善基本信息!")
                                .positiveText(R.string.confirm)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();

                                        AddUrbanLowActivity parentActivity = (AddUrbanLowActivity) getActivity();
                                        parentActivity.vpContent.setCurrentItem(0);
                                    }
                                }).build();
                        dialog.setCancelable(false);
                        dialog.show();
                    }else {
                        onRefresh();
                    }

                } else {
                    String s2=mybatchno;

                 //   onRefresh();
                   /* isVisible = false;
                    onInvisible();*/
                }


            }

            //    private void simulate() {
//        List<PolicyBean> list = SimulateData.getPolicyBeanList();
//        HttpResultBaseBean<List<PolicyBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//    }


            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

                //先判断是哪个页面返回过来的
                switch (requestCode) {
                    case REQUEST_A:

                        //再判断返回过来的情况，是成功还是失败还是其它的什么……
                        switch (resultCode) {
                            case UrbanFamilyActivity.RESULT_SUCCESS:
                                //成功了
                                onRefresh();
                                break;
                            case UrbanFamilyActivity.RESULT_FAILED:
                                //失败了
                                break;


                        }
                        break;

                }
            }

}
