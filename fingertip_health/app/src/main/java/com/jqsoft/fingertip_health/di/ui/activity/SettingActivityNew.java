package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.tencent.bugly.beta.Beta;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.jqsoft.fingertip_health.util.Util.gotoActivity;

/**
 * Created by Administrator on 2017-07-17.
 */

public class SettingActivityNew extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;
    @BindView(R.id.bt_exit_login)
    AppCompatButton btExitLogin;

    CompositeSubscription mCompositeSubscription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_new;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        llCheckUpdate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                Beta.checkUpgrade();
//                Beta.checkUpgrade(true, false);
            }
        });

        btExitLogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                warnExit();
            }
        });
    }

    @Override
    protected void loadData() {
        registerUpgradeEvent();
    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterUpgradeEvent();
    }

    public void warnExit() {
        Util.showMaterialDialog(this, Constants.HINT, Constants.HINT_CONFIRM_EXITING, Constants.CANCEL,
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                        doExit();
                    }
                }, true);
    }

    private void doExit() {
        //   Util.logoutJMessageAndExitApplication();

        PreferencesUtils.putBoolean(this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, false);
        PreferencesUtils.putString(this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);

//        Util.exitApplication();
        exitApp();

    }

    private void exitApp(){
        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);

//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);
        gotoActivity(this, LoginActivityNew.class);

//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);

    }

    private void handleUpgradeEvent(int i){
        switch (i){
            case Constants.EVENT_TYPE_BUGLY_UPGRADE_SUCCESS:
                Util.showToast(this, Constants.HINT_GET_UPGRADE_INFO_SUCCESS);
                Util.hideGifProgressDialog(this);
                break;
            case Constants.EVENT_TYPE_BUGLY_UPGRADE_FAILURE:
                Util.showToast(this, Constants.HINT_GET_UPGRADE_INFO_FAILURE);
                Util.hideGifProgressDialog(this);
                break;
            case Constants.EVENT_TYPE_BUGLY_UPGRADE_UPGRADING:
                Util.showGifProgressDialog(this);
                break;
            case Constants.EVENT_TYPE_BUGLY_UPGRADE_NO_VERSION:
                Util.showToast(this, Constants.HINT_GET_UPGRADE_INFO_ALREADY_LATEST);
                Util.hideGifProgressDialog(this);
                break;
            case Constants.EVENT_TYPE_BUGLY_UPGRADE_DOWNLOAD_COMPLETED:
                break;
            default:
                break;
        }
    }

    private void registerUpgradeEvent(){
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        handleUpgradeEvent(integer);
                    }
                });
        if (mCompositeSubscription==null){
            mCompositeSubscription=new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void unregisterUpgradeEvent(){
        if (mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

}
