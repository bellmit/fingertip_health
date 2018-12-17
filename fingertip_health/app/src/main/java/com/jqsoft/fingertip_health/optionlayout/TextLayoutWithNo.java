package com.jqsoft.fingertip_health.optionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;


/**
 * Created by Administrator on 2018-01-18.
 */

public class TextLayoutWithNo extends LinearLayout {
    private TextView tvName;
    private EditText editText;
    private LinearLayout line1;
    private int normalBackgroundResource, hilightBackgroundResource;
    private int normalTextColor, hilightTextColor;
    private String text;

    private boolean isHilighted = false;
    boolean isSingleSelect=true;

    NameValueBeanWithNo bean;
    public Boolean isSelect(){
        return isSingleSelect;
    }
    public TextLayoutWithNo(Context context, NameValueBeanWithNo bean, boolean isSingleSelect) {
        super(context);
        this.bean = bean;

            normalBackgroundResource= R.drawable.text_layout_normal_background;
            hilightBackgroundResource=R.drawable.text_layout_hilight_background;
            normalTextColor=getResources().getColor(R.color.black);
            hilightTextColor=getResources().getColor(R.color.white);




        this.isSingleSelect=isSingleSelect;
        initLayoutAndState(context);
    }
    public EditText getEditText(){
        return editText;
    }
    public TextLayoutWithNo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextLayout);
        if (bean.getName().equals("输入框"))
        {

        }else {
            normalBackgroundResource = typedArray.getResourceId(R.styleable.TextLayout_normal_background_color, R.drawable.text_layout_normal_background);
            hilightBackgroundResource = typedArray.getResourceId(R.styleable.TextLayout_hilight_background_color, R.drawable.text_layout_hilight_background);
            normalTextColor = typedArray.getColor(R.styleable.TextLayout_normal_text_color, getResources().getColor(R.color.black));
            hilightTextColor = typedArray.getColor(R.styleable.TextLayout_hilight_text_color, getResources().getColor(R.color.white));
            text = typedArray.getString(R.styleable.TextLayout_text_string);
            isSingleSelect = typedArray.getBoolean(R.styleable.TextLayout_is_single_select, true);
        }typedArray.recycle();

        initLayoutAndState(context);
    }
    public void setvisibale(String v){
        if (v.equals("true")){
            line1.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
        }else {
            line1.setVisibility(View.GONE);
        }

//
    }
    private void initLayoutAndState(Context context) {
        View.inflate(context, R.layout.text_layout_witheid, this);

        if (bean!=null){
            text=bean.getName();
        }

        tvName = (TextView) findViewById(R.id.tv_text);
        editText = (EditText) findViewById(R.id.editText);
        line1=(LinearLayout)findViewById(R.id.line1);

        setState(bean.isSelected());

//        Util.setViewListener(this, new Runnable() {
//            @Override
//            public void run() {
//                bean.setSelected(!bean.isSelected());
//                setState();
//            }
//        });
    }

    private void init(){
    }

    public void setState(boolean isSelected) {

        if (bean.getName().equals("输入框")){

            setBackgroundResource(normalBackgroundResource);
            tvName.setVisibility(View.GONE);
            editText.setVisibility(VISIBLE);
            line1.setPadding(0,0,0,0);

        }else {

            bean.setSelected(isSelected);
            if (!isSelected) {
                setBackgroundResource(normalBackgroundResource);
                tvName.setText(text);
                tvName.setTextColor(normalTextColor);
                setHilighted(false);
            } else {
                setBackgroundResource(hilightBackgroundResource);
                tvName.setText(text);
                tvName.setTextColor(hilightTextColor);
                setHilighted(true);
            }
        }

    }


    public boolean isHilighted() {
        return isHilighted;
    }

    public void setHilighted(boolean hilighted) {
        isHilighted = hilighted;
    }
}
