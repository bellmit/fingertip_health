package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.RemindAdapter;
import com.jqsoft.fingertip_health.adapter.SignDoctorNameAndPhoneAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.PolicyBean;
import com.jqsoft.fingertip_health.bean.resident.RemindAndMessageBean;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;
import com.jqsoft.fingertip_health.callback.MyResultCallback;
import com.jqsoft.fingertip_health.configuration.ExecutionProjectsType;
import com.jqsoft.fingertip_health.di.contract.SmartAlertActivityContract;
import com.jqsoft.fingertip_health.di.module.SmartAlertActivityModule;
import com.jqsoft.fingertip_health.di.presenter.SmartAlertActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Jerry on 2017/8/28.
 */

public class SmartAlertActivity extends AbstractActivity implements SmartAlertActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.lay_content_remind)
    View layContentRemind;

    SwipeRefreshLayout srlRemind;
    RecyclerView rvRemind;
    @BindView(R.id.lay_remind_load_failure)
    View remindFailureView;
    TextView tvRemindFailureView;
    List<RemindBean> remindList;
    RemindAdapter remindAdapter;

    @BindView(R.id.ll_dial)
    LinearLayout llDial;

    String cardNo;
    String personID;


    @Inject
    SmartAlertActivityPresenter mPresenter;

    public void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addSmartAlertActivity(new SmartAlertActivityModule(this))
                .inject(this);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.smartalertactivity;
    }

    @Override
    protected void initData() {
        remindList = new ArrayList<>();

        cardNo=getDeliveredStringByKey(Constants.CARD_NO_KEY);
        personID=getDeliveredStringByKey(Constants.PERSON_ID_KEY);
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("智能履约提醒");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        srlRemind = (SwipeRefreshLayout) layContentRemind;
        rvRemind = (RecyclerView) layContentRemind.findViewById(R.id.recyclerview);
        srlRemind.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srlRemind.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                remindAdapter.setEnableLoadMore(false);
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
                        remindAdapter.setEnableLoadMore(false);
                        loadRemindList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        remindAdapter = new RemindAdapter(remindList);
//        easyLoadMoreView = new EasyLoadMoreView();
//        remindAdapter.setLoadMoreView(easyLoadMoreView);
        remindAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        remindAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rvRemind);
        remindAdapter.setEnableLoadMore(false);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvRemind.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, rvRemind, true);
        rvRemind.setAdapter(remindAdapter);
        remindAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                RemindBean bean = remindAdapter.getItem(position);
                int type = bean.getType();
                if (RemindBean.TYPE_REMIND == type) {
                    Util.gotoExecutionProjectsActivity(SmartAlertActivity.this, ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days,
                            cardNo);
                } else {
                    Identity.shouldReadIdCard = false;
                    RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_QUERY);
                }

            }
        });
        loadRemindList();

        RxView.clicks(llDial)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        callDoctor();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MaterialDialog.Builder builder = null;
    private List<DoctorTeamInfo> list = new ArrayList<>();
    BaseQuickAdapter<DoctorTeamInfo, BaseViewHolder> mAdapter;
    MaterialDialog dialog;
    RequestCall requestCall;

    private void callDoctor(){
        mAdapter = new SignDoctorNameAndPhoneAdapter(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        builder = new MaterialDialog.Builder(this)
                .title(null)
                .negativeText(Constants.CANCEL)
                // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                .adapter(mAdapter, new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .autoDismiss(true);
        dialog = (MaterialDialog) builder.build();
        showPhoneDialog();
    }

    public void showPhoneDialog(){
        String url = Version.HTTP_URL+Constants.URL_PATH_CALL_DOCTOR_TEAM;
//        String cardNo = Identity.getCardNo();
        Map<String, String> params =   ParametersFactory.getDoctorTeamDataMap(this, cardNo);
        String json = new Gson().toJson(params);


//        Util.showGifProgressDialog(this);
        Util.showGifProgressDialog(this, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                cancelGettingDoctorTeamProcess();
            }
        });
        requestCall = OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build();
        requestCall.execute(new MyResultCallback<HttpResultBaseBean<List<DoctorTeamInfo>>>() {

                    @Override
                    public void onSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
                        Util.decodeBase64Bean(bean);
                        Util.hideGifProgressDialog(SmartAlertActivity.this);
                        if (bean!=null){
                            final List<DoctorTeamInfo> newList = bean.getData();
//            for (int i = 0; i < 20; ++i){
//                DoctorTeamInfo info = new DoctorTeamInfo("","","医生"+i, "15209999999", "", "0");
//                list.add(info);
//            }
                            if (!ListUtils.isEmpty(newList)){
                                list.clear();
                                list.addAll(newList);
                                mAdapter.setOnItemClickListener(new SignDoctorNameAndPhoneAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        DoctorTeamInfo dti = list.get(position);
                                        String phone = Util.trimString(dti.getDoctorPhone());
                                        Util.dial(SmartAlertActivity.this, phone);

                                        dialog.dismiss();
                                    }
                                });
                                try {
//                                    if (((Activity)context).hasWindowFocus()) {
                                    dialog.show();
//                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                showDoctorPhoneInfoResult(true);
                            }
                        } else {
                            showDoctorPhoneInfoResult(true);
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Util.hideGifProgressDialog(SmartAlertActivity.this);
                        showDoctorPhoneInfoResult(false);
                    }
                });

    }

    private void showDoctorPhoneInfoResult(boolean isEmptyOrFailure){
        if (isEmptyOrFailure){
            Util.showToast(this, Constants.HINT_NO_SIGN_DOCTOR_PHONE_INFO);
        } else {
            Util.showToast(this, Constants.HINT_LOAD_SIGN_DOCTOR_PHONE_INFO_FAILURE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelGettingDoctorTeamProcess();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        cancelGettingDoctorTeamProcess();
    }

    private void cancelGettingDoctorTeamProcess(){
        if (requestCall!=null){
            requestCall.cancel();
            requestCall=null;
        }

    }

    @Override
    protected void loadData() {

    }

    private void loadRemindList() {
        Map<String, String> map = getRemindListRequestMap();
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.getRemindData(body);

    }

    private Map<String, String> getRemindListRequestMap() {
        Map<String, String> map = ParametersFactory.getRemindDataMapForFamilyMember(this, cardNo, personID);
//        String cardNo = Identity.getCardNo();
//        Map<String, String> map = ParametersFactory.getRemindDataMap(cardNo);
        return map;
    }

    @Override
    public void onLoadPolicyDataSuccess(HttpResultBaseBean<List<PolicyBean>> bean) {

    }

    @Override
    public void onLoadPolicyDataFailure(String message) {

    }

    @Override
    public void onLoadRemindDataSuccess(HttpResultBaseBean<RemindAndMessageBean> wrapper) {
        srlRemind.setRefreshing(false);
        if (wrapper != null) {
            RemindAndMessageBean ramb = wrapper.getData();
            if (ramb != null) {
                List<RemindBean> list = getRemindListFromAddingMessageBean(ramb);
                if (!ListUtils.isEmpty(list)) {
                    remindList.clear();
                    remindList.addAll(list);
                    remindAdapter.notifyDataSetChanged();
                    showRemindRecyclerViewOrFailureView(true, false);
                    //                showRemindRecyclerViewOrFailureView(true, true);
                    //                showRemindRecyclerViewOrFailureView(false, true);
                } else {
                    showRemindRecyclerViewOrFailureView(true, true);
                }
            } else {
                showRemindRecyclerViewOrFailureView(true, true);
            }
        } else {
            showRemindRecyclerViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadRemindDataFailure(String message) {
        srlRemind.setRefreshing(false);
        showRemindRecyclerViewOrFailureView(false, true);
//        simulateRemind();
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    private List<RemindBean> getRemindListFromAddingMessageBean(RemindAndMessageBean ramb) {
        List<RemindBean> result = new ArrayList<>();
        if (ramb == null) {
            return result;
        } else {
            String message = Util.trimString(ramb.getContent());
            String count = Util.trimString(ramb.getTotal());
            List<RemindBean> remindList = ramb.getList();
            if (remindList == null) {
                remindList = new ArrayList<>();
            }
            for (int i = 0; i < remindList.size(); ++i) {
                RemindBean bean = remindList.get(i);
                bean.setType(RemindBean.TYPE_REMIND);
            }
            RemindBean messageBean = new RemindBean(RemindBean.TYPE_MESSAGE, R.mipmap.r_remind_3, message, count,
                    Constants.EMPTY_STRING);
            remindList.add(messageBean);

            result.addAll(remindList);
        }
        return result;
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
