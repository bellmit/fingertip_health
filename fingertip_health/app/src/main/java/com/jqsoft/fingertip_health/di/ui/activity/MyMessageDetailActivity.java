package com.jqsoft.fingertip_health.di.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.MyMessageDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.MyMessageDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.MyMessageDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.MyMessageDetailActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.NotInputDialog;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 我的消息详情
 */

public class MyMessageDetailActivity extends AbstractActivity  implements    MyMessageDetailActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.policy_title)
    TextView ReliefItem_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_datetime)
    TextView tv_time;

    @Inject
    MyMessageDetailActivityPresenter mPresenter;

    private  String id;
    String name;
    HelpListBean helpListBean;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addMyMessageDetailActivity(new MyMessageDetailActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mymessagedetail_layout;
    }

    @Override
    protected void initData() {

            id=(String)getDeliveredSerializableByKey(Constants.RELIEF_DETAIL_ACTIVITY_KEY);


    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        ReliefItem_title.setText("我的消息详情");
        ButterKnife.bind(this);



;
    }




    @Override
    protected void loadData() {
        onRefresh();
    }






    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<MyMessageDetailBean> bean) {
        MyMessageDetailBean myMessageDetailBean=bean.getData();
        try {
            tvTitle.setText(myMessageDetailBean.getTheme());
            SpannableStringBuilder span = new SpannableStringBuilder("缩进"+myMessageDetailBean.getMessage());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tv_content.setText(span);
            String time= myMessageDetailBean.getReceiveTime().substring(0,10);
            tv_time.setText(time);
        }catch (Exception ex) {

        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<MyMessageDetailBean> bean) {
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




    public void onRefresh() {

            Map<String, String> map = ParametersFactory.getGCAMyMessageDetail(this,id,
                    "appMessage.messageView");
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
