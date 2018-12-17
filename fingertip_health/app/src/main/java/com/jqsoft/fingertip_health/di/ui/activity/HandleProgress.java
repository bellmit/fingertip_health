package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.ProgressBean;
import com.jqsoft.fingertip_health.bean.SocailHistoryDetailsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HandleProgressContract;
import com.jqsoft.fingertip_health.di.module.HandleProgressModule;
import com.jqsoft.fingertip_health.di.presenter.HandleProgressPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;

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
 * Created by Jerry on 2018/1/8.
 */

public class HandleProgress extends AbstractActivity implements HandleProgressContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.online_consultation_title)
    TextView title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.et_search_his)
    TextView et_search_his;
    @BindView(R.id.text_his)
    TextView text_his;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.check_socaildetail)
    LinearLayout check_socaildetail;
    @BindView(R.id.reset_btn)
    LinearLayout reset_btn;
    private String batchCard, cardNoOrName;
    private String pageType;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.query_btn)
    ImageView query_btn;
    private final static int REQUEST_IMAGE = 200;
    private String picture = null;
    private Bitmap bitmap = null;
    @Inject
    HandleProgressPresenter mPresenter;
    private ArrayList<ProgressBean> progresslist = new ArrayList<>();
    private ArrayList<SocailHistoryDetailsBean> progressHisdata = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handleprogress;
    }

    @Override
    protected void initData() {

    }

    public Map<String, String> getRequestMap() {
        String username = Identity.getUsername();
        Map<String, String> map = ParametersFactory.getprogressList(this, batchCard);
        return map;
    }

    public Map<String, String> getRequstMapHis() {
        Map<String, String> map = ParametersFactory.getSocialHisList(this, cardNoOrName);
        return map;
    }

    @Override
    protected void initView() {
        pageType = getIntent().getStringExtra("pageType");
        if (pageType.equals("1")) {
            title.setText("办理进度");
        } else {
            title.setText("救助历史");
            et_search.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            et_search_his.setVisibility(View.VISIBLE);
            text_his.setVisibility(View.GONE);
        }

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageType.equals("1")) {
                    batchCard = et_search.getText().toString();
                     //batchCard = "20171120064133362";
                    if (!TextUtils.isEmpty(batchCard)) {
                        Map<String, String> map = getRequestMap();
                        mPresenter.getHandleprogress(map, false);

                    } else {
                        Util.showToast(getApplicationContext(), "受理编号或者身份证号不能为空");
                    }
                } else {
                    cardNoOrName = et_search_his.getText().toString();
                     //cardNoOrName = "340323196501215816";
                    if (!TextUtils.isEmpty(cardNoOrName)) {
                        Map<String, String> map = getRequstMapHis();
                        mPresenter.getSocialhistory(map, false);

                    } else {
                        Util.showToast(getApplicationContext(), "姓名或者身份证号不能为空");
                    }
                }


            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageType.equals("1")) {
                    et_search.setText("");
                } else {
                    et_search_his.setText("");
                }
            }
        });
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(HandleProgress.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(HandleProgress.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addhandleprogress(new HandleProgressModule(this))
                .inject(this);
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<ProgressBean>> bean) {
        if (bean.getData().size() > 0) {
            progresslist.clear();
            progresslist.addAll(bean.getData());
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("progeressdata", progresslist);
            if(progresslist.size()>0) {
                intent.setClass(HandleProgress.this, ActivityProgress.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Util.showToast(getApplicationContext(), "此人暂时无办理进度");
            }
            //  Util.gotoActivityWithBundle(getApplicationContext(), ActivityProgress.class, bundle);
        } else {
            Util.showToast(getApplicationContext(), "此人暂时无办理进度");
        }

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<ProgressBean>> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        Util.showToast(getApplicationContext(), message);
    }

    @Override
    public void onLoadHistoryDataSuccess(GCAHttpResultBaseBean<List<SocailHistoryDetailsBean>> bean) {
        if (bean.getData().size() > 0) {
            progressHisdata.clear();
            progressHisdata.addAll(bean.getData());
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("progressHisdata", progressHisdata);
            if(progressHisdata.size()>0) {
                intent.setClass(HandleProgress.this, SocialHistoryPageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Util.showToast(getApplicationContext(), "此人暂时无救助历史");
            }
        } else {
            Util.showToast(getApplicationContext(), "此人暂时无救助历史");
        }

    }

    @Override
    public void onLoadHisdataFailure(String message, boolean isLoadMore) {
        Util.showToast(getApplicationContext(), message);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    /**
     * select picture
     */
    private void selectImage() {
        MultiImageSelector.create(HandleProgress.this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(HandleProgress.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                Util.showGifProgressDialog(HandleProgress.this);
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                if (path != null && path.size() > 0) {
                    picture = path.get(0);
//                    onSelected();
                    bitmap = getImage(picture);
                    //   imageView.setImageBitmap(bitmap);

                    TecentHttpUtil.uploadIdCard(BitMapUtils.bitmapToBase64(bitmap), "0", new TecentHttpUtil.SimpleCallBack() {
                        @Override
                        public void Succ(String res) {
                            IdentifyResult result = new Gson().fromJson(res, IdentifyResult.class);
                            if (result != null) {
                                if (result.getErrorcode() == 0) {
                                    // 识别成功
                                    Util.hideGifProgressDialog(HandleProgress.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(HandleProgress.this);
                                    Toast.makeText(HandleProgress.this, "识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
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

                        }
                    });


                }
            }
        }
    }

    /**
     * 显示对话框
     *
     * @param result
     */
    private void showDialogInfo(final IdentifyResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名：" + result.getName() + "\n");
        sb.append("性别：" + result.getSex() + "\t" + "民族：" + result.getNation() + "\n");
        sb.append("出生：" + result.getBirth() + "\n");
        sb.append("住址：" + result.getAddress() + "\n" + "\n");
        sb.append("公民身份号码：" + result.getId() + "\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(HandleProgress.this);
        AlertDialog dialogInfo = builder.setTitle("识别成功")
                .setMessage(sb.toString())
                .setPositiveButton("复制号码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", result.getId());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(HandleProgress.this, "身份证号已复制到粘贴板", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialogInfo.show();

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

}
