package com.lzy.okgo.model;

import cn.jiguang.net.HttpUtils;
import com.lzy.okgo.f.b;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.MediaType;

public class HttpParams implements Serializable {
    public static final boolean IS_REPLACE = true;
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
    private static final long serialVersionUID = 7369819159227055048L;
    public LinkedHashMap<String, List<FileWrapper>> fileParamsMap;
    public LinkedHashMap<String, List<String>> urlParamsMap;

    public static class FileWrapper implements Serializable {
        private static final long serialVersionUID = -2356139899636767776L;
        public transient MediaType contentType;
        public File file;
        public String fileName;
        public long fileSize;

        public FileWrapper(File file, String str, MediaType mediaType) {
            this.file = file;
            this.fileName = str;
            this.contentType = mediaType;
            this.fileSize = file.length();
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.contentType.toString());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.contentType = MediaType.parse((String) objectInputStream.readObject());
        }

        public String toString() {
            return "FileWrapper{file=" + this.file + ", fileName=" + this.fileName + ", contentType=" + this.contentType + ", fileSize=" + this.fileSize + "}";
        }
    }

    public HttpParams() {
        a();
    }

    public HttpParams(String str, String str2) {
        a();
        a(str, str2, true);
    }

    public HttpParams(String str, File file) {
        a();
        put(str, file);
    }

    private void a() {
        this.urlParamsMap = new LinkedHashMap();
        this.fileParamsMap = new LinkedHashMap();
    }

    public void put(HttpParams httpParams) {
        if (httpParams != null) {
            if (!(httpParams.urlParamsMap == null || httpParams.urlParamsMap.isEmpty())) {
                this.urlParamsMap.putAll(httpParams.urlParamsMap);
            }
            if (httpParams.fileParamsMap != null && !httpParams.fileParamsMap.isEmpty()) {
                this.fileParamsMap.putAll(httpParams.fileParamsMap);
            }
        }
    }

    public void put(Map<String, String> map, boolean... zArr) {
        if (map != null && !map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                put((String) entry.getKey(), (String) entry.getValue(), zArr);
            }
        }
    }

    public void put(String str, String str2, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, str2, true);
        } else {
            a(str, str2, zArr[0]);
        }
    }

    public void put(String str, int i, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(i), true);
        } else {
            a(str, String.valueOf(i), zArr[0]);
        }
    }

    public void put(String str, long j, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(j), true);
        } else {
            a(str, String.valueOf(j), zArr[0]);
        }
    }

    public void put(String str, float f, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(f), true);
        } else {
            a(str, String.valueOf(f), zArr[0]);
        }
    }

    public void put(String str, double d, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(d), true);
        } else {
            a(str, String.valueOf(d), zArr[0]);
        }
    }

    public void put(String str, char c, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(c), true);
        } else {
            a(str, String.valueOf(c), zArr[0]);
        }
    }

    public void put(String str, boolean z, boolean... zArr) {
        if (zArr == null || zArr.length <= 0) {
            a(str, String.valueOf(z), true);
        } else {
            a(str, String.valueOf(z), zArr[0]);
        }
    }

    private void a(String str, String str2, boolean z) {
        if (str != null && str2 != null) {
            List list = (List) this.urlParamsMap.get(str);
            if (list == null) {
                list = new ArrayList();
                this.urlParamsMap.put(str, list);
            }
            if (z) {
                list.clear();
            }
            list.add(str2);
        }
    }

    public void putUrlParams(String str, List<String> list) {
        if (str != null && list != null && !list.isEmpty()) {
            for (String a : list) {
                a(str, a, false);
            }
        }
    }

    public void put(String str, File file) {
        put(str, file, file.getName());
    }

    public void put(String str, File file, String str2) {
        put(str, file, str2, b.a(str2));
    }

    public void put(String str, FileWrapper fileWrapper) {
        if (str != null && fileWrapper != null) {
            put(str, fileWrapper.file, fileWrapper.fileName, fileWrapper.contentType);
        }
    }

    public void put(String str, File file, String str2, MediaType mediaType) {
        if (str != null) {
            List list = (List) this.fileParamsMap.get(str);
            if (list == null) {
                list = new ArrayList();
                this.fileParamsMap.put(str, list);
            }
            list.add(new FileWrapper(file, str2, mediaType));
        }
    }

    public void putFileParams(String str, List<File> list) {
        if (str != null && list != null && !list.isEmpty()) {
            for (File put : list) {
                put(str, put);
            }
        }
    }

    public void putFileWrapperParams(String str, List<FileWrapper> list) {
        if (str != null && list != null && !list.isEmpty()) {
            for (FileWrapper put : list) {
                put(str, put);
            }
        }
    }

    public void removeUrl(String str) {
        this.urlParamsMap.remove(str);
    }

    public void removeFile(String str) {
        this.fileParamsMap.remove(str);
    }

    public void remove(String str) {
        removeUrl(str);
        removeFile(str);
    }

    public void clear() {
        this.urlParamsMap.clear();
        this.fileParamsMap.clear();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.urlParamsMap.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            stringBuilder.append((String) entry.getKey()).append(HttpUtils.EQUAL_SIGN).append(entry.getValue());
        }
        for (Entry entry2 : this.fileParamsMap.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            stringBuilder.append((String) entry2.getKey()).append(HttpUtils.EQUAL_SIGN).append(entry2.getValue());
        }
        return stringBuilder.toString();
    }
}
