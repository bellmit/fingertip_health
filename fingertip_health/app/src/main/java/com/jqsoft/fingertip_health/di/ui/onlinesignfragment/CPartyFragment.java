//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignfragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.flyco.roundview.RoundTextView;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.PatientBean;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.bean.SignTeamBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
//import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
//import com.jqsoft.fingertip_health.di.contract.SaveFamilyDoctorSignContract;
//import com.jqsoft.fingertip_health.di.module.SaveFamilyDoctorSignFragmentModule;
//import com.jqsoft.fingertip_health.di.presenter.SaveFamilyDoctorSignFragmentPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.SignDoctorSeverPakes;
//import com.jqsoft.fingertip_health.di.ui.fragment.HeyibanFragment;
//import com.jqsoft.fingertip_health.di.ui.fragment.InHospitalInspectFragment;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.GallerySeverpakesAdapter;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.listener.submitOnSuccessListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import okhttp3.RequestBody;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
//public class CPartyFragment extends AbstractFragment implements SignDoctorSeverPakes.ButtonListter, GallerySeverpakesAdapter.DeletOnItemClickListener, SaveFamilyDoctorSignContract.View, HeyibanFragment.OnSuccessListener {
//    private Context context;
//    @BindView(R.id.add_layout)
//    LinearLayout addserverlaybtn;
//    @BindView(R.id.mygrideview)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.yingshou)
//    TextView tv_ysje;
//    @BindView(R.id.shishou)
//    TextView tv_ssje;
//    @BindView(R.id.jianmian)
//    TextView tv_jmje;
//    @BindView(R.id.yibaochuchang)
//    TextView tv_ybbc;
//    @BindView(R.id.heji)
//    TextView tv_allcount;
//    @BindView(R.id.shouweistate)
//    TextView shouweistate;
//    @BindView(R.id.submit_over)
//    RoundTextView submit;
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
//
//
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> detailInVoList = new ArrayList<>();
//    private GallerySeverpakesAdapter mAdapter;
//    private String ysje;
//    private String ssje;
//    private String jmje;
//    private String ybbc;
//    private String allcount;
//    @Inject
//    SaveFamilyDoctorSignFragmentPresenter mPresenter;
//    private static CPartyFragment instance = null;
//
//    private CompositeSubscription mInitializeSubscription;
//
//    private void registerInitializeSubscription() {
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_ONLINE_SIGN_PER_FRAGMENT_INITIALIZE, IndexAndOnlineSignInitialData.class).subscribe(new Action1<IndexAndOnlineSignInitialData>() {
//            @Override
//            public void call(IndexAndOnlineSignInitialData indexAndOnlineSignInitialData) {
//                if (indexAndOnlineSignInitialData != null) {
//                    signEdit = "1";
//                }
//                applyKey = indexAndOnlineSignInitialData.getApplyKey();
//                if (indexAndOnlineSignInitialData.getSignDoctorList().size() > 0) {
//                    mRecyclerView.setVisibility(View.VISIBLE);
//                }
//                indexAndOnlineSignInitialData.getSignDoctorList();
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                mRecyclerView.setLayoutManager(linearLayoutManager);
//                //设置适配器
//                datalist.clear();
//                datalist.addAll(indexAndOnlineSignInitialData.getSignDoctorList());
//                mAdapter = new GallerySeverpakesAdapter(getActivity(), datalist);
//                mRecyclerView.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//                settextView(datalist);
//
//                mAdapter.setDeteOnItemClickListener(getCPartyFragment());
//            }
//        });
//        if (mInitializeSubscription == null) {
//            mInitializeSubscription = new CompositeSubscription();
//        }
//        mInitializeSubscription.add(subscription);
//    }
//
//    private void unregisterInitializeSubscription() {
//        if (mInitializeSubscription != null && mInitializeSubscription.hasSubscriptions()) {
//            mInitializeSubscription.unsubscribe();
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterInitializeSubscription();
//    }
//
//
//    public static CPartyFragment getCPartyFragment() {
//        if (instance == null) {
//            instance = new CPartyFragment();
//        }
//        return instance;
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_cparty_fragment;
//    }
//
//    @OnClick(R.id.add_layout)
//    public void addServer() {
//        Intent intent = new Intent(context, SignDoctorSeverPakes.class);
//        context.startActivity(intent);
//        //  Toast.makeText(getActivity(), "w231", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void initData() {
//        context = getActivity();
//    }
//
//    @Override
//    protected void initView() {
//        instance = this;
//        if (mInitializeSubscription == null) {
//            registerInitializeSubscription();
//        }
//
//    }
//
//    @Override
//    protected void loadData() {
//        // HeyibanFragment.getHeyibanFragment().setOnSuccessListener(this);
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addCApartFragment(new SaveFamilyDoctorSignFragmentModule(this))
//                .inject(this);
//    }
//
//    @Override
//    public void setResultdata(ArrayList<SignSeverPakesBeanList> itemlist) {
//        if (itemlist.size() > 0) {
//            mRecyclerView.setVisibility(View.VISIBLE);
//            for (int j = 0; j < datalist.size(); j++) {
//                for (int i = 0; i < itemlist.size(); i++) {
//                    if (itemlist.get(i).getKey().equals(datalist.get(j).getKey())) {
//                        itemlist.remove(i);
//                    }
//                }
//            }
//            datalist.addAll(itemlist);
//        }
//        shouweistate.setBackgroundResource(R.mipmap.weishoufei);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        //设置适配器
//        mAdapter = new GallerySeverpakesAdapter(getActivity(), datalist);
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//        settextView(datalist);
//        mAdapter.setDeteOnItemClickListener(getCPartyFragment());
//
//    }
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
//        double ysje = 0.00;//应收金额总计 hjnysfje
//        double ssje = 0.00;//实收金额总计 serviceCententFee
//        double jmje = 0.00;//减免金额总计 qtjmje
//        double bcje = 0.00;//医保补偿金额总计 xnhbcje
//        double zfje = 0.00;//自付金额总计 sjzfje
//        for (int i = 0; i < datalist.size(); i++) {
//
//            ysje = ysje + Util.getDoubleFromString(datalist.get(i).getHjnysfje());
//            ssje = ssje + Util.getDoubleFromString(datalist.get(i).getServiceCententFee());
//            jmje = jmje + Util.getDoubleFromString(datalist.get(i).getQtjmje());
//            bcje = bcje + Util.getDoubleFromString(datalist.get(i).getXnhbcje());
//            zfje = zfje + Util.getDoubleFromString(datalist.get(i).getSjzfje());
//
//            tv_ysje.setText(Util.getStringdouble(Double.toString(ysje)) + "");
//            tv_ssje.setText(Util.getStringdouble(Double.toString(ssje)) + "");
//            tv_jmje.setText(Util.getStringdouble(Double.toString(jmje)) + "");
//            tv_ybbc.setText(Util.getStringdouble(Double.toString(bcje)) + "");
//            tv_allcount.setText(Util.getStringdouble(Double.toString(zfje)) + "");
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
//
//    @Override
//    public void onItemDelete(ArrayList<SignSeverPakesBeanList> datalist, int position) {
//        double ys = (Util.getDoubleFromString(tv_ysje.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getHjnysfje()));
//        tv_ysje.setText(Util.getStringdouble(Double.toString(ys)) + "");
//        double ss = (Util.getDoubleFromString(tv_ssje.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getServiceCententFee()));
//        tv_ssje.setText(Util.getStringdouble(Double.toString(ss)) + "");
//        double jm = (Util.getDoubleFromString(tv_jmje.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getQtjmje()));
//        tv_jmje.setText(Util.getStringdouble(Double.toString(jm)) + "");
//        double yb = (Util.getDoubleFromString(tv_ybbc.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getXnhbcje()));
//        tv_ybbc.setText(Util.getStringdouble(Double.toString(yb)) + "");
//        double zf = (Util.getDoubleFromString(tv_allcount.getText().toString())) - (Util.getDoubleFromString(datalist.get(position).getSjzfje()));
//        tv_allcount.setText(Util.getStringdouble(Double.toString(zf)) + "");
//    }
//
//    private String key;// Util.getBase64String(key));
//    private String fdSigningDoctorMode;// Util.getBase64String(fdSigningDoctorMode));//（签约方式： 1：按年度签约  2：随到随签）
//    private String statusCode;// Util.getBase64String(statusCode));//1：起草中、2：已提交（已签约）、3：审核通过、4：审核不通过，5解约
//    private String serverPackageName;// Util.getBase64String(serverPackageName));//服务包名称，多个用分号隔开
//    private String isPersonality;// Util.getBase64String(isPersonality));//是否含个性化服务（1是、0否）
//    private String signDT;// Util.getBase64String(signDT));//签约时间
//    private String signMode;// Util.getBase64String(signMode));//签约形式（1、家庭、2、个人）
//    private String signHomeCode;// Util.getBase64String(signHomeCode));//户编号（一户生成一个GUID）
//    private String signDeptName;// Util.getBase64String(signDeptName));//签约机构名称(甲方))
//    private String signDeptCode;// Util.getBase64String(signDeptCode));//签约机构编码
//    private String signDeptPhone;// Util.getBase64String(signDeptPhone));//机构的联系电话
//    private String teamName;// Util.getBase64String(teamName));//团队名称
//    private String teamCode;// Util.getBase64String(teamCode));//团队编码
//    private String signTeamHeaderName;// Util.getBase64String(signTeamHeaderName));//签约团队负责人姓名
//    private String signTeamHeaderCode;// Util.getBase64String(signTeamHeaderCode));//签约团队负责人编码
//    private String signTeamHeaderPhone;// Util.getBase64String(signTeamHeaderPhone));//签约团队负责人电话
//    private String doctorName;// Util.getBase64String(doctorName));//家庭医生姓名
//    private String doctorCode;// Util.getBase64String(doctorCode));//家庭医生编码
//    private String doctorPhone;// Util.getBase64String(doctorPhone));//家庭医生电话
//    private String userName;// Util.getBase64String(userName));//签约人姓名
//    private String sexCode;// Util.getBase64String(sexCode));//签约人性别编号
//    private String cardNo;// Util.getBase64String(cardNo));//签约人身份证号(isUseGuardian = 0  必填))
//    private String guardianCardNo;// Util.getBase64String(guardianCardNo));//监护人身份证号(isUseGuardian = 1  必填))
//    private String isUseGuardian;// Util.getBase64String(isUseGuardian));//是否启用监护人 0 代表本人身份证号码，1代表的是 监护人身份证号码
//    private String phone;// Util.getBase64String(phone));//签约人联系电话
//    private String agriculturalCardNo;// Util.getBase64String(agriculturalCardNo));//医保类型(0 新农合  1职工医保 3居民医保 4其他))
//    private String isHouseholder;// Util.getBase64String(isHouseholder));//是否是户主
//    private String personID;// Util.getBase64String(personID));//个人健康档案唯一标识符
//    private String no;// Util.getBase64String(no));//健康档案号
//    private String isRelation;// Util.getBase64String(isRelation));//是否关联亲属（0未关联1关联))
//    private String filingStatue;// Util.getBase64String(filingStatue));//包是否完成 0 未完成，1已完成
//    private String recordMode;// Util.getBase64String(recordMode));//录入方式（1手动、2农合卡、3医保卡、4身份证）
//    private String inputDeptCode;// Util.getBase64String(inputDeptCode));//行政机构编码
//    private String inputDeptName;// Util.getBase64String(inputDeptName));//行政机构名称
//    private String areaCode;// Util.getBase64String(areaCode));//地区编码
//    private String areaName;// Util.getBase64String(areaName));//地区名称
//    private String addUserCode;// Util.getBase64String(addUserCode));//创建人编码
//    private String addUserName;// Util.getBase64String(addUserName));//创建人名称
//    private String addOrgId;// Util.getBase64String(addOrgId));//添加机构ID
//    private String addDT;// Util.getBase64String(addDT));//录入时间
//    private String updateUserCode;// Util.getBase64String(updateUserCode));//修改人编码
//    private String updateUserName;// Util.getBase64String(updateUserName));//修改人名称
//    private String updateOrgId;// Util.getBase64String(updateOrgId));//修改机构ID
//    private String updateDT;// Util.getBase64String(updateDT));//修改时间
//    private String isFilingStatue;// Util.getBase64String(isFilingStatue));//是否关联健康档案（0未关联1已关联）
//    private String personMold;// Util.getBase64String(personMold));//人员类型 （详细解释在后面）
//    private String docOrganizationKey;// Util.getBase64String(docOrganizationKey));//家庭医生机构编码 (公卫中的机构）
//    private String docOrganizationName;// Util.getBase64String(docOrganizationName));//家庭医生机构名称
//    private String docLoginName;// Util.getBase64String(docLoginName));//签约医生公卫系统登录帐号
//    private String serviceContent;// Util.getBase64String(serviceContent));//服务内容
//    private String isExecute;// Util.getBase64String(isExecute));//是否执行(0执行1未执行))
//    private String docUserID;// Util.getBase64String(docUserID));//家庭医生UserID  注：为基本公共卫生里面的签约医生ID
//    private String isCharge;// Util.getBase64String(isCharge));//是否已收费（1是，0否）
//    private String actualPackageSumFee;// Util.getBase64String(actualPackageSumFee));//实收金额总计(not null))
//    private String packSumFee;// Util.getBase64String(packSumFee));//费用总计
//    private String newRuralCMSFee;// Util.getBase64String(newRuralCMSFee));//新农合补偿金额总计(not null))
//    private String otherReduceFee;// Util.getBase64String(otherReduceFee));//减免金额总计(not null))
//    private String shouldSelfFee;// Util.getBase64String(shouldSelfFee));//应自付金额总计(not null))
//    private String jbggwsState;// Util.getBase64String(jbggwsState)); //是否是基本公共卫生(0 是  1 否))
//    private String signPageYear;// Util.getBase64String(signPageYear));//签约年份
//    private String startExecData;// Util.getBase64String(startExecData));//开始执行时间
//    private String endExecData;// Util.getBase64String(endExecData));//结束执行时间
//    private String death;// Util.getBase64String(death));//死亡状态
//    private String familySysno;// Util.getBase64String(familySysno));//家庭编码
//    private String memberSysno;// Util.getBase64String(memberSysno));//人员编码
//    private String interfaceStatus = "2";// Util.getBase64String(interfaceStatus));//1 代表关联农合成功 2 代表未关联农合成功
//    private String basicPubilcMoney;// Util.getBase64String(basicPubilcMoney));//公共卫生承担金额
//    private String isPrintyjj;// Util.getBase64String(isPrintyjj));//是否打印签约协议（1是0否）
//    private String idType;// Util.getBase64String(idType));//报补类型（农合和医报）
//    private String applyKey;
//    private String signEdit = "0";// (0:在线签约  1 :签约申请)  这个必填
//
//    public void setData() {
//        String serverPackageNametemp = "";
//        String isPersonalitytemp = "";
//        String serviceContenttemp = "";
//        for (int i = 0; i < datalist.size(); i++) {
//            signPageYear = datalist.get(i).getYear();
//            serverPackageNametemp = serverPackageNametemp + datalist.get(i).getFwmc() + " ";
//            if (datalist.get(i).getIsPersonality().contains("1")) {
//                isPersonalitytemp = "1";
//            } else {
//                isPersonalitytemp = "0";
//            }
//            serviceContenttemp = serviceContenttemp + datalist.get(i).getFwmc() + " ";
//        }
//
//        key = UUID.randomUUID().toString();
//        fdSigningDoctorMode = Identity.info.getFdSigningDoctorMode();
//        statusCode = "2";
//        serverPackageName = serverPackageNametemp;
//        isPersonality = isPersonalitytemp;
//        signDT = APartyFragment.qianyuetime; //"2017-07-13 09:31:44"
//        signMode = "1";
//        signHomeCode = UUID.randomUUID().toString();
////////////////////////////////////////////////////////////////////
//        userName = PatientBean.getUsername();
//        sexCode = "2"; //PatientBean.getSexCode()
//        cardNo = PatientBean.getCardNo();
//        guardianCardNo = PatientBean.getGuardianCardNo();
//        isUseGuardian = PatientBean.getIsUseGuardian();
//        phone = PatientBean.getPhone();
//        agriculturalCardNo = PatientBean.getAgriculturalCardNo();
//        isHouseholder = PatientBean.getIsHouseholder();
//        personID = PatientBean.getPersonID();
//        no = PatientBean.getNo();
//        isRelation = "";
//        filingStatue = "0";
//        recordMode = "1";
//        inputDeptCode = Identity.info.getSorgInstitutioncode();
//        inputDeptName = Identity.info.getSorganizationname();
//        areaCode = Identity.info.getSmanagementdivisioncode();
//        areaName = Identity.info.getSmanagementdivisionname();
//        addUserCode = Identity.info.getGuserid();
//        //  addUserName = Identity.info.getSloginname();改为医生的姓名
//        addOrgId = Identity.info.getGuserid();
//        addDT = addDT;
//        updateUserCode = "";
//        updateUserName = "";
//        updateOrgId = "";
//        updateDT = "";
//        isFilingStatue = "0";
//        personMold = "00000000000000000000";
//        docOrganizationKey = Identity.info.getSorganizationkey();
//        docOrganizationName = Identity.info.getSorganizationname();
//        docLoginName = Identity.info.getSloginname();
//        serviceContent = serviceContenttemp;
//        isExecute = "0";
//        //医生后面有一个参数下面
//        isCharge = "0";
//        actualPackageSumFee = tv_ysje.getText().toString();
//        packSumFee = tv_allcount.getText().toString();
//        newRuralCMSFee = tv_ybbc.getText().toString();
//        otherReduceFee = tv_jmje.getText().toString();
//        shouldSelfFee = tv_allcount.getText().toString();
//        jbggwsState = "1";
//        // signPageYear = APartyFragment.getAPartyFragment().qianyuenianfen;//APartyFragment.getAPartyFragment().qianyuetime
//        startExecData = APartyFragment.getAPartyFragment().startime;
//        endExecData = APartyFragment.getAPartyFragment().endtime;
//        death = "1";//未死亡
//        familySysno = PatientBean.getFamilySysno();
//        memberSysno = PatientBean.getMemberNO();
//        interfaceStatus = PatientBean.getInterfaceStatus();
//        basicPubilcMoney = "0.00";
//        isPrintyjj = "0";
//        idType = "1";
//        signEdit = signEdit;// (0:在线签约  1 :签约申请)  这个必填
//        detailInVoList.clear();
////        detailInVoList.addAll(datalist);
//        detailInVoList.addAll(Util.getDeepCopySignSeverPakesBeanList(datalist));
//
//        for (int i = 0; i < detailInVoList.size(); i++) {
//            detailInVoList.get(i).setNhcompensateProjName(Util.getBase64String(detailInVoList.get(i).getNhcompensateProjName()));
//            detailInVoList.get(i).setKey(Util.getBase64String(detailInVoList.get(i).getKey()));
//            detailInVoList.get(i).setFwmc(Util.getBase64String(detailInVoList.get(i).getFwmc()));
//            detailInVoList.get(i).setFwnr(Util.getBase64String(detailInVoList.get(i).getFwnr()));
//            detailInVoList.get(i).setHjnysfje(Util.getBase64String(detailInVoList.get(i).getHjnysfje()));
//            detailInVoList.get(i).setQtjmje(Util.getBase64String(detailInVoList.get(i).getQtjmje()));
//            detailInVoList.get(i).setServiceCententFee(Util.getBase64String(detailInVoList.get(i).getServiceCententFee()));
//            detailInVoList.get(i).setSjzfje(Util.getBase64String(detailInVoList.get(i).getSjzfje()));
//            detailInVoList.get(i).setSydx(Util.getBase64String(detailInVoList.get(i).getSydx()));
//            detailInVoList.get(i).setXnhbcje(Util.getBase64String(detailInVoList.get(i).getXnhbcje()));
//            detailInVoList.get(i).setIsPersonality(Util.getBase64String(detailInVoList.get(i).getIsPersonality()));
//            detailInVoList.get(i).setYear(Util.getBase64String(detailInVoList.get(i).getYear()));
//        }
//
//
//    }
//
//    private submitOnSuccessListener submitSuccessListener;
//
//    private void setSubmitSuccessListener(submitOnSuccessListener listener) {
//        submitSuccessListener = listener;
//    }
//
//
//    @Override
//    public void onLoginSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//        Util.hideGifProgressDialog(getActivity());
//        if (bean.getSuccess().equals("0")) {
//            Util.showToast(getActivity(), "签约成功");
//            submitSuccessListener = InHospitalInspectFragment.getInHospitalInspectFragment();
//            if (submitSuccessListener != null) {
//                submitSuccessListener.sendSuccessinfo();
//            }
//            submitSuccessListener = APartyFragment.getAPartyFragment();
//            if (submitSuccessListener != null) {
//                submitSuccessListener.sendSuccessinfo();
//            }
//            submitSuccessListener = BPartyFragment.getBPartyFragment();
//            if (submitSuccessListener != null) {
//                submitSuccessListener.sendSuccessinfo();
//            }
//            datalist.clear();
//            mAdapter.notifyDataSetChanged();
//            detallText();
//            RxBus.getDefault().post(Constants.EVENT_TYPE_REFRESH_INTELLIGENT_HONOUR_AGREEMENT_REMIND, true);
//        } else {
//            Util.showToast(getActivity(), bean.getMessage());
//        }
//
//
//    }
//
//    @Override
//    public void onLoginFailure(String message) {
//        Util.hideGifProgressDialog(getActivity());
//        Util.showToast(getActivity(), message);
//    }
//
//    @Override
//    public void sendData(SignTeamBean signTeamBean) {
//        signDeptName = signTeamBean.getSignDeptName();
//        signDeptCode = signTeamBean.getSignDeptCode();
//        signDeptPhone = signTeamBean.getSignDeptPhone();
//        teamName = signTeamBean.getTeamName();
//        teamCode = signTeamBean.getTeamCode();
//        signTeamHeaderName = signTeamBean.getSignTeamHeaderName();
//        signTeamHeaderCode = signTeamBean.getSignTeamHeaderCode();
//        signTeamHeaderPhone = signTeamBean.getSignTeamHeaderPhone();
//        doctorName = signTeamBean.getDoctorName();
//        doctorCode = signTeamBean.getDoctorCode();
//        doctorPhone = signTeamBean.getDoctorPhone();
//        docUserID = signTeamBean.getDocUserId();
//        addUserName = signTeamBean.getDoctorName();
//
//    }
//
//    @OnClick(R.id.submit_over)
//    public void submitSignData() {
//        BPartyFragment.getBPartyFragment().setUserData();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        addDT = simpleDateFormat.format(new Date());
//        setData();
//        if (TextUtils.isEmpty(userName)) {
//            Util.showToast(getActivity(), "签约人姓名不可为空");
//        } else if (TextUtils.isEmpty(sexCode)) {
//            Util.showToast(getActivity(), "请选择性别");
//        }
////        else if (TextUtils.isEmpty(cardNo)) {
////            Util.showToast(getActivity(), "签约人身份证不可为空");
////        }
//        else if (TextUtils.isEmpty(phone)) {
//            Util.showToast(getActivity(), "签约人电话不可为空");
//        } else if (TextUtils.isEmpty(no)) {
//            Util.showToast(getActivity(), "签约人档案号码不可为空");
//        } else if (TextUtils.isEmpty(agriculturalCardNo)) {
//            Util.showToast(getActivity(), "请选择医保类型");
//        } else if (TextUtils.isEmpty(signDT)) {
//            Util.showToast(getActivity(), "请选择签约时间");
//        } else if (detailInVoList.size() == 0) {
//            Util.showToast(getActivity(), "请选择服务包");
//        } else {
//            Map<String, Object> map = ParametersFactory.saveFamilyDoctorSign(getActivity(),
//                    key,
//                    fdSigningDoctorMode,
//                    statusCode,
//                    serverPackageName,
//                    isPersonality,
//                    signDT,
//                    signMode,
//                    signHomeCode,
//                    signDeptName,
//                    signDeptCode,
//                    signDeptPhone,
//                    teamName,
//                    teamCode,
//                    signTeamHeaderName,
//                    signTeamHeaderCode,
//                    signTeamHeaderPhone,
//                    doctorName,
//                    doctorCode,
//                    doctorPhone,
//                    userName,
//                    sexCode,
//                    cardNo,
//                    guardianCardNo,
//                    isUseGuardian,
//                    phone,
//                    agriculturalCardNo,
//                    isHouseholder,
//                    personID,
//                    no,
//                    isRelation,
//                    filingStatue,
//                    recordMode,
//                    inputDeptCode,
//                    inputDeptName,
//                    areaCode,
//                    areaName,
//                    addUserCode,
//                    addUserName,
//                    addOrgId,
//                    addDT,
//                    updateUserCode,
//                    updateUserName,
//                    updateOrgId,
//                    updateDT,
//                    isFilingStatue,
//                    personMold,
//                    docOrganizationKey,
//                    docOrganizationName,
//                    docLoginName,
//                    serviceContent,
//                    isExecute,
//                    docUserID,
//                    isCharge,
//                    actualPackageSumFee,
//                    packSumFee,
//                    newRuralCMSFee,
//                    otherReduceFee,
//                    shouldSelfFee,
//                    jbggwsState,
//                    signPageYear,
//                    startExecData,
//                    endExecData,
//                    death,
//                    familySysno,
//                    memberSysno,
//                    interfaceStatus,
//                    basicPubilcMoney,
//                    isPrintyjj,
//                    idType,
//                    applyKey,
//                    signEdit,
//                    detailInVoList);
//            RequestBody body = Util.getBodyFromMap(map);
//            mPresenter.getSingData(body);
//        }
//
//    }
//
//    public void detallText() {
//        tv_ysje.setText("");
//        tv_ssje.setText("");
//        tv_jmje.setText("");
//        tv_ybbc.setText("");
//        tv_allcount.setText("");
//        yuan01.setVisibility(View.GONE);
//        yuan02.setVisibility(View.GONE);
//        yuan03.setVisibility(View.GONE);
//        yuan04.setVisibility(View.GONE);
//        yuan05.setVisibility(View.GONE);
//
//
//    }
//
//}
