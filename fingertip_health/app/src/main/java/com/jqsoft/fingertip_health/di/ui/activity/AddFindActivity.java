package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageAdapter;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.ProvinceBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.AddFindContract;
import com.jqsoft.fingertip_health.di.module.AddFindModule;
import com.jqsoft.fingertip_health.di.presenter.AddFindPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManager;
import com.jqsoft.fingertip_health.util.Base64Util;
import com.jqsoft.fingertip_health.util.Utils;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;

public class AddFindActivity extends AbstractActivity implements AddFindContract.View{

    private GridImageAdapter adapter;

    private int maxSelectNum = 7;// ????????????????????????

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


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.btn_save)
    RelativeLayout btn_save;

    @BindView(R.id.btn_delete)
    RelativeLayout btn_delete;

    @BindView(R.id.btn_zancun)
    RelativeLayout btn_zancun;

    @BindView(R.id.setting_xiangzhen)
    TextView setting_xiangzhen;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    @BindView(R.id.et_address)
    EditText et_address;

    @BindView(R.id.et_reason)
    EditText et_reason;

    @BindView(R.id.iv_location)
    ImageView iv_location;

    @BindView(R.id.setting_shixian)
    TextView setting_shixian;


    @Inject
    AddFindPresenter mPresenter;

    private OptionsPickerView pvOptions,pvOptions2;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private ArrayList<ProvinceBean> options3Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options4Items = new ArrayList<>();
    private String sCodeshi="",sCodexian="",sCodequ="",sCodejiedao="";
    List<SRCLoginAreaBean> arealistqu = new ArrayList<>();
    List<SRCLoginAreaBean> arealistjiedao = new ArrayList<>();
    List<SRCLoginAreaBean> arealistxian = new ArrayList<>();
    List<SRCLoginAreaBean> arealistshi = new ArrayList<>();
    private ArrayList<ArrayList<SRCLoginAreaBean>> jiedaoListOut;


    private ArrayList<ArrayList<SRCLoginAreaBean>> xianjiListOut;

    public static final int REQUEST_A = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_find;
    }

    @Override
    protected void initData() {


        setting_xiangzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(setting_shixian.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
                    }else {


                            initOptionData2();
                            initOptionPicker2();
                            if (pvOptions != null){
                                pvOptions2.show(); //?????????????????????
                            }




                    }

            }
        });


        setting_shixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pvOptions!=null)

                    pvOptions.show(); //?????????????????????
            }
        });



        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("SocialDeatailLocatLat","null");
                intent.putExtra("SocialDeatailLocatLng","null");
                intent.setClass(getApplicationContext(),MainMapActivity.class);
                startActivityForResult(intent, REQUEST_A);
            }
        });



/*
        ll_dibu.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom - oldBottom < -1) {
                    //?????????????????????,?????????????????????0
                    //???"1"?????????????????????????????????????????????1/3???
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            0);
                    //  params.ad(LinearLayout.SHOW_DIVIDER_END);
                    ll_dibu.setLayoutParams(params);

                } else if (bottom - oldBottom > 1) {

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            (int) 150);
                    //  params.addRule(LinearLayout.ALIGN_PARENT_BOTTOM);
                    ll_dibu.setLayoutParams(params);



                    //?????????????????????????????????????????????????????????????????????
                  } } });
*/

    }
    /**
     * ????????????????????????????????????.
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
     * ???????????????????????????
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

    @Override
    protected void initView() {

        et_phone.setCursorVisible(false);
        et_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_phone.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter(13)});
        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_phone.setCursorVisible(true);
            }
        });

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
                            Toast.makeText(getApplicationContext(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(size==12 || size==13){
                    if(s.toString().indexOf("-")!=-1){
                        boolean isTel= Utils.IsTelephone(s.toString());
                        if(  isTel){

                        }else {
                            Toast.makeText(getApplicationContext(),"??????????????????????????????",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        final FullyGridLayoutManager manager = new FullyGridLayoutManager(AddFindActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(AddFindActivity.this, onAddPicClickListener);
        adapter.setList(selectMedia);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (selectType) {
                    case FunctionConfig.TYPE_IMAGE:
                        // ???????????? ??????????????? ???????????????????????????
                        //PictureConfig.getInstance().externalPicturePreview(MainActivity.this, "/custom_file", position, selectMedia);
                        PictureConfig.getInstance().externalPicturePreview(AddFindActivity.this, position, selectMedia);
                        break;
                    case FunctionConfig.TYPE_VIDEO:
                        // ????????????
                        if (selectMedia.size() > 0) {
                            PictureConfig.getInstance().externalPictureVideo(AddFindActivity.this, selectMedia.get(position).getPath());
                        }
                        break;
                }

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sXianshi=  setting_shixian.getText().toString();
                String sXiangzhen=  setting_xiangzhen.getText().toString();
                String sphone=  et_phone.getText().toString();
                String sadress=  et_address.getText().toString();
                String sreason=  et_reason.getText().toString();

                if(sXianshi.equals("")){
                    Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
                } else if(sXiangzhen.equals("")){
                    Toast.makeText(getApplicationContext(),"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(sphone.equals("")){
                    Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(sphone.length()<11){
                    Toast.makeText(getApplicationContext(),"??????????????????????????????",Toast.LENGTH_SHORT).show();
                }else if(sadress.equals("")){
                    Toast.makeText(getApplicationContext(),"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(sreason.equals("")){
                    Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_SHORT).show();
                }else {

                    String base64="";
                    int size =selectMedia.size();
                    if(size==0){
                        base64="";
                    }else {
                        for(int i=0;i<selectMedia.size();i++){
                          String sCompressPath =  selectMedia.get(i).getCompressPath();
                            if(sCompressPath==null || TextUtils.isEmpty(sCompressPath)){
                                String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getPath()).trim()+";";
                                base64=base64+s1;
                            }else {
                                String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getCompressPath()).trim()+";";
                                base64=base64+s1;
                            }


                        }

                    }


                    String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.getAddFindSave(AddFindActivity.this,name,
                            sCodeshi,sCodexian,sCodequ,sCodejiedao,sphone,sadress,sreason,base64,"","","");
                    mPresenter.main(map);
                }
            }
        });

        btn_zancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sXianshi=  setting_shixian.getText().toString();
                String sXiangzhen=  setting_xiangzhen.getText().toString();
                String sphone=  et_phone.getText().toString();
                String sadress=  et_address.getText().toString();
                String sreason=  et_reason.getText().toString();

                 if(sXianshi.equals("")){
                    Toast.makeText(getApplicationContext(),"?????????????????????",Toast.LENGTH_SHORT).show();
                } else if(sXiangzhen.equals("")){
                    Toast.makeText(getApplicationContext(),"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(sphone.equals("")){
                    Toast.makeText(getApplicationContext(),"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(sadress.equals("")){
                    Toast.makeText(getApplicationContext(),"??????????????????",Toast.LENGTH_SHORT).show();
                }else if(sreason.equals("")){
                    Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_SHORT).show();
                }else {


                    String base64="";
                    int size =selectMedia.size();
                    if(size==0){
                        base64="";
                    }else {
                        for(int i=0;i<selectMedia.size();i++){
                            String sCompressPath =  selectMedia.get(i).getCompressPath();
                            if( sCompressPath==null || TextUtils.isEmpty(sCompressPath)){
                                String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getPath()).trim()+";";
                                base64=base64+s1;
                            }else {

                                String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getCompressPath()).trim()+";";
                                base64=base64+s1;
                            }

                        }

                    }


                   String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.getAddFindZancun(AddFindActivity.this,name,
                            sCodeshi,sCodexian,sCodequ,sCodejiedao,sphone,sadress,sreason,base64,"","","");
                    mPresenter.main(map);
                }

            }
        });


//        List<gdws_sys_area>  user1 = DataSupport.where(" areaLevel=? ","area_4" ).find(gdws_sys_area.class);
        List<SRCLoginAreaBean>  user2 = DataSupport.where(" areaLevel=? and state=? ","area_5" ,"0").find(SRCLoginAreaBean.class);
        List<SRCLoginAreaBean>  user3 = DataSupport.where(" areaLevel=? and state=?","area_3" ,"0").find(SRCLoginAreaBean.class);
        List<SRCLoginAreaBean>  user41 = DataSupport.where(" areaLevel=? and state=?","area_2" ,"0").find(SRCLoginAreaBean.class);


//        arealistqu.clear();
//        arealistqu=user1;


        arealistjiedao.clear();
        arealistjiedao=user2;

        arealistxian.clear();
        arealistxian=user3;


        arealistshi.clear();
        arealistshi=user41;


        initOptionData();
        initOptionPicker();




        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sCodexian="";
                sCodequ="";
                sCodejiedao="";
                sCodeshi="";
                setting_xiangzhen.setText("");
                et_phone.setText("");
                et_address.setText("");
                et_reason.setText("");
                selectMedia.clear();
                adapter.setList(selectMedia);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void loadData() {

    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_find);
    }*/


    /**
     * ????????????????????????
     */

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
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
                        themeStyle = ContextCompat.getColor(AddFindActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(AddFindActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ ??????????????? ????????????????????????
                        previewColor = ContextCompat.getColor(AddFindActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(AddFindActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(AddFindActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(AddFindActivity.this, R.color.tab_color_true);
                    }

                    FunctionOptions options = new FunctionOptions.Builder()
                            .setType(selectType) // ??????or?????? FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //????????????
                            .setEnablePixelCompress(true) //????????????????????????
                            .setEnableQualityCompress(true) //?????????????????????
                            .setMaxSelectNum(maxSelectNum) // ????????????????????????
                            .setMinSelectNum(0)// ?????????????????????????????????????????????????????????
                            .setSelectMode(selectMode) // ?????? or ??????
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
                        PictureConfig.getInstance().init(options).startOpenCamera(AddFindActivity.this);
                    } else {
                        // ??????????????????????????????????????????
                        PictureConfig.getInstance().init(options).openPhoto(AddFindActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // ????????????
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }
    };


    /**
     * ??????????????????
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
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
            adapter.setList(selectMedia);
            adapter.notifyDataSetChanged();
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

        if(requestCode==10 && resultCode==10){

            String change01 = data.getStringExtra("location");
            et_address.setText(change01);

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



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name))
                    return getApplicationContext().getSystemService(name);
                return super.getSystemService(name);
            }
        });
    }



    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addFind(new AddFindModule(this))
                .inject(this);
    }

    @Override
    public void onAddFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

        Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
        setResult(0);
        finish();
     //   Toast.makeText(getApplicationContext(),bean.getMessage(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAddFindFailure(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailFindSuccess(HttpResultBaseBean<DetailFindBeans> bean) {

    }

    @Override
    public void onDetailFindFailure(String message) {

    }
    private void initOptionPicker() {//????????????????????????

        /**
         * ?????? ?????????????????????????????????(????????????)???????????? JsonDataActivity ?????????????????????
         */

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                setting_shixian.setText(tx);
                setting_xiangzhen.setText("");

                sCodeshi =getArrayListshi(options1,options2);
                sCodexian =getArrayListxianji(options1,options2);
            }
        })
                .setTitleText("????????????")
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255,177,177))//????????????????????????
                .setSelectOptions(0,1)//???????????????
                .setBgColor(Color.rgb(255,255,255))
                .setTitleBgColor(Color.rgb(238,238,238))

                .setCancelColor(Color.rgb(38,174,158))
                .setSubmitColor(Color.rgb(38,174,158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("","","???")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//???????????????*/
        pvOptions.setPicker(options1Items, options2Items);//???????????????
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//???????????????*/

    }

    private void initOptionData() {
        options1Items.clear();
        options2Items.clear();
        for(int i=0;i<arealistshi.size();i++){
            options1Items.add(new ProvinceBean(0,arealistshi.get(i).getAreaName(),"????????????","????????????"));
        }
        ArrayList<String> options2Items_01 = new ArrayList<>();
        for(int i=0;i<arealistxian.size();i++){
            if(arealistxian.get(i).getAreaName().equals("?????????")){

            }else {
                options2Items_01.add(arealistxian.get(i).getAreaName());
            }

        }

        options2Items.add(options2Items_01);


        //??????????????????
        ArrayList<ArrayList<String>> xianjiNameArrayListOut = new ArrayList<>();

        xianjiListOut = new ArrayList<>();
        for (int i= 0;i < arealistshi.size();i ++){
            SRCLoginAreaBean area = arealistshi.get(i);
            ArrayList<SRCLoginAreaBean> myJiedaoListIn = new ArrayList<>();
            ArrayList<String> jiedaoNameArrayListIn = new ArrayList<>();

            for (int j=0;j<arealistxian.size();j++) {
                if(arealistxian.get(j).getAreaName().equals("?????????")){

                }else {
                    SRCLoginAreaBean areaJiedao = arealistxian.get(j);
                    if (area.getAreaCode().equals(areaJiedao.getAreaPid())){
                        myJiedaoListIn.add(areaJiedao);
                        jiedaoNameArrayListIn.add(areaJiedao.getAreaName());
                    }
                }

            }
            xianjiListOut.add(myJiedaoListIn);
            xianjiNameArrayListOut.add(jiedaoNameArrayListIn);
        }

        options2Items=xianjiNameArrayListOut;

        ArrayList<ArrayList<String>> options2Items_011 = new ArrayList<>();
        ArrayList<String> options2Items_0111 = new ArrayList<>();

        for(int i=0;i<arealistshi.size();i++){
            for (int j=0;j<arealistxian.size();j++){
                if(arealistshi.get(i).getAreaCode().equals(arealistxian.get(j).getAreaPid())){
                    // options2Items_011.add(arealistjiedao.get(j).getName());
                    options2Items_0111.add(arealistxian.get(j).getAreaName());

                }


            }
            options2Items.add(options2Items_0111);
            options2Items_0111.clear();

        }






    }


    private void initOptionPicker2() {//????????????????????????

        /**
         * ?????? ?????????????????????????????????(????????????)???????????? JsonDataActivity ?????????????????????
         */

        pvOptions2 = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                if(options4Items.get(0).size()==0){
                    String tx = options3Items.get(options1).getPickerViewText()
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                    setting_xiangzhen.setText(tx);
                    //   setting_dizhi.setText(setting_shixian.getText().toString()+tx);

                    sCodequ =getArrayListqu(options1,options2);
                    sCodejiedao =getArrayListjiedao(options1,options2);
                }else {
                    String tx = options3Items.get(options1).getPickerViewText()
                            + options4Items.get(options1).get(options2)
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                    setting_xiangzhen.setText(tx);
                    //   setting_dizhi.setText(setting_shixian.getText().toString()+tx);

                    sCodequ =getArrayListqu(options1,options2);
                    sCodejiedao =getArrayListjiedao(options1,options2);
                }


            }
        })
                .setTitleText("????????????")
                .setContentTextSize(20)//????????????????????????
                .setDividerColor(Color.rgb(255,177,177))//????????????????????????
                .setSelectOptions(0,1)//???????????????
                .setBgColor(Color.rgb(255,255,255))
                .setTitleBgColor(Color.rgb(238,238,238))

                .setCancelColor(Color.rgb(38,174,158))
                .setSubmitColor(Color.rgb(38,174,158))

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setLabels("","","")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//???????????????*/
        pvOptions2.setPicker(options3Items, options4Items);//???????????????
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//???????????????*/

    }

    private void initOptionData2() {
        arealistqu.clear();
        options3Items.clear();
        options4Items.clear();
        List<SRCLoginAreaBean>  user1 = DataSupport.where(" areaLevel=? and areaPid=? and state=?","area_4",sCodexian,"0" ).find(SRCLoginAreaBean.class);

        for(int i=0;i<user1.size();i++){
            arealistqu.add(user1.get(i));
        }

        for(int i=0;i<arealistqu.size();i++){
            options3Items.add(new ProvinceBean(0,arealistqu.get(i).getAreaName(),"????????????","????????????"));
        }
        //??????????????????
        ArrayList<ArrayList<String>> jiedaoNameArrayListOut = new ArrayList<>();

        jiedaoListOut = new ArrayList<>();
        for (int i= 0;i < arealistqu.size();i ++){
            SRCLoginAreaBean area = arealistqu.get(i);
            ArrayList<SRCLoginAreaBean> myJiedaoListIn = new ArrayList<>();
            ArrayList<String> jiedaoNameArrayListIn = new ArrayList<>();

            for (int j=0;j<arealistjiedao.size();j++) {
                SRCLoginAreaBean areaJiedao = arealistjiedao.get(j);
                if (area.getAreaCode().equals(areaJiedao.getAreaPid())){
                    myJiedaoListIn.add(areaJiedao);
                    jiedaoNameArrayListIn.add(areaJiedao.getAreaName());
                }
            }
            jiedaoListOut.add(myJiedaoListIn);
            jiedaoNameArrayListOut.add(jiedaoNameArrayListIn);
        }

        options4Items=jiedaoNameArrayListOut;

        ArrayList<ArrayList<String>> options2Items_011 = new ArrayList<>();
        ArrayList<String> options2Items_0111 = new ArrayList<>();

        for(int i=0;i<arealistqu.size();i++){
            for (int j=0;j<arealistjiedao.size();j++){
                if(arealistqu.get(i).getAreaCode().equals(arealistjiedao.get(j).getAreaPid())){
                    // options2Items_011.add(arealistjiedao.get(j).getName());
                    options2Items_0111.add(arealistjiedao.get(j).getAreaName());

                    //   options2Items_0111.clear();
                }


            }
            options4Items.add(options2Items_0111);
            options2Items_0111.clear();

        }


    }

    private  Map<String,String> getArrayList(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        Map<String,String> map = new HashMap<>();
        map.put(arealistQu.getAreaCode(),areasListJiedao.getAreaCode());
        return map;
    }

    private  String getArrayListshi(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistshi.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = xianjiListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return arealistQu.getAreaCode();
    }

    private  String getArrayListxianji(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistxian.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = xianjiListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return areasListJiedao.getAreaCode();
    }


    private  String getArrayListqu(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return arealistQu.getAreaCode();
    }

    private  String getArrayListjiedao(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return areasListJiedao.getAreaCode();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(1);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
