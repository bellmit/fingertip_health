package com.jqsoft.fingertip_health.di.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.DMFragment1;
import com.jqsoft.fingertip_health.di.ui.fragment.DMFragment2;
import com.jqsoft.fingertip_health.di.ui.fragment.DMFragment3;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment1;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment2;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;


public class DiabetesMellitusActivity extends AbstractActivity {


//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_head)
    SlidingTabLayout mTabLayout;
    private  String code,titlename;
    TextView tvFailureView;
    TextView title_bar_title_textview;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.sign_service_assess_title)
     TextView sign_service_assess_title;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    RelativeLayout titleLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int type;

    HBPGuanLi_PersonInfo hbpGuanLi_personInfo;
    HighBloodListActivityBean highBloodListActivityBean;
    private String[] mTitles = {"症状、体征", "生活方式指导","用药情况,转诊"};
    private int[] mIconUnselectIds = {
            R.mipmap.detpake, R.mipmap.detpake,R.mipmap.detpake};
    private int[] mIconSelectIds = {
            R.mipmap.detpake, R.mipmap.detpake, R.mipmap.detpake};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    DMFragment1 dmFragment1;
    DMFragment2 dmFragment2;
    DMFragment3 dmFragment3;
    private  boolean isRefresh=false;
    private  String iReadCardState,sUpdateUserCode;
    private  TextView area_select_title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_highblood;
    }

    public Toolbar gettopview(){
        return toolbar;
    }


    @Override
    protected void initData() {
        sign_service_assess_title.setText("糖尿病患者随访服务记录表");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            highBloodListActivityBean = (HighBloodListActivityBean) bundle.get("data");

        }


        hbpGuanLi_personInfo=new HBPGuanLi_PersonInfo("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
//        titleLayout.setVisibility(View.GONE);
        HasMine();
//        dismissrogressBar();
        getCurrentLocationLatLng();
    }

    @Override
    protected void initView() {
//        });

        mTabLayout=(SlidingTabLayout)findViewById(R.id.ctl_head);
        mTabLayout.setTextUnselectColor(getResources().getColor(R.color.black));
        vpContent=(ViewPager)findViewById(R.id.vp_content);
//        area_select_title=(TextView)findViewById(R.id.area_select_title);
//        area_select_title.setText("高血压患者随访记录表");
//        titleLayout=(RelativeLayout)findViewById(R.id.titleLayout);
//        title_bar_title_textview=(TextView)findViewById(R.id.title_bar_title_textview);
//        title_bar_title_textview.setText("高血压患者随访记录表");
    }

    @Override
    protected void loadData() {

    }


    public ViewPager getVpContent(){
        return vpContent;
}
    public SlidingTabLayout getmTabLayout (){
    return mTabLayout;
}
    public DMFragment1 getDMFragment1(){
    return dmFragment1;
}
    public  DMFragment2 getDMFragment2(){return  dmFragment2;}
    public  DMFragment3 getDMFragment3(){return  dmFragment3;}
    public HBPGuanLi_PersonInfo getHbpGuanLi_personInfo(){return  hbpGuanLi_personInfo;}
    public String getiReadCardState(){
        return iReadCardState;
    }
    public HighBloodListActivityBean getHighBloodListActivityBean(){
        return highBloodListActivityBean;
    }
    public String getsUpdateUserCode(){
        return sUpdateUserCode;
    }

    public int getColorPrimary(){
        TypedValue typedValue = new  TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    private void HasMine(){

        for (int i = 0; i < mTitles.length; ++i){
            if (i==0){

                dmFragment1 = new DMFragment1();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
//                helpCollectionFragment.setArguments(args);
                mFragments.add(dmFragment1);


            } else if (i==1) {
                dmFragment2 = new DMFragment2();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
//                centerCollectionFragment.setArguments(args);
                mFragments.add(dmFragment2);
            }else  if(i==2){
                dmFragment3 = new DMFragment3();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY,Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
//                jzzCollectionFragment.setArguments(args);
                mFragments.add(dmFragment3);

            }
            else {
                String title = mTitles[i];
//                mFragments.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
            }
        }
//        mTabLayout.setIconVisible(false);
//
//
//        mTabLayout.setTextUnselectColor((getResources().getColor(R.color.hbblue)));

//        mTabLayout.setIndicatorColor(getColorPrimary());
//        mTabLayout.setTextSelectColor(getResources().getColor(R.color.colorTheme));
//        mTabLayout.setTextsize(15);
//        mTabLayout.setIndicatorColor(getResources().getColor(R.color.colorTheme));


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTextUnselectColor(getResources().getColor(R.color.white));
        vpContent.setOffscreenPageLimit(5);
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        initTabData();



    }

    private void initTabData() {
        mTabLayout.setViewPager(vpContent);
//        mTabLayout.setTabData(mTitles);
//        mTabLayout.setTextUnselectColor(getResources().getColor(R.color.hbblue));
//        mTabLayout.setDividerColor(getResources().getColor(R.color.hbblue));

//        mTabLayout.set
//        mTabLayout.setTabData(mTitles);
//        mTabLayout.setTabData(mTabEntities, this, R.id.fl_content, mFragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpContent.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
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
//                reassignToolbar(mFragments.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
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




    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //先判断是哪个页面返回过来的
        switch (requestCode) {
            case 1:

                //再判断返回过来的情况，是成功还是失败还是其它的什么……
                switch (resultCode) {
                    case 0:
                        //成功了

                        break;
                    case 1:
                        //失败了
                        break;
                }
                break;

        }
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
                    sUpdateUserCode = currentLon+","+currentLat;
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
