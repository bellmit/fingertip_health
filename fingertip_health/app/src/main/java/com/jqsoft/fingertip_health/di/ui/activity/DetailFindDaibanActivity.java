package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageNewAdapter;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.DiscoverListBean;
import com.jqsoft.fingertip_health.bean.FileListBean;
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
import com.jqsoft.fingertip_health.util.Util;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailFindDaibanActivity extends AbstractActivity implements AddFindContract.View{

    private GridImageNewAdapter adapter;

    private int maxSelectNum = 7;// 图片最大可选数量

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
    FunctionOptions options;
  //  List<FileListBean> fileList = new ArrayList<>();
    List<String> mysList = new ArrayList<>();


    @BindView(R.id.ll_back)
    LinearLayout ll_back;


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.btn_save)
    RoundTextView btn_save;

    @BindView(R.id.btn_zancun)
    RoundTextView btn_zancun;

    @BindView(R.id.setting_xiangzhen)
    TextView setting_xiangzhen;

    @BindView(R.id.et_phone)
    TextView et_phone;

    @BindView(R.id.et_address)
    TextView et_address;

    @BindView(R.id.et_reason)
    TextView et_reason;

    @BindView(R.id.setting_shixian)
    TextView setting_shixian;

    @Inject
    AddFindPresenter mPresenter;

    private OptionsPickerView pvOptions2;
    private ArrayList<ProvinceBean> options3Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options4Items = new ArrayList<>();
    private String sCodeshi="",sCodexian="",sCodequ="",sCodejiedao="",sbatchNo="",sId="";
    ArrayList<SRCLoginAreaBean> arealistqu = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistjiedao = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistxian = new ArrayList<>();
    ArrayList<SRCLoginAreaBean> arealistshi = new ArrayList<>();
    private ArrayList<ArrayList<SRCLoginAreaBean>> jiedaoListOut;
    private String batchNo,isMine;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_daiban_find;
    }

    @Override
    protected void initData() {


        setting_xiangzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(pvOptions2!=null)
                    pvOptions2.show(); //弹出条件选择器*/
            }
        });

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




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

    @Override
    protected void initView() {

        et_phone.setCursorVisible(false);
        et_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_phone.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter(11)});
        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  et_phone.setCursorVisible(true);
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

               /* int size= s.toString().length();
                if(size==11){
                    boolean isPhone= isPhone(s.toString());
                    boolean isFixphone =isFixedLine(s.toString());
                    if(isPhone ){

                    }else {
                        Toast.makeText(getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                    }
                }*/

            }
        });

        batchNo=getDeliveredStringByKey("batchNo");
        isMine=getDeliveredStringByKey("isMine");

        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getDetailFind(DetailFindDaibanActivity.this,name,
                batchNo,isMine);
        mPresenter.maindetail(map);


        final FullyGridLayoutManager manager = new FullyGridLayoutManager(DetailFindDaibanActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageNewAdapter(DetailFindDaibanActivity.this, onAddPicClickListener);
      //  adapter.setList(selectMedia);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridImageNewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (selectType) {
                    case FunctionConfig.TYPE_IMAGE:
                        // 预览图片 可长按保存 也可自定义保存路径
                        //PictureConfig.getInstance().externalPicturePreview(MainActivity.this, "/custom_file", position, selectMedia);
                        PictureConfig.getInstance().externalPicturePreview(DetailFindDaibanActivity.this, position, selectMedia);
                        break;
                    case FunctionConfig.TYPE_VIDEO:
                        // 预览视频
                        if (selectMedia.size() > 0) {
                            PictureConfig.getInstance().externalPictureVideo(DetailFindDaibanActivity.this, selectMedia.get(position).getPath());
                        }
                        break;
                }

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sXiangzhen=  setting_xiangzhen.getText().toString();
                String sphone=  et_phone.getText().toString();
                String sadress=  et_address.getText().toString();
                String sreason=  et_reason.getText().toString();

                if(sXiangzhen.equals("")){
                    Toast.makeText(getApplicationContext(),"乡镇不能为空",Toast.LENGTH_SHORT).show();
                }else if(sphone.equals("")){
                    Toast.makeText(getApplicationContext(),"电话不能为空",Toast.LENGTH_SHORT).show();
                }else if(sadress.equals("")){
                    Toast.makeText(getApplicationContext(),"地址不能为空",Toast.LENGTH_SHORT).show();
                }else if(sreason.equals("")){
                    Toast.makeText(getApplicationContext(),"情况说明不能为空",Toast.LENGTH_SHORT).show();
                }else {

                    String base64="";
                    String sFileId="";
                    int size =selectMedia.size();
                    if(size==0){
                        base64="";
                    }else {
                        for(int i=0;i<selectMedia.size();i++){

                            if(selectMedia.get(i).getPath().equals("test")){
                              /*  String   s2=selectMedia.get(i).getFileId()+";";
                                sFileId=sFileId+s2;*/
                                mysList.remove(selectMedia.get(i).getFileId());

                            }else {
                                if(TextUtils.isEmpty(selectMedia.get(i).getCompressPath()) || selectMedia.get(i).getCompressPath().equals("null")){
                                    String s1 = Base64Util.imageToBase64(selectMedia.get(i).getPath()).trim() + ";";
                                    base64 = base64 + s1;
                                }else {
                                    String s1 = Base64Util.imageToBase64(selectMedia.get(i).getCompressPath()).trim() + ";";
                                    base64 = base64 + s1;
                                }

                            }
                        }

                    }

                    int size2 =mysList.size();
                    if(size2==0){
                        sFileId="";
                    }else {
                        for(int i=0;i<mysList.size();i++){
                            String   s2=mysList.get(i)+";";
                            sFileId=sFileId+s2;
                        }
                    }


                    String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.getAddFindSave(DetailFindDaibanActivity.this,name,
                            sCodeshi,sCodexian,sCodequ,sCodejiedao,sphone,sadress,sreason,base64,sFileId,sbatchNo,sId);
                    mPresenter.main(map);
                }
            }
        });

        btn_zancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sXiangzhen=  setting_xiangzhen.getText().toString();
                String sphone=  et_phone.getText().toString();
                String sadress=  et_address.getText().toString();
                String sreason=  et_reason.getText().toString();

                if(sXiangzhen.equals("")){
                    Toast.makeText(getApplicationContext(),"乡镇不能为空",Toast.LENGTH_SHORT).show();
                }else if(sphone.equals("")){
                    Toast.makeText(getApplicationContext(),"电话不能为空",Toast.LENGTH_SHORT).show();
                }else if(sadress.equals("")){
                    Toast.makeText(getApplicationContext(),"地址不能为空",Toast.LENGTH_SHORT).show();
                }else if(sreason.equals("")){
                    Toast.makeText(getApplicationContext(),"情况说明不能为空",Toast.LENGTH_SHORT).show();
                }else {

                   /* Map<String, RequestBody> map1 =new HashMap<>();
                    for(int i=0;i<selectMedia.size();i++){
                        File file = new File(selectMedia.get(i).getPath());
                        RequestBody requestBody =RequestBody.create(MediaType.parse("multipart/form-data"),file);
                        map1.put("file"+i+"\";filename =\""+file.getName(),requestBody);
                    }*/
                    /*   Bitmap bitmap= BitmapFactory.decodeFile(selectMedia.get(0).getPath());
                    int height= bitmap.getHeight();
                    int width= bitmap.getWidth();*/



                    String base64="";
                    String sFileId="";
                    int size =selectMedia.size();
                    if(size==0){
                        base64="";
                    }else {
                        for(int i=0;i<selectMedia.size();i++){
                          if(selectMedia.get(i).getPath().equals("test")){
                            /*  String   s2=selectMedia.get(i).getFileId()+";";
                              sFileId=sFileId+s2;*/
                              mysList.remove(selectMedia.get(i).getFileId());
                          }else {
                              if(TextUtils.isEmpty(selectMedia.get(i).getCompressPath()) || selectMedia.get(i).getCompressPath().equals("null")){
                                  String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getPath()).trim()+";";
                                  base64=base64+s1;
                              }else {
                                  String   s1=   Base64Util.imageToBase64(selectMedia.get(i).getCompressPath()).trim()+";";
                                  base64=base64+s1;
                              }

                          }

                        }

                    }

                    int size2 =mysList.size();
                    if(size2==0){
                        sFileId="";
                    }else {
                        for(int i=0;i<mysList.size();i++){
                            String   s2=mysList.get(i)+";";
                            sFileId=sFileId+s2;
                        }
                    }


                   String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.getAddFindZancun(DetailFindDaibanActivity.this,name,
                            sCodeshi,sCodexian,sCodequ,sCodejiedao,sphone,sadress,sreason,base64,sFileId,sbatchNo,sId);
                    mPresenter.main(map);
                }

            }
        });


       /* List<gdws_sys_area>  user1 = DataSupport.where(" areaLevel=? ","area_4" ).find(gdws_sys_area.class);
        List<gdws_sys_area>  user2 = DataSupport.where(" areaLevel=? ","area_5" ).find(gdws_sys_area.class);
        List<gdws_sys_area>  user3 = DataSupport.where(" areaLevel=? ","area_3" ).find(gdws_sys_area.class);
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

        initOptionData2();
        initOptionPicker2();*/
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
     * 删除图片回调接口
     */

    private GridImageNewAdapter.onAddPicClickListener onAddPicClickListener = new GridImageNewAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {

        }
    };



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
                .detaildaibanFind(new AddFindModule(this))
                .inject(this);
    }

    @Override
    public void onAddFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

        Toast.makeText(getApplicationContext(),"上传成功",Toast.LENGTH_SHORT).show();
        setResult(3);
        finish();

    }

    @Override
    public void onAddFindFailure(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailFindSuccess(HttpResultBaseBean<DetailFindBeans> bean) {
        DetailFindBeans detailFindBeans = bean.getData();
        DiscoverListBean discoverListBean =detailFindBeans.getDiscover();


        if(bean!=null){
            setting_shixian.setText(discoverListBean.getCityName()+discoverListBean.getCountyName());
            setting_xiangzhen.setText(discoverListBean.getOfficeName()+discoverListBean.getCommunityName());
            et_phone.setText(Util.isMyEmpty(discoverListBean.getDiscoverPhone()));
            et_address.setText(Util.isMyEmpty(discoverListBean.getFamilyAddress()));
            et_reason.setText(Util.isMyEmpty(discoverListBean.getSriReason()));
            sCodeshi=discoverListBean.getCityCode();
            sCodexian=discoverListBean.getCountyCode();
            sCodequ=discoverListBean.getOfficeCode();
            sCodejiedao=discoverListBean.getCommunityCode();
            sbatchNo=discoverListBean.getBatchNo();
            sId =discoverListBean.getId();

           /* String photoUrl = Util.trimString(fileList.get(0).getFilePath());
            String imageUrl = Version.FIND_FILE_URL_BASE+photoUrl;*/



            List<FileListBean> fileList = new ArrayList<>();
            fileList.clear();
            fileList.addAll(detailFindBeans.getFileList());
            selectMedia.clear();
            for(int i=0;i<fileList.size();i++){
                LocalMedia localMedia = new LocalMedia();
                localMedia.setUrl(Version.FILE_URL_BASE+fileList.get(i).getFilePath());
                localMedia.setPath("test");
                localMedia.setType(1);
                localMedia.setFileId(fileList.get(i).getFileId());
                selectMedia.add(localMedia);
            }

            adapter.setList(selectMedia);
            adapter.notifyDataSetChanged();

           // GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_title));

           /* LocalMedia localMedia = new LocalMedia();
            localMedia.setCompressPath();
            selectMedia.add(0,);
            options.setSelectMedia();*/

        }else {

        }


    }

    @Override
    public void onDetailFindFailure(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


    private void initOptionPicker2() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions2 = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options3Items.get(options1).getPickerViewText()
                        + options4Items.get(options1).get(options2)
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                setting_xiangzhen.setText(tx);
          //      setting_dizhi.setText(setting_shixian.getText().toString()+tx);

                sCodequ =getArrayListqu(options1,options2);
                sCodejiedao =getArrayListjiedao(options1,options2);
                sCodexian=getArrayListxian(options1,options2);
                List<SRCLoginAreaBean>  user4 = DataSupport.where(" areaLevel=? and areaCode=? and state=?","area_3",sCodexian,"0").find(SRCLoginAreaBean.class);
                sCodeshi =user4.get(0).getAreaPid();

            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255,177,177))//设置分割线的颜色
                .setSelectOptions(0,1)//默认选中项
                .setBgColor(Color.rgb(255,255,255))
                .setTitleBgColor(Color.rgb(238,238,238))

                .setCancelColor(Color.rgb(38,174,158))
                .setSubmitColor(Color.rgb(38,174,158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("","","")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions2.setPicker(options3Items, options4Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/

    }

    private void initOptionData2() {

        for(int i=0;i<arealistqu.size();i++){
            options3Items.add(new ProvinceBean(0,arealistqu.get(i).getAreaName(),"描述部分","其他数据"));



        }
        //街道显示名称
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

    private  String getArrayListqu(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return arealistQu.getAreaCode();
    }

    private  String getArrayListxian(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return arealistQu.getAreaPid();
    }

    private  String getArrayListjiedao(int leftIndex,int rightIndex){
        SRCLoginAreaBean arealistQu = arealistqu.get(leftIndex);
        ArrayList<SRCLoginAreaBean> arrayList = jiedaoListOut.get(leftIndex);
        SRCLoginAreaBean areasListJiedao = arrayList.get(rightIndex);
        return areasListJiedao.getAreaCode();
    }


}
