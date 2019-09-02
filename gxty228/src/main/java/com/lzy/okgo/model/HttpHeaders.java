package com.lzy.okgo.model;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.ajguan.library.BuildConfig;
import com.amap.api.maps.AMap;
import com.lzy.okgo.a;
import com.lzy.okgo.f.d;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public class HttpHeaders implements Serializable {
    public static final String FORMAT_HTTP_DATA = "EEE, dd MMM y HH:mm:ss 'GMT'";
    public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
    public static final String HEAD_KEY_ACCEPT = "Accept";
    public static final String HEAD_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEAD_KEY_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEAD_KEY_CACHE_CONTROL = "Cache-Control";
    public static final String HEAD_KEY_CONNECTION = "Connection";
    public static final String HEAD_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEAD_KEY_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEAD_KEY_CONTENT_LENGTH = "Content-Length";
    public static final String HEAD_KEY_CONTENT_RANGE = "Content-Range";
    public static final String HEAD_KEY_CONTENT_TYPE = "Content-Type";
    public static final String HEAD_KEY_COOKIE = "Cookie";
    public static final String HEAD_KEY_COOKIE2 = "Cookie2";
    public static final String HEAD_KEY_DATE = "Date";
    public static final String HEAD_KEY_EXPIRES = "Expires";
    public static final String HEAD_KEY_E_TAG = "ETag";
    public static final String HEAD_KEY_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEAD_KEY_IF_NONE_MATCH = "If-None-Match";
    public static final String HEAD_KEY_LAST_MODIFIED = "Last-Modified";
    public static final String HEAD_KEY_LOCATION = "Location";
    public static final String HEAD_KEY_PRAGMA = "Pragma";
    public static final String HEAD_KEY_RANGE = "Range";
    public static final String HEAD_KEY_RESPONSE_CODE = "ResponseCode";
    public static final String HEAD_KEY_RESPONSE_MESSAGE = "ResponseMessage";
    public static final String HEAD_KEY_SET_COOKIE = "Set-Cookie";
    public static final String HEAD_KEY_SET_COOKIE2 = "Set-Cookie2";
    public static final String HEAD_KEY_USER_AGENT = "User-Agent";
    public static final String HEAD_VALUE_ACCEPT_ENCODING = "gzip, deflate";
    public static final String HEAD_VALUE_CONNECTION_CLOSE = "close";
    public static final String HEAD_VALUE_CONNECTION_KEEP_ALIVE = "keep-alive";
    private static String a = null;
    private static String b = null;
    private static final long serialVersionUID = 8458647755751403873L;
    public LinkedHashMap<String, String> headersMap;

    private void a() {
        this.headersMap = new LinkedHashMap();
    }

    public HttpHeaders() {
        a();
    }

    public HttpHeaders(String str, String str2) {
        a();
        put(str, str2);
    }

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            this.headersMap.put(str, str2);
        }
    }

    public void put(HttpHeaders httpHeaders) {
        if (httpHeaders != null && httpHeaders.headersMap != null && !httpHeaders.headersMap.isEmpty()) {
            this.headersMap.putAll(httpHeaders.headersMap);
        }
    }

    public String get(String str) {
        return (String) this.headersMap.get(str);
    }

    public String remove(String str) {
        return (String) this.headersMap.remove(str);
    }

    public void clear() {
        this.headersMap.clear();
    }

    public Set<String> getNames() {
        return this.headersMap.keySet();
    }

    public final String toJSONString() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : this.headersMap.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        } catch (Throwable e) {
            d.a(e);
        }
        return jSONObject.toString();
    }

    public static long getDate(String str) {
        try {
            return parseGMTToMillis(str);
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String getDate(long j) {
        return formatMillisToGMT(j);
    }

    public static long getExpiration(String str) {
        try {
            return parseGMTToMillis(str);
        } catch (ParseException e) {
            return -1;
        }
    }

    public static long getLastModified(String str) {
        try {
            return parseGMTToMillis(str);
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String getCacheControl(String str, String str2) {
        if (str != null) {
            return str;
        }
        if (str2 != null) {
            return str2;
        }
        return null;
    }

    public static void setAcceptLanguage(String str) {
        a = str;
    }

    public static String getAcceptLanguage() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        Object country = locale.getCountry();
        StringBuilder stringBuilder = new StringBuilder(language);
        if (!TextUtils.isEmpty(country)) {
            stringBuilder.append('-').append(country).append(',').append(language).append(";q=0.8");
        }
        a = stringBuilder.toString();
        return a;
    }

    public static void setUserAgent(String str) {
        b = str;
    }

    public static String getUserAgent() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String string;
        String str;
        try {
            string = a.a().b().getString(((Integer) Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
        } catch (Exception e) {
            string = null;
        }
        if (TextUtils.isEmpty(string)) {
            string = "okhttp-okgo/jeasonlzy";
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = VERSION.RELEASE;
        if (str2.length() > 0) {
            stringBuffer.append(str2);
        } else {
            stringBuffer.append(BuildConfig.VERSION_NAME);
        }
        stringBuffer.append("; ");
        str2 = locale.getLanguage();
        if (str2 != null) {
            stringBuffer.append(str2.toLowerCase(locale));
            Object country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase(locale));
            }
        } else {
            stringBuffer.append(AMap.ENGLISH);
        }
        if ("REL".equals(VERSION.CODENAME)) {
            str = Build.MODEL;
            if (str.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str);
            }
        }
        str = Build.ID;
        if (str.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(str);
        }
        b = String.format(string, new Object[]{stringBuffer, "Mobile "});
        return b;
    }

    public static long parseGMTToMillis(String str) throws ParseException {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        simpleDateFormat.setTimeZone(GMT_TIME_ZONE);
        return simpleDateFormat.parse(str).getTime();
    }

    public static String formatMillisToGMT(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        simpleDateFormat.setTimeZone(GMT_TIME_ZONE);
        return simpleDateFormat.format(date);
    }

    public String toString() {
        return "HttpHeaders{headersMap=" + this.headersMap + '}';
    }
}
