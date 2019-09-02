package com.loc;

import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.AMapException;
import java.net.URLConnection;
import java.util.Map;

/* compiled from: BaseNetManager */
public final class an {
    public static int a = 0;
    public static String b = "";
    private static an c;

    /* compiled from: BaseNetManager */
    public interface a {
        URLConnection a();
    }

    public static an a() {
        if (c == null) {
            c = new an();
        }
        return c;
    }

    public static as a(ar arVar, boolean z) throws k {
        k e;
        if (arVar == null) {
            try {
                throw new k("requeust is null");
            } catch (k e2) {
                throw e2;
            } catch (Throwable th) {
                th.printStackTrace();
                e2 = new k(AMapException.ERROR_UNKNOWN);
            }
        } else if (arVar.c() == null || "".equals(arVar.c())) {
            throw new k("request url is empty");
        } else {
            String c;
            aq aqVar = new aq(arVar.c, arVar.d, arVar.e == null ? null : arVar.e, z);
            byte[] d = arVar.d();
            if (d == null || d.length == 0) {
                c = arVar.c();
            } else {
                Map b = arVar.b();
                if (b == null) {
                    c = arVar.c();
                } else {
                    String a = aq.a(b);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(arVar.c()).append(HttpUtils.URL_AND_PARA_SEPARATOR).append(a);
                    c = stringBuffer.toString();
                }
            }
            Map a2 = arVar.a();
            d = arVar.d();
            if (d == null || d.length == 0) {
                String a3 = aq.a(arVar.b());
                if (!TextUtils.isEmpty(a3)) {
                    d = dl.a(a3);
                }
            }
            return aqVar.a(c, a2, d);
        }
    }

    public static byte[] a(ar arVar) throws k {
        k e;
        try {
            as a = a(arVar, true);
            return a != null ? a.a : null;
        } catch (k e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new k(AMapException.ERROR_UNKNOWN);
        }
    }

    public static byte[] b(ar arVar) throws k {
        k e;
        try {
            as a = a(arVar, false);
            return a != null ? a.a : null;
        } catch (k e2) {
            throw e2;
        } catch (Throwable th) {
            g.a(th, "bm", "msp");
            e2 = new k(AMapException.ERROR_UNKNOWN);
        }
    }
}
