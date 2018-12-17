package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.ReceptionDetailNewListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NewsListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ReceptionDetailNewListActivityContract;
import com.jqsoft.fingertip_health.di.module.ReceptionDetailNewListActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ReceptionDetailNewListActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *
 * 受理中心新闻发布
 */

public class ReceptionDetailNewListActivity extends AbstractActivity implements
        ReceptionDetailNewListActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_message)
    WebView webView;

@Inject
ReceptionDetailNewListActivityPresenter mPresenter;

    private NewsListBean newsListBean;
    ReceptionDetailNewListBean receptionDetailNewListBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reception_newlist_layout;
    }

    @Override
    protected void initData() {
        newsListBean=(NewsListBean)getDeliveredSerializableByKey(Constants.RECRPTION_NEWLIST_ACTIVITY_KEY);
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);




    }
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addReceptionDetailNewListActivity(new ReceptionDetailNewListActivityModule(this))
                .inject(this);
    }




    @Override
    protected void loadData() {

        Map<String, String> map = ParametersFactory.getGCAReceptionDetailNewListMap(this,
                newsListBean.getId(),
                "receptionData.queryReceptionNotice");
        mPresenter.main(map);
    }



    private String getTitleString(){
        if (receptionDetailNewListBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(receptionDetailNewListBean.getTitle());
            return result;
        }
    }

    private String getAuthorString(){
        String result = "作者:";
        if (receptionDetailNewListBean==null){

        } else {
            result+=Util.trimString(receptionDetailNewListBean.getAuthor());
        }
        return result;
    }


    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<ReceptionDetailNewListBean> bean) {
        receptionDetailNewListBean=bean.getData();
        tvTitle.setText(getTitleString());
        tvAuthor.setText(getAuthorString());
        webView.getSettings().setJavaScriptEnabled(true);

        StringBuilder sb = new StringBuilder () ;
// 拼接一段HTMI代码
        sb.append("<html>") ;
        sb.append ("<head>") ;
        sb.append("<title> 欢迎您</title>") ;
        sb.append("</head>") ;
        sb.append ("<body>" );
        sb.append ("<h3><span style=\"font-weight:normal;font-size:18px\">"
                + receptionDetailNewListBean.getMessage()+"</span></h3>") ;
        sb.append ("</body>") ;
        sb.append("</html>") ;
// 使用简单的loadData方法会导致乱码，可能是Android API的Bug
// show.loadData(sb.toString() ,"text/html" ，"utf-8") ;

// 加载、并显示HTML代码

        webView.loadDataWithBaseURL (null,sb.toString (),
                "text/html" ,"utf-8",null); ;

//        tv_message.setText(receptionDetailNewListBean.getMessage());
    }

    @Override
    public void onLoadListFailure(String message) {

    }

//    public boolean onKeyDown(int keyCode,KeyEvent event){
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//            return true;//不执行父类点击事件
//        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
//    }
}

