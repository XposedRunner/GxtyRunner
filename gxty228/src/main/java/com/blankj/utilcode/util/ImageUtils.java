package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.RenderScript.RSMessageHandler;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

public final class ImageUtils {
    private ImageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        } else {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    public static byte[] drawable2Bytes(Drawable drawable, CompressFormat compressFormat) {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), compressFormat);
    }

    public static Drawable bytes2Drawable(byte[] bArr) {
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getBitmap(File file) {
        if (file == null) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap getBitmap(File file, int i, int i2) {
        if (file == null) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap getBitmap(String str) {
        if (isSpace(str)) {
            return null;
        }
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap getBitmap(String str, int i, int i2) {
        if (isSpace(str)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getBitmap(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    public static Bitmap getBitmap(InputStream inputStream, int i, int i2) {
        if (inputStream == null) {
            return null;
        }
        return getBitmap(input2Byte(inputStream), 0, i, i2);
    }

    public static Bitmap getBitmap(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length);
    }

    public static Bitmap getBitmap(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length == 0) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
    }

    public static Bitmap getBitmap(@DrawableRes int i) {
        Drawable drawable = ContextCompat.getDrawable(Utils.getApp(), i);
        Canvas canvas = new Canvas();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getBitmap(@DrawableRes int i, int i2, int i3) {
        Options options = new Options();
        Resources resources = Utils.getApp().getResources();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return null;
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor, int i, int i2) {
        if (fileDescriptor == null) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i) {
        if (bitmap != null) {
            return drawColor(bitmap, i, false);
        }
        throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i, boolean z) {
        if (bitmap == null) {
            throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (isEmptyBitmap(bitmap)) {
            return null;
        } else {
            if (!z) {
                bitmap = bitmap.copy(bitmap.getConfig(), true);
            }
            new Canvas(bitmap).drawColor(i, Mode.DARKEN);
            return bitmap;
        }
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        if (!z || bitmap.isRecycled()) {
            return createScaledBitmap;
        }
        bitmap.recycle();
        return createScaledBitmap;
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!z || bitmap.isRecycled()) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return clip(bitmap, i, i2, i3, i4, false);
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (!z || bitmap.isRecycled()) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, boolean z) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, z);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return skew(bitmap, f, f2, f3, f4, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f, f2, f3, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!z || bitmap.isRecycled()) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2) {
        return rotate(bitmap, i, f, f2, false);
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i, f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static int getRotateDegree(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Bitmap toRound(Bitmap bitmap) {
        return toRound(bitmap, 0, 0, false);
    }

    public static Bitmap toRound(Bitmap bitmap, boolean z) {
        return toRound(bitmap, 0, 0, z);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2) {
        return toRound(bitmap, i, i2, false);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        float f = ((float) min) / 2.0f;
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        rectF.inset(((float) (width - min)) / 2.0f, ((float) (height - min)) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        matrix.preScale(((float) min) / ((float) width), ((float) min) / ((float) height));
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(rectF, f, f, paint);
        if (i > 0) {
            paint.setShader(null);
            paint.setColor(i2);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth((float) i);
            canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, f - (((float) i) / 2.0f), paint);
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f) {
        return toRoundCorner(bitmap, f, 0, 0, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, boolean z) {
        return toRoundCorner(bitmap, f, 0, 0, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2) {
        return toRoundCorner(bitmap, f, i, i2, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        float f2 = ((float) i) / 2.0f;
        rectF.inset(f2, f2);
        canvas.drawRoundRect(rectF, f, f, paint);
        if (i > 0) {
            paint.setShader(null);
            paint.setColor(i2);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth((float) i);
            paint.setStrokeCap(Cap.ROUND);
            canvas.drawRoundRect(rectF, f, f, paint);
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f) {
        return addBorder(bitmap, i, i2, false, f, false);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f, boolean z) {
        return addBorder(bitmap, i, i2, false, f, z);
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2) {
        return addBorder(bitmap, i, i2, true, 0.0f, false);
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z) {
        return addBorder(bitmap, i, i2, true, 0.0f, z);
    }

    private static Bitmap addBorder(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z, float f, boolean z2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (!z2) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(1);
        paint.setColor(i2);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth((float) i);
        if (z) {
            canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, (((float) Math.min(width, height)) / 2.0f) - (((float) i) / 2.0f), paint);
            return bitmap;
        }
        int i3 = i >> 1;
        canvas.drawRoundRect(new RectF((float) i3, (float) i3, (float) (width - i3), (float) (height - i3)), f, f, paint);
        return bitmap;
    }

    public static Bitmap addReflection(Bitmap bitmap, int i) {
        return addReflection(bitmap, i, false);
    }

    public static Bitmap addReflection(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height - i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        canvas.drawBitmap(createBitmap, 0.0f, (float) (height + 0), null);
        Paint paint = new Paint(1);
        paint.setShader(new LinearGradient(0.0f, (float) height, 0.0f, (float) (createBitmap2.getHeight() + 0), 1895825407, ViewCompat.MEASURED_SIZE_MASK, TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0.0f, (float) (height + 0), (float) width, (float) createBitmap2.getHeight(), paint);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap2;
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, int i, @ColorInt int i2, float f, float f2) {
        return addTextWatermark(bitmap, str, (float) i, i2, f, f2, false);
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, float f, @ColorInt int i, float f2, float f3, boolean z) {
        if (isEmptyBitmap(bitmap) || str == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(copy);
        paint.setColor(i);
        paint.setTextSize(f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, f2, f3 + f, paint);
        if (!z || bitmap.isRecycled()) {
            return copy;
        }
        bitmap.recycle();
        return copy;
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        return addImageWatermark(bitmap, bitmap2, i, i2, i3, false);
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (!isEmptyBitmap(bitmap2)) {
            Paint paint = new Paint(1);
            Canvas canvas = new Canvas(copy);
            paint.setAlpha(i3);
            canvas.drawBitmap(bitmap2, (float) i, (float) i2, paint);
        }
        if (!z || bitmap.isRecycled()) {
            return copy;
        }
        bitmap.recycle();
        return copy;
    }

    public static Bitmap toAlpha(Bitmap bitmap) {
        return toAlpha(bitmap, Boolean.valueOf(false));
    }

    public static Bitmap toAlpha(Bitmap bitmap, Boolean bool) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap extractAlpha = bitmap.extractAlpha();
        if (!bool.booleanValue() || bitmap.isRecycled()) {
            return extractAlpha;
        }
        bitmap.recycle();
        return extractAlpha;
    }

    public static Bitmap toGray(Bitmap bitmap) {
        return toGray(bitmap, false);
    }

    public static Bitmap toGray(Bitmap bitmap, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (!z || bitmap.isRecycled()) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2) {
        return fastBlur(bitmap, f, f2, false);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Paint paint = new Paint(3);
        Canvas canvas = new Canvas();
        paint.setColorFilter(new PorterDuffColorFilter(0, Mode.SRC_ATOP));
        canvas.scale(f, f);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        if (VERSION.SDK_INT >= 17) {
            createBitmap = renderScriptBlur(createBitmap, f2, z);
        } else {
            createBitmap = stackBlur(createBitmap, (int) f2, z);
        }
        if (f != 1.0f) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap, width, height, true);
            if (!createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return createScaledBitmap;
        } else if (!z || bitmap.isRecycled()) {
            return createBitmap;
        } else {
            bitmap.recycle();
            return createBitmap;
        }
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f) {
        return renderScriptBlur(bitmap, f, false);
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f, boolean z) {
        RenderScript renderScript = null;
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        try {
            renderScript = RenderScript.create(Utils.getApp());
            renderScript.setMessageHandler(new RSMessageHandler());
            Allocation createFromBitmap = Allocation.createFromBitmap(renderScript, bitmap, MipmapControl.MIPMAP_NONE, 1);
            Allocation createTyped = Allocation.createTyped(renderScript, createFromBitmap.getType());
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            create.setInput(createFromBitmap);
            create.setRadius(f);
            create.forEach(createTyped);
            createTyped.copyTo(bitmap);
            return bitmap;
        } finally {
            if (renderScript != null) {
                renderScript.destroy();
            }
        }
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i) {
        return stackBlur(bitmap, i, false);
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        int i2;
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            i = 1;
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = width * height;
        int i6 = (i + i) + 1;
        int[] iArr2 = new int[i5];
        int[] iArr3 = new int[i5];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[Math.max(width, height)];
        i5 = (i6 + 1) >> 1;
        int i7 = i5 * i5;
        int[] iArr6 = new int[(i7 * 256)];
        for (i5 = 0; i5 < i7 * 256; i5++) {
            iArr6[i5] = i5 / i7;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i6, 3});
        int i8 = i + 1;
        int i9 = 0;
        int i10 = 0;
        for (i2 = 0; i2 < height; i2++) {
            int i11;
            int i12;
            i7 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (i11 = -i; i11 <= i; i11++) {
                i12 = iArr[Math.min(i3, Math.max(i11, 0)) + i10];
                int[] iArr8 = iArr7[i11 + i];
                iArr8[0] = (16711680 & i12) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i12) >> 8;
                iArr8[2] = i12 & 255;
                i12 = i8 - Math.abs(i11);
                i19 += iArr8[0] * i12;
                i18 += iArr8[1] * i12;
                i17 += i12 * iArr8[2];
                if (i11 > 0) {
                    i13 += iArr8[0];
                    i20 += iArr8[1];
                    i7 += iArr8[2];
                } else {
                    i16 += iArr8[0];
                    i15 += iArr8[1];
                    i14 += iArr8[2];
                }
            }
            i12 = i19;
            i19 = i18;
            i18 = i17;
            i11 = i10;
            i10 = i;
            for (i17 = 0; i17 < width; i17++) {
                iArr2[i11] = iArr6[i12];
                iArr3[i11] = iArr6[i19];
                iArr4[i11] = iArr6[i18];
                i12 -= i16;
                i19 -= i15;
                i18 -= i14;
                iArr8 = iArr7[((i10 - i) + i6) % i6];
                i16 -= iArr8[0];
                i15 -= iArr8[1];
                i14 -= iArr8[2];
                if (i2 == 0) {
                    iArr5[i17] = Math.min((i17 + i) + 1, i3);
                }
                int i21 = iArr[iArr5[i17] + i9];
                iArr8[0] = (16711680 & i21) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                iArr8[2] = i21 & 255;
                i13 += iArr8[0];
                i20 += iArr8[1];
                i7 += iArr8[2];
                i12 += i13;
                i19 += i20;
                i18 += i7;
                i10 = (i10 + 1) % i6;
                iArr8 = iArr7[i10 % i6];
                i16 += iArr8[0];
                i15 += iArr8[1];
                i14 += iArr8[2];
                i13 -= iArr8[0];
                i20 -= iArr8[1];
                i7 -= iArr8[2];
                i11++;
            }
            i9 += width;
            i10 = i11;
        }
        for (i17 = 0; i17 < width; i17++) {
            i20 = 0;
            i7 = (-i) * width;
            i14 = 0;
            i15 = 0;
            i16 = 0;
            i10 = 0;
            i12 = -i;
            i11 = 0;
            i18 = 0;
            i19 = 0;
            i13 = 0;
            while (i12 <= i) {
                i2 = Math.max(0, i7) + i17;
                int[] iArr9 = iArr7[i12 + i];
                iArr9[0] = iArr2[i2];
                iArr9[1] = iArr3[i2];
                iArr9[2] = iArr4[i2];
                int abs = i8 - Math.abs(i12);
                i9 = (iArr2[i2] * abs) + i19;
                i19 = (iArr3[i2] * abs) + i18;
                i18 = (iArr4[i2] * abs) + i11;
                if (i12 > 0) {
                    i14 += iArr9[0];
                    i13 += iArr9[1];
                    i20 += iArr9[2];
                } else {
                    i10 += iArr9[0];
                    i16 += iArr9[1];
                    i15 += iArr9[2];
                }
                if (i12 < i4) {
                    i7 += width;
                }
                i12++;
                i11 = i18;
                i18 = i19;
                i19 = i9;
            }
            i12 = i18;
            i9 = i19;
            i19 = i11;
            i11 = i17;
            i7 = i20;
            i20 = i13;
            i13 = i14;
            i14 = i15;
            i15 = i16;
            i16 = i10;
            i10 = i;
            for (i18 = 0; i18 < height; i18++) {
                iArr[i11] = (((ViewCompat.MEASURED_STATE_MASK & iArr[i11]) | (iArr6[i9] << 16)) | (iArr6[i12] << 8)) | iArr6[i19];
                i9 -= i16;
                i12 -= i15;
                i19 -= i14;
                int[] iArr10 = iArr7[((i10 - i) + i6) % i6];
                i16 -= iArr10[0];
                i15 -= iArr10[1];
                i14 -= iArr10[2];
                if (i17 == 0) {
                    iArr5[i18] = Math.min(i18 + i8, i4) * width;
                }
                i3 = iArr5[i18] + i17;
                iArr10[0] = iArr2[i3];
                iArr10[1] = iArr3[i3];
                iArr10[2] = iArr4[i3];
                i13 += iArr10[0];
                i20 += iArr10[1];
                i7 += iArr10[2];
                i9 += i13;
                i12 += i20;
                i19 += i7;
                i10 = (i10 + 1) % i6;
                iArr10 = iArr7[i10];
                i16 += iArr10[0];
                i15 += iArr10[1];
                i14 += iArr10[2];
                i13 -= iArr10[0];
                i20 -= iArr10[1];
                i7 -= iArr10[2];
                i11 += width;
            }
        }
        bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap2;
    }

    public static boolean save(Bitmap bitmap, String str, CompressFormat compressFormat) {
        return save(bitmap, getFileByPath(str), compressFormat, false);
    }

    public static boolean save(Bitmap bitmap, File file, CompressFormat compressFormat) {
        return save(bitmap, file, compressFormat, false);
    }

    public static boolean save(Bitmap bitmap, String str, CompressFormat compressFormat, boolean z) {
        return save(bitmap, getFileByPath(str), compressFormat, z);
    }

    public static boolean save(Bitmap bitmap, File file, CompressFormat compressFormat, boolean z) {
        IOException e;
        Throwable th;
        boolean z2 = false;
        if (!isEmptyBitmap(bitmap) && createFileByDeleteOldFile(file)) {
            OutputStream bufferedOutputStream;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    z2 = bitmap.compress(compressFormat, 100, bufferedOutputStream);
                    if (z && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return z2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                bufferedOutputStream = null;
                e222.printStackTrace();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z2;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return z2;
    }

    public static boolean isImage(File file) {
        return file != null && isImage(file.getPath());
    }

    public static boolean isImage(String str) {
        String toUpperCase = str.toUpperCase();
        return toUpperCase.endsWith(".PNG") || toUpperCase.endsWith(".JPG") || toUpperCase.endsWith(".JPEG") || toUpperCase.endsWith(".BMP") || toUpperCase.endsWith(".GIF") || toUpperCase.endsWith(".WEBP");
    }

    public static String getImageType(String str) {
        return getImageType(getFileByPath(str));
    }

    public static String getImageType(File file) {
        InputStream fileInputStream;
        IOException e;
        Throwable th;
        if (file == null) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                String imageType = getImageType(fileInputStream);
                if (imageType == null) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return getFileExtension(file.getAbsolutePath()).toUpperCase();
                } else if (fileInputStream == null) {
                    return imageType;
                } else {
                    try {
                        fileInputStream.close();
                        return imageType;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return imageType;
                    }
                }
            } catch (IOException e4) {
                e2 = e4;
                try {
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return getFileExtension(file.getAbsolutePath()).toUpperCase();
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileInputStream = null;
            e22.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return getFileExtension(file.getAbsolutePath()).toUpperCase();
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    private static String getFileExtension(String str) {
        if (isSpace(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    private static String getImageType(InputStream inputStream) {
        String str = null;
        if (inputStream != null) {
            try {
                byte[] bArr = new byte[8];
                if (inputStream.read(bArr, 0, 8) != -1) {
                    str = getImageType(bArr);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    private static String getImageType(byte[] bArr) {
        if (isJPEG(bArr)) {
            return "JPEG";
        }
        if (isGIF(bArr)) {
            return "GIF";
        }
        if (isPNG(bArr)) {
            return "PNG";
        }
        if (isBMP(bArr)) {
            return "BMP";
        }
        return null;
    }

    private static boolean isJPEG(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == (byte) -1 && bArr[1] == (byte) -40;
    }

    private static boolean isGIF(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == (byte) 71 && bArr[1] == (byte) 73 && bArr[2] == (byte) 70 && bArr[3] == (byte) 56 && ((bArr[4] == (byte) 55 || bArr[4] == (byte) 57) && bArr[5] == (byte) 97);
    }

    private static boolean isPNG(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == (byte) -119 && bArr[1] == (byte) 80 && bArr[2] == (byte) 78 && bArr[3] == (byte) 71 && bArr[4] == (byte) 13 && bArr[5] == (byte) 10 && bArr[6] == (byte) 26 && bArr[7] == (byte) 10;
    }

    private static boolean isBMP(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == (byte) 66 && bArr[1] == (byte) 77;
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2, boolean z) {
        return scale(bitmap, i, i2, z);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2, boolean z) {
        return scale(bitmap, f, f2, z);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, @IntRange(from = 0, to = 100) int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, @IntRange(from = 0, to = 100) int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j, boolean z) {
        int i = 100;
        if (isEmptyBitmap(bitmap) || j <= 0) {
            return null;
        }
        byte[] toByteArray;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (((long) byteArrayOutputStream.size()) <= j) {
            toByteArray = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (((long) byteArrayOutputStream.size()) >= j) {
                toByteArray = byteArrayOutputStream.toByteArray();
            } else {
                int i2 = 0;
                int i3 = 0;
                while (i3 < i) {
                    i2 = (i3 + i) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
                    int size = byteArrayOutputStream.size();
                    if (((long) size) == j) {
                        break;
                    } else if (((long) size) > j) {
                        i = i2 - 1;
                    } else {
                        i3 = i2 + 1;
                    }
                }
                if (i == i2 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(CompressFormat.JPEG, i3, byteArrayOutputStream);
                }
                toByteArray = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i) {
        return compressBySampleSize(bitmap, i, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Options options = new Options();
        options.inSampleSize = i;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2) {
        return compressBySampleSize(bitmap, i, i2, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
    }

    private static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        while (true) {
            i4 >>= 1;
            if (i4 < i) {
                break;
            }
            i3 >>= 1;
            if (i3 < i2) {
                break;
            }
            i5 <<= 1;
        }
        return i5;
    }

    private static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    private static boolean createFileByDeleteOldFile(File file) {
        boolean z = false;
        if (file != null && ((!file.exists() || file.delete()) && createOrExistsDir(file.getParentFile()))) {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static byte[] input2Byte(InputStream inputStream) {
        byte[] bArr = null;
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr2, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
                throw th;
            }
        }
        return bArr;
    }
}
