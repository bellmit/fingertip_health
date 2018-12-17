package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ServicePackDetailAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ServicePackDetailContract;
import com.jqsoft.fingertip_health.di.module.ServicePackDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ServicePackDetailPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

/**
 * Created by Jerry on 2017/7/4.
 */

public class DoctorServerDetails extends AbstractActivity implements ServicePackDetailContract.View {

    //  private ListView lv_service_pack;
    private ServicePackDetailAdapter mServicePackDetailAdapter;
    private List<String> data = new ArrayList<>();

    private List<ServicePackDetailBeanList> mListServerPackDetailBeanList = new ArrayList<>();

    @Inject
    ServicePackDetailPresenter serverpackdetailpresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_service_pack)
    ListView lv_service_pack;

    @BindView(R.id.tv_packname)
    TextView tv_packname;
    @BindView(R.id.tv_base_name)
    TextView tv_base_name;
    @BindView(R.id.tv_pack_content)
    TextView tv_pack_content;
    @BindView(R.id.tv_pack_money)
    TextView tv_pack_money;
    private String getdataKey;

    @Override
    protected int getLayoutId() {
        return R.layout.doctorserverdetailavtivity;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        getdataKey = getIntent().getStringExtra(Constants.SEVER_KEY);


    }

    @Override
    protected void loadData() {

        Map<String, String> map = ParametersFactory.getServicePackDetailList(this, getdataKey);
        RequestBody body = Util.getBodyFromMap(map);
        serverpackdetailpresenter.main(body);
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addservicepackdetail(new ServicePackDetailActivityModule(this))
                .inject(this);
    }


    @Override
    public void onServicePackDetailSuccess(HttpResultBaseBean<List<ServicePackDetailBeanList>> bean) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, "获取成功");

        if (bean != null) {
            List<ServicePackDetailBeanList> list = bean.getData();
            if (list != null) {


                showSignInfoOverview(list);
            }
        }
    }

    public void showSignInfoOverview(List<ServicePackDetailBeanList> list) {
        if (list != null) {
//            List<SignInfoOverviewResultBean> list = bean.getList();
            if (!ListUtils.isEmpty(list)) {

                for (int i = 0; i < list.size(); i++) {
                    //    CoreIndexBeanList mCoreIndexBeanList = list.get(i);
                    mListServerPackDetailBeanList.add(list.get(i));
                }

                if (!TextUtils.isEmpty(mListServerPackDetailBeanList.get(0).fwmc)) {
                    String sBaseName = mListServerPackDetailBeanList.get(0).fwmc;
                    tv_packname.setText(sBaseName);
                }

                if (!TextUtils.isEmpty(mListServerPackDetailBeanList.get(0).nhcompensateProjName)) {
                    if ((mListServerPackDetailBeanList.get(0).nhcompensateProjName).equals("1") || (mListServerPackDetailBeanList.get(0).nhcompensateProjName).equals("")) {
                        tv_base_name.setText("基础包");
                        tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#F4D249"));
                    } else if ((mListServerPackDetailBeanList.get(0).nhcompensateProjName).equals("2")) {
                        tv_base_name.setText("初级包");
                        tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#6ED1E0"));
                    } else if ((mListServerPackDetailBeanList.get(0).nhcompensateProjName).equals("3")) {
                        tv_base_name.setText("中级包");
                        tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#7DD0FF"));
                    } else if ((mListServerPackDetailBeanList.get(0).nhcompensateProjName).equals("4")) {
                        tv_base_name.setText("高级包");
                        tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
                    } else {
                        tv_base_name.setBackgroundColor(android.graphics.Color.parseColor("#FF8C5A"));
                    }
                } else {
                    tv_base_name.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(mListServerPackDetailBeanList.get(0).fwnr)) {
                    tv_pack_content.setText(mListServerPackDetailBeanList.get(0).fwnr);
                } else {
                    tv_pack_content.setText("");
                }

                String sSumMoney, sDerateMoney, sMedicalMoney, sPayMoney, sPackMoney;

                sSumMoney = mListServerPackDetailBeanList.get(0).hjnysfje;
                sDerateMoney = mListServerPackDetailBeanList.get(0).qtjmje;
                sMedicalMoney = mListServerPackDetailBeanList.get(0).xnhbcje;
                sPayMoney = mListServerPackDetailBeanList.get(0).sjzfje;

                if (TextUtils.isEmpty(sSumMoney)) {
                    sSumMoney = "0";
                }

                if (TextUtils.isEmpty(sDerateMoney)) {
                    sDerateMoney = "0";
                }

                if (TextUtils.isEmpty(sMedicalMoney)) {
                    sMedicalMoney = "0";
                }

                if (TextUtils.isEmpty(sPayMoney)) {
                    sPayMoney = "0";
                }

                sPackMoney = "总金额:" + sSumMoney + "元;" + "减免金额:" + sDerateMoney + "元;" + "医保补偿金额:" +
                        sMedicalMoney + "元;" + "自付金额:" + sPayMoney + "元";


                tv_pack_money.setText(sPackMoney);


                mServicePackDetailAdapter = new ServicePackDetailAdapter(this, mListServerPackDetailBeanList);
                lv_service_pack.setAdapter(mServicePackDetailAdapter);


            }
        }
    }


    @Override
    public void onLoadMoreServicePackDetailSuccess(HttpResultBaseBean<List<ServicePackDetailBeanList>> bean) {

    }

    @Override
    public void onServicePackDetailFailure(String message) {
        Util.hideGifProgressDialog(this);
    }
}
