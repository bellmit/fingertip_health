package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.OnlineChatDetailAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.PersonMessage;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.OnLineChatingActivityContract;
import com.jqsoft.fingertip_health.di.module.OnlineChatingActivityModule;
import com.jqsoft.fingertip_health.di.presenter.OnLineChatingActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jerry on 2017/9/4.
 */

public class ChatDetailActivity extends AbstractActivity implements OnLineChatingActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sign_service_assess_title)
    TextView sign_service_assess_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.id_input_msg)
    EditText id_input_msg;
    @BindView(R.id.id_send_msg)
    Button id_send_msg;
    @BindView(R.id.lay_content_remind)
    View layContentRemind;
    SwipeRefreshLayout srlRemind;
    RecyclerView rvRemind;
    @BindView(R.id.lay_remind_load_failure)
    View remindFailureView;
    TextView tvRemindFailureView;
    List<PersonMessage> remindList;
    OnlineChatDetailAdapter OnlineChatDetailAdapter;
    @Inject
    OnLineChatingActivityPresenter onLineChatingActivityPresenter;
    private String docUserID, total, flag = "3";
    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_MESSAGE_PAGE_SIZE;
    private boolean isRefresh = false;
    private boolean isMore = false;
    private int TIME = 5000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chatdetail;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addchatActivity(new OnlineChatingActivityModule(this))
                .inject(this);
    }

    @Override
    protected void initData() {
        remindList = new ArrayList<>();
        docUserID = getIntent().getStringExtra("docUserID") ;
        total = getIntent().getStringExtra("total");
    }
    private CompositeSubscription mCompositeSubscription;
    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        sign_service_assess_title.setText("在线咨询");

        if (mCompositeSubscription == null) {
            registerIndexSelectSubscription();
        }

        srlRemind = (SwipeRefreshLayout) layContentRemind;
        rvRemind = (RecyclerView) layContentRemind.findViewById(R.id.recyclerview);
        srlRemind.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srlRemind.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OnlineChatDetailAdapter.setEnableLoadMore(false);
                isMore = true;
                flag = "2";
                ++currentPage;
                loadRemindList();

            }
        });

        tvRemindFailureView = (TextView) remindFailureView.findViewById(R.id.tv_load_failure_hint);
        RxView.clicks(tvRemindFailureView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        OnlineChatDetailAdapter.setEnableLoadMore(false);
                        loadRemindList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        OnlineChatDetailAdapter = new OnlineChatDetailAdapter(getApplicationContext(), remindList);
//        easyLoadMoreView = new EasyLoadMoreView();
//        remindAdapter.setLoadMoreView(easyLoadMoreView);
        OnlineChatDetailAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        OnlineChatDetailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rvRemind);
        OnlineChatDetailAdapter.setEnableLoadMore(false);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvRemind.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, rvRemind, true);
        rvRemind.setAdapter(OnlineChatDetailAdapter);
//        int index = OnlineChatDetailAdapter.getItemCount() - 1;
//        rvRemind.smoothScrollToPosition(index);
//        OnlineChatDetailAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//                PersonMessage bean = OnlineChatDetailAdapter.getItem(position);
//                int type = 1;
//                if (RemindBean.TYPE_REMIND == type) {
//                    Util.gotoExecutionProjectsActivity(getActivity(), ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days);
//                } else {
//
//                }
//
//            }
//        });
        loadRemindList();
        id_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postMessage = id_input_msg.getText().toString();
                if (!TextUtils.isEmpty(postMessage)) {
                    String residentCardNo = IdentityManager.getCardNo(ChatDetailActivity.this);
                    String senderName = IdentityManager.getPersonName(ChatDetailActivity.this);
                    //  Map<String, String> map = ParametersFactory.postMessage(postMessage, Identity.getPersonID(),docUserID);
                    Map<String, String> map = ParametersFactory.postMessage(ChatDetailActivity.this, postMessage, IdentityManager.getPersonID(ChatDetailActivity.this), docUserID, residentCardNo, senderName);
                    RequestBody body = Util.getBodyFromMap(map);
                    onLineChatingActivityPresenter.saveOnlineConsultation(body);
                } else {
                    Util.showToast(getApplicationContext(), "输入内容后发送");
                }


            }
        });

        int num = Integer.parseInt(Util.trimString(total));
        if (num > 0) {
//        if (true) {
            updateOnlineChatReadStatus();
//            //     Map<String, String> map = ParametersFactory.updateOnlineConsultation(docUserID,Identity.getPersonID());
//            Map<String, String> map = ParametersFactory.updateOnlineConsultation(this, docUserID, IdentityManager.getPersonID(this));
//            RequestBody body = Util.getBodyFromMap(map);
//            onLineChatingActivityPresenter.updateOnlineChat(body);
        }
//        handler.postDelayed(runnable, TIME); //每隔3s执行

//        new Runnable(){
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                loadRemindList();
//            }
//        };
    }

    private void updateOnlineChatReadStatus(){
        //     Map<String, String> map = ParametersFactory.updateOnlineConsultation(docUserID,Identity.getPersonID());
        Map<String, String> map = ParametersFactory.updateOnlineConsultation(this, docUserID, IdentityManager.getPersonID(this));
        RequestBody body = Util.getBodyFromMap(map);
        onLineChatingActivityPresenter.updateOnlineChat(body);

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                handler.postDelayed(this, TIME);
                loadRemindList();
                System.out.println("do...");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
        }
    };

    private void loadRemindList() {
        Map<String, String> map = getRemindListRequestMap();
        RequestBody body = Util.getBodyFromMap(map);
        onLineChatingActivityPresenter.getPersonMessage(body, isMore);

    }
    private void registerIndexSelectSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_CHATDETAILACTIVTY, Boolean.class).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean b) {
                loadRemindList();
            }
        });

        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);

    }
    private void unregisterSubscription(){
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSubscription();
    }

    private Map<String, String> getRemindListRequestMap() {
//        String cardNo = Identity.getCardNo();
        String cardNo = IdentityManager.getCardNo(this);
        String personID = IdentityManager.getPersonID(this);
        Map<String, String> map = ParametersFactory.getPersonMessage(this, currentPage, pageSize, flag, docUserID, personID);
        return map;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onMessageDataSuccess(HttpResultBaseBean<List<PersonMessage>> bean) {
        srlRemind.setRefreshing(false);
        RxBus.getDefault().post(Constants.EVENT_TYPE_INDEX_REFRESH_INTELLIGENT_HONOUR_AGREEMENT, true);
//        RxBus.getDefault().post(Constants.EVENT_TYPE_SURFADAPTER, docUserID);
        updateOnlineChatReadStatus();
        if (bean != null) {
            if (bean.getData().size() > 0) {
                remindList.clear();
                Collections.reverse(bean.getData()); // 倒序排列
                remindList.addAll(bean.getData());
                OnlineChatDetailAdapter.notifyDataSetChanged();
                int index = OnlineChatDetailAdapter.getItemCount() - 1;
                rvRemind.smoothScrollToPosition(index);
                showRemindRecyclerViewOrFailureView(true, false);
            }
        } else {
            showRemindRecyclerViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<PersonMessage>> bean) {
        //      Util.hideGifProgressDialog(this);
        srlRemind.setRefreshing(false);
        RxBus.getDefault().post(Constants.EVENT_TYPE_INDEX_REFRESH_INTELLIGENT_HONOUR_AGREEMENT, true);
//        RxBus.getDefault().post(Constants.EVENT_TYPE_SURFADAPTER, docUserID);
//        updateOnlineChatReadStatus();
        if (bean.getData().size() > 0) {
            Collections.reverse(bean.getData()); // 倒序排列
            remindList.addAll(0, bean.getData());
            OnlineChatDetailAdapter.notifyDataSetChanged();
            int index = OnlineChatDetailAdapter.getItemCount() - 1;
            rvRemind.smoothScrollToPosition(index);
            showRemindRecyclerViewOrFailureView(true, false);
        } else {
           // Util.showToast(getApplicationContext(), bean.getMessage());
        }

    }

    private void showRemindRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                layContentRemind.setVisibility(View.GONE);
                remindFailureView.setVisibility(View.VISIBLE);
                tvRemindFailureView.setText(getRemindListEmptyHint());
            } else {
                layContentRemind.setVisibility(View.VISIBLE);
                remindFailureView.setVisibility(View.GONE);
            }
        } else {
            layContentRemind.setVisibility(View.GONE);
            remindFailureView.setVisibility(View.VISIBLE);
            tvRemindFailureView.setText(getRemindFailureHint());

        }
    }

    private String getRemindListEmptyHint() {
        return getResources().getString(R.string.hint_no_remind_info_please_click_to_reload);
    }

    private String getRemindFailureHint() {
        return getResources().getString(R.string.hint_load_remind_info_error_please_click_to_reload);
    }

    @Override
    public void onMessageDataFailure(String message, boolean isLoadMore) {

    }

    @Override
    public void onsaveOnlineFailure(String message) {


    }

    @Override
    public void onsaveOnlineSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
        Util.hideGifProgressDialog(this);
        Util.showToast(getApplicationContext(), bean.getMessage());
        PersonMessage personMessage = new PersonMessage();
        personMessage.setPostMessage(id_input_msg.getText().toString());
        personMessage.setSenderName(IdentityManager.getPersonName(this));
        personMessage.setSender(IdentityManager.getPersonID(this));
        personMessage.setSetTime(Util.getCurrentYearMonthDayString() + " " + Util.getHourMinSecString());
        personMessage.setPhotoUrl(PreferencesUtils.getString(this, "imageUrl"));
        remindList.add(personMessage);
        OnlineChatDetailAdapter.notifyDataSetChanged();
        int index = OnlineChatDetailAdapter.getItemCount() - 1;
        rvRemind.smoothScrollToPosition(index);
        id_input_msg.setText("");
        RxBus.getDefault().post(Constants.EVENT_TYPE_REFRESH_CHAT_PERSON_LIST, true);
        RxBus.getDefault().post(Constants.EVENT_TYPE_INDEX_REFRESH_INTELLIGENT_HONOUR_AGREEMENT, true);
    }

    @Override
    public void updateOnlineSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
        Util.hideGifProgressDialog(this);
        //    Util.showToast(getApplicationContext(), bean.getMessage());
        RxBus.getDefault().post(Constants.EVENT_TYPE_SURFADAPTER, docUserID);
    }

    @Override
    public void updateOnlineFailure(String message) {
        Util.showToast(getApplicationContext(), message);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
