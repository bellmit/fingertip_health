package com.jqsoft.fingertip_health.optionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2018-05-22.
 */

public class HbOneSetTextOptionsLayout extends LinearLayout {
    @BindView(R.id.iv_necessity)
    TextView ivNecessity;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.fl_container)
    FlowLayout flContainer;

    boolean isCompulsory;
    String name;
    boolean isNameVisible;
    Context context;
    boolean isSingleSelect=true;

    List<NameValueBeanWithNo> optionList = new ArrayList<>();
    List<TextLayoutWithNo> textLayoutList = new ArrayList<>();

    public HbOneSetTextOptionsLayout(Context context, boolean isCompulsory, String name, boolean isNameVisible, boolean isSingleSelect) {
        super(context);
        this.context=context;
        View.inflate(context, R.layout.layout_hb1_name_options, this);
        ButterKnife.bind(this);
        this.context=context;
        this.isCompulsory=isCompulsory;
        this.name=name;
        this.isNameVisible=isNameVisible;
        this.isSingleSelect=isSingleSelect;

        init();

    }
    public void  setclickable(String isclickable){
        if (isclickable.equals("false")){
            for (int i=0;i<textLayoutList.size();i++){
                textLayoutList.get(i).setClickable(false);
            }
        }

    }
    public  List<TextLayoutWithNo> getTextLayoutList(){
        return textLayoutList;
    }
    public HbOneSetTextOptionsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.layout_hb1_name_options, this);
        ButterKnife.bind(this);
        this.context=context;
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.NecessityNameOptionsLayout);
        isCompulsory=attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_compulsory, false);
        name=attributes.getString(R.styleable.NecessityNameOptionsLayout_name_text);
        isNameVisible=attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_name_visible, true);
        isSingleSelect=attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_single_select, true);
        attributes.recycle();

        init();
    }

    private void init(){
        showOrHideNecessity();
        if (isCompulsory){
            ivNecessity.setVisibility(VISIBLE);
        }else {
            ivNecessity.setVisibility(INVISIBLE);
        }
        tvName.setText(name);
        showOrHideName();


    }

    public void setDataList(final List<NameValueBeanWithNo> list){
        optionList=list;
        textLayoutList.clear();

        flContainer.removeAllViewsInLayout();

        if (!ListUtils.isEmpty(optionList)){
            for (int i = 0; i < optionList.size(); ++i) {
                final NameValueBeanWithNo nvb = optionList.get(i);
                final TextLayoutWithNo textLayout = new TextLayoutWithNo(getContext(), nvb, isSingleSelect);
                FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(6, 6, 6, 6);
                textLayout.setLayoutParams(layoutParams);
                textLayout.setPadding(6, 6, 6, 6);
                textLayout.setGravity(Gravity.CENTER);
//                textLayout.setBackgroundResource(R.drawable.blue_border_background);
//                textLayout.setTextColor(getResources().getColor(R.color.deepskyblue));
//                textLayout.setText(evaluation);
//                Util.setViewListener(textLayout, new Runnable() {
//                    @Override
//                    public void run() {
//                        if (isSingleSelect){
//                            clearTextLayoutListState();
//                            textLayout.setState(true);
//                        } else {
//                            textLayout.setState(!nvb.isSelected());
//                        }
//                    }
//                });
                final int finalI = i;
                textLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isSingleSelect){
                            clearTextLayoutListState();
                            textLayout.setState(true);

                         if (list.get(finalI).getName().equals("无")) {
                             if(list.get(finalI).getNumber().equals("4")){
                                 RxBusBaseMessage sx = new RxBusBaseMessage();
                                 sx.setCode(3);
                                 RxBus.getDefault().post(Constant.ENENT_BW, sx);
                             }

                            }else if (list.get(finalI).getName().equals("有")) {
                             if(list.get(finalI).getNumber().equals("3")){
                                 RxBusBaseMessage sx = new RxBusBaseMessage();
                                 sx.setCode(4);
                                 RxBus.getDefault().post(Constant.ENENT_BW, sx);
                             }

                            }
                        } else {
                            textLayout.setState(!nvb.isSelected());
                        }
                    }
                });
                textLayoutList.add(textLayout);
                flContainer.addView(textLayout);
            }

        }
    }

    private void clearTextLayoutListState(){
        for (int i = 0; i < textLayoutList.size(); ++i){
            TextLayoutWithNo tl = textLayoutList.get(i);
            tl.setState(false);
        }
        for (int i = 0; i < optionList.size(); ++i){
            NameValueBeanWithNo nvb = optionList.get(i);
            nvb.setSelected(false);
        }

    }


    private void showOrHideNecessity() {
        int visibility = INVISIBLE;
        if (isCompulsory){
            visibility=VISIBLE;
        } else {
            visibility=INVISIBLE;
        }
//        ivNecessity.setVisibility(visibility);
    }

    private void showOrHideName() {
        int visibility = INVISIBLE;
        if (isNameVisible){
            visibility=VISIBLE;
        } else {
            visibility=INVISIBLE;
        }
        tvName.setVisibility(visibility);
    }


    public boolean isCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(boolean compulsory) {
        isCompulsory = compulsory;
    }

    public boolean isSingleSelect() {
        return isSingleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        isSingleSelect = singleSelect;
    }
    public NameValueBeanWithNo getSelectBean(){
        int selectedIndex = -1;
        NameValueBeanWithNo selectedNvb = null;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex!=-1){
             selectedNvb = optionList.get(selectedIndex);
            result=selectedNvb.getName();
        }
        return selectedNvb;
    }
    public String getSingleSelectName(){
        int selectedIndex = -1;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex!=-1){
            NameValueBeanWithNo selectedNvb = optionList.get(selectedIndex);
            result=selectedNvb.getName();
        }
        return result;
    }

    public String getSingleSelectValue(){
        int selectedIndex = -1;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex!=-1){
            NameValueBeanWithNo selectedNvb = optionList.get(selectedIndex);
            result=selectedNvb.getValue();
        }
        return result;
    }
    public String getSelectValue() {
        int selectedIndex = -1;
        String result = Constant.EMPTY_STRING;


//        if (selectedIndex!=-1){
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBeanWithNo nvb = optionList.get(i);
            if (nvb.isSelected()) {
                if (result.equals(Constant.EMPTY_STRING)) {
                    result = nvb.getStringValue();
                } else {
                    result = result + "," + nvb.getStringValue();
                }


            }
        }
//        }
        return result;
    }
    public String getTitleString(){
        String s = Util.trimString(name);
        return s;
    }

    private int getSelectedIndex() {
        int selectedIndex = -1;
        for (int i = 0; i < optionList.size(); ++i){
            NameValueBeanWithNo nvb = optionList.get(i);
            if (nvb.isSelected()){
                selectedIndex=i;
                break;
            }
        }
        return selectedIndex;
    }



    public boolean isValid(){
        if (isSingleSelect){
            String singleSelectValue = getSingleSelectValue();
            boolean isValid = !StringUtils.isBlank(singleSelectValue);
            return isValid;
        } else {
            return true;
        }
    }

    public void setSingleSelectText(String name){
        name= Util.trimString(name);
        for (int i = 0; i < optionList.size(); ++i){
            NameValueBeanWithNo nvb = optionList.get(i);
            if (name.equals(nvb.getName())){
                clearTextLayoutListState();
                TextLayoutWithNo textLayout = textLayoutList.get(i);
                nvb.setSelected(true);
                textLayout.setState(true);
                break;
            }
        }

    }
    public void setSingleSelectValue(String value){
        value= Util.trimString(value);
        for (int i = 0; i < optionList.size(); ++i){
            NameValueBeanWithNo nvb = optionList.get(i);
            if (value.equals(nvb.getValue())){
                clearTextLayoutListState();
                TextLayoutWithNo textLayout = textLayoutList.get(i);
                nvb.setSelected(true);
                textLayout.setState(true);
                break;
            }
        }

    }

    public void setSingleSelectValue1(String value){
        value= Util.trimString(value);
        for (int i = 0; i < optionList.size(); ++i){
            NameValueBeanWithNo nvb = optionList.get(i);
            if (value.equals(nvb.getValue())){
                clearTextLayoutListState();
                TextLayoutWithNo textLayout = textLayoutList.get(i);
                nvb.setSelected(true);
                textLayout.setState(true);
                break;
            }
        }

    }

}
