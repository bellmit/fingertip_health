package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageAdapter;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilybianjiBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.contract.UrbanAddFamilyContract;
import com.jqsoft.fingertip_health.di.module.UrbanLowAddFamilyActivityModule;
import com.jqsoft.fingertip_health.di.presenter.UrbanAddFamilyPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Base64Util;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.IDCard;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Administrator on 2017-07-07.
 */
//
public class UrbanFamilybianjiActivity extends AbstractActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,UrbanAddFamilyContract.View{


    @Inject
    UrbanAddFamilyPresenter mPresenter;

    private String type;

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;


    @BindView(R.id.tv_guanxi)
    TextView tv_guanxi;

    @BindView(R.id.tv_nation)
    TextView tv_nation;

    @BindView(R.id.tv_jiuyeqingkuang)
    TextView tv_jiuyeqingkuang;

    @BindView(R.id.tv_canbaoleixing)
    TextView tv_canbaoleixing;

    @BindView(R.id.tv_laodong)
    TextView tv_laodong;

    @BindView(R.id.tv_jiangkangzhuangkuang)
    TextView tv_jiangkangzhuangkuang;

    @BindView(R.id.tv_hunyin)
    TextView tv_hunyin;


    @BindView(R.id.tv_zhongbing)
    TextView tv_zhongbing;

    @BindView(R.id.tv_canji)
    TextView tv_canji;

    @BindView(R.id.tv_mianmao)
    TextView tv_mianmao;

    @BindView(R.id.iv_idcard)
    ImageView iv_idcard;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_idcard)
    EditText et_idcard;

    @BindView(R.id.btn_save)
    RoundTextView btn_save;

    @BindView(R.id.tv_sex)
    TextView tv_sex;

    @BindView(R.id.tv_birth)
    TextView tv_birth;

    @BindView(R.id.et_shouru)
    EditText et_shouru;

    @BindView(R.id.et_shebaokahao)
    EditText et_shebaokahao;

    @BindView(R.id.iv_touxiang)
    ImageView iv_touxiang;

    @BindView(R.id.btn_delete)
    RoundTextView btn_delete;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    private String sCodeshi="",sCodexian="",sCodequ="",sCodejiedao="";
    ArrayList<SRCLoginAreaBean> arealistqu = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistjiedao = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistxian = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistshi = new ArrayList<>();
    private ArrayList<ArrayList<SRCLoginAreaBean>> jiedaoListOut;

    private OptionsPickerView opguanxi,opnation,opjiatingleibie,opzhipin,opjinan,opcanji,opkaihuhang,oplaodong,opjiankang,ophunyin,opArea;

    private String officeCode="",communityCode="",relation="",nation="",employmentStatus="",politicalStatus="",
            workStatus="",healthStatus="",itemCode="",canhecanbao="",maritalStatus="";

    private String myid,mybatchNo,myrelation;
    private String myPic;
    private List<String> mCardList = new ArrayList<>();

    public UrbanFamilybianjiActivity() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_urban_family_layout;
    }

    @Override
    protected void initData() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = et_name.getText().toString();
                String regEx = "[^\u4E00-\u9FA5]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("").trim();    //????????????????????????????????????
                if(!editable.equals(str)){
                    et_name.setText(str);  //??????EditText?????????
                    et_name.setSelection(str.length()); //???????????????????????????????????????????????????????????????
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex=tv_sex.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"?????????????????????????????????,?????????????????????!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex=tv_birth.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"?????????????????????????????????,???????????????????????????!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        setPricePoint(et_shouru);

        et_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_idcard.setCursorVisible(true);// ????????????????????????
            }
        });

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

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
                        Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
            }
        }

        iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   adapter = new GridImageAdapter(UrbanFamilyActivity.this, onAddPicClickListener);
              //  adapter.setList(selectMedia);
              //  adapter.setSelectMax(1);

                onAddPicClick(0,0);
            }
        });

        tv_guanxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myreg_type = DataSupport.where(" pcode=? and state=?","relation","0" ).find(SRCLoginDataDictionaryBean.class);
                if(myrelation.equals("??????")){
                    tv_guanxi.setText("??????");

                    for(int i=0;i<myreg_type.size();i++){
                        if(myreg_type.get(i).getName().equals("??????")){
                            relation=myreg_type.get(i).getCode();
                        }
                    }

                }else {
                    for(int i=0;i<myreg_type.size();i++){
                        if(myreg_type.get(i).getName().equals("??????")){
                            myreg_type.remove(i);
                        }
                    }

                    initguanxi(tv_guanxi, myreg_type, "???????????????");
                    opguanxi.show();
                }


            }
        });

        tv_nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","nation" ,"0").find(SRCLoginDataDictionaryBean.class);
                initminzu(tv_nation, myregpro, "??????");
                opnation.show();
            }
        });

        tv_jiuyeqingkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","employment_status","0" ).find(SRCLoginDataDictionaryBean.class);
                initjiatingleibie(tv_jiuyeqingkuang, myregpro, "????????????");
                opjiatingleibie.show();
            }
        });

        tv_canbaoleixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","canhe_canbao","0" ).find(SRCLoginDataDictionaryBean.class);
                initzhipin(tv_canbaoleixing, myregpro, "????????????");
                opzhipin.show();
            }
        });
        tv_zhongbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> myList = new ArrayList<String>();
                myList.add("???");
                myList.add("???");
                initjinan(tv_zhongbing, myList, "????????????");
                opjinan.show();
            }
        });

        tv_canji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> myList = new ArrayList<String>();
                myList.add("???");
                myList.add("???");
                initcanji(tv_canji, myList, "????????????");
                opcanji.show();
            }
        });

        tv_mianmao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","political_status","0" ).find(SRCLoginDataDictionaryBean.class);
                initkaihuhang(tv_mianmao, myregpro, "????????????");
                opkaihuhang.show();
            }
        });

        tv_laodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","work_status","0" ).find(SRCLoginDataDictionaryBean.class);
                initlaodongnengli(tv_laodong, myregpro, "????????????");
                oplaodong.show();
            }
        });

        tv_jiangkangzhuangkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","health_status","0" ).find(SRCLoginDataDictionaryBean.class);
                initjiankang(tv_jiangkangzhuangkuang, myregpro, "????????????");
                opjiankang.show();
            }
        });

        tv_hunyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? and state=?","marital_status" ,"0").find(SRCLoginDataDictionaryBean.class);
                inithunyin(tv_hunyin, myregpro, "????????????");
                ophunyin.show();
            }
        });


        iv_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UrbanFamilybianjiActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // ?????????????????????????????????????????????
                    // ??????WRITE_EXTERNAL_STORAGE??????
                    ActivityCompat.requestPermissions(UrbanFamilybianjiActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
                tv_guanxi.setText("");
                tv_nation.setText("");
                tv_jiuyeqingkuang.setText("");
                tv_canbaoleixing.setText("");
                tv_mianmao.setText("");
                tv_zhongbing.setText("");
                tv_canji.setText("");
                et_shouru.setText("");
                tv_laodong.setText("");
                et_shebaokahao.setText("");
                tv_jiangkangzhuangkuang.setText("");
                tv_hunyin.setText("");
                relation="";
                nation="";
                employmentStatus="";
                politicalStatus="";
                workStatus="";
                healthStatus="";
                canhecanbao="";
                maritalStatus="";

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sname =et_name.getText().toString();
                String sidcard =et_idcard.getText().toString();
                String ssex =tv_sex.getText().toString();
                String sbirth =tv_birth.getText().toString();



              /*  String sexName = IDCard.getSex(sidcard);
                String sbirthName=IDCard.getbirthdayNew(sidcard);
                tv_sex.setText(sexName);
                tv_birth.setText(sbirthName);*/

                String sguanxi =tv_guanxi.getText().toString();
                String snation =tv_nation.getText().toString();
                String sjiuyeqingkuang =tv_jiuyeqingkuang.getText().toString();
                String scanbaoleixing =tv_canbaoleixing.getText().toString();
                String slaodongnengli =tv_laodong.getText().toString();
                String shunyinzhuangkuang =tv_hunyin.getText().toString();

                String Idcard=et_idcard.getText().toString();
                String info= IDCard.IDCardValidate(Idcard);


                if(TextUtils.isEmpty(sname)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sidcard)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"???????????????????????????",Toast.LENGTH_SHORT).show();
                }else if( !TextUtils.isEmpty(info)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,info.toString(),Toast.LENGTH_SHORT).show();
                }else if(isCardchongfu(sidcard)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"?????????????????????????????????????????????",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(ssex)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sbirth)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sguanxi)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"???????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(snation)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sjiuyeqingkuang)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(scanbaoleixing)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(slaodongnengli)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(shunyinzhuangkuang)){
                    Toast.makeText(UrbanFamilybianjiActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
                }else {

                    String sexcode="";
                    if(ssex.equals("???")){
                        sexcode="sex_1";
                    }else if(ssex.equals("???")){
                        sexcode="sex_2";
                    }

                    String szhongbing=tv_zhongbing.getText().toString();

                    if(szhongbing.equals("???")){
                        szhongbing="0";
                    }else if(szhongbing.equals("???")){
                        szhongbing="1";
                    }else {
                        szhongbing="";
                    }

                    String scanji=tv_canji.getText().toString();

                    if(scanji.equals("???")){
                        scanji="0";
                    }else if(scanji.equals("???")){
                        scanji="1";
                    }else {
                        scanji="";
                    }


                    String   sPic="";
                    if(selectMedia.size()==0){
                        sPic="";
                    }else {
                        String base64=selectMedia.get(0).getCompressPath();
                        if( base64==null || TextUtils.isEmpty(base64) || base64.equals("null")){
                            base64=selectMedia.get(0).getPath();
                        }else {

                        }
                        sPic=   Base64Util.imageToBase64(base64).trim();
                    }


                    if(myPic ==null || TextUtils.isEmpty(myPic) || myPic.equals("null")){
                        myPic="";
                    }else {

                    }

                    String incomeStatus=et_shouru.getText().toString();
                    String socialSecurityNo=et_shebaokahao.getText().toString();

                    String editor= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.getUrbanaddfamilySave(getApplicationContext(),sname,sidcard,sexcode,sbirth,relation,politicalStatus,nation
                    ,szhongbing,employmentStatus,scanji,canhecanbao,incomeStatus,workStatus,socialSecurityNo, healthStatus, maritalStatus
                            ,mybatchNo,sPic,myid,myPic);
                    mPresenter.main(map);
                }






            }
        });
    }

    private boolean isCardchongfu(String scard){

        for(int i=0;i<mCardList.size();i++){
            if(scard.equals(mCardList.get(i))){
                return true;
            }
        }

        return false;
    }


    /**
     * ????????????????????????
     */
    private boolean isShow = true;
    private int selectType = FunctionConfig.TYPE_IMAGE;
    private boolean enablePreview = true;
    private boolean isPreviewVideo = true;
    private boolean theme = false;
    private boolean selectImageType = false;
    private int maxB = 0;
    private int compressW = 0;
    private int compressH = 0;
    private boolean isCompress = false;
    private boolean isCheckNumMode = false;
    private int compressFlag = 1;// 1 ?????????????????? 2 luban??????
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private int themeStyle;
    private int previewColor, completeColor, previewBottomBgColor, previewTopBgColor, bottomBgColor, checkedBoxDrawable;
    private boolean mode = false;// ??????????????????
    private int selectMode = FunctionConfig.MODE_MULTIPLE;
    private boolean clickVideo;
    private GridImageAdapter adapter;
    public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // ????????????
                    /**
                     * type --> 1?????? or 2??????
                     * copyMode -->????????????????????????1:1???3:4???3:2???16:9
                     * maxSelectNum --> ????????????????????????
                     * selectMode         --> ?????? or ??????
                     * isShow       --> ???????????????????????? ??????????????????type ????????????????????????
                     * isPreview    --> ????????????????????????
                     * isCrop       --> ????????????????????????
                     * isPreviewVideo -->??????????????????(??????) mode or ????????????
                     * ThemeStyle -->????????????
                     * CheckedBoxDrawable -->??????????????????
                     * cropW-->???????????? ???????????????100  ????????????????????????????????? ?????????????????????
                     * cropH-->???????????? ???????????????100
                     * isCompress -->??????????????????
                     * setEnablePixelCompress ????????????????????????
                     * setEnableQualityCompress ????????????????????????
                     * setRecordVideoSecond ????????????????????????????????????
                     * setRecordVideoDefinition ???????????????  Constants.HIGH ??????  Constants.ORDINARY ?????????
                     * setImageSpanCount -->??????????????????
                     * setCheckNumMode ????????????QQ????????????(???????????????)
                     * setPreviewColor ??????????????????
                     * setCompleteColor ??????????????????
                     * setPreviewBottomBgColor ???????????????????????????
                     * setBottomBgColor ?????????????????????????????????
                     * setCompressQuality ???????????????????????????????????????
                     * setSelectMedia ??????????????????
                     * setCompressFlag 1?????????????????????  2????????????luban??????
                     * ??????-->type???2??? ??????isPreview or isCrop ??????
                     * ?????????Options?????????????????????????????????
                     */
                    String b = "50";// ?????????????????? ?????????b

                    if (!isNull(b)) {
                        maxB = Integer.parseInt(b);
                    }

                    if (!isNull("200") && !isNull("200")) {
                        compressW = Integer.parseInt("200");
                        compressH = Integer.parseInt("200");
                    }

                    if (theme) {
                        // ??????????????????
                        themeStyle = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ ??????????????? ????????????????????????
                        previewColor = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(UrbanFamilybianjiActivity.this, R.color.tab_color_true);
                    }

                    FunctionOptions options = new FunctionOptions.Builder()
                            .setType(selectType) // ??????or?????? FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //????????????
                            .setEnablePixelCompress(true) //????????????????????????
                            .setEnableQualityCompress(true) //?????????????????????
                            .setMaxSelectNum(1) // ????????????????????????
                            .setMinSelectNum(0)// ?????????????????????????????????????????????????????????
                            .setSelectMode(2) // ?????? or ??????
                            .setShowCamera(isShow) //???????????????????????? ??????????????????type ????????????????????????
                            .setEnablePreview(enablePreview) // ????????????????????????
                            .setPreviewVideo(isPreviewVideo) // ??????????????????(??????) mode or ????????????
                            .setCheckedBoxDrawable(checkedBoxDrawable)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // ???????????????
                            .setRecordVideoSecond(60) // ????????????
                            .setVideoS(0)// ??????????????????????????? ??????:???
                            .setCustomQQ_theme(0)// ????????????QQ?????????????????????????????????????????????
                            .setGif(false)// ????????????gif????????????????????????
                            .setMaxB(50) // ??????????????? ??????:200kb  ?????????202400???202400 / 1024 = 200kb
                            .setPreviewColor(previewColor) //??????????????????
                            .setCompleteColor(completeColor) //?????????????????????
                            .setPreviewBottomBgColor(previewBottomBgColor) //???????????????????????????
                            .setPreviewTopBgColor(previewTopBgColor)//???????????????????????????
                            .setBottomBgColor(bottomBgColor) //???????????????????????????
                            .setGrade(Luban.THIRD_GEAR) // ???????????? ????????????
                            .setCheckNumMode(isCheckNumMode)
                            .setCompressQuality(80) // ??????????????????,????????????
                            .setImageSpanCount(4) // ????????????
                            .setSelectMedia(selectMedia) // ?????????????????????????????????????????????????????????????????????
                            .setCompressFlag(2) // 1 ?????????????????? 2 luban??????
                            .setCompressW(0) // ????????? ???????????????????????????????????????
                            .setCompressH(0) // ????????? ???????????????????????????????????????
                            .setThemeStyle(themeStyle) // ??????????????????
                            .setNumComplete(false) // 0/9 ??????  ??????
                            .setClickVideo(clickVideo)// ??????????????????
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // ????????????????????????
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // ??????????????????????????????
//                            .setLeftBackDrawable(R.mipmap.back2) // ?????????????????????
//                            .setStatusBar(ContextCompat.getColor(MainActivity.this, R.color.white)) // ???????????????????????????????????????????????????
//                            .setImmersive(true)// ?????????????????????????????????(??????)
                            .create();

                    if (mode) {
                        // ?????????
                        PictureConfig.getInstance().init(options).startOpenCamera(UrbanFamilybianjiActivity.this);
                    } else {
                        // ??????????????????????????????????????????
                        PictureConfig.getInstance().init(options).openPhoto(UrbanFamilybianjiActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // ????????????
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }

    /**
     * ?????? ???????????????????????????
     *
     * @param s
     * @return
     * @author Michael.Zhang 2013-9-7 ??????4:39:00
     */

    public boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }

    /**
     * ??????????????????
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia.clear();
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            LocalMedia media = resultList.get(0);
            if (media.isCompressed()) {
                // ?????????,???????????????????????????,??????????????????????????????
                String path = media.getCompressPath();
            } else {
                // ????????????
                String path = media.getPath();
            }
            if (selectMedia != null) {
                adapter.setList(selectMedia);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onSelectSuccess(LocalMedia media) {
            selectMedia.add(media);
          //  iv_touxiang.setImageBitmap();

            Glide.with(UrbanFamilybianjiActivity.this)
                    .load(selectMedia.get(0).getPath())
                    .centerCrop()
                    .placeholder(R.color.color_f6)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_touxiang);
         //   selectMedia.clear();
           /* adapter.setList(selectMedia);
            adapter.notifyDataSetChanged();*/
        }
    };

    /**
     * ????????????????????????
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == FunctionConfig.CAMERA_RESULT) {
                if (data != null) {
                    selectMedia = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                    if (selectMedia != null) {
                        adapter.setList(selectMedia);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }

        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // ???????????????????????????
                Util.showGifProgressDialog(UrbanFamilybianjiActivity.this);
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // ???????????????????????? ....
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
                                    // ????????????
                                    Util.hideGifProgressDialog(UrbanFamilybianjiActivity.this);
                                    showDialogInfo(result);

                                }else {
                                    Util.hideGifProgressDialog(UrbanFamilybianjiActivity.this);
                                    Toast.makeText(UrbanFamilybianjiActivity.this,"?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                /*switch (result.getErrorcode()){
                                    case -7001:
                                        Toast.makeText(MainActivity.this, "???????????????????????????????????????(??????????????????????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7002:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7003:
                                        Toast.makeText(MainActivity.this, "???????????????????????????(??????????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7004:
                                        Toast.makeText(MainActivity.this, "???????????????????????????(????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7005:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7006:
                                        Toast.makeText(MainActivity.this, "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                }*/
                                }
                            }
                        }

                        @Override
                        public void error() {
                            Toast.makeText(UrbanFamilybianjiActivity.this,"?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        }
    }




    @Override
    protected void initInject() {
        DaggerApplication.get(getApplicationContext())
                .getAppComponent()
                .addUrbanLowaddFamilybianjiActivity(new UrbanLowAddFamilyActivityModule(this))
                .inject(this);

    }

    @Override
    protected void initView() {
      //  type = getDeliveredString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY);

        myid=getDeliveredStringByKey("id");
        mybatchNo=getDeliveredStringByKey("batchNo");
        myrelation=getDeliveredStringByKey("relation");
        mCardList = getIntent().getStringArrayListExtra("mCardList");

        Map<String, String> map = ParametersFactory.getfamilybianji(UrbanFamilybianjiActivity.this,myid
                );
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

      /*  SignServiceAssessActivity activity = (SignServiceAssessActivity) getApplicationContext();
        activity.onRefresh();*/
    }


    @Override
    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
//        LogUtil.i("SignServiceAssessFragment onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);

        SignServiceAssessActivity activity = (SignServiceAssessActivity) getApplicationContext();
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




    private void initguanxi(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opguanxi = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                relation=listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opguanxi.setPicker(listleixing);//???????????????*/


    }

    private void initminzu(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opnation = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                nation= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))
                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))
                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opnation.setPicker(listleixing);//???????????????*/


    }

    private void initjiatingleibie(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opjiatingleibie = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
                employmentStatus= listleixing.get(options1).getCode();
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/

            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opjiatingleibie.setPicker(listleixing);//???????????????*/


    }

    private void initzhipin(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opzhipin = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                canhecanbao= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opzhipin.setPicker(listleixing);//???????????????*/


    }

    private void initjinan(final TextView huji, final List<String> listleixing, String mingcheng) {//????????????????????????

        opjinan = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1);
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opjinan.setPicker(listleixing);//???????????????*/


    }

    private void initcanji(final TextView huji, final List<String> listleixing, String mingcheng) {//????????????????????????

        opcanji = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1);
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opcanji.setPicker(listleixing);//???????????????*/


    }


    private void initkaihuhang(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opkaihuhang = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                politicalStatus= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opkaihuhang.setPicker(listleixing);//???????????????*/


    }

    private void initlaodongnengli(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        oplaodong = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                workStatus= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        oplaodong.setPicker(listleixing);//???????????????*/


    }

    private void initjiankang(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        opjiankang = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                healthStatus= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opjiankang.setPicker(listleixing);//???????????????*/


    }

    private void inithunyin(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//????????????????????????

        ophunyin = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                maritalStatus= listleixing.get(options1).getCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        ophunyin.setPicker(listleixing);//???????????????*/


    }



    private void initArea(final TextView huji, final List<SRCLoginAreaBean> listleixing, String mingcheng) {//????????????????????????

        opArea = new OptionsPickerView.Builder(UrbanFamilybianjiActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = listleixing.get(options1).getAreaName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                communityCode=listleixing.get(options1).getAreaCode();
            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255, 177, 177))//????????????????????????
                .setSelectOptions(0, 1)//???????????????
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("", "", "")
                .build();
        opArea.setPicker(listleixing);//???????????????*/


    }

    @Override
    public void onUrbanBaseInfoSaveSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
        setResult(RESULT_SUCCESS);
        finish();

    }

    @Override
    public void onUrbanBaseInfoSaveFailure(String message) {

    }

    @Override
    public void onUrbanBaseInfobianjiSuccess(HttpResultBaseBean<UrbanLowFamilybianjiBean> bean) {
       /* sname,sidcard,sexcode,sbirth,relation,politicalStatus,nation
                ,szhongbing,employmentStatus,scanji,canhecanbao,incomeStatus,workStatus,socialSecurityNo, healthStatus, maritalStatus
*/
        if(bean!=null){
            UrbanLowFamilybianjiBean urbanLowFamilybianjiBean = bean.getData();
            String  sname =urbanLowFamilybianjiBean.getName();
            String  sidcard =urbanLowFamilybianjiBean.getCardNo();
            String  sexcode =urbanLowFamilybianjiBean.getSex();
            String  sbirth =urbanLowFamilybianjiBean.getBirthDate();
            relation =urbanLowFamilybianjiBean.getRelation();
            politicalStatus =urbanLowFamilybianjiBean.getPoliticalStatus();
            nation =urbanLowFamilybianjiBean.getNation();
            String  szhongbing =urbanLowFamilybianjiBean.getIsDisease();
            employmentStatus =urbanLowFamilybianjiBean.getEmploymentStatus();
            String  scanji =urbanLowFamilybianjiBean.getIsDeformity();
            canhecanbao=urbanLowFamilybianjiBean.getCanhecanbao();
            String  incomeStatus =urbanLowFamilybianjiBean.getIncomeStatus();
            workStatus =urbanLowFamilybianjiBean.getWorkStatus();
            String  socialSecurityNo =urbanLowFamilybianjiBean.getSocialSecurityNo();
            healthStatus =urbanLowFamilybianjiBean.getHealthStatus();
            maritalStatus =urbanLowFamilybianjiBean.getMaritalStatus();
            et_name.setText(sname);
            et_idcard.setText(sidcard);

            mCardList.remove(sidcard);

            if(TextUtils.isEmpty(relation) || relation.equals("null") || relation==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","relation","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(relation)){
                        tv_guanxi.setText(myguanxi.get(i).getName());
                    }
                }
            }

            if(TextUtils.isEmpty(politicalStatus) || politicalStatus.equals("null") || politicalStatus==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","political_status","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(politicalStatus)){
                        tv_mianmao.setText(myguanxi.get(i).getName());
                    }
                }
            }


            if(TextUtils.isEmpty(nation) || nation.equals("null") || nation==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","nation","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(nation)){
                        tv_nation.setText(myguanxi.get(i).getName());
                    }
                }
            }


            if(TextUtils.isEmpty(szhongbing) || szhongbing.equals("null") || szhongbing==null){

            }else {
                if(szhongbing.equals("0")){
                    tv_zhongbing.setText("???");
                }else if(szhongbing.equals("1")){
                    tv_zhongbing.setText("???");
                }
            }

            if(TextUtils.isEmpty(employmentStatus) || employmentStatus.equals("null") || employmentStatus==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","employment_status","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(employmentStatus)){
                        tv_jiuyeqingkuang.setText(myguanxi.get(i).getName());
                    }
                }
            }

            if(TextUtils.isEmpty(scanji) || scanji.equals("null") || scanji==null){

            }else {
                if(scanji.equals("0")){
                    tv_canji.setText("???");
                }else if(scanji.equals("1")){
                    tv_canji.setText("???");
                }
            }

            if(TextUtils.isEmpty(canhecanbao) || canhecanbao.equals("null") || canhecanbao==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","canhe_canbao","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(canhecanbao)){
                        tv_canbaoleixing.setText(myguanxi.get(i).getName());
                    }
                }
            }

            et_shouru.setText(incomeStatus);

            if(TextUtils.isEmpty(workStatus) || workStatus.equals("null") || workStatus==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","work_status","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(workStatus)){
                        tv_laodong.setText(myguanxi.get(i).getName());
                    }
                }
            }

            et_shebaokahao.setText(socialSecurityNo);

            if(TextUtils.isEmpty(healthStatus) || healthStatus.equals("null") || healthStatus==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","health_status","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(healthStatus)){
                        tv_jiangkangzhuangkuang.setText(myguanxi.get(i).getName());
                    }
                }
            }

            if(TextUtils.isEmpty(maritalStatus) || maritalStatus.equals("null") || maritalStatus==null){

            }else {
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","marital_status","0" ).find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(maritalStatus)){
                        tv_hunyin.setText(myguanxi.get(i).getName());
                    }
                }
            }

            myPic =urbanLowFamilybianjiBean.getPic();

            if(TextUtils.isEmpty(myPic) || myPic.equals("null") || myPic==null){
             //   String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";
                String FIND_FILE_URL_BASE = Version.FILE_URL_BASE;
                String imageUrl =FIND_FILE_URL_BASE+myPic;
                GlideUtils.loadImageNewFamily(imageUrl, (ImageView) iv_touxiang);
            }else {
             //   String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";
                String FIND_FILE_URL_BASE =Version.FILE_URL_BASE;
                String imageUrl =FIND_FILE_URL_BASE+myPic;
                GlideUtils.loadImageNew(imageUrl, (ImageView) iv_touxiang);
            }



        }



    }

    @Override
    public void onUrbanBaseInfobianjiFailure(String message) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_FAILED);

            finish();
        }
        return super.onKeyDown(keyCode, event);

    }





    /**
     * select picture
     */
    private final static int REQUEST_IMAGE = 100;
    private void selectImage(){
        MultiImageSelector.create(UrbanFamilybianjiActivity.this)
                .showCamera(true) // ??????????????????. ???????????????
//                .count(1) // ????????????????????????, ?????????9. ???????????????????????????????????????
                .single() // ????????????
//                .multi() // ????????????, ????????????;
//                .origin(ArrayList<String>) // ?????????????????????. ???????????????????????????????????????
                .start(UrbanFamilybianjiActivity.this, REQUEST_IMAGE);
    }

    private String p = null;
    private Bitmap bitmap = null;



    /**
     * ????????????????????????
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ??????????????????????????????options.inJustDecodeBounds ??????true???
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);// ????????????bm??????

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ??????????????????????????????800*480??????????????????????????????????????????
        float hh = 800f;// ?????????????????????800f
        float ww = 480f;// ?????????????????????480f
        // ????????????????????????????????????????????????????????????????????????????????????????????????
        int be = 1;// be=1???????????????
        if (w > h && w > ww) {// ???????????????????????????????????????????????????
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// ???????????????????????????????????????????????????
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// ??????????????????
        // ??????????????????????????????????????????options.inJustDecodeBounds ??????false???
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// ?????????????????????????????????????????????
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ???????????????????????????100????????????????????????????????????????????????baos???
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  // ?????????????????????????????????????????????100kb,??????????????????
            baos.reset();// ??????baos?????????baos
            if(options<0){
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// ????????????options%?????????????????????????????????baos???
                break;
            }else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ????????????options%?????????????????????????????????baos???
            }

            options -= 10;// ???????????????10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ?????????????????????baos?????????ByteArrayInputStream???
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ???ByteArrayInputStream??????????????????
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
     * ???????????????
     * @param result
     */
    private void showDialogInfo(final IdentifyResult result){
        StringBuilder sb = new StringBuilder();
        sb.append("?????????" + result.getName() + "\n");
        sb.append("?????????" + result.getSex() + "\t" + "?????????" + result.getNation() + "\n");
        sb.append("?????????" + result.getBirth() + "\n");
        sb.append("?????????" + result.getAddress() + "\n" + "\n");
        sb.append("?????????????????????" + result.getId() + "\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(UrbanFamilybianjiActivity.this);
        AlertDialog   dialogInfo = builder.setTitle("????????????")
                .setMessage(sb.toString())
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .create();
        dialogInfo.show();

        et_idcard.setText(result.getId());
        et_idcard.setSelection(result.getId().length());

    }


    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }

}
