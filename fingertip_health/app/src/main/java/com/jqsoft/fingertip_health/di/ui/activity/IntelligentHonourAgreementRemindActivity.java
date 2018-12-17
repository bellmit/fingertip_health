//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.ScrollView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.IdentityManager;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.response_new.IntelligentHonourAgreementOverviewResultBean;
//import com.jqsoft.fingertip_health.configuration.ExecutionProjectsType;
//import com.jqsoft.fingertip_health.di.contract.IntelligentHonourAgreementRemindActivityContract;
//import com.jqsoft.fingertip_health.di.module.IntelligentHonourAgreementRemindActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.IntelligentHonourAgreementRemindActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.util.Util;
//
//import java.util.Locale;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import okhttp3.RequestBody;
//
///**
// * Created by Administrator on 2017-07-07.
// */
////智能履约提醒
//public class IntelligentHonourAgreementRemindActivity extends AbstractActivity implements IntelligentHonourAgreementRemindActivityContract.View {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.sv_content)
//    ScrollView svContent;
//    @BindView(R.id.lay_latest_7_days_need_execute_projects_number)
//    View layLatest7DaysNeedExecuteProjectsNumber;
//    @BindView(R.id.lay_timeout_not_execute_projects_number)
//    View layTimeoutNotExecuteProjectsNumber;
//    @BindView(R.id.lay_new_service_assess_info_number)
//    View layNewServiceAssessInfoNumber;
//    @BindView(R.id.lay_new_sign_application_info_number)
//    View layNewSignApplicationInfoNumber;
//
//    @BindView(R.id.lay_get_remind_number_load_failure)
//    View layGetRemindNumberLoadFailureView;
//
//    @Inject
//    IntelligentHonourAgreementRemindActivityPresenter mPresenter;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_intelligent_honour_agreement_remind;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        setToolBar(toolbar, Constants.EMPTY_STRING);
//
//        layLatest7DaysNeedExecuteProjectsNumber.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                Util.gotoExecutionProjectsActivity(IntelligentHonourAgreementRemindActivity.this, ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days, IdentityManager.getCardNo(IntelligentHonourAgreementRemindActivity.this));
//            }
//        });
//
//        layTimeoutNotExecuteProjectsNumber.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                Util.gotoExecutionProjectsActivity(IntelligentHonourAgreementRemindActivity.this, ExecutionProjectsType.ExecutionProjectsTypeEnum.Timeout, IdentityManager.getCardNo(IntelligentHonourAgreementRemindActivity.this));
//            }
//        });
//
//        layNewServiceAssessInfoNumber.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.SIGN_SERVICE_ASSESS_ACTIVITY_NAME);
//                Util.gotoActivity(IntelligentHonourAgreementRemindActivity.this, SignServiceAssessActivity.class);
//            }
//        });
//
//        layNewSignApplicationInfoNumber.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.SIGN_APPLICATION_ACTIVITY_NAME);
//                Util.gotoActivity(IntelligentHonourAgreementRemindActivity.this, SignApplicationActivity.class);
//            }
//        });
//
//        layGetRemindNumberLoadFailureView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                loadIntelligentHonourAgreementOverviewData();
//            }
//        });
//    }
//
//    @Override
//    protected void loadData() {
//        loadIntelligentHonourAgreementOverviewData();
//
//    }
//
//    @Override
//    protected void initInject() {
//        super.initInject();
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addIntelligentHonourAgreementRemindActivity(new IntelligentHonourAgreementRemindActivityModule(this))
//                .inject(this);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    public void loadIntelligentHonourAgreementOverviewData() {
//        String organizationKey = getOrganizationKey();
//        String userId = getUserId();
//        String category = getCategory();
//        Map<String, String> map = ParametersFactory.getIntelligentHonourAgreementOverviewMap(this, organizationKey, userId, category);
//        RequestBody body = Util.getBodyFromMap(map);
//        mPresenter.getIntelligentHonourAgreementOverview(body);
//
//    }
//
//    public void showIntelligentHonourAgreementOverviewOrFailureView(boolean success) {
//        if (success) {
//            svContent.setVisibility(View.VISIBLE);
//            layGetRemindNumberLoadFailureView.setVisibility(View.GONE);
//        } else {
//            Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
//            svContent.setVisibility(View.GONE);
//            layGetRemindNumberLoadFailureView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void showIntelligentHonourAgreementOverview(IntelligentHonourAgreementOverviewResultBean bean) {
//        if (bean != null) {
////            int imageId = R.mipmap.take_photo_blue;
//
//
//            String latest7DaysNeedExecuteProjectsNumber = getResources().getString(R.string.latest_7_days_need_execute_projects_number);
//            String execuNumber = String.valueOf(Util.getIntFromString(bean.getExecitem()));
//            String latest7DaysPerPersonTimes = String.format(Locale.CHINA, getResources().getString(R.string.per_person_and_times),
//                    execuNumber);
//            Util.setImageTextNumberForViewHorizontal(layLatest7DaysNeedExecuteProjectsNumber, R.mipmap.i_index_latest_7_days, latest7DaysNeedExecuteProjectsNumber, latest7DaysPerPersonTimes);
//
//            String timeoutNotExecuteProjectsNumber = getResources().getString(R.string.timeout_not_execute_projects_number);
//            String timeoutNumber = String.valueOf(Util.getIntFromString(bean.getTimeout()));
//            String timeoutNotExecutePerPersonTimes = String.format(Locale.CHINA, getResources().getString(R.string.per_person_and_times), timeoutNumber);
//            Util.setImageTextNumberForViewHorizontal(layTimeoutNotExecuteProjectsNumber, R.mipmap.i_index_timeout, timeoutNotExecuteProjectsNumber, timeoutNotExecutePerPersonTimes);
//
//            String serviceAssessNumber = String.valueOf(Util.getIntFromString(bean.getServiceEvaluation()));
//            String assess = String.format(Locale.CHINA, getResources().getString(R.string.new_service_assess_info_number),
//                    serviceAssessNumber);
//            Util.setImageTextNumberForViewVertical(layNewServiceAssessInfoNumber, R.mipmap.i_index_service_assess, assess, serviceAssessNumber);
//
//            String signApplicationNumber = String.valueOf(Util.getIntFromString(bean.getApplySignDoctor()));
//            String sign = String.format(Locale.CHINA, getResources().getString(R.string.new_sign_application_info_number), signApplicationNumber);
//            Util.setImageTextNumberForViewVertical(layNewSignApplicationInfoNumber, R.mipmap.i_index_sign_application, sign, signApplicationNumber);
//
////            String appointmentSign = getResources().getString(R.string.appointment_execution);
////            String appointmentSignNumber = bean.getAppointmentService();
////            Util.setImageTextNumberForViewVertical(litnvAppointmentSign, R.mipmap.i_index_appointment_sign, appointmentSign, appointmentSignNumber);
//        }
//    }
//
//    @Override
//    public void onLoadIntelligentHonourAgreementOverviewDataSuccess(HttpResultBaseBean<IntelligentHonourAgreementOverviewResultBean> bean) {
//        if (bean!=null){
//            IntelligentHonourAgreementOverviewResultBean b = bean.getData();
//            if (b!=null){
//                showIntelligentHonourAgreementOverview(b);
//                showIntelligentHonourAgreementOverviewOrFailureView(true);
//            } else {
//                showIntelligentHonourAgreementOverviewOrFailureView(false);
//            }
//        } else {
//            showIntelligentHonourAgreementOverviewOrFailureView(false);
//        }
//
////        IntelligentHonourAgreementOverviewResultBean simulatedBean = SimulateData.getIntelligentHonourAgreementOverviewResultBean();
////        showIntelligentHonourAgreementOverview(simulatedBean);
//
//    }
//
//    @Override
//    public void onLoadIntelligentHonourAgreementOverviewDataFailure(String message) {
//        showIntelligentHonourAgreementOverviewOrFailureView(false);
//
////        IntelligentHonourAgreementOverviewResultBean simulatedBean = SimulateData.getIntelligentHonourAgreementOverviewResultBean();
////        showIntelligentHonourAgreementOverview(simulatedBean);
//    }
//
//
//    private String getOrganizationKey() {
//        return Identity.getOrganizationKey();
//    }
//
//    private String getUserId() {
//        return Identity.getUserId();
//    }
//
//    private String getCategory() {
//        return Constants.INTELLIGENT_HONOUR_AGREEMENT_CATEGORY_OVERVIEW;
//    }
//
//}
