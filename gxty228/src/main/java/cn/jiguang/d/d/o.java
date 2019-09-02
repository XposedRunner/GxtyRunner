package cn.jiguang.d.d;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.a.d;
import cn.jiguang.d.g.c;
import cn.jiguang.d.g.f;
import cn.jiguang.d.g.h;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class o {
    public static JSONObject a = null;
    private static String b = "";
    private static String c = "/v1/report";
    private static String d = "/v2/report";
    private static ExecutorService e = Executors.newSingleThreadExecutor();
    private static ExecutorService f = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Object g = new Object();

    public static String a() {
        long d = d.d(null);
        if (d == 0) {
            cn.jiguang.e.d.c("ReportUtils", " miss uid, generate report token failed");
            return null;
        }
        String b = a.b(d + a.b(d.f(null)));
        return !k.a(b) ? d + ":" + b : null;
    }

    public static String a(int i) {
        String f = cn.jiguang.d.a.f.f();
        try {
            InetAddress.getByName(f);
            return ("https://" + f) + d;
        } catch (Throwable e) {
            cn.jiguang.e.d.c("ReportUtils", "unexpected!", e);
            return b();
        }
    }

    public static String a(String str) {
        if (k.a(str)) {
            cn.jiguang.e.d.g("ReportUtils", "unexpected, report ip is null");
            return "";
        }
        if (!str.startsWith("http://")) {
            str = "http://" + str;
        }
        if (!str.endsWith(d)) {
            str = str + d;
        }
        b = str;
        cn.jiguang.e.d.a("ReportUtils", "sis report url:" + b);
        return b;
    }

    public static String a(String str, int i) {
        if (k.a(str)) {
            cn.jiguang.e.d.c("ReportUtils", " body is null,generate report token failed");
            return null;
        }
        String e = e(str);
        long d = d.d(null);
        if (d == 0) {
            cn.jiguang.e.d.c("ReportUtils", " miss uid,generate report token failed");
            return null;
        }
        e = a.b(d + a.b(d.f(null)) + e);
        return !k.a(e) ? d + ":" + e : null;
    }

    private static ArrayList<JSONArray> a(JSONArray jSONArray, int i) {
        int i2;
        Exception exception;
        Exception exception2;
        ArrayList<JSONArray> arrayList = new ArrayList();
        JSONArray jSONArray2 = new JSONArray();
        if (jSONArray != null && jSONArray.length() > 0) {
            int length = jSONArray.length() - 1;
            int i3 = 0;
            int i4 = 0;
            while (length >= 0) {
                JSONArray jSONArray3;
                int i5;
                int i6;
                JSONObject optJSONObject = jSONArray.optJSONObject(length);
                if (optJSONObject != null) {
                    try {
                        int length2 = optJSONObject.toString().getBytes("UTF-8").length;
                        i2 = i4 + length2;
                        if (i2 > 204800) {
                            try {
                                jSONArray2.put(optJSONObject);
                                arrayList.add(jSONArray2);
                                return arrayList;
                            } catch (Exception e) {
                                exception = e;
                                jSONArray3 = jSONArray2;
                                i5 = i2;
                                i2 = i3;
                            }
                        } else if (i3 + length2 > 40960) {
                            try {
                                arrayList.add(jSONArray2);
                                jSONArray3 = new JSONArray();
                            } catch (Exception e2) {
                                exception2 = e2;
                                jSONArray3 = jSONArray2;
                                i5 = i2;
                                i2 = length2;
                                exception = exception2;
                                cn.jiguang.e.d.i("ReportUtils", exception.getMessage());
                                length--;
                                i3 = i2;
                                i6 = i5;
                                jSONArray2 = jSONArray3;
                                i4 = i6;
                            }
                            try {
                                jSONArray3.put(optJSONObject);
                                i5 = i2;
                                i2 = length2;
                            } catch (Exception e3) {
                                exception2 = e3;
                                i5 = i2;
                                i2 = length2;
                                exception = exception2;
                                cn.jiguang.e.d.i("ReportUtils", exception.getMessage());
                                length--;
                                i3 = i2;
                                i6 = i5;
                                jSONArray2 = jSONArray3;
                                i4 = i6;
                            }
                        } else {
                            i4 = i3 + length2;
                            JSONArray jSONArray4;
                            try {
                                jSONArray2.put(optJSONObject);
                                jSONArray4 = jSONArray2;
                                i5 = i2;
                                i2 = i4;
                                jSONArray3 = jSONArray4;
                            } catch (Exception e4) {
                                exception = e4;
                                jSONArray4 = jSONArray2;
                                i5 = i2;
                                i2 = i4;
                                jSONArray3 = jSONArray4;
                                cn.jiguang.e.d.i("ReportUtils", exception.getMessage());
                                length--;
                                i3 = i2;
                                i6 = i5;
                                jSONArray2 = jSONArray3;
                                i4 = i6;
                            }
                        }
                    } catch (Exception e5) {
                        exception = e5;
                        i2 = i3;
                        i6 = i4;
                        jSONArray3 = jSONArray2;
                        i5 = i6;
                        cn.jiguang.e.d.i("ReportUtils", exception.getMessage());
                        length--;
                        i3 = i2;
                        i6 = i5;
                        jSONArray2 = jSONArray3;
                        i4 = i6;
                    }
                } else {
                    i2 = i3;
                    i6 = i4;
                    jSONArray3 = jSONArray2;
                    i5 = i6;
                }
                length--;
                i3 = i2;
                i6 = i5;
                jSONArray2 = jSONArray3;
                i4 = i6;
            }
            if (jSONArray2.length() > 0) {
                arrayList.add(jSONArray2);
            }
        }
        return arrayList;
    }

    public static JSONObject a(Context context, String str) {
        Closeable openFileInput;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        if (str == null || str.length() <= 0) {
            cn.jiguang.e.d.c("ReportUtils", "file_name is null , give up read ");
            return null;
        }
        String f = f(str);
        if (context == null) {
            cn.jiguang.e.d.c("ReportUtils", "context is null , give up read " + f);
            return null;
        }
        try {
            openFileInput = context.openFileInput(str);
            try {
                byte[] bArr = new byte[(openFileInput.available() + 1)];
                openFileInput.read(bArr);
                a(openFileInput);
                try {
                    String str2 = new String(bArr, "UTF-8");
                    if (!k.a(str2)) {
                        return new JSONObject(str2);
                    }
                    cn.jiguang.e.d.c("ReportUtils", f + " is null, return null");
                    return null;
                } catch (UnsupportedEncodingException e3) {
                    cn.jiguang.e.d.c("ReportUtils", "can't encoding " + f + ", give up read :" + e3.getMessage());
                    return null;
                } catch (JSONException e4) {
                    cn.jiguang.e.d.c("ReportUtils", "can't build " + f + " into JsonObject, give up read :" + e4.getMessage());
                    return null;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                try {
                    cn.jiguang.e.d.c("ReportUtils", "can't open " + f + " inputStream, give up read  :" + e.getMessage());
                    a(openFileInput);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    a(openFileInput);
                    throw th;
                }
            } catch (IOException e6) {
                e2 = e6;
                cn.jiguang.e.d.c("ReportUtils", "can't read " + f + ", give up read :" + e2.getMessage());
                a(openFileInput);
                return null;
            }
        } catch (FileNotFoundException e7) {
            e = e7;
            openFileInput = null;
            cn.jiguang.e.d.c("ReportUtils", "can't open " + f + " inputStream, give up read  :" + e.getMessage());
            a(openFileInput);
            return null;
        } catch (IOException e8) {
            e2 = e8;
            openFileInput = null;
            cn.jiguang.e.d.c("ReportUtils", "can't read " + f + ", give up read :" + e2.getMessage());
            a(openFileInput);
            return null;
        } catch (Throwable th3) {
            openFileInput = null;
            th = th3;
            a(openFileInput);
            throw th;
        }
    }

    private static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("content", new JSONArray().put(jSONObject));
        } catch (JSONException e) {
        }
        f.a(jSONObject3, jSONObject2);
        return jSONObject3;
    }

    public static void a(Context context) {
        cn.jiguang.e.d.c("ReportUtils", "clearReportLogFile with appkey changed ");
        a(context, "jpush_stat_cache.json", null);
        c(context);
        a(context, "jpush_stat_cache_history.json", null);
    }

    public static void a(Context context, int i) {
        int i2 = 0;
        if (a != null) {
            JSONObject jSONObject = a;
            if (i >= 204800) {
                c(context);
                return;
            }
            int length;
            try {
                length = jSONObject.toString().getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                length = 0;
            }
            int i3 = (length + i) - 204800;
            if (i3 > 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("content");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    try {
                        JSONObject jSONObject2;
                        JSONArray jSONArray = new JSONArray();
                        for (length = 0; length < optJSONArray.length(); length++) {
                            JSONObject jSONObject3 = optJSONArray.getJSONObject(length);
                            if (jSONObject3 != null) {
                                if (i2 >= i3) {
                                    jSONArray.put(jSONObject3);
                                }
                                i2 += jSONObject3.toString().getBytes("utf-8").length;
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put("content", jSONArray);
                            jSONObject2 = jSONObject;
                        } else {
                            jSONObject2 = null;
                        }
                        a = jSONObject2;
                        a(context, "jpush_stat_cache_history.json", jSONObject2);
                    } catch (JSONException e2) {
                    } catch (UnsupportedEncodingException e3) {
                    }
                }
            }
        }
    }

    private static void a(Context context, List<l> list, JSONObject jSONObject, boolean z) {
        boolean z2 = (jSONObject == null || a.c(context)) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (!(z2 || list == null)) {
            for (l lVar : list) {
                a(context, lVar.c(), lVar.b(), jSONObject);
                arrayList.add(lVar.b());
            }
        }
        List<File> a = m.a(context, z2);
        if (!a.isEmpty()) {
            if (z2) {
                cn.jiguang.e.d.e("ReportUtils", "no network, only wrap history");
            }
            for (File file : a) {
                if (!arrayList.contains(file)) {
                    a(context, null, file, jSONObject);
                }
            }
        }
    }

    public static void a(Context context, JSONArray jSONArray) {
        a(context, jSONArray, "");
    }

    public static void a(Context context, JSONArray jSONArray, q qVar) {
        int i = -1;
        cn.jiguang.e.d.a("ReportUtils", "Action - sendLogRoutine with report CallBack");
        JSONObject jSONObject = new JSONObject();
        if (context == null || jSONArray == null || jSONArray.length() <= 0) {
            cn.jiguang.e.d.g("ReportUtils", "context:" + context + " items:" + jSONArray);
            if (qVar != null) {
                qVar.a(-1);
                return;
            }
            return;
        }
        try {
            cn.jiguang.e.d.c("ReportUtils", "log size:" + jSONArray.toString().getBytes("utf-8").length);
        } catch (UnsupportedEncodingException e) {
        }
        try {
            jSONObject.put("content", jSONArray);
            try {
                if (a(jSONObject, context)) {
                    try {
                        cn.jiguang.e.d.e("ReportUtils", "send log slice:" + jSONObject.toString(1));
                    } catch (JSONException e2) {
                        cn.jiguang.e.d.e("ReportUtils", "send log slice:" + jSONObject.toString());
                    }
                    int a = i.a(context, jSONObject, true);
                    if (qVar != null) {
                        if (a == 200) {
                            i = 0;
                        }
                        qVar.a(i);
                    }
                } else if (qVar != null) {
                    qVar.a(-1);
                }
            } catch (Exception e3) {
                cn.jiguang.e.d.c("ReportUtils", "wrap container exception, give up send log:" + e3);
                if (qVar != null) {
                    qVar.a(-1);
                }
            }
        } catch (JSONException e4) {
            cn.jiguang.e.d.c("ReportUtils", "put content exception, give up send log:" + e4);
            if (qVar != null) {
                qVar.a(-1);
            }
        }
    }

    public static void a(Context context, JSONArray jSONArray, String str) {
        if (context == null) {
            cn.jiguang.e.d.c("ReportUtils", "context is null , give up send log");
        } else if (jSONArray == null || jSONArray.length() <= 0) {
            cn.jiguang.e.d.c("ReportUtils", "item is null , give up send log");
        } else {
            e.execute(new r(context, jSONArray, str));
        }
    }

    public static void a(Context context, JSONObject jSONObject) {
        a(context, new JSONArray().put(jSONObject), "");
    }

    private static void a(Context context, JSONObject jSONObject, File file, JSONObject jSONObject2) {
        if (jSONObject == null) {
            jSONObject = m.a(file);
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            cn.jiguang.e.d.g("ReportUtils", "upload content is empty, do nothing");
            return;
        }
        if (!l.b(jSONObject)) {
            if (jSONObject2 != null) {
                f.a(jSONObject, jSONObject2);
                file = m.a(file, jSONObject.toString());
            } else {
                return;
            }
        }
        if (a.c(context)) {
            int a = i.a(context, jSONObject, true);
            if (a == 200) {
                cn.jiguang.e.d.c("ReportUtils", "upload json success: " + f.a(jSONObject));
                c.a(file);
                return;
            }
            cn.jiguang.e.d.g("ReportUtils", "upload json failed(" + a + "): " + f.a(jSONObject));
            return;
        }
        cn.jiguang.e.d.e("ReportUtils", "no network, give up upload");
    }

    public static void a(Context context, JSONObject jSONObject, String str) {
        a(context, new JSONArray().put(jSONObject), str);
    }

    private static void a(Context context, JSONObject jSONObject, ArrayList<JSONArray> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList.size() <= 0) {
            c(context);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                JSONArray jSONArray2 = (JSONArray) arrayList.get(i);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    if (jSONArray2.optJSONObject(i2) != null) {
                        jSONArray.put(jSONArray2.optJSONObject(i2));
                    }
                }
            }
        }
        try {
            jSONObject.put("content", jSONArray);
        } catch (JSONException e) {
        }
        a = jSONObject;
        a(context, "jpush_stat_cache_history.json", jSONObject);
    }

    private static void a(Context context, JSONObject jSONObject, JSONArray jSONArray, ArrayList<JSONArray> arrayList) {
        if (arrayList.size() == 1) {
            c(context);
        } else if (jSONArray != null && arrayList.size() > 1) {
            arrayList.remove(jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONArray jSONArray3 = (JSONArray) arrayList.get(i);
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (jSONArray3.optJSONObject(i2) != null) {
                        jSONArray2.put(jSONArray3.optJSONObject(i2));
                    }
                }
            }
            try {
                jSONObject.put("content", jSONArray2);
            } catch (JSONException e) {
            }
            a = jSONObject;
            a(context, "jpush_stat_cache_history.json", jSONObject);
        }
    }

    public static void a(Context context, JSONObject jSONObject, boolean z) {
        e.execute(new p(context, jSONObject, z));
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r8, java.lang.String r9, org.json.JSONObject r10) {
        /*
        r2 = 1;
        r1 = 0;
        r0 = cn.jiguang.g.k.a(r9);
        if (r0 == 0) goto L_0x0011;
    L_0x0008:
        r0 = "ReportUtils";
        r2 = "file_name is null , give up save ";
        cn.jiguang.e.d.c(r0, r2);
        r0 = r1;
    L_0x0010:
        return r0;
    L_0x0011:
        r4 = f(r9);
        if (r8 != 0) goto L_0x002d;
    L_0x0017:
        r0 = "ReportUtils";
        r2 = new java.lang.StringBuilder;
        r3 = "context is null , give up save ";
        r2.<init>(r3);
        r2 = r2.append(r4);
        r2 = r2.toString();
        cn.jiguang.e.d.c(r0, r2);
        r0 = r1;
        goto L_0x0010;
    L_0x002d:
        r5 = g;
        monitor-enter(r5);
        r0 = "";
        if (r10 == 0) goto L_0x0059;
    L_0x0034:
        r0 = r10.toString();	 Catch:{ all -> 0x0077 }
        r3 = "jpush_stat_cache_history.json";
        r3 = r9.equals(r3);	 Catch:{ all -> 0x0077 }
        if (r3 == 0) goto L_0x0059;
    L_0x0040:
        r3 = "ReportUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x006e }
        r7 = "save log in writeHistoryLog:\n";
        r6.<init>(r7);	 Catch:{ Exception -> 0x006e }
        r7 = 1;
        r7 = r10.toString(r7);	 Catch:{ Exception -> 0x006e }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x006e }
        r6 = r6.toString();	 Catch:{ Exception -> 0x006e }
        cn.jiguang.e.d.a(r3, r6);	 Catch:{ Exception -> 0x006e }
    L_0x0059:
        r3 = 0;
        r6 = 0;
        r3 = r8.openFileOutput(r9, r6);	 Catch:{ FileNotFoundException -> 0x007a, UnsupportedEncodingException -> 0x00a5, IOException -> 0x00cf, NullPointerException -> 0x00f9 }
        r6 = "UTF-8";
        r0 = r0.getBytes(r6);	 Catch:{ FileNotFoundException -> 0x012b, UnsupportedEncodingException -> 0x00a5, IOException -> 0x00cf, NullPointerException -> 0x00f9 }
        r3.write(r0);	 Catch:{ FileNotFoundException -> 0x012b, UnsupportedEncodingException -> 0x00a5, IOException -> 0x00cf, NullPointerException -> 0x00f9 }
        a(r3);	 Catch:{ all -> 0x0077 }
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r0 = r2;
        goto L_0x0010;
    L_0x006e:
        r3 = move-exception;
        r6 = "ReportUtils";
        r7 = "save log in writeHistoryLog";
        cn.jiguang.e.d.c(r6, r7, r3);	 Catch:{ all -> 0x0077 }
        goto L_0x0059;
    L_0x0077:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        throw r0;
    L_0x007a:
        r0 = move-exception;
        r2 = r3;
    L_0x007c:
        r3 = "ReportUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0128 }
        r7 = "can't open ";
        r6.<init>(r7);	 Catch:{ all -> 0x0128 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x0128 }
        r6 = " outputStream, give up save :";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0128 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0128 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x0128 }
        r0 = r0.toString();	 Catch:{ all -> 0x0128 }
        cn.jiguang.e.d.c(r3, r0);	 Catch:{ all -> 0x0128 }
        a(r2);	 Catch:{ all -> 0x0077 }
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r0 = r1;
        goto L_0x0010;
    L_0x00a5:
        r0 = move-exception;
        r2 = "ReportUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0123 }
        r7 = "can't encoding ";
        r6.<init>(r7);	 Catch:{ all -> 0x0123 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x0123 }
        r6 = " , give up save :";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0123 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0123 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x0123 }
        r0 = r0.toString();	 Catch:{ all -> 0x0123 }
        cn.jiguang.e.d.c(r2, r0);	 Catch:{ all -> 0x0123 }
        a(r3);	 Catch:{ all -> 0x0077 }
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r0 = r1;
        goto L_0x0010;
    L_0x00cf:
        r0 = move-exception;
        r2 = "ReportUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0123 }
        r7 = "can't write ";
        r6.<init>(r7);	 Catch:{ all -> 0x0123 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x0123 }
        r6 = " , give up save :";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0123 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0123 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x0123 }
        r0 = r0.toString();	 Catch:{ all -> 0x0123 }
        cn.jiguang.e.d.c(r2, r0);	 Catch:{ all -> 0x0123 }
        a(r3);	 Catch:{ all -> 0x0077 }
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r0 = r1;
        goto L_0x0010;
    L_0x00f9:
        r0 = move-exception;
        r2 = "ReportUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0123 }
        r7 = "Filepath error of [";
        r6.<init>(r7);	 Catch:{ all -> 0x0123 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x0123 }
        r6 = "] , give up save :";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0123 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0123 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x0123 }
        r0 = r0.toString();	 Catch:{ all -> 0x0123 }
        cn.jiguang.e.d.c(r2, r0);	 Catch:{ all -> 0x0123 }
        a(r3);	 Catch:{ all -> 0x0077 }
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r0 = r1;
        goto L_0x0010;
    L_0x0123:
        r0 = move-exception;
    L_0x0124:
        a(r3);	 Catch:{ all -> 0x0077 }
        throw r0;	 Catch:{ all -> 0x0077 }
    L_0x0128:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0124;
    L_0x012b:
        r0 = move-exception;
        r2 = r3;
        goto L_0x007c;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.d.d.o.a(android.content.Context, java.lang.String, org.json.JSONObject):boolean");
    }

    private static boolean a(JSONObject jSONObject, Context context) {
        jSONObject.put("platform", "a");
        long d = d.d(context);
        if (d == 0) {
            cn.jiguang.e.d.i("ReportUtils", "miss uid when wrap container info");
            return false;
        }
        jSONObject.put("uid", d);
        String i = d.i(context);
        if (k.a(i)) {
            cn.jiguang.e.d.i("ReportUtils", "miss app_key when wrap container info");
            return false;
        }
        jSONObject.put("app_key", i);
        b.a().a(jSONObject);
        jSONObject.put("core_sdk_ver", "1.1.9");
        String g = cn.jiguang.d.a.a.g("");
        if (k.a(g)) {
            cn.jiguang.e.d.h("ReportUtils", "miss channel when wrap container info,but continue report...");
        } else {
            jSONObject.put(LogBuilder.KEY_CHANNEL, g);
        }
        if (k.a(cn.jiguang.d.a.i)) {
            cn.jiguang.e.d.h("ReportUtils", "miss app version when wrap container info,but continue report...");
        } else {
            jSONObject.put("app_version", cn.jiguang.d.a.i);
        }
        return true;
    }

    private static String b() {
        if (k.a(b) && "CN".equals(cn.jiguang.d.a.f.a())) {
            a(d.c());
        }
        return b;
    }

    private static JSONObject b(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (a(jSONObject, context)) {
                return jSONObject;
            }
        } catch (Exception e) {
            cn.jiguang.e.d.g("ReportUtils", "wrapContainerInfo exception:" + e);
        }
        return null;
    }

    public static JSONObject b(Context context, JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("itime", JCoreInterface.getReportTime());
            jSONObject.put("type", str);
            jSONObject.put("account_id", d.n(context));
        } catch (JSONException e) {
            cn.jiguang.e.d.g("ReportUtils", "fillBase exception:" + e);
        }
        return jSONObject;
    }

    static /* synthetic */ void b(Context context, JSONArray jSONArray, String str) {
        int i = 0;
        String str2 = "jpush_stat_cache_history.json";
        if (a == null) {
            a = a(context, str2);
        }
        JSONObject jSONObject = a;
        JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray("content");
        JSONArray jSONArray2 = optJSONArray == null ? new JSONArray() : optJSONArray;
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONArray2.put(jSONArray.get(i2));
                    }
                }
            } catch (JSONException e) {
            }
        }
        if (!a.c(context)) {
            jSONObject2.put("content", jSONArray2);
            a(context, "jpush_stat_cache_history.json", jSONObject2);
            return;
        }
        if (jSONArray2.length() > 0) {
            ArrayList a = a(jSONArray2, 40960);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a);
            try {
                cn.jiguang.e.d.c("ReportUtils", "log size:" + jSONArray2.toString().getBytes("utf-8").length);
            } catch (UnsupportedEncodingException e2) {
            }
            cn.jiguang.e.d.c("ReportUtils", "log divided into " + a.size() + " parts");
            int i3 = 0;
            while (i < a.size()) {
                optJSONArray = (JSONArray) a.get(i);
                if (i3 == 0) {
                    if (optJSONArray.length() <= 0) {
                        arrayList.remove(optJSONArray);
                    } else {
                        try {
                            jSONObject2.put("content", optJSONArray);
                            try {
                                if (a(jSONObject2, context)) {
                                    try {
                                        cn.jiguang.e.d.e("ReportUtils", "send log slice:" + jSONObject2.toString(1));
                                    } catch (JSONException e3) {
                                        cn.jiguang.e.d.e("ReportUtils", "send log slice:" + jSONObject2.toString());
                                    }
                                    int a2 = i.a(context, jSONObject2, true);
                                    if (!TextUtils.isEmpty(str) && str.equals(SdkType.JANALYTICS.name())) {
                                        cn.jiguang.e.d.d("ReportUtils", str + ",report " + (a2 == 200 ? "success" : "failed"));
                                    }
                                    switch (a2) {
                                        case -5:
                                        case -4:
                                        case -3:
                                        case -2:
                                            a(context, jSONObject2, optJSONArray, arrayList);
                                            break;
                                        case -1:
                                        case 404:
                                        case 429:
                                        case 500:
                                            a(context, jSONObject2, arrayList);
                                            break;
                                        case 200:
                                            a(context, jSONObject2, optJSONArray, arrayList);
                                            break;
                                        case 401:
                                            c(context);
                                            boolean z = true;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                b(context, jSONObject2);
                                return;
                            } catch (Exception e4) {
                                cn.jiguang.e.d.c("ReportUtils", "wrap container exception, give up send log:" + e4);
                                b(context, jSONObject2);
                                return;
                            }
                        } catch (JSONException e5) {
                            cn.jiguang.e.d.c("ReportUtils", "put content exception, give up send log:" + e5);
                            a(context, jSONObject2, optJSONArray, arrayList);
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
        a = jSONObject;
        a(context, "jpush_stat_cache_history.json", jSONObject);
    }

    static /* synthetic */ void b(Context context, JSONObject jSONObject, boolean z) {
        List list = null;
        JSONObject b = b(context);
        if (jSONObject != null) {
            JSONObject a = a(jSONObject, b);
            String str = z ? "prior" : "normal";
            if (b == null) {
                cn.jiguang.e.d.i("ReportUtils", "wrap data failed");
                m.a(context, str + File.separator + "nowrap", a);
            } else {
                list = m.a(context, str, a);
            }
        }
        a(context, list, b, false);
    }

    public static boolean b(String str) {
        b = b();
        return (k.a(str) || k.a(b)) ? false : str.equals(b);
    }

    public static String c(String str) {
        String f = cn.jiguang.d.a.f.f();
        try {
            InetAddress.getByName(f);
            return "https://" + f + str;
        } catch (Exception e) {
            return b();
        }
    }

    private static void c(Context context) {
        a = null;
        if (!a(context, "jpush_stat_cache_history.json", null)) {
            try {
                if (context.deleteFile(f("jpush_stat_cache_history.json"))) {
                    cn.jiguang.e.d.i("ReportUtils", "delete file success filename:" + f("jpush_stat_cache_history.json"));
                }
            } catch (IllegalArgumentException e) {
                cn.jiguang.e.d.i("ReportUtils", "clearHistotyFileContent e:" + e.getMessage());
            } catch (Exception e2) {
                cn.jiguang.e.d.i("ReportUtils", "clearHistotyFileContent e:" + e2.getMessage());
            }
        }
    }

    public static String d(String str) {
        String str2 = null;
        try {
            str2 = Base64.encodeToString(str.getBytes(), 10);
        } catch (Exception e) {
            cn.jiguang.e.d.i("getBasicAuthorization", "basic authorization encode failed");
        }
        return str2;
    }

    private static String e(String str) {
        String str2 = null;
        try {
            str2 = a.a(h.a(str.getBytes("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String f(String str) {
        if (!k.a(str)) {
            return str.equals("jpush_stat_cache_history.json") ? "history_file" : "current_session_file";
        } else {
            cn.jiguang.e.d.c("ReportUtils", "file_name is null , give up save ");
            return null;
        }
    }
}
