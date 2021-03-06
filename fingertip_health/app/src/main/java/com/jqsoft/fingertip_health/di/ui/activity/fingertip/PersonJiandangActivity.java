package com.jqsoft.fingertip_health.di.ui.activity.fingertip;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.PersonChanBean;
import com.jqsoft.fingertip_health.di.contract.PersonJiandangListActivityContract;
import com.jqsoft.fingertip_health.di.module.PersonJiandangListActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PersonJiandangListActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;


import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.PersonFragment1;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.PersonFragment2;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.PersonFragment3;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.SelectAddressPop;
import com.jqsoft.fingertip_health.view.area.SelectAddresstipPop;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class PersonJiandangActivity extends AbstractActivity implements PersonJiandangListActivityContract.View{



    @BindView(R.id.ctl_head)
    SlidingTabLayout mTabLayout;
    private  String code,titlename;
    TextView tvFailureView;
    TextView title_bar_title_textview;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RelativeLayout titleLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int type;

    HBPGuanLi_PersonInfo hbpGuanLi_personInfo;

    private String[] mTitles = {"????????????", "????????????","????????????"};
    private int[] mIconUnselectIds = {
            R.mipmap.detpake, R.mipmap.detpake,R.mipmap.detpake};
    private int[] mIconSelectIds = {
            R.mipmap.detpake, R.mipmap.detpake, R.mipmap.detpake};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    PersonFragment1 hbFragment1;
    PersonFragment2 hbFragment2;
    PersonFragment3 hbFragment3;
    private  boolean isRefresh=false;
    private  String iReadCardState,sUpdateUserCode;
    private  TextView area_select_title;

    private LinearLayout ll_back;
    private String flag,id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_jiandang;
    }


    public Toolbar gettopview(){
        return toolbar;
    }

    @Inject
    PersonJiandangListActivityPresenter mPresenter;


    @Override
    protected void initData() {
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
//
//        hbpGuanLi_personInfo=new HBPGuanLi_PersonInfo("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
//        titleLayout.setVisibility(View.GONE);
        HasMine();
//        dismissrogressBar();


    }

    @Override
    protected void initView() {
//        });

        mTabLayout=(SlidingTabLayout)findViewById(R.id.ctl_head);
        mTabLayout.setTextUnselectColor(getResources().getColor(R.color.black));
        vpContent=(ViewPager)findViewById(R.id.vp_content);
        ll_back=(LinearLayout)findViewById(R.id.ll_back);
//        area_select_title=(TextView)findViewById(R.id.area_select_title);
//        area_select_title.setText("??????????????????????????????");
//        titleLayout=(RelativeLayout)findViewById(R.id.titleLayout);
//        title_bar_title_textview=(TextView)findViewById(R.id.title_bar_title_textview);
//        title_bar_title_textview.setText("??????????????????????????????");

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        flag=getIntent().getStringExtra("flag");
        id=getIntent().getStringExtra("id");
//        id="153675773100000000";
        if(flag.equals("0")){

        }else {
            Map<String, String> map = ParametersFactory.getPersonData2(this, id );


            mPresenter.getDatas2(map, false);
        }
    }


    public ViewPager getVpContent(){
        return vpContent;
}
    public SlidingTabLayout getmTabLayout (){
    return mTabLayout;
}
    public PersonFragment1 getHbFragment1(){
    return hbFragment1;
}
    public  PersonFragment2 getHbFragment2(){return  hbFragment2;}
    public  PersonFragment3 getHbFragment3(){return  hbFragment3;}
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

                hbFragment1 = new PersonFragment1();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
//                helpCollectionFragment.setArguments(args);
                mFragments.add(hbFragment1);


            } else if (i==1) {
                hbFragment2 = new PersonFragment2();
//                Bundle args = new Bundle();
//                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
//                centerCollectionFragment.setArguments(args);
                mFragments.add(hbFragment2);
            }else  if(i==2){
                hbFragment3 = new PersonFragment3();
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

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {

    }

    @Override
    public void onLoadListChakanSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true, bean,false);

    }

    @Override
    public void onLoadListChakanFailure(String message, boolean isLoadMore) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
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


    public interface SelectAddresFinish{
        void finish(String provinceCode, String cityCode, String areaCode,String areaCode4,String areaCode5,String areaCode6,String addres);
    }

    private String provinceCode ;
    private String cityCode;
    private String areaCode;
    private String areaCode4;
    private String areaCode5;
    private String areaCode6;


    public void setpop(){


    }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addPersonJiandangActivity(new PersonJiandangListActivityModule(this))
                .inject(this);
    }


    private PersonChanBean personChanBean=null;

    private void showNotificationSuccessOrFailureView(boolean success, HttpResultBaseBeanForFingertip<String> bean,boolean isloadmore){
        if (success) {
            if (bean!=null){
                String resultString = bean.getResult();
                try {
                    PersonChanBean resultList = JSON.parseObject(resultString,
                            new TypeReference<PersonChanBean>(){});
                    if (resultList!=null) {
                        personChanBean=resultList;

                        hbFragment1.setchakan();
                        hbFragment2.setchakan2(personChanBean);
                        hbFragment3.setchakan3(personChanBean);

                    } else {
//                        showRecyclerViewOrFailureView(true,true);
                        Toast.makeText(getApplicationContext(),bean.getErrorMsg(),Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    showRecyclerViewOrFailureView(false,true);
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            } else {
//                showRecyclerViewOrFailureView(true,true);
                Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_LONG).show();
            }
        } else {
//            showRecyclerViewOrFailureView(false,true);
            Toast.makeText(getApplicationContext(),"????????????????????????",Toast.LENGTH_LONG).show();
        }
    }

    public PersonChanBean getchakan(){
        return  personChanBean;
    }

    public String getflag(){
        return flag;
    }


//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            //???isShouldHideInput(v, ev)???true???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//            if (isShouldHideInput(v, ev)) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    //??????Editext??????????????????????????????
////                    mEdtFind.clearFocus();
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // ????????????????????????????????????????????????TouchEvent???
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
//
//    public  boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] leftTop = { 0, 0 };
//            //????????????????????????location??????
//            v.getLocationInWindow(leftTop);
//            int left = leftTop[0];
//            int top = leftTop[1];
//            int bottom = top + v.getHeight();
//            int right = left + v.getWidth();
//            if (event.getX() > left && event.getX() < right
//                    && event.getY() > top && event.getY() < bottom) {
//                // ??????????????????????????????????????????EditText?????????
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }

}
