package com.jqsoft.fingertip_health.util;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;

public class InputDialog {
    private Context mContext;
    private Dialog mDialog;
    private LinearLayout mLayout;
    private TextView mTitle;
    private EditText mContentInput;
    private View mDividerLine;
    private Button mPositiveBtn;
    private Button mNegativeBtn;

    private boolean mIsShowTitle = false;
    private boolean mIsShowContentInput = false;
    private boolean mIsShowPositiveBtn = false;
    private boolean mIsShowNegativeBtn = false;

    public InputDialog(Context context) {
        this.mContext = context;
    }

    public InputDialog builder() {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.dialog_input, null);

        mLayout = (LinearLayout) view.findViewById(R.id.dialog_input_layout);
        mTitle = (TextView) view.findViewById(R.id.dialog_input_title);
        mContentInput = (EditText) view.findViewById(R.id.dialog_input_content_input);
        mDividerLine = view.findViewById(R.id.dialog_input_divider_line);
        mPositiveBtn = (Button) view.findViewById(R.id.dialog_input_positive_btn);
        mNegativeBtn = (Button) view.findViewById(R.id.dialog_input_negative_btn);

        mTitle.setVisibility(View.GONE);
        mContentInput.setVisibility(View.GONE);
        mDividerLine.setVisibility(View.GONE);
        mPositiveBtn.setVisibility(View.GONE);
        mNegativeBtn.setVisibility(View.GONE);

        int screenWidth = ((WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mDialog = new Dialog(mContext, R.style.TipsDialog);
        mDialog.setContentView(view);
        mDialog.getWindow().setLayout((int)(screenWidth * 0.8f), ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);

        setCancelable(true);
        setCanceledOnTouchOutside(false);

        return this;
    }

    /**
     * ????????????
     * @param title
     * @return
     */
    public InputDialog setTitle(String title) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(title)) {
            mTitle.setText("??????");
        } else {
            mTitle.setText(title);
        }
        return this;
    }

    public InputDialog setTitle(int titleResId) {
        mIsShowTitle = true;
        mTitle.setText(titleResId);
        return this;
    }

    /**
     * ????????????
     * @param msg
     * @return
     */
    public InputDialog setContentMsg(String msg) {
        mIsShowContentInput = true;
        mContentInput.setText(msg);
        if (!TextUtils.isEmpty(msg)) {
            mContentInput.setSelection(msg.length());
        }
        return this;
    }

    public InputDialog setContentMsg(int msgResId) {
        setContentMsg(mContext.getString(msgResId));
        return this;
    }

    public InputDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public InputDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * ??????????????????
     * @param text
     * @param onPositiveListener
     * @return
     */
    public InputDialog setPositiveBtn(String text, final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("??????");
        } else {
            mPositiveBtn.setText(text);
        }
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v, mContentInput.getText().toString());
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * ??????????????????
     * @param text
     * @param onNegativeListener
     * @return
     */
    public InputDialog setNegativeBtn(String text, final OnNegativeListener onNegativeListener) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("??????");
        } else {
            mNegativeBtn.setText(text);
        }
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative(v,  mContentInput.getText().toString());
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        // ??????????????????????????????
        boolean isSetAnyComponent = false;

        /**
         * ????????????
         */
        if (mIsShowTitle) {
            isSetAnyComponent = true;
            mTitle.setVisibility(View.VISIBLE);
        }

        /**
         * ????????????
         */
        if (mIsShowContentInput) {
            isSetAnyComponent = true;
            mContentInput.setVisibility(View.VISIBLE);
        }

        /**
         * ??????????????????
         */
        if (mIsShowPositiveBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);
            mDividerLine.setVisibility(View.VISIBLE);
            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);
        }

        /**
         * ??????????????????
         */
        if (mIsShowPositiveBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * ??????????????????
         */
        if (!mIsShowPositiveBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * ???????????????????????????????????????????????????
         */
        if (!isSetAnyComponent) {
            mTitle.setText("?????????????????????!");
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    public void show() {
        setLayout();
        mDialog.show();
        // ?????????????????????????????????????????????????????????????????????????????????
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // ????????????????????????????????????????????????????????????
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /** start----------------------------------------- */
    public InputDialog setTitleText(String text) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(text)) {
            mTitle.setText("??????");
        } else {
            mTitle.setText(text);
        }
        return this;
    }

    public InputDialog setTitleText(int textId) {
        mIsShowTitle = true;
        mTitle.setText(textId);
        return this;
    }

    public InputDialog setTitleTextColor(int colorId) {
        mIsShowTitle = true;
        mTitle.setTextColor(colorId);
        return this;
    }

    public InputDialog setTitleTextSize(int textSize) {
        mIsShowTitle = true;
        mTitle.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public InputDialog setContentMsgText(String text) {
        mIsShowContentInput = true;
        if (TextUtils.isEmpty(text)) {
            mContentInput.setText("??????");
        } else {
            mContentInput.setText(text);
        }
        return this;
    }

    public InputDialog setContentMsgText(int textId) {
        mIsShowContentInput = true;
        mContentInput.setText(textId);
        return this;
    }

    public InputDialog setContentMsgTextColor(int colorId) {
        mIsShowContentInput = true;
        mContentInput.setTextColor(colorId);
        return this;
    }

    public InputDialog setContentMsgTextSize(int textSize) {
        mIsShowContentInput = true;
        mContentInput.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public InputDialog setNegativeBtnText(String text) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("??????");
        } else {
            mNegativeBtn.setText(text);
        }
        return this;
    }

    public InputDialog setNegativeBtnText(int textId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setText(textId);
        return this;
    }

    public InputDialog setNegativeBtnTextColor(int colorId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextColor(colorId);
        return this;
    }

    public InputDialog setNegativeBtnTextSize(int textSize) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextSize(textSize);
        return this;
    }

    public InputDialog setNegativeBtnListener(final OnNegativeListener onNegativeListener) {
        mIsShowPositiveBtn = true;
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative(v, mContentInput.getText().toString());
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public InputDialog setPositiveBtnText(String text) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("??????");
        } else {
            mPositiveBtn.setText(text);
        }
        return this;
    }

    public InputDialog setPositiveBtnText(int textId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setText(textId);
        return this;
    }

    public InputDialog setPositiveBtnTextColor(int colorId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextColor(colorId);
        return this;
    }

    public InputDialog setPositiveBtnTextSize(int textSize) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextSize(textSize);
        return this;
    }

    public InputDialog setPositiveBtnListener(final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v, mContentInput.getText().toString());
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */
    
    public TextView getTitleView() {
        return this.getTitleView();
    }

    public EditText getContentView() {
        return this.mContentInput;
    }

    public Button getPositiveBtn() {
        return this.mPositiveBtn;
    }

    public Button getNegativeBtn() {
        return this.mNegativeBtn;
    }

    public interface OnPositiveListener {
        public void onPositive(View view, String inputMsg);
    }

    public interface OnNegativeListener {
        public void onNegative(View view, String inputMsg);
    }
}
