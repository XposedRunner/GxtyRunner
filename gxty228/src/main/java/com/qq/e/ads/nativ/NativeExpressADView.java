package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import com.qq.e.ads.nativ.NativeExpressAD.ADListenerAdapter;
import com.qq.e.comm.constants.Constants.KEYS;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.NEADVI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeExpressADView extends FrameLayout {
    private NEADVI a;
    private boolean b = false;
    private volatile boolean c = false;
    private NativeExpressMediaListener d;
    private AdData e;

    public NativeExpressADView(NEADI neadi, Context context, ADSize aDSize, String str, String str2, JSONObject jSONObject, HashMap<String, JSONObject> hashMap) {
        super(context);
        this.e = a((HashMap) hashMap);
        final Context context2 = context;
        final String str3 = str;
        final NEADI neadi2 = neadi;
        final ADSize aDSize2 = aDSize;
        final String str4 = str2;
        final JSONObject jSONObject2 = jSONObject;
        final HashMap<String, JSONObject> hashMap2 = hashMap;
        GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
            final /* synthetic */ NativeExpressADView h;

            public void run() {
                if (GDTADManager.getInstance().initWith(context2, str3)) {
                    try {
                        final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            private /* synthetic */ AnonymousClass1 b;

                            /* JADX WARNING: inconsistent code. */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                r11 = this;
                                r10 = 1;
                                r0 = r0;	 Catch:{ Throwable -> 0x006c }
                                if (r0 == 0) goto L_0x0064;
                            L_0x0005:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r9 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0;	 Catch:{ Throwable -> 0x006c }
                                r1 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r1 = r4;	 Catch:{ Throwable -> 0x006c }
                                r2 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r2 = r2;	 Catch:{ Throwable -> 0x006c }
                                r3 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r3 = r3.h;	 Catch:{ Throwable -> 0x006c }
                                r4 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r4 = r5;	 Catch:{ Throwable -> 0x006c }
                                r5 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r5 = r3;	 Catch:{ Throwable -> 0x006c }
                                r6 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r6 = r6;	 Catch:{ Throwable -> 0x006c }
                                r7 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r7 = r7;	 Catch:{ Throwable -> 0x006c }
                                r8 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r8 = r8;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.getNativeExpressADView(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x006c }
                                r9.a = r0;	 Catch:{ Throwable -> 0x006c }
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r1 = 1;
                                r0.b = true;	 Catch:{ Throwable -> 0x006c }
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.d;	 Catch:{ Throwable -> 0x006c }
                                if (r0 == 0) goto L_0x0053;
                            L_0x0044:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r1 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r1 = r1.h;	 Catch:{ Throwable -> 0x006c }
                                r1 = r1.d;	 Catch:{ Throwable -> 0x006c }
                                r0.setMediaListener(r1);	 Catch:{ Throwable -> 0x006c }
                            L_0x0053:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.c;	 Catch:{ Throwable -> 0x006c }
                                if (r0 == 0) goto L_0x0064;
                            L_0x005d:
                                r0 = r11.b;	 Catch:{ Throwable -> 0x006c }
                                r0 = r0.h;	 Catch:{ Throwable -> 0x006c }
                                r0.render();	 Catch:{ Throwable -> 0x006c }
                            L_0x0064:
                                r0 = r11.b;
                                r0 = r0.h;
                                r0.b = true;
                            L_0x006b:
                                return;
                            L_0x006c:
                                r0 = move-exception;
                                r1 = "Exception while init Native Express AD View Core";
                                com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x007a }
                                r0 = r11.b;
                                r0 = r0.h;
                                r0.b = true;
                                goto L_0x006b;
                            L_0x007a:
                                r0 = move-exception;
                                r1 = r11.b;
                                r1 = r1.h;
                                r1.b = true;
                                throw r0;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeExpressADView.1.1.run():void");
                            }
                        });
                        return;
                    } catch (Throwable th) {
                        GDTLogger.e("Exception while init Native Express AD View plugin", th);
                        return;
                    }
                }
                GDTLogger.e("Fail to init ADManager");
            }
        });
    }

    private static AdData a(HashMap<String, JSONObject> hashMap) {
        JSONObject jSONObject;
        Object obj;
        if (hashMap != null) {
            try {
                jSONObject = (JSONObject) hashMap.get(KEYS.AD_INFO);
            } catch (JSONException e) {
                obj = null;
            }
        } else {
            jSONObject = null;
        }
        obj = jSONObject != null ? jSONObject.get(KEYS.AD_INFO) : null;
        return obj instanceof AdData ? (AdData) obj : null;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public AdData getBoundData() {
        return this.e;
    }

    public void render() {
        if (!this.b) {
            this.c = true;
        } else if (this.a != null) {
            this.a.render();
        } else {
            GDTLogger.e("Native Express AD View Init Error");
        }
    }

    public void setAdSize(ADSize aDSize) {
        if (this.a != null) {
            this.a.setAdSize(aDSize);
        }
    }

    public void setMediaListener(NativeExpressMediaListener nativeExpressMediaListener) {
        this.d = nativeExpressMediaListener;
        if (this.a != null && nativeExpressMediaListener != null) {
            this.a.setAdListener(new ADListenerAdapter(nativeExpressMediaListener));
        }
    }
}
