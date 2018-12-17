package com.jqsoft.fingertip_health.optionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;


/**
 * Created by Administrator on 2018-01-18.
 */

public class TextLayout extends LinearLayout {
    private TextView tvName;

    private int normalBackgroundResource, hilightBackgroundResource;
    private int normalTextColor, hilightTextColor;
    private String text;

    private boolean isHilighted = false;
    boolean isSingleSelect=true;

    NameValueBean bean;

    public TextLayout(Context context, NameValueBean bean, boolean isSingleSelect) {
        super(context);
        this.bean = bean;
        normalBackgroundResource=R.drawable.text_layout_normal_background;
        hilightBackgroundResource= R.drawable.text_layout_hilight_background;
        normalTextColor=getResources().getColor(R.color.black);
        hilightTextColor=getResources().getColor(R.color.white);

        this.isSingleSelect=isSingleSelect;

        initLayoutAndState(context);
    }

    public TextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextLayout);
            normalBackgroundResource = typedArray.getResourceId(R.styleable.TextLayout_normal_background_color, R.drawable.text_layout_normal_background);
            hilightBackgroundResource = typedArray.getResourceId(R.styleable.TextLayout_hilight_background_color, R.drawable.text_layout_hilight_background);
            normalTextColor = typedArray.getColor(R.styleable.TextLayout_normal_text_color, getResources().getColor(R.color.black));
            hilightTextColor = typedArray.getColor(R.styleable.TextLayout_hilight_text_color, getResources().getColor(R.color.white));
            text = typedArray.getString(R.styleable.TextLayout_text_string);
            isSingleSelect = typedArray.getBoolean(R.styleable.TextLayout_is_single_select, true);
            typedArray.recycle();

        initLayoutAndState(context);
    }

    private void initLayoutAndState(Context context) {
        View.inflate(context, R.layout.text_layout, this);

        if (bean!=null){
            text=bean.getName();
        }

        tvName = (TextView) findViewById(R.id.tv_text);

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


    public boolean isHilighted() {
        return isHilighted;
    }

    public void setHilighted(boolean hilighted) {
        isHilighted = hilighted;
    }
}
