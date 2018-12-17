package com.jqsoft.fingertip_health.di.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.DoctorListAdapter;
import com.jqsoft.fingertip_health.adapter.OpadiagnoseAdapter;
import com.jqsoft.fingertip_health.adapter.statistics.DrugListAdapter;
import com.jqsoft.fingertip_health.adapter.statistics.TreatListAdapter;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DiagnosisInVo;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.FeeDetailInVos;
import com.jqsoft.fingertip_health.bean.FeeDetailsCYInVo;
import com.jqsoft.fingertip_health.bean.FeeInVos;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.bean.OutpatientInVo;
import com.jqsoft.fingertip_health.bean.Presciblebean;
import com.jqsoft.fingertip_health.bean.RegisterResultbean;
import com.jqsoft.fingertip_health.bean.TcmInVo;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.DoctorBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.OpoutpatientContractForFingertip;
import com.jqsoft.fingertip_health.di.module.OutpatientModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.OutpatientChargesFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.dialog.fingertip.PatientListDialog;
import com.jqsoft.fingertip_health.feature.PatientSelectListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.SharedPreferencesUtils;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.IDCardUtil;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils.ShareUtils;
import com.jqsoft.fingertip_health.utils2.SharedPreferencesUtil;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jerry on 2018/9/4.
 */

public class OutpatientChargesActivity extends AbstractActivity implements OpoutpatientContractForFingertip.View {

    @BindView(R.id.ll_read_card)
    LinearLayout llReadCard;
    @BindView(R.id.et_person_name)
    EditText etPersonName;
    @BindView(R.id.et_person_id_card_number)
    EditText etPersonIdCardNumber;
    @BindView(R.id.kftime)
    TextView kftime;
    @BindView(R.id.tv_allcount)
    TextView tv_allcount;
    @BindView(R.id.ll_addzhengduan)
    LinearLayout ll_addzhengduan;
    @BindView(R.id.ll_addchufang)
    LinearLayout ll_addchufang;
    @BindView(R.id.zhenduan_recyclerview)
    RecyclerView zhenduan_recyclerview;
    @BindView(R.id.tv_docname)
    TextView tv_docname;
    @BindView(R.id.subimit)
    LinearLayout subimit;
    @BindView(R.id.drugrecyclerview)
    RecyclerView drugrecyclerview;
    @BindView(R.id.treatrecyclerview)
    RecyclerView treatrecyclerview;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    private GDWS_ICD dinfo;
    private ArrayList<GDWS_ICD> opadiagnoseList = new ArrayList<>();
    private OpadiagnoseAdapter opadiagnoseAdapter;
    private OutpatientInVo outpatientInVo = new OutpatientInVo();
    private ArrayList<DiagnosisInVo> diagnosisInVoList = new ArrayList<>();
    private DiagnosisInVo diagnosisInVo = new DiagnosisInVo();
    private List<DoctorBeanForFingertip> docList = new ArrayList<>();
    ArrayList<FeeDetailsCYInVo> caoList = new ArrayList<>();
    @Inject
    OutpatientChargesFingertip OutPresenter;
    PatientListDialog patientListDialog;
    private String patienttype = "0"; //选择农合是 3
    private String patientId;
    private String cardNumber;
    private String birthdate;
    private String age;
    public OutpatientBeanForFingertip outpatientBeanForFingertip;
    private DrugListAdapter drugListAdapter;
    private TreatListAdapter treatListAdapter;
    private ArrayList<DrugInfo> drugInfoArrayList = new ArrayList<>();
    private ArrayList<DrugInfo> caoyaoArrayList = new ArrayList<>();
    private ArrayList<DrugInfo> caoyaoAddDrugList = new ArrayList<>();
    private ArrayList<TreatdirectoryBean> treatdirectoryList = new ArrayList<>();
    private ArrayList<TreatdirectoryBean> treatdirectoryTempList = new ArrayList<>();
    private String visitNumber;
    private ArrayList<FeeInVos> feeInVos = new ArrayList<>(); //诊疗项目
    private FeeInVos feeInVosObj;
    private ArrayList<FeeDetailInVos> feeDetailInVos = new ArrayList<>();//电子处方
    private FeeDetailInVos feeDetailInVosObj;
    private TcmInVo tcmInVo = new TcmInVo();
    private List<String> prescriptionId = new ArrayList<>();
    private String kaiyaoID;
    private String compType;
    private double totalmoney = 0.00;
    private int carNum = 0;
    private ArrayList<DrugInfo> add_drugInfoArrayList = new ArrayList<>();
    private ArrayList<DrugInfo> add_caoyaoArrayList = new ArrayList<>();
    private ArrayList<TreatdirectoryBean> add_treatdirectoryList = new ArrayList<>();
    private String outpatientDoctorCode;
    private String outpatientDoctorName;

    @Override
    protected int getLayoutId() {
        return R.layout.outpatiencharges_activity_layout;
    }

    String idcard;

    @Override
    protected void initData() {
        subimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitNumber = SharedPreferencesUtil.getString(getApplicationContext(), "visitNumber", "");
                idcard = SharedPreferencesUtil.getString(getApplicationContext(), "idcard", "");
                Log.i("jerry", visitNumber);
                if (!TextUtils.isEmpty(visitNumber) && idcard.equals(etPersonIdCardNumber.getText().toString())) {
                    if (feeInVos != null) {
                        feeInVos.clear();
                    }
                    if (feeDetailInVos != null) {
                        feeDetailInVos.clear();
                    }

                    initfeeInVosObj();//诊疗
                    initfeeDetailInVos();//电子处方
                    intitcmInVo();// 草药


                    Map<String, String> map = ParametersFactory.PrescribeForFingertip(getApplicationContext(), visitNumber, feeInVos, feeDetailInVos, tcmInVo);
                    OutPresenter.prescribe(map);
                } else {
                    if (!TextUtils.isEmpty(etPersonName.getText().toString()) && !TextUtils.isEmpty(etPersonIdCardNumber.getText().toString())) {
                        String yse = IDCardUtil.IDCardValidate(etPersonIdCardNumber.getText().toString());
                        if (yse.equals("YES")) {
                            if (opadiagnoseList.size() > 0) {
                                if (carNum > 0) {
                                    for (int i = 0; i < opadiagnoseList.size(); i++) {
                                        diagnosisInVo.setNo(i + "");
                                        diagnosisInVo.setIcdCode(opadiagnoseList.get(i).getCode());
                                        diagnosisInVo.setIcdName(opadiagnoseList.get(i).getName());
                                    }
                                    diagnosisInVoList.add(diagnosisInVo);
                                    initOutpatientInVo();
                                    Map<String, String> map = ParametersFactory.RegisterForFingertip(getApplicationContext(), outpatientInVo, diagnosisInVoList);
                                    OutPresenter.login(map);
                                } else {
                                    Util.showToast(getApplicationContext(), "至少选择一条处方");
                                }

                            } else {
                                Util.showToast(getApplicationContext(), "至少选择一条诊断");
                            }
                        } else {
                            Util.showToast(getApplicationContext(), "请输入合法的身份证号");
                        }
                    } else {
                        Util.showToast(getApplicationContext(), "请选择人员信息");
                    }

                }
            }
        });


        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.setString(getApplicationContext(), "visitNumber", "");
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerUpgradeEvent();
    }

    private String personsex;

    public void initOutpatientInVo() {

        try {
            String id17 = etPersonIdCardNumber.getText().toString().substring(16, 17);
            if (Integer.parseInt(id17) % 2 != 0) {
                personsex = "男";
            } else {
                personsex = "女";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        outpatientInVo.setVisitNumber("");
        outpatientInVo.setPatientId(patientId);
        outpatientInVo.setPatientType(patienttype);
        outpatientInVo.setArchivesNumber("");
        outpatientInVo.setName(etPersonName.getText().toString());
        outpatientInVo.setSex(personsex);
        outpatientInVo.setBirthdate(birthdate);
        outpatientInVo.setIdNumber(etPersonIdCardNumber.getText().toString());
        age = Util.IdNOToAge(etPersonIdCardNumber.getText().toString()) + "岁";
        outpatientInVo.setAge(age.trim());
        outpatientInVo.setMaritalStatus("");
        outpatientInVo.setPhoneNumber("");
        outpatientInVo.setSignDoctor("");
        outpatientInVo.setResidentialAddress("");
        outpatientInVo.setCardNumber(cardNumber);
        outpatientInVo.setOutpatientDoctorCode(outpatientDoctorCode);
        outpatientInVo.setOutpatientDoctorName(outpatientDoctorName);
        outpatientInVo.setCmsMemberPro("");
        outpatientInVo.setFamilySysno("");
        outpatientInVo.setOccupationName("");
        outpatientInVo.setParentName("");
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addOutPatient(new OutpatientModuleForFingertip(this))
//                .addLogin(new SRCLoginModule(this))
                .inject(this);
    }

    @Override
    protected void initView() {
        docList = IdentityManager.getDoctorForFingertip(this);
        compType = IdentityManager.getcmsTypeForFingertip(this);
        outpatientDoctorCode = docList.get(0).getUsername();
        outpatientDoctorName = docList.get(0).getRealName();
        if (compType.equals("102")) {
            compType = "1";
        } else {
            compType = "1101";
        }
        tv_docname.setText(docList.get(0).getRealName());
        kftime.setText(Util.getCurrentYearMonthDayString() + " " + Util.getHourMinSecString());
        tv_docname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        gotoLoginActivity();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        zhenduan_recyclerview.setLayoutManager(layoutmanager);
        LinearLayoutManager layoutmanager2 = new LinearLayoutManager(this);
        drugrecyclerview.setLayoutManager(layoutmanager2);
        LinearLayoutManager layoutmanager3 = new LinearLayoutManager(this);
        treatrecyclerview.setLayoutManager(layoutmanager3);

        Util.setViewListener(llReadCard, new Runnable() {
            @Override
            public void run() {
                final PatientListDialog dialog = new PatientListDialog(OutpatientChargesActivity.this, new PatientSelectListener() {
                    @Override
                    public void patientDidSelect(OutpatientBeanForFingertip bean) {
                        LogUtil.i("OutpatientBeanForFingertip: did select one outpatient");
                        handlePatientSelect(bean);
                        patientListDialog.dismiss();
                    }
                });
                patientListDialog = dialog;
                dialog.show();
                Timer timer = new Timer();

                timer.schedule(new TimerTask()

                               {

                                   public void run()

                                   {

                                       InputMethodManager inputManager = (InputMethodManager) patientListDialog.getEtKeyword().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                                       inputManager.showSoftInput(patientListDialog.getEtKeyword(), 0);

                                   }

                               },

                        80);
            }
        });
        ll_addzhengduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opadiagnoseList.size() > 6) {
                    Toast.makeText(getApplicationContext(), "最多只能添加5条诊断", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(OutpatientChargesActivity.this, SelectChargesActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        });
        ll_addchufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_drugInfoArrayList.clear();
                add_treatdirectoryList.clear();
                add_caoyaoArrayList.clear();
                initListData();
                Intent intent = new Intent(OutpatientChargesActivity.this, ElectronicPrescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("drugInfoArrayList", add_drugInfoArrayList);
                bundle.putSerializable("treatdirectoryList", add_treatdirectoryList);
                bundle.putSerializable("caoyaoArrayList", add_caoyaoArrayList);
                intent.putExtra("carNum", carNum + "");
                intent.putExtra("allmenony", tv_allcount.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, 3);
            }
        });
    }

    public void initListData() {
        add_treatdirectoryList.addAll(treatdirectoryTempList);
        for (int i = 0; i < caoyaoAddDrugList.size(); i++) {
            // 124 电子处方   3  草药两种列表之和
            if (caoyaoAddDrugList.get(i).getType().equals("3")) {
                add_caoyaoArrayList.add(caoyaoAddDrugList.get(i));
            } else {
                add_drugInfoArrayList.add(caoyaoAddDrugList.get(i));

            }
        }
    }

    public void handlePatientSelect(OutpatientBeanForFingertip bean) {
        if (bean != null) {
            outpatientBeanForFingertip = bean;
            patientId = outpatientBeanForFingertip.getMemberId();
            cardNumber = outpatientBeanForFingertip.getMedicalNo();
            birthdate = outpatientBeanForFingertip.getBirthday();
            age = outpatientBeanForFingertip.getMemberAge();
            String sex = outpatientBeanForFingertip.getMemberSex();
            if (sex.equals("1")) {
                personsex = "男";
            } else if (sex.equals("2")) {
                personsex = "女";
            }
            etPersonName.setText(bean.getMemberName());
            etPersonIdCardNumber.setText(bean.getIdCard());
            patienttype = "3";//
        }
    }


    @Override
    protected void loadData() {

    }

    private void gotoLoginActivity() {
        SQLiteDatabase db = Connector.getDatabase();
        int LoginDictionaryDataList = DataSupport.count(GDWS_ICD.class);
        //  int LoginDictionaryDataList1 = DataSupport.count(DictionaryAreaData.class);
        if (LoginDictionaryDataList == 0) {
            DataSupport.deleteAll(GDWS_ICD.class);
            copyDbFile(OutpatientChargesActivity.this, "gdws.db");
//            List<GDWS_DATA_ICD> s=DataSupport.findAll(GDWS_DATA_ICD.class);
//            int sss=s.size();
            //	Util.gotoActivity(this, BphsLoginActivity.class);

        } else {
            //Util.gotoActivity(this, BphsLoginActivity.class);
        }
//        Util.gotoActivity(this, LoginActivityNew.class);
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);
//        Util.gotoActivity(this, LoginActivityNew.class);
//        finish();
    }

    /**
     * 将assets文件夹下/db的本地库拷贝到/data/data/下
     *
     * @param context
     * @param tab_name
     */
    public static void copyDbFile(Context context, String tab_name) {
        InputStream in = null;
        FileOutputStream out = null;
        /**data/data/路径*/
        String path = "/data/data/" + context.getPackageName() + "/databases";
        File file = new File(path + "/" + tab_name);

        try {

            //创建文件夹
            File file_ = new File(path);
            if (!file_.exists())
                file_.mkdirs();

            if (file.exists())//删除已经存在的
                file.deleteOnExit();
            //创建新的文件
            if (!file.exists())
                file.createNewFile();

            in = context.getAssets().open(tab_name); // 从assets目录下复制
            out = new FileOutputStream(file);
            int length = -1;
            byte[] buf = new byte[1024];
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        switch (requestCode) {
            case 2:
                if (data != null) {
                    Bundle MarsBuddle = data.getExtras();
                    dinfo = (GDWS_ICD) MarsBuddle.getSerializable("disInfo");
                    opadiagnoseList.add(dinfo);
                    opadiagnoseAdapter = new OpadiagnoseAdapter(opadiagnoseList, this);
                    zhenduan_recyclerview.setAdapter(opadiagnoseAdapter);
                    opadiagnoseAdapter.notifyDataSetChanged();
                }
                break;
            case 3:
                if (data != null) {
                    Bundle MarsBuddle = data.getExtras();
                    drugInfoArrayList.clear();
                    treatdirectoryList.clear();
                    caoyaoArrayList.clear();
                    caoyaoAddDrugList.clear();
                    treatdirectoryTempList.clear();
                    drugInfoArrayList = (ArrayList<DrugInfo>) MarsBuddle.getSerializable("drugInfoArrayList");
                    treatdirectoryList = (ArrayList<TreatdirectoryBean>) MarsBuddle.getSerializable("treatdirectoryList");
                    caoyaoArrayList = (ArrayList<DrugInfo>) MarsBuddle.getSerializable("caoyaoArrayList");
                    caoyaoAddDrugList.addAll(drugInfoArrayList);
                    caoyaoAddDrugList.addAll(caoyaoArrayList);
                    drugListAdapter = new DrugListAdapter(caoyaoAddDrugList, this);
                    drugrecyclerview.setAdapter(drugListAdapter);
                    drugListAdapter.notifyDataSetChanged();
                    treatdirectoryTempList.addAll(treatdirectoryList);
                    treatListAdapter = new TreatListAdapter(treatdirectoryTempList, this);
                    treatrecyclerview.setAdapter(treatListAdapter);
                    treatListAdapter.notifyDataSetChanged();
                    myhandler.sendEmptyMessage(Constant.MSG_LOAD_OVER);


                }
                break;
        }
    }


    @Override
    public void onOpoutpatientSccusse(HttpResultBaseBeanForFingertip<String> bean) {
        String resultString = bean.getResult();
        RegisterResultbean result = JSON.parseObject(resultString, RegisterResultbean.class);
        visitNumber = result.getVisitNumber();
        SharedPreferencesUtil.setString(this, "visitNumber", visitNumber);
        SharedPreferencesUtil.setString(getApplicationContext(), "idcard", etPersonName.getText().toString());
        if (!TextUtils.isEmpty(visitNumber)) {
            if (feeInVos != null) {
                feeInVos.clear();
            }
            if (feeDetailInVos != null) {
                feeDetailInVos.clear();
            }

            initfeeInVosObj();//诊疗
            initfeeDetailInVos();//电子处方
            intitcmInVo();// 草药


            Map<String, String> map = ParametersFactory.PrescribeForFingertip(getApplicationContext(), visitNumber, feeInVos, feeDetailInVos, tcmInVo);
            OutPresenter.prescribe(map);
        }
    }

    public void initfeeInVosObj() {
        feeInVosObj = new FeeInVos();
        for (int i = 0; i < treatdirectoryTempList.size(); i++) {
            feeInVosObj.setChargeItemsCode(treatdirectoryTempList.get(i).getId());
            feeInVosObj.setChargeFrequency(treatdirectoryTempList.get(i).getChargeFrequency() + "");
            feeInVosObj.setChargeTicketprice(treatdirectoryTempList.get(i).getFeeStandard());
            String price = treatdirectoryTempList.get(i).getFeeStandard();
            double feeTotal = 0.0;
            try {
                double qian = Double.parseDouble(price);
                feeTotal = qian * treatdirectoryTempList.get(i).getChargeFrequency();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            feeInVosObj.setFeeTotal(feeTotal + "");
            feeInVosObj.setIsReim(treatdirectoryTempList.get(i).getReims());
        }
        feeInVos.add(feeInVosObj);
//        if (treatdirectoryTempList.size() == 0) {
//            feeInVos = null;
//        }
    }

    public void initfeeDetailInVos() {

        for (int i = 0; i < caoyaoAddDrugList.size(); i++) {
            feeDetailInVosObj = new FeeDetailInVos();// 124 电子处方   3  草药两种列表之和
            if (!caoyaoAddDrugList.get(i).getType().equals("3")) {
                feeDetailInVosObj.setSerialNumber(i + "");
                feeDetailInVosObj.setDrugId(caoyaoAddDrugList.get(i).getId());
                feeDetailInVosObj.setPrice(caoyaoAddDrugList.get(i).getTempPrice());
                feeDetailInVosObj.setTotal(caoyaoAddDrugList.get(i).getChargeFrequency() + "");
                feeDetailInVosObj.setTotalUnit(caoyaoAddDrugList.get(i).getTempUnit());//getUnitYkName()
//                 String mup = caoyaoAddDrugList.get(i).getTempMpu();
//               if(TextUtils.isEmpty(mup)){
                feeDetailInVosObj.setMpu(caoyaoAddDrugList.get(i).getMpu());
//                }else {
//                    feeDetailInVosObj.setMpu(caoyaoAddDrugList.get(i).getTempMpu());
//                }
                String price = caoyaoAddDrugList.get(i).getTempPrice();//单价
                double priceTotal = 0.0;
                try {
                    double dobleprice = Double.parseDouble(price);
                    priceTotal = dobleprice * caoyaoAddDrugList.get(i).getChargeFrequency();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                feeDetailInVosObj.setTotalAmount(priceTotal + "");
                feeDetailInVosObj.setStockId(caoyaoAddDrugList.get(i).getStockId());
                feeDetailInVosObj.setIsReim(caoyaoAddDrugList.get(i).getIsReim());
                feeDetailInVos.add(feeDetailInVosObj);
            }
        }

    }

    FeeDetailsCYInVo feeDetailsCYInVo;

    public void intitcmInVo() {
        for (int i = 0; i < caoyaoAddDrugList.size(); i++) {
            feeDetailsCYInVo = new FeeDetailsCYInVo();
            if (caoyaoAddDrugList.get(i).getType().equals("3")) {
                feeDetailsCYInVo.setSerialNumber(i + "");
                feeDetailsCYInVo.setDrugId(caoyaoAddDrugList.get(i).getId());
                feeDetailsCYInVo.setPrice(caoyaoAddDrugList.get(i).getTempPrice());
                feeDetailsCYInVo.setTotal(caoyaoAddDrugList.get(i).getChargeFrequency() + "");
                feeDetailsCYInVo.setTotalUnit(caoyaoAddDrugList.get(i).getTempUnit());
                String price = caoyaoAddDrugList.get(i).getTempPrice();//单价
                double priceTotal = 0.0;
                try {
                    double dobleprice = Double.parseDouble(price);
                    priceTotal = dobleprice * caoyaoAddDrugList.get(i).getChargeFrequency();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                feeDetailsCYInVo.setTotalAmount(priceTotal + "");
                feeDetailsCYInVo.setStockId(caoyaoAddDrugList.get(i).getStockId());
                feeDetailsCYInVo.setIsReim(caoyaoAddDrugList.get(i).getIsReim());
                caoList.add(feeDetailsCYInVo);
            } else {
                //  feeDetailsCYInVo = null;
            }
        }
        tcmInVo.setFeeDetailsInVo(caoList);
    }

    @Override
    public void onLoginFailure(String message) {
        Util.showToast(this, message);

    }

    @Override
    public void onPrescribel(HttpResultBaseBeanForFingertip<String> bean) {
        String resultString = bean.getResult();
        prescriptionId.clear();
        Presciblebean result = JSON.parseObject(resultString, Presciblebean.class);
        if (!TextUtils.isEmpty(result.getPrescriptionId())) {
            kaiyaoID = result.getPrescriptionId();
            prescriptionId.add(result.getPrescriptionId());
            Map<String, String> map = ParametersFactory.budgetingForFingertip(getApplicationContext(), prescriptionId, visitNumber, compType);
            OutPresenter.budgeting(map);
        }
    }

    @Override
    public void onPrescribelFailure(String message) {
        Util.showToast(this, message);
    }

    @Override
    public void onbudgeting(HttpResultBaseBeanForFingertip<String> bean) {
        if (bean.getFlag().equals("1")) {
            String resultString = bean.getResult();
            Intent intent = new Intent(OutpatientChargesActivity.this, SettleAccountsActivity.class);//替换结算页面  把结果带过去
            intent.putExtra("resultString", resultString);
            intent.putExtra("outpatientBeanForFingertip", outpatientBeanForFingertip);
            intent.putExtra("compType", compType);
            intent.putExtra("visitNumber", visitNumber);
            intent.putExtra("psername", etPersonName.getText().toString());
            intent.putExtra("cardNo", etPersonIdCardNumber.getText().toString());
            intent.putExtra("prescriptionId", kaiyaoID);

            startActivity(intent);
        }

    }

    @Override
    public void ononbudgetinglFailure(String message) {
        Util.showToast(this, message);
    }

    private Handler myhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.MSG_LOAD_OVER:
                    caculateNum();
                    break;
            }

        }
    };

    public void caculateNum() {
        carNum = caoyaoAddDrugList.size() + treatdirectoryTempList.size();
        double drugmeney = 0.00, treatmeney = 0.00, caoyaomeyney = 0.00;
        double sum1 = 0.00, sum2 = 0.00, sum3 = 0.00;
        for (int i = 0; i < caoyaoAddDrugList.size(); i++) {
            String price = "";
//            if (caoyaoAddDrugList.get(i).getType().equals("3")) {
//                price = caoyaoAddDrugList.get(i).getPrice();
//            } else {
//                price = caoyaoAddDrugList.get(i).getPriceSale();
//            }
            price = caoyaoAddDrugList.get(i).getTempPrice();
            try {
                double saleprice = Double.parseDouble(price);
                drugmeney = saleprice * caoyaoAddDrugList.get(i).getChargeFrequency();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            sum1 = sum1 + drugmeney;
        }
//        for (int i = 0; i < caoyaoArrayList.size(); i++) {
//            String price = caoyaoArrayList.get(i).getPrice();
//            try {
//                double saleprice = Double.parseDouble(price);
//                caoyaomeyney = saleprice * caoyaoArrayList.get(i).getChargeFrequency();
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            sum3 = sum3 + caoyaomeyney;
//        }
        for (int i = 0; i < treatdirectoryTempList.size(); i++) {
            String eeStandard = treatdirectoryTempList.get(i).getFeeStandard();
            try {
                double salepffee = Double.parseDouble(eeStandard);
                treatmeney = salepffee * treatdirectoryTempList.get(i).getChargeFrequency();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            sum2 = sum2 + treatmeney;
        }
        tv_allcount.setText(sum1 + sum2 + sum3 + "");

    }

    public void itemdrug_caculate_jian(String price) {
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count - tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void itemdrug_caculate_add(String price) {
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count + tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void itemtreat_caculate_jian(String price) {
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count - tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void itemtreat_caculate_add(String price) {
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count + tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void item_TotolPrice(int pos, double price, Double num) {
        String listprice = caoyaoAddDrugList.get(pos).getTempPrice();
        try {
            double count = Double.parseDouble(tv_allcount.getText().toString());
            double tprice = Double.parseDouble(listprice);
            double itemprice = tprice * num;
            double newprice = price * num;
            tv_allcount.setText(count - itemprice + newprice + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        drugListAdapter.notifyDataSetChanged();
    }

    // 删除的是两个列表的和  但是一种其中某个没有刷新 提交的还是原来的 是个bug
    public void item_drugcaculate_detel(int pos, String price, String num) {
        caoyaoAddDrugList.remove(pos);
        drugListAdapter.notifyItemRemoved(pos);
        drugListAdapter.notifyDataSetChanged();


        try {
            double number = Double.parseDouble(num);
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count - (number * tv_price) + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        carNum = caoyaoAddDrugList.size() + treatdirectoryTempList.size();


    }

    public void item_treatcaculate_detel(int pos, String price, String num) {
        treatdirectoryTempList.remove(pos);
        treatListAdapter.notifyItemRemoved(pos);
        treatListAdapter.notifyDataSetChanged();
        try {
            double number = Double.parseDouble(num);
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(tv_allcount.getText().toString());
            tv_allcount.setText(count - (number * tv_price) + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        carNum = caoyaoAddDrugList.size() + treatdirectoryTempList.size();
    }

    public void item_diagnose_detel(int pos) {
        opadiagnoseList.remove(pos);
        opadiagnoseAdapter.notifyItemRemoved(pos);
        opadiagnoseAdapter.notifyDataSetChanged();

    }

    CompositeSubscription mCompositeSubscription;

    private void registerUpgradeEvent() {
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE_CLEAR_DATA, Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        clearData();
                    }
                });
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void unregisterUpgradeEvent() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterUpgradeEvent();
    }

    public void clearData() {
        etPersonName.setText("");
        etPersonIdCardNumber.setText("");
        opadiagnoseList.clear();
        opadiagnoseAdapter.notifyDataSetChanged();
        drugInfoArrayList.clear();
        caoyaoArrayList.clear();
        caoyaoAddDrugList.clear();
        drugListAdapter.notifyDataSetChanged();
        treatdirectoryList.clear();
        treatdirectoryTempList.clear();
        treatListAdapter.notifyDataSetChanged();
        outpatientBeanForFingertip = null;
        prescriptionId.clear();
        patienttype = "0";//非农和
        carNum = 0;
        tv_allcount.setText("");
    }

    DoctorListAdapter doctorListAdapter;
    private AlertDialog dialog;

    private void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.select_doctor_layout, null);
        final ListView recyclerView = (ListView) view.findViewById(R.id.recyclerview);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        dialog = builder.create();// 获取dialog
        dialog.show();// 显示对话框
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.55); //设置宽度
        dialog.getWindow().setAttributes(lp);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        //    adapter = new LoginUserAdapter(userInfosList, BphsLoginActivity.this);
        doctorListAdapter = new DoctorListAdapter(OutpatientChargesActivity.this, docList);
        recyclerView.setAdapter(doctorListAdapter);
        doctorListAdapter.notifyDataSetChanged();
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_docname.setText(docList.get(position).getRealName());
                outpatientDoctorCode = docList.get(position).getUsername();//开方医生编码
                outpatientDoctorName = docList.get(position).getRealName();//	开方医生名称
                dialog.dismiss();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        SharedPreferencesUtil.setString(this, "visitNumber", "");
    }

}
