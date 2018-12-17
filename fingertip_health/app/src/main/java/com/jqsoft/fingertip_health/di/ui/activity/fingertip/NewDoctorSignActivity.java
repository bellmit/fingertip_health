package com.jqsoft.fingertip_health.di.ui.activity.fingertip;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.SignManagementReadBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_ph_dict_item;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.di.contract.NewDoctorSignContractForFingertip;
import com.jqsoft.fingertip_health.di.module.NewDoctorSignActivityModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.NewDoctorSignPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.AreaDictionaryUtil;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.IStringRepresentationAndValue;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.dialog.fingertip.SignManagementReadDialog;
import com.jqsoft.fingertip_health.feature.SignManagementReadSelectListener;
import com.jqsoft.fingertip_health.optionlayout.HbOneSetTextOptionsLayout;
import com.jqsoft.fingertip_health.optionlayout.TextLayoutWithNo;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.view.FlowPopWindow;
import com.jqsoft.fingertip_health.view.IDCard;

import net.qiujuer.genius.ui.widget.CheckBox;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 新增家庭医生签约
 * Created by Administrator on 2018-09-14.
 */

public class NewDoctorSignActivity extends AbstractActivity implements NewDoctorSignContractForFingertip.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sv_content)
    ScrollView sv_content;


    @BindView(R.id.tv_sign_doctor_name)
    TextView tvSignDoctorName;
    @BindView(R.id.tv_institution_name)
    TextView tvInstitutionName;
    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;

    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.et_person_name)
    EditText etPersonName;
    @BindView(R.id.et_person_idcard)
    EditText etPersonIdCard;
    @BindView(R.id.tv_person_sex)
    TextView tvPersonSex;
    @BindView(R.id.tv_person_riqi)
    TextView tvPersonRiqi;
    @BindView(R.id.tv_minzu)
    TextView tvMinzu;
    @BindView(R.id.et_lianxi_dianhua)
    EditText etLianxiDianhua;
    @BindView(R.id.choose_habit_residence)
    HbOneSetTextOptionsLayout chooseHabitResidence;
    @BindView(R.id.choose_household_type)
    HbOneSetTextOptionsLayout chooseHouseholdType;
    @BindView(R.id.choose_person_attribute)
    HbOneSetTextOptionsLayout choosePersonAttribute;
    //    @BindView(R.id.tv_person_attribute)
//    TextView tvPersonAttribute;
    //居住地址
    @BindView(R.id.ll_home_address)
    LinearLayout llHomeAddress;
    @BindView(R.id.ll_home_address_input_province)
    LinearLayout llHomeAddressInputProvince;
    @BindView(R.id.et_home_address_input_province)
    EditText etHomeAddressInputProvince;
    @BindView(R.id.iv_home_address_trailing_icon_province)
    ImageView ivHomeAddressTrailingIconProvince;
    @BindView(R.id.ll_home_address_input_city)
    LinearLayout llHomeAddressInputCity;
    @BindView(R.id.et_home_address_input_city)
    EditText etHomeAddressInputCity;
    @BindView(R.id.iv_home_address_trailing_icon_city)
    ImageView ivHomeAddressTrailingIconCity;
    @BindView(R.id.ll_home_address_input_county)
    LinearLayout llHomeAddressInputCounty;
    @BindView(R.id.et_home_address_input_county)
    EditText etHomeAddressInputCounty;
    @BindView(R.id.iv_home_address_trailing_icon_county)
    ImageView ivHomeAddressTrailingIconCounty;
    @BindView(R.id.ll_home_address_input_street)
    LinearLayout llHomeAddressInputTown;
    @BindView(R.id.et_home_address_input_street)
    EditText etHomeAddressInputTown;
    @BindView(R.id.iv_home_address_trailing_icon_street)
    ImageView ivHomeAddressTrailingIconTown;
    @BindView(R.id.ll_home_address_input_village)
    LinearLayout llHomeAddressInputVillage;
    @BindView(R.id.et_home_address_input_village)
    EditText etHomeAddressInputVillage;
    @BindView(R.id.iv_home_address_trailing_icon_village)
    ImageView ivHomeAddressTrailingIconVillage;

    @BindView(R.id.ll_home_address_input_zu)
    LinearLayout llHomeAddressInputzu;
    @BindView(R.id.et_home_address_input_zu)
    EditText etHomeAddressInputzu;
    @BindView(R.id.iv_home_address_trailing_icon_zu)
    ImageView ivHomeAddressTrailingIconzu;

    @BindView(R.id.et_home_address_input_house_number)
    EditText etHomeAddressInputHouseNumber;
    @BindView(R.id.line_attribute)
    LinearLayout line_attribute;
    //    @BindView(R.id.et_home_address_input_extra)
//    EditText etHomeAddressInputExtra;

    @BindView(R.id.cb_confirm)
    CheckBox cbConfirm;
    @BindView(R.id.bt_save)
    AppCompatButton btSave;
    @BindView(R.id.tv_attribute)
    TextView tv_attribute;
    private SignManagementReadDialog readDialog;

    private FlowPopWindow flowPopWindow;


    private String version_num = "";

    private List<FiltrateBean> filtraminzuList = new ArrayList<>();


    private String sexCode = "";
    private String raceCode = "";
    private String residentType = "";
    private String hujiType = "";
    private String houseNumber = "";
    private String personTypes;
    private String properties;
    SignManagementReadBeanForFingertip itemBean;

    @Inject
    NewDoctorSignPresenter mPresenter;
    private List<FiltrateBean> dictList = new ArrayList<>();

    @Override
    public void onSubmitInfoSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        Util.showToast(this, "新增签约成功");
        finish();

    }
    private void initParam(String sSymptomName) {
        dictList.clear();

        String[] colors = {"0~6岁儿童", "孕产妇", "老年人", "高血压", "2型糖尿病", "严重精神障碍", "肺结核"
                , "残疾人", "建档立卡贫困人口", "计划生育特殊家庭", "计划生育特别扶助家庭", "低保五保人员", "一般人群"};
        String[] code = {"0101", "0201", "0301", "0403", "0504", "0601", "0701", "0801", "0901", "1001", "1101", "1201", "9801"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("人员属性");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            cd.setCode(code[x]);
            childrenList2.add(cd);
        }
        if (TextUtils.isEmpty(hujiType)){

        }else {
            String txtattribute="";
                for (int i=0;i<code.length;i++){
                  if (checkLongSame(code[i],sSymptomName)) {
                      childrenList2.get(i).setSelected(true);
                      if (TextUtils.isEmpty(txtattribute)){
                          txtattribute= colors[i];
                      }else {
                          txtattribute=txtattribute+","+colors[i];
                      }
                  }
                }
            tv_attribute.setText(txtattribute);
        }


        fb2.setChildren(childrenList2);
        dictList.add(fb2);

    }
    @Override
    public void onSubmitInfoFailure(String message) {
        Util.showToast(this, message);
//        Util.showToast(this, "新增签约失败");
    }

    private List<String> strCode = new ArrayList<>();

    public void handlePersonSelect(SignManagementReadBeanForFingertip bean) {
        itemBean = bean;
        if (bean != null) {
            //{"code":"0301","color":"grey","isMain":"1","name":"老年人","sname":"老"}
            etPersonName.setText(Util.trimString(bean.getName()));
            etPersonIdCard.setText(Util.trimString(bean.getIdNo()));
            tvPersonSex.setText(Util.trimString(bean.getSexName()));
            sexCode = Util.trimString(bean.getSexCode());
            //出生日期
//            Iterator it = bean.getPropMap().get(0).keySet().iterator();
//            while (it.hasNext()) {
//                String str = (String) it.next();
//                String value = bean.getPropMap().get(0).get("code")+"";
//                System.out.print("key:"+str+"\t");
//                System.out.println("value:"+bean.getPropMap().get(0).get(str));
//            }
            if (bean.getPropMap() != null) {
                if (bean.getPropMap().size() > 0) {
                    for (Map m : bean.getPropMap()) {
                        String code = String.valueOf(m.get("code"));
                        String name = String.valueOf(m.get("name"));
                        strCode.add(code);
                    }
                }
            }

            properties = bean.getProperties();
            tvMinzu.setText(Util.trimString(bean.getNationName()));
            raceCode = Util.trimString(bean.getNationCode());
            etLianxiDianhua.setText(Util.trimString(bean.getPhone()));

            String habitResidenceType = Util.trimString(bean.getResidentType());
            chooseHabitResidence.setSingleSelectValue(habitResidenceType);
            residentType = habitResidenceType;

            String householdType = Util.trimString(bean.getHujiType());
            chooseHouseholdType.setSingleSelectValue(householdType);
            hujiType = householdType;
            String sSymptomName = "";
            for (int i = 0; i < strCode.size(); i++) {
                if (sSymptomName == "") {
                    sSymptomName =  strCode.get(i);
                }else{
                    sSymptomName = sSymptomName + "," + strCode.get(i);
                }
            }

            initParam(sSymptomName);
            List<NameValueBeanWithNo> personAttributeList = new ArrayList<>();
            personAttributeList.add(new NameValueBeanWithNo("0~6岁儿童", "0101", "1", checkLongSame("0101", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("孕产妇", "0201", "1", checkLongSame("0201", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("老年人", "0301", "1", checkLongSame("0301", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("高血压", "0403", "1", checkLongSame("0403", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("2型糖尿病", "0504", "1", checkLongSame("0504", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("严重精神障碍", "0601", "1", checkLongSame("0601", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("肺结核", "0701", "1", checkLongSame("0701", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("残疾人", "0801", "1", checkLongSame("0801", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("建档立卡贫困人口", "0901", "1", checkLongSame("0901", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("计划生育特殊家庭", "1001", "1", checkLongSame("1001", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("计划生育特别扶助家庭", "1101", "1", checkLongSame("1101", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("低保五保人员", "1201", "1", checkLongSame("1201", sSymptomName)));
            personAttributeList.add(new NameValueBeanWithNo("一般人群", "9801", "1", checkLongSame("9801", sSymptomName)));
            setViewDataDictionaryDataByCode(personAttributeList, choosePersonAttribute);
            //人员属性
            if (checkLongSame("其他", sSymptomName)) {
                //   genderList1.add(new NameValueBeanWithNo("输入框", "11", "3", false));
                choosePersonAttribute.setDataList(personAttributeList);
                List<TextLayoutWithNo> textLayoutList = choosePersonAttribute.getTextLayoutList();
//                textLayoutList.get(10).getEditText().setText(newList.get(0).getsSymptomOther());
//                textLayoutList.get(10).getEditText().setEnabled(false);
            } else {
                choosePersonAttribute.setDataList(personAttributeList);
            }

            String areaCode = Util.trimString(bean.getAreavillageCode());
            String areaLevel = AreaDictionaryUtil.getAreaLevelFromAreaCode(areaCode);
            int levelIndex = getDoctorAreaLevelIndex(areaLevel);

            setVariousAddressFromLowest(areaCode, areaLevel, levelIndex);

            ssAreaVillageCode = areaCode;

            houseNumber = Util.trimString(bean.getAreaHouseno());
            etHomeAddressInputHouseNumber.setText(houseNumber);
        }


        line_attribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(NewDoctorSignActivity.this, dictList,false);
                flowPopWindow.showAsDropDown(NewDoctorSignActivity.this.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
//                                        codezhizhuang=children.getCode();
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
//                                        codezhizhuang=codezhizhuang+","+children.getCode();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            tv_attribute.setText(value);
                        }else {
                            tv_attribute.setText("");
                        }

                    }
                });

            }
        });

    }

    private boolean checkLongSame(String str1, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (str2.contains(",")) {
                String[] temp = null;
                temp = str2.split(",");
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equals(str1)) {
                        return true;
                    }
                }

            } else {
                if (str1.equals(str2)) {
                    return true;
                } else {
                    return false;
                }
            }


        }
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_doctor_sign_layout;
    }

    @Override
    protected void initData() {
        version_num = DaggerApplication.getInstance().getVersionNum();

    }


    @Override
    protected void initView() {
        setToolBar(toolbar, "");

        tvSignDoctorName.setText(IdentityManager.getRealNameForFingertip(this));
        tvInstitutionName.setText(IdentityManager.getOrgNameForFingertip(this));
        tvServicePhone.setText(IdentityManager.getPhoneForFingertip(this));

        Util.setViewListener(tvRead, new Runnable() {
            @Override
            public void run() {
                final SignManagementReadDialog dialog = new SignManagementReadDialog(NewDoctorSignActivity.this, new SignManagementReadSelectListener() {
                    @Override
                    public void patientDidSelect(SignManagementReadBeanForFingertip bean) {
                        handlePersonSelect(bean);
                        readDialog.dismiss();
                    }

                });
                readDialog = dialog;
                dialog.show();
//                readDialog.getEtKeyword().setFocusable(true);
//                readDialog.getEtKeyword().setFocusableInTouchMode(true);
//                readDialog.getEtKeyword().requestFocus();
//                InputMethodManager inputManager = (InputMethodManager)readDialog.getEtKeyword().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputManager.showSoftInput(readDialog.getEtKeyword(), 0);


                Timer timer = new Timer();

                timer.schedule(new TimerTask()

                               {

                                   public void run()

                                   {

                                       InputMethodManager inputManager = (InputMethodManager)readDialog.getEtKeyword().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                                       inputManager.showSoftInput(readDialog.getEtKeyword(), 0);

                                   }

                               },

                        80);

            }
        });

        etPersonIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String Idcard = Util.trimString(etPersonIdCard.getText().toString());
                int idCardNumberLength = StringUtils.length(Idcard);
                if (idCardNumberLength == 15 || idCardNumberLength == 18) {
                    //   Toast.makeText(AddFindActivity.this,IDCard.IDCardValidate(Idcard),Toast.LENGTH_SHORT).show();

                    String info = IDCard.IDCardValidate(Idcard);
                    if (info.equals("")) {
                        tvPersonRiqi.setText(IDCard.getbirthdayNew(Idcard));
                        tvPersonSex.setText(IDCard.getSex(Idcard));
                        sexCode = IDCard.getSexCode(Idcard);
                    } else {
                        tvPersonRiqi.setText("");
                        tvPersonSex.setText("");
                        sexCode = "";
                        Toast.makeText(NewDoctorSignActivity.this, info, Toast.LENGTH_SHORT).show();
                    }
//                }

                }
            }
        });

        Util.setViewListener(tvPersonSex, new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NewDoctorSignActivity.this, "请输入正确的身份证号码,会自动填充性别!", Toast.LENGTH_SHORT).show();
            }
        });

        Util.setViewListener(tvPersonRiqi, new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NewDoctorSignActivity.this, "请输入正确的身份证号码,会自动填充出生年月!", Toast.LENGTH_SHORT).show();
            }
        });

        Util.setViewListener(tvMinzu, new Runnable() {
            @Override
            public void run() {
                initminzu();
            }
        });
        initRace();

        List<NameValueBeanWithNo> habitTypeList = new ArrayList<>();
        habitTypeList.add(new NameValueBeanWithNo("户籍", "1", "1", false));
        habitTypeList.add(new NameValueBeanWithNo("非户籍", "2", "1", false));
        setViewDataDictionaryDataByCode(habitTypeList, chooseHabitResidence);
        initParam("");
        List<NameValueBeanWithNo> householdTypeList = new ArrayList<>();
        householdTypeList.add(new NameValueBeanWithNo("农村", "1", "1", false));
        householdTypeList.add(new NameValueBeanWithNo("城镇", "2", "1", false));
        setViewDataDictionaryDataByCode(householdTypeList, chooseHouseholdType);

        List<NameValueBeanWithNo> personAttributeList = new ArrayList<>();
        personAttributeList.add(new NameValueBeanWithNo("0~6岁儿童", "0101", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("孕产妇", "0201", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("老年人", "0301", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("高血压", "0403", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("2型糖尿病", "0504", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("严重精神障碍", "0601", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("肺结核", "0701", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("残疾人", "0801", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("建档立卡贫困人口", "0901", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("计划生育特殊家庭", "1001", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("计划生育特别扶助家庭", "1101", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("低保五保人员", "1201", "1", false));
        personAttributeList.add(new NameValueBeanWithNo("一般人群", "9801", "1", false));
        setViewDataDictionaryDataByCode(personAttributeList, choosePersonAttribute);

        initAreaAddress();

        Util.setViewListener(btSave, new Runnable() {
            @Override
            public void run() {
                submit();
            }
        });


        line_attribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(NewDoctorSignActivity.this, dictList,false);
                flowPopWindow.showAsDropDown(NewDoctorSignActivity.this.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
//                                        codezhizhuang=children.getCode();
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
//                                        codezhizhuang=codezhizhuang+","+children.getCode();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            tv_attribute.setText(value);
                        }else {
                            tv_attribute.setText("");
                        }

                    }
                });

            }
        });



    }

    private void submit() {
        boolean isValid = checkValid();
        if (isValid) {
            String personId = getPersonId();
            String signDoctorPhone = IdentityManager.getPhoneForFingertip(this);
            String areaFullAddress = getAreaFullAddress();
            String nationName = Util.trimString(tvMinzu.getText().toString());
            String birthday = Util.trimString(tvPersonRiqi.getText().toString());
            String name = getName();
            String sexName = Util.trimString(tvPersonSex.getText().toString());
            String houseNumber = Util.trimString(etHomeAddressInputHouseNumber.getText().toString());
            String idCardNumber = getIdCardNumber();
            String phone = Util.trimString(etLianxiDianhua.getText().toString());
            String residentType = Util.trimString(chooseHabitResidence.getSingleSelectValue());
            String hujiType = Util.trimString(chooseHouseholdType.getSingleSelectValue());
//            personTypes = Util.trimString(choosePersonAttribute.getSelectValue());

            String value="";
            for (FiltrateBean fb : dictList) {
                List<FiltrateBean.Children> cdList = fb.getChildren();
                for (int x = 0; x < cdList.size(); x++) {
                    FiltrateBean.Children children = cdList.get(x);
                    if (children.isSelected())
                        if (TextUtils.isEmpty(value)){
//                                        codezhizhuang=children.getCode();
                            value=   children.getCode();
                        }else {
                            value=value+","+children.getCode();
//
                        }

                }
            }
            personTypes=value;

            if (TextUtils.isEmpty(ssAreazuCode)){
                ssAreazuCode=ssAreaVillageCode;

            }
            Map<String, String> map = ParametersFactory.getNewDoctorSignMapForFingertip(this, personId, signDoctorPhone, areaFullAddress,
                    raceCode, nationName, birthday, name, sexCode, sexName, houseNumber, idCardNumber, phone, residentType, hujiType, ssAreaVillageCode, personTypes,ssAreazuCode);
            mPresenter.main(map);

        } else {

        }
    }


    private boolean checkValid() {
        String personId = getPersonId();
        String name = getName();
        String idCardNumber = getIdCardNumber();
        /*else if (StringUtils.isBlank(personId)) {
            Util.showToast(this, "请选择乙方信息");
            return false;
        } */  if (StringUtils.isBlank(name)) {
            sv_content.smoothScrollTo(0, etPersonName.getTop());
            Util.showToast(this, "请输入姓名");
            return false;
        } else if (!isIdCardNumberValid(idCardNumber)) {
            sv_content.smoothScrollTo(0, etPersonIdCard.getTop());
            Util.showToast(this, "请输入合法身份证号码");
            return false;
        } else if (!isSexValid()) {
            sv_content.smoothScrollTo(0, tvPersonSex.getTop());
            Util.showToast(this, "请输入合法性别");
            return false;

        } else if (!isBirthdayValid()) {
            sv_content.smoothScrollTo(0, tvPersonRiqi.getTop());
            Util.showToast(this, "请输入合法出生日期");
            return false;

        } else if (!isRaceValid()) {
            sv_content.smoothScrollTo(0, tvMinzu.getTop());
            Util.showToast(this, "请输入合法民族");
            return false;

        }
//        else if (!isPhoneValid()) {
//            sv_content.smoothScrollTo(0, etLianxiDianhua.getTop());
//            Util.showToast(this, "请输入合法联系电话");
//            return false;
//
//        }
        else if (!isHabitTypeValid()) {
            sv_content.smoothScrollTo(0, chooseHabitResidence.getTop());
            Util.showToast(this, "请选择合法常住类型");
            return false;
        } else if (!isHouseholdTypeValid()) {
            sv_content.smoothScrollTo(0, chooseHouseholdType.getTop());
            Util.showToast(this, "请选择合法户籍类型");
            return false;
        }
//        else if (!isPersonAttributeValid()) {
//            sv_content.smoothScrollTo(0, choosePersonAttribute.getTop());
//            Util.showToast(this, "请选择合法人员属性");
//            return false;
//        }
        else if (!isAreaCodeValid()) {
            sv_content.smoothScrollTo(0, etHomeAddressInputVillage.getTop());
            Util.showToast(this, "请选择合法家庭地址");
            return false;
        }else if (!hasCheckedAgreement()) {
            Util.showToast(this, "请同意服务协议");
            return false;
        }
//        else if(!isHousezuValid()){
//            sv_content.smoothScrollTo(0, etHomeAddressInputzu.getTop());
//            Util.showToast(this, "请输入合法的组");
//            return false;
//        }
//        else if (!isHouseNumberValid()) {
//            sv_content.smoothScrollTo(0, etHomeAddressInputHouseNumber.getTop());
//            Util.showToast(this, "请输入合法门牌号");
//            return false;
//        }
        else  {
            return true;
        }

    }

    private String getAreaFullAddress() {
        String s = etHomeAddressInputProvince.getText().toString()
                + etHomeAddressInputCity.getText().toString()
                + etHomeAddressInputCounty.getText().toString()
                + etHomeAddressInputTown.getText().toString()
                + etHomeAddressInputVillage.getText().toString()
                + etHomeAddressInputzu.getText().toString()
                + Util.trimString(etHomeAddressInputHouseNumber.getText().toString());
        return s;
    }

    private boolean isHouseNumberValid() {
        String s = Util.trimString(etHomeAddressInputHouseNumber.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }
    private boolean isHousezuValid(){
        String s = Util.trimString(etHomeAddressInputzu.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }
    private boolean isAreaCodeValid() {
        String s = Util.trimString(ssAreaVillageCode);
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isPersonAttributeValid() {
        String s = Util.trimString(choosePersonAttribute.getSingleSelectValue());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isHouseholdTypeValid() {
        String s = Util.trimString(chooseHouseholdType.getSingleSelectValue());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isHabitTypeValid() {
        String s = Util.trimString(chooseHabitResidence.getSingleSelectValue());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isPhoneValid() {
        String s = Util.trimString(etLianxiDianhua.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isRaceValid() {
        String s = Util.trimString(raceCode);
//        String s = Util.trimString(tvMinzu.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isBirthdayValid() {
        String s = Util.trimString(tvPersonRiqi.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isSexValid() {
        String s = Util.trimString(sexCode);
//        String s = Util.trimString(tvPersonSex.getText().toString());
        boolean b = !StringUtils.isBlank(s);
        return b;
    }

    private boolean isIdCardNumberValid(String s) {
        String info = IDCard.IDCardValidate(s);
        if ("".equals(info)) {
            return true;
        } else {
            return false;
        }
    }

    private String getIdCardNumber() {
        String s = Util.trimString(etPersonIdCard.getText().toString());
        return s;
    }

    private String getName() {
        String name = Util.trimString(etPersonName.getText().toString());
        return name;
    }

    private String getPersonId() {
        if (itemBean == null) {
            return "";
        } else {
            return Util.trimString(itemBean.getId());
        }
    }

    private boolean hasCheckedAgreement() {
        boolean b = cbConfirm.isChecked();
        return b;
    }

    private void initRace() {
        List<gdws_ph_dict_item> listminzu = DataSupport.where("dict_code=? and version_num=?", "NATION", version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

//        String[] losts5 = {"贫困救助", "城镇职工基本医疗保险", "商业医疗保险", "城镇居民基本医疗保险", "新型农村合作医疗","全自费","全公费","其他"};
//        for(int i=0;i<listminzu.size();i++){
//            losts5.
//        }

        FiltrateBean lostfb5 = new FiltrateBean();
        lostfb5.setTypeName("民族");
//        List<FiltrateBean.Children> lostchildrenList5 = new ArrayList<>();
//        for (int x = 0; x < losts5.length; x++) {
//            FiltrateBean.Children cd = new FiltrateBean.Children();
//            cd.setValue(losts5[x]);
//            lostchildrenList5.add(cd);
//        }
        List<FiltrateBean.Children> lostchildrenList5 = new ArrayList<>();
        for (int x = 0; x < listminzu.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listminzu.get(x).getName());
            cd.setCode(listminzu.get(x).getItem_code());
            lostchildrenList5.add(cd);
        }


        lostfb5.setChildren(lostchildrenList5);
        filtraminzuList.add(lostfb5);

    }

    private void initminzu() {


        flowPopWindow = new FlowPopWindow(this, filtraminzuList, true);
        flowPopWindow.showAsDropDown(this.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value = "";
                String minzuCode = "";
                for (FiltrateBean fb : filtraminzuList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)) {
                                value = children.getValue();
                                minzuCode = children.getCode();
                            } else {
                                value = value + "," + children.getValue();
                                minzuCode = children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)) {
                    tvMinzu.setText(value);
                    raceCode = minzuCode;
                } else {
                    tvMinzu.setText("必选>");
                    raceCode = minzuCode;
                }

            }
        });
    }


    public void setViewDataDictionaryDataByCode(List<NameValueBeanWithNo> genderList, HbOneSetTextOptionsLayout layout) {
        if (layout != null) {

            layout.setDataList(genderList);
        }
    }

    public Toolbar gettopview() {
        return toolbar;
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addNewDoctorSignActivityForFingertip(new NewDoctorSignActivityModuleForFingertip(this))
                .inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    String[] areaLevelArray = new String[]{Constants.AREA_LEVEL_ZU, Constants.AREA_LEVEL_VILLAGE,
            Constants.AREA_LEVEL_STREET,
            Constants.AREA_LEVEL_COUNTY,
            Constants.AREA_LEVEL_CITY,
            Constants.AREA_LEVEL_PROVINCE};

    //家庭住址
    String ssAreaProvinceCode,
            ssAreaCityCode,
            ssAreaCountyCode,
            ssAreaTownCode,
            ssAreaVillageCode,
            ssAreazuCode;
    //            ssAreaGroup;
    int selectedAreaProvinceIndex = -1,
            selectedAreaCityIndex = -1,
            selectedAreaCountyIndex = -1,
            selectedAreaTownIndex = -1,
            selectedAreaVillageIndex = -1,
            selectedAreazuIndex = -1;
    List<gdws_sys_area> areaProvinceList = new ArrayList<>();
    List<gdws_sys_area> areaCityList = new ArrayList<>();
    List<gdws_sys_area> areaCountyList = new ArrayList<>();
    List<gdws_sys_area> areaTownList = new ArrayList<>();
    List<gdws_sys_area> areaVillageList = new ArrayList<>();
    List<gdws_sys_area> areazuList = new ArrayList<>();


    private void initAreaAddress() {
        areaProvinceList = AreaDictionaryUtil.getAreaDataBeanListFromAreaLevel(Constants.AREA_LEVEL_PROVINCE);
        initAreaProvince(selectedAreaProvinceIndex);
        Util.setViewListener(llHomeAddressInputProvince, new Runnable() {
            @Override
            public void run() {
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaProvinceList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择省份", null,
                        realList, selectedAreaProvinceIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreaProvince(which);
                                return false;
                            }
                        });
            }
        });

        areaCityList = getAreaCityList();
        initAreaCity(selectedAreaCityIndex);
        Util.setViewListener(llHomeAddressInputCity, new Runnable() {
            @Override
            public void run() {
                areaCityList = getAreaCityList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaCityList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择市", null,
                        realList, selectedAreaCityIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreaCity(which);
                                return false;
                            }
                        });
            }
        });

        areaCountyList = getAreaCountyList();
        initAreaCounty(selectedAreaCountyIndex);
        Util.setViewListener(llHomeAddressInputCounty, new Runnable() {
            @Override
            public void run() {
                areaCountyList = getAreaCountyList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaCountyList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择县区", null,
                        realList, selectedAreaCountyIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreaCounty(which);
                                return false;
                            }
                        });
            }
        });

        areaTownList = getAreaTownList();
        initAreaTown(selectedAreaTownIndex);
        Util.setViewListener(llHomeAddressInputTown, new Runnable() {
            @Override
            public void run() {
                areaTownList = getAreaTownList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaTownList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择乡镇", null,
                        realList, selectedAreaTownIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreaTown(which);
                                return false;
                            }
                        });
            }
        });

        areaVillageList = getAreaVillageList();
        initAreaVillage(selectedAreaVillageIndex);
        Util.setViewListener(llHomeAddressInputVillage, new Runnable() {
            @Override
            public void run() {
                areaVillageList = getAreaVillageList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaVillageList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择村", null,
                        realList, selectedAreaVillageIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreaVillage(which);
                                return false;
                            }
                        });
            }
        });


        areazuList = getAreazuList();
        initAreazu(selectedAreazuIndex);
        Util.setViewListener(llHomeAddressInputzu, new Runnable() {
            @Override
            public void run() {
                areazuList = getAreazuList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(areazuList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(NewDoctorSignActivity.this, "请选择组", null,
                        realList, selectedAreazuIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreazu(which);
                                return false;
                            }
                        });
            }
        });


        String doctorAreaCode = getDoctorAreaCode();
        String doctorAreaLevel = AreaDictionaryUtil.getAreaLevelFromAreaCode(doctorAreaCode);
        int levelIndex = getDoctorAreaLevelIndex(doctorAreaLevel);

        setVariousAddressFromLowest(doctorAreaCode, doctorAreaLevel, levelIndex);
    }

    private void setVariousAddressFromLowest(String areaCode, String areaLevel, int levelIndex) {
        View[] homeAddressLayoutArray = new View[]{llHomeAddressInputzu, llHomeAddressInputVillage,
                llHomeAddressInputTown,
                llHomeAddressInputCounty,
                llHomeAddressInputCity,
                llHomeAddressInputProvince};
        View[] homeAddressEditTextArray = new View[]{etHomeAddressInputzu, etHomeAddressInputVillage,
                etHomeAddressInputTown,
                etHomeAddressInputCounty,
                etHomeAddressInputCity,
                etHomeAddressInputProvince};

        String tempAreaCode = areaCode;
        String tempAreaLevel = areaLevel;
        View tempAreaLayout = null;
        EditText tempEditText = null;

        if (levelIndex >= 0 && levelIndex < areaLevelArray.length) {
            for (int i = levelIndex; i < areaLevelArray.length; ++i) {
                tempAreaLevel = areaLevelArray[i];
                tempAreaLayout = homeAddressLayoutArray[i];
                tempEditText = (EditText) homeAddressEditTextArray[i];
                gdws_sys_area bean = AreaDictionaryUtil.getAreaDataBeanFromAreaCodeAndAreaLevel(tempAreaCode, tempAreaLevel);
                if (bean != null) {
                    tempAreaLayout.setEnabled(false);
                    tempEditText.setText(bean.getName());
                    setHomeAddressCode(tempAreaLevel, bean.getCode());
                    tempAreaCode = bean.getP_code();
                } else {
                    break;
                }
            }
        }
    }

    private void setHomeAddressCode(String level, String code) {
        level = Util.trimString(level);
        code = Util.trimString(code);
        if (Constants.AREA_LEVEL_PROVINCE.equals(level)) {
            ssAreaProvinceCode = code;
        } else if (Constants.AREA_LEVEL_CITY.equals(level)) {
            ssAreaCityCode = code;
        } else if (Constants.AREA_LEVEL_COUNTY.equals(level)) {
            ssAreaCountyCode = code;
        } else if (Constants.AREA_LEVEL_STREET.equals(level)) {
            ssAreaTownCode = code;
        } else if (Constants.AREA_LEVEL_VILLAGE.equals(level)) {
            ssAreaVillageCode = code;
        }
    }


    private void initAreaProvince(int index) {
        if (index >= 0 && index < areaProvinceList.size()) {
            selectedAreaProvinceIndex = index;
            gdws_sys_area bean = areaProvinceList.get(index);
            ssAreaProvinceCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputProvince.setText(name);

            clearAreaCityStatus();
        }
    }

    private void initAreaCity(int index) {
        if (index >= 0 && index < areaCityList.size()) {
            selectedAreaCityIndex = index;
            gdws_sys_area bean = areaCityList.get(index);
            ssAreaCityCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputCity.setText(name);

            clearAreaCountyStatus();
        }
    }

    private void initAreaCounty(int index) {
        if (index >= 0 && index < areaCountyList.size()) {
            selectedAreaCountyIndex = index;
            gdws_sys_area bean = areaCountyList.get(index);
            ssAreaCountyCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputCounty.setText(name);

            clearAreaTownStatus();
        }
    }

    private void initAreaTown(int index) {
        if (index >= 0 && index < areaTownList.size()) {
            selectedAreaTownIndex = index;
            gdws_sys_area bean = areaTownList.get(index);
            ssAreaTownCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputTown.setText(name);

            clearAreaVillageStatus();
        }
    }

    private void initAreaVillage(int index) {
        if (index >= 0 && index < areaVillageList.size()) {
            selectedAreaVillageIndex = index;
            gdws_sys_area bean = areaVillageList.get(index);
            ssAreaVillageCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputVillage.setText(name);

            clearAreazuStatus();
        }
    }

    private void initAreazu(int index) {
        if (index >= 0 && index < areazuList.size()) {
            selectedAreazuIndex = index;
            gdws_sys_area bean = areazuList.get(index);
            ssAreazuCode = bean.getCode();
            String name = bean.getName();
            etHomeAddressInputzu.setText(name);
        }
    }

    private void clearAreaCityStatus() {
        selectedAreaCityIndex = -1;
        ssAreaCityCode = Constants.EMPTY_STRING;
        areaCityList.clear();
        etHomeAddressInputCity.setText(Constants.EMPTY_STRING);
        clearAreaCountyStatus();
    }

    private void clearAreaCountyStatus() {
        selectedAreaCountyIndex = -1;
        ssAreaCountyCode = Constants.EMPTY_STRING;
        areaCountyList.clear();
        etHomeAddressInputCounty.setText(Constants.EMPTY_STRING);
        clearAreaTownStatus();
    }

    private void clearAreaTownStatus() {
        selectedAreaTownIndex = -1;
        ssAreaTownCode = Constants.EMPTY_STRING;
        areaTownList.clear();
        etHomeAddressInputTown.setText(Constants.EMPTY_STRING);
        clearAreaVillageStatus();
    }

    private void clearAreaVillageStatus() {
        selectedAreaVillageIndex = -1;
        ssAreaVillageCode = Constants.EMPTY_STRING;
        areaVillageList.clear();
        etHomeAddressInputVillage.setText(Constants.EMPTY_STRING);
        clearAreazuStatus();
    }

    private void clearAreazuStatus() {
        selectedAreazuIndex = -1;
        ssAreazuCode = Constants.EMPTY_STRING;
        areazuList.clear();
        etHomeAddressInputzu.setText(Constants.EMPTY_STRING);
    }

    private List<gdws_sys_area> getAreaCityList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_CITY,
                ssAreaProvinceCode);
    }

    private List<gdws_sys_area> getAreaCountyList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_COUNTY,
                ssAreaCityCode);
    }

    private List<gdws_sys_area> getAreaTownList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_STREET,
                ssAreaCountyCode);
    }

    private List<gdws_sys_area> getAreaVillageList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_VILLAGE,
                ssAreaTownCode);
    }

    private List<gdws_sys_area> getAreazuList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_ZU,
                ssAreaVillageCode);
    }

    private String getDoctorAreaCode() {
        String result = DaggerApplication.getInstance().getAreaId();
        return result;
    }


    private int getDoctorAreaLevelIndex(String level) {
        level = Util.trimString(level);
        int index = -1;
        for (int i = 0; i < areaLevelArray.length; ++i) {
            String s = areaLevelArray[i];
            if (level.equals(s)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean checkSame(String str1, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (str1.equals(str2)) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }
}
