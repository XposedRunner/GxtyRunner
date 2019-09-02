package com.loc;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.loc.ai.a;
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
public final class au {
    private static void a(ai aiVar, List<String> list) {
        if (aiVar != null) {
            try {
                for (String c : list) {
                    aiVar.c(c);
                }
                aiVar.close();
            } catch (Throwable th) {
                j.b(th, "ofm", "dlo");
            }
        }
    }

    public static void a(at atVar) {
        ai a;
        Throwable th;
        ai aiVar = null;
        try {
            if (atVar.f.c()) {
                atVar.f.a(true);
                a = ai.a(new File(atVar.a), atVar.b);
                try {
                    List arrayList = new ArrayList();
                    byte[] a2 = a(a, atVar, arrayList);
                    if (a2 != null && a2.length != 0) {
                        ar iVar = new i(a2, atVar.c);
                        an.a();
                        JSONObject jSONObject = new JSONObject(new String(an.b(iVar)));
                        if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
                            if (!(atVar.f == null || a2 == null)) {
                                atVar.f.a(a2.length);
                            }
                            if (atVar.f.b() < Integer.MAX_VALUE) {
                                a(a, arrayList);
                            } else if (a != null) {
                                a.d();
                            }
                        } else {
                            aiVar = a;
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
                        j.b(th, "leg", "uts");
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
            if (aiVar != null) {
                try {
                    aiVar.close();
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

    public static void a(String str, byte[] bArr, at atVar) throws IOException, CertificateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Throwable th;
        ai a;
        OutputStream outputStream = null;
        ai aiVar = null;
        OutputStream outputStream2 = null;
        try {
            if (a(atVar.a, str)) {
                if (null != null) {
                    try {
                        outputStream2.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        aiVar.close();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    return;
                }
            }
            File file = new File(atVar.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            a = ai.a(file, atVar.b);
            try {
                a.a(atVar.d);
                byte[] b = atVar.e.b(bArr);
                a b2 = a.b(str);
                outputStream = b2.a();
                outputStream.write(b);
                b2.b();
                a.c();
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

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            j.b(th, "leg", IXAdRequestInfo.FET);
            return false;
        }
    }

    private static byte[] a(ai aiVar, at atVar, List<String> list) {
        try {
            File b = aiVar.b();
            if (b != null && b.exists()) {
                int i = 0;
                for (String str : b.list()) {
                    String str2;
                    if (str2.contains(".0")) {
                        str2 = str2.split("\\.")[0];
                        byte[] a = ba.a(aiVar, str2);
                        i += a.length;
                        list.add(str2);
                        if (i > atVar.f.b()) {
                            break;
                        }
                        atVar.g.b(a);
                    }
                }
                return atVar.g.a();
            }
        } catch (Throwable th) {
            j.b(th, "leg", "gCo");
        }
        return new byte[0];
    }
}
