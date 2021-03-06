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
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment1;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment2;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.entity.TabEntity;


import java.util.ArrayList;

import butterknife.BindView;


public class HighBloodActivity extends AbstractActivity{


//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_head)
    SlidingTabLayout mTabLayout;
    private  String code,titlename;
    TextView tvFailureView;
    TextView title_bar_title_textview;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    RelativeLayout titleLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int type;
    HighBloodListActivityBean highBloodListActivityBean;
    HBPGuanLi_PersonInfo hbpGuanLi_personInfo;

    private String[] mTitles = {"???????????????", "??????????????????","????????????,??????"};
    private int[] mIconUnselectIds = {
            R.mipmap.detpake, R.mipmap.detpake,R.mipmap.detpake};
    private int[] mIconSelectIds = {
            R.mipmap.detpake, R.mipmap.detpake, R.mipmap.detpake};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    HBFragment1 hbFragment1;
    HBFragment2 hbFragment2;
    HBFragment3 hbFragment3;
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

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        title_bar_left_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            hbpGuanLi_personInfo = (HBPGuanLi_PersonInfo) bundle.get("HBPGuanLi_PersonInfo");
//            iReadCardState = (String) bundle.get("iReadCardState");
//        }

        hbpGuanLi_personInfo=new HBPGuanLi_PersonInfo("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
//        titleLayout.setVisibility(View.GONE);
        HasMine();
//        dismissrogressBar();
        getCurrentLocationLatLng();
    }

    @Override
    protected void initView() {

        mTabLayout=(SlidingTabLayout)findViewById(R.id.ctl_head);
        mTabLayout.setTextUnselectColor(getResources().getColor(R.color.black));
        vpContent=(ViewPager)findViewById(R.id.vp_content);

    }

    @Override
    protected void loadData() {

    }

    public HighBloodListActivityBean getHighBloodListActivityBean(){
        return highBloodListActivityBean;
    }
    public ViewPager getVpContent(){
        return vpContent;
}
    public SlidingTabLayout getmTabLayout (){
    return mTabLayout;
}
    public HBFragment1 getHbFragment1(){
    return hbFragment1;
}
    public  HBFragment2 getHbFragment2(){return  hbFragment2;}
    public  HBFragment3 getHbFragment3(){return  hbFragment3;}
    public HBPGuanLi_PersonInfo getHbpGuanLi_personInfo(){return  hbpGuanLi_personInfo;}
    public String getiReadCardState(){
        return iReadCardState;
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

                hbFragment1 = new HBFragment1();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
//                helpCollectionFragment.setArguments(args);
                mFragments.add(hbFragment1);


            } else if (i==1) {
                hbFragment2 = new HBFragment2();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
//                centerCollectionFragment.setArguments(args);
                mFragments.add(hbFragment2);
            }else  if(i==2){
                hbFragment3 = new HBFragment3();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY,Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
//                jzzCollectionFragment.setArguments(args);
                mFragments.add(hbFragment3);

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

        //???????????????????????????????????????
        switch (requestCode) {
            case 1:

                //?????????????????????????????????????????????????????????????????????????????????
                switch (resultCode) {
                    case 0:
                        //?????????

                        break;
                    case 1:
                        //?????????
                        break;
                }
                break;

        }
    }


    //??????AMapLocationClient?????????
    AMapLocationClient mLocationClient = null;
    //??????AMapLocationClientOption??????
    public AMapLocationClientOption mLocationOption = null;
    LatLonPoint latLonPoint;

    /**
     * ??????????????????????????????????????????
     */
    private void getCurrentLocationLatLng() {
        //???????????????
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //????????????????????????
        mLocationClient.setLocationListener(mLocationListener);
        //?????????AMapLocationClientOption??????
        mLocationOption = new AMapLocationClientOption();

 /* //????????????????????????????????????????????????????????????????????????????????????????????? ?????????????????????????????????????????????
    option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
    if(null != locationClient){
        locationClient.setLocationOption(option);
        //???????????????????????????????????????stop????????????start???????????????????????????
        locationClient.stopLocation();
        locationClient.startLocation();
    }*/
        // ???????????????????????????GPS??????,???????????????????????????????????????,?????????????????????????????????
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //????????????????????????
   /* mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);*/
        //?????????GPS????????????
    /*mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);*/
        // ????????????????????? ?????????false
    /*mLocationOption.setOnceLocation(true);*/
        //??????????????????,????????????,?????????2000ms?????????1000ms????????????????????? ????????????????????????1000ms
        mLocationOption.setInterval(35000);
        //????????????????????????????????????????????????????????????
    /*mLocationOption.setNeedAddress(true);*/
        //?????????????????? ???????????? ?????????????????????????????????????????????????????????????????????????????????????????????,??????????????????????????????????????????GPS??????????????????????????????
    /*mLocationOption.setLocationCacheEnable(false);*/
        //??????????????????????????????????????????
        mLocationClient.setLocationOption(mLocationOption);
        //????????????
        mLocationClient.startLocation();
    }

    /**
     * ?????????????????????
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //?????????????????????????????????????????????
                    amapLocation.getLocationType();//??????????????????????????????????????????????????????????????????????????????
                    double currentLat = amapLocation.getLatitude();//????????????
                    double currentLon = amapLocation.getLongitude();//????????????
                    latLonPoint = new LatLonPoint(currentLat, currentLon);  // latlng?????????
                /*currentLatLng = new LatLng(currentLat, currentLon);*/   //latlng?????????
                    //  String s = amapLocation.getAddress();
                    sUpdateUserCode = currentLon+","+currentLat;
                    amapLocation.getAccuracy();//??????????????????
                } else {
                    //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }

    };
}
