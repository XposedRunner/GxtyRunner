package com.github.mikephil.charting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import java.util.List;

public abstract class Utils {
    public static final double DEG2RAD = 0.017453292519943295d;
    public static final double DOUBLE_EPSILON = Double.longBitsToDouble(1);
    public static final float FDEG2RAD = 0.017453292f;
    public static final float FLOAT_EPSILON = Float.intBitsToFloat(1);
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, ByteBufferUtils.ERROR_CODE, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static Rect mCalcTextHeightRect = new Rect();
    private static Rect mCalcTextSizeRect = new Rect();
    private static IValueFormatter mDefaultValueFormatter = generateDefaultValueFormatter();
    private static Rect mDrawTextRectBuffer = new Rect();
    private static Rect mDrawableBoundsCache = new Rect();
    private static FontMetrics mFontMetrics = new FontMetrics();
    private static FontMetrics mFontMetricsBuffer = new FontMetrics();
    private static int mMaximumFlingVelocity = 8000;
    private static DisplayMetrics mMetrics;
    private static int mMinimumFlingVelocity = 50;

    public static void init(Context context) {
        if (context == null) {
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
            Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
            return;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMetrics = context.getResources().getDisplayMetrics();
    }

    @Deprecated
    public static void init(Resources resources) {
        mMetrics = resources.getDisplayMetrics();
        mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
        mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
    }

    public static float convertDpToPixel(float f) {
        if (mMetrics != null) {
            return f * mMetrics.density;
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
        return f;
    }

    public static float convertPixelsToDp(float f) {
        if (mMetrics != null) {
            return f / mMetrics.density;
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
        return f;
    }

    public static int calcTextWidth(Paint paint, String str) {
        return (int) paint.measureText(str);
    }

    public static int calcTextHeight(Paint paint, String str) {
        Rect rect = mCalcTextHeightRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public static float getLineHeight(Paint paint) {
        return getLineHeight(paint, mFontMetrics);
    }

    public static float getLineHeight(Paint paint, FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public static float getLineSpacing(Paint paint) {
        return getLineSpacing(paint, mFontMetrics);
    }

    public static float getLineSpacing(Paint paint, FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return (fontMetrics.ascent - fontMetrics.top) + fontMetrics.bottom;
    }

    public static FSize calcTextSize(Paint paint, String str) {
        FSize instance = FSize.getInstance(0.0f, 0.0f);
        calcTextSize(paint, str, instance);
        return instance;
    }

    public static void calcTextSize(Paint paint, String str, FSize fSize) {
        Rect rect = mCalcTextSizeRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        fSize.width = (float) rect.width();
        fSize.height = (float) rect.height();
    }

    private static IValueFormatter generateDefaultValueFormatter() {
        return new DefaultValueFormatter(1);
    }

    public static IValueFormatter getDefaultValueFormatter() {
        return mDefaultValueFormatter;
    }

    public static String formatNumber(float f, int i, boolean z) {
        return formatNumber(f, i, z, '.');
    }

    public static String formatNumber(float f, int i, boolean z, char c) {
        char[] cArr = new char[35];
        Object obj = null;
        if (f == 0.0f) {
            return "0";
        }
        Object obj2;
        int i2;
        int i3;
        if (f >= 1.0f || f <= -1.0f) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (f < 0.0f) {
            obj = 1;
            f = -f;
        }
        if (i > POW_10.length) {
            i = POW_10.length - 1;
        }
        long round = (long) Math.round(((float) POW_10[i]) * f);
        int length = cArr.length - 1;
        int i4 = 0;
        Object obj3 = null;
        while (true) {
            if (round == 0 && i4 >= i + 1) {
                break;
            }
            int i5;
            round /= 10;
            int i6 = length - 1;
            cArr[length] = (char) (((int) (round % 10)) + 48);
            i4++;
            if (i4 == i) {
                i5 = i6 - 1;
                cArr[i6] = ',';
                i4++;
                obj3 = 1;
            } else {
                if (z && round != 0 && i4 > i) {
                    if (obj3 != null) {
                        if ((i4 - i) % 4 == 0) {
                            i5 = i6 - 1;
                            cArr[i6] = c;
                            i4++;
                        }
                    } else if ((i4 - i) % 4 == 3) {
                        i5 = i6 - 1;
                        cArr[i6] = c;
                        i4++;
                    }
                }
                i5 = i6;
            }
            length = i5;
        }
        if (obj2 != null) {
            i5 = length - 1;
            cArr[length] = '0';
            i2 = i4 + 1;
            i4 = i5;
        } else {
            i2 = i4;
            i4 = length;
        }
        if (obj != null) {
            i3 = i4 - 1;
            cArr[i4] = '-';
            i3 = i2 + 1;
        } else {
            i3 = i2;
        }
        i3 = cArr.length - i3;
        return String.valueOf(cArr, i3, cArr.length - i3);
    }

    public static float roundToNextSignificant(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d) || d == DOUBLE_EPSILON) {
            return 0.0f;
        }
        double d2;
        if (d < DOUBLE_EPSILON) {
            d2 = -d;
        } else {
            d2 = d;
        }
        float pow = (float) Math.pow(10.0d, (double) (1 - ((int) ((float) Math.ceil((double) ((float) Math.log10(d2)))))));
        return ((float) Math.round(((double) pow) * d)) / pow;
    }

    public static int getDecimals(float f) {
        float roundToNextSignificant = roundToNextSignificant((double) f);
        if (Float.isInfinite(roundToNextSignificant)) {
            return 0;
        }
        return ((int) Math.ceil(-Math.log10((double) roundToNextSignificant))) + 2;
    }

    public static int[] convertIntegers(List<Integer> list) {
        int[] iArr = new int[list.size()];
        copyIntegers(list, iArr);
        return iArr;
    }

    public static void copyIntegers(List<Integer> list, int[] iArr) {
        int length = iArr.length < list.size() ? iArr.length : list.size();
        for (int i = 0; i < length; i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
    }

    public static String[] convertStrings(List<String> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (String) list.get(i);
        }
        return strArr;
    }

    public static void copyStrings(List<String> list, String[] strArr) {
        int length = strArr.length < list.size() ? strArr.length : list.size();
        for (int i = 0; i < length; i++) {
            strArr[i] = (String) list.get(i);
        }
    }

    public static double nextUp(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return d;
        }
        double d2 = d + DOUBLE_EPSILON;
        return Double.longBitsToDouble((d2 >= DOUBLE_EPSILON ? 1 : -1) + Double.doubleToRawLongBits(d2));
    }

    public static MPPointF getPosition(MPPointF mPPointF, float f, float f2) {
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(mPPointF, f, f2, instance);
        return instance;
    }

    public static void getPosition(MPPointF mPPointF, float f, float f2, MPPointF mPPointF2) {
        mPPointF2.x = (float) (((double) mPPointF.x) + (((double) f) * Math.cos(Math.toRadians((double) f2))));
        mPPointF2.y = (float) (((double) mPPointF.y) + (((double) f) * Math.sin(Math.toRadians((double) f2))));
    }

    public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent motionEvent, VelocityTracker velocityTracker) {
        velocityTracker.computeCurrentVelocity(1000, (float) mMaximumFlingVelocity);
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float xVelocity = velocityTracker.getXVelocity(pointerId);
        float yVelocity = velocityTracker.getYVelocity(pointerId);
        int pointerCount = motionEvent.getPointerCount();
        for (pointerId = 0; pointerId < pointerCount; pointerId++) {
            if (pointerId != actionIndex) {
                int pointerId2 = motionEvent.getPointerId(pointerId);
                if ((velocityTracker.getYVelocity(pointerId2) * yVelocity) + (velocityTracker.getXVelocity(pointerId2) * xVelocity) < 0.0f) {
                    velocityTracker.clear();
                    return;
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void postInvalidateOnAnimation(View view) {
        if (VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidateDelayed(10);
        }
    }

    public static int getMinimumFlingVelocity() {
        return mMinimumFlingVelocity;
    }

    public static int getMaximumFlingVelocity() {
        return mMaximumFlingVelocity;
    }

    public static float getNormalizedAngle(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        return f % 360.0f;
    }

    public static void drawImage(Canvas canvas, Drawable drawable, int i, int i2, int i3, int i4) {
        MPPointF instance = MPPointF.getInstance();
        instance.x = (float) (i - (i3 / 2));
        instance.y = (float) (i2 - (i4 / 2));
        drawable.copyBounds(mDrawableBoundsCache);
        drawable.setBounds(mDrawableBoundsCache.left, mDrawableBoundsCache.top, mDrawableBoundsCache.left + i3, mDrawableBoundsCache.top + i3);
        int save = canvas.save();
        canvas.translate(instance.x, instance.y);
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    public static void drawXAxisValue(Canvas canvas, String str, float f, float f2, Paint paint, MPPointF mPPointF, float f3) {
        float fontMetrics = paint.getFontMetrics(mFontMetricsBuffer);
        paint.getTextBounds(str, 0, str.length(), mDrawTextRectBuffer);
        float f4 = 0.0f - ((float) mDrawTextRectBuffer.left);
        float f5 = (-mFontMetricsBuffer.ascent) + 0.0f;
        Align textAlign = paint.getTextAlign();
        paint.setTextAlign(Align.LEFT);
        if (f3 != 0.0f) {
            f4 -= ((float) mDrawTextRectBuffer.width()) * 0.5f;
            f5 -= fontMetrics * 0.5f;
            if (!(mPPointF.x == 0.5f && mPPointF.y == 0.5f)) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees((float) mDrawTextRectBuffer.width(), fontMetrics, f3);
                f -= sizeOfRotatedRectangleByDegrees.width * (mPPointF.x - 0.5f);
                f2 -= sizeOfRotatedRectangleByDegrees.height * (mPPointF.y - 0.5f);
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            canvas.save();
            canvas.translate(f, f2);
            canvas.rotate(f3);
            canvas.drawText(str, f4, f5, paint);
            canvas.restore();
        } else {
            if (!(mPPointF.x == 0.0f && mPPointF.y == 0.0f)) {
                f4 -= ((float) mDrawTextRectBuffer.width()) * mPPointF.x;
                f5 -= fontMetrics * mPPointF.y;
            }
            canvas.drawText(str, f4 + f, f5 + f2, paint);
        }
        paint.setTextAlign(textAlign);
    }

    public static void drawMultilineText(Canvas canvas, StaticLayout staticLayout, float f, float f2, TextPaint textPaint, MPPointF mPPointF, float f3) {
        float width = (float) staticLayout.getWidth();
        float lineCount = ((float) staticLayout.getLineCount()) * textPaint.getFontMetrics(mFontMetricsBuffer);
        float f4 = 0.0f - ((float) mDrawTextRectBuffer.left);
        float f5 = 0.0f + lineCount;
        Align textAlign = textPaint.getTextAlign();
        textPaint.setTextAlign(Align.LEFT);
        if (f3 != 0.0f) {
            f4 -= width * 0.5f;
            f5 -= lineCount * 0.5f;
            if (!(mPPointF.x == 0.5f && mPPointF.y == 0.5f)) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees(width, lineCount, f3);
                f -= sizeOfRotatedRectangleByDegrees.width * (mPPointF.x - 0.5f);
                f2 -= sizeOfRotatedRectangleByDegrees.height * (mPPointF.y - 0.5f);
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            canvas.save();
            canvas.translate(f, f2);
            canvas.rotate(f3);
            canvas.translate(f4, f5);
            staticLayout.draw(canvas);
            canvas.restore();
        } else {
            if (!(mPPointF.x == 0.0f && mPPointF.y == 0.0f)) {
                f4 -= width * mPPointF.x;
                f5 -= mPPointF.y * lineCount;
            }
            f4 += f;
            f5 += f2;
            canvas.save();
            canvas.translate(f4, f5);
            staticLayout.draw(canvas);
            canvas.restore();
        }
        textPaint.setTextAlign(textAlign);
    }

    public static void drawMultilineText(Canvas canvas, String str, float f, float f2, TextPaint textPaint, FSize fSize, MPPointF mPPointF, float f3) {
        drawMultilineText(canvas, new StaticLayout(str, 0, str.length(), textPaint, (int) Math.max(Math.ceil((double) fSize.width), 1.0d), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false), f, f2, textPaint, mPPointF, f3);
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(FSize fSize, float f) {
        return getSizeOfRotatedRectangleByRadians(fSize.width, fSize.height, 0.017453292f * f);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(FSize fSize, float f) {
        return getSizeOfRotatedRectangleByRadians(fSize.width, fSize.height, f);
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(float f, float f2, float f3) {
        return getSizeOfRotatedRectangleByRadians(f, f2, 0.017453292f * f3);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(float f, float f2, float f3) {
        return FSize.getInstance(Math.abs(((float) Math.cos((double) f3)) * f) + Math.abs(((float) Math.sin((double) f3)) * f2), Math.abs(((float) Math.sin((double) f3)) * f) + Math.abs(((float) Math.cos((double) f3)) * f2));
    }

    public static int getSDKInt() {
        return VERSION.SDK_INT;
    }
}
