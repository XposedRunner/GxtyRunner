package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.autonavi.ae.gmap.style.StyleElement;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.amap.mapcore.Convert;
import com.autonavi.amap.mapcore.FileUtil;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StyleParser */
public class dj {
    private static final int[] d = new int[]{1};
    List<dg> a = null;
    private int b = 0;
    private int c = -1;

    public dj(Context context) {
    }

    public dk a(String str, boolean z) {
        if (str == null || "".equals(str)) {
            return null;
        }
        return b(str, z);
    }

    public dk a(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return b(bArr, z);
    }

    public dk b(byte[] bArr, boolean z) {
        dk dkVar = new dk();
        try {
            if (!a(dkVar.a(), bArr)) {
                a(dkVar, bArr, z);
            }
            a(dkVar);
            dkVar.b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return dkVar;
    }

    protected void a(dk dkVar) {
        if (this.c != -1) {
            Map a = dkVar.a();
            for (dg dgVar : a("roads", "trafficRoadBackgroundColor")) {
                a(a, dgVar.d, ea.a("fillColor"), dgVar).value = this.c;
                a(a, dgVar.d, ea.a("strokeColor"), dgVar).value = this.c;
            }
        }
    }

    public dk b(String str, boolean z) {
        try {
            return b(FileUtil.readFileContents(str), z);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private void a(dk dkVar, byte[] bArr, boolean z) {
        dh a = a(bArr);
        if (a != null && a.a() != null) {
            try {
                JSONObject jSONObject = new JSONObject(a.a());
                if (jSONObject != null) {
                    JSONArray names = jSONObject.names();
                    for (int i = 0; i < names.length(); i++) {
                        String string = names.getString(i);
                        if (string == null || !string.trim().equals("sdkTextures")) {
                            if (string == null || !string.trim().equals("background")) {
                                JSONObject optJSONObject = jSONObject.optJSONObject(string);
                                if (optJSONObject != null) {
                                    a(string, dkVar.a(), optJSONObject, z);
                                }
                            } else {
                                this.b = a("#" + jSONObject.optString("background"));
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a(String str, Map<Integer, StyleItem> map, JSONObject jSONObject, boolean z) throws JSONException {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("subType");
            if (optJSONObject == null) {
                a(str, str, (Map) map, jSONObject, z);
                return;
            }
            JSONArray names = optJSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String optString = names.optString(i);
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(optString);
                if (optJSONObject2.has("detailedType")) {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("detailedType");
                    if (optJSONObject3 != null) {
                        JSONArray names2 = optJSONObject3.names();
                        for (int i2 = 0; i2 < names2.length(); i2++) {
                            optString = names2.optString(i2);
                            optJSONObject2 = optJSONObject3.optJSONObject(optString);
                            if (optJSONObject2 != null) {
                                a(str, optString, (Map) map, optJSONObject2, z);
                            }
                        }
                    }
                } else {
                    a(str, optString, (Map) map, optJSONObject2, z);
                }
            }
        }
    }

    public void a(int i) {
        this.c = i;
    }

    private List<dg> b() {
        this.a = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray("[{\n\t\"regions\": {\n\t\t\"name\": \"区域面\",\n\t\t\"subType\": {\n\t\t\t\"land\": {\n\t\t\t\t\"name\": \"陆地\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30001,\n\t\t\t\t\t\"subkey\": [1, 4, 5]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"green\": {\n\t\t\t\t\"name\": \"绿地\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30001,\n\t\t\t\t\t\"subkey\": [3, 7, 8, 9, 10, 12]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"edu\": {\n\t\t\t\t\"name\": \"教育体育\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [3, 31]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"public\": {\n\t\t\t\t\"name\": \"公共设施\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [4, 12, 22, 32]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"traffic\": {\n\t\t\t\t\"name\": \"交通枢纽\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [6, 14, 40]\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 30004\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"scenicSpot\": {\n\t\t\t\t\"name\": \"景区\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [5, 33]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"culture\": {\n\t\t\t\t\"name\": \"文化\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [7, 35]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"health\": {\n\t\t\t\t\"name\": \"医疗卫生\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [8, 36]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"sports\": {\n\t\t\t\t\"name\": \"运动场所\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [9, 10, 13, 19, 20, 21, 34, 37, 39]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"business\": {\n\t\t\t\t\"name\": \"商业场所\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [11, 23, 24, 25, 26, 27, 28, 29, 30, 38]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"parkingLot\": {\n\t\t\t\t\"name\": \"停车场\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30002,\n\t\t\t\t\t\"subkey\": [1]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"subway\": {\n\t\t\t\t\"name\": \"地铁设施\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 30003\n\t\t\t\t}]\n\t\t\t}\n\t\t}\n\t},\n\t\"water\": {\n\t\t\"name\": \"水系\",\n\t\t\"styleMap\": [{\n\t\t\t\"mainkey\": 30001,\n\t\t\t\"subkey\": [2, 6, 11, 13]\n\t\t}, {\n\t\t\t\"mainkey\": 20014\n\t\t}, {\n\t\t\t\"mainkey\": 10002,\n\t\t\t\"subkey\": [13]\n\t\t}]\n\t},\n\t\"buildings\": {\n\t\t\"name\": \"建筑物\",\n\t\t\"styleMap\": [{\n\t\t\t\"mainkey\": 50001\n\t\t}, {\n\t\t\t\"mainkey\": 50002\n\t\t}, {\n\t\t\t\"mainkey\": 50003\n\t\t}, {\n\t\t\t\"mainkey\": 50004\n\t\t}, {\n\t\t\t\"mainkey\": 30002,\n\t\t\t\"subkey\": [2, 15, 16, 17, 18]\n\t\t}]\n\t},\n\t\"roads\": {\n\t\t\"name\": \"道路\",\n\t\t\"subType\": {\n\t\t\t\"highWay\": {\n\t\t\t\t\"name\": \"高速公路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20001\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"ringRoad\": {\n\t\t\t\t\"name\": \"城市环线\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20002\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"nationalRoad\": {\n\t\t\t\t\"name\": \"国道\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20003\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"provincialRoad\": {\n\t\t\t\t\"name\": \"省道\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20004\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"secondaryRoad\": {\n\t\t\t\t\"name\": \"二级公路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20007\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"levelThreeRoad\": {\n\t\t\t\t\"name\": \"三级公路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20008\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"levelFourRoad\": {\n\t\t\t\t\"name\": \"四级道路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20009\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"roadsBeingBuilt\": {\n\t\t\t\t\"name\": \"在建道路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20018\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"railway\": {\n\t\t\t\t\"name\": \"铁路\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20010,\n\t\t\t\t\t\"subkey\": [1]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"highSpeedRailway\": {\n\t\t\t\t\"name\": \"高铁\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20010,\n\t\t\t\t\t\"subkey\": [2]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"subway\": {\n\t\t\t\t\"name\": \"地铁\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20015\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"subwayBeingBuilt\": {\n\t\t\t\t\"name\": \"在建地铁\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20015,\n\t\t\t\t\t\"subkey\": [1, 2]\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 20019\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"overPass\": {\n\t\t\t\t\"name\": \"天桥\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20012\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"underPass\": {\n\t\t\t\t\"name\": \"地道\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20013\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"other\": {\n\t\t\t\t\"name\": \"其他线条\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20011\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 20017\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 20020\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 20024\n\t\t\t\t}, {\n\t\t\t\t\t\"mainkey\": 20028\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"guideBoards\": {\n\t\t\t\t\"name\": \"道路路牌\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 40001\n\t\t\t\t}]\n\t\t\t}\n\t\t}\n\t},\n\t\"labels\": {\n\t\t\"name\": \"标注\",\n\t\t\"subType\": {\n\t\t\t\"pois\": {\n\t\t\t\t\"name\": \"兴趣点\",\n\t\t\t\t\"subType\": {\n\t\t\t\t\t\"hotel\": {\n\t\t\t\t\t\t\"name\": \"住宿\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 0,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [9, 133, 134, 135, 136, 155, 156, 157, 158, 159, 160, 161, 162, 186]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [31, 32, 33, 34, 35, 36, 37, 38, 39, 164, 165]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"restaurant\": {\n\t\t\t\t\t\t\"name\": \"餐饮\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 1,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [19, 20, 21, 22, 114, 115, 116, 117, 118, 119, 183, 187]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [1, 2, 3, 4, 166, 167, 168, 179, 180, 181, 203, 205, 206, 215]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"shop\": {\n\t\t\t\t\t\t\"name\": \"购物\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 2,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [7, 8, 68, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [5, 6, 7, 8, 9, 10, 11, 12, 13, 175, 200, 201, 202, 204]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"scenicSpot\": {\n\t\t\t\t\t\t\"name\": \"风景名胜\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 3,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [4, 12, 14, 38, 69, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 120, 167, 171, 188, 189, 190, 191, 192]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10008\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 187, 188, 190, 192, 193, 194, 195, 196, 198, 216, 217, 218, 219, 220, 221, 223, 224, 225]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"traffic\": {\n\t\t\t\t\t\t\"name\": \"交通设施\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 4,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [23, 24, 25, 26, 31, 36, 148, 154, 168, 172, 175, 176, 177, 178]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\t\t\"subkey\": [11, 16]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10009\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"bank\": {\n\t\t\t\t\t\t\"name\": \"金融保险\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 5,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [42, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 144, 145, 146, 147]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"edu\": {\n\t\t\t\t\t\t\"name\": \"科教文化\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 6,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [10, 11, 13, 35, 138, 139, 140, 141, 142, 143, 163, 164, 165, 166, 170]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [43, 44, 45, 46, 47, 176, 177]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"live\": {\n\t\t\t\t\t\t\"name\": \"生活服务\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 7,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [58, 63, 64, 65, 66, 67, 121, 122, 123]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [28, 29, 30]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"hospital\": {\n\t\t\t\t\t\t\"name\": \"医疗保健\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 8,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [32, 33, 57, 70, 131, 132, 169, 193, 206, 207, 208, 209, 210]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [170, 209]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"pe\": {\n\t\t\t\t\t\t\"name\": \"休闲体育\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 9,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [15, 16, 17, 37, 60, 61, 62, 73, 124, 125, 126, 127, 128, 129, 130, 180, 181, 182, 184, 185, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 213, 214]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [169, 171, 172, 173, 174, 178, 197, 207]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"public\": {\n\t\t\t\t\t\t\"name\": \"公共设施\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 10,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [59, 173, 215]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"buidling\": {\n\t\t\t\t\t\t\"name\": \"商务住宅\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 11,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [5, 6, 74, 75, 76, 77, 78, 79, 80, 81, 179]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [189, 191]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"gov\": {\n\t\t\t\t\t\t\"name\": \"政府机构及社会团体\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 12,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [3, 34, 43, 137]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"moto\": {\n\t\t\t\t\t\t\"name\": \"摩托车服务\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 13,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [113]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"vehicle\": {\n\t\t\t\t\t\t\"name\": \"汽车服务\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 14,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [39, 40, 41, 71, 72, 151, 152, 153]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [40, 41, 42, 182, 183, 184, 185, 186]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"pass\": {\n\t\t\t\t\t\t\"name\": \"通行设施\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 15,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [27, 28, 149, 150, 174]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\t\t\"subkey\": [21]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"subway\": {\n\t\t\t\t\t\t\"name\": \"地铁站\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 16,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10005\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10006\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"roadFacilities\": {\n\t\t\t\t\t\t\"name\": \"道路附属设施\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 17,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [2, 29, 30]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10017\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"address\": {\n\t\t\t\t\t\t\"name\": \"地名\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 18,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [18]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\t\t\"subkey\": [10, 12, 14, 15, 23, 36]\n\t\t\t\t\t\t}]\n\t\t\t\t\t},\n\t\t\t\t\t\"other\": {\n\t\t\t\t\t\t\"name\": \"其他\",\n\t\t\t\t\t\t\"isDetailedType\": true,\n\t\t\t\t\t\t\"detailedCode\": 19,\n\t\t\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\t\t\"mainkey\": 10001,\n\t\t\t\t\t\t\t\"subkey\": [1, 211, 212]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\t\t\"subkey\": [28]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10007,\n\t\t\t\t\t\t\t\"subkey\": [208, 210, 211, 212, 213, 214]\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10010\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10011\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10012\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10013\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10014\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10015\n\t\t\t\t\t\t}, {\n\t\t\t\t\t\t\t\"mainkey\": 10016\n\t\t\t\t\t\t}]\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"aois\": {\n\t\t\t\t\"name\": \"区域标注\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10004\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"continent\": {\n\t\t\t\t\"name\": \"大洲\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [20]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"country\": {\n\t\t\t\t\"name\": \"国家\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [18, 19, 29]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"province\": {\n\t\t\t\t\"name\": \"省/直辖市/自治区/特别行政区\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [22, 26, 33]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"city\": {\n\t\t\t\t\"name\": \"城市\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [1, 2, 3, 4, 5, 7, 24, 25, 27, 30, 31, 32, 34, 35]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"district\": {\n\t\t\t\t\"name\": \"区县\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [6, 8, 37]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"town\": {\n\t\t\t\t\"name\": \"乡镇\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [9]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"village\": {\n\t\t\t\t\"name\": \"村庄\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 10002,\n\t\t\t\t\t\"subkey\": [17]\n\t\t\t\t}]\n\t\t\t}\n\t\t}\n\t},\n\t\"borders\": {\n\t\t\"name\": \"行政区边界\",\n\t\t\"subType\": {\n\t\t\t\"China\": {\n\t\t\t\t\"name\": \"中国国界\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20016,\n\t\t\t\t\t\"subkey\": [1, 2, 9]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"foreign\": {\n\t\t\t\t\"name\": \"外国国界/停火线/主张线\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20016,\n\t\t\t\t\t\"subkey\": [3, 4, 8, 10, 11]\n\t\t\t\t}]\n\t\t\t},\n\t\t\t\"provincial\": {\n\t\t\t\t\"name\": \"省界线\",\n\t\t\t\t\"styleMap\": [{\n\t\t\t\t\t\"mainkey\": 20016,\n\t\t\t\t\t\"subkey\": [5, 6, 7, 12]\n\t\t\t\t}]\n\t\t\t}\n\t\t}\n\t}\n}]");
            if (jSONArray == null || jSONArray.length() == 0) {
                return this.a;
            }
            JSONObject optJSONObject = jSONArray.optJSONObject(0);
            if (optJSONObject == null) {
                return this.a;
            }
            JSONArray names = optJSONObject.names();
            int length = names.length();
            for (int i = 0; i < length; i++) {
                String optString = names.optString(i);
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(optString);
                if (optJSONObject2 != null) {
                    String optString2 = optJSONObject2.optString("name");
                    if (optJSONObject2.has("styleMap")) {
                        a(optJSONObject2.optJSONArray("styleMap"), optString, null, optString2, this.a);
                    } else if (optJSONObject2.has("subType")) {
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("subType");
                        if (optJSONObject3 != null) {
                            JSONArray names2 = optJSONObject3.names();
                            int length2 = names2.length();
                            for (int i2 = 0; i2 < length2; i2++) {
                                String optString3 = names2.optString(i2);
                                optJSONObject2 = optJSONObject3.optJSONObject(optString3);
                                if (optJSONObject2 != null) {
                                    String optString4 = optJSONObject2.optString("name");
                                    if (optJSONObject2.has("styleMap")) {
                                        a(optJSONObject2.optJSONArray("styleMap"), optString, optString3, optString2 + "-" + optString4, this.a);
                                    } else if (optJSONObject2.has("subType")) {
                                        JSONObject optJSONObject4 = optJSONObject2.optJSONObject("subType");
                                        if (optJSONObject4 != null) {
                                            JSONArray names3 = optJSONObject4.names();
                                            int length3 = names3.length();
                                            for (int i3 = 0; i3 < length3; i3++) {
                                                String optString5 = names3.optString(i3);
                                                JSONObject optJSONObject5 = optJSONObject4.optJSONObject(optString5);
                                                if (optJSONObject5 != null) {
                                                    String optString6 = optJSONObject5.optString("name");
                                                    if (optJSONObject5.has("styleMap")) {
                                                        a(optJSONObject5.optJSONArray("styleMap"), optString, optString3 + "-" + optString5, optString2 + "-" + optString4 + "-" + optString6, this.a);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.a.add(new dg(20021, d, "roads", "trafficRoadBackgroundColor", null));
                }
            }
            return this.a;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONArray jSONArray, String str, String str2, String str3, List<dg> list) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("mainkey");
                    int[] iArr = new int[0];
                    JSONArray optJSONArray = optJSONObject.optJSONArray("subkey");
                    if (optJSONArray != null) {
                        int length2 = optJSONArray.length();
                        iArr = new int[length2];
                        for (int i2 = 0; i2 < length2; i2++) {
                            iArr[i2] = optJSONArray.optInt(i2);
                        }
                    }
                    list.add(new dg(optInt, iArr, str, str2, str3));
                }
            }
        }
    }

    private List<dg> a(String str, String str2) {
        List<dg> arrayList = new ArrayList();
        for (dg dgVar : this.a) {
            if (dgVar != null) {
                if (dgVar.e != null && dgVar.e.equals(str2)) {
                    arrayList.add(dgVar);
                } else if (dgVar.e != null && dgVar.e.equals(str) && dgVar.f != null && dgVar.f.contains(str2)) {
                    arrayList.add(dgVar);
                }
            }
        }
        return arrayList;
    }

    private void a(String str, String str2, Map<Integer, StyleItem> map, JSONObject jSONObject, boolean z) throws JSONException {
        if (jSONObject != null) {
            if (this.a == null) {
                this.a = b();
            }
            if (str != null && "roads".equals(str) && "subway".equals(str2)) {
                str2 = "subwayline";
            }
            List<dg> a = a(str, str2);
            for (dg dgVar : a) {
                if (dgVar != null && dgVar.c != NotificationManagerCompat.IMPORTANCE_UNSPECIFIED) {
                    int i = dgVar.d;
                    StyleElement a2;
                    if (jSONObject.optBoolean("visible", true)) {
                        if (!jSONObject.optBoolean("showIcon", true)) {
                            a2 = a((Map) map, i, ea.a("textFillColor"), dgVar);
                            a2.textureId = -1;
                            a2.visible = 0;
                        }
                        if (!jSONObject.optBoolean("showLabel", true)) {
                            a((Map) map, i, ea.a("textFillColor"), dgVar).opacity = 0.0f;
                            a2 = a((Map) map, i, ea.a("textStrokeColor"), dgVar);
                            a2.opacity = 0.0f;
                            a2.visible = 0;
                        }
                        a(map, jSONObject, "color", "opacity", i, dgVar);
                        a(map, jSONObject, "fillColor", "fillOpacity", i, dgVar);
                        a(map, jSONObject, "strokeColor", "strokeOpacity", i, dgVar);
                        a(map, jSONObject, "textFillColor", "textFillOpacity", i, dgVar);
                        a(map, jSONObject, "textStrokeColor", "textStrokeOpacity", i, dgVar);
                        a(map, jSONObject, "backgroundColor", "backgroundOpacity", i, dgVar);
                        if (z) {
                            a((Map) map, jSONObject, "textureName", i, dgVar);
                        }
                    } else {
                        a2 = a((Map) map, i, ea.a("visible"), dgVar);
                        a2.textureId = -1;
                        a2.visible = 0;
                    }
                } else {
                    return;
                }
            }
            a.clear();
        }
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, String str, String str2, int i, dg dgVar) {
        try {
            Object optString = jSONObject.optString(str, null);
            float f = 1.0f;
            int i2 = 0;
            if (TextUtils.isEmpty(optString)) {
                f = (float) jSONObject.optDouble(str2, 1.0d);
            } else {
                i2 = a("#" + optString);
            }
            if (i2 != 0 || ((double) f) != 1.0d) {
                int a = ea.a(str);
                StyleElement a2 = a((Map) map, i, a, dgVar);
                a2.value = i2;
                a2.opacity = f;
                if (dgVar.f != null && dgVar.f.equals("China")) {
                    a((Map) map, i, a, dgVar).opacity = 0.0f;
                } else if (dgVar.e != null && dgVar.e.equals("water") && a == 3) {
                    StyleElement a3 = a((Map) map, i, 2, dgVar);
                    a3.value = i2;
                    a3.opacity = f;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, String str, int i, dg dgVar) {
        try {
            int optInt = jSONObject.optInt(str, 0);
            if (optInt != 0) {
                a((Map) map, i, ea.a(str), dgVar).textureId = optInt;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private dh a(byte[] bArr) {
        dh dhVar;
        Throwable th;
        try {
            dhVar = new dh();
            try {
                byte[] bytes = "l".getBytes("utf-8");
                int length = bArr.length;
                int length2 = bytes.length;
                for (int i = 0; i < length; i++) {
                    int i2 = i % length2;
                    bArr[i] = (byte) (bytes[i2] ^ bArr[i]);
                }
                dhVar.a(Convert.getString(bArr, 0, 4));
                dhVar.b(Convert.getString(bArr, 4, 32));
                dhVar.c(Convert.getString(bArr, 36, 10));
                dhVar.d(a(Convert.getSubBytes(bArr, 78, length - 78), Convert.getSubBytes(bArr, 46, 16), Convert.getSubBytes(bArr, 62, 16)));
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return dhVar;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            dhVar = null;
            th = th4;
            th.printStackTrace();
            return dhVar;
        }
        return dhVar;
    }

    private String a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(bArr), "utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private boolean a(Map<Integer, StyleItem> map, byte[] bArr) {
        try {
            JSONArray jSONArray = new JSONArray(new String(bArr, "UTF-8"));
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("featureType");
                    if (!TextUtils.isEmpty(optString)) {
                        if ("background".equals(optString)) {
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("stylers");
                            if (optJSONObject2 != null) {
                                int a = a(optJSONObject2.optString("color"));
                                if (a != 0) {
                                    this.b = a;
                                }
                            }
                        } else {
                            String b = di.b(optString);
                            if (b != null) {
                                String[] a2 = di.a(optString);
                                if (!(a2 == null || a2.length == 0)) {
                                    Object optString2 = optJSONObject.optString("elementType");
                                    if (!TextUtils.isEmpty(optString2)) {
                                        int a3 = ea.a(optString2);
                                        if (a3 != -1) {
                                            a((Map) map, optJSONObject, b, a2, a3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        } catch (JSONException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, String str, String[] strArr, int i) throws JSONException {
        for (String str2 : strArr) {
            if (this.a == null) {
                this.a = b();
            }
            for (dg dgVar : a(str, str2)) {
                a((Map) map, jSONObject, dgVar.d, i, dgVar);
            }
        }
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, int i, int i2, dg dgVar) throws JSONException {
        StyleElement a = a((Map) map, i, i2, dgVar);
        JSONObject optJSONObject = jSONObject.optJSONObject("stylers");
        if (optJSONObject != null) {
            int a2 = a(optJSONObject.optString("color"));
            if (a2 != 0) {
                a.value = a2;
                a.textureId = optJSONObject.optInt("textureName", 0);
                a.lineWidth = optJSONObject.optInt("lineWidth", 0);
                if (i >= 30 && i <= 38) {
                    a((Map) map, i, 4, dgVar).opacity = 0.1f;
                } else if (dgVar.e != null && dgVar.e.equals("water") && i2 == 3) {
                    a((Map) map, i, 2, dgVar).value = a2;
                }
            }
        }
    }

    private StyleElement a(Map<Integer, StyleItem> map, int i, int i2, dg dgVar) {
        StyleItem styleItem = (StyleItem) map.get(Integer.valueOf(i));
        if (styleItem == null) {
            styleItem = new StyleItem(dgVar.c);
            styleItem.mainKey = dgVar.a;
            styleItem.subKey = dgVar.b;
            map.put(Integer.valueOf(i), styleItem);
        }
        StyleElement styleElement = styleItem.get(i2);
        if (styleElement != null) {
            return styleElement;
        }
        styleElement = new StyleElement();
        styleElement.styleElementType = i2;
        styleItem.put(i2, styleElement);
        return styleElement;
    }

    private int a(String str) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Color.parseColor(str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return i;
    }

    public int a() {
        return this.b;
    }
}
