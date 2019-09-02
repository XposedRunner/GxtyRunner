package cn.jpush.android.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.a;
import cn.jiguang.d.h.c;
import cn.jiguang.d.h.f;
import cn.jiguang.e.d;

public class DownloadProvider extends ContentProvider {
    private static final String TAG = "DownloadProvider";

    private void init() {
        try {
            if (JCoreInterface.init(getContext().getApplicationContext(), false)) {
                JCoreInterface.register(getContext());
            }
        } catch (Throwable th) {
            d.a(TAG, "");
        }
    }

    private void report(int i, boolean z, Uri uri) {
        if (!c.a(getContext())) {
            d.c(TAG, "Not need report waked");
        } else if (uri != null) {
            try {
                if (TextUtils.equals("true", uri.getQueryParameter("need_report"))) {
                    f.a().b().a(getContext(), i, z, uri.getQueryParameter("from_package"), uri.getQueryParameter("from_uid"), uri.getQueryParameter("awake_sequence"));
                    return;
                }
                d.g(TAG, "wakeup is not from sdkWakeUp, give up report data.");
            } catch (Throwable th) {
                d.i(TAG, "report awake fail:" + th);
            }
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        init();
        return 0;
    }

    public String getType(Uri uri) {
        d.c(TAG, "DownloadProvider getType:" + uri);
        init();
        return "1.1.9";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        init();
        return null;
    }

    public boolean onCreate() {
        d.c(TAG, "onCreate");
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        d.c(TAG, "DownloadProvider query:" + uri);
        report(4, a.l, uri);
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        init();
        return 0;
    }
}
