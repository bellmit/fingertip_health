//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.Toast;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.SimpleFragmentPagerAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.ReservationBeanList;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
//import com.jqsoft.fingertip_health.di.contract.ReservationContract;
//import com.jqsoft.fingertip_health.di.module.ReservationActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.ReservationPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.ReservationApplyFragment;
//import com.jqsoft.fingertip_health.di.ui.fragment.ReservationHadFragment;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.view.BadgeView;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import okhttp3.RequestBody;
////签约执行
//public class ReservationServiceActivity extends AbstractActivity implements ReservationContract.View{
//
//
//    private SimpleFragmentPagerAdapter fragmentAdapters;
//    @BindView((R.id.viewpager))
//    ViewPager mViewPager;
//    @BindView(R.id.tabs)
//    TabLayout mTabLayout;
//    private List<Integer> mBadgeCountList = new ArrayList<Integer>();
//    private List<BadgeView> mBadgeViews;
//    private List<Fragment> fragments = new ArrayList<>();
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @Inject
//    ReservationPresenter reservationPresenter;
//
//    private String sYear;
//    private String sflagdelete="";
//    private  final List<ReservationBeanList> mApplyreservationBeanList= new ArrayList<>();
//
//    private final List<ReservationBeanList> mHadreservationBeanList= new ArrayList<>();
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_reservation_execu;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        setToolBar(toolbar, Constants.EMPTY_STRING);
//        initfragment();
//    }
//
//
//    public void initfragment() {
//      //  mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        //初始化TabLayout的title
//
//        mTabLayout.addTab(mTabLayout.newTab().setText("申请中"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("已服务"));
//     /*   mTabLayout.setTabData("申请中");
//        mTabLayout.set*/
//
//
//        List<String> titles = new ArrayList<>();
//        titles.add("申请中");
//        titles.add("已服务");
//
//
//        //初始化ViewPager的数据集
//
//        fragments.add(new ReservationApplyFragment());//
//        fragments.add(new ReservationHadFragment());//
//
//        //创建ViewPager的adapter
//        mViewPager.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
//      //  fragmentAdapters = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
//
//        fragmentAdapters = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager(),
//                fragments, titles, mBadgeCountList);
//
//        mViewPager.setAdapter(fragmentAdapters);
//        //千万别忘了，关联TabLayout与ViewPager
//        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(fragmentAdapters);
//
//
//    }
//
//
//    private void initBadgeViews() {
//        if (mBadgeViews == null) {
//            mBadgeViews = new ArrayList<BadgeView>();
//            for (int i = 0; i < fragments.size(); i++) {
//                BadgeView tmp = new BadgeView(this);
//                tmp.setBadgeMargin(0, 6, 10, 0);
//                tmp.setTextSize(10);
//                mBadgeViews.add(tmp);
//            }
//        }
//    }
//
//
//    /**
//     * 设置Tablayout上的标题的角标
//     */
//    private void setUpTabBadge() {
//        // 1. 最简单
////        for (int i = 0; i < mFragmentList.size(); i++) {
////            mBadgeViews.get(i).setTargetView(((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(i));
////            mBadgeViews.get(i).setText(formatBadgeNumber(mBadgeCountList.get(i)));
////        }
//
//        // 2. 最实用
//        for (int i = 0; i < fragments.size(); i++) {
//            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//
//            // 更新Badge前,先remove原来的customView,否则Badge无法更新
//            View customView = tab.getCustomView();
//            if (customView != null) {
//                ViewParent parent = customView.getParent();
//                if (parent != null) {
//                    ((ViewGroup) parent).removeView(customView);
//                }
//            }
//
//            // 更新CustomView
//            tab.setCustomView(fragmentAdapters.getTabItemView(i));
//        }
//
//        // 需加上以下代码,不然会出现更新Tab角标后,选中的Tab字体颜色不是选中状态的颜色
//        mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()).getCustomView().setSelected(true);
//    }
//
//
//
//    @Override
//    protected void loadData() {
//      //  String orgId = Identity.info.getSorganizationkey();
//     //   String docUserId =Identity.info.getGuserid();
//        String orgId = Identity.getOrganizationKey();
//        String docUserId =Identity.getUserId();
//        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy");
//        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
//        sYear    =    formatter.format(curDate);
//
//        Map<String, String> map = ParametersFactory.getReservationInfo(this, sYear,orgId,docUserId);
//        RequestBody body = Util.getBodyFromMap1(map);
//        reservationPresenter.main(body);
//    }
//
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addreservation(new ReservationActivityModule(this))
//                .inject(this);
//    }
//
//    @Override
//    public void onReservationSuccess(HttpResultBaseBean<List<ReservationBeanList>> bean) {
//        Util.hideGifProgressDialog(this);
//
//
//        if (bean != null) {
//            initBadgeViews();
//
//            List<ReservationBeanList> list = bean.getData();
//
//            if(list.size()==0){
//
//                mBadgeCountList.add(0);
//                mBadgeCountList.add(0);
//
//              //  initBadgeViews();
//                mBadgeCountList.set(0, 0);
//                mBadgeCountList.set(1,0);
//
//                setUpTabBadge();
//                setUpTabBadge();
//            }else{
//                mApplyreservationBeanList.clear();
//                mHadreservationBeanList.clear();
//                for(int i=0;i<list.size();i++){
//                    //  mApplyreservationBeanList.add(list.get(i));
//                    if(list.get(i).getReservationState().equals("1")){
//                        mApplyreservationBeanList.add(list.get(i));
//                    }else if(list.get(i).getReservationState().equals("2")){
//                        mHadreservationBeanList.add(list.get(i));
//                    }
//
//                }
//
//
//                mBadgeCountList.add(mApplyreservationBeanList.size());
//                mBadgeCountList.add(mHadreservationBeanList.size());
//
//                mBadgeCountList.set(0, mApplyreservationBeanList.size());
//                mBadgeCountList.set(1,mHadreservationBeanList.size());
//
//                setUpTabBadge();
//            }
//
//
//
//            Fragment fragment = fragmentAdapters.getItem(0);
//
//            ((ReservationApplyFragment) fragment).setPendbean(list);
//
//            Fragment fragment2 = fragmentAdapters.getItem(1);
//            ((ReservationHadFragment) fragment2).sethadbean(list);
//
//
//
//
//
//
//
//        }
//    }
//
//    @Override
//    public void onLoadMoreReservationSuccess(HttpResultBaseBean<List<ReservationBeanList>> bean) {
//        Util.hideGifProgressDialog(this);
//    }
//
//    @Override
//    public void onReservationFailure(String message) {
//        Util.hideGifProgressDialog(this);
//    }
//
//    @Override
//    public void onLoadDeleteSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
//        Util.hideGifProgressDialog(this);
//        if(bean!=null){
//            Toast.makeText(getApplicationContext(),"取消申请成功！",Toast.LENGTH_SHORT).show();
//
//            String orgId = Identity.getOrganizationKey();
//            String docUserId =Identity.getUserId();
//            SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy");
//            Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
//            sYear    =    formatter.format(curDate);
//
//            Map<String, String> map = ParametersFactory.getReservationInfo(this, sYear,orgId,docUserId);
//            RequestBody body = Util.getBodyFromMap1(map);
//            reservationPresenter.main(body);
//
//        }
//    }
//
//    @Override
//    public void onDeleteFailure(String message) {
//        Util.hideGifProgressDialog(this);
//        Toast.makeText(getApplicationContext(),"取消申请失败！",Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void deleteExecuInfo(ReservationBeanList item){
//        String docUserId =item.getDocUserId();
//        String servicePlanId =item.getServicePlanID();
//     //   String year =sYear;
//        Map<String, String> map = ParametersFactory.getdeleteReservation(this, docUserId,servicePlanId,sYear);
//        RequestBody body = Util.getBodyFromMap1(map);
//        reservationPresenter.maindelete(body);
//
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        String orgId = Identity.getOrganizationKey();
//        String docUserId =Identity.getUserId();
//        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy");
//        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
//        sYear    =    formatter.format(curDate);
//
//        Map<String, String> map = ParametersFactory.getReservationInfo(this, sYear,orgId,docUserId);
//        RequestBody body = Util.getBodyFromMap1(map);
//        reservationPresenter.main(body);
//    }
//
//    public void update(){
//        String orgId = Identity.getOrganizationKey();
//        String docUserId =Identity.getUserId();
//        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy");
//        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
//        sYear    =    formatter.format(curDate);
//
//        Map<String, String> map = ParametersFactory.getReservationInfo(this, sYear,orgId,docUserId);
//        RequestBody body = Util.getBodyFromMap1(map);
//        reservationPresenter.main(body);
//    }
//}
