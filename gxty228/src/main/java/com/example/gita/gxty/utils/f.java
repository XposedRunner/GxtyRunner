package com.example.gita.gxty.utils;

import android.app.Application;
import com.blankj.utilcode.util.AppUtils;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.a;
import com.lzy.okgo.cookie.a.b;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor.Level;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient.Builder;

/* compiled from: HttpUtils */
public class f {
    public static void a(Application application) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.put("versionCode", "" + AppUtils.getAppVersionCode());
            httpHeaders.put("versionName", "" + AppUtils.getAppVersionName());
            httpHeaders.put("platform", "android");
            httpHeaders.put("xxversionxx", "20180601");
            HttpParams httpParams = new HttpParams();
            Builder builder = new Builder();
            if (h.a) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OkGo");
                httpLoggingInterceptor.a(Level.BODY);
                httpLoggingInterceptor.a(java.util.logging.Level.INFO);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            builder.readTimeout(60000, TimeUnit.MILLISECONDS);
            builder.writeTimeout(60000, TimeUnit.MILLISECONDS);
            builder.connectTimeout(60000, TimeUnit.MILLISECONDS);
            builder.cookieJar(new a(new b(application)));
            try {
                com.lzy.okgo.a.a().a(application).a(builder.build()).a(CacheMode.NO_CACHE).a(-1).a(3).a(httpHeaders).a(httpParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
