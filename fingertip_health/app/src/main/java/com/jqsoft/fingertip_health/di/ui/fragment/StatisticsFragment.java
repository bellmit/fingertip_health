package com.jqsoft.fingertip_health.di.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.datasource.DataSourceFactory;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsSecondLevelCategoryActivity;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsThirdLevelCategoryDisasterAssistanceActivity;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsThirdLevelCategoryLowSalaryFamilyActivity;
import com.jqsoft.fingertip_health.di.ui.activity.statistics.StatisticsThirdLevelCategoryActivityNew;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
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
 * 统计一级分类
 * Created by Administrator on 2017-12-28.
 */

public class StatisticsFragment extends AbstractFragment {
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;

    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    private CompositeSubscription compositeSubscription;


    public static final int STATISTICS_MODULE_ID_SOCIAL_RESCUE = 1;
    public static final int STATISTICS_MODULE_ID_PREVENT_DISASTER = 2;
    public static final int STATISTICS_MODULE_ID_SOCIAL_RULE = 3;
    public static final int STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE = 4;
    public static final int STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM = 5;

    public static final String STATISTICS_MODULE_TITLE_SOCIAL_RESCUE = "社会救助";
    public static final String STATISTICS_MODULE_TITLE_PREVENT_DISASTER = "防灾减灾救灾";
    public static final String STATISTICS_MODULE_TITLE_SOCIAL_RULE = "社会治理";
    public static final String STATISTICS_MODULE_TITLE_CIVIL_AFFAIRS_SOCIAL_SERVICE = "民政社会服务";
    public static final String STATISTICS_MODULE_TITLE_NATIONAL_DEFENSE_ARMY_REFORM = "国防和军队改革";

    private int[] idArray = {
            STATISTICS_MODULE_ID_SOCIAL_RESCUE
//            ,
////            隐藏下面四个菜单
//    STATISTICS_MODULE_ID_PREVENT_DISASTER,
//    STATISTICS_MODULE_ID_SOCIAL_RULE,
//    STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE,
//    STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM

////           , STATISTICS_MODULE_ID_SOCIAL_RESCUE,
////            STATISTICS_MODULE_ID_PREVENT_DISASTER,
////           STATISTICS_MODULE_ID_SOCIAL_RULE,
////            STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE,
////            STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM
////            ,STATISTICS_MODULE_ID_SOCIAL_RESCUE,
////            STATISTICS_MODULE_ID_PREVENT_DISASTER,
////           STATISTICS_MODULE_ID_SOCIAL_RULE,
////            STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE,
////            STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM

    };
    private int[] imageIdArray = {
            R.mipmap.g_statistics_module_social_rescue,
            R.mipmap.g_statistics_module_prevent_disaster,
            R.mipmap.g_statistics_module_social_rule,
            R.mipmap.g_statistics_module_civil_affairs_social_service,
            R.mipmap.g_statistics_module_national_defense_army_reform

//            ,R.mipmap.r_intelligent_honour_agreement_remind,
//            R.mipmap.r_sign_service_assess,
//            R.mipmap.r_appointment_sign_service,
//            R.mipmap.r_call_doctor,
//            R.mipmap.r_online_consultation,
//            R.mipmap.r_intelligent_honour_agreement_remind,
//            R.mipmap.r_sign_service_assess,
//            R.mipmap.r_appointment_sign_service,
//            R.mipmap.r_call_doctor,
//            R.mipmap.r_online_consultation

    };
    private String[] titleArray = {
            "社会救助体系", "防灾减灾救助体系","社会治理基础体系",
            "民政社会服务体系", "国防和军队改革体系"

//            ,"社会救助体系", "防灾减灾救助体系","社会治理基础体系",
//            "民政社会服务体系", "国防和军队改革体系"
//
//            ,"社会救助体系", "防灾减灾救助体系","社会治理基础体系",
//            "民政社会服务体系", "国防和军队改革体系"

    };

    private List<ImageAndTextBean> list;

    public StatisticsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        unregisterModuleClickEvent();

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    public void registerModuleClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_DID_SELECT_MODULE, ImageAndTextBean.class)
                .subscribe(new Action1<ImageAndTextBean>() {
                    @Override
                    public void call(ImageAndTextBean imageAndTextBean) {
                        gotoModule(imageAndTextBean.getId());
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
        List<View> viewList = DataSourceFactory.getStatisticsModuleViewList(getActivity());
        if (!ListUtils.isEmpty(viewList)) {
            for (int i = 0; i < viewList.size(); ++i) {
                llRoot.addView(viewList.get(i));
            }
        }
    }


    @Override
    protected void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < idArray.length; ++i){
            ImageAndTextBean bean = new ImageAndTextBean(idArray[i], imageIdArray[i], titleArray[i]);
            list.add(bean);
        }
    }

    @Override
    protected void initView() {
        if (compositeSubscription == null) {
            registerModuleClickEvent();
        }

        addModuleListContent();

////        List<ImageAndTextBean> areaList = SimulateData.getSimulatedFunctionImageGroup();
//        List<ImageAndTextBean> moduleList = list;
//        GridLayoutManager gridLayoutManager = new FullyGridLayoutManager((Activity)getActivity(), Constants.STATISTICS_GRID_LAYOUT_COL_NUMBER);
////        GridLayoutManager gridLayoutManager = new FullyGridLayoutManagerSmoothScroll((Activity)getActivity(), Constants.STATISTICS_GRID_LAYOUT_COL_NUMBER);
//        ImageAndTextVerticalAdapter adapter = new ImageAndTextVerticalAdapter(moduleList);
////        Util.addDividerToRecyclerView(context, recyclerView, true);
//        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//                ImageAndTextBean item = (ImageAndTextBean) adapter.getItem(position);
//                LogUtil.i("has selected item title:" + item.getTitle());
////                Util.showAlert(getActivity(), "提示", Integer.toString(item.getId()));
//                gotoModule(item.getId());
////                RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_MODULE, item);
//            }
//        });
//        recyclerView.setLayoutManager(gridLayoutManager);
////        int color = getResources().getColor(R.color.separator_color);
////        recyclerView.addItemDecoration(new GridRecyclerViewSeparator(getActivity(), 1, color));
//        recyclerView.setAdapter(adapter);

    }

    private void gotoModule(int id){
        Bundle bundle = new Bundle();
        Class clazz = null;
        int secondLevelId = 0;
        switch (id){
            //社会救助体系
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE:
                clazz=StatisticsThirdLevelCategoryActivityNew.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_VERY_POOR_BRINGUP:
                clazz=StatisticsThirdLevelCategoryActivityNew.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_LOW_SALARY_FAMILY:
                clazz=StatisticsThirdLevelCategoryLowSalaryFamilyActivity.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_MEDICAL_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryActivityNew.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_TEMP_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryActivityNew.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_DISASTER_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryDisasterAssistanceActivity.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE;
                break;
            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_FAMILY_ECONOMY_CHECK:
                clazz=StatisticsThirdLevelCategoryActivityNew.class;
                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY;
                break;

            //防灾减灾救灾体系

            default:
                break;
        }
        bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, STATISTICS_MODULE_ID_SOCIAL_RESCUE);
        bundle.putInt(Constants.STATISTICS_SECOND_LEVEL_ID_KEY, secondLevelId);
//        bundle.putString(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY, bean.getTitle());
//        Util.gotoActivityWithBundle(this, StatisticsMultipageContainerActivity.class, bundle);
//        Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
        if (clazz!=null) {
            Util.gotoActivityWithBundle(getActivity(), clazz, bundle);
        } else {
//            Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
        }

//        Bundle bundle = new Bundle();
//        Class clazz = null;
//        int secondLevelId = 0;
//        switch (id){
//            //社会救助体系
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_RURAL_URBAN_SUBSISTENCE:
//                clazz=StatisticsThirdLevelCategorySubsistenceActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_VERY_POOR_BRINGUP:
//                clazz=StatisticsThirdLevelCategoryVeryPoorActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_LOW_SALARY_FAMILY:
//                clazz=StatisticsThirdLevelCategoryLowSalaryFamilyActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_MEDICAL_ASSISTANCE:
//                clazz=StatisticsThirdLevelCategoryMedicalAssistanceActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_TEMP_ASSISTANCE:
//                clazz=StatisticsThirdLevelCategoryTempAssistanceActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_DISASTER_ASSISTANCE:
//                clazz=StatisticsThirdLevelCategoryDisasterAssistanceActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE;
//                break;
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE_FAMILY_ECONOMY_CHECK:
//                clazz=StatisticsThirdLevelCategoryFamilyEconomyCheckActivity.class;
//                secondLevelId= StatisticsSecondLevelCategoryActivity.STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY;
//                break;
//
//            //防灾减灾救灾体系
//
//            default:
//                break;
//        }
//        bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, STATISTICS_MODULE_ID_SOCIAL_RESCUE);
//        bundle.putInt(Constants.STATISTICS_SECOND_LEVEL_ID_KEY, secondLevelId);
////        bundle.putString(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY, bean.getTitle());
////        Util.gotoActivityWithBundle(this, StatisticsMultipageContainerActivity.class, bundle);
////        Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
//        if (clazz!=null) {
//            Util.gotoActivityWithBundle(getActivity(), clazz, bundle);
//        } else {
////            Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
//        }


//        switch (id) {
////            case STATISTICS_MODULE_ID_SOCIAL_RESCUE:
//            case Constants.MODULE_ID_STATISTICS_SOCIAL_RESCUE:
//                Bundle bundle = new Bundle();
//                bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, STATISTICS_MODULE_ID_SOCIAL_RESCUE);
//                Util.gotoActivityWithBundle(getActivity(), StatisticsSecondLevelCategoryActivity.class, bundle);
//                break;
//            case STATISTICS_MODULE_ID_PREVENT_DISASTER:
//                showUnavailableHint();
//                break;
//            case STATISTICS_MODULE_ID_SOCIAL_RULE:
//                showUnavailableHint();
//                break;
//            case STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE:
//                showUnavailableHint();
//                break;
//            case STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM:
//                showUnavailableHint();
//                break;
//            default:
//                break;
//        }
    }

    private void showUnavailableHint(){
        Util.showToast(getActivity(), Constants.HINT_WILL_BE_AVAILABLE_SOON);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }
}
