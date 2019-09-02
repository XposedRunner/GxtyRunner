package cn.jiguang.d.d;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.URLUtil;
import cn.jiguang.d.g.h;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import cn.jiguang.net.HttpConstants;
import cn.jiguang.net.HttpRequest;
import cn.jiguang.net.HttpUtils;
import cn.jiguang.net.SSLTrustManager;
import cn.jiguang.service.Protocol;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.bugly.BuglyStrategy.a;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Arrays;
import org.json.JSONObject;

public final class i {
    public static SSLTrustManager a;

    private static int a(Context context, String str, JSONObject jSONObject, boolean z, int i) {
        boolean z2 = false;
        if (i <= 0) {
            d.d("HttpHelper", "sendLogs has retry but failed");
            return -1;
        }
        HttpRequest httpRequest = new HttpRequest(str);
        httpRequest.setConnectTimeout(a.MAX_USERDATA_VALUE_LENGTH);
        httpRequest.setReadTimeout(a.MAX_USERDATA_VALUE_LENGTH);
        httpRequest.setDoOutPut(true);
        httpRequest.setDoInPut(true);
        httpRequest.setUseCaches(false);
        try {
            String jSONObject2 = jSONObject != null ? jSONObject.toString() : "";
            if (k.a(jSONObject2)) {
                d.c("HttpHelper", "report content is null or empty,give up http report");
                return -2;
            }
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT, "application/jason");
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_ENCODING, "gzip");
            httpRequest.setRequestProperty("X-App-Key", cn.jiguang.d.a.d.i(context));
            String a = jSONObject2 == null ? o.a() : o.a(jSONObject2, 2);
            if (k.a(a)) {
                d.c("HttpHelper", "generate report token failed");
            } else {
                a = o.d(a);
                if (k.a(a)) {
                    d.c("HttpHelper", "generate basic authorization failed");
                } else {
                    httpRequest.setRequestProperty("Authorization", "Basic " + a);
                    httpRequest.setRequestProperty("Charset", "UTF-8");
                    z2 = true;
                }
            }
            if (z2) {
                Object a2 = z ? h.a(jSONObject2.getBytes("UTF-8")) : jSONObject2.getBytes();
                httpRequest.setBody(a2);
                httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(a2.length));
                httpRequest.setHaveRspData(false);
                httpRequest.setNeedRetryIfHttpsFailed(true);
                if (a == null) {
                    String str2 = "";
                    try {
                        a2 = Protocol.getCerTificate();
                    } catch (Throwable th) {
                    }
                    if (!TextUtils.isEmpty(a2)) {
                        a = new SSLTrustManager(a2);
                    }
                }
                if (a != null) {
                    httpRequest.setSslTrustManager(a);
                }
                int responseCode = HttpUtils.httpPost(context, httpRequest).getResponseCode();
                d.d("HttpHelper", "status code:" + responseCode);
                switch (responseCode) {
                    case 200:
                        return 200;
                    case 401:
                        d.g("HttpHelper", "401:authorization failed.");
                        return 401;
                    case 404:
                        return 404;
                    case 429:
                        return 429;
                    case HttpConstants.NET_SSL_EXECPTION /*3005*/:
                        return a(context, str, jSONObject, z, i - 1);
                    default:
                        return responseCode / 100 == 5 ? 500 : -5;
                }
            } else {
                d.c("HttpHelper", "generate basic64 authorization token failed,give up http report");
                return -3;
            }
        } catch (ProtocolException e) {
            d.i("HttpHelper", "ProtocolException:" + e.getMessage());
            return -1;
        } catch (IOException e2) {
            d.i("HttpHelper", "IOException:debug" + e2.getMessage());
            return -1;
        } catch (AssertionError e3) {
            d.i("HttpHelper", "Catch AssertionError to avoid http close crash - " + e3.toString());
            return -1;
        } catch (Exception e4) {
            e4.printStackTrace();
            d.i("HttpHelper", "Exception - " + Arrays.toString(e4.getStackTrace()));
            d.i("HttpHelper", "Exception - " + e4.toString());
            return -1;
        }
    }

    public static int a(Context context, JSONObject jSONObject, boolean z) {
        boolean z2 = false;
        String a = o.a(2);
        d.c("HttpHelper", "sendLogs url:" + a);
        d.a("HttpHelper", "Action - checkURLisValide");
        if (!k.a(a)) {
            if (o.b(a)) {
                if (URLUtil.isHttpUrl(a)) {
                    z2 = true;
                } else {
                    d.i("HttpHelper", "The report Url is invalid, give up this http report");
                }
            } else if (URLUtil.isHttpsUrl(a)) {
                z2 = true;
            } else {
                d.i("HttpHelper", "The report Url is invalid, give up this https report");
            }
        }
        return !z2 ? -1 : a(context, a, jSONObject, true, 2);
    }
}
