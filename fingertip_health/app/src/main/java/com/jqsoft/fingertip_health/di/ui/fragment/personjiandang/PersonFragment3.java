package com.jqsoft.fingertip_health.di.ui.fragment.personjiandang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.PersonChanBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPostBean;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_ph_dict_item;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.di.contract.PersonFragment3Contract;
import com.jqsoft.fingertip_health.di.module.PersonFragment3Module;
import com.jqsoft.fingertip_health.di.presenter.personFragment3Presenter;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.optionlayout.HbInputLayout;
import com.jqsoft.fingertip_health.optionlayout.HbOneSetTextOptionsLayout;
import com.jqsoft.fingertip_health.util.DecimalDigitsInputFilter;
import com.jqsoft.fingertip_health.util.StringUtils;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;
import com.jqsoft.fingertip_health.view.SelectAddressPop;
import com.jqsoft.fingertip_health.view.area.SelectAddresstipPop;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class PersonFragment3 extends AbstractFragment implements View.OnClickListener, PersonFragment3Contract.View{
    @BindView(R.id.sv_content)
    ScrollView sv_content;

    @BindView(R.id.ll_household_address)
    LinearLayout llHouseholdAddress;
    @BindView(R.id.ll_household_address_input_province)
    LinearLayout llHouseholdAddressInputProvince;
    @BindView(R.id.et_household_address_input_province)
    EditText etHouseholdAddressInputProvince;
    @BindView(R.id.iv_household_address_trailing_icon_province)
    ImageView ivHouseholdAddressTrailingIconProvince;
    @BindView(R.id.ll_household_address_input_city)
    LinearLayout llHouseholdAddressInputCity;
    @BindView(R.id.et_household_address_input_city)
    EditText etHouseholdAddressInputCity;
    @BindView(R.id.iv_household_address_trailing_icon_city)
    ImageView ivHouseholdAddressTrailingIconCity;
    @BindView(R.id.ll_household_address_input_county)
    LinearLayout llHouseholdAddressInputCounty;
    @BindView(R.id.et_household_address_input_county)
    EditText etHouseholdAddressInputCounty;
    @BindView(R.id.iv_household_address_trailing_icon_county)
    ImageView ivHouseholdAddressTrailingIconCounty;
    @BindView(R.id.ll_household_address_input_street)
    LinearLayout llHouseholdAddressInputTown;
    @BindView(R.id.et_household_address_input_street)
    EditText etHouseholdAddressInputTown;
    @BindView(R.id.iv_household_address_trailing_icon_street)
    ImageView ivHouseholdAddressTrailingIconTown;
    @BindView(R.id.ll_household_address_input_village)
    LinearLayout llHouseholdAddressInputVillage;
    @BindView(R.id.et_household_address_input_village)
    EditText etHouseholdAddressInputVillage;
    @BindView(R.id.iv_household_address_trailing_icon_village)
    ImageView ivHouseholdAddressTrailingIconVillage;
//    @BindView(R.id.et_household_address_input_extra)
//    EditText etHouseholdAddressInputExtra;
//
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
//    @BindView(R.id.et_home_address_input_extra)
//    EditText etHomeAddressInputExtra;

    private LinearLayout gxy_submit;
    LinearLayout llHomeAddressInputzu;
    EditText etHomeAddressInputzu;
    ImageView ivHomeAddressTrailingIconzu;

    EditText etHouseholdAddressInputzu;
    LinearLayout llHouseholdAddressInputzu;
    ImageView ivHouseholdAddressTrailingIconzu;


    private String version_num="";
    View failureView;
    public static final int REQUEST_A = 1;

    PersonJiandangActivity activity;

    @Inject
    personFragment3Presenter mPresenter;

    private View topview;
//    private TextView tv_hujidizhi;
@BindView(R.id.et_docname)
TextView et_docname;

    @BindView(R.id.tv_date)
    TextView tv_date;



    private LinearLayout ll_paifeng,ll_ranliao,ll_yinshui,ll_cesuo,ll_qincu;
    private TextView tv_paifeng,tv_ranliao,tv_yinshui,tv_cesuo,tv_qincu;


    String[] areaLevelArray = new String[]{Constants.AREA_LEVEL_ZU,Constants.AREA_LEVEL_VILLAGE,
            Constants.AREA_LEVEL_STREET,
            Constants.AREA_LEVEL_COUNTY,
            Constants.AREA_LEVEL_CITY,
            Constants.AREA_LEVEL_PROVINCE};


    //户籍地址
    String ssRegisteredProvinceCode,
            ssRegisteredCityCode,
            ssRegisteredCountyCode,
            ssRegisteredTownCode,
            ssRegisteredVillageCode,
            ssRegisteredZuCode;
    //            ssRegisteredGroup;
    int selectedRegisteredProvinceIndex=-1,
            selectedRegisteredCityIndex=-1,
            selectedRegisteredCountyIndex=-1,
            selectedRegisteredTownIndex=-1,
            selectedRegisteredVillageIndex=-1,
            selectedRegisteredzuIndex=-1;
    List<gdws_sys_area> registeredProvinceList=new ArrayList<>();
    List<gdws_sys_area> registeredCityList=new ArrayList<>();
    List<gdws_sys_area> registeredCountyList=new ArrayList<>();
    List<gdws_sys_area> registeredTownList=new ArrayList<>();
    List<gdws_sys_area> registeredVillageList=new ArrayList<>();
    List<gdws_sys_area> registeredzuList=new ArrayList<>();

    //家庭住址
    String     ssAreaProvinceCode,
            ssAreaCityCode,
            ssAreaCountyCode,
            ssAreaTownCode,
            ssAreaVillageCode,
            ssAreazuCode;
    //            ssAreaGroup;
    int selectedAreaProvinceIndex=-1,
            selectedAreaCityIndex=-1,
            selectedAreaCountyIndex=-1,
            selectedAreaTownIndex=-1,
            selectedAreaVillageIndex=-1,
            selectedAreazuIndex=-1;
    List<gdws_sys_area> areaProvinceList=new ArrayList<>();
    List<gdws_sys_area> areaCityList=new ArrayList<>();
    List<gdws_sys_area> areaCountyList=new ArrayList<>();
    List<gdws_sys_area> areaTownList=new ArrayList<>();
    List<gdws_sys_area> areaVillageList=new ArrayList<>();
    List<gdws_sys_area> areazuList=new ArrayList<>();
private String flag="";

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
        View view = inflater.inflate(R.layout.fragment_person_three_layout, container, false);
        activity = (PersonJiandangActivity) getActivity();
        topview=(View)view.findViewById(R.id.topview);
        gxy_submit = (LinearLayout) view.findViewById(R.id.gxy_submit);






        initv(view);






        ll_paifeng=(LinearLayout)view.findViewById(R.id.ll_paifeng);
        tv_paifeng=(TextView)view.findViewById(R.id.tv_paifeng);

        ll_ranliao=(LinearLayout)view.findViewById(R.id.ll_ranliao);
        tv_ranliao=(TextView)view.findViewById(R.id.tv_ranliao);

        ll_yinshui=(LinearLayout)view.findViewById(R.id.ll_yinshui);
        tv_yinshui=(TextView)view.findViewById(R.id.tv_yinshui);

        ll_cesuo=(LinearLayout)view.findViewById(R.id.ll_cesuo);
        tv_cesuo=(TextView)view.findViewById(R.id.tv_cesuo);

        ll_qincu=(LinearLayout)view.findViewById(R.id.ll_qincu);
        tv_qincu=(TextView)view.findViewById(R.id.tv_qincu);
        tv_date=(TextView)view.findViewById(R.id.tv_date);
        et_docname=(TextView) view.findViewById(R.id.et_docname);

//        tv_hujidizhi=(TextView)view.findViewById(R.id.tv_hujidizhi);
        version_num= DaggerApplication.getInstance().getVersionNum();


        initParam();
        ll_paifeng.setOnClickListener(this);
        ll_ranliao.setOnClickListener(this);
        ll_yinshui.setOnClickListener(this);
        ll_cesuo.setOnClickListener(this);
        ll_qincu.setOnClickListener(this);
        gxy_submit.setOnClickListener(this);
//        tv_hujidizhi.setOnClickListener(this);


        PersonJiandangActivity activity = (PersonJiandangActivity) getActivity();
        flag = activity.getflag();
        if(("1").equals(flag)){

        }else {
            //todo:户籍地址初始化
            initHouseholdAddress();
            //todo:家庭地址初始化
            initAreaAddress();
        }


        Util.setViewListener(tv_date, new Runnable() {
            @Override
            public void run() {
                Calendar maxDate = Calendar.getInstance();
                maxDate.setTime(new Date());
                Util.showDateNewDialogWithMaxDate1(getActivity(), tv_date.getText().toString(), "nnilBirthday",
                        maxDate,
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                                tv_date.setText(s);
                            }
                        } ,true);
            }
        });

        return view;



    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_three_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String yisheng=DaggerApplication.getInstance().getRealName();
        et_docname.setText(yisheng);

    }

    @Override
    protected void loadData() {

    }

    //    LinearLayout llHouseholdAddress;
//    LinearLayout llHouseholdAddressInputProvince;
//    EditText etHouseholdAddressInputProvince;
//    ImageView ivHouseholdAddressTrailingIconProvince;
//    LinearLayout llHouseholdAddressInputCity;
//    EditText etHouseholdAddressInputCity;
//    ImageView ivHouseholdAddressTrailingIconCity;
//    LinearLayout llHouseholdAddressInputCounty;
//    EditText etHouseholdAddressInputCounty;
//    ImageView ivHouseholdAddressTrailingIconCounty;
//    LinearLayout llHouseholdAddressInputTown;
//    EditText etHouseholdAddressInputTown;
//    ImageView ivHouseholdAddressTrailingIconTown;
    private void initv( View view) {
         llHouseholdAddress=(LinearLayout)view.findViewById(R.id.ll_household_address);
        llHouseholdAddressInputProvince=(LinearLayout)view.findViewById(R.id.ll_household_address_input_province);
        etHouseholdAddressInputProvince=(EditText)view.findViewById(R.id.et_household_address_input_province);
        ivHouseholdAddressTrailingIconProvince=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_province);
        llHouseholdAddressInputCity=(LinearLayout)view.findViewById(R.id.ll_household_address_input_city);
        etHouseholdAddressInputCity=(EditText)view.findViewById(R.id.et_household_address_input_city);
        ivHouseholdAddressTrailingIconCity=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_city);
        llHouseholdAddressInputCounty=(LinearLayout)view.findViewById(R.id.ll_household_address_input_county);
        etHouseholdAddressInputCounty=(EditText)view.findViewById(R.id.et_household_address_input_county);

        ivHouseholdAddressTrailingIconCounty=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_county);
        llHouseholdAddressInputTown=(LinearLayout)view.findViewById(R.id.ll_household_address_input_street);
        etHouseholdAddressInputTown=(EditText)view.findViewById(R.id.et_household_address_input_street);



        ivHouseholdAddressTrailingIconTown=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_street);


        llHouseholdAddressInputVillage=(LinearLayout)view.findViewById(R.id.ll_household_address_input_village);

        etHouseholdAddressInputVillage=(EditText)view.findViewById(R.id.et_household_address_input_village);


        ivHouseholdAddressTrailingIconVillage=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_village);






        llHouseholdAddressInputzu=(LinearLayout)view.findViewById(R.id.ll_household_address_input_zu);

        etHouseholdAddressInputzu=(EditText)view.findViewById(R.id.et_household_address_input_zu);


        ivHouseholdAddressTrailingIconzu=(ImageView)view.findViewById(R.id.iv_household_address_trailing_icon_zu);

//        etHouseholdAddressInputExtra=(EditText)view.findViewById(R.id.et_household_address_input_extra);


        llHomeAddress=(LinearLayout)view.findViewById(R.id.ll_home_address);
        llHomeAddressInputProvince=(LinearLayout)view.findViewById(R.id.ll_home_address_input_province);

        etHomeAddressInputProvince=(EditText)view.findViewById(R.id.et_home_address_input_province);

        ivHomeAddressTrailingIconProvince=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_province);

        llHomeAddressInputCity=(LinearLayout)view.findViewById(R.id.ll_home_address_input_city);

        etHomeAddressInputCity=(EditText)view.findViewById(R.id.et_home_address_input_city);


        ivHomeAddressTrailingIconCity=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_city);



        llHomeAddressInputCounty=(LinearLayout)view.findViewById(R.id.ll_home_address_input_county);

        etHomeAddressInputCounty=(EditText)view.findViewById(R.id.et_home_address_input_county);

        ivHomeAddressTrailingIconCounty=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_county);

        llHomeAddressInputTown=(LinearLayout)view.findViewById(R.id.ll_home_address_input_street);

        etHomeAddressInputTown=(EditText)view.findViewById(R.id.et_home_address_input_street);
        ivHomeAddressTrailingIconTown=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_street);

        llHomeAddressInputVillage=(LinearLayout)view.findViewById(R.id.ll_home_address_input_village);



        etHomeAddressInputVillage=(EditText)view.findViewById(R.id.et_home_address_input_village);

        ivHomeAddressTrailingIconVillage=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_village);



        llHomeAddressInputzu=(LinearLayout)view.findViewById(R.id.ll_home_address_input_zu);

        etHomeAddressInputzu=(EditText)view.findViewById(R.id.et_home_address_input_zu);

        ivHomeAddressTrailingIconzu=(ImageView)view.findViewById(R.id.iv_home_address_trailing_icon_zu);



//        etHomeAddressInputExtra=(EditText)view.findViewById(R.id.et_home_address_input_extra);
    }

    private List<FiltrateBean> filtrapaifengList = new ArrayList<>();
    private List<FiltrateBean> filtraranliaoList = new ArrayList<>();
    private List<FiltrateBean> filtrayinshuiList = new ArrayList<>();
    private List<FiltrateBean> filtracesuoList = new ArrayList<>();
    private List<FiltrateBean> filtraqincuList = new ArrayList<>();

    private void initParam() {

        String[] losts = {"无", "抽烟机", "换气扇", "烟囱", "其他"};
        List<gdws_ph_dict_item>  listchufang= DataSupport.where("dict_code=? and version_num=?","EXHAUST_FACILIT",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb = new FiltrateBean();
        lostfb.setTypeName("厨房排风设施");
        List<FiltrateBean.Children> lostchildrenList = new ArrayList<>();
        for (int x = 0; x < listchufang.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listchufang.get(x).getName());
            cd.setCode(listchufang.get(x).getItem_code());
            lostchildrenList.add(cd);
        }
        lostfb.setChildren(lostchildrenList);
        filtrapaifengList.add(lostfb);



        String[] losts1 = {"无", "液化气", "煤", "天然气", "柴火", "其他"};
        List<gdws_ph_dict_item>  listranliao= DataSupport.where("dict_code=? and version_num=?","FUEL_TYPE",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb1 = new FiltrateBean();
        lostfb1.setTypeName("燃料类型");
        List<FiltrateBean.Children> lostchildrenList1 = new ArrayList<>();
        for (int x = 0; x < listranliao.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listranliao.get(x).getName());
            cd.setCode(listranliao.get(x).getItem_code());
            lostchildrenList1.add(cd);
        }
        lostfb1.setChildren(lostchildrenList1);
        filtraranliaoList.add(lostfb1);

        String[] losts2 = {"自来水", "经净化过滤的水", "井水", "河湖水", "塘水", "纯水或桶装水", "其他"};
        List<gdws_ph_dict_item>  listyinshui= DataSupport.where("dict_code=? and version_num=?","DRINKING_WATER",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb2 = new FiltrateBean();
        lostfb2.setTypeName("饮水");
        List<FiltrateBean.Children> lostchildrenList2 = new ArrayList<>();
        for (int x = 0; x < listyinshui.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listyinshui.get(x).getName());
            cd.setCode(listyinshui.get(x).getItem_code());
            lostchildrenList2.add(cd);
        }
        lostfb2.setChildren(lostchildrenList2);
        filtrayinshuiList.add(lostfb2);

        String[] losts3 = {"卫生厕所", "一格或两格粪池式", "马桶", "露天粪坑", "简易棚厕", "其他"};
        List<gdws_ph_dict_item>  listcesuo= DataSupport.where("dict_code=? and version_num=?","TOILET",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb3 = new FiltrateBean();
        lostfb3.setTypeName("厕所");
        List<FiltrateBean.Children> lostchildrenList3 = new ArrayList<>();
        for (int x = 0; x < listcesuo.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listcesuo.get(x).getName());
            cd.setCode(listcesuo.get(x).getItem_code());
            lostchildrenList3.add(cd);
        }
        lostfb3.setChildren(lostchildrenList3);
        filtracesuoList.add(lostfb3);


        String[] losts4 = {"无", "单设", "室内", "室外","其他"};
        List<gdws_ph_dict_item>  listqinchulan= DataSupport.where("dict_code=? and version_num=?","POULTRY_ENCLOSU",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb4 = new FiltrateBean();
        lostfb4.setTypeName("禽畜栏");

        List<FiltrateBean.Children> lostchildrenList4 = new ArrayList<>();
        for (int x = 0; x < listqinchulan.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listqinchulan.get(x).getName());
            cd.setCode(listqinchulan.get(x).getItem_code());
            lostchildrenList4.add(cd);
        }
        lostfb4.setChildren(lostchildrenList4);
        filtraqincuList.add(lostfb4);


    }

    private String provinceCode ;
    private String cityCode;
    private String areaCode;
    private String areaCode4;
    private String areaCode5;
    private String areaCode6;

    @Override
    public void onClick(View v) {
        //获取组件的资源id
        int id = v.getId();
        switch (id) {
            case R.id.ll_paifeng:
                initpaifeng();
                break;
            case R.id.ll_ranliao:
                initranliao();
                break;
            case R.id.ll_yinshui:
                inityinshui();
                break;
            case R.id.ll_cesuo:
                initcesuo();
                break;
            case R.id.ll_qincu:
                initqincu();
                break;
            case R.id.gxy_submit:
                initbaocun();
                break;
//            case R.id.tv_hujidizhi:
//
////                Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG).show();
//
//
////                activity.setpop();
//                SelectAddresstipPop pop1  = new SelectAddresstipPop();
//                //         SelectTwoAddressPop pop1  = new SelectTwoAddressPop();
//                pop1.setSelectAddresFinish(new PersonJiandangActivity.SelectAddresFinish(){
//
//                    @Override
//                    public void finish(String pCode, String cCode, String aCode, String aCode4, String aCode5, String aCode6,String addres) {
//                        provinceCode = pCode;
//                        cityCode = cCode;
//                        areaCode = aCode;
//                        areaCode4 = aCode4;
//                        areaCode5 = aCode5;
//                        areaCode6 = aCode6;
//
//                        tv_hujidizhi.setText(addres);
//
//                    }
//                });
//                pop1.setAddress(provinceCode,cityCode,areaCode,areaCode4,areaCode5,areaCode5);
//                pop1.show(getActivity().getFragmentManager(),"");
//
//                break;

            default:
                break;
        }
    }

    private PersonPostBean hbPostBean1,hbPostBean2;
    private void initbaocun() {
        PersonJiandangActivity activity = (PersonJiandangActivity) getActivity();
        ViewPager viewPager = activity.getVpContent();
         activity.getHbFragment1().getdata1();
        if (viewPager.getCurrentItem() == 2) {
            hbPostBean1 = activity.getHbFragment1().getdata1();


//            activity.getHbFragment2().getdata2();
            hbPostBean2 = activity.getHbFragment2().getdata2();
            String s="";

//            hbPostBean1.setAreaFulladdress();

            hbPostBean1.setAllergyHistories(hbPostBean2.getAllergyHistories());
            hbPostBean1.setExposureHistor(hbPostBean2.getExposureHistor());

            hbPostBean1.setPastHistories(hbPostBean2.getPastHistories());
            hbPostBean1.setFatherDisease(hbPostBean2.getFatherDisease());
            hbPostBean1.setMotherDisease(hbPostBean2.getMotherDisease());
            hbPostBean1.setBrothersDisease(hbPostBean2.getBrothersDisease());
            hbPostBean1.setChildrenDisease(hbPostBean2.getChildrenDisease());
            hbPostBean1.setDeformitySituation(hbPostBean2.getDeformitySituation());
            hbPostBean1.setHereditaryHistory(hbPostBean2.getHereditaryHistory());
            hbPostBean1.setHereditaryName(hbPostBean2.getHereditaryName());

            hbPostBean1.setExhaustFacilit(codepaifeng);
            hbPostBean1.setFuelType(coderanliao);
            hbPostBean1.setDrinkingWater(codeyinshui);
            hbPostBean1.setToilet(codecesuo);
            hbPostBean1.setPoultryEnclosu(codeqincu);

            if(ssRegisteredZuCode==null || TextUtils.isEmpty(ssRegisteredZuCode)){
                if(ssRegisteredVillageCode==null || TextUtils.isEmpty(ssRegisteredVillageCode)){

                    if(ssRegisteredTownCode==null || TextUtils.isEmpty(ssRegisteredTownCode)){
                        if(ssRegisteredCountyCode==null || TextUtils.isEmpty(ssRegisteredCountyCode)){
                            if(ssRegisteredCityCode==null || TextUtils.isEmpty(ssRegisteredCityCode)){
                                if(ssRegisteredProvinceCode==null || TextUtils.isEmpty(ssRegisteredProvinceCode)) {
                                    hbPostBean1.setRegisterAddress("");
                                }else {
                                    hbPostBean1.setRegisterAddress(ssRegisteredProvinceCode);
                                }
                            }else {
                                hbPostBean1.setRegisterAddress(ssRegisteredCityCode);
                            }
                        }else {
                            hbPostBean1.setRegisterAddress(ssRegisteredCountyCode);
                        }

                    }else {
                        hbPostBean1.setRegisterAddress(ssRegisteredTownCode);
                    }
                }else {
                    hbPostBean1.setRegisterAddress(ssRegisteredVillageCode);
                }

            }else {
                hbPostBean1.setRegisterAddress(ssRegisteredZuCode);
            }



            String date =tv_date.getText().toString();
            String yisheng =et_docname.getText().toString();

            if(ssAreaVillageCode==null || TextUtils.isEmpty(ssAreaVillageCode)){
                Toast.makeText(getActivity(), "请选择居住地址至少到村级!", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(date)){
                Toast.makeText(getActivity(), "请选择建档日期!", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(yisheng)){
                Toast.makeText(getActivity(), "请输入建档医生!", Toast.LENGTH_SHORT).show();
            }else {
                if(ssAreazuCode==null || TextUtils.isEmpty(ssAreazuCode)){
                    hbPostBean1.setZuCode(ssAreaVillageCode);
                }else {
                    hbPostBean1.setZuCode(ssAreazuCode);
                }


                hbPostBean1.setArchivingDoctorCode(DaggerApplication.getInstance().getUsername());
                hbPostBean1.setArchivingDoctorName(yisheng);
                hbPostBean1.setArchivingDoctorDate(date);




                Map<String, String> map = ParametersFactory.postHighBloodData1(getActivity(),hbPostBean1
                ) ;

                mPresenter.postdate(map);
            }



        }
    }

    private FlowPopWindow flowPopWindow;
    private String codepaifeng="";
    private void initpaifeng(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtrapaifengList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrapaifengList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codepaifeng=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codepaifeng=codepaifeng+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_paifeng.setText(value);
                }else {
                    tv_paifeng.setText("");
                }

            }
        });
    }

    private String coderanliao="";
    private void initranliao(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtraranliaoList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtraranliaoList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                coderanliao=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                coderanliao=coderanliao+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_ranliao.setText(value);
                }else {
                    tv_ranliao.setText("");
                }

            }
        });
    }

    private String codeyinshui="";
    private void inityinshui(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrayinshuiList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrayinshuiList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codeyinshui=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codeyinshui=codeyinshui+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_yinshui.setText(value);
                }else {
                    tv_yinshui.setText("");
                }

            }
        });
    }

    private String codecesuo="";
    private void initcesuo(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtracesuoList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtracesuoList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codecesuo=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codecesuo=codecesuo+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_cesuo.setText(value);
                }else {
                    tv_cesuo.setText("");
                }

            }
        });
    }

    private String codeqincu="";
    private void initqincu(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtraqincuList,true);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtraqincuList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codeqincu=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codeqincu=codeqincu+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_qincu.setText(value);
                }else {
                    tv_qincu.setText("");
                }

            }
        });
    }




    String mydoctorAreaCode,mydoctorAreaCode1;

    //初始化户籍地址
    private void initHouseholdAddress() {
        registeredProvinceList = AreaDictionaryUtil.getAreaDataBeanListFromAreaLevel(Constants.AREA_LEVEL_PROVINCE);
        registeredCityList = getRegisteredCityList();






        initRegisteredProvince(selectedRegisteredProvinceIndex);
        Util.setViewListener(llHouseholdAddressInputProvince, new Runnable() {
            @Override
            public void run() {
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredProvinceList.toArray(new IStringRepresentationAndValue[0]));
//                initRegisteredProvince(selectedRegisteredProvinceIndex);
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择省份", null,
                        realList, selectedRegisteredProvinceIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredProvince(which);
                                return false;
                            }
                        });
            }
        });


        initRegisteredCity(selectedRegisteredCityIndex);
        Util.setViewListener(llHouseholdAddressInputCity, new Runnable() {
            @Override
            public void run() {
                registeredCityList = getRegisteredCityList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredCityList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择市", null,
                        realList, selectedRegisteredCityIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredCity(which);
                                return false;
                            }
                        });
            }
        });

        registeredCountyList = getRegisteredCountyList();
        initRegisteredCounty(selectedRegisteredCountyIndex);
        Util.setViewListener(llHouseholdAddressInputCounty, new Runnable() {
            @Override
            public void run() {
                registeredCountyList = getRegisteredCountyList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredCountyList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择县区", null,
                        realList, selectedRegisteredCountyIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredCounty(which);
                                return false;
                            }
                        });
            }
        });

        registeredTownList = getRegisteredTownList();
        initRegisteredTown(selectedRegisteredTownIndex);
        Util.setViewListener(llHouseholdAddressInputTown, new Runnable() {
            @Override
            public void run() {
                registeredTownList = getRegisteredTownList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredTownList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择乡镇", null,
                        realList, selectedRegisteredTownIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredTown(which);
                                return false;
                            }
                        });
            }
        });

        registeredVillageList = getRegisteredVillageList();
        initRegisteredVillage(selectedRegisteredVillageIndex);
        Util.setViewListener(llHouseholdAddressInputVillage, new Runnable() {
            @Override
            public void run() {
                registeredVillageList = getRegisteredVillageList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredVillageList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择村", null,
                        realList, selectedRegisteredVillageIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredVillage(which);
                                return false;
                            }
                        });
            }
        });


        registeredzuList = getRegisteredzuList();
        initRegisteredzu(selectedRegisteredzuIndex);
        Util.setViewListener(llHouseholdAddressInputzu, new Runnable() {
            @Override
            public void run() {
                registeredzuList = getRegisteredzuList();
                List<IStringRepresentationAndValue> realList = Arrays.asList(registeredzuList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择组", null,
                        realList, selectedRegisteredzuIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initRegisteredzu(which);
                                return false;
                            }
                        });
            }
        });



        View[] homeAddressLayoutArray = new View[]{llHouseholdAddressInputzu,llHouseholdAddressInputVillage,
                llHouseholdAddressInputTown,
                llHouseholdAddressInputCounty,
                llHouseholdAddressInputCity,
                llHouseholdAddressInputProvince};
        View[] homeAddressEditTextArray = new View[]{etHouseholdAddressInputzu,etHouseholdAddressInputVillage,
                etHouseholdAddressInputTown,
                etHouseholdAddressInputCounty,
                etHouseholdAddressInputCity,
                etHouseholdAddressInputProvince};

        if(flag.equals("0")){
            mydoctorAreaCode = getDoctorAreaCode();
        }else {

        }

        String doctorAreaLevel = AreaDictionaryUtil.getAreaLevelFromAreaCode(mydoctorAreaCode);
        int levelIndex = getDoctorAreaLevelIndex(doctorAreaLevel);
        String tempAreaCode = mydoctorAreaCode;
        String tempAreaLevel = doctorAreaLevel;
        View tempAreaLayout = null;
        EditText tempEditText = null;


        if (levelIndex>=0 && levelIndex<areaLevelArray.length) {
            for (int i = levelIndex; i < areaLevelArray.length; ++i) {
                tempAreaLevel = areaLevelArray[i];
                tempAreaLayout = homeAddressLayoutArray[i];
                tempEditText = (EditText) homeAddressEditTextArray[i];
                gdws_sys_area bean = AreaDictionaryUtil.getAreaDataBeanFromAreaCodeAndAreaLevel(tempAreaCode, tempAreaLevel);
                if (bean != null) {
                    //                tempAreaLayout.setEnabled(false);
                    tempEditText.setText(bean.getName());
                    setRegisteredAddressCode(tempAreaLevel, bean.getCode());
                    tempAreaCode = bean.getP_code();
                } else {
                    break;
                }


            }
            registeredCityList = getRegisteredCityList();
            registeredCountyList = getRegisteredCountyList();
            registeredTownList = getRegisteredTownList();
            registeredVillageList = getRegisteredVillageList();
            registeredzuList = getRegisteredzuList();
            String tempAreaCode1 = mydoctorAreaCode;
            for (int i = levelIndex; i < areaLevelArray.length; ++i) {
                tempAreaLevel = areaLevelArray[i];

                gdws_sys_area bean = AreaDictionaryUtil.getAreaDataBeanFromAreaCodeAndAreaLevel(tempAreaCode1, tempAreaLevel);
                    setRegisteredAddressCode(tempAreaLevel, bean.getCode());
                tempAreaCode1 = bean.getP_code();

            }


            initRegisteredProvince1(selectedRegisteredProvinceIndex);
            initRegisteredCity1(selectedRegisteredCityIndex);
            initRegisteredCounty1(selectedRegisteredCountyIndex);
            initRegisteredTown1(selectedRegisteredTownIndex);
            initRegisteredVillage1(selectedRegisteredVillageIndex);
            initRegisteredzu(selectedRegisteredzuIndex);
        }


    }

    //初始化家庭地址
    private void initAreaAddress(){
        areaProvinceList = AreaDictionaryUtil.getAreaDataBeanListFromAreaLevel(Constants.AREA_LEVEL_PROVINCE);
        initAreaProvince(selectedAreaProvinceIndex);
        Util.setViewListener(llHomeAddressInputProvince, new Runnable() {
            @Override
            public void run() {
                List<IStringRepresentationAndValue> realList = Arrays.asList(areaProvinceList.toArray(new IStringRepresentationAndValue[0]));
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择省份", null,
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
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择市", null,
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
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择县区", null,
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
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择乡镇", null,
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
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择村", null,
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
                Util.showSingleChoiceStringListMaterialDialogEx(getActivity(), "请选择组", null,
                        realList, selectedAreazuIndex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                initAreazu(which);
                                return false;
                            }
                        });
            }
        });


        View[] homeAddressLayoutArray = new View[]{llHomeAddressInputzu,llHomeAddressInputVillage,
                llHomeAddressInputTown,
                llHomeAddressInputCounty,
                llHomeAddressInputCity,
                llHomeAddressInputProvince};
        View[] homeAddressEditTextArray = new View[]{etHomeAddressInputzu,etHomeAddressInputVillage,
                etHomeAddressInputTown,
                etHomeAddressInputCounty,
                etHomeAddressInputCity,
                etHomeAddressInputProvince};
        if(flag.equals("0")){
            mydoctorAreaCode1= getDoctorAreaCode1();
        }else {

        }

        String doctorAreaLevel = AreaDictionaryUtil.getAreaLevelFromAreaCode(mydoctorAreaCode1);
        int levelIndex = getDoctorAreaLevelIndex(doctorAreaLevel);
        String tempAreaCode = mydoctorAreaCode1;
        String tempAreaLevel = doctorAreaLevel;
        View tempAreaLayout = null;
        EditText tempEditText = null;

        if (levelIndex>=0 && levelIndex<areaLevelArray.length) {
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


    private void initRegisteredProvince(int index){
        if (index>=0 && index<registeredProvinceList.size()){
            selectedRegisteredProvinceIndex=index;
            gdws_sys_area bean = registeredProvinceList.get(index);
            ssRegisteredProvinceCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputProvince.setText(name);

            clearRegisteredCityStatus();
        }
    }

    private void initRegisteredProvince1(int index){
        if (index>=0 && index<registeredProvinceList.size()){
            selectedRegisteredProvinceIndex=index;
            gdws_sys_area bean = registeredProvinceList.get(index);
            ssRegisteredProvinceCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputProvince.setText(name);

//            clearRegisteredCityStatus();
        }
    }



    private void initRegisteredCity(int index){
        if (index>=0 && index<registeredCityList.size()){
            selectedRegisteredCityIndex=index;
            gdws_sys_area bean = registeredCityList.get(index);
            ssRegisteredCityCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputCity.setText(name);

            clearRegisteredCountyStatus();
        }
    }

    private void initRegisteredCity1(int index){
        if (index>=0 && index<registeredCityList.size()){
            selectedRegisteredCityIndex=index;
            gdws_sys_area bean = registeredCityList.get(index);
            ssRegisteredCityCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputCity.setText(name);

//            clearRegisteredCountyStatus();
        }
    }

    private void initRegisteredCounty(int index){
        if (index>=0 && index<registeredCountyList.size()){
            selectedRegisteredCountyIndex=index;
            gdws_sys_area bean = registeredCountyList.get(index);
            ssRegisteredCountyCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputCounty.setText(name);

            clearRegisteredTownStatus();
        }
    }

    private void initRegisteredCounty1(int index){
        if (index>=0 && index<registeredCountyList.size()){
            selectedRegisteredCountyIndex=index;
            gdws_sys_area bean = registeredCountyList.get(index);
            ssRegisteredCountyCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputCounty.setText(name);

//            clearRegisteredTownStatus();
        }
    }

    private void initRegisteredTown(int index){
        if (index>=0 && index<registeredTownList.size()){
            selectedRegisteredTownIndex=index;
            gdws_sys_area bean = registeredTownList.get(index);
            ssRegisteredTownCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputTown.setText(name);

            clearRegisteredVillageStatus();
        }
    }

    private void initRegisteredTown1(int index){
        if (index>=0 && index<registeredTownList.size()){
            selectedRegisteredTownIndex=index;
            gdws_sys_area bean = registeredTownList.get(index);
            ssRegisteredTownCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputTown.setText(name);

//            clearRegisteredVillageStatus();
        }
    }

    private void initRegisteredVillage(int index){
        if (index>=0 && index<registeredVillageList.size()){
            selectedRegisteredVillageIndex=index;
            gdws_sys_area bean = registeredVillageList.get(index);
            ssRegisteredVillageCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputVillage.setText(name);

            clearRegisteredzuStatus();
        }
    }

    private void initRegisteredVillage1(int index){
        if (index>=0 && index<registeredVillageList.size()){
            selectedRegisteredVillageIndex=index;
            gdws_sys_area bean = registeredVillageList.get(index);
            ssRegisteredVillageCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputVillage.setText(name);

//            clearRegisteredzuStatus();
        }
    }

    private void initRegisteredzu(int index){
        if (index>=0 && index<registeredzuList.size()){
            selectedRegisteredzuIndex=index;
            gdws_sys_area bean = registeredzuList.get(index);
            ssRegisteredZuCode=bean.getCode();
            String name = bean.getName();
            etHouseholdAddressInputzu.setText(name);


        }
    }

    private void initAreaProvince(int index){
        if (index>=0 && index<areaProvinceList.size()){
            selectedAreaProvinceIndex=index;
            gdws_sys_area bean = areaProvinceList.get(index);
            ssAreaProvinceCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputProvince.setText(name);

            clearAreaCityStatus();
        }
    }

    private void initAreaCity(int index){
        if (index>=0 && index<areaCityList.size()){
            selectedAreaCityIndex=index;
            gdws_sys_area bean = areaCityList.get(index);
            ssAreaCityCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputCity.setText(name);

            clearAreaCountyStatus();
        }
    }

    private void initAreaCounty(int index){
        if (index>=0 && index<areaCountyList.size()){
            selectedAreaCountyIndex=index;
            gdws_sys_area bean = areaCountyList.get(index);
            ssAreaCountyCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputCounty.setText(name);

            clearAreaTownStatus();
        }
    }

    private void initAreaTown(int index){
        if (index>=0 && index<areaTownList.size()){
            selectedAreaTownIndex=index;
            gdws_sys_area bean = areaTownList.get(index);
            ssAreaTownCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputTown.setText(name);

            clearAreaVillageStatus();
        }
    }

    private void initAreaVillage(int index){
        if (index>=0 && index<areaVillageList.size()){
            selectedAreaVillageIndex=index;
            gdws_sys_area bean = areaVillageList.get(index);
            ssAreaVillageCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputVillage.setText(name);

            clearAreazuStatus();
        }
    }

    private void initAreazu(int index){
        if (index>=0 && index<areazuList.size()){
            selectedAreazuIndex=index;
            gdws_sys_area bean = areazuList.get(index);
            ssAreazuCode=bean.getCode();
            String name = bean.getName();
            etHomeAddressInputzu.setText(name);
        }
    }


    private void clearRegisteredCityStatus(){
        selectedRegisteredCityIndex=-1;
        ssRegisteredCityCode=Constants.EMPTY_STRING;
        registeredCityList.clear();
        etHouseholdAddressInputCity.setText(Constants.EMPTY_STRING);
        clearRegisteredCountyStatus();
    }

    private void clearRegisteredCountyStatus(){
        selectedRegisteredCountyIndex=-1;
        ssRegisteredCountyCode=Constants.EMPTY_STRING;
        registeredCountyList.clear();
        etHouseholdAddressInputCounty.setText(Constants.EMPTY_STRING);
        clearRegisteredTownStatus();
    }

    private void clearRegisteredTownStatus(){
        selectedRegisteredTownIndex=-1;
        ssRegisteredTownCode=Constants.EMPTY_STRING;
        registeredTownList.clear();
        etHouseholdAddressInputTown.setText(Constants.EMPTY_STRING);
        clearRegisteredVillageStatus();
    }

    private void clearRegisteredVillageStatus(){
        selectedRegisteredVillageIndex=-1;
        ssRegisteredVillageCode=Constants.EMPTY_STRING;
        registeredVillageList.clear();
        etHouseholdAddressInputVillage.setText(Constants.EMPTY_STRING);
        clearRegisteredzuStatus();
    }

    private void clearRegisteredzuStatus(){
        selectedRegisteredzuIndex=-1;
        ssRegisteredZuCode=Constants.EMPTY_STRING;
        registeredzuList.clear();
        etHouseholdAddressInputzu.setText(Constants.EMPTY_STRING);
    }

    private void clearAreaCityStatus(){
        selectedAreaCityIndex=-1;
        ssAreaCityCode=Constants.EMPTY_STRING;
        areaCityList.clear();
        etHomeAddressInputCity.setText(Constants.EMPTY_STRING);
        clearAreaCountyStatus();
    }

    private void clearAreaCountyStatus(){
        selectedAreaCountyIndex=-1;
        ssAreaCountyCode=Constants.EMPTY_STRING;
        areaCountyList.clear();
        etHomeAddressInputCounty.setText(Constants.EMPTY_STRING);
        clearAreaTownStatus();
    }

    private void clearAreaTownStatus(){
        selectedAreaTownIndex=-1;
        ssAreaTownCode=Constants.EMPTY_STRING;
        areaTownList.clear();
        etHomeAddressInputTown.setText(Constants.EMPTY_STRING);
        clearAreaVillageStatus();
    }

    private void clearAreaVillageStatus(){
        selectedAreaVillageIndex=-1;
        ssAreaVillageCode=Constants.EMPTY_STRING;
        areaVillageList.clear();
        etHomeAddressInputVillage.setText(Constants.EMPTY_STRING);
        clearAreazuStatus();
    }

    private void clearAreazuStatus(){
        selectedAreazuIndex=-1;
        ssAreazuCode=Constants.EMPTY_STRING;
        areazuList.clear();
        etHomeAddressInputzu.setText(Constants.EMPTY_STRING);
    }


    private List<gdws_sys_area> getRegisteredCityList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_CITY,
                ssRegisteredProvinceCode);
    }

    private List<gdws_sys_area> getRegisteredCountyList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_COUNTY,
                ssRegisteredCityCode);
    }

    private List<gdws_sys_area> getRegisteredTownList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_STREET,
                ssRegisteredCountyCode);
    }

    private List<gdws_sys_area> getRegisteredVillageList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_VILLAGE,
                ssRegisteredTownCode);
    }


    private List<gdws_sys_area> getRegisteredzuList() {
        return AreaDictionaryUtil.getAreaDataBeanListFromAreaLevelAndParentAreaCode(Constants.AREA_LEVEL_ZU,
                ssRegisteredVillageCode);
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

    private String getDoctorAreaCode(){

        String result = DaggerApplication.getInstance().getAreaId();
        return result;

    }

    private String getDoctorAreaCode1(){
        String result = DaggerApplication.getInstance().getAreaId();
        return result;
    }




    private int getDoctorAreaLevelIndex(String level){
        level=Util.trimString(level);
        int index = -1;
        for (int i = 0; i < areaLevelArray.length; ++i){
            String s = areaLevelArray[i];
            if (level.equals(s)){
                index = i;
                break;
            }
        }
        return index;
    }


    private void setRegisteredAddressCode(String level, String code){


        level=Util.trimString(level);
        code=Util.trimString(code);
        if (Constants.AREA_LEVEL_PROVINCE.equals(level)){
            ssRegisteredProvinceCode=code;

            for(int i=0;i<registeredProvinceList.size();i++){
                if(ssRegisteredProvinceCode.equals(registeredProvinceList.get(i).getCode())){
                    selectedRegisteredProvinceIndex=i;
                }

            }

//            selectedAreaProvinceIndex=

        } else if (Constants.AREA_LEVEL_CITY.equals(level)){
            ssRegisteredCityCode=code;

            for(int i=0;i<registeredCityList.size();i++){
                if(ssRegisteredCityCode.equals(registeredCityList.get(i).getCode())){
                    selectedRegisteredCityIndex=i;
                }

            }

        } else if (Constants.AREA_LEVEL_COUNTY.equals(level)){
            ssRegisteredCountyCode=code;

            for(int i=0;i<registeredCountyList.size();i++){
                if(ssRegisteredCountyCode.equals(registeredCountyList.get(i).getCode())){
                    selectedRegisteredCountyIndex=i;
                }

            }

        } else if (Constants.AREA_LEVEL_STREET.equals(level)){
            ssRegisteredTownCode=code;


            for(int i=0;i<registeredTownList.size();i++){
                if(ssRegisteredTownCode.equals(registeredTownList.get(i).getCode())){
                    selectedRegisteredTownIndex=i;
                }

            }

        } else if (Constants.AREA_LEVEL_VILLAGE.equals(level)){
            ssRegisteredVillageCode=code;

            for(int i=0;i<registeredVillageList.size();i++){
                if(ssRegisteredVillageCode.equals(registeredVillageList.get(i).getCode())){
                    selectedRegisteredVillageIndex=i;
                }

            }
        }else if(Constants.AREA_LEVEL_ZU.equals(level)){
            ssRegisteredZuCode=code;
            for(int i=0;i<registeredzuList.size();i++){
                if(ssRegisteredZuCode.equals(registeredzuList.get(i).getCode())){
                    selectedRegisteredzuIndex=i;
                }

            }
        }
    }


    private void setHomeAddressCode(String level, String code){
        level=Util.trimString(level);
        code=Util.trimString(code);
        if (Constants.AREA_LEVEL_PROVINCE.equals(level)){
            ssAreaProvinceCode=code;
        } else if (Constants.AREA_LEVEL_CITY.equals(level)){
            ssAreaCityCode=code;
        } else if (Constants.AREA_LEVEL_COUNTY.equals(level)){
            ssAreaCountyCode=code;
        } else if (Constants.AREA_LEVEL_STREET.equals(level)){
            ssAreaTownCode=code;
        } else if (Constants.AREA_LEVEL_VILLAGE.equals(level)){
            ssAreaVillageCode=code;
        }
    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        if(bean==null){
            Toast.makeText(getActivity(),"保存失败!",Toast.LENGTH_LONG).show();
        }else {
            String flag=bean.getFlag();
            if(("1").equals(flag)){
                if(Identity.loginResultForFingertip!=null){
                    Identity.loginResultForFingertip.setFlagdangan(1);
                }

                Toast.makeText(getActivity(),"保存成功!",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }else {
                Toast.makeText(getActivity(),bean.getErrorMsg(),Toast.LENGTH_LONG).show();
            }

        }



    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        Toast.makeText(getActivity(),bean.getErrorMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addpersonFragment3(new PersonFragment3Module(this))
                .inject(this);

    }

    public void setchakan3(PersonChanBean personChanBean) {
        List <String> chufanglisst=personChanBean.getExposureHistorList();
        if(chufanglisst!=null && chufanglisst.size()!=0){
            String chufangvalus= duoxuanvaluse(chufanglisst,filtrapaifengList);
            String chufangcodes= duoxuancode(chufanglisst,filtrapaifengList);
            tv_paifeng.setText(chufangvalus);
            codepaifeng=chufangcodes;
        }


        List <String> ranliaolisst=personChanBean.getFuelTypeList();
        if(ranliaolisst!=null && ranliaolisst.size()!=0){
            String ranliaovalus= duoxuanvaluse(ranliaolisst,filtraranliaoList);
            String ranliaocodes= duoxuancode(ranliaolisst,filtraranliaoList);
            tv_ranliao.setText(ranliaovalus);
            coderanliao=ranliaocodes;
        }


        List <String> yinshuilisst=personChanBean.getDrinkingWaterList();
        if(yinshuilisst!=null && yinshuilisst.size()!=0){
            String yinshuivalus= duoxuanvaluse(yinshuilisst,filtrayinshuiList);
            String yinshiucodes= duoxuancode(yinshuilisst,filtrayinshuiList);
            tv_yinshui.setText(yinshuivalus);
            codeyinshui=yinshiucodes;
        }


        String str = personChanBean.getToilet();
        if(str==null || ("").equals(str)){

        }else {
            List<String> cesuolist = Arrays.asList(str.split(","));

            String cesuovalus= duoxuanvaluse(cesuolist,filtracesuoList);
            String cesuocodes= duoxuancode(cesuolist,filtracesuoList);
            tv_cesuo.setText(cesuovalus);
            codecesuo=cesuocodes;
        }

        String strqincu = personChanBean.getPoultryEnclosu();
        if(strqincu==null || ("").equals(strqincu)){

        }else {
            List<String> qinculist = Arrays.asList(strqincu.split(","));

            String qincuvalus= duoxuanvaluse(qinculist,filtraqincuList);
            String qincucodes= duoxuancode(qinculist,filtraqincuList);
            tv_qincu.setText(qincuvalus);
            codeqincu=qincucodes;
        }

        String date =Util.trimString(personChanBean.getArchivingDoctorDate());
        String docname =Util.trimString(personChanBean.getArchivingDoctorName());
        tv_date.setText(date);
        et_docname.setText(docname);


        if(personChanBean.getRegisterAddress()==null || TextUtils.isEmpty(personChanBean.getRegisterAddress())){
            mydoctorAreaCode = getDoctorAreaCode();
            initHouseholdAddress();
        }else {
            mydoctorAreaCode=personChanBean.getRegisterAddress();
            //todo:户籍地址初始化
            initHouseholdAddress();
        }

        if(personChanBean.getZuCode()==null || TextUtils.isEmpty(personChanBean.getZuCode())){

        }else {

            mydoctorAreaCode1=personChanBean.getZuCode();



            //todo:家庭地址初始化
            initAreaAddress();
        }





    }


    private String duoxuanvaluse(List<String> listbaolu,List<FiltrateBean> filtrabaolouList){
        String valuebaolu="";
        String codebaolou="";
        for(int i=0;i<listbaolu.size();i++){

            int ibaolu= checkSame1(listbaolu.get(i),filtrabaolouList);
            if(ibaolu!=99){
                filtrabaolouList.get(0).getChildren().get(ibaolu).setSelected(true);
            }
            String sbaolu= checkSame2(listbaolu.get(i),filtrabaolouList);
            String sbaolucode= checkSame3(listbaolu.get(i),filtrabaolouList);
            if (TextUtils.isEmpty(valuebaolu)){
                valuebaolu=  sbaolu;
                codebaolou=sbaolucode;
            }else {
                valuebaolu=valuebaolu+","+sbaolu;
                codebaolou=codebaolou+","+sbaolucode;
            }
        }

        return valuebaolu;
    }

    private String duoxuancode(List<String> listbaolu,List<FiltrateBean> filtrabaolouList){
        String valuebaolu="";
        String codebaolou="";
        for(int i=0;i<listbaolu.size();i++){

            int ibaolu= checkSame1(listbaolu.get(i),filtrabaolouList);
            if(ibaolu!=99){
                filtrabaolouList.get(0).getChildren().get(ibaolu).setSelected(true);
            }
            String sbaolu= checkSame2(listbaolu.get(i),filtrabaolouList);
            String sbaolucode= checkSame3(listbaolu.get(i),filtrabaolouList);
            if (TextUtils.isEmpty(valuebaolu)){
                valuebaolu=  sbaolu;
                codebaolou=sbaolucode;
            }else {
                valuebaolu=valuebaolu+","+sbaolu;
                codebaolou=codebaolou+","+sbaolucode;
            }
        }

        return codebaolou;
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

    private String checkSame3(String codeminzu,List<FiltrateBean> fillist){

        for(int i =0;i<fillist.get(0).getChildren().size();i++){
            if(fillist.get(0).getChildren().get(i).getCode().equals(codeminzu)){

                return fillist.get(0).getChildren().get(i).getCode();
            }
        }
        return "";
    }

}
