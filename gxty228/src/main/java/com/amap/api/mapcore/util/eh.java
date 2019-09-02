package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.util.Hashtable;
import java.util.Map;

/* compiled from: AMapMessageHandler */
public final class eh implements Callback {
    private Map<Integer, c> a = new Hashtable();
    private Handler b;
    private HandlerThread c;
    private af d;
    private boolean e = false;

    public eh(Context context, af afVar, lk lkVar) {
        this.d = afVar;
        this.c = new HandlerThread("AMapMessageHandler");
        this.c.start();
        this.b = new Handler(this.c.getLooper(), this);
        this.e = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleMessage(android.os.Message r7) {
        /*
        r6 = this;
        r5 = 0;
        r0 = r6.e;	 Catch:{ Exception -> 0x0020 }
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return r5;
    L_0x0006:
        if (r7 == 0) goto L_0x0005;
    L_0x0008:
        r0 = r7.obj;	 Catch:{ Exception -> 0x0020 }
        r0 = (com.amap.api.mapcore.util.c) r0;	 Catch:{ Exception -> 0x0020 }
        r1 = r7.what;	 Catch:{ Exception -> 0x0020 }
        switch(r1) {
            case 1: goto L_0x0012;
            case 153: goto L_0x0025;
            default: goto L_0x0011;
        };	 Catch:{ Exception -> 0x0020 }
    L_0x0011:
        goto L_0x0005;
    L_0x0012:
        r1 = r6.d;	 Catch:{ Exception -> 0x0020 }
        r0 = r0.b;	 Catch:{ Exception -> 0x0020 }
        r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0020 }
        r0 = r0.intValue();	 Catch:{ Exception -> 0x0020 }
        r1.t(r0);	 Catch:{ Exception -> 0x0020 }
        goto L_0x0005;
    L_0x0020:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0005;
    L_0x0025:
        r1 = r6.a;	 Catch:{ Exception -> 0x0020 }
        monitor-enter(r1);	 Catch:{ Exception -> 0x0020 }
        r0 = r6.a;	 Catch:{ all -> 0x0058 }
        r0 = r0.keySet();	 Catch:{ all -> 0x0058 }
        r2 = r0.size();	 Catch:{ all -> 0x0058 }
        if (r2 <= 0) goto L_0x005b;
    L_0x0034:
        r2 = r0.iterator();	 Catch:{ all -> 0x0058 }
    L_0x0038:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0058 }
        if (r0 == 0) goto L_0x005b;
    L_0x003e:
        r0 = r2.next();	 Catch:{ all -> 0x0058 }
        r0 = (java.lang.Integer) r0;	 Catch:{ all -> 0x0058 }
        r3 = r6.a;	 Catch:{ all -> 0x0058 }
        r0 = r3.remove(r0);	 Catch:{ all -> 0x0058 }
        r0 = (com.amap.api.mapcore.util.c) r0;	 Catch:{ all -> 0x0058 }
        r3 = r6.b;	 Catch:{ all -> 0x0058 }
        r4 = r0.a;	 Catch:{ all -> 0x0058 }
        r0 = r3.obtainMessage(r4, r0);	 Catch:{ all -> 0x0058 }
        r0.sendToTarget();	 Catch:{ all -> 0x0058 }
        goto L_0x0038;
    L_0x0058:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        throw r0;	 Catch:{ Exception -> 0x0020 }
    L_0x005b:
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.eh.handleMessage(android.os.Message):boolean");
    }

    public void a(c cVar) {
        try {
            if (!this.e && cVar != null) {
                int i = cVar.a;
                if (cVar.a == TinkerReport.KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND) {
                    this.b.obtainMessage(TinkerReport.KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND).sendToTarget();
                    return;
                }
                synchronized (this.a) {
                    if (i < 33) {
                        this.a.put(Integer.valueOf(i), cVar);
                    }
                    if (i >= 33) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        this.e = true;
        if (this.c != null) {
            this.c.quit();
        }
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
    }
}
