package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Utility */
public class bk {
    public static void a(String str) {
    }

    public static long a() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    public static List<OfflineMapProvince> a(String str, Context context) throws JSONException {
        if (str == null || "".equals(str)) {
            return new ArrayList();
        }
        return a(new JSONObject(str), context);
    }

    public static List<OfflineMapProvince> a(JSONObject jSONObject, Context context) throws JSONException {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        List<OfflineMapProvince> arrayList = new ArrayList();
        if (jSONObject.has("result")) {
            optJSONObject = jSONObject.optJSONObject("result");
        } else {
            optJSONObject = new JSONObject();
            try {
                optJSONObject.put("result", new JSONObject().put("offlinemap_with_province_vfour", jSONObject));
                c(optJSONObject.toString(), context);
                optJSONObject = optJSONObject.optJSONObject("result");
            } catch (Throwable e) {
                Throwable th = e;
                optJSONObject = jSONObject.optJSONObject("result");
                gz.c(th, "Utility", "parseJson");
                th.printStackTrace();
            }
        }
        if (optJSONObject != null) {
            optJSONObject = optJSONObject.optJSONObject("offlinemap_with_province_vfour");
            if (optJSONObject == null) {
                return arrayList;
            }
            optJSONObject2 = optJSONObject.optJSONObject("offlinemapinfo_with_province");
        } else {
            optJSONObject2 = jSONObject.optJSONObject("offlinemapinfo_with_province");
        }
        if (optJSONObject2 == null) {
            return arrayList;
        }
        if (optJSONObject2.has("version")) {
            al.d = a(optJSONObject2, "version");
        }
        JSONArray optJSONArray = optJSONObject2.optJSONArray("provinces");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
            if (optJSONObject3 != null) {
                arrayList.add(a(optJSONObject3));
            }
        }
        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("others");
        optJSONObject = null;
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            optJSONObject = optJSONArray2.getJSONObject(0);
        }
        if (optJSONObject == null) {
            return arrayList;
        }
        arrayList.add(a(optJSONObject));
        return arrayList;
    }

    public static OfflineMapProvince a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setUrl(a(jSONObject, "url"));
        offlineMapProvince.setProvinceName(a(jSONObject, "name"));
        offlineMapProvince.setJianpin(a(jSONObject, "jianpin"));
        offlineMapProvince.setPinyin(a(jSONObject, "pinyin"));
        offlineMapProvince.setProvinceCode(d(a(jSONObject, "adcode")));
        offlineMapProvince.setVersion(a(jSONObject, "version"));
        offlineMapProvince.setSize(Long.parseLong(a(jSONObject, "size")));
        offlineMapProvince.setCityList(b(jSONObject));
        return offlineMapProvince;
    }

    private static String d(String str) {
        if ("000001".equals(str)) {
            return "100000";
        }
        return str;
    }

    public static ArrayList<OfflineMapCity> b(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        if (optJSONArray == null) {
            return arrayList;
        }
        if (optJSONArray.length() == 0) {
            arrayList.add(c(jSONObject));
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(c(optJSONObject));
            }
        }
        return arrayList;
    }

    public static OfflineMapCity c(JSONObject jSONObject) throws JSONException {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setAdcode(d(a(jSONObject, "adcode")));
        offlineMapCity.setUrl(a(jSONObject, "url"));
        offlineMapCity.setCity(a(jSONObject, "name"));
        offlineMapCity.setCode(a(jSONObject, "citycode"));
        offlineMapCity.setPinyin(a(jSONObject, "pinyin"));
        offlineMapCity.setJianpin(a(jSONObject, "jianpin"));
        offlineMapCity.setVersion(a(jSONObject, "version"));
        offlineMapCity.setSize(Long.parseLong(a(jSONObject, "size")));
        return offlineMapCity;
    }

    public static long a(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j += a(file2);
            } else {
                j += file2.length();
            }
        }
        return j;
    }

    public static boolean b(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!b(listFiles[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String a(Context context, String str) {
        try {
            return en.a(eg.a(context).open(str));
        } catch (Throwable th) {
            gz.c(th, "MapDownloadManager", "readOfflineAsset");
            th.printStackTrace();
            return null;
        }
    }

    public static String c(File file) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        Throwable e;
        Object obj;
        Throwable th;
        String str = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                    } catch (IOException e3) {
                        e = e3;
                    }
                }
                str = stringBuffer.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e42) {
                        e42.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                obj = str;
                try {
                    gz.c(e, "MapDownloadManager", "readOfflineSD filenotfound");
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e42222) {
                            e42222.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e422222) {
                            e422222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                obj = str;
                gz.c(e, "MapDownloadManager", "readOfflineSD io");
                e.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4222222) {
                        e4222222.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e42222222) {
                        e42222222.printStackTrace();
                    }
                }
                return str;
            } catch (Throwable e7) {
                obj = str;
                th = e7;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e8) {
            e7 = e8;
            obj = str;
            Object obj2 = str;
            gz.c(e7, "MapDownloadManager", "readOfflineSD filenotfound");
            e7.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (IOException e9) {
            e7 = e9;
            bufferedReader = str;
            fileInputStream = str;
            gz.c(e7, "MapDownloadManager", "readOfflineSD io");
            e7.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable e72) {
            bufferedReader = str;
            fileInputStream = str;
            th = e72;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    public static void b(String str, Context context) throws IOException, Exception {
        File[] listFiles = new File(en.c(context)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.exists() && file.getName().contains(str)) {
                    b(file);
                }
            }
            b(en.c(context));
        }
    }

    public static void b(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.exists() && file2.isDirectory()) {
                        String[] list = file2.list();
                        if (list == null) {
                            file2.delete();
                        } else if (list.length == 0) {
                            file2.delete();
                        }
                    }
                }
            }
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

    public static void c(String str, Context context) {
        Throwable e;
        if (!"".equals(en.c(context))) {
            File file = new File(en.c(context) + "offlinemapv4.png");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e2) {
                    gz.c(e2, "OfflineUpdateCityHandlerAbstract", "writeSD dirCreate");
                    e2.printStackTrace();
                }
            }
            if (a() > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                OutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(str.getBytes("utf-8"));
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        try {
                            gz.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD filenotfound");
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            e = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw e;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        gz.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD io");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    e = e7;
                    fileOutputStream = null;
                    gz.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD filenotfound");
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e8) {
                    e = e8;
                    fileOutputStream = null;
                    gz.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD io");
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th2) {
                    e = th2;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw e;
                }
            }
        }
    }

    public static String c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return str.substring(str.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1, str.indexOf(".zip"));
            }
        } catch (Throwable th) {
            gz.c(th, "Utility", "getZipFileNameFromUrl");
        }
        return null;
    }
}
