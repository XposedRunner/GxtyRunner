package com.loc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.GeoLanguage;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.github.mikephil.charting.utils.Utils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: Cache */
public final class ca {
    Hashtable<String, ArrayList<a>> a = new Hashtable();
    boolean b = true;
    long c = 0;
    String d = null;
    bv e = null;
    boolean f = true;
    boolean g = true;
    String h = String.valueOf(GeoLanguage.DEFAULT);
    private long i = 0;
    private boolean j = false;
    private String k = "2.0.201501131131".replace(".", "");
    private String l = null;
    private String m = null;
    private long n = 0;

    /* compiled from: Cache */
    static class a {
        private AMapLocationServer a = null;
        private String b = null;

        protected a() {
        }

        public final AMapLocationServer a() {
            return this.a;
        }

        public final void a(AMapLocationServer aMapLocationServer) {
            this.a = aMapLocationServer;
        }

        public final void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.b = null;
            } else {
                this.b = str.replace("##", "#");
            }
        }

        public final String b() {
            return this.b;
        }
    }

    private AMapLocationServer a(String str, StringBuilder stringBuilder) {
        try {
            a aVar;
            a a;
            if (str.contains("cgiwifi")) {
                a = a(stringBuilder, str);
                if (a != null) {
                    aVar = a;
                }
                aVar = a;
            } else if (str.contains(IXAdSystemUtils.NT_WIFI)) {
                a = a(stringBuilder, str);
                if (a != null) {
                    aVar = a;
                }
                aVar = a;
            } else {
                aVar = (str.contains("cgi") && this.a.containsKey(str)) ? (a) ((ArrayList) this.a.get(str)).get(0) : null;
            }
            if (aVar != null && ct.a(aVar.a())) {
                AMapLocationServer a2 = aVar.a();
                a2.e("mem");
                a2.h(aVar.b());
                if (ck.b(a2.getTime())) {
                    if (ct.a(a2)) {
                        this.c = 0;
                    }
                    a2.setLocationType(4);
                    return a2;
                } else if (this.a != null && this.a.containsKey(str)) {
                    ((ArrayList) this.a.get(str)).remove(aVar);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "Cache", "get1");
        }
        return null;
    }

    private a a(StringBuilder stringBuilder, String str) {
        if (this.a.isEmpty() || TextUtils.isEmpty(stringBuilder)) {
            return null;
        }
        if (!this.a.containsKey(str)) {
            return null;
        }
        a aVar;
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        ArrayList arrayList = (ArrayList) this.a.get(str);
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            aVar = (a) arrayList.get(size);
            if (!TextUtils.isEmpty(aVar.b())) {
                boolean z;
                Object obj = null;
                String b = aVar.b();
                if (TextUtils.isEmpty(b) || TextUtils.isEmpty(stringBuilder)) {
                    z = false;
                } else {
                    if (b.contains(",access")) {
                        if (stringBuilder.indexOf(",access") != -1) {
                            String[] split = b.split(",access");
                            Object substring = split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0];
                            z = TextUtils.isEmpty(substring) ? false : stringBuilder.toString().contains(substring + ",access");
                        }
                    }
                    z = false;
                }
                if (z) {
                    if (ct.a(aVar.b(), stringBuilder.toString())) {
                        break;
                    }
                    obj = 1;
                }
                a(aVar.b(), hashtable);
                a(stringBuilder.toString(), hashtable2);
                hashtable3.clear();
                for (String b2 : hashtable.keySet()) {
                    hashtable3.put(b2, "");
                }
                for (String b22 : hashtable2.keySet()) {
                    hashtable3.put(b22, "");
                }
                Set keySet = hashtable3.keySet();
                double[] dArr = new double[keySet.size()];
                double[] dArr2 = new double[keySet.size()];
                Iterator it = keySet.iterator();
                int i = 0;
                while (it != null && it.hasNext()) {
                    b22 = (String) it.next();
                    dArr[i] = hashtable.containsKey(b22) ? 1.0d : Utils.DOUBLE_EPSILON;
                    dArr2[i] = hashtable2.containsKey(b22) ? 1.0d : Utils.DOUBLE_EPSILON;
                    i++;
                }
                keySet.clear();
                double[] a = a(dArr, dArr2);
                if (a[0] < 0.800000011920929d) {
                    if (a[1] < 0.618d) {
                        if (obj != null && a[0] >= 0.618d) {
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
        aVar = null;
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        return aVar;
    }

    private String a(String str, StringBuilder stringBuilder, Context context) {
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.l == null) {
                this.l = bz.a("MD5", db.c(context));
            }
            if (str.contains(HttpUtils.PARAMETERS_SEPARATOR)) {
                str = str.substring(0, str.indexOf(HttpUtils.PARAMETERS_SEPARATOR));
            }
            String substring = str.substring(str.lastIndexOf("#") + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!(TextUtils.isEmpty(stringBuilder) || stringBuilder.indexOf(",access") == -1)) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + 9)));
                String[] split = stringBuilder.toString().split(",access");
                jSONObject.put("mmac", split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0]);
            }
            try {
                return dg.b(bz.c(jSONObject.toString().getBytes("UTF-8"), this.l));
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } catch (Throwable th) {
            return null;
        }
    }

    private void a(Context context, String str) throws Exception {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        if (ck.u() && context != null) {
            SQLiteDatabase openOrCreateDatabase;
            Cursor rawQuery;
            try {
                openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                try {
                    if (ct.a(openOrCreateDatabase, "hist")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT feature, nb, loc FROM ");
                        stringBuilder.append("hist").append(this.k);
                        stringBuilder.append(" WHERE time > ").append(ct.a() - ck.t());
                        if (str != null) {
                            stringBuilder.append(" and feature = '").append(str + "'");
                        }
                        stringBuilder.append(" ORDER BY time ASC;");
                        rawQuery = openOrCreateDatabase.rawQuery(stringBuilder.toString(), null);
                        try {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            if (this.l == null) {
                                this.l = bz.a("MD5", db.c(context));
                            }
                            if (rawQuery != null && rawQuery.moveToFirst()) {
                                do {
                                    JSONObject jSONObject;
                                    JSONObject jSONObject2;
                                    String str2;
                                    if (rawQuery.getString(0).startsWith("{")) {
                                        jSONObject = new JSONObject(rawQuery.getString(0));
                                        stringBuilder2.delete(0, stringBuilder2.length());
                                        if (!TextUtils.isEmpty(rawQuery.getString(1))) {
                                            stringBuilder2.append(rawQuery.getString(1));
                                        } else if (ct.a(jSONObject, "mmac")) {
                                            stringBuilder2.append("#").append(jSONObject.getString("mmac"));
                                            stringBuilder2.append(",access");
                                        }
                                        jSONObject2 = new JSONObject(rawQuery.getString(2));
                                        if (ct.a(jSONObject2, "type")) {
                                            jSONObject2.put("type", "new");
                                        }
                                    } else {
                                        jSONObject = new JSONObject(new String(bz.d(dg.b(rawQuery.getString(0)), this.l), "UTF-8"));
                                        stringBuilder2.delete(0, stringBuilder2.length());
                                        if (!TextUtils.isEmpty(rawQuery.getString(1))) {
                                            stringBuilder2.append(new String(bz.d(dg.b(rawQuery.getString(1)), this.l), "UTF-8"));
                                        } else if (ct.a(jSONObject, "mmac")) {
                                            stringBuilder2.append("#").append(jSONObject.getString("mmac"));
                                            stringBuilder2.append(",access");
                                        }
                                        jSONObject2 = new JSONObject(new String(bz.d(dg.b(rawQuery.getString(2)), this.l), "UTF-8"));
                                        if (ct.a(jSONObject2, "type")) {
                                            jSONObject2.put("type", "new");
                                        }
                                    }
                                    AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                                    aMapLocationServer.b(jSONObject2);
                                    String str3;
                                    if (ct.a(jSONObject, "mmac") && ct.a(jSONObject, "cgi")) {
                                        str3 = (jSONObject.getString("cgi") + "#") + "network#";
                                        str2 = jSONObject.getString("cgi").contains("#") ? str3 + "cgiwifi" : str3 + IXAdSystemUtils.NT_WIFI;
                                    } else if (ct.a(jSONObject, "cgi")) {
                                        str3 = (jSONObject.getString("cgi") + "#") + "network#";
                                        if (jSONObject.getString("cgi").contains("#")) {
                                            str2 = str3 + "cgi";
                                        }
                                    }
                                    a(str2, stringBuilder2, aMapLocationServer, context, false);
                                } while (rawQuery.moveToNext());
                                stringBuilder2.delete(0, stringBuilder2.length());
                                stringBuilder.delete(0, stringBuilder.length());
                            }
                            if (rawQuery != null) {
                                rawQuery.close();
                            }
                            if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                        openOrCreateDatabase.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    rawQuery = null;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    openOrCreateDatabase.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                rawQuery = null;
                openOrCreateDatabase = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                    openOrCreateDatabase.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r10, com.amap.api.location.AMapLocation r11, java.lang.StringBuilder r12, android.content.Context r13) throws java.lang.Exception {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0005 in list [B:14:0x00bf]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r9 = this;
        r1 = 0;
        r0 = 1;
        r8 = 0;
        if (r13 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r2 = r9.l;
        if (r2 != 0) goto L_0x0016;
    L_0x000a:
        r2 = "MD5";
        r3 = com.loc.db.c(r13);
        r2 = com.loc.bz.a(r2, r3);
        r9.l = r2;
    L_0x0016:
        r2 = r9.a(r10, r12, r13);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "hmdb";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1 = r13.openOrCreateDatabase(r4, r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "CREATE TABLE IF NOT EXISTS hist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r9.k;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = " (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1.execSQL(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r3.length();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.delete(r4, r5);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "REPLACE INTO ";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "hist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r9.k;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4.append(r5);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = " VALUES (?, ?, ?, ?)";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = 4;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r5] = r2;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r12.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = "UTF-8";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r5.getBytes(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r9.l;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = com.loc.bz.c(r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 2;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r11.toStr();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = "UTF-8";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r5.getBytes(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r9.l;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = com.loc.bz.c(r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 3;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r11.getTime();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x0092:
        r0 = r4.length;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r0 + -1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        if (r2 >= r0) goto L_0x00a7;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x0097:
        r0 = r4[r2];	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = (byte[]) r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = (byte[]) r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = com.loc.dg.b(r0);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r2 + 1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        goto L_0x0092;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x00a7:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1.execSQL(r0, r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r3.length();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.delete(r0, r2);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r3.length();
        r3.delete(r8, r0);
        if (r1 == 0) goto L_0x0005;
    L_0x00bf:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0005;
    L_0x00c5:
        r1.close();
        goto L_0x0005;
    L_0x00ca:
        r0 = move-exception;
        r2 = "DB";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "updateHist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        com.loc.cl.a(r0, r2, r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r3.length();
        r3.delete(r8, r0);
        if (r1 == 0) goto L_0x0005;
    L_0x00db:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0005;
    L_0x00e1:
        r1.close();
        goto L_0x0005;
    L_0x00e6:
        r0 = move-exception;
        r2 = r3.length();
        r3.delete(r8, r2);
        if (r1 == 0) goto L_0x00f9;
    L_0x00f0:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r1.close();
    L_0x00f9:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ca.a(java.lang.String, com.amap.api.location.AMapLocation, java.lang.StringBuilder, android.content.Context):void");
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            for (Object obj : str.split("#")) {
                if (!(TextUtils.isEmpty(obj) || obj.contains("|"))) {
                    hashtable.put(obj, "");
                }
            }
        }
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        int i;
        double[] dArr3 = new double[3];
        double d = Utils.DOUBLE_EPSILON;
        double d2 = Utils.DOUBLE_EPSILON;
        double d3 = Utils.DOUBLE_EPSILON;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < dArr.length; i++) {
            d2 += dArr[i] * dArr[i];
            d3 += dArr2[i] * dArr2[i];
            d += dArr[i] * dArr2[i];
            if (dArr2[i] == 1.0d) {
                i2++;
                if (dArr[i] == 1.0d) {
                    i3++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d3) * Math.sqrt(d2));
        dArr3[1] = (1.0d * ((double) i3)) / ((double) i2);
        dArr3[2] = (double) i3;
        for (i = 0; i < dArr3.length - 1; i++) {
            if (dArr3[i] > 1.0d) {
                dArr3[i] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean b() {
        return this.i == 0 ? false : this.a.size() > SpatialRelationUtil.A_CIRCLE_DEGREE ? true : ct.b() - this.i > 36000000;
    }

    private void c() {
        this.i = 0;
        if (!this.a.isEmpty()) {
            this.a.clear();
        }
        this.j = false;
    }

    public final AMapLocationServer a(Context context, String str, StringBuilder stringBuilder, boolean z) {
        if (TextUtils.isEmpty(str) || !ck.u()) {
            return null;
        }
        String str2 = str + HttpUtils.PARAMETERS_SEPARATOR + this.f + HttpUtils.PARAMETERS_SEPARATOR + this.g + HttpUtils.PARAMETERS_SEPARATOR + this.h;
        if (str2.contains("gps") || !ck.u() || stringBuilder == null) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.j) {
            try {
                String a = a(str2, stringBuilder, context);
                c();
                a(context, a);
            } catch (Throwable th) {
            }
        }
        return !this.a.isEmpty() ? a(str2, stringBuilder) : null;
    }

    public final AMapLocationServer a(bw bwVar, boolean z, AMapLocationServer aMapLocationServer, by byVar, StringBuilder stringBuilder, String str, Context context) {
        Object obj = (this.b && ck.u()) ? 1 : null;
        obj = obj == null ? null : (aMapLocationServer == null || ck.b(aMapLocationServer.getTime())) ? 1 : null;
        if (obj == null) {
            return null;
        }
        try {
            Object obj2;
            boolean z2;
            bv c = bwVar.c();
            Object obj3 = (!(c == null && this.e == null) && (this.e == null || !this.e.equals(c))) ? 1 : null;
            if (aMapLocationServer != null) {
                obj = (aMapLocationServer.getAccuracy() <= 299.0f || byVar.b().size() <= 5) ? null : 1;
                obj2 = obj;
            } else {
                obj2 = null;
            }
            if (aMapLocationServer == null || this.d == null || obj2 != null || obj3 != null) {
                z2 = false;
            } else {
                z2 = ct.a(this.d, stringBuilder.toString());
                Object obj4 = (this.c == 0 || ct.b() - this.c >= 3000) ? null : 1;
                if ((z2 || obj4 != null) && ct.a(aMapLocationServer)) {
                    aMapLocationServer.e("mem");
                    aMapLocationServer.setLocationType(2);
                    return aMapLocationServer;
                }
            }
            if (z2) {
                this.c = 0;
            } else {
                this.c = ct.b();
            }
            if (this.m == null || str.equals(this.m)) {
                if (this.m == null) {
                    this.n = ct.a();
                    this.m = str;
                } else {
                    this.n = ct.a();
                }
            } else if (ct.a() - this.n < 3000) {
                str = this.m;
            } else {
                this.n = ct.a();
                this.m = str;
            }
            aMapLocationServer = null;
            if (obj2 == null && !z) {
                aMapLocationServer = a(context, str, stringBuilder, false);
            }
            obj = (z || ct.a(aMapLocationServer)) ? null : 1;
            if (obj != null || obj2 != null) {
                return null;
            }
            if (z) {
                return null;
            }
            this.c = 0;
            aMapLocationServer.setLocationType(4);
            return aMapLocationServer;
        } catch (Throwable th) {
            return null;
        }
    }

    public final void a() {
        this.c = 0;
        this.d = null;
    }

    public final void a(Context context) {
        if (!this.j) {
            try {
                c();
                a(context, null);
            } catch (Throwable th) {
                cl.a(th, "Cache", "loadDB");
            }
            this.j = true;
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.g = aMapLocationClientOption.isNeedAddress();
        this.f = aMapLocationClientOption.isOffset();
        this.b = aMapLocationClientOption.isLocationCacheEnable();
        this.h = String.valueOf(aMapLocationClientOption.getGeoLanguage());
    }

    public final void a(bv bvVar) {
        this.e = bvVar;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, StringBuilder stringBuilder, AMapLocationServer aMapLocationServer, Context context, boolean z) {
        int i = 1;
        int i2 = 0;
        try {
            if (ct.a(aMapLocationServer)) {
                String str2 = str + HttpUtils.PARAMETERS_SEPARATOR + aMapLocationServer.isOffset() + HttpUtils.PARAMETERS_SEPARATOR + aMapLocationServer.i() + HttpUtils.PARAMETERS_SEPARATOR + aMapLocationServer.j();
                if (TextUtils.isEmpty(str2) || !ct.a(aMapLocationServer)) {
                    i = 0;
                } else if (str2.startsWith("#")) {
                    i = 0;
                } else if (!str2.contains("network")) {
                    i = 0;
                }
                if (i != 0 && !aMapLocationServer.e().equals("mem") && !aMapLocationServer.e().equals("file") && !aMapLocationServer.e().equals("wifioff") && !"-3".equals(aMapLocationServer.d())) {
                    if (b()) {
                        c();
                    }
                    JSONObject f = aMapLocationServer.f();
                    if (ct.a(f, "offpct")) {
                        f.remove("offpct");
                        aMapLocationServer.a(f);
                    }
                    if (str2.contains(IXAdSystemUtils.NT_WIFI)) {
                        if (!TextUtils.isEmpty(stringBuilder)) {
                            if (aMapLocationServer.getAccuracy() >= 300.0f) {
                                String[] split = stringBuilder.toString().split("#");
                                int length = split.length;
                                i = 0;
                                while (i2 < length) {
                                    if (split[i2].contains(",")) {
                                        i++;
                                    }
                                    i2++;
                                }
                                if (i >= 8) {
                                    return;
                                }
                            } else if (aMapLocationServer.getAccuracy() <= 3.0f) {
                                return;
                            }
                            if (str2.contains("cgiwifi") && !TextUtils.isEmpty(aMapLocationServer.g())) {
                                String replace = str2.replace("cgiwifi", "cgi");
                                AMapLocationServer h = aMapLocationServer.h();
                                if (ct.a(h)) {
                                    a(replace, new StringBuilder(), h, context, true);
                                }
                            }
                        } else {
                            return;
                        }
                    } else if (str2.contains("cgi")) {
                        if (stringBuilder != null && stringBuilder.indexOf(",") != -1) {
                            return;
                        }
                        if ("4".equals(aMapLocationServer.d())) {
                            return;
                        }
                    }
                    AMapLocationServer a = a(str2, stringBuilder);
                    if (!ct.a(a) || !a.toStr().equals(aMapLocationServer.toStr(3))) {
                        this.i = ct.b();
                        a aVar = new a();
                        aVar.a(aMapLocationServer);
                        aVar.a(TextUtils.isEmpty(stringBuilder) ? null : stringBuilder.toString());
                        if (this.a.containsKey(str2)) {
                            ((ArrayList) this.a.get(str2)).add(aVar);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(aVar);
                            this.a.put(str2, arrayList);
                        }
                        if (z) {
                            a(str2, (AMapLocation) aMapLocationServer, stringBuilder, context);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            cl.a(th, "Cache", "add");
        }
    }

    public final void b(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            c();
            if (context != null) {
                try {
                    sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                    if (ct.a(sQLiteDatabase, "hist")) {
                        sQLiteDatabase.delete("hist" + this.k, "time<?", new String[]{String.valueOf(ct.a() - LogBuilder.MAX_INTERVAL)});
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    } else if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    cl.a(th, "DB", "clearHist p2");
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                    }
                }
            }
            this.j = false;
            this.d = null;
            this.n = 0;
        } catch (Throwable th2) {
            cl.a(th2, "Cache", "destroy part");
        }
    }
}
