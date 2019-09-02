package com.loc;

import android.content.Context;
import android.database.Cursor;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Locale;

/* compiled from: ProxyUtil */
public final class di {
    private static String a() {
        String defaultHost;
        try {
            defaultHost = Proxy.getDefaultHost();
        } catch (Throwable th) {
            j.b(th, "pu", "gdh");
            defaultHost = null;
        }
        return defaultHost == null ? "null" : defaultHost;
    }

    public static java.net.Proxy a(Context context) {
        try {
            return VERSION.SDK_INT >= 11 ? a(context, new URI("http://restapi.amap.com")) : b(context);
        } catch (Throwable th) {
            j.b(th, "pu", "gp");
            return null;
        }
    }

    private static java.net.Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                java.net.Proxy proxy = (java.net.Proxy) select.get(0);
                return (proxy == null || proxy.type() == Type.DIRECT) ? null : proxy;
            } catch (Throwable th) {
                j.b(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static int b() {
        int i = -1;
        try {
            i = Proxy.getDefaultPort();
        } catch (Throwable th) {
            j.b(th, "pu", "gdp");
        }
        return i;
    }

    private static java.net.Proxy b(Context context) {
        String string;
        String c;
        Throwable th;
        Cursor cursor;
        int i;
        Throwable th2;
        String toLowerCase;
        Object obj;
        Object obj2;
        Object obj3;
        int i2;
        Cursor cursor2;
        int i3;
        int i4 = 80;
        if (c(context)) {
            Cursor query;
            String a;
            try {
                int b;
                query = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndex("apn"));
                            if (string != null) {
                                string = string.toLowerCase(Locale.US);
                            }
                            if (string != null && string.contains("ctwap")) {
                                a = a();
                                b = b();
                                try {
                                    Object obj4;
                                    if (TextUtils.isEmpty(a) || a.equals("null")) {
                                        obj4 = null;
                                        a = null;
                                    } else {
                                        obj4 = 1;
                                    }
                                    if (obj4 == null) {
                                        try {
                                            c = dl.c("QMTAuMC4wLjIwMA==");
                                        } catch (Throwable e) {
                                            th = e;
                                            cursor = query;
                                            i = b;
                                            th2 = th;
                                            j.b(th2, "pu", "ghp");
                                            string = df.s(context);
                                            if (string != null) {
                                                toLowerCase = string.toLowerCase(Locale.US);
                                                string = a();
                                                i = b();
                                                if (toLowerCase.indexOf("ctwap") != -1) {
                                                    if (!TextUtils.isEmpty(string)) {
                                                    }
                                                    string = a;
                                                    obj = null;
                                                    if (obj == null) {
                                                        string = dl.c("QMTAuMC4wLjIwMA==");
                                                    }
                                                    if (i != -1) {
                                                        i4 = i;
                                                    }
                                                } else if (toLowerCase.indexOf("wap") != -1) {
                                                    if (!TextUtils.isEmpty(string)) {
                                                    }
                                                    obj2 = null;
                                                    string = a;
                                                    if (obj2 == null) {
                                                        string = dl.c("QMTAuMC4wLjE3Mg==");
                                                    }
                                                }
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                c = string;
                                                if (c != null) {
                                                    obj3 = 1;
                                                    if (obj3 != null) {
                                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                    }
                                                    return null;
                                                }
                                                obj3 = null;
                                                if (obj3 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                }
                                                return null;
                                            }
                                            i4 = i;
                                            string = a;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            c = string;
                                            if (c != null) {
                                                obj3 = 1;
                                                if (obj3 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                }
                                                return null;
                                            }
                                            obj3 = null;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        } catch (Throwable e2) {
                                            th = e2;
                                            i2 = b;
                                            th2 = th;
                                            j.b(th2, "pu", "gPx1");
                                            th2.printStackTrace();
                                            if (query != null) {
                                                query.close();
                                            }
                                            i4 = i2;
                                            c = a;
                                            if (c != null) {
                                                obj3 = 1;
                                                if (obj3 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                }
                                                return null;
                                            }
                                            obj3 = null;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        }
                                    }
                                    c = a;
                                    if (b == -1) {
                                        b = 80;
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                    i4 = b;
                                } catch (Throwable e22) {
                                    a = null;
                                    cursor2 = query;
                                    i = b;
                                    th2 = e22;
                                    cursor = cursor2;
                                    j.b(th2, "pu", "ghp");
                                    string = df.s(context);
                                    if (string != null) {
                                        toLowerCase = string.toLowerCase(Locale.US);
                                        string = a();
                                        i = b();
                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                            if (TextUtils.isEmpty(string)) {
                                            }
                                            string = a;
                                            obj = null;
                                            if (obj == null) {
                                                string = dl.c("QMTAuMC4wLjIwMA==");
                                            }
                                            if (i != -1) {
                                                i4 = i;
                                            }
                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                            if (TextUtils.isEmpty(string)) {
                                            }
                                            obj2 = null;
                                            string = a;
                                            if (obj2 == null) {
                                                string = dl.c("QMTAuMC4wLjE3Mg==");
                                            }
                                        }
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        c = string;
                                        if (c != null) {
                                            obj3 = 1;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        }
                                        obj3 = null;
                                        if (obj3 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                        }
                                        return null;
                                    }
                                    i4 = i;
                                    string = a;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    c = string;
                                    if (c != null) {
                                        obj3 = 1;
                                        if (obj3 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                        }
                                        return null;
                                    }
                                    obj3 = null;
                                    if (obj3 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                    }
                                    return null;
                                } catch (Throwable e222) {
                                    a = null;
                                    i3 = b;
                                    th2 = e222;
                                    i2 = i3;
                                    j.b(th2, "pu", "gPx1");
                                    th2.printStackTrace();
                                    if (query != null) {
                                        query.close();
                                    }
                                    i4 = i2;
                                    c = a;
                                    if (c != null) {
                                        obj3 = 1;
                                        if (obj3 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                        }
                                        return null;
                                    }
                                    obj3 = null;
                                    if (obj3 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                    }
                                    return null;
                                }
                                if (c != null) {
                                    obj3 = 1;
                                    if (obj3 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                    }
                                }
                                obj3 = null;
                                if (obj3 != null) {
                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                }
                            } else if (string != null) {
                                if (string.contains("wap")) {
                                    a = a();
                                    i2 = b();
                                    try {
                                        if (TextUtils.isEmpty(a) || a.equals("null")) {
                                            obj3 = null;
                                            a = null;
                                        } else {
                                            obj3 = 1;
                                        }
                                        if (obj3 == null) {
                                            try {
                                                string = dl.c("QMTAuMC4wLjE3Mg==");
                                            } catch (SecurityException e3) {
                                                th2 = e3;
                                                cursor2 = query;
                                                i = i2;
                                                cursor = cursor2;
                                                try {
                                                    j.b(th2, "pu", "ghp");
                                                    string = df.s(context);
                                                    if (string != null) {
                                                        toLowerCase = string.toLowerCase(Locale.US);
                                                        string = a();
                                                        i = b();
                                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                                            if (TextUtils.isEmpty(string)) {
                                                            }
                                                            string = a;
                                                            obj = null;
                                                            if (obj == null) {
                                                                string = dl.c("QMTAuMC4wLjIwMA==");
                                                            }
                                                            if (i != -1) {
                                                                i4 = i;
                                                            }
                                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                                            if (TextUtils.isEmpty(string)) {
                                                            }
                                                            obj2 = null;
                                                            string = a;
                                                            if (obj2 == null) {
                                                                string = dl.c("QMTAuMC4wLjE3Mg==");
                                                            }
                                                        }
                                                        if (cursor != null) {
                                                            try {
                                                                cursor.close();
                                                            } catch (Throwable e2222) {
                                                                j.b(e2222, "pu", "gPx2");
                                                            }
                                                        }
                                                        c = string;
                                                        if (c != null) {
                                                            try {
                                                                obj3 = 1;
                                                                if (obj3 != null) {
                                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                                }
                                                            } catch (Throwable th22) {
                                                                g.a(th22, "pu", "gp2");
                                                                th22.printStackTrace();
                                                            }
                                                            return null;
                                                        }
                                                        obj3 = null;
                                                        if (obj3 != null) {
                                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                        }
                                                        return null;
                                                    }
                                                    i4 = i;
                                                    string = a;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    c = string;
                                                    if (c != null) {
                                                        obj3 = 1;
                                                        if (obj3 != null) {
                                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                        }
                                                        return null;
                                                    }
                                                    obj3 = null;
                                                    if (obj3 != null) {
                                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                    }
                                                    return null;
                                                } catch (Throwable th3) {
                                                    th22 = th3;
                                                    query = cursor;
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable e22222) {
                                                            j.b(e22222, "pu", "gPx2");
                                                        }
                                                    }
                                                    throw th22;
                                                }
                                            } catch (Throwable th4) {
                                                th22 = th4;
                                                try {
                                                    j.b(th22, "pu", "gPx1");
                                                    th22.printStackTrace();
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable th222) {
                                                            j.b(th222, "pu", "gPx2");
                                                        }
                                                    }
                                                    i4 = i2;
                                                    c = a;
                                                    if (c != null) {
                                                        obj3 = 1;
                                                        if (obj3 != null) {
                                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                        }
                                                        return null;
                                                    }
                                                    obj3 = null;
                                                    if (obj3 != null) {
                                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                    }
                                                    return null;
                                                } catch (Throwable th5) {
                                                    th222 = th5;
                                                    if (query != null) {
                                                        query.close();
                                                    }
                                                    throw th222;
                                                }
                                            }
                                        }
                                        string = a;
                                        if (i2 == -1) {
                                            c = string;
                                            b = 80;
                                        } else {
                                            i3 = i2;
                                            c = string;
                                            b = i3;
                                        }
                                        if (query != null) {
                                            try {
                                                query.close();
                                            } catch (Throwable th6) {
                                                j.b(th6, "pu", "gPx2");
                                            }
                                        }
                                        i4 = b;
                                    } catch (SecurityException e4) {
                                        th222 = e4;
                                        a = null;
                                        i3 = i2;
                                        cursor = query;
                                        i = i3;
                                        j.b(th222, "pu", "ghp");
                                        string = df.s(context);
                                        if (string != null) {
                                            toLowerCase = string.toLowerCase(Locale.US);
                                            string = a();
                                            i = b();
                                            if (toLowerCase.indexOf("ctwap") != -1) {
                                                if (TextUtils.isEmpty(string) || string.equals("null")) {
                                                    string = a;
                                                    obj = null;
                                                } else {
                                                    obj = 1;
                                                }
                                                if (obj == null) {
                                                    string = dl.c("QMTAuMC4wLjIwMA==");
                                                }
                                                if (i != -1) {
                                                    i4 = i;
                                                }
                                            } else if (toLowerCase.indexOf("wap") != -1) {
                                                if (TextUtils.isEmpty(string) || string.equals("null")) {
                                                    obj2 = null;
                                                    string = a;
                                                } else {
                                                    obj2 = 1;
                                                }
                                                if (obj2 == null) {
                                                    string = dl.c("QMTAuMC4wLjE3Mg==");
                                                }
                                            }
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            c = string;
                                            if (c != null) {
                                                obj3 = 1;
                                                if (obj3 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                                }
                                                return null;
                                            }
                                            obj3 = null;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        }
                                        i4 = i;
                                        string = a;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        c = string;
                                        if (c != null) {
                                            obj3 = 1;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        }
                                        obj3 = null;
                                        if (obj3 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                        }
                                        return null;
                                    } catch (Throwable th7) {
                                        th222 = th7;
                                        a = null;
                                        j.b(th222, "pu", "gPx1");
                                        th222.printStackTrace();
                                        if (query != null) {
                                            query.close();
                                        }
                                        i4 = i2;
                                        c = a;
                                        if (c != null) {
                                            obj3 = 1;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                            return null;
                                        }
                                        obj3 = null;
                                        if (obj3 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                        }
                                        return null;
                                    }
                                    if (c != null) {
                                        if (c.length() > 0 && i4 != -1) {
                                            obj3 = 1;
                                            if (obj3 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                            }
                                        }
                                    }
                                    obj3 = null;
                                    if (obj3 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                    }
                                }
                            }
                        }
                    } catch (SecurityException e5) {
                        th222 = e5;
                        cursor = query;
                        a = null;
                        i = -1;
                        j.b(th222, "pu", "ghp");
                        string = df.s(context);
                        if (string != null) {
                            toLowerCase = string.toLowerCase(Locale.US);
                            string = a();
                            i = b();
                            if (toLowerCase.indexOf("ctwap") != -1) {
                                if (TextUtils.isEmpty(string)) {
                                }
                                string = a;
                                obj = null;
                                if (obj == null) {
                                    string = dl.c("QMTAuMC4wLjIwMA==");
                                }
                                if (i != -1) {
                                    i4 = i;
                                }
                            } else if (toLowerCase.indexOf("wap") != -1) {
                                if (TextUtils.isEmpty(string)) {
                                }
                                obj2 = null;
                                string = a;
                                if (obj2 == null) {
                                    string = dl.c("QMTAuMC4wLjE3Mg==");
                                }
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            c = string;
                            if (c != null) {
                                obj3 = 1;
                                if (obj3 != null) {
                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                                }
                                return null;
                            }
                            obj3 = null;
                            if (obj3 != null) {
                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                            }
                            return null;
                        }
                        i4 = i;
                        string = a;
                        if (cursor != null) {
                            cursor.close();
                        }
                        c = string;
                        if (c != null) {
                            obj3 = 1;
                            if (obj3 != null) {
                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                            }
                            return null;
                        }
                        obj3 = null;
                        if (obj3 != null) {
                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                        }
                        return null;
                    } catch (Throwable th8) {
                        th222 = th8;
                        i2 = -1;
                        a = null;
                        j.b(th222, "pu", "gPx1");
                        th222.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        i4 = i2;
                        c = a;
                        if (c != null) {
                            obj3 = 1;
                            if (obj3 != null) {
                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                            }
                            return null;
                        }
                        obj3 = null;
                        if (obj3 != null) {
                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                        }
                        return null;
                    }
                }
                b = -1;
                c = null;
                if (query != null) {
                    query.close();
                }
                i4 = b;
            } catch (SecurityException e6) {
                th222 = e6;
                cursor = null;
                i = -1;
                a = null;
                j.b(th222, "pu", "ghp");
                string = df.s(context);
                if (string != null) {
                    toLowerCase = string.toLowerCase(Locale.US);
                    string = a();
                    i = b();
                    if (toLowerCase.indexOf("ctwap") != -1) {
                        if (TextUtils.isEmpty(string)) {
                        }
                        string = a;
                        obj = null;
                        if (obj == null) {
                            string = dl.c("QMTAuMC4wLjIwMA==");
                        }
                        if (i != -1) {
                            i4 = i;
                        }
                    } else if (toLowerCase.indexOf("wap") != -1) {
                        if (TextUtils.isEmpty(string)) {
                        }
                        obj2 = null;
                        string = a;
                        if (obj2 == null) {
                            string = dl.c("QMTAuMC4wLjE3Mg==");
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    c = string;
                    if (c != null) {
                        obj3 = 1;
                        if (obj3 != null) {
                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                        }
                        return null;
                    }
                    obj3 = null;
                    if (obj3 != null) {
                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                    }
                    return null;
                }
                i4 = i;
                string = a;
                if (cursor != null) {
                    cursor.close();
                }
                c = string;
                if (c != null) {
                    obj3 = 1;
                    if (obj3 != null) {
                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                    }
                    return null;
                }
                obj3 = null;
                if (obj3 != null) {
                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                }
                return null;
            } catch (Throwable th9) {
                th222 = th9;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th222;
            }
            if (c != null) {
                obj3 = 1;
                if (obj3 != null) {
                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
                }
            }
            obj3 = null;
            if (obj3 != null) {
                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c, i4));
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return df.q(context) == 0;
    }
}
