package com.jqsoft.fingertip_health.di.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.AdviceBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.JzzListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AdviceActivityContract;
import com.jqsoft.fingertip_health.di.module.AdviceActivityModule;
import com.jqsoft.fingertip_health.di.presenter.AdviceActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.JzzCollectionFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.NoReplyFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.ReplyFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.SimpleCardFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.entity.TabEntity;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.util.VerticalSwipeRefreshLayout;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

//咨询建议

public class AdviceActivity extends AbstractActivity implements
        AdviceActivityContract.View, VerticalSwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.add_advice)
    TextView add_advice;

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


    private String code, titlename;
    TextView tvFailureView;
    public static AdviceActivity instance = null;
    @Inject
    AdviceActivityPresenter mPresenter;
    //    @BindView(R.id.vp_content)
//    ViewPager vpContent;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @BindView(R.id.policy_title)
    TextView policy_title;
    @BindView(R.id.query_btn)
    ImageView query_btn;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.bt_username_clear)
    Button bt_username_clear;
    List<AdviceBean> ReplyList;
    List<AdviceBean> NoReplyList;
    List<JzzListBean> JzzList;
    public static final int REQUEST_A = 1;
    private String[] mTitlesNew = {"我的收藏"};
    private String keywordString;

    String name;

    private View mDecorView;
    private String[] mTitles = {"已回复", "未回复"};
    private int[] mIconUnselectIds = {
            R.mipmap.mine_blue, R.mipmap.inspect_blue, R.mipmap.inspect_blue};
    private int[] mIconSelectIds = {
            R.mipmap.mine_green, R.mipmap.inspect_green, R.mipmap.inspect_green};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    ReplyFragment replyFragment;
    NoReplyFragment noReplyFragment;
    JzzCollectionFragment jzzCollectionFragment;
    private boolean isRefresh = false;
    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = 10;
    private int nocurrentPage = Constants.DEFAULT_INITIAL_PAGE;
    @Override
    protected void loadData() {
        name = IdentityManager.getLoginSuccessUsername(getApplicationContext());

        //未回复
        Map<String, String> map = ParametersFactory.getGCAAdviceMap(this,
                name,
                "1",
                "consult.consultList",
                String.valueOf(nocurrentPage),
                String.valueOf(pageSize));
        mPresenter.noreply(map, false);
        //已回复
        Map<String, String> map1 = ParametersFactory.getGCAAdviceMap(this,
                name,
                "3",
                "consult.consultList",
                String.valueOf(currentPage),
                String.valueOf(pageSize));
        mPresenter.main(map1, false);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_advice;
    }

    @Override
    protected void initData() {
        add_advice.setVisibility(View.VISIBLE);
    }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addAdviceActivity(new AdviceActivityModule(this))
                .inject(this);
    }
    public void setLoadReplyMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {
                replyFragment.getadpater().setEnableLoadMore(false);
                replyFragment.getadpater().loadMoreEnd(true);
                replyFragment.getadpater().loadMoreComplete();

            } else {


                replyFragment.getadpater().setEnableLoadMore(true);
                replyFragment.getadpater().loadMoreComplete();

            }
        } else {


            replyFragment.getadpater().setEnableLoadMore(true);
            replyFragment.getadpater().loadMoreFail();
        }
    }
    public void setLoadNoReplyMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {

                noReplyFragment.getadpater().setEnableLoadMore(false);

                noReplyFragment.getadpater().loadMoreEnd(true);
            } else {

                noReplyFragment.getadpater().setEnableLoadMore(true);


                noReplyFragment.getadpater().loadMoreComplete();
            }
        } else {
            noReplyFragment.getadpater().setEnableLoadMore(true);
            noReplyFragment.getadpater().loadMoreFail();


        }
    }

    @Override
    protected void initView() {
        if(IdentityManager.getAreaLevel(this).equals("area_1")  || IdentityManager.getAreaLevel(this).equals("area_2") || IdentityManager.getAreaLevel(this).equals("area_3")){
            add_advice.setVisibility(View.GONE);

        }


        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        policy_title.setText("咨询建议");
        instance = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        srl.setRefreshing(false);
        srl.setOnRefreshListener(this);
        HasMine();
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });
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
        bt_username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
        add_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AdviceActivity.this, WanttoAdvice.class);
                startActivityForResult(intent, REQUEST_A);

            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String      tempString = et_search.getText().toString();

                if (!TextUtils.isEmpty(tempString)) {
                    String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());

                    //未回复
                    Map<String, String> map = ParametersFactory.getGCAQuaAdviceMap(AdviceActivity.this,
                            tempString,tempString,  name,
                            "1",
                            "consult.consultList",
                            String.valueOf(nocurrentPage),
                            String.valueOf(pageSize));
                    mPresenter.noreply(map, false);
                    //已回复
                    Map<String, String> map1 = ParametersFactory.getGCAQuaAdviceMap(AdviceActivity.this,
                            tempString,tempString, name,
                            "3",
                            "consult.consultList",
                            String.valueOf(currentPage),
                            String.valueOf(pageSize));
                    mPresenter.main(map1, false);
                } else {
                    Util.showToast(AdviceActivity.this,"请输入关键字或编号查询");
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragments.clear();
    }


    public List<AdviceBean> getListFromResult(GCAHttpResultBaseBean<List<AdviceBean>> beanList) {
        if (beanList != null) {
            List<AdviceBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public int getListSizeFromResult(GCAHttpResultBaseBean<List<AdviceBean>> beanList) {
        if (beanList != null) {
            List<AdviceBean> list = beanList.getData();
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
    public void onReplyLoadListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> beanList) {

        int listSize = getListSizeFromResult(beanList);
        List<AdviceBean> list = getListFromResult(beanList);

        ReplyList = list;
//        HelpList =personCollectionBeanlist.getHelpList();
//        JzzList =personCollectionBeanlist.getJzzList();
        mDecorView = getWindow().getDecorView();
//        if (CenterList.size()==0&&HelpList.size()==0){
////            vpContent.setVisibility(View.GONE);
////            failureView.setVisibility(View.VISIBLE);
//        }else {
        vpContent.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);

        replyFragment.RefreshInstance(ReplyList);
        setLoadReplyMoreStatus(pageSize, listSize, true);

//        if (isRefresh) {
//            replyFragment.RefreshInstance(ReplyList);
//
//
//        } else {
//
//
//        }

//        }


    }

    @Override
    public void onNoReplyLoadListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean) {

        int listSize = getListSizeFromResult(bean);
        List<AdviceBean> list = getListFromResult(bean);

        NoReplyList = list;
//        HelpList =personCollectionBeanlist.getHelpList();
//        JzzList =personCollectionBeanlist.getJzzList();
//        mDecorView = getWindow().getDecorView();
//        if (CenterList.size()==0&&HelpList.size()==0){
////            vpContent.setVisibility(View.GONE);
////            failureView.setVisibility(View.VISIBLE);
//        }else {
//        vpContent.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);

        noReplyFragment.RefreshInstance(NoReplyList);
        setLoadNoReplyMoreStatus(pageSize, listSize, true);

        if (isRefresh) {

//                centerCollectionFragment.centerRefreshInstance(CenterList);
//                jzzCollectionFragment.RefreshInstance(JzzList);


        } else {

        }
    }

    @Override
    public void onLoadreplyMoreListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> beanList) {
        int listSize = getListSizeFromResult(beanList);
        List<AdviceBean> list = getListFromResult(beanList);

        ReplyList = list;
//        HelpList =personCollectionBeanlist.getHelpList();
//        JzzList =personCollectionBeanlist.getJzzList();
        mDecorView = getWindow().getDecorView();
//        if (CenterList.size()==0&&HelpList.size()==0){
////            vpContent.setVisibility(View.GONE);
////            failureView.setVisibility(View.VISIBLE);
//        }else {
        vpContent.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);

        replyFragment.addInstance(ReplyList);
        setLoadReplyMoreStatus(pageSize, listSize, true);

//        if (isRefresh) {
//            replyFragment.addInstance(ReplyList);
////                centerCollectionFragment.centerRefreshInstance(CenterList);
////                jzzCollectionFragment.RefreshInstance(JzzList);
//
//
//        } else {
//
//
//        }

    }

    @Override
    public void onnoreplyLoadMoreListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean) {
        int listSize = getListSizeFromResult(bean);
        List<AdviceBean> list = getListFromResult(bean);

        NoReplyList = list;
//        HelpList =personCollectionBeanlist.getHelpList();
//        JzzList =personCollectionBeanlist.getJzzList();
//        mDecorView = getWindow().getDecorView();
//        if (CenterList.size()==0&&HelpList.size()==0){
////            vpContent.setVisibility(View.GONE);
////            failureView.setVisibility(View.VISIBLE);
//        }else {
//        vpContent.setVisibility(View.VISIBLE);
//            failureView.setVisibility(View.GONE);

        noReplyFragment.addInstance(NoReplyList);
        setLoadNoReplyMoreStatus(pageSize, listSize, true);

        if (isRefresh) {

//                centerCollectionFragment.centerRefreshInstance(CenterList);
//                jzzCollectionFragment.RefreshInstance(JzzList);


        } else {

        }
    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore,String fromwhere) {

        if (fromwhere.equals("reply")){

            replyFragment.RefreshInstance(null);
        }else {

            noReplyFragment.RefreshInstance(null);
        }
//        showRecyclerViewOrFailureView(false, true);


//        ReplyFragment.(null);


    }
    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }
    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_advice_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_advice_info_error_please_click_to_reload);
    }

    private void HasMine() {
        mFragments.clear();
        for (int i = 0; i < mTitles.length; ++i) {
            if (i == 0) {

                replyFragment = new ReplyFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_ALREADY_READ);
                replyFragment.setArguments(args);
                mFragments.add(replyFragment);


            } else if (i == 1) {
                noReplyFragment = new NoReplyFragment();
                Bundle args = new Bundle();
                args.putString(Constants.SIGN_SERVICE_ASSESS_TYPE_KEY, Constants.SIGN_SERVICE_ASSESS_TYPE_NEW);
                noReplyFragment.setArguments(args);
                mFragments.add(noReplyFragment);
            } else {
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
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        nocurrentPage= Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
        loadData();
//        helpCollectionFragment.RefreshInstance();
//        centerCollectionFragment.centerRefreshInstance();
        srl.setRefreshing(false);

    }

    //    @Override
//    public void onLoadMoreRequested() {
//        currentPage=currentPage+10;
//        loadData();
//    }
    public  void  Replyloadmore(){

            currentPage++;
            Map<String, String> map1 = ParametersFactory.getGCAAdviceMap(this,
                    name,
                    "3",
                    "consult.consultList",
                    String.valueOf(currentPage),
                    String.valueOf(pageSize));
            mPresenter.main(map1, true);





    }
    public  void  noReplyloadmore(){


            ++nocurrentPage;
//        nocurrentPage=currentPage+10;
            Map<String, String> map = ParametersFactory.getGCAAdviceMap(this,
                    name,
                    "1",
                    "consult.consultList",
                    String.valueOf(nocurrentPage),
                    String.valueOf(pageSize));
            mPresenter.noreply(map, true);

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

    public List<AdviceBean> getReplyList() {
        return ReplyList;
    }

    public List<AdviceBean> getNoReplyList() {
        return NoReplyList;
    }
//    public   List<JzzListBean> getjzzdata(){
//        return JzzList;
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        onRefresh();

        //先判断是哪个页面返回过来的
//        switch (requestCode) {
//            case 1:
//
//                //再判断返回过来的情况，是成功还是失败还是其它的什么……
//                switch (resultCode) {
//                    case 0:
//                        //成功了
//                        onRefresh();
//                        break;
//                    case 1:
//                        //失败了
//                        break;
//                }
//                break;
//
//        }
    }
}
