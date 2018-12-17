package com.jqsoft.fingertip_health.di.ui.activity.webview;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by YLL on 2018-7-5.
 */

public class MyWebChomeClient extends WebChromeClient {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;

    public MyWebChomeClient(OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
    }

    /**
     * 4.4X以下回调             android 3.0以上，android4.0以下：
     *
     * @param uploadMsg
     * @param acceptType
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    //android 4.0 - android 4.3  && android4.4.4
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    /**
     * 该方法的作用:告诉当前app 打开一个文件选择器 如相册,启动拍照或打开本地文件管理器
     * webView加载包含上传文件的表单按钮,html定义了input标签,同同时input的type为file,手指点击该按钮
     * 回调onShowFileChooser方法,在这个重写的方法里打开相册 启动相机 或打开本地文件管理器,甚至做其他逻辑操作
     * 点击一次回调一次的前提是请求被取消,而取消请求回调的方法：给ValueCallBack接口的onReceiveValue抽象方法传入null
     * 同时onShowFileChooser方法返回true
     * <p>
     * 4.4X以上回调onShowFileChooser(替代)4.4X以下回调openFileChooser(隐藏);只重写某一个会造成有的系统点击没有反应
     * 区别：
     * 1：前者ValueCallback接口回传一个Uri数组,后者回传一个Uri对象,在onActivityResult回调方法中调用
     * ValueCallback接口方法onReceiveValue传入参数特别注意
     * for android 5.0+ 回调onShowFileChooser方法,onReceiveVlue传入Uri对象数组
     * for android 5.0- 回调openFileChooser方法,onReceiveVlue传入Uri对象
     * <p>
     * 2：前者将后者的 acceptType、capture封装成FileChooserParams抽象类
     */
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {
        return mOpenFileChooserCallBack.openFileChooserCallBackAndroid5(webView, filePathCallback, fileChooserParams);
    }

    public interface OpenFileChooserCallBack {
        // for API - Version below 5.0.
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);

        // for API - Version above 5.0 (contais 5.0).
        boolean openFileChooserCallBackAndroid5(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                                FileChooserParams fileChooserParams);
    }
}

