package com.amap.api.mapcore.util;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.lzy.okgo.model.Progress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

@hf(a = "update_item", b = true)
/* compiled from: UpdateItem */
public class av extends ay {
    private String n = "";
    private Context o;

    public av(OfflineMapCity offlineMapCity, Context context) {
        this.o = context;
        this.a = offlineMapCity.getCity();
        this.c = offlineMapCity.getAdcode();
        this.b = offlineMapCity.getUrl();
        this.g = offlineMapCity.getSize();
        this.e = offlineMapCity.getVersion();
        this.k = offlineMapCity.getCode();
        this.i = 0;
        this.l = offlineMapCity.getState();
        this.j = offlineMapCity.getcompleteCode();
        this.m = offlineMapCity.getPinyin();
        a();
    }

    public av(OfflineMapProvince offlineMapProvince, Context context) {
        this.o = context;
        this.a = offlineMapProvince.getProvinceName();
        this.c = offlineMapProvince.getProvinceCode();
        this.b = offlineMapProvince.getUrl();
        this.g = offlineMapProvince.getSize();
        this.e = offlineMapProvince.getVersion();
        this.i = 1;
        this.l = offlineMapProvince.getState();
        this.j = offlineMapProvince.getcompleteCode();
        this.m = offlineMapProvince.getPinyin();
        a();
    }

    protected void a() {
        this.d = en.c(this.o) + this.m + ".zip" + ".tmp";
    }

    public String b() {
        return this.n;
    }

    public void a(String str) {
        this.n = str;
    }

    public void b(String str) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    JSONObject jSONObject = new JSONObject(str).getJSONObject("file");
                    if (jSONObject != null) {
                        this.a = jSONObject.optString("title");
                        this.c = jSONObject.optString("code");
                        this.b = jSONObject.optString("url");
                        this.d = jSONObject.optString(Progress.FILE_NAME);
                        this.f = jSONObject.optLong("lLocalLength");
                        this.g = jSONObject.optLong("lRemoteLength");
                        this.l = jSONObject.optInt("mState");
                        this.e = jSONObject.optString("version");
                        this.h = jSONObject.optString("localPath");
                        this.n = jSONObject.optString("vMapFileNames");
                        this.i = jSONObject.optInt("isSheng");
                        this.j = jSONObject.optInt("mCompleteCode");
                        this.k = jSONObject.optString("mCityCode");
                        this.m = a(jSONObject, "pinyin");
                        if ("".equals(this.m)) {
                            String substring = this.b.substring(this.b.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1);
                            this.m = substring.substring(0, substring.lastIndexOf("."));
                        }
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "UpdateItem", "readFileToJSONObject");
                th.printStackTrace();
            }
        }
    }

    public void c() {
        Throwable e;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("title", this.a);
            jSONObject2.put("code", this.c);
            jSONObject2.put("url", this.b);
            jSONObject2.put(Progress.FILE_NAME, this.d);
            jSONObject2.put("lLocalLength", this.f);
            jSONObject2.put("lRemoteLength", this.g);
            jSONObject2.put("mState", this.l);
            jSONObject2.put("version", this.e);
            jSONObject2.put("localPath", this.h);
            if (this.n != null) {
                jSONObject2.put("vMapFileNames", this.n);
            }
            jSONObject2.put("isSheng", this.i);
            jSONObject2.put("mCompleteCode", this.j);
            jSONObject2.put("mCityCode", this.k);
            jSONObject2.put("pinyin", this.m);
            jSONObject.put("file", jSONObject2);
            File file = new File(this.d + ".dt");
            file.delete();
            OutputStreamWriter outputStreamWriter;
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                try {
                    outputStreamWriter.write(jSONObject.toString());
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        gz.c(e, "UpdateItem", "saveJSONObjectToFile");
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw e;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                outputStreamWriter = null;
                gz.c(e, "UpdateItem", "saveJSONObjectToFile");
                e.printStackTrace();
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (Throwable th2) {
                e = th2;
                outputStreamWriter = null;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw e;
            }
        } catch (IOException e32) {
            e32.printStackTrace();
        } catch (Throwable e6) {
            gz.c(e6, "UpdateItem", "saveJSONObjectToFile parseJson");
            e6.printStackTrace();
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || "[]".equals(jSONObject.getString(str))) {
            return str2;
        }
        return jSONObject.optString(str).trim();
    }
}
