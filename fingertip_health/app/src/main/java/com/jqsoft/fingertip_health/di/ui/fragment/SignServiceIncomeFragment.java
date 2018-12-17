package com.jqsoft.fingertip_health.di.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceIncomeResultBean;
import com.jqsoft.fingertip_health.content.SignServiceIncomePerCategoryContent;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.simulate.SimulateData;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-07-10.
 */

public class SignServiceIncomeFragment extends AbstractFragment /*implements SignServiceIncomeFragmentContract.View*/ {
    @BindView(R.id.sv_content)
    ScrollView svContent;
    @BindView(R.id.tv_sign_number)
    TextView tvSignNumber;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.lay_sign_service_income_load_failure)
    View laySignServiceIncomeLoadFailure;

//    String type;
    SignServiceIncomeResultBean item;

//    @Inject
//    SignServiceIncomeFragmentPresenter mPresenter;


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_service_income;
    }


    @Override
    protected void initData() {
        item = (SignServiceIncomeResultBean) getDeliveredSerializable(Constants.SIGN_SERVICE_INCOME_BEAN_KEY);
    }

    @Override
    protected void initView() {
        showView(item);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addSignServiceIncomeFragment(new SignServiceIncomeFragmentModule(this))
//                .inject(this);
    }

    public void showSuccessOrFailureView(boolean success){
        if (success){
            svContent.setVisibility(View.VISIBLE);
            laySignServiceIncomeLoadFailure.setVisibility(View.GONE);
        } else {
            svContent.setVisibility(View.GONE);
            laySignServiceIncomeLoadFailure.setVisibility(View.VISIBLE);
        }
    }

    public void showView(SignServiceIncomeResultBean bean){
        showSuccessOrFailureView(bean!=null);
        if (bean!=null){
            String signNumber = getResources().getString(R.string.sign_number);
            String signNumberString = String.format(Locale.CHINA, signNumber, Util.trimString(bean.getQyrs()));
            tvSignNumber.setText(signNumberString);

//            String type = Util.trimString(bean.getType());
            String totalMoney=Util.trimString(bean.getPackSumFee());
            String actualReceiveMoney = Util.trimString(bean.getActualPackageSumFee());
            String selfPaidMoney = Util.trimString(bean.getShouldSelfFee());
            String medicalInsuranceCompensateMoney = Util.trimString(bean.getNewRuralCMSFee());
            String reductionAndExemptionMoney = Util.trimString(bean.getOtherReduceFee());

            String totalIncomeHint = getResources().getString(R.string.sign_service_total_income);
            SignServiceIncomePerCategoryContent totalIncomeContent = new SignServiceIncomePerCategoryContent(getActivity());
            totalIncomeContent.initView(Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_TOTAL, totalMoney, totalIncomeHint);
            llContent.addView(totalIncomeContent.getView());

            String actualReceiveHint = getResources().getString(R.string.sign_service_actual_receive_money);
            SignServiceIncomePerCategoryContent actualReceiveContent = new SignServiceIncomePerCategoryContent(getActivity());
            actualReceiveContent.initView(Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_ACTUAL, actualReceiveMoney, actualReceiveHint);
            llContent.addView(actualReceiveContent.getView());

            String selfPaidHint = getResources().getString(R.string.sign_service_self_paid_money);
            SignServiceIncomePerCategoryContent selfPaidContent = new SignServiceIncomePerCategoryContent(getActivity());
            selfPaidContent.initView(Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_SELF, selfPaidMoney, selfPaidHint);
            llContent.addView(selfPaidContent.getView());

            String medicalInsuranceCompensateHint = getResources().getString(R.string.sign_service_compensate_money_of_medical_insurance);
            SignServiceIncomePerCategoryContent medicalInsuranceCompensateContent = new SignServiceIncomePerCategoryContent(getActivity());
            medicalInsuranceCompensateContent.initView(Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_COMPENSATE, medicalInsuranceCompensateMoney, medicalInsuranceCompensateHint);
            llContent.addView(medicalInsuranceCompensateContent.getView());

            String reductionAndExemptionHint = getResources().getString(R.string.sign_service_reduction_and_exemption_money);
            SignServiceIncomePerCategoryContent reductionAndExemptionContent = new SignServiceIncomePerCategoryContent(getActivity());
            reductionAndExemptionContent.initView(Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_REDUCTION_AND_EXEMPTION, reductionAndExemptionMoney, reductionAndExemptionHint);
            llContent.addView(reductionAndExemptionContent.getView());
        } else {
//            laySignServiceIncomeLoadFailure.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    downloadData();
//                }
//            });
        }
    }

    private void simulateData(){
        SignServiceIncomeResultBean simulatedBean = SimulateData.getSignServiceIncomeResultBean();
        showView(simulatedBean);
//        showView(null);
    }

//    @Override
//    public void onLoadDataSuccess(HttpResultBaseBean<SignServiceIncomeResultBean> bean) {
////        showView(bean.getData());
//        simulateData();
//    }
//
//    @Override
//    public void onLoadDataFailure(String message) {
////        Util.showToast(getActivity(), Constants.HINT_LOADING_DATA_FAILURE);
//        simulateData();
//    }
}
