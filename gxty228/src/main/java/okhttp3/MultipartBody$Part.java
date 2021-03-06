package okhttp3;

import com.lzy.okgo.model.HttpHeaders;
import javax.annotation.Nullable;

public final class MultipartBody$Part {
    final RequestBody body;
    @Nullable
    final Headers headers;

    public static MultipartBody$Part create(RequestBody requestBody) {
        return create(null, requestBody);
    }

    public static MultipartBody$Part create(@Nullable Headers headers, RequestBody requestBody) {
        if (requestBody == null) {
            throw new NullPointerException("body == null");
        } else if (headers != null && headers.get(HttpHeaders.HEAD_KEY_CONTENT_TYPE) != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        } else if (headers == null || headers.get(HttpHeaders.HEAD_KEY_CONTENT_LENGTH) == null) {
            return new MultipartBody$Part(headers, requestBody);
        } else {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    public static MultipartBody$Part createFormData(String str, String str2) {
        return createFormData(str, null, RequestBody.create(null, str2));
    }

    public static MultipartBody$Part createFormData(String str, @Nullable String str2, RequestBody requestBody) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        StringBuilder stringBuilder = new StringBuilder("form-data; name=");
        MultipartBody.appendQuotedString(stringBuilder, str);
        if (str2 != null) {
            stringBuilder.append("; filename=");
            MultipartBody.appendQuotedString(stringBuilder, str2);
        }
        return create(Headers.of(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION, stringBuilder.toString()), requestBody);
    }

    private MultipartBody$Part(@Nullable Headers headers, RequestBody requestBody) {
        this.headers = headers;
        this.body = requestBody;
    }

    @Nullable
    public Headers headers() {
        return this.headers;
    }

    public RequestBody body() {
        return this.body;
    }
}
