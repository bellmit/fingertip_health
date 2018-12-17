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
     * 如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
     * sdk会发送此事件通知上层。
     **/
//    public void onEvent(ConversationRefreshEvent event) {
//        //获取事件发生的会话对象
//        Conversation conversation = event.getConversation();
//        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是
//        //MSG_ROAMING_COMPLETE
//        ConversationRefreshEvent.Reason reason = event.getReason();
//        LogUtil.i(String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
//        LogUtil.i("事件发生的原因 : " + reason);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//显示toolbar的返回按钮
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

    //不显示返回按钮
    public void setToolBarWithNoBackButton(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//显示toolbar的返回按钮
        getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    //不显示返回按钮和标题
    public void setToolBarWithNoBackButtonAndNoTitle(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//显示toolbar的返回按钮
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
        //不再保存Fragment的状态
//        super.onSaveInstanceState(outState);
    }

    private String TAG = "WorkbenchActivity";

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
//            Fragment fragment = fragmentManager.getFragments().get(indext); //找到第一层Fragment
//            if (fragment == null)
//                Log.w(TAG, "Activity result no fragment exists for index: 0x"
//                        + Integer.toHexString(requestCode));
//            else
//                handleResult(fragment, requestCode, resultCode, data);
//        }
//    }
//
//    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
//        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
//        // Log.e(TAG, "MyBaseFragmentActivity");
//        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
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
    // 必不可少，否则所有的组件都不会有TouchEvent了
    return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
}

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
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
