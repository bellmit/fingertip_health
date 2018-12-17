package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ImageAndTextVerticalAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.StatisticsFragment;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 统计二级分类
 * Created by Administrator on 2017-12-29.
 */

public class StatisticsSecondLevelCategoryActivity extends AbstractActivity {
    //社会救助
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE=1;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE=2;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY=3;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE=4;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE=5;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE=6;
    public static final int STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY=7;

    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_URBAN_RURAL_SUBSISTENCE="城乡低保";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_RAISE_POOR_PEOPLE="特困人员供养";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_LOW_SALARY_FAMILY="低收入家庭";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_MEDICAL_ASSISTANCE="医疗救助";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_TEMPORARY_ASSISTANCE="临时救助";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_DISASTER_ASSISTANCE="受灾救助";
    public static final String STATISTIC_SOCIAL_RESCUE_SECOND_LEVEL_TITLE_CHECK_FAMILY_ECONOMY="家庭经济情况核对";


    @BindView(R.id.statistics_second_level_category_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int statisticsFirstLevelId;

    private String toolbarTitle;

    private int[] selectedIdArray;
    private int[] selectedImageIdArray;
    private String[] selectedTitleArray;

    private List<ImageAndTextBean> list;


    //社会救助
    private int[] socialRescueIdArray = new int[]{STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE,
    STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY};

    private int[] socialRescueImageIdArray = new int[]{
            R.mipmap.g_social_rescue_module_urban_rural_subsistence,
            R.mipmap.g_social_rescue_module_raise_poor_people,
            R.mipmap.g_social_rescue_module_low_salary_family,
            R.mipmap.g_social_rescue_module_medical_assistance,
            R.mipmap.g_social_rescue_module_temporary_assistance,
            R.mipmap.g_social_rescue_module_disaster_assistance,
            R.mipmap.g_social_rescue_module_check_family_economy
    };

    private String[] socialRescueTitleArray = new String[]{
            "城乡低保",
            "特困人员供养",
            "低收入家庭",
            "医疗救助",
            "临时救助",
            "受灾救助",
            "家庭经济情况核对"
    };

    private void populateToolbarTitle(){
        switch (statisticsFirstLevelId){
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RESCUE:
                toolbarTitle = StatisticsFragment.STATISTICS_MODULE_TITLE_SOCIAL_RESCUE;
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_PREVENT_DISASTER:
                toolbarTitle = StatisticsFragment.STATISTICS_MODULE_TITLE_PREVENT_DISASTER;
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RULE:
                toolbarTitle = StatisticsFragment.STATISTICS_MODULE_TITLE_SOCIAL_RULE;
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE:
                toolbarTitle = StatisticsFragment.STATISTICS_MODULE_TITLE_CIVIL_AFFAIRS_SOCIAL_SERVICE;
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM:
                toolbarTitle = StatisticsFragment.STATISTICS_MODULE_TITLE_NATIONAL_DEFENSE_ARMY_REFORM;
                break;
            default:
                break;
        }

    }

    private void populateSelectedFirstLevelCorrespondingInfo(){
        switch (statisticsFirstLevelId){
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RESCUE:
                selectedIdArray = socialRescueIdArray;
                selectedImageIdArray = socialRescueImageIdArray;
                selectedTitleArray = socialRescueTitleArray;
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_PREVENT_DISASTER:
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_SOCIAL_RULE:
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_CIVIL_AFFAIRS_SOCIAL_SERVICE:
                break;
            case StatisticsFragment.STATISTICS_MODULE_ID_NATIONAL_DEFENSE_ARMY_REFORM:
                break;
            default:
                break;
        }
    }

    private void initModelList(){
        list = new ArrayList<>();
        if (selectedIdArray!=null) {
            for (int i = 0; i < selectedIdArray.length; ++i){
                ImageAndTextBean bean = new ImageAndTextBean(selectedIdArray[i], selectedImageIdArray[i], selectedTitleArray[i]);
                list.add(bean);
            }
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics_second_level_category_layout;
    }

    @Override
    protected void initData() {
        statisticsFirstLevelId = getDeliveredIntByKey(Constants.STATISTICS_FIRST_LEVEL_ID_KEY);
        populateToolbarTitle();
        populateSelectedFirstLevelCorrespondingInfo();
        initModelList();
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        tvTitle.setText(toolbarTitle);

        //        List<ImageAndTextBean> areaList = SimulateData.getSimulatedFunctionImageGroup();
        List<ImageAndTextBean> moduleList = list;
        GridLayoutManager gridLayoutManager = new FullyGridLayoutManager(this, Constants.STATISTICS_GRID_LAYOUT_SECOND_LEVEL_COL_NUMBER);
//        GridLayoutManager gridLayoutManager = new FullyGridLayoutManagerSmoothScroll((Activity)getActivity(), Constants.STATISTICS_GRID_LAYOUT_COL_NUMBER);
        ImageAndTextVerticalAdapter adapter = new ImageAndTextVerticalAdapter(moduleList);
//        Util.addDividerToRecyclerView(context, recyclerView, true);
        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                ImageAndTextBean item = (ImageAndTextBean) adapter.getItem(position);
                LogUtil.i("has selected item title:" + item.getTitle());
//                Util.showAlert(StatisticsSecondLevelCategoryActivity.this, "提示", Integer.toString(item.getId()));
                gotoModule(item);
//                RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_MODULE, item);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
//        int color = getResources().getColor(R.color.separator_color);
//        recyclerView.addItemDecoration(new GridRecyclerViewSeparator(this, 1, color));
        recyclerView.setAdapter(adapter);


    }

    private void gotoModule(ImageAndTextBean bean) {
        if (bean==null){
            return;
        }
        int id = bean.getId();
        Bundle bundle = new Bundle();
        Class clazz = null;
        switch (id){
            //社会救助体系
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_URBAN_RURAL_SUBSISTENCE:
                clazz=StatisticsThirdLevelCategorySubsistenceActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_RAISE_POOR_PEOPLE:
                clazz=StatisticsThirdLevelCategoryVeryPoorActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_LOW_SALARY_FAMILY:
                clazz=StatisticsThirdLevelCategoryLowSalaryFamilyActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_MEDICAL_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryMedicalAssistanceActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_TEMPORARY_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryTempAssistanceActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_DISASTER_ASSISTANCE:
                clazz=StatisticsThirdLevelCategoryDisasterAssistanceActivity.class;
                break;
            case STATISTICS_SOCIAL_RESCUE_SECOND_LEVEL_MODULE_ID_CHECK_FAMILY_ECONOMY:
                clazz=StatisticsThirdLevelCategoryFamilyEconomyCheckActivity.class;
                break;

            //防灾减灾救灾体系

            default:
                break;
        }
        bundle.putInt(Constants.STATISTICS_FIRST_LEVEL_ID_KEY, statisticsFirstLevelId);
        bundle.putInt(Constants.STATISTICS_SECOND_LEVEL_ID_KEY, id);
//        bundle.putString(Constants.STATISTICS_THIRD_LEVEL_TITLE_KEY, bean.getTitle());
//        Util.gotoActivityWithBundle(this, StatisticsMultipageContainerActivity.class, bundle);
//        Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
        if (clazz!=null) {
            Util.gotoActivityWithBundle(this, clazz, bundle);
        } else {
//            Util.gotoActivityWithBundle(this, StatisticsThirdLevelCategoryActivity.class, bundle);
        }

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
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
