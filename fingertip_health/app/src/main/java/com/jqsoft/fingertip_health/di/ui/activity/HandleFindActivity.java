package com.jqsoft.fingertip_health.di.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.HandleFindContract;
import com.jqsoft.fingertip_health.di.module.HandleFindModule;
import com.jqsoft.fingertip_health.di.presenter.HandleFindPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class HandleFindActivity extends AbstractActivity  implements HandleFindContract.View, SmoothRadioButton.OnCheckedChangeListener,SmoothRadioGroup.OnCheckedChangeListener{


    private String batchNo,discoverId,userName;

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;

    @BindView(R.id.et_chulitime)
    TextView et_chulitime;

    @BindView(R.id.et_remark)
    EditText et_remark;

    @BindView(R.id.btn_handle_save)
    RelativeLayout btn_handle_save;

    @BindView(R.id.rg_adress1)
    SmoothRadioGroup rg_adress1;

    @BindView(R.id.rb_adress1)
    SmoothRadioButton rb_adress1;

    @BindView(R.id.rb_adress2)
    SmoothRadioButton rb_adress2;

    private String module1="2",remark="";

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    @Inject
    HandleFindPresenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_handle_find_discover;
    }

    @Override
    protected void initData() {
        btn_handle_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String act =module1;
                remark =et_remark.getText().toString();

                SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String    date    =    sDateFormat.format(new    java.util.Date());

                String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());

                if(remark.equals("")){
                    Toast.makeText(getApplicationContext(),"备注内容不可为空",Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> map = ParametersFactory.getHandleFind(HandleFindActivity.this,name,
                            batchNo,discoverId,module1,remark,date);
                    mPresenter.main(map);
                }


            }
        });


        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




    @Override
    protected void initView() {
        batchNo=getDeliveredStringByKey("batchNo");
        discoverId=getDeliveredStringByKey("discoverId");
        userName=getDeliveredStringByKey("userName");

        if (module1.equals("2")){
            rb_adress1.setChecked(true);
        }

        rb_adress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module1="2";
            }
        });
        rb_adress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module1="3";
            }
        });

        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
        String    date2    =    sDateFormat.format(new    java.util.Date());
        et_chulitime.setText(date2);

        et_remark.setCursorVisible(false);

        et_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_remark.setCursorVisible(true);
            }
        });

    }

    @Override
    protected void loadData() {

    }


    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .handlenewFind(new HandleFindModule(this))
                .inject(this);
    }

    @Override
    public void onHandleFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        if(bean!=null){
            Toast.makeText(getApplicationContext(),"处理成功",Toast.LENGTH_SHORT).show();


                setResult(RESULT_SUCCESS);

               finish();
        }
    }

    @Override
    public void onHandleFindFailure(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
        switch (smoothCompoundButton.getId()) {
            case R.id.rb_adress1:

                module1="2";


                //     Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress2:
                module1="3";


                //    Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
                break;


            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(SmoothRadioGroup smoothRadioGroup, int i) {
        switch (i) {
            case R.id.rb_adress1:
                module1="2";


                //   Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_adress2:
                module1="3";


                //   Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
                break;


            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_FAILED);

            finish();
        }
        return super.onKeyDown(keyCode, event);

    }
}
