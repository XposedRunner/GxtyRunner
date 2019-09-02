package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jpush.android.a;
import cn.jpush.android.a.d;
import cn.jpush.android.a.g;
import cn.jpush.android.d.c;
import cn.jpush.android.d.e;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public abstract class b implements Serializable {
    private static final long serialVersionUID = 8653272927271926594L;
    public int A;
    public String B;
    public boolean C = false;
    public boolean D = false;
    public boolean E = false;
    public boolean F = false;
    public int G = -1;
    public ArrayList<String> H = null;
    public String I;
    private boolean a = false;
    public int b;
    public String c;
    public String d;
    public byte e = (byte) 0;
    public boolean f;
    public int g;
    public int h = 0;
    public boolean i;
    public String j;
    public String k;
    public int l = -1;
    public String m;
    public String n;
    public String o;
    public String p;
    public int q;
    public boolean r;
    public List<String> s = null;
    public int t;
    public String u;
    public String v;
    public int w;
    public String x;
    public String y;
    public String z;

    public abstract void a(Context context);

    protected abstract boolean a(JSONObject jSONObject);

    public final boolean a(Context context, JSONObject jSONObject) {
        e.a("Entity", "action: parse - base entity");
        this.r = jSONObject.optInt("full_screen", 0) > 0;
        this.t = jSONObject.optInt("n_flag", 0);
        this.u = jSONObject.optString("n_title", "");
        this.v = jSONObject.optString("n_content", "");
        this.w = jSONObject.optInt("n_style", 0);
        this.x = jSONObject.optString("n_big_text", "");
        this.y = jSONObject.optString("n_big_pic_path", "");
        this.z = jSONObject.optString("n_inbox", "");
        this.n = jSONObject.optString("n_extras", "");
        this.A = jSONObject.optInt("n_priority", 0);
        this.B = jSONObject.optString("n_category", "");
        this.l = jSONObject.optInt("n_alert_type", -1);
        if (TextUtils.isEmpty(this.u)) {
            if (this.i) {
                e.c("Entity", "Not found notificaiton title for developer mode. Use the application name.");
                this.u = a.d;
            } else {
                e.c("Entity", "Invalid - empty notification title for internal");
                d.a(this.c, 996, null, context);
                return false;
            }
        }
        JSONObject a = g.a(context, this.c, jSONObject, "ad_content");
        if (a != null) {
            if (this.i && this.f) {
                this.a = true;
            }
            return a(a);
        } else if (this.i && this.f) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean a() {
        return this.a;
    }

    static boolean a(ArrayList<String> arrayList, Context context, String str, String str2, boolean z) {
        e.a("Entity", "action:loadHtmlImageResources - urlPrefix:" + str);
        if (!g.a(str) || context == null || arrayList.size() <= 0 || TextUtils.isEmpty(str2)) {
            return true;
        }
        Iterator it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            String str3;
            String str4 = (String) it.next();
            if (str4 == null || str4.startsWith("http://")) {
                str3 = str4;
            } else {
                str3 = str + str4;
            }
            byte[] a = cn.jpush.android.b.a.a(str3, 5, 5000, 4);
            if (a != null) {
                try {
                    if (str4.startsWith("http://")) {
                        str4 = c.a(str4);
                    }
                    if (z) {
                        str4 = c.b(context, str2) + str4;
                    } else {
                        str4 = c.a(context, str2) + str4;
                    }
                    c.a(str4, a);
                    e.a("Entity", "Succeed to load image - " + str4);
                } catch (Throwable e) {
                    e.d("Entity", "Write storage error,  create img file fail.", e);
                    z2 = false;
                }
            } else {
                d.a(str2, PointerIconCompat.TYPE_GRAB, cn.jpush.android.d.a.a(context, str3), context);
                z2 = false;
            }
        }
        return z2;
    }

    static String a(Context context, String str, String str2) {
        if (str.endsWith(".jpg") || str.endsWith(".png")) {
            e.e("Entity", "The url is a picture resources.");
            String str3 = c.b(context, str2) + (str2 + str.substring(str.lastIndexOf(".")));
            e.e("Entity", "Big picture notification resource path: " + str3);
            byte[] a = cn.jpush.android.b.a.a(str, 5, 5000, 4);
            if (a == null) {
                return "";
            }
            try {
                c.a(str3, a);
                return str3;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        e.e("Entity", "The url is not a picture resources.");
        return "";
    }
}
