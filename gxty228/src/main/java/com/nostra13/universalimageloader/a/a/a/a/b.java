package com.nostra13.universalimageloader.a.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.a.a.a;
import com.nostra13.universalimageloader.b.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LruDiskCache */
public class b implements a {
    public static final CompressFormat a = CompressFormat.PNG;
    protected a b;
    protected final com.nostra13.universalimageloader.a.a.b.a c;
    protected int d = 32768;
    protected CompressFormat e = a;
    protected int f = 100;
    private File g;

    public b(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2;
            int i2;
            if (j == 0) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = j;
            }
            if (i == 0) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = i;
            }
            this.g = file2;
            this.c = aVar;
            a(file, file2, j2, i2);
        }
    }

    private void a(File file, File file2, long j, int i) throws IOException {
        try {
            this.b = a.a(file, 1, 1, j, i);
        } catch (Throwable e) {
            c.a(e);
            if (file2 != null) {
                a(file2, null, j, i);
            }
            if (this.b == null) {
                throw e;
            }
        }
    }

    public File a(String str) {
        a.c a;
        Throwable e;
        Throwable th;
        File file = null;
        try {
            a = this.b.a(b(str));
            if (a != null) {
                try {
                    file = a.a(0);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        c.a(e);
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e3) {
            e = e3;
            a = file;
            c.a(e);
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable e4) {
            a = file;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public boolean a(String str, InputStream inputStream, com.nostra13.universalimageloader.b.b.a aVar) throws IOException {
        boolean z = false;
        a.a b = this.b.b(b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.a(0), this.d);
            try {
                z = com.nostra13.universalimageloader.b.b.a(inputStream, bufferedOutputStream, aVar, this.d);
                com.nostra13.universalimageloader.b.b.a(bufferedOutputStream);
                if (z) {
                    b.a();
                } else {
                    b.b();
                }
            } catch (Throwable th) {
                com.nostra13.universalimageloader.b.b.a(bufferedOutputStream);
                b.b();
            }
        }
        return z;
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        boolean z = false;
        a.a b = this.b.b(b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.a(0), this.d);
            try {
                z = bitmap.compress(this.e, this.f, bufferedOutputStream);
                if (z) {
                    b.a();
                } else {
                    b.b();
                }
            } finally {
                com.nostra13.universalimageloader.b.b.a(bufferedOutputStream);
            }
        }
        return z;
    }

    private String b(String str) {
        return this.c.a(str);
    }
}
