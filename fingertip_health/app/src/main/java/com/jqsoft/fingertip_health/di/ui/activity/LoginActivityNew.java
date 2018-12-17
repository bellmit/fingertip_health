package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.caption.netmonitorlibrary.netStateLib.NetChangeObserver;
import com.caption.netmonitorlibrary.netStateLib.NetStateReceiver;
import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.flyco.roundview.RoundTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.LoginResultForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_ph_dict_item;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginBean;
import com.jqsoft.fingertip_health.bean.response.LoginResultBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.LoginContractForFingertip;
import com.jqsoft.fingertip_health.di.module.LoginModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.LoginPresenterForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils2.VersionUtil;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import net.qiujuer.genius.ui.widget.CheckBox;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.jqsoft.fingertip_health.base.Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY;

//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.android.api.TagAliasCallback;

//import com.jqsoft.fingertip_health.listener.IMProcessSuccessListener;
//import com.jqsoft.fingertip_health.util.ExampleUtil;
//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.android.api.TagAliasCallback;
//import cn.jpush.im.android.api.JMessageClient;
//import cn.jpush.im.android.api.callback.GetUserInfoCallback;
//import cn.jpush.im.android.api.model.UserInfo;
//import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017-07-12.
 */
//最新效果图的登录界面
public class LoginActivityNew extends AbstractActivity implements LoginContractForFingertip.View {
//    @BindView(R.id.tv_app_name)
//    TextView tvAppName;
    @BindView(R.id.acet_username)
    AppCompatEditText acetUsername;
    @BindView(R.id.bt_username_clear)
    Button btUsernameClear;
    @BindView(R.id.acet_password)
    AppCompatEditText acetPassword;
    @BindView(R.id.bt_password_clear)
    Button btPasswordClear;
    @BindView(R.id.bt_password_eye)
    Button btPasswordEye;
    @BindView(R.id.cb_rp)
    CheckBox cbRp;
    @BindView(R.id.bt_login)
    RoundTextView btLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Inject
    LoginPresenterForFingertip loginPresenter;
//    SRCLoginPresenter loginPresenter;

    public static final String TAG = "LoginActivityNew";

    public static boolean HAS_LOGGED_IN = false;
    public static boolean isJPushRegisterSuccess = false;

    public boolean shouldShowBackButton = false;
    public boolean shouldFinishWhenLoginSuccess = false;

    @OnClick(R.id.bt_username_clear)
    public void onClearUsername() {
        acetUsername.setText("");
//        acetPassword.setText("");
    }

    @OnClick(R.id.bt_password_clear)
    public void onClearPassword() {
        acetPassword.setText("");
    }

    @OnClick(R.id.bt_password_eye)
    public void onChangePasswordVisibility() {
        int inputType = acetPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            btPasswordEye.setBackgroundResource(R.mipmap.eye_open_blue);
            acetPassword.setInputType((InputType.TYPE_CLASS_TEXT));
        } else {
//            btPasswordEye.setBackgroundResource(R.mipmap.eye_close_purple);
            btPasswordEye.setBackgroundResource(R.mipmap.eye_open_gray);
            acetPassword.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }
        acetPassword.setSelection(acetPassword.getText().toString().length());
    }

    //    @OnClick(R.id.login)
    public void onLogin(boolean isManual) {
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
//                Util.showNotification(LoginActivityNew.this, "消息", "王主任发送了一条消息");
////                boolean visible = Util.isAppVisibleToUser(LoginActivityNew.this);
////                LogUtil.i("now the app is visible:"+visible);
////                boolean running = Util.isAppRunning(LoginActivityNew.this);
////                LogUtil.i("now the app is running:"+running);
//            }
//        }, 5000);


        if (!checkLogin(isManual)) {
            return;
        } else {
            doLogin();
        }


//        testKey();
//        Util.showToast(this, "登录按钮被点击了");
//        LoginActivity.HAS_LOGGED_IN = true;
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                Constants.WORKBENCH_ACTIVITY_NAME);
    }

    public boolean checkLogin(boolean isManual) {
        String username = getUsername();
        String password = getPassword();
        if (StringUtils.isBlank(username)) {
            if (isManual) {
                Util.showToast(this, Constants.HINT_PLEASE_INPUT_USERNAME);
            }
            return false;
        } else if (StringUtils.isBlank(password)) {
            if (isManual) {
                Util.showToast(this, Constants.HINT_PLEASE_INPUT_PASSWORD);
            }
            return false;
        } else {
            return true;
        }
    }

    public void doLogin() {

       /* Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, getUsername(), getPassword());
        loginPresenter.main(map);*/
//        Util.showLoadingDialog(this);
//        Util.showGifProgressDialog(this);
//        Identity identity = Identity.getInstance();
//        Map<String, String> map = identity.getLoginMapFromUsernameAndPassword(getUsername(), getPassword());
        // List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);

       /* Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, getUsername(), getPassword());
        loginPresenter.main(map);*/

//        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);
//        int LoginDataDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);


        Util.showGifProgressDialog(getApplicationContext());
        Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPasswordForFingertip(this, getUsername(), getPassword());
        loginPresenter.login(map);
//        gotoWorkbenchActivity();


//        LoginRequestBean loginRequestBean = identity.getLoginBeanFromUsernameAndPassword(getUsername(), getPassword());
//        loginPresenter.main(loginRequestBean);


//        String url = Constants.HTTP_URL + HttpInterfaceConstants.login;
//        Identity identity = Identity.getInstance();
//        Map<String, String> map = identity.getLoginMapFromUsernameAndPassword(getUsername(), getPassword());
//        HttpUtil<HttpResultBaseBean<LoginResultBean>> httpUtil = new HttpUtil<HttpResultBaseBean<LoginResultBean>>();
//        httpUtil.doHttpRequest(url, map, new MyResultCallback<HttpResultBaseBean<LoginResultBean>>() {
//            @Override
//            public void onSuccess(HttpResultBaseBean<LoginResultBean> response) {
//                Util.showToast(LoginActivityNew.this, Constants.HINT_LOGIN_SUCCESS);
//                ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                        Constants.WORKBENCH_ACTIVITY_NAME);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                Util.showToast(LoginActivityNew.this, Constants.HINT_LOGIN_FAILURE);
//            }
//        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_new;
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
        DaggerApplication.get(this)
                .getAppComponent()
                .addLoginForFingertip(new LoginModuleForFingertip(this))
//                .addLogin(new SRCLoginModule(this))
                .inject(this);
    }


//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_login;
//    }

    public String getUsername() {
        return Util.trimString(acetUsername.getText().toString());
    }

    public String getPassword() {
        return Util.trimString(acetPassword.getText().toString());
    }

    public void test() {
//        int i = Util.getIntFromString("05");
//        LogUtil.i("getIntFromString(05):" + i);
    }

    private int getDeliveredWorkbenchPageIndex() {
        int targetIndex = getDeliveredIntByKey(WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY);
        LogUtil.i("LoginActivityNew getDeliveredWorkbenchPageIndex before process targetIndex:" + targetIndex);
        if (targetIndex < Constants.WORKBENCH_INDEX || targetIndex > Constants.WORKBENCH_MINE) {
            targetIndex = Constants.WORKBENCH_INDEX;
        }
        LogUtil.i("LoginActivityNew getDeliveredWorkbenchPageIndex after process targetIndex:" + targetIndex);
        return targetIndex;
    }

//    private void startAndroidJob(){
//        JobManager jobManager = JobManager.create(this.getApplicationContext());
//        jobManager.addJobCreator(new AndroidJobCreator());
////        jobManager.schedule();
////        JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true); // Don't use this in production
//
//        AndroidJob.scheduleJob();
//    }

    public void initView() {
        initBugly();
//        startAndroidJob();


//        tvAppName.setText(Version.LOGIN_APP_NAME);

//        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);
//        int LoginDataDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);
//
//        if (LoginAreaList != 0 &&  LoginDataDictionaryList!=0 &&  LoginSalvationList!=0 && LoginOrganizationList!=0) {
//
//        } else {
////            Toast.makeText(getApplicationContext(), "正在初始化数据,请耐心等候!", Toast.LENGTH_LONG).show();
//            initAlldata();
//
//        }


//        Util.jpushRequestPermission(this);

//        SRCLoginResultBean srcLrb = IdentityManager.getSrcLoginResultBean(this);


//        test();
//        if (shouldShowBackButton) {
//            flBack.setVisibility(View.VISIBLE);
//        } else {
//            flBack.setVisibility(View.GONE);
//        }
//        flBack.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                finish();
//            }
//        });

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
                    btUsernameClear.setVisibility(View.VISIBLE);
                } else {
                    btUsernameClear.setVisibility(View.INVISIBLE);
                }

            }
        };
        acetUsername.addTextChangedListener(userTextWatcher);

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
                    btPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    btPasswordClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        acetPassword.addTextChangedListener(passwordTextWatcher);
        acetPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    onLogin(true);
                }
                return false;
            }
        });

        setClearButtonVisibility();

        initRememberPasswordCheckBox();

//        forceUsernameAndPassword();

        btLogin.setOnClickListener(new NoDoubleClickListener() {
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
//                startActivityInAnotherApp();

                onLogin(true);
            }
        });

        RxView.clicks(tvForgetPassword)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.CARD_NO_KEY, getUsername());
                        Util.gotoActivityWithBundle(LoginActivityNew.this, RetrievePasswordActivity.class, bundle);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        RxView.clicks(tvRegister)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object value) {
//                        //   Util.gotoActivity(LoginActivityNew.this, RegisterActivity.class);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        showVersion();

//        testKey();

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.gotoActivity(LoginActivityNew.this, SettingServerActivity.class);
            }
        });
        onLogin(false);


    }

//    int REQUEST_CODE=100;
//    private void startActivityInAnotherApp(){
//        try {
//            PackageManager pm = getPackageManager();
////            Intent intent = pm.getLaunchIntentForPackage("com.example.gareamedicalexaple");
////            startActivity(intent);
//
//            Intent intent2 = new Intent();
//            intent2.setClassName("com.example.gareamedicalexaple", "com.example.gareamedicalexample.EcgExample");
//            startActivityForResult(intent2, REQUEST_CODE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void finishIntroductionActivity() {
        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_INTRODUCTION_ACTIVITY, true);
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
        String s = "<p>1.家庭医生</p><p >&nbsp;2.</p>";
        String replacedS = Util.getReplacedXmlTagString(s);
        LogUtil.i("replacedS:" + replacedS);

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

    private void forceUsernameAndPassword() {
//        acetUsername.setText("AIA002");
//        acetUsername.setText("AFX001");
//        acetPassword.setText("AEP002");
//        acetUsername.setText("AFR005");
        acetUsername.setText("AJG001");
        acetPassword.setText("123456");

//        acetUsername.setText("ABL030");
//        acetPassword.setText("123456");
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
            acetUsername.setText(rememberedUsername);
            acetPassword.setText(rememberedPassword);
        } else {
            String rememberedUsername = PreferencesUtils.getString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
            acetUsername.setText(rememberedUsername);
//            acetUsername.setText(Constants.EMPTY_STRING);
            acetPassword.setText(Constants.EMPTY_STRING);
        }
    }

    public void rememberPassword(boolean b) {
        PreferencesUtils.putBoolean(LoginActivityNew.this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, b);
        if (b) {
            String username = Util.trimString(acetUsername.getText().toString());
            String password = Util.trimString(acetPassword.getText().toString());
            PreferencesUtils.putString(LoginActivityNew.this, Constants.REMEMBERED_USERNAME_KEY, username);
            PreferencesUtils.putString(LoginActivityNew.this, Constants.REMEMBERED_PASSWORD_KEY, password);
        } else {
            String username = Util.trimString(acetUsername.getText().toString());
            PreferencesUtils.putString(LoginActivityNew.this, Constants.REMEMBERED_USERNAME_KEY, username);
//            PreferencesUtils.putString(LoginActivityNew.this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
            PreferencesUtils.putString(LoginActivityNew.this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);
        }

    }

    public void setClearButtonVisibility() {
        String username = acetUsername.getText().toString();
        if (username.length() > 0) {
            btUsernameClear.setVisibility(View.VISIBLE);
        } else {
            btUsernameClear.setVisibility(View.INVISIBLE);
        }

        String password = acetPassword.getText().toString();
        if (password.length() > 0) {
            btPasswordClear.setVisibility(View.VISIBLE);
        } else {
            btPasswordClear.setVisibility(View.INVISIBLE);
        }
//        btPasswordClear.setVisibility(View.VISIBLE);

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
//                LoginActivityNew.this.startActivity(mHomeIntent);
//            }else{
            LoginActivityNew.this.finish();
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

//    public void assignIdentityData2(HttpResultBaseBean<LoginResultBean2> bean) {
//        if (bean != null) {
//            LoginResultBean2 lrb = bean.getData();
////            Util.decodeBase64Bean(lrb);
////            decodeBase64FieldData(lrb);
//            Identity.info = lrb;
//
//            Version.FILE_URL_BASE= Version.FILE_URL_BASE+Identity.info.getSmanagementdivisioncode()
//                    +"/";
//
//        }
//    }

    public void assignLoginResultForFingertip(LoginResultForFingertip lrft) {
        if (lrft != null) {
            Identity.loginResultForFingertip = lrft;

            IdentityManager.setObjectToShare(this, Identity.loginResultForFingertip, Constants.LOGIN_RESULT_FOR_FINGERTIP_BEAN_KEY);

        }
    }

    public void assignSRCIdentityData(HttpResultBaseBean<SRCLoginBean> bean) {
        if (bean != null) {
            SRCLoginBean lrb = bean.getData();

            Identity.srcInfo = lrb;
            Identity.srcInfo.setLoginSuccessUsername(getUsername());
            Identity.srcInfo.setLoginSuccessPassword(getPassword());
            Identity.srcInfo.setUserName(lrb.getUserName());
            Identity.srcInfo.setRealName(lrb.getRealName());
            Identity.srcInfo.setRoleId(lrb.getRoleId());
            Identity.srcInfo.setAreaId(lrb.getAreaId());
            Identity.srcInfo.setAreaLevel(lrb.getAreaLevel());
            Identity.srcInfo.setJgTag(lrb.getJgTag());
            Identity.srcInfo.setUnitCode(lrb.getUnitCode());

            IdentityManager.setObjectToShare(this, Identity.srcInfo, Constants.SRC_LOGIN_RESULT_BEAN_KEY);

           /* String srcAreaCode = Identity.srcInfo.getAreaCode();
            if(TextUtils.isEmpty(srcAreaCode) ){
                Version.FILE_URL_BASE= Version.FILE_URL_BASE+"null"
                        +"/";
            }else{
                Version.FILE_URL_BASE= Version.FILE_URL_BASE+Util.trimString(Identity.srcInfo.getAreaCode())
                        +"/";
            }*/

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
//                    Util.showToast(LoginActivityNew.this, Constants.HINT_REGISTER_IM_SUCCESS);
//                    JMessageClient.login(userId, password, new BasicCallback() {
//                        @Override
//                        public void gotResult(final int status, String desc) {
//                            if (status == Constants.JMESSAGE_LOGIN_SUCCESS_CODE) {
//                                Util.showToast(LoginActivityNew.this, Constants.HINT_LOGIN_IM_SUCCESS);
//                                String username = JMessageClient.getMyInfo().getUserName();
//                                String appKey = JMessageClient.getMyInfo().getAppKey();
//                                JMessageClient.getUserInfo(userId, new GetUserInfoCallback() {
//                                    @Override
//                                    public void gotResult(int i, String s, UserInfo userInfo) {
//                                        if (Constants.JMESSAGE_GET_USER_INFO_SUCCESS_CODE == i) {
//                                            Identity.imUserInfo = userInfo;
//                                            Util.showToast(LoginActivityNew.this, Constants.HINT_IM_GET_USER_INFO_SUCCESS);
//                                            if (imProcessSuccessListener != null) {
//                                                imProcessSuccessListener.onIMProcessSuccess(userInfo);
//                                            }
//                                        } else {
//                                            Util.showToast(LoginActivityNew.this, Constants.HINT_IM_GET_USER_INFO_FAILURE);
//                                        }
//                                        Util.hideGifProgressDialog(LoginActivityNew.this);
//                                    }
//                                });
////                                UserEntry user = UserEntry.getUser(username, appKey);
////                                if (null == user) {
////                                    user = new UserEntry(username, appKey);
////                                    user.save();
////                                }
////                                mContext.onRegistSuccess();
//                            } else {
//                                Util.showToast(LoginActivityNew.this, Constants.HINT_LOGIN_IM_FAILURE);
//                                Util.hideGifProgressDialog(LoginActivityNew.this);
////                                mLoginDialog.dismiss();
////                                HandleResponseCode.onHandle(mContext, status, false);
//                            }
//                        }
//                    });
//                } else {
//                    Util.hideGifProgressDialog(LoginActivityNew.this);
//                    Util.showToast(LoginActivityNew.this, Constants.HINT_REGISTER_IM_FAILURE);
//                }
//            }
//        });
//    }

    private void setAlias(String alias) {
        if (isJPushRegisterSuccess) {
            return;
        }

//        if (Util.getAliasSetSuccess(getApplication())){
//            return;
//        }
        alias = Util.trimString(alias);
        if (TextUtils.isEmpty(alias)) {
            Util.showToast(LoginActivityNew.this, Constants.HINT_ALIAS_EMPTY);
            return;
        }
//        if (!ExampleUtil.isValidTagAndAlias(alias)) {
//            Util.showToast(LoginActivityNew.this, Constants.HINT_ALIAS_INVALID);
//            return;
//        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case Constants.JPUSH_SET_ALIAS_SUCCESS_CODE:
                    logs = "Set tag and alias success";
                    LogUtil.i(TAG, logs);
                    isJPushRegisterSuccess = true;
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    Util.setAliasStatus(LoginActivityNew.this, true);
                    Util.showToast(LoginActivityNew.this, Constants.HINT_JPUSH_SET_ALIAS_SUCCESS);
                    break;
                case Constants.JPUSH_SET_ALIAS_FAILURE_CODE:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    LogUtil.i(TAG, logs);
                    isJPushRegisterSuccess = false;
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 10);
//                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    LogUtil.i(TAG, logs);
                    isJPushRegisterSuccess = false;
                    break;
            }
        }
    };
    private static final int MSG_SET_ALIAS = Constants.HANDLER_SET_ALIAS_MSG_ID;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Set<String> tags = new HashSet<String>();
                    //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
                    List<String> jgtags = Identity.srcInfo.getJgTag();
                    if (jgtags == null) {
                        tags.add(IdentityManager.getAreaId(getApplicationContext()));
                    } else {
                        if (jgtags.size() == 0) {
                            tags.add(IdentityManager.getAreaId(getApplicationContext()));
                        } else {
                            for (int i = 0; i < jgtags.size(); i++) {
                                tags.add(jgtags.get(i));
                            }
                        }
                    }

                    JPushInterface.setAliasAndTags(LoginActivityNew.this,
                            (String) msg.obj,
                            tags,
                            mAliasCallback);
                    break;
                default:
                    LogUtil.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    private void gotoLoginActivity() {
        SQLiteDatabase db = Connector.getDatabase();
        int LoginDictionaryDataList = DataSupport.count(GDWS_ICD.class);
        Connector.getDatabase();
        int db1 = Connector.getDatabase().getVersion();
        //  int LoginDictionaryDataList1 = DataSupport.count(DictionaryAreaData.class);
        if (LoginDictionaryDataList == 0) {
            DataSupport.deleteAll(GDWS_ICD.class);
            copyDbFile(LoginActivityNew.this, "gdws.db");

            DataSupport.count(gdws_sys_area.class);
            DataSupport.count(gdws_sys_area.class);

            DataSupport.count(gdws_ph_dict_item.class);
            DataSupport.count(gdws_ph_dict_item.class);
//            List<gdws_sys_area>  list = DataSupport.findAll(gdws_sys_area.class);
//            List<gdws_sys_area>  list2 = DataSupport.findAll(gdws_sys_area.class);
        } else {
            DataSupport.count(gdws_sys_area.class);
            DataSupport.count(gdws_sys_area.class);

            DataSupport.count(gdws_ph_dict_item.class);
            DataSupport.count(gdws_ph_dict_item.class);
//            List<gdws_sys_area>  list = DataSupport.findAll(gdws_sys_area.class);
//            List<gdws_sys_area>  list2 = DataSupport.findAll(gdws_sys_area.class);
            //Util.gotoActivity(this, BphsLoginActivity.class);
        }
//        Util.gotoActivity(this, LoginActivityNew.class);
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);
//        Util.gotoActivity(this, LoginActivityNew.class);
//        finish();
    }

    public void gotoWorkbenchActivity() {
//        if (shouldFinishWhenLoginSuccess) {
//            finish();
//        } else {
//
//        }

//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME,
//                Constants.WORKBENCH_ACTIVITY_NAME);
        gotoLoginActivity();

        Bundle bundle = new Bundle();
        int targetIndex = getDeliveredWorkbenchPageIndex();
        boolean isPushNotificationExist = getDeliveredBooleanByKey(Constants.WORKBENCH_PUSH_NOTIFICATION_EXIST_KEY);
        String pi = getDeliveredStringByKey(Constants.WORKBENCH_PUSH_NOTIFICATION_INTENT_KEY);
        String userUuid = getDeliveredStringByKey(Constants.WORKBENCH_PUSH_NOTIFICATION_USER_UUID_KEY);
        LogUtil.i("LoginActivityNew gotoWorkbenchActivity targetIndex:" + targetIndex);
        bundle.putInt(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, targetIndex);
        bundle.putBoolean(Constants.WORKBENCH_PUSH_NOTIFICATION_EXIST_KEY, isPushNotificationExist);
        bundle.putString(Constants.WORKBENCH_PUSH_NOTIFICATION_INTENT_KEY, pi);
        bundle.putString(Constants.WORKBENCH_PUSH_NOTIFICATION_USER_UUID_KEY, userUuid);
        Util.gotoActivityWithBundle(this, WorkbenchActivity.class, bundle);
    }


    /**
     * 将assets文件夹下/db的本地库拷贝到/data/data/下
     *
     * @param context
     * @param tab_name
     */
    public static void copyDbFile(Context context, String tab_name) {
        InputStream in = null;
        FileOutputStream out = null;
        /**data/data/路径*/
        String path = "/data/data/" + context.getPackageName() + "/databases";
        File file = new File(path + "/" + tab_name);

        try {

            //创建文件夹
            File file_ = new File(path);
            if (!file_.exists())
                file_.mkdirs();

            if (file.exists())//删除已经存在的
                file.deleteOnExit();
            //创建新的文件
            if (!file.exists())
                file.createNewFile();

            in = context.getAssets().open(tab_name); // 从assets目录下复制
            out = new FileOutputStream(file);
            int length = -1;
            byte[] buf = new byte[1024];
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

//    @Override
//    public void onLoginSuccess(HttpResultBaseBean<SRCLoginBean> bean) {
//
//
//        //    Util.showToast(this, Constants.HINT_LOGIN_SUCCESS);
//        HAS_LOGGED_IN = true;
//
//
//        assignSRCIdentityData(bean);
//        String alias = Util.trimString(IdentityManager.getLoginSuccessUsername(this));
//        //禁用极光推送
//        setAlias(alias);
//        gotoWorkbenchActivity();
//
//
//        if (shouldFinishWhenLoginSuccess) {
//            finish();
//        } else {
//
//        }
//        finishIntroductionActivity();
//
//    }

    @Override
    public void onLoginSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        HAS_LOGGED_IN = true;

//        HttpResultBaseBeanForFingertip<LoginResultForFingertip> ultimateResult = new HttpResultBaseBeanForFingertip<>();
//        ultimateResult.setFlag(bean.getFlag());
//        ultimateResult.setErrorMsg(bean.getErrorMsg());
        String resultString = bean.getResult();
        LoginResultForFingertip result = JSON.parseObject(resultString, LoginResultForFingertip.class);

        assignLoginResultForFingertip(result);
        DaggerApplication.getInstance().setAreaId(result.getAreaId());
        DaggerApplication.getInstance().setUserlevel(result.getUserLevel());
        DaggerApplication.getInstance().setVersionNum(result.getVersionNum());

        DaggerApplication.getInstance().setVersionNum(result.getVersionNum());
        DaggerApplication.getInstance().setVersionNum(result.getVersionNum());

//        DaggerApplication.getInstance().setRealName(result.getDoctor().get(0).getRealName());
//        DaggerApplication.getInstance().setOrgName(result.getOrgName());
//        DaggerApplication.getInstance().setUsername(result.getDoctor().get(0).getUsername());

        DaggerApplication.getInstance().setRealName(result.getRealName());
        DaggerApplication.getInstance().setOrgName(result.getOrgName());
        DaggerApplication.getInstance().setUsername(result.getUsername());

//        assignSRCIdentityData(bean);
//        String alias = Util.trimString(IdentityManager.getLoginSuccessUsername(this));
//        //禁用极光推送
//        setAlias(alias);
        gotoWorkbenchActivity();


        if (shouldFinishWhenLoginSuccess) {
            finish();
        } else {

        }
        finishIntroductionActivity();

    }

    @Override
    public void onLoginFailure(String message) {
        Util.hideGifProgressDialog(this);
        HAS_LOGGED_IN = false;
////        Util.hideLoadingDialog();
//        Util.hideGifProgressDialog(this);
////        Util.showToast(this, Constants.HINT_LOGIN_FAILURE);
        Util.showToast(this, message);

//        LogUtil.i("登录失败");

        finishIntroductionActivity();

    }

//    @Override
//    public void onLoginAreaSuccess(HttpResultBaseBean<List<gdws_sys_area>> bean) {
//        //  Util.hideGifProgressDialog(this);
//        List<gdws_sys_area> lrb = bean.getData();
//        //  List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);
//        int LoginAreaList = DataSupport.count(gdws_sys_area.class);
//
//        if (lrb.size() == 0) {
//            //  String s1=lrb.get(0).getAreaName();
//        } else {
//            if (LoginAreaList < 5000) {
//                DataSupport.saveAll(lrb);
//               /* for(int i=0;i<lrb.size();i++){
//                    lrb.get(i).save();
//                }*/
//            } else {
//
//            }
//
//            //    Util.showToast(this, "区域初始化完成");
//
//
//            int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//            //   List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
//            if (LoginSalvationList == 0) {
//                Map<String, String> map3 = ParametersFactory.getLoginMapSalvation(this);
//                loginPresenter.mainSalvation(map3);
//            } else {
//
//            }
//
//
//        }
//
//    }
//
//    @Override
//    public void onLoginAreaFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }
//
//    @Override
//    public void onLoginDataDictionatySuccess(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean) {
//
//        List<SRCLoginDataDictionaryBean> lrb = bean.getData();
//        int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//
//
//        if (lrb.size() == 0) {
//
//        } else {
//            if (LoginDictionaryList == 0) {
//                DataSupport.saveAll(lrb);
//
//            } else {
//
//            }
//
//              Util.showToast(this, "数据字典初始化完成");
////            int LoginOrganizationList = DataSupport.count(OrganizationBean.class);
////            if (LoginOrganizationList == 0) {
////                Map<String, String> map4 = ParametersFactory.getLoginOrganization(this);
////                loginPresenter.getOrganizationData(map4);
////            }
//
//
//        }
//    }
//
//    @Override
//    public void onLoginDataDictionatyFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }
//
//    @Override
//    public void onLoginSalvationSuccess(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean) {
//
//        List<SRCLoginSalvationBean> lrb = bean.getData();
//
//        // List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
//        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//
//        if (lrb.size() == 0) {
//            //  String s1=lrb.get(0).getAreaName();
//        } else {
//            if (LoginSalvationList == 0) {
//                DataSupport.saveAll(lrb);
//
//            } else {
//
//
//            }
//
//            Util.showToast(this, "救助项初始化完成");
////            int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
////
////            if (LoginDictionaryList == 0) {
////                Map<String, String> map2 = ParametersFactory.getLoginMapDictionary(this);
////                loginPresenter.mainDictionary(map2);
////            } else {
////
////            }
//
//        }
//    }
//
//    @Override
//    public void onLoginSalvationFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initBugly() {
//        Context context = getApplication();
        Context context = this;
        String packageName = context.getPackageName();
        String processName = Util.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));

//        设置是否显示消息通知
//        如果你不想在通知栏显示下载进度，你可以将这个接口设置为false，默认值为true。
        Beta.enableNotification = true;

//        设置Wifi下自动下载
//        如果你想在Wifi网络下自动下载，可以将这个接口设置为true，默认值为false。
        Beta.autoDownloadOnWifi = false;

        /* 设置更新状态回调接口 */
        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeSuccess(boolean isManual) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Constants.EVENT_TYPE_BUGLY_UPGRADE_SUCCESS);
            }

            @Override
            public void onUpgradeFailed(boolean isManual) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Constants.EVENT_TYPE_BUGLY_UPGRADE_FAILURE);
            }

            @Override
            public void onUpgrading(boolean isManual) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Constants.EVENT_TYPE_BUGLY_UPGRADE_UPGRADING);
            }

            @Override
            public void onDownloadCompleted(boolean b) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Constants.EVENT_TYPE_BUGLY_UPGRADE_DOWNLOAD_COMPLETED);
            }

            @Override
            public void onUpgradeNoVersion(boolean isManual) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE, Constants.EVENT_TYPE_BUGLY_UPGRADE_NO_VERSION);
            }
        };


        initParameters();


//        CrashReport.initCrashreport(getapplication(), version.bugly_app_id, version.bugly_debug_mode, strategy);

//        Bugly.init(this, Version.BUGLY_APP_ID, Version.BUGLY_DEBUG_MODE);
        Bugly.init(this, Version.BUGLY_APP_ID, Version.BUGLY_DEBUG_MODE, strategy);
    }

    public void initParameters() {
        String baseHttpUrl = Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
        String sBaseHttpUrl1 = Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");

//        List<SettingServerBean> Serverlist = DataSupport.findAll(SettingServerBean.class);
//        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        if (Serverlist.size() == 0) {
//
//            //  String str = "<p>sad2f</p>";
////            String regex = "http://(.*)/fdss-api/";
//            String regex = "http://(.*?)/";
////            String regex = "http://(.*)/sri/";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(baseHttpUrl);
//            String sBaseHttpUrl ="";
//
//            while (matcher.find()) {
//                // System.out.println(matcher.group(1));
//                sBaseHttpUrl=  matcher.group(1);
//            }
//
//
//
//            SettingServerBean bean = new SettingServerBean(sBaseHttpUrl, "默认服务器地址", name, "1");
//            if (bean.save()) {
//                baseHttpUrl = "http://" + bean.getIp() + "/";
////                baseHttpUrl = "http://" + bean.getIp() + "/sri/";
//                sBaseHttpUrl1="http://" + bean.getIp()+"/";
//
//            }
//
//
//        } else {
//            for (int i = 0; i < Serverlist.size(); i++) {
//                if (Serverlist.get(i).getIsUse().equals("1")) {
//                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/";
////                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/sri/";
//                    sBaseHttpUrl1="http://" + Serverlist.get(i).getIp()+"/";
//                }
//            }
//        }


//        List<SettingServerBean> Serverlist = DataSupport.findAll(SettingServerBean.class);
//        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        if (Serverlist.size() == 0) {
//            String sBaseHttpUrl = baseHttpUrl.substring(7, 26);
//            SettingServerBean bean = new SettingServerBean(sBaseHttpUrl, "默认服务器地址", name, "1");
//            if (bean.save()) {
//                baseHttpUrl = "http://" + bean.getIp() + "/sri/";
//            }
//
//
//        } else {
//            for (int i = 0; i < Serverlist.size(); i++) {
//                if (Serverlist.get(i).getIsUse().equals("1")) {
//                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/sri/";
//                }
//            }
//        }

        Util.setVariousUrlFromBaseUrl(this, baseHttpUrl);

////        GCARetrofit.BASE_URL =  Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
//        GCARetrofit.BASE_URL = baseHttpUrl;
//        GCARetrofit.BASE_URL_NEW = baseHttpUrl;
//        Version.HTTP_URL = baseHttpUrl;
//        String uploadServiceUrlSuffix = Util.getMetaDataFromManifest(this, "HTTP_UPLOAD_VIDEO_URL");
//        Version.HTTP_UPLOAD_VIDEO_URL = Version.HTTP_URL+uploadServiceUrlSuffix;
////        Version.HTTP_UPLOAD_VIDEO_URL = uploadServiceUrlSuffix;
//        Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = getVideoUrlPrefix(Version.HTTP_UPLOAD_VIDEO_URL);
//        Version.FIND_FILE_URL_BASE = "";
//        Version.FILE_URL_BASE = Version.HTTP_URL.substring(0, Version.HTTP_URL.length() - 1);
//        Version.LOGIN_APP_NAME = Util.getMetaDataFromManifest(this, "LOGIN_APP_NAME");
//        String buglyId = Util.getMetaDataFromManifest(this, "BUGLY_APP_ID");
//        buglyId = Util.getRealBuglyAppId(buglyId);
//        Version.BUGLY_APP_ID = buglyId;
        initNetworkStatusListener();

        initStrictMode();


    }

    private String getVideoUrlPrefix(String baseUrl) {
        String result = baseUrl;
        int lastIndexOfColon = result.lastIndexOf(Constants.COLON_STRING);
        if (lastIndexOfColon != -1) {
            int firstIndexOfSlash = result.indexOf(Constants.SLASH_STRING, lastIndexOfColon);
            if (firstIndexOfSlash != -1) {
                result = result.substring(0, firstIndexOfSlash);
            }
        }
        return result;
    }

    public NetChangeObserver netChangeObserver;

    public void initNetworkStatusListener() {
        netChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                LogUtil.i("connection to server success,net type:" + type);
//                Util.showToast(DaggerApplication.this, Constants.HINT_NET_CONNECTION_SUCCESS);
            }

            @Override
            public void onNetDisConnect() {
                LogUtil.i("connection to server failure");
//                Util.showToast(DaggerApplication.this, Constants.HINT_NET_CONNECTION_FAILURE);
            }
        };
        NetStateReceiver.registerNetworkStateReceiver(this);
        NetStateReceiver.registerObserver(netChangeObserver);
    }


    public void initStrictMode() {
        if (Version.ENABLE_STRICT_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }


//    @Override
//    public void onLoginOrganizationSuccess(HttpResultBaseBean<List<OrganizationBean>> bean) {
//
//        Util.hideGifProgressDialog(getApplicationContext());
//        List<OrganizationBean> lrb = bean.getData();
//
//        int LoginOrganizationBeanList = DataSupport.count(OrganizationBean.class);
//
//
//        if (lrb.size() == 0) {
//
//        } else {
//            if (LoginOrganizationBeanList == 0) {
//                DataSupport.saveAll(lrb);
//
//            } else {
//
//            }
//            Util.showToast(this, "机构初始化完成");
//
////            int LoginAreaList = DataSupport.count(gdws_sys_area.class);
////            int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
////            int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
////
////            if (LoginAreaList < 5000 && LoginSalvationList != 0 && LoginDictionaryList != 0 && LoginOrganizationBeanList != 0) {
////
////                Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, getUsername(), getPassword());
////                loginPresenter.main(map);
////            } else {
////                initAlldata();
////
////            }
//        }
//    }
//
//    @Override
//    public void onLoginOrganizationBeanFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }
//
//    @Override
//    public void onLoginPcodeSuccess(HttpResultBaseBean<List<PcodeDataBean>> bean) {
//
//        if(bean.getData().size()>0){
//            if(bean.getData().get(0).getConfigvalue()==null){
//
//            }else {
//                Identity.srcInfo.setConfigvalue(bean.getData().get(0).getConfigvalue());
//            }
//        }
//
//
//    }
//
//    @Override
//    public void onLoginPcodeBeanFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }


    private void initAlldata() {
        DataSupport.deleteAll(SRCLoginDataDictionaryBean.class);
        DataSupport.deleteAll(SRCLoginSalvationBean.class);
        DataSupport.deleteAll(OrganizationBean.class);
//
//        Map<String, String> map1 = ParametersFactory.getLoginMapFrompCode(this);
//        loginPresenter.getpCodeData(map1);

//        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//        if (LoginSalvationList == 0) {
//            Map<String, String> map3 = ParametersFactory.getLoginMapSalvation(this);
//            loginPresenter.mainSalvation(map3);
//        } else {
//
//        }
//
//        int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//
//        if (LoginDictionaryList == 0) {
//            Map<String, String> map2 = ParametersFactory.getLoginMapDictionary(this);
//            loginPresenter.mainDictionary(map2);
//        } else {
//
//        }
//
//        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);
//        if (LoginOrganizationList == 0) {
//            Map<String, String> map4 = ParametersFactory.getLoginOrganization(this);
//            loginPresenter.getOrganizationData(map4);
//        }
    }
}
