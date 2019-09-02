package cn.jiguang.analytics.android.c.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.jiguang.analytics.android.c.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public final class a {
    public static volatile a a;
    public static final Object b = new Object();
    private static final String[] z;
    private SharedPreferences c = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "'\u001c}%\u0016\u001b\u001fa%U \u000e+\u0006_8\u0018E/B\u0012\u0012~.R\u0011\u0005h%F \u0014d.\u00161\u000fy/Dn";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0089;
            case 1: goto L_0x008c;
            case 2: goto L_0x008f;
            case 3: goto L_0x0092;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 54;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "'\u001c}%\u0016\u001b\u001fa%U \u000e+\ty\u0011\u0005h%F \u0014d.\u00161\u000fy/Dn";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "!\u0013n8U1\r%RtQ+#Y:\tn8Bt\u0014x`X!\u0011g";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "92i*S7\tG)E ]b3\u0016:\bg,";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0015\u001e)Y:]&`E5\u000bn\u000fT>\u0018h4E";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0016\u001cx%e<\u001cy%f&\u0018m%D1\u0013h%";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "><e!Z-\u000eb3i3\u0018e%D5\"h/X2\u0014l";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "8\u0012j$\u0016;\u001fa%U \u000e+%D&\u0012yz";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "8\u0012j$\u0016;\u001fa%U ]n2D;\u000f1";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        z = r4;
        r0 = new java.lang.Object;
        r0.<init>();
        b = r0;
        return;
    L_0x0089:
        r9 = 84;
        goto L_0x0020;
    L_0x008c:
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0020;
    L_0x008f:
        r9 = 11;
        goto L_0x0020;
    L_0x0092:
        r9 = 64;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.b.a.<clinit>():void");
    }

    private a(Context context) {
        if (this.c == null) {
            this.c = context.getSharedPreferences(z[6], 0);
        }
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public static synchronized Object a(Context context, String str) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Exception e;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        synchronized (a.class) {
            if (context == null) {
                b.f(z[5], z[2]);
            } else {
                try {
                    objectInputStream = new ObjectInputStream(context.openFileInput(str));
                    try {
                        readObject = objectInputStream.readObject();
                        try {
                            objectInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            b.f(z[5], new StringBuilder(z[8]).append(e.getMessage()).toString());
                            b(context, str);
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            return readObject;
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    objectInputStream = objectInputStream2;
                    b.f(z[5], new StringBuilder(z[8]).append(e.getMessage()).toString());
                    b(context, str);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return readObject;
                } catch (Throwable th3) {
                    objectInputStream = objectInputStream2;
                    th = th3;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return readObject;
    }

    public static synchronized void a(Context context, String str, Object obj) {
        synchronized (a.class) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
                objectOutputStream.writeObject(obj);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized <T> void a(Context context, String str, ArrayList<T> arrayList) {
        synchronized (a.class) {
            b.a(z[5], z[4]);
            if (context == null) {
                b.f(z[5], z[2]);
            } else if (arrayList == null) {
                b.f(z[5], z[3]);
            } else {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
                    objectOutputStream.writeObject(arrayList);
                    objectOutputStream.close();
                } catch (FileNotFoundException e) {
                    b.h(z[5], new StringBuilder(z[0]).append(e.getMessage()).toString());
                } catch (IOException e2) {
                    b.h(z[5], new StringBuilder(z[1]).append(e2.getMessage()).toString());
                }
            }
        }
    }

    public static synchronized void b(Context context, String str) {
        synchronized (a.class) {
            if (context == null) {
                b.f(z[5], z[2]);
            } else {
                File file = new File(context.getFilesDir(), str);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public static synchronized <T> ArrayList<T> c(Context context, String str) {
        Exception e;
        Throwable th;
        ArrayList<T> arrayList = null;
        synchronized (a.class) {
            if (context == null) {
                b.f(z[5], z[2]);
            } else {
                ArrayList<T> arrayList2 = new ArrayList();
                ObjectInputStream objectInputStream;
                try {
                    objectInputStream = new ObjectInputStream(context.openFileInput(str));
                    try {
                        arrayList = (ArrayList) objectInputStream.readObject();
                        try {
                            objectInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            b.f(z[5], new StringBuilder(z[7]).append(e.getMessage()).toString());
                            b(context, str);
                            if (objectInputStream == null) {
                                try {
                                    objectInputStream.close();
                                    arrayList = arrayList2;
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    arrayList = arrayList2;
                                }
                            } else {
                                arrayList = arrayList2;
                            }
                            return arrayList;
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e5) {
                    Exception exception = e5;
                    objectInputStream = null;
                    e = exception;
                    b.f(z[5], new StringBuilder(z[7]).append(e.getMessage()).toString());
                    b(context, str);
                    if (objectInputStream == null) {
                        arrayList = arrayList2;
                    } else {
                        objectInputStream.close();
                        arrayList = arrayList2;
                    }
                    return arrayList;
                } catch (Throwable th3) {
                    objectInputStream = null;
                    th = th3;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return arrayList;
    }

    public final void a(String str, long j) {
        if (this.c != null) {
            synchronized (b) {
                Editor edit = this.c.edit();
                edit.putLong(str, j);
                edit.apply();
            }
        }
    }

    public final long b(String str, long j) {
        return this.c != null ? this.c.getLong(str, j) : j;
    }
}
