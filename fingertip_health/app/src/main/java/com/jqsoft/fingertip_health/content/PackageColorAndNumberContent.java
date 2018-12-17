package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;

//各级别包数
public class PackageColorAndNumberContent {
    public static final int RADIUS = 15;

    private Context context;
    private View view;
    private int color;
    private String packageName;
    private String packageNumber;
    public PackageColorAndNumberContent(Context context) {
        this.context=context;
    }

    public void initView(int color, String packageName, String packageNumber){
        this.color=color;
        this.packageName=packageName;
        this.packageNumber=packageNumber;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View rootView = inflater.inflate(R.layout.layout_package_color_and_number, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(params);

        ImageView ivColor = (ImageView) rootView.findViewById(R.id.iv_color);
        TextView tvText = (TextView) rootView.findViewById(R.id.tv_text);
        Drawable drawable = getDrawable();
        ivColor.setBackground(drawable);
        String title = getTitle();
        tvText.setText(title);

        view=rootView;
    }

    public Drawable getDrawable(){
        int radius = RADIUS;
        float[] outerR = new float[] { radius, radius, radius, radius,radius, radius, radius, radius };
        RoundRectShape roundRectShape = new RoundRectShape(outerR,null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape); //组合圆角矩形和ShapeDrawable
        shapeDrawable.getPaint().setColor(color);       //设置形状的颜色
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);  // 设置绘制方式为填充
        return shapeDrawable;
    }

    public String getTitle(){
        return packageName+Constants.COLON_STRING+packageNumber+Constants.PERSON;
    }


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
