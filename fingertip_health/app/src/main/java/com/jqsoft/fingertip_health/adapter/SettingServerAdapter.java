package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerStatesBean;
import com.jqsoft.fingertip_health.util.Util;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//
public class SettingServerAdapter extends BaseQuickAdapterEx<SettingServerBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
//    HashMap<String, SettingServerStatesBean> states = new HashMap<String, SettingServerStatesBean>();
    HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    List<SettingServerStatesBean> mStates=new ArrayList<>();
    List<String>  iplist=new ArrayList<>();
    private int type=TYPE_MULTIPLE_LINE;
    private Context context;
    SettingServerBean item;
    public SettingServerAdapter(List<SettingServerBean> data, int type) {
        super(R.layout.item_setting_server_single_line, data);
        this.type = type;
//        this.context=context;
    }

    public   HashMap<String, Boolean>  getstates(){
        return  states  ;

    }
    public  List<String> getIplist(){
        return iplist;
    }
    public  SettingServerBean getSettingServerBean(){
        return item;
    }
    public void SettingChoose(String  ip){
        states.put(ip,true);


    }




    @Override
    protected void convert(final BaseViewHolder helper, final SettingServerBean item) {
      this. item=item;
        helper.setText(R.id.ip, Util.trimString(item.getIp()));//getname
        iplist.add(item.getIp());
        helper.setText(R.id.address, Util.trimString(item.getAddress()));
        final SmoothRadioButton radioButton=(SmoothRadioButton) helper.getView(R.id.choose);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for (String key : states.keySet()) {
                    SettingServerStatesBean bean=new SettingServerStatesBean("",false);
                    states.put(key, false);


                }
                SettingServerStatesBean bean1=new SettingServerStatesBean();
                bean1.setIp(item.getIp());
                bean1.setIscheck(radioButton.isChecked());
                states.put(item.getIp(),radioButton.isChecked());
//                states.put(String.valueOf(helper.getPosition()),radioButton.isChecked());
//                mStates.add(bean1);
                notifyDataSetChanged();
            }
        });

        boolean res = false;
        if (states.get(String.valueOf(item.getIp())) == null
                || states.get(item.getIp()) == false) {
//                || states.get(String.valueOf(helper.getPosition())) == false) {
            res = false;
//            SettingServerStatesBean bean2=new SettingServerStatesBean("",false);
            states.put(item.getIp(), false);

        } else
            res = true;


        radioButton.setChecked(res);

    }



}
