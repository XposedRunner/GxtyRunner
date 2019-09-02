package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.io.b;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* compiled from: Utils */
public class jl {
    public static void a(Context context, je jeVar, String str, int i, int i2, String str2) {
        jeVar.a = gx.c(context, str);
        jeVar.d = i;
        jeVar.b = (long) i2;
        jeVar.c = str2;
    }

    public static je a(WeakReference<je> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference(new je());
        }
        return (je) weakReference.get();
    }

    static byte[] a(io ioVar, String str, boolean z) {
        b a;
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        byte[] bArr = new byte[0];
        try {
            a = ioVar.a(str);
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
                inputStream = a.a(0);
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
                if (z) {
                    ioVar.c(str);
                }
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
                    gz.c(th322, "sui", "rdS");
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

    public static String a() {
        return gl.a(System.currentTimeMillis());
    }

    public static String a(Context context, gk gkVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(gd.g(context)).append("\",\"sdkversion\":\"").append(gkVar.c()).append("\",\"product\":\"").append(gkVar.a()).append("\",\"ed\":\"").append(gkVar.e()).append("\",\"nt\":\"").append(gd.e(context)).append("\",\"np\":\"").append(gd.c(context)).append("\",\"mnc\":\"").append(gd.d(context)).append("\",\"ant\":\"").append(gd.f(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
