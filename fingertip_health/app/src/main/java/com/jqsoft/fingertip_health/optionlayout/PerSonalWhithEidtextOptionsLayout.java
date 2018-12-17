package com.jqsoft.fingertip_health.optionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;


import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-05-22.
 */

public class PerSonalWhithEidtextOptionsLayout extends LinearLayout {
    @BindView(R.id.iv_necessity)
    ImageView ivNecessity;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.fl_container)
    FlowLayout flContainer;
    @BindView(R.id.et_input)
    EditText et_input;
    boolean isCompulsory;
    String name;
    boolean isNameVisible;
    Context context;
    boolean isSingleSelect = true;

    List<NameValueBean> optionList = new ArrayList<>();
    List<PerSonalTextLayout> textLayoutList = new ArrayList<>();

    public PerSonalWhithEidtextOptionsLayout(Context context, boolean isCompulsory, String name, boolean isNameVisible, boolean isSingleSelect) {
        super(context);
        this.context = context;
        View.inflate(context, R.layout.item_build_document_layout, this);
        ButterKnife.bind(this);
        this.context = context;
        this.isCompulsory = isCompulsory;
        this.name = name;
        this.isNameVisible = isNameVisible;
        this.isSingleSelect = isSingleSelect;

        init();

    }

    public List<NameValueBean> getOptionList() {
        return optionList;
    }

    public void setclickable(String isclickable) {
        if (isclickable.equals("false")) {
            for (int i = 0; i < textLayoutList.size(); i++) {
                textLayoutList.get(i).setClickable(false);
            }
        }

    }

    public PerSonalWhithEidtextOptionsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.item_build_layout, this);
        ButterKnife.bind(this);
        this.context = context;
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.NecessityNameOptionsLayout);
        isCompulsory = attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_compulsory, false);
        name = attributes.getString(R.styleable.NecessityNameOptionsLayout_name_text);
        isNameVisible = attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_name_visible, true);
        isSingleSelect = attributes.getBoolean(R.styleable.NecessityNameOptionsLayout_is_single_select, true);
        attributes.recycle();

        init();
    }

    private void init() {
        showOrHideNecessity();
        tvName.setText(name);
        showOrHideName();


    }

    public List<PerSonalTextLayout> getTextLayoutList() {
        return textLayoutList;
    }

    public void setDataList(final List<NameValueBean> list) {
        optionList = list;
        textLayoutList.clear();
        if (!ListUtils.isEmpty(optionList)) {
            for (int i = 0; i < optionList.size(); ++i) {


                final NameValueBean nvb = optionList.get(i);
                final PerSonalTextLayout textLayout = new PerSonalTextLayout(getContext(), nvb, isSingleSelect);
                FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(6, 6, 6, 6);
                textLayout.setLayoutParams(layoutParams);
                textLayout.setPadding(6, 6, 6, 6);
                textLayout.setGravity(Gravity.CENTER);
//                if (i==11){
//                    textLayout.setvisibale("false");
//                }
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
                        if (isSingleSelect) {


                            if (list.get(finalI).isSelected()) {
                                textLayout.setState(false);
                            } else {
                                clearTextLayoutListState();
                                textLayout.setState(true);

                            }
                            et_input.setVisibility(View.GONE);
                            int t = 0;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getName().equals("其他")) {
                                    t = i;
                                }
                            }
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getName().equals("有")) {
                                    t = i;
                                }
                            }
                            if (finalI == t && !(t == 0)) {
                                if (nvb.isSelected()) {
                                    final PerSonalTextLayout textLayout = new PerSonalTextLayout(getContext(), new NameValueBean("输入框", "", false), isSingleSelect);
                                    FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
                                    layoutParams.setMargins(6, 6, 6, 6);
                                    textLayout.setLayoutParams(layoutParams);
                                    textLayout.setPadding(6, 6, 6, 6);
                                    textLayout.setGravity(Gravity.CENTER);
                                    textLayoutList.add(textLayout);
                                    flContainer.addView(textLayout);
                                } else {
                                    if (textLayoutList.size() > (list.size())) {
                                        textLayoutList.remove(list.size());
                                        flContainer.removeViewAt(list.size());
                                    }
                                }

                            } else {
                                if (textLayoutList.size() > (list.size())) {
                                    textLayoutList.remove(list.size());
                                    flContainer.removeViewAt(list.size());
                                }
                            }


                        } else {
                            et_input.setVisibility(View.GONE);
                            int t1 = 20;
                            for (int i1 = 0; i1 < list.size(); i1++) {
                                if (list.get(i1).getName().equals("无")) {
                                    t1 = i1;
                                }
                            }
                            for (int i1 = 0; i1 < list.size(); i1++) {
                                if (list.get(i1).getName().equals("无残疾")) {
                                    t1 = i1;
                                }
                            }
                            if (finalI == t1) {
                                for (int t = 0; t < textLayoutList.size(); t++) {
                                    textLayoutList.get(t).setState(false);
                                }
                                textLayout.setState(!nvb.isSelected());
                                if (textLayoutList.size() > list.size()) {
                                    textLayoutList.remove(list.size());
                                    flContainer.removeViewAt(list.size());
                                }

                            } else {
                                if (t1 == 20) {
                                    textLayout.setState(!nvb.isSelected());
                                } else {
                                    textLayoutList.get(t1).setState(false);
                                    textLayout.setState(!nvb.isSelected());
                                }

                            }
                            int t = 0;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getName().equals("其他")) {
                                    t = i;
                                }
                            }
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getName().equals("其他残疾")) {
                                    t = i;
                                }
                            }
                            if (finalI == t && !(t == 0)) {
                                if (nvb.isSelected()) {
                                    final PerSonalTextLayout textLayout = new PerSonalTextLayout(getContext(), new NameValueBean("输入框", "", false), isSingleSelect);
                                    FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
                                    layoutParams.setMargins(6, 6, 6, 6);
                                    textLayout.setLayoutParams(layoutParams);
                                    textLayout.setPadding(6, 6, 6, 6);
                                    textLayout.setGravity(Gravity.CENTER);
                                    textLayoutList.add(textLayout);
                                    flContainer.addView(textLayout);
                                } else {
                                    if (textLayoutList.size() > (list.size())) {
                                        textLayoutList.remove(list.size());
                                        flContainer.removeViewAt(list.size());
                                    }
                                }

                            } else {
//                                if (textLayoutList.size()>(list.size())){
//                                    textLayoutList.remove(list.size());
//                                    flContainer.removeViewAt(list.size());
//                                }
                            }

//                            for (int i=0;i<list.size();i++){
//                                if (list.get(i).getName().equals("其他")||list.get(i).getName().equals("有")){
//                                    if (list.get(i).isSelected()){
////                                        textLayoutList.get(textLayoutList.size()-1).setvisibale("false");
////                                        et_input.setVisibility(View.VISIBLE);
//                                    }else {
////                                        textLayoutList.get(textLayoutList.size()-1).setvisibale("true");
//
//                                    }
//                                }
//                            }
//                            textLayout.get
//                            if (list.get(0).isSelected()){
//                                textLayout.setState(false);
//
//                            }else {
//
//                                int flage=0;
//                                for (int t=1;t<list.size();t++){
//                                    if (list.get(t).isSelected()){
//                                            if (finalI ==0){
//                                                flage=1;
//
//                                            }
//                                    }
//                                }
//                                if (flage==1){
//                                    textLayout.setState(false);
//                                }else {
//                                    textLayout.setState(!nvb.isSelected());
//                                }
//
//
//                            }

                        }
                    }
                });

                textLayoutList.add(textLayout);

                flContainer.addView(textLayout);
            }


        }
    }

    private void clearTextLayoutListState() {
        for (int i = 0; i < textLayoutList.size(); ++i) {
            PerSonalTextLayout tl = textLayoutList.get(i);
            tl.setState(false);
        }
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBean nvb = optionList.get(i);
            nvb.setSelected(false);
        }

    }


    private void showOrHideNecessity() {
        int visibility = INVISIBLE;
        if (isCompulsory) {
            visibility = VISIBLE;
        } else {
            visibility = INVISIBLE;
        }
        ivNecessity.setVisibility(visibility);
    }

    private void showOrHideName() {
        int visibility = INVISIBLE;
        if (isNameVisible) {
            visibility = VISIBLE;
        } else {
            visibility = INVISIBLE;
        }
//        tvName.setVisibility(visibility);
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

    public NameValueBean getSelectBean() {
        int selectedIndex = -1;
        NameValueBean selectedNvb = null;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex != -1) {
            selectedNvb = optionList.get(selectedIndex);
            result = selectedNvb.getName();
        }
        return selectedNvb;
    }

    public String getSingleSelectName() {
        int selectedIndex = -1;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex != -1) {
            NameValueBean selectedNvb = optionList.get(selectedIndex);
            result = selectedNvb.getName();
        }
        return result;
    }

    public String getSingleSelectValue() {
        int selectedIndex = -1;
        selectedIndex = getSelectedIndex();
        String result = Constant.EMPTY_STRING;
        if (selectedIndex != -1) {
            NameValueBean selectedNvb = optionList.get(selectedIndex);
            result = selectedNvb.getValue();
        }
        return result;
    }

    public String getSelectValue() {
        int selectedIndex = -1;
        String result = Constant.EMPTY_STRING;


//        if (selectedIndex!=-1){
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBean nvb = optionList.get(i);
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

    public String getSelectName() {
        int selectedIndex = -1;
        String result = Constant.EMPTY_STRING;


//        if (selectedIndex!=-1){
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBean nvb = optionList.get(i);
            if (nvb.isSelected()) {
                if (result.equals(Constant.EMPTY_STRING)) {
                    result = nvb.getName();
                } else {
                    result = result + "," + nvb.getName();
                }


            }
        }
//        }
        return result;
    }

    public String getTitleString() {
        String s = Util.trimString(name);
        return s;
    }

    private int getSelectedIndex() {
        int selectedIndex = -1;
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBean nvb = optionList.get(i);
            if (nvb.isSelected()) {
                selectedIndex = i;
                break;
            }
        }
        return selectedIndex;
    }

    public String getEditInputText() {
        String s = et_input.getText().toString();
        s = Util.trimString(s);
        return s;
    }

    public boolean isValid() {
        if (isSingleSelect) {
            String singleSelectValue = getSingleSelectValue();
            boolean isValid = !StringUtils.isBlank(singleSelectValue);
            return isValid;
        } else {
            return true;
        }
    }

    public void setSingleSelectText(String name) {
        name = Util.trimString(name);
        for (int i = 0; i < optionList.size(); ++i) {
            NameValueBean nvb = optionList.get(i);
            if (name.equals(nvb.getName())) {
                clearTextLayoutListState();
                PerSonalTextLayout textLayout = textLayoutList.get(i);
                nvb.setSelected(true);
                textLayout.setState(true);
                break;
            }
        }

    }
}
