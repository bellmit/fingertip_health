package com.jqsoft.fingertip_health.di.ui.activity.statistics;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.datasource.DataSourceFactory;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsSecondLevelCategoryActivity;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsSpecificActivity;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018-07-26.
 */

public class StatisticsThirdLevelCategoryActivityNew extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.statistics_title)
    TextView tvStatisticsTitle;

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    private CompositeSubscription compositeSubscription;

    private int statisticsFirstLevelId;
    private int statisticsSecondLevelId;

    private String toolbarTitle;

    private void populateToolbarTitle(){
        switch (statisticsSecondLevelId) {
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_URBAN_RURAL_SUBSISTENCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_RAISE_POOR_PEOPLE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_LOW_SALARY_FAMILY;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_MEDICAL_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_TEMPORARY_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_DISASTER_ASSISTANCE;
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY:
                toolbarTitle = StatisticsSecondLevelCategoryActivity.STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_CHECK_FAMILY_ECONOMY;
                break;
            default:
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics_third_level_layout;
    }

    @Override
    protected void initData() {
        statisticsFirstLevelId= getDeliveredIntByKey(Constants.STATISTICS_FIRST_LEVEL_ID_KEY);
        statisticsSecondLevelId = getDeliveredIntByKey(Constants.STATISTICS_SECOND_LEVEL_ID_KEY);
        populateToolbarTitle();

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        tvStatisticsTitle.setText(toolbarTitle);

        if (compositeSubscription == null) {
            registerModuleClickEvent();
        }

        addModuleListContent();

    }

    private void gotoModule(ImageAndTextBean bean) {
        if (bean==null){
            return;
        }
        int id = bean.getId();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, statisticsFirstLevelId);
        bundle.putInt(Constants.STATISTICS_SECOND_LEVEL_ID_KEY, statisticsSecondLevelId);
        bundle.putInt(Constants.STATISTICS_THIRD_LEVEL_ID_KEY, id);
        bundle.putString(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY, bean.getTitle());
        Util.gotoActivityWithBundle(this, StatisticsSpecificActivity.class, bundle);
//        Util.gotoActivityWithBundle(this, StatisticsMultipageContainerActivity.class, bundle);

    }


    public void registerModuleClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_STATISTICS_DID_SELECT_CONCRETE_MODULE, ImageAndTextBean.class)
                .subscribe(new Action1<ImageAndTextBean>() {
                    @Override
                    public void call(ImageAndTextBean imageAndTextBean) {
                        gotoModule(imageAndTextBean);
                        // Util.showAlert(getActivity(), "提示", "您选择了功能" + imageAndTextBean.getId());
                    }
                });
        if (this.compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(mSubscription);
    }

    public void unregisterModuleClickEvent() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }



    private void addModuleListContent() {
//        ModuleListContentNew mlc = new ModuleListContentNew(getActivity());
        List<View> viewList = new ArrayList<>();
        viewList=getSecondLevelModuleListView();
        if (!ListUtils.isEmpty(viewList)) {
            for (int i = 0; i < viewList.size(); ++i) {
                llRoot.addView(viewList.get(i));
            }
        }
    }

    private List<View> getSecondLevelModuleListView(){
        List<View> viewList = new ArrayList<>();
        switch (statisticsSecondLevelId) {
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE:
                viewList=DataSourceFactory.getStatisticsSocialRescueSubsistenceModuleListView(this);
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE:
                viewList=DataSourceFactory.getStatisticsSocialRescueVeryPoorModuleListView(this);
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY:
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE:
                viewList=DataSourceFactory.getStatisticsSocialRescueMedicalAssistanceModuleListView(this);
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE:
                viewList=DataSourceFactory.getStatisticsSocialRescueTempAssistanceModuleListView(this);
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE:
                break;
            case StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY:
                viewList=DataSourceFactory.getStatisticsSocialRescueFamilyEconomyCheckModuleListView(this);
                break;
            default:
                break;
        }
        return viewList;
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        unregisterModuleClickEvent();

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
