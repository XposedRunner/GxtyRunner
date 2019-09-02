package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostRequest<T> extends BodyRequest<T, PostRequest<T>> {
    public PostRequest(String str) {
        super(str);
    }

    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    public Request generateRequest(RequestBody requestBody) {
        return generateRequestBuilder(requestBody).post(requestBody).url(this.url).tag(this.tag).build();
    }
}
