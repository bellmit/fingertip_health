package com.jqsoft.fingertip_health.di.ui.activity.webview;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.http.httpmanafe.HttpManager;
import com.tencent.bugly.beta.Beta;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MyWebChomeClient.OpenFileChooserCallBack {

    private WebView mWebView;
    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 1;

    private Intent mSourceIntent;
    private ValueCallback<Uri> mUploadMsg;
    public ValueCallback<Uri[]> mUploadMsgForAndroid5;

    // permission Code
    private static final int P_CODE_PERMISSIONS = 101;
    private ImageView iv_load;
    private boolean flag=true;
    private String itemid;


    private TextView tv_failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_web);



        mWebView=(WebView)findViewById(R.id.webview);
        tv_failed=(TextView)findViewById(R.id.tv_faile);
        iv_load=(ImageView)findViewById(R.id.faq_activity_back) ;
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //设置滚动条样式
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);     //设置js可以直接打开窗口，如window.open()，默认为false
        mWebView.getSettings().setJavaScriptEnabled(true);     //是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        mWebView.getSettings().setSupportZoom(true);           //是否可以缩放，默认true
        mWebView.getSettings().setAllowFileAccess(true);       // 设置允许访问文件数据
        mWebView.getSettings().setBuiltInZoomControls(false);   //是否显示缩放按钮，默认false
        mWebView.getSettings().setUseWideViewPort(true);       //设置此属性，可任意比例缩放。大视图模式
        mWebView.getSettings().setLoadWithOverviewMode(true);  //和setUseWideViewPort(true)一起解决网页自适应问题
        mWebView.getSettings().setAppCacheEnabled(true);       //是否使用缓存
        mWebView.getSettings().setDomStorageEnabled(true);     //DOM Storage


        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //   mWebView.addJavascriptInterface(new JsInterface(MainActivity.this), "PartnerHome");
        mWebView.setWebChromeClient(new MyWebChomeClient(this));


       itemid=getIntent().getStringExtra("ItemId");

        HttpManager.getInstance().requestPhotoCheck3("",  "", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final String err = e.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                //    dismissWaitDlg();
//                                Util.hideGifProgressDialog(LoginActivityEidNew.this);
//                                ToastUtil.showToast(err, getApplicationContext());

                                iv_load.setVisibility(View.VISIBLE);
                                tv_failed.setVisibility(View.VISIBLE);

                            }
                        });

                        //   dismissWaitDlg();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String sResult = response.body().string();

                JSONObject js = null;
                try {
                    js = new JSONObject(sResult);

                    final  String  interfaceIp =js.getString("interfaceIp");
                    final  String interfaceIp1 = interfaceIp.replaceAll("\\\\","");
                    final String  areacode =js.getString("areacode");
                    final String  openid =js.getString("openid");
                    final String  currarea =js.getString("currarea");
                    final String  curruser =js.getString("curruser");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_load.setVisibility(View.GONE);
                            tv_failed.setVisibility(View.GONE);

String url=interfaceIp1+"/n_page/YMSMobile/onlinelist.aspx?areacode="+areacode+"&slbcode="+itemid+"&openid="+openid+"&curruser="+curruser+"&currarea="+currarea;

                            mWebView.loadUrl(url);

                            //    dismissWaitDlg();
//                                Util.hideGifProgressDialog(LoginActivityEidNew.this);
//                                ToastUtil.showToast(message, getApplicationContext());
//
//
//                                doLogin(name,idNum,eid,userId);

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_load.setVisibility(View.VISIBLE);
                            tv_failed.setVisibility(View.VISIBLE);
                            //       dismissWaitDlg();

//                            Util.hideGifProgressDialog(LoginActivityEidNew.this);
//                            ToastUtil.showToast("登陆失败:", getApplicationContext());




                        }
                    });

                }



            }
        });






        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {//用于打开支付宝
            /*view.loadUrl(url);
            return true;*/
                if(flag){
                    iv_load.setVisibility(View.VISIBLE);
                }


                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    CookieSyncManager.getInstance().sync();
                } else {
                    CookieManager.getInstance().flush();
                }


                if(flag){
                    iv_load.setVisibility(View.GONE);
                }
                flag=false;



            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
                //这里的判断是为了让页面在有上一个页面的情况下，跳转到上一个html页面，而不是退出当前activity

                if(flag){
                    iv_load.setVisibility(View.GONE);
                }
                flag=false;


            }

        });

        fixDirPath();
        //明光
     //   mWebView.loadUrl("http://112.29.244.172:8083/phimp-app");
        //义安

        //  mWebView.loadUrl("http://112.29.244.172:8083/phimp-app");
        //   mWebView.loadUrl("http://60.173.247.168:7187/gdws/admin/appindex.do");

        Beta.checkUpgrade();


//        设置是否显示消息通知
//        如果你不想在通知栏显示下载进度，你可以将这个接口设置为false，默认值为true。
        Beta.enableNotification = true;



    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {         //==Activity.RESULT_CANCELED
            if (mUploadMsg != null) {
                mUploadMsg.onReceiveValue(null);
            }

            if (mUploadMsgForAndroid5 != null) {                     // for android 5.0+
                mUploadMsgForAndroid5.onReceiveValue(null);
            }
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_IMAGE_CAPTURE:
            case REQUEST_CODE_PICK_IMAGE: {
                try {
                    //for android 5.0- 回调openFileChooser方法,onReceiveVlue传入Uri对象
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        if (mUploadMsg == null) {
                            return;
                        }

                        String sourcePath = ImageUtil.retrievePath(MainActivity.this, mSourceIntent, data);

                        if (TextUtils.isEmpty(sourcePath) || !new File(sourcePath).exists()) {
                            //  Log.e(TAG, "sourcePath empty or not exists.");
                            break;
                        }
                        Uri uri = null;
                        uri = Uri.fromFile(new File(sourcePath));
                        mUploadMsg.onReceiveValue(uri);

                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (mUploadMsgForAndroid5 == null) {        // for android 5.0+
                            return;
                        }

                        String sourcePath = ImageUtil.retrievePath(MainActivity.this, mSourceIntent, data);

                        if (TextUtils.isEmpty(sourcePath) || !new File(sourcePath).exists()) {
                            //    Log.e(TAG, "sourcePath empty or not exists.");
                            break;
                        }
                        Uri uri = null;
                        try {
                            uri = Uri.fromFile(new File(sourcePath));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //for android 5.0+ 回调onShowFileChooser方法,onReceiveVlue传入Uri对象数组
                        mUploadMsgForAndroid5.onReceiveValue(new Uri[]{uri});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 选择完文件之后调用
     *
     * @param uploadMsg
     * @param acceptType
     */
    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMsg = uploadMsg;
        showOptions();
    }

    /**
     * 选择完文件之后调用    5.0以上
     */
    @Override
    public boolean openFileChooserCallBackAndroid5(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        mUploadMsgForAndroid5 = filePathCallback;       //选中的图片uri数组
        showOptions();
        return true;
    }

    public void showOptions() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setOnCancelListener(new DialogOnCancelListener());

        alertDialog.setTitle("请选择操作");
        // gallery, camera.
        String[] options = {"相册", "拍照"};

        alertDialog.setItems(options, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {

//点击了相册
                            if (PermissionUtil.isOverMarshmallow()) {

                                if (!PermissionUtil.isPermissionValid(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                                    //     ToastUtils.showToast("请去\"设置\"中开启本应用的图片媒体访问权限");
                                    restoreUploadMsg();
                                    requestPermissionsAndroidM();
                                    return;
                                }
                            }

                            try {
                                mSourceIntent = ImageUtil.choosePicture();
                                startActivityForResult(mSourceIntent, REQUEST_CODE_PICK_IMAGE);
                            } catch (Exception e) {
                                e.printStackTrace();

                                //   ToastUtils.showToast("请去\"设置\"中开启本应用的图片媒体访问权限");
                                restoreUploadMsg();
                            }

                        } else {
//点击了拍照
                            if (PermissionUtil.isOverMarshmallow()) {

                                if (!PermissionUtil.isPermissionValid(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                                    //    ToastUtils.showToast("请去\"设置\"中开启本应用的图片媒体访问权限");
                                    restoreUploadMsg();
                                    requestPermissionsAndroidM();
                                    return;
                                }

                                if (!PermissionUtil.isPermissionValid(MainActivity.this, Manifest.permission.CAMERA)) {

                                    //   ToastUtils.showToast("请去\"设置\"中开启本应用的相机权限");
                                    restoreUploadMsg();
                                    requestPermissionsAndroidM();
                                    return;
                                }
                            }

                            try {
                                mSourceIntent = ImageUtil.takeBigPicture(MainActivity.this);
                                startActivityForResult(mSourceIntent, REQUEST_CODE_IMAGE_CAPTURE);

                            } catch (Exception e) {
                                e.printStackTrace();
                                //   ToastUtils.showToast("请去\"设置\"中开启本应用的相机和图片媒体访问权限");
                                restoreUploadMsg();
                            }


                        }
                    }
                }
        );

        alertDialog.show();
    }

    private void fixDirPath() {
        String path = ImageUtil.getDirPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private class DialogOnCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            restoreUploadMsg();
        }
    }

    //重置  解决无法重复选择
    private void restoreUploadMsg() {

        if (mUploadMsg != null) {
            mUploadMsg.onReceiveValue(null);
            mUploadMsg = null;

        } else if (mUploadMsgForAndroid5 != null) {
            mUploadMsgForAndroid5.onReceiveValue(null);
            mUploadMsgForAndroid5 = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case P_CODE_PERMISSIONS:
                requestResult(permissions, grantResults);
                restoreUploadMsg();
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestPermissionsAndroidM() {             //请求权限

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            List<String> needPermissionList = new ArrayList<>();
            needPermissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            needPermissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            needPermissionList.add(Manifest.permission.CAMERA);

            PermissionUtil.requestPermissions(MainActivity.this, P_CODE_PERMISSIONS, needPermissionList);

        } else {
            return;
        }
    }

    public void requestResult(String[] permissions, int[] grantResults) {
        ArrayList<String> needPermissions = new ArrayList<String>();

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (PermissionUtil.isOverMarshmallow()) {

                    needPermissions.add(permissions[i]);
                }
            }
        }

        if (needPermissions.size() > 0) {
            StringBuilder permissionsMsg = new StringBuilder();

            for (int i = 0; i < needPermissions.size(); i++) {
                String strPermissons = needPermissions.get(i);

                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(strPermissons)) {
                    //   permissionsMsg.append("," + getString(R.string.permission_storage));

                } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(strPermissons)) {
                    //  permissionsMsg.append("," + getString(R.string.permission_storage));

                } else if (Manifest.permission.CAMERA.equals(strPermissons)) {
                    //  permissionsMsg.append("," + getString(R.string.permission_camera));
                }
            }

            String strMessage = "请允许使用\"" + permissionsMsg.substring(1).toString() + "\"权限, 以正常使用APP的所有功能.";

        }
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
////            if (mWebView.canGoBack())
////            {
////                mWebView.goBack(); //goBack()表示返回WebView的上一页面
////                return true;
////            }else
////            {
////                finish();
////                return true;
////            }
//            if (!mWebView.canGoBack()) {
//
////           finish();
//                return true;
//            }
//
//        }
//        return false;
//    }

    /**
     * 设置点击返回按钮，跳转到上一个html页面，而不是退出当前activity
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()) {
                mWebView.goBack();
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    private long mExitTime;
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出!", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
