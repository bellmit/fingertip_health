package com.jqsoft.fingertip_health.di.ui.activity.fingertip;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.DeatailPeopleBean;
import com.jqsoft.fingertip_health.bean.SignDoctorListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-08-23.
 * 签约政策解读明细
 */

public class PageDetailActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_datetime)
    TextView tvDatetime;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    WebView webView;
    @BindView(R.id.ll_arealevel)
    LinearLayout ll_arealevel;
    @BindView(R.id.arealevel)
    TextView tv_arealevel;

    @BindView(R.id.policy_title)
    TextView policy_title;
//    @BindView(R.id.wv_content)
//    WebView wvContent;
//    @BindView(R.id.tv_content)
//    TextView tvContent;

//    private PolicyBean policyBean;
    SignDoctorListBean signDoctorListBean;
    DeatailPeopleBean deatailPeopleBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_policy_detail_layout;
    }

    @Override
    protected void initData() {
//        policyBean=(PolicyBean)getDeliveredSerializableByKey(Constants.POLICY_DETAIL_ACTIVITY_KEY);
        signDoctorListBean = (SignDoctorListBean) getDeliveredSerializableByKey("data");
        deatailPeopleBean = (DeatailPeopleBean) getDeliveredSerializableByKey("data2");
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        policy_title.setText("签约协议");

        tvTitle.setText(getTitleString());
        tvAuthor.setText(getAuthorString());
        tvDatetime.setText(getDatetimeString());

        initWebview();
//        tvContent.setText(getContentString());
    }

    private void initWebview(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        llContent.addView(webView);


        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//支持插件
//        webSettings.setPluginsEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setTextZoom(350);
//        webSettings.setTextSize(WebSettings.TextSize.LARGEST);

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        String contentString = getContentString();
//        webView.loadData(contentString, "text/html;charset=utf-8", null);
        webView.loadDataWithBaseURL(null, contentString, "text/html", "utf-8", null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }


    private String getTitleString(){
        if (deatailPeopleBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(deatailPeopleBean.getAgreementName());
            return result;
        }
    }

    private String getAuthorString(){
        String result = " 来源:";
//        String author = policyBean.getAuthor();
//        if (policyBean==null){
//
//        } else {
//            String trimmedAuthor = Util.trimString(author);
//            if (StringUtils.isBlank(trimmedAuthor)) {
//                result=Constants.EMPTY_STRING;
//            } else {
//                result+=trimmedAuthor;
//            }
//        }
        return "";
    }

    private String getDatetimeString(){
        String result = "发布日期:";
//        if (policyBean==null){
//
//        } else {
//            String processedDateTime = Util.getProcessedDateTimeString(policyBean.getReleaseTime());
//            result+=processedDateTime;
//        }
        return "";
    }

    private String getContentString(){
        if (deatailPeopleBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(deatailPeopleBean.getContent());
            if(deatailPeopleBean.getServicePackageList()==null){

            }else {

                for(int i=0;i<deatailPeopleBean.getServicePackageList().size();i++){

                    String stitile= deatailPeopleBean.getServicePackageList().get(i).getPackageName();
                    String s="<p class=\"MsoNormal\" align=\"justify\" style=\"margin-right: 0pt; margin-left: 0pt; line-height: 20pt;font-weight:bold; text-indent: 0pt; text-align: justify;\">"+stitile+"</p>";
                    String scontent= deatailPeopleBean.getServicePackageList().get(i).getServiceContent();
                    result= result +s+scontent;
                }
            }


            return result;
        }
    }
}
