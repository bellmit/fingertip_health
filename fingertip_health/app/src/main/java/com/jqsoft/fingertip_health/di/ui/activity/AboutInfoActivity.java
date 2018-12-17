package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.AboutInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AboutInfoContract;
import com.jqsoft.fingertip_health.di.module.AboutInfoActivityModule;
import com.jqsoft.fingertip_health.di.presenter.AboutInfoPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


//我的信息
public class AboutInfoActivity extends AbstractActivity implements AboutInfoContract.View {
    @BindView(R.id.treatment_title)
    TextView treatment_title;
//    @BindView(R.id.contactUs)
//     TextView tv_contractUs;
    @BindView(R.id.disclaimer)
    TextView tv_disclaimer;
    @BindView(R.id.technicalSupport)
     TextView tv_technicalSupport;
    @BindView(R.id.phone)
    TextView tv_phone;

    @BindView(R.id.address)
    TextView tv_address;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Inject
    AboutInfoPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_info;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        treatment_title.setText("关于");
    }



    @Override
    protected void loadData() {
//        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        Map<String, String> map = ParametersFactory.getGCAAboutInfoMap(this, "appMineUpdate.queryAboutListSri");
//        mPresenter.main(map);
        tv_phone.setText("");
        tv_address.setText("");

    }





    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addAboutInfoActivity(new AboutInfoActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_my_info, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<AboutInfoBean> bean) {
        AboutInfoBean bean1= bean.getData();
//        tv_contractUs.setText(bean1.getContractUs());
        tv_disclaimer.setText(Html.fromHtml(bean1.getDisclaimer()));
        tv_technicalSupport.setText(bean1.getTechnicalSupport());
        tv_phone.setText(bean1.getPhone());
        tv_address.setText(bean1.getAddress());

    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
