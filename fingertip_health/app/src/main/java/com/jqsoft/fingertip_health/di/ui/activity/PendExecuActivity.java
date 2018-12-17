package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ExecuedProjectAdapter;
import com.jqsoft.fingertip_health.adapter.PendExecuAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.PendExecuContract;
import com.jqsoft.fingertip_health.di.module.PendExecuActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PendExecuPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.CancelReserverFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HadExeucedFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.PendExeucedFragment;
import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.FragmentAdapters;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

public class PendExecuActivity extends AbstractActivity implements PendExecuContract.View {


    private PendExecuAdapter mPendExecuAdapter;
    private ExecuedProjectAdapter mExecuProjectAdapter;
    private FragmentAdapters fragmentAdapters;

    @Inject
    PendExecuPresenter pendexecuPresenter;

   /* @BindView(R.id.lv_pend_execu)
    ListView lv_pend_execu;

    @BindView(R.id.lv_execued)
    ListView lv_execued;*/

    private String sYear,sSignKey;
    private PeopleBaseInfoBean  mpeopleBasebean;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView((R.id.viewpager))
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    private PendExeucedFragment pendExeucedFragment;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_pend_execu;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


   //     lv_execued.setAdapter(mPendExecuAdapter);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        initfragment();
    }

    public void initfragment() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //初始化TabLayout的title

        mTabLayout.addTab(mTabLayout.newTab().setText("待预约项目"));
        mTabLayout.addTab(mTabLayout.newTab().setText("已预约项目"));
        mTabLayout.addTab(mTabLayout.newTab().setText("已取消项目"));

        List<String> titles = new ArrayList<>();
        titles.add("待预约项目");
        titles.add("已预约项目");
        titles.add("已取消项目");

        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PendExeucedFragment());//
        fragments.add(new HadExeucedFragment());//
        fragments.add(new CancelReserverFragment());//

        //创建ViewPager的adapter
        mViewPager.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        fragmentAdapters = new FragmentAdapters(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentAdapters);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapters);

    }

    @Override
    protected void loadData() {
        /*mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");
        sYear = (String) this.getIntent().getSerializableExtra("sYear");
        sSignKey = (String) this.getIntent().getSerializableExtra("sSignKey");

        Map<String, String> map = ParametersFactory.getPeopleSignInfo(sYear,sSignKey);
        RequestBody body = Util.getBodyFromMap(map);
        pendexecuPresenter.main(body);*/

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addpendexecu(new PendExecuActivityModule(this))
                .inject(this);
    }

    @Override
    public void onServicePackDetailSuccess(HttpResultBaseBean<List<PendExecuBeanList>> bean) {

        Util.hideGifProgressDialog(this);


        if (bean != null) {
            HttpResultBaseBean<List<PendExecuBeanList>> bean2 = null;




            List<PendExecuBeanList> list = new ArrayList<>();
            List<PendExecuBeanList> list2 = new ArrayList<>();
            list = bean.getData();
            list2 = bean.getData();
            final List<PendExecuBeanList> pendExecuBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> execuProjectBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> cancelProjectBeanLists = new ArrayList<>();


            bean2 = new HttpResultBaseBean<List<PendExecuBeanList>>();
            List<PendExecuBeanList> listPlanIDEmty =new ArrayList<>();
            listPlanIDEmty=list;
            pendExecuBeanLists.clear();
            execuProjectBeanLists.clear();
            cancelProjectBeanLists.clear();
            for(int i=0;i<listPlanIDEmty.size();i++){

                bean2.setData(list);
                if(TextUtils.isEmpty(listPlanIDEmty.get(i).getServicePlanID())){
                    pendExecuBeanLists.add(listPlanIDEmty.get(i));
                }

               if(bean.getData().get(i).getReservationState().equals("3")){
                    cancelProjectBeanLists.add(bean.getData().get(i));
                }
            }
           /* Set set=new HashSet();
            set.addAll(pendExecuBeanLists);
            pendExecuBeanLists.clear();
            pendExecuBeanLists.addAll(set);*/


        /*    for(int i=0;i<bean2.getData().size();i++){

                if(!TextUtils.isEmpty(bean2.getData().get(i).getServicePlanID())){
                    execuProjectBeanLists.add(bean2.getData().get(i));
                }

            }*/



            Fragment fragment = fragmentAdapters.getFragments().get(0);
            ((PendExeucedFragment) fragment).setPendbean(pendExecuBeanLists,mpeopleBasebean);

           // Fragment fragment2 = fragmentAdapters.getFragments().get(1);
           // ((HadExeucedFragment) fragment2).sethadbean1(execuProjectBeanLists,mpeopleBasebean);
            Fragment fragment3 = fragmentAdapters.getFragments().get(2);
            ((CancelReserverFragment) fragment3).sethadbean1(cancelProjectBeanLists,mpeopleBasebean);


        }
    }

    @Override
    public void onServicePackDetailSuccess1(HttpResultBaseBean<List<PendExecuBeanList>> bean) {
        if (bean != null) {
            List<PendExecuBeanList> list = new ArrayList<>();
            List<PendExecuBeanList> list2 = new ArrayList<>();
            list = bean.getData();
            list2 = bean.getData();
            final List<PendExecuBeanList> pendExecuBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> execuProjectBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> cancelProjectBeanLists = new ArrayList<>();

            execuProjectBeanLists.clear();
            cancelProjectBeanLists.clear();
            for(int i=0;i<bean.getData().size();i++){

                if(bean.getData().get(i).getReservationState().equals("1")){
                    execuProjectBeanLists.add(bean.getData().get(i));

                }else if(bean.getData().get(i).getReservationState().equals("3")){
                    cancelProjectBeanLists.add(bean.getData().get(i));
                }

            }


            Fragment fragment2 = fragmentAdapters.getFragments().get(1);
            ((HadExeucedFragment) fragment2).sethadbean1(execuProjectBeanLists,mpeopleBasebean);

            Fragment fragment3 = fragmentAdapters.getFragments().get(2);
            ((CancelReserverFragment) fragment3).sethadbean1(cancelProjectBeanLists,mpeopleBasebean);



        }
    }

    @Override
    public void onServicePackDetailFailure1(String message) {

    }

    public void showSignInfoOverview(List<PendExecuBeanList> list) {
        /*if (list != null) {
            final List<PendExecuBeanList> pendExecuBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> execuProjectBeanLists = new ArrayList<>();
            pendExecuBeanLists.clear();
            execuProjectBeanLists.clear();

            for(int i=0;i<list.size();i++){
                if(list.get(i).getFinished().equals("1")){
                    pendExecuBeanLists.add(list.get(i));
                }
                if(!TextUtils.isEmpty(list.get(i).getServicePlanID())){
                    execuProjectBeanLists.add(list.get(i));
                }

            }


            mPendExecuAdapter = new PendExecuAdapter(this, pendExecuBeanLists,mpeopleBasebean);
            lv_pend_execu.setAdapter(mPendExecuAdapter);

            mExecuProjectAdapter = new ExecuedProjectAdapter(this, execuProjectBeanLists);
            lv_execued.setAdapter(mExecuProjectAdapter);

            lv_execued.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(PendExecuActivity.this,MotifyExecuActivity.class);
                    intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
                    intent.putExtra("PendExecuBeanList", (Serializable)execuProjectBeanLists.get(position));
                    startActivity(intent);
                }
            });


        }*/
    }

    @Override
    public void onLoadMoreServicePackDetailSuccess(HttpResultBaseBean<List<PendExecuBeanList>> bean) {

    }

    @Override
    public void onServicePackDetailFailure(String message) {

    }

    @Override
    public void onDeleteExecuServeritemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

    }

    @Override
    public void onLoadDeleteExecuServeritemFailure(String message) {

    }




    public String getDeliveredStringByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return Constants.EMPTY_STRING;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return Constants.EMPTY_STRING;
            } else {
                String result = intent.getStringExtra(key);
                return result;
            }
        }
    }

    public void deleteExecuInfo(PendExecuBeanList deletedata){

/*
        String sYear =mpeopleBasebean.getYear();
        String servicePlanId =deletedata.getServicePlanID();
        String skey =deletedata.getPackageExecutiveKey

        Map<String, Object> map = ParametersFactory.getdeleteSignInfo("","");
        RequestBody body = Util.getBodyFromMap1(map);
        pendexecuPresenter.maindelete(body);*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");
        sYear = (String) this.getIntent().getSerializableExtra("sYear");
        sSignKey = (String) this.getIntent().getSerializableExtra("sSignKey");

        Map<String, String> map = ParametersFactory.getPeopleSignInfo(this, sYear,sSignKey);
        RequestBody body = Util.getBodyFromMap1(map);
        pendexecuPresenter.main(body);

        pendexecuPresenter.main1(body);

    }

    public void update(){
        mpeopleBasebean = (PeopleBaseInfoBean) this.getIntent().getSerializableExtra("mpeopleBasebean");
        sYear = (String) this.getIntent().getSerializableExtra("sYear");
        sSignKey = (String) this.getIntent().getSerializableExtra("sSignKey");

        Map<String, String> map = ParametersFactory.getPeopleSignInfo(this, sYear,sSignKey);
        RequestBody body = Util.getBodyFromMap1(map);
        pendexecuPresenter.main(body);

        pendexecuPresenter.main1(body);
    }
}
