package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

/* compiled from: CoordinatorSoDownloader */
public final class de extends dj {
    private boolean g = false;

    public de(Context context, String str, String str2, String str3) {
        super(context, str, str2, str3);
    }

    private static void a(File file, File file2) {
        int i = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(new byte[32]);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
            Object obj = new byte[1024];
            while (true) {
                int read = fileInputStream.read(obj);
                if (read > 0) {
                    if (read == 1024) {
                        randomAccessFile.seek((long) i);
                        randomAccessFile.write(obj);
                    } else {
                        Object obj2 = new byte[read];
                        System.arraycopy(obj, 0, obj2, 0, read);
                        randomAccessFile.seek((long) i);
                        randomAccessFile.write(obj2);
                    }
                    i += read;
                } else {
                    fileInputStream.close();
                    randomAccessFile.close();
                    file.delete();
                    return;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a() {
        if (this.a != null && !TextUtils.isEmpty(this.a.c()) && this.a.c().endsWith("png") && this.a.c().contains(dl.a(this.f))) {
            if ((this.g || !dl.b(this.f)) && !new File(this.d).exists()) {
                start();
            }
        }
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void d() {
        File file;
        try {
            if (this.b != null) {
                this.b.close();
            }
            String a = dh.a(this.c);
            if (a == null || !a.equalsIgnoreCase(this.e)) {
                b();
                return;
            }
            File file2 = new File(this.d);
            if (file2.exists()) {
                b();
                return;
            }
            file = new File(this.c);
            if (file.exists()) {
                a(file, file2);
                b();
            }
        } catch (Throwable th) {
            b();
            file = new File(this.d);
            if (file.exists()) {
                file.delete();
            }
            g.a(th, "sdl", "ofs");
        }
    }
}
