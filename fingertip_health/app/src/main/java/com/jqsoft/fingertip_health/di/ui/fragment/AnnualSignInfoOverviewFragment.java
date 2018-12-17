package com.jqsoft.fingertip_health.di.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.DonutPercentageView;

import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-06-28.
 */

//年度签约信息一览
public class AnnualSignInfoOverviewFragment extends AbstractFragment {
    @BindView(R.id.tv_annual_sign_info_overview)
    TextView tvAnnualSignInfoOverview;
    @BindView(R.id.dpv_fee_sign)
    DonutPercentageView dpvFeeSign;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_annual_sign_info_overview;
    }

    @Override
    protected void initData() {
        type=getDeliveredString(Constants.CONTAINER_TYPE_KEY);
        totalNumber = getDeliveredInt(Constants.TOTAL_SIGN_INFO_OVERVIEW_NUMBER_KEY);
        index = getDeliveredInt(Constants.SIGN_INFO_OVERVIEW_INDEX_KEY);
        organizationName = getDeliveredString(Constants.CLINIC_NAME_KEY);
        year=getDeliveredString(Constants.YEAR_KEY);
        totalFeeSignNumber=getDeliveredString(Constants.TOTAL_FEE_SIGN_NUMBER);
        signRatio=getDeliveredString(Constants.SIGN_RATIO);
        signInfoHint=getDeliveredString(Constants.SIGN_INFO_HINT);

    }

    @Override
    protected void initView() {
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

        dpvFeeSign.setValue(totalFeeSignNumber);
        dpvFeeSign.setHint(signInfoHint);
        dpvFeeSign.setPercentageValue(Util.getFloatFromString(signRatio)/100);

        tvAnnualSignInfoOverview.setText(getMedicalRoomAnnualSignInfoOverviewHint());
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
