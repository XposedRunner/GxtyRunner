package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.GeoLanguage;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.tencent.open.SocialConstants;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Parser */
public final class ci {
    private StringBuilder a = new StringBuilder();
    private AMapLocationClientOption b = new AMapLocationClientOption();

    private void a(AMapLocationServer aMapLocationServer, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str).append(" ");
        }
        if (!TextUtils.isEmpty(str2)) {
            if (this.b.getGeoLanguage() == GeoLanguage.EN) {
                if (!str2.equals(str)) {
                    stringBuilder.append(str2).append(" ");
                }
            } else if (!(str.contains("市") && str.equals(str2))) {
                stringBuilder.append(str2).append(" ");
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(str3).append(" ");
        }
        if (!TextUtils.isEmpty(str4)) {
            stringBuilder.append(str4).append(" ");
        }
        if (!TextUtils.isEmpty(str5)) {
            stringBuilder.append(str5).append(" ");
        }
        if (!TextUtils.isEmpty(str6)) {
            if (TextUtils.isEmpty(str7) || this.b.getGeoLanguage() == GeoLanguage.EN) {
                stringBuilder.append("Near " + str6);
                aMapLocationServer.setDescription("Near " + str6);
            } else {
                stringBuilder.append("靠近");
                stringBuilder.append(str6).append(" ");
                aMapLocationServer.setDescription("在" + str6 + "附近");
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("citycode", aMapLocationServer.getCityCode());
        bundle.putString(SocialConstants.PARAM_APP_DESC, stringBuilder.toString());
        bundle.putString("adcode", aMapLocationServer.getAdCode());
        aMapLocationServer.setExtras(bundle);
        aMapLocationServer.g(stringBuilder.toString());
        String adCode = aMapLocationServer.getAdCode();
        if (adCode == null || adCode.trim().length() <= 0 || this.b.getGeoLanguage() == GeoLanguage.EN) {
            aMapLocationServer.setAddress(stringBuilder.toString());
        } else {
            aMapLocationServer.setAddress(stringBuilder.toString().replace(" ", ""));
        }
    }

    private static String b(String str) {
        return "[]".equals(str) ? "" : str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer a(com.autonavi.aps.amapapi.model.AMapLocationServer r14, byte[] r15) {
        /*
        r13 = this;
        r1 = 0;
        r2 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r12 = 0;
        r11 = 1;
        if (r15 != 0) goto L_0x002b;
    L_0x000a:
        r0 = 5;
        r14.setErrorCode(r0);	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0 = r13.a;	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r2 = "binaryResult is null#0504";
        r0.append(r2);	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0 = r13.a;	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r14.setLocationDetail(r0);	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0 = r13.a;	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r2 = 0;
        r3 = r13.a;	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r3 = r3.length();	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0.delete(r2, r3);	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
    L_0x002a:
        return r14;
    L_0x002b:
        r9 = java.nio.ByteBuffer.wrap(r15);	 Catch:{ Throwable -> 0x0234, all -> 0x0273 }
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != 0) goto L_0x0049;
    L_0x0035:
        r0 = r9.getShort();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.b(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.clear();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r9 == 0) goto L_0x002a;
    L_0x0045:
        r9.clear();
        goto L_0x002a;
    L_0x0049:
        r0 = r9.getInt();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = (double) r0;	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 / r2;
        r0 = com.loc.ct.a(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.setLongitude(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.getInt();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = (double) r0;	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 / r2;
        r0 = com.loc.ct.a(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.setLatitude(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.getShort();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = (float) r0;	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.setAccuracy(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.c(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.d(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != r11) goto L_0x0176;
    L_0x0087:
        r1 = "";
        r3 = "";
        r4 = "";
        r5 = "";
        r6 = "";
        r7 = "";
        r8 = "";
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x02a2, all -> 0x027b }
        r10 = "UTF-8";
        r2.<init>(r0, r10);	 Catch:{ Throwable -> 0x02a2, all -> 0x027b }
        r14.setCountry(r2);	 Catch:{ Throwable -> 0x02a2, all -> 0x027b }
    L_0x00aa:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r2 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r2);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x0211, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r2, r10);	 Catch:{ Throwable -> 0x0211, all -> 0x027b }
        r14.setProvince(r0);	 Catch:{ Throwable -> 0x029f, all -> 0x027b }
        r2 = r0;
    L_0x00c0:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x0216, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x0216, all -> 0x027b }
        r14.setCity(r0);	 Catch:{ Throwable -> 0x029c, all -> 0x027b }
        r3 = r0;
    L_0x00d6:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x021b, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x021b, all -> 0x027b }
        r14.setDistrict(r0);	 Catch:{ Throwable -> 0x029a, all -> 0x027b }
        r4 = r0;
    L_0x00ec:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x0220, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x0220, all -> 0x027b }
        r14.setStreet(r0);	 Catch:{ Throwable -> 0x0298, all -> 0x027b }
        r14.setRoad(r0);	 Catch:{ Throwable -> 0x0298, all -> 0x027b }
        r5 = r0;
    L_0x0105:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x0225, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x0225, all -> 0x027b }
        r14.setNumber(r0);	 Catch:{ Throwable -> 0x0296, all -> 0x027b }
        r6 = r0;
    L_0x011b:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x022a, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x022a, all -> 0x027b }
        r14.setPoiName(r0);	 Catch:{ Throwable -> 0x0294, all -> 0x027b }
        r7 = r0;
    L_0x0131:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0291, all -> 0x027b }
        r10 = "UTF-8";
        r1.<init>(r0, r10);	 Catch:{ Throwable -> 0x0291, all -> 0x027b }
        r14.setAoiName(r1);	 Catch:{ Throwable -> 0x0291, all -> 0x027b }
    L_0x0146:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r1);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x022f, all -> 0x027b }
        r10 = "UTF-8";
        r0.<init>(r1, r10);	 Catch:{ Throwable -> 0x022f, all -> 0x027b }
        r14.setAdCode(r0);	 Catch:{ Throwable -> 0x028f, all -> 0x027b }
        r8 = r0;
    L_0x015c:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x028c, all -> 0x027b }
        r10 = "UTF-8";
        r1.<init>(r0, r10);	 Catch:{ Throwable -> 0x028c, all -> 0x027b }
        r14.setCityCode(r1);	 Catch:{ Throwable -> 0x028c, all -> 0x027b }
    L_0x0171:
        r0 = r13;
        r1 = r14;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
    L_0x0176:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != r11) goto L_0x0190;
    L_0x0187:
        r9.getInt();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.getInt();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.getShort();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
    L_0x0190:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != r11) goto L_0x01c0;
    L_0x0196:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0289, all -> 0x027b }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x0289, all -> 0x027b }
        r14.setBuildingId(r1);	 Catch:{ Throwable -> 0x0289, all -> 0x027b }
    L_0x01ab:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = r0 & 255;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0286, all -> 0x027b }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x0286, all -> 0x027b }
        r14.setFloor(r1);	 Catch:{ Throwable -> 0x0286, all -> 0x027b }
    L_0x01c0:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != r11) goto L_0x01cf;
    L_0x01c6:
        r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.getInt();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
    L_0x01cf:
        r0 = r9.get();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        if (r0 != r11) goto L_0x01dc;
    L_0x01d5:
        r0 = r9.getLong();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r14.setTime(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
    L_0x01dc:
        r0 = r9.getShort();	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r9.get(r0);	 Catch:{ Throwable -> 0x0280, all -> 0x027b }
        r1 = r0.length;	 Catch:{ Throwable -> 0x0283, all -> 0x027b }
        if (r1 <= 0) goto L_0x01f7;
    L_0x01e8:
        r1 = 0;
        r0 = android.util.Base64.decode(r0, r1);	 Catch:{ Throwable -> 0x0283, all -> 0x027b }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x0283, all -> 0x027b }
        r2 = "UTF-8";
        r1.<init>(r0, r2);	 Catch:{ Throwable -> 0x0283, all -> 0x027b }
        r14.a(r1);	 Catch:{ Throwable -> 0x0283, all -> 0x027b }
    L_0x01f7:
        if (r9 == 0) goto L_0x01fc;
    L_0x01f9:
        r9.clear();
    L_0x01fc:
        r0 = r13.a;
        r0 = r0.length();
        if (r0 <= 0) goto L_0x002a;
    L_0x0204:
        r0 = r13.a;
        r1 = r13.a;
        r1 = r1.length();
        r0.delete(r12, r1);
        goto L_0x002a;
    L_0x0211:
        r0 = move-exception;
        r0 = r1;
    L_0x0213:
        r2 = r0;
        goto L_0x00c0;
    L_0x0216:
        r0 = move-exception;
        r0 = r3;
    L_0x0218:
        r3 = r0;
        goto L_0x00d6;
    L_0x021b:
        r0 = move-exception;
        r0 = r4;
    L_0x021d:
        r4 = r0;
        goto L_0x00ec;
    L_0x0220:
        r0 = move-exception;
        r0 = r5;
    L_0x0222:
        r5 = r0;
        goto L_0x0105;
    L_0x0225:
        r0 = move-exception;
        r0 = r6;
    L_0x0227:
        r6 = r0;
        goto L_0x011b;
    L_0x022a:
        r0 = move-exception;
        r0 = r7;
    L_0x022c:
        r7 = r0;
        goto L_0x0131;
    L_0x022f:
        r0 = move-exception;
        r0 = r8;
    L_0x0231:
        r8 = r0;
        goto L_0x015c;
    L_0x0234:
        r0 = move-exception;
    L_0x0235:
        r14 = new com.autonavi.aps.amapapi.model.AMapLocationServer;	 Catch:{ all -> 0x027d }
        r2 = "";
        r14.<init>(r2);	 Catch:{ all -> 0x027d }
        r2 = 5;
        r14.setErrorCode(r2);	 Catch:{ all -> 0x027d }
        r2 = r13.a;	 Catch:{ all -> 0x027d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x027d }
        r4 = "parser data error:";
        r3.<init>(r4);	 Catch:{ all -> 0x027d }
        r0 = r0.getMessage();	 Catch:{ all -> 0x027d }
        r0 = r3.append(r0);	 Catch:{ all -> 0x027d }
        r3 = "#0505";
        r0 = r0.append(r3);	 Catch:{ all -> 0x027d }
        r0 = r0.toString();	 Catch:{ all -> 0x027d }
        r2.append(r0);	 Catch:{ all -> 0x027d }
        r0 = 0;
        r2 = 2054; // 0x806 float:2.878E-42 double:1.015E-320;
        com.loc.cq.a(r0, r2);	 Catch:{ all -> 0x027d }
        r0 = r13.a;	 Catch:{ all -> 0x027d }
        r0 = r0.toString();	 Catch:{ all -> 0x027d }
        r14.setLocationDetail(r0);	 Catch:{ all -> 0x027d }
        if (r1 == 0) goto L_0x01fc;
    L_0x026f:
        r1.clear();
        goto L_0x01fc;
    L_0x0273:
        r0 = move-exception;
        r9 = r1;
    L_0x0275:
        if (r9 == 0) goto L_0x027a;
    L_0x0277:
        r9.clear();
    L_0x027a:
        throw r0;
    L_0x027b:
        r0 = move-exception;
        goto L_0x0275;
    L_0x027d:
        r0 = move-exception;
        r9 = r1;
        goto L_0x0275;
    L_0x0280:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0235;
    L_0x0283:
        r0 = move-exception;
        goto L_0x01f7;
    L_0x0286:
        r0 = move-exception;
        goto L_0x01c0;
    L_0x0289:
        r0 = move-exception;
        goto L_0x01ab;
    L_0x028c:
        r0 = move-exception;
        goto L_0x0171;
    L_0x028f:
        r1 = move-exception;
        goto L_0x0231;
    L_0x0291:
        r0 = move-exception;
        goto L_0x0146;
    L_0x0294:
        r1 = move-exception;
        goto L_0x022c;
    L_0x0296:
        r1 = move-exception;
        goto L_0x0227;
    L_0x0298:
        r1 = move-exception;
        goto L_0x0222;
    L_0x029a:
        r1 = move-exception;
        goto L_0x021d;
    L_0x029c:
        r1 = move-exception;
        goto L_0x0218;
    L_0x029f:
        r1 = move-exception;
        goto L_0x0213;
    L_0x02a2:
        r0 = move-exception;
        goto L_0x00aa;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ci.a(com.autonavi.aps.amapapi.model.AMapLocationServer, byte[]):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final AMapLocationServer a(String str) {
        try {
            String b;
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("regeocode");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
            aMapLocationServer.setCountry(b(optJSONObject2.optString("country")));
            String b2 = b(optJSONObject2.optString("province"));
            aMapLocationServer.setProvince(b2);
            String b3 = b(optJSONObject2.optString("citycode"));
            aMapLocationServer.setCityCode(b3);
            String optString = optJSONObject2.optString("city");
            if (!b3.endsWith("010") && !b3.endsWith("021") && !b3.endsWith("022") && !b3.endsWith("023")) {
                optString = b(optString);
                aMapLocationServer.setCity(optString);
            } else if (b2 != null && b2.length() > 0) {
                aMapLocationServer.setCity(b2);
                optString = b2;
            }
            if (TextUtils.isEmpty(optString)) {
                aMapLocationServer.setCity(b2);
                optString = b2;
            }
            b3 = b(optJSONObject2.optString("district"));
            aMapLocationServer.setDistrict(b3);
            String b4 = b(optJSONObject2.optString("adcode"));
            aMapLocationServer.setAdCode(b4);
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("streetNumber");
            String b5 = b(optJSONObject3.optString("street"));
            aMapLocationServer.setStreet(b5);
            aMapLocationServer.setRoad(b5);
            String b6 = b(optJSONObject3.optString("number"));
            aMapLocationServer.setNumber(b6);
            JSONArray optJSONArray = optJSONObject.optJSONArray("pois");
            if (optJSONArray.length() > 0) {
                b = b(optJSONArray.getJSONObject(0).optString("name"));
                aMapLocationServer.setPoiName(b);
            } else {
                b = null;
            }
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("aois");
            if (optJSONArray2.length() > 0) {
                aMapLocationServer.setAoiName(b(optJSONArray2.getJSONObject(0).optString("name")));
            }
            a(aMapLocationServer, b2, optString, b3, b5, b6, b, b4);
            return aMapLocationServer;
        } catch (Throwable th) {
            return null;
        }
    }

    public final AMapLocationServer a(String str, Context context, as asVar) {
        String str2;
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(7);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("#SHA1AndPackage#").append(db.e(context));
            str2 = (String) ((List) asVar.b.get("gsid")).get(0);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("#gsid#").append(str2);
            }
            Object obj = asVar.c;
            if (!TextUtils.isEmpty(obj)) {
                stringBuffer.append("#csid#" + obj);
            }
        } catch (Throwable th) {
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.has("status") && jSONObject.has("info"))) {
                this.a.append("json is error:").append(str).append(stringBuffer).append("#0702");
            }
            String string = jSONObject.getString("status");
            String string2 = jSONObject.getString("info");
            str2 = jSONObject.getString("infocode");
            if ("0".equals(string)) {
                this.a.append("auth fail:").append(string2).append(stringBuffer).append("#0701");
                cq.a(asVar.d, str2, string2);
            }
        } catch (Throwable th2) {
            this.a.append("json exception error:").append(th2.getMessage()).append(stringBuffer).append("#0703");
            cl.a(th2, "parser", "paseAuthFailurJson");
        }
        aMapLocationServer.setLocationDetail(this.a.toString());
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        return aMapLocationServer;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            this.b = new AMapLocationClientOption();
        } else {
            this.b = aMapLocationClientOption;
        }
    }
}
