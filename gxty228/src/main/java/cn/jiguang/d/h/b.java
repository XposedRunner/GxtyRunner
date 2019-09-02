package cn.jiguang.d.h;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import cn.jiguang.e.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

public final class b implements ServiceConnection {
    public static HashMap<String, WeakReference<ServiceConnection>> a = new HashMap();
    public Context b;
    private boolean c = false;

    public b(Context context, boolean z) {
        this.b = context;
        this.c = z;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        d.c("CustomServiceConnection", "onServiceConnected:" + componentName);
        CharSequence charSequence = null;
        if (componentName != null) {
            try {
                charSequence = componentName.getPackageName() + componentName.getClassName();
            } catch (Throwable th) {
                d.c("CustomServiceConnection", "handleonServiceConnected error:" + th);
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            a.remove(charSequence);
        }
        this.b.getApplicationContext().unbindService(this);
        d.e("CustomServiceConnection", "CustomServiceConnection unbindService");
        if (this.c) {
            ArrayList arrayList = new ArrayList();
            d dVar = new d();
            dVar.a(componentName);
            if (componentName != null) {
                dVar.a(2, true);
            } else {
                dVar.a(2, false);
            }
            arrayList.add(dVar);
            JSONObject a = f.a().b().a(this.b.getPackageName(), arrayList);
            d.c("CustomServiceConnection", "report:" + a);
            e.b(this.b, "android_awake", a);
            return;
        }
        d.c("CustomServiceConnection", "while list wakeup, not report here.");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        d.c("CustomServiceConnection", "onServiceDisconnected:" + componentName);
    }
}
