package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.RetrievePasswordContract;
import com.jqsoft.fingertip_health.di.module.RetrievePasswordModule;
import com.jqsoft.fingertip_health.di.presenter.RetrievePasswordPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.RegexUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017-08-16.
 * 找回密码界面
 */

public class RetrievePasswordActivity extends AbstractActivity implements RetrievePasswordContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.acet_id_card)
    AppCompatEditText acetIdCard;
    @BindView(R.id.bt_id_card_clear)
    Button btIdCardClear;
    @BindView(R.id.acet_phone)
    AppCompatEditText acetPhone;
    @BindView(R.id.bt_phone_clear)
    Button btPhoneClear;
    @BindView(R.id.acet_new_password)
    AppCompatEditText acetNewPassword;
    @BindView(R.id.bt_new_password_clear)
    Button btNewPasswordClear;
    @BindView(R.id.bt_new_password_eye)
    Button btNewPasswordEye;
    @BindView(R.id.acet_confirm_password)
    AppCompatEditText acetConfirmPassword;
    @BindView(R.id.bt_confirm_password_clear)
    Button btConfirmPasswordClear;
    @BindView(R.id.bt_confirm_password_eye)
    Button btConfirmPasswordEye;
    @BindView(R.id.bt_confirm)
    AppCompatButton btConfirm;


    @Inject
    RetrievePasswordPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        String initialIdCardNumber = getDeliveredStringByKey(Constants.CARD_NO_KEY);
        acetIdCard.setText(initialIdCardNumber);

        RxTextView.textChanges(acetIdCard)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setIdCardClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btIdCardClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetIdCard.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetPhone)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setPhoneClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btPhoneClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetPhone.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetNewPassword)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setNewPasswordClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btNewPasswordClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetNewPassword.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btNewPasswordEye)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onChangeNewPasswordVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        RxTextView.textChanges(acetConfirmPassword)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setConfirmPasswordClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btConfirmPasswordClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetConfirmPassword.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btConfirmPasswordEye)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onChangeConfirmPasswordVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        acetConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    onConfirm();
                }
                return false;
            }
        });

        RxView.clicks(btConfirm)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onConfirm();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        setClearButtonVisibility();
    }

    private void onConfirm() {
        if (!isInputValid()){
            return;
        }
        Map<String, String> map = ParametersFactory.getCheckUserExistMapFromParameters(RetrievePasswordActivity.this, getIdCard(), getPhone());
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.main(body);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addRetrievePassword(new RetrievePasswordModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onChangeNewPasswordVisibility() {
        int inputType = acetNewPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            btNewPasswordEye.setBackgroundResource(R.mipmap.eye_open_blue);
            acetNewPassword.setInputType((InputType.TYPE_CLASS_TEXT));
        } else {
//            btNewPasswordEye.setBackgroundResource(R.mipmap.eye_close_purple);
            btNewPasswordEye.setBackgroundResource(R.mipmap.eye_open_gray);
            acetNewPassword.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }
        acetNewPassword.setSelection(acetNewPassword.getText().toString().length());
    }

    public void onChangeConfirmPasswordVisibility() {
        int inputType = acetConfirmPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            btConfirmPasswordEye.setBackgroundResource(R.mipmap.eye_open_blue);
            acetConfirmPassword.setInputType((InputType.TYPE_CLASS_TEXT));
        } else {
//            btNewPasswordEye.setBackgroundResource(R.mipmap.eye_close_purple);
            btConfirmPasswordEye.setBackgroundResource(R.mipmap.eye_open_gray);
            acetConfirmPassword.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }
        acetConfirmPassword.setSelection(acetConfirmPassword.getText().toString().length());
    }

    public void setClearButtonVisibility() {
        setIdCardClearButtonVisibility();
        setPhoneClearButtonVisibility();
        setNewPasswordClearButtonVisibility();
        setConfirmPasswordClearButtonVisibility();
    }


    private void setIdCardClearButtonVisibility(){
        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)){
            btIdCardClear.setVisibility(View.INVISIBLE);
        } else {
            btIdCardClear.setVisibility(View.VISIBLE);
        }
    }

    private void setPhoneClearButtonVisibility(){
        String phone = getPhone();
        if (StringUtils.isBlank(phone)){
            btPhoneClear.setVisibility(View.INVISIBLE);
        } else {
            btPhoneClear.setVisibility(View.VISIBLE);
        }
    }

    private void setNewPasswordClearButtonVisibility(){
        String password = getNewPassword();
        if (StringUtils.isBlank(password)){
            btNewPasswordClear.setVisibility(View.INVISIBLE);
        } else {
            btNewPasswordClear.setVisibility(View.VISIBLE);
        }
    }

    private void setConfirmPasswordClearButtonVisibility(){
        String password = getNewPassword();
        if (StringUtils.isBlank(password)){
            btNewPasswordClear.setVisibility(View.INVISIBLE);
        } else {
            btNewPasswordClear.setVisibility(View.VISIBLE);
        }
    }


    private boolean isInputValid(){

        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)){
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_empty));
            return false;
        } else if (!RegexUtil.checkIdCard(idCard)) {
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_invalid));
            return false;
        }

        String phone = getPhone();
        if (StringUtils.isBlank(phone)){
            Util.showToast(this, getResources().getString(R.string.register_hint_phone_empty));
            return false;
        } else if (!RegexUtils.isMobileSimple(phone)){
            Util.showToast(this, getResources().getString(R.string.register_hint_phone_invalid));
            return false;
        }

        String password = getNewPassword();
        if (StringUtils.isBlank(password)){
            Util.showToast(this, getResources().getString(R.string.retrieve_password_hint_new_password_empty));
            return false;
        } else if (StringUtils.length(password)<=0){
            Util.showToast(this, getResources().getString(R.string.retrieve_password_hint_new_password_invalid));
            return false;
        }

        String confirmPassword = getConfirmPassword();
        if (StringUtils.isBlank(confirmPassword)){
            Util.showToast(this, getResources().getString(R.string.retrieve_password_hint_confirm_password_empty));
            return false;
        } else if (StringUtils.length(confirmPassword)<=0){
            Util.showToast(this, getResources().getString(R.string.retrieve_password_hint_confirm_password_invalid));
            return false;
        }

        if (!password.equals(confirmPassword)){
            Util.showToast(this, getResources().getString(R.string.retrieve_password_hint_two_password_not_equal));
            return false;
        }

        return true;
    }



    private String getIdCard(){
        return Util.trimString(acetIdCard.getText().toString());
    }

    private String getPhone(){
        return Util.trimString(acetPhone.getText().toString());
    }

    private String getNewPassword(){
        return Util.trimString(acetNewPassword.getText().toString());
    }

    private String getConfirmPassword(){
        return Util.trimString(acetConfirmPassword.getText().toString());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.clear();
//        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_take_photo:
//                Util.showToast(this, "拍照按钮被点击");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doRetrievePassword(){
        Map<String, String> map = ParametersFactory.getRetrievePasswordMapFromParameters(this, getIdCard(), getPhone(),
                getNewPassword());
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.retrievePassword(body);

    }


    @Override
    public void onCardNumberExistSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        doRetrievePassword();
    }

    @Override
    public void onCardNumberExistFailure(String message) {
        Util.showToast(this, message);
//        Util.showToast(this, Constants.HINT_ID_CARD_NUMBER_AND_PHONE_NUMBER_NOT_MATCH);
    }

    @Override
    public void onRetrievePasswordSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.showToast(this, Constants.HINT_RETRIEVE_PASSWORD_SUCCESS);
        finish();

    }

    @Override
    public void onRetrievePasswordFailure(String msg) {
        Util.showToast(this, msg);
    }
}
