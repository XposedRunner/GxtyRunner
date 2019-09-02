package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.c.a;

/* compiled from: ConnectivityMonitorFactory */
public class d {
    public c a(Context context, a aVar) {
        if ((context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 ? 1 : null) != null) {
            return new e(context, aVar);
        }
        return new i();
    }
}
