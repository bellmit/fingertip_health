package com.jqsoft.fingertip_health.di.ui.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.MyAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.UrbanbaseInfoSaveBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MyNodeBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanbaseInfobianjiBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.contract.UrbanBaseInfoContract;
import com.jqsoft.fingertip_health.di.module.UrbanBaseInfoFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.UrbanBaseInfoPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.util.Utils;
import com.jqsoft.fingertip_health.utils.UserEventNew;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.IDCard;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017-07-07.
 */
//
public class UrbanBaseInfoBianjiStatusFragment extends AbstractFragment implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,UrbanBaseInfoContract.View{


    @Inject
    UrbanBaseInfoPresenter mPresenter;

    private String type;

    @BindView(R.id.tv_jiedao)
    TextView tv_jiedao;

    @BindView(R.id.tv_hujileixing)
    TextView tv_hujileixing;

    @BindView(R.id.tv_hujixingzhi)
    TextView tv_hujixingzhi;

    @BindView(R.id.tv_jiatingleibie)
    TextView tv_jiatingleibie;

    @BindView(R.id.tv_zhipin)
    TextView tv_zhipin;

    @BindView(R.id.tv_jinan)
    TextView tv_jinan;

    @BindView(R.id.tv_kaihuhang)
    TextView tv_kaihuhang;

    @BindView(R.id.et_xiangzhen)
    TextView et_xiangzhen;

    @BindView(R.id.et_name)
    TextView et_name;

    @BindView(R.id.et_idcard)
    TextView et_idcard;

    @BindView(R.id.btn_save)
    RoundTextView btn_save;

    @BindView(R.id.tv_sex)
    TextView tv_sex;

    @BindView(R.id.tv_birth)
    TextView tv_birth;

    @BindView(R.id.et_applytime)
    TextView et_applytime;

    @BindView(R.id.et_phone)
    TextView et_phone;

    @BindView(R.id.et_kaihuren)
    TextView et_kaihuren;

    @BindView(R.id.et_bankaccount)
    TextView et_bankaccount;

    @BindView(R.id.et_hujiadress)
    TextView et_hujiadress;

    @BindView(R.id.et_familyadress)
    TextView et_familyadress;

    @BindView(R.id.et_applyreason)
    TextView et_applyreason;
    @BindView(R.id.et_diaochareason)
    TextView et_diaochareason;

    @BindView(R.id.et_remark)
    TextView et_remark;

    @BindView(R.id.tv_jiuzhuleibie)
    TextView tv_jiuzhuleibie;

    @BindView(R.id.view36)
    View view36;

    @BindView(R.id.ll_jinan_reason)
    LinearLayout ll_jinan_reason;

    @BindView(R.id.tv_jinan_reason)
    TextView tv_jinan_reason;

    @BindView(R.id.btn_delete)
    RoundTextView btn_delete;



    private String sCodeshi="",sCodexian="",sCodequ="",sCodejiedao="";
    ArrayList<SRCLoginAreaBean> arealistqu = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistjiedao = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistxian = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistshi = new ArrayList<>();
    private ArrayList<ArrayList<SRCLoginAreaBean>> jiedaoListOut;

    private OptionsPickerView ophujileixing,ophujixingzhi,opjiatingleibie,opzhipin,opjinan,opkaihuhang,opArea;

    private String officeCode="",communityCode="",registerType="",registerProperty="",familyType="",bankName="",
            farmerCode="",familyPoorType="",itemCode="",poorReason="",ItemId="",ItemIdNew="";

    String xian="",shi="",xiang="";
    private String mybatchNo,status;


    HashSet<String> hashSet = new HashSet<String>();
    List<String> myItemName= new ArrayList<String>();
    List<String> myItemNameNew= new ArrayList<String>();


    List<Map<String, String>> parentList = new ArrayList<Map<String, String>>();
    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    ExpandableListView exListView;
    MyAdapter newadapter=null;

    @BindView(R.id.iv_idcard)
    ImageView iv_idcard;

    public UrbanBaseInfoBianjiStatusFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_urban_baseinfo_status_layout;
    }

    @Override
    protected void initData() {

        et_phone.addTextChangedListener(new TextWatcher() {
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
                            Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(size==12 || size==13){
                    if(s.toString().indexOf("-")!=-1){
                        boolean isTel= Utils.IsTelephone(s.toString());
                        if(  isTel){

                        }else {
                            Toast.makeText(getActivity(),"请输入正确的联系电话",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "请输入正确的联系电话", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        et_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_idcard.setCursorVisible(true);// 再次点击显示光标
            }
        });

        tv_jinan.setText("否");

        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new    java.util.Date());
        et_applytime.setText(date);

        tv_hujileixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myreg_type = DataSupport.where(" pcode=? ","reg_type" ).find(SRCLoginDataDictionaryBean.class);
                inithujileixing(tv_hujileixing, myreg_type, "户籍类型");
                ophujileixing.show();*/
            }
        });

        tv_hujixingzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","regpro" ).find(SRCLoginDataDictionaryBean.class);
                inithujixingzhi(tv_hujixingzhi, myregpro, "户籍性质");
                ophujixingzhi.show();*/
            }
        });

        tv_jiatingleibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","family_type" ).find(SRCLoginDataDictionaryBean.class);
                initjiatingleibie(tv_jiatingleibie, myregpro, "家庭类别");
                opjiatingleibie.show();*/
            }
        });

        tv_zhipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","poor_reason" ).find(SRCLoginDataDictionaryBean.class);
                initzhipin(tv_zhipin, myregpro, "致贫原因");
                opzhipin.show();*/
            }
        });
        tv_jinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  List<String> myList = new ArrayList<String>();

                myList.add("否");
                myList.add("是");
                initjinan(tv_jinan, myList, "是否急难");
                opjinan.show();*/
            }
        });

        tv_kaihuhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","bank" ).find(SRCLoginDataDictionaryBean.class);
                initkaihuhang(tv_kaihuhang, myregpro, "开户行");
                opkaihuhang.show();*/
            }
        });

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String sex=tv_sex.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(getActivity(),"请输入正确的身份证号码,会自动填充性别!",Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        tv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String sex=tv_birth.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(getActivity(),"请输入正确的身份证号码,会自动填充出生年月!",Toast.LENGTH_SHORT).show();
                }*/

            }
        });


        iv_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name.setText("");
                et_idcard.setText("");
                tv_sex.setText("");
                tv_birth.setText("");
                et_phone.setText("");
                tv_hujileixing.setText("");
                tv_hujixingzhi.setText("");
                tv_jiatingleibie.setText("");
                et_kaihuren.setText("");
                tv_kaihuhang.setText("");
                et_bankaccount.setText("");
                et_hujiadress.setText("");
                et_familyadress.setText("");
                et_applyreason.setText("");
                tv_jiuzhuleibie.setText("");
                tv_zhipin.setText("");
                tv_jinan.setText("否");
                tv_jinan_reason.setText("");
                et_diaochareason.setText("");
                et_remark.setText("");
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sxiangzhen =et_xiangzhen.getText().toString();
                String sshequ =tv_jiedao.getText().toString();
                String sname =et_name.getText().toString();
                String sidcard =et_idcard.getText().toString();
                String ssex =tv_sex.getText().toString();
                String sbirth =tv_birth.getText().toString();
                String sapplytime =et_applytime.getText().toString();
                String sphone =et_phone.getText().toString();
                String shujileixing =tv_hujileixing.getText().toString();
                String shujixingzhi =tv_hujixingzhi.getText().toString();
                String sjiatingleibie =tv_jiatingleibie.getText().toString();
                String skaihuren =et_kaihuren.getText().toString();
                String skaihuhang =tv_kaihuhang.getText().toString();
                String sbankaccount =et_bankaccount.getText().toString();
                String shujiadress =et_hujiadress.getText().toString();
                String sfamilyadress =et_familyadress.getText().toString();
                String sapplyreason =et_applyreason.getText().toString();
                String szhipin =tv_zhipin.getText().toString();
                String sjinan =tv_jinan.getText().toString();
                String sdiaochareason =et_diaochareason.getText().toString();
                String sremark =et_remark.getText().toString();


                String Idcard=et_idcard.getText().toString();
                String info= IDCard.IDCardValidate(Idcard);

                if(TextUtils.isEmpty(sxiangzhen)){
                    Toast.makeText(getActivity(),"乡镇不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sshequ)){
                    Toast.makeText(getActivity(),"社区不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sname)){
                    Toast.makeText(getActivity(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sidcard)){
                    Toast.makeText(getActivity(),"身份证号码不能为空",Toast.LENGTH_SHORT).show();
                }else if( !TextUtils.isEmpty(info)){
                    Toast.makeText(getActivity(),info.toString(),Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(ssex)){
                    Toast.makeText(getActivity(),"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sbirth)){
                    Toast.makeText(getActivity(),"出生日期不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sapplytime)){
                    Toast.makeText(getActivity(),"申请日期不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sphone)){
                    Toast.makeText(getActivity(),"联系电话不能为空",Toast.LENGTH_SHORT).show();
                }else if(sphone.length()<11){
                    Toast.makeText(getActivity(),"请输入正确的联系方式",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(shujileixing)){
                    Toast.makeText(getActivity(),"户籍类型不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(shujixingzhi)){
                    Toast.makeText(getActivity(),"户籍性质不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sjiatingleibie)){
                    Toast.makeText(getActivity(),"家庭类别不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(shujiadress)){
                    Toast.makeText(getActivity(),"户籍地址不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sfamilyadress)){
                    Toast.makeText(getActivity(),"家庭住址不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sapplyreason)){
                    Toast.makeText(getActivity(),"申请理由不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(szhipin)){
                    Toast.makeText(getActivity(),"致贫原因不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sjinan)){
                    Toast.makeText(getActivity(),"是否急难不能为空",Toast.LENGTH_SHORT).show();
                }else {

                    String sexName = IDCard.getSex(sidcard);
                    String sbirthName=IDCard.getbirthdayNew(sidcard);
                    tv_sex.setText(sexName);
                    tv_birth.setText(sbirthName);


                    String sexcode="";
                    if(ssex.equals("男")){
                        sexcode="sex_1";
                    }else if(ssex.equals("女")){
                        sexcode="sex_2";
                    }

                    String jinancode="";
                    String jinanreason="";
                    if(sjinan.equals("否")){
                        jinancode="0";
                    //    ll_jinan_reason.setVisibility(View.GONE);
                     //   view36.setVisibility(View.GONE);
                        jinanreason="";

                    }else {
                        jinancode="1";
                      //  ll_jinan_reason.setVisibility(View.VISIBLE);
                     //   view36.setVisibility(View.VISIBLE);
                        jinanreason=tv_jinan_reason.getText().toString();
                    }



                    String editor= IdentityManager.getLoginSuccessUsername(getActivity());
                    Map<String, String> map = ParametersFactory.getUrbanbaseinfoSave(getActivity(),sname,editor,sidcard,sexcode,sbirth,sphone,sapplytime,skaihuren
                            ,sbankaccount,shujiadress,sfamilyadress,sapplyreason,jinancode,sdiaochareason,sremark, officeCode, communityCode,
                            registerType, registerProperty, familyType, bankName,
                            poorReason,mybatchNo,ItemIdNew,jinanreason,arearLevel);
                    mPresenter.main(map);
                }

            }
        });

        tv_jiuzhuleibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*
                String sourceStr = "1,2,3,4,5";
                String[] sourceStrArray = null;

                ItemId=tv_jiuzhuleibie.getText().toString();
                if(TextUtils.isEmpty(ItemId)){
                    myItemName.clear();
                }else {
                    sourceStrArray = ItemId.split(";");
                    for (int i = 0; i < sourceStrArray.length; i++) {
                        myItemName.add(sourceStrArray[i]);
                    }
                }







                View bottomView = View.inflate(getActivity(),R.layout.tree_list_new,null);//填充ListView布局
                exListView = (ExpandableListView) bottomView.findViewById(R.id.exlistview);//初始化ListView控件


                final List<SRCLoginSalvationBean>  myalljiuzhu = DataSupport.findAll(SRCLoginSalvationBean.class);

                List<SRCLoginSalvationBean>  mypjiuzhu = DataSupport.where(" pId=? ","1003" ).find(SRCLoginSalvationBean.class);


                for(int i=0;i<mypjiuzhu.size();i++){
                    Map<String, String> groupMap = new HashMap<String, String>();
                    groupMap.put("groupText", mypjiuzhu.get(i).getName());
                    groupMap.put("isGroupCheckd", "No");
                    parentList.add(groupMap);
                }

                for (int i = 0; i < mypjiuzhu.size(); i++)
                {
                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

                    for (int j = 0; j < myalljiuzhu.size(); j++)
                    {
                        if(mypjiuzhu.get(i).getItemId().equals(myalljiuzhu.get(j).getpId())){
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("childItem", myalljiuzhu.get(j).getName());
                            map.put("isChecked", "No");
                            list.add(map);
                        }


                    }
                    childData.add(list);
                }

                //   initDatas();

                newadapter = new MyAdapter(getActivity(),childData,parentList,exListView,myItemName,tv_hujileixing.getText().toString());
                exListView.setAdapter(newadapter);
                exListView.expandGroup(0);



                //  myItemName.clear();



                exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        //存取已选定的集合
                        hashSet = new HashSet<String>();
                        // myItemName=new ArrayList<String>();
                    }
                });
                // ExpandableListView的Group的点击事件
                exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
                {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id)
                    {
                        // 可以写点击后实现的功能

                        return false;
                    }
                });
                // ExpandableListView的child的点击事件

                exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        Map<String, String> map = childData.get(groupPosition).get(
                                childPosition);
                        String childChecked = map.get("isChecked");
                        if ("No".equals(childChecked))
                        {
                            map.put("isChecked", "Yes");
                            hashSet.add("选定" + childPosition);
                            String s1=childData.get(groupPosition).get(childPosition).get("childItem");
                            myItemName.add(s1);
                        } else
                        {
                            map.put("isChecked", "No");
                            if (hashSet.contains("选定" + childPosition))
                            {
                                hashSet.remove("选定" + childPosition);
                            }


                            String sFlag =childData.get(groupPosition).get(childPosition).get("childItem");


                            for (int i = myItemName.size()-1; i >=0; i--) {
                                if (myItemName.get(i).equals(sFlag)) {
                                    myItemName.remove(i);
                                }
                            }
                            *//*if(myItemName.contains(childData.get(groupPosition).get(childPosition).get("childItem"))){
                                myItemName.remove(childData.get(groupPosition).get(childPosition).get("childItem"));
                            }*//*
                        }


                        System.out.println("选定的长度==1" + hashSet.size());
                        System.out.println("选定的长度==2"
                                + childData.get(groupPosition).size());
                           *//* if (hashSet.size() == childData.get(groupPosition).size())
                            {
                                parentList.get(groupPosition).put("isGroupCheckd", "Yes");
                            } else
                            {
                                parentList.get(groupPosition).put("isGroupCheckd", "No");
                            }*//*
                        newadapter.notifyDataSetChanged();



                        return false;
                    }
                });





            *//*    View bottomView = View.inflate(getActivity(),R.layout.tree_list,null);//填充ListView布局
                ListView lvCarIds = (ListView) bottomView.findViewById(R.id.tree_lv);//初始化ListView控件

                mDatas.clear();
                initDatas();
                MyTreeListViewAdapter<MyNodeBean> adapter = null;

                try {


                    adapter = new MyTreeListViewAdapter<MyNodeBean>(lvCarIds, getActivity(),
                            mDatas, 0, isHide,ItemId,mDatas);

                    adapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                        @Override
                        public void onClick(Node node, int position) {
                            if (node.isLeaf()) {
                                Toast.makeText(getActivity(), node.getName(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCheckChange(Node node, int position,
                                                  List<Node> checkedNodes) {
                            // TODO Auto-generated method stub

                            StringBuffer sb = new StringBuffer();
                            StringBuffer sb1 = new StringBuffer();
                            StringBuffer sb2 = new StringBuffer();
                            for (Node n : checkedNodes) {
                                int pos = n.getId() - 1;
                                if(mDatas.get(pos).getPid()==0){

                                }else {
                                    sb.append(mDatas.get(pos).getName()).append("---")
                                            .append(mDatas.get(pos).getDesc()).append(";");
                                    sb1.append(mDatas.get(pos).getName()).append(";");
                                    sb2.append(mDatas.get(pos).getDesc()).append(",");
                                }


                            }
                            ItemId=sb2.toString();
                            tv_jiuzhuleibie.setText(sb1.toString());

                        }

                    });


                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                lvCarIds.setAdapter(adapter);
                adapter.updateView(false);*//*

                AlertDialog parkIdsdialog = new AlertDialog.Builder(getActivity())
                        .setTitle("选择救助类别").setView(bottomView)//在这里把写好的这个listview的布局加载dialog中
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                             *//*   int s =myItemName.size();
                                String se=myItemName.get(0).toString();*//*

                                StringBuffer sb = new StringBuffer();
                                StringBuffer sb1 = new StringBuffer();
                                StringBuffer sb2 = new StringBuffer();

                                for(int i=0;i<myItemName.size();i++){
                                    sb1.append(myItemName.get(i)).append(";");
                                    for(int j=0;j<myalljiuzhu.size();j++){
                                        if(myItemName.get(i).equals(myalljiuzhu.get(j).getName())){
                                            sb2.append(myalljiuzhu.get(j).getItemCode()).append(",");
                                        }
                                    }
                                }
                                //  ItemId=sb2.toString();

                              *//*  for (Node n : checkedNodes) {
                                    int pos = n.getId() - 1;
                                    if(mDatas.get(pos).getPid()==0){

                                    }else {
                                        sb.append(mDatas.get(pos).getName()).append("---")
                                                .append(mDatas.get(pos).getDesc()).append(";");
                                        sb1.append(mDatas.get(pos).getName()).append(";");
                                        sb2.append(mDatas.get(pos).getDesc()).append(",");
                                    }


                                }
                                ItemId=sb2.toString();*//*
                                myItemName.clear();
                                ItemIdNew=sb2.toString();
                                tv_jiuzhuleibie.setText(sb1.toString());

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();

                parkIdsdialog.show();
*/

            }
        });


    }

    private ListView treeLv;
    private Button checkSwitchBtn;
    List<MyNodeBean> mDatas = new ArrayList<MyNodeBean>();
    //标记是显示Checkbox还是隐藏
    private boolean isHide = true;

    private void initDatas() {
         List<SRCLoginSalvationBean>  myalljiuzhu = DataSupport.findAll(SRCLoginSalvationBean.class);

         List<SRCLoginSalvationBean>  mypjiuzhu = DataSupport.where(" pId=? and state=?","1003" ,"0").find(SRCLoginSalvationBean.class);

        List<SRCLoginSalvationBean>  jiuzhuminzheng = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhurenshe = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhuweijiwei = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhuzonggong = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhuzhujian = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhujiaoti = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhucanlian = new ArrayList<>();
        List<SRCLoginSalvationBean>  jiuzhuqita = new ArrayList<>();
        jiuzhuminzheng.clear();
        jiuzhurenshe.clear();
        jiuzhuweijiwei.clear();
        jiuzhuzonggong.clear();
        jiuzhuzhujian.clear();
        jiuzhujiaoti.clear();
        jiuzhucanlian.clear();
        jiuzhuqita.clear();
        for(int i=0;i<myalljiuzhu.size();i++){
            if(myalljiuzhu.get(i).getpId().equals("f307e913-d676-4de7-b4d9-3f195809ed8a")){
                jiuzhuminzheng.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("7069664e-eedb-47d1-b9e0-8c3116043386")){
                jiuzhurenshe.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("75110eee-946b-4137-bac7-46f44d2160bc")){
                jiuzhuweijiwei.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("f1bc2593-f0c4-46bd-8434-1479672edd63")){
                jiuzhuzonggong.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("c339e56d-efae-4567-9ae4-6b6f03f9e367")){
                jiuzhuzhujian.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("91905223-fd92-409d-bae5-ee021293f48a")){
                jiuzhujiaoti.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("73488336-c731-4392-aea5-c5be5f0b54e2")){
                jiuzhucanlian.add(myalljiuzhu.get(i));
            }else if(myalljiuzhu.get(i).getpId().equals("cab274d3-153e-417a-8ea8-c2f59f4d06dd")){
                jiuzhuqita.add(myalljiuzhu.get(i));
            }
        }


        mDatas.add(new MyNodeBean(1, 0, "民政"));
        mDatas.add(new MyNodeBean(2, 0, "人社"));
        mDatas.add(new MyNodeBean(3, 0, "卫计委"));
        mDatas.add(new MyNodeBean(4, 0, "总工会"));
        mDatas.add(new MyNodeBean(5, 0, "住建局"));
        mDatas.add(new MyNodeBean(6, 0, "教体局"));
        mDatas.add(new MyNodeBean(7, 0, "残联"));
        mDatas.add(new MyNodeBean(8, 0, "其他救助"));

        for(int i=0;i<jiuzhuminzheng.size();i++){
            mDatas.add(new MyNodeBean(i+8, 1, jiuzhuminzheng.get(i).getName()));
        }

        int s2=mDatas.size();
        for(int i=0;i<jiuzhurenshe.size();i++){
            mDatas.add(new MyNodeBean(i+s2-1, 2, jiuzhurenshe.get(i).getName()));
        }

        int s3=mDatas.size();
        for(int i=0;i<jiuzhuweijiwei.size();i++){
            mDatas.add(new MyNodeBean(i+s3-1, 3, jiuzhuweijiwei.get(i).getName()));
        }

        int s4=mDatas.size();
        for(int i=0;i<jiuzhuzonggong.size();i++){
            mDatas.add(new MyNodeBean(i+s4-1, 4, jiuzhuzonggong.get(i).getName()));
        }

        int s5=mDatas.size();
        for(int i=0;i<jiuzhuzhujian.size();i++){
            mDatas.add(new MyNodeBean(i+s5-1, 5, jiuzhuzhujian.get(i).getName()));
        }

        int s6=mDatas.size();
        for(int i=0;i<jiuzhujiaoti.size();i++){
            mDatas.add(new MyNodeBean(i+s6-1, 6, jiuzhujiaoti.get(i).getName()));
        }

        int s7=mDatas.size();
        for(int i=0;i<jiuzhucanlian.size();i++){
            mDatas.add(new MyNodeBean(i+s7-1, 7, jiuzhucanlian.get(i).getName()));
        }

        int s8=mDatas.size();
        for(int i=0;i<jiuzhuqita.size();i++){
            mDatas.add(new MyNodeBean(i+s8-1, 8, jiuzhuqita.get(i).getName()));
        }







    }



    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addUrbanBaseInfobianjistatusFragment(new UrbanBaseInfoFragmentModule(this))
                .inject(this);

    }
    private  String arearLevel="";
    @Override
    protected void initView() {
        type = getDeliveredString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY);
        mybatchNo = getDeliveredString("batchNo");
        status = getDeliveredString("status");

        if(TextUtils.isEmpty(mybatchNo) || mybatchNo.equals("null") || mybatchNo==null){
            final UserEventNew ue = new UserEventNew();
            ue.setUserName("test");
            EventBus.getDefault().post(ue);
        }else {
            final UserEventNew ue = new UserEventNew();
            ue.setUserName(mybatchNo);
            EventBus.getDefault().post(ue);
        }


       /* List<gdws_sys_area>  user2 = DataSupport.where(" areaLevel=? ","area_5" ).find(gdws_sys_area.class);
        List<gdws_sys_area>  user3 = DataSupport.where(" areaLevel=? ","area_3" ).find(gdws_sys_area.class);
        List<gdws_sys_area>  user4 = DataSupport.where(" areaLevel=? ","area_2" ).find(gdws_sys_area.class);

        arealistqu.clear();
        for(int i=0;i<user1.size();i++){
            arealistqu.add(user1.get(i));
        }
        arealistjiedao.clear();
        for(int i=0;i<user2.size();i++){
            arealistjiedao.add(user2.get(i));
        }

        arealistxian.clear();
        for(int i=0;i<user3.size();i++){
            arealistxian.add(user3.get(i));
        }

        arealistshi.clear();
        for(int i=0;i<user4.size();i++){
            arealistshi.add(user4.get(i));
        }
*/
        final String areaid= Identity.srcInfo.getAreaId();
        // String cesarea="340103013";


/*
        List<gdws_sys_area>  userall = DataSupport.where("areaCode=? ",areaid ).find(gdws_sys_area.class);

        if (userall.size()==0){

        }else {
            arearLevel= userall.get(0).getAreaLevel();
        }*/

        if(areaid.length()==9){
            arearLevel="area_4";
        }else if(areaid.length()==12){
            arearLevel="area_5";
        }else {
            //  arearLevel="area_4";
        }

        if(arearLevel.equals("area_4")){
            List<SRCLoginAreaBean>  user1 = DataSupport.where(" areaLevel=? and state=?","area_4" ,"0").find(SRCLoginAreaBean.class);
            for(int i=0;i<user1.size();i++){
                if(areaid.equals(user1.get(i).getAreaCode())){
                    et_xiangzhen.setText(user1.get(i).getAreaName());
                    xiang=user1.get(i).getAreaName();
                }
            }

            officeCode=areaid;
            et_hujiadress.setText(xiang);
            et_familyadress.setText(xiang);

            tv_jiedao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<SRCLoginAreaBean>  userjiedao = DataSupport.where(" areaLevel=? and areaPid=? and state=?","area_5", areaid,"0").find(SRCLoginAreaBean.class);
                    initArea(tv_jiedao, userjiedao, "社区",xiang);
                    opArea.show();
                }
            });

        }else if(arearLevel.equals("area_5")){
            List<SRCLoginAreaBean>  userall = DataSupport.where("areaCode=? and state=?",areaid ,"0").find(SRCLoginAreaBean.class);
            String areaPid =userall.get(0).getAreaPid();
            List<SRCLoginAreaBean>  userallnew = DataSupport.where("areaCode=? and state=?",areaPid ,"0").find(SRCLoginAreaBean.class);
            et_xiangzhen.setText(userallnew.get(0).getAreaName());
            tv_jiedao.setText(userall.get(0).getAreaName());
//            communityCode=userallnew.get(0).getAreaCode();
//            officeCode=areaid;
            officeCode=userallnew.get(0).getAreaCode();
            communityCode=areaid;

            et_hujiadress.setText(userallnew.get(0).getAreaName()+userall.get(0).getAreaName());
            et_familyadress.setText(userallnew.get(0).getAreaName()+userall.get(0).getAreaName());
        }


/*

        officeCode=areaid;


        for(int i=0;i<user1.size();i++){
            if(areaid.equals(user1.get(i).getAreaCode())){
                et_xiangzhen.setText(user1.get(i).getAreaName());
                xiang=user1.get(i).getAreaName();



            }
        }

        et_hujiadress.setText(xiang);
        et_familyadress.setText(xiang);

        tv_jiedao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<gdws_sys_area>  userjiedao = DataSupport.where(" areaLevel=? and areaPid=?","area_5", areaid).find(gdws_sys_area.class);
                initArea(tv_jiedao, userjiedao, "社区");
                opArea.show();
            }
        });
*/


        et_idcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String Idcard=et_idcard.getText().toString();
                if (et_idcard.length()==18){
                    //   Toast.makeText(AddFindActivity.this,IDCard.IDCardValidate(Idcard),Toast.LENGTH_SHORT).show();

                    String info= IDCard.IDCardValidate(Idcard);
                    if (info.equals("")){
                        tv_birth.setText( IDCard.getbirthdayNew(Idcard));
                        tv_sex.setText(IDCard.getSex(Idcard));
                    }else{
                        tv_birth.setText("");
                        tv_sex.setText("");
                        Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

        String scard =et_idcard.getText().toString();
        if (et_idcard.length()==18){
            //   Toast.makeText(AddFindActivity.this,IDCard.IDCardValidate(Idcard),Toast.LENGTH_SHORT).show();

            String info= IDCard.IDCardValidate(scard);
            if (info.equals("")){
                tv_birth.setText( IDCard.getbirthdayNew(scard));
                tv_sex.setText(IDCard.getSex(scard));
            }else{
                tv_birth.setText("");
                tv_sex.setText("");
                Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
            }
        }



        Map<String, String> map = ParametersFactory.getUrbanLowBaseinfobianji(getActivity(),
                mybatchNo);
        mPresenter.mainbianji(map);



    }

    @Override
    protected void loadData() {
//        onRefresh();


    }



    private String getListEmptyHint(){
        String s = getResources().getString(R.string.hint_no_service_assess_info_please_click_to_reload);
        return s;
//        return getResources().getString(R.string.hint_list_empty_please_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_failure);
    }


    public void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){

    }


    public void endRefreshing(){

    }

    @Override
    public void onRefresh() {
//        currentPage = Constants.DEFAULT_INITIAL_PAGE;
//        mAdapter.setEnableLoadMore(false);
//        LogUtil.i("SignServiceAssessFragment onRefresh:currentPage/pageSize:" + currentPage + "/" + pageSize);

//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, false);

        SignServiceAssessActivity activity = (SignServiceAssessActivity) getActivity();
        activity.onRefresh();
    }


    @Override
    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//        LogUtil.i("SignServiceAssessFragment onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);

        SignServiceAssessActivity activity = (SignServiceAssessActivity) getActivity();
        activity.onLoadMore();
    }


    public List<SignServiceAssessResultBean> getListFromResult(HttpResultBaseBean<List<SignServiceAssessResultBean>> beanList) {
        if (beanList != null) {
            List<SignServiceAssessResultBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public int getListSizeFromResult(HttpResultBaseBean<List<SignServiceAssessResultBean>> beanList) {
        if (beanList != null) {
            List<SignServiceAssessResultBean> list = beanList.getData();
            if (list != null) {
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }




    private void inithujileixing(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        ophujileixing = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                registerType=listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        ophujileixing.setPicker(listleixing);//一级选择器*/


    }

    private void inithujixingzhi(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        ophujixingzhi = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                registerProperty= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        ophujixingzhi.setPicker(listleixing);//一级选择器*/


    }

    private void initjiatingleibie(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opjiatingleibie = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
                familyType= listleixing.get(options1).getCode();
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/

            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        opjiatingleibie.setPicker(listleixing);//一级选择器*/


    }

    private void initzhipin(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opzhipin = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                poorReason= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        opzhipin.setPicker(listleixing);//一级选择器*/


    }

    private void initjinan(final TextView huji, final List<String> listleixing, String mingcheng) {//条件选择器初始化

        opjinan = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1);
                huji.setText(tx);

                String sjinan =huji.getText().toString();
                if(sjinan.equals("否")){
                    ll_jinan_reason.setVisibility(View.GONE);
                    view36.setVisibility(View.GONE);
                }else {
                    ll_jinan_reason.setVisibility(View.VISIBLE);
                    view36.setVisibility(View.VISIBLE);
                }
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        opjinan.setPicker(listleixing);//一级选择器*/


    }


    private void initkaihuhang(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opkaihuhang = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                bankName= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        opkaihuhang.setPicker(listleixing);//一级选择器*/


    }

    private void initArea(final TextView huji, final List<SRCLoginAreaBean> listleixing, String mingcheng, final String xiang) {//条件选择器初始化

        opArea = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getAreaName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                communityCode=listleixing.get(options1).getAreaCode();

                et_hujiadress.setText(xiang+tx);
                et_familyadress.setText(xiang+tx);
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        opArea.setPicker(listleixing);//一级选择器*/


    }

    @Override
    public void onUrbanBaseInfoSaveSuccess(HttpResultBaseBean<UrbanbaseInfoSaveBean> bean) {

        if(bean.getMessage().equals("调用正常")){
            Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_SHORT).show();

            Intent intent = getActivity().getIntent();
            getActivity().setResult(10,intent);


        }else {
            Toast.makeText(getActivity(),bean.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String batch =bean.getData().getBatchNo();
        if(TextUtils.isEmpty(batch) || batch.equals("null") || batch==null){

        }else {
            final UserEventNew ue = new UserEventNew();
            ue.setUserName(bean.getData().getBatchNo());
            EventBus.getDefault().post(ue);

           /* AddUrbanLowActivity parentActivity = (AddUrbanLowActivity) getActivity();
            parentActivity.vpContent.setCurrentItem(1);*/
        }
    }

    @Override
    public void onUrbanBaseInfoSaveFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUrbanBaseInfobianjiSuccess(HttpResultBaseBean<UrbanbaseInfobianjiBean> bean) {

        if(bean!=null) {
            UrbanbaseInfobianjiBean urbanbaseInfobianjiBean = bean.getData();

            String provinceCode = urbanbaseInfobianjiBean.getProvinceCode();
            String cityCode = urbanbaseInfobianjiBean.getCityCode();
            officeCode = urbanbaseInfobianjiBean.getOfficeCode();
            communityCode = urbanbaseInfobianjiBean.getCommunityCode();
            String countyCode = urbanbaseInfobianjiBean.getCountyCode();
            String name = urbanbaseInfobianjiBean.getName();
            String cardNo = urbanbaseInfobianjiBean.getCardNo();
             registerType = urbanbaseInfobianjiBean.getRegisterType();
             registerProperty = urbanbaseInfobianjiBean.getRegisterProperty();
             familyType = urbanbaseInfobianjiBean.getFamilyType();
            String accountHolder = urbanbaseInfobianjiBean.getAccountHolder();
             bankName = urbanbaseInfobianjiBean.getBankName();
            String bankCardno = urbanbaseInfobianjiBean.getBankCardno();
            String registerAddress = urbanbaseInfobianjiBean.getRegisterAddress();
            String familyAddress = urbanbaseInfobianjiBean.getFamilyAddress();
            String sriReason = urbanbaseInfobianjiBean.getSriReason();
            ItemIdNew = urbanbaseInfobianjiBean.getItemCode();
             poorReason = urbanbaseInfobianjiBean.getPoorReason();
            String isTrouble = urbanbaseInfobianjiBean.getIsTrouble();
            String troubleReason = urbanbaseInfobianjiBean.getTroubleReason();
            String investiSituation = urbanbaseInfobianjiBean.getInvestiSituation();
            String remarks = urbanbaseInfobianjiBean.getRemarks();
            String phone = urbanbaseInfobianjiBean.getPhone();




            if(TextUtils.isEmpty(officeCode) || officeCode.equals("null") || officeCode==null){

            }else {
                List<SRCLoginAreaBean>  user1 = DataSupport.where(" areaLevel=? and state=?","area_4" ,"0").find(SRCLoginAreaBean.class);
                for(int i=0;i<user1.size();i++){
                    if(user1.get(i).getAreaCode().equals(officeCode)){
                        et_xiangzhen.setText(user1.get(i).getAreaName());
                    }
                }
            }

            if(TextUtils.isEmpty(communityCode) || communityCode.equals("null") || communityCode==null){

            }else {
                List<SRCLoginAreaBean>  user1 = DataSupport.where(" areaLevel=? and state=?","area_5" ,"0").find(SRCLoginAreaBean.class);
                for(int i=0;i<user1.size();i++){
                    if(user1.get(i).getAreaCode().equals(communityCode)){
                        tv_jiedao.setText(user1.get(i).getAreaName());
                    }
                }
            }

            et_name.setText(name);

            et_idcard.setText(cardNo);

            if(TextUtils.isEmpty(registerType) || registerType.equals("null") || registerType==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myreg_type = DataSupport.where(" pcode=? and state=?","reg_type","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myreg_type.size();i++){
                    if(myreg_type.get(i).getCode().equals(registerType)){
                        tv_hujileixing.setText(myreg_type.get(i).getName());
                    }
                }
            }

            if(TextUtils.isEmpty(registerProperty) || registerProperty.equals("null") || registerProperty==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","regpro","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myregpro.size();i++){
                    if(myregpro.get(i).getCode().equals(registerProperty)){
                        tv_hujixingzhi.setText(myregpro.get(i).getName());
                    }
                }
            }

            if(TextUtils.isEmpty(familyType) || familyType.equals("null") || familyType==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","family_type","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myregpro.size();i++){
                    if(myregpro.get(i).getCode().equals(familyType)){
                        tv_jiatingleibie.setText(myregpro.get(i).getName());
                    }
                }
            }


            et_kaihuren.setText(accountHolder);

            if(TextUtils.isEmpty(bankName) || bankName.equals("null") || bankName==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","bank","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myregpro.size();i++){
                    if(myregpro.get(i).getCode().equals(bankName)){
                        tv_kaihuhang.setText(myregpro.get(i).getName());
                    }
                }
            }

            et_bankaccount.setText(bankCardno);
            et_phone.setText(phone);

            et_hujiadress.setText(registerAddress);
            et_familyadress.setText(familyAddress);
            et_applyreason.setText(sriReason);


            if(TextUtils.isEmpty(isTrouble) || isTrouble.equals("null") || isTrouble==null){

            }else {
                if(isTrouble.equals("0")){
                    tv_jinan.setText("否");
                    ll_jinan_reason.setVisibility(View.GONE);
                    view36.setVisibility(View.GONE);
                }else if(isTrouble.equals("1")){
                    tv_jinan.setText("是");
                    ll_jinan_reason.setVisibility(View.VISIBLE);
                    tv_jinan_reason.setText(troubleReason);
                    view36.setVisibility(View.VISIBLE);
                }
            }

            et_diaochareason.setText(investiSituation);
            et_remark.setText(remarks);

            if(TextUtils.isEmpty(poorReason) || poorReason.equals("null") || poorReason==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","poor_reason" ,"0").find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myregpro.size();i++){
                    if(myregpro.get(i).getCode().equals(poorReason)){
                        tv_zhipin.setText(myregpro.get(i).getName());
                    }
                }
            }



            if(TextUtils.isEmpty(ItemIdNew) || ItemIdNew.equals("null") || poorReason==null){

            }else {
                final List<SRCLoginSalvationBean>  myregprojiuzhu = DataSupport.where("state=?","0" ).find(SRCLoginSalvationBean.class);
                for(int i=0;i<myregprojiuzhu.size();i++){
                    if(TextUtils.isEmpty(myregprojiuzhu.get(i).getItemCode()) || myregprojiuzhu.get(i).getItemCode().equals("null") || myregprojiuzhu.get(i).getItemCode()==null){
                        tv_jiuzhuleibie.setText("");
                    }else {
                        if(myregprojiuzhu.get(i).getItemCode().equals(ItemIdNew)){
                            tv_jiuzhuleibie.setText(myregprojiuzhu.get(i).getName());
                        }
                    }

                }
            }






        }


    }

    @Override
    public void onUrbanBaseInfobianjiFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }



    /**
     * select picture
     */
    private final static int REQUEST_IMAGE = 100;
    private void selectImage(){
        MultiImageSelector.create(getActivity())
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(getActivity(), REQUEST_IMAGE);
    }

    private String p = null;
    private Bitmap bitmap = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                Util.showGifProgressDialog(getActivity());
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                if(path != null && path.size() > 0){
                    p = path.get(0);
//                    onSelected();
                    bitmap = getImage(p);
                    //   imageView.setImageBitmap(bitmap);

                    TecentHttpUtil.uploadIdCard(BitMapUtils.bitmapToBase64(bitmap), "0", new TecentHttpUtil.SimpleCallBack() {
                        @Override
                        public void Succ(String res) {
                            IdentifyResult result = new Gson().fromJson(res, IdentifyResult.class);
                            if(result != null){
                                if(result.getErrorcode() == 0){
                                    // 识别成功
                                    Util.hideGifProgressDialog(getActivity());
                                    showDialogInfo(result);

                                }else {
                                    Util.hideGifProgressDialog(getActivity());
                                    Toast.makeText(getActivity(),"识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
                                /*switch (result.getErrorcode()){
                                    case -7001:
                                        Toast.makeText(MainActivity.this, "未检测到身份证，请对准边框(请避免拍摄时倾角和旋转角过大、摄像头)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7002:
                                        Toast.makeText(MainActivity.this, "请使用第二代身份证件进行扫描", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7003:
                                        Toast.makeText(MainActivity.this, "不是身份证正面照片(请使用带证件照的一面进行扫描)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7004:
                                        Toast.makeText(MainActivity.this, "不是身份证反面照片(请使用身份证反面进行扫描)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7005:
                                        Toast.makeText(MainActivity.this, "确保扫描证件图像清晰", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7006:
                                        Toast.makeText(MainActivity.this, "请避开灯光直射在证件表面", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "识别失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                        break;
                                }*/
                                }
                            }
                        }

                        @Override
                        public void error() {
                            Toast.makeText(getActivity(),"识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        }
    }


    /**
     * 获取压缩后的图片
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            if(options<0){
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                break;
            }else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            }

            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }




    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }


    /**
     * 显示对话框
     * @param result
     */
    private void showDialogInfo(final IdentifyResult result){
        StringBuilder sb = new StringBuilder();
        sb.append("姓名：" + result.getName() + "\n");
        sb.append("性别：" + result.getSex() + "\t" + "民族：" + result.getNation() + "\n");
        sb.append("出生：" + result.getBirth() + "\n");
        sb.append("住址：" + result.getAddress() + "\n" + "\n");
        sb.append("公民身份号码：" + result.getId() + "\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog   dialogInfo = builder.setTitle("识别成功")
                .setMessage(sb.toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .create();
        dialogInfo.show();

     /*   et_idcard.setText(result.getId());
        et_idcard.setSelection(result.getId().length());*/

    }


    /**
     * 判断电话号码是否符合格式.
     *
     * @param inputText the input text
     * @return true, if is phone
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    /**
     * 是否是座机电话号码
     *
     * @param str
     * @return
     */
    public static boolean isFixedLine(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^([0-9]{3}-?[0-9]{8})|([0-9]{4}-?[0-9]{7})$");
        Matcher matcher = pattern.matcher(str);
        boolean b = matcher.matches();
        return b;
    }


}
