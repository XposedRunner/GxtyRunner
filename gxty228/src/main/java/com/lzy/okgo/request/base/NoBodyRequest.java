package com.lzy.okgo.request.base;

import com.lzy.okgo.f.b;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public abstract class NoBodyRequest<T, R extends NoBodyRequest> extends Request<T, R> {
    private static final long serialVersionUID = 1200621102761691196L;

    public NoBodyRequest(String str) {
        super(str);
    }

    public RequestBody generateRequestBody() {
        return null;
    }

    protected Builder generateRequestBuilder(RequestBody requestBody) {
        this.url = b.a(this.baseUrl, this.params.urlParamsMap);
        return b.a(new Builder(), this.headers);
    }
}
