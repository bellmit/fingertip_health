package com.jqsoft.fingertip_health.optionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.luck.picture.lib.widget.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2018-05-22.
 */
public class HbInputLayout extends LinearLayout {
//    @BindView(R.id.iv_necessity)
//    ImageView ivNecessity;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name_right)
    TextView tv_name_right;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.icon)
     TextView icon;
//    @BindView(R.id.view_underline)
//    View viewUnderline;
//    @BindView(R.id.iv_trailing_icon)
//    ImageView ivTrailingIcon;

    boolean isCompulsory;
    String name,name_right;
    boolean isNameVisible;
    String inputPlaceholder;
    boolean isInputEditable=true;
    boolean isInputUnderlineVisible = true;
    int trailingIcon;
    boolean isTrailingIconVisible;

    public HbInputLayout(Context context) {
        super(context);
    }

    public HbInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.hb_eidtext_layout, this);
        ButterKnife.bind(this);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.HbInputLayout);
        isCompulsory=attributes.getBoolean(R.styleable.HbInputLayout_is_compulsory, false);
        name=attributes.getString(R.styleable.HbInputLayout_name_text);
        isNameVisible=attributes.getBoolean(R.styleable.HbInputLayout_is_name_visible, true);
        inputPlaceholder=attributes.getString(R.styleable.HbInputLayout_edit_text_placeholder);
        isInputEditable=attributes.getBoolean(R.styleable.HbInputLayout_is_input_editable, true);
        isInputUnderlineVisible=attributes.getBoolean(R.styleable.HbInputLayout_is_input_underline_visible, true);
        trailingIcon=attributes.getResourceId(R.styleable.HbInputLayout_trailing_icon, R.mipmap.i_select_date);
        isTrailingIconVisible=attributes.getBoolean(R.styleable.HbInputLayout_is_trailing_icon_visible, false);
        name_right=attributes.getString(R.styleable.HbInputLayout_name_right_text);


        attributes.recycle();

        init();
    }

    private void init(){
//        showOrHideNecessity();

        tvName.setText(name);
        tv_name_right.setText(name_right);
        showOrHideName();
        if (isTrailingIconVisible){
            icon.setVisibility(VISIBLE);

        }else {
            icon.setVisibility(INVISIBLE);
        }
        setEditInputEditable();
//        showOrHideInputUnderline();
        setEditInputPlaceholder();

//        showOrHideTrailingIcon();

    }

    private void setEditInputEditable(){
        etInput.setEnabled(isInputEditable);
    }

//    private void showOrHideInputUnderline(){
//        int visibility = VISIBLE;
//        if (isInputUnderlineVisible){
//            visibility=VISIBLE;
//        } else {
//            visibility=INVISIBLE;
//        }
//        viewUnderline.setVisibility(visibility);
//    }

    private void setEditInputPlaceholder() {
        etInput.setHint(inputPlaceholder);
    }

//    private void showOrHideNecessity() {
//        int visibility = INVISIBLE;
//        if (isCompulsory){
//            visibility=VISIBLE;
//        } else {
//            visibility=INVISIBLE;
//        }
//        ivNecessity.setVisibility(visibility);
//    }
    public   void  setEtInputType(String type){
        if (type.equals("number")){
            etInput.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

    }

    public   void  setIconVisible(String str){
       if (str.equals("true")){
           icon.setVisibility(View.VISIBLE);

       }else if (str.equals("false")){
           icon.setVisibility(View.GONE);

       }



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

//    private void showOrHideTrailingIcon() {
//        int visibility = INVISIBLE;
//        if (isTrailingIconVisible){
//            visibility=VISIBLE;
//        } else {
//            visibility=GONE;
//        }
//        ivTrailingIcon.setVisibility(visibility);
//        ivTrailingIcon.setImageResource(trailingIcon);
//    }

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
            Util.setViewListener(etInput, runnable);
        }
    }
}
