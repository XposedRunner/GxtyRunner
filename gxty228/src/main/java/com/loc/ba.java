package com.loc;

import android.content.Context;
import com.loc.ai.b;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* compiled from: Utils */
public final class ba {
    public static at a(WeakReference<at> weakReference) {
        WeakReference weakReference2;
        if (weakReference == null || weakReference.get() == null) {
            weakReference2 = new WeakReference(new at());
        }
        return (at) weakReference2.get();
    }

    public static String a(Context context, dk dkVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(df.g(context)).append("\",\"sdkversion\":\"").append(dkVar.c()).append("\",\"product\":\"").append(dkVar.a()).append("\",\"ed\":\"").append(dkVar.d()).append("\",\"nt\":\"").append(df.e(context)).append("\",\"np\":\"").append(df.c(context)).append("\",\"mnc\":\"").append(df.d(context)).append("\",\"ant\":\"").append(df.f(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void a(Context context, at atVar, String str, int i, int i2, String str2) {
        atVar.a = h.c(context, str);
        atVar.d = i;
        atVar.b = (long) i2;
        atVar.c = str2;
    }

    static byte[] a(ai aiVar, String str) {
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        byte[] bArr = new byte[0];
        b a;
        try {
            a = aiVar.a(str);
            if (a == null) {
                if (inputStream != null) {
                    try {
                        inputStream2.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th4) {
                        th3 = th4;
                        th3.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            }
            try {
                inputStream = a.a();
                if (inputStream == null) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th5) {
                            th32 = th5;
                            th32.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                }
                bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th322) {
                        th322.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th6) {
                        th322 = th6;
                        th322.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            } catch (Throwable th7) {
                th322 = th7;
                try {
                    j.b(th322, "sui", "rdS");
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3222) {
                            th3222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th8) {
                            th3222 = th8;
                            th3222.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th9) {
                    th2 = th9;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th32222) {
                            th32222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th322222) {
                            th322222.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th10) {
            th2 = th10;
            a = inputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th2;
        }
    }
}
