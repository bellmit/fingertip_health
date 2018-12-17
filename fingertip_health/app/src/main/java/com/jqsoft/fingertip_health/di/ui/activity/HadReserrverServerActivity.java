package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.ReserverContract;
import com.jqsoft.fingertip_health.di.presenter.ReServerPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;

import javax.inject.Inject;

import butterknife.BindView;

public class HadReserrverServerActivity extends AbstractActivity implements
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
    TextView tv_applydate;




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
        return R.layout.activity_hadreserrver_server;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);


    }

    @Override
    protected void loadData() {
        mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");
        mPendExecuBeanList= (PendExecuBeanList) this.getIntent().getSerializableExtra("PendExecuBeanList");

        String s = mpeopleBasebean.age;
        String s1 = mpeopleBasebean.age;

        tv_applypackname.setText(mPendExecuBeanList.getFwmc());
        tv_applyservername.setText(mPendExecuBeanList.getServiceItemsName());

      /*  SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        sNowDate    =    formatter.format(curDate);*/
      String sReservationTime =mPendExecuBeanList.getReservationTime();
        if(TextUtils.isEmpty(sReservationTime) || sReservationTime==null){
            tv_applydate.setText("");
        }else {
            if(sReservationTime.length()>10){
                tv_applydate.setText(sReservationTime.substring(0,10));
            }else {
                tv_applydate.setText( mPendExecuBeanList.getReservationTime());

            }


        }



        String reservationServrtPlace = mPendExecuBeanList.getReservationServrtPlace();

        if(TextUtils.isEmpty(reservationServrtPlace) || reservationServrtPlace==null){

        }else{
            if(reservationServrtPlace.equals("1")){
                rb_adress1.setEnabled(true);
                rb_adress1.setChecked(true);


                rb_adress2.setEnabled(false);
                rb_adress3.setEnabled(false);
                rb_adress4.setEnabled(false);

            }else if(reservationServrtPlace.equals("2")){
                rb_adress2.setEnabled(true);
                rb_adress2.setChecked(true);

                rb_adress1.setEnabled(false);
                rb_adress3.setEnabled(false);
                rb_adress4.setEnabled(false);

            }else if(reservationServrtPlace.equals("3")){
                rb_adress4.setEnabled(true);
                rb_adress4.setChecked(true);
                rb_adress1.setEnabled(false);
                rb_adress2.setEnabled(false);
                rb_adress3.setEnabled(false);


                et_execu_addressother.setVisibility(View.VISIBLE);
                et_execu_addressother.setEnabled(false);
                et_execu_addressother.setText(mPendExecuBeanList.getReservationServrtPlaceOther());
            }else if(reservationServrtPlace.equals("4")){
                rb_adress3.setEnabled(true);
                rb_adress3.setChecked(true);
                rb_adress1.setEnabled(false);
                rb_adress2.setEnabled(false);
                rb_adress4.setEnabled(false);

            }else{
                rb_adress1.setEnabled(false);
                rb_adress2.setEnabled(false);
                rb_adress3.setEnabled(false);
                rb_adress4.setEnabled(false);
            }
        }





    }


    @Override
    protected void initInject() {

    }


    @Override
    public void onSaveReserverSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.hideGifProgressDialog(this);
        if(bean!=null){

            String msg = Util.getMessageFromHttpResponse(bean);
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

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
