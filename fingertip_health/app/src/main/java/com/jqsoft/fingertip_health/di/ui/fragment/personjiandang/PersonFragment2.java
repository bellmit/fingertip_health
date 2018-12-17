package com.jqsoft.fingertip_health.di.ui.fragment.personjiandang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.fingertip.PersonChanBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPastHistoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonPostBean;
import com.jqsoft.fingertip_health.bean.fingertip.PersonallergyHistoriesBean;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_ph_dict_item;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.optionlayout.HbInputLayout;
import com.jqsoft.fingertip_health.optionlayout.HbOneSetTextOptionsLayout;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.DecimalDigitsInputFilter;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class PersonFragment2 extends Fragment implements View.OnClickListener{
    @BindView(R.id.sv_content)
    ScrollView sv_content;

    View failureView;
    public static final int REQUEST_A = 1;
    private HbOneSetTextOptionsLayout choose_0,choose_1,choose_3,choose_5;
    PersonJiandangActivity activity;

    private View topview;
    private String version_num="";


    private LinearLayout ll_guomin,ll_baolou,ll_jibing,ll_fuqin,ll_muqin,ll_xiongdi,ll_zinv,ll_canji;
    private TextView tv_guomin,tv_baolou,tv_jibing,tv_fuqin,tv_muqin,tv_xiongdi,tv_zinv,tv_canji;
    private EditText et_yichuanbing;
    private CompositeSubscription mInitializeSubscription;



    private void registerInitializeSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constant.ENENT_BW, RxBusBaseMessage.class).subscribe(new Action1<RxBusBaseMessage>() {
            @Override
            public void call(RxBusBaseMessage indexAndOnlineSignInitialData) {
                if (indexAndOnlineSignInitialData != null) {

                    switch (indexAndOnlineSignInitialData.getCode()){
                        case 3:
                            et_yichuanbing.setVisibility(View.GONE);
                            et_yichuanbing.setEnabled(false);
                            et_yichuanbing.setText("");
                            break;
                        case 4:

                            et_yichuanbing.setVisibility(View.VISIBLE);
                            et_yichuanbing.setHint("请输入遗传病名称");
                            et_yichuanbing.setEnabled(true);

                            et_yichuanbing.setFocusable(true);
                            et_yichuanbing.setFocusableInTouchMode(true);
                            et_yichuanbing.requestFocus();
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
        View view = inflater.inflate(R.layout.fragment_person_two_layout, container, false);
        activity = (PersonJiandangActivity) getActivity();
        topview=(View)view.findViewById(R.id.topview);
        choose_0 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_0);
        choose_1 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_1);
        choose_3 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_3);
        choose_5 = (HbOneSetTextOptionsLayout) view.findViewById(R.id.choose_5);
        ll_guomin=(LinearLayout)view.findViewById(R.id.ll_guomin);
        tv_guomin=(TextView)view.findViewById(R.id.tv_guomin);

        ll_baolou=(LinearLayout)view.findViewById(R.id.ll_baolou);
        tv_baolou=(TextView)view.findViewById(R.id.tv_baolou);

        ll_jibing=(LinearLayout)view.findViewById(R.id.ll_jibing);
        tv_jibing=(TextView)view.findViewById(R.id.tv_jibing);

        ll_fuqin=(LinearLayout)view.findViewById(R.id.ll_fuqin);
        tv_fuqin=(TextView)view.findViewById(R.id.tv_fuqin);

        ll_muqin=(LinearLayout)view.findViewById(R.id.ll_muqin);
        tv_muqin=(TextView)view.findViewById(R.id.tv_muqin);

        ll_xiongdi=(LinearLayout)view.findViewById(R.id.ll_xiongdi);
        tv_xiongdi=(TextView)view.findViewById(R.id.tv_xiongdi);

        ll_zinv=(LinearLayout)view.findViewById(R.id.ll_zinv);
        tv_zinv=(TextView)view.findViewById(R.id.tv_zinv);

        ll_canji=(LinearLayout)view.findViewById(R.id.ll_canji);
        tv_canji=(TextView)view.findViewById(R.id.tv_canji);
        version_num= DaggerApplication.getInstance().getVersionNum();

        et_yichuanbing=(EditText)view.findViewById(R.id.et_yichuanbing);

        List<NameValueBeanWithNo> genderList0 = new ArrayList<>();
        genderList0.add(new NameValueBeanWithNo("无", "1", "1", false));
        genderList0.add(new NameValueBeanWithNo("有", "2", "1", false));
        setViewDataDictionaryDataByCode(genderList0, choose_0);

        List<NameValueBeanWithNo> genderList1 = new ArrayList<>();
        genderList1.add(new NameValueBeanWithNo("无", "1", "1", false));
        genderList1.add(new NameValueBeanWithNo("有", "2", "1", false));
        setViewDataDictionaryDataByCode(genderList1, choose_1);

        List<NameValueBeanWithNo> genderList2 = new ArrayList<>();
        genderList2.add(new NameValueBeanWithNo("无", "1", "1", false));
        genderList2.add(new NameValueBeanWithNo("有", "2", "1", false));
        setViewDataDictionaryDataByCode(genderList2, choose_3);

        List<NameValueBeanWithNo> genderList5 = new ArrayList<>();
        genderList5.add(new NameValueBeanWithNo("无", "1", "4", false));
        genderList5.add(new NameValueBeanWithNo("有", "2", "3", false));
        setViewDataDictionaryDataByCode(genderList5, choose_5);
//
//        choose_5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
//            }
//        });
//        choose_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
//            }
//        });
//        choose_5.


        initParam();
        ll_guomin.setOnClickListener(this);
        ll_baolou.setOnClickListener(this);
        ll_jibing.setOnClickListener(this);
        ll_fuqin.setOnClickListener(this);
        ll_muqin.setOnClickListener(this);
        ll_xiongdi.setOnClickListener(this);
        ll_zinv.setOnClickListener(this);
        ll_canji.setOnClickListener(this);
        registerInitializeSubscription();
        return view;



    }

    private List<FiltrateBean> filtraguominList = new ArrayList<>();
    private List<FiltrateBean> filtrabaolouList = new ArrayList<>();
    private List<FiltrateBean> filtrajibingList = new ArrayList<>();
    private List<FiltrateBean> filtrafuqinList = new ArrayList<>();
    private List<FiltrateBean> filtramuqinList = new ArrayList<>();
    private List<FiltrateBean> filtraxiongdiList = new ArrayList<>();
    private List<FiltrateBean> filtrazinvList = new ArrayList<>();
    private List<FiltrateBean> filtracanjiList = new ArrayList<>();
    private void initParam() {

        String[] losts = {"无", "青霉素", "磺胺", "链霉素", "其他"};
        List<gdws_ph_dict_item>  listyaowu= DataSupport.where("dict_code=? and version_num=?","ALLERGY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb = new FiltrateBean();
        lostfb.setTypeName("药物过敏史");
        List<FiltrateBean.Children> lostchildrenList = new ArrayList<>();
        for (int x = 0; x < listyaowu.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();

            cd.setValue(listyaowu.get(x).getName());
            cd.setCode(listyaowu.get(x).getItem_code());
            lostchildrenList.add(cd);
        }
        lostfb.setChildren(lostchildrenList);
        filtraguominList.add(lostfb);



        String[] losts1 = {"无", "化学品", "毒物", "射线"};
        List<gdws_ph_dict_item>  listbaolu= DataSupport.where("dict_code=? and version_num=?","EXPOSURE_HISTOR",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb1 = new FiltrateBean();
        lostfb1.setTypeName("暴露史");
        List<FiltrateBean.Children> lostchildrenList1 = new ArrayList<>();
        for (int x = 0; x < listbaolu.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listbaolu.get(x).getName());
            cd.setCode(listbaolu.get(x).getItem_code());
            lostchildrenList1.add(cd);
        }
        lostfb1.setChildren(lostchildrenList1);
        filtrabaolouList.add(lostfb1);

        String[] losts2 = {"无", "高血压", "糖尿病", "冠心病", "慢性阻塞性肺疾病", "恶性肿瘤", "脑卒中","严重精神障碍","结核病","肝炎","先天畸形","其他"};
        List<gdws_ph_dict_item>  listjibing= DataSupport.where("dict_code=? and version_num=?","FAMILY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb2 = new FiltrateBean();
        lostfb2.setTypeName("既往史疾病");
        List<FiltrateBean.Children> lostchildrenList2 = new ArrayList<>();
        for (int x = 0; x < listjibing.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listjibing.get(x).getName());
            cd.setCode(listjibing.get(x).getItem_code());
            lostchildrenList2.add(cd);
        }
        lostfb2.setChildren(lostchildrenList2);
        filtrajibingList.add(lostfb2);

        String[] losts3 = {"无", "高血压", "糖尿病", "冠心病", "慢性阻塞性肺疾病", "恶性肿瘤", "脑卒中","严重精神障碍","结核病","肝炎","先天畸形","其他"};
        List<gdws_ph_dict_item>  listjibingfuqin= DataSupport.where("dict_code=? and version_num=?","FAMILY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb3 = new FiltrateBean();
        lostfb3.setTypeName("家族史父亲");
        List<FiltrateBean.Children> lostchildrenList3 = new ArrayList<>();
        for (int x = 0; x < listjibingfuqin.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listjibingfuqin.get(x).getName());
            cd.setCode(listjibingfuqin.get(x).getItem_code());
            lostchildrenList3.add(cd);
        }
        lostfb3.setChildren(lostchildrenList3);
        filtrafuqinList.add(lostfb3);


        String[] losts4 = {"无", "高血压", "糖尿病", "冠心病", "慢性阻塞性肺疾病", "恶性肿瘤", "脑卒中","严重精神障碍","结核病","肝炎","先天畸形","其他"};
        List<gdws_ph_dict_item>  listjibingmuqin= DataSupport.where("dict_code=? and version_num=?","FAMILY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);

        FiltrateBean lostfb4 = new FiltrateBean();
        lostfb4.setTypeName("家族史母亲");
        List<FiltrateBean.Children> lostchildrenList4 = new ArrayList<>();
        for (int x = 0; x < listjibingmuqin.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listjibingmuqin.get(x).getName());
            cd.setCode(listjibingmuqin.get(x).getItem_code());
            lostchildrenList4.add(cd);
        }
        lostfb4.setChildren(lostchildrenList4);
        filtramuqinList.add(lostfb4);

        String[] losts5 = {"无", "高血压", "糖尿病", "冠心病", "慢性阻塞性肺疾病", "恶性肿瘤", "脑卒中","严重精神障碍","结核病","肝炎","先天畸形","其他"};
        List<gdws_ph_dict_item>  listjibingxiongdi= DataSupport.where("dict_code=? and version_num=?","FAMILY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb5 = new FiltrateBean();
        lostfb5.setTypeName("家族史兄弟姐妹");
        List<FiltrateBean.Children> lostchildrenList5 = new ArrayList<>();
        for (int x = 0; x < listjibingxiongdi.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listjibingxiongdi.get(x).getName());
            cd.setCode(listjibingxiongdi.get(x).getItem_code());
            lostchildrenList5.add(cd);
        }
        lostfb5.setChildren(lostchildrenList5);
        filtraxiongdiList.add(lostfb5);

        String[] losts6 = {"无", "高血压", "糖尿病", "冠心病", "慢性阻塞性肺疾病", "恶性肿瘤", "脑卒中","严重精神障碍","结核病","肝炎","先天畸形","其他"};
        List<gdws_ph_dict_item>  listjibingzinv= DataSupport.where("dict_code=? and version_num=?","FAMILY_HISTORY",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb6 = new FiltrateBean();
        lostfb6.setTypeName("家族史子女");
        List<FiltrateBean.Children> lostchildrenList6 = new ArrayList<>();
        for (int x = 0; x < listjibingzinv.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listjibingzinv.get(x).getName());
            cd.setCode(listjibingzinv.get(x).getItem_code());
            lostchildrenList6.add(cd);
        }
        lostfb6.setChildren(lostchildrenList6);
        filtrazinvList.add(lostfb6);

        String[] losts7 = {"无", "视力残疾", "听力残疾", "言语残疾", "肢体残疾","智力残疾","精神残疾","其他残疾"};
        List<gdws_ph_dict_item>  listcanji= DataSupport.where("dict_code=? and version_num=?","DISABILITY_SITU",version_num).order("sort_no  asc").find(gdws_ph_dict_item.class);
        FiltrateBean lostfb7 = new FiltrateBean();
        lostfb7.setTypeName("残疾情况");
        List<FiltrateBean.Children> lostchildrenList7 = new ArrayList<>();
        for (int x = 0; x < listcanji.size(); x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(listcanji.get(x).getName());
            cd.setCode(listcanji.get(x).getItem_code());
            lostchildrenList7.add(cd);
        }
        lostfb7.setChildren(lostchildrenList7);
        filtracanjiList.add(lostfb7);
    }


    @Override
    public void onClick(View v) {
        //获取组件的资源id
        int id = v.getId();
        switch (id) {
            case R.id.ll_guomin:
                initguomin();
                break;
            case R.id.ll_baolou:
                initbaolou();
                break;
            case R.id.ll_jibing:
                initjibing();
                break;
            case R.id.ll_fuqin:
                initfuqin();
                break;
            case R.id.ll_muqin:
                initmuqin();
                break;
            case R.id.ll_xiongdi:
                initxiongdi();
                break;
            case R.id.ll_zinv:
                initzinv();
                break;
            case R.id.ll_canji:
                initcanji();
                break;

            default:
                break;
        }
    }
    private FlowPopWindow flowPopWindow;

    List<PersonallergyHistoriesBean> listguomin=new ArrayList<>();

    private void initguomin(){


        listguomin.clear();
        flowPopWindow = new FlowPopWindow(getActivity(), filtraguominList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";


                for (FiltrateBean fb : filtraguominList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                PersonallergyHistoriesBean personallergyHistoriesBean = new PersonallergyHistoriesBean();
                                personallergyHistoriesBean.setAllergyHistoryCode(children.getCode());
                                listguomin.add(personallergyHistoriesBean);
                            }else {
                                value=value+","+children.getValue();

                                PersonallergyHistoriesBean personallergyHistoriesBean1 = new PersonallergyHistoriesBean();
                                personallergyHistoriesBean1.setAllergyHistoryCode(children.getCode());
                                listguomin.add(personallergyHistoriesBean1);
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_guomin.setText(value);
                }else {
                    tv_guomin.setText("");
                }

            }
        });
    }

    private String codebaolou="";
    private void initbaolou(){



        flowPopWindow = new FlowPopWindow(getActivity(), filtrabaolouList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrabaolouList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codebaolou=children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codebaolou=codebaolou+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_baolou.setText(value);
                }else {
                    tv_baolou.setText("");
                }

            }
        });
    }

    List<PersonPastHistoryBean> listjibing=new ArrayList<>();
    List<PersonPastHistoryBean> listjibing1=new ArrayList<>();

    private String  codejibing="";
    private void initjibing(){

        listjibing.clear();
        listjibing1.clear();

        flowPopWindow = new FlowPopWindow(getActivity(), filtrajibingList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                listjibing1.clear();
                String value="";
                for (FiltrateBean fb : filtrajibingList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codejibing=children.getCode();

                            }else {
                                value=value+","+children.getValue();
                                codejibing=codejibing+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){

                    tv_jibing.setText(value);
                    PersonPastHistoryBean personPastHistoryBean = new PersonPastHistoryBean();
                    personPastHistoryBean.setTypeCode("1");
                    personPastHistoryBean.setPastHistoryCode(codejibing);
//                    listjibing.add(personPastHistoryBean);
                    listjibing1.add(personPastHistoryBean);
                }else {
                    tv_jibing.setText("");
                }

            }
        });
    }

    private String codefather="";
    private void initfuqin(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrafuqinList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrafuqinList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codefather=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codefather=codefather+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_fuqin.setText(value);
                }else {
                    tv_fuqin.setText("");
                }

            }
        });
    }
    private String codemuqin="";
    private void initmuqin(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtramuqinList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtramuqinList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codemuqin=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codemuqin=codemuqin+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_muqin.setText(value);
                }else {
                    tv_muqin.setText("");
                }

            }
        });
    }
    private String codexiongdi="";
    private void initxiongdi(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtraxiongdiList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtraxiongdiList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codexiongdi=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codexiongdi=codexiongdi+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_xiongdi.setText(value);
                }else {
                    tv_xiongdi.setText("");
                }

            }
        });
    }

    private String codezinv="";
    private void initzinv(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtrazinvList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtrazinvList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codezinv=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codezinv=codezinv+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_zinv.setText(value);
                }else {
                    tv_zinv.setText("");
                }

            }
        });
    }

    private String codecanji="";
    private void initcanji(){




        flowPopWindow = new FlowPopWindow(getActivity(), filtracanjiList,false);
        flowPopWindow.showAsDropDown(activity.gettopview());
        flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                String value="";
                for (FiltrateBean fb : filtracanjiList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
                            if (TextUtils.isEmpty(value)){
                                value=   children.getValue();
                                codecanji=   children.getCode();
                            }else {
                                value=value+","+children.getValue();
                                codecanji=codecanji+","+children.getCode();
                            }

                    }
                }
                if (!TextUtils.isEmpty(value)){
                    tv_canji.setText(value);
                }else {
                    tv_canji.setText("");
                }

            }
        });
    }


    public PersonPostBean getdata2() {
//        String xingming = et_person_name.getText().toString();
//        String shenfenzheng = et_person_idcard.getText().toString();
//        String person_sex = tv_person_sex.getText().toString();
//        String person_riqi = tv_person_riqi.getText().toString();
//        String person_danwei = et_person_danwei.getText().toString();
//        String changzhu = choose_1.getSingleSelectName();
//        String minzu = tv_minzu.getText().toString();

        PersonJiandangActivity activity = (PersonJiandangActivity) getActivity();
        PersonPostBean hbPostBean=new PersonPostBean();
        hbPostBean.setAllergyHistories(listguomin);
        hbPostBean.setExposureHistor(codebaolou);
//        hbPostBean.setSexCode("0");
//        hbPostBean.setSexName("男");


        if(choose_0.getSingleSelectValue()==null || TextUtils.isEmpty(choose_0.getSingleSelectValue())){

        }else {
            PersonPastHistoryBean personPastHistoryBean2 = new PersonPastHistoryBean();
            personPastHistoryBean2.setTypeCode("2");
            personPastHistoryBean2.setPastHistoryCode(choose_0.getSingleSelectValue());
            listjibing.add(personPastHistoryBean2);
        }


        if(choose_1.getSingleSelectValue()==null || TextUtils.isEmpty(choose_1.getSingleSelectValue())){

        }else {
            PersonPastHistoryBean personPastHistoryBean3 = new PersonPastHistoryBean();
            personPastHistoryBean3.setTypeCode("3");
            personPastHistoryBean3.setPastHistoryCode(choose_1.getSingleSelectValue());
            listjibing.add(personPastHistoryBean3);
        }


        if(choose_3.getSingleSelectValue()==null || TextUtils.isEmpty(choose_3.getSingleSelectValue())){

        }else {
            PersonPastHistoryBean personPastHistoryBean4 = new PersonPastHistoryBean();
            personPastHistoryBean4.setTypeCode("4");
            personPastHistoryBean4.setPastHistoryCode(choose_3.getSingleSelectValue());
            listjibing.add(personPastHistoryBean4);
        }



        listjibing.addAll(listjibing1);
        hbPostBean.setPastHistories(listjibing);

        hbPostBean.setFatherDisease(codefather);
        hbPostBean.setMotherDisease(codemuqin);
        hbPostBean.setBrothersDisease(codexiongdi);
        hbPostBean.setChildrenDisease(codezinv);
        hbPostBean.setDeformitySituation(codecanji);
        hbPostBean.setHereditaryHistory(choose_5.getSingleSelectValue());
        hbPostBean.setHereditaryName(et_yichuanbing.getText().toString().trim());


        return hbPostBean;

    }


    public void setchakan2( PersonChanBean personChanBean) {
        listguomin.clear();
        listguomin=personChanBean.getAllergyHistoryList();
        if(listguomin==null || listguomin.size()==0){

        }else {
            String valueguomin="";
            for(int i=0;i<listguomin.size();i++){
                int iguomin= checkSame1(listguomin.get(i).getAllergyHistoryCode(),filtraguominList);
                if(iguomin!=99){
                    filtraguominList.get(0).getChildren().get(iguomin).setSelected(true);
                }
                String sguomin= checkSame2(listguomin.get(i).getAllergyHistoryCode(),filtraguominList);
                if (TextUtils.isEmpty(valueguomin)){
                    valueguomin=  sguomin;
                }else {
                    valueguomin=valueguomin+","+sguomin;
                }
            }
            tv_guomin.setText(valueguomin);
        }


        listjibing.clear();
        listjibing=personChanBean.getPastHistorList();
        if(listjibing!=null && listjibing.size()!=0){
            String valuejibing="";
            for(int i=0;i<listjibing.size();i++){
                if(listjibing.get(i).getTypeCode().equals("1")){
                    int ijibing= checkSame1(listjibing.get(i).getPastHistoryCode(),filtrajibingList);
                    if(ijibing!=99){
                        filtrajibingList.get(0).getChildren().get(ijibing).setSelected(true);
                    }

                    String sjibing= checkSame2(listjibing.get(i).getPastHistoryCode(),filtrajibingList);
                    if (TextUtils.isEmpty(valuejibing)){
                        valuejibing=  sjibing;
                    }else {
                        valuejibing=valuejibing+","+sjibing;
                    }

                }else if(listjibing.get(i).getTypeCode().equals("2")){

                    String habitResidenceType = Util.trimString(listjibing.get(i).getPastHistoryCode());
                    choose_0.setSingleSelectValue1(habitResidenceType);

                }else if(listjibing.get(i).getTypeCode().equals("3")){

                    String habitResidenceType = Util.trimString(listjibing.get(i).getPastHistoryCode());
                    choose_1.setSingleSelectValue1(habitResidenceType);

                }else if(listjibing.get(i).getTypeCode().equals("4")){

                    String habitResidenceType = Util.trimString(listjibing.get(i).getPastHistoryCode());
                    choose_3.setSingleSelectValue1(habitResidenceType);

                }

            }
            tv_jibing.setText(valuejibing);
        }












//        codebaolou
        List<String> listbaolu =personChanBean.getExposureHistorList();
        if(listbaolu!=null && listbaolu.size()!=0){
            String valuebaolu="";
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
            tv_baolou.setText(valuebaolu);
        }

        List <String> fuqinlisst=personChanBean.getFatherDiseaseList();
        if(fuqinlisst!=null && fuqinlisst.size()!=0){
            String fuqinvalus= duoxuanvaluse(fuqinlisst,filtrafuqinList);
            String fuqincodes= duoxuancode(fuqinlisst,filtrafuqinList);
            tv_fuqin.setText(fuqinvalus);
            codefather=fuqincodes;
        }



        List <String> muqinlisst=personChanBean.getMotherDiseaseList();
        if(muqinlisst!=null && muqinlisst.size()!=0){
            String muqinvalus= duoxuanvaluse(muqinlisst,filtramuqinList);
            String muqincodes= duoxuancode(muqinlisst,filtramuqinList);
            tv_muqin.setText(muqinvalus);
            codemuqin=muqincodes;
        }


        List <String> xiongdilisst=personChanBean.getBrothersDiseaseInfo();
        if(xiongdilisst!=null && xiongdilisst.size()!=0){
            String xiongdivalus= duoxuanvaluse(xiongdilisst,filtraxiongdiList);
            String xiongdicodes= duoxuancode(xiongdilisst,filtraxiongdiList);
            tv_xiongdi.setText(xiongdivalus);
            codexiongdi=xiongdicodes;
        }


        List <String> zinvlisst=personChanBean.getChildrenDiseaseList();
        if(zinvlisst!=null && zinvlisst.size()!=0){
            String zinvvalus= duoxuanvaluse(zinvlisst,filtrazinvList);
            String zinvcodes= duoxuancode(zinvlisst,filtrazinvList);
            tv_zinv.setText(zinvvalus);
            codezinv=zinvcodes;
        }


        List <String> canjilist=personChanBean.getDeformitySituationList();
        if(canjilist!=null && canjilist.size()!=0){
            String canjivalus= duoxuanvaluse(canjilist,filtracanjiList);
            String canjicodes= duoxuancode(canjilist,filtracanjiList);
            tv_canji.setText(canjivalus);
            codecanji=canjicodes;
        }


        String rh=Util.trimString(personChanBean.getHereditaryName());
        String habitResidenceType = Util.trimString(personChanBean.getHereditaryHistory());
        choose_5.setSingleSelectValue1(habitResidenceType);
        et_yichuanbing.setText(rh);

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

}
