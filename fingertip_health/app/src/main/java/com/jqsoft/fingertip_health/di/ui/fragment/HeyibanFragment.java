//package com.jqsoft.grassroots_civil_administration_platform.di.ui.fragment;
//
//import android.app.AlertDialog;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.bean.SignTeamBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.SignTeamContract;
//import com.jqsoft.fingertip_health.di.module.SignTeamFragmentModule;
//import com.jqsoft.fingertip_health.di.presenter.SignTeamFragmentPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SimpleFragmentPagerAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.APartyFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignfragment.BPartyFragment;
//
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.miguelcatalan.materialsearchview.MaterialSearchView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//
//
//public class HeyibanFragment extends AbstractFragment implements SignTeamContract.View {
//    private AlertDialog dialog;
//    @BindView(R.id.view_search_heyiban)
//    MaterialSearchView searchView;
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//    @BindView(R.id.tabs)
//    TabLayout mTabLayout;
//    @BindView((R.id.viewpager))
//    ViewPager mViewPager;
//    @BindView(R.id.tv_note)
//    TextView tv_note;
//
//
//
//
//    @Inject
//    SignTeamFragmentPresenter mPresenter;
//    private List<String> mPageTitleList = new ArrayList<String>();
//    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
//    private SimpleFragmentPagerAdapter mPagerAdapter;
//    private String code;
//    public static SignTeamBean signTeamBean;
//
//    private static HeyibanFragment instance = null;
//
//    public static HeyibanFragment getHeyibanFragment() {
//        if (instance == null) {
//            instance = new HeyibanFragment();
//        }
//        return instance;
//    }
//
//    @Override
//    protected void loadData() {
//       /* Map<String, String> map = ParametersFactory.getSignTeamInfo(Identity.getOrganizationKey(), Identity.getUserId());
//        RequestBody body = Util.getBodyFromMap(map);
//        mPresenter.getSingTeamData(body);*/
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_heyiban;
//    }
//
//    @Override
//    protected void initData() {
//        instance = this;
//    }
//
//    public void reassignToolbar() {
//        WorkbenchActivity workbenchActivity = (WorkbenchActivity) getActivity();
//        if (workbenchActivity != null) {
//            Toolbar toolbar = (Toolbar) workbenchActivity.findViewById(R.id.toolbar3);
//            workbenchActivity.setToolBarWithNoBackButtonAndNoTitle(toolbar);
//        }
//
//    }
//
//    @Override
//    protected void initView() {
//        setHasOptionsMenu(true);
//
//        mPageTitleList.add("甲方信息");
//        mPageTitleList.add("乙方信息");
//        mPageTitleList.add("服务包信息");
//        mFragmentList.add(new APartyFragment());
//        mFragmentList.add(new BPartyFragment());
//        mFragmentList.add(new CPartyFragment());
//        mPagerAdapter = new SimpleFragmentPagerAdapter(getActivity(), getActivity().getSupportFragmentManager(),
//                mFragmentList, mPageTitleList);
//        mViewPager.setOffscreenPageLimit(10);
//        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.setCurrentItem(0);
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
//
//
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////        menu.clear();
////        inflater.inflate(R.menu.option_menu, menu);
////        inflater.inflate(R.menu.menu_search, menu);
////        MenuItem item = menu.findItem(R.id.action_search);
////        searchView.setMenuItem(item);
//    }
//
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.action_search:
////                //code here
////                return true;
////            case R.id.setusenfc:
////
////                return true;
////            case R.id.setusebt:
////
////                return true;
////            case R.id.setbtconfig:
////
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
//
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addsignteamFragment(new SignTeamFragmentModule(this))
//                .inject(this);
//    }
//
//    public interface OnSuccessListener {
//        void sendData(SignTeamBean signTeamBean);
//    }
//
//    public OnSuccessListener onSuccessListener;
//
//    public void setOnSuccessListener(OnSuccessListener listener) {
//        onSuccessListener = listener;
//    }
//
//    @Override
//    public void onLoginSuccess(HttpResultBaseBean<SignTeamBean> bean) {
//        System.out.println(bean + "");
//        code = bean.getSuccess();
//        if (TextUtils.isEmpty(bean.getData().doctorName)) {
//            mTabLayout.setVisibility(View.GONE);
//            mViewPager.setVisibility(View.GONE);
//            tv_note.setVisibility(View.VISIBLE);
//            Toast.makeText(getActivity(), "不具有权限签约", Toast.LENGTH_SHORT).show();
//        } else {
//            onSuccessListener = APartyFragment.getAPartyFragment();
//            if (onSuccessListener != null) {
//                onSuccessListener.sendData(bean.getData());
//            }
//            onSuccessListener = CPartyFragment.getCPartyFragment();
//            if (onSuccessListener != null) {
//                onSuccessListener.sendData(bean.getData());
//            }
//        }
//
//    }
//
//    @Override
//    public void onLoginFailure(String message) {
//        System.out.println(message + "");
//        Toast.makeText(getActivity(), "不具有权限签约", Toast.LENGTH_SHORT).show();
//        mTabLayout.setVisibility(View.GONE);
//        mViewPager.setVisibility(View.GONE);
//        tv_note.setVisibility(View.VISIBLE);
//    }
//}
