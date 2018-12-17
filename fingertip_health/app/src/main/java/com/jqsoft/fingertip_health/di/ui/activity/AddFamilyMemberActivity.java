package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.AddFamilyMemberActivityContract;
import com.jqsoft.fingertip_health.di.module.AddFamilyMemberActivityModule;
import com.jqsoft.fingertip_health.di.presenter.AddFamilyMemberActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.RegexUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;

import org.apmem.tools.layouts.FlowLayout;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017-08-16.
 * 添加家庭成员界面
 */

public class AddFamilyMemberActivity extends AbstractActivity implements AddFamilyMemberActivityContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fl_relationship)
    FlowLayout flRelationship;

    @BindView(R.id.rb_self)
    SmoothRadioButton rbSelf;
    @BindView(R.id.rb_spouse)
    SmoothRadioButton rbSpouse;
    @BindView(R.id.rb_son)
    SmoothRadioButton rbSon;
    @BindView(R.id.rb_daughter)
    SmoothRadioButton rbDaughter;
    @BindView(R.id.rb_grand_son)
    SmoothRadioButton rbGrandson;
    @BindView(R.id.rb_grand_daughter)
    SmoothRadioButton rbGranddaughter;
    @BindView(R.id.rb_parent)
    SmoothRadioButton rbParent;
    @BindView(R.id.rb_grandparent)
    SmoothRadioButton rbGrandparent;
    @BindView(R.id.rb_sibling)
    SmoothRadioButton rbSibling;
    @BindView(R.id.rb_other)
    SmoothRadioButton rbOther;
    @BindView(R.id.rb_daughter_in_law)
    SmoothRadioButton rbDaughterInLaw;

    @BindView(R.id.acet_name)
    AppCompatEditText acetName;
    @BindView(R.id.bt_name_clear)
    Button btNameClear;
    @BindView(R.id.acet_id_card)
    AppCompatEditText acetIdCard;
    @BindView(R.id.bt_id_card_clear)
    Button btIdCardClear;

    @BindView(R.id.bt_confirm)
    AppCompatButton btConfirm;

    SmoothRadioButton[] radioButtonArray;

    @Inject
    AddFamilyMemberActivityPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_family_member;
    }

    @Override
    protected void initData() {

    }

    private void clearRadioButtonSelection() {
        for (int j = 0; j < radioButtonArray.length; ++j) {
            SmoothRadioButton button = radioButtonArray[j];
            button.setChecked(false);
        }

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        radioButtonArray = new SmoothRadioButton[]{rbSelf, rbSpouse, rbSon, rbDaughter, rbGrandson, rbGranddaughter,
                rbParent, rbGrandparent, rbSibling, rbOther, rbDaughterInLaw};
        for (int i = 0; i < radioButtonArray.length; ++i) {
            final SmoothRadioButton radioButton = radioButtonArray[i];
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < radioButtonArray.length; ++j) {
                        SmoothRadioButton button = radioButtonArray[j];
                        if (button != radioButton) {
                            button.setChecked(false);
                }
                                           }
                    radioButton.setChecked(true);

                }
            });
//            radioButton.setOnCheckedChangeListener(new SmoothCompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
//                    for (int j = 0; j < radioButtonArray.length; ++j) {
//                        SmoothRadioButton button = radioButtonArray[j];
//                        if (button != radioButton) {
//                            button.setChecked(false);
//                        }
//                    }
//                    smoothCompoundButton.setChecked(true);
//                }
//            });
        }

//        String initialIdCardNumber = getDeliveredStringByKey(Constants.CARD_NO_KEY);
//        acetIdCard.setText(initialIdCardNumber);

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

        RxTextView.textChanges(acetName)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setNameClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btNameClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetName.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
                        if (!isInputValid()) {
                            return;
                        }
                        doAddFamilyMember();
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

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addAddingFamilyMemberActivity(new AddFamilyMemberActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String getSelectedRelationship() {
        SmoothRadioButton selectedRadioButton = null;
        for (int i = 0; i < radioButtonArray.length; ++i) {
            SmoothRadioButton button = radioButtonArray[i];
            if (button.isChecked()) {
                selectedRadioButton = button;
                break;
            }
        }
        String result = Constants.EMPTY_STRING;
        if (selectedRadioButton != null) {
            if (selectedRadioButton == rbSelf) {
                result = Constants.FAMILY_MEMBER_SELF_VALUE;
            } else if (selectedRadioButton == rbSpouse) {
                result = Constants.FAMILY_MEMBER_SPOUSE_VALUE;
            } else if (selectedRadioButton == rbSon) {
                result = Constants.FAMILY_MEMBER_SON_VALUE;
            } else if (selectedRadioButton == rbDaughter) {
                result = Constants.FAMILY_MEMBER_DAUGHTER_VALUE;
            } else if (selectedRadioButton == rbGrandson) {
                result = Constants.FAMILY_MEMBER_GRAND_SON_VALUE;
            } else if (selectedRadioButton == rbGranddaughter) {
                result = Constants.FAMILY_MEMBER_GRAND_DAUGHTER_VALUE;
            } else if (selectedRadioButton == rbParent) {
                result = Constants.FAMILY_MEMBER_PARENT_VALUE;
            } else if (selectedRadioButton == rbGrandparent) {
                result = Constants.FAMILY_MEMBER_GRAND_PARENT_VALUE;
            } else if (selectedRadioButton == rbSibling) {
                result = Constants.FAMILY_MEMBER_SIBLING_VALUE;
            } else if (selectedRadioButton == rbOther) {
                result = Constants.FAMILY_MEMBER_OTHER_VALUE;
            } else if (selectedRadioButton == rbDaughterInLaw) {
                result = Constants.FAMILY_MEMBER_DAUGHTER_IN_LAW_VALUE;
            }
        }
        return result;
    }


    public void setClearButtonVisibility() {
        setIdCardClearButtonVisibility();
        setNameClearButtonVisibility();
    }


    private void setIdCardClearButtonVisibility() {
        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)) {
            btIdCardClear.setVisibility(View.INVISIBLE);
        } else {
            btIdCardClear.setVisibility(View.VISIBLE);
        }
    }

    private void setNameClearButtonVisibility() {
        String phone = getName();
        if (StringUtils.isBlank(phone)) {
            btNameClear.setVisibility(View.INVISIBLE);
        } else {
            btNameClear.setVisibility(View.VISIBLE);
        }
    }


    private boolean isInputValid() {
        String selectedRelationship = getSelectedRelationship();
        if (StringUtils.isBlank(selectedRelationship)) {
            Util.showToast(this, getResources().getString(R.string.add_family_member_not_select_relationship));
            return false;
        }

        String name = getName();
        if (StringUtils.isBlank(name)) {
            Util.showToast(this, getResources().getString(R.string.add_family_member_hint_name_empty));
            return false;
        }

        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)) {
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_empty));
            return false;
        } else if (!RegexUtil.checkIdCard(idCard)) {
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_invalid));
            return false;
        }


        return true;
    }


    private String getIdCard() {
        return Util.trimString(acetIdCard.getText().toString());
    }

    private String getName() {
        return Util.trimString(acetName.getText().toString());
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
        switch (id) {
            case R.id.action_take_photo:
//                Util.showToast(this, "拍照按钮被点击");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doAddFamilyMember() {
        String userId  = IdentityManager.getId(this);
        String selectedRelationship = getSelectedRelationship();
        Map<String, String> map = ParametersFactory.getAddFamilyMemberMapFromParameters(this, userId, getIdCard(), getName(),
                selectedRelationship);
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.main(body);

    }


    @Override
    public void onAddFamilyMemberSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.showToast(this, Constants.HINT_ADD_FAMILY_MEMBER_SUCCESS);
        RxBus.getDefault().post(Constants.EVENT_TYPE_ADD_FAMILY_MEMBER_SUCCESS, (Integer) 0);
        finish();
    }

    @Override
    public void onAddFamilyMemberFailure(String msg) {
        Util.showToast(this, msg);
    }
}
