package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;


public class PersonLocationAdapter extends BaseQuickAdapterEx<PersonLocationBean, BaseViewHolder> {
    private Context context;
    public PersonLocationAdapter(Context context, List<PersonLocationBean> data) {
        super(R.layout.item_person_location, data);
        this.context = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final PersonLocationBean item) {
        helper.setText(R.id.tv_name, item.getName());
        String address = item.getAddress();
        helper.setText(R.id.tv_address, address);
        if (StringUtils.isBlank(address)){
            helper.setVisible(R.id.tv_address, false);
        } else {
            helper.setVisible(R.id.tv_address, true);
        }
        final TextView phoneView = (TextView)helper.getView(R.id.tv_telephone);
        final String phone = item.getPhone();
        String hint = Constants.EMPTY_STRING;
        if (StringUtils.isBlank(phone) || "无".equals(phone)){
            hint="无";
            RxView.clicks(phoneView)
                    .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Object o) {
                            Util.showToast(context, "电话号码为空");
                        }
                    });
        } else {
            hint=phone;
            Spanned spanned = Html.fromHtml(Util.getUnderlinedString(hint));
            phoneView.setText(spanned);
            RxView.clicks(phoneView)
                    .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Object o) {
                            Util.dial(phoneView.getContext(), phone);
                        }
                    });

        }
        helper.setText(R.id.tv_telephone, hint);
        ImageView naviImageView = helper.getView(R.id.iv_navi);
        RxView.clicks(naviImageView)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        startAMapNavi(item);
                    }
                });
    }

    private void startAMapNavi(PersonLocationBean plb) {
        MapServiceActivity activity = null;
        if (context instanceof MapServiceActivity){
            activity=(MapServiceActivity) context;
            activity.startNavi(plb);
        }
    }

}
