package com.jqsoft.fingertip_health.di.ui.fragment.personjiandang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;

import com.jqsoft.fingertip_health.bean.fingertip.PersonChanBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPostBean;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_ph_dict_item;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.optionlayout.HbInputLayout;
import com.jqsoft.fingertip_health.optionlayout.HbOneSetTextOptionsLayout;
import com.jqsoft.fingertip_health.util.DecimalDigitsInputFilter;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.util.Utils;
import com.jqsoft.fingertip_health.view.FlowPopWindow;
import com.jqsoft.fingertip_health.view.IDCard;

import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class PersonFragment1 extends Fragment implements View.OnClickListener{
    @BindView(R.id.sv_content)
    ScrollView sv_content;

    View failureView;
    public static final int REQUEST_A = 1;
    private HbOneSetTextOptionsLayout choose_1,choose_2;
    PersonJiandangActivity activity;

    private View topview;


    private LinearLayout ll_xuexing,ll_wenhua,ll_zhiye,ll_hunyin,ll_yiliao,ll_minzu;
    private TextView tv_xuexing,tv_wenhua,tv_zhiye,tv_hunyin,tv_yiliao,tv_minzu;
    private String version_num="";
    private EditText et_person_name,et_person_idcard,et_person_danwei,et_benren_dianhua,et_lianxiren_name,et_lianxi_dianhua,et_bianhao;
    private TextView tv_person_riqi,tv_person_sex,tv_jiaoyan,tv_shoushu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void setViewDataDictionaryDataByCode(List<NameValueBeanWithNo> genderList, HbOneSetTextOptionsLayout layout) {
        if (layout != null) {

            layout.setDataList(genderList);
        }
    }



    public List<NameValueBeanWithNo> getdata() {

        return null;
    }



    public String gettime() {
        return "";
    }

    private void setinputtype(HbInputLayout inputLayout) {
        inputLayout.getEditInput().setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1)});

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_one_layout, container, false);
        activity = (PersonJiandangActivity) getActivity();
        topview=(View)view.findViewById(R.id.topview);

        et_person_name=(EditText) view.findViewById(R.id.et_person_name);
        et_person_idcard=(EditText) view.findViewById(R.id.et_person_idcard);
        et_person_danwei=(EditText) view.findViewById(R.id.et_person_danwei);
        et_benren_dianhua=(EditText) view.findViewById(R.id.et_benren_dianhua);
        et_lianxiren_name=(EditText) view.findViewById(R.id.et_lianxiren_name);
        et_lianxi_dianhua=(EditText) view.findViewById(R.id.et_lianxi_dianhua);
        tv_person_riqi=(TextView) view.findViewById(R.id.tv_person_riqi);
        tv_person_sex=(TextView) view.findViewById(R.id.tv_person_sex);
        tv_jiaoyan=(TextView) view.findViewById(R.id.tv_jiaoyan);
        tv_shoushu=(TextView) view.findViewById(R.id.tv_shoushu);
        sv_content=(ScrollView)view.findViewById(R.id.sv_content);
        et_bianhao=(EditText)view.findViewById(R.id.et_bianhao);

//        choose_0 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_0);
        choose_1 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_1);
        choose_2 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_2);
        ll_xuexing=(LinearLayout)view.findViewById(R.id.ll_xuexing);
        tv_xuexing=(TextView)view.findViewById(R.id.tv_xuexing);

        ll_minzu=(LinearLayout)view.findViewById(R.id.ll_minzu);
        tv_minzu=(TextView)view.findViewById(R.id.tv_minzu);

        ll_wenhua=(LinearLayout)view.findViewById(R.id.ll_wenhua);
        tv_wenhua=(TextView)view.findViewById(R.id.tv_wenhua);

        ll_zhiye=(LinearLayout)view.findViewById(R.id.ll_zhiye);
        tv_zhiye=(TextView)view.findViewById(R.id.tv_zhiye);

        ll_hunyin=(LinearLayout)view.findViewById(R.id.ll_hunyin);
        tv_hunyin=(TextView)view.findViewById(R.id.tv_hunyin);

        ll_yiliao=(LinearLayout)view.findViewById(R.id.ll_yiliao);
        tv_yiliao=(TextView)view.findViewById(R.id.tv_yiliao);
        version_num= DaggerApplication.getInstance().getVersionNum();


//        List<gdws_ph_dict_item>  listsex= DataSupport.where("dict_code=? and version_num=?","SEX_CODE",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
//        List<NameValueBeanWithNo> genderList0 = new ArrayList<>();
//        genderList0.clear();
//        for(int i=0;i<listsex.size();i++){
//            genderList0.add(new NameValueBeanWithNo(listsex.get(i).getName(), listsex.get(i).getItem_code(), "1", false));
//        }
////        genderList0.add(new NameValueBeanWithNo("男", "1", "1", false));
////        genderList0.add(new NameValueBeanWithNo("女", "2", "1", false));
//        setViewDataDictionaryDataByCode(genderList0, choose_0);


        List<NameValueBeanWithNo> genderList1 = new ArrayList<>();

        genderList1.add(new NameValueBeanWithNo("户籍", "1", "1", false));
        genderList1.add(new NameValueBeanWithNo("非户籍", "2", "1", false));
        setViewDataDictionaryDataByCode(genderList1, choose_1);

        List<NameValueBeanWithNo> genderList2 = new ArrayList<>();
        genderList2.add(new NameValueBeanWithNo("阴性", "1", "1", false));
        genderList2.add(new NameValueBeanWithNo("阳性", "2", "1", false));
        genderList2.add(new NameValueBeanWithNo("不详", "1", "1", false));
        setViewDataDictionaryDataByCode(genderList2, choose_2);

        initParam();
        ll_xuexing.setOnClickListener(this);
        ll_wenhua.setOnClickListener(this);
        ll_zhiye.setOnClickListener(this);
        ll_hunyin.setOnClickListener(this);
        ll_yiliao.setOnClickListener(this);
        ll_minzu.setOnClickListener(this);
        tv_person_riqi.setOnClickListener(this);
        tv_person_sex.setOnClickListener(this);
        tv_jiaoyan.setOnClickListener(this);
        tv_shoushu.setOnClickListener(this);
        et_benren_dianhua.setOnClickListener(this);
        et_lianxi_dianhua.setOnClickListener(this);
        et_person_danwei.clearFocus();


        et_benren_dianhua.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                       @Override
                                                       public void onFocusChange(View v, boolean hasFocus) {
                                                           if (hasFocus) {
                                                               // 此处为得到焦点时的处理内容
                                                           } else {
                                                               shouji();
                                                           }
                                                       }
                                                   });

        et_lianxi_dianhua.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    shouji1();
                }
            }
        });

        et_benren_dianhua.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int size= s.toString().length();
                if(size==11 ){
                    if(s.toString().indexOf("-")==-1) {
                        boolean isPhone = Utils.isMobileNO(s.toString());
                        if (isPhone) {

                        } else {
                            Toast.makeText(getActivity(), "请输入正确的本人手机号码", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(size==12 || size==13){
                    if(s.toString().indexOf("-")!=-1){
                        boolean isTel= Utils.IsTelephone(s.toString());
                        if(  isTel){

                        }else {
                            Toast.makeText(getActivity(),"请输入正确的本人电话",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "请输入正确的本人电话", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        et_lianxi_dianhua.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int size= s.toString().length();
                if(size==11 ){
                    if(s.toString().indexOf("-")==-1) {
                        boolean isPhone = Utils.isMobileNO(s.toString());
                        if (isPhone) {

                        } else {
                            Toast.makeText(getActivity(), "请输入正确的联系人手机号码", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(size==12 || size==13){
                    if(s.toString().indexOf("-")!=-1){
                        boolean isTel= Utils.IsTelephone(s.toString());
                        if(  isTel){

                        }else {
                            Toast.makeText(getActivity(),"请输入正确的联系人电话",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "请输入正确的联系人电话", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        et_person_idcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String Idcard=et_person_idcard.getText().toString();
                if (et_person_idcard.length()==18){
                    //   Toast.makeText(AddFindActivity.this,IDCard.IDCardValidate(Idcard),Toast.LENGTH_SHORT).show();

                    String info= IDCard.IDCardValidate(Idcard);
                    if (info.equals("")){
                        tv_person_riqi.setText( IDCard.getbirthdayNew(Idcard));
                        tv_person_sex.setText(IDCard.getSex(Idcard));
                    }else{
                        tv_person_riqi.setText("");
                        tv_person_sex.setText("");
                        Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });



//        view.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    if(getActivity().getCurrentFocus()!=null && getActivity().getCurrentFocus().getWindowToken()!=null){
//                        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                    }
//                }
//                return false;
//            }
//        });

//        PersonJiandangActivity activity = (PersonJiandangActivity) getActivity();
//        String flag = activity.getflag();
//        if(("1").equals(flag)){
//            setchakan();
//        }else {
//
//        }

        return view;



    }

    private void shouji(){
        String s= et_benren_dianhua.getText().toString().trim();
        int size= et_benren_dianhua.getText().toString().length();
        if(size==11 ){
            if(s.toString().indexOf("-")==-1) {
                boolean isPhone = Utils.isMobileNO(s.toString());
                if (isPhone) {

                } else {
                    Toast.makeText(getActivity(), "请输入正确的本人手机号码", Toast.LENGTH_SHORT).show();
                }
            }

        }else if(size==12 || size==13) {
            if (s.toString().indexOf("-") != -1) {
                boolean isTel = Utils.IsTelephone(s.toString());
                if (isTel) {

                } else {
                    Toast.makeText(getActivity(), "请输入正确的本人电话", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "请输入正确的本人电话", Toast.LENGTH_SHORT).show();
            }
        }else if(size==0){

        }else {
            et_benren_dianhua.setText("");
            et_benren_dianhua.setFocusable(true);
            et_benren_dianhua.setFocusableInTouchMode(true);
            et_benren_dianhua.requestFocus();
            Toast.makeText(getActivity(), "本人电话不正确，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    private void shouji1(){
        String s= et_lianxi_dianhua.getText().toString().trim();
        int size= et_lianxi_dianhua.getText().toString().length();
        if(size==11 ){
            if(s.toString().indexOf("-")==-1) {
                boolean isPhone = Utils.isMobileNO(s.toString());
                if (isPhone) {

                } else {
                    Toast.makeText(getActivity(), "请输入正确的联系人手机号码", Toast.LENGTH_SHORT).show();
                }
            }

        }else if(size==12 || size==13) {
            if (s.toString().indexOf("-") != -1) {
                boolean isTel = Utils.IsTelephone(s.toString());
                if (isTel) {

                } else {
                    Toast.makeText(getActivity(), "请输入正确的联系人电话", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "请输入正确的联系人电话", Toast.LENGTH_SHORT).show();
            }
        }else if(size==0){

        }else {
            et_lianxi_dianhua.setText("");
            et_lianxi_dianhua.setFocusable(true);
            et_lianxi_dianhua.setFocusableInTouchMode(true);
            et_lianxi_dianhua.requestFocus();
            Toast.makeText(getActivity(), "联系人电话不正确，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }


    private String myid="";

    public PersonPostBean getdata1() {

        String xingming =et_person_name.getText().toString().trim();
        String shenfenzheng =et_person_idcard.getText().toString().trim();
        String person_sex =tv_person_sex.getText().toString().trim();
        String person_riqi =tv_person_riqi.getText().toString().trim();
        String person_danwei =et_person_danwei.getText().toString().trim();
        String changzhu=choose_1.getSingleSelectName().trim();
        String minzu =tv_minzu.getText().toString().trim();

        PersonJiandangActivity activity = (PersonJiandangActivity) getActivity();
        final ViewPager viewPager = activity.getVpContent();
        if (TextUtils.isEmpty(xingming)) {
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, et_person_name.getTop());
            Toast.makeText(getActivity(), "请输入居民姓名!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(shenfenzheng)){
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, et_person_idcard.getTop());
            Toast.makeText(getActivity(), "请输入身份证号!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(person_sex)){
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, tv_person_sex.getTop());
            Toast.makeText(getActivity(), "请输入性别!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(person_riqi)){
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, tv_person_riqi.getTop());
            Toast.makeText(getActivity(), "请输入出生日期!", Toast.LENGTH_SHORT).show();
        }
//        else if(TextUtils.isEmpty(person_danwei)){
//            viewPager.setCurrentItem(0);
//            sv_content.smoothScrollTo(0, et_person_danwei.getTop());
//            Toast.makeText(getActivity(), "请输入工作单位!", Toast.LENGTH_SHORT).show();
//        }
        else if(TextUtils.isEmpty(changzhu)){
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, choose_1.getTop());
            Toast.makeText(getActivity(), "请选择常住类型!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(minzu)){
            viewPager.setCurrentItem(0);
            sv_content.smoothScrollTo(0, tv_minzu.getTop());
            Toast.makeText(getActivity(), "请选择民族!", Toast.LENGTH_SHORT).show();
        }else {
            PersonPostBean hbPostBean=new PersonPostBean();
            hbPostBean.setName(xingming);
            hbPostBean.setIdNo(shenfenzheng);
            String mysex=tv_person_sex.getText().toString().trim();
            if(mysex.equals("男")){
                hbPostBean.setSexCode("0");
                hbPostBean.setSexName("男");
            }else if(mysex.equals("女")){
                hbPostBean.setSexCode("1");
                hbPostBean.setSexName("女");
            }else {
                hbPostBean.setSexCode("2");
                hbPostBean.setSexName("未知的性别");
            }
            hbPostBean.setNo(et_bianhao.getText().toString().trim());
            hbPostBean.setId(myid);
            hbPostBean.setBirthday(person_riqi);
            hbPostBean.setWorkUnit(person_danwei);
            hbPostBean.setContactName(et_lianxiren_name.getText().toString().trim());
            hbPostBean.setContactPhone(et_lianxi_dianhua.getText().toString().trim());
            hbPostBean.setPhone(et_benren_dianhua.getText().toString().trim());
            hbPostBean.setResidentType(choose_1.getSingleSelectValue());
            hbPostBean.setNationCode(codeminzu);
            hbPostBean.setNationName(tv_minzu.getText().toString());

            hbPostBean.setAbobloodCode(codexuexing);
            hbPostBean.setAbobloodName(tv_xuexing.getText().toString());
            hbPostBean.setRhbloodName(choose_2.getSingleSelectName());
            hbPostBean.setCultureCode(codewenhua);
            hbPostBean.setCultureName(tv_wenhua.getText().toString());
            hbPostBean.setOccupationCode(codezhiye);
            hbPostBean.setOccupationName(tv_zhiye.getText().toString());
            hbPostBean.setMarryCode(codehunyin);
            hbPostBean.setInsType(codeyiliao);
//            hbPostBean.setNationCode(codeminzu);
//            hbPostBean.setNationCode(codeminzu);


            return hbPostBean;
        }
        return null;
    }

    private List<FiltrateBean> filtraxuexingList = new ArrayList<>();
    private List<FiltrateBean> filtrawenhuaList = new ArrayList<>();
    private List<FiltrateBean> filtrazhiyeList = new ArrayList<>();
    private List<FiltrateBean> filtrahunyinList = new ArrayList<>();
    private List<FiltrateBean> filtrayiliaoList = new ArrayList<>();
    private List<FiltrateBean> filtraminzuList = new ArrayList<>();
    private void initParam() {
        List<gdws_ph_dict_item>  listxuexing= DataSupport.where("dict_code=? and version_num=?","BLOOD_TYPE",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
//        String[] losts = {"AB型", "A型", "B型", "O型", "不详"};
        FiltrateBean lostfb = new FiltrateBean();
        lostfb.setTypeName("血型");
        List<FiltrateBean.Children> lostchildrenList = new ArrayList<>();
        for (int x = 0; x < listxuexing.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listxuexing.get(x).getName());
            cd.setCode(listxuexing.get(x).getItem_code());
            lostchildrenList.add(cd);
        }
        lostfb.setChildren(lostchildrenList);
        filtraxuexingList.add(lostfb);


        List<gdws_ph_dict_item>  listwenhua= DataSupport.where("dict_code=? and version_num=?","DEGREE_OF_EDUCATION",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
//        String[] losts1 = {"小学", "初中", "高中", "专科", "本科", "研究生", "博士"};
        FiltrateBean lostfb1 = new FiltrateBean();
        lostfb1.setTypeName("文化程度");
        List<FiltrateBean.Children> lostchildrenList1 = new ArrayList<>();
        for (int x = 0; x < listwenhua.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listwenhua.get(x).getName());
            cd.setCode(listwenhua.get(x).getItem_code());
            lostchildrenList1.add(cd);
        }
        lostfb1.setChildren(lostchildrenList1);
        filtrawenhuaList.add(lostfb1);

//        String[] losts2 = {"无职业", "专业技术人员", "办事人员和有关人员", "国家机关,党群组织，企业，事业单位负责人", "商业，服务业人员", "农，林，牧，渔，水利业生产人员", "生产，运输设备操作人员及有关人员","军人","不便分类的其他从业人员"};
        List<gdws_ph_dict_item>  listzhiye= DataSupport.where("dict_code=? and version_num=?","OCCUPATION",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb2 = new FiltrateBean();
        lostfb2.setTypeName("职业");
        List<FiltrateBean.Children> lostchildrenList2 = new ArrayList<>();
        for (int x = 0; x < listzhiye.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listzhiye.get(x).getName());
            cd.setCode(listzhiye.get(x).getItem_code());
            lostchildrenList2.add(cd);
        }
        lostfb2.setChildren(lostchildrenList2);
        filtrazhiyeList.add(lostfb2);

        String[] losts3 = {"丧偶", "已婚", "未婚", "离婚", "不详"};
        List<gdws_ph_dict_item>  listhunyin= DataSupport.where("dict_code=? and version_num=?","MARITAL_STATUS",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb3 = new FiltrateBean();
        lostfb3.setTypeName("婚姻状况");
        List<FiltrateBean.Children> lostchildrenList3 = new ArrayList<>();
        for (int x = 0; x < listhunyin.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listhunyin.get(x).getName());
            cd.setCode(listhunyin.get(x).getItem_code());
            lostchildrenList3.add(cd);
        }
        lostfb3.setChildren(lostchildrenList3);
        filtrahunyinList.add(lostfb3);


        String[] losts4 = {"贫困救助", "城镇职工基本医疗保险", "商业医疗保险", "城镇居民基本医疗保险", "新型农村合作医疗","全自费","全公费","其他"};
        List<gdws_ph_dict_item>  listyiliao= DataSupport.where("dict_code=? and version_num=?","PAYMENT_METHOD",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb4 = new FiltrateBean();
        lostfb4.setTypeName("医疗费用支付方式");
        List<FiltrateBean.Children> lostchildrenList4 = new ArrayList<>();
        for (int x = 0; x < listyiliao.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listyiliao.get(x).getName());
            cd.setCode(listyiliao.get(x).getItem_code());
            lostchildrenList4.add(cd);
        }
        lostfb4.setChildren(lostchildrenList4);
        filtrayiliaoList.add(lostfb4);


        List<gdws_ph_dict_item>  listminzu= DataSupport.where("dict_code=? and version_num=?","NATION",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

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


    @Override
    public void onClick(View v) {
        //获取组件的资源id
        int id = v.getId();
        switch (id) {
            case R.id.ll_xuexing:
                initxuexing();
                break;
            case R.id.ll_wenhua:
                initwenhua();
                break;
            case R.id.ll_zhiye:
                initzhiye();
                break;
            case R.id.ll_hunyin:
                inithunyin();
                break;
            case R.id.ll_yiliao:
                inityiliao();
                break;
            case R.id.ll_minzu:
                initminzu();
                break;
            case R.id.tv_person_riqi:
                Toast.makeText(getActivity(),"请输入正确的身份证号码,会自动填充出生年月!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_person_sex:
                Toast.makeText(getActivity(),"请输入正确的身份证号码,会自动填充性别!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_jiaoyan:
                String Idcard=et_person_idcard.getText().toString();
                String info= IDCard.IDCardValidate(Idcard);
                if (info.equals("")){
                    tv_person_riqi.setText( IDCard.getbirthdayNew(Idcard));
                    tv_person_sex.setText(IDCard.getSex(Idcard));
                    Toast.makeText(getActivity(),"身份证号码校验正确",Toast.LENGTH_SHORT).show();
                }else{
                    tv_person_riqi.setText("");
                    tv_person_sex.setText("");
                    Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_shoushu:

                String s=tv_shoushu.getText().toString().trim();
                if(s.equals("手输")){
                    InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    et_bianhao.setHint("");

                    et_bianhao.setFocusable(true);
                    et_bianhao.setFocusableInTouchMode(true);
                    et_bianhao.requestFocus();

//                    et_bianhao.setFocusable(true);
//                    et_bianhao.setFocusableInTouchMode(true);
//                    et_bianhao.requestFocus();
//                    et_bianhao.findFocus();
                    mInputMethodManager.showSoftInput(et_bianhao, InputMethodManager.SHOW_FORCED);

//                    tv_shoushu.getLayoutParams().height = 100;//这样设置生效。
                    tv_shoushu.setText("自动生成");
                }else {
                    InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    et_bianhao.setFocusable(false);
                    if (mInputMethodManager.isActive()) {
                        mInputMethodManager.hideSoftInputFromWindow(et_bianhao.getWindowToken(), 0);
                    }
//                    tv_shoushu.getLayoutParams().height = 60;//这样设置生效。
                    tv_shoushu.setText("手输");
                    et_bianhao.setHint("保存后自动生成");
                }

                break;

            default:
                break;
        }
    }



    private FlowPopWindow flowPopWindow;

    private String codexuexing="";
    private void initxuexing(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtraxuexingList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtraxuexingList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codexuexing=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codexuexing=codexuexing+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_xuexing.setText(value);
                }else {
                    tv_xuexing.setText("");
                }

            }
        });
    }

    private String codewenhua="";
    private void initwenhua(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtrawenhuaList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrawenhuaList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codewenhua=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codewenhua=codewenhua+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_wenhua.setText(value);
                }else {
                    tv_wenhua.setText("");
                }

            }
        });
    }

    private String codezhiye="";
    private void initzhiye(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrazhiyeList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrazhiyeList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codezhiye=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codezhiye=codezhiye+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_zhiye.setText(value);
                }else {
                    tv_zhiye.setText("");
                }

            }
        });
    }

    String codehunyin="";
    private void inithunyin(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrahunyinList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrahunyinList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codehunyin=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codehunyin=codehunyin+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_hunyin.setText(value);
                }else {
                    tv_hunyin.setText("");
                }

            }
        });
    }


    private String codeyiliao="";
    private void inityiliao(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrayiliaoList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrayiliaoList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codeyiliao=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codeyiliao=codeyiliao+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_yiliao.setText(value);
                }else {
                    tv_yiliao.setText("");
                }

            }
        });
    }

    private String codeminzu="";
    private void initminzu(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtraminzuList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtraminzuList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codeminzu=children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codeminzu=codeminzu+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_minzu.setText(value);
                }else {
                    tv_minzu.setText("必选>");
                }

            }
        });
    }




    public void setchakan() {

        PersonChanBean personChanBean=activity.getchakan();
        if(personChanBean==null){

        }else {
            myid=personChanBean.getId();
            String name =Util.trimString(personChanBean.getName());
            et_person_name.setText(name);

            et_bianhao.setText(Util.trimString(personChanBean.getNo()));
            et_person_idcard.setText(Util.trimString(personChanBean.getIdNo()));
            tv_person_sex.setText(Util.trimString(personChanBean.getSexName()));

            tv_person_riqi.setText(Util.trimString(personChanBean.getBirthday()));
            et_person_danwei.setText(Util.trimString(personChanBean.getWorkUnit()));

            et_benren_dianhua.setText(Util.trimString(personChanBean.getPhone()));
            et_lianxiren_name.setText(Util.trimString(personChanBean.getContactName()));
            et_lianxi_dianhua.setText(Util.trimString(personChanBean.getContactPhone()));


            List<NameValueBeanWithNo> genderList1 = new ArrayList<>();
//            if(Util.trimString(personChanBean.getResidentType())

//            genderList1.add(new NameValueBeanWithNo("户籍", "1", "1",   checkSame("1",Util.trimString(personChanBean.getResidentType()))));
//            genderList1.add(new NameValueBeanWithNo("非户籍", "2", "1", checkSame("2",Util.trimString(personChanBean.getResidentType()))));
//            setViewDataDictionaryDataByCode(genderList1, choose_1);
            String rh=Util.trimString(personChanBean.getAbobloodName());
            String habitResidenceType = Util.trimString(personChanBean.getResidentType());
            choose_1.setSingleSelectValue1(habitResidenceType);




            String minzu =Util.trimString(personChanBean.getNationCode());
            codeminzu=minzu;


            String nameMinzu=personChanBean.getNationName();
            if(nameMinzu==null || TextUtils.isEmpty(nameMinzu)){
                nameMinzu= checkSame2(codeminzu,filtraminzuList);
                tv_minzu.setText(nameMinzu);
            }else {
                tv_minzu.setText(Util.trimString(personChanBean.getNationName()));
            }

            int iminzu= checkSame1(codeminzu,filtraminzuList);
            if(iminzu!=99){
                filtraminzuList.get(0).getChildren().get(iminzu ).setSelected(true);
            }

            String xuexing =Util.trimString(personChanBean.getAbobloodCode());
            codexuexing=xuexing;
            tv_xuexing.setText(Util.trimString(personChanBean.getAbobloodName()));
            int ixuexign= checkSame1(codexuexing,filtraxuexingList);
            if(ixuexign!=99){
                filtraxuexingList.get(0).getChildren().get(ixuexign ).setSelected(true);
            }

            String rhname=Util.trimString(personChanBean.getRhbloodName());

            List<NameValueBeanWithNo> genderList2 = new ArrayList<>();
            genderList2.add(new NameValueBeanWithNo("阴性", "1", "1", checkSame("阴性",rhname)));
            genderList2.add(new NameValueBeanWithNo("阳性", "2", "1", checkSame("阳性",rhname)));
            genderList2.add(new NameValueBeanWithNo("不详", "3", "1", checkSame("不详",rhname)));
            setViewDataDictionaryDataByCode(genderList2, choose_2);



            String wenhua =Util.trimString(personChanBean.getCultureCode());
            codewenhua=wenhua;
            tv_wenhua.setText(Util.trimString(personChanBean.getCultureName()));
            int iwenhua= checkSame1(codewenhua,filtrawenhuaList);
            if(iwenhua!=99){
                filtrawenhuaList.get(0).getChildren().get(iwenhua ).setSelected(true);
            }


            String yiliao =Util.trimString(personChanBean.getInsType());
            codeyiliao=yiliao;
            int iyiliao= checkSame1(codeyiliao,filtrayiliaoList);
            if(iyiliao!=99){
                filtrayiliaoList.get(0).getChildren().get(iyiliao ).setSelected(true);
            }
            String syiliao= checkSame2(codeyiliao,filtrayiliaoList);
            tv_yiliao.setText(syiliao);


            String hunyin =Util.trimString(personChanBean.getMarryCode());
            codehunyin=hunyin;
            int ihunyin= checkSame1(codehunyin,filtrahunyinList);
            if(ihunyin!=99){
                filtrahunyinList.get(0).getChildren().get(ihunyin ).setSelected(true);
            }
            String shunyin= checkSame2(codehunyin,filtrahunyinList);
            tv_hunyin.setText(shunyin);



            String zhiye =Util.trimString(personChanBean.getOccupationCode());
            codezhiye=zhiye;
            tv_zhiye.setText(Util.trimString(personChanBean.getOccupationName()));
            int izhiye= checkSame1(codezhiye,filtrazhiyeList);
            if(izhiye!=99){
                filtrazhiyeList.get(0).getChildren().get(izhiye ).setSelected(true);
            }

        }
    }

    private int checkSame1(String codeminzu,List<FiltrateBean> fillist){

        for(int i =0;i<fillist.get(0).getChildren().size();i++){
            if(fillist.get(0).getChildren().get(i).getCode().equals(codeminzu)){

                return i;
            }
        }
        return 99;
    }

    private String checkSame2(String codeminzu,List<FiltrateBean> fillist){

        for(int i =0;i<fillist.get(0).getChildren().size();i++){
            if(fillist.get(0).getChildren().get(i).getCode().equals(codeminzu)){

                return fillist.get(0).getChildren().get(i).getValue();
            }
        }
        return "";
    }

    private boolean checkSame(String str1,String str2){
        if (!TextUtils.isEmpty(str2)){
            if (str1.equals(str2)){
                return true;
            }else {
                return false;
            }

        }
        return false;
    }



}
