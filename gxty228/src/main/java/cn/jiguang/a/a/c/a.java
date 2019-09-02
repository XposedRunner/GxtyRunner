package cn.jiguang.a.a.c;

import android.content.Context;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.i;
import cn.jiguang.g.k;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a extends Thread {
    private static final Object c = new Object();
    private static final Object d = new Object();
    private String a;
    private Context b;

    public a(Context context, String str) {
        this.b = context;
        this.a = str;
    }

    public static String a(Context context) {
        Closeable openFileInput;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        if (context == null) {
            d.g("AppsUtil", "context did not init, return");
            return null;
        }
        try {
            openFileInput = context.openFileInput("appPackageNames_v2");
            try {
                byte[] bArr = new byte[(openFileInput.available() + 1)];
                openFileInput.read(bArr);
                o.a(openFileInput);
                try {
                    String str = new String(bArr, "UTF-8");
                    if (!k.a(str)) {
                        return str;
                    }
                    d.c("AppsUtil", "appPackageNames_v2 is null, return null");
                    return null;
                } catch (UnsupportedEncodingException e3) {
                    d.c("AppsUtil", "can't encoding appPackageNames_v2, give up read :" + e3.getMessage());
                    return null;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                try {
                    d.c("AppsUtil", "can't open appPackageNames_v2 inputStream, give up read  :" + e.getMessage());
                    o.a(openFileInput);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    o.a(openFileInput);
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                d.c("AppsUtil", "can't read appPackageNames_v2, give up read :" + e2.getMessage());
                o.a(openFileInput);
                return null;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            openFileInput = null;
            d.c("AppsUtil", "can't open appPackageNames_v2 inputStream, give up read  :" + e.getMessage());
            o.a(openFileInput);
            return null;
        } catch (IOException e7) {
            e2 = e7;
            openFileInput = null;
            d.c("AppsUtil", "can't read appPackageNames_v2, give up read :" + e2.getMessage());
            o.a(openFileInput);
            return null;
        } catch (Throwable th3) {
            openFileInput = null;
            th = th3;
            o.a(openFileInput);
            throw th;
        }
    }

    public static void a(Context context, ArrayList<h> arrayList) {
        if (context == null) {
            d.g("AppsUtil", "context did not init, return");
        }
        if (arrayList == null || arrayList.isEmpty()) {
            d.a("AppsUtil", "apps list is null, return");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(((h) arrayList.get(i)).b);
            if (i != arrayList.size() - 1) {
                stringBuilder.append("&&");
            }
        }
        d.c("AppsUtil", "store appPackageNames:" + stringBuilder.toString());
        if (a(context, stringBuilder.toString())) {
            d.c("AppsUtil", "store app package list success");
        }
    }

    private void a(HashSet<String> hashSet) {
        if (this.b == null) {
            d.g("AppsUtil", "context did not init, return");
        } else if (hashSet == null || hashSet.isEmpty()) {
            d.g("AppsUtil", "old applist is null, return");
        } else {
            h hVar;
            JSONObject a;
            d.c("AppsUtil", "ReportUserUpdateApps oldApplist.size():" + hashSet.size());
            JSONArray jSONArray = new JSONArray();
            ArrayList a2 = d.a(this.b, true);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a2);
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                hVar = (h) it.next();
                if (hashSet.remove(hVar.b)) {
                    arrayList.remove(hVar);
                }
                if (!k.a(this.a) && hVar.b.equals(this.a)) {
                    arrayList.remove(hVar);
                }
            }
            if (!k.a(this.a)) {
                hashSet.remove(this.a);
            }
            d.c("AppsUtil", "uninstalled appPackageName:" + hashSet.toString() + "\n installed appPackageName:" + arrayList.toString());
            it = hashSet.iterator();
            while (it.hasNext()) {
                a = i.a((String) it.next());
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                hVar = (h) it2.next();
                a = i.a(hVar.b, hVar.e);
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            if (jSONArray.length() > 0) {
                o.a(this.b, jSONArray);
            }
            if (jSONArray.length() > 0 || !k.a(this.a)) {
                a(this.b, a2);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r7, java.lang.String r8) {
        /*
        r0 = 0;
        if (r7 != 0) goto L_0x000b;
    L_0x0003:
        r1 = "AppsUtil";
        r2 = "context did not init, return";
        cn.jiguang.e.d.g(r1, r2);
    L_0x000a:
        return r0;
    L_0x000b:
        r3 = d;
        monitor-enter(r3);
        if (r8 == 0) goto L_0x0047;
    L_0x0010:
        r1 = "AppsUtil";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003b }
        r4 = "save log in writeHistoryLog:\n";
        r2.<init>(r4);	 Catch:{ Exception -> 0x003b }
        r2 = r2.append(r8);	 Catch:{ Exception -> 0x003b }
        r2 = r2.toString();	 Catch:{ Exception -> 0x003b }
        cn.jiguang.e.d.a(r1, r2);	 Catch:{ Exception -> 0x003b }
    L_0x0024:
        r2 = 0;
        r1 = "appPackageNames_v2";
        r4 = 0;
        r2 = r7.openFileOutput(r1, r4);	 Catch:{ FileNotFoundException -> 0x0050, UnsupportedEncodingException -> 0x006e, IOException -> 0x008d, NullPointerException -> 0x00ac }
        r1 = "UTF-8";
        r1 = r8.getBytes(r1);	 Catch:{ FileNotFoundException -> 0x0050, UnsupportedEncodingException -> 0x006e, IOException -> 0x008d, NullPointerException -> 0x00ac }
        r2.write(r1);	 Catch:{ FileNotFoundException -> 0x0050, UnsupportedEncodingException -> 0x006e, IOException -> 0x008d, NullPointerException -> 0x00ac }
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        r0 = 1;
        goto L_0x000a;
    L_0x003b:
        r1 = move-exception;
        r2 = "AppsUtil";
        r4 = "save log in writeHistoryLog";
        cn.jiguang.e.d.c(r2, r4, r1);	 Catch:{ all -> 0x0044 }
        goto L_0x0024;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        throw r0;
    L_0x0047:
        r1 = "AppsUtil";
        r2 = "applist is null";
        cn.jiguang.e.d.c(r1, r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x000a;
    L_0x0050:
        r1 = move-exception;
        r4 = "AppsUtil";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r6 = "can't open appPackageNames_v2 outputStream, give up save :";
        r5.<init>(r6);	 Catch:{ all -> 0x00cb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00cb }
        r1 = r5.append(r1);	 Catch:{ all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x00cb }
        cn.jiguang.e.d.c(r4, r1);	 Catch:{ all -> 0x00cb }
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x000a;
    L_0x006e:
        r1 = move-exception;
        r4 = "AppsUtil";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r6 = "can't encoding appPackageNames_v2 , give up save :";
        r5.<init>(r6);	 Catch:{ all -> 0x00cb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00cb }
        r1 = r5.append(r1);	 Catch:{ all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x00cb }
        cn.jiguang.e.d.c(r4, r1);	 Catch:{ all -> 0x00cb }
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x000a;
    L_0x008d:
        r1 = move-exception;
        r4 = "AppsUtil";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r6 = "can't write appPackageNames_v2 , give up save :";
        r5.<init>(r6);	 Catch:{ all -> 0x00cb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00cb }
        r1 = r5.append(r1);	 Catch:{ all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x00cb }
        cn.jiguang.e.d.c(r4, r1);	 Catch:{ all -> 0x00cb }
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x000a;
    L_0x00ac:
        r1 = move-exception;
        r4 = "AppsUtil";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r6 = "Filepath error of [appPackageNames_v2] , give up save :";
        r5.<init>(r6);	 Catch:{ all -> 0x00cb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00cb }
        r1 = r5.append(r1);	 Catch:{ all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x00cb }
        cn.jiguang.e.d.c(r4, r1);	 Catch:{ all -> 0x00cb }
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x000a;
    L_0x00cb:
        r0 = move-exception;
        cn.jiguang.d.d.o.a(r2);	 Catch:{ all -> 0x0044 }
        throw r0;	 Catch:{ all -> 0x0044 }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.a.a.c.a.a(android.content.Context, java.lang.String):boolean");
    }

    public final void run() {
        try {
            synchronized (c) {
                if (this.b == null) {
                    d.g("AppsUtil", "context did not init, return");
                    return;
                }
                String a = a(this.b);
                d.c("AppsUtil", "action check old app package list:" + a);
                if (a == null) {
                    return;
                }
                HashSet hashSet;
                cn.jiguang.d.a.d.a(this.b, "last_check_userapp_status", Long.valueOf(System.currentTimeMillis()));
                if (a == null) {
                    d.a("AppsUtil", "Convert2HashSet applist was null, return");
                    hashSet = null;
                } else {
                    String[] split = a.replace("\u0000", "").split("&&");
                    hashSet = new HashSet();
                    Collections.addAll(hashSet, split);
                }
                a(hashSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            d.c("AppsUtil", "checkUserApps execption:" + e.getMessage());
        }
    }
}
