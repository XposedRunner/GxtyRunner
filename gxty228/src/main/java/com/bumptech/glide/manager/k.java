package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.g;
import com.bumptech.glide.g.h;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RequestManagerRetriever */
public class k implements Callback {
    private static final k c = new k();
    final Map<FragmentManager, j> a = new HashMap();
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> b = new HashMap();
    private volatile g d;
    private final Handler e = new Handler(Looper.getMainLooper(), this);

    public static k a() {
        return c;
    }

    k() {
    }

    private g b(Context context) {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new g(context.getApplicationContext(), new b(), new f());
                }
            }
        }
        return this.d;
    }

    public g a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (h.b() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return a((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return a((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return a(((ContextWrapper) context).getBaseContext());
            }
        }
        return b(context);
    }

    public g a(FragmentActivity fragmentActivity) {
        if (h.c()) {
            return a(fragmentActivity.getApplicationContext());
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager());
    }

    @TargetApi(11)
    public g a(Activity activity) {
        if (h.c() || VERSION.SDK_INT < 11) {
            return a(activity.getApplicationContext());
        }
        b(activity);
        return a((Context) activity, activity.getFragmentManager());
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    j a(FragmentManager fragmentManager) {
        j jVar = (j) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (jVar != null) {
            return jVar;
        }
        jVar = (j) this.a.get(fragmentManager);
        if (jVar != null) {
            return jVar;
        }
        Fragment jVar2 = new j();
        this.a.put(fragmentManager, jVar2);
        fragmentManager.beginTransaction().add(jVar2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(1, fragmentManager).sendToTarget();
        return jVar2;
    }

    @TargetApi(11)
    g a(Context context, FragmentManager fragmentManager) {
        j a = a(fragmentManager);
        g b = a.b();
        if (b != null) {
            return b;
        }
        b = new g(context, a.a(), a.c());
        a.a(b);
        return b;
    }

    SupportRequestManagerFragment a(android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        supportRequestManagerFragment = (SupportRequestManagerFragment) this.b.get(fragmentManager);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        android.support.v4.app.Fragment supportRequestManagerFragment2 = new SupportRequestManagerFragment();
        this.b.put(fragmentManager, supportRequestManagerFragment2);
        fragmentManager.beginTransaction().add(supportRequestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(2, fragmentManager).sendToTarget();
        return supportRequestManagerFragment2;
    }

    g a(Context context, android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment a = a(fragmentManager);
        g b = a.b();
        if (b != null) {
            return b;
        }
        b = new g(context, a.a(), a.c());
        a.a(b);
        return b;
    }

    public boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case 1:
                FragmentManager fragmentManager = (FragmentManager) message.obj;
                remove = this.a.remove(fragmentManager);
                break;
            case 2:
                android.support.v4.app.FragmentManager fragmentManager2 = (android.support.v4.app.FragmentManager) message.obj;
                remove = this.b.remove(fragmentManager2);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && r1 == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj);
        }
        return z;
    }
}
