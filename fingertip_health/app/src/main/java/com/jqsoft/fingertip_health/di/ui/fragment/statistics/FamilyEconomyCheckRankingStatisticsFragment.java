package com.jqsoft.fingertip_health.di.ui.fragment.statistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.statistics.FamilyEconomyCheckRankingStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.FamilyEconomyCheckRankingBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingSpecificCategoryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.FamilyEconomyCheckRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.FamilyEconomyCheckRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.FamilyEconomyCheckRankingStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsSpecificActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.feature.IUpLevel;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.helper.chart.BarDataEx;
import com.jqsoft.fingertip_health.helper.chart.HorizontalBarChartEx;
import com.jqsoft.fingertip_health.popup_window.MonthQuarterYearRangePopupWindow;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.sevenheaven.segmentcontrol.SegmentControl;

import net.qiujuer.genius.ui.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;

/**
 * 柱状图
 * 家庭经济状况核对-业务受理情况排名统计，核对报告排名统计，核对报告复议排名统计
 * Created by Administrator on 2018-01-02.
 */

public class FamilyEconomyCheckRankingStatisticsFragment extends AbstractFragment implements FamilyEconomyCheckRankingStatisticsFragmentContract.View, IUpLevel {
    @BindView(R.id.sc_date_range)
    SegmentControl scDateRange;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.ll_check_type)
    LinearLayout llCheckType;
    @BindView(R.id.tv_check_type)
    TextView tvCheckType;
//    @BindView(R.id.tv_household_type)
//    TextView tvHouseholdType;
//    @BindView(R.id.ll_result_type)
//    LinearLayout llResultType;
//    @BindView(R.id.tv_result_type)
//    TextView tvResultType;
//    @BindView(R.id.ll_support_type)
//    LinearLayout llSupportType;
//    @BindView(R.id.tv_support_type)
//    TextView tvSupportType;
    @BindView(R.id.lay_content)
    LinearLayout rlContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_first)
    TextView tvFirst;
    @BindView(R.id.tv_second)
    TextView tvSecond;
//    @BindView(R.id.tv_third)
//    TextView tvThird;
//    @BindView(R.id.tv_fourth)
//    TextView tvFourth;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_chart_hint)
    View tvChartHint;
    @BindView(R.id.hbc_chart)
    HorizontalBarChartEx hbcChart;
    @BindView(R.id.lay_family_economy_check_ranking_load_failure)
    View failureView;

    @BindView(R.id.ll_statistics_list)
    LinearLayout llStatisticsList;
    @BindView(R.id.iv_statistics_list)
    ImageView ivStatisticsList;
    @BindView(R.id.tv_statistics_list)
    TextView tvStatisticsList;

    @BindView(R.id.ll_statistics_chart)
    LinearLayout llStatisticsChart;
    @BindView(R.id.iv_statistics_chart)
    ImageView ivStatisticsChart;
    @BindView(R.id.tv_statistics_chart)
    TextView tvStatisticsChart;

    TextView tvFailureView;

    boolean isRequestSuccess = false;

    String type;//	0 业务受理情况排名统计  1核对报告排名统计   2核对报告复议排名统计

    int checkTypeIndex = Constants.CHECK_TYPE_ALL;
    String checkType;//核对项目(允许为空)	ZDSHBZ	最低生活保障    TKRYGY	特困人员供养    SZRYJZ	受灾人员救助
//    YLJZ	医疗救助    JIAOYUJZ	教育救助    ZFJZ	住房救助    JIUYEJZ	就业救助    LSJZ	临时救助

    String methodName;

    Stack<String> areaCodeStack = new Stack<>();
    List<String> currentAreaCodeList = new ArrayList<>();


    @Inject
    FamilyEconomyCheckRankingStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

//    int householdTypeIndex = Constants.HOUSEHOLD_TYPE_ALL;
//    String householdType;
//    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
//    String resultType;
//    int supportTypeIndex = Constants.RESCUE_BRINGUP_TYPE_ALL;
//    String supportType;

    FamilyEconomyCheckRankingStatisticsAdapter adapter;

    public FamilyEconomyCheckRankingStatisticsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
        return R.layout.fragment_family_economy_check_ranking_statistics_layout;
    }

    @Override
    protected void initData() {
        populateData();

        String firstAreaCode = IdentityManager.getSrcLoginResultBean(getActivity()).getAreaId();
        areaCodeStack.push(firstAreaCode);
        showUpLevelButtonAccordingly();

    }

    private boolean isChartSelected(){
        boolean b = tvStatisticsList.getCurrentTextColor()==getResources().getColor(R.color.colorTheme);
        return !b;
    }

    private void hilightChart(){
        hilightStatisticsListIndicatorView(false);
        hilightStatisticsChartIndicatorView(true);

    }

    private void hilightList(){
        hilightStatisticsListIndicatorView(true);
        hilightStatisticsChartIndicatorView(false);

    }


    @Override
    protected void initView() {
        hilightChart();

        scDateRange.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int i) {
                LogUtil.i("selected index:" + i);
                initDateRangePopupWindow(i);
                onRefresh();
            }
        });

        initDateRangePopupWindow(Constants.DATE_RANGE_TYPE_MONTH);

        RxView.clicks(tvDate)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        dateRangePopupWindow.show();
                    }
                });

        RxView.clicks(btQuery)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        onRefresh();
                    }
                });

        initCheckType(checkTypeIndex);
        RxView.clicks(tvCheckType)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        String[] typeArray = new String[]{"全部", "最低生活保障", "特困人员供养", "受灾人员救助", "医疗救助", "教育救助",
                        "住房救助", "就业救助", "临时救助"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择核对项目类型", null, typeList,
                                checkTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initCheckType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });
                    }
                });
//
//        if (Constants.RESCUE_TYPE_VALUE_SUBSISTENCE.equals(rescueType) ||
//                Constants.RESCUE_TYPE_VALUE_LOW_SALARY.equals(rescueType)){
//            llResultType.setVisibility(View.VISIBLE);
//            llSupportType.setVisibility(View.GONE);
//        } else if (Constants.RESCUE_TYPE_VALUE_VERY_POOR.equals(rescueType)){
//            llResultType.setVisibility(View.GONE);
//            llSupportType.setVisibility(View.VISIBLE);
//        } else {
//            llResultType.setVisibility(View.VISIBLE);
//            llSupportType.setVisibility(View.GONE);
//        }
//        initResultType(resultTypeIndex);
//        RxView.clicks(tvResultType)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        String[] typeArray = new String[]{"户数", "人数"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择返回结果类型", null, typeList,
//                                resultTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initResultType(which);
//                                        return false;
//                                    }
//                                });
//
//                    }
//                });
//
//        initSupportType(supportTypeIndex);
//        RxView.clicks(tvSupportType)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        String[] typeArray = new String[]{"全部", "集中供养", "分散供养"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择供养类型", null, typeList,
//                                supportTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initSupportType(which);
//                                        return false;
//                                    }
//                                });
//
//                    }
//                });

        initRecyclerViewHeaderVisibilityAndValue();

        initHorizontalBarChart();

        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
        RxView.clicks(tvFailureView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        onRefresh();
                    }
                });

        adapter = new FamilyEconomyCheckRankingStatisticsAdapter(type, new ArrayList<FamilyEconomyCheckRankingBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//            }
//        });

        RxView.clicks(llStatisticsList)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (isRequestSuccess) {
                            if (isResultEmpty()){
                                rlContent.setVisibility(View.GONE);
                                hbcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);

                            } else {
                                rlContent.setVisibility(View.VISIBLE);
                                hbcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.GONE);
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            hbcChart.setVisibility(View.GONE);
                            failureView.setVisibility(View.VISIBLE);
                            tvFailureView.setText(getFailureHint());
                            showChartHint(false);
                            hilightStatisticsListIndicatorView(true);
                            hilightStatisticsChartIndicatorView(false);

                        }
                    }
                });

        RxView.clicks(llStatisticsChart)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (isRequestSuccess) {
                            if (isResultEmpty()){
                                rlContent.setVisibility(View.GONE);
                                hbcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);

                            } else {
                                rlContent.setVisibility(View.GONE);
                                hbcChart.setVisibility(View.VISIBLE);
                                failureView.setVisibility(View.GONE);
                                showChartHint(true);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);

                            }

                        } else {
                            rlContent.setVisibility(View.GONE);
                            hbcChart.setVisibility(View.GONE);
                            failureView.setVisibility(View.VISIBLE);
                            tvFailureView.setText(getFailureHint());
                            showChartHint(false);
                            hilightStatisticsListIndicatorView(false);
                            hilightStatisticsChartIndicatorView(true);

                        }

                    }
                });
    }

    private boolean isResultEmpty(){
        return ListUtils.isEmpty(adapter.getData());
    }



    private void initRecyclerViewHeaderVisibilityAndValue(){
        if (Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING.equals(type)){
            tvTitle.setVisibility(View.VISIBLE);
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.VISIBLE);
            tvTitle.setText("辖区");
            tvFirst.setText("总人次");
            tvSecond.setText("总户次");
        } else if (Constants.FAMILY_ECONOMY_CHECK_REPORT_RANKING.equals(type)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.GONE);
            tvTitle.setText("辖区");
            tvFirst.setText("出具核对报告数");
            tvSecond.setText(Constants.EMPTY_STRING);

        } else if (Constants.FAMILY_ECONOMY_CHECK_REVIEW_RANKING.equals(type)){
            tvTitle.setVisibility(View.VISIBLE);
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.GONE);
            tvTitle.setText("辖区");
            tvFirst.setText("出具复议核对报告数");
            tvSecond.setText(Constants.EMPTY_STRING);

        }
    }

    @Override
    public void upLevelDidClick(){
        if (areaCodeStack.size()>=2){
            String code = areaCodeStack.pop();
            onRefresh();
        }
        showUpLevelButtonAccordingly();
    }

    public void showUpLevelButtonAccordingly() {
        if (areaCodeStack.size()<=1){
            showUpLevelButton(false);
        } else {
            showUpLevelButton(true);
        }
    }

    public void showUpLevelButton(boolean show){
        Activity activity = getActivity();
        if (activity instanceof StatisticsSpecificActivity){
            StatisticsSpecificActivity ssa = (StatisticsSpecificActivity)activity;
            ssa.showUpLevelButton(show);
        }
    }

    private void initHorizontalBarChartClickListener(){
        hbcChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e!=null && e instanceof BarEntry) {
                    BarEntry barEntry = (BarEntry)e;
                    float floatIndex = barEntry.getX();
                    int intIndex = (int)floatIndex;
                    if (intIndex>=0 && intIndex<currentAreaCodeList.size()){
                        String code = currentAreaCodeList.get(intIndex);
                        if (!StringUtils.isBlank(code)) {
//                            areaCodeStack.push("340103007");
                            areaCodeStack.push(code);
                            showUpLevelButtonAccordingly();
                            onRefresh();
                        }
                    }


                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }



    private void initHorizontalBarChart() {
        initHorizontalBarChartClickListener();

//        hbcChart.setOnChartValueSelectedListener(this);
        // hbcChart.setHighlightEnabled(false);

        hbcChart.setNoDataText(Constants.CHART_NO_DATA_TEXT);
        hbcChart.setNoDataTextColor(getResources().getColor(R.color.colorTheme));
        hbcChart.setDrawBarShadow(false);

        hbcChart.setDrawValueAboveBar(true);

        hbcChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        hbcChart.setMaxVisibleValueCount(Constants.CHART_MAX_NO_VALUES_ENTRY_NUMBER);

        // scaling can now only be done on x- and y-axis separately
        hbcChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // hbcChart.setDrawBarShadow(true);

        hbcChart.setDrawGridBackground(false);

        hbcChart.setHighlightPerDragEnabled(false);


        XAxis xl = hbcChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
//        xl.setGranularity(Constants.CHART_DEFAULT_BAR_WIDTH);
        xl.setGranularity(1f);
        xl.setDrawLabels(true);
        xl.setCenterAxisLabels(true);
//        xl.setLabelCount(8, true);
        xl.setXOffset(Constants.BAR_CHART_XAXIS_OFFSET);

        YAxis yl = hbcChart.getAxisLeft();
//        yl.setTypeface(mTfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = hbcChart.getAxisRight();
//        yr.setTypeface(mTfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

//        hbcChart.setFitBars(false);
        hbcChart.setFitBars(true);
        hbcChart.animateY(Constants.CHART_ANIMATION_DURATION);


        Legend l = hbcChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        l.setWordWrapEnabled(true);
    }

    private void setData(SubsistenceVarianceRankingStatisticsBean bean) {
        Util.resetHorizontalBarChartState(hbcChart);

        float barWidth = 0f;
        if (is3Column()){
            barWidth=Constants.BAR_CHART_TWO_COLUMN_BAR_WIDTH;
        } else {
            barWidth=Constants.BAR_CHART_ONE_COLUMN_BAR_WIDTH;
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();

//        float randomMultiplier = 3* 100000f;
        final List<String> titleList = bean.getxTitle();

        List<String> codeList = bean.getxCode();
        if (!ListUtils.isEmpty(codeList)){
            currentAreaCodeList=codeList;
        } else {
            currentAreaCodeList=new ArrayList<>();
        }
        Util.processStatisticsChartList(currentAreaCodeList);

        List<SubsistenceVarianceRankingSpecificCategoryBean> categoryList = bean.getList();
        List<String> totalList = bean.getTotal();
        List<FamilyEconomyCheckRankingBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList, totalList);
        //显示列表
//        adapter.setTitleList(titleList);
        adapter.setNewData(naturalList);
        showSuccessViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));

        if (ListUtils.isEmpty(titleList) || ListUtils.isEmpty(categoryList)) {
            return;
        }
        List<Float> fList = new ArrayList<>();
        int entityNumber = ListUtils.getSize(titleList);

        for (int j = 0; j < categoryList.size(); ++j) {
            SubsistenceVarianceRankingSpecificCategoryBean cb = categoryList.get(j);
            List<String> cbItem = cb.getList();
            Util.processStatisticsChartList(cbItem);
//            fList.addAll(cbItem);
            for (int i = 0; i < entityNumber; i++) {
                String str = cbItem.get(i);
                float f = Util.getFloatFromString(str);
                fList.add(f);
                if (is3Column()) {
                    if (j == 0) {
                        yVals1.add(new BarEntry(i, (float) (f)));
                    } else if (j == 1) {
                        yVals2.add(new BarEntry(i, (float) (f)));

                    }
//                    else if (j == 2) {
//                        yVals3.add(new BarEntry(i, (float) (f)));
//
//                    } else if (j == 3) {
//                        yVals4.add(new BarEntry(i, (float) (f)));
//
//                    }
                } else {
                    if (j == 0) {
                        yVals1.add(new BarEntry(i, (float) (f)));
                    }
//                    else if (j == 1) {
//                        yVals2.add(new BarEntry(i, (float) (f)));
//
//                    }
                }
            }
        }

        BarDataSet set1, set2 = null, set3 = null, set4=null;
//        String label1, label2, label3, label4;

//        if (hbcChart.getData() != null && hbcChart.getData().getDataSetCount() > 0) {
//
//            if (is3Column()) {
//                set1 = (BarDataSet) hbcChart.getData().getDataSetByIndex(0);
//                set2 = (BarDataSet) hbcChart.getData().getDataSetByIndex(1);
////                set3 = (BarDataSet) hbcChart.getData().getDataSetByIndex(2);
////                set4 = (BarDataSet) hbcChart.getData().getDataSetByIndex(3);
//                set1.setValues(yVals1);
//                set2.setValues(yVals2);
////                set3.setValues(yVals3);
////                set4.setValues(yVals4);
//            } else {
//                set1 = (BarDataSet) hbcChart.getData().getDataSetByIndex(0);
////                set2 = (BarDataSet) hbcChart.getData().getDataSetByIndex(1);
//                set1.setValues(yVals1);
////                set2.setValues(yVals2);
//            }
//            hbcChart.getData().notifyDataChanged();
//            hbcChart.notifyDataSetChanged();
//
//        } else {
            // create 4 DataSets
            if (Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING.equals(type)){
                set1 = new BarDataSet(yVals1, "总人次");
                set1.setColor(Constants.CHART_FIRST_COLOR);
                set2 = new BarDataSet(yVals2, "总户次");
                set2.setColor(Constants.CHART_SECOND_COLOR);
            } else if (Constants.FAMILY_ECONOMY_CHECK_REPORT_RANKING.equals(type)) {
                set1 = new BarDataSet(yVals1, "出具核对报告数");
                set1.setColor(Constants.CHART_FIRST_COLOR);

            } else if (Constants.FAMILY_ECONOMY_CHECK_REVIEW_RANKING.equals(type)){
                set1 = new BarDataSet(yVals1, "出具复议核对报告数");
                set1.setColor(Constants.CHART_FIRST_COLOR);

            } else {
                set1 = new BarDataSet(yVals1, "总人次");
                set1.setColor(Constants.CHART_FIRST_COLOR);
                set2 = new BarDataSet(yVals2, "总户次");
                set2.setColor(Constants.CHART_SECOND_COLOR);

            }


            BarData data ;
            if (is3Column()){
                data   = new BarDataEx(set1, set2);

            } else {
                data = new BarDataEx(set1);
            }
            Util.setChartDataLargeValueFormatter(data);
//            data.setValueFormatter(new LargeValueFormatter());
//            data.setValueTypeface(mTfLight);
            data.setValueTextSize(Constants.CHART_VALUE_TEXT_SIZE);

            hbcChart.setData(data);
//        }

        // specify the width each bar should have
        hbcChart.getBarData().setBarWidth(barWidth);

        final List<String> reversedTitleList = Util.getProcessedStatisticsChartTitleList(titleList);
        XAxis xAxis = hbcChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / getNumberPerGroup();
                int index = (int) (v);
//                LogUtil.i("v:"+v+" calculatedV:"+calculatedV+" index:"+index);
                if (index >= 0 && index < reversedTitleList.size()) {
                    String s = reversedTitleList.get(index);
                    String chartLabel = Util.getMultipleLineChartLabel(s);
//                    String chartLabel = Util.getChartLabel(s);
                    return chartLabel;
                } else {
                    return Constants.EMPTY_STRING;
                }
            }
        });
        // restrict the x-axis range
        hbcChart.getXAxis().setAxisMinimum(0);

//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided
//        float axisMaximum = barWidth * getNumberPerGroup() * yVals1.size();
//        axisMaximum+=(yVals1.size()-1)*Constants.CHART_GROUP_SPACE_WIDTH;
//        hbcChart.getXAxis().setAxisMaximum(axisMaximum);
////        hbcChart.getXAxis().setAxisMaximum(2000);
////        hbcChart.groupBars(0, 0.08f, 0.03f);
//        hbcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);

        int groupCount = yVals1.size();
        float groupSpace = 0f;
        if (is3Column()){
            groupSpace=Constants.BAR_CHART_TWO_COLUMN_GROUP_SPACE;
        } else {
            groupSpace = Constants.BAR_CHART_ONE_COLUMN_GROUP_SPACE;
        }
        float barSpace = 0f;
        if (is3Column()){
            barSpace=  Constants.BAR_CHART_TWO_COLUMN_BAR_SPACE; // x4 DataSet
        } else {
            barSpace=Constants.BAR_CHART_ONE_COLUMN_BAR_SPACE;
        }
//        float barWidth = 0.22f; // x4 DataSet
        // (0.3 + 0.04) * 2 + 0.32 = 1.00 -> interval per "group"
        // (0.4+0.1)*1 + 0.5=1
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        float xMaximum = hbcChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount;
        hbcChart.getXAxis().setAxisMaximum(xMaximum);
        hbcChart.groupBars(0, groupSpace, barSpace);

        Util.setYAxisLargeValueFormat(hbcChart.getAxisLeft(), hbcChart.getAxisRight());
        Util.setYAxisSpaceTop(hbcChart.getAxisLeft(), hbcChart.getAxisRight());

        Util.setHorizontalBarChartInversionPositionAxisMinimum(hbcChart);

//        hbcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
        if (!is3Column()){
            hbcChart.setVisibleXRangeMinimum(Constants.BAR_NUMBER_PER_SCREEN_FOR_ONE_COLUMN*barWidth);
            hbcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN_FOR_ONE_COLUMN * barWidth);

        } else {
            hbcChart.setVisibleXRangeMinimum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);
            hbcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);

        }

        hbcChart.setVisibleYRangeMinimum(Util.getMax(fList)*Constants.CHART_Y_AXIS_VALUE_FACTOR,
                YAxis.AxisDependency.LEFT);
        hbcChart.setVisibleYRangeMinimum(Util.getMax(fList)*Constants.CHART_Y_AXIS_VALUE_FACTOR,
                YAxis.AxisDependency.RIGHT);

        Util.setHorizontalBarChartInversionPosition(hbcChart);

        hbcChart.invalidate();


    }

    private int getNumberPerGroup(){
        if (is3Column()){
            return 2;
        } else {
            return 1;
        }
    }

    private FamilyEconomyCheckRankingBean getTotalBeanFromTotalList(List<String> totalList) {
        int size = ListUtils.getSize(totalList);
        if (ListUtils.isEmpty(totalList)) {
            return null;
        } else {
            if (Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING.equals(type) && size>=2){
                FamilyEconomyCheckRankingBean nb = new FamilyEconomyCheckRankingBean();
                nb.setTitle(Constants.LIST_SUMMARY_TEXT);
                nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(0))));
                nb.setSecondValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(1))));
                return nb;
            } else if (Constants.FAMILY_ECONOMY_CHECK_REPORT_RANKING.equals(type) && size>=1){
                FamilyEconomyCheckRankingBean nb = new FamilyEconomyCheckRankingBean();
                nb.setTitle(Constants.LIST_SUMMARY_TEXT);
                nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(0))));
                nb.setSecondValue(Constants.EMPTY_STRING);
                return nb;
            } else if (Constants.FAMILY_ECONOMY_CHECK_REVIEW_RANKING.equals(type) && size>=1){
                FamilyEconomyCheckRankingBean nb = new FamilyEconomyCheckRankingBean();
                nb.setTitle(Constants.LIST_SUMMARY_TEXT);
                nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(0))));
                nb.setSecondValue(Constants.EMPTY_STRING);
                return nb;
            } else {
                return null;
            }
        }
    }

    private List<FamilyEconomyCheckRankingBean> getNaturalBeanListFromRawBeanList(
            List<String> titleList,
            List<SubsistenceVarianceRankingSpecificCategoryBean> rawList,
            List<String> totalList){
        List<FamilyEconomyCheckRankingBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList) ||
                    ListUtils.isEmpty(titleList)){
                return resultList;
            } else {
                FamilyEconomyCheckRankingBean total = getTotalBeanFromTotalList(totalList);
                if (total!=null){
                    resultList.add(total);
                }
                if (is3Column()) {
                    SubsistenceVarianceRankingSpecificCategoryBean bean0 = rawList.get(0);
                    SubsistenceVarianceRankingSpecificCategoryBean bean1 = rawList.get(1);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean2 = rawList.get(2);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean3 = rawList.get(3);
                    for (int j = 0; bean0!=null&&bean0.getList()!=null&&j<ListUtils.getSize(bean0.getList()); ++j) {
                        FamilyEconomyCheckRankingBean nb = new FamilyEconomyCheckRankingBean();
                        nb.setTitle(titleList.get(j));
                        nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
                        nb.setSecondValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean1.getList().get(j))));
                        resultList.add(nb);
                    }
                } else {
                    SubsistenceVarianceRankingSpecificCategoryBean bean0 = rawList.get(0);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean1 = rawList.get(1);
                    for (int j = 0; bean0!=null&&bean0.getList()!=null&&j<ListUtils.getSize(bean0.getList()); ++j) {
                        FamilyEconomyCheckRankingBean nb = new FamilyEconomyCheckRankingBean();
                        nb.setTitle(titleList.get(j));
                        nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
                        nb.setSecondValue(Constants.EMPTY_STRING);
                        resultList.add(nb);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return resultList;
    }

    private boolean is3Column(){
        if (Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_RANKING.equals(type)){
            return true;
        } else {
            return false;
        }
    }

    private void showChartHint(boolean show){
        if (show){
            tvChartHint.setVisibility(View.VISIBLE);
        } else {
            tvChartHint.setVisibility(View.GONE);
        }
    }

    private void showSuccessViewOrFailureView(boolean success, boolean isListEmpty) {
        if (isChartSelected()) {
            hilightChart();
        } else {
            hilightList();
        }
        if (success) {
            if (isListEmpty) {
                adapter.getData().clear();
                rlContent.setVisibility(View.GONE);
                hbcChart.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
                showChartHint(false);
 /*  hilightStatisticsListIndicatorView(false);
                hilightStatisticsChartIndicatorView(true);*/
            } else {
                if (isChartSelected()){
                    rlContent.setVisibility(View.GONE);
                    hbcChart.setVisibility(View.VISIBLE);
                    showChartHint(true);
                } else {
                    rlContent.setVisibility(View.VISIBLE);
                    hbcChart.setVisibility(View.GONE);
                    showChartHint(false);
                }
                failureView.setVisibility(View.GONE);
               /* if (isChartSelected()) {
                    hilightChart();
                } else {
                    hilightList();
                }*/
            }
        } else {
            adapter.getData().clear();
            rlContent.setVisibility(View.GONE);
            hbcChart.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());
            showChartHint(false);
/*   hilightStatisticsListIndicatorView(false);
            hilightStatisticsChartIndicatorView(true);*/
        }
    }


    private void hilightStatisticsListIndicatorView(boolean hilight) {
        if (!hilight) {
            ivStatisticsList.setImageResource(R.mipmap.g_statistics_list_n);
            tvStatisticsList.setTextColor(getResources().getColor(R.color.gray));
        } else {
            ivStatisticsList.setImageResource(R.mipmap.g_statistics_list_h);
            tvStatisticsList.setTextColor(getResources().getColor(R.color.colorTheme));
        }

    }

    private void hilightStatisticsChartIndicatorView(boolean hilight) {
        if (!hilight) {
            ivStatisticsChart.setImageResource(R.mipmap.g_statistics_chart_n);
            tvStatisticsChart.setTextColor(getResources().getColor(R.color.gray));
        } else {
            ivStatisticsChart.setImageResource(R.mipmap.g_statistics_chart_h);
            tvStatisticsChart.setTextColor(getResources().getColor(R.color.colorTheme));
        }
    }

    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_list_empty_please_reload);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_failure);
    }

    private void populateData(){
                type=getDeliveredString(Constants.STATISTICS_TYPE_KEY);
                methodName=getDeliveredString(Constants.METHOD_KEY);
    }

//    private void initResultType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempResultType = Constants.EMPTY_STRING;
//        if (Constants.RESCUE_RESULT_TYPE_HOUSEHOLD == index) {
//            presentation = "户数";
//            tempResultType = Constants.RESCUE_RESULT_TYPE_VALUE_HOUSEHOLD;
//        } else if (Constants.RESCUE_RESULT_TYPE_PERSON == index) {
//            presentation = "人数";
//            tempResultType = Constants.RESCUE_RESULT_TYPE_VALUE_PERSON;
//        }
//        tvResultType.setText(presentation);
//        resultType = tempResultType;
//        resultTypeIndex = index;
//    }
//
//    private void initSupportType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempResultType = Constants.EMPTY_STRING;
//        if (Constants.RESCUE_BRINGUP_TYPE_ALL == index) {
//            presentation = "全部";
//            tempResultType = Constants.RESCUE_BRINGUP_TYPE_VALUE_ALL;
//        } else if (Constants.RESCUE_BRINGUP_TYPE_FOCUS == index) {
//            presentation = "集中供养";
//            tempResultType = Constants.RESCUE_BRINGUP_TYPE_VALUE_FOCUS;
//        } else if (Constants.RESCUE_BRINGUP_TYPE_DISPERSE == index){
//            presentation = "分散供养";
//            tempResultType = Constants.RESCUE_BRINGUP_TYPE_VALUE_DISPERSE;
//        }
//        tvSupportType.setText(presentation);
//        supportType = tempResultType;
//        supportTypeIndex = index;
//    }
//
    private void initCheckType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempCheckType = Constants.EMPTY_STRING;
        if (Constants.CHECK_TYPE_ALL == index) {
            presentation = "全部";
            tempCheckType = Constants.CHECK_ITEM_TYPE_ALL;
        } else if (Constants.CHECK_TYPE_LOWEST_LIFE_ASSURANCE == index) {
            presentation = "最低生活保障";
            tempCheckType = Constants.CHECK_ITEM_TYPE_LOWEST_LIFE_ASSURANCE;
        } else if (Constants.CHECK_TYPE_VERY_POOR_SUPPORT == index) {
            presentation = "特困人员供养";
            tempCheckType = Constants.CHECK_ITEM_TYPE_VERY_POOR_SUPPORT;
        } else if (Constants.CHECK_TYPE_DISASTER_ASSISTANCE == index){
            presentation="受灾人员救助";
            tempCheckType=Constants.CHECK_ITEM_TYPE_DISASTER_ASSISTANCE;
        } else if (Constants.CHECK_TYPE_MEDICAL_ASSISTANCE == index){
            presentation="医疗救助";
            tempCheckType = Constants.CHECK_ITEM_TYPE_MEDICAL_ASSISTANCE;
        } else if (Constants.CHECK_TYPE_EDUCATION_ASSISTANT == index){
            presentation="教育救助";
            tempCheckType = Constants.CHECK_ITEM_TYPE_EDUCATION_ASSISTANCE;
        } else if (Constants.CHECK_TYPE_HOUSE_ASSISTANCE == index){
            presentation="住房救助";
            tempCheckType = Constants.CHECK_ITEM_TYPE_HOUSE_ASSISTANCE;
        } else if (Constants.CHECK_TYPE_JOB_ASSISTANCE == index){
            presentation="就业救助";
            tempCheckType = Constants.CHECK_ITEM_TYPE_JOB_ASSISTANCE;
        } else if (Constants.CHECK_TYPE_TEMP_ASSISTANCE == index){
            presentation = "临时救助";
            tempCheckType = Constants.CHECK_ITEM_TYPE_TEMP_ASSISTANCE;
        }
        tvCheckType.setText(presentation);
        checkType = tempCheckType;
        checkTypeIndex = index;
    }


    private void initDateRangePopupWindow(int index) {
        List<IDateRange> list = new ArrayList<>();
        if (Constants.DATE_RANGE_TYPE_MONTH == index) {
            List<MonthTextBean> monthList = Util.getLatestNumberMonthTextBeanList(MonthTextBean.NUMBER);
            list = Arrays.asList(monthList.toArray(new IDateRange[0]));
        } else if (Constants.DATE_RANGE_TYPE_QUARTER == index) {
            List<QuarterTextBean> quarterList = Util.getLatestNumberQuarterTextBeanList(QuarterTextBean.NUMBER);
            list = Arrays.asList(quarterList.toArray(new IDateRange[0]));
        } else if (Constants.DATE_RANGE_TYPE_YEAR == index) {
            List<YearTextBean> yearList = Util.getLatestNumberYearTextBeanList(YearTextBean.NUMBER);
            list = Arrays.asList(yearList.toArray(new IDateRange[0]));
        }
        if (!ListUtils.isEmpty(list)) {
            selectedDateRange = list.get(0);
        } else {
            selectedDateRange = null;
        }
        setDateRangeString();
        dateRangePopupWindow = new MonthQuarterYearRangePopupWindow(getActivity(), Constants.POPUP_WINDOW_WIDTH_FOR_STATISTICS, Constants.POPUP_WINDOW_HEIGHT, tvDate, list);
        dateRangePopupWindow.setDateRangeItemClickListener(new MonthQuarterYearRangePopupWindow.DateRangeItemClickListener() {
            @Override
            public void dateRangeItemDidClick(IDateRange iDateRange) {
                selectedDateRange = iDateRange;
                setDateRangeString();
                onRefresh();
            }
        });
    }

    private void setDateRangeString() {
        if (selectedDateRange != null) {
            tvDate.setText(selectedDateRange.getPresentation());
        }

    }


    private String getStartDateString() {
        if (selectedDateRange != null) {
            return selectedDateRange.getStartDateString();
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    private String getEndDateString() {
        if (selectedDateRange != null) {
            return selectedDateRange.getEndDateString();
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    private String getAreaCodeString() {
        if (!areaCodeStack.empty()){
            String result = areaCodeStack.peek();
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }

//        String areaCode = IdentityManager.getSrcLoginResultBean(getActivity()).getAreaId();
//        return areaCode;
////        return Constants.EMPTY_STRING;
    }

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addFamilyEconomyCheckRankingStatisticsFragment(new FamilyEconomyCheckRankingStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getFamilyEconomyCheckRankingListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getFamilyEconomyCheckRankingListRequestMap() {
        Map<String, String> map = ParametersFactory.getFamilyEconomyCheckRankingStatisticsListMap(getActivity(),
                checkType, getStartDateString(), getEndDateString(), getAreaCodeString(), methodName);
        return map;
    }

    private SubsistenceVarianceRankingStatisticsBean getStatisticsBean(GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>> bean) {
        if (bean != null) {
            List<SubsistenceVarianceRankingStatisticsBean> list = bean.getData();
            if (!ListUtils.isEmpty(list)) {
                return list.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<SubsistenceVarianceRankingStatisticsBean>> bean) {
        SubsistenceVarianceRankingStatisticsBean b = getStatisticsBean(bean);
        isRequestSuccess = true;
        if (b != null) {
            setData(b);
        } else {
            showSuccessViewOrFailureView(true, true);
        }
    }

    @Override
    public void onLoadListFailure(String message) {
        isRequestSuccess=false;
        showSuccessViewOrFailureView(false, true);
        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);


        //模拟加载数据成功
//        simulate();

    }


    private void simulate() {
//        isRequestSuccess = true;
//        showSuccessViewOrFailureView(true, false);
//
//        String[] titleArray = {"蜀山区", "庐阳区", "区3", "区4", "区5", "区6"/*, "区7", "区8", "区9", "区10"
//                , "区11", "区12", "区13", "区14", "区15", "区16", "区17", "区18"*/};
//        List<String> titleList = Arrays.asList(titleArray);
//        List<SubsistenceVarianceRankingSpecificCategoryBean> dataList = new ArrayList<>();
//        int number = 2;
//        if (is3Column()){
//            number=4;
//        }
//        for (int i = 0; i < number; ++i) {
//            List<Float> floatList = new ArrayList<>();
//            for (int j = 0; j < titleArray.length; ++j) {
//                floatList.add(120.f + j);
//            }
//            SubsistenceVarianceRankingSpecificCategoryBean b = new SubsistenceVarianceRankingSpecificCategoryBean("新增" + i, floatList);
//            dataList.add(b);
//        }
//        SubsistenceVarianceRankingStatisticsBean bean = new SubsistenceVarianceRankingStatisticsBean(titleList, dataList);
//        setData(bean);
    }
}
