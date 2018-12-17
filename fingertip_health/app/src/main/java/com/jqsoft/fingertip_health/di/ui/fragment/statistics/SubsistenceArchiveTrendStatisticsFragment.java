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

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.jqsoft.fingertip_health.adapter.statistics.SubsistenceArchiveTrendStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceArchiveTrendNaturalBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceTrendSpecificCategoryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceVarianceTrendStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.SubsistenceArchiveTrendStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.SubsistenceArchiveTrendStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.SubsistenceArchiveTrendStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.popup_window.MonthQuarterYearRangePopupWindow;
import com.jqsoft.fingertip_health.util.Util;
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
 * 低保/特困人员/低收入家庭档案趋势分析
 * Created by Administrator on 2018-01-02.
 */

public class SubsistenceArchiveTrendStatisticsFragment extends AbstractFragment implements SubsistenceArchiveTrendStatisticsFragmentContract.View {
    @BindView(R.id.sc_date_range)
    SegmentControl scDateRange;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.ll_subsistence_type)
    LinearLayout llSubsistenceType;
    @BindView(R.id.tv_subsistence_type)
    TextView tvSubsistenceType;
    @BindView(R.id.ll_result_type)
    LinearLayout llResultType;
    @BindView(R.id.tv_result_type)
    TextView tvResultType;
    @BindView(R.id.ll_support_type)
    LinearLayout llSupportType;
    @BindView(R.id.tv_support_type)
    TextView tvSupportType;
    @BindView(R.id.lay_content)
    LinearLayout rlContent;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_chart_hint)
    View tvChartHint;
    @BindView(R.id.lc_chart)
    LineChart lcChart;
    @BindView(R.id.lay_subsistence_archive_trend_load_failure)
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

    @Inject
    SubsistenceArchiveTrendStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

    int pageType;

    int subsistenceTypeIndex = Constants.HOUSEHOLD_TYPE_RURAL;
    String subsistenceType;
    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
    String resultType;
    int supportTypeIndex = Constants.VERY_POOR_SUPPORT_TYPE_ALL;
    String supportType;

    SubsistenceArchiveTrendStatisticsAdapter adapter;

    public SubsistenceArchiveTrendStatisticsFragment() {
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
        return R.layout.fragment_subsistence_archive_trend_statistics_layout;
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

//        scDateRange.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
//            @Override
//            public void onSegmentControlClick(int i) {
//                LogUtil.i("selected index:" + i);
//                initDateRangePopupWindow(i);
//                onRefresh();
//            }
//        });

        initDateRangePopupWindow(Constants.DATE_RANGE_TYPE_YEAR);

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

        if (Constants.PAGE_TYPE_SUBSISTENCE==pageType){
            initSubsistenceType(subsistenceTypeIndex);
        } else if (Constants.PAGE_TYPE_VERY_POOR==pageType){
            initSupportType(supportTypeIndex);
        } else {
        }
        RxView.clicks(tvSubsistenceType)
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
                        String[] typeArray = new String[]{"农村低保", "城市低保"};
//                        String[] typeArray = new String[]{"全部", "农村低保", "城市低保"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择户籍类别", null, typeList,
                                subsistenceTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initSubsistenceType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });
                    }
                });

        if (Constants.PAGE_TYPE_SUBSISTENCE==(pageType)){
            llSubsistenceType.setVisibility(View.VISIBLE);
            llSupportType.setVisibility(View.GONE);
        } else if (Constants.PAGE_TYPE_VERY_POOR==(pageType)){
            llSubsistenceType.setVisibility(View.GONE);
            llSupportType.setVisibility(View.VISIBLE);
        } else if (Constants.PAGE_TYPE_LOW_SALARY_FAMILY==(pageType)){
            llSubsistenceType.setVisibility(View.GONE);
            llSupportType.setVisibility(View.GONE);
        }else {
            llSubsistenceType.setVisibility(View.VISIBLE);
            llSupportType.setVisibility(View.GONE);
        }
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

//        initSupportType(supportTypeIndex);
        RxView.clicks(tvSupportType)
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
                        String[] typeArray = new String[]{"全部", "农村特困集中供养", "农村特困分散供养", "城市特困集中供养", "城市特困分散供养"};
                        List<String> typeList = Arrays.asList(typeArray);
                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择特困供养类型", null, typeList,
                                supportTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        initSupportType(which);
                                        onRefresh();
                                        return false;
                                    }
                                });

                    }
                });

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

        adapter = new SubsistenceArchiveTrendStatisticsAdapter(new ArrayList<SubsistenceArchiveTrendNaturalBean>());
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
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);

                            } else {
                                rlContent.setVisibility(View.VISIBLE);
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.GONE);
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            lcChart.setVisibility(View.GONE);
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
                                lcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                showChartHint(false);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);

                            } else {
                                rlContent.setVisibility(View.GONE);
                                lcChart.setVisibility(View.VISIBLE);
                                failureView.setVisibility(View.GONE);
                                showChartHint(true);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            lcChart.setVisibility(View.GONE);
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

    private void simulateListData(List<SubsistenceArchiveTrendNaturalBean> naturalList){
        for (int i =  0; i < 6; ++i){
            SubsistenceArchiveTrendNaturalBean bean  = new SubsistenceArchiveTrendNaturalBean("month"+i, ""+i, ""+i);
            naturalList.add(bean);
        }
    }
    private void setData(SubsistenceVarianceTrendStatisticsBean bean) {
        float barWidth = Constants.CHART_DEFAULT_BAR_WIDTH;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

//        float randomMultiplier = 3* 100000f;
        final List<String> titleList = bean.getxTitle();
        List<SubsistenceVarianceTrendSpecificCategoryBean> categoryList = bean.getList();
        List<SubsistenceArchiveTrendNaturalBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList);
        //显示列表
//        adapter.setTitleList(titleList);
//        simulateListData(naturalList);
        adapter.setNewData(naturalList);
        showSuccessViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));

        if (ListUtils.isEmpty(titleList) || ListUtils.isEmpty(categoryList)) {
            return;
        }

        List<Float> fList = new ArrayList<>();
        int entityNumber = ListUtils.getSize(titleList);

        for (int j = 0; j < categoryList.size(); ++j) {
            SubsistenceVarianceTrendSpecificCategoryBean cb = categoryList.get(j);
            List<String> cbItem = cb.getList();
//            fList.addAll(cbItem);
            for (int i = 0; i < entityNumber; i++) {
                String str = cbItem.get(i);
                float integer = Util.getFloatFromString(str);
                fList.add(integer);
                if (j == 0) {
                    yVals1.add(new Entry(i, (float) (integer)));
                } else if (j == 1) {
                    yVals2.add(new Entry(i, (float) (integer)));

                }
//                else if (j == 2) {
//                    yVals3.add(new Entry(i, (float) (integer)));
//
//                }
            }
        }

        LineDataSet set1, set2, set3;

//        if (lcChart.getData() != null && lcChart.getData().getDataSetCount() > 0) {
//
//            set1 = (LineDataSet) lcChart.getData().getDataSetByIndex(0);
//            set2 = (LineDataSet) lcChart.getData().getDataSetByIndex(1);
////            set3 = (LineDataSet) lcChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
////            set3.setValues(yVals3);
//            lcChart.getData().notifyDataChanged();
//            lcChart.notifyDataSetChanged();
//
//        } else {
            // create 4 DataSets
            set1 = new LineDataSet(yVals1, "户数");
            set1.setColor(Constants.CHART_FIRST_COLOR);
            set1.setValueTextColor(Constants.CHART_FIRST_COLOR);
            set1.setLineWidth(Constants.LINE_CHART_LINE_WIDTH);
            set1.setCircleRadius(Constants.LINE_CHART_CIRCLE_RADIUS);
            set1.setHighLightColor(Constants.LINE_CHART_HILIGHT_COLOR);
            set1.setDrawValues(true);
            set2 = new LineDataSet(yVals2, "人数");
            set2.setColor(Constants.CHART_SECOND_COLOR);
            set2.setValueTextColor(Constants.CHART_SECOND_COLOR);
            set2.setLineWidth(Constants.LINE_CHART_LINE_WIDTH);
            set2.setCircleRadius(Constants.LINE_CHART_CIRCLE_RADIUS);
            set2.setHighLightColor(Constants.LINE_CHART_HILIGHT_COLOR);
            set2.setDrawValues(true);
//            set3 = new LineDataSet(yVals3, "注销");
//            set3.setColor(Constants.CHART_THIRD_COLOR);
//            set3.setLineWidth(Constants.LINE_CHART_LINE_WIDTH);
//            set3.setCircleRadius(Constants.LINE_CHART_CIRCLE_RADIUS);
//            set3.setHighLightColor(Constants.LINE_CHART_HILIGHT_COLOR);
//            set3.setDrawValues(true);

            LineData data = new LineData(set1, set2);
            Util.setChartDataLargeValueFormatter(data);
//            data.setValueFormatter(new LargeValueFormatter());
//            data.setValueTypeface(mTfLight);
            data.setValueTextSize(Constants.CHART_VALUE_TEXT_SIZE);

            lcChart.setData(data);
//        }

        // specify the width each bar should have
//        lcChart.getBarData().setBarWidth(barWidth);

        XAxis xAxis = lcChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                float calculatedV = v / Constants.CHART_DEFAULT_BAR_WIDTH / 1;
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
        float axisMaximum = barWidth * yVals1.size();
        axisMaximum+=(yVals1.size()-1)*Constants.CHART_GROUP_SPACE_WIDTH;
        lcChart.getXAxis().setAxisMaximum(axisMaximum);
//        lcChart.getXAxis().setAxisMaximum(2000);
//        lcChart.groupBars(0, 0.08f, 0.03f);
//        lcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);

        Util.setYAxisLargeValueFormat(lcChart.getAxisLeft(), lcChart.getAxisRight());
        Util.setYAxisSpaceTop(lcChart.getAxisLeft(), lcChart.getAxisRight());


//        lcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
//        lcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);
        lcChart.setVisibleXRangeMinimum(Constants.LINE_CHART_ITEM_NUMBER_PER_SCREEN*barWidth);
        lcChart.setVisibleXRangeMaximum(Constants.LINE_CHART_ITEM_NUMBER_PER_SCREEN*barWidth);
        lcChart.invalidate();

        lcChart.animateX(Constants.CHART_ANIMATION_DURATION);

    }

//    private void initHorizontalBarChart() {
////        lcChart.setOnChartValueSelectedListener(this);
//        // lcChart.setHighlightEnabled(false);
//
//        lcChart.setNoDataText(Constants.CHART_NO_DATA_TEXT);
//        lcChart.setNoDataTextColor(getResources().getColor(R.color.colorTheme));
//        lcChart.setDrawBarShadow(false);
//
//        lcChart.setDrawValueAboveBar(true);
//
//        lcChart.getDescription().setEnabled(false);
//
//        // if more than 60 entries are displayed in the chart, no values will be
//        // drawn
//        lcChart.setMaxVisibleValueCount(Constants.CHART_MAX_NO_VALUES_ENTRY_NUMBER);
//
//        // scaling can now only be done on x- and y-axis separately
//        lcChart.setPinchZoom(false);
//
//        // draw shadows for each bar that show the maximum value
//        // lcChart.setDrawBarShadow(true);
//
//        lcChart.setDrawGridBackground(false);
//
//        XAxis xl = lcChart.getXAxis();
//        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
////        xl.setTypeface(mTfLight);
//        xl.setDrawAxisLine(true);
//        xl.setDrawGridLines(false);
////        xl.setGranularity(Constants.CHART_DEFAULT_BAR_WIDTH);
//        xl.setGranularity(1f);
//        xl.setDrawLabels(true);
//        xl.setCenterAxisLabels(true);
////        xl.setLabelCount(8, true);
//
//        YAxis yl = lcChart.getAxisLeft();
////        yl.setTypeface(mTfLight);
//        yl.setDrawAxisLine(true);
//        yl.setDrawGridLines(false);
//        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
////        yl.setInverted(true);
//
//        YAxis yr = lcChart.getAxisRight();
////        yr.setTypeface(mTfLight);
//        yr.setDrawAxisLine(true);
//        yr.setDrawGridLines(false);
//        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
////        yr.setInverted(true);
//
////        lcChart.setFitBars(false);
//        lcChart.setFitBars(true);
//        lcChart.animateY(Constants.CHART_ANIMATION_DURATION);
//
//
//        Legend l = lcChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setFormSize(8f);
//        l.setXEntrySpace(4f);
//        l.setWordWrapEnabled(true);
//    }

//    private void setData(SubsistenceVarianceTrendStatisticsBean bean) {
//        float barWidth = Constants.CHART_DEFAULT_BAR_WIDTH;
//
//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
//
////        float randomMultiplier = 3* 100000f;
//        final List<String> titleList = bean.getxTitle();
//        List<SubsistenceVarianceTrendSpecificCategoryBean> categoryList = bean.getList();
//        List<SubsistenceArchiveTrendNaturalBean> naturalList = getNaturalBeanListFromRawBeanList(titleList, categoryList);
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
//            SubsistenceVarianceTrendSpecificCategoryBean cb = categoryList.get(j);
//            List<Float> cbItem = cb.getList();
//            for (int i = 0; i < entityNumber; i++) {
//                Float integer = cbItem.get(i);
//                if (j == 0) {
//                    yVals1.add(new BarEntry(i, (float) (integer)));
//                } else if (j == 1) {
//                    yVals2.add(new BarEntry(i, (float) (integer)));
//
//                } else if (j == 2) {
//                    yVals3.add(new BarEntry(i, (float) (integer)));
//
//                }
//            }
//        }
//
//        BarDataSet set1, set2, set3;
//
//        if (lcChart.getData() != null && lcChart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) lcChart.getData().getDataSetByIndex(0);
//            set2 = (BarDataSet) lcChart.getData().getDataSetByIndex(1);
//            set3 = (BarDataSet) lcChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
//            set3.setValues(yVals3);
//            lcChart.getData().notifyDataChanged();
//            lcChart.notifyDataSetChanged();
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
//            lcChart.setData(data);
//        }
//
//        // specify the width each bar should have
//        lcChart.getBarData().setBarWidth(barWidth);
//
//        XAxis xAxis = lcChart.getXAxis();
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
//        lcChart.getXAxis().setAxisMinimum(0);
//
//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided
//        float axisMaximum = barWidth * Constants.NUMBER_PER_DISTRICT * yVals1.size();
//        axisMaximum+=(yVals1.size()-1)*Constants.CHART_GROUP_SPACE_WIDTH;
//        lcChart.getXAxis().setAxisMaximum(axisMaximum);
////        lcChart.getXAxis().setAxisMaximum(2000);
////        lcChart.groupBars(0, 0.08f, 0.03f);
//        lcChart.groupBars(0, Constants.CHART_GROUP_SPACE_WIDTH, 0f);
//
////        lcChart.setVisibleXRangeMaximum(Constants.NUMBER_PER_DISTRICT * yVals1.size() * barWidth);
//        lcChart.setVisibleXRangeMaximum(Constants.BAR_NUMBER_PER_SCREEN*barWidth);
//        lcChart.invalidate();
//
//
//    }

    private List<SubsistenceArchiveTrendNaturalBean> getNaturalBeanListFromRawBeanList(
            List<String> titleList,
            List<SubsistenceVarianceTrendSpecificCategoryBean> rawList){
        List<SubsistenceArchiveTrendNaturalBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList) ||
                    ListUtils.isEmpty(titleList)){
                return resultList;
            } else {
//                for (int i = 0; i < ListUtils.getSize(rawList); ++i){
                    SubsistenceVarianceTrendSpecificCategoryBean bean0 = rawList.get(0);
                    SubsistenceVarianceTrendSpecificCategoryBean bean1 = rawList.get(1);
//                    SubsistenceVarianceTrendSpecificCategoryBean bean2 = rawList.get(2);
                    for (int j = 0; bean0!=null&&bean0.getList()!=null&&j<ListUtils.getSize(bean0.getList()); ++j) {
                        SubsistenceArchiveTrendNaturalBean nb = new SubsistenceArchiveTrendNaturalBean();
                        nb.setMonthName(titleList.get(j));
                        nb.setHouseholdNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean0.getList().get(j))));
                        nb.setPersonNumber(Util.getCommaSeparatedIntString(Util.getForcedIntStringFromDoubleString(bean1.getList().get(j))));
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
                adapter.getData().clear();
                rlContent.setVisibility(View.GONE);
                lcChart.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
                showChartHint(false);
/*  hilightStatisticsListIndicatorView(false);
                hilightStatisticsChartIndicatorView(true);*/
            } else {
                if (isChartSelected()) {
                    rlContent.setVisibility(View.GONE);
                    lcChart.setVisibility(View.VISIBLE);
                    showChartHint(true);
                } else {
                    rlContent.setVisibility(View.VISIBLE);
                    lcChart.setVisibility(View.GONE);
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
            adapter.getData().clear();
            rlContent.setVisibility(View.GONE);
            lcChart.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());
            showChartHint(false);
/*  hilightStatisticsListIndicatorView(false);
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
        pageType=getDeliveredInt(Constants.PAGE_TYPE_KEY);
        subsistenceType=getDeliveredString(Constants.ITEM_TYPE_KEY);
        supportType=getDeliveredString(Constants.SUB_TYPE_KEY);
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

    private void initSupportType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempResultType = Constants.EMPTY_STRING;
        if (Constants.VERY_POOR_SUPPORT_TYPE_ALL==index){
            presentation = "全部";
            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE;
        } else if (Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS == index) {
            presentation = "农村特困集中供养";
            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS_VALUE;
        } else if (Constants.VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE == index) {
            presentation = "农村特困分散供养";
            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE_VALUE;
        } else if (Constants.VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS == index){
            presentation = "城市特困集中供养";
            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS_VALUE;
        } else if (Constants.VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE == index){
            presentation = "城市特困分散供养";
            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE_VALUE;
        }
        tvSupportType.setText(presentation);
        supportType = tempResultType;
        supportTypeIndex = index;
    }

    private void initSubsistenceType(int index) {
        String presentation = Constants.EMPTY_STRING;
        String tempHouseholdType = Constants.EMPTY_STRING;
        if (Constants.HOUSEHOLD_TYPE_RURAL == index) {
            presentation = "农村低保";
            tempHouseholdType = Constants.SUBSISTENCE_TYPE_VALUE_RURAL;
        } else if (Constants.HOUSEHOLD_TYPE_URBAN == index) {
            presentation = "城市低保";
            tempHouseholdType = Constants.SUBSISTENCE_TYPE_VALUE_URBAN;
        }

//        if (Constants.SUBSISTENCE_TYPE_ALL == index) {
//            presentation = "全部";
//            tempHouseholdType = Constants.SUBSISTENCE_TYPE_VALUE_ALL;
//        } else if (Constants.SUBSISTENCE_TYPE_RURAL == index) {
//            presentation = "农村低保";
//            tempHouseholdType = Constants.SUBSISTENCE_TYPE_VALUE_RURAL;
//        } else if (Constants.SUBSISTENCE_TYPE_URBAN == index) {
//            presentation = "城市低保";
//            tempHouseholdType = Constants.SUBSISTENCE_TYPE_VALUE_URBAN;
//        }
        tvSubsistenceType.setText(presentation);
        subsistenceType = tempHouseholdType;
        subsistenceTypeIndex = index;
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

    private String getItemType(){
        return subsistenceType;
    }

    private String getIsAreaOwner(){
        return isAreaOwner;
    }

    private String getYear(){
        if (selectedDateRange != null) {
            return selectedDateRange.getYear();
        } else {
            return Constants.EMPTY_STRING;
        }
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
                .addSubsistenceArchiveTrendStatisticsFragment(new SubsistenceArchiveTrendStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getTrendListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getTrendListRequestMap() {
        Map<String, String> map = ParametersFactory.getSubsistenceArchiveTrendStatisticsListMap(getActivity(), subsistenceType,
                supportType, getYear(), getIsAreaOwner(), getAreaCodeString(), methodName);
        return map;
    }

    private SubsistenceVarianceTrendStatisticsBean getStatisticsBean(GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>> bean) {
        if (bean != null) {
            List<SubsistenceVarianceTrendStatisticsBean> list = bean.getData();
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
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<SubsistenceVarianceTrendStatisticsBean>> bean) {
        SubsistenceVarianceTrendStatisticsBean b = getStatisticsBean(bean);
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
//        String[] titleArray = {"1月", "2月", "3月", "4月", "5月", "6月"/*, "区7", "区8", "区9", "区10"
//                , "区11", "区12", "区13", "区14", "区15", "区16", "区17", "区18"*/};
//        List<String> titleList = Arrays.asList(titleArray);
//        List<SubsistenceVarianceTrendSpecificCategoryBean> dataList = new ArrayList<>();
//        for (int i = 0; i < 3; ++i) {
//            List<Float> intList = new ArrayList<>();
//            for (int j = 0; j < titleArray.length; ++j) {
//                intList.add(120f + j * i * 30);
//            }
//            SubsistenceVarianceTrendSpecificCategoryBean b = new SubsistenceVarianceTrendSpecificCategoryBean("新增" + i, intList);
//            dataList.add(b);
//        }
//        SubsistenceVarianceTrendStatisticsBean bean = new SubsistenceVarianceTrendStatisticsBean(titleList, dataList);
//        setData(bean);
    }
}
