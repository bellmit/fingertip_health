package com.jqsoft.fingertip_health.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment2;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.inflate;


public class HbThreeAdapter extends BaseQuickAdapterEx<UseDrugInfo, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private Map<Integer, String> unitCheckmap = new HashMap<>();
    private Map<Integer, String> timesCheckmap = new HashMap<>();
    private Map<Integer, String> NameCheckmap = new HashMap<>();
    private int type=TYPE_MULTIPLE_LINE;
    private Context context;
    private FlowPopWindow flowPopWindow;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private List<FiltrateBean> unitList = new ArrayList<>();


    public HbThreeAdapter(List<UseDrugInfo> data, Context  context) {
        super(R.layout.item_usedrug_layout, data);
        this.type = type;
        this.context=context;
    }

    private void initParam() {
        String[] colors = {"????????????", "????????????","????????????","?????????","12????????????"
                ,"1????????????","2????????????","3????????????","4????????????","6????????????"
                ,"8????????????","????????????","????????????","????????????","????????????","??????","????????????","????????????"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("????????????");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);
        dictList.add(fb2);

        String[] untis = {"???", "???","???","???","???"," kg","g","???","???","???","???","???","???","???","???","???","???",
                "ml","???","???","???","???","???","???","???","???","???","???","???","???","???","???","mg","ug","l","???","???",
        };
        FiltrateBean fb = new FiltrateBean();
        fb.setTypeName("??????");
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
        final HighBloodActivity activity = (HighBloodActivity) context;
        final HBFragment3 hbFragment3=    activity.getHbFragment3();
        helper.setOnClickListener(R.id.bt_username_clear, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hbFragment3.removeDate(helper.getPosition());
                unitCheckmap.remove(helper.getPosition());
                timesCheckmap.remove(helper.getPosition());
                NameCheckmap.remove(helper.getPosition());

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
                            hbFragment3.setDate(helper.getPosition(),value,"??????");
                            unitCheckmap.put(helper.getPosition(),value);
                        }else {
                            hbFragment3.setDate(helper.getPosition(),"mg","??????");
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
                        helper.setText(R.id.daytimes,value);
                        timesCheckmap.put(helper.getPosition(),value);
                        hbFragment3.setDate(helper.getPosition(),value,"??????");
                    }else {
                        hbFragment3.setDate(helper.getPosition(),"????????????","??????");
                        helper.setText(R.id.daytimes,"????????????");
                        timesCheckmap.put(helper.getPosition(),"????????????");
                    }

                }
            });
        }
    });



        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_person_name, Util.trimString(item.getName()));
        }


        helper.setText(R.id.daytimes, Util.trimString(item.getFrequency()));
        helper.setText(R.id.sDrug_EveryTime, Util.trimString(item.getSingleDose()));




       final TextView  daytimes =helper.getView(R.id.daytimes);
       daytimes.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {
               hbFragment3.setDate(helper.getPosition(),daytimes .getText().toString(),"??????");


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
                hbFragment3.setDate(helper.getPosition(),sDrugName .getText().toString(),"??????");
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
                hbFragment3.setDate(helper.getPosition(),sDrug_EveryTime .getText().toString(),"????????????");
            }
        });
        ImageView button= (ImageView) helper.getView(R.id.bt_username_clear);
        if (helper.getPosition()==0){

            button.setVisibility(View.INVISIBLE);
        }else {
            button.setVisibility(View.VISIBLE);
            helper.setBackgroundRes(R.id.bt_username_clear,R.mipmap.remove_red);
        }
        if (timesCheckmap.get(helper.getPosition()) == null) {
            timesCheckmap.put(helper.getPosition(), "????????????");
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
            name.setHint("?????????????????????");
        }else {
            helper.setText(R.id.tv_person_name,NameCheckmap.get(helper.getPosition()));
        }



    }



}
