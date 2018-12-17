package com.jqsoft.fingertip_health.di.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignClientServiceAssessAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.SignClientServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.ui.activity.ClientSignServiceAssessActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-07-07.
 */
//签约服务评价fragment
public class SignClientServiceAssessFragment extends AbstractFragment implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{
//    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

    @BindView(R.id.lay_sign_service_assess_new_or_already_read_load_failure)
    View failureView;

    TextView tvFailureView;


//    @Inject
//    SignServiceAssessFragmentPresenter mPresenter;

    private String type;
    public SignClientServiceAssessAdapter mAdapter;



//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;


    public SignClientServiceAssessFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_service_assess_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        type = getDeliveredString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY);

        recyclerView=(RecyclerView)srl.findViewById(R.id.recyclerview);

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


        BaseQuickAdapter<SignClientServiceAssessResultBean, BaseViewHolder> mAdapter = new SignClientServiceAssessAdapter(new ArrayList<SignClientServiceAssessResultBean>(),getActivity());
        this.mAdapter = (SignClientServiceAssessAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
//        onRefresh();

    }

    @Override
    protected void initInject() {
        super.initInject();
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addSignServiceAssessFragment(new SignServiceAssessFragmentModule(this))
//                .inject(this);

    }

    private String getListEmptyHint(){
        String s = getResources().getString(R.string.hint_no_service_assess_info_please_click_to_reload);
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


    public void endRefreshing(){
        if (srl!=null) {
            srl.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
//        currentPage = Constants.DEFAULT_INITIAL_PAGE;
//        mAdapter.setEnableLoadMore(false);
//        LogUtil.i("SignServiceAssessFragment onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);

//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, false);

        ClientSignServiceAssessActivity activity = (ClientSignServiceAssessActivity) getActivity();
        activity.onRefresh();
    }


    @Override
    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//        LogUtil.i("SignServiceAssessFragment onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);

        SignServiceAssessActivity activity = (SignServiceAssessActivity) getActivity();
        activity.onLoadMore();
    }


    public List<SignClientServiceAssessResultBean> getListFromResult(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> beanList) {
        if (beanList != null) {
            List<SignClientServiceAssessResultBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public int getListSizeFromResult(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> beanList) {
        if (beanList != null) {
            List<SignClientServiceAssessResultBean> list = beanList.getData();
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
        if (mAdapter!=null) {
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

            mAdapter.loadMoreComplete();
            mAdapter.setEnableLoadMore(false);
        }
    }

//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> beanList) {
////        List<SignClientServiceAssessResultBean> simulatedList = SimulateData.getSimulatedTreatmentResultList();
////        ExecutionProjectsResultWrapperBean wrapperBean = new ExecutionProjectsResultWrapperBean(1, 20, simulatedList);
////        beanList.setData(wrapperBean);
//
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("SignServiceAssessFragment onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);
//
//        List<SignClientServiceAssessResultBean> list = getListFromResult(beanList);
//        mAdapter.setNewData(list);
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
////        mAdapter.setEnableLoadMore(true);
//
//
////        mAdapter.disableLoadMoreIfNotFullPage();
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> beanList) {
////        TreatmentListBean bean = SimulateData.getSimulatedTreatmentListBean();
////
////        treatmentList = bean.getTreatmentList();
////        int  page = getPageFromResult(beanList);
////        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
////        currentPage=page;
////        this.pageSize =pageSize;
////        LogUtil.i("SignServiceAssessFragment onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<SignClientServiceAssessResultBean> list = getListFromResult(beanList);
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
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
////        LogUtil.i("SignServiceAssessFragment onLoadListFailure,message:"+message);
////        simulate();
//
////        if (isLoadMore){
////            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
////                --currentPage;
////            }
////
////        } else {
////
////        }
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0, 0, false);
//////        Util.showToast(this, message);
//        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);
//    }

//    private void simulate() {
//        List<SignClientServiceAssessResultBean> list = SimulateData.getSignServiceAssessList();
//        HttpResultBaseBean<List<SignClientServiceAssessResultBean>> wrapper = new HttpResultBaseBean<>("0", "success", list);
//        onLoadMoreListSuccess(wrapper);
//
//    }


}
