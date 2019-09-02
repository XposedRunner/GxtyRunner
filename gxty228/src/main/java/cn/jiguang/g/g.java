package cn.jiguang.g;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public final class g {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] a(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            return bArr;
        } finally {
            a((Closeable) inputStream);
        }
    }
}
