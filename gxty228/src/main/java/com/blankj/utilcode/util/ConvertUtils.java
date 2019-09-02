package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class ConvertUtils {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String bytes2Bits(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            for (int i = 7; i >= 0; i--) {
                stringBuilder.append(((b >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] bits2Bytes(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = "0" + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (length = 0; length < 8; length++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + length) - 48));
            }
        }
        return bArr;
    }

    public static char[] bytes2Chars(byte[] bArr) {
        char[] cArr = null;
        if (bArr != null) {
            int length = bArr.length;
            if (length > 0) {
                cArr = new char[length];
                for (int i = 0; i < length; i++) {
                    cArr[i] = (char) (bArr[i] & 255);
                }
            }
        }
        return cArr;
    }

    public static byte[] chars2Bytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static String bytes2HexString(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        if (length <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = hexDigits[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = hexDigits[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexString2Bytes(String str) {
        if (isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] toCharArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Int(toCharArray[i]) << 4) | hex2Int(toCharArray[i + 1]));
        }
        return bArr;
    }

    private static int hex2Int(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException();
    }

    public static long memorySize2Byte(long j, int i) {
        if (j < 0) {
            return -1;
        }
        return ((long) i) * j;
    }

    public static double byte2MemorySize(long j, int i) {
        if (j < 0) {
            return -1.0d;
        }
        return ((double) j) / ((double) i);
    }

    @SuppressLint({"DefaultLocale"})
    public static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.format("%.3fB", new Object[]{Double.valueOf((double) j)});
        } else if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.format("%.3fKB", new Object[]{Double.valueOf(((double) j) / 1024.0d)});
        } else if (j < 1073741824) {
            return String.format("%.3fMB", new Object[]{Double.valueOf(((double) j) / 1048576.0d)});
        } else {
            return String.format("%.3fGB", new Object[]{Double.valueOf(((double) j) / 1.073741824E9d)});
        }
    }

    public static long timeSpan2Millis(long j, int i) {
        return ((long) i) * j;
    }

    public static long millis2TimeSpan(long j, int i) {
        return j / ((long) i);
    }

    @SuppressLint({"DefaultLocale"})
    public static String millis2FitTimeSpan(long j, int i) {
        if (j <= 0 || i <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] strArr = new String[]{"天", "小时", "分钟", "秒", "毫秒"};
        int[] iArr = new int[]{TimeConstants.DAY, TimeConstants.HOUR, TimeConstants.MIN, 1000, 1};
        int min = Math.min(i, 5);
        long j2 = j;
        for (int i2 = 0; i2 < min; i2++) {
            if (j2 >= ((long) iArr[i2])) {
                long j3 = j2 / ((long) iArr[i2]);
                j2 -= ((long) iArr[i2]) * j3;
                stringBuilder.append(j3).append(strArr[i2]);
            }
        }
        return stringBuilder.toString();
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            inputStream.close();
            return byteArrayOutputStream;
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
                return null;
            } catch (IOException e22) {
                e22.printStackTrace();
                return null;
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

    public ByteArrayInputStream output2InputStream(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }

    public static byte[] inputStream2Bytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return input2OutputStream(inputStream).toByteArray();
    }

    public static InputStream bytes2InputStream(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    public static byte[] outputStream2Bytes(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    public static OutputStream bytes2OutputStream(byte[] bArr) {
        IOException iOException;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            OutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream3.write(bArr);
                if (byteArrayOutputStream3 == null) {
                    return byteArrayOutputStream3;
                }
                try {
                    byteArrayOutputStream3.close();
                    return byteArrayOutputStream3;
                } catch (IOException e) {
                    e.printStackTrace();
                    return byteArrayOutputStream3;
                }
            } catch (IOException e2) {
                IOException iOException2 = e2;
                OutputStream outputStream = byteArrayOutputStream3;
                iOException = iOException2;
                try {
                    iOException.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException iOException3) {
                            iOException3.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                OutputStream outputStream2 = byteArrayOutputStream3;
                th = th4;
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            iOException3 = e4;
            byteArrayOutputStream = null;
            iOException3.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th;
        }
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream == null || isSpace(str)) {
            return "";
        }
        try {
            return new String(inputStream2Bytes(inputStream), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static InputStream string2InputStream(String str, String str2) {
        if (str == null || isSpace(str2)) {
            return null;
        }
        try {
            return new ByteArrayInputStream(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String outputStream2String(OutputStream outputStream, String str) {
        if (outputStream == null || isSpace(str)) {
            return "";
        }
        try {
            return new String(outputStream2Bytes(outputStream), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static OutputStream string2OutputStream(String str, String str2) {
        OutputStream outputStream = null;
        if (!(str == null || isSpace(str2))) {
            try {
                outputStream = bytes2OutputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return outputStream;
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
        return bArr == null ? null : bitmap2Drawable(bytes2Bitmap(bArr));
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

    public static int dp2px(float f) {
        return (int) ((Utils.getApp().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int px2dp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((Utils.getApp().getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
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
}
