//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.content.Intent;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.ChatAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.ChatConfigurationBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
//import com.jqsoft.fingertip_health.bean.response.ChatBean;
//import com.jqsoft.fingertip_health.bean.response.ChatListWrapperBean;
//import com.jqsoft.fingertip_health.di.contract.ChatActivityContract;
//import com.jqsoft.fingertip_health.di.module.ChatActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.ChatActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.simulate.SimulateData;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.rengwuxian.materialedittext.MaterialEditText;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
//
//public class ChatActivity extends AbstractActivity implements
//        ChatActivityContract.View, SwipeRefreshLayout.OnRefreshListener,
//        BaseQuickAdapter.RequestLoadMoreListener {
//
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @BindView(R.id.met_content)
//    MaterialEditText metContent;
//    @BindView(R.id.btn_send)
//    AppCompatButton btnSend;
//
//    @Inject
//    ChatActivityPresenter mPresenter;
//
//    private CompositeSubscription mShouldRefreshSubscription;
//
//    private boolean isRefresh = false;
//
//    private ChatAdapter mAdapter;
//    private List<ChatBean> chatList=new ArrayList<>();
////    private EasyLoadMoreView easyLoadMoreView;
//
//    public String fromId;
//    public String toId;
//
//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
//
////    @OnClick(R.id.btn_send)
//    public void onSend(){
//        String msg = getMessageContent();
//        if (StringUtils.isBlank(msg)){
//            Util.showToast(this, Constants.HINT_PLEASE_INPUT_MSG_TO_SEND);
//        } else {
////            Util.showToast(this, "将要发送消息:"+msg);
//            sendMessage(msg);
//        }
//    }
//
//    public void sendMessage(String msg){
//        Map<String, String> map = ParametersFactory.getSendMsgMap(toId, msg);
//        if (map==null){
//            Util.showToast(this, Constants.HINT_MSG_LENGTH_CANNOT_EXCEED_ONE_VALUE);
//        } else {
//            mPresenter.sendMessage(map);
//
//        }
//
//    }
//
//    public String getMessageContent(){
//        return Util.trimString(metContent.getText().toString());
//    }
//
//    private void registerShouldRefreshEvent(){
//        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE, ChatConfigurationBean.class).subscribe(new Action1<ChatConfigurationBean>() {
//            @Override
//            public void call(ChatConfigurationBean chatConfigurationBean) {
//                if (chatConfigurationBean!=null){
//                    fromId=chatConfigurationBean.getFromId();
//                    fromId=Util.trimString(fromId);
//                    toId=chatConfigurationBean.getToId();
//                    toId=Util.trimString(toId);
//
//                }
//                onRefresh();
//            }
//        });
//        if (mShouldRefreshSubscription==null){
//            mShouldRefreshSubscription=new CompositeSubscription();
//        }
//        mShouldRefreshSubscription.add(mSubscription);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intentIn) {
//        super.onNewIntent(intentIn);
//        setIntent(intentIn);
//        Intent intent = getIntent();
//        if (intent!=null){
//            fromId=intent.getStringExtra(Constants.MESSAGE_FROM_USER_ID_KEY);
//            fromId=Util.trimString(fromId);
//            toId=intent.getStringExtra(Constants.MESSAGE_TO_USER_ID_KEY);
//            toId=Util.trimString(toId);
//        }
//        onRefresh();
//    }
//
//    @Override
//    protected void loadData() {
//        if (mShouldRefreshSubscription==null){
//            registerShouldRefreshEvent();
//        }
//        onRefresh();
////        currentIndex = 0;//为了以后写下拉刷新 不然可以直接用这个方法。
////        mPresenter.main(null, false);
//    }
//
////    @Override
////    public int getContentLayoutId() {
////        return R.layout.layout_recyclerview;
////    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_chat;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
////    @Override
////    public int setFrameLayoutId() {
////        return R.id.framelayout;
////    }
//
////    @Override
////    protected void initUI() {
////        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
////        setToolBar(toolbar, "");
////    }
//
//
//    @Override
//    protected void initView() {
//        LogUtil.i("ChatActivity initView enter");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setToolBar(toolbar, "");
//
//        Intent intent = getIntent();
//        if (intent!=null){
//            fromId=intent.getStringExtra(Constants.MESSAGE_FROM_USER_ID_KEY);
//            fromId=Util.trimString(fromId);
//            toId=intent.getStringExtra(Constants.MESSAGE_TO_USER_ID_KEY);
//            toId=Util.trimString(toId);
//        }
//
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//
//
//        BaseQuickAdapter<ChatBean, BaseViewHolder> mAdapter = new ChatAdapter(new ArrayList<ChatBean>());
//        this.mAdapter = (ChatAdapter) mAdapter;
////        easyLoadMoreView = new EasyLoadMoreView();
////        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.setEnableLoadMore(false);
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
//        Util.addDividerToRecyclerView(this, recyclerView, true);
//        recyclerView.setAdapter(mAdapter);
////        ((ChatAdapter) mAdapter).setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                MaterialDialog dialog = new MaterialDialog.Builder(ChatActivity.this)
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
////
////            }
////
//////            @Override
//////            public void onItemClickListener(String id, String imgUrl, View view) {
//////                startZhiHuDetailActivity(id, imgUrl, view);
//////            }
////        });
//
//        btnSend.setText(Constants.BTN_SEND_TEXT);
//        btnSend.setTextColor(getResources().getColor(R.color.colorTheme));
//        btnSend.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onSend();
//            }
//        });
//
////        LogUtil.i("ChatActivity initView before simulateData");
////        simulateData();
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addChatActivity(new ChatActivityModule(this))
//                .inject(this);
////        DaggerTopNewsComponent.builder()
////                .topNewsHttpModule(new TopNewsHttpModule())
////                .topNewsModule(new TopNewsModule())
////                .build().injectTopNews(this);
//    }
//
//
////    @Override
////    public void refreshView(ChatListWrapperBean data) {
//////        LogUtils.e("aaaacurrentIndex" + currentIndex);
////        LogUtil.i("ChatFragment refreshView");
////        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();
////
////        chatList = (ArrayList<ChatBean>) simulatedList;
//////        mAdapter.addData(chatList);
//////        index += 1;
//////        currentIndex = mAdapter.getData().size() - 2 * index;
//////        mAdapter.loadMoreComplete();
//////
////        if (isRefresh) {
////            srl.setRefreshing(false);
////            mAdapter.setEnableLoadMore(true);
////            isRefresh = false;
////            mAdapter.setNewData(chatList);
////        } else {
////            srl.setEnabled(true);
////            index += 20;
////            mAdapter.addData(chatList);
////            mAdapter.loadMoreComplete();
////        }
////
////
////    }
//
//    public void simulateData() {
//        LogUtil.i("ChatFragemnt simulateData");
////        setState(AppConstants.STATE_SUCCESS);
//
//        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();
//
//        chatList = (ArrayList<ChatBean>) simulatedList;
//        mAdapter.getData().clear();
//        mAdapter.addData(chatList);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.loadMoreComplete();
//
//    }
//
//
//    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
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
//    }
//
//    @Override
//    public void onRefresh() {
//        currentPage = Constants.DEFAULT_INITIAL_PAGE;
//        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
//        LogUtil.i("ChatFragment onRefresh");
//        mPresenter.main(null, false);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mShouldRefreshSubscription!=null&&mShouldRefreshSubscription.hasSubscriptions()){
//            mShouldRefreshSubscription.unsubscribe();
//        }
//    }
//
//    @Override
//    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
//        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//        mAdapter.loadMoreEnd(true);
//////        // mQuickAdapter.setAutoLoadMoreSize(int);
//////        if (currentIndex >= 60) {
//////            mAdapter.loadMoreEnd();
//////            srl.setEnabled(true);
//////        } else {
////        mPresenter.main(null, true);
////        srl.setEnabled(false);
//////        }
//    }
//
//    public List<ChatBean> getListFromResult(HttpResultBaseBean<ChatListWrapperBean> beanList){
//        if (beanList!=null){
//            ChatListWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                return wrapperBean.getList();
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    public int getPageFromResult(HttpResultBaseBean<ChatListWrapperBean> beanList) {
//        if (beanList!=null){
//            ChatListWrapperBean wrapperBean = beanList.getData();
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
//    public int getPageSizeFromResult(HttpResultBaseBean<ChatListWrapperBean> beanList){
//        if (beanList!=null){
//            ChatListWrapperBean wrapperBean = beanList.getData();
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
//
//    public int getListSizeFromResult(HttpResultBaseBean<ChatListWrapperBean> beanList){
//        if (beanList!=null){
//            ChatListWrapperBean wrapperBean = beanList.getData();
//            if (wrapperBean!=null){
//                List<ChatBean> list = wrapperBean.getList();
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
//    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful){
//        if (isSuccessful) {
////            if (listSize<pageSize){
//////                mAdapter.setEnableLoadMore(false);
////                mAdapter.loadMoreEnd(true);
////            } else {
////                mAdapter.setEnableLoadMore(true);
////                mAdapter.loadMoreComplete();
////            }
//            mAdapter.loadMoreEnd(true);
//        } else {
////            mAdapter.setEnableLoadMore(true);
////            mAdapter.loadMoreFail();
//            mAdapter.loadMoreEnd(true);
//        }
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<ChatListWrapperBean> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
//        List<ChatBean> list = getListFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("ChatActivity onLoadListSuccess,returned list size:"+pageSize+" currentPage/pageSize/listSize:"+currentPage+"/"+ this.pageSize+"/"+listSize);
//
//        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();
//
////        chatList = (ArrayList<ChatBean>) simulatedList;
//        if (list!=null){
//            chatList=list;
//        } else {
//            chatList = new ArrayList<>();
//        }
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0,0,true);
////        mAdapter.setEnableLoadMore(true);
//        isRefresh = false;
//        mAdapter.setNewData(chatList);
//
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<ChatListWrapperBean> beanList) {
//        int  page = getPageFromResult(beanList);
//        int pageSize = getPageSizeFromResult(beanList);
//        int listSize = getListSizeFromResult(beanList);
//        List<ChatBean> list = getListFromResult(beanList);
//        currentPage=page;
//        this.pageSize =pageSize;
//        LogUtil.i("ChatActivity onLoadMoreListSuccess,returned list size:"+pageSize+" currentPage/pageSize"+currentPage+"/"+ this.pageSize);
//
//        List<ChatBean> simulatedList = SimulateData.getSimulatedChatBeanList();
//
////        chatList = (ArrayList<ChatBean>) simulatedList;
//        if (list!=null){
//            chatList=list;
//        } else {
//            chatList = new ArrayList<>();
//        }
//
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        mAdapter.addData(chatList);
//        setLoadMoreStatus(0, 0, true);
////        mAdapter.loadMoreComplete();
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
//        if (isLoadMore){
//            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
//                --currentPage;
//            }
//
//        } else {
//
//        }
//        srl.setRefreshing(false);
//        setLoadMoreStatus(0, 0, false);
//
//        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//    }
//
//    @Override
//    public void onSendMessageSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//        Util.showToast(this, Constants.HINT_SEND_MESSAGE_SUCCESS);
//        onRefresh();
//    }
//
//    @Override
//    public void onSendMessageFailure(String message) {
//        Util.showToast(this, Constants.HINT_SEND_MESSAGE_FAILURE);
//    }
//}
