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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.statistics.InstitutionCharacterClassificationStatisticsAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionCharacterNameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.di.contract.InstitutionCharacterClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di.module.InstitutionCharacterClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.InstitutionCharacterClassificationStatisticsFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.feature.IDateRange;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetKcData;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetKcPie;
import com.jqsoft.fingertip_health.helper.chart.piechart.AssetLegend;
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
 * 饼状图
 * 机构性质 分类统计
 * Created by Administrator on 2018-01-02.
 */

public class InstitutionCharacterClassificationStatisticsFragment extends AbstractFragment implements InstitutionCharacterClassificationStatisticsFragmentContract.View {
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
    @BindView(R.id.ll_institution_type)
    LinearLayout llInstitutionType;
    @BindView(R.id.tv_institution_type)
    TextView tvInstitutionType;
//    @BindView(R.id.ll_support_type)
//    LinearLayout llSupportType;
//    @BindView(R.id.tv_support_type)
//    TextView tvSupportType;
    @BindView(R.id.lay_content)
    LinearLayout rlContent;
//    @BindView(R.id.tv_poverty_reason)
//    TextView tvPovertyReason;
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
    @BindView(R.id.lay_institution_character_classification_load_failure)
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
    InstitutionCharacterClassificationStatisticsFragmentPresenter mPresenter;

    MonthQuarterYearRangePopupWindow dateRangePopupWindow;
    IDateRange selectedDateRange;

//    int householdTypeIndex = Constants.HOUSEHOLD_TYPE_ALL;
//    String householdType;
//    int resultTypeIndex = Constants.RESCUE_RESULT_TYPE_HOUSEHOLD;
//    String resultType;

    String isAreaOwner=Constants.IS_NOT_AREA_OWNER;
    //    String rescueType;//(城乡低保:99	全部  01	农村低保    02	城市低保)   (特困人员供养：03） （低收入家庭：04）
//    String statisticsType;  //1	致贫原因    2	退保注销原因
    String methodName;

//    int pageType;

    int institutionTypeIndex = Constants.INSTITUTION_TYPE_ALL;
    String institutionType;
//    int supportTypeIndex = Constants.VERY_POOR_SUPPORT_TYPE_ALL;
//    String supportType;


    InstitutionCharacterClassificationStatisticsAdapter adapter;

    public InstitutionCharacterClassificationStatisticsFragment() {
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
        return R.layout.fragment_institution_character_classification_statistics_layout;
    }

    @Override
    protected void initData() {
        populateData();
    }

    private void populateData() {
//        pageType=getDeliveredInt(Constants.PAGE_TYPE_KEY);
//        institutionType =getDeliveredString(Constants.ITEM_TYPE_KEY);
//        supportType=getDeliveredString(Constants.SUB_TYPE_KEY);
        isAreaOwner=getDeliveredString(Constants.IS_AREA_OWNER_KEY);
        methodName=getDeliveredString(Constants.METHOD_NAME_KEY);

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

//        if (Constants.PAGE_TYPE_SUBSISTENCE==pageType){
//            initInstitutionType(institutionTypeIndex);
//        } else if (Constants.PAGE_TYPE_VERY_POOR==pageType){
//            initSupportType(supportTypeIndex);
//        } else {
//        }
        initInstitutionType(institutionTypeIndex);

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
//                        String[] typeArray = new String[]{"全部", "农村特困集中供养", "农村特困分散供养", "城市特困集中供养", "城市特困分散供养"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(getActivity(), "请选择特困供养类型", null, typeList,
//                                supportTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initSupportType(which);
//                                        onRefresh();
//                                        return false;
//                                    }
//                                });
//
//                    }
//                });


//        if (Constants.PAGE_TYPE_SUBSISTENCE==(pageType)){
//            llInstitutionType.setVisibility(View.VISIBLE);
//            llSupportType.setVisibility(View.GONE);
//        } else if (Constants.PAGE_TYPE_VERY_POOR==(pageType)){
//            llInstitutionType.setVisibility(View.GONE);
//            llSupportType.setVisibility(View.VISIBLE);
//        } else if (Constants.PAGE_TYPE_LOW_SALARY_FAMILY==(pageType)){
//            llInstitutionType.setVisibility(View.GONE);
//            llSupportType.setVisibility(View.GONE);
//        }else {
//            llInstitutionType.setVisibility(View.VISIBLE);
//            llSupportType.setVisibility(View.GONE);
//        }
//        llInstitutionType.setVisibility(View.VISIBLE);


        initRecyclerViewHeaderVisibilityAndValue();
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

        adapter = new InstitutionCharacterClassificationStatisticsAdapter(new ArrayList<InstitutionCharacterNameValueBean>());
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
//                                pcChart.setVisibility(View.GONE);
                                llLegendPieChart.setVisibility(View.GONE);
                                failureView.setVisibility(View.VISIBLE);
                                tvFailureView.setText(getListEmptyHint());
                                hilightStatisticsListIndicatorView(true);
                                hilightStatisticsChartIndicatorView(false);

                            } else {
                                rlContent.setVisibility(View.VISIBLE);
//                                pcChart.setVisibility(View.GONE);
                                llLegendPieChart.setVisibility(View.GONE);
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

    private void initRecyclerViewHeaderVisibilityAndValue() {
//        if (Constants.POVERTY_REASON_POVERTY.equals(statisticsType)){
//            tvPovertyReason.setText("致贫原因");
//        } else if (Constants.POVERTY_REASON_CANCEL.equals(statisticsType)){
//            tvPovertyReason.setText("退保原因");
//        }
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
////        pcChart.setDrawEntryLabels(false);
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

    private void setPieChartData(List<InstitutionCharacterNameValueBean> beanList){
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
            InstitutionCharacterNameValueBean bean = beanList.get(i);
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


    private void setData(List<InstitutionCharacterNameValueBean> beanList) {
        List<InstitutionCharacterNameValueBean> naturalList = getNaturalBeanListFromRawBeanList(beanList);
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
//        boolean b = Util.isItemPercentVerySmallCountLessThanFixedNumberFromInstitutionCharacterNameValueBeanList(beanList);
//        b=true;
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        if (b){
//            for (int i = 0; i < beanList.size() ; i++) {
//                InstitutionCharacterNameValueBean bean = beanList.get(i);
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
//                InstitutionCharacterNameValueBean bean = beanList.get(i);
//                float value = (float) Util.getFloatFromPercentString(String.valueOf(bean.getValue()));
//                boolean isSmall = Util.isItemPercentVerySmallInInstitutionCharacterNameValueBeanList(value, beanList);
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
////        for (int i = 0; i < beanList.size() ; i++) {
////            InstitutionCharacterNameValueBean bean = naturalList.get(i);
////            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
////                    bean.getName()));
//////            entries.add(new PieEntry((float) Util.getFloatFromPercentString(String.valueOf(bean.getValue())),
//////                    bean.getName()+Constants.SPACE_STRING+bean.getValue()+Constants.SPACE_STRING+bean.getPercent()));
////        }
//
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
//
//        for (int c : Constants.CHART_COLOR_LIST){
//            colors.add(c);
//        }
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
//        List<Float> fl = Util.getFloatListFromInstitutionCharacterNameValueBeanList(beanList);
//
//        PieData data = new PieData(dataSet);
////        data.setValueFormatter(new PercentFormatter());
//        data.setValueFormatter(new PercentAndLabelFormatter(b,fl));
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

    private List<InstitutionCharacterNameValueBean> getNaturalBeanListFromRawBeanList(
            List<InstitutionCharacterNameValueBean> rawList){
        List<InstitutionCharacterNameValueBean> resultList = new ArrayList<>();
        try {
            if (ListUtils.isEmpty(rawList)){
                return resultList;
            } else {
                for (int i = 0; i < rawList.size(); ++i){
                    InstitutionCharacterNameValueBean nvb = rawList.get(i);
                    nvb.setValue(Util.getCommaSeparatedForcedIntString(nvb.getValue()));
                    nvb.setBednum(Util.getCommaSeparatedForcedIntString(nvb.getBednum()));
                }

                resultList=rawList;
//                    for (int j = 0; j < rawList.size(); ++j) {
//                        SubsistenceApprovePovertyReasonBean b = rawList.get(j);
//                        InstitutionCharacterNameValueBean nb = new InstitutionCharacterNameValueBean();
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
              /*  hilightStatisticsListIndicatorView(false);
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

//    private void initSupportType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempResultType = Constants.EMPTY_STRING;
//        if (Constants.VERY_POOR_SUPPORT_TYPE_ALL==index){
//            presentation = "全部";
//            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_ALL_VALUE;
//        } else if (Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS == index) {
//            presentation = "农村特困集中供养";
//            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_RURAL_FOCUS_VALUE;
//        } else if (Constants.VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE == index) {
//            presentation = "农村特困分散供养";
//            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_RURAL_DISPERSE_VALUE;
//        } else if (Constants.VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS == index){
//            presentation = "城市特困集中供养";
//            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_URBAN_FOCUS_VALUE;
//        } else if (Constants.VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE == index){
//            presentation = "城市特困分散供养";
//            tempResultType = Constants.VERY_POOR_SUPPORT_TYPE_URBAN_DISPERSE_VALUE;
//        }
//        tvSupportType.setText(presentation);
//        supportType = tempResultType;
//        supportTypeIndex = index;
//    }

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
                .addInstitutionCharacterClassificationStatisticsFragment(new InstitutionCharacterClassificationStatisticsFragmentModule(this))
                .inject(this);
    }

    private void onRefresh() {
        loadInfo();
    }

    private void loadInfo() {
        Map<String, String> map = getCharacterClassificationListRequestMap();
        mPresenter.main(map);
    }

    private Map<String, String> getCharacterClassificationListRequestMap() {
        Map<String, String> map = ParametersFactory.getInstitutionCharacterClassificationStatisticsListMap(getActivity(), institutionType,
                getAreaCodeString(), methodName);
        return map;
    }

    private List<InstitutionCharacterNameValueBean> getStatisticsBean(GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>> bean) {
        if (bean != null) {
            List<InstitutionCharacterNameValueBean> list = bean.getData();
            return list;
        } else {
            return null;
        }
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<InstitutionCharacterNameValueBean>> bean) {
        List<InstitutionCharacterNameValueBean> b = getStatisticsBean(bean);
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

        String[] titleArray = {"缺乏劳动力", "失地", "失业"/*, "4月", "5月", "6月", "区7", "区8", "区9", "区10"
                , "区11", "区12", "区13", "区14", "区15", "区16", "区17", "区18"*/};
        List<String> titleList = Arrays.asList(titleArray);

        List<InstitutionCharacterNameValueBean> list = new ArrayList<>();
        for (int i = 0; i < titleArray.length; ++i){
            InstitutionCharacterNameValueBean b = new InstitutionCharacterNameValueBean(titleList.get(i), String.valueOf(100+i*50),
                    ""+i, ""+i);
            list.add(b);
        }

        setData(list);
    }
}
