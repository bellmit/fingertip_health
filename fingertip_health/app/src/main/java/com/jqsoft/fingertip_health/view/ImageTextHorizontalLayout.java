package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;

import java.util.concurrent.TimeUnit;

import rx.Observer;

/**
 * Created by Administrator on 2018-01-18.
 */

public class ImageTextHorizontalLayout extends LinearLayout {
    private ImageView ivImage;
    private TextView tvName;

    private int normalImageId;
    private String text;

    private ClickListener clickListener;


    public ImageTextHorizontalLayout(Context context) {
        super(context);
    }

    public ImageTextHorizontalLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextHorizontalLayout);
        normalImageId = typedArray.getResourceId(R.styleable.ImageTextHorizontalLayout_normal_image_id, R.mipmap.g_ranking_statistics);
        text = typedArray.getString(R.styleable.ImageTextHorizontalLayout_text_string);
        typedArray.recycle();

        View.inflate(context, R.layout.layout_image_and_text_horizontal, this);

        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvName = (TextView) findViewById(R.id.tv_title);

        setNormalState();
        init();
    }

    public void setNormalImageId(int normalImageId){
        this.normalImageId = normalImageId;
        ivImage.setImageResource(normalImageId);
    }

    public void setTitleText(String text){
        this.text = text;
        tvName.setText(text);
    }

    public void setNormalState() {
        ivImage.setImageResource(normalImageId);
        tvName.setText(text);
    }

    private void init(){
        RxView.clicks(this)
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
                        if (clickListener!=null){
                            clickListener.layoutDidClick();
                        }
                    }
                });
    }

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        public void layoutDidClick();
    }

}
