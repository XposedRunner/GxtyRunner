package com.lzy.okgo.f;

import cn.jiguang.net.HttpUtils;
import com.lzy.okgo.a;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.HttpParams.FileWrapper;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

/* compiled from: HttpUtils */
public class b {
    public static String a(String str, Map<String, List<String>> map) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            if (str.indexOf(38) > 0 || str.indexOf(63) > 0) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            } else {
                stringBuilder.append(HttpUtils.URL_AND_PARA_SEPARATOR);
            }
            for (Entry entry : map.entrySet()) {
                for (String encode : (List) entry.getValue()) {
                    stringBuilder.append((String) entry.getKey()).append(HttpUtils.EQUAL_SIGN).append(URLEncoder.encode(encode, "UTF-8")).append(HttpUtils.PARAMETERS_SEPARATOR);
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            str = stringBuilder.toString();
        } catch (Throwable e) {
            d.a(e);
        }
        return str;
    }

    public static Builder a(Builder builder, HttpHeaders httpHeaders) {
        if (!httpHeaders.headersMap.isEmpty()) {
            Headers.Builder builder2 = new Headers.Builder();
            try {
                for (Entry entry : httpHeaders.headersMap.entrySet()) {
                    builder2.add((String) entry.getKey(), (String) entry.getValue());
                }
            } catch (Throwable e) {
                d.a(e);
            }
            builder.headers(builder2.build());
        }
        return builder;
    }

    public static RequestBody a(HttpParams httpParams, boolean z) {
        if (!httpParams.fileParamsMap.isEmpty() || z) {
            MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (!httpParams.urlParamsMap.isEmpty()) {
                for (Entry entry : httpParams.urlParamsMap.entrySet()) {
                    for (String addFormDataPart : (List) entry.getValue()) {
                        type.addFormDataPart((String) entry.getKey(), addFormDataPart);
                    }
                }
            }
            for (Entry entry2 : httpParams.fileParamsMap.entrySet()) {
                for (FileWrapper fileWrapper : (List) entry2.getValue()) {
                    type.addFormDataPart((String) entry2.getKey(), fileWrapper.fileName, RequestBody.create(fileWrapper.contentType, fileWrapper.file));
                }
            }
            return type.build();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String str : httpParams.urlParamsMap.keySet()) {
            for (String addFormDataPart2 : (List) httpParams.urlParamsMap.get(str)) {
                builder.add(str, addFormDataPart2);
            }
        }
        return builder.build();
    }

    public static MediaType a(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        if (contentTypeFor == null) {
            return HttpParams.MEDIA_TYPE_STREAM;
        }
        return MediaType.parse(contentTypeFor);
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static void a(Runnable runnable) {
        a.a().c().post(runnable);
    }
}
