package com.jqsoft.fingertip_health.di.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HbThreeAdapter;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HbPostBean;
import com.jqsoft.fingertip_health.bean.HighBloodFragmentBean;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.di.contract.HighBloodFragmentContract;
import com.jqsoft.fingertip_health.di.contract.PeopleSignInfoFragmentContract;
import com.jqsoft.fingertip_health.di.module.HighBloodFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.HbFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.optionlayout.NecessityNameOptionsNewLayout;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//import com.jqsoft.launcher.service.activity.bean.YongyaoBean;


public class HBFragment3 extends AbstractFragment implements
        HighBloodFragmentContract.View{
    private static String gKey;// sInputDeptCode);//

    private List<UseDrugInfo> sMedicalList = new ArrayList<>();
    private String iReadCardState,sUpdateUserCode;

    private Dialog mDialog;
    private View rootView;
    View failureView;
    private HbThreeAdapter adapter;
    public static final int REQUEST_A = 1;
    private String sMessage;
    private LinearLayout gxy_submit;
    private NecessityNameOptionsNewLayout ishealthedu,ly_isReferral;
    private EditText edit_ransferreatment, edit_transfertreatmentdept, edit_visitingdoctor;
    private String content;
    private HbPostBean hbPostBean1,hbPostBean2;
    private HBPGuanLi_PersonInfo personInfo;
    RecyclerView recyclerView;
    private ArrayList<UseDrugInfo> datalist = new ArrayList<UseDrugInfo>();
    private CompositeSubscription mInitializeSubscription;
    private  LinearLayout add_drug;
    TextView edit_nextvisitingdate;
    ScrollView sv_content;
    @Inject
    HbFragmentPresenter mPresenter;
    private  LinearLayout ll_transfertreatmentdept,ll_ransferreatment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void registerInitializeSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constant.ENENT_BW, RxBusBaseMessage.class).subscribe(new Action1<RxBusBaseMessage>() {
            @Override
            public void call(RxBusBaseMessage indexAndOnlineSignInitialData) {
                if (indexAndOnlineSignInitialData != null) {

                    switch (indexAndOnlineSignInitialData.getCode()){
                        case 1:
                            ll_transfertreatmentdept.setVisibility(View.VISIBLE);
                            ll_ransferreatment.setVisibility(View.VISIBLE);
                            edit_ransferreatment.setHint("请输入转诊原因");
                            edit_transfertreatmentdept.setHint("请输入转诊机构及科别");
//                            edit_ransferreatment.setEnabled(true);
//                            edit_transfertreatmentdept.setEnabled(true);
                        break;
                        case 2:
                            ll_ransferreatment.setVisibility(View.GONE);
                            ll_transfertreatmentdept.setVisibility(View.GONE);
//                            edit_ransferreatment.setHint("无需输入");
//                            edit_transfertreatmentdept.setHint("无需输入");
                            edit_ransferreatment.setText("");
                            edit_transfertreatmentdept.setText("");
//                            edit_ransferreatment.setEnabled(false);
//                            edit_transfertreatmentdept.setEnabled(false);
                            break;

                    }

                }

            }
        });
        if (mInitializeSubscription == null) {
            mInitializeSubscription = new CompositeSubscription();
        }
        mInitializeSubscription.add(subscription);
    }

    private void unregisterInitializeSubscription() {
        if (mInitializeSubscription != null && mInitializeSubscription.hasSubscriptions()) {
            mInitializeSubscription.unsubscribe();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterInitializeSubscription();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hb_three_layout;
    }

    @Override
    protected void initData() {
        registerInitializeSubscription();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    public String getgKey() {
        return gKey;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hb_three_layout, container, false);
        HighBloodActivity activity = (HighBloodActivity) getActivity();
        ll_transfertreatmentdept=(LinearLayout)view.findViewById(R.id.ll_transfertreatmentdept);
        ll_ransferreatment=(LinearLayout)view.findViewById(R.id.ll_ransferreatment);
        personInfo = activity.getHbpGuanLi_personInfo();
        iReadCardState = activity.getiReadCardState();
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);
        ly_isReferral=(NecessityNameOptionsNewLayout)view.findViewById(R.id.isReferral);
     //   sUpdateUserCode =activity.getsUpdateUserCode();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView) ;
        edit_ransferreatment = (EditText) view.findViewById(R.id.edit_ransferreatment);
        edit_transfertreatmentdept = (EditText) view.findViewById(R.id.edit_transfertreatmentdept);
        edit_nextvisitingdate = (TextView) view.findViewById(R.id.nextvisitingdate);
        edit_visitingdoctor = (EditText) view.findViewById(R.id.edit_visitingdoctor);
        edit_visitingdoctor.setText(DaggerApplication.getInstance().getRealName());
        gxy_submit = (LinearLayout) view.findViewById(R.id.gxy_submit);
        ishealthedu = (NecessityNameOptionsNewLayout) view.findViewById(R.id.ishealthedu);
        gKey = UUID.randomUUID().toString();

        add_drug=(LinearLayout)view.findViewById(R.id.add_drug);

        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        adapter = new HbThreeAdapter(new ArrayList<UseDrugInfo>(), getContext());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        datalist.add(new UseDrugInfo("","每日一次","","mg",""));

        adapter.setNewData(datalist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        List<NameValueBean> genderListisReferral = new ArrayList<>();
        genderListisReferral.add(new NameValueBean("是", "1", false));
        genderListisReferral.add(new NameValueBean("否", "2", false));
        ly_isReferral.setDataList(genderListisReferral);

        add_drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalist.add(new UseDrugInfo("","每日一次","","mg",""));



                adapter.notifyDataSetChanged();
            }

        });


        Util.setViewListener(edit_nextvisitingdate, new Runnable() {
            @Override
            public void run() {
                Calendar maxDate = Calendar.getInstance();
                maxDate.setTime(new Date());
                Util.showDateNewDialogWithMaxDate1(getActivity(), edit_nextvisitingdate.getText().toString(), "nnilBirthday",
                        maxDate,
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                                edit_nextvisitingdate.setText(s);
                            }
                        } ,false);
            }
        });

        gxy_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HighBloodActivity activity = (HighBloodActivity) getActivity();
                ViewPager viewPager = activity.getVpContent();
                activity.getHbFragment1().getdata();
                if (viewPager.getCurrentItem() == 2) {
                    hbPostBean1 = activity.getHbFragment1().getdata();
//                    setwebData(fragment01list);
                    activity.getHbFragment2().getdata();
                    if (viewPager.getCurrentItem() == 2) {
                        if (TextUtils.isEmpty(edit_visitingdoctor.getText())) {
                            viewPager.setCurrentItem(2);
                            sv_content.smoothScrollTo(0, edit_visitingdoctor.getTop());
                            Toast.makeText(getActivity(), "请输入随访医生签名!", Toast.LENGTH_SHORT).show();
                        }else if (TextUtils.isEmpty(edit_nextvisitingdate.getText())) {
                            viewPager.setCurrentItem(2);
                            sv_content.smoothScrollTo(0, edit_nextvisitingdate.getTop());
                            Toast.makeText(getActivity(), "请选择下次随访时间!", Toast.LENGTH_SHORT).show();
                        }else {

                            hbPostBean2 = activity.getHbFragment2().getdata();

                            hbPostBean1.setDoctor(edit_visitingdoctor.getText().toString());


                            hbPostBean1.setDoctorCode(DaggerApplication.getInstance().getUsername());
                            hbPostBean1.setDoctorCode(DaggerApplication.getInstance().getUsername());
                            hbPostBean1.setExercise(hbPostBean2.getExercise());
                            hbPostBean1.setNextExercise(hbPostBean2.getNextExercise());
                            hbPostBean1.setExerciseTimes(hbPostBean2.getExerciseTimes());
                            hbPostBean1.setNextExerciseTimes(hbPostBean2.getNextExerciseTimes());
                            hbPostBean1.setPsychic(hbPostBean2.getPsychic());
                            hbPostBean1.setAccessoryExamination(hbPostBean2.getAccessoryExamination());
                            hbPostBean1.setDrugCompliance( hbPostBean2.getDrugCompliance());
                            hbPostBean1.setPatientCompliance(hbPostBean2.getPatientCompliance());
                            hbPostBean1.setAdverseReaction(hbPostBean2.getAdverseReaction());
                            hbPostBean1.setOtherAdverseReaction(hbPostBean2.getOtherAdverseReaction());
                            hbPostBean1.setSort(hbPostBean2.getSort());
                            hbPostBean1.setDailySmoke(hbPostBean2.getDailySmoke());
                            hbPostBean1.setDailySmokeTarget(hbPostBean2.getDailySmokeTarget());
                            hbPostBean1.setDailyDrink(hbPostBean2.getDailyDrink());
                            hbPostBean1.setDailyDrinkTarget(hbPostBean2.getDailyDrinkTarget());
                            hbPostBean1.setSaltUptake(hbPostBean2.getSaltUptake());
                            hbPostBean1.setSaltUptakeTarget(hbPostBean2.getSaltUptakeTarget());


                            hbPostBean1.setNextFlwDate(edit_nextvisitingdate.getText().toString());
                            activity=(HighBloodActivity)getActivity();
                            HighBloodListActivityBean bean= activity.getHighBloodListActivityBean();
                            String  taskId=hbPostBean1.getTaskId();
                            String  name=bean.getName();
                            String  id=bean.getId();
                            String  no=bean.getNo();
                            String  doctor=hbPostBean1.getDoctor();
                            String  idnum=bean.getIdNo();

                            String  flwDate=hbPostBean1.getFlwDate();
                            String  flwType=checknull(hbPostBean1.getFlwType());
                            String  followDownReason=hbPostBean1.getFollowDownReason();
                            String  symptom=hbPostBean1.getSymptom();
                            String  otherSymptom=hbPostBean1.getOtherSymptom();
                            String  sbp=checknull(hbPostBean1.getSbp());
                            String  dbp=checknull(hbPostBean1.getDbp());
                            String  heartRate=checknull(hbPostBean1.getHeartRate());
                            String  height=checknull(hbPostBean1.getHeight());
                            String  weight=checknull(hbPostBean1.getWeight());
                            String  weightTarget=checknull(hbPostBean1.getWeightTarget());
                            String  bmi=checknull(hbPostBean1.getBmi());
                            String  bmiTarget=checknull(hbPostBean1.getBmiTarget());
                            String  otherSign=hbPostBean1.getOtherSign();
                            String  dailySmoke=checknull( hbPostBean1.getDailySmoke()) ;
                            String  psychic=checknull(hbPostBean1.getPsychic());
                            String  patientCompliance=checknull(hbPostBean1.getPatientCompliance());
                            String  accessoryExamination=hbPostBean1.getAccessoryExamination();
                            String  drugCompliance=checknull(hbPostBean1.getDrugCompliance());
                            String  adverseReaction=checknull(hbPostBean1.getAdverseReaction());
                            String  otherAdverseReaction=hbPostBean1.getOtherAdverseReaction();
                            String  sort=checknull(hbPostBean1.getSort());
                            String  nextFlwDate=hbPostBean1.getNextFlwDate();
                            String  doctorCode=hbPostBean1.getDoctorCode();
                            String exercise=hbPostBean1.getExercise();
                            String exerciseTimes=hbPostBean1.getExerciseTimes();
                            String nextExercise=hbPostBean1.getNextExercise();
                            String nextExerciseTimes=hbPostBean1.getNextExerciseTimes();
                            String isReferral=checknull(ly_isReferral.getSingleSelectValue());
                            String  referralReason=edit_ransferreatment.getText().toString();
                            String  referralHosp = edit_transfertreatmentdept.getText().toString();
                            String dailySmokeTarget=hbPostBean1.getDailySmokeTarget();
                            String dailyDrink=hbPostBean1.getDailyDrink();
                            String dailyDrinkTarget=hbPostBean1.getDailyDrinkTarget();
                            String saltUptake =hbPostBean1.getSaltUptake();
                            String  saltUptakeTarget=hbPostBean1.getSaltUptakeTarget();
                            String  pp=hbPostBean1.getPatientCompliance();
                            String  personid=bean.getPersonId();

//                      , String saltUptake,String saltUptakeTarget
//                       , saltUptake,saltUptakeTarget


                            Map<String, String> map = ParametersFactory.postHighBloodData(getActivity(),taskId, name, id, no, flwDate, flwType, followDownReason, symptom, otherSymptom, sbp, dbp,
                                    heartRate, height, weight, weightTarget, bmi, bmiTarget, otherSign, dailySmoke, psychic, patientCompliance,
                                    accessoryExamination, drugCompliance, adverseReaction, otherAdverseReaction, sort, nextFlwDate, doctorCode,datalist,
                                    exercise,exerciseTimes,nextExercise,nextExerciseTimes,isReferral,referralReason,referralHosp,
                                    dailySmokeTarget,dailyDrink,dailyDrinkTarget , saltUptake,saltUptakeTarget,pp,personid,doctor,idnum
                            ) ;

                            mPresenter.postdate(map);
                        }




                    }


                }

            }
        });

        return view;

    }
    public String checknull(String str){
        if (TextUtils.isEmpty(str)){
            return "0";
        }
        return str;
    }
    public void removeDate(int postion) {

        adapter.remove(postion);
        adapter.notifyDataSetChanged();


    }
    public void setDate(int postion, String value, String type) {
        if (TextUtils.isEmpty(value)||datalist.size()==0||datalist.size()<postion+1) {
        } else {
            switch (type) {
                case "药名":
                    datalist.get(postion).setName(value);


                    break;
                case "频次":
                    datalist.get(postion).setFrequency(value);

                    break;
                case "单次剂量":
                    datalist.get(postion).setSingleDose(value);

                    break;
                case "单位":

                    datalist.get(postion).setUnit(value);

                    break;

            }

        }


    }





    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addHighBloodFragment(new HighBloodFragmentModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        Toast.makeText(getContext(),"保存成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        Toast.makeText(getContext(),"保存成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(getContext(),"保存失败！"+message,Toast.LENGTH_SHORT).show();
    }
}
