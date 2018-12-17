package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.MyFindDiscoverBean;
import com.jqsoft.fingertip_health.bean.MyFindDoDiscovertBean;
import com.jqsoft.fingertip_health.bean.MyFindResultBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.contract.SignServiceAssessActivityContract;
import com.jqsoft.fingertip_health.di.module.SignServiceAssessActivityModule;
import com.jqsoft.fingertip_health.di.presenter.SignServiceAssessActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.MyFindFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SignServiceAssessFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

//签约服务评价
public class   SignServiceAssessActivity extends AbstractActivity implements  SignServiceAssessActivityContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    public static final int REQUEST_A = 1;

    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;

    private boolean isRefresh = false;


    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {Constants.TITLE_NEW_ASSESS, Constants.TITLE_REVIEWED};
    private String[] mTitlesNew = {Constants.TITLE_NEW_ASSESS};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
//    private View mDecorView;
//    private ViewPager mViewPager;

//    private CompositeSubscription mIndexSelectSubscription;

    private CompositeSubscription compositeSubscription;


    @Inject
    public SignServiceAssessActivityPresenter mPresenter;

    private int currentPage = 0;

    private int pageSize = 15;
    private boolean flag =true;

    public void registerReadAssessItemClickEvent() {
        Subscription mReadSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SIGN_SERVICE_ASSESS_DID_CLICK_READ_BUTTON, SignServiceAssessResultBean.class)
                .subscribe(new Action1<SignServiceAssessResultBean>() {
                    @Override
                    public void call(SignServiceAssessResultBean signServiceAssessResultBean) {
                        handleReadButtonClick(signServiceAssessResultBean);
                    }
                });
        if (this.compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(mReadSubscription);
    }

    public void unregisterReadAssessItemClickEvent() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    public void handleReadButtonClick(final SignServiceAssessResultBean item) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.hint_suggestion)
                .content(R.string.hint_confirm_to_read_sign_service_assess_item)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        readItem(item);
                        dialog.dismiss();
                    }
                }).build();
        dialog.show();


    }


    public Map<String, String> getRequestMap() {


        String docUserId ="";
        String year = "";
        Map<String, String> map = ParametersFactory.getSignServiceAssessListMap(this, docUserId, year,  currentPage, pageSize);
        return map;
    }

    public Map<String, String> getReadSignServiceAssessItemRequestMap(SignServiceAssessResultBean bean) {
        String docUserId = Identity.getUserId();
        String servicePlanId = Util.trimString(bean.getServicePlanId());
        String year = Util.getCurrentYearString();
        Map<String, String> map = ParametersFactory.getReadSignServiceAssessItemMap(this, servicePlanId, year);
        return map;
    }

    public void readItem(SignServiceAssessResultBean item){
        if (item==null){

        } else {
           /* Map<String, String> map = getReadSignServiceAssessItemRequestMap(item);
            mPresenter.readItem(map);*/
        }
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }
*/
    public void onRefresh(){
        isRefresh = true;
        currentPage=0;
       String sUsername= Identity.srcInfo.getUserName();
        String sRealName=  Identity.srcInfo.getRealName();
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map =  ParametersFactory.getSignServiceAssessListMap(this, sUsername, sRealName,  0, 15);
        mPresenter.main(map, false);


    }

    public void onLoadMore(){

        ++currentPage;
        String sUsername= Identity.srcInfo.getUserName();
        String sRealName=  Identity.srcInfo.getRealName();
        Map<String, String> map =  ParametersFactory.getSignServiceAssessListMap(this, sUsername,sRealName,  currentPage, 15);
        mPresenter.main(map, true);

    }

   /* @Override
    public void onLoadListSuccess(HttpResultBaseBean<List<SignServiceAssessResultBean>> bean) {
        if (bean!=null){
            List<SignServiceAssessResultBean> list = bean.getData();
            List<SignServiceAssessResultBean> newList = getSpecificList(list, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
            List<SignServiceAssessResultBean> alreadyReadList = getSpecificList(list, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
            SignServiceAssessFragment newFragment = getFragmentFromPosition(0);
            newFragment.mAdapter.setNewData(newList);
            newFragment.endRefreshing();
            newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));

            SignServiceAssessFragment alreadyReadFragment = getFragmentFromPosition(1);
            alreadyReadFragment.mAdapter.setNewData(alreadyReadList);
            alreadyReadFragment.endRefreshing();
            alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));

        }
        setAdapterStatus();
    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<List<SignServiceAssessResultBean>> bean) {
        if (bean!=null){
            List<SignServiceAssessResultBean> list = bean.getData();
            List<SignServiceAssessResultBean> newList = getSpecificList(list, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
            List<SignServiceAssessResultBean> alreadyReadList = getSpecificList(list, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
            SignServiceAssessFragment newFragment = getFragmentFromPosition(0);
            newFragment.mAdapter.addData(newList);
            newFragment.endRefreshing();
            newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));

            SignServiceAssessFragment alreadyReadFragment = getFragmentFromPosition(1);
            alreadyReadFragment.mAdapter.addData(alreadyReadList);
            alreadyReadFragment.endRefreshing();
            alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
        }
        setAdapterStatus();

    }*/

    @Override
    public void onLoadListSuccess(HttpResultBaseBean<MyFindResultBean> bean) {
        if (bean!=null){



            MyFindResultBean bean1 = bean.getData();
            List<MyFindDoDiscovertBean> newList = bean1.getDoDiscist();
            List<MyFindDiscoverBean> alreadyReadList = bean1.getDiscoverList();
            String hasMine = bean1.getHasMine();
            if(hasMine.equals("true")){
                if(flag){
                    HasMine();
                }

                MyFindFragment alreadyReadFragment = getFragmentFromPosition1(0);
                alreadyReadFragment.mAdapter.setNewData(alreadyReadList);
                alreadyReadFragment.endRefreshing();
                alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
                setAdapterMyNew(alreadyReadList.size());

                SignServiceAssessFragment newFragment = getFragmentFromPosition(1);
                newFragment.mAdapter.setEnableLoadMore(false);
                newFragment.mAdapter.setNewData(newList);
                newFragment.endRefreshing();
                isRefresh = false;
                newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));
                setAdapterStatusNew(pageSize, newList.size(), true);

            }else if(hasMine.equals("false")){
                if(flag){
                    NoMine();
                }
                MyFindFragment alreadyReadFragment = getFragmentFromPosition1(0);
                alreadyReadFragment.mAdapter.setNewData(alreadyReadList);
                alreadyReadFragment.endRefreshing();
                alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
                setAdapterMyNew(alreadyReadList.size());
            }else {
                NoMine();
            }




        }

    }


    private void HasMine(){
        flag=false;
        for (int i = 0; i < mTitles.length; ++i){
            if (i==0){

                MyFindFragment newAssessFragment = new MyFindFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);


            } else if (i==1) {
                SignServiceAssessFragment newAssessFragment = new SignServiceAssessFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);
            }
            else {
                String title = mTitles[i];
                mFragments.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
            }
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        vpContent.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        initTabData();

    }



    private void NoMine(){

        flag=false;

                MyFindFragment newAssessFragment = new MyFindFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);


        for (int i = 0; i < mTitlesNew.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        vpContent.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


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

        mTabLayout.setVisibility(View.GONE);
    }




    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<MyFindResultBean> bean) {
        if (bean!=null){
            MyFindResultBean bean1 = bean.getData();
            List<MyFindDoDiscovertBean> newList = bean1.getDoDiscist();
            List<MyFindDiscoverBean> alreadyReadList = bean1.getDiscoverList();

            String hasMine =bean1.getHasMine();
            if(hasMine.equals("true")){

                SignServiceAssessFragment newFragment = getFragmentFromPosition(1);
                newFragment.mAdapter.addData(newList);
                newFragment.endRefreshing();
                newFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(newFragment.mAdapter.getData()));
                setAdapterStatusNew(pageSize, newList.size(), true);

                MyFindFragment alreadyReadFragment = getFragmentFromPosition1(0);
                alreadyReadFragment.mAdapter.addData(alreadyReadList);
                alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
                setAdapterMyNew(alreadyReadList.size());
            }else if(hasMine.equals("false")){
                MyFindFragment alreadyReadFragment = getFragmentFromPosition1(0);
                alreadyReadFragment.mAdapter.addData(alreadyReadList);
                alreadyReadFragment.showRecyclerViewOrFailureView(true, ListUtils.isEmpty(alreadyReadFragment.mAdapter.getData()));
                setAdapterMyNew(alreadyReadList.size());
            }else {

            }


        }

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
       /* SignServiceAssessFragment newFragment = getFragmentFromPosition(1);
        newFragment.endRefreshing();
        newFragment.showRecyclerViewOrFailureView(false, true);*/

       /* SignServiceAssessFragment alreadyReadFragment = getFragmentFromPosition(1);
        alreadyReadFragment.endRefreshing();
        alreadyReadFragment.showRecyclerViewOrFailureView(false, true);*/
        NoMine();

        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);

    }

    @Override
    public void onReadSignServiceAssessItemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.showToast(this, Constants.HINT_SIGN_SERVICE_ASSESS_READ_ITEM_SUCCESS);
        //onRefresh();
    }

    @Override
    public void onReadSignServiceAssessItemFailure(String message) {
        Util.showToast(this, Constants.HINT_SIGN_SERVICE_ASSESS_READ_ITEM_FAILURE);
    }

    public void setAdapterStatusNew(int pageSize,int listsize,boolean flag){

            SignServiceAssessFragment fragment = (SignServiceAssessFragment) mFragments.get(1);
            fragment.setLoadMoreStatusNew(pageSize, listsize, flag);

    }

    public void setAdapterMyNew(int pageSize){

        MyFindFragment fragment = (MyFindFragment) mFragments.get(0);
        fragment.setLoadMoreStatusNew(currentPage, pageSize, false);

    }

    public void setAdapterStatus(){
        for (int i = 0; i < mFragments.size(); ++i){
            SignServiceAssessFragment fragment = (SignServiceAssessFragment) mFragments.get(i);
            fragment.setLoadMoreStatusNew(currentPage, pageSize, false);
        }
    }

    public List<SignServiceAssessResultBean> getSpecificList(List<SignServiceAssessResultBean> allList, String status){
        if (!ListUtils.isEmpty(allList)){
            List<SignServiceAssessResultBean> result = new ArrayList<>();
            for (int i = 0; i < allList.size(); ++i){
                SignServiceAssessResultBean bean = allList.get(i);
                if (status.equals(bean.getEvaluationState())){
                    result.add(bean);
                }
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public SignServiceAssessFragment getFragmentFromPosition(int pos){
        return (SignServiceAssessFragment) mFragments.get(pos);
    }

    public MyFindFragment getFragmentFromPosition1(int pos){
        return (MyFindFragment) mFragments.get(pos);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_service_assess;
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

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AddFindActivity.class);
                startActivityForResult(intent, REQUEST_A);
             //   startActivity(intent);
             //   finish();
            }
        });


    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addSignServiceAssessActivity(new SignServiceAssessActivityModule(this))
                .inject(this);

    }

    @Override
    protected void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        LogUtil.i("in WorkbenchActivity toolbar:"+toolbar);
//        setSupportActionBar(toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        initSearchView();

        registerReadAssessItemClickEvent();



//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }





    }

    @Override
    protected void loadData() {
        onRefresh();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mIndexSelectSubscription!=null&&mIndexSelectSubscription.hasSubscriptions()){
//            mIndexSelectSubscription.unsubscribe();
//        }
        unregisterReadAssessItemClickEvent();

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

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
//                        .show();
                loadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
//                keywordString= Util.trimString(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
//                ToastUtil.show(getActivity(), "searchview show");
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.i("SignServiceAssessActivity onCreateOptionsMenu called");
//        reassignToolbar();
        menu.clear();
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.i("SignServiceAssessActivity onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_search:
                //code here
                return true;
            default:
                return super.onOptionsItemSelected(item);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //先判断是哪个页面返回过来的
        if(resultCode==3){
            onRefresh();
        }else {

            switch (requestCode) {
                case REQUEST_A:
                    //再判断返回过来的情况，是成功还是失败还是其它的什么……
                    switch (resultCode) {
                        case 0:
                            //成功了
                            onRefresh();
                            break;
                        case 1:
                            //失败了
                            break;
                        case 3:
                            onRefresh();
                            break;
                    }
                    break;

            }
        }

    }
}