package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ModifyServerSituationAdapter;
import com.jqsoft.fingertip_health.adapter.ServerSituationAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.CoreIndexBeanList;
import com.jqsoft.fingertip_health.bean.DeletehxzbInVoListBeans;
import com.jqsoft.fingertip_health.bean.ModifyExecuedBean;
import com.jqsoft.fingertip_health.bean.ModifyExecuedBeanList;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.SaveItemTargetsoListBeans;
import com.jqsoft.fingertip_health.bean.SaveSignServiceContentItemListBeans;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultItemBean;
import com.jqsoft.fingertip_health.di.contract.CoreIndexContract;
import com.jqsoft.fingertip_health.di.module.CoreIndexActivityModule;
import com.jqsoft.fingertip_health.di.presenter.CoreIndexPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//SmoothCompoundButton.OnCheckedChangeListener,

public class MotifyExecuActivity extends AbstractActivity implements
        CoreIndexContract.View,
        SmoothRadioButton.OnCheckedChangeListener,SmoothRadioGroup.OnCheckedChangeListener
       {

    @Inject
    CoreIndexPresenter coreindexpresenter;


    @BindView(R.id.rg_adress1)
    SmoothRadioGroup rg_adress1;

    @BindView(R.id.rg_adress2)
    SmoothRadioGroup rg_adress2;

    @BindView(R.id.rg_reason1)
    SmoothRadioGroup rg_reason1;

    @BindView(R.id.rg_reason2)
    SmoothRadioGroup rg_reason2;

    @BindView(R.id.rg_info)
    SmoothRadioGroup rg_info;

    @BindView(R.id.rb_adress1)
    SmoothRadioButton rb_adress1;

    @BindView(R.id.rb_adress2)
    SmoothRadioButton rb_adress2;

    @BindView(R.id.rb_adress3)
    SmoothRadioButton rb_adress3;

    @BindView(R.id.rb_adress4)
    SmoothRadioButton rb_adress4;

    @BindView(R.id.rb_reason1)
    SmoothRadioButton rb_reason1;

    @BindView(R.id.rb_reason2)
    SmoothRadioButton rb_reason2;

    @BindView(R.id.rb_reason3)
    SmoothRadioButton rb_reason3;

    @BindView(R.id.rb_info1)
    SmoothRadioButton rb_info1;

    @BindView(R.id.rb_info2)
    SmoothRadioButton rb_info2;
/*
    @BindView(R.id.lv_server_situation)
    ListViewForScrollView lv_server_situation;*/

    @BindView(R.id.lv_server_situation)
    ListView lv_server_situation;

    @BindView(R.id.sv_job_overview)
    ScrollView sv_job_overview;

    @BindView(R.id.btn_save)
    RoundTextView btn_save;

     @BindView(R.id.btn_delete)
     RoundTextView btn_delete;

    @BindView(R.id.tv_execu_name)
    TextView tv_execu_name;
    @BindView(R.id.tv_execu_doctor)
    TextView tv_execu_doctor;
    @BindView(R.id.tv_execu_project)
    TextView tv_execu_project;
    @BindView(R.id.et_execu_serverdate)
    EditText et_execu_serverdate;
    @BindView(R.id.et_execu_nextdate)
    TextView et_execu_nextdate;

    @BindView(R.id.ll_pend_execu)
    LinearLayout ll_pend_execu;
    @BindView(R.id.et_execu_addressother)
    EditText et_execu_addressother;
    @BindView(R.id.et_reason_other)
    EditText et_reason_other;

    @BindView(R.id.et_execu_serviceinfo)
    EditText et_execu_serviceinfo;
    private List<User> users;
    private ServerSituationAdapter adapter;
    private ModifyServerSituationAdapter modifyAdapter;
   // private List<CoreIndexBeanList> mListCoreIndexBeanList = new ArrayList<>();
  //  private List<ModifyExecuedBean> mModifyExecuedBeanList = new ArrayList<>();
      private List<ModifyExecuedBeanList> mModifyExecuedBeanList =new ArrayList<>();


    private String ServiceContentItemsKey;
    private PeopleBaseInfoBean mpeopleSignInfoBean;
    private PeopleBaseInfoBean  mpeopleBasebean;
    private PendExecuBeanList mPendExecuBeanList;
    private CompositeSubscription compositeSubscription;
    private   ModifyExecuedBean modifyexecuedbean;

           String address_shangmen="",address_cunsi="",address_xiangz="",address_other="",
            info_had="",info_pend="",reason_doctor="",reason_resident="",reason_other="";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String sign,signPageYear,servicePlanId,signDetailKey,serviceContentItemsId,serviceContentId,
            viewgKey,serviceContentDesc,serverDT,byServiceUserName,feedBackOpinion,feedBackDT,nextServerDT
                   ,doctorName,doctorCode,doctorPhone,addUserName,
            addOrgId,addDT,updateUserName,updateOrgId,updateDT,docUserID,townDeptCode,
            serverPlaceType,serverPlaceOther,docOrganizationKey,docOrganizationName,isExecute,
            isRefused,noExecuteRemark,checkDate,doctorSignKey,signFilingStatue,messageKey,
            serviceContentItemsGkey,shouldExecTimes,hadExecTimes,execTimes,edit,flag="1",orderServiceId="";
    private  List<SaveItemTargetsoListBeans> myCoreIndexItemList= new ArrayList<>();
    private  List<SaveSignServiceContentItemListBeans> myServiceContentItemList= new ArrayList<>();
    private String sNowDate;
    private String isEmpty="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_project;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        rb_adress1.setOnCheckedChangeListener(this);
        rb_adress2.setOnCheckedChangeListener(this);
        rb_adress3.setOnCheckedChangeListener(this);
        rb_adress4.setOnCheckedChangeListener(this);
        rb_reason1.setOnCheckedChangeListener(this);
        rb_reason2.setOnCheckedChangeListener(this);
        rb_reason3.setOnCheckedChangeListener(this);
        rg_adress1.setOnCheckedChangeListener(this);
        rg_adress2.setOnCheckedChangeListener(this);
        rg_reason1.setOnCheckedChangeListener(this);
        rg_reason2.setOnCheckedChangeListener(this);
        rg_info.setOnCheckedChangeListener(this);


        et_execu_addressother.setCursorVisible(false);
        et_execu_addressother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_execu_addressother.setCursorVisible(true);
            }
        });


     /*   rb_adress1.setChecked(true);
        rb_reason1.setChecked(true);
        rb_info2.setChecked(true);*/

        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date    curDate    =   new Date(System.currentTimeMillis());//??????????????????
        sNowDate    =    formatter.format(curDate);
        et_execu_serverdate.setText(sNowDate);

        et_execu_serverdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
             //   et_execu_serverdate.setText("");
                String initial = getSignTimeString();
                Util.showDateSelectDialog(MotifyExecuActivity.this, initial, "a_party_fragment_sign_time", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        et_execu_serverdate.setText(s);


                    }
                });
            }
        });


        et_execu_nextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                String initial = getNextTimeString();
                Util.showDateSelectDialog(MotifyExecuActivity.this, initial, "sign_time", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        et_execu_nextdate.setText(s);


                    }
                });
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String s3 =mListCoreIndexBeanList.get(0).getAbnormal();
             //   String s2 =mListCoreIndexBeanList.get(0).getInfo();

              //  String s3 =mListCoreIndexBeanList.get(1).getInfo();
             /* String s =  adapter.setServerInfo();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();*/
                setData();



            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new MaterialDialog.Builder(MotifyExecuActivity.this)
                        .title(R.string.hint_suggestion)
                        .content("???????????????????")
                        .negativeText(R.string.cancel)
                        .positiveText(R.string.confirm)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull
                                                                            MaterialDialog dialog, @NonNull DialogAction which) {
                                                    dialog.dismiss();

                                                    String sYear =mpeopleBasebean.getYear();
                                                    String servicePlanId =modifyexecuedbean.servicePlanId;

                                                    String serviceContentItemsGkey =mPendExecuBeanList.getItemsKey();
                                                    String signDetailKey=modifyexecuedbean.getSignDetailKey();
                                                    String hadExecTimes=mPendExecuBeanList.getHadExecTimes();

                                                    List<ModifyExecuedBeanList> CoreIndexlist =modifyexecuedbean.signDoctorItemhxzbOutVoList;
                                                    ArrayList<DeletehxzbInVoListBeans> myDeletehxzbInVoListBeans = new ArrayList<>();
                                                    myDeletehxzbInVoListBeans.clear();
                                                    if(modifyexecuedbean.signDoctorItemhxzbOutVoList.size()==0){

                                                    }else{


                                                        for(int i=0;i<CoreIndexlist.size();i++){
                                                            DeletehxzbInVoListBeans myDeletehxzbInVoBeans = new DeletehxzbInVoListBeans();
                                                            myDeletehxzbInVoBeans.setPackageExecutiveKey(Util.getBase64String(CoreIndexlist.get(i).getPackageExecutiveKey()));
                                                            myDeletehxzbInVoBeans.setKey(Util.getBase64String(CoreIndexlist.get(i).getKey()));
                                                            myDeletehxzbInVoListBeans.add(myDeletehxzbInVoBeans);
                                                        }
                                                    }
                                                    Map<String, Object> map = ParametersFactory.getdeleteSignInfo(MotifyExecuActivity.this, sYear,servicePlanId,myDeletehxzbInVoListBeans,serviceContentItemsGkey,signDetailKey,hadExecTimes);
                                                    RequestBody body = Util.getBodyFromMap1(map);
                                                    coreindexpresenter.maindelete(body);

                                                }
                                            }).build();
                       dialog.show();




            }
        });


        if (compositeSubscription == null) {
            registerProjectExecutionClickEvent();
        }

    }

    private void execued(){
        Map<String, Object> map = ParametersFactory.SaveExecuServerItem(this,
                signPageYear,
                servicePlanId,
                signDetailKey,
                serviceContentItemsId,
                serviceContentId,
                viewgKey,
                serviceContentDesc,
                serverDT,
                byServiceUserName,
                feedBackOpinion,
                feedBackDT,
                nextServerDT,
                doctorName,
                doctorCode,
                doctorPhone,
                addUserName,
                addOrgId,
                addDT,
                updateUserName,
                updateOrgId,
                updateDT,
                docUserID,
                townDeptCode,
                serverPlaceType,
                serverPlaceOther,
                docOrganizationKey,
                docOrganizationName,
                isExecute,
                isRefused,
                noExecuteRemark,
                checkDate,
                doctorSignKey,
                signFilingStatue,
                serviceContentItemsGkey,
                shouldExecTimes,
                hadExecTimes,
                execTimes,
                edit,
                flag,
                orderServiceId,
                myCoreIndexItemList,
                myServiceContentItemList
        );
        RequestBody body = Util.getBodyFromMap1(map);
     //   String bodyJson = JSON.toJSONString(map);
        coreindexpresenter.mainsave(body);
    }

     private String getSignTimeString() {
               String s = Util.trimString(et_execu_serverdate.getText().toString());
               return s;
           }
     private String getNextTimeString() {
               String s = Util.trimString(et_execu_nextdate.getText().toString());
               return s;
           }



     public void saveAbnormalData(int position, String str) {
         mModifyExecuedBeanList.get(position).setAbnormal(str);

      }
      public void saveEditData(int position, String str) {
          mModifyExecuedBeanList.get(position).setInfo(str);

           }
      public void saveEditData5(int position, String str) {
          mModifyExecuedBeanList.get(position).setInfo5(str);

           }

     public void saveHad1(int position, String str) {
         mModifyExecuedBeanList.get(position).setHad1(str);

           }
     public void savePlus3(int position, String str) {
         mModifyExecuedBeanList.get(position).setPlus3(str);

           }

     public void savePlusplus4(int position, String str) {
         mModifyExecuedBeanList.get(position).setPlusplus4(str);

           }

    private void setData(){

        if(TextUtils.isEmpty(address_shangmen)&&TextUtils.isEmpty(address_cunsi)
                &&TextUtils.isEmpty(address_xiangz)
                &&TextUtils.isEmpty(address_other)){
            Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(info_had)&&TextUtils.isEmpty(info_pend)){
            Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();

        }else {
            if (TextUtils.isEmpty(info_had)) {
                //??????????????????

                if (TextUtils.isEmpty(reason_doctor) && TextUtils.isEmpty(reason_resident)
                        && TextUtils.isEmpty(reason_other)) {
                    Toast.makeText(getApplicationContext(), "????????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if(TextUtils.isEmpty(mPendExecuBeanList.getHadExecTimes())||TextUtils.isEmpty(mPendExecuBeanList.getShouldExecTimes())){
                        nextServerDT = "";   //??????????????????????????????????????????????????????????????????????????????????????????
                    }else{
                        int hadTimes =  Integer.parseInt(mPendExecuBeanList.getHadExecTimes());
                        int shouldTimes =  Integer.parseInt(mPendExecuBeanList.getShouldExecTimes());
                        String nextDT= et_execu_nextdate.getText().toString();
                        if(shouldTimes-hadTimes>1){
                            if(TextUtils.isEmpty(nextDT)){
                                Toast.makeText(getApplicationContext(), "???????????????????????????", Toast.LENGTH_SHORT).show();
                                isEmpty="1";
                            }else{
                                isEmpty="2";
                                nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????
                            }

                        }else{
                            nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????

                        }
                    }

                  //  getCoreIndexInfo();
                    getCoreIndexInfo2();
                    if(isEmpty.equals("1")){

                    }else{
                        saveInfo1();
                    }


                }

            } else {
                if(TextUtils.isEmpty(mPendExecuBeanList.getHadExecTimes())||TextUtils.isEmpty(mPendExecuBeanList.getShouldExecTimes())){
                    nextServerDT = "";   //??????????????????????????????????????????????????????????????????????????????????????????
                }else{
                    int hadTimes =  Integer.parseInt(mPendExecuBeanList.getHadExecTimes());
                    int shouldTimes =  Integer.parseInt(mPendExecuBeanList.getShouldExecTimes());
                    String nextDT= et_execu_nextdate.getText().toString();
                    if(shouldTimes-hadTimes>1){
                        if(TextUtils.isEmpty(nextDT)){
                            Toast.makeText(getApplicationContext(), "???????????????????????????", Toast.LENGTH_SHORT).show();
                            isEmpty="1";
                        }else{
                            isEmpty="2";
                            nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????
                        }

                    }else{
                        nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????

                    }
                }


                getCoreIndexInfo();


                if(isEmpty.equals("1")){

                }else{
                    saveInfo2();
                }

            }

        }

    }

    private void saveInfo1(){
         isExecute = "2";//???????????????1 ???????????? 2 ?????????
        if (!TextUtils.isEmpty(reason_doctor)) {
            isRefused = "1";//??????????????????1??????????????????2??????????????????9?????????
            noExecuteRemark = "";//????????????????????? (?????? isExecute ?????????2???noExecuteRemark??????????????????)
        } else if (!TextUtils.isEmpty(reason_resident)) {
            isRefused = "2";//??????????????????1??????????????????2??????????????????9?????????
            noExecuteRemark = "";//????????????????????? (?????? isExecute ?????????2???noExecuteRemark??????????????????)
        } else {
            isRefused = "3";//??????????????????1??????????????????2??????????????????9?????????
            noExecuteRemark = et_reason_other.getText().toString();//????????????????????? (?????? isExecute ?????????2???noExecuteRemark??????????????????)
        }

        if (!TextUtils.isEmpty(address_other)) {
            serverPlaceType = "3";//??????????????????
            serverPlaceOther = et_execu_addressother.getText().toString();//??????????????????
        } else if (!TextUtils.isEmpty(address_shangmen)) {
            serverPlaceType = "1";//??????????????????
            serverPlaceOther = "";//??????????????????
        } else if (!TextUtils.isEmpty(address_cunsi)) {
            serverPlaceType = "2";//??????????????????
            serverPlaceOther = "";//??????????????????
        } else {
            serverPlaceType = "4";//??????????????????
            serverPlaceOther = et_execu_addressother.getText().toString();//??????????????????
        }

        serviceContentItemsId = mPendExecuBeanList.getServiceContentItemsKey();//5??????????????????key

        serviceContentItemsGkey = mPendExecuBeanList.getItemsKey();//36????????????????????????


        signPageYear = mpeopleBasebean.year;//????????????
        servicePlanId = mPendExecuBeanList.getServicePlanID();//????????????????????????????????????????????????????????????????????????????????????
        signDetailKey = mPendExecuBeanList.getSignDetailID();//????????????key

        serviceContentId = mPendExecuBeanList.getServiceContentKey();   //???????????????id
        viewgKey = "";          //????????????
        serverDT = et_execu_serverdate.getText().toString(); //9????????????

        byServiceUserName = mpeopleBasebean.getUserName();//????????????
        feedBackOpinion = "";           //????????????
        feedBackDT = "";            //????????????
    //    nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????
        doctorName = mpeopleBasebean.doctorName;//??????????????????
    //    doctorCode = Identity.info.getGuserid();//????????????code
        doctorCode = Identity.info.getShiploginname();//????????????code
        doctorPhone = mpeopleBasebean.getPhone();//??????????????????

    //    addUserName = Identity.info.getSusername(); //17???????????????
        addUserName = IdentityManager.getLoginSuccessUsername(this); //17???????????????
    //    addOrgId = Identity.info.getSorganizationkey();  //19????????????id
        addOrgId = Identity.getOrganizationKey();  //19????????????id
        addDT = sNowDate;   //18????????????

    //    updateUserName = Identity.info.getSusername();//??????????????? (??????????????????????????????)
        updateUserName = Identity.getUsername();//??????????????? (??????????????????????????????)
   //     updateOrgId = Identity.info.getSorganizationkey();  //????????????id (??????????????????????????????)
        updateOrgId = Identity.getOrganizationKey();  //????????????id (??????????????????????????????)
        updateDT = sNowDate;   //???????????? (??????????????????????????????)
    //    docUserID = Identity.info.getGuserid();//????????????id
        docUserID = Identity.getUserId();//????????????id

        if (Identity.info.getSorganizationlevelcode().equals("3")) {
            townDeptCode = Identity.info.getSorganizationtypecode();//??????????????????
        } else {
            townDeptCode = "";//24??????????????????
        }

    //    docOrganizationKey = Identity.info.getSorganizationkey();//27????????????????????????
    //    docOrganizationName = Identity.info.getSorganizationname();//28????????????????????????
        docOrganizationKey = Identity.getOrganizationKey();//27????????????????????????
        docOrganizationName = Identity.getOrganizationName();//28????????????????????????

        checkDate = "";//????????????
        doctorSignKey = mpeopleBasebean.getKey();//33??????????????????
        signFilingStatue = mPendExecuBeanList.getFinished();//34???????????????????????? ???0???????????????1???????????????


        shouldExecTimes = mPendExecuBeanList.getShouldExecTimes();//???????????????
        hadExecTimes = mPendExecuBeanList.getHadExecTimes();//?????????????????????
        execTimes = mPendExecuBeanList.getExecTime();//????????????????????????
        edit = "2";//???????????????????????????1??????????????????2??????????????????3?????????????????????

        myServiceContentItemList.clear();
        //??????????????????LIst
        SaveSignServiceContentItemListBeans mySaveSignServiceContentItemList = new SaveSignServiceContentItemListBeans();
        mySaveSignServiceContentItemList.setHadExecTimes(Util.getBase64String(mPendExecuBeanList.getHadExecTimes()));//????????????
        mySaveSignServiceContentItemList.setKey(Util.getBase64String(mPendExecuBeanList.getServiceContentKey()));//??????(xx-xx-xx-xx-xx) UUID  ????????? (D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
        mySaveSignServiceContentItemList.setShouldExecTimes(Util.getBase64String(mPendExecuBeanList.getShouldExecTimes()));//??????????????????

        myServiceContentItemList.add(mySaveSignServiceContentItemList);

        execued();
    }

    private void saveInfo2(){

        isExecute = "1";//???????????????1 ???????????? 2 ?????????
        isRefused = "";//??????????????????1??????????????????2??????????????????9?????????
        noExecuteRemark = "";//????????????????????? (?????? isExecute ?????????2???noExecuteRemark??????????????????)

        if (!TextUtils.isEmpty(address_other)) {
            serverPlaceType = "3";//??????????????????
            serverPlaceOther = et_execu_addressother.getText().toString();//??????????????????
        } else if (!TextUtils.isEmpty(address_shangmen)) {
            serverPlaceType = "1";//??????????????????
            serverPlaceOther = "";//??????????????????
        } else if (!TextUtils.isEmpty(address_cunsi)) {
            serverPlaceType = "2";//??????????????????
            serverPlaceOther = "";//??????????????????
        } else {
            serverPlaceType = "4";//??????????????????
            serverPlaceOther = et_execu_addressother.getText().toString();//??????????????????
        }

        serviceContentItemsId = mPendExecuBeanList.getServiceContentItemsKey();//5??????????????????key

        serviceContentItemsGkey = mPendExecuBeanList.getItemsKey();//36????????????????????????

        signPageYear = mpeopleBasebean.year;//????????????
        servicePlanId = mPendExecuBeanList.getServicePlanID();//????????????????????????????????????????????????????????????????????????????????????
        signDetailKey = mPendExecuBeanList.getSignDetailID();//????????????key

        serviceContentId = mPendExecuBeanList.getServiceContentKey();   //???????????????id
        viewgKey = "";          //????????????
        serverDT = et_execu_serverdate.getText().toString(); //9????????????

        byServiceUserName = mpeopleBasebean.getUserName();//????????????
        feedBackOpinion = "";           //????????????
        feedBackDT = "";            //????????????
     //   nextServerDT = et_execu_nextdate.getText().toString();   //??????????????????????????????????????????????????????????????????????????????????????????
        doctorName = mpeopleBasebean.doctorName;//??????????????????
    //    doctorCode = Identity.info.getGuserid();//????????????code
        doctorCode = Identity.getUserId();//????????????code
        doctorPhone = mpeopleBasebean.getPhone();//??????????????????

     //   addUserName = Identity.info.getSusername(); //17???????????????
      //  addOrgId = Identity.info.getSorganizationkey();  //19????????????id
        addUserName = IdentityManager.getLoginSuccessUsername(this); //17???????????????
        addOrgId = Identity.getOrganizationKey();  //19????????????id

        addDT = sNowDate;   //18????????????

    //    updateUserName = Identity.info.getSusername();//??????????????? (??????????????????????????????)
     //   updateOrgId = Identity.info.getSorganizationkey();  //????????????id (??????????????????????????????)
        updateUserName = Identity.getUsername();//??????????????? (??????????????????????????????)
        updateOrgId = Identity.getOrganizationKey();  //????????????id (??????????????????????????????)
        updateDT = sNowDate;   //???????????? (??????????????????????????????)
     //   docUserID = Identity.info.getGuserid();//????????????id
        docUserID = Identity.getUserId();//????????????id

        if (Identity.info.getSorganizationlevelcode().equals("3")) {
            townDeptCode = Identity.info.getSorganizationtypecode();//??????????????????
        } else {
            townDeptCode = "";//24??????????????????
        }

     //   docOrganizationKey = Identity.info.getSorganizationkey();//27????????????????????????
    //    docOrganizationName = Identity.info.getSorganizationname();//28????????????????????????
        docOrganizationKey = Identity.getOrganizationKey();//27????????????????????????
        docOrganizationName = Identity.getOrganizationName();//28????????????????????????

        checkDate = "";//????????????
        doctorSignKey = mpeopleBasebean.getKey();//33??????????????????
        signFilingStatue = mPendExecuBeanList.getFinished();//34???????????????????????? ???0???????????????1???????????????


        shouldExecTimes = mPendExecuBeanList.getShouldExecTimes();//???????????????
        hadExecTimes = mPendExecuBeanList.getHadExecTimes();//?????????????????????
        execTimes = mPendExecuBeanList.getExecTime();//????????????????????????
        edit = "2";//???????????????????????????1??????????????????2??????????????????3?????????????????????

        myServiceContentItemList.clear();
        //??????????????????LIst
        SaveSignServiceContentItemListBeans mySaveSignServiceContentItemList = new SaveSignServiceContentItemListBeans();
        mySaveSignServiceContentItemList.setHadExecTimes(Util.getBase64String(mPendExecuBeanList.getHadExecTimes()));//????????????
        mySaveSignServiceContentItemList.setKey(Util.getBase64String(mPendExecuBeanList.getServiceContentKey()));//??????(xx-xx-xx-xx-xx) UUID  ????????? (D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
        mySaveSignServiceContentItemList.setShouldExecTimes(Util.getBase64String(mPendExecuBeanList.getShouldExecTimes()));//??????????????????

        myServiceContentItemList.add(mySaveSignServiceContentItemList);

        execued();
           }



    private void getCoreIndexInfo(){
        if(mModifyExecuedBeanList.size()==0){
            /*isEmpty="2";
            myCoreIndexItemList.clear();
            serviceContentDesc=et_execu_serviceinfo.getText().toString();*/

            myCoreIndexItemList.clear();
            if(TextUtils.isEmpty(et_execu_serviceinfo.getText().toString())){
                Toast.makeText(getApplicationContext(),"????????????????????????!",Toast.LENGTH_SHORT).show();
                isEmpty="1";

            }else{
                serviceContentDesc=et_execu_serviceinfo.getText().toString();
                isEmpty="2";
            }
        }else{

            for(int i=0;i<mModifyExecuedBeanList.size();i++){
                if(mModifyExecuedBeanList.get(i).getSrgs().equals("001")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getHad1())){
                        Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
                         isEmpty="1";
                        break;
                    }else{
                        isEmpty="2";
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getHad1()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getHad1()+mModifyExecuedBeanList.get(i).getUnit()+";";

                    }
                }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("002")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getAbnormal())){
                        Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
                        isEmpty="1";
                        break;
                    }else{
                        isEmpty="2";
                     //   getCoreIndexList();
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getAbnormal()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getAbnormal()+mModifyExecuedBeanList.get(i).getUnit()+";";
                    }
                }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("003")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getPlus3())){
                        Toast.makeText(getApplicationContext(),"????????????-??? +??? ??????",Toast.LENGTH_SHORT).show();
                        isEmpty="1";
                        break;
                    }else{
                      //  getCoreIndexList();
                        isEmpty="2";
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getPlus3()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getPlus3()+mModifyExecuedBeanList.get(i).getUnit()+";";
                    }
                }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("004")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getPlusplus4())){
                        Toast.makeText(getApplicationContext(),"????????????- ???+??? ++???+++ ??? ??????",Toast.LENGTH_SHORT).show();
                        isEmpty="1";
                        break;
                    }else{
                        isEmpty="2";
                       // getCoreIndexList();
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getPlusplus4()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getPlusplus4()+mModifyExecuedBeanList.get(i).getUnit()+";";
                    }
                }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("005")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getInfo5())){
                        Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_SHORT).show();
                        isEmpty="1";
                        break;
                    }else{
                        isEmpty="2";
                      //  getCoreIndexList();
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getInfo5()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getInfo5()+mModifyExecuedBeanList.get(i).getUnit()+";";
                    }
                }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("006")){
                    if(TextUtils.isEmpty(mModifyExecuedBeanList.get(i).getInfo())){
                        Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_SHORT).show();
                        isEmpty="1";
                        break;
                    }else {
                        isEmpty="2";
                      //  getCoreIndexList();
                        SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                        myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                        myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                        myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                        myItemTargetsoList.setOther("");//??????

                        myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                        myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                        myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                        myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                        myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                        myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                        myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getInfo()));//???
                        myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                        myCoreIndexItemList.add(myItemTargetsoList);

                        serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                +mModifyExecuedBeanList.get(i).getInfo()+mModifyExecuedBeanList.get(i).getUnit()+";";
                    }
                }else{
                  /*  myCoreIndexItemList.clear();
                    serviceContentDesc=et_execu_serviceinfo.getText().toString();
                    getCoreIndexList();*/
                }
            }




        }

    }

           private void getCoreIndexInfo2(){
               if(mModifyExecuedBeanList.size()==0){
            /*isEmpty="2";
            myCoreIndexItemList.clear();
            serviceContentDesc=et_execu_serviceinfo.getText().toString();*/

                   myCoreIndexItemList.clear();
                   if(TextUtils.isEmpty(et_execu_serviceinfo.getText().toString())){
                     //  Toast.makeText(getApplicationContext(),"????????????????????????!",Toast.LENGTH_SHORT).show();
                       isEmpty="2";
                       serviceContentDesc="";

                   }else{
                       serviceContentDesc=et_execu_serviceinfo.getText().toString();
                       isEmpty="2";
                   }
               }else{

                   for(int i=0;i<mModifyExecuedBeanList.size();i++){
                       if(mModifyExecuedBeanList.get(i).getSrgs().equals("001")){

                               isEmpty="2";
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getHad1()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getHad1()+mModifyExecuedBeanList.get(i).getUnit()+";";


                       }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("002")){

                               isEmpty="2";
                               //   getCoreIndexList();
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getAbnormal()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getAbnormal()+mModifyExecuedBeanList.get(i).getUnit()+";";

                       }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("003")){

                               //  getCoreIndexList();
                               isEmpty="2";
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getPlus3()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getPlus3()+mModifyExecuedBeanList.get(i).getUnit()+";";

                       }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("004")){

                               isEmpty="2";
                               // getCoreIndexList();
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getPlusplus4()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getPlusplus4()+mModifyExecuedBeanList.get(i).getUnit()+";";

                       }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("005")){

                               isEmpty="2";
                               //  getCoreIndexList();
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getInfo5()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getInfo5()+mModifyExecuedBeanList.get(i).getUnit()+";";

                       }else if(mModifyExecuedBeanList.get(i).getSrgs().equals("006")){

                               isEmpty="2";
                               //  getCoreIndexList();
                               SaveItemTargetsoListBeans myItemTargetsoList = new SaveItemTargetsoListBeans();
                               myItemTargetsoList.setPackageExecutiveKey(Util.getBase64String(mModifyExecuedBeanList.get(i).packageExecutiveKey));//?????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setKey(Util.getBase64String(mModifyExecuedBeanList.get(i).key));//????????????????????????????????????????????????????????????????????????
                               myItemTargetsoList.setYtjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).ytjSource));//????????????????????????
                               myItemTargetsoList.setLisjSource(Util.getBase64String(mModifyExecuedBeanList.get(i).lisjSource));//LIS?????????
                               myItemTargetsoList.setOther("");//??????

                               myItemTargetsoList.setSrgs( Util.getBase64String(mModifyExecuedBeanList.get(i).srgs));//????????????
                               myItemTargetsoList.setTargetsNo(Util.getBase64String(mModifyExecuedBeanList.get(i).targetsNo));//??????
                               myItemTargetsoList.setServiceItemTargetsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getKey()));//????????????????????????
                               myItemTargetsoList.setServiceItemsKey(Util.getBase64String(mModifyExecuedBeanList.get(i).getServiceItemsKey()));//????????????key
                               myItemTargetsoList.setServiceContentItemsID(Util.getBase64String(mPendExecuBeanList.getServiceContentItemsKey()));//????????????key
                               myItemTargetsoList.setUnit(Util.getBase64String(mModifyExecuedBeanList.get(i).getUnit()));//??????
                               myItemTargetsoList.setValue(Util.getBase64String(mModifyExecuedBeanList.get(i).getInfo()));//???
                               myItemTargetsoList.setZbmc(Util.getBase64String(mModifyExecuedBeanList.get(i).getZbmc()));
                               myCoreIndexItemList.add(myItemTargetsoList);

                               serviceContentDesc=serviceContentDesc+mModifyExecuedBeanList.get(i).getZbmc()+":"
                                       +mModifyExecuedBeanList.get(i).getInfo()+mModifyExecuedBeanList.get(i).getUnit()+";";

                       }else{
                  /*  myCoreIndexItemList.clear();
                    serviceContentDesc=et_execu_serviceinfo.getText().toString();
                    getCoreIndexList();*/
                       }
                   }




               }

           }

    @Override
    protected void loadData() {
        mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");

        mPendExecuBeanList= (PendExecuBeanList) this.getIntent().getSerializableExtra("PendExecuBeanList");

        ServiceContentItemsKey = mPendExecuBeanList.getServiceContentItemsKey();

        tv_execu_name.setText(mpeopleBasebean.getUserName());
        tv_execu_doctor.setText(mpeopleBasebean.getDoctorName());
        tv_execu_project.setText(mPendExecuBeanList.getServiceItemsName());

       // String nextDt = mpeopleBasebean.getNextServerDT();
       // nextDt =nextDt.substring(10)

        et_execu_nextdate.setText(mpeopleBasebean.getNextServerDT());

       /* Map<String, String> map = ParametersFactory.getCoreIndexList(ServiceContentItemsKey);
        RequestBody body = Util.getBodyFromMap(map);
        coreindexpresenter.main(body);*/

        info_pend ="";
        info_had ="1";
        rb_info1.setChecked(true);

        String getServicePlanID = mPendExecuBeanList.getServicePlanID();
        String year =mpeopleBasebean.getYear();
        Map<String, String> map = ParametersFactory.getSignExecProDetail(this, getServicePlanID,year);
        RequestBody body = Util.getBodyFromMap1(map);
        coreindexpresenter.mainmodify(body);

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
                       //     Toast.makeText(getApplicationContext(),"??????:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress2:
                       rg_adress2.clearCheck();

                       address_shangmen="";
                       address_cunsi="2";
                       address_xiangz="";
                       address_other="";
                       //    Toast.makeText(getApplicationContext(), "??????"+address_cunsi, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress3:
                       rg_adress2.clearCheck();
                       address_shangmen="";
                       address_cunsi="";
                       address_xiangz="3";
                       address_other="";
                       //      Toast.makeText(getApplicationContext(), "??????"+address_xiangz, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress4:
                       rg_adress1.clearCheck();

                       address_shangmen="";
                       address_cunsi="";
                       address_xiangz="";
                       address_other="4";
                       //      Toast.makeText(getApplicationContext(), "??????"+address_other, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_reason1:
                       rg_reason2.clearCheck();
                       break;
                   case R.id.rb_reason2:
                       rg_reason2.clearCheck();
                       break;
                   case R.id.rb_reason3:
                       rg_reason1.clearCheck();
                       break;
         /*   case R.id.rb_info2:
             ll_pend_execu.setVisibility(View.VISIBLE);
             break;
             case R.id.rb_info1:
                 ll_pend_execu.setVisibility(View.GONE);
                 break;*/

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
                    //   Toast.makeText(getApplicationContext(),"??????:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress2:
                       address_shangmen="";
                       address_cunsi="2";
                       address_xiangz="";
                       address_other="";
                       et_execu_addressother.setVisibility(View.GONE);
                   //    Toast.makeText(getApplicationContext(), "??????"+address_cunsi, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress3:

                       address_shangmen="";
                       address_cunsi="";
                       address_xiangz="3";
                       address_other="";
                       et_execu_addressother.setVisibility(View.GONE);
                    //   Toast.makeText(getApplicationContext(), "??????"+address_xiangz, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_adress4:
                       address_shangmen="";
                       address_cunsi="";
                       address_xiangz="";
                       address_other="4";
                       et_execu_addressother.setVisibility(View.VISIBLE);
                   //    Toast.makeText(getApplicationContext(), "??????"+address_other, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_reason1:
                       reason_doctor="1";
                       reason_resident="";
                       reason_other="";
                       et_reason_other.setVisibility(View.GONE);
               //        Toast.makeText(getApplicationContext(), "????????????"+reason_doctor, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_reason2:
                       reason_doctor="";
                       reason_resident="2";
                       reason_other="";
                       et_reason_other.setVisibility(View.GONE);
                     //  Toast.makeText(getApplicationContext(), "????????????"+reason_resident, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_reason3:
                       reason_doctor="";
                       reason_resident="";
                       reason_other="3";
                       et_reason_other.setVisibility(View.VISIBLE);
                  //     Toast.makeText(getApplicationContext(), "????????????"+reason_other, Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rb_info1:
                       info_had="1";
                       info_pend="";
                       ll_pend_execu.setVisibility(View.GONE);
                    //   Toast.makeText(getApplicationContext(), "?????????"+info_had, Toast.LENGTH_SHORT).show();

                       break;
                   case R.id.rb_info2:
                       info_had="";
                       info_pend="2";
                       ll_pend_execu.setVisibility(View.VISIBLE);
                    //   Toast.makeText(getApplicationContext(), "?????????"+info_pend, Toast.LENGTH_SHORT).show();

                       break;

                   default:
                       break;
               }
           }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addmodifycoreinddex(new CoreIndexActivityModule(this))
                .inject(this);

    }

    @Override
    public void onModifyExecuServeritemSuccess(HttpResultBaseBean<ModifyExecuedBean> bean) {
               Util.hideGifProgressDialog(this);
               if (bean!=null) {

                   modifyexecuedbean = bean.getData();
                   tv_execu_name.setText(modifyexecuedbean.getByServiceUserName());
                   tv_execu_doctor.setText(modifyexecuedbean.getDoctorName());
                //   et_execu_serverdate.setText(modifyexecuedbean.getServerDT());

                   String serviceDt =modifyexecuedbean.getServerDT();


                    String nextDt =modifyexecuedbean.getNextServerDT();

                   if(TextUtils.isEmpty(nextDt)){
                       et_execu_nextdate.setText("");
                   }else{
                       nextDt =nextDt.substring(0,10);
                       et_execu_nextdate.setText(nextDt);
                   }


                   if(TextUtils.isEmpty(serviceDt) ){
                       et_execu_serverdate.setText("");
                   }else{
                       serviceDt =serviceDt.substring(0,10);
                       et_execu_serverdate.setText(serviceDt);
                   }



                   String serverPlaceType = modifyexecuedbean.getServerPlaceType();
                   if(serverPlaceType.equals("1")){
                       rb_adress1.setChecked(true);
                   }else if(serverPlaceType.equals("2")){
                       rb_adress2.setChecked(true);
                   }else if(serverPlaceType.equals("3")){
                       rb_adress4.setChecked(true);
                       et_execu_addressother.setText(modifyexecuedbean.getServerPlaceOther());
                   }else if(serverPlaceType.equals("4")){
                       rb_adress3.setChecked(true);
                   }

                   String isExecute  = modifyexecuedbean.getIsExecute();
                   if(isExecute.equals("1")){
                       ll_pend_execu.setVisibility(View.GONE);
                       rb_info1.setChecked(true);
                   }else if(isExecute.equals("2")){
                       rb_info2.setChecked(true);
                       ll_pend_execu.setVisibility(View.VISIBLE);
                       String isRefused =modifyexecuedbean.getIsRefused();
                       if(isRefused.equals("1")){
                           rb_reason1.setChecked(true);
                       }else if(isRefused.equals("2")){
                           rb_reason2.setChecked(true);
                       }else if(isRefused.equals("3")){
                           rb_reason3.setChecked(true);
                           et_reason_other.setText(modifyexecuedbean.getNoExecuteRemark());
                       }
                   }


                   List<ModifyExecuedBeanList> list = bean.getData().signDoctorItemhxzbOutVoList;
                       if (list==null || (list.size()==0)) {
                           lv_server_situation.setVisibility(View.GONE);
                           et_execu_serviceinfo.setVisibility(View.VISIBLE);
                           et_execu_serviceinfo.setText(modifyexecuedbean.getServiceContentDesc());
                       }else{
                           showModifyCoreIndexview(list);
                       }


/*
                   mModifyExecuedBeanList.clear();
                   mModifyExecuedBeanList =modifyexecuedbean.signDoctorItemhxzbOutVoList;
              //     String s1 =modifyExecuedBeanList.get(0).getKey();
               //    String s2 =modifyExecuedBeanList.get(0).getSrgs();
                   if (mModifyExecuedBeanList!=null) {

                           showModifyCoreIndexview(mModifyExecuedBeanList);

                   }*/

               }
           }

           @Override
           public void onLoadModifyExecuServeritemFailure(String message) {
               if(message!=null){

               }

           }

           @Override
           public void onDeleteExecuServeritemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
               Util.hideGifProgressDialog(this);
               if(bean!=null){
                   String msg = Util.getMessageFromHttpResponse(bean);
                   Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                   finish();
               }
           }

           @Override
           public void onLoadDeleteExecuServeritemFailure(String message) {
               Util.hideGifProgressDialog(this);
               if(message!=null){
                   Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
               }
           }

           @Override
    public void onCoreIndexSuccess(HttpResultBaseBean<List<CoreIndexBeanList>> bean) {

        Util.hideGifProgressDialog(this);
       // Util.showToast(this, "????????????");

      /*  String s1 =bean.getSuccess();
        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_SHORT).show();*/

      /*  if (bean!=null) {
            List<CoreIndexBeanList> list = bean.getData();
            if (list!=null) {
                showSignInfoOverview(list);
            }
        }*/

    }

    public void showSignInfoOverview(List<CoreIndexBeanList> list) {
       /* if (list != null) {
            if (!ListUtils.isEmpty(list)) {

               for(int i=0;i<list.size();i++){
                   mListCoreIndexBeanList.add(list.get(i));
               }
               if(TextUtils.isEmpty(mListCoreIndexBeanList.get(0).getSrgs())){
                   lv_server_situation.setVisibility(View.GONE);
                   et_execu_serviceinfo.setVisibility(View.VISIBLE);
               }else{
                   lv_server_situation.setVisibility(View.VISIBLE);
                   et_execu_serviceinfo.setVisibility(View.GONE);
                   adapter = new ServerSituationAdapter(this, mListCoreIndexBeanList);
                   lv_server_situation.setAdapter(adapter);
                   setListViewHeightBasedOnChildren(lv_server_situation);
               }

            }
        }*/
    }


           private void showModifyCoreIndexview(List<ModifyExecuedBeanList> mymodifylist) {
               if (mymodifylist != null) {

                      mModifyExecuedBeanList.clear();


                       for(int i=0;i<mymodifylist.size();i++){
                           mModifyExecuedBeanList.add(mymodifylist.get(i));
                       }

                       if(TextUtils.isEmpty(mModifyExecuedBeanList.get(0).getSrgs())){
                           lv_server_situation.setVisibility(View.GONE);
                           et_execu_serviceinfo.setVisibility(View.VISIBLE);
                       }else{
                           lv_server_situation.setVisibility(View.VISIBLE);
                           et_execu_serviceinfo.setVisibility(View.GONE);
                           modifyAdapter = new ModifyServerSituationAdapter(this, mModifyExecuedBeanList);
                           lv_server_situation.setAdapter(modifyAdapter);
                           setListViewHeightBasedOnChildren(lv_server_situation);
                       }


               }
           }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
               if(listView == null) return;

               ListAdapter listAdapter = listView.getAdapter();
               if (listAdapter == null) {
                   // pre-condition
                   return;
               }

               int totalHeight = 0;
               for (int i = 0; i < listAdapter.getCount(); i++) {
                   View listItem = listAdapter.getView(i, null, listView);
                   listItem.measure(0, 0);
                   totalHeight += listItem.getMeasuredHeight();
               }

               ViewGroup.LayoutParams params = listView.getLayoutParams();
               params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
               listView.setLayoutParams(params);
           }




    @Override
    public void onLoadMoreCoreIndexSuccess(HttpResultBaseBean<List<CoreIndexBeanList>> bean) {

    }

    @Override
    public void onLoadCoreIndexFailure(String message) {

        Util.hideGifProgressDialog(this);
        lv_server_situation.setVisibility(View.GONE);
        et_execu_serviceinfo.setVisibility(View.VISIBLE);
    }



    @Override
    public void onSaveExecuServeritemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

        Util.hideGifProgressDialog(this);
        if(bean!=null){
            RxBus.getDefault().post(Constants.EVENT_TYPE_REFRESH_INTELLIGENT_HONOUR_AGREEMENT_REMIND, true);
            String msg = Util.getMessageFromHttpResponse(bean);
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onLoadSaveExecuServeritemFailure(String message) {
        Util.hideGifProgressDialog(this);
        if(message!=null){
          Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }
    }




           public class User {

        //????????????????????????
        private String item1_str;
        //????????????????????????
        private String item2_str;
        //????????????????????????
        private String item3_str;

        private String item4_str;
        private String item5_str;
        private String item6_str;

        public User(String item1_str, String item2_str, String item3_str, String item4_str, String item5_str, String item6_str) {
            this.item1_str = item1_str;
            this.item2_str = item2_str;
            this.item3_str = item3_str;
            this.item4_str = item4_str;
            this.item5_str = item5_str;
            this.item6_str = item6_str;
        }


        public String getItem1_str() {
            return item1_str;
        }

        public String getItem2_str() {
            return item2_str;
        }

        public String getItem3_str() {
            return item3_str;
        }

        public String getItem4_str() {
            return item4_str;
        }

        public String getItem5_str() {
            return item5_str;
        }

        public String getItem6_str() {
            return item6_str;
        }
    }


    public String getDeliveredStringByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return Constants.EMPTY_STRING;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return Constants.EMPTY_STRING;
            } else {
                String result = intent.getStringExtra(key);
                return result;
            }
        }
    }

           public void registerProjectExecutionClickEvent() {
               Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM, ExecutionProjectsResultItemBean.class)
                       .subscribe(new Action1<ExecutionProjectsResultItemBean>() {
                           @Override
                           public void call(ExecutionProjectsResultItemBean bean) {
                          //     handleExecutionButtonClick(bean);
//                        Util.showAlert(ExecutionProjectsActivity.this, "??????", "??????????????????" + bean.getServiceContentID());
                           }
                       });
               if (this.compositeSubscription == null) {
                   compositeSubscription = new CompositeSubscription();
               }
               compositeSubscription.add(mSubscription);
           }

           public void unregisterProjectExecutionClickEvent() {
               if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
                   compositeSubscription.unsubscribe();
               }
           }

           @Override
           protected void onDestroy() {
               super.onDestroy();
               unregisterProjectExecutionClickEvent();
           }

       }
