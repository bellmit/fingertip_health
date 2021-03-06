package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageAdapter;
import com.jqsoft.fingertip_health.adapter.MyNewAdapter;
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
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Base64Util;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils.UserEventNew;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

//??????????????????
public class UrbanFuJianBianjiNewFragment extends AbstractFragment implements
        UrbanLowFamilyFragmentContract.View
        {


/*
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;*/

            @BindView(R.id.lv_server_situation)
            ListView lv_server_situation;


   /* @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;*/




  /*  @BindView(R.id.srl)
    SwipeRefreshLayout srl;*/

    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;

    @Inject
    UrbanLowFamilyFragmentPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    //private UrbanLowFujianJiuzhuxiangBianjiAdapter mAdapter;
            List<UrbanLowFujianBean> myNewlist = new ArrayList<>();
            private MyNewAdapter aMyNewdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = 1;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private String myItemid,myfileCode;
    private String mybatchno="",status="";

    private  int myCoun=0;

    @Override
    protected void loadData() {


      /*  aMyNewdapter.myNewAdapter2.setOnPaizhaoClickListener(new MyNewAdapter2.PaizhaoListener() {
            @Override
            public void onPaizhaoClick(String s, String itemid) {
                Toast.makeText(getActivity(),s+"???"+itemid,Toast.LENGTH_SHORT).show();
                myItemid=itemid;
                myfileCode=s;
                onAddPicClick(0,0);
            }
        });*/

      /*  aMyNewdapter.myNewAdapter2.setOnDeleteNewClickListener(new MyNewAdapter2.DeleteNewListener() {
            @Override
            public void onDeleteNewClick(String s) {
                String s2 =s;
                Toast.makeText(getActivity(),s2+"???",Toast.LENGTH_SHORT).show();
            }


        });*/
       /* mAdapter.myAdapter2.setOnDeleteNewClickListener(new UrbanLowFujianShenqingDetailAdapter.DeleteNewListener() {
            @Override
            public void onDeleteNewClick(String s) {
                String s2 =s;
                Toast.makeText(getActivity(),s2+"???",Toast.LENGTH_SHORT).show();
            }


        });*/
    }

    public void myPaizhao(String s, String itemid){
        myItemid=itemid;
        myfileCode=s;
        onAddPicClick(0,0);
    }




//    public String getKeywordString() {
//        return Util.trimString(keywordString);
//    }


    public Map<String, String> getRequestMap() {
//        String year = Util.getCurrentYearString();
//        Map<String, String> map = ParametersFactory.getPolicyDataMap(this, year, currentPage, pageSize);
        Map<String, String> map = ParametersFactory.getUrbanLowFujianMap(getActivity(),
                mybatchno);
        return map;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_urban_fujiannew_layout2;
    }

    @Override
    protected void initData() {
        // UrbanLowFujianShenqingshuAdapter myAdapter = new UrbanLowFujianShenqingshuAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang>(), TYPE_MULTIPLE_LINE,getActivity());


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




    @Override
    protected void initView() {

        status = getDeliveredString("status");
        aMyNewdapter = new MyNewAdapter(getActivity(),myNewlist,UrbanFuJianBianjiNewFragment.this,mPresenter,status);
        mybatchno = getDeliveredString("batchNo");
        //???????????????
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

      //  srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
      //  srl.setOnRefreshListener(this);

       /* FullyLinearLayoutManager fullyLinearLayoutManager1 = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager1.setSmoothScrollbarEnabled(false);
        fullyLinearLayoutManager1.setAutoMeasureEnabled(false);

       *//* UrbanLowFujianShenqingDetailAdapter myAdapter2 = new UrbanLowFujianShenqingDetailAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang>(),
                TYPE_MULTIPLE_LINE,getActivity(),mPresenter);
        myAdapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);*//*
        FullyLinearLayoutManager fullyLinearLayoutManager2 = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager2.setSmoothScrollbarEnabled(false);
        fullyLinearLayoutManager2.setAutoMeasureEnabled(false);
*/
     /*   UrbanLowFujianShenqingshuBianjiAdapter myAdapter = new UrbanLowFujianShenqingshuBianjiAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang>(), TYPE_MULTIPLE_LINE,getActivity(),
                fullyLinearLayoutManager1,mPresenter,mybatchno,UrbanFuJianBianjiNewFragment.this );
        myAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
*/
/*
        final BaseQuickAdapter<UrbanLowFujianBean, BaseViewHolder> mAdapter = new UrbanLowFujianJiuzhuxiangBianjiAdapter(new ArrayList<UrbanLowFujianBean>(), TYPE_MULTIPLE_LINE,getActivity(),
                myAdapter,fullyLinearLayoutManager1,mPresenter,mybatchno);
        this.mAdapter = (UrbanLowFujianJiuzhuxiangBianjiAdapter) mAdapter;*/
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
      /*  mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
 //       mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager.setSmoothScrollbarEnabled(false);
        fullyLinearLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(fullyLinearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);


        Util.addDividerToRecyclerView(getActivity(), recyclerView, false);




        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(PolicyActivity.this, position+" is clicked");

            }
        });
*/




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
                .addUrbanLowFUjianbianjiNewFragment(new UrbanLowFamilyFragmentModule(this))
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





    public void onRefresh() {

        isRefresh = true;
    //    mAdapter.setEnableLoadMore(false);


        Map<String, String> map = getRequestMap();
        mPresenter.mainfujian(map);
    }



    public List<UrbanLowFujianBean> getListFromResult(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowFujianBean> list = beanList.getData();
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

    public int getListSizeFromResult(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> beanList) {
        if (beanList != null) {
            List<UrbanLowFujianBean> list = beanList.getData();
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
       /* if (isSuccessful) {
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
        }*/
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
      //  srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);
    }



            @Override
            public void onLoadFujianSuccess(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> bean) {
             //   int listSize = getListSizeFromResult(bean);

                List<UrbanLowFujianBean> list = getListFromResult(bean);

                aMyNewdapter = new MyNewAdapter(getActivity(), list,UrbanFuJianBianjiNewFragment.this,mPresenter,status);
                lv_server_situation.setAdapter(aMyNewdapter);
                aMyNewdapter.notifyDataSetChanged();

                setListViewHeightBasedOnChildren(lv_server_situation);
                showRecyclerViewOrFailureView(true, list.isEmpty());

             /*   mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();

                showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

             //   srl.setRefreshing(false);
                setLoadMoreStatus(pageSize, listSize, true);

                isRefresh = false;*/

            }

            @Override
            public void onLoadFujianListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujianTakeSuccess(GCAHttpResultBaseBean<UrbanLowFujianSaveBean> bean) {

                onRefresh();
            }

            @Override
            public void onLoadFujianTakeListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFamilydeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {

            }

            @Override
            public void onLoadFamilydeleteListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujiandeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {
                onRefresh();
            }

            @Override
            public void onLoadFujiandeleteListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFujianBIanjiSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean) {
                onRefresh();
            }

            @Override
            public void onLoadFujianBIanjiListFailure(String message) {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }


            private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
             //   srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
              //  srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
         //   srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }



            public static void setListViewHeightBasedOnChildren(ListView listView) {
                if(listView == null) return;

                ListAdapter listAdapter = listView.getAdapter();
                if (listAdapter == null) {
                    // pre-condition
                    return;
                }

                int totalHeight = 0;
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    View listItem = listAdapter.getView(i, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }

                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                listView.setLayoutParams(params);
            }


            private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_fujian_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_fujian_info_error_please_click_to_reload);
    }



            /**
             * ????????????????????????
             */
            private boolean isShow = true;
            private int selectType = FunctionConfig.TYPE_IMAGE;
            private boolean enablePreview = true;
            private boolean isPreviewVideo = true;
            private boolean theme = false;
            private boolean selectImageType = false;
            private int maxB = 0;
            private int compressW = 0;
            private int compressH = 0;
            private boolean isCompress = false;
            private boolean isCheckNumMode = false;
            private int compressFlag = 1;// 1 ?????????????????? 2 luban??????
            private List<LocalMedia> selectMedia = new ArrayList<>();
            private int themeStyle;
            private int previewColor, completeColor, previewBottomBgColor, previewTopBgColor, bottomBgColor, checkedBoxDrawable;
            private boolean mode = false;// ??????????????????
            private int selectMode = FunctionConfig.MODE_MULTIPLE;
            private boolean clickVideo;
            private GridImageAdapter adapter;
            public void onAddPicClick(int type, int position) {
                switch (type) {
                    case 0:
                        // ????????????
                        /**
                         * type --> 1?????? or 2??????
                         * copyMode -->????????????????????????1:1???3:4???3:2???16:9
                         * maxSelectNum --> ????????????????????????
                         * selectMode         --> ?????? or ??????
                         * isShow       --> ???????????????????????? ??????????????????type ????????????????????????
                         * isPreview    --> ????????????????????????
                         * isCrop       --> ????????????????????????
                         * isPreviewVideo -->??????????????????(??????) mode or ????????????
                         * ThemeStyle -->????????????
                         * CheckedBoxDrawable -->??????????????????
                         * cropW-->???????????? ???????????????100  ????????????????????????????????? ?????????????????????
                         * cropH-->???????????? ???????????????100
                         * isCompress -->??????????????????
                         * setEnablePixelCompress ????????????????????????
                         * setEnableQualityCompress ????????????????????????
                         * setRecordVideoSecond ????????????????????????????????????
                         * setRecordVideoDefinition ???????????????  Constants.HIGH ??????  Constants.ORDINARY ?????????
                         * setImageSpanCount -->??????????????????
                         * setCheckNumMode ????????????QQ????????????(???????????????)
                         * setPreviewColor ??????????????????
                         * setCompleteColor ??????????????????
                         * setPreviewBottomBgColor ???????????????????????????
                         * setBottomBgColor ?????????????????????????????????
                         * setCompressQuality ???????????????????????????????????????
                         * setSelectMedia ??????????????????
                         * setCompressFlag 1?????????????????????  2????????????luban??????
                         * ??????-->type???2??? ??????isPreview or isCrop ??????
                         * ?????????Options?????????????????????????????????
                         */
                        String b = "50";// ?????????????????? ?????????b

                        if (!isNull(b)) {
                            maxB = Integer.parseInt(b);
                        }

                        if (!isNull("200") && !isNull("200")) {
                            compressW = Integer.parseInt("200");
                            compressH = Integer.parseInt("200");
                        }

                        if (theme) {
                            // ??????????????????
                            themeStyle = ContextCompat.getColor(getActivity(), R.color.blue);
                        } else {
                            themeStyle = ContextCompat.getColor(getActivity(), R.color.bar_grey);
                        }

                        if (selectImageType) {
                            checkedBoxDrawable = R.drawable.select_cb;
                        } else {
                            checkedBoxDrawable = 0;
                        }

                        if (isCheckNumMode) {
                            // QQ ??????????????? ????????????????????????
                            previewColor = ContextCompat.getColor(getActivity(), R.color.blue);
                            completeColor = ContextCompat.getColor(getActivity(), R.color.blue);
                        } else {
                            previewColor = ContextCompat.getColor(getActivity(), R.color.tab_color_true);
                            completeColor = ContextCompat.getColor(getActivity(), R.color.tab_color_true);
                        }

                        FunctionOptions options = new FunctionOptions.Builder()
                                .setType(selectType) // ??????or?????? FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                                .setCompress(true) //????????????
                                .setEnablePixelCompress(true) //????????????????????????
                                .setEnableQualityCompress(true) //?????????????????????
                                .setMaxSelectNum(1) // ????????????????????????
                                .setMinSelectNum(0)// ?????????????????????????????????????????????????????????
                                .setSelectMode(2) // ?????? or ??????
                                .setShowCamera(isShow) //???????????????????????? ??????????????????type ????????????????????????
                                .setEnablePreview(enablePreview) // ????????????????????????
                                .setPreviewVideo(isPreviewVideo) // ??????????????????(??????) mode or ????????????
                                .setCheckedBoxDrawable(checkedBoxDrawable)
                                .setRecordVideoDefinition(FunctionConfig.HIGH) // ???????????????
                                .setRecordVideoSecond(60) // ????????????
                                .setVideoS(0)// ??????????????????????????? ??????:???
                                .setCustomQQ_theme(0)// ????????????QQ?????????????????????????????????????????????
                                .setGif(false)// ????????????gif????????????????????????
                                .setMaxB(50) // ??????????????? ??????:200kb  ?????????202400???202400 / 1024 = 200kb
                                .setPreviewColor(previewColor) //??????????????????
                                .setCompleteColor(completeColor) //?????????????????????
                                .setPreviewBottomBgColor(previewBottomBgColor) //???????????????????????????
                                .setPreviewTopBgColor(previewTopBgColor)//???????????????????????????
                                .setBottomBgColor(bottomBgColor) //???????????????????????????
                                .setGrade(Luban.THIRD_GEAR) // ???????????? ????????????
                                .setCheckNumMode(isCheckNumMode)
                                .setCompressQuality(80) // ??????????????????,????????????
                                .setImageSpanCount(4) // ????????????
                                .setSelectMedia(selectMedia) // ?????????????????????????????????????????????????????????????????????
                                .setCompressFlag(2) // 1 ?????????????????? 2 luban??????
                                .setCompressW(0) // ????????? ???????????????????????????????????????
                                .setCompressH(0) // ????????? ???????????????????????????????????????
                                .setThemeStyle(themeStyle) // ??????????????????
                                .setNumComplete(false) // 0/9 ??????  ??????
                                .setClickVideo(clickVideo)// ??????????????????
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // ????????????????????????
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // ??????????????????????????????
//                            .setLeftBackDrawable(R.mipmap.back2) // ?????????????????????
//                            .setStatusBar(ContextCompat.getColor(MainActivity.this, R.color.white)) // ???????????????????????????????????????????????????
//                            .setImmersive(true)// ?????????????????????????????????(??????)
                                .create();

                        if (mode) {
                            // ?????????
                            PictureConfig.getInstance().init(options).startOpenCamera(getActivity());
                        } else {
                            // ??????????????????????????????????????????
                            PictureConfig.getInstance().init(options).openPhoto(getActivity(), resultCallback);
                        }
                        break;
                    case 1:
                        // ????????????
                        selectMedia.remove(position);
                        adapter.notifyItemRemoved(position);
                        break;
                }
            }

            /**
             * ?????? ???????????????????????????
             *
             * @param s
             * @return
             * @author Michael.Zhang 2013-9-7 ??????4:39:00
             */

            public boolean isNull(String s) {
                if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
                    return true;
                }

                return false;
            }

            /**
             * ??????????????????
             */
            private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
                @Override
                public void onSelectSuccess(List<LocalMedia> resultList) {
                    selectMedia = resultList;
                    Log.i("callBack_result", selectMedia.size() + "");
                    LocalMedia media = resultList.get(0);
                    if (media.isCompressed()) {
                        // ?????????,???????????????????????????,??????????????????????????????
                        String path = media.getCompressPath();
                    } else {
                        // ????????????
                        String path = media.getPath();
                    }
                    if (selectMedia != null) {
                        adapter.setList(selectMedia);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onSelectSuccess(LocalMedia media) {
                    selectMedia.add(media);
                    //  iv_touxiang.setImageBitmap();

                //    Toast.makeText(getActivity(),selectMedia.get(0).getPath(),Toast.LENGTH_SHORT).show();

                    String   sBase64="";

                            String cutPath=selectMedia.get(0).getCompressPath();
                    if(TextUtils.isEmpty(cutPath)){
                        sBase64=   Base64Util.imageToBase64(selectMedia.get(0).getPath());
                    }else {
                        sBase64=   Base64Util.imageToBase64(selectMedia.get(0).getCompressPath());
                    }


                    SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
                    String    date    =    sDateFormat.format(new    java.util.Date());

                    Map<String, String> map = ParametersFactory.getUrbanLowFujianTakeMap(getActivity(),
                            myfileCode,myItemid,sBase64,mybatchno,date,myCoun);
                    mPresenter.mainfujiantake(map);
                    selectMedia.clear();
                }
            };

            /**
             * ????????????????????????
             *
             * @param requestCode
             * @param resultCode
             * @param data
             */
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK) {
                    if (requestCode == FunctionConfig.CAMERA_RESULT) {
                        if (data != null) {
                            selectMedia = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                            if (selectMedia != null) {
                                adapter.setList(selectMedia);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }




            //????????????????????????
            @Subscribe
            public void onEventMainThread(UserEventNew event) {
                mybatchno =event.getUserName();
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                EventBus.getDefault().unregister(this);
            }


            @Override
            public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if (getUserVisibleHint()) {

                    onRefresh();

                   /* String s2=mybatchno;
                    if(TextUtils.isEmpty(s2) || s2.equals("null") || s2==null){
                        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                                .title(R.string.hint_suggestion)
                                .content("?????????????????????!")
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
                    }*/

                } else {
                  //  String s2=mybatchno;

                    //   onRefresh();
                   /* isVisible = false;
                    onInvisible();*/
                }


            }


            public String  getbatchNo(){
                return mybatchno;

            }






        }
