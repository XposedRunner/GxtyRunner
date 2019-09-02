package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.io.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONObject;

/* compiled from: LogEngine */
public class jf {
    public static void a(String str, byte[] bArr, je jeVar) throws IOException, CertificateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Throwable th;
        io a;
        OutputStream outputStream = null;
        io ioVar = null;
        OutputStream outputStream2 = null;
        try {
            if (a(jeVar.a, str)) {
                if (null != null) {
                    try {
                        outputStream2.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        ioVar.close();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    return;
                }
            }
            File file = new File(jeVar.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            a = io.a(file, 1, 1, jeVar.b);
            try {
                a.a(jeVar.d);
                byte[] b = jeVar.e.b(bArr);
                a b2 = a.b(str);
                outputStream = b2.a(0);
                outputStream.write(b);
                b2.a();
                a.e();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th5) {
                        th4 = th5;
                        th4.printStackTrace();
                    }
                }
            } catch (Throwable th6) {
                th4 = th6;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th72) {
                        th72.printStackTrace();
                    }
                }
                throw th4;
            }
        } catch (Throwable th8) {
            th4 = th8;
            a = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th4;
        }
    }

    public static void a(je jeVar) {
        Throwable th;
        io ioVar = null;
        io a;
        try {
            if (jeVar.f.c()) {
                jeVar.f.a(true);
                a = io.a(new File(jeVar.a), 1, 1, jeVar.b);
                try {
                    List arrayList = new ArrayList();
                    byte[] a2 = a(a, jeVar, arrayList);
                    if (a2 != null && a2.length != 0) {
                        JSONObject jSONObject = new JSONObject(new String(it.a().b(new gy(a2, jeVar.c))));
                        if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
                            if (!(jeVar.f == null || a2 == null)) {
                                jeVar.f.a(a2.length);
                            }
                            if (jeVar.f.b() < Integer.MAX_VALUE) {
                                a(a, arrayList);
                            } else {
                                a(a);
                            }
                        } else {
                            ioVar = a;
                        }
                    } else if (a != null) {
                        try {
                            a.close();
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        gz.c(th, "leg", "uts");
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable th4) {
                                th = th4;
                                th.printStackTrace();
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable th6) {
                                th6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            if (ioVar != null) {
                try {
                    ioVar.close();
                } catch (Throwable th7) {
                    th = th7;
                    th.printStackTrace();
                }
            }
        } catch (Throwable th8) {
            th = th8;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    private static byte[] a(io ioVar, je jeVar, List<String> list) {
        try {
            File c = ioVar.c();
            if (c != null && c.exists()) {
                int i = 0;
                for (String str : c.list()) {
                    String str2;
                    if (str2.contains(".0")) {
                        str2 = str2.split("\\.")[0];
                        byte[] a = jl.a(ioVar, str2, false);
                        i += a.length;
                        list.add(str2);
                        if (i > jeVar.f.b()) {
                            break;
                        }
                        jeVar.g.b(a);
                    }
                }
                return jeVar.g.a();
            }
        } catch (Throwable th) {
            gz.c(th, "leg", "gCo");
        }
        return new byte[0];
    }

    private static void a(io ioVar) {
        if (ioVar != null) {
            try {
                ioVar.f();
            } catch (Throwable th) {
                gz.c(th, "ofm", "dlo");
            }
        }
    }

    private static void a(io ioVar, List<String> list) {
        if (ioVar != null) {
            try {
                for (String c : list) {
                    ioVar.c(c);
                }
                ioVar.close();
            } catch (Throwable th) {
                gz.c(th, "ofm", "dlo");
            }
        }
    }

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            gz.c(th, "leg", IXAdRequestInfo.FET);
            return false;
        }
    }
}
