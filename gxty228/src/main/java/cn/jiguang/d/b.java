package cn.jiguang.d;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import cn.jiguang.d.d.j;
import cn.jiguang.e.d;
import cn.jiguang.g.a.a;

final class b implements ServiceConnection {
    b() {
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        d.c("JCoreGlobal", "action - onServiceConnected, ComponentName:" + componentName.toString());
        d.e("JCoreGlobal", "Remote Service bind success.");
        a.a(cn.jiguang.b.b.a(iBinder));
        if (a.e != null) {
            j.a().b(a.e, "intent.INIT", new Bundle());
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        d.c("JCoreGlobal", "action - onServiceDisconnected, ComponentName:" + componentName.toString());
        a.d();
    }
}
