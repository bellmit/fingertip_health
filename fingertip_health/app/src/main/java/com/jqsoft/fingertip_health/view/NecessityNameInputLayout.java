package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.RegexUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2018-05-22.
 */

public class NecessityNameInputLayout extends LinearLayout {
    @BindView(R.id.iv_necessity)
    ImageView ivNecessity;
    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.view_underline)
    View viewUnderline;
    @BindView(R.id.iv_trailing_icon)
    ImageView ivTrailingIcon;

    boolean isCompulsory;
    String name;
    boolean isNameVisible;
    String inputPlaceholder;
    boolean isInputEditable=true;
    boolean isInputUnderlineVisible = true;
    int trailingIcon;
    boolean isTrailingIconVisible;

    public NecessityNameInputLayout(Context context) {
        super(context);
    }

    public NecessityNameInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.layout_necessity_name_input, this);
        ButterKnife.bind(this);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.NecessityNameInputLayout);
        isCompulsory=attributes.getBoolean(R.styleable.NecessityNameInputLayout_is_compulsory, false);
        name=attributes.getString(R.styleable.NecessityNameInputLayout_name_text);
        isNameVisible=attributes.getBoolean(R.styleable.NecessityNameInputLayout_is_name_visible, true);
        inputPlaceholder=attributes.getString(R.styleable.NecessityNameInputLayout_edit_text_placeholder);
        isInputEditable=attributes.getBoolean(R.styleable.NecessityNameInputLayout_is_input_editable, true);
        isInputUnderlineVisible=attributes.getBoolean(R.styleable.NecessityNameInputLayout_is_input_underline_visible, true);
        trailingIcon=attributes.getResourceId(R.styleable.NecessityNameInputLayout_trailing_icon, R.mipmap.i_select_date);
        isTrailingIconVisible=attributes.getBoolean(R.styleable.NecessityNameInputLayout_is_trailing_icon_visible, false);
        attributes.recycle();

        init();
    }

    private void init(){
        showOrHideNecessity();

        tvName.setText(name);
        showOrHideName();

        setEditInputEditable();
        showOrHideInputUnderline();
        setEditInputPlaceholder();

        showOrHideTrailingIcon();

    }

    private void setEditInputEditable(){
        etInput.setEnabled(isInputEditable);
    }

    private void showOrHideInputUnderline(){
        int visibility = VISIBLE;
        if (isInputUnderlineVisible){
            visibility=VISIBLE;
        } else {
            visibility=INVISIBLE;
        }
        viewUnderline.setVisibility(visibility);
    }

    private void setEditInputPlaceholder() {
        etInput.setHint(inputPlaceholder);
    }

    private void showOrHideNecessity() {
        int visibility = INVISIBLE;
        if (isCompulsory){
            visibility=VISIBLE;
        } else {
            visibility=INVISIBLE;
        }
        ivNecessity.setVisibility(visibility);
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

    private void showOrHideTrailingIcon() {
        int visibility = INVISIBLE;
        if (isTrailingIconVisible){
            visibility=VISIBLE;
        } else {
            visibility=GONE;
        }
        ivTrailingIcon.setVisibility(visibility);
        ivTrailingIcon.setImageResource(trailingIcon);
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(boolean compulsory) {
        isCompulsory = compulsory;
    }

    public String getInputPlaceholder() {
        return inputPlaceholder;
    }

    public void setInputPlaceholder(String inputPlaceholder) {
        this.inputPlaceholder = inputPlaceholder;
        setEditInputPlaceholder();
    }

    public EditText getEditInput() {
        return etInput;
    }

    public String getEditInputText(){
        String s = etInput.getText().toString();
        s= Util.trimString(s);
        return s;
    }

    public void setEditInputText(String s){
        s= Util.trimString(s);
        etInput.setText(s);
    }

    public boolean isInputTextValid(){
        String s = getEditInputText();
        boolean isValid = !StringUtils.isBlank(s);
        return isValid;
    }

    public boolean isInputPhoneValid(){
        String s = getEditInputText();
        int length = StringUtils.length(s);
        if (length < 7|| length > 12){
            return false;
        }
        boolean isValid = RegexUtil.checkPhone(s);
        return isValid;
    }

    public boolean isInputIdCardNumberValid(){
        String s = getEditInputText();
        int length = StringUtils.length(s);
        if (length < 15|| length >18){
            return false;
        }
        boolean isValid = RegexUtil.checkIdCard(s);
        return isValid;
    }

    public void setTextBlurRunnable(final Runnable runnable){
        if (runnable!=null){
            EditText et = getEditInput();
            et.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        runnable.run();
                    }
                }
            });
        }
    }

    public void setTrailingIconClickListener(Runnable runnable){
        if (runnable!=null){
            Util.setViewListener(ivTrailingIcon, runnable);
        }
    }
}
