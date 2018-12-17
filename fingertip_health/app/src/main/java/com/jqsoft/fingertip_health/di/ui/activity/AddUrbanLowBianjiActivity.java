package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.presenter.SignServiceAssessActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanBaseInfoBianjiFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanBaseInfoBianjiOtherFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanBaseInfoBianjiStatusFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanBaseInfoFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFamilyBianjiFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianBianjiNewFragment;
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

//城乡低保
public class AddUrbanLowBianjiActivity extends AbstractActivity  {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_head)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    @BindView(R.id.sign_service_assess_title)
    TextView tv_titils;


    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {Constants.TITLE_BASEINFO, Constants.TITLE_FAMILY,Constants.TITLE_FUJIAN};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue,R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green,R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    private CompositeSubscription compositeSubscription;

    private String mybatchNo;

    private String mytitles,status;
    private String myAreaLeavel="";


    @Inject
    public SignServiceAssessActivityPresenter mPresenter;

    private int currentPage = 0;

    private int pageSize = 15;

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

    public void onRefresh(){
       /* Map<String, String> map =  ParametersFactory.getSignServiceAssessListMap(this, "", "",  0, 15);
        mPresenter.main(map, false);*/


    }



    public void setAdapterStatusNew(int pageSize){
/*
        UrbanBaseInfoFragment fragment = (UrbanBaseInfoFragment) mFragments.get(1);
            fragment.setLoadMoreStatusNew(currentPage, pageSize, false);*/

    }

    public void setAdapterMyNew(int pageSize){

      /*  UrbanBaseInfoFragment fragment = (UrbanBaseInfoFragment) mFragments.get(0);
        fragment.setLoadMoreStatusNew(currentPage, pageSize, false);*/

    }

    public void setAdapterStatus(){
        for (int i = 0; i < mFragments.size(); ++i){
         /*   UrbanBaseInfoFragment fragment = (UrbanBaseInfoFragment) mFragments.get(i);
            fragment.setLoadMoreStatusNew(currentPage, pageSize, false);*/
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

    public UrbanBaseInfoFragment getFragmentFromPosition(int pos){
        return (UrbanBaseInfoFragment) mFragments.get(pos);
    }

    public UrbanBaseInfoFragment getFragmentFromPosition1(int pos){
        return (UrbanBaseInfoFragment) mFragments.get(pos);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_urbanlow_bianji;
    }




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
    protected void initInject() {
        super.initInject();
        /*DaggerApplication.get(this)
                .getAppComponent()
                .addSignServiceAssessActivity(new SignServiceAssessActivityModule(this))
                .inject(this);*/

    }

    @Override
    protected void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        LogUtil.i("in WorkbenchActivity toolbar:"+toolbar);
//        setSupportActionBar(toolbar);


        setToolBar(toolbar, Constants.EMPTY_STRING);

        initSearchView();

        registerReadAssessItemClickEvent();

        mybatchNo=getDeliveredStringByKey("batchNo");
        mytitles=getDeliveredStringByKey("titils");
        status=getDeliveredStringByKey("status");
        tv_titils.setText(mytitles);

//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }

        myAreaLeavel = IdentityManager.getAreaLevel(AddUrbanLowBianjiActivity.this);

        for (int i = 0; i < mTitles.length; ++i){
            if (i==0){

                if(status.equals("0") || status.equals("10")){


                    if(myAreaLeavel.equals("area_4") || myAreaLeavel.equals("area_5")){

                        if(mytitles.equals("其他救助")){
                            UrbanBaseInfoBianjiOtherFragment newAssessFragment = new UrbanBaseInfoBianjiOtherFragment();
                            Bundle args = new Bundle();
                            args.putString("batchNo",mybatchNo);
                            args.putString("status",status);
                            args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                            newAssessFragment.setArguments(args);
                            mFragments.add(newAssessFragment);
                        }else {
                            UrbanBaseInfoBianjiFragment newAssessFragment = new UrbanBaseInfoBianjiFragment();
                            Bundle args = new Bundle();
                            args.putString("batchNo",mybatchNo);
                            args.putString("status",status);
                            args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                            newAssessFragment.setArguments(args);
                            mFragments.add(newAssessFragment);
                        }


                    }else {
                        UrbanBaseInfoBianjiStatusFragment newAssessFragment = new UrbanBaseInfoBianjiStatusFragment();
                        Bundle args = new Bundle();
                        args.putString("batchNo",mybatchNo);
                        args.putString("status",status);
                        args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                        newAssessFragment.setArguments(args);
                        mFragments.add(newAssessFragment);
                    }


                }else {
                    UrbanBaseInfoBianjiStatusFragment newAssessFragment = new UrbanBaseInfoBianjiStatusFragment();
                    Bundle args = new Bundle();
                    args.putString("batchNo",mybatchNo);
                    args.putString("status",status);
                    args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                    newAssessFragment.setArguments(args);
                    mFragments.add(newAssessFragment);
                }



//                SignedResidentDirectoryFragment fragment = new SignedResidentDirectoryFragment();
//                mFragments.add(fragment);
//                IndexFragment indexFragment = new IndexFragment();
//                mFragments.add(indexFragment);
////                mFragments.add(new WeChatFragment());
            }
            else if (i==1) {

                UrbanFamilyBianjiFragment newAssessFragment = new UrbanFamilyBianjiFragment();
                Bundle args = new Bundle();
                args.putString("batchNo",mybatchNo);
                args.putString("status",status);
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);


            }else if (i==2) {
                UrbanFuJianBianjiNewFragment newAssessFragment = new UrbanFuJianBianjiNewFragment();
             //   UrbanFuJianBianjiFragment newAssessFragment = new UrbanFuJianBianjiFragment();
                Bundle args = new Bundle();
                args.putString("batchNo",mybatchNo);
                args.putString("status",status);
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                newAssessFragment.setArguments(args);
                mFragments.add(newAssessFragment);


            }
            else {
              //  String title = mTitles[i];
               // mFragments.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
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
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== 100){
            //UrbanBaseInfoBianjiFragment
            UrbanBaseInfoBianjiFragment fragment= (UrbanBaseInfoBianjiFragment) mFragments.get(0);
            //调用AFragment的onActivityResult
            fragment.onActivityResult(requestCode,resultCode,data);
        }

    }
}