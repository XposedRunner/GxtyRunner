package com.blankj.utilcode.util;

import android.os.Build.VERSION;
import android.text.Html;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class EncodeUtils {
    private EncodeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String urlEncode(String str) {
        return urlEncode(str, "UTF-8");
    }

    public static String urlEncode(String str, String str2) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String urlDecode(String str) {
        return urlDecode(str, "UTF-8");
    }

    public static String urlDecode(String str, String str2) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] base64Encode(String str) {
        return base64Encode(str.getBytes());
    }

    public static byte[] base64Encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        return Base64.encode(bArr, 2);
    }

    public static String base64Encode2String(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] base64Decode(String str) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        return Base64.decode(str, 2);
    }

    public static byte[] base64Decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        return Base64.decode(bArr, 2);
    }

    public static String htmlEncode(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            switch (charAt) {
                case '\"':
                    stringBuilder.append("&quot;");
                    break;
                case '&':
                    stringBuilder.append("&amp;");
                    break;
                case '\'':
                    stringBuilder.append("&#39;");
                    break;
                case '<':
                    stringBuilder.append("&lt;");
                    break;
                case '>':
                    stringBuilder.append("&gt;");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static CharSequence htmlDecode(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }
}
