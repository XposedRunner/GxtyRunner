package cn.jiguang.d.d;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import cn.jiguang.d.g.c;
import cn.jiguang.d.g.f;
import cn.jiguang.e.d;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class m {
    public static File a(File file, String str) {
        File file2 = new File(file.getAbsolutePath().replace(File.separator + "nowrap", ""));
        c.a(file2, str);
        c.a(file);
        d.c("ReportHistory", "wrap data, move to" + file2.getAbsolutePath());
        return file2;
    }

    private static synchronized File a(String str) {
        File file = null;
        synchronized (m.class) {
            File file2 = new File(str);
            File[] listFiles = (file2.exists() && file2.isDirectory()) ? file2.listFiles() : null;
            if (!(listFiles == null || listFiles.length == 0)) {
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    file2 = listFiles[i];
                    if (file2.isFile()) {
                        if (file2.lastModified() > (file != null ? file.lastModified() : 0)) {
                            i++;
                            file = file2;
                        }
                    }
                    file2 = file;
                    i++;
                    file = file2;
                }
            }
        }
        return file;
    }

    private static String a(Context context) {
        return context.getFilesDir().getPath() + File.separator + "jpush_stat_history";
    }

    private static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    public static List<l> a(Context context, String str, JSONObject jSONObject) {
        int i = 1;
        int i2 = 0;
        File a = a(a(context, str));
        l lVar = a != null ? new l(a) : null;
        List<l> arrayList = new ArrayList();
        File b;
        if (lVar == null) {
            b = b(context, str);
            c.a(b, jSONObject.toString());
            d.c("ReportHistory", "save to " + b.getAbsolutePath());
            arrayList.add(new l(b, jSONObject));
        } else {
            boolean z;
            if (lVar.d()) {
                JSONObject c = lVar.c();
                if (jSONObject == null || jSONObject.length() == 0) {
                    z = false;
                } else {
                    Set hashSet = new HashSet();
                    hashSet.add("uid");
                    hashSet.add("app_key");
                    hashSet.add("sdk_ver");
                    hashSet.add("core_sdk_ver");
                    hashSet.add("share_sdk_ver");
                    hashSet.add("statistics_sdk_ver");
                    hashSet.add(LogBuilder.KEY_CHANNEL);
                    hashSet.add("app_version");
                    z = f.a(c, jSONObject, hashSet);
                }
            } else {
                z = !l.b(jSONObject);
            }
            if (!z) {
                d.c("ReportHistory", "wrapper not match");
                i = 0;
            } else if (lVar.a() + ((long) f.b(jSONObject)) < 40960) {
                d.c("ReportHistory", "can merge");
            } else {
                d.c("ReportHistory", "out size");
                i = 0;
            }
            if (i != 0) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray("content");
                    JSONArray jSONArray2 = lVar.c().getJSONArray("content");
                    while (i2 < jSONArray2.length()) {
                        jSONArray.put(jSONArray2.getJSONObject(i2));
                        i2++;
                    }
                } catch (JSONException e) {
                }
                lVar.a(jSONObject);
                arrayList.add(lVar);
                d.c("ReportHistory", "update to " + lVar.b().getAbsolutePath());
            } else {
                a = b(context, str);
                c.a(a, jSONObject.toString());
                arrayList.add(new l(a, jSONObject));
                arrayList.add(lVar);
                d.c("ReportHistory", "save to " + a.getAbsolutePath());
            }
            b = new File(a(context, str));
            AtomicLong atomicLong = new AtomicLong(0);
            c.a(atomicLong, b);
            if (atomicLong.get() > ("prior".equals(str) ? 512000 : PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                List arrayList2 = new ArrayList();
                c.a(arrayList2, b, null);
                b = c.a(arrayList2);
                if (b != null) {
                    d.c("ReportHistory", "over limit, delete file=" + b.getAbsolutePath());
                    c.a(b);
                }
            }
        }
        return arrayList;
    }

    public static List<File> a(Context context, boolean z) {
        File file = new File(a(context));
        List<File> arrayList = new ArrayList();
        if (z) {
            c.a(arrayList, file, new n());
        } else {
            c.a(arrayList, file, null);
        }
        return arrayList;
    }

    public static JSONObject a(File file) {
        if (file == null) {
            return null;
        }
        Object b = c.b(file);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            return new JSONObject(b);
        } catch (JSONException e) {
            return null;
        }
    }

    private static File b(Context context, String str) {
        return new File(a(context, str) + File.separator + UUID.randomUUID().toString());
    }
}
