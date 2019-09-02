package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.lzy.okgo.a.c;
import com.lzy.okgo.a.d;
import com.lzy.okgo.c.a;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cache.a.b;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.HttpParams.FileWrapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class Request<T, R extends Request> implements Serializable {
    private static final long serialVersionUID = -7174118653689916252L;
    protected String baseUrl;
    protected String cacheKey;
    protected CacheMode cacheMode;
    protected transient b<T> cachePolicy;
    protected long cacheTime;
    protected transient c<T> call;
    protected transient com.lzy.okgo.b.b<T> callback;
    protected transient OkHttpClient client;
    protected transient a<T> converter;
    protected HttpHeaders headers = new HttpHeaders();
    protected transient okhttp3.Request mRequest;
    protected HttpParams params = new HttpParams();
    protected int retryCount;
    protected transient Object tag;
    protected transient a.b uploadInterceptor;
    protected String url;

    public abstract okhttp3.Request generateRequest(RequestBody requestBody);

    protected abstract RequestBody generateRequestBody();

    public abstract HttpMethod getMethod();

    public Request(String str) {
        this.url = str;
        this.baseUrl = str;
        com.lzy.okgo.a a = com.lzy.okgo.a.a();
        Object acceptLanguage = HttpHeaders.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        acceptLanguage = HttpHeaders.getUserAgent();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_USER_AGENT, acceptLanguage);
        }
        if (a.h() != null) {
            params(a.h());
        }
        if (a.i() != null) {
            headers(a.i());
        }
        this.retryCount = a.e();
        this.cacheMode = a.f();
        this.cacheTime = a.g();
    }

    public R tag(Object obj) {
        this.tag = obj;
        return this;
    }

    public R retryCount(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.retryCount = i;
        return this;
    }

    public R client(OkHttpClient okHttpClient) {
        com.lzy.okgo.f.b.a((Object) okHttpClient, "OkHttpClient == null");
        this.client = okHttpClient;
        return this;
    }

    public R call(c<T> cVar) {
        com.lzy.okgo.f.b.a((Object) cVar, "call == null");
        this.call = cVar;
        return this;
    }

    public R converter(a<T> aVar) {
        com.lzy.okgo.f.b.a((Object) aVar, "converter == null");
        this.converter = aVar;
        return this;
    }

    public R cacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    public R cachePolicy(b<T> bVar) {
        com.lzy.okgo.f.b.a((Object) bVar, "cachePolicy == null");
        this.cachePolicy = bVar;
        return this;
    }

    public R cacheKey(String str) {
        com.lzy.okgo.f.b.a((Object) str, "cacheKey == null");
        this.cacheKey = str;
        return this;
    }

    public R cacheTime(long j) {
        if (j <= -1) {
            j = -1;
        }
        this.cacheTime = j;
        return this;
    }

    public R headers(HttpHeaders httpHeaders) {
        this.headers.put(httpHeaders);
        return this;
    }

    public R headers(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public R removeHeader(String str) {
        this.headers.remove(str);
        return this;
    }

    public R removeAllHeaders() {
        this.headers.clear();
        return this;
    }

    public R params(HttpParams httpParams) {
        this.params.put(httpParams);
        return this;
    }

    public R params(Map<String, String> map, boolean... zArr) {
        this.params.put((Map) map, zArr);
        return this;
    }

    public R params(String str, String str2, boolean... zArr) {
        this.params.put(str, str2, zArr);
        return this;
    }

    public R params(String str, int i, boolean... zArr) {
        this.params.put(str, i, zArr);
        return this;
    }

    public R params(String str, float f, boolean... zArr) {
        this.params.put(str, f, zArr);
        return this;
    }

    public R params(String str, double d, boolean... zArr) {
        this.params.put(str, d, zArr);
        return this;
    }

    public R params(String str, long j, boolean... zArr) {
        this.params.put(str, j, zArr);
        return this;
    }

    public R params(String str, char c, boolean... zArr) {
        this.params.put(str, c, zArr);
        return this;
    }

    public R params(String str, boolean z, boolean... zArr) {
        this.params.put(str, z, zArr);
        return this;
    }

    public R addUrlParams(String str, List<String> list) {
        this.params.putUrlParams(str, list);
        return this;
    }

    public R removeParam(String str) {
        this.params.remove(str);
        return this;
    }

    public R removeAllParams() {
        this.params.clear();
        return this;
    }

    public R uploadInterceptor(a.b bVar) {
        this.uploadInterceptor = bVar;
        return this;
    }

    public String getUrlParam(String str) {
        List list = (List) this.params.urlParamsMap.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (String) list.get(0);
    }

    public FileWrapper getFileParam(String str) {
        List list = (List) this.params.fileParamsMap.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (FileWrapper) list.get(0);
    }

    public HttpParams getParams() {
        return this.params;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public String getUrl() {
        return this.url;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public Object getTag() {
        return this.tag;
    }

    public CacheMode getCacheMode() {
        return this.cacheMode;
    }

    public b<T> getCachePolicy() {
        return this.cachePolicy;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public long getCacheTime() {
        return this.cacheTime;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public okhttp3.Request getRequest() {
        return this.mRequest;
    }

    public void setCallback(com.lzy.okgo.b.b<T> bVar) {
        this.callback = bVar;
    }

    public a<T> getConverter() {
        if (this.converter == null) {
            this.converter = this.callback;
        }
        com.lzy.okgo.f.b.a(this.converter, "converter == null, do you forget to call Request#converter(Converter<T>) ?");
        return this.converter;
    }

    public Call getRawCall() {
        RequestBody generateRequestBody = generateRequestBody();
        if (generateRequestBody != null) {
            RequestBody aVar = new a(generateRequestBody, this.callback);
            aVar.a(this.uploadInterceptor);
            this.mRequest = generateRequest(aVar);
        } else {
            this.mRequest = generateRequest(null);
        }
        if (this.client == null) {
            this.client = com.lzy.okgo.a.a().d();
        }
        return this.client.newCall(this.mRequest);
    }

    public c<T> adapt() {
        if (this.call == null) {
            return new com.lzy.okgo.a.b(this);
        }
        return this.call;
    }

    public <E> E adapt(d<T, E> dVar) {
        c cVar = this.call;
        if (cVar == null) {
            cVar = new com.lzy.okgo.a.b(this);
        }
        return dVar.a(cVar, null);
    }

    public <E> E adapt(com.lzy.okgo.a.a aVar, d<T, E> dVar) {
        c cVar = this.call;
        if (cVar == null) {
            cVar = new com.lzy.okgo.a.b(this);
        }
        return dVar.a(cVar, aVar);
    }

    public Response execute() throws IOException {
        return getRawCall().execute();
    }

    public void execute(com.lzy.okgo.b.b<T> bVar) {
        com.lzy.okgo.f.b.a((Object) bVar, "callback == null");
        this.callback = bVar;
        adapt().a(bVar);
    }
}
