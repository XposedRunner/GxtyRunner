package com.bumptech.glide.load.b;

import android.util.Log;
import com.bumptech.glide.load.a;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: StreamEncoder */
public class o implements a<InputStream> {
    public boolean a(InputStream inputStream, OutputStream outputStream) {
        byte[] b = com.bumptech.glide.g.a.a().b();
        while (true) {
            try {
                int read = inputStream.read(b);
                if (read != -1) {
                    outputStream.write(b, 0, read);
                } else {
                    com.bumptech.glide.g.a.a().a(b);
                    return true;
                }
            } catch (Throwable e) {
                if (Log.isLoggable("StreamEncoder", 3)) {
                    Log.d("StreamEncoder", "Failed to encode data onto the OutputStream", e);
                }
                com.bumptech.glide.g.a.a().a(b);
                return false;
            } catch (Throwable th) {
                com.bumptech.glide.g.a.a().a(b);
            }
        }
    }

    public String a() {
        return "";
    }
}
