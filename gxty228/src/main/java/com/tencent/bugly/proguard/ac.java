package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public class ac {
    public static final long a = System.currentTimeMillis();
    private static ac b = null;
    private Context c;
    private String d = a.b().e;
    private Map<Integer, Map<String, ab>> e = new HashMap();
    private SharedPreferences f;

    public ac(Context context) {
        this.c = context;
        this.f = context.getSharedPreferences("crashrecord", 0);
    }

    public static synchronized ac a(Context context) {
        ac acVar;
        synchronized (ac.class) {
            if (b == null) {
                b = new ac(context);
            }
            acVar = b;
        }
        return acVar;
    }

    public static synchronized ac a() {
        ac acVar;
        synchronized (ac.class) {
            acVar = b;
        }
        return acVar;
    }

    private synchronized boolean b(int i) {
        boolean z;
        try {
            List<ab> c = c(i);
            if (c == null) {
                z = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                List arrayList = new ArrayList();
                Collection arrayList2 = new ArrayList();
                for (ab abVar : c) {
                    if (abVar.b != null && abVar.b.equalsIgnoreCase(this.d) && abVar.d > 0) {
                        arrayList.add(abVar);
                    }
                    if (abVar.c + LogBuilder.MAX_INTERVAL < currentTimeMillis) {
                        arrayList2.add(abVar);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c.removeAll(arrayList2);
                    a(i, (List) c);
                    z = false;
                } else if (arrayList.size() <= 0 || ((ab) arrayList.get(arrayList.size() - 1)).c + LogBuilder.MAX_INTERVAL >= currentTimeMillis) {
                    z = true;
                } else {
                    c.clear();
                    a(i, (List) c);
                    z = false;
                }
            }
        } catch (Exception e) {
            an.e("isFrequentCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    public void a(final int i, final int i2) {
        am.a().a(new Runnable(this) {
            final /* synthetic */ ac c;

            public void run() {
                try {
                    if (!TextUtils.isEmpty(this.c.d)) {
                        ab abVar;
                        ab abVar2;
                        List a = this.c.c(i);
                        List arrayList;
                        if (a == null) {
                            arrayList = new ArrayList();
                        } else {
                            arrayList = a;
                        }
                        if (this.c.e.get(Integer.valueOf(i)) == null) {
                            this.c.e.put(Integer.valueOf(i), new HashMap());
                        }
                        if (((Map) this.c.e.get(Integer.valueOf(i))).get(this.c.d) == null) {
                            ab abVar3 = new ab();
                            abVar3.a = (long) i;
                            abVar3.g = ac.a;
                            abVar3.b = this.c.d;
                            abVar3.f = a.b().o;
                            a.b().getClass();
                            abVar3.e = "2.6.5";
                            abVar3.c = System.currentTimeMillis();
                            abVar3.d = i2;
                            ((Map) this.c.e.get(Integer.valueOf(i))).put(this.c.d, abVar3);
                            abVar = abVar3;
                        } else {
                            abVar2 = (ab) ((Map) this.c.e.get(Integer.valueOf(i))).get(this.c.d);
                            abVar2.d = i2;
                            abVar = abVar2;
                        }
                        Collection arrayList2 = new ArrayList();
                        int i = 0;
                        for (ab abVar22 : r4) {
                            if (abVar22.g == abVar.g && abVar22.b != null && abVar22.b.equalsIgnoreCase(abVar.b)) {
                                i = 1;
                                abVar22.d = abVar.d;
                            }
                            if ((abVar22.e != null && !abVar22.e.equalsIgnoreCase(abVar.e)) || ((abVar22.f != null && !abVar22.f.equalsIgnoreCase(abVar.f)) || abVar22.d <= 0)) {
                                arrayList2.add(abVar22);
                            }
                        }
                        r4.removeAll(arrayList2);
                        if (i == 0) {
                            r4.add(abVar);
                        }
                        this.c.a(i, (List) r4);
                    }
                } catch (Exception e) {
                    an.e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    private synchronized <T extends List<?>> T c(int i) {
        T t;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        Throwable th;
        try {
            File file = new File(this.c.getDir("crashrecord", 0), i + "");
            if (file == null || !file.exists()) {
                t = null;
            } else {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    objectInputStream2 = null;
                    try {
                        an.a("open record file error", new Object[0]);
                        if (objectInputStream2 != null) {
                            objectInputStream2.close();
                        }
                        t = null;
                        return t;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        objectInputStream = objectInputStream2;
                        th = th3;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                } catch (ClassNotFoundException e2) {
                    objectInputStream = null;
                    try {
                        an.a("get object error", new Object[0]);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        t = null;
                        return t;
                    } catch (Throwable th4) {
                        th = th4;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                try {
                    List list = (List) objectInputStream.readObject();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (IOException e3) {
                    objectInputStream2 = objectInputStream;
                    an.a("open record file error", new Object[0]);
                    if (objectInputStream2 != null) {
                        objectInputStream2.close();
                    }
                    t = null;
                    return t;
                } catch (ClassNotFoundException e4) {
                    an.a("get object error", new Object[0]);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    t = null;
                    return t;
                }
            }
        } catch (Exception e5) {
            an.e("readCrashRecord error", new Object[0]);
        }
        return t;
    }

    private synchronized <T extends List<?>> void a(int i, T t) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        Throwable th;
        if (t != null) {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(this.c.getDir("crashrecord", 0), i + "")));
                    try {
                        objectOutputStream.writeObject(t);
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            an.a("open record file error", new Object[0]);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    objectOutputStream = null;
                    e.printStackTrace();
                    an.a("open record file error", new Object[0]);
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = null;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                an.e("writeCrashRecord error", new Object[0]);
            }
        }
    }

    public synchronized boolean a(final int i) {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.f.getBoolean(i + "_" + this.d, true);
                am.a().a(new Runnable(this) {
                    final /* synthetic */ ac b;

                    public void run() {
                        this.b.f.edit().putBoolean(i + "_" + this.b.d, !this.b.b(i)).commit();
                    }
                });
            } catch (Exception e) {
                an.e("canInit error", new Object[0]);
            }
        }
        return z;
    }
}
