package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Administrator on 2017-07-07.
 */
//
public class UrbanFamilybianjiStatusActivity extends AbstractActivity implements
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
    TextView et_name;

    @BindView(R.id.et_idcard)
    TextView et_idcard;

    @BindView(R.id.btn_save)
    RoundTextView btn_save;

    @BindView(R.id.tv_sex)
    TextView tv_sex;

    @BindView(R.id.tv_birth)
    TextView tv_birth;

    @BindView(R.id.et_shouru)
    TextView et_shouru;

    @BindView(R.id.et_shebaokahao)
    TextView et_shebaokahao;

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

    public UrbanFamilybianjiStatusActivity() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_urban_family_status_layout;
    }

    @Override
    protected void initData() {

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String sex=tv_sex.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"请输入正确的身份证号码,会自动填充性别!",Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        tv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String sex=tv_birth.getText().toString();
                if(sex.equals("")){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"请输入正确的身份证号码,会自动填充出生年月!",Toast.LENGTH_SHORT).show();
                }*/

            }
        });

      //  setPricePoint(et_shouru);

        et_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // et_idcard.setCursorVisible(true);// 再次点击显示光标
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

             //   onAddPicClick(0,0);
            }
        });

        tv_guanxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myreg_type = DataSupport.where(" pcode=? ","relation" ).find(SRCLoginDataDictionaryBean.class);
                if(myrelation.equals("本人")){
                    tv_guanxi.setText("本人");

                    for(int i=0;i<myreg_type.size();i++){
                        if(myreg_type.get(i).getName().equals("本人")){
                            relation=myreg_type.get(i).getCode();
                        }
                    }

                }else {
                    for(int i=0;i<myreg_type.size();i++){
                        if(myreg_type.get(i).getName().equals("本人")){
                            myreg_type.remove(i);
                        }
                    }

                    initguanxi(tv_guanxi, myreg_type, "与户主关系");
                    opguanxi.show();
                }
*/

            }
        });

        tv_nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","nation" ).find(SRCLoginDataDictionaryBean.class);
                initminzu(tv_nation, myregpro, "民族");
                opnation.show();*/
            }
        });

        tv_jiuyeqingkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","employment_status" ).find(SRCLoginDataDictionaryBean.class);
                initjiatingleibie(tv_jiuyeqingkuang, myregpro, "就业情况");
                opjiatingleibie.show();*/
            }
        });

        tv_canbaoleixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","canhe_canbao" ).find(SRCLoginDataDictionaryBean.class);
                initzhipin(tv_canbaoleixing, myregpro, "参保类型");
                opzhipin.show();*/
            }
        });
        tv_zhongbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   List<String> myList = new ArrayList<String>();
                myList.add("否");
                myList.add("是");
                initjinan(tv_zhongbing, myList, "是否重病");
                opjinan.show();*/
            }
        });

        tv_canji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* List<String> myList = new ArrayList<String>();
                myList.add("否");
                myList.add("是");
                initcanji(tv_canji, myList, "是否残疾");
                opcanji.show();*/
            }
        });

        tv_mianmao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","political_status" ).find(SRCLoginDataDictionaryBean.class);
                initkaihuhang(tv_mianmao, myregpro, "政治面貌");
                opkaihuhang.show();*/
            }
        });

        tv_laodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","work_status" ).find(SRCLoginDataDictionaryBean.class);
                initlaodongnengli(tv_laodong, myregpro, "劳动能力");
                oplaodong.show();*/
            }
        });

        tv_jiangkangzhuangkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","health_status" ).find(SRCLoginDataDictionaryBean.class);
                initjiankang(tv_jiangkangzhuangkuang, myregpro, "健康状况");
                opjiankang.show();*/
            }
        });

        tv_hunyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    final List<SRCLoginDataDictionaryBean>  myregpro = DataSupport.where(" pcode=? ","marital_status" ).find(SRCLoginDataDictionaryBean.class);
                inithunyin(tv_hunyin, myregpro, "婚姻状况");
                ophunyin.show();*/
            }
        });


        iv_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (ActivityCompat.checkSelfPermission(UrbanFamilybianjiStatusActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(UrbanFamilybianjiStatusActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }*/
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
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sidcard)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"身份证号码不能为空",Toast.LENGTH_SHORT).show();
                }else if( !TextUtils.isEmpty(info)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,info.toString(),Toast.LENGTH_SHORT).show();
                }else if(isCardchongfu(sidcard)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"其他家庭成员已存在该身份证号码",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(ssex)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sbirth)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"出生日期不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sguanxi)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"与户主关系不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(snation)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"民族不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sjiuyeqingkuang)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"就业情况不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(scanbaoleixing)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"参保类型不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(slaodongnengli)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"劳动能力不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(shunyinzhuangkuang)){
                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"婚姻状况不能为空",Toast.LENGTH_SHORT).show();
                }else {

                    String sexcode="";
                    if(ssex.equals("男")){
                        sexcode="sex_1";
                    }else if(ssex.equals("女")){
                        sexcode="sex_2";
                    }

                    String szhongbing=tv_zhongbing.getText().toString();

                    if(szhongbing.equals("否")){
                        szhongbing="0";
                    }else if(szhongbing.equals("是")){
                        szhongbing="1";
                    }else {
                        szhongbing="";
                    }

                    String scanji=tv_canji.getText().toString();

                    if(scanji.equals("否")){
                        scanji="0";
                    }else if(scanji.equals("是")){
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
     * 删除图片回调接口
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
    private int compressFlag = 1;// 1 系统自带压缩 2 luban压缩
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private int themeStyle;
    private int previewColor, completeColor, previewBottomBgColor, previewTopBgColor, bottomBgColor, checkedBoxDrawable;
    private boolean mode = false;// 启动相册模式
    private int selectMode = FunctionConfig.MODE_MULTIPLE;
    private boolean clickVideo;
    private GridImageAdapter adapter;
    public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 进入相册
                    /**
                     * type --> 1图片 or 2视频
                     * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
                     * maxSelectNum --> 可选择图片的数量
                     * selectMode         --> 单选 or 多选
                     * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
                     * isPreview    --> 是否打开预览选项
                     * isCrop       --> 是否打开剪切选项
                     * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
                     * ThemeStyle -->主题颜色
                     * CheckedBoxDrawable -->图片勾选样式
                     * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
                     * cropH-->裁剪高度 值不能小于100
                     * isCompress -->是否压缩图片
                     * setEnablePixelCompress 是否启用像素压缩
                     * setEnableQualityCompress 是否启用质量压缩
                     * setRecordVideoSecond 录视频的秒数，默认不限制
                     * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
                     * setImageSpanCount -->每行显示个数
                     * setCheckNumMode 是否显示QQ选择风格(带数字效果)
                     * setPreviewColor 预览文字颜色
                     * setCompleteColor 完成文字颜色
                     * setPreviewBottomBgColor 预览界面底部背景色
                     * setBottomBgColor 选择图片页面底部背景色
                     * setCompressQuality 设置裁剪质量，默认无损裁剪
                     * setSelectMedia 已选择的图片
                     * setCompressFlag 1为系统自带压缩  2为第三方luban压缩
                     * 注意-->type为2时 设置isPreview or isCrop 无效
                     * 注意：Options可以为空，默认标准模式
                     */
                    String b = "50";// 压缩最大大小 单位是b

                    if (!isNull(b)) {
                        maxB = Integer.parseInt(b);
                    }

                    if (!isNull("200") && !isNull("200")) {
                        compressW = Integer.parseInt("200");
                        compressH = Integer.parseInt("200");
                    }

                    if (theme) {
                        // 设置主题样式
                        themeStyle = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ 风格模式下 这里自己搭配颜色
                        previewColor = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(UrbanFamilybianjiStatusActivity.this, R.color.tab_color_true);
                    }

                    FunctionOptions options = new FunctionOptions.Builder()
                            .setType(selectType) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //是否压缩
                            .setEnablePixelCompress(true) //是否启用像素压缩
                            .setEnableQualityCompress(true) //是否启质量压缩
                            .setMaxSelectNum(1) // 可选择图片的数量
                            .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                            .setSelectMode(2) // 单选 or 多选
                            .setShowCamera(isShow) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                            .setEnablePreview(enablePreview) // 是否打开预览选项
                            .setPreviewVideo(isPreviewVideo) // 是否预览视频(播放) mode or 多选有效
                            .setCheckedBoxDrawable(checkedBoxDrawable)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                            .setRecordVideoSecond(60) // 视频秒数
                            .setVideoS(0)// 查询多少秒内的视频 单位:秒
                            .setCustomQQ_theme(0)// 可自定义QQ数字风格，不传就默认是蓝色风格
                            .setGif(false)// 是否显示gif图片，默认不显示
                            .setMaxB(50) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb
                            .setPreviewColor(previewColor) //预览字体颜色
                            .setCompleteColor(completeColor) //已完成字体颜色
                            .setPreviewBottomBgColor(previewBottomBgColor) //预览图片底部背景色
                            .setPreviewTopBgColor(previewTopBgColor)//预览图片标题背景色
                            .setBottomBgColor(bottomBgColor) //图片列表底部背景色
                            .setGrade(Luban.THIRD_GEAR) // 压缩档次 默认三档
                            .setCheckNumMode(isCheckNumMode)
                            .setCompressQuality(80) // 图片裁剪质量,默认无损
                            .setImageSpanCount(4) // 每行个数
                            .setSelectMedia(selectMedia) // 已选图片，传入在次进去可选中，不能传入网络图片
                            .setCompressFlag(2) // 1 系统自带压缩 2 luban压缩
                            .setCompressW(0) // 压缩宽 如果值大于图片原始宽高无效
                            .setCompressH(0) // 压缩高 如果值大于图片原始宽高无效
                            .setThemeStyle(themeStyle) // 设置主题样式
                            .setNumComplete(false) // 0/9 完成  样式
                            .setClickVideo(clickVideo)// 开启点击声音
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题字体颜色
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题右边字体颜色
//                            .setLeftBackDrawable(R.mipmap.back2) // 设置返回键图标
//                            .setStatusBar(ContextCompat.getColor(MainActivity.this, R.color.white)) // 设置状态栏颜色，默认是和标题栏一致
//                            .setImmersive(true)// 是否改变状态栏字体颜色(黑色)
                            .create();

                    if (mode) {
                        // 只拍照
                        PictureConfig.getInstance().init(options).startOpenCamera(UrbanFamilybianjiStatusActivity.this);
                    } else {
                        // 先初始化参数配置，在启动相册
                        PictureConfig.getInstance().init(options).openPhoto(UrbanFamilybianjiStatusActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }

    /**
     * 判断 一个字段的值否为空
     *
     * @param s
     * @return
     * @author Michael.Zhang 2013-9-7 下午4:39:00
     */

    public boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }

    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia.clear();
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            LocalMedia media = resultList.get(0);
            if (media.isCompressed()) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                String path = media.getCompressPath();
            } else {
                // 原图地址
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

            Glide.with(UrbanFamilybianjiStatusActivity.this)
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
     * 単独拍照图片回调
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
                // 获取返回的图片列表
                Util.showGifProgressDialog(UrbanFamilybianjiStatusActivity.this);
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
                                    Util.hideGifProgressDialog(UrbanFamilybianjiStatusActivity.this);
                                    showDialogInfo(result);

                                }else {
                                    Util.hideGifProgressDialog(UrbanFamilybianjiStatusActivity.this);
                                    Toast.makeText(UrbanFamilybianjiStatusActivity.this,"识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(UrbanFamilybianjiStatusActivity.this,"识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
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
                .addUrbanLowaddFamilybianjistatusActivity(new UrbanLowAddFamilyActivityModule(this))
                .inject(this);

    }

    @Override
    protected void initView() {
      //  type = getDeliveredString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY);

        myid=getDeliveredStringByKey("id");
        mybatchNo=getDeliveredStringByKey("batchNo");
        myrelation=getDeliveredStringByKey("relation");
        mCardList = getIntent().getStringArrayListExtra("mCardList");

        Map<String, String> map = ParametersFactory.getfamilybianji(UrbanFamilybianjiStatusActivity.this,myid
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




    private void initguanxi(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opguanxi = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                relation=listleixing.get(options1).getCode();
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
        opguanxi.setPicker(listleixing);//一级选择器*/


    }

    private void initminzu(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opnation = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                nation= listleixing.get(options1).getCode();
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
        opnation.setPicker(listleixing);//一级选择器*/


    }

    private void initjiatingleibie(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opjiatingleibie = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
                employmentStatus= listleixing.get(options1).getCode();
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

        opzhipin = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                canhecanbao= listleixing.get(options1).getCode();
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

        opjinan = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1);
                huji.setText(tx);
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

    private void initcanji(final TextView huji, final List<String> listleixing, String mingcheng) {//条件选择器初始化

        opcanji = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1);
                huji.setText(tx);
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
        opcanji.setPicker(listleixing);//一级选择器*/


    }


    private void initkaihuhang(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opkaihuhang = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                politicalStatus= listleixing.get(options1).getCode();
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

    private void initlaodongnengli(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        oplaodong = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                workStatus= listleixing.get(options1).getCode();
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
        oplaodong.setPicker(listleixing);//一级选择器*/


    }

    private void initjiankang(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opjiankang = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                healthStatus= listleixing.get(options1).getCode();
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
        opjiankang.setPicker(listleixing);//一级选择器*/


    }

    private void inithunyin(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        ophunyin = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                maritalStatus= listleixing.get(options1).getCode();
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
        ophunyin.setPicker(listleixing);//一级选择器*/


    }



    private void initArea(final TextView huji, final List<SRCLoginAreaBean> listleixing, String mingcheng) {//条件选择器初始化

        opArea = new OptionsPickerView.Builder(UrbanFamilybianjiStatusActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getAreaName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                communityCode=listleixing.get(options1).getAreaCode();
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
    public void onUrbanBaseInfoSaveSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Toast.makeText(getApplicationContext(),bean.getMessage(),Toast.LENGTH_SHORT).show();
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
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","nation" ,"0").find(SRCLoginDataDictionaryBean.class);
                for(int i=0;i<myguanxi.size();i++){
                    if(myguanxi.get(i).getCode().equals(nation)){
                        tv_nation.setText(myguanxi.get(i).getName());
                    }
                }
            }


            if(TextUtils.isEmpty(szhongbing) || szhongbing.equals("null") || szhongbing==null){

            }else {
                if(szhongbing.equals("0")){
                    tv_zhongbing.setText("否");
                }else if(szhongbing.equals("1")){
                    tv_zhongbing.setText("是");
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
                    tv_canji.setText("否");
                }else if(scanji.equals("1")){
                    tv_canji.setText("是");
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
                final List<SRCLoginDataDictionaryBean>  myguanxi = DataSupport.where(" pcode=? and state=?","health_status" ,"0").find(SRCLoginDataDictionaryBean.class);
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
        MultiImageSelector.create(UrbanFamilybianjiStatusActivity.this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(UrbanFamilybianjiStatusActivity.this, REQUEST_IMAGE);
    }

    private String p = null;
    private Bitmap bitmap = null;



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
        AlertDialog.Builder builder = new AlertDialog.Builder(UrbanFamilybianjiStatusActivity.this);
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
