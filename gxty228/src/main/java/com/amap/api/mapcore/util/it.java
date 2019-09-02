package com.amap.api.mapcore.util;

import com.amap.api.maps.AMapException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: BaseNetManager */
public class it {
    public static int a = 0;
    public static String b = "";
    private static it c;

    /* compiled from: BaseNetManager */
    public interface a {
        URLConnection a(Proxy proxy, URL url);
    }

    public static it a() {
        if (c == null) {
            c = new it();
        }
        return c;
    }

    public byte[] a(iy iyVar) throws gp {
        gp e;
        try {
            jb a = a(iyVar, true);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] b(iy iyVar) throws gp {
        gp e;
        try {
            jb a = a(iyVar, false);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            gw.a(th, "bm", "msp");
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void c(iy iyVar) throws gp {
        if (iyVar == null) {
            throw new gp("requeust is null");
        } else if (iyVar.c() == null || "".equals(iyVar.c())) {
            throw new gp("request url is empty");
        }
    }

    public jb a(iy iyVar, boolean z) throws gp {
        gp e;
        try {
            Proxy proxy;
            c(iyVar);
            if (iyVar.h == null) {
                proxy = null;
            } else {
                proxy = iyVar.h;
            }
            return new iw(iyVar.f, iyVar.g, proxy, z).a(iyVar.m(), iyVar.a(), iyVar.n());
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }
}
