package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.AboutInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ChangePasswordContract;
import com.jqsoft.fingertip_health.di.module.ChangePasswordActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ChangePasswordPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


//更改密码
public class ChangePasswordActivity extends AbstractActivity implements ChangePasswordContract.View ,View.OnClickListener{
    @BindView(R.id.treatment_title)
    TextView treatment_title;

    @BindView(R.id.old_password)
    EditText oldPasswordEdit;
    @BindView(R.id.new_password)
    EditText newPasswordEdit;
    @BindView(R.id.new_password2)
    EditText newPassword2Edit;
    @BindView(R.id.update_pswd_confirm)
    Button mConfirm;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private String mOldPassword, mNewPassword;




    @Inject
    ChangePasswordPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pswd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        treatment_title.setText("修改密码");

        mConfirm.setOnClickListener(this);
        mConfirm.setEnabled(false);
        oldPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setConformButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setConformButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newPassword2Edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setConformButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setConformButtonState() {
        if (TextUtils.isEmpty(oldPasswordEdit.getText().toString().trim())
                && TextUtils.isEmpty(newPasswordEdit.getText().toString().trim())
                && TextUtils.isEmpty(oldPasswordEdit.getText().toString().trim())) {
            mConfirm.setEnabled(false);
        } else {
            mConfirm.setEnabled(true);
        }
    }
    @Override
    protected void loadData() {




    }





    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addChangePasswordActivity(new ChangePasswordActivityModule(this))
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


    public void  ChangePassword(String oldpassword,String newpassword){
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getGCAChangePasswordMap(this, name,oldpassword,newpassword,"appMineUpdate.updatePasswordSri");
        mPresenter.main(map);
    }


    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<AboutInfoBean> bean) {
        if (bean.getResult().equals("500")){
        Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();
            doExit();
            finish();
        }else {
            Toast.makeText(this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.update_pswd_confirm:
                Util.showMaterialDialog(this, Constants.HINT, "修改密码成功后会退出并跳转致登录界面，确定修改吗？", Constants.CANCEL,
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                CheckChangePassword();
                                dialog.dismiss();

                            }
                        }, true);

                break;
        }


    }




    private void doExit() {
        //   Util.logoutJMessageAndExitApplication();

        PreferencesUtils.putBoolean(this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, false);
        PreferencesUtils.putString(this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);

//        Util.exitApplication();
        exitApp();

    }

    private void exitApp(){
        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);

        Intent intent = new Intent();
        intent.setClass(this,LoginActivityNew.class);
        this.startActivity(intent);

//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);
       /* gotoActivity(getActivity(), LoginActivityNew.class);*/

//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);

    }
    private  void CheckChangePassword(){

        String old = oldPasswordEdit.getText().toString().trim();
        String new1 = newPasswordEdit.getText().toString().trim();
        String new2 = newPassword2Edit.getText().toString().trim();
        String cachePassword =  IdentityManager.getLoginSuccessPassword(this);

        IdentityManager.getLoginSuccessPassword(this);
        IdentityManager.getLoginSuccessUsername(this);

        if (TextUtils.isEmpty(old)) {
            Toast.makeText(this, R.string.original_password,Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(new1)) {
            Toast.makeText(this, R.string.new_password_not_null,Toast.LENGTH_SHORT).show();


            return;
        }

//        if (new1.length() < 6 || new1.length() > 16) {
//            Toast.makeText(this, R.string.passwords_invalid,Toast.LENGTH_SHORT).show();
//
//            return;
//        }

        if (TextUtils.isEmpty(new2)) {
            Toast.makeText(this, R.string.confirm_password_not_null,Toast.LENGTH_SHORT).show();

            return;
        }
        if (!cachePassword.equals(old)) {
            Toast.makeText(this, R.string.original_password_mistake,Toast.LENGTH_SHORT).show();

            return;
        }
        if (!new1.equals(new2)) {
            Toast.makeText(this, R.string.passwords_do_not_match,Toast.LENGTH_SHORT).show();

            return;
        }

        if (new1.equals(old)) {
            Toast.makeText(this, R.string.new_and_old_password,Toast.LENGTH_SHORT).show();

            return;
        }

        mOldPassword = old;
        mNewPassword = new1;
        ChangePassword(mOldPassword,mNewPassword);
    }

}
