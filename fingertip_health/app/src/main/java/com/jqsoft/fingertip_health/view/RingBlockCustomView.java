package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.feature.NumberAndPercentInterface;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

//环状区块视图
public class RingBlockCustomView extends View implements OnTouchListener {

    private static final int IMAGE_WIDTH = 60;
    private static final int IMAGE_HEIGHT = 60;
    private static final double OFFSET = 3;
    private static final double SELECTED_OFFSET = 14;
    private static final int IMAGE_AND_TEXT_OFFSET = 8;
    private static final int NUMBER_TEXT_SIZE = 52;
    private static final int OTHER_TEXT_SIZE = 34;
    private static final int INNER_RADIUS_EXTRA_OFFSET = 20;
    private static final int ARROW_OFFSET_ANGLE = 7;
    private static final int ARROW_OFFSET_RADIUS = 38;
    private static final int ANIMATION_DEGREE_OFFSET = 10;
    private static final int ANIMATION_INTERVAL_TIME = 40;
    private int width;
    private int height;
    private int outerRadius;
    private int innerRadius;
    private List<Integer> imageIdList;
    private ArrayList<Path> pathList;
    private ArrayList<RectF> imageRectList;
    private ArrayList<RectF> unselectedTextRectList;
    private RectF innerNumberTextRect, innerHintTextRect, innerPercentTextRect;
    private float itemDegree;
    private ArrayList<Bitmap> bitmapList;
    private ArrayList<BitmapDrawable> drawableList;
    private Paint imagePaint;
    private ArrayList<BitmapShader> shaderList;
    private ArrayList<Bitmap> scaledBitmapList;
    private int previousDownTouchedIndex = 0;
    private int downTouchedIndex = 0;
    private float tempDegree=-90;
    private float animationEndDegree = -90;
    private boolean isInAnimation = false;
    private boolean isAnimationCW = true;
    private PorterDuffColorFilter dimColorFilter;
    private RingBlockViewListener listener;
    private List<String> titleList;
    private int unselectedTitleColor;
    private int selectedTitleColor;
    private int unselectedTitleBackgroundColor;
    private int selectedTitleBackgroundColor;
    private Paint unselectedTitlePaint;
    private Paint selectedTitlePaint;
    private Paint backgroundPaint;

    private List<NumberAndPercentInterface> beanList;

    public RingBlockCustomView(Context context, List<NumberAndPercentInterface> beanList,
                               int unselectedTitleColor, int selectedTitleColor, int unselectedTitleBackgroundColor,
                               int selectedTitleBackgroundColor,
                               RingBlockViewListener listener) {
        super(context);

        setWillNotDraw(false);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.beanList = beanList;
        this.titleList = titleList;
        this.unselectedTitleColor = unselectedTitleColor;
        this.selectedTitleColor = selectedTitleColor;
        this.unselectedTitleBackgroundColor = unselectedTitleBackgroundColor;
        this.selectedTitleBackgroundColor = selectedTitleBackgroundColor;
        this.imageIdList = new ArrayList<>();
        for (int i = 0; i < beanList.size(); ++i) {
            NumberAndPercentInterface item = beanList.get(i);
            int imageId = item.getImageId();
            imageIdList.add(imageId);
        }
        bitmapList = new ArrayList<Bitmap>();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        for (int i = 0; i < imageIdList.size(); i++) {
            int imageId = imageIdList.get(i);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    imageId, options);
            bitmapList.add(bitmap);
        }

        this.listener = listener;
        dimColorFilter = new PorterDuffColorFilter(Color.GRAY,
                PorterDuff.Mode.MULTIPLY);
        setOnTouchListener(this);
    }

    public RingBlockCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public RingBlockCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private String getHintFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        return beanList.get(index).getHint();
    }

    private String getNumberFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        return beanList.get(index).getNumber();
    }

    private String getPercentFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        return beanList.get(index).getPercent();
    }

    private String getHintWithSuffixFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        String hint = beanList.get(index).getHint();
        hint= Util.getSpecificRatioHint(hint);
        return  hint;
    }

    private String getNumberWithSuffixFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        return beanList.get(index).getNumber() + Constants.PERSON;
    }

    private String getPercentWithSuffixFromIndex(int index) {
        if (index < 0) {
            return "";
        }
        return beanList.get(index).getPercent() + Constants.PERCENTAGE_SIGN;
    }

    private int getImageIdFromIndex(int index) {
        return beanList.get(index).getImageId();
    }

    private android.os.Handler handler = new android.os.Handler();

    @Override
    protected void onDraw(Canvas canvas) {
//		LogUtil.i(
//				"chenxu",
//				"canvas.isHardwareAccelerated:"
//						+ canvas.isHardwareAccelerated());
        generatePath();

        backgroundPaint.setColor(unselectedTitleBackgroundColor);
        for (int i = 0; i < imageIdList.size(); i++) {
            Path path = pathList.get(i + 1);
            canvas.drawPath(path, backgroundPaint);
            RectF imageRect = imageRectList.get(i);
            Bitmap bitmap = bitmapList.get(i);
            RectF r = new RectF(imageRect.left, imageRect.top, imageRect.left + IMAGE_WIDTH, imageRect.top + IMAGE_HEIGHT);
            canvas.drawBitmap(bitmap, null, r, imagePaint);
            unselectedTitlePaint.setColor(unselectedTitleColor);
            RectF textRect = unselectedTextRectList.get(i);
            String text = getHintFromIndex(i);
            canvas.drawText(text, textRect.left, textRect.top, unselectedTitlePaint);
        }

        backgroundPaint.setColor(selectedTitleBackgroundColor);
        canvas.drawPath(pathList.get(0), backgroundPaint);
        String selectedNumber = getNumberWithSuffixFromIndex(downTouchedIndex);
        String selectedHint = getHintWithSuffixFromIndex(downTouchedIndex);
        String selectedPercent = getPercentWithSuffixFromIndex(downTouchedIndex);
        selectedTitlePaint.setTextSize(NUMBER_TEXT_SIZE);
        canvas.drawText(selectedNumber, innerNumberTextRect.left, innerNumberTextRect.top, selectedTitlePaint);
        selectedTitlePaint.setTextSize(OTHER_TEXT_SIZE);
        canvas.drawText(selectedHint, innerHintTextRect.left, innerHintTextRect.top, selectedTitlePaint);
        canvas.drawText(selectedPercent, innerPercentTextRect.left, innerPercentTextRect.top, selectedTitlePaint);


//        for (int i = 0; i < imageIdList.size(); i++) {
//            String title = null;
//            if (titleList != null) {
//                title = titleList.get(i);
//            }
//            Path path = pathList.get(i);
//            BitmapShader shader = shaderList.get(i);
//            imagePaint.setShader(shader);
//            if (i == downTouchedIndex) {
//                imagePaint.setColorFilter(dimColorFilter);
//            } else {
//                imagePaint.setColorFilter(null);
//            }
//            canvas.drawPath(path, imagePaint);
//            if (title != null) {
//                int textWidth = getOtherStringWidth(title);
//                int textHeight = getOtherStringHeight(title);
//                int centerX;
//                int centerY;
//                if (i == 0) {
//                    centerX = width / 2;
//                    centerY = height / 2;
//                } else {
//                    centerX = (int) (width / 2 + (outerRadius + innerRadius) / 2 * Math.cos(getCenterRadian(i)));
//                    centerY = (int) (height / 2 + (outerRadius + innerRadius) / 2 * Math.sin(getCenterRadian(i)));
//                }
//                canvas.drawText(title, centerX - textWidth / 2, centerY, unselectedTitlePaint);
//            }
//        }
    }

    public int getNumberStringWidth(String s) {
        Rect bounds = new Rect();
        selectedTitlePaint.setTextSize(NUMBER_TEXT_SIZE);
        selectedTitlePaint.getTextBounds(s, 0, s.length(), bounds);
        return bounds.width();
    }

    public int getNumberStringHeight(String s) {
        Rect bounds = new Rect();
        selectedTitlePaint.setTextSize(NUMBER_TEXT_SIZE);
        selectedTitlePaint.getTextBounds(s, 0, s.length(), bounds);
        return bounds.height();
    }

    public int getOtherStringWidth(String s) {
        Rect bounds = new Rect();
        unselectedTitlePaint.getTextBounds(s, 0, s.length(), bounds);
        return bounds.width();
    }

    public int getOtherStringHeight(String s) {
        Rect bounds = new Rect();
        unselectedTitlePaint.getTextBounds(s, 0, s.length(), bounds);
        return bounds.height();
    }

    private void processOnTouchEVent(float x, float y){
        downTouchedIndex = getTouchedIndex(x, y);
        if (downTouchedIndex != -1) {
            tempDegree = getAnimationStartDegree();
            animationEndDegree = getAnimationEndDegree();
            if (downTouchedIndex != previousDownTouchedIndex) {
                if (tempDegree<animationEndDegree){
                    if (animationEndDegree-tempDegree<=180){
                        isAnimationCW=true;
                    } else {
                        animationEndDegree-=360;
                        isAnimationCW=false;
                    }
                } else {
                    if (tempDegree-animationEndDegree<=180) {
                        isAnimationCW=false;
                    } else {
                        animationEndDegree+=360;
                        isAnimationCW=true;
                    }
                }
                isInAnimation = true;
            }
            postInvalidate();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isInAnimation){
            return true;
        }
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                processOnTouchEVent(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                processOnTouchEVent(x, y);
                break;
            case MotionEvent.ACTION_UP:
                processOnTouchEVent(x, y);
//                downTouchedIndex = getTouchedIndex(x, y);
//                if (downTouchedIndex != -1) {
//                    tempDegree = getAnimationStartDegree();
//                    if (downTouchedIndex != previousDownTouchedIndex) {
//                        isInAnimation = true;
//                    }
//                    if (listener != null) {
//                        listener.ringBlockViewDidClick(downTouchedIndex);
//                    }
//                    postInvalidate();
//                }
                break;
            default:
                break;
        }
        return false;
    }

    public int getTouchedIndex(float x, float y) {
        float distance = distanceToCenter(x, y);
        float angle = 0f;
        float radian = (float) Math.atan2(y - height / 2, x - width / 2);
        angle = (float) Math.toDegrees(radian);
        if (angle >= -180 && angle <= -135) {
            return 3;
        }
//        if (angle < 0) {
//            angle += 360;
//        }
        float itemRadian = (float) Math.toRadians(itemDegree);
        float halfItemRadian = itemRadian / 2;
        float xOffset = (float) (OFFSET * Math.cos(halfItemRadian));
        float yOffset = (float) (OFFSET * Math.sin(halfItemRadian));
        float offsetDistance = (float) Math.sqrt(Math.pow(xOffset, 2)
                + Math.pow(yOffset, 2));
        int index = -1;
        if (distance <= innerRadius) {
            index = 0;
        } else if (distance >= innerRadius + offsetDistance
                && distance <= outerRadius + offsetDistance) {
            for (int i = 0; i < imageIdList.size(); i++) {
                float startDegree = -90 + (i) * itemDegree - itemDegree / 2;
                float endDegree = -90 + (i) * itemDegree + itemDegree / 2;
                if (angle >= startDegree && angle < endDegree) {
                    index = i;
                    break;
                }
            }
        } else {
            index = -1;
        }
        return index;
    }

    public float distanceToCenter(float x, float y) {
        float powX = (float) Math.pow(x - width / 2, 2);
        float powY = (float) Math.pow(y - height / 2, 2);
        float distance = (float) Math.sqrt(powX + powY);
        return distance;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != 0 && h != 0) {
            width = w;
            height = h;

            outerRadius = (int) (Math.min(width, height) * 0.38f);
            innerRadius = (int) (Math.min(width, height) * 0.15f);

            itemDegree = (360.0f) / (imageIdList.size());

            imagePaint = new Paint();
            imagePaint.setAntiAlias(true);
            imagePaint.setDither(true);
            imagePaint.setStyle(Style.FILL_AND_STROKE);

            unselectedTitlePaint = new Paint();
            unselectedTitlePaint.setAntiAlias(true);
            unselectedTitlePaint.setDither(true);
            unselectedTitlePaint.setStyle(Style.FILL);
            unselectedTitlePaint.setColor(unselectedTitleColor);
            unselectedTitlePaint.setStrokeJoin(Join.ROUND);
            unselectedTitlePaint.setTextSize(OTHER_TEXT_SIZE);

            selectedTitlePaint = new Paint();
            selectedTitlePaint.setAntiAlias(true);
            selectedTitlePaint.setDither(true);
            selectedTitlePaint.setStyle(Style.FILL);
            selectedTitlePaint.setColor(selectedTitleColor);
            selectedTitlePaint.setStrokeJoin(Join.ROUND);
            selectedTitlePaint.setTextSize(NUMBER_TEXT_SIZE);


            backgroundPaint = new Paint();
            backgroundPaint.setAntiAlias(true);
            backgroundPaint.setDither(true);
            backgroundPaint.setStyle(Style.FILL);
            backgroundPaint.setColor(selectedTitleBackgroundColor);
//            backgroundPaint.setStrokeJoin(Join.ROUND);


            generatePath();


            scaledBitmapList = new ArrayList<Bitmap>();
            drawableList = new ArrayList<BitmapDrawable>();
            for (int i = 0; i < bitmapList.size(); i++) {
                Bitmap bitmap = bitmapList.get(i);
                BitmapDrawable drawable = new BitmapDrawable(getResources(),
                        bitmap);
                drawable.setAntiAlias(true);
                drawable.setDither(true);
                drawable.setGravity(Gravity.CENTER);
                int boundWidth, boundHeight;
//				if (i == 0) {
//					drawable.setBounds(width / 2 - innerRadius, height / 2
//							- innerRadius, width / 2 + innerRadius, height / 2
//							+ innerRadius);
//					boundWidth = 2 * innerRadius;
//					boundHeight = 2 * innerRadius;
//				} else {
//					float startDegree = (i - 1) * itemDegree;
//					float endDegree = i * itemDegree;
//					float startRadian = (float) Math.toRadians(startDegree);
//					float endRadian = (float) Math.toRadians(endDegree);
//					float[] xArray = new float[] {
//							(float) (width / 2 + innerRadius
//									* Math.cos(startRadian)),
//							(float) (width / 2 + innerRadius
//									* Math.cos(endRadian)),
//							(float) (width / 2 + outerRadius
//									* Math.cos(startRadian)),
//							(float) (width / 2 + outerRadius
//									* Math.cos(endRadian)) };
//					float[] yArray = new float[] {
//							(float) (height / 2 + innerRadius
//									* Math.sin(startRadian)),
//							(float) (height / 2 + innerRadius
//									* Math.sin(endRadian)),
//							(float) (height / 2 + outerRadius
//									* Math.sin(startRadian)),
//							(float) (height / 2 + outerRadius
//									* Math.sin(endRadian)) };
//					List<Float> xList = new ArrayList<Float>();
//					for (int j = 0; j < xArray.length; j++) {
//						xList.add(xArray[j]);
//					}
//					List<Float> yList = new ArrayList<Float>();
//					for (int j = 0; j < yArray.length; j++) {
//						yList.add(yArray[j]);
//					}
//					drawable.setBounds((int) (float) Collections.min(xList),
//							(int) (float) Collections.min(yList),
//							(int) (float) Collections.max(xList),
//							(int) (float) Collections.max(yList));
//					boundWidth = (int) (Collections.max(xList) - Collections
//							.min(xList));
//					boundHeight = (int) (Collections.max(yList) - Collections
//							.min(yList));
//				}
//				LogUtil.i("chenxu", "boundWidth:" + boundWidth
//						+ " boundHeight:" + boundHeight);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,
                        IMAGE_WIDTH, IMAGE_HEIGHT, true);
                scaledBitmapList.add(scaledBitmap);
                drawable = new BitmapDrawable(getResources(), scaledBitmap);
                drawableList.add(drawable);
            }
            shaderList = new ArrayList<BitmapShader>();
            for (int i = 0; i < scaledBitmapList.size(); i++) {
                Bitmap scaledBitmap = scaledBitmapList.get(i);
                BitmapShader shader = new BitmapShader(scaledBitmap,
                        TileMode.MIRROR, TileMode.MIRROR);
                shaderList.add(shader);
            }

        }
    }

    public float getAnimationStartDegree() {
        float tempDegree = -90;
        if (previousDownTouchedIndex == 0) {
            tempDegree = -90;
        } else if (previousDownTouchedIndex == 1) {
            tempDegree = 0;
        } else if (previousDownTouchedIndex == 2) {
            tempDegree = 90;
        } else if (previousDownTouchedIndex == 3) {
            tempDegree = 180;
        }
        return tempDegree;
    }

    public float getAnimationEndDegree() {
        float tempDegree = -90;
        if (downTouchedIndex == 0) {
            tempDegree = -90;
        } else if (downTouchedIndex == 1) {
            tempDegree = 0;
        } else if (downTouchedIndex == 2) {
            tempDegree = 90;
        } else if (downTouchedIndex == 3) {
            tempDegree = 180;
        }
        return tempDegree;
    }

    public Path getInnerPath() {
        Path path = new Path();
        float radius = innerRadius + INNER_RADIUS_EXTRA_OFFSET;
//        path.addCircle(width / 2, height / 2, radius ,
//                Direction.CCW);

        if (isInAnimation) {
            if (isAnimationCW) {
                tempDegree += ANIMATION_DEGREE_OFFSET;
                if (tempDegree > animationEndDegree) {
                    tempDegree = animationEndDegree;
                    postInvalidate();
                    previousDownTouchedIndex = downTouchedIndex;
                    isInAnimation = false;
                }
            } else {
                tempDegree -= ANIMATION_DEGREE_OFFSET;
                if (tempDegree < animationEndDegree) {
                    tempDegree = animationEndDegree;
                    postInvalidate();
                    previousDownTouchedIndex = downTouchedIndex;
                    isInAnimation = false;
                }

            }
            LogUtil.i("isAnimationCW:"+isAnimationCW);
            LogUtil.i("from "+tempDegree+" to "+animationEndDegree);
        }

        if (isInAnimation) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            }, ANIMATION_INTERVAL_TIME);
        }


//        float tempDegree = -90;
//        if (downTouchedIndex==0){
//            tempDegree=-90;
//        } else if (downTouchedIndex==1){
//            tempDegree=0;
//        } else if (downTouchedIndex==2){
//            tempDegree=90;
//        } else if (downTouchedIndex==3){
//            tempDegree=180;
//        }

        float startDegree = tempDegree - ARROW_OFFSET_ANGLE;
        float endDegree = tempDegree + ARROW_OFFSET_ANGLE;
        float startRadian = (float) Math.toRadians(startDegree);
        float endRadian = (float) Math.toRadians(endDegree);

        path.moveTo(
                (float) (width / 2 + radius
                        * Math.cos(startRadian)),
                (float) (height / 2 + radius
                        * Math.sin(startRadian)));
        path.lineTo((float) (width / 2 + (innerRadius + ARROW_OFFSET_RADIUS)
                        * Math.cos((float) Math.toRadians(tempDegree))),
                (float) (height / 2 + (innerRadius + ARROW_OFFSET_RADIUS)
                        * Math.sin((float) Math.toRadians(tempDegree))));
        path.lineTo(
                (float) (width / 2 + radius
                        * Math.cos(endRadian)),
                (float) (height / 2 + radius
                        * Math.sin(endRadian)));

        RectF rect = new RectF(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);
        path.arcTo(rect, endDegree, 360 - 2 * ARROW_OFFSET_ANGLE);
        path.close();
        return path;
    }

    public Path getArrowPath() {
        Path path = new Path();
        float tempDegree = -90;
        if (downTouchedIndex == 0) {
            tempDegree = -90;
        } else if (downTouchedIndex == 1) {
            tempDegree = 0;
        } else if (downTouchedIndex == 2) {
            tempDegree = 90;
        } else if (downTouchedIndex == 3) {
            tempDegree = 180;
        }
        float radius = innerRadius + INNER_RADIUS_EXTRA_OFFSET;

        float startDegree = tempDegree - ARROW_OFFSET_ANGLE;
        float endDegree = tempDegree + ARROW_OFFSET_ANGLE;
        float startRadian = (float) Math.toRadians(startDegree);
        float endRadian = (float) Math.toRadians(endDegree);

        path.moveTo(
                (float) (width / 2 + radius
                        * Math.cos(startRadian)),
                (float) (height / 2 + radius
                        * Math.sin(startRadian)));
        path.lineTo((float) (width / 2 + (innerRadius + ARROW_OFFSET_RADIUS)
                        * Math.cos((float) Math.toRadians(tempDegree))),
                (float) (height / 2 + (innerRadius + ARROW_OFFSET_RADIUS)
                        * Math.sin((float) Math.toRadians(tempDegree))));
        path.lineTo(
                (float) (width / 2 + radius
                        * Math.cos(endRadian)),
                (float) (height / 2 + radius
                        * Math.sin(endRadian)));
//        path.close();
        return path;

    }

    public void generatePath() {
        pathList = new ArrayList<Path>();
        Path firstPath = getInnerPath();
        float radius = innerRadius + INNER_RADIUS_EXTRA_OFFSET;
//        firstPath.addCircle(width / 2, height / 2, radius ,
//                Direction.CCW);
//        Path arrowPath = getArrowPath();
//        firstPath.addPath(arrowPath);
        pathList.add(firstPath);

        for (int i = 0; i < imageIdList.size(); i++) {
            float startDegree = -90 + (i) * itemDegree - itemDegree / 2;
            float endDegree = -90 + (i) * itemDegree + itemDegree / 2;
            float startRadian = (float) Math.toRadians(startDegree);
            float endRadian = (float) Math.toRadians(endDegree);

            Path path = new Path();
            path.moveTo(
                    (float) (width / 2 + innerRadius
                            * Math.cos(startRadian)),
                    (float) (height / 2 + innerRadius
                            * Math.sin(startRadian)));
            path.arcTo(new RectF(width / 2 - innerRadius, height / 2
                    - innerRadius, width / 2 + innerRadius, height / 2
                    + innerRadius), startDegree, itemDegree, true);
            path.lineTo(
                    (float) (width / 2 + outerRadius * Math.cos(endRadian)),
                    (float) (height / 2 + outerRadius * Math.sin(endRadian)));
            path.arcTo(new RectF(width / 2 - outerRadius, height / 2
                    - outerRadius, width / 2 + outerRadius, height / 2
                    + outerRadius), endDegree, -itemDegree, true);
            path.lineTo(
                    (float) (width / 2 + innerRadius
                            * Math.cos(startRadian)),
                    (float) (height / 2 + innerRadius
                            * Math.sin(startRadian)));
            path.close();
            float centerRadian = getCenterRadian(i);
            Matrix matrix = new Matrix();
            double offset = OFFSET;
            if (i == downTouchedIndex) {
                offset = SELECTED_OFFSET;
            } else {
                offset = OFFSET;
            }
            matrix.postTranslate((float) (offset * Math.cos(centerRadian)),
                    (float) (offset * Math.sin(centerRadian)));
            path.transform(matrix);

            pathList.add(path);


        }

        imageRectList = new ArrayList<>();
        unselectedTextRectList = new ArrayList<>();
        for (int i = 0; i < imageIdList.size(); i++) {
            String title = getHintFromIndex(i);
            int titleWidth = getOtherStringWidth(title);
            int titleHeight = getOtherStringHeight(title);
            RectF imageRect;
            RectF titleRect;
            float centerX = 0;
            float centerY = 0;
            if (i == 0) {
                centerX = width / 2;
                centerY = height / 2 - (innerRadius + outerRadius) / 2;
            } else if (i == 1) {
                centerX = width / 2 + (innerRadius + outerRadius) / 2;
                centerY = height / 2;
            } else if (i == 2) {
                centerX = width / 2;
                centerY = height / 2 + (innerRadius + outerRadius) / 2;
            } else if (i == 3) {
                centerX = width / 2 - (innerRadius + outerRadius) / 2;
                centerY = height / 2;
            }
            imageRect = new RectF(centerX - IMAGE_WIDTH / 2, centerY - IMAGE_HEIGHT, centerX + IMAGE_WIDTH / 2,
                    centerY);
            imageRectList.add(imageRect);
            titleRect = new RectF(centerX - titleWidth / 2, centerY + IMAGE_AND_TEXT_OFFSET * 4, centerX + titleWidth / 2,
                    centerY + IMAGE_AND_TEXT_OFFSET * 4 + titleHeight);
            unselectedTextRectList.add(titleRect);
        }

        String innerNumber = getNumberWithSuffixFromIndex(downTouchedIndex);
        String innerHint = getHintWithSuffixFromIndex(downTouchedIndex);
        String innerPercent = getPercentWithSuffixFromIndex(downTouchedIndex);
        int innerNumberWidth = getNumberStringWidth(innerNumber);
        int innerNumberHeight = getNumberStringHeight(innerNumber);
        int innerHintWidth = getOtherStringWidth(innerHint);
        int innerHintHeight = getOtherStringHeight(innerHint);
        int innerPercentWidth = getOtherStringWidth(innerPercent);
        int innerPercentHeight = getOtherStringHeight(innerPercent);

        innerNumberTextRect = new RectF(width / 2 - innerNumberWidth / 2, height / 2 - innerNumberHeight,
                width / 2 + innerNumberWidth / 2, height / 2);
        innerHintTextRect = new RectF(width / 2 - innerHintWidth / 2, height / 2 + IMAGE_AND_TEXT_OFFSET,
                width / 2 + innerHintWidth / 2, height / 2 + IMAGE_AND_TEXT_OFFSET + innerHintHeight);
        innerPercentTextRect = new RectF(width / 2 - innerPercentWidth / 2, height / 2 + IMAGE_AND_TEXT_OFFSET * 2 + innerHintHeight,
                width / 2 + innerPercentWidth / 2, height / 2 + IMAGE_AND_TEXT_OFFSET * 2 + innerHintHeight + innerPercentHeight);
    }

    public float getCenterRadian(int index) {
        float degree = (float) (-90 + (index) * itemDegree);
        float radian = (float) Math.toRadians(degree);
        return radian;
    }

    public interface RingBlockViewListener {
        public void ringBlockViewDidClick(int index);
    }

    public enum Status {
        InitStatus, ClickedStatus
    }

}


