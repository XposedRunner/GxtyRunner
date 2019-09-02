package cn.jiguang.d.b.a.a;

import android.text.TextUtils;
import cn.jiguang.d.b.a.a;
import cn.jiguang.d.b.a.d;
import cn.jiguang.d.b.a.e;
import cn.jiguang.d.b.f;
import cn.jiguang.d.b.i;
import cn.jiguang.d.d.c;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class j {
    final d a;

    public j(d dVar) {
        this.a = dVar;
    }

    private a a(InetAddress inetAddress, int i, String str, DatagramSocket datagramSocket) {
        long currentTimeMillis;
        e e;
        int a;
        Throwable th;
        long j;
        String hostAddress = inetAddress.getHostAddress();
        long j2 = 0;
        long u = cn.jiguang.d.a.a.u() + System.currentTimeMillis();
        try {
            byte[] c = this.a.c();
            cn.jiguang.e.d.c("SisPolicy", "sis sendBuf=" + Arrays.toString(c));
            DatagramPacket datagramPacket = new DatagramPacket(c, c.length, inetAddress, i);
            u = System.currentTimeMillis();
            try {
                c = c.a(datagramSocket, datagramPacket);
                j2 = (System.currentTimeMillis() - u) / 1000;
                u = cn.jiguang.d.a.a.a(u);
                i a2 = c.a(new String(d.a(c).b));
                if (a2 == null) {
                    throw new e(5, "Can not parse sis info from host: " + hostAddress);
                }
                a2.h();
                cn.jiguang.e.d.f("SisPolicy", "Get sis info succeed with host: " + hostAddress + " type:" + str);
                cn.jiguang.d.a.a.g();
                cn.jiguang.d.a.a.a(a2.g());
                this.a.a(a.a(a2));
                a b = a.b(a2);
                if (b.a()) {
                    throw new e(5, "sis address is Empty from host:" + hostAddress);
                }
                this.a.c(new cn.jiguang.d.b.a.c(hostAddress, i));
                return b;
            } catch (Exception e2) {
                currentTimeMillis = (System.currentTimeMillis() - u) / 1000;
                try {
                    j2 = cn.jiguang.d.a.a.a(u);
                    throw new e(2, "Can not get sis response from host: - " + hostAddress + " - " + e2.getMessage());
                } catch (e e3) {
                    e = e3;
                    j2 = u;
                    u = currentTimeMillis;
                    try {
                        a = e.a();
                        try {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        a = 0;
                        if (a == 0) {
                            this.a.c(new cn.jiguang.d.b.a.c(hostAddress, i));
                        } else {
                            this.a.a(hostAddress, i, j2, u, a);
                            this.a.a(hostAddress, i, a);
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    j2 = u;
                    u = currentTimeMillis;
                    a = 0;
                    if (a == 0) {
                        this.a.c(new cn.jiguang.d.b.a.c(hostAddress, i));
                    } else {
                        this.a.a(hostAddress, i, j2, u, a);
                        this.a.a(hostAddress, i, a);
                    }
                    throw th;
                }
            } catch (e e4) {
                e = e4;
                u = currentTimeMillis;
                a = e.a();
                throw e;
            } catch (Throwable th5) {
                th = th5;
                u = currentTimeMillis;
                a = 0;
                if (a == 0) {
                    this.a.a(hostAddress, i, j2, u, a);
                    this.a.a(hostAddress, i, a);
                } else {
                    this.a.c(new cn.jiguang.d.b.a.c(hostAddress, i));
                }
                throw th;
            }
        } catch (Exception e22) {
            throw new e(1, "Failed to package data - " + e22.getMessage());
        } catch (e e5) {
            e = e5;
            j = u;
            u = j2;
            j2 = j;
            a = e.a();
            throw e;
        } catch (Throwable th6) {
            th = th6;
            a = 0;
            j = u;
            u = j2;
            j2 = j;
            if (a == 0) {
                this.a.a(hostAddress, i, j2, u, a);
                this.a.a(hostAddress, i, a);
            } else {
                this.a.c(new cn.jiguang.d.b.a.c(hostAddress, i));
            }
            throw th;
        }
    }

    abstract int a();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final cn.jiguang.d.b.a.a a(cn.jiguang.d.b.a.a r10) {
        /*
        r9 = this;
        r2 = 0;
        r0 = "SisPolicy";
        r1 = new java.lang.StringBuilder;
        r3 = "sisAddresses:";
        r1.<init>(r3);
        r1 = r1.append(r10);
        r1 = r1.toString();
        cn.jiguang.e.d.c(r0, r1);
        if (r10 == 0) goto L_0x001d;
    L_0x0017:
        r0 = r10.a();
        if (r0 == 0) goto L_0x001f;
    L_0x001d:
        r0 = r2;
    L_0x001e:
        return r0;
    L_0x001f:
        r3 = new java.net.DatagramSocket;	 Catch:{ Throwable -> 0x0163, all -> 0x015b }
        r3.<init>();	 Catch:{ Throwable -> 0x0163, all -> 0x015b }
        r4 = r10.b();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
    L_0x0028:
        r0 = r4.hasNext();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        if (r0 == 0) goto L_0x010f;
    L_0x002e:
        r0 = r4.next();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r0.getKey();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = (cn.jiguang.d.b.a.c) r1;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r5 = r1.a;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r5 = cn.jiguang.d.d.c.b(r5);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        if (r5 != 0) goto L_0x006f;
    L_0x0048:
        r0 = "SisPolicy";
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = "Failed to resolve host - ";
        r5.<init>(r6);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r1.a;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r5.append(r1);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        cn.jiguang.e.d.e(r0, r1);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        goto L_0x0028;
    L_0x005f:
        r0 = move-exception;
        r1 = r3;
    L_0x0061:
        r3 = "SisPolicy";
        r4 = "Get sis info error :";
        cn.jiguang.e.d.d(r3, r4, r0);	 Catch:{ all -> 0x015f }
        if (r1 == 0) goto L_0x006d;
    L_0x006a:
        r1.close();	 Catch:{ Throwable -> 0x012d }
    L_0x006d:
        r0 = r2;
        goto L_0x001e;
    L_0x006f:
        r6 = r5.getHostAddress();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1.a = r6;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = "SisPolicy";
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r8 = "To get sis - host:";
        r7.<init>(r8);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r8 = r1.a;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r7 = r7.append(r8);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r8 = ", port:";
        r7 = r7.append(r8);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r8 = r1.b;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r7 = r7.append(r8);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r8 = " ,type:";
        r7 = r7.append(r8);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r7 = r7.append(r0);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r7 = r7.toString();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        cn.jiguang.e.d.d(r6, r7);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = r9.a;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = r6.a(r1);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        if (r6 == 0) goto L_0x00d4;
    L_0x00a9:
        r0 = "SisPolicy";
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = "Skip, already sis - ip:";
        r5.<init>(r6);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = r1.a;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = ", port:";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r1.b;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r5.append(r1);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        cn.jiguang.e.d.c(r0, r1);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        goto L_0x0028;
    L_0x00cd:
        r0 = move-exception;
    L_0x00ce:
        if (r3 == 0) goto L_0x00d3;
    L_0x00d0:
        r3.close();	 Catch:{ Throwable -> 0x0144 }
    L_0x00d3:
        throw r0;
    L_0x00d4:
        r1 = r1.b;	 Catch:{ Throwable -> 0x00f8, all -> 0x00cd }
        r0 = r9.a(r5, r1, r0, r3);	 Catch:{ Throwable -> 0x00f8, all -> 0x00cd }
        if (r3 == 0) goto L_0x001e;
    L_0x00dc:
        r3.close();	 Catch:{ Throwable -> 0x00e1 }
        goto L_0x001e;
    L_0x00e1:
        r1 = move-exception;
        r2 = "SisPolicy";
        r3 = new java.lang.StringBuilder;
        r4 = "Exception when close udp socket - ";
        r3.<init>(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        cn.jiguang.e.d.i(r2, r1);
        goto L_0x001e;
    L_0x00f8:
        r0 = move-exception;
        r1 = "SisPolicy";
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r6 = "singleSendSis failed, error:";
        r5.<init>(r6);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r0 = r5.append(r0);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        cn.jiguang.e.d.h(r1, r0);	 Catch:{ Throwable -> 0x005f, all -> 0x00cd }
        goto L_0x0028;
    L_0x010f:
        if (r3 == 0) goto L_0x006d;
    L_0x0111:
        r3.close();	 Catch:{ Throwable -> 0x0116 }
        goto L_0x006d;
    L_0x0116:
        r0 = move-exception;
        r1 = "SisPolicy";
        r3 = new java.lang.StringBuilder;
        r4 = "Exception when close udp socket - ";
        r3.<init>(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        cn.jiguang.e.d.i(r1, r0);
        goto L_0x006d;
    L_0x012d:
        r0 = move-exception;
        r1 = "SisPolicy";
        r3 = new java.lang.StringBuilder;
        r4 = "Exception when close udp socket - ";
        r3.<init>(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        cn.jiguang.e.d.i(r1, r0);
        goto L_0x006d;
    L_0x0144:
        r1 = move-exception;
        r2 = "SisPolicy";
        r3 = new java.lang.StringBuilder;
        r4 = "Exception when close udp socket - ";
        r3.<init>(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        cn.jiguang.e.d.i(r2, r1);
        goto L_0x00d3;
    L_0x015b:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00ce;
    L_0x015f:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00ce;
    L_0x0163:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.d.b.a.a.j.a(cn.jiguang.d.b.a.a):cn.jiguang.d.b.a.a");
    }

    public final int b(a aVar) {
        cn.jiguang.e.d.c("SisPolicy", "action - openConnection, addressesList:" + aVar);
        if (aVar == null || aVar.a()) {
            return -1;
        }
        Iterator b = aVar.b();
        while (b.hasNext()) {
            if (this.a.e().d()) {
                return 2;
            }
            int i;
            Entry entry = (Entry) b.next();
            cn.jiguang.d.b.a.c cVar = (cn.jiguang.d.b.a.c) entry.getKey();
            String str = (String) entry.getValue();
            Object obj = (TextUtils.isEmpty(cVar.a) || cVar.b <= 0) ? null : 1;
            if (obj == null) {
                i = 1;
                continue;
            } else {
                String str2 = cVar.a;
                int i2 = cVar.b;
                if (this.a.b(cVar)) {
                    cn.jiguang.e.d.e("SisPolicy", "Skip, already opened connection - ip:" + str2 + ", port:" + i2);
                    i = 1;
                    continue;
                } else {
                    cn.jiguang.e.d.d("SisPolicy", "Open connection with " + str + " - ip:" + str2 + ", port:" + i2);
                    long currentTimeMillis = System.currentTimeMillis();
                    f e = this.a.e();
                    if (e.d()) {
                        cn.jiguang.e.d.g("ConnectingHelper", "action - injectConnection, unexpected...");
                        i = -991;
                    } else {
                        i = cn.jiguang.d.f.d.a().b().a(str2, i2);
                        if (i != 0) {
                            if (e.d()) {
                                cn.jiguang.e.d.c("ConnectingHelper", "Open connection failed - ret:" + i);
                            } else {
                                cn.jiguang.e.d.d("ConnectingHelper", "Open connection failed - ret:" + i);
                            }
                        }
                    }
                    if (i != 0) {
                        this.a.b(str2, i2, cn.jiguang.d.a.a.a(currentTimeMillis), (System.currentTimeMillis() - currentTimeMillis) / 1000, i);
                        this.a.b(str2, i2, i);
                        continue;
                    } else {
                        continue;
                    }
                }
            }
            if (i == 0) {
                cn.jiguang.d.a.a.a(cVar.a, cVar.b);
                cn.jiguang.e.d.f("SisPolicy", "Succeed to open connection - ip:" + cVar.a + ", port:" + cVar.b);
                return 0;
            }
        }
        return -1;
    }
}
