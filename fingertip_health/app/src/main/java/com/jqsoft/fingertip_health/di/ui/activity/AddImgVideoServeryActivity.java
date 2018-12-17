package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AddGridImageAdapter;
import com.jqsoft.fingertip_health.adapter.AddVideoImageAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.HouseHoldeBackBean;
import com.jqsoft.fingertip_health.bean.HttpResultTestBean;
import com.jqsoft.fingertip_health.bean.ImageListData;
import com.jqsoft.fingertip_health.bean.Uploadpic;
import com.jqsoft.fingertip_health.bean.VideoBackBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.DispalyBaseinfoContract;
import com.jqsoft.fingertip_health.di.module.DispalyInfoActivityModule;
import com.jqsoft.fingertip_health.di.presenter.DispalyBaseinfoPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManager;
import com.jqsoft.fingertip_health.helper.video_upload.UploadVideoManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Base64Util;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.FileUtils;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AddImgVideoServeryActivity extends AbstractActivity implements DispalyBaseinfoContract.View {
    private int maxSelectNum = 8;// 图片最大可选数量
    private AddGridImageAdapter adapter;
    private AddVideoImageAdapter videoAdapter;
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
    private List<LocalMedia> selectMediax = new ArrayList<>();
    private List<LocalMedia> adapterImgs = new ArrayList<>();
    private List<LocalMedia> selectVideoMedia = new ArrayList<>();
    private List<LocalMedia> allList = new ArrayList<>();
    private int themeStyle;
    private int previewColor, completeColor, previewBottomBgColor, previewTopBgColor, bottomBgColor, checkedBoxDrawable;
    private boolean mode = false;// 启动相册模式
    private int selectMode = FunctionConfig.MODE_MULTIPLE;
    private boolean clickVideo;
    FunctionOptions options;
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.responsname)
    EditText responsname;
    @BindView(R.id.householddata)
    TextView householddata;
    @BindView(R.id.householdresult)
    EditText householdresult;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.btn_zancun)
    RoundTextView btn_zancun;
//    @BindView(R.id.save_btn)
//    LinearLayout save_btn;
    @BindView(R.id.mapsite)
    TextView mapsite;
//    @BindView(R.id.save_btn)
//    LinearLayout save_btn;
    @BindView(R.id.recyclervideo)
    RecyclerView recyclervideo;
    @BindView(R.id.adddata)
    ImageView adddata;
    private String gfamliyId, gid, diaochadate, diaocharenname, diaochajielun, fileSize, filePath_0, fileName_0, addDate_0, fileType_0;
    private ArrayList<ImageListData> dataArrayList = new ArrayList<>();
    private ArrayList<ImageListData> dataArrayListForVideo = new ArrayList<>();
    private ArrayList<ImageListData> dataArrayListx = new ArrayList<>();
    @Inject
    DispalyBaseinfoPresenter mPresenter;
    private String videoName, byteArray;
    private List<ByteArrayInputStream> temp = new ArrayList<>();
    private String fileName = "", fileBase64 = "";
    private String gId, ftype;
    private String longitude;// 经度-
    private String latitude;//纬度-
    private String diaochaaddress;//调查位置-
    private String cardno;

    @Override

    protected int getLayoutId() {
        return R.layout.activity_addhouse_layout;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        gfamliyId = bundle.getString("gfamliyId");//读出数据
        gId = bundle.getString("gId");
        ftype = bundle.getString("ftype");
        cardno = bundle.getString("cardno");
        mapsite.setSelected(true);
        getCurrentLocationLatLng();
    }


    @Override
    protected void initView() {
        online_consultation_title.setText("入户调查");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final FullyGridLayoutManager manager = new FullyGridLayoutManager(AddImgVideoServeryActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new AddGridImageAdapter(AddImgVideoServeryActivity.this, onAddPicClickListener);
        adapter.setList(adapterImgs);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AddGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureConfig.getInstance().externalPicturePreview(AddImgVideoServeryActivity.this, position, selectMediax);
            }

        });


        final FullyGridLayoutManager manager2 = new FullyGridLayoutManager(AddImgVideoServeryActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclervideo.setLayoutManager(manager2);
        videoAdapter = new

                AddVideoImageAdapter(AddImgVideoServeryActivity.this, onAddVideoClickListener);
        videoAdapter.setList(selectVideoMedia);
        videoAdapter.setSelectMax(maxSelectNum);
        recyclervideo.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(new AddVideoImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(ftype.equals("2")){
                    String serverUrl = selectVideoMedia.get(position).getPath();
                    String path = selectVideoMedia.get(position).getUrl();
                    String ultimatePath = serverUrl;
                    if (!StringUtils.isBlank(path)){
                        ultimatePath+=path;
                    }
                    Util.gotoVideoDisplayActivityWithUrl(AddImgVideoServeryActivity.this, ultimatePath);
//                    PictureConfig.getInstance().externalPictureVideo(AddImgVideoServeryActivity.this,ultimatePath);
////                    PictureConfig.getInstance().externalPictureVideo(AddImgVideoServeryActivity.this,selectMedia.get(position).getPath());
                }else{//新增调查
                    String serverUrl = selectVideoMedia.get(position).getPath();
                    String path = selectVideoMedia.get(position).getUrl();
                    String ultimatePath = serverUrl;
                    if (!StringUtils.isBlank(path)){
                        ultimatePath+=path;
                    }
                    Util.gotoVideoDisplayActivityWithUrl(AddImgVideoServeryActivity.this, ultimatePath);
//                    PictureConfig.getInstance().externalPictureVideo(AddImgVideoServeryActivity.this, ultimatePath);
////                    PictureConfig.getInstance().externalPictureVideo(AddImgVideoServeryActivity.this, selectVideoMedia.get(position).getPath());
            }}
        });

        adddata.setOnClickListener(new

                                           NoDoubleClickListener() {
                                               @Override
                                               public void onNoDoubleClick(View v) {
                                                   super.onNoDoubleClick(v);
                                                   String initial = getSignTimeString();
                                                   Util.showDateSelectDialog(AddImgVideoServeryActivity.this, initial, "a_party_fragment_sign_time", new DatePickerDialog.OnDateSetListener() {
                                                       @Override
                                                       public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                                           String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                                                           householddata.setText(s);
                                                       }
                                                   });
                                               }
                                           });
        btn_zancun.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
//                byteArray = "dsdsd";
//                Util.enCodeVideo(selectVideoMedia.get(0).getPath());
//                Map<String, String> map = getRequestMap();
//                mPresenter.saveVideo(map, false);
////                submitVedio(selectVideoMedia.get(0).getPath());
                diaocharenname = responsname.getText().toString();
                diaochadate = householddata.getText().toString();
                diaochajielun = householdresult.getText().toString();

                if (TextUtils.isEmpty(diaocharenname)) {
                    Util.showToast(getApplicationContext(), "负责人不能为空");
                } else if (TextUtils.isEmpty(diaochadate)) {
                    Util.showToast(getApplicationContext(), "调查结论时间不能为空");
                } else if (TextUtils.isEmpty(diaochajielun)) {
                    Util.showToast(getApplicationContext(), "调查结论不能为空");
                } else {
                    saveBaseInfo();
                }


            }
        });


    }

    public void saveBaseInfo() {

//        ImageListData imageListData = new ImageListData();
//        for (int i = 0; i < selectMedia.size(); i++) {
//            imageListData.setFilePath_0(selectMedia.get(i).getPath());
//            imageListData.setAddDate_0("2018-01-31");
//            imageListData.setFileType_0(selectMedia.get(i).getType() + "");
//            imageListData.setFileName_0("selectMedia");
//            imageListData.setVideoUrl_0(selectMedia.get(i).getPath());
////            String temp_path = selectMedia.get(i).getPath() + ",";
////            filePath_0 = filePath_0 + temp_path;
////            String temp_name = "01" + ",";
////            fileName_0 = fileName_0 + temp_name;
////            String tempdata = "2018-01-31" + ",";
////            addDate_0 = addDate_0 + tempdata;
////            String temp_type = selectMedia.get(i).getType() + ",";
////            fileType_0 = fileType_0 + temp_type;
//            dataArrayList.add(imageListData);
//        }
        if (ftype.equals("1")) {
            gid = "";
            List<ImageListData> list = new ArrayList<>();
            list.addAll(dataArrayList);
            list.addAll(dataArrayListForVideo);
            Map<String, String> map2 = getRequestMapBase(list);
            mPresenter.main(map2);
        } else {
            dataArrayListx.clear();

            for (int i = 0; i < selectMediax.size(); i++) {
                ImageListData imgdata = new ImageListData();
                if (TextUtils.isEmpty(selectMediax.get(i).getFileId())) {
                    selectMediax.remove(i);
                    //chenxu
                    --i;
                }else {
                    imgdata.setFilePath_0(selectMediax.get(i).getUrl());
                    imgdata.setFileName_0(selectMediax.get(i).getFileId());
                    imgdata.setAddDate_0(diaochadate);
                    imgdata.setFileType_0("0");
                    imgdata.setVideoUrl_0("");
                    dataArrayListx.add(imgdata);
                }
            }
            for (int i = 0; i < selectVideoMedia.size(); i++) {
                ImageListData imgdata = new ImageListData();
                if (TextUtils.isEmpty(selectVideoMedia.get(i).getFileId())) {
                    selectVideoMedia.remove(i);
                    //chenxu
                    --i;
                }else {
                    imgdata.setFilePath_0(Constants.EMPTY_STRING);
//                    imgdata.setFilePath_0(selectVideoMedia.get(i).getUrl());
//                    LocalMedia lm = selectVideoMedia.get(i);
//                    String filePath = lm.getPath();
//                    String fileName = FileUtils.getFileName(filePath);
//                    imgdata.setFileName_0(fileName);
                    imgdata.setFileName_0(selectVideoMedia.get(i).getFileId());
                    imgdata.setAddDate_0(diaochadate);
                    imgdata.setFileType_0("1");
                    imgdata.setVideoUrl_0(selectVideoMedia.get(i).getUrl());
                    dataArrayListx.add(imgdata);
                }
            }
            dataArrayListx.addAll(dataArrayList);
            dataArrayListx.addAll(dataArrayListForVideo);
            List<ImageListData> list = new ArrayList<>();
            list.addAll(dataArrayListx);
            Map<String, String> map2 = getRequestMapBasex(list);
            mPresenter.main(map2);
        }


    }

    public Map<String, String> getRequestMapBase(List<ImageListData> dataList) {
        Map<String, String> map = ParametersFactory.saveBaseHouseInfo(this, gfamliyId, gid, diaochadate, diaocharenname, diaochajielun, fileSize, dataList,longitude,latitude,diaochaaddress,cardno);
        return map;
    }

    public Map<String, String> getRequestMapBasex(List<ImageListData> dataList) {
        Map<String, String> map = ParametersFactory.saveBaseHouseInfo(this, gfamliyId, gId, diaochadate, diaocharenname, diaochajielun, fileSize, dataList,longitude,latitude,diaochaaddress,cardno);
        return map;
    }

    public Map<String, String> getRequestMapx() {
        // gId = "5EE5B8D1-4FA3-43AE-A130-05B3F673FAFC";
        Map<String, String> map = ParametersFactory.getAddServeryData(this, gId);
        return map;
    }

    @Override
    protected void loadData() {
        if (ftype.equals("2")) {
            Map<String, String> map = getRequestMapx();
            mPresenter.getSocialDatas(map, false);
        }
    }

    private void submitVedio(final String path) {
        final Handler handler = new Handler(this.getMainLooper());
        final Runnable callBack = new Runnable() {
            public void run() {
                try {
                    //上传成功处理
                } catch (Exception ex) {
                }
            }
        };
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    File file = new File(path);
                    FileInputStream is = null;
                    // 获取文件大小
                    long length = file.length();
                    // 创建一个数据来保存文件数据
                    byte[] fileData = null;
                    try {
                        is = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 读取数据到byte数组中

                    int len = 0;
                    fileData = new byte[1000 * 1000 * 2];
                    //断点续传
                    while ((len = is.read(fileData)) != -1) {
                        temp = new ArrayList<>();
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
                        temp.add(byteArrayInputStream);
                        //这里是提交数组流到后台
//                        RegisterControlService.submitVedioSon(
//                                SubVedioViewActivity.this, temp, fInfos, subIdx);
//                        Map<String, Object> map = getRequestMap();
//                        mPresenter.saveVideo(map, false);
                        temp.clear();
                        byteArrayInputStream.close();
                    }
                    if (is != null)
                        is.close();
                } catch (Exception ex) {
                    System.out.print(ex.toString() + "dujq");
                    String a = ex + "";
                }
                handler.post(callBack);
            }
        };
        thread.start();
    }

    public Map<String, String> getRequestMap() {
        videoName = "5EE5B8D1-4FA3-43AE-A130-05B3F673FAFC";
//        Map<String, Object> map = ParametersFactory.saveTest(temp, videoName);
        Map<String, String> map = ParametersFactory.saveVideoData(this, videoName, byteArray);
        return map;
    }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .adddisplay(new DispalyInfoActivityModule(this))
                .inject(this);
    }


    private void removeDeletedImageItemInDataArrayList(String fileName){
        if (!StringUtils.isBlank(fileName)){
            for (int i = 0; i < dataArrayList.size(); ++i){
                ImageListData ild = dataArrayList.get(i);
                String imageName = ild.getFileName_0();
                if (fileName.equals(imageName)){
                    dataArrayList.remove(i);
                    --i;
                }
            }
        }
    }

    private void removeDeletedVideoItemInDataArrayList(String fileName){
        if (!StringUtils.isBlank(fileName)){
            for (int i = 0; i < dataArrayListForVideo.size(); ++i){
                ImageListData ild = dataArrayListForVideo.get(i);
                String videoName = ild.getFileName_0();
                if (fileName.equals(videoName)){
                    dataArrayListForVideo.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * 删除图片回调接口
     */

    private AddGridImageAdapter.onAddPicClickListener onAddPicClickListener = new AddGridImageAdapter.onAddPicClickListener() {
        @Override
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
                        themeStyle = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ 风格模式下 这里自己搭配颜色
                        previewColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.tab_color_true);
                    }
//                    selectMedia.clear();
//                    adapter.notifyDataSetChanged();
                    options = new FunctionOptions.Builder()
                            .setType(selectType) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //是否压缩
                            .setEnablePixelCompress(true) //是否启用像素压缩
                            .setEnableQualityCompress(true) //是否启质量压缩
                            .setMaxSelectNum(maxSelectNum) // 可选择图片的数量
                            .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                            .setSelectMode(selectMode) // 单选 or 多选
                            .setShowCamera(isShow) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                            .setEnablePreview(enablePreview) // 是否打开预览选项
                            .setPreviewVideo(isPreviewVideo) // 是否预览视频(播放) mode or 多选有效
                            .setCheckedBoxDrawable(checkedBoxDrawable)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                            .setRecordVideoSecond(Constants.RECORD_VIDEO_MAX_DURATION_IN_SECONDS) // 视频秒数
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
                            .setSelectMedia(new ArrayList<LocalMedia>()) // 已选图片，传入在次进去可选中，不能传入网络图片
//                            .setSelectMedia(selectMedia) // 已选图片，传入在次进去可选中，不能传入网络图片
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
                        PictureConfig.getInstance().init(options).startOpenCamera(AddImgVideoServeryActivity.this);
                    } else {
                        // 先初始化参数配置，在启动相册
                        PictureConfig.getInstance().init(options).openPhoto(AddImgVideoServeryActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // 删除图片
                    LocalMedia lm = selectMediax.get(position);
                    String deletedFileName = lm.getFileName();
                    removeDeletedImageItemInDataArrayList(deletedFileName);
                    if (ftype.equals("2")) {
                        selectMediax.remove(position);
                    } else {
                        // adapterImgs.remove(position);
                        selectMediax.remove(position);
                    }
                    adapter.notifyItemRemoved(position);

                    break;
            }
        }
    };
    /**
     * 删除图片回调接口
     */

    private AddVideoImageAdapter.onAddPicClickListener onAddVideoClickListener = new AddVideoImageAdapter.onAddPicClickListener() {
        @Override
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
                        themeStyle = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ 风格模式下 这里自己搭配颜色
                        previewColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(AddImgVideoServeryActivity.this, R.color.tab_color_true);
                    }

                    options = new FunctionOptions.Builder()
                            .setType(FunctionConfig.TYPE_VIDEO) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //是否压缩
                            .setEnablePixelCompress(true) //是否启用像素压缩
                            .setEnableQualityCompress(true) //是否启质量压缩
                            .setMaxSelectNum(1) // 可选择图片的数量
//                            .setMaxSelectNum(maxSelectNum) // 可选择图片的数量
                            .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                            .setSelectMode(FunctionConfig.MODE_SINGLE) // 单选 or 多选
//                            .setSelectMode(selectMode) // 单选 or 多选
                            .setShowCamera(isShow) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                            .setEnablePreview(enablePreview) // 是否打开预览选项
                            .setPreviewVideo(isPreviewVideo) // 是否预览视频(播放) mode or 多选有效
                            .setCheckedBoxDrawable(checkedBoxDrawable)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                            .setRecordVideoSecond(Constants.RECORD_VIDEO_MAX_DURATION_IN_SECONDS) // 视频秒数
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
                            .setSelectMedia(selectVideoMedia) // 已选图片，传入在次进去可选中，不能传入网络图片
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
                        PictureConfig.getInstance().init(options).startOpenCamera(AddImgVideoServeryActivity.this);
                    } else {
                        // 先初始化参数配置，在启动相册
                        PictureConfig.getInstance().init(options).openPhoto(AddImgVideoServeryActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // 删除图片
                    LocalMedia lm = selectVideoMedia.get(position);
                    String deletedFileName = lm.getFileName();
                    removeDeletedVideoItemInDataArrayList(deletedFileName);
                    selectVideoMedia.remove(position);
//                    dataArrayListForVideo.clear();//只有一个视频时这样处理
                    videoAdapter.notifyItemRemoved(position);
                    break;
            }
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

    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
//        选择图片
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
//            selectMedia.clear();
//            adapterImgs.clear();
//            selectMediax.clear();

            fileName = "";
            fileBase64 = "";
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getType() == 1) {
                    LocalMedia lm = resultList.get(i);
                    selectMedia.add(lm);
                    adapterImgs.add(lm);
                    selectMediax.add(lm);
                    // 选择图片上传 fileName fileBase64
                    String paths = (resultList.get(i).getCompressPath()).trim();
                    if (TextUtils.isEmpty(paths)) {
                        paths = resultList.get(i).getPath().trim();
                    }
                    String temp[] = paths.replaceAll("\\\\", "/").split("/");
                    String s0 = "";
                    if (temp.length > 1) {
                        String aFileName = temp[temp.length - 1];
                        lm.setFileName(aFileName);

                        s0 = aFileName + ";";
                        System.out.println(s0);
                    }
//                    lm.setFileId(s0);
                    fileName = fileName + s0;
                    String s1 = Base64Util.imageToBase64(paths) + ";";
                    fileBase64 = fileBase64 + s1;

                }
//                else if (resultList.get(i).getType() == 2) {
//                    selectVideoMedia.add(resultList.get(i));
//                }

            }
            Map<String, String> map = getImgRequestMap();
            mPresenter.savePic(map, false);

            Log.i("callBack_result", adapterImgs.size() + "");
            LocalMedia media = resultList.get(0);
            if (media.isCompressed()) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                String path = media.getCompressPath();
            } else {
                // 原图地址
                String path = media.getPath();
            }
//            if (adapterImgs != null) {
//                adapter.setList(adapterImgs);
//                adapter.notifyDataSetChanged();
//
//            }
            if (selectMediax != null) {
                adapter.setList(selectMediax);
                adapter.notifyDataSetChanged();
            }
//            if (selectVideoMedia != null) {
//                videoAdapter.setList(selectVideoMedia);
//                videoAdapter.notifyDataSetChanged();
//            }
        }

//        选择视频
        @Override
        public void onSelectSuccess(LocalMedia media) {
//            selectMedia.add(media);
//            adapterImgs.add(media);
//            adapter.setList(adapterImgs);
//            adapter.notifyDataSetChanged();


            String paths = Util.trimString(media.getCompressPath());
            if (TextUtils.isEmpty(paths)) {
                paths = Util.trimString(media.getPath());
            }
            String temp[] = paths.replaceAll("\\\\", "/").split("/");
            if (temp.length > 1) {
                String aFileName = temp[temp.length - 1];
                media.setFileName(aFileName);
            }


//            selectVideoMedia.clear();
//            selectVideoMedia.add(media);
            selectVideoMedia.add(media);
            videoAdapter.setList(selectVideoMedia);
            videoAdapter.notifyDataSetChanged();

            uploadVideoUsingRaw();
//            uploadVideoUsingRetrofit();

        }
    };

    public void uploadVideoUsingRaw(){
        if (ListUtils.getSize(selectVideoMedia)>0) {
//            LocalMedia lm = selectVideoMedia.get(0);
            LocalMedia lm = selectVideoMedia.get(selectVideoMedia.size()-1);
            String filePath = lm.getPath();
            String fileName = FileUtils.getFileName(filePath);
            File file = new File(filePath);
            if (file.exists()) {
                final UploadVideoManager uvm = UploadVideoManager.getInstance(this);
                uvm.setCallback(new UploadVideoManager.UploadVideoCallback() {
                    @Override
                    public void onUploadSuccess(GCAHttpResultBaseBean<VideoBackBean> result, String fileName) {
                        Util.hideGifProgressDialog(AddImgVideoServeryActivity.this);
                        handleUploadVideoSuccess(result, fileName);
                    }

                    @Override
                    public void onUploadFailure(String msg) {
                        Util.hideGifProgressDialog(AddImgVideoServeryActivity.this);
                        handleUploadVideoFailure(msg);
                    }
                });
                Util.showGifProgressDialog(this, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        uvm.cancelThread();
                    }
                });
                uvm.startUploadVideo(filePath);
            } else {
                Util.showToast(getApplicationContext(), "上传的视频不存在");
            }
        }
    }

    public void uploadVideoUsingRetrofit(){
        //            byte[] byteArray = Util.getBytesFromFilePath(media.getPath());
//            if (byteArray==null){
//                byteArray=new byte[0];
//            }
        String uploadVideoUrl = Version.HTTP_UPLOAD_VIDEO_URL;
//            RequestBody identity = Util.getRequestBodyFromText("identity");
//            RequestBody identity = Util.getRequestBodyFromText(gid);
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < selectVideoMedia.size(); ++i){
            LocalMedia lm = selectVideoMedia.get(i);
            String filePath = lm.getPath();
            String fileName = FileUtils.getFileName(filePath);
//                lm.setFileId(fileName);
            File file = new File(filePath);
            if (file.exists()) {
                RequestBody requestBody = Util.getRequestBodyFromVideo(file);
                String key = "uploadVideo\"; filename=\""+fileName;
                params.put(key, requestBody);
            }
        }
//            mPresenter.saveVideo(uploadVideoUrl, identity, "description",  false);
        mPresenter.saveVideo(uploadVideoUrl, params,  false);

//            MultipartBody.Part filePart = Util.getPartFromFilePath(media.getPath(), "uploadVideo");
//            mPresenter.saveVideo(uploadVideoUrl, filePart,  false);

    }


    public Map<String, String> getImgRequestMap() {
        Map<String, String> map = ParametersFactory.saveImageView(this, fileName, fileBase64);
        return map;
    }


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
                    adapterImgs = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                    if (adapterImgs != null) {
                        adapter.setList(adapterImgs);
                        adapter.notifyDataSetChanged();
                    }


                    selectVideoMedia = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                    if (selectVideoMedia != null) {
                        videoAdapter.setList(selectVideoMedia);
                        videoAdapter.notifyDataSetChanged();
                    }
                }

            }
        }
    }

    @Override
    public void onAddFindSuccess(HttpResultTestBean bean) {
        Util.showToast(getApplicationContext(), bean.getData());
        RxBus.getDefault().post(Constants.EVENT_TYPE_SOUND_SABESUCUSS, 2018);
        finish();
    }

    @Override
    public void onAddFindFailure(String message) {

    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<VideoBackBean> bean) {
        handleUploadVideoSuccess(bean, null);

    }

    private void handleUploadVideoSuccess(GCAHttpResultBaseBean<VideoBackBean> bean, String fileName) {
        Util.showToast(getApplicationContext(), "上传视频成功");
//        dataArrayListForVideo.clear();

        if (bean!=null && bean.getData()!=null) {
            VideoBackBean vbb = bean.getData();
            ImageListData imgdata = new ImageListData();
            imgdata.setFilePath_0(Constants.EMPTY_STRING);
            imgdata.setAddDate_0(vbb.getAddDate());
            if (!StringUtils.isBlank(fileName)){
                imgdata.setFileName_0(fileName);
            } else {
                imgdata.setFileName_0(vbb.getVideoName());
            }
            imgdata.setFileType_0("1");
            imgdata.setVideoUrl_0(vbb.getVideoPath());
//            imgdata.setVideoUrl_0(vbb.getVideoUrl());
            dataArrayListForVideo.add(imgdata);
//            dataArrayList.add(imgdata);
        }
    }


    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<VideoBackBean> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        handleUploadVideoFailure(message);

    }

    private void handleUploadVideoFailure(String msg) {
        Util.showToast(getApplicationContext(), "上传视频失败");
//        Util.showToast(getApplicationContext(), message);
    }


    @Override
    public void onAddpicSuccess(GCAHttpResultBaseBean<List<Uploadpic>> bean) {
        if (bean!=null && bean.getData()!=null && bean.getData().size() > 0) {
            Util.showToast(getApplicationContext(), "图片上传成功");
            for (int i = 0; i < bean.getData().size(); i++) {
                ImageListData imgdata = new ImageListData();
                imgdata.setFilePath_0(bean.getData().get(i).getFilePath());
                imgdata.setAddDate_0(bean.getData().get(i).getAddDate());
                imgdata.setFileName_0(bean.getData().get(i).getFileName());
                imgdata.setFileType_0("0");
                imgdata.setVideoUrl_0("");
                dataArrayList.add(imgdata);
            }
        }


    }

//    private void updateSelectedPictureFilename(List<Uploadpic> list){
//        if (!ListUtils.isEmpty(list)){
//            for (int i = 0; i < list.size(); ++i){
//                Uploadpic up = list.get(i);
//            }
//        }
//    }
//
//    private LocalMedia getLocalMediaFromFileName(String fileName){
//        if (StringUtils.isBlank(fileName)){
//            return null;
//        } else {
//            for (int i = 0; i < selectMediax.size(); ++i){
//                LocalMedia lm = selectMediax.get(i);
//            }
//        }
//    }

    @Override
    public void onLoadMorepicListSuccess(GCAHttpResultBaseBean<List<Uploadpic>> bean) {

    }

    @Override
    public void onAddpicFailure(String message) {
        Util.showToast(getApplicationContext(), "图片上传失败");

    }

    @Override
    public void onLoadListSuccessx(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean) {
        if (bean!=null && !ListUtils.isEmpty(bean.getData())) {
            int diaochelistsize = bean.getData().get(0).getDiaoChaFiles().size();
            for (int i = 0; i < diaochelistsize; i++) {
                int temps = 0;
                try {
                    temps = Integer.valueOf(bean.getData().get(0).getDiaoChaFiles().get(i).getFileType());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (temps == 0) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setUrl(bean.getData().get(0).getDiaoChaFiles().get(i).getFilePath());
                    //    localMedia.setPath(bean.getData().get(0).getDiaoChaFiles().get(i).getVideoUrl());
                    localMedia.setPath(bean.getData().get(0).getGoalIP());//
                    localMedia.setType(Integer.valueOf(bean.getData().get(0).getDiaoChaFiles().get(i).getFileType()));
                    int ss = localMedia.getType();
                    localMedia.setWebimg(1);
                    localMedia.setFileId(bean.getData().get(0).getDiaoChaFiles().get(i).getFileName());
                    localMedia.setFileName(bean.getData().get(0).getDiaoChaFiles().get(i).getFileName());
                    selectMediax.add(localMedia);
                } else if (temps == 1){
                    LocalMedia localMedia = new LocalMedia();
//                    localMedia.setUrl(bean.getData().get(0).getDiaoChaFiles().get(i).getFilePath());
                    localMedia.setUrl(bean.getData().get(0).getDiaoChaFiles().get(i).getVideoUrl());
                    localMedia.setPath(Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX);//
//                    localMedia.setPath(bean.getData().get(0).getGoalIP());//
                    localMedia.setType(Integer.valueOf(bean.getData().get(0).getDiaoChaFiles().get(i).getFileType()));
                    int ss = localMedia.getType();
                    localMedia.setWebimg(0);
                    localMedia.setFileId(bean.getData().get(0).getDiaoChaFiles().get(i).getFileName());
                    localMedia.setFileName(bean.getData().get(0).getDiaoChaFiles().get(i).getFileName());
                    selectVideoMedia.add(localMedia);

                }
            }
            adapter.setList(selectMediax);
            adapter.setgolIp(bean.getData().get(0).getGoalIP());
            adapter.notifyDataSetChanged();

            videoAdapter.setList(selectVideoMedia);
            videoAdapter.setgolIp(Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX);
//            videoAdapter.setgolIp(Version.HTTP_URL);
//            videoAdapter.setgolIp(bean.getData().get(0).getGoalIP());
            videoAdapter.notifyDataSetChanged();

            responsname.setText(bean.getData().get(0).getDiaoCha().getDiaoChaRenName());
            householddata.setText(bean.getData().get(0).getDiaoCha().getDiaoChaDate());
            householdresult.setText(bean.getData().get(0).getDiaoCha().getDiaoChaJieLun());
        }

    }

    @Override
    public void onLoadMoreListSuccessx(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean) {

    }

    @Override
    public void onLoadListFailurex(String message, boolean isLoadMore) {

    }

    private String getSignTimeString() {
        String s = Util.trimString(householddata.getText().toString());
        return s;
    }

    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    LatLonPoint latLonPoint;

    /**
     * 初始化定位并设置定位回调监听
     */
    private void getCurrentLocationLatLng() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

 /* //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景） 设置了场景就不用配置定位模式等
    option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
    if(null != locationClient){
        locationClient.setLocationOption(option);
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        locationClient.stopLocation();
        locationClient.startLocation();
    }*/
        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //只会使用网络定位
   /* mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);*/
        //只使用GPS进行定位
    /*mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);*/
        // 设置为单次定位 默认为false
    /*mLocationOption.setOnceLocation(true);*/
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。默认连续定位 切最低时间间隔为1000ms
        mLocationOption.setInterval(35000);
        //设置是否返回地址信息（默认返回地址信息）
    /*mLocationOption.setNeedAddress(true);*/
        //关闭缓存机制 默认开启 ，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存,不区分单次定位还是连续定位。GPS定位结果不会被缓存。
    /*mLocationOption.setLocationCacheEnable(false);*/
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    double currentLat = amapLocation.getLatitude();//获取纬度
                    double currentLon = amapLocation.getLongitude();//获取经度
                    latLonPoint = new LatLonPoint(currentLat, currentLon);  // latlng形式的
                /*currentLatLng = new LatLng(currentLat, currentLon);*/   //latlng形式的
                    //  String s = amapLocation.getAddress();
                    String s = amapLocation.getDistrict()+amapLocation.getRoad()+amapLocation.getStreetNum()+amapLocation.getDescription();
                    mapsite.setText(s);
                    mapsite.setText(s);
                    longitude = currentLon+"";
                    latitude = currentLat+"";
                    diaochaaddress=s;
                    amapLocation.getAccuracy();//获取精度信息
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }

    };
}
