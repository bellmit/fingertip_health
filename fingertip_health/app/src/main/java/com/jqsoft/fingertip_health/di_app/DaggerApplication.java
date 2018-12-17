package com.jqsoft.fingertip_health.di_app;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.utils.Utils;
import com.caption.netmonitorlibrary.netStateLib.NetChangeObserver;
import com.caption.netmonitorlibrary.netStateLib.NetStateReceiver;
import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.danikula.videocache.HttpProxyCacheServer;
import com.evernote.android.job.JobManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.MyUncaughtExceptionHandler;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCARetrofit;
import com.jqsoft.fingertip_health.keepalive.job_scheduler.AndroidJob;
import com.jqsoft.fingertip_health.keepalive.job_scheduler.AndroidJobCreator;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.service.FirService;
import com.jqsoft.fingertip_health.service.GrayService;
import com.jqsoft.fingertip_health.service.LongTermService;
import com.jqsoft.fingertip_health.service.MyJobService;
import com.jqsoft.fingertip_health.service.SecService;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePalApplication;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static com.jqsoft.fingertip_health.util.Util.getVideoUrlPrefix;

//import cn.jpush.android.api.JPushInterface;

/**
 * <b>类名称：</b> DaggerApplication <br/>
 * <b>类描述：</b> Application类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:21<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
//public class DaggerApplication extends TinkerApplication {
public class DaggerApplication extends MultiDexApplication {

    private static DaggerApplication instance;

    private static AppComponent appComponent;

    private RefWatcher mRefWatcher;
    private String goalIP;
    private String tableType;
    private String applyOrcheck;
    private String areaId;
    private String userlevel;
    private String versionNum;

    private String orgName;
    private String realName;
    private String username;

    private List<SRCLoginAreaBean> areaBeanList=new ArrayList<>();
    private List<gdws_sys_area> areaBeanListnew=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList1=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList2=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList3=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList4=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList5=new ArrayList<>();
    private List<gdws_sys_area> areaBeanList6=new ArrayList<>();
    private List<GDWS_ICD> datalist = new ArrayList<>();

    public List<SRCLoginAreaBean> getAreaBeanList() {
        return areaBeanList;
    }

    public void setAreaBeanList(List<SRCLoginAreaBean> areaBeanList) {
        this.areaBeanList = areaBeanList;
    }

    public String getapplyOrcheck() {
        return applyOrcheck;
    }

    public void setapplyOrcheck(String applyOrcheck) {
        this.applyOrcheck = applyOrcheck;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getGoalIP() {
        return goalIP;
    }

    public void setGoalIP(String goalIP) {
        this.goalIP = goalIP;
    }

    public NetChangeObserver netChangeObserver;

    public DaggerApplication() {
        super();
//        super(ShareConstants.TINKER_ENABLE_ALL, "com.jqsoft.fingertip_health.di_app.MyApplicationLike",
//                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    public static DaggerApplication getInstance() {
        return instance;
    }

    public static DaggerApplication get(Context context) {
        return (DaggerApplication) context.getApplicationContext();
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

    private void setupApplicationComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            this.setupApplicationComponent();
        }
        return appComponent;
    }

    private HttpProxyCacheServer videoCacheProxy;

    public static HttpProxyCacheServer getVideoCacheProxy(Context context) {
        DaggerApplication app = (DaggerApplication) context.getApplicationContext();
        return app.videoCacheProxy == null ? (app.videoCacheProxy = app.newVideoCacheProxy()) : app.videoCacheProxy;
    }

    private HttpProxyCacheServer newVideoCacheProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(200 * 1024 * 1024)
                .build();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        mRefWatcher = Version.ENABLE_LEAK_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
//        setupLeakCanary();


        clearSavedSRCLoginResultBean();

        startAndroidJob();
//        startJobSchedulerService();
//        startGrayService();
//        startTwoDaemonService();

//        startLongTermService();

        forbidReboot();


        //禁用tinker后把初始化代码放到这里
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Utils.init(this);//一个utils库的初始化 https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md

        initDatabase();

        initBugly();

        initNetworkStatusListener();

        initStrictMode();

        JPushInterface.setDebugMode(Version.JPUSH_DEBUG_MODE);
        JPushInterface.init(this);
//
//        JMessageClient.setDebugMode(Version.JMESSAGE_DEBUG_MODE);
//        JMessageClient.init(this, Version.JMESSAGE_ENABLE_ROAMING);
        //--------------------------------


        //腾讯优图识别身份证
        x.Ext.init(this);
        //数据库初始化

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterNetworkStatusListener();
    }

    private void initDatabase(){
        LitePalApplication.initialize(instance);
//        int count = DataSupport.count(GDWS_ICD.class);

    }

    private void forbidReboot() {
        Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.UncaughtExceptionHandler ueh = new MyUncaughtExceptionHandler(defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(ueh);
        // 禁止崩溃后重启
//        UncaughtExceptionHandlerImpl.getInstance().init(this, BuildConfig.DEBUG/*, true, 0, LoginActivityNew.class*/);
    }


    //清除保存到SharedPreferences中的登录返回的信息
    private void clearSavedSRCLoginResultBean() {
        IdentityManager.setObjectToShare(this, null, Constants.LOGIN_RESULT_FOR_FINGERTIP_BEAN_KEY);
//        IdentityManager.setObjectToShare(this, null, Constants.SRC_LOGIN_RESULT_BEAN_KEY);
    }

//    private void setupLeakCanary(){
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        mRefWatcher = LeakCanary.install(this);
//
//    }

    private void startAndroidJob() {
        try {
            JobManager jobManager = JobManager.create(this);
            jobManager.addJobCreator(new AndroidJobCreator());
//        jobManager.schedule();
//        JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true); // Don't use this in production

            AndroidJob.scheduleJob();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("startAndroidJob exception:" + e.getLocalizedMessage());
        }
    }

    private void startJobSchedulerService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(Constants.JOB_SERVICE_JOB_ID, new ComponentName(getPackageName(), MyJobService.class.getName()))
                    .setPeriodic(2000)
                    .setPersisted(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();
            jobScheduler.schedule(jobInfo);
        }
    }

    private void startGrayService() {
        Intent grayServiceIntent = new Intent(this, GrayService.class);
        startService(grayServiceIntent);
    }

    private void startTwoDaemonService() {
        Intent firstServiceIntent = new Intent(this, FirService.class);
        startService(firstServiceIntent);

        Intent secondServiceIntent = new Intent(this, SecService.class);
        startService(secondServiceIntent);
    }

    private void startLongTermService() {
        Intent intent = new Intent(this, LongTermService.class);
        startService(intent);
    }

    private void stopLongTermService() {
        Intent intent = new Intent(this, LongTermService.class);
        stopService(intent);
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

    public void unregisterNetworkStatusListener() {
        NetStateReceiver.removeRegisterObserver(netChangeObserver);
        NetStateReceiver.unRegisterNetworkStateReceiver(this);

    }

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
//            String sBaseHttpUrl = "";
//
//            while (matcher.find()) {
//                // System.out.println(matcher.group(1));
//                sBaseHttpUrl = matcher.group(1);
//            }
//
//
//            SettingServerBean bean = new SettingServerBean(sBaseHttpUrl, "默认服务器地址", name, "1");
//            if (bean.save()) {
//                baseHttpUrl = "http://" + bean.getIp() + "/";
////                baseHttpUrl = "http://" + bean.getIp() + "/sri/";
//                sBaseHttpUrl1 = "http://" + bean.getIp() + "/";
//
//            }
//
//
//        } else {
//            for (int i = 0; i < Serverlist.size(); i++) {
//                if (Serverlist.get(i).getIsUse().equals("1")) {
//                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/";
////                    baseHttpUrl = "http://" + Serverlist.get(i).getIp() + "/sri/";
//                    sBaseHttpUrl1 = "http://" + Serverlist.get(i).getIp() + "/";
//                }
//            }
//        }

        Util.setVariousUrlFromBaseUrl(this, baseHttpUrl);

//        GCARetrofit.BASE_URL =  Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
        GCARetrofit.BASE_URL = baseHttpUrl;
        GCARetrofit.BASE_URL_NEW = baseHttpUrl;
        Version.HTTP_URL = baseHttpUrl;
        String uploadServiceUrlSuffix = Util.getMetaDataFromManifest(this, "HTTP_UPLOAD_VIDEO_URL");
        Version.HTTP_UPLOAD_VIDEO_URL = Version.HTTP_URL+uploadServiceUrlSuffix;
//        Version.HTTP_UPLOAD_VIDEO_URL = uploadServiceUrlSuffix;
        Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = getVideoUrlPrefix(Version.HTTP_UPLOAD_VIDEO_URL);
        Version.FIND_FILE_URL_BASE = "";
        Version.FILE_URL_BASE = Version.HTTP_URL.substring(0, Version.HTTP_URL.length() - 1);
        Version.LOGIN_APP_NAME = Util.getMetaDataFromManifest(this, "LOGIN_APP_NAME");
        String buglyId = Util.getMetaDataFromManifest(this, "BUGLY_APP_ID");
        buglyId = Util.getRealBuglyAppId(buglyId);
        Version.BUGLY_APP_ID = buglyId;


    }

//    private String getVideoUrlPrefix(String baseUrl){
//        String result = baseUrl;
//        int lastIndexOfColon = result.lastIndexOf(Constants.COLON_STRING);
//        if (lastIndexOfColon!=-1){
//            int firstIndexOfSlash = result.indexOf(Constants.SLASH_STRING, lastIndexOfColon);
//            if (firstIndexOfSlash!=-1){
//                result=result.substring(0, firstIndexOfSlash);
//            }
//        }
//        return result;
//    }


    public String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 1, str.lastIndexOf(strEnd));
        return tempStr;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public List<gdws_sys_area> getAreaBeanListnew() {
        return areaBeanListnew;
    }

    public void setAreaBeanListnew(List<gdws_sys_area> areaBeanListnew) {
        this.areaBeanListnew = areaBeanListnew;
    }

    public List<gdws_sys_area> getAreaBeanList1() {
        return areaBeanList1;
    }

    public void setAreaBeanList1(List<gdws_sys_area> areaBeanList1) {
        this.areaBeanList1 = areaBeanList1;
    }

    public List<gdws_sys_area> getAreaBeanList2() {
        return areaBeanList2;
    }

    public void setAreaBeanList2(List<gdws_sys_area> areaBeanList2) {
        this.areaBeanList2 = areaBeanList2;
    }

    public List<gdws_sys_area> getAreaBeanList3() {
        return areaBeanList3;
    }

    public void setAreaBeanList3(List<gdws_sys_area> areaBeanList3) {
        this.areaBeanList3 = areaBeanList3;
    }

    public List<gdws_sys_area> getAreaBeanList4() {
        return areaBeanList4;
    }

    public void setAreaBeanList4(List<gdws_sys_area> areaBeanList4) {
        this.areaBeanList4 = areaBeanList4;
    }

    public List<gdws_sys_area> getAreaBeanList5() {
        return areaBeanList5;
    }

    public void setAreaBeanList5(List<gdws_sys_area> areaBeanList5) {
        this.areaBeanList5 = areaBeanList5;
    }

    public List<gdws_sys_area> getAreaBeanList6() {
        return areaBeanList6;
    }

    public void setAreaBeanList6(List<gdws_sys_area> areaBeanList6) {
        this.areaBeanList6 = areaBeanList6;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GDWS_ICD> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<GDWS_ICD> datalist) {
        this.datalist = datalist;
    }
}
