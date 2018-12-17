package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.FamilyDetailbeans;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.FamilDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.FamilyDetailsActivityModule;
import com.jqsoft.fingertip_health.di.presenter.GetFaimilyDetaildataPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.utils.GlideUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/27.
 */

public class FamilyDetailActivity extends AbstractActivity implements FamilDetailActivityContract.View {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    private String gId;
    @BindView(R.id.userimg)
    ImageView userimg;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.usersex)
    TextView usersex;
    @BindView(R.id.useridcard)
    TextView useridcard;
    @BindView(R.id.relationship)
    TextView relationship;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.minzu)
    TextView minzu;
    @BindView(R.id.personfl)
    TextView personfl;
    @BindView(R.id.personlb)
    TextView personlb;
    @BindView(R.id.hyzk)
    TextView hyzk;
    @BindView(R.id.whcd)
    TextView whcd;
    @BindView(R.id.ldpower)
    TextView ldpower;
    @BindView(R.id.cjlb)
    TextView cjlb;
    @BindView(R.id.cjcardid)
    TextView cjcardid;
    @BindView(R.id.jkzk)
    TextView jkzk;
    @BindView(R.id.zbbz)
    TextView zbbz;
    @BindView(R.id.shbzh)
    TextView shbzh;
    @BindView(R.id.cblx)
    TextView cblx;
    @BindView(R.id.sfyfdx)
    TextView sfyfdx;
    @BindView(R.id.sfsw)
    TextView sfsw;
    @BindView(R.id.grysr)
    TextView grysr;
    @BindView(R.id.flsb)
    TextView flsb;
    @BindView(R.id.tdjzdxlb)
    TextView tdjzdxlb;

    @Inject
    GetFaimilyDetaildataPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_familymemberdetails_layout;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        gId = bundle.getString("gId");//读出数据
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("家庭成员详情");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        Map<String, String> map = getRequestMap();
        mPresenter.getfaimilydata(map, false);

    }

    public Map<String, String> getRequestMap() {
     //;/   gId = "3480FAA9-55F3-401A-8926-B79EE6228C32";
        Map<String, String> map = ParametersFactory.getFaimilydata(this, gId);
        return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addfaimilydetailactivity(new FamilyDetailsActivityModule(this))
                .inject(this);
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<FamilyDetailbeans>> bean) {
           String photoUrl = bean.getData().get(0).getSPEOPNO();
           String imageUrl = bean.getData().get(0).getGoalIP()+photoUrl;

            GlideUtils.loadImage(imageUrl,userimg);

        username.setText("姓名: "+bean.getData().get(0).getSPEOPNAME());
        usersex.setText("性别: "+bean.getData().get(0).getSSEXNAME());
        useridcard.setText(bean.getData().get(0).getSIDCARD());
        relationship.setText(bean.getData().get(0).getSFAMILYGXNAME());
        birthday.setText(bean.getData().get(0).getDateStr());
        minzu.setText(bean.getData().get(0).getSNATIONNAME());
        personfl.setText(bean.getData().get(0).getRYLBNAME());
        personlb.setText(bean.getData().get(0).getSLYLBNAME());
        hyzk.setText(bean.getData().get(0).getSHYZKNAME());
        whcd.setText(bean.getData().get(0).getSWHCDNAME());
        ldpower.setText(bean.getData().get(0).getSWORKABLENAME());
        cjlb.setText(bean.getData().get(0).getSCBLXNAME());
        cjcardid.setText(bean.getData().get(0).getSCJNO());
        jkzk.setText(bean.getData().get(0).getSJKZKNAME());
        zbbz.setText(bean.getData().get(0).getSZBBZNAME());
        shbzh.setText(bean.getData().get(0).getSBZZH());
        cblx.setText(bean.getData().get(0).getSCBLXNAME());
        sfyfdx.setText(bean.getData().get(0).getISFYFDXSTR());
        sfsw.setText(bean.getData().get(0).getISFSWSTR());
        grysr.setText(bean.getData().get(0).getFZSR());
        flsb.setText(bean.getData().get(0).getSFLSBNAME());
        tdjzdxlb.setText(bean.getData().get(0).getSTDJZDXNAME());
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<FamilyDetailbeans>> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {

    }
}
