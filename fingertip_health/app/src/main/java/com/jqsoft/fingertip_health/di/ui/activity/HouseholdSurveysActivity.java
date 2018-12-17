package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HouseApplying;
import com.jqsoft.fingertip_health.bean.HouseCheckingBean;
import com.jqsoft.fingertip_health.bean.HouseHoldSurveyBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HouseHoldServeyActivityContract;
import com.jqsoft.fingertip_health.di.module.HouseHoldServeyActivityModule;
import com.jqsoft.fingertip_health.di.presenter.HouseholdServeyActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.ApplyingFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.CheckingFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.util.Util;
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

/**
 * Created by Administrator on 2018/1/12.
 */
//入户调查
public class HouseholdSurveysActivity extends AbstractActivity implements HouseHoldServeyActivityContract.View {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.check_socaildetail)
    LinearLayout check_socaildetail;
    @BindView(R.id.btn_reset)
    LinearLayout btn_reset;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.query_btn)
    ImageView query_btn;
    private String tempString, cardNo, name;
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {Constants.TITLE_APPLICATION, Constants.TITLE_CHECKING};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private Boolean isRefresh = false;
    @Inject
    HouseholdServeyActivityPresenter mPresenter;
    private int pageIndex = 0;
    private String searchCondition, queryType;
    private List<HouseApplying> applyList = new ArrayList<>();
    private List<HouseCheckingBean> reviewList = new ArrayList<>();
    private final static int REQUEST_IMAGE = 100;
    private String picture = null;
    private Bitmap bitmap = null;
    private DaggerApplication application;
    private int a = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_household_surveys;
    }

    @Override
    protected void initData() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        application = (DaggerApplication) this.getApplication();
    }

    public void onRefresh() {
        try {
            String t = application.getapplyOrcheck();
            if (t.equals("1") || t.equals("2")) {
                queryType = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        applyList.clear();
        reviewList.clear();
        isRefresh = true;
        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());

        Map<String, String> map = ParametersFactory.getHouseholdData(this, name, searchCondition, queryType, pageIndex);
        mPresenter.main(map, false);


    }

    public Map<String, String> getRequestMap() {
        queryType = "";
        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());

        Map<String, String> map = ParametersFactory.getHouseholdData(this, name, searchCondition, queryType, pageIndex);
        return map;
    }

    public void onLoadMore() {

        ++pageIndex;
        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getHouseholdData(this, name, searchCondition, queryType, pageIndex);
        mPresenter.main(map, true);

    }

    @Override
    protected void initView() {
        try {
            if (application.getTableType().equals("1")) {
                online_consultation_title.setText("入户调查");
            } else {
                online_consultation_title.setText("民主评议");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i = 0; i < mTitles.length; ++i) {
            if (i == 0) {
                ApplyingFragment newAssessFragment = new ApplyingFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);


//                SignedResidentDirectoryFragment fragment = new SignedResidentDirectoryFragment();
//                mFragments.add(fragment);
//                IndexFragment indexFragment = new IndexFragment();
//                mFragments.add(indexFragment);
////                mFragments.add(new WeChatFragment());
            } else if (i == 1) {

                CheckingFragment newAssessFragment = new CheckingFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);
//                TownLevelMedicalInstitutionDirectoryFragment fragment = new TownLevelMedicalInstitutionDirectoryFragment();
//                mFragments.add(fragment);
//                QueryDataFragment moduleListFragment = new QueryDataFragment();
//                mFragments.add(moduleListFragment);
            } else {
                String title = mTitles[i];
                mFragments.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
            }
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

//        mDecorView = getWindow().getDecorView();
//        mViewPager = ViewFindUtils.find(mDecorView, R.id.viewPager);
        vpContent.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        vpContent.setAdapter(new HouseholdSurveysActivity.MyPagerAdapter(getSupportFragmentManager()));

//        mTabLayout = ViewFindUtils.find(mDecorView, R.id.ctl_head);

        initTabData();
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == 1) {
                    queryType = "apply";
                }
                tempString = et_search.getText().toString();
                if (tempString.length() < 18) {
                    searchCondition = tempString;
                } else {
                    searchCondition = tempString;
                }
                if (!TextUtils.isEmpty(tempString)) {
                    //  queryType ="apply";
                    onRefresh();
                } else {
                    Util.showToast(HouseholdSurveysActivity.this, Constants.CHECK_DATA);
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
                    Util.showToast(HouseholdSurveysActivity.this, Constants.CHECK_DATA);
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
//        if (mIndexSelectSubscription==null) {
//            registerindexselectsubscription();
//        }
//
//        int index = util.getworkbenchindexfromintent(getintent());
//        if (index>=constants.workbench_index&&index<=constants.workbench_mine){
//            switchtoindex(index);
//        }


//        //两位数
//        mTabLayout.showMsg(0, 55);
//        mTabLayout.setMsgMargin(0, -5, 5);
//
//        //三位数
//        mTabLayout.showMsg(1, 100);
//        mTabLayout.setMsgMargin(1, -5, 5);
//
//        //设置未读消息红点
//        mTabLayout.showDot(2);
//        MsgView rtv_2_2 = mTabLayout.getMsgView(2);
//        if (rtv_2_2 != null) {
//            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
//        }
//
//        //设置未读消息背景
//        mTabLayout.showMsg(3, 5);
//        mTabLayout.setMsgMargin(3, 0, 5);
//        MsgView rtv_2_3 = mTabLayout.getMsgView(3);
//        if (rtv_2_3 != null) {
//            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
//        }

//        mViewPager.setCurrentItem(0);
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(HouseholdSurveysActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(HouseholdSurveysActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });

    }

    private void initTabData() {
        mTabLayout.setTabData(mTabEntities);
//        mTabLayout.setTabData(mTabEntities, this, R.id.fl_content, mFragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpContent.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                a = 2;
                if (position == 0) {
                    queryType = "apply";
                } else {
                    queryType = "check";
                }

//                if (position == 0) {
//                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
////                    UnreadMsgUtils.show(mTabLayout.getMsgView(0), mRandom.nextInt(100) + 1);
//                }
            }
        });

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
                a = 2;
//                reassignToolbar(mFragments.get(position));
                if (position == 0) {
                    queryType = "apply";
                } else {
                    queryType = "check";
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//
//        vpContent.setCurrentItem(0);
    }

    @Override
    protected void loadData() {
        queryType = "";
        String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getHouseholdData(this, name, searchCondition, queryType, pageIndex);
        mPresenter.main(map, false);
    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addHouseholdActivity(new HouseHoldServeyActivityModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBean<HouseHoldSurveyBean> bean) {
        if (bean != null) {
            applyList.addAll(bean.getData().getApplyList());
            reviewList.addAll(bean.getData().getReviewList());
            ApplyingFragment alreadyReadFragment = getApplyingPosition(0);
            alreadyReadFragment.mAdapter.setEnableLoadMore(false);
            alreadyReadFragment.mAdapter.setNewData(applyList);
            alreadyReadFragment.endRefreshing();
            alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
            setAdapterMyNew(ListUtils.getSize(bean.getData().getApplyList()), ListUtils.getSize(bean.getData().getApplyList()));
//            setAdapterMyNew(ListUtils.getSize(bean.getData().getApplyList()), applyList.size());


            CheckingFragment newFragment = getCheckingFragmentPosition(1);
            newFragment.mAdapter.setNewData(reviewList);
            newFragment.endRefreshing();
            isRefresh = false;
            newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));
            setAdapterStatusNew(ListUtils.getSize(bean.getData().getReviewList()), ListUtils.getSize(bean.getData().getReviewList()));
//            setAdapterStatusNew(ListUtils.getSize(bean.getData().getReviewList()), reviewList.size());
        }

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<HouseHoldSurveyBean> bean) {
        if (bean != null) {
            applyList.addAll(bean.getData().getApplyList());
            reviewList.addAll(bean.getData().getReviewList());
            ApplyingFragment alreadyReadFragment = getApplyingPosition(0);
            alreadyReadFragment.mAdapter.setNewData(applyList);
            alreadyReadFragment.endRefreshing();
            alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
            setAdapterMyNew(ListUtils.getSize(bean.getData().getApplyList()), ListUtils.getSize(bean.getData().getApplyList()));
//            setAdapterMyNew(ListUtils.getSize(bean.getData().getApplyList()), applyList.size());


            CheckingFragment newFragment = getCheckingFragmentPosition(1);
            newFragment.mAdapter.setNewData(reviewList);
            newFragment.endRefreshing();
            newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));
            setAdapterStatusNew(ListUtils.getSize(bean.getData().getReviewList()), ListUtils.getSize(bean.getData().getReviewList()));
//            setAdapterStatusNew(ListUtils.getSize(bean.getData().getReviewList()), reviewList.size());
        }
    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public ApplyingFragment getApplyingPosition(int pos) {
        return (ApplyingFragment) mFragments.get(pos);
    }

    public CheckingFragment getCheckingFragmentPosition(int pos) {
        return (CheckingFragment) mFragments.get(pos);
    }

    public void setAdapterStatusNew(int newSize, int pageSize) {

        CheckingFragment fragment = (CheckingFragment) mFragments.get(1);
        fragment.setLoadMoreStatusNew(newSize, pageSize, false);

    }

    public void setAdapterMyNew(int newSize, int pageSize) {

        ApplyingFragment fragment = (ApplyingFragment) mFragments.get(0);
        fragment.setLoadMoreStatusNew(newSize, pageSize, false);

    }

    /**
     * select picture
     */
    private void selectImage() {
        MultiImageSelector.create(HouseholdSurveysActivity.this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(HouseholdSurveysActivity.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                Util.showGifProgressDialog(HouseholdSurveysActivity.this);
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
                                    Util.hideGifProgressDialog(HouseholdSurveysActivity.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(HouseholdSurveysActivity.this);
                                    Toast.makeText(HouseholdSurveysActivity.this, "识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(HouseholdSurveysActivity.this);
        AlertDialog dialogInfo = builder.setTitle("识别成功")
                .setMessage(sb.toString())
                .setPositiveButton("复制号码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", result.getId());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(HouseholdSurveysActivity.this, "身份证号已复制到粘贴板", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialogInfo.show();

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
