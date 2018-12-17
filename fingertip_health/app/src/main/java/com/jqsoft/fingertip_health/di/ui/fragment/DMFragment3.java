package com.jqsoft.fingertip_health.di.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.DmThreeAdapter;
import com.jqsoft.fingertip_health.adapter.HbThreeAdapter;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DmPostBean;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HighBloodFragmentBean;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.di.contract.DmFragmentContract;
import com.jqsoft.fingertip_health.di.module.DMFragmentModule;
import com.jqsoft.fingertip_health.di.module.HighBloodFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.DmFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.DiabetesMellitusActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.optionlayout.NecessityNameOptionsNewLayout;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//import com.jqsoft.launcher.service.activity.bean.YongyaoBean;


public class DMFragment3 extends AbstractFragment implements DmFragmentContract.View{
    private static String gKey;// sInputDeptCode);//
    private TextView edit_nextvisitingdate;
    private List<UseDrugInfo> sMedicalList = new ArrayList<>();
    private String iReadCardState,sUpdateUserCode;
    private DmPostBean dmPostBean1,dmPostBean2;
    private Dialog mDialog;
    private View rootView;
    View failureView;
    private DmThreeAdapter adapter,ydsadapter;
    public static final int REQUEST_A = 1;
    private String sMessage;
    private LinearLayout gxy_submit;
    private NecessityNameOptionsNewLayout ishealthedu,ly_isReferral;
    private EditText edit_ransferreatment, edit_transfertreatmentdept, edit_visitingdoctor;
    private String content;
    private List<NameValueBeanWithNo> fragment01list = new ArrayList<>();
    private List<NameValueBeanWithNo> fragment02list = new ArrayList<>();
    private HBPGuanLi_PersonInfo personInfo;
    RecyclerView recyclerView ,recyclerView2;
    private  LinearLayout ll_transfertreatmentdept;
    private ArrayList<UseDrugInfo> datalist = new ArrayList<UseDrugInfo>();
    private ArrayList<UseDrugInfo> ydslist = new ArrayList<UseDrugInfo>();
    @Inject
    DmFragmentPresenter mPresenter;
    private CompositeSubscription mInitializeSubscription;
    private  LinearLayout add_drug,add_yds,ll_ransferreatment;
    ScrollView sv_content;
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
                            ll_transfertreatmentdept.setVisibility(View.GONE);
                            ll_ransferreatment.setVisibility(View.GONE);

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
        return R.layout.fragment_dm_three_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        registerInitializeSubscription();
    }

    @Override
    protected void loadData() {

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dm_three_layout, container, false);
        DiabetesMellitusActivity activity = (DiabetesMellitusActivity) getActivity();
        personInfo = activity.getHbpGuanLi_personInfo();
        iReadCardState = activity.getiReadCardState();
        ll_ransferreatment=(LinearLayout)view.findViewById(R.id.ll_ransferreatment);
     //   sUpdateUserCode =activity.getsUpdateUserCode();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView) ;
        recyclerView2=(RecyclerView)view.findViewById(R.id.recyclerView2) ;
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);
        edit_ransferreatment = (EditText) view.findViewById(R.id.edit_ransferreatment);
        edit_transfertreatmentdept = (EditText) view.findViewById(R.id.edit_transfertreatmentdept);
        edit_nextvisitingdate = (TextView) view.findViewById(R.id.nextvisitingdate);
        edit_visitingdoctor = (EditText) view.findViewById(R.id.edit_visitingdoctor);
        edit_visitingdoctor.setText(DaggerApplication.getInstance().getRealName());
        gxy_submit = (LinearLayout) view.findViewById(R.id.gxy_submit);
        ishealthedu = (NecessityNameOptionsNewLayout) view.findViewById(R.id.ishealthedu);
        ll_transfertreatmentdept=(LinearLayout)view.findViewById(R.id.ll_transfertreatmentdept);
        ly_isReferral=(NecessityNameOptionsNewLayout)view.findViewById(R.id.isReferral);
        add_drug=(LinearLayout)view.findViewById(R.id.add_drug);
        add_yds=(LinearLayout)view.findViewById(R.id.add_yds);

        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView2.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        ydsadapter = new DmThreeAdapter(new ArrayList<UseDrugInfo>(), getContext(),true);
        adapter = new DmThreeAdapter(new ArrayList<UseDrugInfo>(), getContext(),false);
        ydsadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        ydslist.add(new UseDrugInfo("","每日一次","","mg",""));
        datalist.add(new UseDrugInfo("","每日一次","","mg",""));
        List<NameValueBean> genderListisReferral = new ArrayList<>();
        genderListisReferral.add(new NameValueBean("是", "2", false));
        genderListisReferral.add(new NameValueBean("否", "1", false));
        ly_isReferral.setDataList(genderListisReferral);
        adapter.setNewData(datalist);
        ydsadapter.setNewData(ydslist);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(ydsadapter);
        adapter.notifyDataSetChanged();
        ydsadapter.notifyDataSetChanged();
        add_yds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ydslist.add(new UseDrugInfo("","","","mg",""));
                ydsadapter.notifyDataSetChanged();
            }

        });
        add_drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalist.add(new UseDrugInfo("","","","mg",""));
                adapter.notifyDataSetChanged();
            }

        });




        gxy_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiabetesMellitusActivity activity = (DiabetesMellitusActivity) getActivity();
                ViewPager viewPager = activity.getVpContent();
                activity.getDMFragment1().getdata();
                if (viewPager.getCurrentItem() == 2) {
                    dmPostBean1 = activity.getDMFragment1().getdata();
//                    setwebData(fragment01list);
                    activity.getDMFragment2().getdata();
                    if (viewPager.getCurrentItem() == 2) {
                        dmPostBean2 = activity.getDMFragment2().getdata();

                            if (TextUtils.isEmpty(edit_visitingdoctor.getText())) {
                                viewPager.setCurrentItem(2);
                                sv_content.smoothScrollTo(0, edit_visitingdoctor.getTop());
                                Toast.makeText(getActivity(), "请输入随访医生签名!", Toast.LENGTH_SHORT).show();
                            }else if (TextUtils.isEmpty(edit_nextvisitingdate.getText())) {
                                viewPager.setCurrentItem(2);
                                sv_content.smoothScrollTo(0, edit_nextvisitingdate.getTop());
                                Toast.makeText(getActivity(), "请选择下次随访时间!", Toast.LENGTH_SHORT).show();
                            }else {

                                dmPostBean1.setDoctor(edit_visitingdoctor.getText().toString());
                                dmPostBean1.setDailySmoke(dmPostBean2.getDailySmoke());
                                dmPostBean1.setDailySmokeTarget(dmPostBean2.getDailySmoke());
                                dmPostBean1.setDailyDrink(dmPostBean2.getDailySmoke());
                                dmPostBean1.setDailyDrinkTarget(dmPostBean2.getDailyDrinkTarget());
                                dmPostBean1.setExercise(dmPostBean2.getExercise());
                                dmPostBean1.setNextExercise(dmPostBean2.getNextExercise());
                                dmPostBean1.setExerciseTimes(dmPostBean2.getExerciseTimes());
                                dmPostBean1.setNextExerciseTimes(dmPostBean2.getNextExerciseTimes());
                                dmPostBean1.setPsychic(dmPostBean2.getPsychic());
                                dmPostBean1.setDrugCompliance(dmPostBean2.getDrugCompliance());
                                dmPostBean1.setAdverseReaction(dmPostBean2.getAdverseReaction());
                                dmPostBean1.setPatientCompliance(dmPostBean2.getPatientCompliance());
                                dmPostBean1.setSort(dmPostBean2.getSort());
                                dmPostBean1.setStaple(dmPostBean2.getStaple());
                                dmPostBean1.setStapleAim(dmPostBean2.getStapleAim());
                                dmPostBean1.setFpg(dmPostBean2.getFpg());
                                dmPostBean1.setPbg(dmPostBean2.getPbg());
                                dmPostBean1.setAccessoryExaminationDate(dmPostBean2.getAccessoryExaminationDate());
                                dmPostBean1.setAccessoryExaminationHba1c(dmPostBean2.getAccessoryExaminationHba1c());
                                dmPostBean1.setOtherAdverseReaction(dmPostBean2.getOtherAdverseReaction());
                                dmPostBean1.setLowSugarReaction(dmPostBean2.getLowSugarReaction());


                                dmPostBean1.setNextFlwDate(edit_nextvisitingdate.getText().toString());
                                activity=(DiabetesMellitusActivity) getActivity();
                                HighBloodListActivityBean bean= activity.getHighBloodListActivityBean();
                                String  taskId=dmPostBean1.getTaskId();
                                String  name=bean.getName();
                                String  id=bean.getId();
                                String  no=bean.getNo();
                                String  doctor=dmPostBean1.getDoctor();
                                String  idNo=bean.getIdNo();

                                String flwDate=dmPostBean1.getFlwDate();
                                String flwType=checknull(dmPostBean1.getFlwType());
                                String followDownReason=dmPostBean1.getFollowDownReason();
                                String symptom=dmPostBean1.getSymptom();
                                String otherSymptom=dmPostBean1.getOtherSymptom();
                                String sbp=checknull(dmPostBean1.getSbp());
                                String dbp=checknull(dmPostBean1.getDbp());
                                String heartRate=checknull(dmPostBean1.getHeartRate());
                                String height=checknull(dmPostBean1.getHeight());
                                String weight=checknull(dmPostBean1.getWeight());
                                String weightTarget=checknull(dmPostBean1.getWeightTarget());
                                String bmi=checknull(dmPostBean1.getBmi());
                                String bmiTarget=checknull(dmPostBean1.getBmiTarget());
                                String otherSign=dmPostBean1.getOtherSign();
                                String dorsalisPedisL=checknull(dmPostBean1.getDorsalisPedisL());
                                String dorsalisPedisR=checknull(dmPostBean1.getDorsalisPedisR());
                                String dailySmoke=checknull(dmPostBean1.getDailySmoke());
                                String dailySmokeTarget=checknull(dmPostBean1.getDailySmokeTarget());
                                String dailyDrink=checknull(dmPostBean1.getDailyDrink());
                                String dailyDrinkTarget=checknull(dmPostBean1.getDailyDrinkTarget());
                                String exercise=dmPostBean1.getExercise();
                                String exerciseTimes=dmPostBean1.getExerciseTimes();
                                String nextExercise=dmPostBean1.getNextExercise();
                                String nextExerciseTimes=dmPostBean1.getNextExerciseTimes();
                                String staple=checknull(dmPostBean1.getStaple());
                                String stapleAim=checknull(dmPostBean1.getStapleAim());
                                String psychic=checknull(dmPostBean1.getPsychic());
                                String patientCompliance=checknull(dmPostBean1.getPatientCompliance());
                                String fpg=checknull(dmPostBean1.getFpg());
                                String pbg=checknull(dmPostBean1.getPbg());
                                String accessoryExaminationHba1c=checknull(dmPostBean1.getAccessoryExaminationHba1c());
                                String accessoryExaminationDate=checknull(dmPostBean1.getAccessoryExaminationDate());
                                String drugCompliance=checknull(dmPostBean1.getDrugCompliance());
                                String adverseReaction=checknull(dmPostBean1.getAdverseReaction());
                                String lowSugarReaction=checknull(dmPostBean1.getLowSugarReaction());
                                String sort=checknull(dmPostBean1.getSort());
                                String insulinType=dmPostBean1.getInsulinType();
                                String nextFlwDate=dmPostBean1.getNextFlwDate();
                                String doctorCode=DaggerApplication.getInstance().getUsername();
                                String frequency=dmPostBean1.getFrequency();
                                String singleDose=dmPostBean1.getSingleDose();
                                String unit=dmPostBean1.getUnit();
                                String  isReferral=checknull(ly_isReferral.getSingleSelectValue());
                                String  referralReason=edit_ransferreatment.getText().toString();
                                String  referralHosp = edit_transfertreatmentdept.getText().toString();
                                String otherAdverseReaction=dmPostBean1.getOtherAdverseReaction();
                                String personid=bean.getPersonId();


                        Map<String, String> map = ParametersFactory.postDmData(getActivity(),
                                taskId,id,no,flwDate,flwType,followDownReason,symptom,otherSymptom,sbp,dbp,heartRate,height,weight,weightTarget,bmi,bmiTarget,otherSign,
                                dorsalisPedisL,dorsalisPedisR,dailySmoke,dailySmokeTarget,dailyDrink,
                                dailyDrinkTarget,exercise,exerciseTimes,nextExercise,nextExerciseTimes,
                                staple,stapleAim,psychic,patientCompliance,fpg,pbg,
                                accessoryExaminationHba1c,accessoryExaminationDate,drugCompliance,
                                adverseReaction,lowSugarReaction,sort,insulinType,nextFlwDate,
                                doctorCode,name,frequency,singleDose,unit,isReferral,referralReason,
                                referralHosp,otherAdverseReaction,datalist ,ydslist,personid,doctor,idNo

                                ) ;

                        mPresenter.postdate(map);



                            }



                    }


                }

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
                        },false );
            }
        });


        return view;

    }

    public void removeDate(int postion,Boolean isyds) {
        if (isyds){
            ydsadapter.remove(postion);
            ydsadapter.notifyDataSetChanged();
        }else {
            adapter.remove(postion);
            adapter.notifyDataSetChanged();


        }



    }
    public void setDate(int postion, String value, String type,Boolean isyds) {
        if (TextUtils.isEmpty(value)||datalist.size()==0||datalist.size()<postion+1) {
        } else {
                if (isyds){

                    switch (type) {
                        case "药名":
                            ydslist.get(postion).setName(value);


                            break;
                        case "频次":
                            ydslist.get(postion).setFrequency(value);

                            break;
                        case "单次剂量":
                            ydslist.get(postion).setSingleDose(value);

                            break;
                        case "单位":

                            ydslist.get(postion).setUnit(value);

                            break;

                        case "用法":

                            ydslist.get(postion).setDrugUsage(value);

                            break;
                    }

                }else {

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


    }
    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addDMFragment(new DMFragmentModule(this))
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


    public String checknull(String str){
        if (TextUtils.isEmpty(str)){
            return "0";
        }
        return str;
    }
}
