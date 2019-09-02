package com.autonavi.amap.mapcore.tools;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class TextTextureGenerator {
    private static final int ALIGNCENTER = 51;
    private static final int ALIGNLEFT = 49;
    private static final int ALIGNRIGHT = 50;
    static final int AN_LABEL_MAXCHARINLINE = 7;
    static final int AN_LABEL_MULITYLINE_SPAN = 2;
    public static final int CHAR_MAX = 256;
    public static final int MIN_DIFF_SIZE = 4;
    private float baseLine = 0.0f;
    private float startX = 0.0f;
    private int textFontsize = -1;
    private int textFontsizeTrue = -1;
    private Paint textPaint = null;

    public static int getNearstSize2N(int i) {
        int i2 = 1;
        while (i > i2) {
            i2 *= 2;
        }
        return i2;
    }

    public TextTextureGenerator() {
        createTextParam();
    }

    private void createTextParam() {
        this.textFontsizeTrue = this.textFontsize - 2;
        this.textPaint = newPaint(null, this.textFontsizeTrue, 49);
        float f = ((float) (this.textFontsize - this.textFontsizeTrue)) / 2.0f;
        this.startX = f;
        float f2 = 7.3242188f;
        float f3 = -27.832031f;
        try {
            FontMetrics fontMetrics = this.textPaint.getFontMetrics();
            f2 = fontMetrics.descent;
            f3 = fontMetrics.ascent;
        } catch (Exception e) {
        }
        this.baseLine = (((((float) this.textFontsizeTrue) - (f3 + f2)) / 2.0f) + f) + 0.5f;
    }

    public byte[] getTextPixelBuffer(int i, int i2) {
        if (this.textFontsize != i2) {
            this.textFontsize = i2;
            createTextParam();
        }
        try {
            char[] cArr = new char[]{(char) i};
            float f = this.baseLine;
            Bitmap createBitmap = Bitmap.createBitmap(this.textFontsize, this.textFontsize, Config.ALPHA_8);
            Canvas canvas = new Canvas(createBitmap);
            byte[] bArr = new byte[(this.textFontsize * this.textFontsize)];
            Buffer wrap = ByteBuffer.wrap(bArr);
            float measureText = this.textPaint.measureText(String.valueOf((char) i));
            if (cArr[0] > '\u0000' && cArr[0] < 'Ā') {
                f -= 1.5f;
            }
            Align textAlign = this.textPaint.getTextAlign();
            float f2 = measureText - ((float) this.textFontsizeTrue);
            if (textAlign == Align.CENTER || f2 < 4.0f) {
                canvas.drawText(cArr, 0, 1, this.startX, f, this.textPaint);
            } else {
                this.textPaint.setTextAlign(Align.CENTER);
                this.textPaint.setTextSize(((float) this.textFontsizeTrue) - f2);
                canvas.drawText(cArr, 0, 1, (((float) this.textFontsizeTrue) - f2) / 2.0f, f, this.textPaint);
                this.textPaint.setTextAlign(textAlign);
            }
            createBitmap.copyPixelsToBuffer(wrap);
            createBitmap.recycle();
            return bArr;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public byte[] getCharsWidths(int[] iArr) {
        int length = iArr.length;
        byte[] bArr = new byte[length];
        float[] fArr = new float[1];
        for (int i = 0; i < length; i++) {
            fArr[0] = this.textPaint.measureText(((char) iArr[i]) + "");
            bArr[i] = (byte) ((int) (fArr[0] + ((float) (this.textFontsize - this.textFontsizeTrue))));
        }
        return bArr;
    }

    private static Paint newPaint(String str, int i, int i2) {
        Paint textPaint = new TextPaint();
        textPaint.setColor(-1);
        textPaint.setTextSize((float) i);
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTypeface(Typeface.DEFAULT);
        switch (i2) {
            case 49:
                textPaint.setTextAlign(Align.LEFT);
                break;
            case 50:
                textPaint.setTextAlign(Align.RIGHT);
                break;
            case 51:
                textPaint.setTextAlign(Align.CENTER);
                break;
            default:
                textPaint.setTextAlign(Align.LEFT);
                break;
        }
        return textPaint;
    }

    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static float getFontHeight(Paint paint) {
        FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }
}
