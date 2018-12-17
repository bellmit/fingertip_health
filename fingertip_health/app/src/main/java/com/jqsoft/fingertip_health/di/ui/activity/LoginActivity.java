package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response.LoginResultBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.LoginContract;
import com.jqsoft.fingertip_health.di.presenter.LoginPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils2.VersionUtil;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.qiujuer.genius.ui.widget.CheckBox;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.android.api.TagAliasCallback;
//import cn.jpush.im.android.api.JMessageClient;

//import com.gc.materialdesign.views.CheckBox;

//import com.gc.materialdesign.views.CheckBox;

/**
 * Created by Administrator on 2017-05-15.
 */
//登录界面
public class LoginActivity extends AbstractActivity implements LoginContract.View {
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.username)
    MaterialEditText metUsername;
    @BindView(R.id.bt_username_clear)
    Button btnUsernameClear;
    @BindView(R.id.password)
    MaterialEditText metPassword;
    @BindView(R.id.bt_password_clear)
    Button btnPasswordClear;
    @BindView(R.id.bt_password_eye)
    Button btnPasswordEye;

    //   @BindView(R.id.cb_remember_password)
//    CheckBox cbRememberPassword;
    @BindView(R.id.cb_rp)
    CheckBox cbRp;
    @BindView(R.id.login)
    RoundTextView btnLogin;
    @BindView(R.id.tv_version)
    TextView tvVersion;


    @Inject
    LoginPresenter loginPresenter;

    public static final String TAG = "LoginActivity";

    public static boolean HAS_LOGGED_IN = false;

    public boolean shouldShowBackButton = false;
    public boolean shouldFinishWhenLoginSuccess = false;

    @OnClick(R.id.bt_username_clear)
    public void onClearUsername() {
        metUsername.setText("");
//        metPassword.setText("");
    }

    @OnClick(R.id.bt_password_clear)
    public void onClearPassword() {
        metPassword.setText("");
    }

    @OnClick(R.id.bt_password_eye)
    public void onChangePasswordVisibility() {
        int inputType = metPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            btnPasswordEye.setBackgroundResource(R.mipmap.eye_open_green);
            metPassword.setInputType((InputType.TYPE_CLASS_TEXT));
        } else {
//            btnPasswordEye.setBackgroundResource(R.mipmap.eye_close_purple);
            btnPasswordEye.setBackgroundResource(R.mipmap.eye_open_gray);
            metPassword.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }
        metPassword.setSelection(metPassword.getText().toString().length());
    }

    //    @OnClick(R.id.login)
    public void onLogin() {
//        String s = null;
//        int length = s.length();

//        boolean willRememberPassword = cbRememberPassword.isCheck();
        boolean willRememberPassword = cbRp.isChecked();
        LogUtil.i("willRememberPassword:" + willRememberPassword);
        rememberPassword(willRememberPassword);

//        boolean isVisible = Util.isAppVisibleToUser(this);
//        LogUtil.i("isVisible:"+isVisible);
//        boolean isRunning = Util.isAppRunning(this);
//        LogUtil.i("isRunning:"+isRunning);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Util.showNotification(LoginActivity.this, "消息", "王主任发送了一条消息");
////                boolean visible = Util.isAppVisibleToUser(LoginActivity.this);
////                LogUtil.i("now the app is visible:"+visible);
////                boolean running = Util.isAppRunning(LoginActivity.this);
////                LogUtil.i("now the app is running:"+running);
//            }
//        }, 5000);

        if (!checkLogin()) {
            return;
        } else {
            doLogin();

        }


//        testKey();
//        Util.showToast(this, "登录按钮被点击了");
//        LoginActivity.HAS_LOGGED_IN = true;
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                Constants.WORKBENCH_ACTIVITY_NAME);
//        Util.gotoActivity(this, WorkbenchActivity.class);
    }

    public boolean checkLogin() {
        String username = getUsername();
        String password = getPassword();
        if (StringUtils.isBlank(username)) {
            Util.showToast(this, Constants.HINT_PLEASE_INPUT_USERNAME);
            return false;
        } else if (StringUtils.isBlank(password)) {
            Util.showToast(this, Constants.HINT_PLEASE_INPUT_PASSWORD);
            return false;
        } else {
            return true;
        }
    }

    public void doLogin() {
//        Util.showLoadingDialog(this);
//        Util.showGifProgressDialog(this);
//        Identity identity = Identity.getInstance();
//        Map<String, String> map = identity.getLoginMapFromUsernameAndPassword(getUsername(), getPassword());

        Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, getUsername(), getPassword());
        RequestBody body = Util.getBodyFromMap(map);
        loginPresenter.main(body);

//        LoginRequestBean loginRequestBean = identity.getLoginBeanFromUsernameAndPassword(getUsername(), getPassword());
//        loginPresenter.main(loginRequestBean);


//        String url = Constants.HTTP_URL + HttpInterfaceConstants.login;
//        Identity identity = Identity.getInstance();
//        Map<String, String> map = identity.getLoginMapFromUsernameAndPassword(getUsername(), getPassword());
//        HttpUtil<HttpResultBaseBean<LoginResultBean>> httpUtil = new HttpUtil<HttpResultBaseBean<LoginResultBean>>();
//        httpUtil.doHttpRequest(url, map, new MyResultCallback<HttpResultBaseBean<LoginResultBean>>() {
//            @Override
//            public void onSuccess(HttpResultBaseBean<LoginResultBean> response) {
//                Util.showToast(LoginActivity.this, Constants.HINT_LOGIN_SUCCESS);
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                        Constants.WORKBENCH_ACTIVITY_NAME);
//        Util.gotoActivity(LoginActivity.this, WorkbenchActivity.class);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                Util.showToast(LoginActivity.this, Constants.HINT_LOGIN_FAILURE);
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        shouldShowBackButton = Util.getShouldShowBackButton(intent);
        shouldFinishWhenLoginSuccess = Util.getShouldFinishWhenLoginSuccess(intent);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_login);
////        ButterKnife.bind(this);
//    }

    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addLogin(new LoginModule(this))
//                .inject(this);
    }


//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_login;
//    }

    public String getUsername() {
        return Util.trimString(metUsername.getText().toString());
    }

    public String getPassword() {
        return Util.trimString(metPassword.getText().toString());
    }

    public void test() {
//        int i = Util.getIntFromString("05");
//        LogUtil.i("getIntFromString(05):" + i);
    }

    public void initView() {
//        Util.jpushRequestPermission(this);

//        test();
        if (shouldShowBackButton) {
            flBack.setVisibility(View.VISIBLE);
        } else {
            flBack.setVisibility(View.GONE);
        }
        flBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                finish();
            }
        });

        TextWatcher userTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String trimmedString = Util.trimString(s.toString());
                if (trimmedString.length() > 0) {
                    btnUsernameClear.setVisibility(View.VISIBLE);
                } else {
                    btnUsernameClear.setVisibility(View.INVISIBLE);
                }

            }
        };
        metUsername.addTextChangedListener(userTextWatcher);

        TextWatcher passwordTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String trimmedString = Util.trimString(s.toString());
                if (trimmedString.length() > 0) {
                    btnPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    btnPasswordClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        metPassword.addTextChangedListener(passwordTextWatcher);

        setClearButtonVisibility();

        initRememberPasswordCheckBox();

        btnLogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onLogin();
            }
        });

        showVersion();

//        testKey();

    }

    @Override
    protected void loadData() {

    }

    public void showVersion() {
        String version = VersionUtil.getVersionName(this);
        version = Constants.VERSION_PREFIX + version;
        tvVersion.setText(version);
    }

    public void testKey() {
//        LoginResultBean2 bean = SimulateData.getLoginResultBean2();
//        LoginResultBean2 bean2 = SimulateData.getLoginResultBean2();
//        TestResultBean trb = new TestResultBean("QTNDRTc2MjUtRTcxRC00NTg0LTlGNjEtODM0Mzc2RkM4QzYx","YWRtaW4=",
//                "566h55CG5ZGY", "", "","MDAwMA==", "RkJBMzdGN0EtREJFOC00REJGLUJCREMtOTAwMEIyQzdCRUI4",
//                "MDAwMDAwMDAtMA==", "566h55CG5py65p6E", "MzA=", "Mw==", "MzQxMjAy", "6aKN5bee5Yy6", bean);
//        TestResultBean trb2 = new TestResultBean("QTNDRTc2MjUtRTcxRC00NTg0LTlGNjEtODM0Mzc2RkM4QzYx","YWRtaW4=",
//                "566h55CG5ZGY", "", "","MDAwMA==", "RkJBMzdGN0EtREJFOC00REJGLUJCREMtOTAwMEIyQzdCRUI4",
//                "MDAwMDAwMDAtMA==", "566h55CG5py65p6E", "MzA=", "Mw==", "MzQxMjAy", "6aKN5bee5Yy6", bean2);
//        List<TestResultBean> list = new ArrayList<>();
//        list.add(trb);
//        list.add(trb2);
//        BeanBase64Decoder.decodeBase64Bean(list);
//        LogUtil.i("test key ended");


//        Identity identity = Identity.getInstance();
//        identity.getLoginMapFromUsernameAndPassword(getUsername(), getPassword());

//        CommonParameters cp = identity.getCommonParametersObject();
//        LoginParameters lp = new LoginParameters(cp.getKey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
//                "user", "pass");
////        LoginParameters lp = new LoginParameters("key", "timestamp", "token", "sig", "v",
////                "user", "pass");
//        identity.getParametersKeysAndValuesExcludeSignature(cp);
//        identity.getParametersKeysAndValuesExcludeSignature(lp);
    }

    public void initRememberPasswordCheckBox() {
        boolean isRemembered = PreferencesUtils.getBoolean(this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, false);
        LogUtil.i("saved isRememberedPassword:" + isRemembered);
        cbRp.setChecked(isRemembered);
        cbRp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rememberPassword(isChecked);
            }
        });
//        cbRememberPassword.setChecked(isRemembered);
//        cbRememberPassword.setChecked(true);
//        cbRememberPassword.setOncheckListener(new CheckBox.OnCheckListener() {
//            @Override
//            public void onCheck(CheckBox checkBox, boolean b) {
//                rememberPassword(b);
//            }
//        });
        if (isRemembered) {
            String rememberedUsername = PreferencesUtils.getString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
            String rememberedPassword = PreferencesUtils.getString(this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);
            metUsername.setText(rememberedUsername);
            metPassword.setText(rememberedPassword);
        } else {
            String rememberedUsername = PreferencesUtils.getString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
            metUsername.setText(rememberedUsername);
//            metUsername.setText(Constants.EMPTY_STRING);
            metPassword.setText(Constants.EMPTY_STRING);
        }
    }

    public void rememberPassword(boolean b) {
        PreferencesUtils.putBoolean(LoginActivity.this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, b);
        if (b) {
            String username = Util.trimString(metUsername.getText().toString());
            String password = Util.trimString(metPassword.getText().toString());
            PreferencesUtils.putString(LoginActivity.this, Constants.REMEMBERED_USERNAME_KEY, username);
            PreferencesUtils.putString(LoginActivity.this, Constants.REMEMBERED_PASSWORD_KEY, password);
        } else {
            String username = Util.trimString(metUsername.getText().toString());
            PreferencesUtils.putString(LoginActivity.this, Constants.REMEMBERED_USERNAME_KEY, username);
//            PreferencesUtils.putString(LoginActivity.this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
            PreferencesUtils.putString(LoginActivity.this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);
        }

    }

    public void setClearButtonVisibility() {
        String username = metUsername.getText().toString();
        if (username.length() > 0) {
            btnUsernameClear.setVisibility(View.VISIBLE);
        } else {
            btnUsernameClear.setVisibility(View.INVISIBLE);
        }

        String password = metPassword.getText().toString();
        if (password.length() > 0) {
            btnPasswordClear.setVisibility(View.VISIBLE);
        } else {
            btnPasswordClear.setVisibility(View.INVISIBLE);
        }
//        btnPasswordClear.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        HAS_LOGGED_IN = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            if(isReLogin){
//                Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
//                mHomeIntent.addCategory(Intent.CATEGORY_HOME);
//                mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                LoginActivity.this.startActivity(mHomeIntent);
//            }else{
            LoginActivity.this.finish();
//            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void assignIdentityData(HttpResultBaseBean<LoginResultBean> bean) {
        if (bean != null) {
            LoginResultBean lrb = bean.getData();
            Identity.initWithData(lrb);
        }
    }

    public void assignIdentityData2(HttpResultBaseBean<LoginResultBean2> bean) {
        if (bean != null) {
            LoginResultBean2 lrb = bean.getData();
//            Util.decodeBase64Bean(lrb);
//            decodeBase64FieldData(lrb);
            Identity.info = lrb;

        }
    }

    public void decodeBase64FieldData(LoginResultBean2 lrb) {
        if (lrb != null) {
            lrb.setGuserid(Util.getDecodedBase64String(lrb.getGuserid()));
            lrb.setSloginname(Util.getDecodedBase64String(lrb.getSloginname()));
            lrb.setSusername(Util.getDecodedBase64String(lrb.getSusername()));
            lrb.setSphone(Util.getDecodedBase64String(lrb.getSphone()));
            lrb.setSsexname(Util.getDecodedBase64String(lrb.getSsexname()));
            lrb.setShiploginname(Util.getDecodedBase64String(lrb.getShiploginname()));
            lrb.setSorganizationkey(Util.getDecodedBase64String(lrb.getSorganizationkey()));
            lrb.setSorgInstitutioncode(Util.getDecodedBase64String(lrb.getSorgInstitutioncode()));
            lrb.setSorganizationname(Util.getDecodedBase64String(lrb.getSorganizationname()));
            lrb.setSorganizationtypecode(Util.getDecodedBase64String(lrb.getSorganizationtypecode()));
            lrb.setSorganizationlevelcode(Util.getDecodedBase64String(lrb.getSorganizationlevelcode()));
            lrb.setSmanagementdivisioncode(Util.getDecodedBase64String(lrb.getSmanagementdivisioncode()));
            lrb.setSmanagementdivisionname(Util.getDecodedBase64String(lrb.getSmanagementdivisionname()));
        }
    }

//    public void registerAndLoginJMessage(final String userId, final String password, final IMProcessSuccessListener imProcessSuccessListener) {
//        Util.showGifProgressDialog(this);
//        JMessageClient.register(userId, password, new BasicCallback() {
//
//            @Override
//            public void gotResult(final int status, final String desc) {
//                if (status == Constants.JMESSAGE_REGISTER_SUCCESS_CODE ||
//                        status == Constants.JMESSAGE_REGISTER_USER_EXIST_CODE) {
//                    Util.showToast(LoginActivity.this, Constants.HINT_REGISTER_IM_SUCCESS);
//                    JMessageClient.login(userId, password, new BasicCallback() {
//                        @Override
//                        public void gotResult(final int status, String desc) {
//                            if (status == Constants.JMESSAGE_LOGIN_SUCCESS_CODE) {
//                                Util.showToast(LoginActivity.this, Constants.HINT_LOGIN_IM_SUCCESS);
//                                String username = JMessageClient.getMyInfo().getUserName();
//                                String appKey = JMessageClient.getMyInfo().getAppKey();
//                                JMessageClient.getUserInfo(userId, new GetUserInfoCallback() {
//                                    @Override
//                                    public void gotResult(int i, String s, UserInfo userInfo) {
//                                        if (Constants.JMESSAGE_GET_USER_INFO_SUCCESS_CODE == i) {
//                                            Identity.imUserInfo = userInfo;
//                                            Util.showToast(LoginActivity.this, Constants.HINT_IM_GET_USER_INFO_SUCCESS);
//                                            if (imProcessSuccessListener != null) {
//                                                imProcessSuccessListener.onIMProcessSuccess(userInfo);
//                                            }
//                                        } else {
//                                            Util.showToast(LoginActivity.this, Constants.HINT_IM_GET_USER_INFO_FAILURE);
//                                        }
//                                        Util.hideGifProgressDialog(LoginActivity.this);
//                                    }
//                                });
////                                UserEntry user = UserEntry.getUser(username, appKey);
////                                if (null == user) {
////                                    user = new UserEntry(username, appKey);
////                                    user.save();
////                                }
////                                mContext.onRegistSuccess();
//                            } else {
//                                Util.showToast(LoginActivity.this, Constants.HINT_LOGIN_IM_FAILURE);
//                                Util.hideGifProgressDialog(LoginActivity.this);
////                                mLoginDialog.dismiss();
////                                HandleResponseCode.onHandle(mContext, status, false);
//                            }
//                        }
//                    });
//                } else {
//                    Util.hideGifProgressDialog(LoginActivity.this);
//                    Util.showToast(LoginActivity.this, Constants.HINT_REGISTER_IM_FAILURE);
//                }
//            }
//        });
//    }

//    private void setAlias(String alias) {
////        if (Util.getAliasSetSuccess(getApplication())){
////            return;
////        }
//        alias = Util.trimString(alias);
//        if (TextUtils.isEmpty(alias)) {
//            Util.showToast(LoginActivity.this, Constants.HINT_ALIAS_EMPTY);
//            return;
//        }
//        if (!ExampleUtil.isValidTagAndAlias(alias)) {
//            Util.showToast(LoginActivity.this, Constants.HINT_ALIAS_INVALID);
//            return;
//        }
//
//        // 调用 Handler 来异步设置别名
//        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
//    }

//    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
//        @Override
//        public void gotResult(int code, String alias, Set<String> tags) {
//            String logs;
//            switch (code) {
//                case Constants.JPUSH_SET_ALIAS_SUCCESS_CODE:
//                    logs = "Set tag and alias success";
//                    LogUtil.i(TAG, logs);
//                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
//                    Util.setAliasStatus(LoginActivity.this, true);
//                    Util.showToast(LoginActivity.this, Constants.HINT_JPUSH_SET_ALIAS_SUCCESS);
//                    break;
//                case Constants.JPUSH_SET_ALIAS_FAILURE_CODE:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    LogUtil.i(TAG, logs);
//                    // 延迟 60 秒来调用 Handler 设置别名
//                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
//                    break;
//                default:
//                    logs = "Failed with errorCode = " + code;
//                    LogUtil.i(TAG, logs);
//            }
//        }
//    };
//    private static final int MSG_SET_ALIAS = Constants.HANDLER_SET_ALIAS_MSG_ID;
//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case MSG_SET_ALIAS:
//                    LogUtil.i(TAG, "Set alias in handler.");
//                    // 调用 JPush 接口来设置别名。
//                    JPushInterface.setAliasAndTags(LoginActivity.this,
//                            (String) msg.obj,
//                            null,
//                            mAliasCallback);
//                    break;
//                default:
//                    LogUtil.i(TAG, "Unhandled msg - " + msg.what);
//            }
//        }
//    };

    public void gotoWorkbenchActivity() {
//        if (shouldFinishWhenLoginSuccess) {
//            finish();
//        } else {
//
//        }

//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                Constants.WORKBENCH_ACTIVITY_NAME);
        Util.gotoActivity(this, WorkbenchActivity.class);
    }

    @Override
    public void onLoginSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
//        Util.sleep(3000);
//        Util.hideLoadingDialog();
        Util.hideGifProgressDialog(this);
        Util.showToast(this, Constants.HINT_LOGIN_SUCCESS);
        HAS_LOGGED_IN = true;

        assignIdentityData2(bean);
//        assignIdentityData(bean);
//        String alias = Util.trimString(Identity.uid);
//        setAlias(alias);
//
//        String uid = Util.trimString(bean.getData().getUid());
//        String passwod = getPassword();
//        registerAndLoginJMessage(uid, passwod, new IMProcessSuccessListener() {
//            @Override
//            public void onIMProcessSuccess(UserInfo userInfo) {
//                gotoWorkbenchActivity();
//            }
//        });

        gotoWorkbenchActivity();


//        if (shouldFinishWhenLoginSuccess){
//            finish();
//        } else {
//
//        }
//
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                Constants.WORKBENCH_ACTIVITY_NAME);
//            Util.gotoActivity(this, WorkbenchActivity.class);
    }

    @Override
    public void onLoginFailure(String message) {
        HAS_LOGGED_IN = false;
////        Util.hideLoadingDialog();
//        Util.hideGifProgressDialog(this);
        Util.showToast(this, Constants.HINT_LOGIN_FAILURE);
    }
}
