package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.BudgetingBean;
import com.jqsoft.fingertip_health.bean.CmsReimbursement;
import com.jqsoft.fingertip_health.bean.Presciblebean;
import com.jqsoft.fingertip_health.bean.SetteBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.FormalsettlementContractForFingertip;
import com.jqsoft.fingertip_health.di.module.FormalsettlementModuleForFingertip;
import com.jqsoft.fingertip_health.di.module.OutpatientModuleForFingertip;
import com.jqsoft.fingertip_health.di.presenter.FormalSettlementFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 门诊结算
 * Created by Administrator on 2018-09-11.
 */

public class SettleAccountsActivity extends AbstractActivity implements FormalsettlementContractForFingertip.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.tv_person_type)
    TextView tvPersonType;
    @BindView(R.id.tv_card1)
    TextView tvCard1;
    @BindView(R.id.tv_card2)
    TextView tvCard2;
    @BindView(R.id.tv_card3)
    TextView tvCard3;
    @BindView(R.id.tv_person_address)
    TextView tvPersonAddress;
    @BindView(R.id.tv_compensate_fee)
    TextView tvCompensateFee;

    @BindView(R.id.tv_insurance_inner_fee)
    TextView tvInsuranceInnerFee;
    @BindView(R.id.tv_whole_pay)
    TextView tvWholePay;
    @BindView(R.id.tv_write_off_ratio)
    TextView tvWriteOffRatio;

    @BindView(R.id.tv_compensate_time)
    TextView tvCompensateTime;
    @BindView(R.id.tv_attend_institute)
    TextView tvAttendInstitute;

    @BindView(R.id.tv_total_fee)
    TextView tvTotalFee;
    @BindView(R.id.tv_medical_fee)
    TextView tvMedicalFee;
    @BindView(R.id.tv_family_account_remainder_fee)
    TextView tvFamilyAccountRemainderFee;
    @BindView(R.id.tv_family_account_pay)
    TextView tvFamilyAccountPay;
    @BindView(R.id.tv_individual_pay)
    TextView tvIndividualPay;
    @BindView(R.id.tv_medical_close_fee)
    TextView tvMedicalCloseFee;

    @BindView(R.id.tv_this_time_should_pay)
    TextView tvThisTimeShouldPay;
    @BindView(R.id.et_this_time_pay)
    EditText etThisTimePay;
    @BindView(R.id.tv_this_time_pay_back)
    TextView tvThisTimePayBack;

    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;
    @BindView(R.id.ll_submit)
    LinearLayout llSubmit;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private String resultString;
    private CmsReimbursement cmsReimbursement;
    private OutpatientBeanForFingertip outpatientBeanForFingertip;
    @Inject
    FormalSettlementFingertip mPresenter;
    private String feelTotalSS, personName, cardNo;
    private String kaiyaoID, visitNumber, compType;
    private List<String> prescriptionId = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settle_accounts_layout;
    }

    @Override
    protected void initData() {

        initWidgetData();
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, "");
        resultString = getIntent().getStringExtra("resultString");
        kaiyaoID = getIntent().getStringExtra("prescriptionId");
        visitNumber = getIntent().getStringExtra("visitNumber");
        compType = getIntent().getStringExtra("compType");
        personName = getIntent().getStringExtra("psername");
        cardNo = getIntent().getStringExtra("cardNo");
        prescriptionId.add(kaiyaoID);
        tvPersonName.setText(personName);
        outpatientBeanForFingertip = (OutpatientBeanForFingertip) getIntent().getSerializableExtra("outpatientBeanForFingertip");
        BudgetingBean result = JSON.parseObject(resultString, BudgetingBean.class);
        feelTotalSS = result.getFeePay();
        tvThisTimeShouldPay.setText(feelTotalSS);
        etThisTimePay.setText(feelTotalSS);
        tvSubmit.setText("结算" + feelTotalSS + "元");
        tvCompensateTime.setText(Util.getCurrentYearMonthDayString());
        tvAttendInstitute.setText(IdentityManager.getOrgNameForFingertip(this));
        tvPersonType.setText("自费");
        tvCard1.setText(cardNo);
        try {
            tvPersonAddress.setText(outpatientBeanForFingertip.getAreaName());
            tvPersonType.setText("新农合" + "(" + outpatientBeanForFingertip.getMemberPro() + ")");
            tvWriteOffRatio.setText(result.getPercent() + "%");
            tvTotalFee.setText(result.getFeeTotal());
            tvCompensateFee.setText(" 累计补偿:" + outpatientBeanForFingertip.getOutpCompensateCost() + "元");
            cmsReimbursement = result.getCmsReimbursement();
            tvInsuranceInnerFee.setText(cmsReimbursement.getEnableMoney() + "");
            tvWholePay.setText(cmsReimbursement.getGeneralMoney() + "");
            tvIndividualPay.setText(cmsReimbursement.getPersonalPayMoney() + "");
            tvMedicalCloseFee.setText(cmsReimbursement.getBottomMoney() + "");
            tvFamilyAccountPay.setText(cmsReimbursement.getGeneralMoney() + "");
            tvMedicalFee.setText(cmsReimbursement.getEnableMoney() + "");
            tvMedicalCloseFee.setText(cmsReimbursement.getFamilyAccountMoney() + "");
            tvFamilyAccountRemainderFee.setText(cmsReimbursement.getCmsMoney() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Util.setViewListener(llCancel, new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });

        Util.setViewListener(llSubmit, new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = ParametersFactory.SettleForFingertip(getApplicationContext(), feelTotalSS, prescriptionId, visitNumber, compType);
                mPresenter.FormalSettlement(map);
            }
        });
        etThisTimePay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String price = s.toString();
                if (price.length() > 0) {
                    try {
                        double a = Double.parseDouble(price);
                        double b = Double.parseDouble(feelTotalSS);
                        tvThisTimePayBack.setText((a - b) + "");
                        tvSubmit.setText("结算" + (a - b) + "元");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void initWidgetData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addFormal(new FormalsettlementModuleForFingertip(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFormalsettlementSccusse(HttpResultBaseBeanForFingertip<String> bean) {
        String res = bean.getResult();
        if (res != null) {
            SetteBean result = JSON.parseObject(res, SetteBean.class);
            String a = result.getInvoiceNumber();
            Util.showToast(getApplication(), "结算成功" + a);
            RxBus.getDefault().post(Constants.EVENT_TYPE_BUGLY_UPGRADE_CODE_CLEAR_DATA, Constants.EVENT_TYPE_BUGLY_UPGRADE_SUCCESS);
            SharedPreferencesUtil.setString(this,"visitNumber","");
            finish();
        }

    }

    @Override
    public void onFormalsettlementFailure(String message) {

    }
}
