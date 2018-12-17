package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultBean;
import com.jqsoft.fingertip_health.content.AccumulatedAssessStarLevelContent;
import com.jqsoft.fingertip_health.content.PackageColorAndNumberContent;
import com.jqsoft.fingertip_health.di.contract.MySignInfoActivityContract;
import com.jqsoft.fingertip_health.di.module.MySignInfoActivityModule;
import com.jqsoft.fingertip_health.di.presenter.MySignInfoActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.AnnualSignInfoOverviewFragmentNew;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.DonutDivisionPercentageView;
import com.jqsoft.fingertip_health.view.PagePointView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017-07-04.
 */
//我的签约信息
public class MySignInfoActivity extends AbstractActivity implements MySignInfoActivityContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.point)
    PagePointView ppvIndicator;
    @BindView(R.id.lay_sign_info_overview_load_failure)
    View laySignInfoOverviewLoadFailure;
    @BindView(R.id.ll_star_container)
    LinearLayout llStarContainer;
    @BindView(R.id.ddpv_package)
    DonutDivisionPercentageView ddpvPackage;
    @BindView(R.id.ll_package_number_container)
     LinearLayout llPackageNumberContainer;


    List<Fragment> mFragments = new ArrayList<>();
    List<SignInfoOverviewResultBean> signInfoOverviewList = new ArrayList<>();

    MyPagerAdapter adapter;

    @Inject
    MySignInfoActivityPresenter mPresenter;

    private CompositeSubscription arrowClickSubscription;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_sign_info;
    }

    @Override
    protected void initData() {
        mFragments.clear();

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
        vpContent.setOffscreenPageLimit(Constants.MAX_OFFSCREEN_PAGE_LIMIT);

        laySignInfoOverviewLoadFailure.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                loadSignInfoOverviewData();
            }
        });

    }

    @Override
    protected void loadData() {
        if (this.arrowClickSubscription == null) {
            registerArrowClickEvent();
        }

        loadSignInfoOverviewData();

    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addMySignInfoActivity(new MySignInfoActivityModule(this))
                .inject(this);

    }

    public void registerArrowClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_DID_CLICK_LEFT_RIGHT_ARROW_IN_MY_SIGN_INFO_OVERVIEW, Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        vpContent.setCurrentItem(integer, true);
                    }
                });
        if (this.arrowClickSubscription == null) {
            arrowClickSubscription = new CompositeSubscription();
        }
        arrowClickSubscription.add(mSubscription);
    }

    public void unregisterArrowClickEvent(){
        if (arrowClickSubscription != null && arrowClickSubscription.hasSubscriptions()) {
            arrowClickSubscription.unsubscribe();
        }

    }

    public void loadSignInfoOverviewData() {
        String organizationKey = getOrganizationKey();
        String userId = getUserId();
        Map<String, String> map = ParametersFactory.getSignInfoOverviewMap(this,  organizationKey, userId);
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.getSignInfoOverview(body);
    }

    private List<SignInfoOverviewResultBean> getOrganizationSignInfoOverviewList(List<SignInfoOverviewResultBean> list){
        List<SignInfoOverviewResultBean> result = new ArrayList<>();
        if (!ListUtils.isEmpty(list)) {
            for (SignInfoOverviewResultBean item : list) {
                String type = item.getType();
                if (Constants.TYPE_CURRENT_INSTITUTION.equals(type)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    public void showSignInfoOverviewData(List<SignInfoOverviewResultBean> list) {
        if (list != null) {
            this.signInfoOverviewList = list;
            List<SignInfoOverviewResultBean> filteredList = getOrganizationSignInfoOverviewList(list);
            mFragments.clear();
            for (int i = 0; i < filteredList.size(); ++i) {
                SignInfoOverviewResultBean item = filteredList.get(i);
//                for (int j = 0; j < Constants.NUMBER_OF_SIGN_INFO_OVERVIEW_PER_YEAR; ++j){
                Bundle args = new Bundle();
                args.putString(Constants.CONTAINER_TYPE_KEY, Constants.TYPE_INDEX);
                args.putSerializable(Constants.SIGN_INFO_KEY, item);
                args.putInt(Constants.TOTAL_SIGN_INFO_OVERVIEW_NUMBER_KEY, filteredList.size());
                args.putInt(Constants.SIGN_INFO_OVERVIEW_INDEX_KEY, i);
//                    args.putString(Constants.CLINIC_NAME_KEY, Identity.getOrganizationName());
//                    args.putString(Constants.YEAR_KEY, item.getYear());
//                    String count = Constants.EMPTY_STRING;
//                    String ratio = Constants.EMPTY_STRING;
//                    String hint = Constants.EMPTY_STRING;
//                    if (j==0){
//                        count=item.getQyPaidCount();
//                        ratio=item.getQyPaidLv();
//                        hint=Constants.HINT_PAID_SIGN;
//                    } else if (j==1){
//                        count=item.getQyOldCount();
//                        ratio=item.getQyOldLv();
//                        hint=Constants.HINT_ELDERLY_PEOPLE;
//                    } else if (j==2){
//                        count=item.getQyGxyCount();
//                        ratio=item.getQyGxyLv();
//                        hint=Constants.HINT_HYPERTENSION;
//                    } else if (j==3){
//                        count=item.getQyTnbCount();
//                        ratio=item.getQyTnbLv();
//                        hint=Constants.HINT_DIABETES;
//                    }
//                    args.putString(Constants.TOTAL_FEE_SIGN_NUMBER, count);
//                    args.putString(Constants.SIGN_RATIO, ratio);
//                    args.putString(Constants.SIGN_INFO_HINT, hint);
//                AnnualSignInfoOverviewFragment fragment = new AnnualSignInfoOverviewFragment();
                AnnualSignInfoOverviewFragmentNew fragment = new AnnualSignInfoOverviewFragmentNew();
                fragment.setArguments(args);
                mFragments.add(fragment);

//                }

            }
            vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    showSignInfoOverview(signInfoOverviewList, position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            adapter.notifyDataSetChanged();
            ppvIndicator.setViewPager(vpContent);
        }

    }

    public void showStarLevel(List<SignInfoOverviewResultBean> list, int index){
        if (!ListUtils.isEmpty(list)){
//            SignInfoOverviewResultBean bean = list.get(0);
            SignInfoOverviewResultBean bean = getCurrentDoctorLatestYearSignInfoOverviewResultBean(list, index);
            AccumulatedAssessStarLevelContent starLevelContent = new AccumulatedAssessStarLevelContent(this);
            String hint = getStarLevelHintString();
            int starLevel = getStarLevel(bean);
            starLevelContent.initView(hint, starLevel);
            llStarContainer.removeAllViews();
            llStarContainer.addView(starLevelContent.getView());
        }
    }

    private void showDonutDivisionPercentageView(List<SignInfoOverviewResultBean> list, int index){
        if (!ListUtils.isEmpty(list)){
//            SignInfoOverviewResultBean bean = list.get(0);
            SignInfoOverviewResultBean bean = getCurrentDoctorLatestYearSignInfoOverviewResultBean(list, index);
            ddpvPackage.setHint(getResources().getString(R.string.hint_total_paid_package));
            String value = bean.getQyPaidPack();
            ddpvPackage.setValue(Util.trimString(value));
            List<Float> numberList = getNumberList(bean);
            ddpvPackage.setNumberList(numberList);
            ddpvPackage.setColorList(getColorList());
        }
    }

    private void showPackageNameAndNumberView(List<SignInfoOverviewResultBean> list, int index){
        if (!ListUtils.isEmpty(list)){
//            SignInfoOverviewResultBean bean = list.get(0);
            SignInfoOverviewResultBean bean = getCurrentDoctorLatestYearSignInfoOverviewResultBean(list, index);
            List<Integer> colorList = getColorList();
            if (colorList==null&&colorList.size()<4){
                return;
            }
            llPackageNumberContainer.removeAllViews();
            String baseNumber = bean.getQyBasisCount();
            String juniorNumber = bean.getQyJuniorPack();
            String intermediateNumber = bean.getQyIntermediatePack();
            String advancedNumber = bean.getQyAdvancedPack();
            PackageColorAndNumberContent contentBase = new PackageColorAndNumberContent(this);
            contentBase.initView(colorList.get(0), Constants.PACKAGE_BASE, baseNumber);
            llPackageNumberContainer.addView(contentBase.getView());
            PackageColorAndNumberContent contentJunior = new PackageColorAndNumberContent(this);
            contentJunior.initView(colorList.get(1), Constants.PACKAGE_JUNIOR, juniorNumber);
            llPackageNumberContainer.addView(contentJunior.getView());
            PackageColorAndNumberContent contentIntermediate = new PackageColorAndNumberContent(this);
            contentIntermediate.initView(colorList.get(2), Constants.PACKAGE_INTERMEDIATE, intermediateNumber);
            llPackageNumberContainer.addView(contentIntermediate.getView());
            PackageColorAndNumberContent contentAdvanced = new PackageColorAndNumberContent(this);
            contentAdvanced.initView(colorList.get(3), Constants.PACKAGE_ADVANCED, advancedNumber);
            llPackageNumberContainer.addView(contentAdvanced.getView());
        }
    }

    private SignInfoOverviewResultBean getCurrentDoctorLatestYearSignInfoOverviewResultBean(List<SignInfoOverviewResultBean> list, int index){
        SignInfoOverviewResultBean result = new SignInfoOverviewResultBean();
        if (!ListUtils.isEmpty(list)){
            SignInfoOverviewResultBean bean = list.get(index);
            String year = bean.getYear();
            for (int i = 0; i < list.size(); ++i){
                SignInfoOverviewResultBean item = list.get(i);
                String type = item.getType();
                String someYear = Util.trimString(item.getYear());
                if (Constants.TYPE_CURRENT_DOCTOR.equals(type) && someYear.equals(year)){
                    result=item;
                    break;
                }
            }
        }
        return result;
    }

//    public String getMySignNumber(List<SignInfoOverviewResultBean> list, int index) {
//        String result = Constants.ZERO_STRING;
//        if (!ListUtils.isEmpty(list)) {
//            SignInfoOverviewResultBean bean = list.get(index);
//            String year = bean.getYear();
//            for (SignInfoOverviewResultBean item : list) {
//                String type = item.getType();
//                String someYear = Util.trimString(item.getYear());
//                if (Constants.TYPE_CURRENT_DOCTOR.equals(type) && someYear.equals(year)) {
//                    result = item.getQyCount();
//                    break;
//                }
//            }
//        }
//        return result;
//    }


    private List<Float> getNumberList(SignInfoOverviewResultBean bean){
        List<Float> result = new ArrayList<>();
        if (bean!=null){
            Float base = Util.getFloatFromString(bean.getQyBasisCount());
            Float junior = Util.getFloatFromString(bean.getQyJuniorPack());
            Float intermediate = Util.getFloatFromString(bean.getQyIntermediatePack());
            Float advanced = Util.getFloatFromString(bean.getQyAdvancedPack());
            result= Arrays.asList(base, junior, intermediate, advanced);
        }
        return result;
    }

    private List<Integer> getColorList(){
        List<Integer> result = new ArrayList<>();
        int base = getResources().getColor(R.color.base_package_color);
        int junior = getResources().getColor(R.color.junior_package_color);
        int intermediate = getResources().getColor(R.color.intermediate_package_color);
        int advanced = getResources().getColor(R.color.advanced_package_color);
        result = Arrays.asList(base, junior, intermediate, advanced);
        return result;
    }

    public String getStarLevelHintString(){
        String s = getResources().getString(R.string.hint_accumulated_assess_star_level);
        return s;
    }

    public int getStarLevel(SignInfoOverviewResultBean bean){
        if (bean==null){
            return 0;
        } else {
            String starSum = bean.getStarSum();
            int i = Util.getIntFromString(starSum);
            int result = Util.getValidStarNumber(i);
            return result;
        }
    }


    public void showSignInfoOverview(List<SignInfoOverviewResultBean> list, int index) {
        if (list != null) {
            this.signInfoOverviewList = list;
//            List<SignInfoOverviewResultBean> list = bean.getList();
            if (!ListUtils.isEmpty(list)) {
//                showSignInfoOverviewData(list);
                showStarLevel(list, index);
                showDonutDivisionPercentageView(list, index);
                showPackageNameAndNumberView(list, index);
            }
        } else {
            this.signInfoOverviewList = new ArrayList<>();
        }
//        String signInfoOverviewString = getSignInfoOverviewString(bean);
//        tvSignInfoOverview.setText(signInfoOverviewString);
//        tvHonourAgreementHint.setText(Constants.HINT_INTELLIGENT_HONOUR_AGREEMENT_ALERT);
//        if (bean!=null){
//            List<SignNumberAndRatioBean> list = bean.getSignNumberAndRatioList();
//            if (!ListUtils.isEmpty(list)){
//                for (SignNumberAndRatioBean item : list){
//                    SignNumberAndRatioContent content = new SignNumberAndRatioContent(getActivity());
//                    content.initView(item);
//                    llSignInfoDetail.addView(content.getView());
//                }
//            }
//            String mySignInfo = Constants.HINT_NUMBER_OF_MY_SIGN+bean.getMySignedNumber()+Constants.PERSON;
//            tvSignInfoMe.setText(mySignInfo);
//
//            ImageView latest7DayImageView = getImageViewFromView(litLatest7Days);
//            TextView latest7DayTextView = getTextViewFromView(litLatest7Days);
//            latest7DayImageView.setImageResource(R.mipmap.person);
//            String latest7DayText = Constants.HINT_NUMBER_OF_PROJECTS_LATEST_7_DAYS+bean.getNumberOfProjectsNeedToExecute()+Constants.PERSON_CI;
//            latest7DayTextView.setText(latest7DayText);
//            litLatest7Days.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    Util.gotoExecutionProjectsActivity(getActivity(), ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days);
//                }
//            });
//
//            ImageView timeoutImageView = getImageViewFromView(litTimeout);
//            TextView timeoutTextView = getTextViewFromView(litTimeout);
//            timeoutImageView.setImageResource(R.mipmap.person);
//            String timeoutText = Constants.HINT_NUMBER_OF_PROJECTS_TIMEOUT+bean.getNumberOfProjectsTimeout()+Constants.PERSON_CI;
//            timeoutTextView.setText(timeoutText);
//            litTimeout.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    Util.gotoExecutionProjectsActivity(getActivity(), ExecutionProjectsType.ExecutionProjectsTypeEnum.Timeout);
//                }
//            });
//
//            ImageView newServiceAssessImageView = getImageViewFromView(litServiceAssess);
//            TextView newServiceAssessTextView = getTextViewFromView(litServiceAssess);
//            newServiceAssessImageView.setImageResource(R.mipmap.person);
//            String newServiceAssessText = Constants.YOU_HAVE+bean.getNumberOfNewServiceAssess()+Constants.HINT_NEW_SERVICE_ASSESS_INFO;
//            newServiceAssessTextView.setText(newServiceAssessText);
//
//            ImageView newSignApplicationImageView = getImageViewFromView(litSignApplication);
//            TextView newSignApplicationTextView = getTextViewFromView(litSignApplication);
//            newSignApplicationImageView.setImageResource(R.mipmap.person);
//            String newSignApplicationText = Constants.YOU_HAVE+bean.getNumberOfNewSignedApplication()+Constants.HINT_NEW_SIGN_APPLICATION_INFO;
//            newSignApplicationTextView.setText(newSignApplicationText);
//
//            ImageView appointmentSignImageView = getImageViewFromView(litAppointmentSign);
//            TextView appointmentSignTextView = getTextViewFromView(litAppointmentSign);
//            appointmentSignImageView.setImageResource(R.mipmap.person);
//            String appointmentSignText = Constants.YOU_HAVE+bean.getNumberOfAppointmentSignServiceService()+Constants.HINT_APPOINTMENT_SIGN_SERVICE_INFO;
//            appointmentSignTextView.setText(appointmentSignText);
//        } else {
//
//        }
    }

    public void showSignInfoOverviewOrFailureView(boolean success) {
        if (success) {
//            vpContent.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.VISIBLE);
            laySignInfoOverviewLoadFailure.setVisibility(View.GONE);
        } else {
            Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//            vpContent.setVisibility(View.GONE);
            llContent.setVisibility(View.GONE);
            laySignInfoOverviewLoadFailure.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadSignOverviewDataSuccess(HttpResultBaseBean<List<SignInfoOverviewResultBean>> bean) {
        if (bean != null) {
            List<SignInfoOverviewResultBean> list = bean.getData();
            if (list != null) {
                showSignInfoOverviewData(list);
                showSignInfoOverview(list, 0);
                showSignInfoOverviewOrFailureView(true);
            } else {
                showSignInfoOverviewOrFailureView(false);
            }
        } else {
            showSignInfoOverviewOrFailureView(false);
        }
//        SignInfoOverviewResultWrapperBean simulatedBean = SimulateData.getSignInfoOverviewResultWrapperBean();
//        showSignInfoOverview(simulatedBean);

    }

    @Override
    public void onLoadSignOverviewDataFailure(String message) {
        showSignInfoOverviewOrFailureView(false);
//        SignInfoOverviewResultWrapperBean simulatedBean = SimulateData.getSignInfoOverviewResultWrapperBean();
//        showSignInfoOverview(simulatedBean);

//        showSignInfoOverview(null);
    }

//    @Override
//    public void onLoadSignOverviewDataSuccess(HttpResultBaseBean<List<SignInfoOverviewResultBean>> bean) {
//        if (bean!=null) {
//            List<SignInfoOverviewResultBean> list = bean.getData();
//            SignInfoOverviewResultBean item = SimulateData.getSignInfoOverviewResultBean();
//            list.clear();
//            list.add(item);
////            List<SignInfoOverviewResultBean> specificList = getSpecificDoctorSignInfoList(list);
//            List<SignInfoOverviewResultBean> specificList = list;
//            if (specificList!=null) {
//                showSignInfoOverview(specificList);
//                showSignInfoOverviewOrFailureView(true);
//            } else {
//                showSignInfoOverviewOrFailureView(false);
//            }
//        } else {
//            showSignInfoOverviewOrFailureView(false);
//        }
////        SignInfoOverviewResultWrapperBean simulatedBean = SimulateData.getSignInfoOverviewResultWrapperBean();
////        showSignInfoOverview(simulatedBean);
//
//    }
//
//    @Override
//    public void onLoadSignOverviewDataFailure(String message) {
//        showSignInfoOverviewOrFailureView(false);
////        SignInfoOverviewResultWrapperBean simulatedBean = SimulateData.getSignInfoOverviewResultWrapperBean();
////        showSignInfoOverview(simulatedBean);
//
////        showSignInfoOverview(null);
//    }

    private List<SignInfoOverviewResultBean> getSpecificDoctorSignInfoList(List<SignInfoOverviewResultBean> allList){
        if (ListUtils.isEmpty(allList)){
            return null;
        } else {
            List<SignInfoOverviewResultBean> result = new ArrayList<>();
            for (int i = 0; i < allList.size(); ++i){
                SignInfoOverviewResultBean item = allList.get(i);
                String type = item.getType();
                if (Constants.TYPE_CURRENT_DOCTOR.equals(type)){
                    result.add(item);
                } else {
                    continue;
                }
            }
            return result;
        }
    }

    private String getOrganizationKey() {
        return Identity.getOrganizationKey();
    }

    private String getUserId() {
        return Identity.getUserId();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterArrowClickEvent();
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
            return Constants.EMPTY_STRING;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
