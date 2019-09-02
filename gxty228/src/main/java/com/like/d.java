package com.like;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.TypedValue;
import com.like.view.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Utils */
public class d {
    public static double a(double d, double d2, double d3, double d4, double d5) {
        return (((d - d2) / (d3 - d2)) * (d5 - d4)) + d4;
    }

    public static double a(double d, double d2, double d3) {
        return Math.min(Math.max(d, d2), d3);
    }

    public static List<a> a() {
        List<a> arrayList = new ArrayList();
        arrayList.add(new a(R.drawable.heart_on, R.drawable.heart_off, IconType.Heart));
        arrayList.add(new a(R.drawable.star_on, R.drawable.star_off, IconType.Star));
        arrayList.add(new a(R.drawable.thumb_on, R.drawable.thumb_off, IconType.Thumb));
        return arrayList;
    }

    public static Drawable a(Context context, Drawable drawable, int i, int i2) {
        return new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(a(drawable, i, i2), i, i2, true));
    }

    public static Bitmap a(Drawable drawable, int i, int i2) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof VectorDrawableCompat) {
            return a((VectorDrawableCompat) drawable, i, i2);
        }
        if (drawable instanceof VectorDrawable) {
            return a((VectorDrawable) drawable, i, i2);
        }
        throw new IllegalArgumentException("Unsupported drawable type");
    }

    @TargetApi(21)
    private static Bitmap a(VectorDrawable vectorDrawable, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return createBitmap;
    }

    private static Bitmap a(VectorDrawableCompat vectorDrawableCompat, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawableCompat.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawableCompat.draw(canvas);
        return createBitmap;
    }

    public static float a(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
