package com.jqsoft.fingertip_health.di.ui.onlinesignadapter;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.utils.BadgeView;

import java.util.List;

/**
 * Created by Jerry on 2017/6/22.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragmentList;

    public List<Fragment> getmFragmentList() {
        return mFragmentList;
    }

    private List<String> mPageTitleList;
    private List<Integer> mBadgeCountList;

    public SimpleFragmentPagerAdapter(Context context,
                                      FragmentManager fm,
                                      List<Fragment> fragmentList,
                                      List<String> pageTitleList
                                       ) {
        super(fm);
        this.mContext = context;
        this.mFragmentList = fragmentList;
        this.mPageTitleList = pageTitleList;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitleList.get(position);
    }

    public View getTabItemView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_layout_item, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mPageTitleList.get(position));

        View target = view.findViewById(R.id.badgeview_target);

        BadgeView badgeView = new BadgeView(mContext);
        badgeView.setTargetView(target);
        badgeView.setBadgeMargin(0, 6, 10, 0);
        badgeView.setTextSize(10);
//        badgeView.setText(formatBadgeNumber(mBadgeCountList.get(position)));

        return view;
    }

    public static String formatBadgeNumber(int value) {
        if (value <= 0) {
            return null;
        }

        if (value < 100) {
            // equivalent to String#valueOf(int);
            return Integer.toString(value);
        }

        // my own policy
        return "99+";
    }
}
