package cn.jiguang.d.h;

import android.content.ComponentName;
import java.util.HashMap;

public final class d {
    private ComponentName a;
    private HashMap<Integer, Boolean> b = new HashMap();

    public final ComponentName a() {
        return this.a;
    }

    public final void a(int i, boolean z) {
        this.b.put(Integer.valueOf(i), Boolean.valueOf(z));
    }

    public final void a(ComponentName componentName) {
        this.a = componentName;
    }

    public final HashMap<Integer, Boolean> b() {
        return this.b;
    }
}
