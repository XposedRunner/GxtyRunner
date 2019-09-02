package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import org.json.JSONObject;

/* compiled from: MapParser */
public final class ko {
    private StringBuilder a = new StringBuilder();

    public final kx a(String str, Context context, jb jbVar) {
        String string;
        kx kxVar = new kx("");
        kxVar.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.has("status") && jSONObject.has("info"))) {
                this.a.append("json is error " + str);
            }
            String string2 = jSONObject.getString("status");
            string = jSONObject.getString("info");
            if (string2.equals("0")) {
                this.a.append("auth fail:" + string);
            }
        } catch (Throwable th) {
            this.a.append("json exception error:" + th.getMessage());
            kz.a(th, "MapParser", "paseAuthFailurJson");
        }
        try {
            this.a.append("#SHA1AndPackage#").append(fx.e(context));
            string = (String) ((List) jbVar.b.get("gsid")).get(0);
            if (!TextUtils.isEmpty(string)) {
                this.a.append(" #gsid#").append(string);
            }
            Object obj = jbVar.c;
            if (!TextUtils.isEmpty(obj)) {
                this.a.append(" #csid#" + obj);
            }
        } catch (Throwable th2) {
        }
        kxVar.setLocationDetail(this.a.toString());
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        return kxVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.amap.api.mapcore.util.kx a(byte[] r14) {
        /*
        r13 = this;
        r12 = 0;
        r4 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r11 = 1;
        r1 = 0;
        r0 = new com.amap.api.mapcore.util.kx;	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2 = "";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        if (r14 != 0) goto L_0x0032;
    L_0x0011:
        r2 = 5;
        r0.setErrorCode(r2);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2 = r13.a;	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r3 = "byte[] is null";
        r2.append(r3);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2 = r13.a;	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r0.setLocationDetail(r2);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2 = r13.a;	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r3 = 0;
        r4 = r13.a;	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r4 = r4.length();	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r2.delete(r3, r4);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
    L_0x0031:
        return r0;
    L_0x0032:
        r8 = java.nio.ByteBuffer.wrap(r14);	 Catch:{ Throwable -> 0x031d, all -> 0x0310 }
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != 0) goto L_0x0050;
    L_0x003c:
        r1 = r8.getShort();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.b(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.clear();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r8 == 0) goto L_0x0031;
    L_0x004c:
        r8.clear();
        goto L_0x0031;
    L_0x0050:
        r1 = r8.getInt();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = (double) r1;	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = r2 / r4;
        r2 = com.amap.api.mapcore.util.lc.a(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setLongitude(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.getInt();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = (double) r1;	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = r2 / r4;
        r2 = com.amap.api.mapcore.util.lc.a(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setLatitude(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.getShort();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = (float) r1;	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setAccuracy(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.c(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.d(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != r11) goto L_0x0226;
    L_0x008e:
        r2 = "";
        r3 = "";
        r4 = "";
        r5 = "";
        r6 = "";
        r7 = "";
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r9 = new java.lang.String;	 Catch:{ Throwable -> 0x0346, all -> 0x0318 }
        r10 = "UTF-8";
        r9.<init>(r1, r10);	 Catch:{ Throwable -> 0x0346, all -> 0x0318 }
        r0.setCountry(r9);	 Catch:{ Throwable -> 0x0346, all -> 0x0318 }
    L_0x00af:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r9 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r9);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x02b9, all -> 0x0318 }
        r10 = "UTF-8";
        r1.<init>(r9, r10);	 Catch:{ Throwable -> 0x02b9, all -> 0x0318 }
        r0.setProvince(r1);	 Catch:{ Throwable -> 0x0343, all -> 0x0318 }
        r10 = r1;
    L_0x00c5:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r2 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x02be, all -> 0x0318 }
        r9 = "UTF-8";
        r1.<init>(r2, r9);	 Catch:{ Throwable -> 0x02be, all -> 0x0318 }
        r0.setCity(r1);	 Catch:{ Throwable -> 0x0340, all -> 0x0318 }
        r9 = r1;
    L_0x00db:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r2 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x02c3, all -> 0x0318 }
        r3 = "UTF-8";
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x02c3, all -> 0x0318 }
        r0.setDistrict(r1);	 Catch:{ Throwable -> 0x033e, all -> 0x0318 }
        r4 = r1;
    L_0x00f1:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r2 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x02c8, all -> 0x0318 }
        r3 = "UTF-8";
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x02c8, all -> 0x0318 }
        r0.setStreet(r1);	 Catch:{ Throwable -> 0x033c, all -> 0x0318 }
        r0.setRoad(r1);	 Catch:{ Throwable -> 0x033c, all -> 0x0318 }
        r3 = r1;
    L_0x010a:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x0339, all -> 0x0318 }
        r5 = "UTF-8";
        r2.<init>(r1, r5);	 Catch:{ Throwable -> 0x0339, all -> 0x0318 }
        r0.setNumber(r2);	 Catch:{ Throwable -> 0x0339, all -> 0x0318 }
    L_0x011f:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r2 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x02cd, all -> 0x0318 }
        r5 = "UTF-8";
        r1.<init>(r2, r5);	 Catch:{ Throwable -> 0x02cd, all -> 0x0318 }
        r0.setPoiName(r1);	 Catch:{ Throwable -> 0x0337, all -> 0x0318 }
        r2 = r1;
    L_0x0135:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r5 = new java.lang.String;	 Catch:{ Throwable -> 0x0334, all -> 0x0318 }
        r6 = "UTF-8";
        r5.<init>(r1, r6);	 Catch:{ Throwable -> 0x0334, all -> 0x0318 }
        r0.setAoiName(r5);	 Catch:{ Throwable -> 0x0334, all -> 0x0318 }
    L_0x014a:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r5 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r5);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x032d, all -> 0x0318 }
        r6 = "UTF-8";
        r1.<init>(r5, r6);	 Catch:{ Throwable -> 0x032d, all -> 0x0318 }
        r0.setAdCode(r1);	 Catch:{ Throwable -> 0x0331, all -> 0x0318 }
    L_0x015f:
        r5 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r5 = r5 & 255;
        r5 = new byte[r5];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r5);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r6 = new java.lang.String;	 Catch:{ Throwable -> 0x032a, all -> 0x0318 }
        r7 = "UTF-8";
        r6.<init>(r5, r7);	 Catch:{ Throwable -> 0x032a, all -> 0x0318 }
        r0.setCityCode(r6);	 Catch:{ Throwable -> 0x032a, all -> 0x0318 }
    L_0x0174:
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r5.<init>();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r6 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r6 != 0) goto L_0x0188;
    L_0x017f:
        r6 = r5.append(r10);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r7 = " ";
        r6.append(r7);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x0188:
        r6 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r6 != 0) goto L_0x01a5;
    L_0x018e:
        r6 = "市";
        r6 = r10.contains(r6);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r6 == 0) goto L_0x019c;
    L_0x0196:
        r6 = r10.equals(r9);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r6 != 0) goto L_0x01a5;
    L_0x019c:
        r6 = r5.append(r9);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r7 = " ";
        r6.append(r7);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x01a5:
        r6 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r6 != 0) goto L_0x01b4;
    L_0x01ab:
        r4 = r5.append(r4);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r6 = " ";
        r4.append(r6);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x01b4:
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r4 != 0) goto L_0x01c3;
    L_0x01ba:
        r3 = r5.append(r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r4 = " ";
        r3.append(r4);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x01c3:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r3 != 0) goto L_0x01dd;
    L_0x01c9:
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != 0) goto L_0x01d4;
    L_0x01cf:
        r1 = "靠近";
        r5.append(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x01d4:
        r1 = r5.append(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = " ";
        r1.append(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x01dd:
        r1 = new android.os.Bundle;	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1.<init>();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = "citycode";
        r3 = r0.getCityCode();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1.putString(r2, r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = "desc";
        r3 = r5.toString();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1.putString(r2, r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = "adcode";
        r3 = r0.getAdCode();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1.putString(r2, r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setExtras(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r5.toString();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.e(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r0.getAdCode();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 == 0) goto L_0x02d2;
    L_0x020d:
        r1 = r1.trim();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1.length();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 <= 0) goto L_0x02d2;
    L_0x0217:
        r1 = r5.toString();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = " ";
        r3 = "";
        r1 = r1.replace(r2, r3);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setAddress(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x0226:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != r11) goto L_0x0240;
    L_0x0237:
        r8.getInt();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.getInt();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.getShort();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x0240:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != r11) goto L_0x0270;
    L_0x0246:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x0327, all -> 0x0318 }
        r3 = "UTF-8";
        r2.<init>(r1, r3);	 Catch:{ Throwable -> 0x0327, all -> 0x0318 }
        r0.setBuildingId(r2);	 Catch:{ Throwable -> 0x0327, all -> 0x0318 }
    L_0x025b:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = r1 & 255;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x0324, all -> 0x0318 }
        r3 = "UTF-8";
        r2.<init>(r1, r3);	 Catch:{ Throwable -> 0x0324, all -> 0x0318 }
        r0.setFloor(r2);	 Catch:{ Throwable -> 0x0324, all -> 0x0318 }
    L_0x0270:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != r11) goto L_0x027f;
    L_0x0276:
        r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.getInt();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x027f:
        r1 = r8.get();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        if (r1 != r11) goto L_0x028c;
    L_0x0285:
        r2 = r8.getLong();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setTime(r2);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
    L_0x028c:
        r1 = r8.getShort();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r8.get(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x0321, all -> 0x0318 }
        r3 = "UTF-8";
        r2.<init>(r1, r3);	 Catch:{ Throwable -> 0x0321, all -> 0x0318 }
        r0.a(r2);	 Catch:{ Throwable -> 0x0321, all -> 0x0318 }
    L_0x029f:
        if (r8 == 0) goto L_0x02a4;
    L_0x02a1:
        r8.clear();
    L_0x02a4:
        r1 = r13.a;
        r1 = r1.length();
        if (r1 <= 0) goto L_0x0031;
    L_0x02ac:
        r1 = r13.a;
        r2 = r13.a;
        r2 = r2.length();
        r1.delete(r12, r2);
        goto L_0x0031;
    L_0x02b9:
        r1 = move-exception;
        r1 = r2;
    L_0x02bb:
        r10 = r1;
        goto L_0x00c5;
    L_0x02be:
        r1 = move-exception;
        r1 = r3;
    L_0x02c0:
        r9 = r1;
        goto L_0x00db;
    L_0x02c3:
        r1 = move-exception;
        r1 = r4;
    L_0x02c5:
        r4 = r1;
        goto L_0x00f1;
    L_0x02c8:
        r1 = move-exception;
        r1 = r5;
    L_0x02ca:
        r3 = r1;
        goto L_0x010a;
    L_0x02cd:
        r1 = move-exception;
        r1 = r6;
    L_0x02cf:
        r2 = r1;
        goto L_0x0135;
    L_0x02d2:
        r1 = r5.toString();	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        r0.setAddress(r1);	 Catch:{ Throwable -> 0x02db, all -> 0x0318 }
        goto L_0x0226;
    L_0x02db:
        r0 = move-exception;
        r1 = r0;
        r2 = r8;
    L_0x02de:
        r0 = new com.amap.api.mapcore.util.kx;	 Catch:{ all -> 0x031a }
        r3 = "";
        r0.<init>(r3);	 Catch:{ all -> 0x031a }
        r3 = 5;
        r0.setErrorCode(r3);	 Catch:{ all -> 0x031a }
        r3 = r13.a;	 Catch:{ all -> 0x031a }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x031a }
        r5 = "parser data error:";
        r4.<init>(r5);	 Catch:{ all -> 0x031a }
        r1 = r1.getMessage();	 Catch:{ all -> 0x031a }
        r1 = r4.append(r1);	 Catch:{ all -> 0x031a }
        r1 = r1.toString();	 Catch:{ all -> 0x031a }
        r3.append(r1);	 Catch:{ all -> 0x031a }
        r1 = r13.a;	 Catch:{ all -> 0x031a }
        r1 = r1.toString();	 Catch:{ all -> 0x031a }
        r0.setLocationDetail(r1);	 Catch:{ all -> 0x031a }
        if (r2 == 0) goto L_0x02a4;
    L_0x030c:
        r2.clear();
        goto L_0x02a4;
    L_0x0310:
        r0 = move-exception;
        r8 = r1;
    L_0x0312:
        if (r8 == 0) goto L_0x0317;
    L_0x0314:
        r8.clear();
    L_0x0317:
        throw r0;
    L_0x0318:
        r0 = move-exception;
        goto L_0x0312;
    L_0x031a:
        r0 = move-exception;
        r8 = r2;
        goto L_0x0312;
    L_0x031d:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x02de;
    L_0x0321:
        r1 = move-exception;
        goto L_0x029f;
    L_0x0324:
        r1 = move-exception;
        goto L_0x0270;
    L_0x0327:
        r1 = move-exception;
        goto L_0x025b;
    L_0x032a:
        r5 = move-exception;
        goto L_0x0174;
    L_0x032d:
        r1 = move-exception;
        r1 = r7;
        goto L_0x015f;
    L_0x0331:
        r5 = move-exception;
        goto L_0x015f;
    L_0x0334:
        r1 = move-exception;
        goto L_0x014a;
    L_0x0337:
        r2 = move-exception;
        goto L_0x02cf;
    L_0x0339:
        r1 = move-exception;
        goto L_0x011f;
    L_0x033c:
        r2 = move-exception;
        goto L_0x02ca;
    L_0x033e:
        r2 = move-exception;
        goto L_0x02c5;
    L_0x0340:
        r2 = move-exception;
        goto L_0x02c0;
    L_0x0343:
        r2 = move-exception;
        goto L_0x02bb;
    L_0x0346:
        r1 = move-exception;
        goto L_0x00af;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ko.a(byte[]):com.amap.api.mapcore.util.kx");
    }
}
