package cn.jpush.android.data;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.net.HttpResponse;
import cn.jiguang.net.HttpUtils;
import cn.jpush.android.a.d;
import cn.jpush.android.d.a;
import cn.jpush.android.d.b;
import cn.jpush.android.d.c;
import cn.jpush.android.d.e;
import java.util.ArrayList;
import org.json.JSONObject;

public final class g extends b {
    private static final long serialVersionUID = 2748721849169550485L;
    public String J;
    public int K;
    public int L;
    public int M;
    public ArrayList<String> N;
    public String O;
    public String P;
    public String Q;
    public String a;

    public g() {
        this.N = new ArrayList();
        this.O = "";
        this.P = "";
        this.q = 0;
    }

    public final boolean a(JSONObject jSONObject) {
        e.a("ShowEntity", "action: parse - content");
        this.a = jSONObject.optString("e_url", "").trim();
        this.J = jSONObject.optString("e_title", "").trim();
        if (!(TextUtils.isEmpty(this.a) || cn.jpush.android.a.g.a(this.a))) {
            this.a = "http://" + this.a;
            e.e("ShowEntity", "Add http to non-prefix url: " + this.a);
        }
        this.L = jSONObject.optInt("e_rich_type", 0);
        this.K = jSONObject.optInt("e_jump_mode", 0);
        this.M = jSONObject.optInt("e_show", 0);
        if (3 == this.L || 2 == this.L || 1 == this.L) {
            this.N = b.a(jSONObject.optJSONArray("e_eres"));
        }
        this.O = jSONObject.optString("from_num", "");
        this.P = jSONObject.optString("to_num", "");
        return true;
    }

    public final void a(final Context context) {
        e.a("ShowEntity", "action:process");
        new Thread(this) {
            final /* synthetic */ g c;

            public final void run() {
                e.a("ShowEntity", "showEntity process start run! showMode = " + this.c.M + ", richType = " + this.c.L + ", jumpMode = " + this.c.K);
                if (this.c.M != 0) {
                    e.c("ShowEntity", "Unexpected: unknown show  mode - " + this.c.M);
                    return;
                }
                String str = this.c;
                String str2 = this.a;
                String str3 = this.y;
                JCoreInterface.triggerSceneCheck(context.getApplicationContext(), 3);
                if (this.L == 0) {
                    if (this.w == 3 && !TextUtils.isEmpty(str3)) {
                        Object a;
                        if (str3.startsWith("http://") || str3.startsWith("https://")) {
                            if (a.b(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                                a = b.a(context, str3, str);
                                if (TextUtils.isEmpty(a)) {
                                    e.h("ShowEntity", "Get network picture failed, show basic notification only.");
                                } else {
                                    this.y = a;
                                }
                            } else {
                                e.h("ShowEntity", "No permission to write resource to storage, show basic notification only.");
                            }
                        } else if (a.b(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                            a = c.c(context, str3);
                            if (TextUtils.isEmpty(a)) {
                                e.h("ShowEntity", "Get developer picture failed, show basic notification only.");
                            } else {
                                this.y = a;
                            }
                        } else {
                            e.h("ShowEntity", "No permission to read resource from storage, show basic notification only.");
                        }
                    }
                    d.a(str, 995, null, context);
                    cn.jpush.android.api.b.a(context, this);
                } else if (4 == this.L) {
                    this.Q = str2;
                    d.a(str, 995, null, context);
                    cn.jpush.android.api.b.a(context, this);
                } else if (a.b(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    int i;
                    String str4;
                    if (TextUtils.isEmpty(str2)) {
                        e.g("ShowEntity", "Show url is Empty! Give up to download!");
                        i = 0;
                        str4 = null;
                    } else {
                        for (i = 0; i < 4; i++) {
                            HttpResponse a2 = cn.jpush.android.b.a.a(str2, 5, 5000);
                            if (a2 != null && a2.getResponseCode() == 200) {
                                i = 1;
                                str4 = a2.getResponseBody();
                                break;
                            }
                        }
                        i = 0;
                        str4 = null;
                    }
                    String b = c.b(context, str);
                    if (i != 0) {
                        str3 = b + str + ".html";
                        String substring = str2.substring(0, str2.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1);
                        if (this.N.isEmpty()) {
                            this.Q = this.a;
                            cn.jpush.android.api.b.a(context, this);
                            return;
                        } else if (b.a(this.N, context, substring, str, this.a())) {
                            e.c("ShowEntity", "Loads rich push resources succeed, webView will open cache!");
                            if (c.a(str3, str4.replaceAll("img src=\"" + substring, "img src=\"" + b), context)) {
                                this.Q = "file://" + str3;
                                d.a(str, 995, null, context);
                                cn.jpush.android.api.b.a(context, this);
                                return;
                            }
                            d.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, null, context);
                            return;
                        } else {
                            e.c("ShowEntity", "Loads rich push resources failed, webView will open url!");
                            d.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, null, context);
                            cn.jpush.android.api.b.a(context, this);
                            return;
                        }
                    }
                    e.g("ShowEntity", "NOTE: failed to download html page. Give up this.");
                    d.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, null, context);
                    d.a(str, 1021, a.a(context, str2), context);
                } else {
                    e.j("ShowEntity", "Rich-push needs the permission of WRITE_EXTERNAL_STORAGE, please request it.");
                    d.a(str, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, null, context);
                }
            }
        }.start();
    }
}
