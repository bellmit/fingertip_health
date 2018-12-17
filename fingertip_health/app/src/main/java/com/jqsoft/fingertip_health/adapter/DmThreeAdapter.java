package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.di.ui.activity.DiabetesMellitusActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.DMFragment3;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DmThreeAdapter extends BaseQuickAdapterEx<UseDrugInfo, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private Map<Integer, String> unitCheckmap = new HashMap<>();
    private Map<Integer, String> timesCheckmap = new HashMap<>();
    private Map<Integer, String> NameCheckmap = new HashMap<>();


    private Map<Integer, String> ManyCheckmap = new HashMap<>();
    private Map<Integer, String> drugUsageCheckmap = new HashMap<>();



    private Context context;
    private FlowPopWindow flowPopWindow;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private List<FiltrateBean> unitList = new ArrayList<>();
    private  Boolean isyds;


    public DmThreeAdapter(List<UseDrugInfo> data, Context  context,Boolean isyds) {
        super(R.layout.item_usedrug_layout);
        this.context=context;
        this.isyds=isyds;
    }

    private void initParam() {
        String[] colors = {"每天两次", "每周两次","每天一次","必要时","12小时一次"
                ,"1小时一次","2小时一次","3小时一次","4小时一次","6小时一次"
                ,"8小时一次","每天四次","每晚一次","隔日使用","每周一次","立即","每周三次","每天三次"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("每日次数");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);
        dictList.add(fb2);

        String[] untis = {"瓶", "盒","片","粒","支"," kg","g","个","板","付","袋","包","根","卷","套","听","桶",
                "ml","张","块","只","吨","枚","双","条","筒","箱","扎","斤","本","组","份","mg","ug","l","丸","贴",
        };
        FiltrateBean fb = new FiltrateBean();
        fb.setTypeName("单位");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (int x = 0; x < untis.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(untis[x]);
            childrenList.add(cd);
        }
        fb.setChildren(childrenList);
        unitList.add(fb);


    }
    @Override
    protected void convert(final BaseViewHolder helper, final UseDrugInfo item) {
        final DiabetesMellitusActivity activity = (DiabetesMellitusActivity) context;
        final DMFragment3 dmFragment3=    activity.getDMFragment3();


        if (isyds){

            helper.setVisible(R.id.sDrug_EveryTime,false);
            helper.setVisible(R.id.ed_many,true);
            helper.setVisible(R.id.tv_unit,false);
            helper.setVisible(R.id.tip,false);
            helper.setVisible(R.id.tip2,false);
            helper.setVisible(R.id.tip3,false);
            helper.setVisible(R.id.daytimes,false);
            helper.setVisible(R.id.drugUsage,true);
        }else {

            helper.setVisible(R.id.sDrug_EveryTime,true);
            helper.setVisible(R.id.ed_many,false);
            helper.setVisible(R.id.tv_unit,true);
            helper.setVisible(R.id.tip,true);
            helper.setVisible(R.id.tip2,true);
            helper.setVisible(R.id.tip3,true);
            helper.setVisible(R.id.daytimes,true);
            helper.setVisible(R.id.drugUsage,false);


        }


        helper.setOnClickListener(R.id.bt_username_clear, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dmFragment3.removeDate(helper.getPosition(),isyds);
                unitCheckmap.remove(helper.getPosition());
                timesCheckmap.remove(helper.getPosition());
                NameCheckmap.remove(helper.getPosition());

                ManyCheckmap.remove(helper.getPosition());
                drugUsageCheckmap.remove(helper.getPosition());

            }
        });

        helper.setOnClickListener(R.id.line_unit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unitList.clear();
                initParam();
                flowPopWindow = new FlowPopWindow(activity, unitList,true);
                flowPopWindow.showAsDropDown(activity.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : unitList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            helper.setText(R.id.tv_unit,value);
                            dmFragment3.setDate(helper.getPosition(),value,"单位",isyds);
                            unitCheckmap.put(helper.getPosition(),value);
                        }else {
                            dmFragment3.setDate(helper.getPosition(),"mg","单位",isyds);
                            unitCheckmap.put(helper.getPosition(),"mg");
                            helper.setText(R.id.tv_unit,"mg");
                        }

                    }
                });
            }
        });
        helper.setOnClickListener(R.id.Day, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dictList.clear();
                initParam();
                flowPopWindow = new FlowPopWindow(activity, dictList,true);
                flowPopWindow.showAsDropDown(activity.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            dmFragment3.setDate(helper.getPosition(),value,"频次",isyds);
                            helper.setText(R.id.daytimes,value);
                            timesCheckmap.put(helper.getPosition(),value);
                        }else {
                            dmFragment3.setDate(helper.getPosition(),"每日一次","频次",isyds);
                            helper.setText(R.id.daytimes,"每日一次");
                            timesCheckmap.put(helper.getPosition(),"每日一次");
                        }

                    }
                });
            }
        });


        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_person_name, Util.trimString(item.getName()));
        }
       final EditText ed_many= helper.getView(R.id.ed_many);
        ed_many.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 胰岛素用量 放单位
                dmFragment3.setDate(helper.getPosition(),ed_many.getText().toString(),"单位",isyds);
                ManyCheckmap.put(helper.getPosition(),ed_many.getText().toString());
            }
        });
        helper.setText(R.id.daytimes, Util.trimString(item.getFrequency()));
        helper.setText(R.id.sDrug_EveryTime, Util.trimString(item.getSingleDose()));
        final TextView drugUsage=helper.getView(R.id.drugUsage);
        drugUsage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dmFragment3.setDate(helper.getPosition(),drugUsage.getText().toString(),"用法",isyds);
                drugUsageCheckmap.put(helper.getPosition(),drugUsage.getText().toString());
            }
        });


        final TextView sDrugName = helper.getView(R.id.tv_person_name);
        sDrugName .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dmFragment3.setDate(helper.getPosition(),sDrugName .getText().toString(),"药名",isyds);
                NameCheckmap.put(helper.getPosition(),sDrugName .getText().toString());
            }
        });

        final TextView sDrug_EveryTime = helper.getView(R.id.sDrug_EveryTime);

        sDrug_EveryTime .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                    dmFragment3.setDate(helper.getPosition(),sDrug_EveryTime .getText().toString(),"单次剂量",isyds);



            }
        });


        ImageView button= (ImageView) helper.getView(R.id.bt_username_clear);
        if (helper.getPosition()==0){

            button.setVisibility(View.INVISIBLE);
        }else {
            button.setVisibility(View.VISIBLE);
            helper.setBackgroundRes(R.id.bt_username_clear,R.mipmap.remove_red);
        }

        if (ManyCheckmap.get(helper.getPosition()) == null) {
            ManyCheckmap.put(helper.getPosition(), "");
        }
        if (drugUsageCheckmap.get(helper.getPosition()) == null) {
            drugUsageCheckmap.put(helper.getPosition(), "");
        }
        if (timesCheckmap.get(helper.getPosition()) == null) {
            timesCheckmap.put(helper.getPosition(), "每日一次");
        }
        if (NameCheckmap.get(helper.getPosition()) == null) {
            NameCheckmap.put(helper.getPosition(), "");
        }
        if (unitCheckmap.get(helper.getPosition()) == null) {
            unitCheckmap.put(helper.getPosition(), "mg");
        }
        helper.setText(R.id.tv_unit,unitCheckmap.get(helper.getPosition()));
        helper.setText(R.id.daytimes,timesCheckmap.get(helper.getPosition()));

        if (TextUtils.isEmpty(NameCheckmap.get(helper.getPosition()))){
            TextView name=helper.getView(R.id.tv_person_name);
            name.setText("");
            name.setHint("请输入药物名称");
        }else {
            helper.setText(R.id.tv_person_name,NameCheckmap.get(helper.getPosition()));
        }

        helper.setText(R.id.ed_many,ManyCheckmap.get(helper.getPosition()));
        helper.setText(R.id.drugUsage,drugUsageCheckmap.get(helper.getPosition()));

    }



}
