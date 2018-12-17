//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.SignSeverPakesContract;
//import com.jqsoft.fingertip_health.di.module.SignSeverPakesActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.SignDoctorServerPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.FragmentAdapters;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.PopWindowListAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServerAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServersAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignSeverPakesAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SimpleFragmentPagerAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.BasicserverFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.CPartyFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.HigserverFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.MidserverFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.OtherserverFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.PrimaryserverFragment;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.BadgeView;
//import com.jqsoft.fingertip_health.view.CustomPopWindow;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import okhttp3.RequestBody;
//
///**
// * Created by Jerry on 2017/6/26.
// */
//
//public class SignDoctorSeverPakes extends AbstractActivity implements SignSeverPakesContract.View, SignDoctorServerAdapter.FragmentInteraction {
//    private Boolean aBoolean = false;
//    Context context;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.bottom_lay)
//    LinearLayout bottom_btn;
//    @BindView((R.id.viewpager))
//    ViewPager mViewPager;
//    @BindView(R.id.tabs)
//    TabLayout mTabLayout;
//    @BindView(R.id.tv_number)
//    TextView tv_number;//购物车数量
//    @BindView(R.id.allcount)
//    TextView allcount;//购物车总价格
//    @BindView(R.id.tv_count)
//    TextView tv_count;//服务项
//    @BindView(R.id.sure_btn)
//    Button sure_btn;
//    @BindView(R.id.bgtb)
//    TextView vt_bg;
//    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
//    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
//    private List<String> mPageTitleList = new ArrayList<String>();
//    private List<Integer> mBadgeCountList = new ArrayList<Integer>();
//    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
//    private SimpleFragmentPagerAdapter mPagerAdapter;
//    private List<BadgeView> mBadgeViews;
//    private FragmentAdapters fragmentAdapters;
//    @Inject
//    SignDoctorServerPresenter doctorServerPresenter;
//    private SignDoctorServerAdapter serverAdapter;
//    private SignDoctorServersAdapter myAdapter; //正式数据
//    private PopWindowListAdapter popWindowListAdapter;
//    private SignSeverPakesAdapter mAdapter;
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist1 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist2 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist3 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist4 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist5 = new ArrayList<>();
//    private Map<String, ArrayList<SignSeverPakesBeanList>> maplist = new HashMap<>();
//    private ArrayList<SignSeverPakesBeanList> reslist = new ArrayList<>();
//    private int tabtype;
//    private Fragment fragment;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_doctorserverpake;
//
//    }
//
//    @Override
//    protected void initData() {
//
//
//    }
//
//    private ButtonListter buttonListter;
//
//    public interface ButtonListter {
//        void setResultdata(ArrayList<SignSeverPakesBeanList> itemlist);
//    }
//
//    public void setButtonListter(ButtonListter listter) {
//        this.buttonListter = listter;
//    }
//
//    @Override
//    protected void initView() {
//        setToolBar(toolbar, Constants.EMPTY_STRING);
//
//        this.buttonListter = CPartyFragment.getCPartyFragment();
//        if (buttonListter != null) {
//            sure_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    for (int i = 0; i < reslist.size(); i++) {
//                        if (reslist.get(i).getNhcompensateProjName().equals("1")) {
//                            aBoolean = true;
//                            break;
//                        }
//                    }
//                    if (aBoolean) {
//                        buttonListter.setResultdata(reslist);
//                        finish();
//                        return;
//                    }
//                    Toast.makeText(getApplicationContext(), "请至少选择一条基础服务包", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
//        initfragment();
//        bottom_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopListView();
//                //showPopListView2();
//                vt_bg.setVisibility(View.VISIBLE);
//                vt_bg.getBackground().setAlpha(100);
//            }
//        });
//
//
//    }
//
//    public void initfragment() {
//        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        //初始化TabLayout的title
//
//        mTabLayout.addTab(mTabLayout.newTab().setText("基础包"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("初级包"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("中级包"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("高级包"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("其他"));
//        List<String> titles = new ArrayList<>();
//        titles.add("基础包");
//        titles.add("初级包");
//        titles.add("中级包");
//        titles.add("高级包");
//        titles.add("其他");
//        //初始化ViewPager的数据集
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new BasicserverFragment());//
//        fragments.add(new PrimaryserverFragment());//
//        fragments.add(new MidserverFragment());//
//        fragments.add(new HigserverFragment());//
//        fragments.add(new OtherserverFragment());//
//
//
//        //创建ViewPager的adapter
//        mViewPager.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
//        fragmentAdapters = new FragmentAdapters(getSupportFragmentManager(), fragments, titles);
//        mViewPager.setAdapter(fragmentAdapters);
//        //千万别忘了，关联TabLayout与ViewPager
//        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(fragmentAdapters);
//
//    }
//
//    @Override
//    protected void loadData() {
//        Map<String, String> map = ParametersFactory.getSignDoctorServerList(this);
//        RequestBody body = Util.getBodyFromMap(map);
//        doctorServerPresenter.main(body);
//
//    }
//
//
//    private CustomPopWindow mListPopWindow;
//
//    private void showPopListView() {
//        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
//        //处理popWindow 显示内容
//        handleListView(contentView);
//        //创建并显示popWindow
//        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
//                .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
//                .setBgDarkAlpha(0.6f) // 控制亮度
//                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                       vt_bg.setVisibility(View.GONE);
//                    }
//                })
//                .setView(contentView)
//                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
//                .create()
//                .showAsDropDown(bottom_btn, 0,3);
//
//    }
//
//    private void handleListView(View contentView) {
//        ListView listView = (ListView) contentView.findViewById(R.id.poplistview);
//        TextView detaddlist = (TextView) contentView.findViewById(R.id.detaddlist);
//        popWindowListAdapter = new PopWindowListAdapter(this, reslist);
//        listView.setAdapter(popWindowListAdapter);
//        popWindowListAdapter.notifyDataSetChanged();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(getApplicationContext(), DoctorServerDetails.class);
//                intent.putExtra("", reslist.get(position).getFwmc());
//                startActivity(intent);
//            }
//        });
//        detaddlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<String> listKey = new ArrayList<String>();
//                for(int i =0;i<reslist.size();i++){
//                    String deleteKey = Util.trimString(reslist.get(i).getKey());
//                    listKey.add(deleteKey);
//                }
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE,
//                        listKey);
//                reslist.clear();
//                popWindowListAdapter.notifyDataSetChanged();
//                setbottom();
//            }
//        });
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addserverpakes2(new SignSeverPakesActivityModule(this))
//                .inject(this);
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean) {
//        Util.hideGifProgressDialog(this);
//        Util.showToast(this, Constants.GET_SERVER_SUCCESS);
//        datalist.addAll(bean.getData());
//        dividedata();
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean) {
//
//    }
//
//    public void dividedata() {
//        for (int i = 0; i < datalist.size(); i++) {
//            // String nhcompensateProjName = Util.getDecodedBase64String(datalist.get(i).getNhcompensateProjName());
//            if (datalist.get(i).getNhcompensateProjName().equals("1") || TextUtils.isEmpty(datalist.get(i).getNhcompensateProjName())) {
//                datalist1.add(datalist.get(i));
//            } else if (datalist.get(i).getNhcompensateProjName().equals("2")) {
//                datalist2.add(datalist.get(i));
//            } else if (datalist.get(i).getNhcompensateProjName().equals("3")) {
//                datalist3.add(datalist.get(i));
//            } else if (datalist.get(i).getNhcompensateProjName().equals("4")) {
//                datalist4.add(datalist.get(i));
//            } else if (datalist.get(i).getNhcompensateProjName().equals("5")) {
//                datalist5.add(datalist.get(i));
//            }
//
//        }
//        fragment = fragmentAdapters.getFragments().get(0);
//        if (fragment != null) {
//            ((BasicserverFragment) fragment).setDatalist(datalist1);
//        }
//        fragment = fragmentAdapters.getFragments().get(1);
//        if (fragment != null) {
//            ((PrimaryserverFragment) fragment).setDatalist(datalist2);
//        }
//        fragment = fragmentAdapters.getFragments().get(2);
//        if (fragment != null) {
//            ((MidserverFragment) fragment).setDatalist(datalist3);
//        }
//        fragment = fragmentAdapters.getFragments().get(3);
//        if (fragment != null) {
//            ((HigserverFragment) fragment).setDatalist(datalist4);
//        }
//        fragment = fragmentAdapters.getFragments().get(4);
//        if (fragment != null) {
//            ((OtherserverFragment) fragment).setDatalist(datalist5);
//        }
//    }
//
//
//    @Override
//    public void onLoadListFailure(String message) {
//
//    }
//
//    private void initBadgeViews() {
//        if (mBadgeViews == null) {
//            mBadgeViews = new ArrayList<BadgeView>();
//            for (int i = 0; i < mFragmentList.size(); i++) {
//                BadgeView tmp = new BadgeView(this);
//                tmp.setBadgeMargin(0, 6, 10, 0);
//                tmp.setTextSize(10);
//                mBadgeViews.add(tmp);
//            }
//        }
//    }
//
//    private void setUpTabBadge() {
//        // 1. 最简单
//        for (int i = 0; i < 5; i++) {
//            mBadgeViews.get(i).setTargetView(((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(i));
//            mBadgeViews.get(i).setText(Integer.toString(mBadgeCountList.get(i)));
//        }
//    }
//
//
//    // 3.2 +实现接口，实现回调
//    @Override
//    public void process(ArrayList<SignSeverPakesBeanList> data) {
//        reslist.addAll(data);
//        //  Toast.makeText(this, reslist.size() + "", Toast.LENGTH_SHORT).show();
//        if (data.size() > 0) {
////            TextView tv_number;//购物车数量
////            TextView allcount;//购物车总价格
////            TextView tv_count;//服务项
////            Button sure_btn;
//            tv_number.setText(reslist.size() + "");
//            tv_count.setText("共" + reslist.size() + "项服务");
//            double jjj = toAddCalculate(reslist);
//            allcount.setText(Util.getStringdouble(Double.toString(jjj)) + "");
//            bottom_btn.setVisibility(View.VISIBLE);
//        } else {
//            bottom_btn.setVisibility(View.GONE);
//        }
//
//    }
//
//    @Override
//    public void uncheck(String str) {
//        for (int i = 0; i < reslist.size(); i++) {
//            if (str.equals(reslist.get(i).getKey())) {
//                reslist.remove(i);
//                tv_number.setText(reslist.size() + "");
//                tv_count.setText("共" + reslist.size() + "项服务");
//                double jieguo = toAddCalculate(reslist);
//                allcount.setText(Util.getStringdouble(Double.toString(jieguo)));
//            }
//        }
//        if (reslist.size() == 0) {
//            bottom_btn.setVisibility(View.GONE);
//        }
//
//    }
//
//    private double toAddCalculate(ArrayList<SignSeverPakesBeanList> result) {
//        double heji = 0.00;
//        double tmperes = 0;
//        for (int i = 0; i < result.size(); i++) {
//            String res = result.get(i).getSjzfje();//自付费用
//            tmperes = Util.getDoubleFromString(res);
//            heji = heji + tmperes;
//        }
//
//        return heji;
//    }
//
//
//    public void detItemcount(ArrayList<SignSeverPakesBeanList> itemlist, int a) {
//        double itemcount = Util.getDoubleFromString(itemlist.get(a).getSjzfje());
//        double mm = (Util.getDoubleFromString(allcount.getText().toString())) - itemcount;
//        allcount.setText(Util.getStringdouble(Double.toString(mm)) + "");
//        //  reslist.remove(a);
//        System.out.println(reslist);
//    }
//
//    public void detItem() {
//        tv_number.setText(reslist.size() - 1 + "");
//        tv_count.setText("共" + tv_number.getText().toString() + "项服务");
//    }
//
//    public void setbottom() {
//        mListPopWindow.dissmiss();
//        bottom_btn.setVisibility(View.GONE);
//    }
//
//
//    public void showPopListView2() {
//
//    }
//
//}
