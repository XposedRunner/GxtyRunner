package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import java.lang.reflect.Field;

/* compiled from: ImageViewAware */
public class b extends d {
    public /* synthetic */ View d() {
        return g();
    }

    public b(ImageView imageView) {
        super(imageView);
    }

    public int a() {
        int a = super.a();
        if (a <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return a(obj, "mMaxWidth");
            }
        }
        return a;
    }

    public int b() {
        int b = super.b();
        if (b <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return a(obj, "mMaxHeight");
            }
        }
        return b;
    }

    public ViewScaleType c() {
        ImageView imageView = (ImageView) this.a.get();
        if (imageView != null) {
            return ViewScaleType.fromImageView(imageView);
        }
        return super.c();
    }

    public ImageView g() {
        return (ImageView) super.d();
    }

    protected void a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    protected void a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Throwable e) {
            c.a(e);
        }
        return 0;
    }
}
