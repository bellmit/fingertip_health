package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;

/**
 * Created by Administrator on 2018-01-18.
 */

public class ImageTextLayout extends LinearLayout {
    private ImageView ivImage;
    private TextView tvName;

    private int normalImageId, hilightImageId;
    private int normalTextColor, hilightTextColor;
    private String text;

    private boolean isHilighted = false;

    public ImageTextLayout(Context context) {
        super(context);
    }

    public ImageTextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextLayout);
        normalImageId = typedArray.getResourceId(R.styleable.ImageTextLayout_normal_image_id, R.mipmap.g_map_ambient_n);
        hilightImageId = typedArray.getResourceId(R.styleable.ImageTextLayout_hilight_image_id, R.mipmap.g_map_ambient_h);
        normalTextColor = typedArray.getColor(R.styleable.ImageTextLayout_normal_text_color, getResources().getColor(R.color.list_item_sub_text_color));
        hilightTextColor = typedArray.getColor(R.styleable.ImageTextLayout_hilight_text_color, getResources().getColor(R.color.colorTheme));
        text = typedArray.getString(R.styleable.ImageTextLayout_text_string);
        typedArray.recycle();

        View.inflate(context, R.layout.map_service_icon_text_layout, this);

        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvName = (TextView) findViewById(R.id.tv_text);

        setNormalState();
    }

    public void setNormalState() {
        ivImage.setImageResource(normalImageId);
        tvName.setText(text);
        tvName.setTextColor(normalTextColor);
        setHilighted(false);
    }

    public void setHilightState(){
        ivImage.setImageResource(hilightImageId);
        tvName.setText(text);
        tvName.setTextColor(hilightTextColor);
        setHilighted(true);
    }

    public boolean isHilighted() {
        return isHilighted;
    }

    public void setHilighted(boolean hilighted) {
        isHilighted = hilighted;
    }
}
