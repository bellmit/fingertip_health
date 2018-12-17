package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.EvaluationInfos;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.bean.response_new.SignClientServiceAssessResultBean;
import com.jqsoft.fingertip_health.di.contract.SignServiceEvaluteContract;
import com.jqsoft.fingertip_health.di.module.SignServiceEvaluteActivityModule;
import com.jqsoft.fingertip_health.di.presenter.SignServiceEvalutionPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

/**
 * Created by Jerry on 2017/8/21.
 */

public class SignServiceEvalution extends AbstractActivity implements SignServiceEvaluteContract.View {
    @BindView(R.id.online_consultation_title)
    TextView activity_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.servername)
    TextView serviceName;
    @BindView(R.id.serverxm)
    TextView serviceXM;
    @BindView(R.id.servicetime)
    TextView serviceTime;
    @BindView(R.id.servicedoctorname)
    TextView serviceDoctorName;
    @BindView(R.id.evalutioncontent)
    EditText evalutioncontent;
    @BindView(R.id.submit_evlute)
    AppCompatButton submitBtn;
    @BindView(R.id.ratingbar)
    RatingBar ratingBar;
    private String signDetailID, serviceContentItemsID, serviceContentID, userName, doctorCode, orgID, docUserID, evaluation = "0", evaluationNote;
    private String loginName, servicePlanID, year;


    @Inject
    public SignServiceEvalutionPresenter signServiceEvalutionPresenter;

    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addSignServiceEvalutionActivity(new SignServiceEvaluteActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signserviceevalution;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        activity_title.setText("签约服务评价");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                int count = (int) RatingCount;
                evaluation = count + "";
            }
        });
    }

    @Override
    protected void loadData() {
        final SignClientServiceAssessResultBean data = (SignClientServiceAssessResultBean) getIntent().getSerializableExtra("SignClientServiceAssessResultBean");

        serviceName.setText(data.getFwmc());
        serviceXM.setText(data.getServiceItemsName());
        Map<String, String> map = ParametersFactory.getEvaluationInfo(this, data.getServicePlanID(), data.getSignPageyear());
        RequestBody body = Util.getBodyFromMap(map);
        signServiceEvalutionPresenter.getEvaluationInfo(body);
        if (data.getEvaluationState().equals("2")) {
            submitBtn.setVisibility(View.VISIBLE);
            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    evaluationNote = evalutioncontent.getText().toString();
                    if (!TextUtils.isEmpty(evaluationNote)) {
                        String userName = IdentityManager.getPersonName(SignServiceEvalution.this);
                        String docUserID = Util.trimString(data.getDocUserID());
                        Map<String, String> map = ParametersFactory.savePersonEvaluationServer(SignServiceEvalution.this, data.getServicePlanID(), data.getSignPageyear(), evaluation, evaluationNote, userName, docUserID);
                        RequestBody body = Util.getBodyFromMap(map);
                        signServiceEvalutionPresenter.savePersonEvaluation(body);
                    } else {
                        Util.showToast(getApplicationContext(), "请填写您的评价信息后提交");
                    }

                }
            });
        } else {
            submitBtn.setVisibility(View.GONE);
            ratingBar.setClickable(false);
        }


    }

    @Override
    public void onLoginSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
        Util.hideGifProgressDialog(this);
        RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_CLIENTSIGN_ASSESS, "okone");
        Util.showToast(getApplicationContext(), bean.getMessage());
        finish();
    }

    @Override
    public void onLoginFailure(String message) {
        Util.hideGifProgressDialog(this);
        Util.showToast(this, message);
    }

    @Override
    public void onGetEvaluationInfos(HttpResultBaseBean<EvaluationInfos> bean) {
        Util.hideGifProgressDialog(this);
        String evaluationNote = bean.getData().getEvaluationNote();
        String counts = bean.getData().getEvaluation();
        serviceTime.setText(bean.getData().getServerDT().substring(0, 10));
        serviceDoctorName.setText(bean.getData().getDocUserName());
        evalutioncontent.setText(bean.getData().getEvaluationNote());
        float cc = Float.parseFloat(counts);
        ratingBar.setStar(cc);
    }
}
