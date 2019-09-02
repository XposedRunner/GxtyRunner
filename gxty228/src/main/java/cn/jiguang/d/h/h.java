package cn.jiguang.d.h;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.support.v4.provider.FontsContractCompat.Columns;
import android.text.TextUtils;
import android.util.Base64;
import cn.jiguang.d.d.a;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import cn.jiguang.net.HttpRequest;
import cn.jiguang.net.HttpResponse;
import cn.jiguang.net.HttpUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.lzy.okgo.model.HttpHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class h extends a {
    private static final Object g = new Object();
    private String e = null;
    private ConcurrentLinkedQueue<a> f = new ConcurrentLinkedQueue();
    private boolean h = false;
    private AtomicBoolean i = new AtomicBoolean(false);

    private ArrayList<a> a(Context context, String str) {
        ArrayList<a> arrayList = null;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt(Columns.RESULT_CODE) == 0) {
                    arrayList = a(context, jSONObject.optString("pk_md"), jSONObject.optString("pk_list"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private static ArrayList<a> a(Context context, String str, String str2) {
        try {
            Key d = d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvoyg9qkT/mHa4Av/zMWpzV0lsZoEA7eCtzI0TgRmF3QsPuiZI3zyThkVxyJMyWWH3/hnaJoqJYNIDM/oTRtiyICBeG/0L+BpZYtlv1/FVRPkS6OL3T7e2Xv79T1gCVr948X370lHebKbEzYv6sWlz5SwgMs/rrKSq9bPJqnmCnwIDAQAB");
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, d);
            String str3 = new String(instance.doFinal(Base64.decode(str.getBytes(), 2)));
            String a = cn.jiguang.d.g.a.a.a(str2, "DFA84B10B7ACDD25");
            d.e("WakeUpWhiteListManager", "pklist:" + a);
            Object b = k.b(a);
            if (a == null || TextUtils.isEmpty(b)) {
                return null;
            }
            if (str3.compareToIgnoreCase(b) == 0) {
                ArrayList<a> arrayList = new ArrayList();
                JSONArray jSONArray = new JSONArray(a);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    a aVar = new a();
                    aVar.a = jSONObject.optString("pk_name");
                    aVar.b = jSONObject.optString("sv_name");
                    if (TextUtils.isEmpty(aVar.b)) {
                        aVar.b = "cn.jpush.android.service.DaemonService";
                    }
                    ApplicationInfo g = cn.jiguang.g.a.g(context, aVar.a);
                    if (g != null) {
                        aVar.c = g.targetSdkVersion;
                    }
                    arrayList.add(aVar);
                }
                d.c("WakeUpWhiteListManager", "parseJsonObjet  list:" + arrayList);
                return arrayList;
            }
            d.i("WakeUpWhiteListManager", "md5 not match.");
            return null;
        } catch (Exception e) {
            d.c("WakeUpWhiteListManager", "parseJsonObjet  Exception:" + e);
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject a(String str, ComponentName componentName, boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (componentName == null) {
            d.g("WakeUpWhiteListManager", "componentName was null");
            return null;
        }
        try {
            jSONObject.put("awake_from", str);
            jSONObject.put("awake_to", componentName.getPackageName());
            jSONObject.put("awake_class", componentName.getClassName());
            jSONObject.put("awake_count", 1);
            jSONObject.put("success", z);
            return jSONObject;
        } catch (Throwable th) {
            d.i("WakeUpWhiteListManager", "convert json item failed, error:" + th);
            return null;
        }
    }

    private synchronized <T> void a(Context context, String str, ArrayList<T> arrayList) {
        ObjectOutputStream objectOutputStream;
        Exception e;
        Object e2;
        Throwable th;
        if (!(context == null || arrayList == null)) {
            ObjectOutputStream objectOutputStream2 = null;
            try {
                objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
                try {
                    objectOutputStream.writeObject(arrayList);
                    try {
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    try {
                        d.c("WakeUpWhiteListManager", "FileNotFoundException :" + e2);
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.flush();
                                objectOutputStream.close();
                            } catch (Exception e32) {
                                e32.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.flush();
                                objectOutputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e6) {
                    e32 = e6;
                    objectOutputStream2 = objectOutputStream;
                    try {
                        e32.printStackTrace();
                        if (objectOutputStream2 != null) {
                            try {
                                objectOutputStream2.flush();
                                objectOutputStream2.close();
                            } catch (Exception e322) {
                                e322.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                            objectOutputStream.flush();
                            objectOutputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                objectOutputStream = null;
                d.c("WakeUpWhiteListManager", "FileNotFoundException :" + e2);
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            } catch (Exception e8) {
                e322 = e8;
                e322.printStackTrace();
                if (objectOutputStream2 != null) {
                    objectOutputStream2.flush();
                    objectOutputStream2.close();
                }
            } catch (Throwable th4) {
                th = th4;
                objectOutputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
                throw th;
            }
        }
    }

    private void a(Context context, ArrayList<d> arrayList) {
        a(context, "app_awake", b(context, (ArrayList) arrayList));
    }

    private static boolean a(d dVar) {
        if (dVar == null) {
            d.g("WakeUpWhiteListManager", "wakeup result is null");
            return false;
        }
        HashMap b = dVar.b();
        if (b == null || b.isEmpty()) {
            d.g("WakeUpWhiteListManager", "wakeup result is empty");
            return false;
        }
        for (Integer intValue : b.keySet()) {
            int intValue2 = intValue.intValue();
            d.c("WakeUpWhiteListManager", dVar.a() + intValue2 + ",value:" + b.get(Integer.valueOf(intValue2)));
            if (((Boolean) b.get(Integer.valueOf(intValue2))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private String b() {
        JSONObject jSONObject = new JSONObject();
        if (this.b == null) {
            this.b = "";
        }
        try {
            jSONObject.put("app_key", this.b);
            jSONObject.put("sdk_ver", this.d);
            jSONObject.put(IXAdRequestInfo.OS, VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT));
            jSONObject.put("post_type", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private synchronized ArrayList<a> b(Context context, String str) {
        ObjectInputStream objectInputStream;
        Exception e;
        Throwable th;
        ArrayList<a> arrayList = null;
        synchronized (this) {
            if (context != null) {
                File file = new File(context.getFilesDir(), "Jpush_awake_file_list");
                if (file.exists()) {
                    d.c("WakeUpWhiteListManager", "cache file is exist path " + file.getAbsolutePath());
                    ArrayList<a> arrayList2 = new ArrayList();
                    try {
                        objectInputStream = new ObjectInputStream(context.openFileInput(str));
                        try {
                            arrayList = (ArrayList) objectInputStream.readObject();
                            try {
                                objectInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                d.c("WakeUpWhiteListManager", "getCacheAwakeList Exception" + e);
                                e.printStackTrace();
                                if (objectInputStream == null) {
                                    try {
                                        objectInputStream.close();
                                        arrayList = arrayList2;
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                        arrayList = arrayList2;
                                    }
                                } else {
                                    arrayList = arrayList2;
                                }
                                return arrayList;
                            } catch (Throwable th2) {
                                th = th2;
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (Exception e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        Exception exception = e5;
                        objectInputStream = null;
                        e4 = exception;
                        d.c("WakeUpWhiteListManager", "getCacheAwakeList Exception" + e4);
                        e4.printStackTrace();
                        if (objectInputStream == null) {
                            arrayList = arrayList2;
                        } else {
                            objectInputStream.close();
                            arrayList = arrayList2;
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        objectInputStream = null;
                        th = th3;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                }
                d.c("WakeUpWhiteListManager", "cache file is not exist");
            }
        }
        return arrayList;
    }

    private JSONObject b(Context context, ArrayList<d> arrayList) {
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    String packageName = context.getApplicationContext().getPackageName();
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        d dVar = (d) it.next();
                        if (dVar != null) {
                            JSONObject a = a(packageName, dVar.a(), a(dVar));
                            if (a != null) {
                                jSONArray.put(a);
                            }
                        }
                    }
                    d.c("WakeUpWhiteListManager", "report app list:" + jSONArray);
                    jSONObject.put("awake_path", jSONArray);
                    return jSONObject;
                }
            } catch (Throwable th) {
                d.i("WakeUpWhiteListManager", "reportAwakeList app list failed, error" + th);
                return null;
            }
        }
        d.c("WakeUpWhiteListManager", "none info to report");
        return null;
    }

    private static RSAPublicKey d(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 2)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (Exception e3) {
            throw new Exception("公钥数据为空");
        }
    }

    private List<a> e(Context context) {
        d.c("WakeUpWhiteListManager", "url:" + this.e);
        String str = this.e;
        String b = b();
        try {
            HttpRequest httpRequest = new HttpRequest(str);
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "Close");
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
            httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json");
            httpRequest.setBody(b);
            HttpResponse httpPost = HttpUtils.httpPost(context, httpRequest);
            if (httpPost == null || httpPost.getResponseCode() != 200) {
                d.c("WakeUpWhiteListManager", "Other wrong response status - " + -1 + ", url:" + str);
                return null;
            }
            cn.jiguang.d.a.a.a("jpush_awake_app_pk", System.currentTimeMillis() / 1000);
            b = httpPost.getResponseBody();
            d.e("WakeUpWhiteListManager", "whiteList respContent:" + b);
            return a(context, b);
        } catch (Throwable th) {
            d.c("WakeUpWhiteListManager", "http client execute [" + str + "] error", th);
        }
    }

    private void f(Context context) {
        if (this.h) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.f);
            a(context, "Jpush_awake_file_list", arrayList);
            this.h = false;
        }
    }

    public final void a(Context context, a aVar) {
        int i = 1;
        try {
            if (this.f.contains(aVar)) {
                d.g("WakeUpWhiteListManager", "aWakeInfo has in list, .");
            } else {
                this.f.add(aVar);
                this.h = true;
            }
        } catch (Throwable th) {
            d.i("WakeUpWhiteListManager", "add fail:" + th);
        }
        f(context);
        d.c("WakeUpWhiteListManager", "add aWakeInfo into wakeupList succ.");
        if (!this.i.get()) {
            d.c("WakeUpWhiteListManager", "workQueue is not running,will single run and report.");
            ApplicationInfo g = cn.jiguang.g.a.g(context, aVar.a);
            if (g != null) {
                aVar.c = g.targetSdkVersion;
            }
            if (VERSION.SDK_INT >= 26 && aVar.c >= 26) {
                i = 2;
            }
            ArrayList arrayList = new ArrayList();
            d a = c.a(context, 2, i, aVar, null);
            if (a != null) {
                arrayList.add(a);
            }
            a(context, arrayList);
        }
    }

    protected final boolean a(Context context) {
        if (context == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long l = cn.jiguang.d.a.d.l(context);
        d.c("WakeUpWhiteListManager", "time now:" + currentTimeMillis + ", last launched time:" + l);
        if (-1 == l || Math.abs(currentTimeMillis - l) > this.a) {
            return true;
        }
        d.a("WakeUpWhiteListManager", "localTime - lastLaunchTime = " + (currentTimeMillis - l));
        return false;
    }

    protected final void b(Context context) {
        cn.jiguang.d.a.d.b(context, System.currentTimeMillis() / 1000);
    }

    public final void b(Context context, a aVar) {
        if (this.f.contains(aVar)) {
            try {
                this.f.remove(aVar);
                this.h = true;
            } catch (Throwable th) {
                d.i("WakeUpWhiteListManager", "remove fail:" + th);
            }
            d.c("WakeUpWhiteListManager", "remove aWakeInfo from wakeupList succ.");
        } else {
            d.c("WakeUpWhiteListManager", "cannot found this in wakeupList.");
        }
        f(context);
    }

    protected final ArrayList<a> c(Context context) {
        ArrayList arrayList = (ArrayList) e(context);
        if (arrayList != null && arrayList.size() > 0) {
            if (arrayList.size() == 1 && k.a(((a) arrayList.get(0)).a)) {
                File file = new File(context.getFilesDir(), "Jpush_awake_file_list");
                d.c("WakeUpWhiteListManager", "获取更新list 大小为1，且pkg 空串，：：" + arrayList);
                if (file.exists()) {
                    file.delete();
                    d.c("WakeUpWhiteListManager", "获取更新list 大小为1，且pkg 空串，删除文件" + file.getAbsolutePath());
                }
            } else {
                a(context, "Jpush_awake_file_list", arrayList);
                d.c("WakeUpWhiteListManager", "获取更新list：：" + arrayList);
                return arrayList;
            }
        }
        return null;
    }

    public final void c(String str) {
        this.e = str;
    }

    public final void d(Context context) {
        this.i.set(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collection c;
        if (Math.abs((System.currentTimeMillis() / 1000) - cn.jiguang.d.a.a.k("jpush_awake_app_pk")) > 86400) {
            c = c(context);
            if (c != null) {
                this.f.clear();
                this.f.addAll(c);
            }
        } else if (this.f.isEmpty()) {
            c = b(context, "Jpush_awake_file_list");
            if (c != null) {
                this.f.clear();
                this.f.addAll(c);
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            int i = (VERSION.SDK_INT < 26 || aVar.c < 26) ? 1 : 2;
            d a = c.a(context, 2, i, aVar, null);
            if (a != null) {
                arrayList.add(a);
            }
        }
        this.i.set(false);
        a(context, arrayList);
    }
}
