package com.loc;

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
public final class ai implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), q);
    private static final ThreadFactory q = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.a.getAndIncrement());
        }
    };
    private static final OutputStream s = new OutputStream() {
        public final void write(int i) throws IOException {
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
    private aj o;
    private long p = 0;
    private final Callable<Void> r = new Callable<Void>(this) {
        final /* synthetic */ ai a;

        {
            this.a = r1;
        }

        private Void a() throws Exception {
            synchronized (this.a) {
                if (this.a.k == null) {
                } else {
                    this.a.l();
                    if (this.a.j()) {
                        this.a.i();
                        this.a.n = 0;
                    }
                }
            }
            return null;
        }

        public final /* synthetic */ Object call() throws Exception {
            return a();
        }
    };

    /* compiled from: DiskLruCache */
    public final class a {
        final /* synthetic */ ai a;
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

            public final void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        private a(ai aiVar, c cVar) {
            this.a = aiVar;
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[aiVar.i];
        }

        public final OutputStream a() throws IOException {
            if (this.a.i <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + this.a.i);
            }
            OutputStream e;
            synchronized (this.a) {
                File b;
                OutputStream fileOutputStream;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[0] = true;
                }
                b = this.b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e2) {
                    this.a.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e3) {
                        e = ai.s;
                    }
                }
                e = new a(fileOutputStream);
            }
            return e;
        }

        public final void b() throws IOException {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.b);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public final void c() throws IOException {
            this.a.a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    public final class b implements Closeable {
        final /* synthetic */ ai a;
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(ai aiVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = aiVar;
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public final InputStream a() {
            return this.d[0];
        }

        public final void close() {
            for (Closeable a : this.d) {
                al.a(a);
            }
        }
    }

    /* compiled from: DiskLruCache */
    private final class c {
        final /* synthetic */ ai a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(ai aiVar, String str) {
            this.a = aiVar;
            this.b = str;
            this.c = new long[aiVar.i];
        }

        private static IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        static /* synthetic */ void a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != cVar.a.i) {
                throw a(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    cVar.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw a(strArr);
                }
            }
        }

        public final File a(int i) {
            return new File(this.a.c, this.b + "." + i);
        }

        public final String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        public final File b(int i) {
            return new File(this.a.c, this.b + "." + i + ".tmp");
        }
    }

    private ai(File file, long j) {
        this.c = file;
        this.g = 1;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = 1;
        this.h = j;
    }

    public static ai a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        ai aiVar = new ai(file, j);
        if (aiVar.d.exists()) {
            try {
                aiVar.g();
                aiVar.h();
                aiVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aiVar.d, true), al.a));
                return aiVar;
            } catch (Throwable th) {
                aiVar.d();
            }
        }
        file.mkdirs();
        aiVar = new ai(file, j);
        aiVar.i();
        return aiVar;
    }

    public static void a() {
        if (b != null && !b.isShutdown()) {
            b.shutdown();
        }
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
                            aVar.c();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.c();
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
            if (this.j > this.h || j()) {
                f().submit(this.r);
            }
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

    private synchronized a d(String str) throws IOException {
        a aVar;
        k();
        e(str);
        c cVar = (c) this.m.get(str);
        if (-1 == -1 || (cVar != null && cVar.f == -1)) {
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

    private static void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private static ThreadPoolExecutor f() {
        try {
            if (b == null || b.isShutdown()) {
                b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(256), q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b;
    }

    private void g() throws IOException {
        Closeable akVar = new ak(new FileInputStream(this.d), al.a);
        int i;
        try {
            String a = akVar.a();
            String a2 = akVar.a();
            String a3 = akVar.a();
            String a4 = akVar.a();
            String a5 = akVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.g).equals(a3) && Integer.toString(this.i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    a3 = akVar.a();
                    int indexOf = a3.indexOf(32);
                    if (indexOf == -1) {
                        throw new IOException("unexpected journal line: " + a3);
                    }
                    int i2 = indexOf + 1;
                    int indexOf2 = a3.indexOf(32, i2);
                    c cVar;
                    if (indexOf2 != -1) {
                        a2 = a3.substring(i2, indexOf2);
                        cVar = (c) this.m.get(a2);
                        if (cVar == null) {
                            cVar = new c(a2);
                            this.m.put(a2, cVar);
                        }
                        if (indexOf2 == -1) {
                        }
                        if (indexOf2 != -1) {
                        }
                        if (indexOf2 == -1) {
                            break;
                        }
                        break;
                    }
                    String substring = a3.substring(i2);
                    if (indexOf == 6 && a3.startsWith("REMOVE")) {
                        this.m.remove(substring);
                    } else {
                        a2 = substring;
                        cVar = (c) this.m.get(a2);
                        if (cVar == null) {
                            cVar = new c(a2);
                            this.m.put(a2, cVar);
                        }
                        if (indexOf2 == -1 && indexOf == 5 && a3.startsWith("CLEAN")) {
                            String[] split = a3.substring(indexOf2 + 1).split(" ");
                            cVar.d = true;
                            cVar.e = null;
                            c.a(cVar, split);
                        } else if (indexOf2 != -1 && indexOf == 5 && a3.startsWith("DIRTY")) {
                            cVar.e = new a(cVar);
                        } else if (indexOf2 == -1 && indexOf == 4 && a3.startsWith("READ")) {
                        }
                    }
                    i++;
                }
                throw new IOException("unexpected journal line: " + a3);
            }
            throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
        } catch (EOFException e) {
            this.n = i - this.m.size();
            al.a(akVar);
        } catch (Throwable th) {
            al.a(akVar);
        }
    }

    private void h() throws IOException {
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

    private synchronized void i() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), al.a));
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
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), al.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean j() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    private void k() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void l() throws IOException {
        while (true) {
            if (this.j > this.h || this.m.size() > this.l) {
                c((String) ((Entry) this.m.entrySet().iterator().next()).getKey());
                if (this.o != null) {
                    aj ajVar = this.o;
                }
            } else {
                return;
            }
        }
    }

    public final synchronized b a(String str) throws IOException {
        int i;
        b bVar = null;
        synchronized (this) {
            k();
            e(str);
            c cVar = (c) this.m.get(str);
            if (cVar != null) {
                if (cVar.d) {
                    r6 = new InputStream[this.i];
                    int i2 = 0;
                    while (i2 < this.i) {
                        try {
                            r6[i2] = new FileInputStream(cVar.a(i2));
                            i2++;
                        } catch (FileNotFoundException e) {
                            i = 0;
                            while (i < this.i && r6[i] != null) {
                                InputStream[] inputStreamArr;
                                al.a(inputStreamArr[i]);
                                i++;
                            }
                        }
                    }
                    this.n++;
                    this.k.append("READ " + str + '\n');
                    if (j()) {
                        f().submit(this.r);
                    }
                    bVar = new b(str, cVar.f, inputStreamArr, cVar.c);
                }
            }
        }
        return bVar;
    }

    public final void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > ByteBufferUtils.ERROR_CODE) {
            i = ByteBufferUtils.ERROR_CODE;
        }
        this.l = i;
    }

    public final a b(String str) throws IOException {
        return d(str);
    }

    public final File b() {
        return this.c;
    }

    public final synchronized void c() throws IOException {
        k();
        l();
        this.k.flush();
    }

    public final synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            k();
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
                if (j()) {
                    f().submit(this.r);
                }
                z = true;
            }
        }
        return z;
    }

    public final synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.c();
                }
            }
            l();
            this.k.close();
            this.k = null;
        }
    }

    public final void d() throws IOException {
        close();
        al.a(this.c);
    }
}
