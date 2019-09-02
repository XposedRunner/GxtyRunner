package com.amap.api.mapcore.util;

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
import java.io.InputStream;
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
public final class io implements Closeable {
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
    private final LinkedHashMap<String, c> m = new LinkedHashMap(0, 0.75f, true);
    private int n;
    private ip o;
    private long p = 0;
    private final Callable<Void> r = new Callable<Void>(this) {
        final /* synthetic */ io a;

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
                    this.a.m();
                    if (this.a.k()) {
                        this.a.j();
                        this.a.n = 0;
                    }
                }
            }
            return null;
        }
    };

    /* compiled from: DiskLruCache */
    public final class a {
        final /* synthetic */ io a;
        private final c b;
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

        private a(io ioVar, c cVar) {
            this.a = ioVar;
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[ioVar.i];
        }

        public OutputStream a(int i) throws IOException {
            if (i < 0 || i >= this.a.i) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + this.a.i);
            }
            OutputStream g;
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
                        g = io.s;
                    }
                }
                g = new a(fileOutputStream);
            }
            return g;
        }

        public void a() throws IOException {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.b);
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
    public final class b implements Closeable {
        final /* synthetic */ io a;
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(io ioVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = ioVar;
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.d) {
                ir.a(a);
            }
        }
    }

    /* compiled from: DiskLruCache */
    private final class c {
        final /* synthetic */ io a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(io ioVar, String str) {
            this.a = ioVar;
            this.b = str;
            this.c = new long[ioVar.i];
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

    public static void a() {
        if (b != null && !b.isShutdown()) {
            b.shutdown();
        }
    }

    public static ThreadPoolExecutor b() {
        try {
            if (b == null || b.isShutdown()) {
                b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(256), q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b;
    }

    private io(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static io a(File file, int i, int i2, long j) throws IOException {
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
            io ioVar = new io(file, i, i2, j);
            if (ioVar.d.exists()) {
                try {
                    ioVar.h();
                    ioVar.i();
                    ioVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ioVar.d, true), ir.a));
                    return ioVar;
                } catch (Throwable th) {
                    ioVar.f();
                }
            }
            file.mkdirs();
            ioVar = new io(file, i, i2, j);
            ioVar.j();
            return ioVar;
        }
    }

    private void h() throws IOException {
        Closeable iqVar = new iq(new FileInputStream(this.d), ir.a);
        int i;
        try {
            String a = iqVar.a();
            String a2 = iqVar.a();
            String a3 = iqVar.a();
            String a4 = iqVar.a();
            String a5 = iqVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.g).equals(a3) && Integer.toString(this.i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    d(iqVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.n = i - this.m.size();
            ir.a(iqVar);
        } catch (Throwable th) {
            ir.a(iqVar);
        }
    }

    private void d(String str) throws IOException {
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
        c cVar = (c) this.m.get(str2);
        if (cVar == null) {
            cVar = new c(str2);
            this.m.put(str2, cVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            cVar.d = true;
            cVar.e = null;
            cVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            cVar.e = new a(cVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void i() throws IOException {
        a(this.e);
        Iterator it = this.m.values().iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            int i;
            if (cVar.e == null) {
                for (i = 0; i < this.i; i++) {
                    this.j += cVar.c[i];
                }
            } else {
                cVar.e = null;
                for (i = 0; i < this.i; i++) {
                    a(cVar.a(i));
                    a(cVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void j() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), ir.a));
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
            for (c cVar : this.m.values()) {
                if (cVar.e != null) {
                    bufferedWriter.write("DIRTY " + cVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + cVar.b + cVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), ir.a));
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

    public synchronized b a(String str) throws IOException {
        int i;
        b bVar = null;
        synchronized (this) {
            l();
            e(str);
            c cVar = (c) this.m.get(str);
            if (cVar != null) {
                if (cVar.d) {
                    InputStream[] inputStreamArr = new InputStream[this.i];
                    int i2 = 0;
                    while (i2 < this.i) {
                        try {
                            inputStreamArr[i2] = new FileInputStream(cVar.a(i2));
                            i2++;
                        } catch (FileNotFoundException e) {
                            i = 0;
                            while (i < this.i && inputStreamArr[i] != null) {
                                ir.a(inputStreamArr[i]);
                                i++;
                            }
                        }
                    }
                    this.n++;
                    this.k.append("READ " + str + '\n');
                    if (k()) {
                        b().submit(this.r);
                    }
                    bVar = new b(str, cVar.f, inputStreamArr, cVar.c);
                }
            }
        }
        return bVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        l();
        e(str);
        c cVar = (c) this.m.get(str);
        if (j == -1 || (cVar != null && cVar.f == j)) {
            c cVar2;
            if (cVar == null) {
                cVar = new c(str);
                this.m.put(str, cVar);
                cVar2 = cVar;
            } else if (cVar.e != null) {
                aVar = null;
            } else {
                cVar2 = cVar;
            }
            aVar = new a(cVar2);
            cVar2.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public File c() {
        return this.c;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            c a = aVar.b;
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
            if (this.j > this.h || k()) {
                b().submit(this.r);
            }
        }
    }

    private boolean k() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            l();
            e(str);
            c cVar = (c) this.m.get(str);
            if (cVar == null || cVar.e != null) {
                z = false;
            } else {
                while (i < this.i) {
                    File a = cVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.j -= cVar.c[i];
                        cVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.n++;
                this.k.append("REMOVE " + str + '\n');
                this.m.remove(str);
                if (k()) {
                    b().submit(this.r);
                }
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean d() {
        return this.k == null;
    }

    private void l() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void e() throws IOException {
        l();
        m();
        this.k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.b();
                }
            }
            m();
            this.k.close();
            this.k = null;
        }
    }

    private void m() throws IOException {
        while (true) {
            if (this.j > this.h || this.m.size() > this.l) {
                String str = (String) ((Entry) this.m.entrySet().iterator().next()).getKey();
                c(str);
                if (this.o != null) {
                    this.o.a(str);
                }
            } else {
                return;
            }
        }
    }

    public void f() throws IOException {
        close();
        ir.a(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
