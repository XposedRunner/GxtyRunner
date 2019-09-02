package com.amap.api.services.a;

import com.amap.api.services.a.y.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: LogEngine */
public class ad {
    public static void a(String str, byte[] bArr, ac acVar) throws IOException, CertificateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Throwable th;
        OutputStream outputStream = null;
        y yVar = null;
        OutputStream outputStream2 = null;
        y a;
        try {
            if (a(acVar.a, str)) {
                if (null != null) {
                    try {
                        outputStream2.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        yVar.close();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    return;
                }
            }
            File file = new File(acVar.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            a = y.a(file, 1, 1, acVar.b);
            try {
                a.a(acVar.d);
                byte[] b = acVar.e.b(bArr);
                a a2 = a.a(str);
                outputStream = a2.a(0);
                outputStream.write(b);
                a2.a();
                a.b();
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
            p.c(th, "leg", IXAdRequestInfo.FET);
            return false;
        }
    }
}
