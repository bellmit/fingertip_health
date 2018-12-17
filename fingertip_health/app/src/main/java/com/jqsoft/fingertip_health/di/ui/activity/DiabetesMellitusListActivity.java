package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HighBloodListActivityAdapter;
import com.jqsoft.fingertip_health.adapter.SocialAssistanceObjectAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.DiabetesMellitusListActivityContract;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.module.DiabetesMellitusListActivityModule;
import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.presenter.DiabetesMellitusListActivityresenter;
import com.jqsoft.fingertip_health.di.presenter.HighBloodListActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;


//高血压列表页
public class DiabetesMellitusListActivity extends AbstractActivity implements DiabetesMellitusListActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private int currentPage = 1;
    private int pageSize = Constants.HB_PAGE_SIZE;
    private EditText et_search;
    private String tempString, cardNo, name;
    private HighBloodListActivityAdapter mAdapter;
    RecyclerView recyclerView;
    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.check_socaildetail)
    LinearLayout check_socaildetail;
    @BindView(R.id.btn_reset)
    LinearLayout btn_reset;
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.lay_online_consultation_load_failure)
    View failureView;
    @BindView(R.id.query_btn)
    ImageView query_btn;
    @BindView(R.id.tv_add)
    ImageView tv_add;
    @BindView(R.id.bt_username_clear)
    Button bt_username_clear;
    private TextView tvFailureView;
    private TextView tv_search_send;
    private LinearLayout ll_back;
    private Boolean isRefresh = false;
    private final static int REQUEST_IMAGE = 100;
    private String picture = null;
    private Bitmap bitmap = null;
    @Inject
    DiabetesMellitusListActivityresenter  mPresenter;

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_socialassistanceobject;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        online_consultation_title.setText("糖尿病管理");
        et_search = (EditText) findViewById(R.id.et_search);
     //   tv_search_send = (TextView) findViewById(R.id.tv_search_send);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recyclerView = (RecyclerView) srl.findViewById(R.id.recyclerview);
        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });
        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);
        tv_add.setVisibility(View.GONE);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiabetesMellitusListActivity.this, DiabetesMellitusActivity.class);
                startActivity(intent);
            }
        });

        final BaseQuickAdapter<HighBloodListActivityBean, BaseViewHolder> mAdapter = new HighBloodListActivityAdapter(new ArrayList<HighBloodListActivityBean>(), getApplicationContext());
        this.mAdapter = (HighBloodListActivityAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Log.i(TAG, "输入文字中的状态，count是输入字符数");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
               // Log.i(TAG, "输入文本之前的状态");
            }

            @Override
            public void afterTextChanged(Editable s) {
               // Log.i(TAG, "输入文字后的状态");
                if(!TextUtils.isEmpty(s.toString())){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }

            }
        });
        bt_username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempString = et_search.getText().toString();
                if (tempString.length() < 18) {
                    name = tempString;
                } else {
                    cardNo = tempString;
                }
                if (!TextUtils.isEmpty(tempString)) {
                    onRefresh();
                } else {
                    Util.showToast(DiabetesMellitusListActivity.this, Constants.CHECK_DATA);
                }
            }
        });
        check_socaildetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempString = et_search.getText().toString();
                if (tempString.length() < 18) {
                    name = tempString;
                } else {
                    cardNo = tempString;
                }
                if (!TextUtils.isEmpty(tempString)) {
                    onRefresh();
                } else {
                    Util.showToast(DiabetesMellitusListActivity.this, Constants.CHECK_DATA);
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                onRefresh();

            }
        });

        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);

                HighBloodListActivityBean pb = mAdapter.getItem(position);


                Intent intent=new Intent(DiabetesMellitusListActivity.this,DiabetesMellitusActivity.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("data", pb);

                intent.putExtras(bundle);

                startActivity(intent);
//                Util.gotoActivityWithBundle(HighBloodListActivity.this, HighBloodActivity.class, bundle);
            }
        });
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(DiabetesMellitusListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(DiabetesMellitusListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });


    }

    public Map<String, String> getRequestMap() {
            String username ="";
            String no="";
            String idNo="";
            Map<String, String> map = ParametersFactory.getDiabetesDataList(this, tempString, tempString, idNo, currentPage,pageSize );
            return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addDiabetesMellitusListActivity(new DiabetesMellitusListActivityModule(this))
                .inject(this);
    }

    @Override
    public void onRefresh() {
        currentPage = Constants.HB_INITIAL_PAGE;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        Map<String, String> map = getRequestMap();
        mPresenter.getlist(map, false);
    }

    @Override
    public void onLoadMoreRequested() {
        ++currentPage;
        Map<String, String> map = getRequestMap();
        mPresenter.getlist(map, true);
        LogUtil.i("OnlineConsultationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true, bean,false);
    }

    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true, bean,true);
    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        if (isLoadMore) {
            if (currentPage > Constants.HB_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(this, message);

    }

    public int getListSizeFromResult(List<HighBloodListActivityBean> beanList) {
        if (beanList != null) {
            List<HighBloodListActivityBean> list = beanList;
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
    private void showNotificationSuccessOrFailureView(boolean success, HttpResultBaseBeanForFingertip<String> bean,boolean isloadmore){
        if (success) {
            if (bean!=null){
                String resultString = bean.getResult();
                try {
                    List<HighBloodListActivityBean> resultList = JSON.parseObject(resultString,
                            new TypeReference<List<HighBloodListActivityBean>>(){});
                    if (!ListUtils.isEmpty(resultList)) {
                        if (isloadmore){
                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.addData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
                            srl.setEnabled(true);
                            srl.setRefreshing(false);
                            setLoadMoreStatus(this.pageSize, listSize, true);

                        }else {

                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.setNewData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(resultList));
                            srl.setRefreshing(false);
                            setLoadMoreStatus(pageSize, listSize, true);
                        }






                    } else {
                        showRecyclerViewOrFailureView(true,true);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showRecyclerViewOrFailureView(false,true);
                }
            } else {
                showRecyclerViewOrFailureView(true,true);
            }
        } else {
            showRecyclerViewOrFailureView(false,true);
        }
    }
    public List<HighBloodListActivityBean> getListFromResult(HttpResultBaseBeanForFingertip<List<HighBloodListActivityBean>> beanList) {
        if (beanList != null) {
            List<HighBloodListActivityBean> list = beanList.getResult();
            return list;
        } else {
            return null;
        }
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_no_consultation);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_failure);
    }

    /**
     * select picture
     */
    private void selectImage() {
        MultiImageSelector.create(DiabetesMellitusListActivity.this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(DiabetesMellitusListActivity.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                Util.showGifProgressDialog(DiabetesMellitusListActivity.this);
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
                                    Util.hideGifProgressDialog(DiabetesMellitusListActivity.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(DiabetesMellitusListActivity.this);
                                    Toast.makeText(DiabetesMellitusListActivity.this, "识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(DiabetesMellitusListActivity.this);
        AlertDialog dialogInfo = builder.setTitle("识别成功")
                .setMessage(sb.toString())
                /*.setPositiveButton("复制号码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", result.getId());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(SocialAssistanceObject.this, "身份证号已复制到粘贴板", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })*/
                .setNegativeButton("取消", null)
                .create();
        dialogInfo.show();

        et_search.setText(result.getId());

    }

    /**
     * 获取压缩后的图片
     *
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

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
            if (options < 0) {
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                break;
            } else {
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
