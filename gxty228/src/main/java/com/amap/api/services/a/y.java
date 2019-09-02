package com.amap.api.services.a;

import cn.jiguang.api.utils.ByteBufferUtils;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache */
public final class y implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), q);
    private static final ThreadFactory q = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.a.getAndIncrement());
        }
    };
    private static final OutputStream s = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private long j = 0;
    private Writer k;
    private int l = 1000;
    private final LinkedHashMap<String, b> m = new LinkedHashMap(0, 0.75f, true);
    private int n;
    private z o;
    private long p = 0;
    private final Callable<Void> r = new Callable<Void>(this) {
        final /* synthetic */ y a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }

        public Void a() throws Exception {
            synchronized (this.a) {
                if (this.a.k == null) {
                } else {
                    this.a.j();
                    if (this.a.h()) {
                        this.a.g();
                        this.a.n = 0;
                    }
                }
            }
            return null;
        }
    };

    /* compiled from: DiskLruCache */
    public final class a {
        final /* synthetic */ y a;
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* compiled from: DiskLruCache */
        private class a extends FilterOutputStream {
            final /* synthetic */ a a;

            private a(a aVar, OutputStream outputStream) {
                this.a = aVar;
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        private a(y yVar, b bVar) {
            this.a = yVar;
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[yVar.i];
        }

        public OutputStream a(int i) throws IOException {
            if (i < 0 || i >= this.a.i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + this.a.i);
            }
            OutputStream d;
            synchronized (this.a) {
                File b;
                OutputStream fileOutputStream;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                b = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    this.a.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = y.s;
                    }
                }
                d = new a(fileOutputStream);
            }
            return d;
        }

        public void a() throws IOException {
            if (this.d) {
                this.a.a(this, false);
                this.a.b(this.b.b);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            this.a.a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    private final class b {
        final /* synthetic */ y a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private b(y yVar, String str) {
            this.a = yVar;
            this.b = str;
            this.c = new long[yVar.i];
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != this.a.i) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(this.a.c, this.b + "." + i);
        }

        public File b(int i) {
            return new File(this.a.c, this.b + "." + i + ".tmp");
        }
    }

    public void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > ByteBufferUtils.ERROR_CODE) {
            i = ByteBufferUtils.ERROR_CODE;
        }
        this.l = i;
    }

    public static ThreadPoolExecutor a() {
        try {
            if (b == null || b.isShutdown()) {
                b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(256), q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b;
    }

    private y(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static y a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            y yVar = new y(file, i, i2, j);
            if (yVar.d.exists()) {
                try {
                    yVar.e();
                    yVar.f();
                    yVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(yVar.d, true), ab.a));
                    return yVar;
                } catch (Throwable th) {
                    yVar.c();
                }
            }
            file.mkdirs();
            yVar = new y(file, i, i2, j);
            yVar.g();
            return yVar;
        }
    }

    private void e() throws IOException {
        int i;
        Closeable aaVar = new aa(new FileInputStream(this.d), ab.a);
        try {
            String a = aaVar.a();
            String a2 = aaVar.a();
            String a3 = aaVar.a();
            String a4 = aaVar.a();
            String a5 = aaVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.g).equals(a3) && Integer.toString(this.i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    c(aaVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.n = i - this.m.size();
            ab.a(aaVar);
        } catch (Throwable th) {
            ab.a(aaVar);
        }
    }

    private void c(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.m.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        b bVar = (b) this.m.get(str2);
        if (bVar == null) {
            bVar = new b(str2);
            this.m.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.d = true;
            bVar.e = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.e = new a(bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void f() throws IOException {
        a(this.e);
        Iterator it = this.m.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.e == null) {
                for (i = 0; i < this.i; i++) {
                    this.j += bVar.c[i];
                }
            } else {
                bVar.e = null;
                for (i = 0; i < this.i; i++) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void g() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), ab.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.m.values()) {
                if (bVar.e != null) {
                    bufferedWriter.write("DIRTY " + bVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bVar.b + bVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), ab.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public a a(String str) throws IOException {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        i();
        d(str);
        b bVar = (b) this.m.get(str);
        if (j == -1 || (bVar != null && bVar.f == j)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(str);
                this.m.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.e != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(bVar2);
            bVar2.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b a = aVar.b;
            if (a.e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.d) {
                    int i2 = 0;
                    while (i2 < this.i) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.i) {
                File b = a.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.c[i];
                    long length = a2.length();
                    a.c[i] = length;
                    this.j = (this.j - j) + length;
                }
                i++;
            }
            this.n++;
            a.e = null;
            if ((a.d | z) != 0) {
                a.d = true;
                this.k.write("CLEAN " + a.b + a.a() + '\n');
                if (z) {
                    long j2 = this.p;
                    this.p = 1 + j2;
                    a.f = j2;
                }
            } else {
                this.m.remove(a.b);
                this.k.write("REMOVE " + a.b + '\n');
            }
            this.k.flush();
            if (this.j > this.h || h()) {
                a().submit(this.r);
            }
        }
    }

    private boolean h() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    public synchronized boolean b(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            i();
            d(str);
            b bVar = (b) this.m.get(str);
            if (bVar == null || bVar.e != null) {
                z = false;
            } else {
                while (i < this.i) {
                    File a = bVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.j -= bVar.c[i];
                        bVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.n++;
                this.k.append("REMOVE " + str + '\n');
                this.m.remove(str);
                if (h()) {
                    a().submit(this.r);
                }
                z = true;
            }
        }
        return z;
    }

    private void i() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void b() throws IOException {
        i();
        j();
        this.k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            j();
            this.k.close();
            this.k = null;
        }
    }

    private void j() throws IOException {
        while (true) {
            if (this.j > this.h || this.m.size() > this.l) {
                String str = (String) ((Entry) this.m.entrySet().iterator().next()).getKey();
                b(str);
                if (this.o != null) {
                    this.o.a(str);
                }
            } else {
                return;
            }
        }
    }

    public void c() throws IOException {
        close();
        ab.a(this.c);
    }

    private void d(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
