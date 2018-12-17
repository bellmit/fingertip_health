package com.jqsoft.fingertip_health.di.ui.fragment;

import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HouseHoldBasebean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldServeyBaseContract;
import com.jqsoft.fingertip_health.di.module.HouseHoldServeyBaseModule;
import com.jqsoft.fingertip_health.di.presenter.HouseHoldBaseFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HouseHoldBaseInfoFragment extends AbstractFragment implements HouseHoldServeyBaseContract.View {
    @Inject
    HouseHoldBaseFragmentPresenter mPresenter;
    private String userID;
    @BindView(R.id.homesocialtype)
    TextView homesocialtype;
    @BindView(R.id.applytime)
    TextView applytime;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.useridcard)
    TextView useridcard;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.usertel)
    TextView usertel;
    @BindView(R.id.poorreson)
    TextView poorreson;
    @BindView(R.id.housemun)
    TextView housemun;
    @BindView(R.id.socailmun)
    TextView socailmun;
    @BindView(R.id.socailmoney)
    TextView socailmoney;
    @BindView(R.id.totoalmoney)
    TextView totoalmoney;
    @BindView(R.id.applyresason)
    TextView applyresason;

    @Override
    protected int getLayoutId() {
        return R.layout.serverybaseview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        //userID = "5C5AAD6E-5EBC-4B5F-B0D7-3147C8D5D17A";
        Map<String, String> map = getRequestMap();
        mPresenter.getbasepresent(map);

    }

    public Map<String, String> getRequestMap() {
        Map<String, String> map = ParametersFactory.getBaseFragmentData(getActivity(), userID);
        return map;
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addhouseholdbaseFragment(new HouseHoldServeyBaseModule(this))
                .inject(this);

    }



    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseHoldBasebean>> bean) {
        homesocialtype.setText(bean.getData().get(0).getSJTLBNAME());
        applytime.setText(bean.getData().get(0).getDSQRQ());
        username.setText(bean.getData().get(0).getSHZNAME());
        useridcard.setText(bean.getData().get(0).getSHZIDCARD());
        address.setText(bean.getData().get(0).getSADRR());
        usertel.setText(bean.getData().get(0).getSTEL());
        if (bean.getData().get(0).getSZPYYNAME().equals("undefined")) {
            poorreson.setText("");
        } else {
            poorreson.setText(bean.getData().get(0).getSZPYYNAME());
        }
        totoalmoney.setText(bean.getData().get(0).getFTOTALREVENUE());
        try {
            housemun.setText(bean.getData().get(0).getIJTRKS().substring(0, 1) + " 人");
            socailmun.setText(bean.getData().get(0).getIJTRKS().substring(0, 1) + " 人");
        } catch (Exception e) {
            e.printStackTrace();
        }
        socailmoney.setText(bean.getData().get(0).getFTOTALREVENUE());
        applyresason.setText(bean.getData().get(0).getSSQLY());
        GCAHttpResultBaseBean sx = new GCAHttpResultBaseBean();
        sx.setMsg(bean.getData().get(0).getSHZIDCARD());
        RxBus.getDefault().post(Constants.EVENT_TYPE_IDCARD, sx);

    }



    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseHoldBasebean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message) {
        Util.showToast(getActivity(), message);

    }

    public void setId(String id) {
        userID = id;

    }
}
