package cn.jiguang.d.g;

import android.content.Context;
import android.database.Cursor;
import cn.jiguang.d.a.a;
import cn.jiguang.d.a.g;
import cn.jiguang.d.a.h;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import cn.jiguang.g.m;
import com.lzy.okgo.model.Progress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    private static JSONObject a(h hVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("net", hVar.b());
            jSONObject.put("conn_ip", hVar.c());
            jSONObject.put("local_dns", hVar.d());
            jSONObject.put("source", hVar.e());
            jSONObject.put("times", hVar.g());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("count_0_1", hVar.h());
            jSONObject2.put("count_1_3", hVar.i());
            jSONObject2.put("count_3_", hVar.j());
            jSONObject.put("success_details", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static void a(Context context) {
        if (context != null) {
            b(context);
            c(context);
        }
    }

    public static synchronized void a(Context context, int i, long j, int i2) {
        synchronized (e.class) {
            d.e("IndexStats", "insertData");
            String c = m.c(context);
            String d = a.d();
            if (d == null) {
                d = "";
            }
            String str = c + "_" + i + "_" + d + "_" + cn.jiguang.g.a.b();
            d.c("IndexStats", "sort_key : " + str + "    login_costime: " + j);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            if (i2 == 0) {
                if (j <= 1000) {
                    i3 = 1;
                }
                if (j > 1000 && j <= 3000) {
                    i4 = 1;
                }
                if (j > 3000) {
                    i5 = 1;
                }
            }
            try {
                g a = g.a(context);
                h a2 = a.a(str);
                if (a2 != null) {
                    d.e("IndexStats", "sort_key:" + str + " is exists : update");
                    a.b(str, c, d, cn.jiguang.g.a.b(), i, a2.f() + i2, a2.g() + 1, i3 + a2.h(), i4 + a2.i(), i5 + a2.j(), a2.k() + 0);
                } else {
                    d.e("IndexStats", "sort_key:" + str + " not exists : insert");
                    a.a(str, c, d, cn.jiguang.g.a.b(), i, i2, 1, i3, i4, i5, 0);
                }
            } catch (Exception e) {
                d.g("IndexStats", "insertData exception:" + e.getMessage());
            }
        }
    }

    private static JSONObject b(h hVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("net", hVar.b());
            jSONObject.put("conn_ip", hVar.c());
            jSONObject.put("local_dns", hVar.d());
            jSONObject.put("source", hVar.e());
            jSONObject.put("times", hVar.g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static synchronized void b(Context context) {
        synchronized (e.class) {
            if (cn.jiguang.d.a.d.e(context)) {
                g a = g.a(context);
                if (a.a(false)) {
                    h a2;
                    Cursor cursor = null;
                    JSONObject jSONObject = new JSONObject();
                    o.b(context, jSONObject, "sdk_index");
                    jSONObject.put(Progress.DATE, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(a.b())));
                    jSONObject.put("login_total", a.c(true));
                    jSONObject.put("login_failed", a.c(false));
                    JSONArray jSONArray = new JSONArray();
                    cursor = a.a();
                    if (cursor != null) {
                        do {
                            a2 = g.a(cursor);
                            if (a2 == null || k.a(a2.a())) {
                                d.c("IndexStats", "FailedTop3 is null ");
                            } else {
                                try {
                                    jSONArray.put(b(a2));
                                } catch (Exception e) {
                                    d.i("IndexStats", "buildStatisticsAndReport exception:" + e.getMessage());
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    a.b(false);
                                } catch (Throwable th) {
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    a.b(false);
                                }
                            }
                        } while (cursor.moveToNext());
                        cursor.close();
                    }
                    jSONObject.put("failed_top", jSONArray);
                    jSONArray = new JSONArray();
                    cursor = a.b();
                    if (cursor != null) {
                        do {
                            a2 = g.a(cursor);
                            if (a2 == null || k.a(a2.a())) {
                                d.c("IndexStats", "SucceedTop3 is null ");
                            } else {
                                jSONArray.put(a(a2));
                            }
                        } while (cursor.moveToNext());
                        cursor.close();
                    }
                    jSONObject.put("succeed_top", jSONArray);
                    o.a(context, jSONObject);
                    if (cursor != null) {
                        cursor.close();
                    }
                    a.b(false);
                } else {
                    d.i("IndexStats", "buildStatisticsAndReport exception: db open failed");
                }
            }
        }
    }

    private static synchronized void c(Context context) {
        synchronized (e.class) {
            g a = g.a(context);
            if (a.a(true)) {
                try {
                    a.getWritableDatabase().execSQL("delete from jpush_statistics");
                    a.b(true);
                } catch (Exception e) {
                    d.i("StatisticsDB", "deleteTable exception:" + e.getMessage());
                    a.b(true);
                } catch (Throwable th) {
                    a.b(true);
                }
            } else {
                d.i("StatisticsDB", "deleteTable exception: db open failed");
            }
        }
    }
}
