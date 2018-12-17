//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.content.Intent;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.flyco.roundview.RoundTextView;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.SpinnerDoctorAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.IdentityManager;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.ClientPersonSignApply;
//import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
//import com.jqsoft.fingertip_health.di.contract.OnLineSignApplicationContract;
//import com.jqsoft.fingertip_health.di.module.OnlineSignApplicationActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.OnlineApplicationPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.GallerySeverpakesAdapter;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import okhttp3.RequestBody;
//
///**
// * Created by Jerry on 2017/8/21.
// */
////在线签约申请
//public class OnLineSignApplication extends AbstractActivity implements OnLineSignApplicationContract.View, SignClientDoctorSeverPakes.ButtonListter, GallerySeverpakesAdapter.DeletOnItemClickListener {
//    public static OnLineSignApplication contx;
//    @BindView(R.id.online_consultation_title)
//    TextView activity_title;
//    @BindView(R.id.ll_back)
//    LinearLayout ll_back;
//    @BindView(R.id.img_head)
//    ImageView img_head;
//    @BindView(R.id.iv_phone)
//    ImageView iv_phone;
//    @BindView(R.id.tv_base_name)
//    TextView username;
//    @BindView(R.id.iv_base_sex)
//    ImageView usersex;
//    @BindView(R.id.tv_base_age)
//    TextView userage;
//    @BindView(R.id.tv_base_idcard)
//    TextView userIdcard;
//    @BindView(R.id.tv_base_files)
//    TextView userfileNo;
//    @BindView(R.id.tv_base_doctor)
//    TextView doctorName;
//    @BindView(R.id.tv_base_signdate)
//    TextView signdata;
//    @BindView(R.id.famialyadd)
//    TextView famialyadd;
//    @BindView(R.id.allservciepakes)
//    TextView allservciepakes;
//    @BindView(R.id.suoshujigou)
//    TextView docorg;
//    @BindView(R.id.signlayout)
//    LinearLayout signLayout;
//    @BindView(R.id.spinner)
//    Spinner spinner;
//    @BindView(R.id.submit_sign)
//    RoundTextView submitBtn;
//    @BindView(R.id.mygrideview)
//    RecyclerView recyclerView;
//    @BindView(R.id.addseverpake_btn)
//    TextView addserverpakeBtn;
//    @BindView(R.id.yuan01)
//    TextView yuan01;
//    @BindView(R.id.yuan02)
//    TextView yuan02;
//    @BindView(R.id.yuan03)
//    TextView yuan03;
//    @BindView(R.id.yuan04)
//    TextView yuan04;
//    @BindView(R.id.yuan05)
//    TextView yuan05;
//    @BindView(R.id.yingshou1)
//    TextView tv_packSumFee;//应收金额
//    @BindView(R.id.shishou2)
//    TextView tv_actualPackageSumFee;//实收金额总计
//    @BindView(R.id.jianmian3)
//    TextView tv_otherReduceFee;//减免金额总计
//    @BindView(R.id.yibaochuchang5)
//    TextView tv_newRuralCMSFee;//新农合补偿金额总计
//    @BindView(R.id.zifu4)
//    TextView tv_shouldSelfFeee;//应自付金额总计
//    @BindView(R.id.iv_tang)
//    ImageView iv_tang;
//
//    @BindView(R.id.iv_gao)
//    ImageView iv_gao;
//
//    @BindView(R.id.iv_lao)
//    ImageView iv_lao;
//
//    @BindView(R.id.iv_jing)
//    ImageView iv_jing;
//
//    @BindView(R.id.iv_mian)
//    ImageView iv_mian;
//
//    @BindView(R.id.iv_pin)
//    ImageView iv_pin;
//
//    @BindView(R.id.iv_tong)
//    ImageView iv_tong;
//
//    @BindView(R.id.iv_tuo)
//    ImageView iv_tuo;
//
//    @BindView(R.id.iv_yun)
//    ImageView iv_yun;
//    @BindView(R.id.ll_label)
//    LinearLayout ll_label;
//    private String cardNo;
//    private String personID;
//    private String no;
//    private String personName;
//    private String phone;
//    private String serverPackageName = "";
//    private String packID;
//    private String address;
//    private String areaCode;
//    private String applyDoctor;
//    private String applyDoctorName;
//    private String age;
//    private String personMold;
//    private String sexName;
//    private ArrayList<DoctorTeamInfo> doctorList = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    private SpinnerDoctorAdapter spinnerAdapter;
//    private GallerySeverpakesAdapter gallerySeverpakesAdapter;
//    @Inject
//    OnlineApplicationPresenter onlineApplicationPresenter;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.onllinesignapplication;
//    }
//
//    @Override
//    protected void initData() {
//        activity_title.setText("在线签约申请");
//    }
//
//    @Override
//    protected void initView() {
//        contx = this;
//        ll_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        addserverpakeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OnLineSignApplication.this, SignClientDoctorSeverPakes.class);
//                startActivity(intent);
//            }
//        });
//
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                cardNo = Identity.getCardNo();
//                cardNo = IdentityManager.getCardNo(OnLineSignApplication.this);
//                personID = IdentityManager.getPersonID(OnLineSignApplication.this);
//                sexName = IdentityManager.getSexName(OnLineSignApplication.this);
//                no = userfileNo.getText().toString();
//                personName = username.getText().toString();
//                phone = IdentityManager.getPhoneNumber(OnLineSignApplication.this);
//                for (int i = 0; i < datalist.size(); i++) {
//                    serverPackageName = serverPackageName + " " + datalist.get(i).getFwmc();
//                    packID = packID + " " + datalist.get(i).getKey();
//                }
//                address = famialyadd.getText().toString();
//                areaCode = IdentityManager.getAreaCode(OnLineSignApplication.this);
//                applyDoctorName = doctorName.getText().toString();
//                age = IdentityManager.getAge(OnLineSignApplication.this);
//                personMold = IdentityManager.getIsHypertensionState(OnLineSignApplication.this) + IdentityManager.getIsType2DiabetesState(OnLineSignApplication.this) + IdentityManager.getIsPsychosisState(OnLineSignApplication.this) + IdentityManager.getIsOldPeopleInfoState(OnLineSignApplication.this) + IdentityManager.getIsPregnantwomenInfoState(OnLineSignApplication.this) + IdentityManager.getIsChildrenInfoState(OnLineSignApplication.this) + IdentityManager.getIsPoorState(OnLineSignApplication.this) + "0000000000000";
//                Map<String, String> map = ParametersFactory.savePersonSignApply(
//                        OnLineSignApplication.this,
//                        cardNo,
//                        personID,
//                        sexName,
//                        no,
//                        personName,
//                        phone,
//                        serverPackageName,
//                        packID,
//                        address,
//                        areaCode,
//                        applyDoctor,
//                        applyDoctorName,
//                        age,
//                        personMold);
//                RequestBody body = Util.getBodyFromMap(map);
//                onlineApplicationPresenter.savePersonSignApply(body);
//            }
//        });
//
//        iv_phone.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
////                String phoneNumber = Util.trimString(Identity.getPhoneNumber());
//                String phoneNumber = Util.trimString(IdentityManager.getPhoneNumber(OnLineSignApplication.this));
//                if (!StringUtils.isBlank(phoneNumber)) {
//                    AppUtils.actionDial(iv_phone.getContext(), phoneNumber);
//                } else {
//                    Util.showToast(iv_phone.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void loadData() {
//        Map<String, String> map = getRemindListRequestMap();
//        RequestBody body = Util.getBodyFromMap(map);
//        onlineApplicationPresenter.getPersonSignApply(body);
//
//    }
//
//    private Map<String, String> getRemindListRequestMap() {
////        String cardNo = Identity.getCardNo();
//        String cardNo = IdentityManager.getCardNo(this);
//        String personID = IdentityManager.getPersonID(this);
//        Map<String, String> map = ParametersFactory.getRemindDataMap(this, cardNo, personID);//"341222195011132094"
//        return map;
//    }
//
//
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addOnlineSignApplicationActity(new OnlineSignApplicationActivityModule(this))
//                .inject(this);
//    }
//
//    //医生团队结果集
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
//        Util.hideGifProgressDialog(this);
//        if (bean.getData().size() > 0) {
//            submitBtn.setVisibility(View.VISIBLE);
//            signLayout.setVisibility(View.VISIBLE);
//            doctorList.addAll(bean.getData());
//            spinnerAdapter = new SpinnerDoctorAdapter(doctorList, getApplicationContext());
//            spinner.setAdapter(spinnerAdapter);
//            spinnerAdapter.notifyDataSetChanged();
//            docorg.setText(doctorList.get(0).getOrgName());
//            doctorName.setText(doctorList.get(0).getDoctorName());
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                    docorg.setText(doctorList.get(position).getOrgName());
//                    doctorName.setText(doctorList.get(position).getDoctorName());
//                    applyDoctor = doctorList.get(position).getDocUserId();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
//        }
//
//    }
//
//    //    private String actualPackageSumFee;//实收金额总计 b
////    private String packSumFee;//费用总计a
////    private String newRuralCMSFee;//新农合补偿金额总计 f
////    private String otherReduceFee;//减免金额总计 c
////    private String shouldSelfFee;//应自付金额总计 e
//
//    //    TextView tv_packSumFee;//应收金额 a
////    TextView tv_actualPackageSumFee;//实收金额总计 b
////    TextView tv_otherReduceFee;//减免金额总计 c
////    TextView tv_newRuralCMSFee;//新农合补偿金额总计 f
////    TextView tv_shouldSelfFeee;//应自付金额总计 e
//    //获取签约申请结果集
//    @Override
//    public void onLineApplicationListSuccess(HttpResultBaseBean<List<ClientPersonSignApply>> bean) {
//        Util.hideGifProgressDialog(this);
//        if (bean.getData().size() > 0) {
//            submitBtn.setVisibility(View.GONE);
//            signLayout.setVisibility(View.GONE);
//            String headUrl = Util.trimString(bean.getData().get(0).getPhotoUrl());
//            String imageUrl = Version.FILE_URL_BASE + headUrl;
//            GlideUtils.loadImage(imageUrl, img_head);
//
//            username.setText(bean.getData().get(0).getPersonName());
//            userage.setText(IdentityManager.getAge(OnLineSignApplication.this) + "岁");//
//            if (bean.getData().get(0).getSexName().equals("男")) {
//                usersex.setBackgroundResource(R.mipmap.i_male);
//            } else {
//                usersex.setBackgroundResource(R.mipmap.i_female);
//            }
//            userIdcard.setText(bean.getData().get(0).getCardNo());
//            userfileNo.setText(bean.getData().get(0).getNo());
//            doctorName.setText(bean.getData().get(0).getApplyDoctorName());
//            famialyadd.setText(bean.getData().get(0).getAddress());
//            if (bean.getData().get(0).getApplyTime().length() > 10) {
//                signdata.setText(bean.getData().get(0).getApplyTime().substring(0, 10));
//            } else {
//                signdata.setText(bean.getData().get(0).getApplyTime());
//            }
//            allservciepakes.setText(bean.getData().get(0).getServerPackageName());
//            docorg.setText(bean.getData().get(0).getOrgName());
//            tv_packSumFee.setText(bean.getData().get(0).getPackSumFee());//应收金额
//            tv_actualPackageSumFee.setText(bean.getData().get(0).getActualPackageSumFee());//实收金额总计
//            tv_otherReduceFee.setText(bean.getData().get(0).getOtherReduceFee());//减免金额总计
//            tv_shouldSelfFeee.setText(bean.getData().get(0).getShouldSelfFee());//应自付金额总计
//            tv_newRuralCMSFee.setText(bean.getData().get(0).getNewRuralCMSFee());//新农合补偿金额总计
//            yuan01.setVisibility(View.VISIBLE);
//            yuan02.setVisibility(View.VISIBLE);
//            yuan03.setVisibility(View.VISIBLE);
//            yuan04.setVisibility(View.VISIBLE);
//            yuan05.setVisibility(View.VISIBLE);
//            String spersonMold = bean.getData().get(0).getPersonMold();
//            ArrayList<String> spersonMoldList = new ArrayList<>();
//            spersonMoldList.clear();
//            if (spersonMold.substring(0, 1).equals("1")) {
//                spersonMoldList.add("高");
//            }
//            if (spersonMold.substring(1, 2).equals("1")) {
//                spersonMoldList.add("糖");
//            }
//            if (spersonMold.substring(2, 3).equals("1")) {
//                spersonMoldList.add("精");
//            }
//            if (spersonMold.substring(3, 4).equals("1")) {
//                spersonMoldList.add("老");
//            }
//            if (spersonMold.substring(4, 5).equals("1")) {
//                spersonMoldList.add("孕");
//            }
//            if (spersonMold.substring(5, 6).equals("1")) {
//                spersonMoldList.add("童");
//            }
//            if (spersonMold.substring(6, 7).equals("1")) {
//                spersonMoldList.add("贫");
//            }
//            if (spersonMold.substring(6, 7).equals("2")) {
//                spersonMoldList.add("脱");
//            }
//            if (spersonMold.substring(6, 7).equals("3")) {
//                spersonMoldList.add("免");
//            }
//            String s = "";
//            for (int i = 0; i < spersonMoldList.size(); i++) {
//                s = s + spersonMoldList.get(i);
//            }
//            if (spersonMoldList.size() == 0) {
//                ll_label.setVisibility(View.GONE);
//            } else {
//                ll_label.setVisibility(View.VISIBLE);
//                if (s.indexOf("糖") != -1) {
//                    iv_tang.setImageResource(R.mipmap.ic_tang);
//                    iv_tang.setVisibility(View.VISIBLE);
//                }
//
//                if (s.indexOf("高") != -1) {
//                    iv_gao.setImageResource(R.mipmap.ic_gao);
//                    iv_gao.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("老") != -1) {
//                    iv_lao.setImageResource(R.mipmap.ic_lao);
//                    iv_lao.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("精") != -1) {
//                    iv_jing.setImageResource(R.mipmap.ic_jing);
//                    iv_jing.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("免") != -1) {
//                    iv_mian.setImageResource(R.mipmap.ic_mian);
//                    iv_mian.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("贫") != -1) {
//                    iv_pin.setImageResource(R.mipmap.ic_pin);
//                    iv_pin.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("童") != -1) {
//                    iv_tong.setImageResource(R.mipmap.ic_tong);
//                    iv_tong.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("脱") != -1) {
//                    iv_tuo.setImageResource(R.mipmap.ic_tuo);
//                    iv_tuo.setVisibility(View.VISIBLE);
//                }
//                if (s.indexOf("孕") != -1) {
//                    iv_yun.setImageResource(R.mipmap.ic_yun);
//                    iv_yun.setVisibility(View.VISIBLE);
//                }
//            }
//
//
//        } else {
//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            recyclerView.setLayoutManager(linearLayoutManager);
//            //设置适配器
//
//            gallerySeverpakesAdapter = new GallerySeverpakesAdapter(this, datalist);
//            recyclerView.setAdapter(gallerySeverpakesAdapter);
//            gallerySeverpakesAdapter.notifyDataSetChanged();
//
//            Map<String, String> map = geDoctorListRequestMap();
//            RequestBody body = Util.getBodyFromMap(map);
//            onlineApplicationPresenter.getDoctorTeamInfo(body);
//            signdata.setText(Util.getCurrentYearMonthDayString());
//            username.setText(IdentityManager.getPersonName(OnLineSignApplication.this));
//            userage.setText(IdentityManager.getAge(OnLineSignApplication.this) + "岁");
//            if (IdentityManager.getSexName(OnLineSignApplication.this).equals("男")) {
//                usersex.setBackgroundResource(R.mipmap.i_male);
//            } else {
//                usersex.setBackgroundResource(R.mipmap.i_female);
//            }
//            famialyadd.setText(IdentityManager.getAddress(OnLineSignApplication.this));
//            userIdcard.setText(IdentityManager.getCardNo(this));
//            userfileNo.setText(IdentityManager.getNo(OnLineSignApplication.this));
//
//        }
//
//
//    }
//
//    private Map<String, String> geDoctorListRequestMap() {
////        String cardNo = Identity.getCardNo();
//        String cardNo = IdentityManager.getCardNo(this);
//        String personID = IdentityManager.getPersonID(this);
//        Map<String, String> map = ParametersFactory.getRemindDataMap(this, cardNo, personID);
//        return map;
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, message);
//    }
//
//    //签约成功结果返回
//    @Override
//    public void onLoginSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, bean.getMessage());
//        submitBtn.setVisibility(View.GONE);
//        signLayout.setVisibility(View.GONE);
//        allservciepakes.setText(serverPackageName);
//
//    }
//
//    @Override
//    public void onLoginFailure(String message) {
//
//    }
//
//    @Override
//    public void setResultdata(ArrayList<SignSeverPakesBeanList> itemlist) {
//        if (itemlist.size() > 0) {
//            recyclerView.setVisibility(View.VISIBLE);
//            for (int j = 0; j < datalist.size(); j++) {
//                for (int i = 0; i < itemlist.size(); i++) {
//                    if (itemlist.get(i).getKey().equals(datalist.get(j).getKey())) {
//                        itemlist.remove(i);
//                    }
//                }
//            }
//            datalist.addAll(itemlist);
//        }
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        //设置适配器
//        gallerySeverpakesAdapter = new GallerySeverpakesAdapter(this, datalist);
//        recyclerView.setAdapter(gallerySeverpakesAdapter);
//        gallerySeverpakesAdapter.notifyDataSetChanged();
//        settextView(itemlist);
//        gallerySeverpakesAdapter.setDeteOnItemClickListener(this);
//    }
//
//    //    TextView tv_packSumFee;//应收金额
////    TextView tv_actualPackageSumFee;//实收金额总计
////    TextView tv_otherReduceFee;//减免金额总计
////    TextView tv_shouldSelfFeee;//应自付金额总计
////    TextView tv_newRuralCMSFee;//新农合补偿金额总计
//
//    //    double ysje = 0.00;//应收金额总计 hjnysfje
////    double ssje = 0.00;//实收金额总计 serviceCententFee
////    double jmje = 0.00;//减免金额总计 qtjmje
////    double bcje = 0.00;//医保补偿金额总计 xnhbcje
////    double zfje = 0.00;//自付金额总计 sjzfje
//    @Override
//    public void onItemDelete(ArrayList<SignSeverPakesBeanList> datalist, int position) {
//        double ys = (Util.getDoubleFromString(tv_packSumFee.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getHjnysfje()));
//        tv_packSumFee.setText(Util.getStringdouble(Double.toString(ys)) + "");
//        double ss = (Util.getDoubleFromString(tv_actualPackageSumFee.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getServiceCententFee()));
//        tv_actualPackageSumFee.setText(Util.getStringdouble(Double.toString(ss)) + "");
//        double jm = (Util.getDoubleFromString(tv_otherReduceFee.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getQtjmje()));
//        tv_otherReduceFee.setText(Util.getStringdouble(Double.toString(jm)) + "");
//        double yb = (Util.getDoubleFromString(tv_newRuralCMSFee.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getXnhbcje()));
//        tv_newRuralCMSFee.setText(Util.getStringdouble(Double.toString(yb)) + "");
//        double zf = (Util.getDoubleFromString(tv_shouldSelfFeee.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getSjzfje()));
//        tv_shouldSelfFeee.setText(Util.getStringdouble(Double.toString(zf)) + "");
//    }
//
//
//    private void settextView(ArrayList<SignSeverPakesBeanList> datalist) {
//     /*   nhcompensateProjName  （1:基础包 2:初级包 3: 中级包 4:高级包 5 其他）
//        fwmc                    服务包名称
//        fwnr                     服务内容
//        sydx                     适用对象
//        hjnysfje                  总收入（总金额）
//        xnhbcje                  新农合应予补偿金额
//        qtjmje                   其他减免金额
//        sjzfje                    实际自付金额
//        cdje                     基本公共卫生服务经费承担金额
//        serviceCententFee         实收金额
//        key                      服务包主键*/
//        double ysje = 0.00;//应收金额总计 hjnysfje  A
//        double ssje = 0.00;//实收金额总计 serviceCententFee  B
//        double jmje = 0.00;//减免金额总计 qtjmje           C
//        double bcje = 0.00;//医保补偿金额总计 xnhbcje   D
//        double zfje = 0.00;//自付金额总计 sjzfje   V
//        for (int i = 0; i < datalist.size(); i++) {
//
//            //    TextView tv_packSumFee;//应收金额  A
////    TextView tv_actualPackageSumFee;//实收金额总计 B
////    TextView tv_otherReduceFee;//减免金额总计 C
////    TextView tv_shouldSelfFeee;//应自付金额总计  V
////    TextView tv_newRuralCMSFee;//新农合补偿金额总计 D
//
//            ysje = ysje + Util.getDoubleFromString(datalist.get(i).getHjnysfje());
//            ssje = ssje + Util.getDoubleFromString(datalist.get(i).getServiceCententFee());
//            jmje = jmje + Util.getDoubleFromString(datalist.get(i).getQtjmje());
//            bcje = bcje + Util.getDoubleFromString(datalist.get(i).getXnhbcje());
//            zfje = zfje + Util.getDoubleFromString(datalist.get(i).getSjzfje());
//
//            tv_packSumFee.setText(Util.getStringdouble(Double.toString(ysje)) + "");
//            tv_actualPackageSumFee.setText(Util.getStringdouble(Double.toString(ssje)) + "");
//            tv_otherReduceFee.setText(Util.getStringdouble(Double.toString(jmje)) + "");
//            tv_newRuralCMSFee.setText(Util.getStringdouble(Double.toString(bcje)) + "");
//            tv_shouldSelfFeee.setText(Util.getStringdouble(Double.toString(zfje)) + "");
//            if (!TextUtils.isEmpty(ysje + "")) {
//                yuan01.setVisibility(View.VISIBLE);
//            }
//            if (!TextUtils.isEmpty(ssje + "")) {
//                yuan02.setVisibility(View.VISIBLE);
//            }
//            if (!TextUtils.isEmpty(jmje + "")) {
//                yuan03.setVisibility(View.VISIBLE);
//            }
//            if (!TextUtils.isEmpty(bcje + "")) {
//                yuan04.setVisibility(View.VISIBLE);
//            }
//            if (!TextUtils.isEmpty(zfje + "")) {
//                yuan05.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//}
