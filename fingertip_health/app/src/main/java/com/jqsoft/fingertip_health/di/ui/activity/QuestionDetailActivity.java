package com.jqsoft.fingertip_health.di.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.QuestionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.QuestionDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.QuestionDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.QuestionDetailActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.NotInputDialog;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 常见问题详情
 */

public class QuestionDetailActivity extends AbstractActivity  implements    QuestionDetailActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.policy_title)
    TextView ReliefItem_title;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.tv_datetime)
    TextView tv_time;
    WebView webView;
    @Inject
    QuestionDetailActivityPresenter mPresenter;
    String content;
    private  String id;
    String name;
    HelpListBean helpListBean;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addQuestionDetailActivity(new QuestionDetailActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_questiondetail_layout;
    }

    @Override
    protected void initData() {

            id=(String)getDeliveredSerializableByKey(Constants.RELIEF_Question_ACTIVITY_KEY);


    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        ReliefItem_title.setText("问题详情");
        ButterKnife.bind(this);



;
    }




    @Override
    protected void loadData() {
        onRefresh();
    }






    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<QuestionDetailBean> bean) {
        QuestionDetailBean questionDetailBean=bean.getData();

        try {
            tvTitle.setText(questionDetailBean.getTitle());
            SpannableStringBuilder span = new SpannableStringBuilder("缩进"+questionDetailBean.getContent());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            content =    questionDetailBean.getContent();
            tv_time.setText(questionDetailBean.getReleaseTime());
            initWebview();
        }catch (Exception ex) {

        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<QuestionDetailBean> bean) {
//        reliefItemBean = bean.getData();

    }


    @Override
    public void onLoadListFailure(String message) {


        NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该消息尚未数据！" )
                .setCancelable(false)
                .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view, String inputMsg) {
                        finish();
                    }
                })
                .setCanceledOnTouchOutside(false);


        inputDialog.show();
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

//        showdiaog();
    }

    private void initWebview(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
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


    private String getContentString(){
        if (content==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(content);
            return result;
        }
    }
    public void onRefresh() {

            Map<String, String> map = ParametersFactory.getGCAQuestionDetail(this,id,
                    "commonQuestion.commonQuestionDetail");
            mPresenter.main(map);




    }



//    private  void  showdiaog(){
//        if (reliefItemBean.getAcceptCondition()==null){
//
//
//            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//
//            NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该救助指南项目尚未完善！" )
//                    .setCancelable(false)
//                    .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
//                        @Override
//                        public void onPositive(View view, String inputMsg) {
//                            finish();
//                        }
//                    })
//                    .setCanceledOnTouchOutside(false);
//
//
//            inputDialog.show();
//
//
//        }
//    }

}
