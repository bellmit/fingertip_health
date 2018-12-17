package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Locale;


public class SignServiceIncomePerCategoryContent {
    private Context context;
    private View view;
    public SignServiceIncomePerCategoryContent(Context context) {
        this.context=context;
    }

    public void initView(String type, String money, String hint){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View rootView = inflater.inflate(R.layout.item_sign_service_income_per_category, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        int imageId = getImageResourceIdFromType(type);
        String formattedMoney = getSignedMoneyFromString(money);

        ImageView ivImage = (ImageView) rootView.findViewById(R.id.iv_image);
        TextView tvMoney = (TextView) rootView.findViewById(R.id.tv_money);
        TextView tvHint = (TextView)rootView.findViewById(R.id.tv_hint);
        ivImage.setImageResource(imageId);
        tvMoney.setText(formattedMoney);
        tvHint.setText(hint);

        view=rootView;
    }

    public int getImageResourceIdFromType(String type){
        if (Constants.SIGN_SERVICE_INCOME_ITEM_TYPE_TOTAL.equals(type)){
            return R.mipmap.i_rmb;
        } else {
            return R.mipmap.i_money;
        }
    }

    public String getSignedMoneyFromString(String s){
        float f = Util.getFloatFromString(s);
        String result = String.format(Locale.CHINA, "%+.2f元", f);
        return result;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
