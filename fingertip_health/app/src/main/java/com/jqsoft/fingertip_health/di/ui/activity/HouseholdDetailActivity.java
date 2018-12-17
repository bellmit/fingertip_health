package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.DemocraticAppraisalfragment;
import com.jqsoft.fingertip_health.di.ui.fragment.FileFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldBaseInfoFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldFaimilyFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HouseHoldServeyFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/12.
 */
// 入户调查 5个详细页面
public class HouseholdDetailActivity extends AbstractActivity {
    private DaggerApplication application;
    @BindView(R.id.sign_service_assess_title)
    TextView sign_service_assess_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private String gid;

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[4];
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue, R.mipmap.inspect_blue, R.mipmap.inspect_blue, R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green, R.mipmap.inspect_green, R.mipmap.inspect_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String appType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_householedetail;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        application = (DaggerApplication) this.getApplication();
        appType = application.getTableType();
        if (appType.equals("1")) {
            mTitles[0] = Constants.TITLE_BASEINFOSER;
            mTitles[1] = Constants.TITLE_FAMAILYMEN;
            mTitles[2] = Constants.TITLE_FILE;
            mTitles[3] = Constants.TITLE_SEVERYPAGE;
            sign_service_assess_title.setText("入户调查");
            ll_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            gid = getIntent().getStringExtra("gid");
            for (int i = 0; i < mTitles.length; ++i) {
                if (i == 0) {
                    HouseHoldBaseInfoFragment houseHoldBaseInfoFragment = new HouseHoldBaseInfoFragment();
                    mFragments.add(houseHoldBaseInfoFragment);
                    houseHoldBaseInfoFragment.setId(gid);
                } else if (i == 1) {
                    HouseHoldFaimilyFragment houseHoldFaimilyFragment = new HouseHoldFaimilyFragment();
                    mFragments.add(houseHoldFaimilyFragment);
                    houseHoldFaimilyFragment.setId(gid);
                } else if (i == 2) {
                    FileFragment fileFragment = new FileFragment();
                    mFragments.add(fileFragment);
                    fileFragment.setId(gid);
                } else if (i == 3) {
                    HouseHoldServeyFragment houseHoldServeyFragment = new HouseHoldServeyFragment();
                    mFragments.add(houseHoldServeyFragment);
                    houseHoldServeyFragment.setId(gid);
                }
//                 else if (i == 4) {
//                    DemocraticAppraisalfragment democraticAppraisalfragment = new DemocraticAppraisalfragment();
//                    mFragments.add(democraticAppraisalfragment);
//                    democraticAppraisalfragment.setId(gid);
//                }
                else {
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
            vpContent.setAdapter(new HouseholdDetailActivity.MyPagerAdapter(getSupportFragmentManager()));

//        mTabLayout = ViewFindUtils.find(mDecorView, R.id.ctl_head);

            initTabData();
        } else {
            mTitles[0] = Constants.TITLE_BASEINFOSER;
            mTitles[1] = Constants.TITLE_FAMAILYMEN;
            mTitles[2] = Constants.TITLE_FILE;

            mTitles[3] = Constants.TITLE_DEMOCRATIC;
            sign_service_assess_title.setText("民主评议");
            ll_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            gid = getIntent().getStringExtra("gid");
            for (int i = 0; i < mTitles.length; ++i) {
                if (i == 0) {
                    HouseHoldBaseInfoFragment houseHoldBaseInfoFragment = new HouseHoldBaseInfoFragment();
                    mFragments.add(houseHoldBaseInfoFragment);
                    houseHoldBaseInfoFragment.setId(gid);
                } else if (i == 1) {
                    HouseHoldFaimilyFragment houseHoldFaimilyFragment = new HouseHoldFaimilyFragment();
                    mFragments.add(houseHoldFaimilyFragment);
                    houseHoldFaimilyFragment.setId(gid);
                } else if (i == 2) {
                    FileFragment fileFragment = new FileFragment();
                    mFragments.add(fileFragment);
                    fileFragment.setId(gid);
                }
//                else if (i == 3) {
//                    HouseHoldServeyFragment houseHoldServeyFragment = new HouseHoldServeyFragment();
//                    mFragments.add(houseHoldServeyFragment);
//                    houseHoldServeyFragment.setId(gid);
//                }
                else if (i == 3) {
                    DemocraticAppraisalfragment democraticAppraisalfragment = new DemocraticAppraisalfragment();
                    mFragments.add(democraticAppraisalfragment);
                    democraticAppraisalfragment.setId(gid);
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
            vpContent.setAdapter(new HouseholdDetailActivity.MyPagerAdapter(getSupportFragmentManager()));

//        mTabLayout = ViewFindUtils.find(mDecorView, R.id.ctl_head);

            initTabData();
        }

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
//
//        vpContent.setCurrentItem(0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {


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

//    public static String setcardno() {
//        return
//    }
}
