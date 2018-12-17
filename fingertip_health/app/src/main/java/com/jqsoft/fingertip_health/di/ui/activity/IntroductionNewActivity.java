package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.caption.netmonitorlibrary.netStateLib.NetChangeObserver;
import com.caption.netmonitorlibrary.netStateLib.NetStateReceiver;
import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.PcodeDataBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginBean;
import com.jqsoft.fingertip_health.di.contract.SRCLoginContract;
import com.jqsoft.fingertip_health.di.module.SRCLoginModule;
import com.jqsoft.fingertip_health.di.presenter.SRCLoginPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCARetrofit;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017-07-18.
 */
//应用介绍页面
public class IntroductionNewActivity extends AbstractActivity implements SRCLoginContract.View {
//    @BindView(R.id.liv_image)
//    LargeImageView livImage;

    CompositeSubscription mCompositeSubscription;

    @Inject
    SRCLoginPresenter loginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_introduction_new;
    }

    private String rememberedUsername="",rememberedPassword="";

   // private Button  btn_test;
    @BindView(R.id.btn_test)
    Button btn_test;

    @Override
    protected void initData() {
        //test();
//        hasCompatibleCPU();
    }


    private void test() {
        String arch = "";//cpu类型
        try {
            Class<?> clazz = Class.forName("Android.os.SystemProperties");
            Method get = clazz.getDeclaredMethod("get", new Class[]{String.class});
            arch = (String) get.invoke(clazz, new Object[]{"ro.product.cpu.abi"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.i("arch " + arch);
    }

    public boolean hasCompatibleCPU() {
        // If already checked return cached result

        String CPU_ABI = Build.CPU_ABI;
        String CPU_ABI2 = "none";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) { // CPU_ABI2
            // since
            // 2.2
            try {
                CPU_ABI2 = (String) Build.class.getDeclaredField(
                        "CPU_ABI2").get(null);
            } catch (Exception e) {
                return false;
            }
        }

        if (CPU_ABI.equals("armeabi-v7a") || CPU_ABI2.equals("armeabi-v7a")) {
            return true;
        }

        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("ARMv7")) {
                    return true;
                }

            }
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    protected void initView() {
        initBugly();

//        livImage.setImage(R.mipmap.i_introduction);
//        livImage.setEnabled(false);
        //  initTimer();

      /*  List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);
        List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
        List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
*/

        // gotoLoginActivity();
        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);

        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);

        int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);



        DataSupport.deleteAll(SRCLoginDataDictionaryBean.class);
        DataSupport.deleteAll(SRCLoginSalvationBean.class);
        DataSupport.deleteAll(OrganizationBean.class);
        if (LoginAreaList > 5000 && LoginSalvationList != 0 && LoginDictionaryList != 0 && LoginOrganizationList != 0) {
           // gotoLoginActivity();
           // initAlldata2();
        } else {
            Toast.makeText(getApplicationContext(), "正在初始化数据,请耐心等候!", Toast.LENGTH_LONG).show();
           // initAlldata();
            initAlldata2();


        }

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
//                DataSupport.deleteAll(SettingServerBean.class);
//                DataSupport.deleteAll(SRCLoginDataDictionaryBean.class);
//                DataSupport.deleteAll(SRCLoginSalvationBean.class);
//                DataSupport.deleteAll(OrganizationBean.class);
            }
        });
    }

    public void save() {
        String dbpath = "/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db";
//        String dbpath = "/data/data/com.jqsoft.signed_doctor_client.ah_lingbi/databases/DBDictionaryInfo.db";
//        String dbpath = "/data/data/com.jqsoft.signed_doctor_client.ah_luan_shucheng/databases/DBDictionaryInfo.db";
        boolean success=copyFile(dbpath, Environment.getExternalStorageDirectory() + "/"
                + "/DBPeopleBaseInfo.db");
        if(success){
            Toast.makeText(IntroductionNewActivity.this, "完成", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(IntroductionNewActivity.this, "shibai", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean copyFile(String source, String dest) {
        try {
            File f1 = new File(source);
            File f2 = new File(dest);
            InputStream in = new FileInputStream(f1);

            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);

            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;

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


        List<SettingServerBean> Serverlist = DataSupport.findAll(SettingServerBean.class);
        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
        if (Serverlist.size() == 0) {

            String regex = "http://(.*?)/";
//            String regex = "http://(.*)/sri/";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(baseHttpUrl);
            String sBaseHttpUrl ="";

            while (matcher.find()) {
                // System.out.println(matcher.group(1));
                sBaseHttpUrl=  matcher.group(1);
            }

            SettingServerBean bean = new SettingServerBean(sBaseHttpUrl, "默认服务器地址", name, "1");
            if (bean.save()) {
                baseHttpUrl = "http://" + bean.getIp() + "/";
//                baseHttpUrl = "http://" + bean.getIp() + "/sri/";
            }


        } else {
            for (int i = 0; i < Serverlist.size(); i++) {
                if (Serverlist.get(i).getIsUse().equals("1")) {
                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/";
//                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/sri/";
                }
            }
        }
//        GCARetrofit.BASE_URL =  Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
        GCARetrofit.BASE_URL = baseHttpUrl;
        GCARetrofit.BASE_URL_NEW = baseHttpUrl;
        Version.HTTP_URL = baseHttpUrl;
        String uploadServiceUrlSuffix = Util.getMetaDataFromManifest(this, "HTTP_UPLOAD_VIDEO_URL");
        Version.HTTP_UPLOAD_VIDEO_URL = Version.HTTP_URL+uploadServiceUrlSuffix;
//        Version.HTTP_UPLOAD_VIDEO_URL = uploadServiceUrlSuffix;
        Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = getVideoUrlPrefix(Version.HTTP_UPLOAD_VIDEO_URL);
        Version.FILE_URL_BASE = Version.HTTP_URL.substring(0, Version.HTTP_URL.length() - 1);
        Version.LOGIN_APP_NAME = Util.getMetaDataFromManifest(this, "LOGIN_APP_NAME");
        String buglyId = Util.getMetaDataFromManifest(this, "BUGLY_APP_ID");
        buglyId = Util.getRealBuglyAppId(buglyId);
        Version.BUGLY_APP_ID = buglyId;
        initNetworkStatusListener();

        initStrictMode();


    }

    private String getVideoUrlPrefix(String baseUrl){
        String result = baseUrl;
        int lastIndexOfColon = result.lastIndexOf(Constants.COLON_STRING);
        if (lastIndexOfColon!=-1){
            int firstIndexOfSlash = result.indexOf(Constants.SLASH_STRING, lastIndexOfColon);
            if (firstIndexOfSlash!=-1){
                result=result.substring(0, firstIndexOfSlash);
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


    @Override
    protected void loadData() {
        //  registerFinishIntroductionActivityEvent();

    }

    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addNewArea(new SRCLoginModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  unregisterFinishIntroductionActivityEvent();
    }

    private void initTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                gotoWorkbenchActivity();
                gotoLoginActivity();
            }
        };
        timer.schedule(timerTask, Constants.INTRODUCTION_DISPLAY_DURATION);
    }

    private void gotoLoginActivity() {
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);

        Util.hideGifProgressDialog(this);
        initRememberPasswordCheckBox();
      //  onLogin(false);
     //   Util.gotoActivity(this, LoginActivityNew.class);
//        finish();
    }

    public void initRememberPasswordCheckBox() {
        boolean isRemembered = PreferencesUtils.getBoolean(this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, false);


        if (isRemembered) {
             rememberedUsername = PreferencesUtils.getString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
             rememberedPassword = PreferencesUtils.getString(this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);

            doLogin(rememberedUsername,rememberedPassword);

        } else {

             rememberedUsername = PreferencesUtils.getString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);

            gotoLoginNewActivity();
        }
    }

    public void onLogin(boolean isManual) {
//        if (!checkLogin(isManual)) {
//            return;
//        } else {
//         //   doLogin();
//
//        }
    }

    public void doLogin(String rememberedUsername,String rememberedPassword) {

//            Util.showGifProgressDialog(getApplicationContext());
//            Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, rememberedUsername, rememberedPassword);
//            loginPresenter.main(map);

    }


//        LoginRequestBean loginRequestBean = identity.getLoginBeanFromUsernameAndPassword(getUsername(), getPassword());
//        loginPresenter.main(loginRequestBean);




    private void gotoWorkbenchActivity() {
        finish();
        Util.gotoActivity(this, WorkbenchActivity.class);
    }

    private void gotoLoginNewActivity() {
     //   finish();
     //   Util.gotoActivity(this, LoginActivityNew.class);
    }

    private void registerFinishIntroductionActivityEvent() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_FINISH_INTRODUCTION_ACTIVITY, Boolean.class)
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

    private void unregisterFinishIntroductionActivityEvent() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public static boolean HAS_LOGGED_IN = false;

    public void onLoginSuccess(HttpResultBaseBean<SRCLoginBean> bean) {

        //    Util.showToast(this, Constants.HINT_LOGIN_SUCCESS);
        HAS_LOGGED_IN = true;


        assignSRCIdentityData(bean);
        String alias = Util.trimString(rememberedUsername);
        //禁用极光推送
        setAlias(alias);
        gotoWorkbenchActivity();

//
//        if (shouldFinishWhenLoginSuccess) {
//            finish();
//        } else {
//
//        }
//        finishIntroductionActivity();
    }

    @Override
    public void onLoginSuccess(HttpResultBaseBeanForFingertip<String> bean) {

    }

    @Override
    public void onLoginFailure(String message) {
        Util.hideGifProgressDialog(this);
        HAS_LOGGED_IN = false;
////        Util.hideLoadingDialog();
//        Util.hideGifProgressDialog(this);
////        Util.showToast(this, Constants.HINT_LOGIN_FAILURE);
        Util.showToast(this, message);
        gotoLoginNewActivity();
//        LogUtil.i("登录失败");

      //  finishIntroductionActivity();
    }

    public void assignSRCIdentityData(HttpResultBaseBean<SRCLoginBean> bean) {
        if (bean != null) {
            SRCLoginBean lrb = bean.getData();

            Identity.srcInfo = lrb;
            Identity.srcInfo.setLoginSuccessUsername(rememberedUsername);
            Identity.srcInfo.setLoginSuccessPassword(rememberedPassword);
            Identity.srcInfo.setUserName(lrb.getUserName());
            Identity.srcInfo.setRealName(lrb.getRealName());
            Identity.srcInfo.setRoleId(lrb.getRoleId());
            Identity.srcInfo.setAreaId(lrb.getAreaId());
            Identity.srcInfo.setAreaLevel(lrb.getAreaLevel());
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

    @Override
    public void onLoginAreaSuccess(HttpResultBaseBean<List<SRCLoginAreaBean>> bean) {
        List<SRCLoginAreaBean> lrb = bean.getData();
        //  List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);

        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);
        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            if (LoginAreaList == 0) {
                /*for(int i=0;i<lrb.size();i++){
                    lrb.get(i).save();
                }*/

                DataSupport.saveAll(lrb);


            } else {

            }

                Util.showToast(this, "区域初始化完成");


            int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);

            //  List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
            if (LoginSalvationList == 0) {
                Map<String, String> map3 = ParametersFactory.getLoginMapSalvation(this);
                loginPresenter.mainSalvation(map3);
            } else {

            }


        }
    }

    @Override
    public void onLoginAreaFailure(String message) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, message);
        gotoLoginActivity();
    }

    @Override
    public void onLoginDataDictionatySuccess(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean) {
        List<SRCLoginDataDictionaryBean> lrb = bean.getData();

        //  List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
        int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            if (LoginDictionaryList == 0) {
              for(int i=0;i<lrb.size();i++){
                  if(lrb.get(i).getpCode()==null || TextUtils.isEmpty("null")){
                      lrb.get(i).setpCode("null");
                  }
              }

                DataSupport.saveAll(lrb);
            } else {

            }
            Util.showToast(this, "字典初始化完成");
            int LoginDictionaryList2 = DataSupport.count(OrganizationBean.class);

            if (LoginDictionaryList2 == 0 ) {
                Map<String, String> map4 = ParametersFactory.getLoginOrganization(this);
                loginPresenter.getOrganizationData(map4);
            } else {

            }


        }
    }

    @Override
    public void onLoginDataDictionatyFailure(String message) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, message);
        gotoLoginActivity();
    }

    @Override
    public void onLoginSalvationSuccess(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean) {
        List<SRCLoginSalvationBean> lrb = bean.getData();

        //  List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);

        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            if (LoginSalvationList == 0) {
                DataSupport.saveAll(lrb);
            } else {


            }

               Util.showToast(this, "救助事项初始化完成");

            //    List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);

            int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
            if (LoginDictionaryList == 0) {
                Map<String, String> map2 = ParametersFactory.getLoginMapDictionary(this);
                loginPresenter.mainDictionary(map2);
            } else {

            }

        }
    }

    @Override
    public void onLoginSalvationFailure(String message) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, message);
        gotoLoginActivity();
    }


    private void initAlldata() {
        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);
     /*   List<gdws_sys_area> LoginAreaList = DataSupport.count(gdws_sys_area.class);
        List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);*/
        if (LoginAreaList < 5000) {
            DataSupport.deleteAll(SRCLoginAreaBean.class);
            Map<String, String> map1 = ParametersFactory.getLoginMapArea(this);
            loginPresenter.mainArea(map1);
        } else {
            int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
            // List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
//            if (LoginSalvationList == 0) {
//                Map<String, String> map3 = ParametersFactory.getLoginMapSalvation(this);
//                loginPresenter.mainSalvation(map3);
//            } else {
//                int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//                //  List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
//                if (LoginDictionaryList == 0) {
//                    Map<String, String> map2 = ParametersFactory.getLoginMapDictionary(this);
//                    loginPresenter.mainDictionary(map2);
//
//                } else {
//                    int LogingOrganization = DataSupport.count(OrganizationBean.class);
//                    if (LogingOrganization == 0) {
//                        Map<String, String> map4 = ParametersFactory.getLoginOrganization(this);
//                        loginPresenter.getOrganizationData(map4);
//                    } else {
//                        gotoLoginActivity();
//                    }
//                }
//            }
        }



    }

    private void initAlldata2() {
        int LoginAreaList = DataSupport.count(SRCLoginAreaBean.class);
     /*   List<gdws_sys_area> LoginAreaList = DataSupport.count(gdws_sys_area.class);
        List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);*/
        if (LoginAreaList < 5000) {
            DataSupport.deleteAll(SRCLoginAreaBean.class);
            Map<String, String> map1 = ParametersFactory.getLoginMapArea(this);
            loginPresenter.mainArea(map1);
        } else {
            int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
            // List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
            if (LoginSalvationList == 0) {
                Map<String, String> map3 = ParametersFactory.getLoginMapSalvation(this);
                loginPresenter.mainSalvation(map3);
            } else {
                int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
                //  List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
                if (LoginDictionaryList == 0) {
                    Map<String, String> map2 = ParametersFactory.getLoginMapDictionary(this);
                    loginPresenter.mainDictionary(map2);

                } else {
                    int LogingOrganization = DataSupport.count(OrganizationBean.class);
                    if (LogingOrganization == 0) {
                        Map<String, String> map4 = ParametersFactory.getLoginOrganization(this);
                        loginPresenter.getOrganizationData(map4);
                    } else {
                        gotoLoginActivity();
                    }
                }
            }
        }



    }

    @Override
    public void onLoginOrganizationSuccess(HttpResultBaseBean<List<OrganizationBean>> bean) {
        List<OrganizationBean> lrb = bean.getData();

        //  List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);

        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            if (LoginOrganizationList == 0) {
                DataSupport.saveAll(lrb);
            } else {


            }

            int LoginDictionaryListnew = DataSupport.count(OrganizationBean.class);
            if (LoginDictionaryListnew != 0) {
                Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
                gotoLoginActivity();

            } else {
                gotoLoginActivity();

            }

        }

    }

    @Override
    public void onLoginOrganizationBeanFailure(String message) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, message);
        gotoLoginActivity();
    }

    @Override
    public void onLoginPcodeSuccess(HttpResultBaseBean<List<PcodeDataBean>> bean) {

    }

    @Override
    public void onLoginPcodeBeanFailure(String message) {

    }

    private void setAlias(String alias) {
//        if (Util.getAliasSetSuccess(getApplication())){
//            return;
//        }
        alias = Util.trimString(alias);
        if (TextUtils.isEmpty(alias)) {
            Util.showToast(IntroductionNewActivity.this, Constants.HINT_ALIAS_EMPTY);
            return;
        }
//        if (!ExampleUtil.isValidTagAndAlias(alias)) {
//            Util.showToast(IntroductionActivity.this, Constants.HINT_ALIAS_INVALID);
//            return;
//        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }
    private static final int MSG_SET_ALIAS = Constants.HANDLER_SET_ALIAS_MSG_ID;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                   // LogUtil.i(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(IntroductionNewActivity.this,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                  //  LogUtil.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case Constants.JPUSH_SET_ALIAS_SUCCESS_CODE:
                    logs = "Set tag and alias success";
                   // LogUtil.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    Util.setAliasStatus(IntroductionNewActivity.this, true);
//                    Util.showToast(LoginActivityNew.this, Constants.HINT_JPUSH_SET_ALIAS_SUCCESS);
                    break;
                case Constants.JPUSH_SET_ALIAS_FAILURE_CODE:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                 //   LogUtil.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                  //  LogUtil.i(TAG, logs);
            }
        }
    };
}
