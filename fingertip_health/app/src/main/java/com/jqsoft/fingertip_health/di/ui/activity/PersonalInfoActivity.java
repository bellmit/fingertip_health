package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonalInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.PersonalInfoContract;
import com.jqsoft.fingertip_health.di.module.PersonalInfoActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PersonalInfoPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Utils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

//我的信息
public class PersonalInfoActivity extends AbstractActivity implements PersonalInfoContract.View {

//    @BindView(R.id.edit_cardid)
//    TextView edit_cardid;
//    @BindView(R.id.edit_address)
//    TextView edit_address;
    @BindView(R.id.edit_name)
    EditText edit_name;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.RReset)
    RelativeLayout Rl_Reset;
    @BindView(R.id.Rpost)
    RelativeLayout Rl_post;

    @BindView(R.id.tv_loginname)
    TextView tv_loginname;
    @BindView(R.id.treatment_title)
    TextView treatment_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    PersonalInfoPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initData() {
        edit_phone.setText("");
        edit_name.setText(DaggerApplication.getInstance().getRealName());
        tv_loginname.setText(DaggerApplication.getInstance().getUsername());
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        treatment_title.setText("个人资料");
        Rl_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_phone.setText("");
                edit_name.setText("");
            }
        });
       Rl_post.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               savepersonalinfo();
           }
       });
        edit_phone.setCursorVisible(false);
        edit_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        edit_phone.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter(11)});
        edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_phone.setCursorVisible(true);
            }
        });
        edit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int size= s.toString().length();
                if(size==11 ){
                    if(s.toString().indexOf("-")==-1) {
                        boolean isPhone = Utils.isMobileNO(s.toString());
                        if (isPhone) {

                        } else {
                            Toast.makeText(getApplicationContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(size==12 || size==13){
                    if(s.toString().indexOf("-")!=-1){
                        boolean isTel= Utils.IsTelephone(s.toString());
                        if(  isTel){

                        }else {
                            Toast.makeText(getApplicationContext(),"请输入正确的联系电话",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "请输入正确的联系电话", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    @Override
    protected void loadData() {
//        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        Map<String, String> map = ParametersFactory.getGCAPersonInfoMap(this, name, "personInfo.personalInformation");
//        mPresenter.main(map);



    }





    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addPersonalInfoActivity(new PersonalInfoActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void savepersonalinfo(){
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        String realname=edit_name.getText().toString();
         String phone=   edit_phone.getText().toString();
        if (realname.equals("")){
            Toast.makeText(this,"姓名信息不能为空",Toast.LENGTH_SHORT).show();
        }else {
        Map<String, String> map = ParametersFactory.getGCASavePersonInfoMap(this, name, realname,phone,"personInfo.savePersonInfo");
        mPresenter.postinfo(map);}



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_my_info, menu);
        return super.onCreateOptionsMenu(menu);
    }



//
//    public void showFetchedInfo(PersonalInfoBean bean){
//        if (bean!=null){
//
//            Spanned html = Html.fromHtml("<u>"+phoneNumber+"</u>");
//            tvContactNumber.setText(html);
//            tvContactNumber.setTextColor(getResources().getColor(R.color.blue));
//            tvContactNumber.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//
//                    if (!StringUtils.isBlank(phoneNumber)) {
//                        AppUtils.actionDial(PersonalInfoActivity.this, phoneNumber);
//                    } else {
//                        Util.showToast(PersonalInfoActivity.this, Constants.HINT_PHONE_NUMBER_EMPTY);
//                    }
//                }
//            });
//            btnUpdatePhoneNumber.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
////                    RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_TRANSACT);
//
////                    Util.showToast(MyInfoActivity.this, "更新电话号码按钮被点击");
//                    Util.showEditTextMaterialDialog(PersonalInfoActivity.this, Constants.HINT, Constants.HINT_UPDATE_PHONE, Constants.HINT_PLEASE_INPUT_PHONE_NUMBER, phoneNumber,
//                            false, 1, 20, Constants.EDIT_INPUT_TYPE_PHONE, new MaterialDialog.InputCallback() {
//                                @Override
//                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
////                                    updatePhoneNumber(input.toString());
//
//                                }
//                            });
//                }
//            });
//        } else {
//            Util.showToast(this, Constants.HINT_GET_MY_INFO_EMPTY);
//        }
//    }
    private String checknull(String str){
        if (str==null){
            return "暂无数据";

        }else {
            return str;
        }

        }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<PersonalInfoBean> bean) {
        PersonalInfoBean bean1= bean.getData();
//        edit_cardid.setText(checknull(bean1.getCardNo()));
//        edit_address.setText(checknull(bean1.getArea()));
        edit_phone.setText(checknull(bean1.getMobiePhone()));
        edit_name.setText(checknull(bean1.getRealName()));
        tv_loginname.setText(checknull(bean1.getUserName()));




    }

    @Override
    public void onPostSuccess(GCAHttpResultBaseBean<PersonalInfoBean> bean) {
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
