package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
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
import com.jqsoft.fingertip_health.bean.fingertip.LoginResultForFingertip;
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
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.HashSet;
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
public class IntroductionActivity extends AbstractActivity implements SRCLoginContract.View {
//    @BindView(R.id.liv_image)
//    LargeImageView livImage;

        @BindView(R.id.btn_test)
        Button btn_test;

    CompositeSubscription mCompositeSubscription;

    @Inject
    SRCLoginPresenter loginPresenter;
//    LoginPresenterForFingertip loginPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_introduction;
    }

    private String rememberedUsername="",rememberedPassword="";

    @Override
    protected void initData() {
//        test();
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

        String CPU_ABI = android.os.Build.CPU_ABI;
        String CPU_ABI2 = "none";
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) { // CPU_ABI2
            // since
            // 2.2
            try {
                CPU_ABI2 = (String) android.os.Build.class.getDeclaredField(
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
          initTimer();

      /*  List<gdws_sys_area> LoginAreaList = DataSupport.findAll(gdws_sys_area.class);
        List<SRCLoginSalvationBean> LoginSalvationList = DataSupport.findAll(SRCLoginSalvationBean.class);
        List<SRCLoginDataDictionaryBean> LoginDictionaryList = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
*/

//         gotoLoginActivity();
//        int LoginAreaList = DataSupport.count(gdws_sys_area.class);
//
//        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);
//
//        int LoginDictionaryList = DataSupport.count(SRCLoginDataDictionaryBean.class);
//        int LoginOrganizationList = DataSupport.count(OrganizationBean.class);
//        Connector.getDatabase();
//        int db = Connector.getDatabase().getVersion();
//
//
//            if (LoginAreaList !=0 && LoginSalvationList != 0 && LoginDictionaryList != 0 && LoginOrganizationList != 0 && db!=2) {
//                //   gotoLoginActivity();
//                initareadata();
//
//                // gotoLoginActivity();
//
//            } else {
//                //  gotoLoginActivity();
//                DataSupport.deleteAll(SRCLoginDataDictionaryBean.class);
//                DataSupport.deleteAll(gdws_sys_area.class);
//                DataSupport.deleteAll(SRCLoginSalvationBean.class);
//                DataSupport.deleteAll(OrganizationBean.class);
//
//                copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
//                gotoLoginActivity();
//
//
//
//        }




        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataSupport.deleteAll(gdws_sys_area.class);
//
//                        String sCity=  getJson(IntroductionActivity.this,"coummity.json");
//                        Gson gson = new Gson();
//                        JsonParser parser = new JsonParser();
//                        JsonArray Jarray = parser.parse(sCity).getAsJsonArray();
//
//                        ArrayList<gdws_sys_area> lcs = new ArrayList<gdws_sys_area>();
//                        lcs.clear();
//                        for(JsonElement obj : Jarray ){
//                            gdws_sys_area cse = gson.fromJson( obj , gdws_sys_area.class);
//                            lcs.add(cse);
//                        }
//
//                        DataSupport.saveAll(lcs);
//
//
//
//
//                copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
//                List <gdws_sys_area> srcLoginAreaBeen = DataSupport.findAll(gdws_sys_area.class);
//                List <SRCLoginDataDictionaryBean> srcLoginDataDictionaryBeen = DataSupport.findAll(SRCLoginDataDictionaryBean.class);
//                if(DataSupport.count(gdws_sys_area.class)>1000){
//
//
//                    Toast.makeText(getApplicationContext(), "导入成功!", Toast.LENGTH_LONG).show();
//                }else {
//
//                    Toast.makeText(getApplicationContext(), "导入失败!", Toast.LENGTH_LONG).show();
//                }

                readdb();

            }
        });
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
//            String sBaseHttpUrl = baseHttpUrl.substring(7, 26);

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
//                baseHttpUrl = "http://" + bean.getIp() + "/sri/";
                baseHttpUrl = "http://" + bean.getIp() + "/";
            }


        } else {
            for (int i = 0; i < Serverlist.size(); i++) {
                if (Serverlist.get(i).getIsUse().equals("1")) {
                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/";
//                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/sri/";
                }
            }
        }

        Util.setVariousUrlFromBaseUrl(this, baseHttpUrl);

////        GCARetrofit.BASE_URL =  Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
//        GCARetrofit.BASE_URL = baseHttpUrl;
//        GCARetrofit.BASE_URL_NEW = baseHttpUrl;
//        Version.HTTP_URL = baseHttpUrl;
//        String uploadServiceUrlSuffix = Util.getMetaDataFromManifest(this, "HTTP_UPLOAD_VIDEO_URL");
//        Version.HTTP_UPLOAD_VIDEO_URL = Version.HTTP_URL+uploadServiceUrlSuffix;
////        Version.HTTP_UPLOAD_VIDEO_URL = uploadServiceUrlSuffix;
//        Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = getVideoUrlPrefix(Version.HTTP_UPLOAD_VIDEO_URL);
//        Version.FILE_URL_BASE = Version.HTTP_URL.substring(0, Version.HTTP_URL.length() - 1);
//        Version.LOGIN_APP_NAME = Util.getMetaDataFromManifest(this, "LOGIN_APP_NAME");
//        String buglyId = Util.getMetaDataFromManifest(this, "BUGLY_APP_ID");
//        buglyId = Util.getRealBuglyAppId(buglyId);
//        Version.BUGLY_APP_ID = buglyId;
////        initNetworkStatusListener();
////
////        initStrictMode();


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


    public static String replaceBlank(String input){
        if(input==null)
            return null;
        StringBuffer output=new StringBuffer();
        for(int i=0;i<input.length();i++)
        {
            if(input.charAt(i)=='T')
            {
                output.append(" ");

            }
            else{
                output.append(input.charAt(i));
            }
        }
        return String.valueOf(output);
    }

    private void initareadata(){


        String result = DataSupport.max(SRCLoginAreaBean.class, "lastTime", String.class);
        if(result==null){
            result="";
            copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
            gotoLoginActivity();
        }else {
            result=  result.replace("T"," ");

            Map<String, String> map1 = ParametersFactory.getLogininitArea(this,result);
            loginPresenter.mainArea(map1);
        }


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

            in = context.getAssets().open( tab_name ); // 从assets目录下复制
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


    private void readdb(){
        File f = new File("/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db"); //比如  "/data/data/com.hello/databases/test.db"
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File o = new File(sdcardPath+"/DBPeopleBaseInfo.db"); //sdcard上的目标地址
        if(f.exists()) {
            FileChannel outF;
            try {
                outF = new FileOutputStream(o).getChannel();
                new FileInputStream(f).getChannel().transferTo(0, f.length(),outF);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(IntroductionActivity.this, "完成", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 从asset路径下读取对应文件转String输出
     *
     * @return
     */
    public static String getJson(Context mContext, String fileName) {

        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

    @Override
    protected void loadData() {
        //  registerFinishIntroductionActivityEvent();

    }

    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addArea(new SRCLoginModule(this))
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
//                gotoLoginActivity();
                Util.gotoActivity(getApplicationContext(), LoginActivityNew.class);

            }
        };
        timer.schedule(timerTask, Constants.INTRODUCTION_DISPLAY_DURATION);
    }

    private void gotoLoginActivity() {
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);

        Util.hideGifProgressDialog(this);
        initRememberPasswordCheckBox();
      //  onLogin(false);
//        Util.gotoActivity(this, LoginActivityNew.class);
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

        Util.showGifProgressDialog(getApplicationContext());
        Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPasswordForFingertip(this, rememberedUsername, rememberedPassword);
        loginPresenter.login(map);


    }


//        LoginRequestBean loginRequestBean = identity.getLoginBeanFromUsernameAndPassword(getUsername(), getPassword());
//        loginPresenter.main(loginRequestBean);




    private void gotoWorkbenchActivity() {
        finish();
        Util.gotoActivity(this, WorkbenchActivity.class);
    }

    private void gotoLoginNewActivity() {
        finish();
        Util.gotoActivity(this, LoginActivityNew.class);
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
//    @Override
//    public void onLoginSuccess(HttpResultBaseBean<SRCLoginBean> bean) {
//
//        //    Util.showToast(this, Constants.HINT_LOGIN_SUCCESS);
//        HAS_LOGGED_IN = true;
//
//
//        assignSRCIdentityData(bean);
//        String alias = Util.trimString(rememberedUsername);
//        //禁用极光推送
//        setAlias(alias);
//        gotoWorkbenchActivity();
//
////
////        if (shouldFinishWhenLoginSuccess) {
////            finish();
////        } else {
////
////        }
////        finishIntroductionActivity();
//    }

    @Override
    public void onLoginSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        HAS_LOGGED_IN = true;

        String resultString = bean.getResult();
        LoginResultForFingertip result = JSON.parseObject(resultString, LoginResultForFingertip.class);

        assignLoginResultForFingertip(result);

////        assignSRCIdentityData(bean);
//        String alias = Util.trimString(rememberedUsername);
//        //禁用极光推送
//        setAlias(alias);
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

    public void assignLoginResultForFingertip(LoginResultForFingertip lrft){
        if (lrft!=null){
            Identity.loginResultForFingertip = lrft;

            IdentityManager.setObjectToShare(this, Identity.loginResultForFingertip, Constants.LOGIN_RESULT_FOR_FINGERTIP_BEAN_KEY);

        }
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

    @Override
    public void onLoginAreaSuccess(HttpResultBaseBean<List<SRCLoginAreaBean>> bean) {
        List<SRCLoginAreaBean> lrb = bean.getData();

        if (lrb.size() == 0) {

        } else {
            String pate ="/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db";
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(pate, null);

            for(int i=0;i<lrb.size();i++){
                ContentValues values = new ContentValues();
                values.put("areaCode", lrb.get(i).getAreaCode());
                values.put("lastTime", lrb.get(i).getLastTime());
                values.put("alias", lrb.get(i).getAlias());
                values.put("areaLevel", lrb.get(i).getAreaLevel());
                values.put("areaName", lrb.get(i).getAreaName());
                values.put("areaPid", lrb.get(i).getAreaPid());
                values.put("state", lrb.get(i).getState());

                long replace = database.replace("gdws_sys_area",null,values);
            }
            database.close();
          //  Util.hideGifProgressDialog(getApplicationContext());
            Toast.makeText(getApplicationContext(),lrb.size()+"条地区数据更新成功",Toast.LENGTH_SHORT).show();

        }
        initsalvation();

    }

    private void initsalvation(){
        String result = DataSupport.max(SRCLoginSalvationBean.class, "lastTime", String.class);
        if(result==null){
            result="";
            copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
            gotoLoginActivity();
        }else {
            result=  result.replace("T"," ");
            Map<String, String> map3 = ParametersFactory.getLogininitSalvation(this,result);
            loginPresenter.mainSalvation(map3);
        }


    }

    @Override
    public void onLoginAreaFailure(String message) {

        if(message.equals("暂无数据")){
            initsalvation();
        }else {
            Util.hideGifProgressDialog(this);
            Util.showToast(this, message);
            gotoLoginActivity();
        }


    }

    @Override
    public void onLoginDataDictionatySuccess(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean) {
        List<SRCLoginDataDictionaryBean> lrb = bean.getData();

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            String pate ="/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db";
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(pate, null);

            for(int i=0;i<lrb.size();i++){
                DataSupport.deleteAll(SRCLoginDataDictionaryBean.class, "code = ? and pCode = ?", lrb.get(i).getCode(), lrb.get(i).getpCode());
//                SRCLoginDataDictionaryBean srcLoginDataDictionaryBean = new SRCLoginDataDictionaryBean();
//                srcLoginDataDictionaryBean.setCode(lrb.get(i).getCode());
//                srcLoginDataDictionaryBean.setpCode(lrb.get(i).getpCode());
//                srcLoginDataDictionaryBean.setName(lrb.get(i).getName());
//                srcLoginDataDictionaryBean.setSort(lrb.get(i).getSort());
//                srcLoginDataDictionaryBean.setState(lrb.get(i).getState());
//                srcLoginDataDictionaryBean.setLastTime(lrb.get(i).getLastTime());
            }
            DataSupport.saveAll(lrb);
          //  Util.hideGifProgressDialog(getApplicationContext());
            Toast.makeText(getApplicationContext(),lrb.size()+"条数据字典更新成功",Toast.LENGTH_SHORT).show();

        }
        initorigindata();
    }

    private void initorigindata(){
        String result = DataSupport.max(OrganizationBean.class, "lastTime", String.class);
        if(result==null){
            result="";
            copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
            gotoLoginActivity();
        }else {
            result=  result.replace("T"," ");
            Map<String, String> map4 = ParametersFactory.getLogininitOrganization(this,result);
            loginPresenter.getOrganizationData(map4);
        }


    }


    @Override
    public void onLoginDataDictionatyFailure(String message) {

        if(message.equals("暂无数据")){
            initorigindata();
        }else {
            Util.hideGifProgressDialog(this);
            Util.showToast(this, message);
            gotoLoginActivity();
        }
     //   gotoLoginActivity();

    }

    @Override
    public void onLoginSalvationSuccess(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean) {
        List<SRCLoginSalvationBean> lrb = bean.getData();


        int LoginSalvationList = DataSupport.count(SRCLoginSalvationBean.class);

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            String pate ="/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db";
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(pate, null);

            for(int i=0;i<lrb.size();i++){
                ContentValues values = new ContentValues();
                values.put("description", lrb.get(i).getDescription());
                values.put("itemId", lrb.get(i).getItemId());
                values.put("imgUrl", lrb.get(i).getImgUrl());
                values.put("itemCode", lrb.get(i).getItemCode());
                values.put("name", lrb.get(i).getName());
                values.put("sort", lrb.get(i).getSort());
                values.put("type", lrb.get(i).getType());
                values.put("state", lrb.get(i).getState());
                values.put("lastTime", lrb.get(i).getLastTime());
                values.put("unused", lrb.get(i).getUnused());
                values.put("processId", lrb.get(i).getProcessId());

                long replace = database.replace("SRCLoginSalvationBean",null,values);
            }
            database.close();
          //  Util.hideGifProgressDialog(getApplicationContext());
            Toast.makeText(getApplicationContext(),lrb.size()+"条救助项更新成功",Toast.LENGTH_SHORT).show();


        }
        initdictiondata();
    }

    private void initdictiondata(){
        String result = DataSupport.max(SRCLoginDataDictionaryBean.class, "lastTime", String.class);
        if(result==null){
            result="";
            copyDbFile(IntroductionActivity.this,"DBPeopleBaseInfo.db");
            gotoLoginActivity();
        }else {
            result=  result.replace("T"," ");
            Map<String, String> map2 = ParametersFactory.getLogininitDictionary(this,result);
            loginPresenter.mainDictionary(map2);
        }


    }

    @Override
    public void onLoginSalvationFailure(String message) {

        if(message.equals("暂无数据")){
            initdictiondata();
        }else {
            Util.hideGifProgressDialog(this);
            Util.showToast(this, message);
            gotoLoginActivity();
        }

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

        if (lrb.size() == 0) {
            //  String s1=lrb.get(0).getAreaName();
        } else {
            String pate ="/data/data/com.jqsoft.fingertip_health/databases/DBPeopleBaseInfo.db";
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(pate, null);

            for(int i=0;i<lrb.size();i++){
                ContentValues values = new ContentValues();
                values.put("aliasName", lrb.get(i).getAliasName());
                values.put("areaId", lrb.get(i).getAreaId());
                values.put("code", lrb.get(i).getCode());
                values.put("name", lrb.get(i).getName());
                values.put("parentCode", lrb.get(i).getParentCode());
                values.put("unitType", lrb.get(i).getUnitType());
                values.put("gglx_xtbldw", lrb.get(i).getGglx_xtbldw());
                values.put("state", lrb.get(i).getState());
                values.put("lastTime", lrb.get(i).getLastTime());

                long replace = database.replace("OrganizationBean",null,values);
            }
            database.close();
            Util.hideGifProgressDialog(getApplicationContext());
            Toast.makeText(getApplicationContext(),lrb.size()+"条机构数据更新成功",Toast.LENGTH_SHORT).show();

        }
        gotoLoginActivity();

    }

    @Override
    public void onLoginOrganizationBeanFailure(String message) {
        Util.hideGifProgressDialog(this);
        if(message.equals("暂无数据")){

        }else {
            Util.showToast(this, message);

        }
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
            Util.showToast(IntroductionActivity.this, Constants.HINT_ALIAS_EMPTY);
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
                    //if (UserUtils.getTagAlias(getHoldingActivity()) == false) {
                    Set<String> tags = new HashSet<String>();
                    //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
                   List<String> jgtags= Identity.srcInfo.getJgTag();
                    if(jgtags==null){
                        tags.add(IdentityManager.getAreaId(getApplicationContext()));
                    }else {
                        if(jgtags.size()==0){
                            tags.add(IdentityManager.getAreaId(getApplicationContext()));
                        }else {
                            for(int i=0;i<jgtags.size();i++){
                                tags.add(jgtags.get(i));
                            }
                        }
                    }


                    JPushInterface.setAliasAndTags(IntroductionActivity.this,
                            (String) msg.obj,
                            tags,
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
                    Util.setAliasStatus(IntroductionActivity.this, true);
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
