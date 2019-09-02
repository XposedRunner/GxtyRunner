package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.ajguan.library.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SoCrashLogProcessor */
public class hb {
    public static byte[] a = "FDF1F436161AEF5B".getBytes();
    public static byte[] b = "0102030405060708".getBytes();
    public static String c = "SOCRASH";
    private static HashSet<String> d = new HashSet();
    private static final String f = c;
    private File[] e;

    /* compiled from: SoCrashLogProcessor */
    private static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        public a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return new a();
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new a(jSONObject.optString("mk", ""), jSONObject.optString("ak", ""), jSONObject.optString("bk", ""), jSONObject.optString("ik", ""), jSONObject.optString("nk", ""));
            } catch (Throwable th) {
                return new a();
            }
        }

        public static List<a> b(String str) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            List<a> arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(a(jSONArray.getString(i)));
                }
                return arrayList;
            } catch (Throwable th) {
                th.printStackTrace();
                return arrayList;
            }
        }
    }

    /* compiled from: SoCrashLogProcessor */
    private static class b {
        private int a;
        private String b;
    }

    void a(Context context) {
        int i = 0;
        try {
            File[] b = b(context);
            if (b != null) {
                this.e = b;
                gk gkVar = null;
                try {
                    gkVar = new com.amap.api.mapcore.util.gk.a(f, BuildConfig.VERSION_NAME, "").a(new String[0]).a();
                } catch (Throwable th) {
                }
                List arrayList = new ArrayList();
                while (i < b.length && i < 10) {
                    File file = b[i];
                    if (file != null && file.exists() && file.isFile()) {
                        byte[] b2 = b(file);
                        if (b2 == null || b2.length == 0 || b2.length > 100000) {
                            file.delete();
                        } else {
                            String a = gf.a(b2);
                            if (a(arrayList, a) || d.contains(a)) {
                                file.delete();
                            } else {
                                a(context, b2);
                                d.add(a);
                                ha.a(gkVar, context, f, ge.b(b2));
                                a(file);
                            }
                        }
                    }
                    i++;
                }
            }
        } catch (Throwable th2) {
        }
    }

    private File[] b(Context context) {
        File file = new File(gx.a(context));
        if (file == null || !file.isDirectory()) {
            return null;
        }
        return file.listFiles();
    }

    private void a(Context context, byte[] bArr) {
        if (context != null) {
            try {
                String str = new String(bArr, "ISO-8859-1");
                Object obj = (str.indexOf("{\"") <= 0 || str.indexOf("\"}") <= 0) ? null : 1;
                if (obj != null) {
                    JSONObject jSONObject = new JSONObject(str.substring(str.indexOf("{\""), str.lastIndexOf("\"}") + 2));
                    String optString = jSONObject.optString("ik");
                    Object optString2 = jSONObject.optString("jk");
                    if (!TextUtils.isEmpty(optString2)) {
                        List b = a.b(optString);
                        if (b != null) {
                            for (int i = 0; i < b.size(); i++) {
                                a aVar = (a) b.get(i);
                                if (optString2.contains(aVar.e())) {
                                    a(context, aVar);
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static String a() {
        return gf.b("SO_DYNAMIC_FILE_KEY");
    }

    private static void a(Context context, a aVar) throws JSONException {
        int i = 0;
        if (!TextUtils.isEmpty(aVar.b()) && !TextUtils.isEmpty(aVar.c()) && !TextUtils.isEmpty(aVar.d())) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a(), 0);
            JSONArray jSONArray = new JSONArray(gl.a(fw.b(gl.e(sharedPreferences.getString("SO_ERROR_KEY", "")))));
            while (i < jSONArray.length()) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.opt("mk").equals(aVar.a()) || !jSONObject.opt("ak").equals(aVar.b()) || !jSONObject.opt("bk").equals(aVar.c()) || !jSONObject.opt("ik").equals(aVar.d()) || !jSONObject.opt("nk").equals(aVar.e())) {
                    i++;
                } else {
                    return;
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("mk", aVar.a());
            jSONObject2.putOpt("ak", aVar.b());
            jSONObject2.putOpt("bk", aVar.c());
            jSONObject2.putOpt("ik", aVar.d());
            jSONObject2.putOpt("nk", aVar.e());
            jSONArray.put(jSONObject2);
            Editor edit = sharedPreferences.edit();
            edit.putString("SO_ERROR_KEY", gl.g(fw.a(gl.a(jSONArray.toString()))));
            edit.commit();
        }
    }

    private void a(File file) {
        if (file != null) {
            try {
                file.delete();
            } catch (Exception e) {
            }
        }
    }

    private boolean a(List<b> list, String str) {
        if (list == null) {
            return false;
        }
        for (b bVar : list) {
            if (bVar.b.equals(str)) {
                bVar.a = bVar.a + 1;
                return true;
            }
        }
        return false;
    }

    private byte[] b(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            Object a = ge.a("a1f5886b7153004c5c99559f5261676f".getBytes(), bArr, "nFy1THrhajaZzz8U".getBytes());
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[(a.length - 16)];
            System.arraycopy(a, 0, bArr2, 0, 16);
            System.arraycopy(a, 16, bArr3, 0, a.length - 16);
            if (a(gf.a(bArr3, "MD5"), bArr2)) {
                return bArr3;
            }
            return new byte[0];
        } catch (Throwable th) {
            return null;
        }
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0 || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
