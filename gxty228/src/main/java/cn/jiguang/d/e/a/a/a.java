package cn.jiguang.d.e.a.a;

import android.content.Context;
import cn.jiguang.api.JResponse;
import cn.jiguang.d.e.a.b;
import cn.jiguang.d.e.a.c;
import cn.jiguang.d.e.a.e;
import cn.jiguang.d.e.a.f;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import java.nio.ByteBuffer;

public final class a {
    public static JResponse a(c cVar, ByteBuffer byteBuffer) {
        if (cVar == null) {
            d.i("JCommands", "");
            return null;
        }
        d.a("JCommands", "parseResponseInbound - head:" + cVar.toString());
        switch (cVar.c) {
            case 0:
                return new e(cVar, byteBuffer);
            case 1:
                return new cn.jiguang.d.e.a.d(cVar, byteBuffer);
            case 19:
                return new cn.jiguang.d.e.a.a(cVar, byteBuffer);
            case 25:
                return new b(cVar, byteBuffer);
            case 26:
                return new f(cVar, byteBuffer);
            default:
                d.g("JCommands", "Unknown command for parsing inbound.");
                return null;
        }
    }

    public static JResponse a(byte[] bArr) {
        c b = b(bArr);
        return b != null ? a(b.a(), b.b()) : null;
    }

    public static boolean a(Context context, byte[] bArr) {
        try {
            c b = b(bArr);
            if (b != null) {
                cn.jiguang.d.d.b.a();
                cn.jiguang.d.d.b.a(context, b.a(), b.b());
                return true;
            }
        } catch (Throwable th) {
            d.h("JCommands", "dispatchMessage error:" + th.getMessage());
        }
        return false;
    }

    private static c b(byte[] bArr) {
        c cVar = null;
        d.c("JCommands", "total bytes - " + k.a(bArr));
        if (bArr.length < 20) {
            d.h("JCommands", "Error: received body-length short than common head-length, return null");
            return cVar;
        }
        Object obj = new byte[20];
        System.arraycopy(bArr, 0, obj, 0, 20);
        int i = obj[0] & 128;
        c cVar2 = new c(false, obj);
        d.c("JCommands", "parsed head - " + cVar2.toString());
        int i2 = cVar2.a - 20;
        if (i2 < 0 || bArr.length < 20) {
            d.h("JCommands", "Error: totalBytes length error with no encrypted, return null");
            return cVar;
        }
        ByteBuffer wrap;
        d.a("JCommands", "body size:" + i2);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 20, bArr2, 0, i2);
        if (i > 0) {
            try {
                wrap = ByteBuffer.wrap(cn.jiguang.d.g.a.a.b(cn.jiguang.d.g.a.a.a(), bArr2));
            } catch (Exception e) {
                d.c("JCommands", "decryptBytes error:" + e.getMessage());
                return cVar;
            }
        }
        System.arraycopy(bArr, 20, bArr2, 0, i2);
        wrap = ByteBuffer.wrap(bArr2);
        return new c(cVar2, wrap);
    }
}
