package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.mapcore.util.fy.a;
import com.amap.api.mapcore.util.fy.a.d;
import com.amap.api.mapcore.util.fy.a.e;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.amap.mapcore.AeUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.json.JSONObject;

/* compiled from: AuthTask */
public class il extends Thread {
    WeakReference<lj> a = null;
    private Context b;

    public il(Context context, lj ljVar) {
        this.b = context;
        this.a = new WeakReference(ljVar);
    }

    public void run() {
        boolean z = true;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                JSONObject optJSONObject;
                Object optString;
                String str;
                boolean a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("14S");
                stringBuilder.append(";");
                stringBuilder.append("11K");
                stringBuilder.append(";");
                stringBuilder.append("001");
                stringBuilder.append(";");
                stringBuilder.append("14M");
                stringBuilder.append(";");
                stringBuilder.append("14L");
                stringBuilder.append(";");
                stringBuilder.append("151");
                stringBuilder.append(";");
                stringBuilder.append("14Z");
                stringBuilder.append(";");
                stringBuilder.append("154");
                stringBuilder.append(";");
                stringBuilder.append("156");
                stringBuilder.append(";");
                stringBuilder.append("15C");
                a a2 = fy.a(this.b, en.e(), stringBuilder.toString(), null);
                if (!(fy.a == 1 || a2 == null || this.a == null || this.a.get() == null)) {
                    Message obtainMessage = ((lj) this.a.get()).getMainHandler().obtainMessage();
                    obtainMessage.what = 2;
                    if (a2.a != null) {
                        obtainMessage.obj = a2.a;
                    }
                    ((lj) this.a.get()).getMainHandler().sendMessage(obtainMessage);
                }
                if (!(a2 == null || a2.w == null)) {
                    optJSONObject = a2.w.optJSONObject("154");
                    if (optJSONObject != null && fy.a(optJSONObject.getString("able"), true)) {
                        Object optString2 = optJSONObject.optString("mc");
                        optString = optJSONObject.optString("si");
                        if (!TextUtils.isEmpty(optString2)) {
                            ec.a(this.b, "approval_number", "mc", optString2);
                        }
                        if (!TextUtils.isEmpty(optString)) {
                            ec.a(this.b, "approval_number", "si", optString);
                        }
                    }
                }
                if (!(a2 == null || a2.x == null)) {
                    gk e = en.e();
                    if (e != null) {
                        e.a(a2.x.a);
                    }
                }
                if (!(!MapsInitializer.isDownloadCoordinateConvertLibrary() || a2 == null || a2.B == null)) {
                    new gc(this.b, "3dmap", a2.B.a, a2.B.b).a();
                }
                if (a2 != null) {
                    a(a2);
                }
                String str2 = "able";
                if (a2 != null) {
                    if (a2.w != null) {
                        JSONObject optJSONObject2 = a2.w.optJSONObject("14M");
                        if (optJSONObject2 != null && optJSONObject2.has(str2) && fy.a(optJSONObject2.getString(str2), true)) {
                            int i = 2592000;
                            str = "time";
                            if (optJSONObject2.has(str)) {
                                i = Math.max(60, optJSONObject2.getInt(str));
                            }
                            if (!(System.currentTimeMillis() - ec.a(this.b, "Map3DCache", "time", Long.valueOf(0)).longValue() <= ((long) (i * 1000)) || this.a == null || this.a.get() == null)) {
                                ((lj) this.a.get()).b();
                            }
                        }
                    }
                }
                if (a2 != null) {
                    if (a2.w != null) {
                        try {
                            optJSONObject = a2.w.optJSONObject("14L");
                            if (optJSONObject != null && optJSONObject.has(str2)) {
                                a = fy.a(optJSONObject.getString(str2), false);
                                if (!(this.a == null || this.a.get() == null)) {
                                    lj ljVar = (lj) this.a.get();
                                    if (a) {
                                        z = false;
                                    }
                                    ljVar.i(z);
                                }
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
                if (a2 != null) {
                    if (a2.y != null) {
                        d dVar = a2.y;
                        if (dVar != null) {
                            Object obj = dVar.b;
                            Object obj2 = dVar.a;
                            optString = dVar.c;
                            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(optString)) {
                                new hk(this.b, null, en.e()).a();
                            } else {
                                new hk(this.b, new hm(obj2, obj, optString), en.e()).a();
                            }
                        } else {
                            new hk(this.b, null, en.e()).a();
                        }
                    }
                }
                if (!(a2 == null || a2.G == null)) {
                    hx.a().a(this.b, dq.a(), a2.G);
                }
                if (!(a2 == null || a2.z == null)) {
                    e eVar = a2.z;
                    if (eVar != null && eVar.a) {
                        hx.a().a(this.b, dq.a(), AeUtil.SO_FILENAME);
                        hx.a(this.b, en.e(), dq.a().c(), Arrays.asList(new String[]{"libAMapSDK_MAP_v6_4_1", "libAMapSDK_NAVI_v6_3_0", "librtbt800.so", "libwtbt800.so", "libztcodec2.so"}));
                    }
                }
                if (!(a2 == null || a2.w == null)) {
                    optJSONObject = a2.w.optJSONObject("156");
                    if (optJSONObject != null) {
                        dv.a(fy.a(optJSONObject.optString("able"), false));
                    }
                }
                if (!(a2 == null || a2.w == null)) {
                    optJSONObject = a2.w.optJSONObject("15C");
                    if (optJSONObject != null) {
                        a = fy.a(optJSONObject.optString("able"), false);
                        final String optString3 = optJSONObject.optString("logo_day_url");
                        final String optString4 = optJSONObject.optString("logo_day_md5");
                        str = optJSONObject.optString("logo_night_url");
                        final String optString5 = optJSONObject.optString("logo_night_md5");
                        em.a().a(new Runnable(this) {
                            final /* synthetic */ il f;

                            public void run() {
                                boolean z;
                                String str;
                                String str2;
                                String str3;
                                if (!(TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString3))) {
                                    z = a;
                                    str = AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME;
                                    str2 = optString3;
                                    str3 = optString4;
                                    if (z) {
                                        a dVar = new d(str2, str3, str);
                                        dVar.a("amap_web_logo", "md5_day");
                                        new jn(this.f.b, dVar, en.e()).a();
                                    }
                                    if (!(this.f.a == null || this.f.a.get() == null)) {
                                        ((lj) this.f.a.get()).a(str, z, 0);
                                    }
                                }
                                if (!TextUtils.isEmpty(optString5) && !TextUtils.isEmpty(str)) {
                                    z = a;
                                    str = AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME;
                                    str2 = str;
                                    str3 = optString5;
                                    if (z) {
                                        dVar = new d(str2, str3, str);
                                        dVar.a("amap_web_logo", "md5_night");
                                        new jn(this.f.b, dVar, en.e()).a();
                                    }
                                    if (this.f.a != null && this.f.a.get() != null) {
                                        ((lj) this.f.a.get()).a(str, z, 1);
                                    }
                                }
                            }
                        });
                    }
                }
                gz.a(this.b, en.e());
                interrupt();
                if (this.a != null && this.a.get() != null) {
                    ((lj) this.a.get()).setRunLowFrame(false);
                }
            }
        } catch (Throwable th2) {
            interrupt();
            gz.c(th2, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th2.printStackTrace();
        }
    }

    private void a(a aVar) {
        try {
            a.a aVar2 = aVar.x;
            if (aVar2 != null) {
                ek.a(this.b, "maploc", "ue", Boolean.valueOf(aVar2.a));
                JSONObject jSONObject = aVar2.c;
                int optInt = jSONObject.optInt("fn", 1000);
                int optInt2 = jSONObject.optInt("mpn", 0);
                int i = 500;
                int i2 = 30;
                if (optInt2 <= 500) {
                    i = optInt2;
                }
                if (i >= 30) {
                    i2 = i;
                }
                ji.a(optInt, fy.a(jSONObject.optString("igu"), false));
                ek.a(this.b, "maploc", "opn", Integer.valueOf(i2));
            }
        } catch (Throwable th) {
            gz.c(th, "AuthUtil", "loadConfigDataUploadException");
        }
    }
}
