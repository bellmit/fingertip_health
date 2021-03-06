package com.jqsoft.fingertip_health.di.ui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.Serializable;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//import cn.jpush.im.android.api.JMessageClient;
//import cn.jpush.im.android.api.event.ConversationRefreshEvent;
//import cn.jpush.im.android.api.event.LoginStateChangeEvent;
//import cn.jpush.im.android.api.event.MessageEvent;
//import cn.jpush.im.android.api.event.NotificationClickEvent;
//import cn.jpush.im.android.api.event.OfflineMessageEvent;
//import cn.jpush.im.android.api.model.Conversation;

/**
 * Created by Administrator on 2017/5/21.
 */

//public abstract class AbstractActivity extends AppCompatActivity {
public abstract class AbstractActivity extends RxAppCompatActivity {

//    private Unbinder bind;

    CompositeSubscription mCompositeSubscription;

    protected void registerFinishActivityEvent() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_FINISH_ACTIVITY, Boolean.class)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean b) {
                        if (b) {
                            finish();
                        }
                    }
                });
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void unregisterFinishActivityEvent() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void loadData();

    protected void initInject() {

    }

    public Serializable getDeliveredSerializableByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return null;
            } else {
                Serializable result = intent.getSerializableExtra(key);
                return result;
            }
        }
    }

    public String getDeliveredStringByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return Constants.EMPTY_STRING;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return Constants.EMPTY_STRING;
            } else {
                String result = intent.getStringExtra(key);
                if (result==null){
                    result=Constants.EMPTY_STRING;
                }
                return result;
            }
        }
    }

    public int getDeliveredIntByKey(String key) {
        LogUtil.i("getDeliveredIntByKey key:"+key);
        if (StringUtils.isBlank(key)) {
            return Constants.DEFAULT_INT;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            LogUtil.i("getDeliveredIntByKey intent:"+intent);
            if (intent == null) {
                return Constants.DEFAULT_INT;
            } else {
                int result = intent.getIntExtra(key, Constants.DEFAULT_INT);
                return result;
            }
        }
    }

    public boolean getDeliveredBooleanByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return false;
            } else {
                boolean result = intent.getBooleanExtra(key, false);
                return result;
            }
        }
    }

//    public void registerEventReceiver() {
//        JMessageClient.registerEventReceiver(this);
//    }
//
//    public void unregisterEventReceiver() {
//        JMessageClient.unRegisterEventReceiver(this);
//    }

    /**
     * ?????????JMessageClient.init????????????????????????????????????????????????????????????????????????????????????
     * sdk?????????????????????????????????
     **/
//    public void onEvent(ConversationRefreshEvent event) {
//        //?????????????????????????????????
//        Conversation conversation = event.getConversation();
//        //???????????????????????????????????????????????????????????????????????????reason?????????
//        //MSG_ROAMING_COMPLETE
//        ConversationRefreshEvent.Reason reason = event.getReason();
//        LogUtil.i(String.format(Locale.SIMPLIFIED_CHINESE, "??????ConversationRefreshEvent??????,?????????????????????%s.\n", conversation.getTargetId()));
//        LogUtil.i("????????????????????? : " + reason);
//    }
//
//    public void onEvent(OfflineMessageEvent event) {
//    }
//
//    public void onEvent(MessageEvent event) {
//    }
//
//    public void onEvent(LoginStateChangeEvent event) {
//    }
//
//    public void onEvent(NotificationClickEvent event) {
////        Message msg = event.getMessage();
////        UserInfo userInfo = (UserInfo) msg.getFromUser();
////        String title = Identity.getTargetDisplayTitle(userInfo);
////        String targetId = userInfo.getUserName(); ;
////        String appkey = msg.getFromAppKey();
////
////        if (Util.isCurrentChatActivity(this)){
////            MessageListRefreshConfigurationBean bean = new MessageListRefreshConfigurationBean();
////            bean.setTitle(title);
////            bean.setTargetId(targetId);
////            bean.setAppkey(appkey);
////            RxBus.getDefault().post(Constants.EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE, bean);
////        } else if (Util.isAppVisibleToUser(this)){
////            Intent intent = new Intent(this, MessageListActivity.class);
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
////            intent.putExtra(Constants.CONV_TITLE, title);
////            intent.putExtra(Constants.TARGET_ID, targetId);
////            intent.putExtra(Constants.TARGET_APP_KEY, appkey);
////            startActivity(intent);
////        } else if (Util.isAppRunning(this)){
////            Util.bringApplicationToForegroundFromChatMessageNotificationClick2(this, title, targetId, appkey);
//////            Util.runApplicationFromScratch(this, Constants.PACKAGE_NAME);
////        } else {
////            Util.runApplicationFromScratch(this, Constants.PACKAGE_NAME);
////        }
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

//        registerEventReceiver();

        registerFinishActivityEvent();

        initData();
        initInject();
        initView();
        loadData();


    }

    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//??????toolbar???????????????
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View backView = toolbar.findViewById(R.id.ll_back);
        if (backView != null) {
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                onBackPressed();
////            }
////        });
    }

    //?????????????????????
    public void setToolBarWithNoBackButton(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//??????toolbar???????????????
        getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    //??????????????????????????????
    public void setToolBarWithNoBackButtonAndNoTitle(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//??????toolbar???????????????
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (bind != null) {
//            bind.unbind();
//        }
//        unregisterEventReceiver();
        unregisterFinishActivityEvent();
        DaggerApplication.getRefWatcher().watch(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //????????????Fragment?????????
//        super.onSaveInstanceState(outState);
    }

    private String TAG = "WorkbenchActivity";

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
//            Fragment fragment = fragmentManager.getFragments().get(indext); //???????????????Fragment
//            if (fragment == null)
//                Log.w(TAG, "Activity result no fragment exists for index: 0x"
//                        + Integer.toHexString(requestCode));
//            else
//                handleResult(fragment, requestCode, resultCode, data);
//        }
//    }
//
//    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
//        fragment.onActivityResult(requestCode, resultCode, data);//????????????Fragment???onActivityResult
//        // Log.e(TAG, "MyBaseFragmentActivity");
//        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //???????????????Fragment
//        if (childFragment != null)
//            for (Fragment f : childFragment)
//                if (f != null) {
//                    handleResult(f, requestCode, resultCode, data);
//                }
//        if (childFragment == null)
//            Log.e(TAG, "MyBaseFragmentActivity1111");
//    }
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        View v = getCurrentFocus();
        if (isShouldHideInput(v, ev)) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                assert v != null;
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                /** here */
                v.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    // ????????????????????????????????????????????????TouchEvent???
    return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
}

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //????????????????????????location??????
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
