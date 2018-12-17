package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.SignedResidentDirectoryFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.TownLevelMedicalInstitutionDirectoryFragment;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

//通讯录
public class DirectoryActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

//    @BindView(R.id.view_search)
//    MaterialSearchView searchView;
    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;


    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {Constants.TITLE_SIGN_RESIDENT_DIRECTORY, Constants.TITLE_MEDICAL_INSTITUTION};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
//    private View mDecorView;
//    private ViewPager mViewPager;

//    private CompositeSubscription mIndexSelectSubscription;

    @Override
    public int getLayoutId() {
        return R.layout.activity_directory;
    }

//    private void registerIndexSelectSubscription(){
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Integer.class).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                switchToIndex(integer);
//            }
//        });
//        if (mIndexSelectSubscription==null){
//            mIndexSelectSubscription=new CompositeSubscription();
//        }
//        mIndexSelectSubscription.add(subscription);
//    }

//    public void switchToIndex(int index){
//        mViewPager.setCurrentItem(index);
//    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
//        int index = Util.getWorkbenchIndexFromIntent(intent);
//        if (index>=Constants.WORKBENCH_INDEX&&index<=Constants.WORKBENCH_MINE){
//            switchToIndex(index);
//        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        LogUtil.i("in WorkbenchActivity toolbar:"+toolbar);
//        setSupportActionBar(toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        initSearchView();


//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }

        for (int i = 0; i < mTitles.length; ++i){
            if (i==0){
                SignedResidentDirectoryFragment fragment = new SignedResidentDirectoryFragment();
                mFragments.add(fragment);
//                IndexFragment indexFragment = new IndexFragment();
//                mFragments.add(indexFragment);
////                mFragments.add(new WeChatFragment());
            }
            else if (i==1) {
                TownLevelMedicalInstitutionDirectoryFragment fragment = new TownLevelMedicalInstitutionDirectoryFragment();
                mFragments.add(fragment);
//                QueryDataFragment moduleListFragment = new QueryDataFragment();
//                mFragments.add(moduleListFragment);
            }
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
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

//        mTabLayout = ViewFindUtils.find(mDecorView, R.id.ctl_head);

        initTabData();

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

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mIndexSelectSubscription!=null&&mIndexSelectSubscription.hasSubscriptions()){
//            mIndexSelectSubscription.unsubscribe();
//        }
    }

//    public void onEvent(NotificationClickEvent event){
//        Message msg = event.getMessage();
//        UserInfo userInfo = (UserInfo) msg.getFromUser();
//        String title = Identity.getTargetDisplayTitle(userInfo);
//        String targetId = userInfo.getUserName(); ;
//        String appkey = msg.getFromAppKey();
//
//        if (Util.isCurrentChatActivity(this)){
//            MessageListRefreshConfigurationBean bean = new MessageListRefreshConfigurationBean();
//            bean.setTitle(title);
//            bean.setTargetId(targetId);
//            bean.setAppkey(appkey);
//            RxBus.getDefault().post(Constants.EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE, bean);
//        } else if (Util.isAppVisibleToUser(this)){
//            Intent intent = new Intent(this, MessageListActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Constants.CONV_TITLE, title);
//            intent.putExtra(Constants.TARGET_ID, targetId);
//            intent.putExtra(Constants.TARGET_APP_KEY, appkey);
//            startActivity(intent);
//        } else if (Util.isAppRunning(this)){
//            Util.bringApplicationToForegroundFromChatMessageNotificationClick2(this, title, targetId, appkey);
////            Util.runApplicationFromScratch(this, Constants.PACKAGE_NAME);
//        } else {
//            Util.runApplicationFromScratch(this, Constants.PACKAGE_NAME);
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_workbench);


    }

    Random mRandom = new Random();

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

    public void initSearchView(){
        //        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setHint(getResources().getString(R.string.search_hint));
//        searchView.setCursorDrawable(R.drawable.color_cursor_white);
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

        searchView.setOnQueryTextListener(new MaterialSearchViewNew.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
//                        .show();
//                loadData();
                LogUtil.i("onQueryTextSubmit :query:"+query);
                searchSignedResidentDirectoryByKeyword(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
//                keywordString= Util.trimString(newText);
                LogUtil.i("onQueryTextChange:newText:"+newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchViewNew.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
//                ToastUtil.show(getActivity(), "searchview show");
                LogUtil.i("onSearchViewShown");
//                searchView.setQuery(Constants.SPACE_STRING, false);
//                searchSignedResidentDirectoryByKeyword(Constants.EMPTY_STRING);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                LogUtil.i("onSearchViewClosed");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.i("TreatmentActivity onCreateOptionsMenu called");
//        reassignToolbar();
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i("TreatmentActivity onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_search:
                //code here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void searchSignedResidentDirectoryByKeyword(String keyword){
        keyword= Util.trimString(keyword);
        SignedResidentDirectoryFragment fragment = getSignedResidentDirectoryFragment();
        if (fragment!=null){
            fragment.setKeywordString(keyword);
            fragment.onRefresh();
        }
    }

    private void setSignedResidentDirectoryKeyword(String keyword){
        keyword= Util.trimString(keyword);
        SignedResidentDirectoryFragment fragment = getSignedResidentDirectoryFragment();
        if (fragment!=null){
            fragment.setKeywordString(keyword);
//            fragment.onRefresh();
        }
    }

    private SignedResidentDirectoryFragment getSignedResidentDirectoryFragment(){
        if (mFragments!=null && mFragments.size()>0){
            return (SignedResidentDirectoryFragment) mFragments.get(0);
        } else {
            return null;
        }
    }



//    public void reassignToolbar(Fragment fragment){
//        if (fragment instanceof IndexFragment){
//            IndexFragment indexFragment = (IndexFragment)fragment;
//            indexFragment.reassignToolbar();
//        } else if (fragment instanceof InHospitalInspectFragment){
//            InHospitalInspectFragment inHospitalInspectFragment = (InHospitalInspectFragment)fragment;
//            inHospitalInspectFragment.reassignToolbar();
//        } else if (fragment instanceof HeyibanFragment){
//            HeyibanFragment heyibanFragment = (HeyibanFragment)fragment;
//            heyibanFragment.reassignToolbar();
//        } else if (fragment instanceof MineFragment){
//            MineFragment mineFragment = (MineFragment)fragment;
//            mineFragment.reassignToolbar();
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        warnExit();
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
}