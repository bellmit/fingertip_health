package com.jqsoft.fingertip_health.di.ui.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SettingServerAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;


//更改添加服务器
public class SettingServerChangeActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.treatment_title)
    TextView treatment_title;
    @BindView(R.id.ip)
    EditText ed_ip;
    @BindView(R.id.d_ip)
    EditText ed_dip;
    @BindView(R.id.address)
    EditText ed_address;
    @BindView(R.id.btn_save)
    RoundTextView btn_save;
    private SettingServerAdapter mAdapter;
    SettingServerBean   settingServerBean;
    String SettingStyle="0";
    private static  String CHANGESERVER="1";
    private static  String ADDSERVER="2";
    int Checkposition;
    List<SettingServerBean>  Serverlist = DataSupport.findAll(SettingServerBean.class);
    private   int repeatipflag=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_serverchange;
    }

    @Override
    protected void initData() {
        SettingStyle=(String)getDeliveredSerializableByKey(Constants.SETTING_SERVER_ACTIVITY_STYE_KEY);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
        treatment_title.setText("设置服务器");
        if (SettingStyle.equals(ADDSERVER)){

        }else if (SettingStyle.equals(CHANGESERVER)){
            settingServerBean=(SettingServerBean)getDeliveredSerializableByKey(Constants.SETTING_SERVER_ACTIVITY_KEY);
            Checkposition=(int)getDeliveredSerializableByKey(Constants.SETTING_SERVER_POSTION_ACTIVITY_KEY);
            ed_address.setTextColor(R.color.black_alpha_128);
            ed_address.setText(settingServerBean.getAddress());
            String ipstr= settingServerBean.getIp();

           if (isIp(ipstr)){
        try {
            String ip=ipstr.substring(0,ipstr.indexOf(":"));
            ed_ip.setTextColor(R.color.black_alpha_128);
            ed_dip.setTextColor(R.color.black_alpha_128);

            ed_ip.setText(ip);

            String dip=ipstr.substring(ipstr.indexOf(":")+1 ,ipstr.length());

            ed_dip.setText(dip);

        }catch (Exception e){
            Toast.makeText(this,"ip地址格式不正确",Toast.LENGTH_SHORT).show();

        }
           }else {
               Toast.makeText(this,"ip地址格式不正确",Toast.LENGTH_SHORT).show();
           }


        }
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ip= ed_ip.getText().toString();
                String dip= ed_dip.getText().toString();
                String address=ed_address.getText().toString();
                String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
                String ipstr=ip+":"+dip;
              //判断ip重复

                if (isIp(ipstr)) {


                    if (SettingStyle.equals(ADDSERVER)) {
                        addrepeatip(ipstr);
                        if (repeatipflag==0){
                        SettingServerBean bean = new SettingServerBean(ipstr, address, name, "0");
                        bean.setAddress(address);
                        bean.setIp(ipstr);
                        bean.setUsername(name);
                        if (bean.save()) {

                            Toast.makeText(SettingServerChangeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SettingServerChangeActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }


                        }else {
                            Toast.makeText(SettingServerChangeActivity.this,"已经存在相同服务器地址",Toast.LENGTH_SHORT).show();
                            repeatipflag=0;
                        }

                    } else if (SettingStyle.equals(CHANGESERVER)) {
                        changerepeatip(ipstr);
                 if (repeatipflag==1) {

                     Toast.makeText(SettingServerChangeActivity.this,"已经存在相同服务器地址",Toast.LENGTH_SHORT).show();
                     repeatipflag=0;
                 }else {

                     ContentValues values = new ContentValues();
                     values.put("address", address);
                     values.put("ip", ipstr);
                     values.put("username", name);
                     DataSupport.update(SettingServerBean.class, values, Checkposition + 1);
                     Serverlist = DataSupport.findAll(SettingServerBean.class);
                     Toast.makeText(SettingServerChangeActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
                     finish();

                 }

                    }

                }else {
                    Toast.makeText(SettingServerChangeActivity.this,"ip地址格式不正确",Toast.LENGTH_SHORT).show();
                }



            }
        });





    }


    @Override
    protected void loadData() {




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public static boolean isIp(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)"+
                    ":([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }
private  void addrepeatip(String ip){
    for (int i =0;i<Serverlist.size();i++){

        if (Serverlist.get(i).getIp().equals(ip)){

            repeatipflag=1;

        }

    }

}
private void   changerepeatip(String ip){
    for (int i =0;i<Serverlist.size();i++){

        if (Serverlist.get(i).getIp().equals(settingServerBean.getIp())){

        }else   if (Serverlist.get(i).getIp().equals(ip)){


            repeatipflag=1;

        }



    }


}

}