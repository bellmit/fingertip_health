package com.jqsoft.fingertip_health.view.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.rx.RxBus;

/**
 * Created by Administrator on 2018-01-26.
 */

public class TouchToDrawCircleView extends LinearLayout {
    public static final int MAP_DRAW_TO_SEARCH_CIRCLE_COLOR= Color.argb(120, 182, 209, 238);

    private PointF centerPoint, outerPoint;
    private double radius;

    Paint paint;

    public TouchToDrawCircleView(Context context) {
        super(context);
        init();
    }

    public TouchToDrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setWillNotDraw(false);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(MAP_DRAW_TO_SEARCH_CIRCLE_COLOR);
        paint.setDither(true);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility==GONE){
            centerPoint=null;
            outerPoint = null;
            postInvalidate();
        }
    }

    private double calculateRadius(PointF center, PointF outer){
        double distance = Math.sqrt(Math.pow(center.x - outer.x, 2) + Math.pow(center.y - outer.y, 2));
        return distance;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                centerPoint=new PointF(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                outerPoint = new PointF(x, y);
                radius = calculateRadius(centerPoint, outerPoint);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (centerPoint!=null && outerPoint!=null) {
                    double [] doubleArray = new double[]{centerPoint.x, centerPoint.y, outerPoint.x, outerPoint.y};
                    RxBus.getDefault().post(Constants.EVENT_TYPE_MAP_TOUCH_TO_DRAW_CIRCLE_DID_FINISH, doubleArray);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (centerPoint!=null) {
            canvas.save();
            canvas.drawCircle(centerPoint.x, centerPoint.y, (float) radius, paint);
            canvas.restore();
        }
    }
}
