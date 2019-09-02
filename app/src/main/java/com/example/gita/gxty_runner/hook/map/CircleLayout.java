package com.example.gita.gxty_runner.hook.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CircleLayout extends FrameLayout {
    private Paint roundPaint;
    private Paint imagePaint;
    private float mBorderWidth;
    private int mBorderColor;
    private float mRadius;

    public CircleLayout(Context context) {
        this(context, null, 0, 0);
    }

    public CircleLayout(Context context, float borderWidth, int borderColor) {
        this(context, null, borderWidth, borderColor);
    }

//    CircleLayout(Context context, AttributeSet attrs, float borderWidth, int borderColor) {
//        this(context, attrs, 0, borderWidth, borderColor);
//    }

    private CircleLayout(Context context, AttributeSet attrs, float borderWidth, int borderColor) {
        super(context, attrs, 0);
        roundPaint = new Paint();
        roundPaint.setColor(Color.WHITE);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        imagePaint = new Paint();
        imagePaint.setXfermode(null);
        mBorderWidth = borderWidth;
        mBorderColor = borderColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRadius = getWidth() / 2;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        drawBorder(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    private void drawBorder(Canvas canvas) {
        if (mBorderWidth != 0 && mRadius > 0) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(mBorderWidth);
            paint.setColor(mBorderColor);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, paint);

        }

    }

    private void drawTopLeft(Canvas canvas) {
        if (mRadius > 0) {
            Path path = new Path();
            path.moveTo(0, mRadius);
            path.lineTo(0, 0);
            path.lineTo(mRadius, 0);
            path.arcTo(new RectF(0, 0, mRadius * 2, mRadius * 2),
                    -90, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (mRadius > 0) {
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - mRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, mRadius);
            path.arcTo(new RectF(width - 2 * mRadius, 0, width,
                    mRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (mRadius > 0) {
            int height = getHeight();
            Path path = new Path();
            path.moveTo(0, height - mRadius);
            path.lineTo(0, height);
            path.lineTo(mRadius, height);
            path.arcTo(new RectF(0, height - 2 * mRadius,
                    mRadius * 2, height), 90, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (mRadius > 0) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - mRadius, height);
            path.lineTo(width, height);
            path.lineTo(width, height - mRadius);
            path.arcTo(new RectF(width - 2 * mRadius, height - 2
                    * mRadius, width, height), 0, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

}