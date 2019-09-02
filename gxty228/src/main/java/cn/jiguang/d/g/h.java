package cn.jiguang.d.g;

import cn.jiguang.g.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class h {
    public static byte[] a(byte[] bArr) {
        Closeable gZIPOutputStream;
        Throwable th;
        if (!(bArr == null || bArr.length == 0)) {
            Closeable byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    g.a(byteArrayOutputStream);
                    g.a(gZIPOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    g.a(byteArrayOutputStream);
                    g.a(gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                g.a(byteArrayOutputStream);
                g.a(gZIPOutputStream);
                throw th;
            }
        }
        return bArr;
    }

    public static byte[] b(byte[] bArr) {
        Closeable gZIPInputStream;
        Throwable th;
        if (!(bArr == null || bArr.length == 0)) {
            Closeable byteArrayOutputStream = new ByteArrayOutputStream();
            Closeable byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int read = gZIPInputStream.read(bArr2);
                        if (read < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    g.a(byteArrayOutputStream);
                    g.a(byteArrayInputStream);
                    g.a(gZIPInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    g.a(byteArrayOutputStream);
                    g.a(byteArrayInputStream);
                    g.a(gZIPInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPInputStream = null;
                g.a(byteArrayOutputStream);
                g.a(byteArrayInputStream);
                g.a(gZIPInputStream);
                throw th;
            }
        }
        return bArr;
    }
}
