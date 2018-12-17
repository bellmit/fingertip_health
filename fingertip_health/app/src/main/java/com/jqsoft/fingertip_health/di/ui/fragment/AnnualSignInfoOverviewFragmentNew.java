package com.jqsoft.fingertip_health.di.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.bean.response_new.SignInfoOverviewResultBean;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.feature.NumberAndPercentInterface;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.RingBlockCustomView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-06-28.
 */

//年度签约信息一览
public class AnnualSignInfoOverviewFragmentNew extends AbstractFragment {
    @BindView(R.id.tv_medical_institution_name)
    TextView tvMedicalInstitutionName;
    @BindView(R.id.tv_annual_sign_info_overview)
    TextView tvAnnualSignInfoOverview;
    //    @BindView(R.id.dpv_fee_sign)
//    DonutPercentageView dpvFeeSign;
    @BindView(R.id.ll_chart_container)
    LinearLayout llChartContainer;
    @BindView(R.id.tv_total_fee_sign_number)
    TextView tvTotalFeeSignNumber;
    @BindView(R.id.tv_sign_ratio)
    TextView tvSignRatio;

    @BindView(R.id.iv_arrow_left)
    ImageView ivArrowLeft;
    @BindView(R.id.iv_arrow_right)
    ImageView ivArrowRight;

    String type;
    int totalNumber;
    int index;
    String organizationName;
    String year;
    String totalFeeSignNumber;
    String signRatio;
    String signInfoHint;

    SignInfoOverviewResultBean bean;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_annual_sign_info_overview_new;
    }

    @Override
    protected void initData() {
        type=getDeliveredString(Constants.CONTAINER_TYPE_KEY);
        totalNumber = getDeliveredInt(Constants.TOTAL_SIGN_INFO_OVERVIEW_NUMBER_KEY);
        index = getDeliveredInt(Constants.SIGN_INFO_OVERVIEW_INDEX_KEY);
        bean= (SignInfoOverviewResultBean) getDeliveredSerializable(Constants.SIGN_INFO_KEY);
//        organizationName = getDeliveredString(Constants.CLINIC_NAME_KEY);
//        year=getDeliveredString(Constants.YEAR_KEY);
//        totalFeeSignNumber=getDeliveredString(Constants.TOTAL_FEE_SIGN_NUMBER);
//        signRatio=getDeliveredString(Constants.SIGN_RATIO);
//        signInfoHint=getDeliveredString(Constants.SIGN_INFO_HINT);

    }

    private void initChart(){
        if (bean!=null) {
            final List<NumberAndPercentInterface> beanList = new ArrayList<>();
            NumberAndPercentInterface paidSign = new NumberAndPercentInterface() {
                @Override
                public String getHint() {
                    return Constants.HINT_PAID_SIGN;
                }

                @Override
                public String getNumber() {
                    return Util.trimString(bean.getQyPaidCount());
                }

                @Override
                public String getPercent() {
                    return Util.trimString(bean.getQyPaidLv());
                }

                @Override
                public int getImageId() {
                    return R.mipmap.i_sign_info_overview_chart_paid_sign;
                }
            };
            NumberAndPercentInterface diabetes = new NumberAndPercentInterface() {
                @Override
                public String getHint() {
                    return Constants.HINT_DIABETES;
                }

                @Override
                public String getNumber() {
                    return Util.trimString(bean.getQyTnbCount());
                }

                @Override
                public String getPercent() {
                    return Util.trimString(bean.getQyTnbLv());
                }

                @Override
                public int getImageId() {
                    return R.mipmap.i_sign_info_overview_chart_diabetes;
                }
            };
            NumberAndPercentInterface hypertension = new NumberAndPercentInterface() {
                @Override
                public String getHint() {
                    return Constants.HINT_HYPERTENSION;
                }

                @Override
                public String getNumber() {
                    return Util.trimString(bean.getQyGxyCount());
                }

                @Override
                public String getPercent() {
                    return Util.trimString(bean.getQyGxyLv());
                }

                @Override
                public int getImageId() {
                    return R.mipmap.i_sign_info_overview_chart_hypertension;
                }
            };
            NumberAndPercentInterface elderlyPeople = new NumberAndPercentInterface() {
                @Override
                public String getHint() {
                    return Constants.HINT_ELDERLY_PEOPLE;
                }

                @Override
                public String getNumber() {
                    return Util.trimString(bean.getQyOldCount());
                }

                @Override
                public String getPercent() {
                    return Util.trimString(bean.getQyOldLv());
                }

                @Override
                public int getImageId() {
                    return R.mipmap.i_sign_info_overview_chart_elderly_people;
                }
            };
            beanList.add(paidSign);
            beanList.add(diabetes);
            beanList.add(hypertension);
            beanList.add(elderlyPeople);

            int unselectedTitleColor = getResources().getColor(R.color.unselected_title_color);
            int selectedTitleColor = getResources().getColor(R.color.selected_title_color);
            int unselectedTitleBackgroundColor = getResources().getColor(R.color.unselected_title_background_color);
            int selectedTitleBackgroundColor = getResources().getColor(R.color.selected_title_background_color);
            RingBlockCustomView rbcv = new RingBlockCustomView(getActivity(), beanList, unselectedTitleColor,
                    selectedTitleColor, unselectedTitleBackgroundColor, selectedTitleBackgroundColor, new RingBlockCustomView.RingBlockViewListener() {
                @Override
                public void ringBlockViewDidClick(int index) {

                }
            });
            llChartContainer.addView(rbcv);
        }
    }

    @Override
    protected void initView() {
        tvMedicalInstitutionName.setText(getMedicalRoomName()+getSignInfoOverview());
        tvAnnualSignInfoOverview.setText(getSignInfoOverview());
        initChart();
        ivArrowLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                int destIndex = index-1;
                gotoIndex(type, destIndex);
            }
        });
        ivArrowRight.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                int destIndex = index+1;
                gotoIndex(type, destIndex);
            }
        });

        if (index==0){
            ivArrowLeft.setVisibility(View.GONE);
        }
        if (index==totalNumber-1){
            ivArrowRight.setVisibility(View.GONE);
        }

//        dpvFeeSign.setValue(totalFeeSignNumber);
//        dpvFeeSign.setHint(signInfoHint);
//        dpvFeeSign.setPercentageValue(Util.getFloatFromString(signRatio)/100);

//        tvAnnualSignInfoOverview.setText(getMedicalRoomAnnualSignInfoOverviewHint());
        tvTotalFeeSignNumber.setText(getTotalFeeSignNumberHint());
        tvSignRatio.setText(getSignRatioHint());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    public void gotoIndex(String type, int destIndex){
        int eventId = 0 ;
        if (Constants.TYPE_INDEX.equals(type)){
            eventId=Constants.EVENT_TYPE_DID_CLICK_LEFT_RIGHT_ARROW_IN_SIGN_INFO_OVERVIEW;
        } else if (Constants.TYPE_MY_SIGN_INFO.equals(type)){
            eventId=Constants.EVENT_TYPE_DID_CLICK_LEFT_RIGHT_ARROW_IN_MY_SIGN_INFO_OVERVIEW;
        }
        RxBus.getDefault().post(eventId, destIndex);

    }

    public String getMedicalRoomName(){
        return  Util.trimString(Identity.getOrganizationName());
    }

    public String getYear(){
        if (bean!=null) {
            return Util.trimString(bean.getYear());
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public String getSignInfoOverview(){
        return  getYear()+Constants.ANNUAL_SIGN_INFO_OVERVIEW;
    }

    public String getMedicalRoomAnnualSignInfoOverviewHint() {
        String result = String.format(Locale.CHINA, getResources().getString(R.string.clinic_annual_sign_info_overview), organizationName, year);
        return result;
    }

    public String getTotalFeeSignNumberHint() {
        String result = String.format(Locale.CHINA, getResources().getString(R.string.specific_sign_number), signInfoHint, totalFeeSignNumber);
        return result;
    }

    public String getSignRatioHint() {
        String percentageString = Util.getPercentageStringFromString(signRatio);
        String result = String.format(Locale.CHINA, getResources().getString(R.string.sign_ratio), percentageString);
        return result;
    }
}
