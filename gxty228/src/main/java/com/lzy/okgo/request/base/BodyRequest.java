package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.lzy.okgo.f.b;
import com.lzy.okgo.f.d;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.HttpParams.FileWrapper;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BodyRequest<T, R extends BodyRequest> extends Request<T, R> {
    private static final long serialVersionUID = -6459175248476927501L;
    protected byte[] bs;
    protected String content;
    protected transient File file;
    protected boolean isMultipart = false;
    protected boolean isSpliceUrl = false;
    protected transient MediaType mediaType;
    protected RequestBody requestBody;

    public BodyRequest(String str) {
        super(str);
    }

    public R isMultipart(boolean z) {
        this.isMultipart = z;
        return this;
    }

    public R isSpliceUrl(boolean z) {
        this.isSpliceUrl = z;
        return this;
    }

    public R params(String str, File file) {
        this.params.put(str, file);
        return this;
    }

    public R addFileParams(String str, List<File> list) {
        this.params.putFileParams(str, list);
        return this;
    }

    public R addFileWrapperParams(String str, List<FileWrapper> list) {
        this.params.putFileWrapperParams(str, list);
        return this;
    }

    public R params(String str, File file, String str2) {
        this.params.put(str, file, str2);
        return this;
    }

    public R params(String str, File file, String str2, MediaType mediaType) {
        this.params.put(str, file, str2, mediaType);
        return this;
    }

    public R upRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public R upString(String str) {
        this.content = str;
        this.mediaType = HttpParams.MEDIA_TYPE_PLAIN;
        return this;
    }

    public R upString(String str, MediaType mediaType) {
        this.content = str;
        this.mediaType = mediaType;
        return this;
    }

    public R upJson(String str) {
        this.content = str;
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }

    public R upJson(JSONObject jSONObject) {
        this.content = jSONObject.toString();
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }

    public R upJson(JSONArray jSONArray) {
        this.content = jSONArray.toString();
        this.mediaType = HttpParams.MEDIA_TYPE_JSON;
        return this;
    }

    public R upBytes(byte[] bArr) {
        this.bs = bArr;
        this.mediaType = HttpParams.MEDIA_TYPE_STREAM;
        return this;
    }

    public R upBytes(byte[] bArr, MediaType mediaType) {
        this.bs = bArr;
        this.mediaType = mediaType;
        return this;
    }

    public R upFile(File file) {
        this.file = file;
        this.mediaType = b.a(file.getName());
        return this;
    }

    public R upFile(File file, MediaType mediaType) {
        this.file = file;
        this.mediaType = mediaType;
        return this;
    }

    public RequestBody generateRequestBody() {
        if (this.isSpliceUrl) {
            this.url = b.a(this.baseUrl, this.params.urlParamsMap);
        }
        if (this.requestBody != null) {
            return this.requestBody;
        }
        if (this.content != null && this.mediaType != null) {
            return RequestBody.create(this.mediaType, this.content);
        }
        if (this.bs != null && this.mediaType != null) {
            return RequestBody.create(this.mediaType, this.bs);
        }
        if (this.file == null || this.mediaType == null) {
            return b.a(this.params, this.isMultipart);
        }
        return RequestBody.create(this.mediaType, this.file);
    }

    protected Builder generateRequestBuilder(RequestBody requestBody) {
        try {
            headers(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (Throwable e) {
            d.a(e);
        }
        return b.a(new Builder(), this.headers);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.mediaType == null ? "" : this.mediaType.toString());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (!TextUtils.isEmpty(str)) {
            this.mediaType = MediaType.parse(str);
        }
    }
}
