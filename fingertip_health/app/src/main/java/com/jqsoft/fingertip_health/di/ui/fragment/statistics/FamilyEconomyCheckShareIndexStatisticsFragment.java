package com.jqsoft.fingertip_health.di.ui.fragment.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.statistics.FamilyEconomyCheckShareIndexStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.FamilyEconomyCheckShareIndexBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingSpecificCategoryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.FamilyEconomyCheckShareIndexStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.FamilyEconomyCheckShareIndexStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.FamilyEconomyCheckShareIndexStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.popup_window.MonthQuarterYearRangePopupWindow;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.sevenheaven.segmentcontrol.SegmentControl;

import net.qiujuer.genius.ui.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;

/**
 * 折线图
 * 家庭经济状况核对-信息共享指标统计
 * Created by Administrator on 2018-01-02.
 */

public class FamilyEconomyCheckShareIndexStatisticsFragment extends AbstractFragment implements FamilyEconomyCheckShareIndexStatisticsFragmentContract.View {
    @BindView(R.id.sc_date_range)
    SegmentControl scDateRange;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_query)
    Button btQuery;
    //    @BindView(R.id.ll_check_type)
//    LinearLayout llCheckType;
//    @BindView(R.id.tv_check_type)
//    TextView tvCheckType;
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
    //    @BindView(R.id.tv_second)
//    TextView tvSecond;
//    @BindView(R.id.tv_third)
//    TextView tvThird;
//    @BindView(R.id.tv_fourth)
//    TextView tvFourth;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lc_chart)
    LineChart lcChart;
    @BindView(R.id.lay_family_economy_check_share_index_load_failure)
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

//    String type;

//    int checkTypeIndex = Constants.CHECK_TYPE_ALL;
//    String checkType;//核对项目(允许为空)	ZDSHBZ	最低生活保障    TKRYGY	特困人员供养    SZRYJZ	受灾人员救助
////    YLJZ	医疗救助    JIAOYUJZ	教育救助    ZFJZ	住房救助    JIUYEJZ	就业救助    LSJZ	临时救助

    String methodName;

    @Inject
    FamilyEconomyCheckShareIndexStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

//    int householdTypeIndex = Constants.HOUSEHOLD_TYPE_ALL;
//    String householdType;
//    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
//    String resultType;
//    int supportTypeIndex = Constants.RESCUE_BRINGUP_TYPE_ALL;
//    String supportType;

    FamilyEconomyCheckShareIndexStatisticsAdapter adapter;

    public FamilyEconomyCheckShareIndexStatisticsFragment() {
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
        return R.layout.fragment_family_economy_check_share_index_statistics_layout;
    }

    @Override
    protected void initData() {
        populateData();
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

//        initCheckType(checkTypeIndex);
//        RxView.clicks(tvCheckType)
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
//                        String[] typeArray = new String[]{"全部", "最低生活保障", "特困人员供养", "受灾人员救助", "医疗救助", "教育救助",
//                        "住房救助", "就业救助", "临时救助"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择核对项目类型", null, typeList,
//                                checkTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initCheckType(which);
//                                        return false;
//                                    }
//                                });
//                    }
//                });
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

        initLineChart();

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

        adapter = new FamilyEconomyCheckShareIndexStatisticsAdapter(new ArrayList<FamilyEconomyCheckShareIndexBean>());
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
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);

                            } else {
                                rlContent.setVisibility(View.VISIBLE);
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.GONE);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            lcChart.setVisibility(View.GONE);
                            failureView.setVisibility(View.VISIBLE);
                            tvFailureView.setText(getFailureHint());
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
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);

                            } else {
                                rlContent.setVisibility(View.GONE);
                                lcChart.setVisibility(View.VISIBLE);
                                failureView.setVisibility(View.GONE);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            lcChart.setVisibility(View.GONE);
                            failureView.setVisibility(View.VISIBLE);
                            tvFailureView.setText(getFailureHint());
                            hilightStatisticsListIndicatorView(false);
                            hilightStatisticsChartIndicatorView(true);

                        }

                    }
                });
    }

    private boolean isResultEmpty(){
        return ListUtils.isEmpty(adapter.getData());
    }


    private void initRecyclerViewHeaderVisibilityAndValue() {
//        if (Constants.FAMILY_ECONOMY_CHECK_ACCEPTANCE_TREND.equals(type)){
        tvTitle.setVisibility(View.VISIBLE);
        tvFirst.setVisibility(View.VISIBLE);
        tvTitle.setText("信息共享类型");
        tvFirst.setText("接口反馈次数");
//        }
    }

    private void initLineChart() {
//        lcChart.setOnChartValueSelectedListener(this);
        // lcChart.setHighlightEnabled(false);

        lcChart.setNoDataText(Constants.CHART_NO_DATA_TEXT);
        lcChart.setNoDataTextColor(getResources().getColor(R.color.colorTheme));

        lcChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        lcChart.setMaxVisibleValueCount(Constants.CHART_MAX_NO_VALUES_ENTRY_NUMBER);

        // scaling can now only be done on x- and y-axis separately
        lcChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // lcChart.setDrawBarShadow(true);

        lcChart.setDrawGridBackground(false);


        XAxis xl = lcChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
//        xl.setGranularity(Constants.CHART_DEFAULT_BAR_WIDTH);
        xl.setGranularity(1f);
        xl.setDrawLabels(true);
//        xl.setCenterAxisLabels(true);
        xl.setCenterAxisLabels(false);
//        xl.setLabelCount(8, true);

        YAxis yl = lcChart.getAxisLeft();
//        yl.setTypeface(mTfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(false);
//        yl.setLabelCount(5, false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = lcChart.getAxisRight();
//        yr.setTypeface(mTfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
//        yr.setLabelCount(5, false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

//        lcChart.setFitBars(false);
//        lcChart.animateY(Constants.CHART_ANIMATION_DURATION);


        Legend l = lcChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        l.setWordWrapEnabled(true);
    }

    private void setData(SubsistenceVarianceRankingStatisticsBean bean) {
        float barWidth = Constants.CHART_DEFAULT_BAR_WIDTH;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

//        float randomMultiplier = 3* 100000f;
        final List<String> titleList = bean.getxTitle();
        List<SubsistenceVarianceRankingSpecificCategoryBean> categoryList = bean.getList();
        List<FamilyEconomyCheckShareIndexBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList);
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
//            fList.addAll(cbItem);
            for (int i = 0; i < entityNumber; i++) {
                String f = cbItem.get(i);
                Float fl = Util.getFloatFromString(f);
                fList.add(fl);
                if (j == 0) {
                    yVals1.add(new Entry(i, fl));
                }
            }
        }

        LineDataSet set1, set2 = null, set3 = null, set4 = null;
//        String label1, label2, label3, label4;


//        if (lcChart.getData() != null && lcChart.getData().getDataSetCount() > 0) {
//
//            set1 = (LineDataSet) lcChart.getData().getDataSetByIndex(0);
////                set2 = (BarDataSet) lcChart.getData().getDataSetByIndex(1);
//            set1.setValues(yVals1);
////                set2.setValues(yVals2);
//            set1.setDrawValues(true);
//            lcChart.getData().notifyDataChanged();
//            lcChart.notifyDataSetChanged();
//
//        } else {
            set1 = new LineDataSet(yVals1, "接口反馈次数");
            set1.setColor(Constants.CHART_FIRST_COLOR);
            set1.setValueTextColor(Constants.CHART_FIRST_COLOR);
            set1.setLineWidth(Constants.LINE_CHART_LINE_WIDTH);
            set1.setCircleRadius(Constants.LINE_CHART_CIRCLE_RADIUS);
            set1.setHighLightColor(Constants.LINE_CHART_HILIGHT_COLOR);
            set1.setDrawValues(true);


            LineData data;
            data = new LineData(set1);
            Util.setChartDataLargeValueFormatter(data);
//            data.setValueFormatter(new LargeValueFormatter());
//            data.setValueTypeface(mTfLight);
            data.setValueTextSize(Constants.CHART_VALUE_TEXT_SIZE);

            // set data
            lcChart.setData(data);
//        }

        // specify the width each bar should have
//        lcChart.getLineData().setBarWidth(barWidth);

        XAxis xAxis = lcChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / 1;
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / getNumberPerGroup();
                int index = (int) (v);
//                LogUtil.i("v:"+v+" calculatedV:"+calculatedV+" index:"+index);
                if (index >= 0 && index < titleList.size()) {
                    return titleList.get(index);
                } else {
                    return Constants.EMPTY_STRING;
                }
            }
        });
        // restrict the x-axis range
        lcChart.getXAxis().setAxisMinimum(0);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided
//        float axisMaximum = barWidth * getNumberPerGroup() * yVals1.size();
        float axisMaximum = barWidth * yVals1.size();
        axisMaximum += (yVals1.size() - 1) * Constants.CHART_GROUP_SPACE_WIDTH;
        lcChart.getXAxis().setAxisMaximum(axisMaximum);
//        lcChart.getXAxis().setAxisMaximum(2000);
//        lcChart.groupBars(0, 0.08f, 0.03f);
//        lcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);

        Util.setYAxisLargeValueFormat(lcChart.getAxisLeft(), lcChart.getAxisRight());
        Util.setYAxisSpaceTop(lcChart.getAxisLeft(), lcChart.getAxisRight());


        Util.setLineChartAxisMinimumMaximum(lcChart, fList);

//        lcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
//        lcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN * barWidth);
        lcChart.setVisibleXRangeMinimum(Constants.LINE_CHART_ITEM_NUMBER_PER_SCREEN*barWidth);
        lcChart.setVisibleXRangeMaximum(Constants.LINE_CHART_ITEM_NUMBER_PER_SCREEN*barWidth);
        lcChart.invalidate();
        // do not forget to refresh the chart
        // holder.chart.invalidate();
        lcChart.animateX(Constants.CHART_ANIMATION_DURATION);


//        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
//        sets.add(d1);
//        sets.add(d2);
//
//        LineData cd = new LineData(sets);


    }

    private int getNumberPerGroup() {
        return 1;
    }

    private List<FamilyEconomyCheckShareIndexBean> getNaturalBeanListFromRawBeanList(
            List<String> titleList,
            List<SubsistenceVarianceRankingSpecificCategoryBean> rawList) {
        List<FamilyEconomyCheckShareIndexBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList) ||
                    ListUtils.isEmpty(titleList)) {
                return resultList;
            } else {
                SubsistenceVarianceRankingSpecificCategoryBean bean0 = rawList.get(0);
//                    SubsistenceVarianceRankingSpecificCategoryBean bean1 = rawList.get(1);
                for (int j = 0; bean0 != null && bean0.getList() != null && j < ListUtils.getSize(bean0.getList()); ++j) {
                    FamilyEconomyCheckShareIndexBean nb = new FamilyEconomyCheckShareIndexBean();
                    nb.setTitle(titleList.get(j));
                    nb.setFirstValue(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
                    resultList.add(nb);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return resultList;
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
                lcChart.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
               /* hilightStatisticsListIndicatorView(false);
                hilightStatisticsChartIndicatorView(true);*/
            } else {
                if (isChartSelected()) {
                    rlContent.setVisibility(View.GONE);
                    lcChart.setVisibility(View.VISIBLE);
                } else {
                    rlContent.setVisibility(View.VISIBLE);
                    lcChart.setVisibility(View.GONE);
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
            lcChart.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());
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

    private void populateData() {
//                type=getDeliveredString(Constants.STATISTICS_TYPE_KEY);
        methodName = getDeliveredString(Constants.METHOD_KEY);
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
//    private void initCheckType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempCheckType = Constants.EMPTY_STRING;
//        if (Constants.CHECK_TYPE_ALL == index) {
//            presentation = "全部";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_ALL;
//        } else if (Constants.CHECK_TYPE_LOWEST_LIFE_ASSURANCE == index) {
//            presentation = "最低生活保障";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_LOWEST_LIFE_ASSURANCE;
//        } else if (Constants.CHECK_TYPE_VERY_POOR_SUPPORT == index) {
//            presentation = "特困人员供养";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_VERY_POOR_SUPPORT;
//        } else if (Constants.CHECK_TYPE_DISASTER_ASSISTANCE == index) {
//            presentation = "受灾人员救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_DISASTER_ASSISTANCE;
//        } else if (Constants.CHECK_TYPE_MEDICAL_ASSISTANCE == index) {
//            presentation = "医疗救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_MEDICAL_ASSISTANCE;
//        } else if (Constants.CHECK_TYPE_EDUCATION_ASSISTANT == index) {
//            presentation = "教育救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_EDUCATION_ASSISTANCE;
//        } else if (Constants.CHECK_TYPE_HOUSE_ASSISTANCE == index) {
//            presentation = "住房救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_HOUSE_ASSISTANCE;
//        } else if (Constants.CHECK_TYPE_JOB_ASSISTANCE == index) {
//            presentation = "就业救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_JOB_ASSISTANCE;
//        } else if (Constants.CHECK_TYPE_TEMP_ASSISTANCE == index) {
//            presentation = "临时救助";
//            tempCheckType = Constants.CHECK_ITEM_TYPE_TEMP_ASSISTANCE;
//        }
//        tvCheckType.setText(presentation);
//        checkType = tempCheckType;
//        checkTypeIndex = index;
//    }


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
        String areaCode = IdentityManager.getSrcLoginResultBean(getActivity()).getAreaId();
        return areaCode;
//        return Constants.EMPTY_STRING;
    }

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addFamilyEconomyCheckShareIndexStatisticsFragment(new FamilyEconomyCheckShareIndexStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getFamilyEconomyCheckShareIndexListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getFamilyEconomyCheckShareIndexListRequestMap() {
        Map<String, String> map = ParametersFactory.getFamilyEconomyCheckShareIndexStatisticsListMap(getActivity(),
                getStartDateString(), getEndDateString(), getAreaCodeString(), methodName);
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
//        String[] titleArray = {"车辆信息","低保信息","法人信息","扶贫人员信息","公积金贷款信息","公积金缴纳信息","户籍信息","婚姻登记信息","契税信息","人社社保个人基本信息","社保个人参保信息","私营企业信息","投资人信息","住房信息"};
//        List<String> titleList = Arrays.asList(titleArray);
//        List<SubsistenceVarianceRankingSpecificCategoryBean> dataList = new ArrayList<>();
//        int number = 1;
//        for (int i = 0; i < number; ++i) {
//            List<Float> floatList = new ArrayList<>();
//            for (int j = 0; j < titleArray.length; ++j) {
//                floatList.add(120.f + j * 50 * (i + 1));
//            }
//            SubsistenceVarianceRankingSpecificCategoryBean b = new SubsistenceVarianceRankingSpecificCategoryBean("新增" + i, floatList);
//            dataList.add(b);
//        }
//        SubsistenceVarianceRankingStatisticsBean bean = new SubsistenceVarianceRankingStatisticsBean(titleList, dataList);
//        setData(bean);
    }
}
