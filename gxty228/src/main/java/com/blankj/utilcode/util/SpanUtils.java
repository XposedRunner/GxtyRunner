package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LeadingMarginSpan.Standard;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public final class SpanUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    private static final int COLOR_DEFAULT = -16777217;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int alignImage;
    private int alignLine;
    private Alignment alignment;
    private int backgroundColor;
    private float blurRadius;
    private int bulletColor;
    private int bulletGapWidth;
    private int bulletRadius;
    private ClickableSpan clickSpan;
    private int first;
    private int flag;
    private String fontFamily;
    private int fontSize;
    private boolean fontSizeIsDp;
    private int foregroundColor;
    private Bitmap imageBitmap;
    private Drawable imageDrawable;
    private int imageResourceId;
    private Uri imageUri;
    private boolean isBold;
    private boolean isBoldItalic;
    private boolean isItalic;
    private boolean isStrikethrough;
    private boolean isSubscript;
    private boolean isSuperscript;
    private boolean isUnderline;
    private int lineHeight;
    private SpannableStringBuilder mBuilder = new SpannableStringBuilder();
    private CharSequence mText = "";
    private int mType;
    private final int mTypeCharSequence = 0;
    private final int mTypeImage = 1;
    private final int mTypeSpace = 2;
    private float proportion;
    private int quoteColor;
    private int quoteGapWidth;
    private int rest;
    private Shader shader;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int spaceColor;
    private int spaceSize;
    private Object[] spans;
    private int stripeWidth;
    private Blur style;
    private Typeface typeface;
    private String url;
    private float xProportion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    class CustomBulletSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int radius;
        private Path sBulletPath;

        private CustomBulletSpan(int i, int i2, int i3) {
            this.sBulletPath = null;
            this.color = i;
            this.radius = i2;
            this.gapWidth = i3;
        }

        public int getLeadingMargin(boolean z) {
            return (this.radius * 2) + this.gapWidth;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.color);
                paint.setStyle(Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.sBulletPath == null) {
                        this.sBulletPath = new Path();
                        this.sBulletPath.addCircle(0.0f, 0.0f, (float) this.radius, Direction.CW);
                    }
                    canvas.save();
                    canvas.translate((float) ((this.radius * i2) + i), ((float) (i3 + i5)) / 2.0f);
                    canvas.drawPath(this.sBulletPath, paint);
                    canvas.restore();
                } else {
                    canvas.drawCircle((float) ((this.radius * i2) + i), ((float) (i3 + i5)) / 2.0f, (float) this.radius, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }
    }

    abstract class CustomDynamicDrawableSpan extends ReplacementSpan {
        static final int ALIGN_BASELINE = 1;
        static final int ALIGN_BOTTOM = 0;
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        private WeakReference<Drawable> mDrawableRef;
        final int mVerticalAlignment;

        public abstract Drawable getDrawable();

        private CustomDynamicDrawableSpan() {
            this.mVerticalAlignment = 0;
        }

        private CustomDynamicDrawableSpan(int i) {
            this.mVerticalAlignment = i;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            if (paint == null) {
                throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            Rect bounds = getCachedDrawable().getBounds();
            if (fontMetricsInt != null) {
                int i3 = fontMetricsInt.bottom - fontMetricsInt.top;
                if (i3 < bounds.height()) {
                    if (this.mVerticalAlignment == 3) {
                        fontMetricsInt.top = fontMetricsInt.top;
                        fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                    } else if (this.mVerticalAlignment == 2) {
                        fontMetricsInt.top = ((-bounds.height()) / 2) - (i3 / 4);
                        fontMetricsInt.bottom = (bounds.height() / 2) - (i3 / 4);
                    } else {
                        fontMetricsInt.top = (-bounds.height()) + fontMetricsInt.bottom;
                        fontMetricsInt.bottom = fontMetricsInt.bottom;
                    }
                    fontMetricsInt.ascent = fontMetricsInt.top;
                    fontMetricsInt.descent = fontMetricsInt.bottom;
                }
            }
            return bounds.right;
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (paint == null) {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else {
                Drawable cachedDrawable = getCachedDrawable();
                Rect bounds = cachedDrawable.getBounds();
                canvas.save();
                if (bounds.height() < i5 - i3) {
                    float f2;
                    if (this.mVerticalAlignment == 3) {
                        f2 = (float) i3;
                    } else if (this.mVerticalAlignment == 2) {
                        f2 = (float) (((i5 + i3) - bounds.height()) / 2);
                    } else if (this.mVerticalAlignment == 1) {
                        f2 = (float) (i4 - bounds.height());
                    } else {
                        f2 = (float) (i5 - bounds.height());
                    }
                    canvas.translate(f, f2);
                } else {
                    canvas.translate(f, (float) i3);
                }
                cachedDrawable.draw(canvas);
                canvas.restore();
            }
        }

        private Drawable getCachedDrawable() {
            WeakReference weakReference = this.mDrawableRef;
            Drawable drawable = null;
            if (weakReference != null) {
                drawable = (Drawable) weakReference.get();
            }
            if (drawable != null) {
                return drawable;
            }
            drawable = getDrawable();
            this.mDrawableRef = new WeakReference(drawable);
            return drawable;
        }
    }

    class CustomImageSpan extends CustomDynamicDrawableSpan {
        private Uri mContentUri;
        private Drawable mDrawable;
        private int mResourceId;

        private CustomImageSpan(Bitmap bitmap, int i) {
            super(i);
            this.mDrawable = new BitmapDrawable(Utils.getApp().getResources(), bitmap);
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(Drawable drawable, int i) {
            super(i);
            this.mDrawable = drawable;
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(Uri uri, int i) {
            super(i);
            this.mContentUri = uri;
        }

        private CustomImageSpan(int i, @DrawableRes int i2) {
            super(i2);
            this.mResourceId = i;
        }

        public Drawable getDrawable() {
            Drawable bitmapDrawable;
            Throwable e;
            if (this.mDrawable != null) {
                return this.mDrawable;
            }
            if (this.mContentUri != null) {
                try {
                    InputStream openInputStream = Utils.getApp().getContentResolver().openInputStream(this.mContentUri);
                    bitmapDrawable = new BitmapDrawable(Utils.getApp().getResources(), BitmapFactory.decodeStream(openInputStream));
                    try {
                        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                        if (openInputStream == null) {
                            return bitmapDrawable;
                        }
                        openInputStream.close();
                        return bitmapDrawable;
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                        return bitmapDrawable;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    bitmapDrawable = null;
                    e = th;
                    Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                    return bitmapDrawable;
                }
            }
            try {
                bitmapDrawable = ContextCompat.getDrawable(Utils.getApp(), this.mResourceId);
                try {
                    bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                    return bitmapDrawable;
                } catch (Exception e4) {
                    Log.e("sms", "Unable to find resource: " + this.mResourceId);
                    return bitmapDrawable;
                }
            } catch (Exception e5) {
                bitmapDrawable = null;
                Log.e("sms", "Unable to find resource: " + this.mResourceId);
                return bitmapDrawable;
            }
        }
    }

    class CustomLineHeightSpan extends CharacterStyle implements LineHeightSpan {
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        private final int height;
        final int mVerticalAlignment;

        CustomLineHeightSpan(int i, int i2) {
            this.height = i;
            this.mVerticalAlignment = i2;
        }

        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, FontMetricsInt fontMetricsInt) {
            int i5 = this.height - (((fontMetricsInt.descent + i4) - fontMetricsInt.ascent) - i3);
            if (this.mVerticalAlignment == 3) {
                fontMetricsInt.descent = i5 + fontMetricsInt.descent;
            } else if (this.mVerticalAlignment == 2) {
                fontMetricsInt.descent += i5 / 2;
                fontMetricsInt.ascent -= i5 / 2;
            } else {
                fontMetricsInt.ascent -= i5;
            }
            i5 = this.height - (((fontMetricsInt.bottom + i4) - fontMetricsInt.top) - i3);
            if (this.mVerticalAlignment == 3) {
                fontMetricsInt.top = i5 + fontMetricsInt.top;
            } else if (this.mVerticalAlignment == 2) {
                fontMetricsInt.bottom += i5 / 2;
                fontMetricsInt.top -= i5 / 2;
            } else {
                fontMetricsInt.top -= i5;
            }
        }

        public void updateDrawState(TextPaint textPaint) {
        }
    }

    class CustomQuoteSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int stripeWidth;

        private CustomQuoteSpan(int i, int i2, int i3) {
            this.color = i;
            this.stripeWidth = i2;
            this.gapWidth = i3;
        }

        public int getLeadingMargin(boolean z) {
            return this.stripeWidth + this.gapWidth;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Style.FILL);
            paint.setColor(this.color);
            canvas.drawRect((float) i, (float) i3, (float) ((this.stripeWidth * i2) + i), (float) i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }
    }

    @SuppressLint({"ParcelCreator"})
    class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.newType = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        public void updateMeasureState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        private void apply(Paint paint, Typeface typeface) {
            int i;
            Typeface typeface2 = paint.getTypeface();
            if (typeface2 == null) {
                i = 0;
            } else {
                i = typeface2.getStyle();
            }
            i &= typeface.getStyle() ^ -1;
            if ((i & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((i & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }
    }

    class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader mShader;

        private ShaderSpan(Shader shader) {
            this.mShader = shader;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.mShader);
        }
    }

    class ShadowSpan extends CharacterStyle implements UpdateAppearance {
        private float dx;
        private float dy;
        private float radius;
        private int shadowColor;

        private ShadowSpan(float f, float f2, float f3, int i) {
            this.radius = f;
            this.dx = f2;
            this.dy = f3;
            this.shadowColor = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.radius, this.dx, this.dy, this.shadowColor);
        }
    }

    class SpaceSpan extends ReplacementSpan {
        private final int color;
        private final int width;

        private SpaceSpan(SpanUtils spanUtils, int i) {
            this(i, 0);
        }

        private SpaceSpan(int i, int i2) {
            this.width = i;
            this.color = i2;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable FontMetricsInt fontMetricsInt) {
            if (paint != null) {
                return this.width;
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (paint == null) {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else {
                Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setStyle(Style.FILL);
                paint.setColor(this.color);
                canvas.drawRect(f, (float) i3, f + ((float) this.width), (float) i5, paint);
                paint.setStyle(style);
                paint.setColor(color);
            }
        }
    }

    public SpanUtils() {
        setDefault();
    }

    private void setDefault() {
        this.flag = 33;
        this.foregroundColor = COLOR_DEFAULT;
        this.backgroundColor = COLOR_DEFAULT;
        this.lineHeight = -1;
        this.quoteColor = COLOR_DEFAULT;
        this.first = -1;
        this.bulletColor = COLOR_DEFAULT;
        this.fontSize = -1;
        this.proportion = -1.0f;
        this.xProportion = -1.0f;
        this.isStrikethrough = false;
        this.isUnderline = false;
        this.isSuperscript = false;
        this.isSubscript = false;
        this.isBold = false;
        this.isItalic = false;
        this.isBoldItalic = false;
        this.fontFamily = null;
        this.typeface = null;
        this.alignment = null;
        this.clickSpan = null;
        this.url = null;
        this.blurRadius = -1.0f;
        this.shader = null;
        this.shadowRadius = -1.0f;
        this.spans = null;
        this.imageBitmap = null;
        this.imageDrawable = null;
        this.imageUri = null;
        this.imageResourceId = -1;
        this.spaceSize = -1;
    }

    public SpanUtils setFlag(int i) {
        this.flag = i;
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt int i) {
        this.foregroundColor = i;
        return this;
    }

    public SpanUtils setBackgroundColor(@ColorInt int i) {
        this.backgroundColor = i;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i) {
        return setLineHeight(i, 2);
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i, int i2) {
        this.lineHeight = i;
        this.alignLine = i2;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt int i) {
        return setQuoteColor(i, 2, 2);
    }

    public SpanUtils setQuoteColor(@ColorInt int i, @IntRange(from = 1) int i2, @IntRange(from = 0) int i3) {
        this.quoteColor = i;
        this.stripeWidth = i2;
        this.quoteGapWidth = i3;
        return this;
    }

    public SpanUtils setLeadingMargin(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        this.first = i;
        this.rest = i2;
        return this;
    }

    public SpanUtils setBullet(@IntRange(from = 0) int i) {
        return setBullet(0, 3, i);
    }

    public SpanUtils setBullet(@ColorInt int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.bulletColor = i;
        this.bulletRadius = i2;
        this.bulletGapWidth = i3;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i) {
        return setFontSize(i, false);
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i, boolean z) {
        this.fontSize = i;
        this.fontSizeIsDp = z;
        return this;
    }

    public SpanUtils setFontProportion(float f) {
        this.proportion = f;
        return this;
    }

    public SpanUtils setFontXProportion(float f) {
        this.xProportion = f;
        return this;
    }

    public SpanUtils setStrikethrough() {
        this.isStrikethrough = true;
        return this;
    }

    public SpanUtils setUnderline() {
        this.isUnderline = true;
        return this;
    }

    public SpanUtils setSuperscript() {
        this.isSuperscript = true;
        return this;
    }

    public SpanUtils setSubscript() {
        this.isSubscript = true;
        return this;
    }

    public SpanUtils setBold() {
        this.isBold = true;
        return this;
    }

    public SpanUtils setItalic() {
        this.isItalic = true;
        return this;
    }

    public SpanUtils setBoldItalic() {
        this.isBoldItalic = true;
        return this;
    }

    public SpanUtils setFontFamily(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'fontFamily' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.fontFamily = str;
        return this;
    }

    public SpanUtils setTypeface(@NonNull Typeface typeface) {
        if (typeface == null) {
            throw new NullPointerException("Argument 'typeface' of type Typeface (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.typeface = typeface;
        return this;
    }

    public SpanUtils setAlign(@NonNull Alignment alignment) {
        if (alignment == null) {
            throw new NullPointerException("Argument 'alignment' of type Alignment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.alignment = alignment;
        return this;
    }

    public SpanUtils setClickSpan(@NonNull ClickableSpan clickableSpan) {
        if (clickableSpan == null) {
            throw new NullPointerException("Argument 'clickSpan' of type ClickableSpan (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.clickSpan = clickableSpan;
        return this;
    }

    public SpanUtils setUrl(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'url' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.url = str;
        return this;
    }

    public SpanUtils setBlur(@FloatRange(from = 0.0d, fromInclusive = false) float f, Blur blur) {
        this.blurRadius = f;
        this.style = blur;
        return this;
    }

    public SpanUtils setShader(@NonNull Shader shader) {
        if (shader == null) {
            throw new NullPointerException("Argument 'shader' of type Shader (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.shader = shader;
        return this;
    }

    public SpanUtils setShadow(@FloatRange(from = 0.0d, fromInclusive = false) float f, float f2, float f3, int i) {
        this.shadowRadius = f;
        this.shadowDx = f2;
        this.shadowDy = f3;
        this.shadowColor = i;
        return this;
    }

    public SpanUtils setSpans(@NonNull Object... objArr) {
        if (objArr == null) {
            throw new NullPointerException("Argument 'spans' of type Object[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (objArr.length > 0) {
            this.spans = objArr;
        }
        return this;
    }

    public SpanUtils append(@NonNull CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        apply(0);
        this.mText = charSequence;
        return this;
    }

    public SpanUtils appendLine() {
        apply(0);
        this.mText = LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendLine(@NonNull CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        apply(0);
        this.mText = charSequence + LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap) {
        if (bitmap != null) {
            return appendImage(bitmap, 0);
        }
        throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap, int i) {
        if (bitmap == null) {
            throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        apply(1);
        this.imageBitmap = bitmap;
        this.alignImage = i;
        return this;
    }

    public SpanUtils appendImage(@NonNull Drawable drawable) {
        if (drawable != null) {
            return appendImage(drawable, 0);
        }
        throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Drawable drawable, int i) {
        if (drawable == null) {
            throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        apply(1);
        this.imageDrawable = drawable;
        this.alignImage = i;
        return this;
    }

    public SpanUtils appendImage(@NonNull Uri uri) {
        if (uri != null) {
            return appendImage(uri, 0);
        }
        throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Uri uri, int i) {
        if (uri == null) {
            throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        apply(1);
        this.imageUri = uri;
        this.alignImage = i;
        return this;
    }

    public SpanUtils appendImage(@DrawableRes int i) {
        return appendImage(i, 0);
    }

    public SpanUtils appendImage(@DrawableRes int i, int i2) {
        append(Character.toString('\u0000'));
        apply(1);
        this.imageResourceId = i;
        this.alignImage = i2;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i) {
        return appendSpace(i, 0);
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i, @ColorInt int i2) {
        apply(2);
        this.spaceSize = i;
        this.spaceColor = i2;
        return this;
    }

    private void apply(int i) {
        applyLast();
        this.mType = i;
    }

    public SpannableStringBuilder create() {
        applyLast();
        return this.mBuilder;
    }

    private void applyLast() {
        if (this.mType == 0) {
            updateCharCharSequence();
        } else if (this.mType == 1) {
            updateImage();
        } else if (this.mType == 2) {
            updateSpace();
        }
        setDefault();
    }

    private void updateCharCharSequence() {
        if (this.mText.length() != 0) {
            int length = this.mBuilder.length();
            this.mBuilder.append(this.mText);
            int length2 = this.mBuilder.length();
            if (this.foregroundColor != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), length, length2, this.flag);
            }
            if (this.backgroundColor != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), length, length2, this.flag);
            }
            if (this.first != -1) {
                this.mBuilder.setSpan(new Standard(this.first, this.rest), length, length2, this.flag);
            }
            if (this.quoteColor != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new CustomQuoteSpan(this.quoteColor, this.stripeWidth, this.quoteGapWidth), length, length2, this.flag);
            }
            if (this.bulletColor != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new CustomBulletSpan(this.bulletColor, this.bulletRadius, this.bulletGapWidth), length, length2, this.flag);
            }
            if (this.fontSize != -1) {
                this.mBuilder.setSpan(new AbsoluteSizeSpan(this.fontSize, this.fontSizeIsDp), length, length2, this.flag);
            }
            if (this.proportion != -1.0f) {
                this.mBuilder.setSpan(new RelativeSizeSpan(this.proportion), length, length2, this.flag);
            }
            if (this.xProportion != -1.0f) {
                this.mBuilder.setSpan(new ScaleXSpan(this.xProportion), length, length2, this.flag);
            }
            if (this.lineHeight != -1) {
                this.mBuilder.setSpan(new CustomLineHeightSpan(this.lineHeight, this.alignLine), length, length2, this.flag);
            }
            if (this.isStrikethrough) {
                this.mBuilder.setSpan(new StrikethroughSpan(), length, length2, this.flag);
            }
            if (this.isUnderline) {
                this.mBuilder.setSpan(new UnderlineSpan(), length, length2, this.flag);
            }
            if (this.isSuperscript) {
                this.mBuilder.setSpan(new SuperscriptSpan(), length, length2, this.flag);
            }
            if (this.isSubscript) {
                this.mBuilder.setSpan(new SubscriptSpan(), length, length2, this.flag);
            }
            if (this.isBold) {
                this.mBuilder.setSpan(new StyleSpan(1), length, length2, this.flag);
            }
            if (this.isItalic) {
                this.mBuilder.setSpan(new StyleSpan(2), length, length2, this.flag);
            }
            if (this.isBoldItalic) {
                this.mBuilder.setSpan(new StyleSpan(3), length, length2, this.flag);
            }
            if (this.fontFamily != null) {
                this.mBuilder.setSpan(new TypefaceSpan(this.fontFamily), length, length2, this.flag);
            }
            if (this.typeface != null) {
                this.mBuilder.setSpan(new CustomTypefaceSpan(this.typeface), length, length2, this.flag);
            }
            if (this.alignment != null) {
                this.mBuilder.setSpan(new AlignmentSpan.Standard(this.alignment), length, length2, this.flag);
            }
            if (this.clickSpan != null) {
                this.mBuilder.setSpan(this.clickSpan, length, length2, this.flag);
            }
            if (this.url != null) {
                this.mBuilder.setSpan(new URLSpan(this.url), length, length2, this.flag);
            }
            if (this.blurRadius != -1.0f) {
                this.mBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.blurRadius, this.style)), length, length2, this.flag);
            }
            if (this.shader != null) {
                this.mBuilder.setSpan(new ShaderSpan(this.shader), length, length2, this.flag);
            }
            if (this.shadowRadius != -1.0f) {
                this.mBuilder.setSpan(new ShadowSpan(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor), length, length2, this.flag);
            }
            if (this.spans != null) {
                for (Object span : this.spans) {
                    this.mBuilder.setSpan(span, length, length2, this.flag);
                }
            }
        }
    }

    private void updateImage() {
        int length = this.mBuilder.length();
        this.mBuilder.append("<img>");
        int i = length + 5;
        if (this.imageBitmap != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageBitmap, this.alignImage), length, i, this.flag);
        } else if (this.imageDrawable != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageDrawable, this.alignImage), length, i, this.flag);
        } else if (this.imageUri != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageUri, this.alignImage), length, i, this.flag);
        } else if (this.imageResourceId != -1) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageResourceId, this.alignImage), length, i, this.flag);
        }
    }

    private void updateSpace() {
        int length = this.mBuilder.length();
        this.mBuilder.append("< >");
        this.mBuilder.setSpan(new SpaceSpan(this.spaceSize, this.spaceColor), length, length + 3, this.flag);
    }
}
