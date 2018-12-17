package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageAdapter;

import com.jqsoft.fingertip_health.adapter.UrbanLowFujianJiuzhuxiangBianjiAdapter;

import com.jqsoft.fingertip_health.adapter.UrbanLowFujianShenqingshuBianjiAdapter;
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
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Base64Util;
import com.jqsoft.fingertip_health.util.FullyLinearLayoutManager;
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
import static com.jqsoft.fingertip_health.adapter.UrbanLowInsAdapter.TYPE_MULTIPLE_LINE;

//政策解读列表
public class UrbanFuJianBianjiFragment extends AbstractFragment implements
        UrbanLowFamilyFragmentContract.View
        {



    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

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

    private UrbanLowFujianJiuzhuxiangBianjiAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = 1;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private String myItemid,myfileCode;
    private String mybatchno="";

    private  int myCoun=0;

    @Override
    protected void loadData() {


        mAdapter.myAdapter.setOnPaizhaoClickListener(new UrbanLowFujianShenqingshuBianjiAdapter.PaizhaoListener() {
            @Override
            public void onPaizhaoClick(String s,String itemid,int myCount) {
          //      Toast.makeText(getActivity(),s+"啦"+itemid,Toast.LENGTH_SHORT).show();
                myItemid=itemid;
                myfileCode=s;
                myCoun=myCount;
               // onAddPicClick(0,0);
            }
        });

       /* mAdapter.myAdapter2.setOnDeleteNewClickListener(new UrbanLowFujianShenqingDetailAdapter.DeleteNewListener() {
            @Override
            public void onDeleteNewClick(String s) {
                String s2 =s;
                Toast.makeText(getActivity(),s2+"啦",Toast.LENGTH_SHORT).show();
            }


        });*/
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
        return R.layout.fragment_urban_fujiannew_layout;
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
        mybatchno = getDeliveredString("batchNo");
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

      //  srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
      //  srl.setOnRefreshListener(this);

        FullyLinearLayoutManager fullyLinearLayoutManager1 = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager1.setSmoothScrollbarEnabled(false);
        fullyLinearLayoutManager1.setAutoMeasureEnabled(false);

       /* UrbanLowFujianShenqingDetailAdapter myAdapter2 = new UrbanLowFujianShenqingDetailAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang>(),
                TYPE_MULTIPLE_LINE,getActivity(),mPresenter);
        myAdapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);*/
        FullyLinearLayoutManager fullyLinearLayoutManager2 = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager2.setSmoothScrollbarEnabled(false);
        fullyLinearLayoutManager2.setAutoMeasureEnabled(false);

        UrbanLowFujianShenqingshuBianjiAdapter myAdapter = new UrbanLowFujianShenqingshuBianjiAdapter(new ArrayList<UrbanLowFujianBean.Jiuzhuxiang>(), TYPE_MULTIPLE_LINE,getActivity(),
                fullyLinearLayoutManager1,mPresenter,mybatchno,UrbanFuJianBianjiFragment.this );
        myAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);


        final BaseQuickAdapter<UrbanLowFujianBean, BaseViewHolder> mAdapter = new UrbanLowFujianJiuzhuxiangBianjiAdapter(new ArrayList<UrbanLowFujianBean>(), TYPE_MULTIPLE_LINE,getActivity(),
                myAdapter,fullyLinearLayoutManager1,mPresenter,mybatchno);
        this.mAdapter = (UrbanLowFujianJiuzhuxiangBianjiAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
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
                .addUrbanLowFUjianbianjiFragment(new UrbanLowFamilyFragmentModule(this))
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
        mAdapter.setEnableLoadMore(false);


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
                int listSize = getListSizeFromResult(bean);

                List<UrbanLowFujianBean> list = getListFromResult(bean);

                mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();

                showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

             //   srl.setRefreshing(false);
                setLoadMoreStatus(pageSize, listSize, true);

                isRefresh = false;

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

    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_fujian_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_fujian_info_error_please_click_to_reload);
    }



            /**
             * 删除图片回调接口
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
            private int compressFlag = 1;// 1 系统自带压缩 2 luban压缩
            private List<LocalMedia> selectMedia = new ArrayList<>();
            private int themeStyle;
            private int previewColor, completeColor, previewBottomBgColor, previewTopBgColor, bottomBgColor, checkedBoxDrawable;
            private boolean mode = false;// 启动相册模式
            private int selectMode = FunctionConfig.MODE_MULTIPLE;
            private boolean clickVideo;
            private GridImageAdapter adapter;
            public void onAddPicClick(int type, int position) {
                switch (type) {
                    case 0:
                        // 进入相册
                        /**
                         * type --> 1图片 or 2视频
                         * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
                         * maxSelectNum --> 可选择图片的数量
                         * selectMode         --> 单选 or 多选
                         * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
                         * isPreview    --> 是否打开预览选项
                         * isCrop       --> 是否打开剪切选项
                         * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
                         * ThemeStyle -->主题颜色
                         * CheckedBoxDrawable -->图片勾选样式
                         * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
                         * cropH-->裁剪高度 值不能小于100
                         * isCompress -->是否压缩图片
                         * setEnablePixelCompress 是否启用像素压缩
                         * setEnableQualityCompress 是否启用质量压缩
                         * setRecordVideoSecond 录视频的秒数，默认不限制
                         * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
                         * setImageSpanCount -->每行显示个数
                         * setCheckNumMode 是否显示QQ选择风格(带数字效果)
                         * setPreviewColor 预览文字颜色
                         * setCompleteColor 完成文字颜色
                         * setPreviewBottomBgColor 预览界面底部背景色
                         * setBottomBgColor 选择图片页面底部背景色
                         * setCompressQuality 设置裁剪质量，默认无损裁剪
                         * setSelectMedia 已选择的图片
                         * setCompressFlag 1为系统自带压缩  2为第三方luban压缩
                         * 注意-->type为2时 设置isPreview or isCrop 无效
                         * 注意：Options可以为空，默认标准模式
                         */
                        String b = "50";// 压缩最大大小 单位是b

                        if (!isNull(b)) {
                            maxB = Integer.parseInt(b);
                        }

                        if (!isNull("200") && !isNull("200")) {
                            compressW = Integer.parseInt("200");
                            compressH = Integer.parseInt("200");
                        }

                        if (theme) {
                            // 设置主题样式
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
                            // QQ 风格模式下 这里自己搭配颜色
                            previewColor = ContextCompat.getColor(getActivity(), R.color.blue);
                            completeColor = ContextCompat.getColor(getActivity(), R.color.blue);
                        } else {
                            previewColor = ContextCompat.getColor(getActivity(), R.color.tab_color_true);
                            completeColor = ContextCompat.getColor(getActivity(), R.color.tab_color_true);
                        }

                        FunctionOptions options = new FunctionOptions.Builder()
                                .setType(selectType) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                                .setCompress(true) //是否压缩
                                .setEnablePixelCompress(true) //是否启用像素压缩
                                .setEnableQualityCompress(true) //是否启质量压缩
                                .setMaxSelectNum(1) // 可选择图片的数量
                                .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                                .setSelectMode(2) // 单选 or 多选
                                .setShowCamera(isShow) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                                .setEnablePreview(enablePreview) // 是否打开预览选项
                                .setPreviewVideo(isPreviewVideo) // 是否预览视频(播放) mode or 多选有效
                                .setCheckedBoxDrawable(checkedBoxDrawable)
                                .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                                .setRecordVideoSecond(60) // 视频秒数
                                .setVideoS(0)// 查询多少秒内的视频 单位:秒
                                .setCustomQQ_theme(0)// 可自定义QQ数字风格，不传就默认是蓝色风格
                                .setGif(false)// 是否显示gif图片，默认不显示
                                .setMaxB(50) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb
                                .setPreviewColor(previewColor) //预览字体颜色
                                .setCompleteColor(completeColor) //已完成字体颜色
                                .setPreviewBottomBgColor(previewBottomBgColor) //预览图片底部背景色
                                .setPreviewTopBgColor(previewTopBgColor)//预览图片标题背景色
                                .setBottomBgColor(bottomBgColor) //图片列表底部背景色
                                .setGrade(Luban.THIRD_GEAR) // 压缩档次 默认三档
                                .setCheckNumMode(isCheckNumMode)
                                .setCompressQuality(80) // 图片裁剪质量,默认无损
                                .setImageSpanCount(4) // 每行个数
                                .setSelectMedia(selectMedia) // 已选图片，传入在次进去可选中，不能传入网络图片
                                .setCompressFlag(2) // 1 系统自带压缩 2 luban压缩
                                .setCompressW(0) // 压缩宽 如果值大于图片原始宽高无效
                                .setCompressH(0) // 压缩高 如果值大于图片原始宽高无效
                                .setThemeStyle(themeStyle) // 设置主题样式
                                .setNumComplete(false) // 0/9 完成  样式
                                .setClickVideo(clickVideo)// 开启点击声音
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题字体颜色
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题右边字体颜色
//                            .setLeftBackDrawable(R.mipmap.back2) // 设置返回键图标
//                            .setStatusBar(ContextCompat.getColor(MainActivity.this, R.color.white)) // 设置状态栏颜色，默认是和标题栏一致
//                            .setImmersive(true)// 是否改变状态栏字体颜色(黑色)
                                .create();

                        if (mode) {
                            // 只拍照
                            PictureConfig.getInstance().init(options).startOpenCamera(getActivity());
                        } else {
                            // 先初始化参数配置，在启动相册
                            PictureConfig.getInstance().init(options).openPhoto(getActivity(), resultCallback);
                        }
                        break;
                    case 1:
                        // 删除图片
                        selectMedia.remove(position);
                        adapter.notifyItemRemoved(position);
                        break;
                }
            }

            /**
             * 判断 一个字段的值否为空
             *
             * @param s
             * @return
             * @author Michael.Zhang 2013-9-7 下午4:39:00
             */

            public boolean isNull(String s) {
                if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
                    return true;
                }

                return false;
            }

            /**
             * 图片回调方法
             */
            private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
                @Override
                public void onSelectSuccess(List<LocalMedia> resultList) {
                    selectMedia = resultList;
                    Log.i("callBack_result", selectMedia.size() + "");
                    LocalMedia media = resultList.get(0);
                    if (media.isCompressed()) {
                        // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                        String path = media.getCompressPath();
                    } else {
                        // 原图地址
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
             * 単独拍照图片回调
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




            //定义处理接收方法
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
