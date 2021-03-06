package com.jqsoft.fingertip_health.di.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HbPostBean;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.di.ui.activity.webview.MainActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.optionlayout.HbInputLayout;

import com.jqsoft.fingertip_health.optionlayout.HbOneSetTextOptionsLayout;
import com.jqsoft.fingertip_health.optionlayout.PerSonalTextLayout;
import com.jqsoft.fingertip_health.optionlayout.PerSonalWhithEidtextOptionsLayout;
import com.jqsoft.fingertip_health.optionlayout.TextLayoutWithNo;
import com.jqsoft.fingertip_health.util.DecimalDigitsInputFilter;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;
import com.luck.picture.lib.widget.Constant;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jqsoft.fingertip_health.util.Util.isNumeric;
import static com.jqsoft.fingertip_health.util.Util.isOnlyPointNumber;


public class HBFragment1 extends AbstractFragment {
    @BindView(R.id.sv_content)
    ScrollView sv_content;

    @BindView(R.id.tv_ibm)
    TextView tv_ibm;
    @BindView(R.id.tv_ibm2)
    TextView tv_ibm2;
    @BindView(R.id.weight2)
    EditText weight2;
    View failureView;
    public static final int REQUEST_A = 1;
    private HbOneSetTextOptionsLayout choose_0;
    HighBloodActivity activity;
    HBPGuanLi_PersonInfo hbpGuanLi_personInfo;
    private FlowPopWindow flowPopWindow;
    private String sMessage, sUploadgkey = "0";
    private EditText edit_moke1;
    private TextView tv_tip, tv_tipe2;
    private  TextView tv_zhengzhuang,tv_lost;
    private LinearLayout line_zhengzhuang,line_lost;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private List<FiltrateBean> LostdictList = new ArrayList<>();
    private View topview;
    EditText id_sbp,id_dbp,id_heartRate,height,weight,id_otherSign,otherSymptom;
    TextView id_flwDate,id_no;

    private String codezhizhuang="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hb_one_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

    }



    public void setViewDataDictionaryDataByCode(List<NameValueBeanWithNo> genderList, HbOneSetTextOptionsLayout layout) {
        if (layout != null) {

            layout.setDataList(genderList);
        }
    }


    public HbPostBean getdata() {
        HighBloodActivity activity = (HighBloodActivity) getActivity();
        final ViewPager viewPager = activity.getVpContent();
        if (TextUtils.isEmpty(id_no.getText())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_no.getTop());
            Toast.makeText(getActivity(), "????????????????????????!", Toast.LENGTH_SHORT).show();
        }
//        else if (TextUtils.isEmpty(choose_0.getSingleSelectName())) {
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, choose_0.getTop());
//            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
//        }

//        else if (TextUtils.isEmpty(id_heartRate.getText().toString())) {
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, id_heartRate.getTop());
//            Toast.makeText(getActivity(), "???????????????!", Toast.LENGTH_SHORT).show();
//        }
        else if (TextUtils.isEmpty(height.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, height.getTop());
            Toast.makeText(getActivity(), "???????????????!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(weight.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, weight.getTop());
            Toast.makeText(getActivity(), "???????????????!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(weight2.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, weight2.getTop());
            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(id_flwDate.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_flwDate.getTop());
            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(id_dbp.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_dbp.getTop());
            Toast.makeText(getActivity(), "??????????????????!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(id_sbp.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_sbp.getTop());
            Toast.makeText(getActivity(), "??????????????????!", Toast.LENGTH_SHORT).show();
        }else if (!isNumeric(id_sbp.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_sbp.getTop());
            Toast.makeText(getActivity(), "????????????????????????(???????????????)!", Toast.LENGTH_SHORT).show();
        }else if (!isNumeric(id_dbp.getText().toString())) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, id_dbp.getTop());
            Toast.makeText(getActivity(), "????????????????????????(???????????????)!", Toast.LENGTH_SHORT).show();
        }
//        else if (Double.valueOf(id_sbp.getText().toString())<90||Double.valueOf(id_sbp.getText().toString())>140) {
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, id_sbp.getTop());
//            Toast.makeText(getActivity(), "?????????????????????90????????????140!", Toast.LENGTH_SHORT).show();
//        }

//        else if (Double.valueOf(id_dbp.getText().toString())<60||Double.valueOf(id_dbp.getText().toString())>90) {
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, id_dbp.getTop());
//            Toast.makeText(getActivity(), "?????????????????????60?????????90!", Toast.LENGTH_SHORT).show();
//        }

//        else if (TextUtils.isEmpty(tv_zhengzhuang.getText().toString())){
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, tv_zhengzhuang.getTop());
//            Toast.makeText(getActivity(), "???????????????!", Toast.LENGTH_SHORT).show();
//        }
        else {
            HbPostBean hbPostBean=new HbPostBean();
            hbPostBean.setNo(id_no.getText().toString());
            hbPostBean.setFlwType(choose_0.getSingleSelectValue());
            hbPostBean.setFlwDate(id_flwDate.getText().toString());

            if (!TextUtils.isEmpty(tv_zhengzhuang.getText().toString())){
                hbPostBean.setSymptom(codezhizhuang);
            }

            if (!TextUtils.isEmpty(tv_lost.getText().toString())){
                hbPostBean.setFollowDownReason(codeshifang);
            }

            hbPostBean.setSbp(id_sbp.getText().toString());
            hbPostBean.setDbp(id_dbp.getText().toString());
            hbPostBean.setHeartRate(id_heartRate.getText().toString());
            hbPostBean.setHeight(height.getText().toString());
            hbPostBean.setWeight(weight.getText().toString());
            hbPostBean.setWeightTarget(weight2.getText().toString());
            hbPostBean.setBmi(tv_ibm.getText().toString());
            hbPostBean.setBmiTarget(tv_ibm2.getText().toString());
            hbPostBean.setOtherSign(id_otherSign.getText().toString());
            hbPostBean.setOtherSymptom(otherSymptom.getText().toString());

            return hbPostBean;
        }
        return null;
    }

    public void setTiZhi() {
        String hight =height.getText().toString();
        String might = weight.getText().toString();

        if (!TextUtils.isEmpty(hight) && !TextUtils.isEmpty(might)) {
            double h2 = Util.getFloatFromString(hight) * Util.getFloatFromString(hight);
            double m = Util.getFloatFromString(might);
            h2 = h2 / 10000;
            DecimalFormat df = new DecimalFormat("0.0");
//            ?????????BMI???28.0      ??????:24.0???BMI???28.0
//            ????????????:  18.5???BMI???24.0       ????????????: BMI???18.5
//            if (((float) m / h2) >= 28) {
//                tv_ibm.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//            } else if (28 > ((float) m / h2) && ((float) m / h2) >= 24) {
//                tv_ibm.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//
//            } else if (24 > ((float) m / h2) && ((float) m / h2) >= 18.5) {
//                tv_ibm.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//
//            } else if (18.5 > ((float) m / h2)) {
//                tv_ibm.setText("BMI??????" + df.format((float) m / h2) + " ????????????:????????????");
//
//            }
            tv_ibm.setText(df.format((float) m / h2));
//            et_input11.setEditInputText(df.format((float) m / h2));
        }


    }


    public void setTiZhi2() {
        String hight =height.getText().toString();
        String might =weight2.getText().toString();


        if (!TextUtils.isEmpty(hight) && !TextUtils.isEmpty(might)) {
            double h2 = Util.getFloatFromString(hight) * Util.getFloatFromString(hight);
            double m = Util.getFloatFromString(might);
            h2 = h2 / 10000;
            DecimalFormat df = new DecimalFormat("0.0");

//            ?????????BMI???28.0      ??????:24.0???BMI???28.0
//            ????????????:  18.5???BMI???24.0       ????????????: BMI???18.5
//            if (((float) m / h2) >= 28) {
//                tv_ibm2.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//            } else if (28 > ((float) m / h2) && ((float) m / h2) >= 24) {
//                tv_ibm2.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//
//            } else if (24 > ((float) m / h2) && ((float) m / h2) >= 18.5) {
//                tv_ibm2.setText("BMI??????" + df.format((float) m / h2) + " ????????????:??????");
//
//            } else if (18.5 > ((float) m / h2)) {
//                tv_ibm2.setText("BMI??????" + df.format((float) m / h2) + " ????????????:????????????");
//
//            }
            tv_ibm2.setText(df.format((float) m / h2));

        }


    }

    public String gettime() {
        return "";
    }

    private void setinputtype(HbInputLayout inputLayout) {
        inputLayout.getEditInput().setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});

    }

    private String codeshifang="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hb_one_layout, container, false);
        otherSymptom=(EditText)view.findViewById(R.id.otherSymptom);

        id_no=(TextView)view.findViewById(R.id.id_no);
        id_flwDate=(TextView) view.findViewById(R.id.id_flwDate);
        id_sbp=(EditText)view.findViewById(R.id.id_sbp);
        id_dbp=(EditText)view.findViewById(R.id.id_dbp);
        id_heartRate=(EditText)view.findViewById(R.id.id_heartRate);
        id_otherSign=(EditText)view.findViewById(R.id.id_otherSign);
        activity = (HighBloodActivity) getActivity();
        tv_zhengzhuang=(TextView)view.findViewById(R.id.tv_zhengzhuang);
        choose_0 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_0);
        line_zhengzhuang=(LinearLayout)view.findViewById(R.id.line_zhengzhuang);
        topview=(View)view.findViewById(R.id.topview);
        edit_moke1=(EditText)view.findViewById(R.id.edit_moke1);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);
//        UserLoginInfo info = UserLoginInfo.getInstances();
        line_lost=(LinearLayout)view.findViewById(R.id.line_lost);
        hbpGuanLi_personInfo = activity.getHbpGuanLi_personInfo();
        tv_lost=(TextView)view.findViewById(R.id.tv_lost);
        intdata();
        List<NameValueBeanWithNo> genderList2 = new ArrayList<>();
        genderList2.add(new NameValueBeanWithNo("??????", "1", "1", false));
        genderList2.add(new NameValueBeanWithNo("??????", "2", "1", false));
        genderList2.add(new NameValueBeanWithNo("??????", "3", "1", false));
        setViewDataDictionaryDataByCode(genderList2, choose_0);


        height=(EditText)view.findViewById(R.id.height);
        weight=(EditText)view.findViewById(R.id.weight);
        weight2=(EditText)view.findViewById(R.id.weight2);
        tv_ibm=(TextView)view.findViewById(R.id.tv_ibm);
        tv_ibm2=(TextView)view.findViewById(R.id.tv_ibm2);

        initParam();
        id_flwDate.setText(Util.getCurrentYearMonthDayString());
        final HighBloodActivity activity=(HighBloodActivity)getActivity();
        line_zhengzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(getActivity(), dictList,false);

                flowPopWindow.showAsDropDown(activity.gettopview());
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
                                      codezhizhuang=children.getCode();
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
                                        codezhizhuang=codezhizhuang+","+children.getCode();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            tv_zhengzhuang.setText(value);
                        }else {
                            tv_zhengzhuang.setText("");
                        }

                    }
                });

            }
        });


        line_lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(getActivity(), LostdictList,true);
                flowPopWindow.showAsDropDown(activity.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : LostdictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
                                        codeshifang=children.getCode();
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
                                        codeshifang=codeshifang+","+children.getCode();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){

                            tv_lost.setText(value);
                        }else {

                            tv_lost.setText("");
                        }

                    }
                });
            }
        });

        intIBM();
        Util.setViewListener(id_flwDate, new Runnable() {
            @Override
            public void run() {
                Calendar maxDate = Calendar.getInstance();
                maxDate.setTime(new Date());
                Util.showDateNewDialogWithMaxDate1(getActivity(), id_flwDate.getText().toString(), "nnilBirthday",
                        maxDate,
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                                id_flwDate.setText(s);
                            }
                        },true );
            }
        });
        tv_zhengzhuang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(tv_zhengzhuang.getText().toString())){

                    otherSymptom.setHint("????????????");
                    otherSymptom.setText("");
                    otherSymptom.setEnabled(false);
                }else if ("???".equals(tv_zhengzhuang.getText().toString())){
                    otherSymptom.setHint("????????????");
                    otherSymptom.setText("");
                    otherSymptom.setEnabled(false);
                }else {
                    otherSymptom.setHint("?????????????????????");
                    otherSymptom.setEnabled(true);
                }
            }
        });




        return view;



    }

    private void intdata() {
        HighBloodActivity activity=(HighBloodActivity)getActivity();
        if (activity.getHighBloodListActivityBean()==null){}else {
           HighBloodListActivityBean bean= activity.getHighBloodListActivityBean();
           if (bean.getNo()!=null&&!TextUtils.isEmpty(bean.getNo())){
               id_no.setText(bean.getNo());
           }

        }

    }



    private void initParam() {
        String[] colors = {"???", "????????????", "????????????", "????????????", "????????????", "????????????", "??????????????????", "????????????", "????????????"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("??????");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            cd.setCode(x+1+"");
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);
        dictList.add(fb2);



        String[] lostscode = {"1", "2", "3", "4", "5"};

        String[] losts = {"????????????", "????????????", "??????", "??????", "??????3????????????"};
        FiltrateBean lostfb = new FiltrateBean();
        lostfb.setTypeName("????????????");
        List<FiltrateBean.Children> lostchildrenList = new ArrayList<>();
        for (int x = 0; x < losts.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();

            cd.setValue(losts[x]);
            cd.setCode(x+1+"");
            lostchildrenList.add(cd);
        }
        lostfb.setChildren(lostchildrenList);
        LostdictList.add(lostfb);


    }

    private void intIBM() {
        weight.addTextChangedListener(new TextWatcher() {
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (weight.getText().length() >= 1) {
                    String douhao = weight.getText().toString().substring(0, 1);


                    if (douhao.equals(".")) {

                        Toast.makeText(getActivity(), "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                        weight.setText(" ");
                    } else {
                        selectionStart =weight.getSelectionStart();
                        selectionEnd = weight.getSelectionEnd();

                        if (!isOnlyPointNumber(weight.getText().toString()) && s.length() > 0) {
                            //????????????????????????????????????????????????
                            if (selectionStart > 0) {
                                s.delete(selectionStart - 1, selectionEnd);
                                weight.setText(s);
                                weight.setSelection(s.length());
                            }

                        }


                        setTiZhi();
                    }
                }
            }
        });
        height.addTextChangedListener(new TextWatcher() {
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (height.getText().length() >= 1) {
                    String douhao = height.getText().toString().substring(0, 1);


                    if (douhao.equals(".")) {

                        Toast.makeText(getActivity(), "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                        height.setText("");
                    } else {
                        selectionStart =height.getSelectionStart();
                        selectionEnd = height.getSelectionEnd();

                        if (!isOnlyPointNumber(height.getText().toString()) && s.length() > 0) {
                            //????????????????????????????????????????????????
                            if (selectionStart > 0) {
                                s.delete(selectionStart - 1, selectionEnd);
                                height.setText(s);
                                height.setSelection(s.length());
                            }

                        }

                        setTiZhi2();
                        setTiZhi();
                    }
                }
            }
        });
        weight2.addTextChangedListener(new TextWatcher() {
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (weight2.getText().length() >= 1) {
                    String douhao = weight2.getText().toString().substring(0, 1);


                    if (douhao.equals(".")) {

                        Toast.makeText(getActivity(), "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                        weight2.setText("");
                    } else {
                        selectionStart =weight2.getSelectionStart();
                        selectionEnd = weight2.getSelectionEnd();

                        if (!isOnlyPointNumber(weight2.getText().toString()) && s.length() > 0) {
                            //????????????????????????????????????????????????
                            if (selectionStart > 0) {
                                s.delete(selectionStart - 1, selectionEnd);
                                weight2.setText(s);
                                weight2.setSelection(s.length());
                            }

                        }

                        setTiZhi2();
                    }
                }
            }
        });

    }


}
