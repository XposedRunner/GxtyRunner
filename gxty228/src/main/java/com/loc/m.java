package com.loc;

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
public final class m {
    public static byte[] a = "FDF1F436161AEF5B".getBytes();
    public static byte[] b = "0102030405060708".getBytes();
    public static String c;
    private static HashSet<String> d = new HashSet();
    private static final String f;
    private File[] e;

    /* compiled from: SoCrashLogProcessor */
    private static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        private a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public static List<a> a(String str) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            List<a> arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(b(jSONArray.getString(i)));
                }
                return arrayList;
            } catch (Throwable th) {
                th.printStackTrace();
                return arrayList;
            }
        }

        private static a b(String str) {
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

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }

        public final String e() {
            return this.e;
        }
    }

    /* compiled from: SoCrashLogProcessor */
    private static class b {
        private int a;
        private String b;
    }

    static {
        String str = "SOCRASH";
        c = str;
        f = str;
    }

    private static void a(Context context, a aVar) throws JSONException {
        int i = 0;
        if (!TextUtils.isEmpty(aVar.b()) && !TextUtils.isEmpty(aVar.c()) && !TextUtils.isEmpty(aVar.d())) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(dh.b("SO_DYNAMIC_FILE_KEY"), 0);
            JSONArray jSONArray = new JSONArray(dl.a(dm.b(dl.d(sharedPreferences.getString("SO_ERROR_KEY", "")))));
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
            edit.putString("SO_ERROR_KEY", dl.g(dm.a(dl.a(jSONArray.toString()))));
            edit.commit();
        }
    }

    private static void a(Context context, byte[] bArr) {
        if (context != null) {
            try {
                String str = new String(bArr, "ISO-8859-1");
                Object obj = (str.indexOf("{\"") <= 0 || str.indexOf("\"}") <= 0) ? null : 1;
                if (obj != null) {
                    JSONObject jSONObject = new JSONObject(str.substring(str.indexOf("{\""), str.lastIndexOf("\"}") + 2));
                    String optString = jSONObject.optString("ik");
                    Object optString2 = jSONObject.optString("jk");
                    if (!TextUtils.isEmpty(optString2)) {
                        List a = a.a(optString);
                        if (a != null) {
                            for (int i = 0; i < a.size(); i++) {
                                a aVar = (a) a.get(i);
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

    private static boolean a(List<b> list, String str) {
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

    private static boolean a(byte[] bArr, byte[] bArr2) {
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

    private static byte[] a(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            Object a = dg.a("a1f5886b7153004c5c99559f5261676f".getBytes(), bArr, "nFy1THrhajaZzz8U".getBytes());
            byte[] bArr2 = new byte[16];
            Object obj = new byte[(a.length - 16)];
            System.arraycopy(a, 0, bArr2, 0, 16);
            System.arraycopy(a, 16, obj, 0, a.length - 16);
            return !a(dh.a(obj, "MD5"), bArr2) ? new byte[0] : obj;
        } catch (Throwable th) {
            return null;
        }
    }

    final void a(Context context) {
        dk dkVar = null;
        int i = 0;
        try {
            File file = new File(h.a(context));
            File[] listFiles = !file.isDirectory() ? dkVar : file.listFiles();
            if (listFiles != null) {
                this.e = listFiles;
                try {
                    dkVar = new com.loc.dk.a(f, BuildConfig.VERSION_NAME, "").a(new String[0]).a();
                } catch (Throwable th) {
                }
                List arrayList = new ArrayList();
                while (i < listFiles.length && i < 10) {
                    File file2 = listFiles[i];
                    if (file2 != null && file2.exists() && file2.isFile()) {
                        byte[] a = a(file2);
                        if (a == null || a.length == 0 || a.length > 100000) {
                            file2.delete();
                        } else {
                            String a2 = dh.a(a);
                            if (a(arrayList, a2) || d.contains(a2)) {
                                file2.delete();
                            } else {
                                a(context, a);
                                d.add(a2);
                                l.a(dkVar, context, f, dg.b(a));
                                if (file2 != null) {
                                    try {
                                        file2.delete();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        } catch (Throwable th2) {
        }
    }
}
