package cn.jiguang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import cn.jiguang.e.d;
import cn.jiguang.g.g;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;

public class HttpUtils {
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String EQUAL_SIGN = "=";
    public static final String HTTP_DEFUALT_PROXY = "10.0.0.172";
    public static final String PARAMETERS_SEPARATOR = "&";
    public static final String PATHS_SEPARATOR = "/";
    public static final String URL_AND_PARA_SEPARATOR = "?";
    private static final SimpleDateFormat a = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    public abstract class HttpListener {
    }

    private static HttpResponse a(Context context, HttpRequest httpRequest, boolean z) {
        Closeable inputStream;
        MalformedURLException e;
        HttpURLConnection httpURLConnection;
        Closeable closeable;
        Throwable th;
        Closeable closeable2;
        IOException e2;
        HttpResponse httpPost;
        Exception e3;
        Closeable closeable3 = null;
        if (httpRequest == null) {
            return null;
        }
        HttpResponse httpResponse = new HttpResponse(httpRequest.getUrl());
        HttpURLConnection httpURLConnectionWithProxy;
        try {
            byte[] paras;
            httpURLConnectionWithProxy = getHttpURLConnectionWithProxy(context, httpRequest.getUrl());
            try {
                if (httpURLConnectionWithProxy instanceof HttpsURLConnection) {
                    SSLContext instance = SSLContext.getInstance("TLS");
                    TrustManager[] trustManagerArr = new TrustManager[1];
                    if (httpRequest.getSslTrustManager() != null) {
                        trustManagerArr[0] = httpRequest.getSslTrustManager();
                    } else {
                        trustManagerArr[0] = new DefaultTrustManager();
                    }
                    instance.init(null, trustManagerArr, new SecureRandom());
                    if (instance != null) {
                        ((HttpsURLConnection) httpURLConnectionWithProxy).setSSLSocketFactory(instance.getSocketFactory());
                    }
                    if (httpRequest.getHostnameVerifier() != null) {
                        ((HttpsURLConnection) httpURLConnectionWithProxy).setHostnameVerifier(httpRequest.getHostnameVerifier());
                    } else {
                        ((HttpsURLConnection) httpURLConnectionWithProxy).setHostnameVerifier(new DefaultHostVerifier());
                    }
                }
                if (!(httpRequest == null || httpURLConnectionWithProxy == null)) {
                    setURLConnection(httpRequest.getRequestProperties(), httpURLConnectionWithProxy);
                    if (httpRequest.getConnectTimeout() >= 0) {
                        httpURLConnectionWithProxy.setConnectTimeout(httpRequest.getConnectTimeout());
                    }
                    if (httpRequest.getReadTimeout() >= 0) {
                        httpURLConnectionWithProxy.setReadTimeout(httpRequest.getReadTimeout());
                    }
                }
                if (z) {
                    httpURLConnectionWithProxy.setRequestMethod(Constants.HTTP_POST);
                    httpURLConnectionWithProxy.setDoOutput(true);
                    httpURLConnectionWithProxy.setDoInput(true);
                    paras = httpRequest.getParas();
                    if (paras != null) {
                        httpURLConnectionWithProxy.getOutputStream().write(paras);
                    }
                }
                inputStream = httpURLConnectionWithProxy.getInputStream();
            } catch (MalformedURLException e4) {
                e = e4;
                httpURLConnection = httpURLConnectionWithProxy;
                closeable = null;
                try {
                    d.g("HttpUtils", "http post  error:" + e.getMessage());
                    httpResponse.setResponseCode(HttpConstants.NET_MALTFORMED_ERROR);
                    httpResponse.setResponseBody("MalformedURLException");
                    g.a(closeable);
                    g.a(null);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return httpResponse;
                } catch (Throwable th2) {
                    th = th2;
                    closeable2 = closeable;
                    httpURLConnectionWithProxy = httpURLConnection;
                    inputStream = closeable2;
                    g.a(inputStream);
                    g.a(null);
                    if (httpURLConnectionWithProxy != null) {
                        httpURLConnectionWithProxy.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                inputStream = null;
                try {
                    httpResponse.setResponseCode(HttpConstants.NET_ERROR_CODE);
                    httpResponse.setResponseBody("网络错误");
                    if (!(e2 instanceof SocketTimeoutException)) {
                        httpResponse.setResponseCode(3001);
                        httpResponse.setResponseBody("请求超时");
                    } else if (!(e2 instanceof UnknownHostException)) {
                        httpResponse.setResponseCode(3003);
                        httpResponse.setResponseBody("域名无效");
                    } else if (e2 instanceof SSLHandshakeException) {
                        httpResponse.setResponseCode(HttpConstants.NET_SSL_EXECPTION);
                        httpResponse.setResponseBody("SSL失败");
                        d.d("HttpUtils", "default ssl failed,will use http post once");
                        httpRequest.setUrl(a(httpRequest.getUrl()));
                        httpPost = httpPost(context, httpRequest);
                        g.a(inputStream);
                        g.a(null);
                        if (httpURLConnectionWithProxy != null) {
                            return httpPost;
                        }
                        httpURLConnectionWithProxy.disconnect();
                        return httpPost;
                    }
                    d.g("HttpUtils", "http post IOException error:" + e2.getMessage());
                    g.a(inputStream);
                    g.a(null);
                    if (httpURLConnectionWithProxy != null) {
                        httpURLConnectionWithProxy.disconnect();
                    }
                    return httpResponse;
                } catch (Throwable th3) {
                    th = th3;
                    g.a(inputStream);
                    g.a(null);
                    if (httpURLConnectionWithProxy != null) {
                        httpURLConnectionWithProxy.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e3 = e6;
                inputStream = null;
                d.g("HttpUtils", "http post Exception error:" + e3.getMessage());
                httpResponse.setResponseCode(HttpConstants.UNKNOW_EXECPTION);
                httpResponse.setResponseBody("UNKnow execption" + e3.getMessage());
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                return httpResponse;
            } catch (StackOverflowError e7) {
                inputStream = null;
                d.g("HttpUtils", "StackOverflowError");
                httpResponse.setResponseCode(HttpConstants.STACK_OVER_EXECPTION);
                httpResponse.setResponseBody("StackOverflowError");
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                return httpResponse;
            } catch (Throwable th4) {
                th = th4;
                inputStream = null;
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                throw th;
            }
            try {
                if (httpRequest.isHaveRspData()) {
                    paras = readInputStream(inputStream);
                    if (paras != null) {
                        httpResponse.setResponseBody(new String(paras, "UTF-8"));
                    }
                } else if (httpURLConnectionWithProxy.getResponseCode() != 200 && httpRequest.isNeedErrorInput()) {
                    closeable3 = httpURLConnectionWithProxy.getErrorStream();
                    paras = readInputStream(closeable3);
                    if (paras != null) {
                        httpResponse.setResponseBody(new String(paras, "UTF-8"));
                    }
                }
                if (httpURLConnectionWithProxy != null) {
                    httpResponse.setResponseCode(httpURLConnectionWithProxy.getResponseCode());
                    httpResponse.setResponseHeader("expires", httpURLConnectionWithProxy.getHeaderField(HttpHeaders.HEAD_KEY_EXPIRES));
                    httpResponse.setResponseHeader(HttpConstants.CACHE_CONTROL, httpURLConnectionWithProxy.getHeaderField(HttpHeaders.HEAD_KEY_CACHE_CONTROL));
                }
                g.a(inputStream);
                g.a(closeable3);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
            } catch (MalformedURLException e8) {
                e = e8;
                closeable2 = inputStream;
                httpURLConnection = httpURLConnectionWithProxy;
                closeable = closeable2;
                d.g("HttpUtils", "http post  error:" + e.getMessage());
                httpResponse.setResponseCode(HttpConstants.NET_MALTFORMED_ERROR);
                httpResponse.setResponseBody("MalformedURLException");
                g.a(closeable);
                g.a(null);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return httpResponse;
            } catch (IOException e9) {
                e2 = e9;
                httpResponse.setResponseCode(HttpConstants.NET_ERROR_CODE);
                httpResponse.setResponseBody("网络错误");
                if (!(e2 instanceof SocketTimeoutException)) {
                    httpResponse.setResponseCode(3001);
                    httpResponse.setResponseBody("请求超时");
                } else if (!(e2 instanceof UnknownHostException)) {
                    httpResponse.setResponseCode(3003);
                    httpResponse.setResponseBody("域名无效");
                } else if (e2 instanceof SSLHandshakeException) {
                    httpResponse.setResponseCode(HttpConstants.NET_SSL_EXECPTION);
                    httpResponse.setResponseBody("SSL失败");
                    if (httpRequest.isNeedRetryIfHttpsFailed() && httpRequest.getUrl().startsWith("https")) {
                        d.d("HttpUtils", "default ssl failed,will use http post once");
                        httpRequest.setUrl(a(httpRequest.getUrl()));
                        httpPost = httpPost(context, httpRequest);
                        g.a(inputStream);
                        g.a(null);
                        if (httpURLConnectionWithProxy != null) {
                            return httpPost;
                        }
                        httpURLConnectionWithProxy.disconnect();
                        return httpPost;
                    }
                }
                d.g("HttpUtils", "http post IOException error:" + e2.getMessage());
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                return httpResponse;
            } catch (Exception e10) {
                e3 = e10;
                d.g("HttpUtils", "http post Exception error:" + e3.getMessage());
                httpResponse.setResponseCode(HttpConstants.UNKNOW_EXECPTION);
                httpResponse.setResponseBody("UNKnow execption" + e3.getMessage());
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                return httpResponse;
            } catch (StackOverflowError e11) {
                d.g("HttpUtils", "StackOverflowError");
                httpResponse.setResponseCode(HttpConstants.STACK_OVER_EXECPTION);
                httpResponse.setResponseBody("StackOverflowError");
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    httpURLConnectionWithProxy.disconnect();
                }
                return httpResponse;
            }
        } catch (MalformedURLException e12) {
            e = e12;
            closeable = null;
            httpURLConnection = null;
            d.g("HttpUtils", "http post  error:" + e.getMessage());
            httpResponse.setResponseCode(HttpConstants.NET_MALTFORMED_ERROR);
            httpResponse.setResponseBody("MalformedURLException");
            g.a(closeable);
            g.a(null);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return httpResponse;
        } catch (IOException e13) {
            e2 = e13;
            inputStream = null;
            httpURLConnectionWithProxy = null;
            httpResponse.setResponseCode(HttpConstants.NET_ERROR_CODE);
            httpResponse.setResponseBody("网络错误");
            if (!(e2 instanceof SocketTimeoutException)) {
                httpResponse.setResponseCode(3001);
                httpResponse.setResponseBody("请求超时");
            } else if (!(e2 instanceof UnknownHostException)) {
                httpResponse.setResponseCode(3003);
                httpResponse.setResponseBody("域名无效");
            } else if (e2 instanceof SSLHandshakeException) {
                httpResponse.setResponseCode(HttpConstants.NET_SSL_EXECPTION);
                httpResponse.setResponseBody("SSL失败");
                d.d("HttpUtils", "default ssl failed,will use http post once");
                httpRequest.setUrl(a(httpRequest.getUrl()));
                httpPost = httpPost(context, httpRequest);
                g.a(inputStream);
                g.a(null);
                if (httpURLConnectionWithProxy != null) {
                    return httpPost;
                }
                httpURLConnectionWithProxy.disconnect();
                return httpPost;
            }
            d.g("HttpUtils", "http post IOException error:" + e2.getMessage());
            g.a(inputStream);
            g.a(null);
            if (httpURLConnectionWithProxy != null) {
                httpURLConnectionWithProxy.disconnect();
            }
            return httpResponse;
        } catch (Exception e14) {
            e3 = e14;
            inputStream = null;
            httpURLConnectionWithProxy = null;
            d.g("HttpUtils", "http post Exception error:" + e3.getMessage());
            httpResponse.setResponseCode(HttpConstants.UNKNOW_EXECPTION);
            httpResponse.setResponseBody("UNKnow execption" + e3.getMessage());
            g.a(inputStream);
            g.a(null);
            if (httpURLConnectionWithProxy != null) {
                httpURLConnectionWithProxy.disconnect();
            }
            return httpResponse;
        } catch (StackOverflowError e15) {
            inputStream = null;
            httpURLConnectionWithProxy = null;
            d.g("HttpUtils", "StackOverflowError");
            httpResponse.setResponseCode(HttpConstants.STACK_OVER_EXECPTION);
            httpResponse.setResponseBody("StackOverflowError");
            g.a(inputStream);
            g.a(null);
            if (httpURLConnectionWithProxy != null) {
                httpURLConnectionWithProxy.disconnect();
            }
            return httpResponse;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            httpURLConnectionWithProxy = null;
            g.a(inputStream);
            g.a(null);
            if (httpURLConnectionWithProxy != null) {
                httpURLConnectionWithProxy.disconnect();
            }
            throw th;
        }
        return httpResponse;
    }

    private static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str.startsWith("https")) {
                    str = "http" + str.substring(5);
                }
            } catch (Exception e) {
                d.d("HttpUtils", "fiflerHttpsToHttp error:" + e.getMessage());
            }
        }
        return str;
    }

    public static String appendParaToUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.contains(URL_AND_PARA_SEPARATOR)) {
            stringBuilder.append(PARAMETERS_SEPARATOR);
        } else {
            stringBuilder.append(URL_AND_PARA_SEPARATOR);
        }
        return stringBuilder.append(str2).append(EQUAL_SIGN).append(str3).toString();
    }

    public static HttpURLConnection getHttpURLConnectionWithProxy(Context context, String str) {
        URL url = new URL(str);
        if (context != null) {
            try {
                if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                        String extraInfo = activeNetworkInfo.getExtraInfo();
                        if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(HTTP_DEFUALT_PROXY, 80)));
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return (HttpURLConnection) url.openConnection();
    }

    public static String getUrlWithParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        Object joinParas = joinParas(map);
        if (!TextUtils.isEmpty(joinParas)) {
            stringBuilder.append(URL_AND_PARA_SEPARATOR).append(joinParas);
        }
        return stringBuilder.toString();
    }

    public static String getUrlWithValueEncodeParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        Object joinParasWithEncodedValue = joinParasWithEncodedValue(map);
        if (!TextUtils.isEmpty(joinParasWithEncodedValue)) {
            stringBuilder.append(URL_AND_PARA_SEPARATOR).append(joinParasWithEncodedValue);
        }
        return stringBuilder.toString();
    }

    public static HttpResponse httpGet(Context context, HttpRequest httpRequest) {
        return a(context, httpRequest, false);
    }

    public static HttpResponse httpGet(Context context, String str) {
        return httpGet(context, new HttpRequest(str));
    }

    public static void httpGet(Context context, HttpRequest httpRequest, HttpListener httpListener) {
        new a(context, httpListener).execute(new HttpRequest[]{httpRequest});
    }

    public static void httpGet(Context context, String str, HttpListener httpListener) {
        new b(httpListener, context).execute(new String[]{str});
    }

    public static String httpGetString(Context context, HttpRequest httpRequest) {
        HttpResponse httpGet = httpGet(context, httpRequest);
        return httpGet == null ? null : httpGet.getResponseBody();
    }

    public static String httpGetString(Context context, String str) {
        HttpResponse httpGet = httpGet(context, new HttpRequest(str));
        return httpGet == null ? null : httpGet.getResponseBody();
    }

    public static HttpResponse httpPost(Context context, HttpRequest httpRequest) {
        return a(context, httpRequest, true);
    }

    public static HttpResponse httpPost(Context context, String str) {
        return httpPost(context, new HttpRequest(str));
    }

    public static String httpPostString(Context context, String str) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str));
        return httpPost == null ? null : httpPost.getResponseBody();
    }

    public static String httpPostString(Context context, String str, Map<String, String> map) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str, map));
        return httpPost == null ? null : httpPost.getResponseBody();
    }

    public static String joinParas(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            stringBuilder.append((String) entry.getKey()).append(EQUAL_SIGN).append((String) entry.getValue());
            if (it.hasNext()) {
                stringBuilder.append(PARAMETERS_SEPARATOR);
            }
        }
        return stringBuilder.toString();
    }

    public static String joinParasWithEncodedValue(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (map != null && map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    Entry entry = (Entry) it.next();
                    stringBuilder.append((String) entry.getKey()).append(EQUAL_SIGN).append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                    if (it.hasNext()) {
                        stringBuilder.append(PARAMETERS_SEPARATOR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public static long parseGmtTime(String str) {
        try {
            return a.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void setURLConnection(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null && map.size() != 0 && httpURLConnection != null) {
            for (Entry entry : map.entrySet()) {
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
    }
}
