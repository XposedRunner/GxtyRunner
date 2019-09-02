package cn.jiguang.d.c;

import android.support.v4.internal.view.SupportMenu;
import cn.jiguang.e.d;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class f {
    public static List<p> a(String str) {
        List<p> arrayList = new ArrayList();
        try {
            String[] a = o.b().a();
            byte[] b = h.a(m.a(j.a(j.a(str), j.a), 33, 1)).b(SupportMenu.USER_MASK);
            int length = a.length;
            int i = 0;
            while (i < length) {
                String str2 = a[i];
                try {
                    h hVar = new h(r.a(null, new InetSocketAddress(InetAddress.getByName(str2), 53), b, System.currentTimeMillis() + 1000));
                    m a2 = hVar.a();
                    if (a2 == null) {
                        break;
                    }
                    for (l lVar : hVar.a(1)) {
                        if (lVar.b().e() == a2.e()) {
                            int d = lVar.b().d();
                            j b2 = lVar.b().b();
                            if (d == a2.c() && b2.equals(a2.b())) {
                                Iterator a3 = lVar.a();
                                while (a3.hasNext()) {
                                    p pVar = (p) a3.next();
                                    if (pVar.h() > 0) {
                                        arrayList.add(pVar);
                                    }
                                    d.c("DNSSrvUtils", "srv:" + pVar.toString());
                                }
                            }
                        }
                    }
                    i++;
                } catch (UnknownHostException e) {
                    d.h("DNSSrvUtils", "Get default ports error at " + str2 + ", with UnknownHostException:" + e.getMessage());
                } catch (IOException e2) {
                    d.h("DNSSrvUtils", "Get default ports error at " + str2 + ", with IOException:" + e2.getMessage());
                }
            }
        } catch (s e3) {
            d.h("DNSSrvUtils", "Get default ports error with TextParseException");
        } catch (k e4) {
            d.h("DNSSrvUtils", "Get default ports error with NameTooLongException");
        } catch (Exception e5) {
            d.h("DNSSrvUtils", "Get default ports error with Exception");
        }
        return arrayList;
    }
}
