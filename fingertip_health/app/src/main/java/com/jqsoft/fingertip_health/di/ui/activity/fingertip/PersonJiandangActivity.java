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

    private String[] mTitles = {"基本信息", "健康信息","生活环境"};
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
//        area_select_title.setText("高血压患者随访记录表");
//        titleLayout=(RelativeLayout)findViewById(R.id.titleLayout);
//        title_bar_title_textview=(TextView)findViewById(R.id.title_bar_title_textview);
//        title_bar_title_textview.setText("高血压患者随访记录表");

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
                Toast.makeText(getApplicationContext(),"获取健康档案失败",Toast.LENGTH_LONG).show();
            }
        } else {
//            showRecyclerViewOrFailureView(false,true);
            Toast.makeText(getApplicationContext(),"获取健康档案失败",Toast.LENGTH_LONG).show();
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
//            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
//            if (isShouldHideInput(v, ev)) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    //处理Editext的光标隐藏、显示逻辑
////                    mEdtFind.clearFocus();
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
//
//    public  boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] leftTop = { 0, 0 };
//            //获取输入框当前的location位置
//            v.getLocationInWindow(leftTop);
//            int left = leftTop[0];
//            int top = leftTop[1];
//            int bottom = top + v.getHeight();
//            int right = left + v.getWidth();
//            if (event.getX() > left && event.getX() < right
//                    && event.getY() > top && event.getY() < bottom) {
//                // 点击的是输入框区域，保留点击EditText的事件
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }

}
