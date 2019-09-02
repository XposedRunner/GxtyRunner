package com.nostra13.universalimageloader.core.download;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.lzy.okgo.model.HttpHeaders;
import com.nostra13.universalimageloader.b.b;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: BaseImageDownloader */
public class a implements ImageDownloader {
    protected final Context a;
    protected final int b;
    protected final int c;

    public a(Context context) {
        this(context, GLMapStaticValue.TMC_REFRESH_TIMELIMIT, 20000);
    }

    public a(Context context, int i, int i2) {
        this.a = context.getApplicationContext();
        this.b = i;
        this.c = i2;
    }

    public InputStream a(String str, Object obj) throws IOException {
        switch (Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                return b(str, obj);
            case FILE:
                return d(str, obj);
            case CONTENT:
                return e(str, obj);
            case ASSETS:
                return f(str, obj);
            case DRAWABLE:
                return g(str, obj);
            default:
                return h(str, obj);
        }
    }

    protected InputStream b(String str, Object obj) throws IOException {
        HttpURLConnection c = c(str, obj);
        int i = 0;
        while (c.getResponseCode() / 100 == 3 && i < 5) {
            c = c(c.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION), obj);
            i++;
        }
        try {
            Closeable inputStream = c.getInputStream();
            if (a(c)) {
                return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(inputStream, 32768), c.getContentLength());
            }
            b.a(inputStream);
            throw new IOException("Image request failed with response code " + c.getResponseCode());
        } catch (IOException e) {
            b.a(c.getErrorStream());
            throw e;
        }
    }

    protected boolean a(HttpURLConnection httpURLConnection) throws IOException {
        return httpURLConnection.getResponseCode() == 200;
    }

    protected HttpURLConnection c(String str, Object obj) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        return httpURLConnection;
    }

    protected InputStream d(String str, Object obj) throws IOException {
        String crop = Scheme.FILE.crop(str);
        if (b(str)) {
            return a(crop);
        }
        return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(new FileInputStream(crop), 32768), (int) new File(crop).length());
    }

    @TargetApi(8)
    private InputStream a(String str) {
        if (VERSION.SDK_INT >= 8) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
            if (createVideoThumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        }
        return null;
    }

    protected InputStream e(String str, Object obj) throws FileNotFoundException {
        ContentResolver contentResolver = this.a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (b(parse)) {
            Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith("content://com.android.contacts/")) {
            return a(parse);
        }
        return contentResolver.openInputStream(parse);
    }

    @TargetApi(14)
    protected InputStream a(Uri uri) {
        ContentResolver contentResolver = this.a.getContentResolver();
        if (VERSION.SDK_INT >= 14) {
            return Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
        return Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    protected InputStream f(String str, Object obj) throws IOException {
        return this.a.getAssets().open(Scheme.ASSETS.crop(str));
    }

    protected InputStream g(String str, Object obj) {
        return this.a.getResources().openRawResource(Integer.parseInt(Scheme.DRAWABLE.crop(str)));
    }

    protected InputStream h(String str, Object obj) throws IOException {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
    }

    private boolean b(Uri uri) {
        String type = this.a.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    private boolean b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }
}
