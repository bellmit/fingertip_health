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
import com.github.mikephil.charting.charts.HorizontalBarChart;
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
import com.jqsoft.fingertip_health.adapter.statistics.InstitutionRankingStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionRankingNaturalBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingSpecificCategoryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.InstitutionRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.InstitutionRankingStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.InstitutionRankingStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.StatisticsSpecificActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.feature.IUpLevel;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.helper.chart.BarDataEx;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.popup_window.MonthQuarterYearRangePopupWindow;
import com.jqsoft.fingertip_health.util.Util;
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
 * 供养机构排名统计
 * Created by Administrator on 2018-01-02.
 */

public class InstitutionRankingStatisticsFragment extends AbstractFragment implements InstitutionRankingStatisticsFragmentContract.View
,IUpLevel{
    @BindView(R.id.sc_date_range)
    SegmentControl scDateRange;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.ll_institution_type)
    LinearLayout llInstitutionType;
    @BindView(R.id.tv_institution_type)
    TextView tvInstitutionType;
    @BindView(R.id.ll_result_type)
    LinearLayout llResultType;
    @BindView(R.id.tv_result_type)
    TextView tvResultType;
    @BindView(R.id.ll_legal_person_type)
    LinearLayout llLegalPersonType;
    @BindView(R.id.tv_legal_person_type)
    TextView tvLegalPersonType;
    @BindView(R.id.lay_content)
    LinearLayout rlContent;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_chart_hint)
    View tvChartHint;
    @BindView(R.id.hbc_chart)
    HorizontalBarChart hbcChart;
    @BindView(R.id.lay_institution_ranking_load_failure)
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

    String isAreaOwner=Constants.IS_NOT_AREA_OWNER;

//    String rescueType;//(城乡低保:99	全部  01	农村低保    02	城市低保)   (特困人员供养：03） （低收入家庭：04）

    String methodName;

    Stack<String> areaCodeStack = new Stack<>();
    List<String> currentAreaCodeList = new ArrayList<>();


    @Inject
    InstitutionRankingStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

//    int pageType;

    int institutionTypeIndex = Constants.INSTITUTION_TYPE_ALL;
    String institutionType;
    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
    String resultType;
    int legalPersonTypeIndex = Constants.LEGAL_PERSON_TYPE_ALL;
    String legalPersonType;

    InstitutionRankingStatisticsAdapter adapter;

    public InstitutionRankingStatisticsFragment() {
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
        return R.layout.fragment_institution_ranking_statistics_layout;
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

//        scDateRange.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
//            @Override
//            public void onSegmentControlClick(int i) {
//                LogUtil.i("selected index:" + i);
//                initDateRangePopupWindow(i);
//                onRefresh();
//            }
//        });

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

//        if (Constants.PAGE_TYPE_SUBSISTENCE==pageType){
//            initInstitutionType(institutionTypeIndex);
//        } else if (Constants.PAGE_TYPE_VERY_POOR==pageType){
//            initLegalPersonType(legalPersonTypeIndex);
//        }
//        else {
//        }
        initInstitutionType(institutionTypeIndex);
        initLegalPersonType(legalPersonTypeIndex);

        RxView.clicks(tvInstitutionType)
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
                        String[] typeArray = new String[]{"全部", "公建公营", "公建民营", "社会失能半失能集中供养机构"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择机构性质", null, typeList,
                                institutionTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initInstitutionType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });
                    }
                });

//        if (Constants.PAGE_TYPE_SUBSISTENCE==(pageType)){
//            llInstitutionType.setVisibility(View.VISIBLE);
//            llLegalPersonType.setVisibility(View.GONE);
//        } else if (Constants.PAGE_TYPE_VERY_POOR==(pageType)){
//            llInstitutionType.setVisibility(View.GONE);
//            llLegalPersonType.setVisibility(View.VISIBLE);
//        } else if (Constants.PAGE_TYPE_LOW_SALARY_FAMILY==(pageType)){
//            llInstitutionType.setVisibility(View.GONE);
//            llLegalPersonType.setVisibility(View.GONE);
//        }else {
//            llInstitutionType.setVisibility(View.VISIBLE);
//            llLegalPersonType.setVisibility(View.GONE);
//        }
//        llInstitutionType.setVisibility(View.VISIBLE);
        llLegalPersonType.setVisibility(View.VISIBLE);

        initResultType(resultTypeIndex);
        RxView.clicks(tvResultType)
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
                        String[] typeArray = new String[]{"户数", "人数"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择统计范围", null, typeList,
                                resultTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initResultType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });

                    }
                });

//        initLegalPersonType(legalPersonTypeIndex);
        RxView.clicks(tvLegalPersonType)
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
                        String[] typeArray = new String[]{"全部", "民非", "工商", "事业", "无"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择法人登记分类", null, typeList,
                                legalPersonTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initLegalPersonType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });

                    }
                });

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

        adapter = new InstitutionRankingStatisticsAdapter(new ArrayList<InstitutionRankingNaturalBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
            }
        });

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

//    private void setData(SubsistenceVarianceRankingStatisticsBean bean) {
//        float barWidth = Constants.CHART_DEFAULT_BAR_WIDTH;
//
//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
//
////        float randomMultiplier = 3* 100000f;
//        final List<String> titleList = bean.getxTitle();
//        List<SubsistenceVarianceRankingSpecificCategoryBean> categoryList = bean.getList();
//        List<InstitutionRankingNaturalBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList);
//        //显示列表
////        adapter.setTitleList(titleList);
//        adapter.setNewData(naturalList);
//        showSuccessViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));
//
//        if (ListUtils.isEmpty(titleList) || ListUtils.isEmpty(categoryList)) {
//            return;
//        }
//        int entityNumber = ListUtils.getSize(titleList);
//
//        for (int j = 0; j < categoryList.size(); ++j) {
//            SubsistenceVarianceRankingSpecificCategoryBean cb = categoryList.get(j);
//            List<Float> cbItem = cb.getList();
//            for (int i = 0; i < entityNumber; i++) {
//                Float f = cbItem.get(i);
//                if (j == 0) {
//                    yVals1.add(new BarEntry(i, (float) (f)));
//                } else if (j == 1) {
//                    yVals2.add(new BarEntry(i, (float) (f)));
//
//                } else if (j == 2) {
//                    yVals3.add(new BarEntry(i, (float) (f)));
//
//                }
//            }
//        }
//
//        BarDataSet set1, set2, set3;
//
//        if (hbcChart.getData() != null && hbcChart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) hbcChart.getData().getDataSetByIndex(0);
//            set2 = (BarDataSet) hbcChart.getData().getDataSetByIndex(1);
//            set3 = (BarDataSet) hbcChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
//            set3.setValues(yVals3);
//            hbcChart.getData().notifyDataChanged();
//            hbcChart.notifyDataSetChanged();
//
//        } else {
//            // create 4 DataSets
//            set1 = new BarDataSet(yVals1, "新增");
//            set1.setColor(Constants.CHART_FIRST_COLOR);
//            set2 = new BarDataSet(yVals2, "复查");
//            set2.setColor(Constants.CHART_SECOND_COLOR);
//            set3 = new BarDataSet(yVals3, "注销");
//            set3.setColor(Constants.CHART_THIRD_COLOR);
//
//            BarData data = new BarDataEx(set1, set2, set3);
//            data.setValueFormatter(new LargeValueFormatter());
////            data.setValueTypeface(mTfLight);
//
//            hbcChart.setData(data);
//        }
//
//        // specify the width each bar should have
//        hbcChart.getBarData().setBarWidth(barWidth);
//
//        XAxis xAxis = hbcChart.getXAxis();
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float v, AxisBase axisBase) {
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / Constants.NUMBER_PER_DISTRICT;
//                int index = (int) (calculatedV);
////                LogUtil.i("v:"+v+" calculatedV:"+calculatedV+" index:"+index);
//                if (index >= 0 && index < titleList.size()) {
//                    return titleList.get(index);
//                } else {
//                    return Constants.EMPTY_STRING;
//                }
//            }
//        });
//        // restrict the x-axis range
//        hbcChart.getXAxis().setAxisMinimum(0);
//
//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided
//        float axisMaximum = barWidth * Constants.NUMBER_PER_DISTRICT * yVals1.size();
//        axisMaximum+=(yVals1.size()-1)*Constants.CHART_GROUP_SPACE_WIDTH;
//        hbcChart.getXAxis().setAxisMaximum(axisMaximum);
////        hbcChart.getXAxis().setAxisMaximum(2000);
////        hbcChart.groupBars(0, 0.5f, 0f);
//        hbcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);
//
//
//
////        hbcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
//        hbcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);
//        hbcChart.invalidate();
//
//
//    }
    private void setData(SubsistenceVarianceRankingStatisticsBean bean) {
        Util.resetHorizontalBarChartState(hbcChart);

        float barWidth = Constants.BAR_CHART_ONE_COLUMN_BAR_WIDTH;
//        float barWidth = Constants.BAR_CHART_TWO_COLUMN_BAR_WIDTH;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

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
        List<InstitutionRankingNaturalBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList, totalList);
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
                if (j == 0) {
                    yVals1.add(new BarEntry(i, (float) (f)));
                }
//                else if (j == 1) {
//                    yVals2.add(new BarEntry(i, (float) (f)));
//
//                }
//                else if (j == 2) {
//                    yVals3.add(new BarEntry(i, (float) (f)));
//
//                }
            }
        }

        BarDataSet set1, set2, set3;

//        if (hbcChart.getData() != null && hbcChart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) hbcChart.getData().getDataSetByIndex(0);
//            set2 = (BarDataSet) hbcChart.getData().getDataSetByIndex(1);
////            set3 = (BarDataSet) hbcChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
////            set3.setValues(yVals3);
//            hbcChart.getData().notifyDataChanged();
//            hbcChart.notifyDataSetChanged();
//
//        } else {
            // create 4 DataSets
            set1 = new BarDataSet(yVals1, "床位数");
//            set1 = new BarDataSet(yVals1, "供养机构数量");
            set1.setColor(Constants.CHART_FIRST_COLOR);
//            set2 = new BarDataSet(yVals2, "床位数");
//            set2.setColor(Constants.CHART_SECOND_COLOR);
//            set3 = new BarDataSet(yVals3, "注销");
//            set3.setColor(Constants.CHART_THIRD_COLOR);

            BarData data = new BarDataEx(set1);
//            Util.setChartDataLargeValueFormatter(data);
            Util.setChartDataIntValueFormatter(data);
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
//                return  String.valueOf((int)v);
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / Constants.NUMBER_PER_DISTRICT;
                int index = (int) (v);
//                LogUtil.i("v:"+v+" calculatedV:"+calculatedV+" index:"+index);
                if (index >= 0 && index < reversedTitleList.size()) {
                    String s = reversedTitleList.get(index);
                    String chartLabel = Util.getChartLabel(s);
                    return chartLabel;
                } else {
                    return Constants.EMPTY_STRING;
                }
            }
        });
        // restrict the x-axis range
        hbcChart.getXAxis().setAxisMinimum(0);

//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided
//        float axisMaximum = barWidth * Constants.NUMBER_PER_DISTRICT * yVals1.size();
//        axisMaximum+=(yVals1.size()-1)*Constants.CHART_GROUP_SPACE_WIDTH;
//        hbcChart.getXAxis().setAxisMaximum(axisMaximum);
////        hbcChart.getXAxis().setAxisMaximum(2000);
////        hbcChart.groupBars(0, 0.5f, 0f);
//        hbcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);

        int groupCount = yVals1.size();
        float groupSpace = Constants.BAR_CHART_ONE_COLUMN_GROUP_SPACE;
        float barSpace = Constants.BAR_CHART_ONE_COLUMN_BAR_SPACE; // x4 DataSet
//        float groupSpace = Constants.BAR_CHART_TWO_COLUMN_GROUP_SPACE;
//        float barSpace = Constants.BAR_CHART_TWO_COLUMN_BAR_SPACE; // x4 DataSet
//        float barWidth = 0.22f; // x4 DataSet
        // (0.22 + 0.04) * 3 + 0.22 = 1.00 -> interval per "group"
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        float xMaximum = hbcChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount;
        hbcChart.getXAxis().setAxisMaximum(xMaximum);
        hbcChart.groupBars(0, groupSpace, barSpace);

        Util.setYAxisLargeValueFormat(hbcChart.getAxisLeft(), hbcChart.getAxisRight());
        Util.setYAxisSpaceTop(hbcChart.getAxisLeft(), hbcChart.getAxisRight());

        Util.setHorizontalBarChartInversionPositionAxisMinimum(hbcChart);

        //        hbcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
//        hbcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);
        hbcChart.setVisibleXRangeMinimum(Constants.BAR_NUMBER_PER_SCREEN_FOR_ONE_COLUMN*barWidth);
        hbcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN_FOR_ONE_COLUMN*barWidth);

        hbcChart.setVisibleYRangeMinimum(Util.getMax(fList)*Constants.CHART_Y_AXIS_VALUE_FACTOR,
                YAxis.AxisDependency.LEFT);
        hbcChart.setVisibleYRangeMinimum(Util.getMax(fList)*Constants.CHART_Y_AXIS_VALUE_FACTOR,
                YAxis.AxisDependency.RIGHT);


        Util.setHorizontalBarChartInversionPosition(hbcChart);

        hbcChart.invalidate();


    }

    private InstitutionRankingNaturalBean getTotalBeanFromTotalList(List<String> totalList){
        if (ListUtils.isEmpty(totalList) || ListUtils.getSize(totalList)<1){
            return null;
        } else {
            InstitutionRankingNaturalBean nb = new InstitutionRankingNaturalBean();
            nb.setDistrictName(Constants.LIST_SUMMARY_TEXT);
//            nb.setInstitutionNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(0))));
            nb.setBedNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(totalList.get(0))));
            return nb;
        }

    }

    private List<InstitutionRankingNaturalBean> getNaturalBeanListFromRawBeanList(
            List<String> titleList,
            List<SubsistenceVarianceRankingSpecificCategoryBean> rawList,
            List<String> totalList){
        List<InstitutionRankingNaturalBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList) ||
                    ListUtils.isEmpty(titleList)){
                return resultList;
            } else {
//                for (int i = 0; i < ListUtils.getSize(rawList); ++i){
                InstitutionRankingNaturalBean total = getTotalBeanFromTotalList(totalList);
                if (total!=null){
                    resultList.add(total);
                }
                    SubsistenceVarianceRankingSpecificCategoryBean bean0 = rawList.get(0);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean1 = rawList.get(1);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean2 = rawList.get(2);
                    for (int j = 0; bean0!=null&&bean0.getList()!=null&&j<ListUtils.getSize(bean0.getList()); ++j) {
                        InstitutionRankingNaturalBean nb = new InstitutionRankingNaturalBean();
                        nb.setDistrictName(titleList.get(j));
                        nb.setInstitutionNumber(Constants.EMPTY_STRING);
//                        nb.setInstitutionNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
                        nb.setBedNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
//                        nb.setDeleteQuantity(String.valueOf(bean2.getList().get(j)));
                        resultList.add(nb);
                    }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return resultList;
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
                if (isChartSelected()) {
                    rlContent.setVisibility(View.GONE);
                    hbcChart.setVisibility(View.VISIBLE);
                    showChartHint(true);
                } else {
                    rlContent.setVisibility(View.VISIBLE);
                    hbcChart.setVisibility(View.GONE);
                    showChartHint(false);
                }
                failureView.setVisibility(View.GONE);
                /*if (isChartSelected()) {
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
/* hilightStatisticsListIndicatorView(false);
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
//                pageType=getDeliveredInt(Constants.PAGE_TYPE_KEY);
//                institutionType=getDeliveredString(Constants.ITEM_TYPE_KEY);
//                legalPersonType=getDeliveredString(Constants.SUB_TYPE_KEY);
                isAreaOwner=getDeliveredString(Constants.IS_AREA_OWNER_KEY);
                methodName=getDeliveredString(Constants.METHOD_NAME_KEY);
    }

    private void initResultType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempResultType = Constants.EMPTY_STRING;
        if (Constants.RESCUE_RESULT_TYPE_HOUSEHOLD == index) {
            presentation = "户数";
            tempResultType = Constants.RESCUE_RESULT_TYPE_VALUE_HOUSEHOLD;
        } else if (Constants.RESCUE_RESULT_TYPE_PERSON == index) {
            presentation = "人数";
            tempResultType = Constants.RESCUE_RESULT_TYPE_VALUE_PERSON;
        }
        tvResultType.setText(presentation);
        resultType = tempResultType;
        resultTypeIndex = index;
    }

    private void initLegalPersonType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempLegalPersonType = Constants.EMPTY_STRING;
        if (Constants.LEGAL_PERSON_TYPE_ALL==index){
            presentation = "全部";
            tempLegalPersonType = Constants.LEGAL_PERSON_TYPE_ALL_VALUE;
        } else if (Constants.LEGAL_PERSON_TYPE_CIVIL == index) {
            presentation = "民非";
            tempLegalPersonType = Constants.LEGAL_PERSON_TYPE_CIVIL_VALUE;
        } else if (Constants.LEGAL_PERSON_TYPE_INDUSTRIAL == index) {
            presentation = "工商";
            tempLegalPersonType = Constants.LEGAL_PERSON_TYPE_INDUSTRIAL_VALUE;
        } else if (Constants.LEGAL_PERSON_TYPE_BUSINESS == index){
            presentation = "事业";
            tempLegalPersonType = Constants.LEGAL_PERSON_TYPE_BUSINESS_VALUE;
        } else if (Constants.LEGAL_PERSON_TYPE_EMPTY == index){
            presentation = "无";
            tempLegalPersonType = Constants.LEGAL_PERSON_TYPE_EMPTY_VALUE;
        }
        tvLegalPersonType.setText(presentation);
        legalPersonType = tempLegalPersonType;
        legalPersonTypeIndex = index;
    }

    private void initInstitutionType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempInstitutionType = Constants.EMPTY_STRING;
        if (Constants.INSTITUTION_TYPE_ALL == index) {
            presentation = "全部";
            tempInstitutionType = Constants.INSTITUTION_TYPE_ALL_VALUE;
        } else if (Constants.INSTITUTION_TYPE_GOVERNMENT == index) {
            presentation = "公建公营";
            tempInstitutionType = Constants.INSTITUTION_TYPE_GOVERNMENT_VALUE;
        } else if (Constants.INSTITUTION_TYPE_CIVIL == index) {
            presentation = "公建民营";
            tempInstitutionType = Constants.INSTITUTION_TYPE_CIVIL_VALUE;
        } else if (Constants.INSTITUTION_TYPE_DISABLED == index){
            presentation="社会失能半失能集中供养机构";
            tempInstitutionType = Constants.INSTITUTION_TYPE_DISABLED_VALUE;
        }
        tvInstitutionType.setText(presentation);
        institutionType = tempInstitutionType;
        institutionTypeIndex = index;
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

    private String getItemType(){
        return institutionType;
    }

    private String getIsAreaOwner(){
        return isAreaOwner;
    }

    private String getMonth(){
        if (selectedDateRange != null) {
            return selectedDateRange.getMonth();
        } else {
            return Constants.EMPTY_STRING;
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
                .addInstitutionRankingStatisticsFragment(new InstitutionRankingStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getRankingListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getRankingListRequestMap() {
        Map<String, String> map = ParametersFactory.getInstitutionRankingStatisticsListMap(getActivity(), institutionType,
                legalPersonType, getIsAreaOwner(), getAreaCodeString(), methodName);
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

    private void addRecyclerViewTitle(){

    }

    private void simulate() {
//        isRequestSuccess = true;
//        showSuccessViewOrFailureView(true, false);
//
//        String[] titleArray = {"蜀山区", "庐阳区", "区3", "区4", "区5", "区6", "区7", "区8", "区9", "区10"
//                , "区11", "区12", "区13", "区14", "区15", "区16", "区17"/*, "区18"*/};
//        List<String> titleList = Arrays.asList(titleArray);
//        List<SubsistenceVarianceRankingSpecificCategoryBean> dataList = new ArrayList<>();
//        for (int i = 0; i < 3; ++i) {
//            List<Float> intList = new ArrayList<>();
//            for (int j = 0; j < titleArray.length; ++j) {
//                intList.add(120.f + j);
//            }
//            SubsistenceVarianceRankingSpecificCategoryBean b = new SubsistenceVarianceRankingSpecificCategoryBean("新增" + i, intList);
//            dataList.add(b);
//        }
//        SubsistenceVarianceRankingStatisticsBean bean = new SubsistenceVarianceRankingStatisticsBean(titleList, dataList);
//        setData(bean);
    }
}
