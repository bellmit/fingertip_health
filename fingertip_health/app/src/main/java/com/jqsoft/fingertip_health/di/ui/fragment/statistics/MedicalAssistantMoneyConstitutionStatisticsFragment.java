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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.statistics.MedicalAssistantMoneyConstitutionStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MedicalAssistantMoneyConstitutionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.MedicalAssistantMoneyConstitutionStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.MedicalAssistantMoneyConstitutionStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.MedicalAssistantMoneyConstitutionStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetKcData;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetKcPie;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetLegend;
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
 * 饼状图
 * 医疗救助资金构成
 医疗救助资金补偿方式构成
 医疗救助资金补偿类型构成
 * Created by Administrator on 2018-01-02.
 */

public class MedicalAssistantMoneyConstitutionStatisticsFragment extends AbstractFragment implements MedicalAssistantMoneyConstitutionStatisticsFragmentContract.View {
    @BindView(R.id.sc_date_range)
    SegmentControl scDateRange;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_query)
    Button btQuery;
//    @BindView(R.id.tv_household_type)
//    TextView tvHouseholdType;
//    @BindView(R.id.tv_result_type)
//    TextView tvResultType;
    @BindView(R.id.lay_content)
    LinearLayout rlContent;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

//    @BindView(R.id.pc_chart)
//    PieChart pcChart;
    @BindView(R.id.ll_legend_pie_chart)
    LinearLayout llLegendPieChart;
    @BindView(R.id.al_legend)
    AssetLegend alLegend;
    @BindView(R.id.akp_pie_chart)
    AssetKcPie akpPieChart;
    @BindView(R.id.lay_medical_assistant_money_constitution_load_failure)
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

    @Inject
    MedicalAssistantMoneyConstitutionStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

//    int householdTypeIndex = Constants.HOUSEHOLD_TYPE_ALL;
//    String householdType;
//    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
//    String resultType;

    String type;// 统计类型	“0”	医疗救助资金构成	“1”	医疗救助资金补偿方式构成	“2”	医疗救助资金补偿类型构成

    String methodName;

    MedicalAssistantMoneyConstitutionStatisticsAdapter adapter;

    public MedicalAssistantMoneyConstitutionStatisticsFragment() {
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
        return R.layout.fragment_medical_assistant_money_constitution_statistics_layout;
    }

    @Override
    protected void initData() {
        type=getDeliveredString(Constants.STATISTICS_TYPE_KEY);
        methodName=getDeliveredString(Constants.METHOD_KEY);
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

//        initHouseholdType(householdTypeIndex);
//        RxView.clicks(tvHouseholdType)
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
//                        String[] typeArray = new String[]{"全部", "城市", "农村"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择户籍类型", null, typeList,
//                                householdTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initHouseholdType(which);
//                                        return false;
//                                    }
//                                });
//                    }
//                });
//
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

        initPieChart();

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

        adapter = new MedicalAssistantMoneyConstitutionStatisticsAdapter(new ArrayList<MedicalAssistantMoneyConstitutionBean>());
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
                                llLegendPieChart.setVisibility(View.GONE);
//                                pcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);

                            } else {
                                rlContent.setVisibility(View.VISIBLE);
                                llLegendPieChart.setVisibility(View.GONE);
//                                pcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.GONE);
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            llLegendPieChart.setVisibility(View.GONE);
//                            pcChart.setVisibility(View.GONE);
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
                                llLegendPieChart.setVisibility(View.GONE);
//                                pcChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);

                            } else {
                                rlContent.setVisibility(View.GONE);
                                llLegendPieChart.setVisibility(View.VISIBLE);
//                                pcChart.setVisibility(View.VISIBLE);
                                failureView.setVisibility(View.GONE);
                                hilightStatisticsListIndicatorView(false);
                                hilightStatisticsChartIndicatorView(true);
                            }
                        } else {
                            rlContent.setVisibility(View.GONE);
                            llLegendPieChart.setVisibility(View.GONE);
//                            pcChart.setVisibility(View.GONE);
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

    private void initPieChart() {

//        pcChart.setNoDataText(Constants.CHART_NO_DATA_TEXT);
//        pcChart.setNoDataTextColor(getResources().getColor(R.color.colorTheme));
//
//        pcChart.getDescription().setEnabled(false);
//
//        pcChart.setUsePercentValues(true);
//        pcChart.getDescription().setEnabled(false);
//        pcChart.setExtraOffsets(20, 0, 20, 0);
//
//        pcChart.setDragDecelerationFrictionCoef(0.95f);
//
//        pcChart.setCenterText(Constants.EMPTY_STRING);
//
//        pcChart.setDrawHoleEnabled(true);
//        pcChart.setHoleColor(Color.WHITE);
//
//        pcChart.setTransparentCircleColor(Color.WHITE);
//        pcChart.setTransparentCircleAlpha(110);
//
//        pcChart.setHoleRadius(58f);
//        pcChart.setTransparentCircleRadius(61f);
//
//        pcChart.setDrawCenterText(true);
//        pcChart.setDrawEntryLabels(false);
//
//        pcChart.setRotationAngle(0);
//        // enable rotation of the chart by touch
//        pcChart.setRotationEnabled(true);
//        pcChart.setHighlightPerTapEnabled(true);
//
//        // pcChart.setUnit(" €");
//        // pcChart.setDrawUnitsInChart(true);
//
//        Legend l = pcChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setFormSize(8f);
//        l.setXEntrySpace(4f);
//        l.setWordWrapEnabled(true);
    }

    private void setPieChartData(List<MedicalAssistantMoneyConstitutionBean> beanList){
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : Constants.CHART_COLOR_LIST){
            colors.add(c);
        }

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        List<AssetKcData> list = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        for (int i = 0; i < beanList.size() ; i++) {
            MedicalAssistantMoneyConstitutionBean bean = beanList.get(i);
//            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
//                    Constants.EMPTY_STRING));
            String name = bean.getName();
            float value = (float) Util.getFloatFromPercentString(String.valueOf(bean.getValue()));
            floatList.add(value);
            AssetKcData data = new AssetKcData(colors.get(i), name, value, Constants.EMPTY_STRING);
            list.add(data);
            titleList.add(name);
//            entries.add(new PieEntry(value,
//                    bean.getName()));
////            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
////                    bean.getName()+Constants.SPACE_STRING+bean.getValue()));
        }

        float sum = Util.getSum(floatList);
        for (int i = 0; i < list.size(); ++i){
            AssetKcData data = list.get(i);
            float percent = Util.getPercent(data.getNum(), sum);
            data.setPercent(String.valueOf(percent));
        }
        akpPieChart.setData(list, sum);
        alLegend.setData(colors, titleList);


    }


    private void setData(List<MedicalAssistantMoneyConstitutionBean> beanList) {
        List<MedicalAssistantMoneyConstitutionBean> naturalList = getNaturalBeanListFromRawBeanList(beanList);
        //显示列表
//        adapter.setTitleList(titleList);
        adapter.setNewData(naturalList);
        showSuccessViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));

        if (ListUtils.isEmpty(beanList)) {
            return;
        }

        setPieChartData(beanList);

//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//
//        boolean b = Util.isItemPercentVerySmallCountLessThanFixedNumberFromMedicalAssistantMoneyConstitutionBeanList(beanList);
//        b=true;
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        if (b){
//            for (int i = 0; i < beanList.size() ; i++) {
//                MedicalAssistantMoneyConstitutionBean bean = beanList.get(i);
////            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
////                    Constants.EMPTY_STRING));
//                float value = (float) Util.getFloatFromPercentString(String.valueOf(bean.getValue()));
//                entries.add(new PieEntry(value,
//                        bean.getName()));
////            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
////                    bean.getName()+Constants.SPACE_STRING+bean.getValue()));
//            }
//        } else {
//            for (int i = 0; i < beanList.size(); i++) {
//                MedicalAssistantMoneyConstitutionBean bean = beanList.get(i);
//                float value = (float) Util.getFloatFromPercentString(String.valueOf(bean.getValue()));
//                boolean isSmall = Util.isItemPercentVerySmallInMedicalAssistantMoneyConstitutionBeanList(value, beanList);
//                if (!isSmall) {
//                    entries.add(new PieEntry(value,
//                            bean.getName()));
//                }
////                else {
////                    entries.add(new PieEntry(value,
////                            Constants.EMPTY_STRING));
////
////                }
//            }
//        }
//
//
////        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
////        // the chart.
////        for (int i = 0; i < naturalList.size() ; i++) {
////            MedicalAssistantMoneyConstitutionBean bean = naturalList.get(i);
////            entries.add(new PieEntry(Util.getFloatFromPercentString(bean.getValue()),
////                    bean.getName()));
//////            entries.add(new PieEntry(Util.getFloatFromPercentString(bean.getValue()),
//////                    bean.getName()+Constants.SPACE_STRING+bean.getValue()));
////        }
//
//        String chartTitle = getChartTitle();
//        PieDataSet dataSet = new PieDataSet(entries, "");
//
//        dataSet.setDrawIcons(false);
//
//        dataSet.setSliceSpace(3f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(5f);
//
//        // add a lot of colors
//
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for (int c : Constants.CHART_COLOR_LIST){
//            colors.add(c);
//        }
//
//
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
//
//        dataSet.setColors(colors);
//        //dataSet.setSelectionShift(0f);
//
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
////        dataSet.setValueLinePart2Length(2f);
//        dataSet.setValueLinePart2Length(0.4f);
//        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//
//
//        pcChart.setEntryLabelColor(Color.BLACK);
//
//        List<Float> fl = Util.getFloatListFromMedicalAssistantMoneyConstitutionBeanList(beanList);
//
//        PieData data = new PieData(dataSet);
////        data.setValueFormatter(new PercentFormatter());
//        data.setValueFormatter(new PercentAndLabelFormatter(b, fl));
//        data.setValueTextSize(Constants.CHART_VALUE_TEXT_SIZE);
//        data.setValueTextColor(Color.BLACK);
//        pcChart.setData(data);
//
////        for (IDataSet<?> set : pcChart.getData().getDataSets())
////            set.setDrawValues(true);
//
//
//
//
//        // undo all highlights
//        pcChart.highlightValues(null);
//        pcChart.invalidate();
//
//        pcChart.animateY(Constants.CHART_ANIMATION_DURATION, Easing.EasingOption.EaseInOutQuad);
//        // mChart.spin(2000, 0, 360);


    }

    private String getChartTitle(){
        String chartTitle = Constants.EMPTY_STRING;
        if (Constants.MEDICAL_ASSISTANT_MONEY_CONSTITUTIONI_VALUE.equals(type)){
            chartTitle = "资金构成";
        } else if (Constants.MEDICAL_ASSISTANT_COMPENSATE_METHOD_CONSTITUTION_VALUE.equals(type)){
            chartTitle = "补偿方式构成";
        } else if (Constants.MEDICAL_ASSISTANT_COMPENSATE_TYPE_CONSTITUTION_VALUE.equals(type)){
            chartTitle = "补偿类型构成";
        }
        return chartTitle;
    }

    private List<MedicalAssistantMoneyConstitutionBean> getNaturalBeanListFromRawBeanList(
            List<MedicalAssistantMoneyConstitutionBean> rawList){
        List<MedicalAssistantMoneyConstitutionBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList)){
                return resultList;
            } else {
                for (int i = 0; i < rawList.size(); ++i){
                    MedicalAssistantMoneyConstitutionBean nvb = rawList.get(i);
                    nvb.setValue(Util.getNonscientificNumberStringFromString(nvb.getValue()));
                }

                resultList=rawList;
//                    for (int j = 0; j < rawList.size(); ++j) {
//                        MedicalAssistantMoneyConstitutionBean b = rawList.get(j);
//                        MedicalAssistantMoneyConstitutionBean nb = new MedicalAssistantMoneyConstitutionBean();
//                        nb.setTitle(b.getName());
//                        nb.setHouseholdNumber(b.getValue());
//                        nb.setPercent(b.getPercentval());
//                        resultList.add(nb);
//                    }
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
                llLegendPieChart.setVisibility(View.GONE);
//                pcChart.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
               /* hilightStatisticsListIndicatorView(false);
                hilightStatisticsChartIndicatorView(true);*/
            } else {
                if (isChartSelected()) {
                    rlContent.setVisibility(View.GONE);
                    llLegendPieChart.setVisibility(View.VISIBLE);
//                    pcChart.setVisibility(View.VISIBLE);
                } else {
                    rlContent.setVisibility(View.VISIBLE);
                    llLegendPieChart.setVisibility(View.GONE);
//                    pcChart.setVisibility(View.GONE);
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
            llLegendPieChart.setVisibility(View.GONE);
//            pcChart.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());
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
//    private void initHouseholdType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempHouseholdType = Constants.EMPTY_STRING;
//        if (Constants.HOUSEHOLD_TYPE_ALL == index) {
//            presentation = "全部";
//            tempHouseholdType = Constants.HOUSEHOLD_TYPE_VALUE_ALL;
//        } else if (Constants.HOUSEHOLD_TYPE_CITY == index) {
//            presentation = "城市";
//            tempHouseholdType = Constants.HOUSEHOLD_TYPE_VALUE_CITY;
//        } else if (Constants.HOUSEHOLD_TYPE_COUNTRYSIDE == index) {
//            presentation = "农村";
//            tempHouseholdType = Constants.HOUSEHOLD_TYPE_VALUE_COUNTRYSIDE;
//        }
//        tvHouseholdType.setText(presentation);
//        householdType = tempHouseholdType;
//        householdTypeIndex = index;
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
                .addMedicalAssistantMoneyConstitutionStatisticsFragment(new MedicalAssistantMoneyConstitutionStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getMedicalAssistantListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getMedicalAssistantListRequestMap() {
        Map<String, String> map = ParametersFactory.getMedicalAssistantMoneyConstitutionStatisticsListMap(getActivity(),  type,
                 getStartDateString(), getEndDateString(), getAreaCodeString(), methodName);
        return map;
    }

    private List<MedicalAssistantMoneyConstitutionBean> getStatisticsBean(GCAHttpResultBaseBean<List<MedicalAssistantMoneyConstitutionBean>> bean) {
        if (bean != null) {
            List<MedicalAssistantMoneyConstitutionBean> list = bean.getData();
            return list;
        } else {
            return null;
        }
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<MedicalAssistantMoneyConstitutionBean>> bean) {
        List<MedicalAssistantMoneyConstitutionBean> b = getStatisticsBean(bean);
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
        isRequestSuccess = true;
        showSuccessViewOrFailureView(true, false);

        String[] titleArray = {"直接医疗救助（万元）", "资助参合参保（万元）"/*, "失业", "4月", "5月", "6月", "区7", "区8", "区9", "区10"
                , "区11", "区12", "区13", "区14", "区15", "区16", "区17", "区18"*/};
        List<String> titleList = Arrays.asList(titleArray);

        List<MedicalAssistantMoneyConstitutionBean> list = new ArrayList<>();
        for (int i = 0; i < titleArray.length; ++i){
            MedicalAssistantMoneyConstitutionBean b = new MedicalAssistantMoneyConstitutionBean(
                    titleList.get(i), String.valueOf(100+i*50));
            list.add(b);
        }

        setData(list);
    }
}
