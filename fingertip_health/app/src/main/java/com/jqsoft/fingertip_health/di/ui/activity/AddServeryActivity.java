package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GridImageHouseHoldAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HouseHoldeBackBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AddServeryActivityContract;
import com.jqsoft.fingertip_health.di.module.AddServeryActivityModule;
import com.jqsoft.fingertip_health.di.presenter.AddServeryPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManager;
import com.jqsoft.fingertip_health.util.Util;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/24.
 */

// 入户调查显示页面
public class AddServeryActivity extends AbstractActivity implements AddServeryActivityContract.View {
    private int maxSelectNum = 7;// 图片最大可选数量
    private GridImageHouseHoldAdapter adapter;
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
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.responsname)
    TextView responsname;
    @BindView(R.id.householddata)
    TextView householddata;
    @BindView(R.id.householdresult)
    TextView householdresult;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
//    @BindView(R.id.save_btn)
//    LinearLayout save_btn;
    @BindView(R.id.textsp)
    TextView textsp;
    @BindView(R.id.recyclervideo)
    RecyclerView recyclervideo;
    private String gId;
    @Inject
    AddServeryPresenter mPresenter;
    @BindView(R.id.btn_zancun)
    RoundTextView btn_zancun;
    @Override

    protected int getLayoutId() {
        return R.layout.activity_addhouse_layout;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        gId = bundle.getString("gId");//读出数据
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("入户调查");
        btn_zancun.setVisibility(View.GONE);
//        textsp.setVisibility(View.GONE);
//        recyclervideo.setVisibility(View.GONE);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final FullyGridLayoutManager manager = new FullyGridLayoutManager(AddServeryActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageHouseHoldAdapter(AddServeryActivity.this, onAddPicClickListener);
        adapter.setList(selectMedia);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new GridImageHouseHoldAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureConfig.getInstance().externalPicturePreview(AddServeryActivity.this, position, selectMedia);
            }
        });
    }

    @Override
    protected void loadData() {
        Map<String, String> map = getRequestMap();
        mPresenter.getSocialDatas(map, false);
    }

    public Map<String, String> getRequestMap() {
        // gId = "5EE5B8D1-4FA3-43AE-A130-05B3F673FAFC";
        Map<String, String> map = ParametersFactory.getAddServeryData(this, gId);
        return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addServeryactivity(new AddServeryActivityModule(this))
                .inject(this);
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean) {
        int diaochelistsize = bean.getData().get(0).getDiaoChaFiles().size();
        for (int i = 0; i < diaochelistsize; i++) {
            int temps = 0;
            try {
                temps = Integer.valueOf(bean.getData().get(0).getDiaoChaFiles().get(i).getFileType());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if(temps==0) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setUrl(bean.getData().get(0).getDiaoChaFiles().get(i).getFilePath());
            //    localMedia.setPath(bean.getData().get(0).getDiaoChaFiles().get(i).getVideoUrl());
                localMedia.setPath(bean.getData().get(i).getGoalIP());
                localMedia.setType(Integer.valueOf(bean.getData().get(0).getDiaoChaFiles().get(i).getFileType()));
                int ss = localMedia.getType();
                localMedia.setFileId(bean.getData().get(0).getDiaoChaFiles().get(i).getFileName());
                selectMedia.add(localMedia);
            }
        }
        adapter.setList(selectMedia);
        adapter.setgolIp(bean.getData().get(0).getGoalIP());
        adapter.notifyDataSetChanged();
        responsname.setText(bean.getData().get(0).getDiaoCha().getDiaoChaRenName());
        householddata.setText(bean.getData().get(0).getDiaoCha().getDiaoChaDate());
        householdresult.setText(bean.getData().get(0).getDiaoCha().getDiaoChaJieLun());

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        Util.showToast(getApplicationContext(), message);

    }

    /**
     * 删除图片回调接口
     */

    private GridImageHouseHoldAdapter.onAddPicClickListener onAddPicClickListener = new GridImageHouseHoldAdapter.onAddPicClickListener() {
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
                        themeStyle = ContextCompat.getColor(AddServeryActivity.this, R.color.blue);
                    } else {
                        themeStyle = ContextCompat.getColor(AddServeryActivity.this, R.color.bar_grey);
                    }

                    if (selectImageType) {
                        checkedBoxDrawable = R.drawable.select_cb;
                    } else {
                        checkedBoxDrawable = 0;
                    }

                    if (isCheckNumMode) {
                        // QQ 风格模式下 这里自己搭配颜色
                        previewColor = ContextCompat.getColor(AddServeryActivity.this, R.color.blue);
                        completeColor = ContextCompat.getColor(AddServeryActivity.this, R.color.blue);
                    } else {
                        previewColor = ContextCompat.getColor(AddServeryActivity.this, R.color.tab_color_true);
                        completeColor = ContextCompat.getColor(AddServeryActivity.this, R.color.tab_color_true);
                    }

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
                        PictureConfig.getInstance().init(options).startOpenCamera(AddServeryActivity.this);
                    } else {
                        // 先初始化参数配置，在启动相册
                        PictureConfig.getInstance().init(options).openPhoto(AddServeryActivity.this, resultCallback);
                    }
                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
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
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
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
            adapter.setList(selectMedia);
            adapter.notifyDataSetChanged();
        }
    };

}
