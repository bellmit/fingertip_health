package com.jqsoft.fingertip_health.di.ui.fragment.fingertip;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-07.
 */

public class TodoFragmentForFingertip extends AbstractFragment {

    @BindView(R.id.todo_title)
    TextView tvTodoTitle;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    String[] mTitles = {"基本医疗", "公共卫生", "签约管理"};

    String title="";

    public TodoFragmentForFingertip() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo_layout;
    }

    @Override
    protected void initData() {
        String title = getDeliveredString("title");
        tvTodoTitle.setText(title);
        this.title = title;

        if ("待办".equals(title)) {
            mTitles = new String[]{"基本医疗", "公共卫生", "签约管理"};
        } else if ("发现".equals(title)) {
            mTitles = new String[]{"最新资讯", "通知公告", "能力提升"};
        }
    }

    @Override
    protected void initView() {
        for (int i = 0; i < mTitles.length; ++i){
            if ("待办".equals(title)) {
                if (i == 0){
                    BasicMedicalFragmentForFingertip fragment = new BasicMedicalFragmentForFingertip();
                    mFragments.add(fragment);
                } else if (i == 1){
                    BasicMedicalFragmentForFingertip fragment = new BasicMedicalFragmentForFingertip();
                    mFragments.add(fragment);

                } else if (i == 2){
                    BasicMedicalFragmentForFingertip fragment = new BasicMedicalFragmentForFingertip();
                    mFragments.add(fragment);

                }
            }else if ("发现".equals(title)) {
                if (i == 0){
                    NewsNotificationFragmentForFingertip fragment = new NewsNotificationFragmentForFingertip();
                    mFragments.add(fragment);
                } else if (i == 1){
                    NewsNotificationFragmentForFingertip fragment = new NewsNotificationFragmentForFingertip();
                    mFragments.add(fragment);

                } else if (i == 2){
                    NewsNotificationFragmentForFingertip fragment = new NewsNotificationFragmentForFingertip();
                    mFragments.add(fragment);

                }

            }
        }

        init();
    }

    private void init(){
        viewPager.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
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

}
