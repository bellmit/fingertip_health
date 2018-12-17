package com.jqsoft.fingertip_health.di.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.CenterListAdapter;
import com.jqsoft.fingertip_health.adapter.HelpListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.CenterListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.JzzListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonCollectionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.PersonCollectionActivityContract;
import com.jqsoft.fingertip_health.di.module.PersonCollectionActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PersonCollectionActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.CenterCollectionFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HelpCollectionFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.JzzCollectionFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.util.VerticalSwipeRefreshLayout;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

//我的收藏

public class PersonCollectionActivity extends AbstractActivity implements
        PersonCollectionActivityContract.View,VerticalSwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;


    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;

    @BindView(R.id.vp_content)
    ViewPager vpContent;
//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//
//    @BindView(R.id.lay_policy_load_failure)
//    View failureView;

    private  String code,titlename;
    TextView tvFailureView;
    public static PersonCollectionActivity instance = null;
    @Inject
    PersonCollectionActivityPresenter mPresenter;
//    @BindView(R.id.vp_content)
//    ViewPager vpContent;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int type;

    private HelpListAdapter helpAdapter;
    private CenterListAdapter centerAdapter;
    List<CenterListBean> CenterList;
    List<HelpListBean>   HelpList;
    List<JzzListBean>   JzzList;
    private String[] mTitlesNew = {"我的收藏"};
    private String keywordString;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private View mDecorView;
    private String[] mTitles = {"办事指南", "受理中心","救助站"};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue,R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    HelpCollectionFragment helpCollectionFragment;
    CenterCollectionFragment centerCollectionFragment;
    JzzCollectionFragment jzzCollectionFragment;
    private  boolean isRefresh=false;
    @Override
    protected void loadData() {
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getGCAPersonCollectionMap(this,name,
                "collectionData.queryMyCollectionReceptions");
        mPresenter.main(map, false);
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_personcollection;
    }

    @Override
    protected void initData() {


    }
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addPersonCollectionActivity(new PersonCollectionActivityModule(this))
                .inject(this);
    }


    @Override
    protected void initView() {
        instance=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        srl.setRefreshing(false);
        srl.setOnRefreshListener(this);
//        failureView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isRefresh=true;
//                loadData();
////        helpCollectionFragment.RefreshInstance();
////        centerCollectionFragment.centerRefreshInstance();
//                srl.setRefreshing(false);
//
//            }
//        });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public List<PersonCollectionBean> getListFromResult(GCAHttpResultBaseBean<List<PersonCollectionBean>> beanList) {
        if (beanList != null) {
            List<PersonCollectionBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<PersonCollectionBean>> beanList) {
        if (beanList != null) {
            List<PersonCollectionBean> list = beanList.getData();
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


    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<PersonCollectionBean>> beanList) {

        int listSize = getListSizeFromResult(beanList);
        List<PersonCollectionBean> list = getListFromResult(beanList);
        PersonCollectionBean personCollectionBeanlist=list.get(0);
        CenterList =personCollectionBeanlist.getCenterList();
        HelpList =personCollectionBeanlist.getHelpList();
        JzzList =personCollectionBeanlist.getJzzList();
        mDecorView = getWindow().getDecorView();
//        if (CenterList.size()==0&&HelpList.size()==0){
////            vpContent.setVisibility(View.GONE);
////            failureView.setVisibility(View.VISIBLE);
//        }else {
            vpContent.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);



            if (isRefresh){
                helpCollectionFragment.RefreshInstance(HelpList );
                centerCollectionFragment.centerRefreshInstance(CenterList);
                jzzCollectionFragment.RefreshInstance(JzzList);


            }else {
                HasMine();

            }

//        }




    }


    private void HasMine(){

        for (int i = 0; i < mTitles.length; ++i){
            if (i==0){

              helpCollectionFragment = new HelpCollectionFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                helpCollectionFragment.setArguments(args);
                mFragments.add(helpCollectionFragment);


            } else if (i==1) {
             centerCollectionFragment = new CenterCollectionFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                centerCollectionFragment.setArguments(args);
                mFragments.add(centerCollectionFragment);
            }else  if(i==2){
                jzzCollectionFragment = new JzzCollectionFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY,Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                jzzCollectionFragment.setArguments(args);
                mFragments.add(jzzCollectionFragment);
            }
            else {
                String title = mTitles[i];
                mFragments.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
            }
        }
            mTabLayout.setIconVisible(false);


        mTabLayout.setTextUnselectColor((getResources().getColor(R.color.black_alpha_112)));
        mTabLayout.setTextSelectColor(getResources().getColor(R.color.colorTheme));
        mTabLayout.setTextsize(15);
        mTabLayout.setIndicatorColor(getResources().getColor(R.color.colorTheme));


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        vpContent.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

            initTabData();



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
    public void onRefresh() {
        isRefresh=true;
        loadData();
//        helpCollectionFragment.RefreshInstance();
//        centerCollectionFragment.centerRefreshInstance();
        srl.setRefreshing(false);

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
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PersonCollectionBean>> beanList) {
        int listSize = getListSizeFromResult(beanList);
        List<PersonCollectionBean> list = getListFromResult(beanList);

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
//        vpContent.setVisibility(View.GONE);
//        failureView.setVisibility(View.VISIBLE);
    }



    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_policy_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_policy_info_error_please_click_to_reload);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }

    public   List<CenterListBean> getcenterdata(){
        return CenterList;
    }
    public   List<HelpListBean> gethelpdata(){
        return HelpList;
    }
    public   List<JzzListBean> getjzzdata(){
        return JzzList;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
        //先判断是哪个页面返回过来的
        switch (requestCode) {
            case 1:

                //再判断返回过来的情况，是成功还是失败还是其它的什么……
                switch (resultCode) {
                    case 0:
                        //成功了
                        onRefresh();
                        break;
                    case 1:
                        //失败了
                        break;
                }
                break;

        }
    }
}
