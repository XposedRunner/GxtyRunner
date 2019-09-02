package cn.jiguang.d.b.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.a.a.b.e;
import cn.jiguang.d.a.a;
import cn.jiguang.d.b.f;
import cn.jiguang.d.d.c;
import cn.jiguang.g.h;
import cn.jiguang.g.m;
import cn.jiguang.g.o;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import org.json.JSONArray;

public final class d {
    private final Context a;
    private final f b;
    private final long c;
    private final a d = new a();
    private final a e = new a();
    private byte[] f;
    private g g;
    private a h;
    private c i;
    private final LinkedList<f> j;

    public d(Context context, f fVar, long j) {
        this.a = context;
        this.b = fVar;
        this.c = j;
        this.j = f.a(a.o());
    }

    private int a(a aVar, DatagramSocket datagramSocket, byte[] bArr) {
        if (aVar != null) {
            Iterator b = aVar.b();
            while (b.hasNext()) {
                c cVar = (c) ((Entry) b.next()).getKey();
                int a = a(cVar.a, cVar.b, datagramSocket, bArr);
                if (a == 0) {
                    cn.jiguang.e.d.c("SisContext", "report at " + cVar + " succeed");
                    return a;
                }
                cn.jiguang.e.d.g("SisContext", "report at " + cVar + " failed(" + a + ")");
            }
        }
        return -1;
    }

    private static int a(String str, int i, DatagramSocket datagramSocket, byte[] bArr) {
        try {
            InetAddress b = c.b(str);
            if (b == null) {
                return -1;
            }
            byte[] bArr2 = a(c.a(datagramSocket, new DatagramPacket(bArr, bArr.length, b, i))).b;
            if (bArr2 != null && bArr2.length != 0) {
                return bArr2.length == 1 ? (short) bArr2[0] : (short) (((short) (bArr2[1] & 255)) | ((short) ((bArr2[0] & 255) << 8)));
            } else {
                throw new Exception("byte could not be empty");
            }
        } catch (Throwable th) {
            cn.jiguang.e.d.i("SisContext", "report failed : " + th);
            return -1;
        }
    }

    public static b a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new e(3, "response is empty!");
        }
        cn.jiguang.e.d.c("SisContext", "unpackSisResp");
        try {
            h hVar = new h(bArr);
            hVar.a();
            String str = new String(hVar.a(2));
            long b = hVar.b();
            int i = (int) (b >>> 24);
            b &= 16777215;
            hVar.a();
            byte[] c = hVar.c();
            if (b != 0) {
                try {
                    c = cn.jiguang.d.g.a.a.b(cn.jiguang.d.g.a.a.a(b), c);
                    if (c == null) {
                        throw new e(4, "decrypt response error");
                    }
                } catch (Exception e) {
                    throw new e(4, "decrypt response error");
                }
            }
            if ((i & 1) == 1) {
                try {
                    c = cn.jiguang.d.g.h.b(c);
                } catch (IOException e2) {
                }
            }
            return new b(str, c);
        } catch (o e3) {
            throw new e(3, "parse head error:" + e3.getMessage());
        }
    }

    private static byte[] a(String str, String str2) {
        byte[] bArr;
        byte[] bytes = str2.getBytes("UTF-8");
        cn.jiguang.e.d.c("SisContext", "packageSisSendBuf origin size = " + bytes.length);
        boolean z = true;
        try {
            byte[] a = cn.jiguang.d.g.h.a(bytes);
            if (a.length < bytes.length) {
                bArr = a;
            } else {
                z = false;
                bArr = bytes;
            }
        } catch (IOException e) {
            z = false;
            bArr = bytes;
        }
        int length = bArr.length;
        int b = cn.jiguang.d.g.a.a.b();
        return c.a(str, b, cn.jiguang.d.g.a.a.a(cn.jiguang.d.g.a.a.a((long) b), bArr), z, length);
    }

    private g g() {
        if (this.g == null) {
            String i = cn.jiguang.d.a.d.i(this.a);
            long d = cn.jiguang.d.a.d.d(this.a);
            int a = m.a(this.a);
            String b = m.b(this.a);
            e a2 = cn.jiguang.a.a.b.f.a(this.a);
            this.g = new g(a, i, "1.1.9", d, b, a2.b(), a2.c(), a2.d());
        }
        return this.g;
    }

    private void h() {
        while (this.j.size() > 5) {
            this.j.removeFirst();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            jSONArray.put(((f) it.next()).a());
        }
        a.e(jSONArray.toString());
    }

    public final a a() {
        if (this.h == null) {
            this.h = a.a(a.n());
        }
        return this.h;
    }

    public final void a(a aVar) {
        if (aVar != null && !aVar.equals(this.h)) {
            this.h = aVar;
            a.d(this.h.toString());
        }
    }

    public final void a(String str, int i, int i2) {
        this.d.a(str, i, String.valueOf(i2));
    }

    public final void a(String str, int i, long j, long j2, int i2) {
        if (c.a(str, i)) {
            cn.jiguang.e.d.c("SisContext", "addSisReportInfo");
            f fVar = new f();
            fVar.a = 1;
            fVar.b = new c(str, i);
            fVar.d = j;
            fVar.e = j2;
            fVar.j = i2;
            g g = g();
            if (g != null) {
                fVar.f = g.a();
                fVar.c = g.b();
                fVar.g = g.c();
                fVar.h = g.d();
                fVar.i = g.e();
            }
            this.j.add(fVar);
            h();
        }
    }

    public final boolean a(c cVar) {
        return this.d.a(cVar);
    }

    public final a b() {
        this.i = c.a(a.p());
        a aVar = new a();
        for (Entry entry : cn.jiguang.d.a.f.b().entrySet()) {
            aVar.a((String) entry.getKey(), ((Integer) entry.getValue()).intValue(), "hardcode");
        }
        if (this.i != null) {
            aVar.a(this.i.a, this.i.b, "last_good");
        }
        return aVar;
    }

    public final void b(String str, int i, int i2) {
        this.e.a(str, i, String.valueOf(i2));
    }

    public final void b(String str, int i, long j, long j2, int i2) {
        if (c.a(str, i)) {
            cn.jiguang.e.d.c("SisContext", "addConnReportInfo");
            f fVar = new f();
            fVar.a = 2;
            fVar.b = new c(str, i);
            fVar.d = j;
            fVar.e = j2;
            fVar.j = i2;
            fVar.f = m.a(this.a);
            fVar.c = cn.jiguang.d.a.d.d(this.a);
            e a = cn.jiguang.a.a.b.f.a(this.a);
            if (a != null && a.a()) {
                fVar.g = a.b();
                fVar.h = a.c();
                fVar.i = a.d();
            }
            this.j.add(fVar);
            h();
        }
    }

    public final boolean b(c cVar) {
        return this.e.a(cVar);
    }

    public final void c(c cVar) {
        if (!cVar.equals(this.i)) {
            this.i = cVar;
            a.f(this.i.toString());
        }
    }

    public final byte[] c() {
        if (this.f == null) {
            g();
            try {
                this.f = a("UG", this.g.f().toString());
            } catch (Exception e) {
                throw new e(1, "Failed to package data - " + e.getMessage());
            }
        }
        return this.f;
    }

    public final Context d() {
        return this.a;
    }

    public final f e() {
        return this.b;
    }

    public final void f() {
        try {
            if (a.q()) {
                DatagramSocket datagramSocket = new DatagramSocket();
                String o = a.o();
                if (TextUtils.isEmpty(o)) {
                    cn.jiguang.e.d.c("SisContext", "reportInfo is Empty, quit report");
                    return;
                }
                cn.jiguang.e.d.c("SisContext", "sis report data:" + o);
                byte[] a = a("DG", o);
                int a2 = a(b(), datagramSocket, a);
                if (a2 != 0) {
                    a2 = a(a.a(cn.jiguang.d.c.f.a(cn.jiguang.d.a.f.e()), false), datagramSocket, a);
                }
                if (a2 == 0) {
                    cn.jiguang.e.d.c("SisContext", "report succeed : " + o);
                    a.e(null);
                    return;
                }
                cn.jiguang.e.d.g("SisContext", "report failed(" + a2 + ") :" + o);
            }
        } catch (Throwable th) {
            cn.jiguang.e.d.g("SisContext", "sisReport failed, error:" + th);
        }
    }
}
