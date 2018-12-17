package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.ReserverContract;
import com.jqsoft.fingertip_health.di.module.ReserverActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ReServerPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

public class ReserrverServerActivity extends AbstractActivity implements
        ReserverContract.View,
        SmoothRadioButton.OnCheckedChangeListener,SmoothRadioGroup.OnCheckedChangeListener{

    @Inject
    ReServerPresenter coreindexpresenter;

    private PeopleBaseInfoBean  mpeopleBasebean;
    private PendExecuBeanList  mPendExecuBeanList;
    private String  sNowDate;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_applypackname)
    TextView tv_applypackname;

    @BindView(R.id.tv_applyservername)
    TextView tv_applyservername;

    @BindView(R.id.tv_applydate)
    EditText tv_applydate;

    @BindView(R.id.btn_save)
    RoundTextView ll_reserver;


    @BindView(R.id.rg_adress1)
    SmoothRadioGroup rg_adress1;

    @BindView(R.id.rg_adress2)
    SmoothRadioGroup rg_adress2;


    @BindView(R.id.rb_adress1)
    SmoothRadioButton rb_adress1;

    @BindView(R.id.rb_adress2)
    SmoothRadioButton rb_adress2;

    @BindView(R.id.rb_adress3)
    SmoothRadioButton rb_adress3;

    @BindView(R.id.rb_adress4)
    SmoothRadioButton rb_adress4;

    String address_shangmen="",address_cunsi="",address_xiangz="",address_other="",
            serverPlaceType="",serverPlaceOther="",flagOther="";

    @BindView(R.id.et_execu_addressother)
    EditText et_execu_addressother;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reserrver_server;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        if(TextUtils.isEmpty(address_shangmen) && TextUtils.isEmpty(address_cunsi)
                && TextUtils.isEmpty(address_xiangz) && TextUtils.isEmpty(address_other)){
            address_shangmen="";
            address_cunsi="2";
            address_xiangz="";
            address_other="";
            rb_adress2.setChecked(true);

        }


        ll_reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String signDetailID =mPendExecuBeanList.getSignDetailID();
                String serviceContentItemsID =mPendExecuBeanList.getServiceContentItemsKey();
                String serviceContentID =mPendExecuBeanList.getServiceContentKey();

                String userName =mpeopleBasebean.getUserName();
                String doctorCode = mPendExecuBeanList.getDoctorCode();

                String orgID = mPendExecuBeanList.getOrgID();
                String docUserID =mPendExecuBeanList.getDocUserID();//签约医生id
                String reservationTime =tv_applydate.getText().toString();
                String signPageyear =mPendExecuBeanList.getSignPageyear();

                if (!TextUtils.isEmpty(address_other)) {
                    serverPlaceType = "3";//服务地点类型
                    serverPlaceOther = et_execu_addressother.getText().toString();//服务地点其他
                    if(TextUtils.isEmpty(serverPlaceOther)){
                        flagOther="1";

                    }else{
                        flagOther="2";
                    }

                } else if (!TextUtils.isEmpty(address_shangmen)) {
                    serverPlaceType = "1";//服务地点类型
                    serverPlaceOther = "";//服务地点其他
                } else if (!TextUtils.isEmpty(address_cunsi)) {
                    serverPlaceType = "2";//服务地点类型
                    serverPlaceOther = "";//服务地点其他
                } else {
                    serverPlaceType = "4";//服务地点类型
                    serverPlaceOther ="";//服务地点其他
                }

                if(flagOther.equals("1")){
                    Toast.makeText(ReserrverServerActivity.this,"请输入其他地点",Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> map = ParametersFactory.saveReserver(ReserrverServerActivity.this, signDetailID,serviceContentItemsID,serviceContentID,
                            userName,doctorCode,orgID,docUserID,reservationTime,serverPlaceType,serverPlaceOther,signPageyear);
                    RequestBody body = Util.getBodyFromMap1(map);
                    coreindexpresenter.mainsave(body);
                }


            }
        });

        rb_adress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg_adress2.clearCheck();
                address_shangmen="1";
                address_cunsi="";
                address_xiangz="";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);


            }
        });

        rb_adress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg_adress2.clearCheck();
                address_shangmen="";
                address_cunsi="2";
                address_xiangz="";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);
                //   Toast.makeText(context,"村室"+address_cunsi,Toast.LENGTH_SHORT).show();
            }
        });

        rb_adress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg_adress2.clearCheck();
                address_shangmen="";
                address_cunsi="";
                address_xiangz="4";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);
                //   Toast.makeText(context,"乡镇"+address_xiangz,Toast.LENGTH_SHORT).show();
            }
        });

        rb_adress4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg_adress1.clearCheck();
                address_shangmen="";
                address_cunsi="";
                address_xiangz="";
                address_other="3";
                et_execu_addressother.setVisibility(View.VISIBLE);
                // Toast.makeText(context,"其他"+address_other,Toast.LENGTH_SHORT).show();
            }
        });

        tv_applydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String initial = getSignTimeString();
                Util.showDateSelectMinDate(ReserrverServerActivity.this, initial, "a_party_fragment_sign_time", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        tv_applydate.setText(s);


                    }
                });
            }
        });

    }


    private String getSignTimeString() {
        String s = Util.trimString(tv_applydate.getText().toString());
        return s;
    }

    @Override
    protected void loadData() {
        mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");
        mPendExecuBeanList= (PendExecuBeanList) this.getIntent().getSerializableExtra("PendExecuBeanList");

        String s = mpeopleBasebean.age;
        String s1 = mpeopleBasebean.age;

        tv_applypackname.setText(mPendExecuBeanList.getFwmc());
        tv_applyservername.setText(mPendExecuBeanList.getServiceItemsName());

        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        sNowDate    =    formatter.format(curDate);
        tv_applydate.setText(sNowDate);

    }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addreserver(new ReserverActivityModule(this))
                .inject(this);

    }


    @Override
    public void onSaveReserverSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.hideGifProgressDialog(this);
        if(bean!=null){

            String msg = Util.getMessageFromHttpResponse(bean);
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

            finish();


        }
    }

    @Override
    public void onLoadSaveReserverFailure(String message) {
        Util.hideGifProgressDialog(this);
        if(message!=null){

            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
        switch (smoothCompoundButton.getId()) {
            case R.id.rb_adress1:
                rg_adress2.clearCheck();
                address_shangmen="1";
                address_cunsi="";
                address_xiangz="";
                address_other="";
                //     Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress2:
                rg_adress2.clearCheck();

                address_shangmen="";
                address_cunsi="2";
                address_xiangz="";
                address_other="";
                //    Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress3:
                rg_adress2.clearCheck();
                address_shangmen="";
                address_cunsi="";
                address_xiangz="3";
                address_other="";
                //      Toast.makeText(getApplicationContext(), "乡镇"+address_xiangz, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress4:
                rg_adress1.clearCheck();

                address_shangmen="";
                address_cunsi="";
                address_xiangz="";
                address_other="4";
                //      Toast.makeText(getApplicationContext(), "其他"+address_other, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(SmoothRadioGroup smoothRadioGroup, int i) {
        switch (i) {
            case R.id.rb_adress1:
                address_shangmen="1";
                address_cunsi="";
                address_xiangz="";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);
                //   Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress2:
                address_shangmen="";
                address_cunsi="2";
                address_xiangz="";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);
                //   Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress3:

                address_shangmen="";
                address_cunsi="";
                address_xiangz="3";
                address_other="";
                et_execu_addressother.setVisibility(View.GONE);
                //  Toast.makeText(getApplicationContext(), "乡镇"+address_xiangz, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress4:
                address_shangmen="";
                address_cunsi="";
                address_xiangz="";
                address_other="4";
                et_execu_addressother.setVisibility(View.VISIBLE);
                //   Toast.makeText(getApplicationContext(), "其他"+address_other, Toast.LENGTH_SHORT).show();
                break;


            default:
                break;
        }
    }
}
