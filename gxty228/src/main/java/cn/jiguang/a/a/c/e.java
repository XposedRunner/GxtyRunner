package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import cn.jiguang.d.a.a;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.o;
import cn.jiguang.g.k;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e implements UncaughtExceptionHandler {
    private static e b = new e();
    private static int c = 1048576;
    public boolean a;
    private UncaughtExceptionHandler d;
    private Context e;

    private e() {
        this.d = null;
        this.a = true;
        this.a = d.b();
    }

    public static e a() {
        return b;
    }

    private static String a(Throwable th, String str) {
        String str2 = str + th.toString();
        try {
            String[] split = str2.split(":");
            if (split.length <= 1) {
                return str2;
            }
            int length = split.length - 1;
            while (length >= 0) {
                if (split[length].endsWith("Exception") || split[length].endsWith("Error")) {
                    return split[length];
                }
                length--;
            }
            return str2;
        } catch (NullPointerException e) {
            return str2;
        } catch (PatternSyntaxException e2) {
            return str2;
        }
    }

    private JSONArray a(Context context, JSONArray jSONArray, Throwable th, String str) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        try {
            Object optJSONObject;
            int i;
            long u = a.u();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null && stringWriter2.equals(optJSONObject.getString("stacktrace"))) {
                    optJSONObject.put(ParamKey.COUNT, optJSONObject.getInt(ParamKey.COUNT) + 1);
                    optJSONObject.put("crashtime", System.currentTimeMillis() + u);
                    i = i2;
                    break;
                }
            }
            optJSONObject = null;
            i = 0;
            if (optJSONObject != null) {
                jSONArray = a(jSONArray, i);
                jSONArray.put(optJSONObject);
                return jSONArray;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("crashtime", System.currentTimeMillis() + u);
            jSONObject.put("stacktrace", stringWriter2);
            jSONObject.put("message", a(th, str));
            jSONObject.put(ParamKey.COUNT, 1);
            if (!(this.e == null && context == null)) {
                jSONObject.put("networktype", cn.jiguang.g.a.d(context));
            }
            if (this.e != null) {
                PackageInfo packageInfo = this.e.getPackageManager().getPackageInfo(this.e.getPackageName(), 1);
                if (packageInfo != null) {
                    optJSONObject = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                    String str2 = packageInfo.versionCode;
                    jSONObject.put("versionname", optJSONObject);
                    jSONObject.put("versioncode", str2);
                }
            }
            jSONArray.put(jSONObject);
            return jSONArray;
        } catch (Throwable th2) {
            return jSONArray;
        }
    }

    private static JSONArray a(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (i2 != i) {
                try {
                    jSONArray2.put(jSONArray.get(i2));
                } catch (JSONException e) {
                }
            }
        }
        return jSONArray2;
    }

    public static void a(Context context, String str, String str2) {
        cn.jiguang.e.d.a("JPushCrashHandler", "Action - handleInfoReport reportType:" + str + " extraInfo:" + str2);
        if (k.a(str)) {
            cn.jiguang.e.d.a("JPushCrashHandler", "reportType was null, return");
        } else if (str.equals("crash_log")) {
            e eVar = b;
            if (context == null) {
                cn.jiguang.e.d.g("JPushCrashHandler", "Action - reportCrashLog context is null");
            } else if (d.e(context)) {
                JSONObject e = e(context);
                if (e != null) {
                    o.a(context, e);
                    d(context);
                }
            }
        } else {
            cn.jiguang.e.d.g("JPushCrashHandler", "unHandle report type!");
        }
    }

    private static void a(Context context, JSONArray jSONArray) {
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null && jSONArray2.length() > 0 && context != null) {
            try {
                FileOutputStream openFileOutput = context.openFileOutput("jpush_uncaughtexception_file", 0);
                openFileOutput.write(jSONArray2.getBytes());
                openFileOutput.flush();
                openFileOutput.close();
            } catch (IOException e) {
            }
        }
    }

    public static void d(Context context) {
        if (context == null) {
            cn.jiguang.e.d.g("JPushCrashHandler", "Action - deleteCrashLog context is null");
            return;
        }
        File file = new File(context.getFilesDir(), "jpush_uncaughtexception_file");
        if (file.exists()) {
            file.delete();
        }
    }

    public static JSONObject e(Context context) {
        JSONArray f = f(context);
        if (f == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashlogs", f);
            jSONObject.put("network_type", cn.jiguang.g.a.d(context));
            o.b(context, jSONObject, "crash_log");
            JSONObject a = b.a(context);
            if (a == null || a.length() <= 0) {
                return jSONObject;
            }
            jSONObject.put("device_info", a);
            return jSONObject;
        } catch (Exception e) {
            return jSONObject;
        }
    }

    private static JSONArray f(Context context) {
        Exception e;
        Throwable th;
        JSONArray jSONArray = null;
        if (new File(context.getFilesDir(), "jpush_uncaughtexception_file").exists()) {
            FileInputStream openFileInput;
            try {
                openFileInput = context.openFileInput("jpush_uncaughtexception_file");
                try {
                    byte[] bArr = new byte[1024];
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        int read = openFileInput.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        stringBuffer.append(new String(bArr, 0, read));
                    }
                    if (stringBuffer.toString().length() > 0) {
                        jSONArray = new JSONArray(stringBuffer.toString());
                    }
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return jSONArray;
                    } catch (Throwable th2) {
                        th = th2;
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                openFileInput = null;
                e.printStackTrace();
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return jSONArray;
            } catch (Throwable th3) {
                openFileInput = null;
                th = th3;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw th;
            }
        }
        return jSONArray;
    }

    public final void a(Context context) {
        this.e = context;
        if (this.d == null) {
            this.d = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public final void b(Context context) {
        this.e = context;
        if (!this.a) {
            this.a = true;
            d.a(context, true);
        }
    }

    public final void c(Context context) {
        this.e = context;
        if (this.a) {
            this.a = false;
            d.a(context, false);
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (this.a) {
            cn.jiguang.e.d.a("JPushCrashHandler", "enable crash report");
            Context context = this.e;
            JSONArray a = a(context, f(context), th, "");
            d(this.e);
            a(this.e, a);
            f fVar = new f(this);
            fVar.start();
            try {
                fVar.join(2000);
            } catch (InterruptedException e) {
            }
        } else {
            cn.jiguang.e.d.a("JPushCrashHandler", "disable crash report");
        }
        if (this.d != this) {
            this.d.uncaughtException(thread, th);
        }
        throw new RuntimeException(th);
    }
}
