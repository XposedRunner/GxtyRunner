package cn.jiguang.a.a.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.e.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public final class a {
    private static a b = null;
    private static final Object c = new Object();
    private HashMap<String, Long> a = null;
    private File d = null;

    private a() {
    }

    public static long a(Context context) {
        return cn.jiguang.a.b.a.c(context);
    }

    public static a a() {
        if (b == null) {
            synchronized (c) {
                b = new a();
            }
        }
        return b;
    }

    private void b(Context context) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        if (this.d == null && context != null) {
            this.d = new File(context.getCacheDir(), "ArpCache");
            try {
                if (this.d != null && this.a != null && this.d.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.d));
                    this.a = (HashMap) objectInputStream.readObject();
                    objectInputStream.close();
                }
            } catch (Exception e) {
                d.i("ArpCacheUtil", "readFromFile error:" + e.toString());
            }
        }
    }

    public final void a(Context context, String str) {
        b(context);
        if (!TextUtils.isEmpty(str)) {
            this.a.put(cn.jiguang.g.a.a(str), Long.valueOf(System.currentTimeMillis()));
            try {
                if (this.d != null && this.a != null) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.d));
                    objectOutputStream.writeObject(this.a);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                d.i("ArpCacheUtil", "saveInFile error:" + e.toString());
            }
        }
    }

    public final boolean b(Context context, String str) {
        b(context);
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String a = cn.jiguang.g.a.a(str);
        Long l = (Long) this.a.get(a);
        if (l == null) {
            d.c("ArpCacheUtil", "key:" + a + " cache not exist.");
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long longValue = l.longValue();
        long c = cn.jiguang.a.b.a.c(context);
        d.c("ArpCacheUtil", "key:" + a + " cache :currentTime" + currentTimeMillis + " cacheTime:" + longValue + " frequency:" + c);
        return c != 0 && Math.abs(currentTimeMillis - longValue) > c;
    }
}
