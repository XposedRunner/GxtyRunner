package cn.jiguang.d.d;

import cn.jiguang.d.g.c;
import cn.jiguang.d.g.f;
import java.io.File;
import org.json.JSONObject;

public final class l {
    private File a;
    private JSONObject b;
    private boolean c;
    private boolean d = true;
    private long e;

    public l(File file) {
        this.a = file;
        this.e = file.length();
        this.b = m.a(file);
        if (this.b != null) {
            this.c = b(this.b);
        }
    }

    public l(File file, JSONObject jSONObject) {
        this.a = file;
        this.b = jSONObject;
        if (this.b != null) {
            this.c = b(this.b);
            this.e = (long) f.b(this.b);
        }
    }

    public static boolean b(JSONObject jSONObject) {
        return jSONObject.optLong("uid", -1) > 0;
    }

    public final long a() {
        return this.e;
    }

    public final void a(JSONObject jSONObject) {
        this.b = jSONObject;
        this.d = false;
        c.a(this.a, jSONObject.toString());
        this.e = this.a.length();
    }

    public final File b() {
        return this.a;
    }

    public final JSONObject c() {
        return this.b;
    }

    public final boolean d() {
        return this.c;
    }
}
