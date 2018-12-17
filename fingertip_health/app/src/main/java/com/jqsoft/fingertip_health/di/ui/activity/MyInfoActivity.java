package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.di.contract.MyInfoContract;
import com.jqsoft.fingertip_health.di.module.MyInfoModule;
import com.jqsoft.fingertip_health.di.presenter.MyInfoPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.AppUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017-06-23.
 */
//我的信息
public class MyInfoActivity extends AbstractActivity implements MyInfoContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_login_account)
    TextView tvLoginAccount;
    @BindView(R.id.tv_login_alias)
    TextView tvLoginAlias;
    @BindView(R.id.tv_id_card_number)
    TextView tvIdCardNumber;
    @BindView(R.id.tv_institution_name)
    TextView tvInstitutionName;
    @BindView(R.id.tv_contact_number)
    TextView tvContactNumber;
    @BindView(R.id.btn_update_phone_number)
    ImageView btnUpdatePhoneNumber;

    String tempPhoneNumber;
    String phoneNumber;

    @Inject
    MyInfoPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
    }

    private String getUsername(){
        String result = IdentityManager.getLoginSuccessUsername(this);
        return result;
    }

    private String getPassword(){
        String result = IdentityManager.getLoginSuccessPassword(this);
        return result;
    }

    @Override
    protected void loadData() {
        Map<String, String> map = ParametersFactory.getLoginMapFromUsernameAndPassword(this, getUsername(), getPassword());
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.getUserInfo(body);

//        mPresenter.getMyInfo(null);
    }

    private void updatePhoneNumber(String newNumber){
        tempPhoneNumber=newNumber;
        Map<String, String> map = ParametersFactory.getUpdatePhoneNumberFromUsernameAndPassword(this, Identity.getUserId(), newNumber);
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.updatePhoneNumber(body);

    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addMyInfoActivity(new MyInfoModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_my_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.action_modify_password == id){
            Util.showToast(this, "modify password button clicked");
        } else if (R.id.action_modify_profile == id){
            Util.showToast(this, "modify profile button clicked");
        }
        return super.onOptionsItemSelected(item);
    }

//    public void showMyInfo(MyInfoBean bean){
//        if (bean!=null){
//            String username = getResources().getString(R.string.my_info_username)+Constants.COLON_STRING+bean.getUsername();
//            tvUsername.setText(Util.trimString(username));
//            String account = getResources().getString(R.string.my_info_account)+Constants.COLON_STRING+bean.getLoginAccount();
//            tvLoginAccount.setText(Util.trimString(account));
//            tvLoginAlias.setText(Util.trimString(bean.getLoginAlias()));
//            tvIdCardNumber.setText(Util.trimString(bean.getIdCardNumber()));
//            tvInstitutionName.setText(Util.trimString(bean.getInstitutionName()));
//            phoneNumber = Util.trimString(bean.getContactNumber());
//            Spanned html = Html.fromHtml("<u>"+phoneNumber+"</u>");
//            tvContactNumber.setText(html);
//            tvContactNumber.setTextColor(getResources().getColor(R.color.blue));
//            tvContactNumber.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    if (!StringUtils.isBlank(phoneNumber)) {
//                        AppUtils.actionDial(MyInfoActivity.this, phoneNumber);
//                    } else {
//                        Util.showToast(MyInfoActivity.this, Constants.HINT_PHONE_NUMBER_EMPTY);
//                    }
//                }
//            });
//            btnUpdatePhoneNumber.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
////                    Util.showToast(MyInfoActivity.this, "更新电话号码按钮被点击");
//                    Util.showEditTextMaterialDialog(MyInfoActivity.this, "提示", "更新电话号码", "请输入更新后的电话号码", phoneNumber,
//                            false, 1, 20, Constants.EDIT_INPUT_TYPE_PHONE, new MaterialDialog.InputCallback() {
//                                @Override
//                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
//                                }
//                            });
//                }
//            });
//        } else {
//            Util.showToast(this, Constants.HINT_GET_MY_INFO_EMPTY);
//        }
//    }

    public void showFetchedInfo(LoginResultBean2 bean){
        if (bean!=null){
            String username = getResources().getString(R.string.my_info_username)+Constants.COLON_STRING+bean.getSusername();
            tvUsername.setText(Util.trimString(username));
            String account = getResources().getString(R.string.my_info_account)+Constants.COLON_STRING+bean.getSloginname();
            tvLoginAccount.setText(Util.trimString(account));
            String alias = Util.trimString(bean.getAlias());
            tvLoginAlias.setText(alias);
            tvIdCardNumber.setText(Util.trimString(bean.getCardNo()));
            tvInstitutionName.setText(Util.trimString(bean.getSorganizationname()));
            phoneNumber = Util.trimString(bean.getSphone());
            Spanned html = Html.fromHtml("<u>"+phoneNumber+"</u>");
            tvContactNumber.setText(html);
            tvContactNumber.setTextColor(getResources().getColor(R.color.blue));
            tvContactNumber.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    super.onNoDoubleClick(v);

                    if (!StringUtils.isBlank(phoneNumber)) {
                        AppUtils.actionDial(MyInfoActivity.this, phoneNumber);
                    } else {
                        Util.showToast(MyInfoActivity.this, Constants.HINT_PHONE_NUMBER_EMPTY);
                    }
                }
            });
            btnUpdatePhoneNumber.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    super.onNoDoubleClick(v);
//                    RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_TRANSACT);

//                    Util.showToast(MyInfoActivity.this, "更新电话号码按钮被点击");
                    Util.showEditTextMaterialDialog(MyInfoActivity.this, Constants.HINT, Constants.HINT_UPDATE_PHONE, Constants.HINT_PLEASE_INPUT_PHONE_NUMBER, phoneNumber,
                            false, 1, 20, Constants.EDIT_INPUT_TYPE_PHONE, new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    updatePhoneNumber(input.toString());

                                }
                            });
                }
            });
        } else {
            Util.showToast(this, Constants.HINT_GET_MY_INFO_EMPTY);
        }
    }


//    @Override
//    public void onLoadMyInfoSuccess(HttpResultBaseBean<MyInfoBean> bean) {
//        if (bean!=null) {
//            showMyInfo(bean.getData());
//        } else {
//            Util.showToast(this, Constants.HINT_GET_MY_INFO_EMPTY);
//        }
//    }
//
//    @Override
//    public void onLoadMyInfoFailure(String message) {
//        MyInfoBean myInfoBean = SimulateData.getMyInfoBean();
//        showMyInfo(myInfoBean);
////        Util.showToast(this, Constants.HINT_GET_MY_INFO_FAILURE);
//    }

    @Override
    public void onLoginSuccess(HttpResultBaseBean<LoginResultBean2> bean) {
        if (bean!=null){
            LoginResultBean2 item = bean.getData();
            showFetchedInfo(item);
        }
    }

    @Override
    public void onLoginFailure(String message) {
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);

    }

    @Override
    public void onUpdatePhoneSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        phoneNumber=tempPhoneNumber;
        Spanned html = Html.fromHtml("<u>"+phoneNumber+"</u>");
        tvContactNumber.setText(html);
        Util.showToast(this, Constants.HINT_UPDATE_PHONE_NUMBER_SUCCESS);
    }

    @Override
    public void onUpdatePhoneFailure(String message) {
        Util.showToast(this, Constants.HINT_UPDATE_PHONE_NUMBER_FAILURE);
    }
}
